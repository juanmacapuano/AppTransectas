package com.example.transectas.DB;

import android.app.Application;

import com.example.transectas.data.FindingDao;
import com.example.transectas.data.Hallazgos;
import com.example.transectas.data.HallazgosMuestreos;
import com.example.transectas.data.HallazgosMuestreosDao;
import com.example.transectas.data.Muestreos;
import com.example.transectas.data.MuestreosDao;
import com.example.transectas.data.MuestreosTransectas;
import com.example.transectas.data.MuestreosTransectasDao;
import com.example.transectas.data.ProyectosDao;
import com.example.transectas.data.Proyectos;
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

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import androidx.lifecycle.LiveData;

public class AppRepository {

    private ProyectosDao mProyectoDao;
    private ValoresDao mValoresDao;
    private TablaDao mTablaDao;
    private TablaValorDao mTablaValorDao;
    private TransectasDao mTransectasDao;
    private TransectasProyectosDao mTransectasProyectosDao;
    private MuestreosDao mMuestreosDao;
    private FindingDao mFindingDao;
    private HallazgosMuestreosDao mHallazgosMuestreosDao;
    private MuestreosTransectasDao mMuestreosTransectasDao;

    private LiveData<List<Valores>> mAllValores;
    private List<Valores> mAllOperadoresList, mAllPendienteList, mAllPotencialEntList, mAllVisibilidadList,
            mAllContextoAmbList, mAllTipoOcur1HallazgoList, mAllTipoOcur2HallazgoList;
    private LiveData<List<Proyectos>> mAllProyectos;
    private List<Transectas> mAllTransectas;


    public AppRepository(Application application) {
        Database db = Database.getDatabase(application);
        mProyectoDao = db.getProjectDao();
        mValoresDao = db.getValuesDao();
        mAllOperadoresList = getAllOperadoresList();
        mAllPendienteList = getAllPendienteList();
        mAllPotencialEntList = getAllPotencialEntList();
        mAllVisibilidadList = getAllVisibilidadList();
        mAllContextoAmbList = getAllContextoAmbList();
        mTablaDao = db.getTablaDao();
        mTablaValorDao = db.geTablaValorDao();
        mAllProyectos = getAllProyectos();
        mTransectasProyectosDao = db.getTransectsProjectsDao();
        mTransectasDao = db.getTransectsDao();
        mMuestreosDao = db.getSamplingDao();
        mMuestreosTransectasDao = db.getSamplingTransectsDao();
        mFindingDao = db.getFindingDao();
        mHallazgosMuestreosDao = db.getHallazgoMuestreoDao();
    }

    public List<Valores> getAllItemsSpinner(String table) {

        Callable<List<Valores>> callable = new Callable<List<Valores>>() {
            @Override
            public List<Valores> call() throws Exception {
                return mValoresDao.selectAllItemsSpinner(table);
            }
        };

        Future<List<Valores>> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Proyectos

    public int countProyecto() {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return mProyectoDao.count();
            }
        };

