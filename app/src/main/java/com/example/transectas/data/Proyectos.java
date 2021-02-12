package com.example.transectas.data;

import android.provider.BaseColumns;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "proyectos", foreignKeys = @ForeignKey(entity = Transectas.class,
        parentColumns = "_id",
        childColumns = "fk_transectas"
))
public class Proyectos {

    /** The name of the Operadores table. */
    public static final String TABLE_NAME = "proyectos";

    /** The name of the ID column. */
    public static final String COLUMN_ID = BaseColumns._ID;

    /** The name of the name column. */
    public static final String COLUMN_NAME = "nombre";

    /** The name of the name column. */
    public static final String COLUMN_UBICACION = "ubicacion";

    /** The name of the name column. */
    public static final String COLUMN_FECHA = "fechaCreacion";

    /** The name of the borrado column. */
    public static final String COLUMN_BORRADO = "borrado";

    /** The name of the borrado column. */
    public static final int SET_BORRADO = 0;

    /** The name of the transectas column. */
    public static final String COLUMN_TRANSECTAS = "fk_transectas";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long id;

    @ColumnInfo(name = COLUMN_NAME)
    private String nombre;

    @ColumnInfo(name = COLUMN_UBICACION)
    private String ubicacion;

    @ColumnInfo(name = COLUMN_FECHA)
    private String fechaCreacion;

    @ColumnInfo(name = COLUMN_BORRADO)
    private int borrado;

    @ColumnInfo(name = COLUMN_TRANSECTAS)
    private List<Long> fk_transectas;

    public Proyectos() {
    }

    public Proyectos(String nombre, String ubicacion, String fechaCreacion, List<Long> transectas) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fechaCreacion = fechaCreacion;
        this.borrado = SET_BORRADO;
        this.fk_transectas = transectas;
    }
    @Ignore
    public Proyectos(Long id, String nombre, String ubicacion, String fechaCreacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fechaCreacion = fechaCreacion;
        this.borrado = SET_BORRADO;
        this.fk_transectas = null;
        this.id = id;
    }

    // Proyectos sin Transectas
    @Ignore
    public Proyectos(String nombre, String ubicacion, String fechaCreacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.fechaCreacion = fechaCreacion;
        this.borrado = SET_BORRADO;
        this.fk_transectas = null;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public List<Long> getFk_transectas() { return fk_transectas; }

    public void setFk_transectas(List<Long> fk_transectas) { this.fk_transectas = fk_transectas; }

    @Override
    public String toString() {
        return this.nombre;            // What to display in the Spinner list.
    }
}
