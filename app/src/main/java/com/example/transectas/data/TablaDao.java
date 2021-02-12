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
public interface TablaDao {

    /**
     * Counts the number of items in the table.
     *
     * @return The number of items.
     */
    @Query("SELECT COUNT(*) FROM " + Tabla.TABLE_NAME)
    int count();

    /**
     * Inserts a item into the table.
     *
     * @param tabla A new item.
     * @return The row ID of the newly inserted item.
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Tabla tabla);

    /**
     * Inserts multiple items into the database
     *
     * @param tabla An array of new items.
     * @return The row IDs of the newly inserted item.
     */
    @Insert
    long[] insertAll(Tabla[] tabla);

    /**
     * Select a item by the ID.
     *
     * @param id The row ID.
     * @return A {@link Cursor} of the selected item.
     */
    @Query("SELECT * FROM " + Tabla.TABLE_NAME + " WHERE " + Tabla.COLUMN_ID + " = :id")
    Cursor selectById(long id);

    /**
     * Select a item by name.
     *
     * @param name The row name.
     * @return A {@link Cursor} of the selected item.
     */
    @Query("SELECT * FROM " + Tabla.TABLE_NAME + " WHERE " + Tabla.COLUMN_NAME + " = :name")
    Tabla[] selectByName(String name);

    /**
     * Update the item. The item is identified by the row ID.
     *
     * @param tabla The item to update.
     * @return A number of items updated. This should always be {@code 1}.
     */
    @Update
    int update(Tabla tabla);

    /**
     * Select a item by name.
     *
     * @param name The row name.
     * @return A {@link Cursor} of the selected item.
     */
    @Query("SELECT * FROM " + Tabla.TABLE_NAME )
    Tabla[] selectAllItems();
}