        Future<Integer> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;

    }

    public LiveData<List<Proyectos>> getAllProyectos() {

        Callable<LiveData<List<Proyectos>>> callable = new Callable<LiveData<List<Proyectos>>>() {
            @Override
            public LiveData<List<Proyectos>> call() throws Exception {
                return mProyectoDao.getAllProyectos();
            }
        };

        Future<LiveData<List<Proyectos>>> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long insertProyecto(Proyectos proyecto) {
        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mProyectoDao.insert(proyecto);
            }
        };

        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer updateProyecto(Proyectos proyecto) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return mProyectoDao.update(proyecto);
            }
        };

        Future<Integer> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Operadores

    public List<Valores> getAllOperadoresList() {
        mAllOperadoresList = getOperadoresList();
        return mAllOperadoresList;
    }

    public List<Valores> getOperadoresList() {
        return getAllItemsSpinner("operadores");
    }

    // ContextoAmb

    public List<Valores> getAllContextoAmbList() {
        mAllContextoAmbList = getContextoAmbList();
        return mAllContextoAmbList;
    }

    public List<Valores> getContextoAmbList() {
        return getAllItemsSpinner("contextoAmbiental");
    }

    // Spinner

    public List<Valores> getAllPendienteList() {
        mAllOperadoresList.clear();
        mAllPendienteList = getPendienteList();
        return mAllPendienteList;
    }

    public List<Valores> getPendienteList() {
        return getAllItemsSpinner("pendiente");
    }

    public List<Valores> getAllPotencialEntList() {
        mAllVisibilidadList = getPotencialEntList();
        return mAllVisibilidadList;
    }

    public List<Valores> getPotencialEntList() {
        return getAllItemsSpinner("potencialEnterramiento");
    }

    public List<Valores> getAllVisibilidadList() {
        mAllVisibilidadList = getVisibilidadList();
        return mAllVisibilidadList;
    }

    public List<Valores> getVisibilidadList() {
        return getAllItemsSpinner("visibilidad");
    }

    // Tablas DB

    public Tabla[] getAllTablas() {
        return getmAllTablas();
    }

    public Tabla[] getmAllTablas() {

        Callable<Tabla[]> callable = new Callable<Tabla[]>() {
            @Override
            public Tabla[] call() throws Exception {
                return mTablaDao.selectAllItems();
            }
        };

        Future<Tabla[]> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Valores

    public long insertItemValor(Valores valor) {
        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mValoresDao.insert(valor);
            }
        };

        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
       // new insertItemValor(mValoresDao).execute(valor);

    }

    public Long insertTablaValor(TablaValor tablaValor) {
        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mTablaValorDao.insert(tablaValor);
            }
        };

        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Transectas

    public List<Transectas> getAllTransectas() {
        mAllTransectas = getAllTransectasList();
        return mAllTransectas;
    }

    public List<Transectas> getAllTransectasList() {

        Callable<List<Transectas>> callable = new Callable<List<Transectas>>() {
            @Override
            public List<Transectas> call() throws Exception {
                return mTransectasDao.selectAllTransectas();
            }
        };

        Future<List<Transectas>> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long insertTransecta(Transectas transecta) {

        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mTransectasDao.insert(transecta);
            }
        };

        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer updateTransecta(Transectas transecta) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return mTransectasDao.update(transecta);
            }
        };

        Future<Integer> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long insertTransectaToProyecto(TransectasProyectos transectasProyectos) {

        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mTransectasProyectosDao.insert(transectasProyectos);
            }
        };

        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<List<Transectas>> getAllTransectasLive(long idProyecto) {
        Callable<LiveData<List<Transectas>>> callable = new Callable<LiveData<List<Transectas>>>() {
            @Override
            public LiveData<List<Transectas>> call() throws Exception {
                return mTransectasProyectosDao.getAllTransectasLive(idProyecto);
            }
        };

        Future<LiveData<List<Transectas>>> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Muestreos

    public Long insertMuestreo(Muestreos muestreo) {

        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mMuestreosDao.insert(muestreo);
            }
        };

        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer updateMuestreo(Muestreos muestreo) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return mMuestreosDao.update(muestreo);
            }
        };

        Future<Integer> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long insertMuestreoToTransecta(MuestreosTransectas muestreosTransectas) {

        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mMuestreosTransectasDao.insert(muestreosTransectas);
            }
        };

        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<List<Muestreos>> getAllMuestreos(long idTransecta) {
        Callable<LiveData<List<Muestreos>>> callable = new Callable<LiveData<List<Muestreos>>>() {
            @Override
            public LiveData<List<Muestreos>> call() throws Exception {
                return mMuestreosTransectasDao.getAllMuestreos(idTransecta);
            }
        };

        Future<LiveData<List<Muestreos>>> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer existTransecta(int numeroTransecta, Long idProyecto) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return mTransectasProyectosDao.existTransecta(numeroTransecta, idProyecto);
            }
        };

        Future<Integer> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer existMuestreo(int numeroTransecta, Long idTransecta) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return mMuestreosTransectasDao.existMuestreo(numeroTransecta, idTransecta);
            }
        };

        Future<Integer> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Findings

    public Long insertFinding(Hallazgos finding) {

        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mFindingDao.insert(finding);
            }
        };

        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long insertFindingToSampling(HallazgosMuestreos hallazgosMuestreos) {

        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mHallazgosMuestreosDao.insert(hallazgosMuestreos);
            }
        };

        Future<Long> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer updateFinding(Hallazgos finding) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return mFindingDao.update(finding);
            }
        };

        Future<Integer> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<List<Hallazgos>> getAllFindings(Long idFinding) {
        Callable<LiveData<List<Hallazgos>>> callable = new Callable<LiveData<List<Hallazgos>>>() {
            @Override
            public LiveData<List<Hallazgos>> call() throws Exception {
                return mHallazgosMuestreosDao.getAllFindings(idFinding);
            }
        };

        Future<LiveData<List<Hallazgos>>> future = Executors.newSingleThreadExecutor().submit(callable);

        try {
            return future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Valores> getAllTipoOcur1HallazgoList() {
        mAllTipoOcur1HallazgoList = getTipoOcur1HallazgoList();
        return mAllTipoOcur1HallazgoList;
    }

    public List<Valores> getTipoOcur1HallazgoList() {
        return getAllItemsSpinner("tipoOcurrencia1");
    }

    public List<Valores> getAllTipoOcur2HallazgoList() {
        mAllTipoOcur2HallazgoList = getTipoOcur2HallazgoList();
        return mAllTipoOcur2HallazgoList;
    }

    public List<Valores> getTipoOcur2HallazgoList() {
        return getAllItemsSpinner("tipoOcurrencia2");
    }

    public List<Valores> getAllPosicionHallazgoList() {
        return getAllItemsSpinner("posicionHallazgo");
    }

    public List<Valores> getAllOrientacionHallazgoList() {
        return getAllItemsSpinner("orientacionAguaHallazgo");
    }

    public List<Valores> getAllTaxonHallazgoList() {
        return getAllItemsSpinner("taxonHallazgo");
    }

    public List<Valores> getAllTaxonTamanioHallazgoList() {
        return getAllItemsSpinner("taxonTamanioHallazgo");
    }
}
