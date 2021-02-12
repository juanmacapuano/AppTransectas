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
public interface MuestreosDao {

    /**
     * Inserts a item into the table.
     *
     * @param muestreo A new item.
     * @return The row ID of the newly inserted item.
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Muestreos muestreo);

    /**
     * Update the item. The item is identified by the row ID.
     *
     * @param muestreo The item to update.
     * @return A number of items updated. This should always be {@code 1}.
     */
    @Update
    Integer update(Muestreos muestreo);

    /**
     * Select all items in order.
     *
     * @return A {@link List} of all the items in the table.
     */
    @Query("SELECT * from " + Muestreos.TABLE_NAME + " ORDER BY " + Muestreos.COLUMN_ID + " DESC")
    LiveData<List<Muestreos>> getAllMuestreos();

    /**
     * Select a item by the ID.
     *
     * @param id The row ID.
     * @return A {@link Cursor} of the selected item.
     */
    @Query("SELECT * FROM " + Muestreos.TABLE_NAME + " WHERE " + Muestreos.COLUMN_ID + " = :id")
    Muestreos getMuestreobyId(long id);
}
