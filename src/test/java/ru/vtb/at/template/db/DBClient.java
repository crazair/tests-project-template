package ru.vtb.at.template.db;

import ru.vtb.at.template.db_utils.DBUtils;

import java.sql.Driver;

public class DBClient extends DBUtils {

    public DBClient(Driver driver) {
        super(driver);
    }

    @Override
    public String getDbUrl() {
        return props.dbUrl();
    }

    @Override
    public String getDbUsername() {
        return props.dbLogin();
    }

    @Override
    public String getDbPassword() {
        return props.dbPassword();
    }
}
