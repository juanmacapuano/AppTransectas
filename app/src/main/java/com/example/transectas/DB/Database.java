package com.example.transectas.DB;

import android.content.Context;
import android.os.AsyncTask;

import com.example.transectas.data.DataConverter;
import com.example.transectas.data.FindingDao;
import com.example.transectas.data.Hallazgos;
import com.example.transectas.data.HallazgosMuestreos;
import com.example.transectas.data.HallazgosMuestreosDao;
import com.example.transectas.data.Muestreos;
import com.example.transectas.data.MuestreosDao;
import com.example.transectas.data.MuestreosTransectas;
import com.example.transectas.data.MuestreosTransectasDao;
import com.example.transectas.data.ProyectosDao;
import com.example.transectas.data.Tabla;
import com.example.transectas.data.TablaDao;
import com.example.transectas.data.TablaValor;
import com.example.transectas.data.TablaValorDao;
import com.example.transectas.data.Transectas;
import com.example.transectas.data.TransectasDao;
import com.example.transectas.data.TransectasProyectos;
import com.example.transectas.data.TransectasProyectosDao;
import com.example.transectas.data.Valores;
import com.example.transectas.data.ValoresDao;
import com.example.transectas.data.Proyectos;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
@androidx.room.Database(entities = {Proyectos.class, Valores.class,
        Tabla.class, TablaValor.class, Transectas.class, TransectasProyectos.class, MuestreosTransectas.class,
        Muestreos.class, Hallazgos.class, HallazgosMuestreos.class}, version = 13, exportSchema = false)
@TypeConverters({DataConverter.class})
public abstract class Database extends RoomDatabase {

    public abstract TablaDao getTablaDao();
    public abstract TablaValorDao geTablaValorDao();
    public abstract ProyectosDao getProjectDao();
    public abstract ValoresDao getValuesDao();
    public abstract TransectasProyectosDao getTransectsProjectsDao();
    public abstract TransectasDao getTransectsDao();
    public abstract MuestreosTransectasDao getSamplingTransectsDao();
    public abstract MuestreosDao getSamplingDao();
    public abstract FindingDao getFindingDao();
    public abstract HallazgosMuestreosDao getHallazgoMuestreoDao();

