package com.example.transectas.data;

import android.provider.BaseColumns;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "valores")
public class Valores {

    /** The name of the Operadores table. */
    public static final String TABLE_NAME = "valores";

    /** The name of the ID column. */
    public static final String COLUMN_ID = BaseColumns._ID;

    /** The name of the name column. */
    public static final String COLUMN_NAME = "valor";

    /** The name of the name column. */
    public static final String COLUMN_BORRADO = "borrado";


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long id;

    @ColumnInfo(name = COLUMN_NAME)
    private String valor;

    @ColumnInfo(name = COLUMN_BORRADO)
    private Integer borrado;

    public Integer getBorrado() {
        return borrado;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }

    public Valores(String valor) {
        this.valor = valor;
        this.borrado = 0;
    }

    public Valores() {
        this.valor = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return this.valor;
    }
}
