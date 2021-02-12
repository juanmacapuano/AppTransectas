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
public interface TransectasDao {

    /**
     * Counts the number of items in the table.
     *
     * @return The number of items.
     */
    @Query("SELECT COUNT(*) FROM " + Transectas.TABLE_NAME)
    int count();

    /**
     * Inserts a item into the table.
     *
     * @param transecta A new item.
     * @return The row ID of the newly inserted item.
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Transectas transecta);

    /**
     * Select all items in order.
     *
     * @return A {@link List} of all the items in the table.
     */
    @Query("SELECT * from " + Transectas.TABLE_NAME + " ORDER BY " + Transectas.COLUMN_ID + " DESC")
    LiveData<List<Transectas>> getAllTransectas();

    /**
     * Select a item by the ID.
     *
     * @param id The row ID.
     * @return A {@link Cursor} of the selected item.
     */
    @Query("SELECT * FROM " + Transectas.TABLE_NAME + " WHERE " + Transectas.COLUMN_ID + " = :id")
    Transectas getTransectabyId(long id);

    /**
     * Update the item. The item is identified by the row ID.
     *
     * @param transecta The item to update.
     * @return A number of items updated. This should always be {@code 1}.
     */
    @Update
    Integer update(Transectas transecta);

    /**
     * Select all items in order.
     *
     * @return A {@link List} of all the items in the table.
     */
    @Query("SELECT * from " + Transectas.TABLE_NAME + " ORDER BY " + Transectas.COLUMN_ID + " DESC")
    List<Transectas> selectAllTransectas();

}
