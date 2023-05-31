package modele;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mariadb://localhost:3306/bd_velo";
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

    public static Connection getWriteConnection() throws NoConnectionException {
        if(writeConnection == null){
            throw new NoConnectionException("Write connection is null");
        }
        return writeConnection;
    }

    public static Connection getReadConnection() throws NoConnectionException {
        if(readConnection == null){
            throw new NoConnectionException("Read connection is null");
        }
        return readConnection;
    }

    public static void closeReadConnection() throws SQLException{
        if(readConnection != null){
            readConnection.close();
            readConnection = null;
        }
    }

    public static void closeWriteConnection() throws SQLException{
        if(writeConnection != null){
            writeConnection.close();
            writeConnection = null;
        }
    }

    public static ResultSet executeReadQuery(String query) throws SQLException{
        Statement stmt = readConnection.createStatement();
        return stmt.executeQuery(query);
    }

    public static void executeWriteQuery(String query) throws SQLException{
        Statement stmt = writeConnection.createStatement();
        stmt.executeUpdate(query);
    }

    public static void loadQuartiers() throws SQLException{
        ResultSet rs = executeReadQuery("SELECT * FROM QUARTIER");
        while(rs.next()){
            new Quartier(rs);
        }
    }

    public static void loadCompteurs() throws SQLException{
        ResultSet rs = executeReadQuery("SELECT * FROM COMPTEUR");
        while(rs.next()){
            new Compteur(rs);
        }
    }

    public static void loadDateInfos() throws SQLException{
        ResultSet rs = executeReadQuery("SELECT * FROM DATEINFO");
        while(rs.next()){
            new DateInfo(rs);
        }
    }

    public static void loadComptages() throws SQLException{
        ResultSet rs = executeReadQuery("SELECT * FROM COMPTAGE");
        while(rs.next()){
            new Comptage(rs);
        }
    }
}