package com.example.transectas.data;


import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MuestreosTransectasDao {

    /**
     * Inserts a item into the table.
     *
     * @param muestreosTransectas A new item.
     * @return The row ID of the newly inserted item.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(MuestreosTransectas muestreosTransectas);

    @Query("SELECT MuestreosTransectas.muestreoID FROM " + MuestreosTransectas.TABLE_NAME + " WHERE " + MuestreosTransectas.COLUMN_MUESTREO + " = :idMuestreo")
    List<Long> getAllMuestreosList(long idMuestreo);

    /**
     * Select all items in order.
     *
     * @return A {@link List} of all the items in the table.
     */
    @Query("SELECT M.* FROM " + Muestreos.TABLE_NAME  + " M " +
            " INNER JOIN " + MuestreosTransectas.TABLE_NAME + " as MT ON MT.muestreoID" + " = M._id"  + " AND MT.transectaID = :idTransecta")
    LiveData<List<Muestreos>> getAllMuestreos(long idTransecta);

    /**
     */
    @Query("SELECT COUNT(*) FROM " + Muestreos.TABLE_NAME  +
            " INNER JOIN " + MuestreosTransectas.TABLE_NAME + " ON MuestreosTransectas.muestreoID" + " = muestreos._id"  +
            " INNER JOIN " + Transectas.TABLE_NAME + " ON transectas._id" + " = MuestreosTransectas.transectaID " + " AND TRANSECTAS._id = :idTransecta "
            + "WHERE " + "Muestreos.numero = :numeroMuestreo")
    int existMuestreo(int numeroMuestreo, long idTransecta);

}
