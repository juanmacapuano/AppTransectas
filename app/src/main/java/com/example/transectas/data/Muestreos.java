package com.example.transectas.data;

import android.provider.BaseColumns;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "muestreos")
public class Muestreos {

    /** The name of the Operadores table. */
    public static final String TABLE_NAME = "muestreos";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NUMERO = "numero";
    public static final String COLUMN_TRANSECTA = "nroTransecta";
    public static final String COLUMN_FECHA_CREACION = "fecha_creacion";
    public static final String COLUMN_AS_DESCRIPCION = "as_descripcion";
    public static final String COLUMN_AS_PENDIENTE = "as_pendiente";
    public static final String COLUMN_AS_SEDIMENTO_TIPO = "as_sedimento_tipo";
    public static final String COLUMN_AS_PD_EROSION = "as_pd_erosion";
    public static final String COLUMN_AS_PD_DEPOSITACION = "as_pd_depositacion";
    public static final String COLUMN_AS_PD_PEDOGENESIS = "as_pd_pedogenesis";
    public static final String COLUMN_AS_PE = "as_potencial_enterramiento";
    public static final String COLUMN_AS_PE_OBSERVACIONES = "as_pe_observaciones";
    public static final String COLUMN_AS_PA_AUSENCIA = "as_pa_ausencia";
    public static final String COLUMN_AS_PA_HUMEDAD = "as_pa_humedad";
    public static final String COLUMN_AS_PA_ENCHARCAMIENTO = "as_pa_encharcamiento";
    public static final String COLUMN_AS_PA_ESCORRENTIA = "as_pa_escorrentia";
    public static final String COLUMN_AS_PA_CANAL = "as_pa_canal";
    public static final String COLUMN_AS_PA_OBSERVACIONES = "as_pa_observaciones";
    public static final String COLUMN_VE_TIPO = "ve_tipo";
    public static final String COLUMN_VE_DISTRIBUCION = "ve_distribucion";
    public static final String COLUMN_VE_VISIBILIDAD = "ve_visibilidad";
    public static final String COLUMN_BIO_VEGETALES = "bio_vegetales";
    public static final String COLUMN_BIO_ROEDORES = "bio_roedores";
    public static final String COLUMN_BIO_PISOTEO = "bio_pisoteo";
    public static final String COLUMN_BIO_CARNIVOROS = "bio_carnivoros";
    public static final String COLUMN_BIO_OTROS = "bio_otros";
    public static final String COLUMN_BIO_DESCRIPCION = "bio_descripcion";
    public static final String COLUMN_PRESENCIA_FAUNA = "presencia_fauna";
    public static final String COLUMN_AA_GANADERIA = "aa_ganaderia";
    public static final String COLUMN_AA_AGRICULTURA = "aa_agricultura";
    public static final String COLUMN_AA_ALAMBRADO = "aa_alambrado";
    public static final String COLUMN_AA_CONSTRUCCION = "aa_construccion";
    public static final String COLUMN_AA_CAMINO = "aa_camino";
    public static final String COLUMN_AA_TERRAPLENES = "aa_terraplenes";
    public static final String COLUMN_AA_CANAL = "aa_canal";
    public static final String COLUMN_AA_DESECHOS = "aa_desechos";
    public static final String COLUMN_AA_OTROS = "aa_otros";
    public static final String COLUMN_AA_DESCRIPCION = "aa_descripcion";
    public static final String COLUMN_PM_LITICO = "pm_litico";
    public static final String COLUMN_PM_FAUNISTICO = "pm_faunistico";
    public static final String COLUMN_PM_CERAMICA = "pm_ceramica";
    public static final String COLUMN_PM_GRESS = "pm_gress";
    public static final String COLUMN_PM_VIDRIO = "pm_vidrio";
    public static final String COLUMN_PM_OTROS = "pm_otros";
    public static final String COLUMN_PM_OBSERVACIONES = "pm_observacion";


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long id;

    @ColumnInfo(name = COLUMN_NUMERO)
    private Integer numero;

    public long getNro_transecta() {
        return nro_transecta;
    }

