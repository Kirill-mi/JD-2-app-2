package by.kirill.pympproject.dao.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;

public class ConnectionPoolOld {
    private static ConnectionPoolOld instance = null;
    private DataSource ds;
    private static final String PATH = "C:\\Users\\Кирилл\\IdeaProjects\\pumpProject\\src\\main\\resources\\by\\kirill\\pumpproject\\dao\\connection\\db_ru_RU.properties";

    private ConnectionPoolOld() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ds = new DataSource();
        ds.setUrl(properties.getProperty("db.url"));
        ds.setUsername(properties.getProperty("db.user"));
        ds.setPassword(properties.getProperty("db.password"));
        ds.setMaxIdle(Integer.parseInt(properties.getProperty("db.maxIdle")));
        ds.setMinIdle(Integer.parseInt(properties.getProperty("db.minIdle")));
        ds.setDriverClassName(properties.getProperty("db.driver"));
        ds.setInitialSize(Integer.parseInt(properties.getProperty("db.pollSize")));
        ds.setMaxActive(Integer.parseInt(properties.getProperty("db.maxActive")));
    }

    public static ConnectionPoolOld getInstance() {
        if (instance == null) {
            instance = new ConnectionPoolOld();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
