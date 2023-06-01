package modele;

import java.sql.*;

/**
 * Database class which communicate with the mysql/mariadb database
 * It use JDBC to interact with the database
 * It has two connections, one for read access and one for write access if the user has the rights
 * @author Groupe 4B2
 */
public class Database {

    // ---------------- Attributes ---------------- //

    private static final String URL = "jdbc:mariadb://localhost:3306/bd_velo";
    private static Connection readConnection;
    private static Connection writeConnection;

    // ---------------- Constructors ---------------- //

    /**
     * Default constructor
     */
    public Database() {
        // Si besoin
    }

    // ---------------- Methods ---------------- //

    /**
     * Open a connection to the database with read access
     * @param user the user
     * @param password the password
     * @throws SQLException if there is an error with the connection
     */
    public static void openReadConnection(String user, String password) throws SQLException{
        if(readConnection != null){
            System.out.println("Read connection already open");
        } else {
            readConnection = DriverManager.getConnection(URL, user, password);
        }
    }

    /**
     * Open a connection to the database with write access
     * @param user the user
     * @param password the password
     * @throws SQLException if there is an error with the connection
     */
    public static void openWriteConnection(String user, String password) throws SQLException{
        if(writeConnection != null){
            System.out.println("Write connection already open");
        } else {
            writeConnection = DriverManager.getConnection(URL, user, password);
        }
    }

    /**
     * Close the connection to the database with read access
     * @throws SQLException if there is an error with the connection
     */
    public static void closeReadConnection() throws SQLException{
        if(readConnection != null){
            readConnection.close();
            readConnection = null;
        }
    }

    /**
     * Close the connection to the database with write access
     * @throws SQLException if there is an error with the connection
     */
    public static void closeWriteConnection() throws SQLException{
        if(writeConnection != null){
            writeConnection.close();
            writeConnection = null;
        }
    }

    /**
     * Execute a read query
     * @param query the query
     * @return the ResultSet
     * @throws SQLException if there is an error with the query
     */
    public static ResultSet executeReadQuery(String query) throws SQLException{
        Statement stmt = readConnection.createStatement();
        return stmt.executeQuery(query);
    }

    /**
     * Execute a write query
     * @param query the query
     * @throws SQLException if there is an error with the query
     */
    public static void executeWriteQuery(String query) throws SQLException{
        Statement stmt = writeConnection.createStatement();
        stmt.executeUpdate(query);
    }

    /**
     * Load all the data from the database
     * @throws SQLException if there is an error with the query
     */
    public static void loadDatabase() throws SQLException{
        new Quartier(0, "Inconnu", 0);
        ResultSet rs = executeReadQuery("SELECT * FROM QUARTIER");
        while(rs.next()){
            new Quartier(rs);
        }

        rs = executeReadQuery("SELECT * FROM COMPTEUR");
        while(rs.next()){
            new Compteur(rs);
        }

        rs = executeReadQuery("SELECT * FROM DATEINFO");
        while(rs.next()){
            new DateInfo(rs);
        }

        rs = executeReadQuery("SELECT * FROM COMPTAGE");
        while(rs.next()){
            new Comptage(rs);
        }
    }
}