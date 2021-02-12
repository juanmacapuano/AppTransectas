package com.example.transectas.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "hallazgosMuestreos", primaryKeys = {"muestreoID", "hallazgoID"})
public class HallazgosMuestreos {

    /** The name of the Operadores table. */
    public static final String TABLE_NAME = "hallazgosMuestreos";

    /** The name of the name column. */
    public static final String COLUMN_HALLAZGO = "hallazgoID";
    /** The name of the name column. */
    public static final String COLUMN_MUESTREO = "muestreoID";

    @NonNull
    @ColumnInfo(index = true, name = COLUMN_HALLAZGO)
    @ForeignKey(entity = Hallazgos.class,
            parentColumns = "_id",
            childColumns = "hallazgoID",
            onDelete = ForeignKey.NO_ACTION)
    private long hallazgoID;


    @NonNull
    @ColumnInfo(index = true, name = COLUMN_MUESTREO)
    @ForeignKey(entity = Muestreos.class,
            parentColumns = "_id",
            childColumns = "muestreoID",
            onDelete = ForeignKey.NO_ACTION)
    private long muestreoID;

    public HallazgosMuestreos(long hallazgoID, long muestreoID) {
        this.hallazgoID = hallazgoID;
        this.muestreoID = muestreoID;
    }


    public Long getHallazgoID() {
        return hallazgoID;
    }

    public void setHallazgoID(Long hallazgoID) {
        this.hallazgoID = hallazgoID;
    }

    public Long getMuestreoID() {
        return muestreoID;
    }

    public void setMuestreoID(Long muestreoID) {
        this.muestreoID = muestreoID;
    }
}
