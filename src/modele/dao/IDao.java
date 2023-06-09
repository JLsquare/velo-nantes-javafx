package modele;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interface IDao to implement the DAO pattern
 * @param <T> the type of the DAO
 * @author Groupe 4B2
 */
public interface IDao<T> {

    /**
     * Get all the T from the database
     * @return an ArrayList of T
     */
    public ArrayList<T> getAll();

    /**
     * Read all the T from the database
     */
    public void readAll() throws SQLException;

    /**
     * Add a T to the database
     * @param t the T to add
     */
    public void add(T t) throws SQLException;

    /**
     * Remove a T from the database
     * @param t the T to remove
     */
    public void remove(T t) throws SQLException;

    /**
     * Update a T from the database
     * @param t the T to update
     */
    public void update(T t) throws SQLException;
}
