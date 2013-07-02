package za.co.yellowfire.carat.db;

import java.util.List;

public interface Dao<T> {
    List<T> retrieve() throws DataAccessException;
}
