package io.github.haedhutner.db;

import io.github.haedhutner.entity.Entity;

import java.util.Optional;

/**
 * A DBManager is responsible for managing the database persistence of a single {@link Entity} class.
 *
 * @param <T>
 * @param <ID>
 */
public interface DBManager<T extends Entity<ID>, ID> {

    /**
     * The DBManager must provide easy access to pre-written SQL queries based on a String id.
     *
     * @param queryId The query Id to look for
     * @return The string representing a non-parameterized query
     */
    String getRawQuery(String queryId);

    /**
     * Initializes this DBManager
     */
    void init();

    /**
     * Select an {@link Entity} from the databse based on the id provided.
     *
     * @param id The Entity id
     * @return An optional containing the entity. Is empty if the entity could not be found based on the id.
     */
    Optional<T> select(ID id);

    /**
     * Insert a new object into the database
     *
     * @param object The object to be inserted
     */
    void insert(T object);

    /**
     * Updates an object in the database.
     *
     * @param object The object to be updated
     */
    void update(T object);

    /**
     * Deletes an object from the database.
     *
     * @param object The object to be deleted.
     */
    void delete(T object);

    /**
     * Filters based on entity fields
     *
     * @param entity
     */
    void filter(T entity);
}
