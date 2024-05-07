package Datasource;

import java.sql.Connection;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        Connection coneccion =DataSource.getConnection();

        if (!coneccion.isClosed()){
            System.out.println("coneccion realizada correctamente");
        }
        coneccion.close();

        if (coneccion.isClosed()) {
            System.out.println("coneccion cerrada correctamente");
        }
    }
}