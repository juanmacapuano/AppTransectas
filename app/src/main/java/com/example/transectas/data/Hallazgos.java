package com.example.transectas.data;

import android.content.Intent;
import android.provider.BaseColumns;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "hallazgos")
public class Hallazgos {
    /** The name of the Operadores table. */
    public static final String TABLE_NAME = "hallazgos";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_OCURRENCIA = "ocurrencia";
    public static final String COLUMN_TIPOOCUR1 = "tipo_ocur_1";
    public static final String COLUMN_TIPOOCUR2 = "tipo_ocur_2";
    public static final String COLUMN_CONC_CARC = "conc_carc";
    public static final String COLUMN_AMBIENTE_INMED = "ambiente_inmediato";
    public static final String COLUMN_POSICION = "posicion";
    public static final String COLUMN_ORIENTACION_AGUA = "orientacion_agua";
    public static final String COLUMN_TAXON = "taxon";
    public static final String COLUMN_TAXON_TAMANIO = "taxon_tamanio";
    public static final String COLUMN_FOTOS = "fotos";
    public static final String COLUMN_ANALISTA = "analista";
    public static final String COLUMN_OBSERVACIONES = "observaciones";
    public static final String COLUMN_MUESTREO_ID = "idMuestro";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long id;

    @ColumnInfo(name = COLUMN_OCURRENCIA)
    private Integer ocurrencia;

    @ColumnInfo(name = COLUMN_TIPOOCUR1)
    private List<Long> tipo_ocur1;

    @ColumnInfo(name = COLUMN_TIPOOCUR2)
    private List<Long> tipo_ocur2;

    @ColumnInfo(name = COLUMN_CONC_CARC)
    private String conc_carc;

    @ColumnInfo(name = COLUMN_AMBIENTE_INMED)
    private String ambiente_inmediato;

    @ColumnInfo(name = COLUMN_POSICION)
    private List<Long> posicion;

    @ColumnInfo(name = COLUMN_ORIENTACION_AGUA)
    private List<Long> orientacion_agua;

    @ColumnInfo(name = COLUMN_TAXON)
    private List<Long> taxon;

    @ColumnInfo(name = COLUMN_TAXON_TAMANIO)
    private List<Long> taxon_tamanio;

    @ColumnInfo(name = COLUMN_FOTOS)
    private List<String> fotos;

    @ColumnInfo(name = COLUMN_ANALISTA)
    private String analista;

    @ColumnInfo(name = COLUMN_OBSERVACIONES)
    private String observaciones;

    @ColumnInfo(name = COLUMN_MUESTREO_ID)
    private Long muestreo_id;

    @Ignore
    public Hallazgos(){
    }

    public Hallazgos(long id, Integer ocurrencia, List<Long> tipo_ocur1, List<Long> tipo_ocur2, String conc_carc, String ambiente_inmediato, List<Long> posicion, List<Long> orientacion_agua, List<Long> taxon, List<Long> taxon_tamanio, List<String> fotos, String analista, String observaciones, Long muestreo_id) {
        this.id = id;
        this.ocurrencia = ocurrencia;
        this.tipo_ocur1 = tipo_ocur1;
        this.tipo_ocur2 = tipo_ocur2;
        this.conc_carc = conc_carc;
        this.ambiente_inmediato = ambiente_inmediato;
        this.posicion = posicion;
        this.orientacion_agua = orientacion_agua;
        this.taxon = taxon;
        this.taxon_tamanio = taxon_tamanio;
        this.fotos = fotos;
        this.analista = analista;
        this.observaciones = observaciones;
        this.muestreo_id = muestreo_id;
    }
    @Ignore
    public Hallazgos(Integer ocurrencia, List<Long> tipo_ocur1, List<Long> tipo_ocur2, String conc_carc, String ambiente_inmediato, List<Long> posicion, List<Long> orientacion_agua, List<Long> taxon, List<Long> taxon_tamanio, List<String> fotos, String analista, String observaciones, Long muestreo_id) {
        this.ocurrencia = ocurrencia;
        this.tipo_ocur1 = tipo_ocur1;
        this.tipo_ocur2 = tipo_ocur2;
        this.conc_carc = conc_carc;
        this.ambiente_inmediato = ambiente_inmediato;
        this.posicion = posicion;
        this.orientacion_agua = orientacion_agua;
        this.taxon = taxon;
        this.taxon_tamanio = taxon_tamanio;
        this.fotos = fotos;
        this.analista = analista;
        this.observaciones = observaciones;
        this.muestreo_id = muestreo_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getOcurrencia() {
        return ocurrencia;
    }

    public void setOcurrencia(Integer ocurrencia) {
        this.ocurrencia = ocurrencia;
    }

    public List<Long> getTipo_ocur1() {
        return tipo_ocur1;
    }

    public void setTipo_ocur1(List<Long> tipo_ocur1) {
        this.tipo_ocur1 = tipo_ocur1;
    }

    public List<Long> getTipo_ocur2() {
        return tipo_ocur2;
    }

    public void setTipo_ocur2(List<Long> tipo_ocur2) {
        this.tipo_ocur2 = tipo_ocur2;
    }

    public String getConc_carc() {
        return conc_carc;
    }

    public void setConc_carc(String conc_carc) {
        this.conc_carc = conc_carc;
    }

    public String getAmbiente_inmediato() {
        return ambiente_inmediato;
    }

    public void setAmbiente_inmediato(String ambiente_inmediato) {
        this.ambiente_inmediato = ambiente_inmediato;
    }

    public List<Long> getPosicion() {
        return posicion;
    }

    public void setPosicion(List<Long> posicion) {
        this.posicion = posicion;
    }

    public List<Long> getOrientacion_agua() {
        return orientacion_agua;
    }

    public void setOrientacion_agua(List<Long> orientacion_agua) {
        this.orientacion_agua = orientacion_agua;
    }

    public List<Long> getTaxon() {
        return taxon;
    }

    public void setTaxon(List<Long> taxon) {
        this.taxon = taxon;
    }

    public List<Long> getTaxon_tamanio() {
        return taxon_tamanio;
    }

    public void setTaxon_tamanio(List<Long> taxon_tamanio) {
        this.taxon_tamanio = taxon_tamanio;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public String getAnalista() {
        return analista;
    }

    public void setAnalista(String analista) {
        this.analista = analista;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getMuestreo_id() {
        return muestreo_id;
    }

    public void setMuestreo_id(Long muestreo_id) {
        this.muestreo_id = muestreo_id;
    }
}
