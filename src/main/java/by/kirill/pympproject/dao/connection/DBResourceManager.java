package by.kirill.pympproject.dao.connection;

import java.util.Locale;
import java.util.ResourceBundle;

public class DBResourceManager {

    private final static String DB_RESOURCE = "by\\kirill\\pumpproject\\dao\\connection\\db_ru_RU";

    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle resourceBundle = ResourceBundle.getBundle(DB_RESOURCE);

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return resourceBundle.getString(key);
    }
}