    public void setNro_transecta(long nro_transecta) {
        this.nro_transecta = nro_transecta;
    }

    @ColumnInfo(name = COLUMN_TRANSECTA)
    private long nro_transecta;

    @ColumnInfo(name = COLUMN_FECHA_CREACION)
    private String fecha_creacion;

    @ColumnInfo(name = COLUMN_AS_DESCRIPCION)
    private String as_descripcion;

    @ColumnInfo(name = COLUMN_AS_PENDIENTE)
    private List<Long> as_pendiente;

    @ColumnInfo(name = COLUMN_AS_SEDIMENTO_TIPO)
    private String as_sedimento_tipo;

    @ColumnInfo(name = COLUMN_AS_PD_EROSION)
    private Integer as_pd_erosion;

    @ColumnInfo(name = COLUMN_AS_PD_DEPOSITACION)
    private Integer as_pd_depositacion;

    @ColumnInfo(name = COLUMN_AS_PD_PEDOGENESIS)
    private Integer as_pd_pedogenesis;

    @ColumnInfo(name = COLUMN_AS_PE)
    private List<Long> as_potencial_enterramiento;

    @ColumnInfo(name = COLUMN_AS_PE_OBSERVACIONES)
    private String as_pe_observaciones;

    @ColumnInfo(name = COLUMN_AS_PA_AUSENCIA)
    private Integer as_pa_ausencia;

    @ColumnInfo(name = COLUMN_AS_PA_HUMEDAD)
    private Integer as_pa_humedad;

    @ColumnInfo(name = COLUMN_AS_PA_ENCHARCAMIENTO)
    private Integer as_pa_encharcamiento;

    @ColumnInfo(name = COLUMN_AS_PA_ESCORRENTIA)
    private Integer as_pa_escorrentia;

    @ColumnInfo(name = COLUMN_AS_PA_CANAL)
    private Integer as_pa_canal;

    @ColumnInfo(name = COLUMN_AS_PA_OBSERVACIONES)
    private String as_pa_observaciones;

    @ColumnInfo(name = COLUMN_VE_TIPO)
    private String ve_tipo;

    @ColumnInfo(name = COLUMN_VE_DISTRIBUCION)
    private String ve_distribucion;

    @ColumnInfo(name = COLUMN_VE_VISIBILIDAD)
    private List<Long> ve_visibilidad;

    @ColumnInfo(name = COLUMN_BIO_VEGETALES)
    private Integer bio_vegetales;

    @ColumnInfo(name = COLUMN_BIO_ROEDORES)
    private Integer bio_roedores;

    @ColumnInfo(name = COLUMN_BIO_PISOTEO)
    private Integer bio_pisoteo;

    @ColumnInfo(name = COLUMN_BIO_CARNIVOROS)
    private Integer bio_carnivoros;

    @ColumnInfo(name = COLUMN_BIO_OTROS)
    private Integer bio_otros;

    @ColumnInfo(name = COLUMN_BIO_DESCRIPCION)
    private String bio_descripcion;

    @ColumnInfo(name = COLUMN_PRESENCIA_FAUNA)
    private String presencia_fauna;

    @ColumnInfo(name = COLUMN_AA_GANADERIA)
    private Integer aa_ganaderia;

    @ColumnInfo(name = COLUMN_AA_AGRICULTURA)
    private Integer aa_agricultura;

    @ColumnInfo(name = COLUMN_AA_ALAMBRADO)
    private Integer aa_alambrado;

    @ColumnInfo(name = COLUMN_AA_CONSTRUCCION)
    private Integer aa_construccion;

    @ColumnInfo(name = COLUMN_AA_CAMINO)
    private Integer aa_camino;

    @ColumnInfo(name = COLUMN_AA_TERRAPLENES)
    private Integer aa_terraplenes;

    @ColumnInfo(name = COLUMN_AA_CANAL)
    private Integer aa_canal;

    @ColumnInfo(name = COLUMN_AA_DESECHOS)
    private Integer aa_desechos;

    @ColumnInfo(name = COLUMN_AA_OTROS)
    private Integer aa_otros;

