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
public interface TransectasProyectosDao {

    /**
     * Counts the number of items in the table.
     *
     * @return The number of items.
     */
    @Query("SELECT COUNT(*) FROM " + TransectasProyectos.TABLE_NAME)
    int count();

    /**
     * Inserts a item into the table.
     *
     * @param transectasProyectos A new item.
     * @return The row ID of the newly inserted item.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(TransectasProyectos transectasProyectos);

    @Query("SELECT TransectasProyectos.transectaID FROM " + TransectasProyectos.TABLE_NAME + " WHERE " + TransectasProyectos.COLUMN_PROYECTO + " = :idProyecto")
    List<Long> getAllTransectas(long idProyecto);

    /**
     * Select all items in order.
     *
     * @return A {@link List} of all the items in the table.
     */
    @Query("SELECT T.* FROM " + Transectas.TABLE_NAME  + " T " +
            " INNER JOIN " + TransectasProyectos.TABLE_NAME + " as TP ON TP.transectaID" + " = T._id"  + " AND TP.proyectoID = :idProyecto")
    LiveData<List<Transectas>> getAllTransectasLive(long idProyecto);

    /**
     * Select all items in order.
     *
     * @return A {@link List} of all the items in the table.
     */
    @Query("SELECT COUNT(*) FROM " + Transectas.TABLE_NAME  +
            " INNER JOIN " + TransectasProyectos.TABLE_NAME + " ON TransectasProyectos.transectaID" + " = Transectas._id"  + " AND TransectasProyectos.proyectoID = :idProyecto "
            + "WHERE " + "Transectas.numero = :numeroTransecta")
    int existTransecta(int numeroTransecta, long idProyecto);


}
