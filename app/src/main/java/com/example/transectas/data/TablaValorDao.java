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
public interface TablaValorDao {

    /**
     * Counts the number of items in the table.
     *
     * @return The number of items.
     */
    @Query("SELECT COUNT(*) FROM " + TablaValor.TABLE_NAME)
    int count();

    /**
     * Inserts a item into the table.
     *
     * @param tablaValor A new item.
     * @return The row ID of the newly inserted item.
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(TablaValor tablaValor);

    /**
     * Inserts multiple items into the database
     *
     * @param tabla An array of new items.
     * @return The row IDs of the newly inserted item.
     */
    @Insert
    long[] insertAll(TablaValor[] tablaValor);

    /**
     * Update the item. The item is identified by the row ID.
     *
     * @param tablaValor The item to update.
     * @return A number of items updated. This should always be {@code 1}.
     */
    @Update
    int update(TablaValor tablaValor);
}
