package com.example.transectas.data;

import android.provider.BaseColumns;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "tablaValor", primaryKeys = {"tablaID","valorID"})
public class TablaValor {

    /** The name of the Operadores table. */
    public static final String TABLE_NAME = "tablaValor";

    /** The name of the name column. */
    public static final String COLUMN_TABLA = "tablaID";
    /** The name of the name column. */
    public static final String COLUMN_VALOR = "valorID";


    public TablaValor(long tablaId, long valorId) {
        this.tablaId = tablaId;
        this.valorId = valorId;
    }

    public long getTablaId() {
        return tablaId;
    }

    public void setTablaId(long tablaId) {
        this.tablaId = tablaId;
    }

    public long getValorId() {
        return valorId;
    }

    public void setValorId(long valorId) {
        this.valorId = valorId;
    }


    @NonNull
    @ColumnInfo(index = true, name = COLUMN_TABLA)
    @ForeignKey(entity = Tabla.class,
            parentColumns = "_id",
            childColumns = "tablaId",
            onDelete = ForeignKey.NO_ACTION)
    private long tablaId;


    @NonNull
    @ColumnInfo(index = true, name = COLUMN_VALOR)
    @ForeignKey(entity = Valores.class,
            parentColumns = "_id",
            childColumns = "valorId",
            onDelete = ForeignKey.NO_ACTION)
    private long valorId;
}
