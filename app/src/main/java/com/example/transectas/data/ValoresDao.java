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
public interface ValoresDao {

    /**
     * Counts the number of items in the table.
     *
     * @return The number of items.
     */
    @Query("SELECT V.* FROM " + Valores.TABLE_NAME  + " V " +
            " INNER JOIN " + TablaValor.TABLE_NAME + " as TV ON TV.valorID" + " = V._id"  +
            " INNER JOIN " + Tabla.TABLE_NAME + " AS T ON T._id = TV.tablaID AND T.nombre = :tabla"
    )
    List<Valores> selectAllItemsSpinner(String tabla);
    /**
     * Counts the number of items in the table.
     *
     * @return The number of items.
     */
    @Query("SELECT V.* FROM " + Valores.TABLE_NAME  + " V " +
            " INNER JOIN " + TablaValor.TABLE_NAME + " as TV ON TV.valorID" + " = V._id"  +
            " INNER JOIN " + Tabla.TABLE_NAME + " AS T ON T._id = TV.tablaID AND T.nombre = 'operadores'"
    )
    List<Valores> selectAllOperadoresList();

    /**
     * Counts the number of items in the table.
     *
     * @return The number of items.
     */
    @Query("SELECT V.* FROM " + Valores.TABLE_NAME  + " V " +
            " INNER JOIN " + TablaValor.TABLE_NAME + " as TV ON TV.valorID" + " = V._id"  +
            " INNER JOIN " + Tabla.TABLE_NAME + " AS T ON T._id = TV.tablaID AND T.nombre = 'pendiente'"
    )
    List<Valores> selectAllPendienteList();

    /**
     * Counts the number of items in the table.
     *
     * @return The number of items.
     */
    @Query("SELECT COUNT(*) FROM " + Valores.TABLE_NAME)
    int count();

    /**
     * Inserts a item into the table.
     *
     * @param valores A new item.
     * @return The row ID of the newly inserted item.
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Valores valores);

    /**
     * Inserts multiple items into the database
     *
     * @param valores An array of new items.
     * @return The row IDs of the newly inserted item.
     */
    @Insert
    long[] insertAll(Valores[] valores);

     /**
     * Select a item by the ID.
     *
     * @param id The row ID.
     * @return A {@link Cursor} of the selected item.
     */
    @Query("SELECT * FROM " + Valores.TABLE_NAME + " WHERE " + Valores.COLUMN_ID + " = :id")
    Cursor selectById(long id);

    /**
     * Update the item. The item is identified by the row ID.
     *
     * @param valores The item to update.
     * @return A number of items updated. This should always be {@code 1}.
     */
    @Update
    int update(Valores valores);

    @Query("SELECT * from " + Valores.TABLE_NAME + " LIMIT 1")
    Valores[] getAnyItem();
}