    private static volatile Database INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, "app_database")
                            .addCallback(sRoomDatabaseCallback) //.allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static void addItemTabla(Database db, Tabla _tabla, ArrayList<String> _values) {
        long idTabla;
        final TablaDao tablaDao;
        final ValoresDao valoresDao;
        final TablaValorDao tablaValorDao;
        tablaDao = db.getTablaDao();
        valoresDao = db.getValuesDao();
        tablaValorDao = db.geTablaValorDao();

        idTabla = tablaDao.insert(_tabla);

        //Values
        long idValuesTabla;
        Valores valor = new Valores();
        int countTabla = valoresDao.count();
        Tabla[] resultado = new Tabla[countTabla];
        resultado = tablaDao.selectByName("Seleccione");
        TablaValor tablaValor;
        if (resultado.length > 0) {
            tablaValor = new TablaValor(idTabla, resultado[0].getId());
            tablaValorDao.insert(tablaValor);
        }
        for (String _valor : _values) {
            valor.setValor(_valor);
            valor.setBorrado(0);
            idValuesTabla = valoresDao.insert(valor);
            tablaValor = new TablaValor(idTabla, idValuesTabla);
            tablaValorDao.insert(tablaValor);
        }

    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ProyectosDao proyectoDao;
        private final TablaValorDao tablaValorDao;
        private final TablaDao tablaDao;
        private final ValoresDao valoresDao;
        private final TransectasProyectosDao transectasProyectosDao;
        private final TransectasDao transectasDao;
        private final MuestreosTransectasDao muestreosTransectasDao;
        private final MuestreosDao muestreosDao;
        private final FindingDao findingDao;
        private final HallazgosMuestreosDao hallazgosMuestreosDao;

        PopulateDbAsync(Database db) {
            proyectoDao = db.getProjectDao();
            tablaValorDao = db.geTablaValorDao();
            tablaDao = db.getTablaDao();
            valoresDao = db.getValuesDao();
            transectasProyectosDao = db.getTransectsProjectsDao();
            transectasDao = db.getTransectsDao();
            muestreosTransectasDao = db.getSamplingTransectsDao();
            muestreosDao = db.getSamplingDao();
            findingDao = db.getFindingDao();
            hallazgosMuestreosDao = db.getHallazgoMuestreoDao();

        }

        @Override
        protected Void doInBackground(final Void... params) {
            if (valoresDao.getAnyItem().length < 1) {
                ArrayList<String> valuesString = new ArrayList<>();

                Valores valor = new Valores("Seleccione");
                valoresDao.insert(valor);
                valor = new Valores("SI");
                valoresDao.insert(valor);
                valor = new Valores("NO");
                valoresDao.insert(valor);
                valor = new Valores("OTROS");
                valoresDao.insert(valor);

                //tabla: pending
                Tabla tabla = new Tabla("pendiente");
                valuesString.add("Nula");
                valuesString.add("Baja");
                valuesString.add("Media");
                valuesString.add("Alta");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: operators
                tabla = new Tabla("operadores");
                //creo valores tabla operadores
                valuesString = new ArrayList<String>();
                valuesString.add("Alvarez");
                valuesString.add("Gonzalez");
                valuesString.add("Gutierrez");
                valuesString.add("Massigoge");
                valuesString.add("Rafuse");

                addItemTabla(INSTANCE,tabla,valuesString);



                //tabla: processDominant
                tabla = new Tabla("processDominant");
                valuesString = new ArrayList<>();
                valuesString.add("Erosión");
                valuesString.add("Depositación");
                valuesString.add("Pedogénesis");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: potentialEnterramiento
                tabla = new Tabla("potencialEnterramiento");
                valuesString = new ArrayList<>();
                valuesString.add("Nulo");
                valuesString.add("Bajo");
                valuesString.add("Alto");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: presenceWater
                tabla = new Tabla("presenceWater");
                valuesString = new ArrayList<>();
                valuesString.add("Ausencia");
                valuesString.add("Humedad");
                valuesString.add("Encharcamiento");
                valuesString.add("Escorrentia");
                valuesString.add("Canal");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: visibilidad
                tabla = new Tabla("visibilidad");
                valuesString = new ArrayList<>();
                valuesString.add("Excelente");
                valuesString.add("Muy buena");
                valuesString.add("Buena");
                valuesString.add("Regular");
                valuesString.add("Mala");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: bioturbation
                tabla = new Tabla("bioturbation");
                valuesString = new ArrayList<>();
                valuesString.add("Vegetales");
                valuesString.add("Roedores");
                valuesString.add("Pisoteo");
                valuesString.add("Carnívoros");
                valuesString.add("Otros");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: actionAnthropic
                tabla = new Tabla("actionAnthropic");
                valuesString = new ArrayList<>();
                valuesString.add("Ganadería");
                valuesString.add("Agricultura");
                valuesString.add("Alambrado");
                valuesString.add("Construcción");
                valuesString.add("Camino");
                valuesString.add("Terraplenes");
                valuesString.add("Canal Artificial");
                valuesString.add("Desechos");
                valuesString.add("Otros");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: hcc
                tabla = new Tabla("findConcetCarcasa");
                valuesString = new ArrayList<>();
                valuesString.add("Hallazgo ailado");
                valuesString.add("Concentración");
                valuesString.add("Carcasa");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: completeness
                tabla = new Tabla("completeness");
                valuesString = new ArrayList<>();
                valuesString.add("C");
                valuesString.add("F");
                valuesString.add("Incompleto");
                valuesString.add("Pérdida");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: estateFusion
                tabla = new Tabla("estateFusion");
                valuesString = new ArrayList<>();
                valuesString.add("NF");
                //valoresString.add("F");  se repite
                valuesString.add("NA");
                valuesString.add("Indeterminado");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: positionWater
                tabla = new Tabla("positionWater");
                valuesString = new ArrayList<>();
                valuesString.add("S");
                valuesString.add("E");
                valuesString.add("O");
                valuesString.add("N");
                valuesString.add("SE");
                valuesString.add("SO");
                valuesString.add("NE");
                valuesString.add("NO");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: orientationWater
                tabla = new Tabla("orientationWater");
                valuesString = new ArrayList<>();
                //valoresString.add("NA");  se repite
                valuesString.add("Transversal");
                valuesString.add("Oblicua");
                valuesString.add("Paralelo");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: memorization
                tabla = new Tabla("memorization");
                //valoresString.add("NA");   se repite
                valuesString = new ArrayList<String>();
                valuesString.add("0");
                valuesString.add("1");
                valuesString.add("2");
                valuesString.add("3");
                valuesString.add("4");
                valuesString.add("5");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: materialArchaeological
                tabla = new Tabla("materialArchaeological");
                //valoresString.add("Otros");   se repite
                valuesString = new ArrayList<>();
                valuesString.add("Lítico");
                valuesString.add("Faunístico");
                valuesString.add("Cerámica");
                valuesString.add("Gress");
                valuesString.add("Vidrio");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: contextEnvironmental
                tabla = new Tabla("contextoAmbiental");
                //valoresString.add("Otros");   se repite
                valuesString = new ArrayList<>();
                valuesString.add("Valles fluviales");
                valuesString.add("Márg. lag. llanura");
                valuesString.add("Márg. lag. intermedanosa");
                valuesString.add("Márg. albufera");
                valuesString.add("Lomadas-planicies"); //Lomadas y planicies loessicas edafisadas
                valuesString.add("Médanos costeros vivos");
                valuesString.add("Médanos vegetados"); //medanos vegetados con monte tala
                valuesString.add("Bañados");
                valuesString.add("Laderas serranas");
                valuesString.add("Base de frentes serranos");
                valuesString.add("Médanos vegetados litoral");
                valuesString.add("Márg. lag. e/llanura-medanos"); //margenes de lagunas entre llanura y medanos litorales
                valuesString.add("Cima serrana");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: tipoOcurrencia1
                tabla = new Tabla("tipoOcurrencia1");
                valuesString = new ArrayList<>();
                valuesString.add("Elemento Individual");
                valuesString.add("Test1");
                valuesString.add("Test2");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: tipoOcurrencia2
                tabla = new Tabla("tipoOcurrencia2");
                valuesString = new ArrayList<>();
                valuesString.add("Concentración");
                valuesString.add("Test1_to1");
                valuesString.add("Test2_to2");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: posicionHallazgo
                tabla = new Tabla("posicionHallazgo");
                valuesString = new ArrayList<>();
                valuesString.add("Semienterrado");
                valuesString.add("Test1_semi");
                valuesString.add("Test2_semi");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: orientacionAguaHallazgo
                tabla = new Tabla("orientacionAguaHallazgo");
                valuesString = new ArrayList<>();
                valuesString.add("Paralelo");
                valuesString.add("Perpendicular");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: taxonHallazgo
                tabla = new Tabla("taxonHallazgo");
                valuesString = new ArrayList<>();
                valuesString.add("Especie Canis");
                valuesString.add("Especie Canis (Perro)");

                addItemTabla(INSTANCE,tabla,valuesString);

                //tabla: taxonTamanioHallazgo
                tabla = new Tabla("taxonTamanioHallazgo");
                valuesString = new ArrayList<>();
                valuesString.add("Med-Peq(25kg)");
                valuesString.add("Pequeño 10kg");

                addItemTabla(INSTANCE,tabla,valuesString);
            }
            return null;
        }
    }
}