    @ColumnInfo(name = COLUMN_AA_DESCRIPCION)
    private String aa_descripcion;

    @ColumnInfo(name = COLUMN_PM_LITICO)
    private Integer pm_litico;

    @ColumnInfo(name = COLUMN_PM_FAUNISTICO)
    private Integer pm_faunistico;

    @ColumnInfo(name = COLUMN_PM_CERAMICA)
    private Integer pm_ceramica;

    @ColumnInfo(name = COLUMN_PM_GRESS)
    private Integer pm_gress;

    @ColumnInfo(name = COLUMN_PM_VIDRIO)
    private Integer pm_vidrio;

    @ColumnInfo(name = COLUMN_PM_OTROS)
    private String pm_otros;

    @ColumnInfo(name = COLUMN_PM_OBSERVACIONES)
    private String pm_observacion;

    public Muestreos() {
    }

    public Muestreos(long id, Integer numero, String fecha_creacion, String as_descripcion, List<Long> as_pendiente, String as_sedimento_tipo, Integer as_pd_erosion, Integer as_pd_depositacion, Integer as_pd_pedogenesis, List<Long> as_potencial_enterramiento, String as_pe_observaciones, Integer as_pa_ausencia, Integer as_pa_humedad, Integer as_pa_encharcamiento, Integer as_pa_escorrentia, Integer as_pa_canal, String as_pa_observaciones, String ve_tipo, String ve_distribucion, List<Long> ve_visibilidad, Integer bio_vegetales, Integer bio_roedores, Integer bio_pisoteo, Integer bio_carnivoros, Integer bio_otros, String bio_descripcion, String presencia_fauna, Integer aa_ganaderia, Integer aa_agricultura, Integer aa_alambrado, Integer aa_construccion, Integer aa_camino, Integer aa_terraplenes, Integer aa_canal, Integer aa_desechos, Integer aa_otros, String aa_descripcion, Integer pm_litico, Integer pm_faunistico, Integer pm_ceramica, Integer pm_gress, Integer pm_vidrio, String pm_otros, String pm_observacion, long nro_transecta) {
        this.id = id;
        this.numero = numero;
        this.fecha_creacion = fecha_creacion;
        this.as_descripcion = as_descripcion;
        this.as_pendiente = as_pendiente;
        this.as_sedimento_tipo = as_sedimento_tipo;
        this.as_pd_erosion = as_pd_erosion;
        this.as_pd_depositacion = as_pd_depositacion;
        this.as_pd_pedogenesis = as_pd_pedogenesis;
        this.as_potencial_enterramiento = as_potencial_enterramiento;
        this.as_pe_observaciones = as_pe_observaciones;
        this.as_pa_ausencia = as_pa_ausencia;
        this.as_pa_humedad = as_pa_humedad;
        this.as_pa_encharcamiento = as_pa_encharcamiento;
        this.as_pa_escorrentia = as_pa_escorrentia;
        this.as_pa_canal = as_pa_canal;
        this.as_pa_observaciones = as_pa_observaciones;
        this.ve_tipo = ve_tipo;
        this.ve_distribucion = ve_distribucion;
        this.ve_visibilidad = ve_visibilidad;
        this.bio_vegetales = bio_vegetales;
        this.bio_roedores = bio_roedores;
        this.bio_pisoteo = bio_pisoteo;
        this.bio_carnivoros = bio_carnivoros;
        this.bio_otros = bio_otros;
        this.bio_descripcion = bio_descripcion;
        this.presencia_fauna = presencia_fauna;
        this.aa_ganaderia = aa_ganaderia;
        this.aa_agricultura = aa_agricultura;
        this.aa_alambrado = aa_alambrado;
        this.aa_construccion = aa_construccion;
        this.aa_camino = aa_camino;
        this.aa_terraplenes = aa_terraplenes;
        this.aa_canal = aa_canal;
        this.aa_desechos = aa_desechos;
        this.aa_otros = aa_otros;
        this.aa_descripcion = aa_descripcion;
        this.pm_litico = pm_litico;
        this.pm_faunistico = pm_faunistico;
        this.pm_ceramica = pm_ceramica;
        this.pm_gress = pm_gress;
        this.pm_vidrio = pm_vidrio;
        this.pm_otros = pm_otros;
        this.pm_observacion = pm_observacion;
        this.nro_transecta = nro_transecta;
    }

