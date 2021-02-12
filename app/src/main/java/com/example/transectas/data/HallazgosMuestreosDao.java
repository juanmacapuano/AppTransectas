package com.example.transectas.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface HallazgosMuestreosDao {

    /**
     * Inserts a item into the table.
     *
     * @param hallazgosMuestreos A new item.
     * @return The row ID of the newly inserted item.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(HallazgosMuestreos hallazgosMuestreos);

    @Query("SELECT " + HallazgosMuestreos.COLUMN_HALLAZGO + " FROM " + HallazgosMuestreos.TABLE_NAME + " WHERE " + HallazgosMuestreos.COLUMN_HALLAZGO + " = :idHallazgo")
    List<Long> getAllFindingsList(long idHallazgo);

    /**
     * Select all items in order.
     *
     * @return A {@link List} of all the items in the table.
     */
    @Query("SELECT H.* FROM " + Hallazgos.TABLE_NAME  + " H " +
            " INNER JOIN " + HallazgosMuestreos.TABLE_NAME + " as HM ON HM.hallazgoID" + " = H._id"  + " AND HM.muestreoID = :idMuestreo")
    LiveData<List<Hallazgos>> getAllFindings(long idMuestreo);

    /**
     */
    /*
    @Query("SELECT COUNT(*) FROM " + Hallazgos.TABLE_NAME  +
            " INNER JOIN " + HallazgosMuestreos.TABLE_NAME + " ON HallazgosMuestreos.muestreoID" + " = muestreos._id"  +
            " INNER JOIN " + Transectas.TABLE_NAME + " ON transectas._id" + " = MuestreosTransectas.transectaID " + " AND TRANSECTAS._id = :idTransecta "
            + "WHERE " + "Hallazgos. = :numeroHallazgo")
    int existMuestreo(int numeroHallazgo, long idTransecta);*/
}
