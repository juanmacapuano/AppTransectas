package com.example.transectas.data;

import android.provider.BaseColumns;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "transectas")
public class Transectas {

    /** The name of the Operadores table. */
    public static final String TABLE_NAME = "transectas";

    /** The name of the ID column. */
    public static final String COLUMN_ID = BaseColumns._ID;

    /** The name of the name column. */
    public static final String COLUMN_NUMERO = "numero";

    /** The name of the date column. */
    public static final String COLUMN_FECHA = "fechaCreacion";

    /** The name of the description column. */
    public static final String COLUMN_DESCRIPCION = "descripcion";

    /** The name of the gpsOrigen column. */
    public static final String COLUMN_GPS_ORIGEN = "gpsOrigen";

    /** The name of the gpsOrigenS column. */
    public static final String COLUMN_GPS_ORIGENS = "gpsOrigenS";

    /** The name of the gpsDestino column. */
    public static final String COLUMN_GPS_DESTINO = "gpsDestino";

    /** The name of the gpsDestinoS column. */
    public static final String COLUMN_GPS_DESTINOS = "gpsDestinoS";

    /** The name of the rumbo column. */
    public static final String COLUMN_RUMBO = "rumbo";

    /** The name of the longitud column. */
    public static final String COLUMN_LONGITUD = "longitud";

    /** The name of the Ancho column. */
    public static final String COLUMN_ANCHO = "ancho";

    /** The name of the borrado column. */
    public static final String COLUMN_BORRADO = "borrado";

    /** The name of the borrado column. */
    public static final int SET_BORRADO = 0;

    /** The name of the operador column. */
    public static final String COLUMN_OPERADOR = "fk_operador";

    /** The name of the contextoAmb column. */
    public static final String COLUMN_CONTEXTOAMB = "fk_contextoAmb";

    /** The name of the muestro column. */
    public static final String COLUMN_MUESTREO = "fk_muestreo";

    /** The name of the observaciones column. */
    public static final String COLUMN_OBSERVACIONES = "observaciones";

    /** The name of the fotos column. */
    public static final String COLUMN_FOTOS = "fotos";


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long id;

    @ColumnInfo(name = COLUMN_NUMERO)
    private Integer numero;

    @ColumnInfo(name = COLUMN_FECHA)
    private String fechaCreacion;

    @ColumnInfo(name = COLUMN_DESCRIPCION)
    private String descripcion;

    @ColumnInfo(name = COLUMN_GPS_ORIGEN)
    private String gpsOrigen;

    @ColumnInfo(name = COLUMN_GPS_ORIGENS)
    private String gpsOrigenS;

    @ColumnInfo(name = COLUMN_GPS_DESTINO)
    private String gpsDestino;

    @ColumnInfo(name = COLUMN_GPS_DESTINOS)
    private String gpsDestinoS;

    @ColumnInfo(name = COLUMN_RUMBO)
    private String rumbo;

    @ColumnInfo(name = COLUMN_LONGITUD)
    private String longitud;

    @ColumnInfo(name = COLUMN_ANCHO)
    private String ancho;

    @ColumnInfo(name = COLUMN_BORRADO)
    private int borrado;

    @ColumnInfo(name = COLUMN_OBSERVACIONES)
    private String observaciones;

    @ColumnInfo(name = COLUMN_FOTOS)
    private List<String> fotos;

    @ColumnInfo(name = COLUMN_OPERADOR)
    @ForeignKey(entity = Valores.class,
            parentColumns = "_id",
            childColumns = "fk_operador",
            onDelete = ForeignKey.NO_ACTION)
    private List<Long> fk_operador;

    @ColumnInfo(name = COLUMN_CONTEXTOAMB)
    @ForeignKey(entity = Valores.class,
            parentColumns = "_id",
            childColumns = "fk_contextoAmb",
            onDelete = ForeignKey.NO_ACTION)
    private List<Long> fk_contextoAmb;

    @ColumnInfo(name = COLUMN_MUESTREO)
    @ForeignKey(entity = Muestreos.class,
            parentColumns = "_id",
            childColumns = "fk_muestreo",
            onDelete = ForeignKey.NO_ACTION)
    private List<Long> fk_muestreo;

