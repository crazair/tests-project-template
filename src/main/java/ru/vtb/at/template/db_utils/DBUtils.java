package ru.vtb.at.template.db_utils;

import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vtb.at.template.Props;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.List;

import static io.qameta.allure.Allure.addAttachment;

public abstract class DBUtils {

    private static final Logger LOG = LoggerFactory.getLogger(DBUtils.class);
    protected static final Props props = ConfigFactory.create(Props.class);
    private final Driver driver;

    public DBUtils(Driver driver) {
        this.driver = driver;
    }

    abstract public String getDbUrl();

    abstract public String getDbUsername();

    abstract public String getDbPassword();

    /**
     * Метод для выполнения SQL запросов на нужном экзепляре БД.
     *
     * @param query   - выполняемая SQL команда. Поддерживается два типа List<String> для выполнения группы команд
     *                и String для выполнения одиночной команды
     * @return возвращает CachedRowSet при SELECT запросах или null в других случаях
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    @Step("Выполнение SQL запроса: {query}")
    public <T> CachedRowSet execute(T query) throws SQLException {
        Connection connection = null;
        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet rowSet = factory.createCachedRowSet();
        try {
            connection = DriverManager.getConnection(getDbUrl(), getDbUsername(), getDbPassword());
            DriverManager.registerDriver(driver);
            ResultSet result = query instanceof String ? executeSQL(connection, query.toString()) : executeSQL(connection, (List<String>) query);
            if (result != null && result.getMetaData().getColumnCount() != 0) {
                writeToLog(result);
                rowSet.populate(result);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return rowSet;
    }

    private ResultSet executeSQL(Connection connection, List<String> query) throws SQLException {
        LOG.info("SQL EXECUTE BATCH: " + StringUtils.join(query, "\n"));
        addAttachment("SQL_EXECUTE_BATCH", StringUtils.join(query, "\n"));
        Statement statement = connection.createStatement();
        for (String sqlEx : query) {
            statement.addBatch(sqlEx);
        }
        statement.executeBatch();
        return null;
    }

    private ResultSet executeSQL(Connection connection, String query) throws SQLException {
        LOG.info("SQL EXECUTE: " + query);
        addAttachment("SQL_EXECUTE", query);
        Statement statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );
        return statement.executeQuery(query);
    }

    /**
     * Метод для записи ResultSet в лог.
     *
     * @param result - результат выполнения SQL запроса
     * @throws SQLException
     */
    private void writeToLog(ResultSet result) throws SQLException {
        int numCols = result.getMetaData().getColumnCount();
        StringBuilder sb = new StringBuilder();
        while (result.next()) {
            sb.append("===Row №").append(result.getRow()).append("===").append("\n");
            for (int i = 1; i <= numCols; i++) {
                sb.append(result.getMetaData().getColumnName(i)).append(" = ");
                sb.append(result.getString(i)).append("\n");
            }
        }
        LOG.info("SQL RESULTS:\n" + sb.toString());
        addAttachment("SQL_RESULTS", sb.toString());
        while (result.previous());
    }
}
