package modele;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/bd_velo";
    public static Database readDatabase;
    public static Database writeDatabase;

    private final String USER;
    private final String PASSWORD;
    private Connection connection;

    public Database(String user, String password, boolean type) throws SQLException, NullPointerException {
        if(user == null || password == null){
            throw new NullPointerException("User or password can't be null");
        }

        this.USER = user;
        this.PASSWORD = password;

        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);

        if(type){
            readDatabase = this;
        }else{
            writeDatabase = this;
        }
    }

    public void closeConnection() throws SQLException{
        this.connection.close();
    }

    public static Connection getWriteConnection() throws NoDatabaseException {
        if(writeDatabase == null){
            throw new NoDatabaseException("No write database");
        }
        return writeDatabase.connection;
    }

    public static Connection getReadConnection() throws NoDatabaseException {
        if(readDatabase == null){
            throw new NoDatabaseException("No read database");
        }
        return readDatabase.connection;
    }
}