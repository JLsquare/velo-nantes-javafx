package modele;

import java.sql.SQLException;
import java.util.ArrayList;

interface IDao<T> {
    public ArrayList<T> getAll() throws SQLException;
    public void add(T t) throws SQLException;
    public void remove(T t) throws SQLException;
    public void update(T t) throws SQLException;
}
