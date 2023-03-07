package ru.vtb.at.template.db;

import oracle.jdbc.driver.OracleDriver;
import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DisplayName("DB тест")
public class DemoDBTest {

    private DBClient db;

    @BeforeEach
    void setUp() throws SQLException {
        db = new DBClient(new OracleDriver());

        //Пример выполнения группы запросов
        List<String> batchQuery = new ArrayList<>();
        batchQuery.add("UPDATE TABLE SET VALUE1 = 1 WHERE ID = 1");
        batchQuery.add("UPDATE TABLE SET VALUE2 = 2 WHERE ID = 2");
        db.execute(batchQuery);
    }

    @Test
    @DisplayName("Проверка значения в БД")
    void test() throws SQLException {
        //Пример выполнения одиночного запроса
        String query = "SELECT VALUE2 FROM TABLE WHERE ID = 2";
        ResultSet result =  db.execute(query);
        while (result.next()) {
            Assertions.assertEquals(2, result.getInt("VALUE2"));
        }
    }
}
