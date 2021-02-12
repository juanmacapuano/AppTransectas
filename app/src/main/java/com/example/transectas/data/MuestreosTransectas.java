package com.example.transectas.data;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "muestreosTransectas", primaryKeys = {"transectaID","muestreoID"})
public class MuestreosTransectas {

    /** The name of the Operadores table. */
    public static final String TABLE_NAME = "muestreosTransectas";

    /** The name of the name column. */
    public static final String COLUMN_TRANSECTA = "transectaID";
    /** The name of the name column. */
    public static final String COLUMN_MUESTREO = "muestreoID";

    @NonNull
    @ColumnInfo(index = true, name = COLUMN_TRANSECTA)
    @ForeignKey(entity = Transectas.class,
            parentColumns = "_id",
            childColumns = "transectaID",
            onDelete = ForeignKey.NO_ACTION)
    private long transectaID;


    @NonNull
    @ColumnInfo(index = true, name = COLUMN_MUESTREO)
    @ForeignKey(entity = Muestreos.class,
            parentColumns = "_id",
            childColumns = "muestreoID",
            onDelete = ForeignKey.NO_ACTION)
    private long muestreoID;

    public MuestreosTransectas(long transectaID, long muestreoID) {
        this.transectaID = transectaID;
        this.muestreoID = muestreoID;
    }

    public long getTransectaID() {
        return transectaID;
    }

    public void setTransectaID(long transectaID) {
        this.transectaID = transectaID;
    }

    public long getMuestreoID() {
        return muestreoID;
    }

    public void setMuestreoID(long muestreoID) {
        this.muestreoID = muestreoID;
    }
}
