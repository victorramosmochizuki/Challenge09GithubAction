 package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

    public static Connection cnx = null;

    public static Connection conectar()  {
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://MANUEL;databaseName=tiendita_de_motos";
            String user = "sa";
            String pwd = "123manuelgamer";
            Class.forName(driver).newInstance();
            cnx = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            System.err.println("Error en la conexión " + e.getMessage());
        }
        return cnx;
        
    }
   public static Connection getCnx() {
        return cnx;
    }

    public static void setCnx(Connection aCnx) {
        cnx = aCnx;
    }

    public void Cerrar() throws Exception {
        if (cnx != null) {
            cnx.close();
        }
    } 
     public static void main(String[] args) {
        conectar();
        try {
            if (cnx != null) {
                System.out.println("Conexion exitosa");
            } else {
                System.out.println("Conexion fallida");
            }
        } catch (Exception e) {
            System.out.println("El error es " + e.getMessage());
        }

    }

    public Connection getCn() {
        return cnx;
    }

    public void setCn(Connection cnx) {
        this.cnx = cnx;
    }
}