    public Transectas() {
    }

    @Ignore
    public Transectas(Long id, Integer numero, String fechaCreacion, String descripcion, String gpsOrigen, String gpsOrigenS, String gpsDestino, String gpsDestinoS, String rumbo, String longitud, String ancho, List<Long> fk_operador, List<Long> fk_contextoAmb, String observaciones, List<String> fotos) {
        this.id = id;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
        this.gpsOrigen = gpsOrigen;
        this.gpsOrigenS = gpsOrigenS;
        this.gpsDestino = gpsDestino;
        this.gpsDestinoS = gpsDestinoS;
        this.rumbo = rumbo;
        this.longitud = longitud;
        this.ancho = ancho;
        this.borrado = SET_BORRADO;
        this.fk_operador = fk_operador;
        this.fk_contextoAmb = fk_contextoAmb;
        this.fk_muestreo = null;
        this.observaciones = observaciones;
        this.fotos = fotos;
    }

    public Transectas(Integer numero, String fechaCreacion, String descripcion, String gpsOrigen, String gpsOrigenS, String gpsDestino, String gpsDestinoS, String rumbo, String longitud, String ancho, String observaciones, List<String> fotos) {
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
        this.gpsOrigen = gpsOrigen;
        this.gpsOrigenS = gpsOrigenS;
        this.gpsDestino = gpsDestino;
        this.gpsDestinoS = gpsDestinoS;
        this.rumbo = rumbo;
        this.longitud = longitud;
        this.ancho = ancho;
        this.borrado = SET_BORRADO;
        this.observaciones = observaciones;
        this.fotos = fotos;
       // this.fk_operador = null;
       // this.fk_muestreo = null;
    }

    @Ignore
    public Transectas(Integer numero, String fechaCreacion, String descripcion, String gpsOrigen, String gpsOrigenS, String gpsDestino, String gpsDestinoS, String rumbo, String longitud, String ancho, List<Long> fk_operador, List<Long> fk_contextoAmb, String observaciones, List<String> fotos) {
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
        this.gpsOrigen = gpsOrigen;
        this.gpsOrigenS = gpsOrigenS;
        this.gpsDestino = gpsDestino;
        this.gpsDestinoS = gpsDestinoS;
        this.rumbo = rumbo;
        this.longitud = longitud;
        this.ancho = ancho;
        this.borrado = SET_BORRADO;
        this.fk_operador = fk_operador;
        this.fk_muestreo = null;
        this.fk_contextoAmb = fk_contextoAmb;
        this.observaciones = observaciones;
        this.fotos = fotos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGpsOrigen() {
        return gpsOrigen;
    }

    public String getGpsOrigenS() {
        return gpsOrigenS;
    }

    public void setGpsOrigen(String gpsOrigen) {
        this.gpsOrigen = gpsOrigen;
    }

    public void setGpsOrigenS(String gpsOrigenS) {
        this.gpsOrigenS = gpsOrigenS;
    }

    public String getGpsDestino() {
        return gpsDestino;
    }

    public String getGpsDestinoS() {
        return gpsDestinoS;
    }

    public void setGpsDestino(String gpsDestino) {
        this.gpsDestino = gpsDestino;
    }

    public void setGpsDestinoS(String gpsDestinoS) {
        this.gpsDestinoS = gpsDestinoS;
    }

    public String getRumbo() {
        return rumbo;
    }

    public void setRumbo(String rumbo) {
        this.rumbo = rumbo;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public List<Long> getFk_operador() {
        return fk_operador;
    }

    public void setFk_operador(List<Long> fk_operador) {
        this.fk_operador = fk_operador;
    }

    public List<Long> getFk_contextoAmb() {
        return fk_contextoAmb;
    }

    public void setFk_contextoAmb(List<Long> fk_contextoAmb) {
        this.fk_contextoAmb = fk_contextoAmb;
    }

    public List<Long> getFk_muestreo() {
        return fk_muestreo;
    }

    public void setFk_muestreo(List<Long> fk_muestreo) {
        this.fk_muestreo = fk_muestreo;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
}
