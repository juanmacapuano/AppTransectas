package com.example.transectas.data;

import android.database.Cursor;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ProyectosDao {

    /**
     * Counts the number of items in the table.
     *
     * @return The number of items.
     */
    @Query("SELECT COUNT(*) FROM " + Proyectos.TABLE_NAME)
    int count();

    /**
     * Inserts a item into the table.
     *
     * @param proyecto A new item.
     * @return The row ID of the newly inserted item.
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Proyectos proyecto);

    /**
     * Inserts multiple items into the database
     *
     * @param proyecto An array of new items.
     * @return The row IDs of the newly inserted item.
     */
    @Insert
    List<Long> insertAll(Proyectos[] proyecto);

    /**
     * Select all items.
     *
     * @return A {@link Cursor} of all the items in the table.
     */
    @Query("SELECT * FROM " + Proyectos.TABLE_NAME)
    Cursor selectAll();

    /**
     * Select a item by the ID.
     *
     * @param id The row ID.
     * @return A {@link Cursor} of the selected item.
     */
    @Query("SELECT * FROM " + Proyectos.TABLE_NAME + " WHERE " + Proyectos.COLUMN_ID + " = :id")
    Cursor selectById(long id);

    /**
     * Select all items in order.
     *
     * @return A {@link List} of all the items in the table.
     */
    @Query("SELECT * from " + Proyectos.TABLE_NAME + " ORDER BY " + Proyectos.COLUMN_NAME + " ASC")
    List<Proyectos> getAlphabetizedItems();

    /**
     * Select all items in order.
     *
     * @return A {@link List} of all the items in the table.
     */
    @Query("SELECT * from " + Proyectos.TABLE_NAME + " ORDER BY " + Proyectos.COLUMN_ID + " DESC")
    LiveData<List<Proyectos>> getAllProyectos();

    /**
     * Delete a item by the ID.
     *
     * @param id The row ID.
     * @return A number of item deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + Proyectos.TABLE_NAME + " WHERE " + Proyectos.COLUMN_ID + " = :id")
    int deleteById(long id);

    /**
     * Delete all items.
     */
    @Query("DELETE FROM " + Proyectos.TABLE_NAME)
    void deleteAll();

    /**
     * Update the item. The item is identified by the row ID.
     *
     * @param proyecto The item to update.
     * @return A number of items updated. This should always be {@code 1}.
     */
    @Update
    Integer update(Proyectos proyecto);
}
