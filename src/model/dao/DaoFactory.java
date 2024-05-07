package model.dao;

import Datasource.DataSource;
import com.sun.jdi.connect.spi.Connection;

public class DaoFactory {
    public static ProductoDao createProductoDao(){
        return new ProductoDaoImpl((Connection) DataSource.getConnection());
    }
}
