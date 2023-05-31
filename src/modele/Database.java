package modele;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/bd_velo";
    private static Connection readConnection;
    private static Connection writeConnection;

    public Database() {
        // Si besoin
    }

    public static void openReadConnection(String user, String password) throws SQLException{
        if(readConnection != null){
            System.out.println("Read connection already open");
        } else {
            readConnection = DriverManager.getConnection(URL, user, password);
        }
    }

    public static void openWriteConnection(String user, String password) throws SQLException{
        if(writeConnection != null){
            System.out.println("Write connection already open");
        } else {
            writeConnection = DriverManager.getConnection(URL, user, password);
        }
    }

    public static Connection getWriteConnection() throws NoDatabaseException {
        if(writeConnection == null){
            throw new NoDatabaseException("Write connection is null");
        }
        return writeConnection;
    }

    public static Connection getReadConnection() throws NoDatabaseException {
        if(readConnection == null){
            throw new NoDatabaseException("Read connection is null");
        }
        return readConnection;
    }
}