package com.example.transectas.data;
import android.provider.BaseColumns;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "materialArqueologico", foreignKeys = @ForeignKey(entity = Valores.class,
        parentColumns = "_id",
        childColumns = "fk_valores",
        onDelete = ForeignKey.NO_ACTION))
public class MaterialArqueologico {

    /** The name of the Operadores table. */
    public static final String TABLE_NAME = "materialArqueologico";

    /** The name of the ID column. */
    public static final String COLUMN_ID = BaseColumns._ID;

    /** The name of the distribucion column. */
    public static final String COLUMN_DESCRIPCION = "descripcion";

    /** The name of the visibilidad column. */
    public static final String COLUMN_VALORES = "fk_valores";

    /** The name of the borrado column. */
    public static final String COLUMN_BORRADO = "borrado";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long id;

    @ColumnInfo(name = COLUMN_DESCRIPCION)
    private String descripcion;

    @ColumnInfo(name = COLUMN_VALORES)
    private List<Long> fk_valores;

    @ColumnInfo(name = COLUMN_BORRADO)
    private Integer borrado;

    public MaterialArqueologico(String descripcion, List<Long> fk_valores, Integer borrado) {
        this.descripcion = descripcion;
        this.fk_valores = fk_valores;
        this.borrado = borrado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Long> getFk_valores() {
        return fk_valores;
    }

    public void setFk_valores(List<Long> fk_valores) {
        this.fk_valores = fk_valores;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }
}
