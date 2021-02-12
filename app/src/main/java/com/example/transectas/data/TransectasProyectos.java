package com.example.transectas.data;

import android.provider.BaseColumns;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "transectasProyectos", primaryKeys = {"proyectoID","transectaID"})
public class TransectasProyectos {

    /** The name of the Operadores table. */
    public static final String TABLE_NAME = "transectasProyectos";

    /** The name of the name column. */
    public static final String COLUMN_PROYECTO = "proyectoID";
    /** The name of the name column. */
    public static final String COLUMN_TRANSECTA = "transectaID";

    @NonNull
    @ColumnInfo(index = true, name = COLUMN_PROYECTO)
    @ForeignKey(entity = Proyectos.class,
            parentColumns = "_id",
            childColumns = "proyectoID",
            onDelete = ForeignKey.NO_ACTION)
    private long proyectoID;


    @NonNull
    @ColumnInfo(index = true, name = COLUMN_TRANSECTA)
    @ForeignKey(entity = Transectas.class,
            parentColumns = "_id",
            childColumns = "transectaID",
            onDelete = ForeignKey.NO_ACTION)
    private long transectaID;

    public TransectasProyectos(long proyectoID, long transectaID) {
        this.proyectoID = proyectoID;
        this.transectaID = transectaID;
    }

    public long getProyectoID() {
        return proyectoID;
    }

    public void setProyectoID(long proyectoID) {
        this.proyectoID = proyectoID;
    }

    public long getTransectaID() {
        return transectaID;
    }

    public void setTransectaID(long transectaID) {
        this.transectaID = transectaID;
    }
}