    public Muestreos(Integer numero, String fecha_creacion, String as_descripcion, List<Long> as_pendiente, String as_sedimento_tipo, Integer as_pd_erosion, Integer as_pd_depositacion, Integer as_pd_pedogenesis, List<Long> as_potencial_enterramiento, String as_pe_observaciones, Integer as_pa_ausencia, Integer as_pa_humedad, Integer as_pa_encharcamiento, Integer as_pa_escorrentia, Integer as_pa_canal, String as_pa_observaciones, String ve_tipo, String ve_distribucion, List<Long> ve_visibilidad, Integer bio_vegetales, Integer bio_roedores, Integer bio_pisoteo, Integer bio_carnivoros, Integer bio_otros, String bio_descripcion, String presencia_fauna, Integer aa_ganaderia, Integer aa_agricultura, Integer aa_alambrado, Integer aa_construccion, Integer aa_camino, Integer aa_terraplenes, Integer aa_canal, Integer aa_desechos, Integer aa_otros, String aa_descripcion, Integer pm_litico, Integer pm_faunistico, Integer pm_ceramica, Integer pm_gress, Integer pm_vidrio, String pm_otros, String pm_observacion, long nro_transecta) {
        this.numero = numero;
        this.fecha_creacion = fecha_creacion;
        this.as_descripcion = as_descripcion;
        this.as_pendiente = as_pendiente;
        this.as_sedimento_tipo = as_sedimento_tipo;
        this.as_pd_erosion = as_pd_erosion;
        this.as_pd_depositacion = as_pd_depositacion;
        this.as_pd_pedogenesis = as_pd_pedogenesis;
        this.as_potencial_enterramiento = as_potencial_enterramiento;
        this.as_pe_observaciones = as_pe_observaciones;
        this.as_pa_ausencia = as_pa_ausencia;
        this.as_pa_humedad = as_pa_humedad;
        this.as_pa_encharcamiento = as_pa_encharcamiento;
        this.as_pa_escorrentia = as_pa_escorrentia;
        this.as_pa_canal = as_pa_canal;
        this.as_pa_observaciones = as_pa_observaciones;
        this.ve_tipo = ve_tipo;
        this.ve_distribucion = ve_distribucion;
        this.ve_visibilidad = ve_visibilidad;
        this.bio_vegetales = bio_vegetales;
        this.bio_roedores = bio_roedores;
        this.bio_pisoteo = bio_pisoteo;
        this.bio_carnivoros = bio_carnivoros;
        this.bio_otros = bio_otros;
        this.bio_descripcion = bio_descripcion;
        this.presencia_fauna = presencia_fauna;
        this.aa_ganaderia = aa_ganaderia;
        this.aa_agricultura = aa_agricultura;
        this.aa_alambrado = aa_alambrado;
        this.aa_construccion = aa_construccion;
        this.aa_camino = aa_camino;
        this.aa_terraplenes = aa_terraplenes;
        this.aa_canal = aa_canal;
        this.aa_desechos = aa_desechos;
        this.aa_otros = aa_otros;
        this.aa_descripcion = aa_descripcion;
        this.pm_litico = pm_litico;
        this.pm_faunistico = pm_faunistico;
        this.pm_ceramica = pm_ceramica;
        this.pm_gress = pm_gress;
        this.pm_vidrio = pm_vidrio;
        this.pm_otros = pm_otros;
        this.pm_observacion = pm_observacion;
        this.nro_transecta = nro_transecta;
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

    public long getNumeroTransecta() {
        return this.nro_transecta;
    }

    public void setNumeroTransecta(long numero) {
        this.nro_transecta = numero;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getAs_descripcion() {
        return as_descripcion;
    }

    public void setAs_descripcion(String as_descripcion) {
        this.as_descripcion = as_descripcion;
    }

    public List<Long> getAs_pendiente() {
        return as_pendiente;
    }

    public void setAs_pendiente(List<Long> as_pendiente) {
        this.as_pendiente = as_pendiente;
    }

    public String getAs_sedimento_tipo() {
        return as_sedimento_tipo;
    }

    public void setAs_sedimento_tipo(String as_sedimento_tipo) {
        this.as_sedimento_tipo = as_sedimento_tipo;
    }

    public Integer getAs_pd_erosion() {
        return as_pd_erosion;
    }

    public void setAs_pd_erosion(Integer as_pd_erosion) {
        this.as_pd_erosion = as_pd_erosion;
    }

    public Integer getAs_pd_depositacion() {
        return as_pd_depositacion;
    }

    public void setAs_pd_depositacion(Integer as_pd_depositacion) {
        this.as_pd_depositacion = as_pd_depositacion;
    }

    public Integer getAs_pd_pedogenesis() {
        return as_pd_pedogenesis;
    }

    public void setAs_pd_pedogenesis(Integer as_pd_pedogenesis) {
        this.as_pd_pedogenesis = as_pd_pedogenesis;
    }

    public List<Long> getAs_potencial_enterramiento() {
        return as_potencial_enterramiento;
    }

    public void setAs_potencial_enterramiento(List<Long> as_potencial_enterramiento) {
        this.as_potencial_enterramiento = as_potencial_enterramiento;
    }

    public String getAs_pe_observaciones() {
        return as_pe_observaciones;
    }

    public void setAs_pe_observaciones(String as_pe_observaciones) {
        this.as_pe_observaciones = as_pe_observaciones;
    }

    public Integer getAs_pa_ausencia() {
        return as_pa_ausencia;
    }

    public void setAs_pa_ausencia(Integer as_pa_ausencia) {
        this.as_pa_ausencia = as_pa_ausencia;
    }

    public Integer getAs_pa_humedad() {
        return as_pa_humedad;
    }

    public void setAs_pa_humedad(Integer as_pa_humedad) {
        this.as_pa_humedad = as_pa_humedad;
    }

    public Integer getAs_pa_encharcamiento() {
        return as_pa_encharcamiento;
    }

    public void setAs_pa_encharcamiento(Integer as_pa_encharcamiento) {
        this.as_pa_encharcamiento = as_pa_encharcamiento;
    }

    public Integer getAs_pa_escorrentia() {
        return as_pa_escorrentia;
    }

    public void setAs_pa_escorrentia(Integer as_pa_escorrentia) {
        this.as_pa_escorrentia = as_pa_escorrentia;
    }

    public Integer getAs_pa_canal() {
        return as_pa_canal;
    }

    public void setAs_pa_canal(Integer as_pa_canal) {
        this.as_pa_canal = as_pa_canal;
    }

    public String getAs_pa_observaciones() {
        return as_pa_observaciones;
    }

    public void setAs_pa_observaciones(String as_pa_observaciones) {
        this.as_pa_observaciones = as_pa_observaciones;
    }

    public String getVe_tipo() {
        return ve_tipo;
    }

    public void setVe_tipo(String ve_tipo) {
        this.ve_tipo = ve_tipo;
    }

    public String getVe_distribucion() {
        return ve_distribucion;
    }

    public void setVe_distribucion(String ve_distribucion) {
        this.ve_distribucion = ve_distribucion;
    }

    public List<Long> getVe_visibilidad() {
        return ve_visibilidad;
    }

    public void setVe_visibilidad(List<Long> ve_visibilidad) {
        this.ve_visibilidad = ve_visibilidad;
    }

    public Integer getBio_vegetales() {
        return bio_vegetales;
    }

    public void setBio_vegetales(Integer bio_vegetales) {
        this.bio_vegetales = bio_vegetales;
    }

    public Integer getBio_roedores() {
        return bio_roedores;
    }

    public void setBio_roedores(Integer bio_roedores) {
        this.bio_roedores = bio_roedores;
    }

    public Integer getBio_pisoteo() {
        return bio_pisoteo;
    }

    public void setBio_pisoteo(Integer bio_pisoteo) {
        this.bio_pisoteo = bio_pisoteo;
    }

    public Integer getBio_carnivoros() {
        return bio_carnivoros;
    }

    public void setBio_carnivoros(Integer bio_carnivoros) {
        this.bio_carnivoros = bio_carnivoros;
    }

    public Integer getBio_otros() {
        return bio_otros;
    }

    public void setBio_otros(Integer bio_otros) {
        this.bio_otros = bio_otros;
    }

    public String getBio_descripcion() {
        return bio_descripcion;
    }

    public void setBio_descripcion(String bio_descripcion) {
        this.bio_descripcion = bio_descripcion;
    }

    public String getPresencia_fauna() {
        return presencia_fauna;
    }

    public void setPresencia_fauna(String presencia_fauna) {
        this.presencia_fauna = presencia_fauna;
    }

    public Integer getAa_ganaderia() {
        return aa_ganaderia;
    }

    public void setAa_ganaderia(Integer aa_ganaderia) {
        this.aa_ganaderia = aa_ganaderia;
    }

    public Integer getAa_agricultura() {
        return aa_agricultura;
    }

    public void setAa_agricultura(Integer aa_agricultura) {
        this.aa_agricultura = aa_agricultura;
    }

    public Integer getAa_alambrado() {
        return aa_alambrado;
    }

    public void setAa_alambrado(Integer aa_alambrado) {
        this.aa_alambrado = aa_alambrado;
    }

    public Integer getAa_construccion() {
        return aa_construccion;
    }

    public void setAa_construccion(Integer aa_construccion) {
        this.aa_construccion = aa_construccion;
    }

    public Integer getAa_camino() {
        return aa_camino;
    }

    public void setAa_camino(Integer aa_camino) {
        this.aa_camino = aa_camino;
    }

    public Integer getAa_terraplenes() {
        return aa_terraplenes;
    }

    public void setAa_terraplenes(Integer aa_terraplenes) {
        this.aa_terraplenes = aa_terraplenes;
    }

    public Integer getAa_canal() {
        return aa_canal;
    }

    public void setAa_canal(Integer aa_canal) {
        this.aa_canal = aa_canal;
    }

    public Integer getAa_desechos() {
        return aa_desechos;
    }

    public void setAa_desechos(Integer aa_desechos) {
        this.aa_desechos = aa_desechos;
    }

    public Integer getAa_otros() {
        return aa_otros;
    }

    public void setAa_otros(Integer aa_otros) {
        this.aa_otros = aa_otros;
    }

    public String getAa_descripcion() {
        return aa_descripcion;
    }

    public void setAa_descripcion(String aa_descripcion) {
        this.aa_descripcion = aa_descripcion;
    }

    public Integer getPm_litico() {
        return pm_litico;
    }

    public void setPm_litico(Integer pm_litico) {
        this.pm_litico = pm_litico;
    }

    public Integer getPm_faunistico() {
        return pm_faunistico;
    }

    public void setPm_faunistico(Integer pm_faunistico) {
        this.pm_faunistico = pm_faunistico;
    }

    public Integer getPm_ceramica() {
        return pm_ceramica;
    }

    public void setPm_ceramica(Integer pm_ceramica) {
        this.pm_ceramica = pm_ceramica;
    }

    public Integer getPm_gress() {
        return pm_gress;
    }

    public void setPm_gress(Integer pm_gress) {
        this.pm_gress = pm_gress;
    }

    public Integer getPm_vidrio() {
        return pm_vidrio;
    }

    public void setPm_vidrio(Integer pm_vidrio) {
        this.pm_vidrio = pm_vidrio;
    }

    public String getPm_otros() {
        return pm_otros;
    }

    public void setPm_otros(String pm_otros) {
        this.pm_otros = pm_otros;
    }

    public String getPm_observacion() {
        return pm_observacion;
    }

    public void setPm_observacion(String pm_observacion) {
        this.pm_observacion = pm_observacion;
    }
}
