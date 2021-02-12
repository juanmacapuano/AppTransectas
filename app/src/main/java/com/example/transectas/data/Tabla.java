package com.example.transectas.data;

import android.provider.BaseColumns;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tablas")
public class Tabla {

    /** The name of the Operadores table. */
    public static final String TABLE_NAME = "tablas";

    /** The name of the ID column. */
    public static final String COLUMN_ID = BaseColumns._ID;

    /** The name of the name column. */
    public static final String COLUMN_NAME = "nombre";

    /** The name of the name column. */
    public static final String COLUMN_BORRADO = "borrado";

    public Tabla(String nombre) {
        this.nombre = nombre;
        this.borrado = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return this.nombre;            // What to display in the Spinner list.
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long id;

    @ColumnInfo(name = COLUMN_NAME)
    private String nombre;

    @ColumnInfo(name = COLUMN_BORRADO)
    private Integer borrado;
}
