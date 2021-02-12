package com.example.transectas;

import android.app.Application;


import com.example.transectas.DB.AppRepository;
import com.example.transectas.data.Hallazgos;
import com.example.transectas.data.HallazgosMuestreos;
import com.example.transectas.data.Muestreos;
import com.example.transectas.data.MuestreosTransectas;
import com.example.transectas.data.Proyectos;
import com.example.transectas.data.Tabla;
import com.example.transectas.data.TablaValor;
import com.example.transectas.data.Transectas;
import com.example.transectas.data.TransectasProyectos;
import com.example.transectas.data.Valores;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class AppViewModel extends AndroidViewModel {

    private final AppRepository mRepository;
    private final LiveData<List<Proyectos>> mAllProjects;

    public AppViewModel(Application application) {
        super(application);
        mRepository = new AppRepository(application);
        mAllProjects = mRepository.getAllProyectos();
    }

    public Long insertProject(Proyectos _project){
        return mRepository.insertProyecto(_project);
    }

    public void updateProject(Proyectos _project){
        mRepository.updateProyecto(_project);
    }

    LiveData<List<Proyectos>> getAllProjector() {
        return mAllProjects;
    }

    //Operators
    public List<Valores> getAllOperatorsList() {
        return mRepository.getAllOperadoresList();
    }

    //ContextAmb
    public List<Valores> getAllContextAmbList() {
        return mRepository.getAllContextoAmbList();
    }

    //Spinner
    public List<Valores> getAllPendentList() {
        return mRepository.getAllPendienteList();
    }

    public List<Valores> getAllPotentialEntList() {
        return mRepository.getAllPotencialEntList();
    }

    public List<Valores> getAllVisibilityList() {
        return mRepository.getAllVisibilidadList();
    }

    public List<Transectas> getAllTransects() {
        return mRepository.getAllTransectas();
    }

    //Transects

    public void updateTransect(Transectas _transect){
        mRepository.updateTransecta(_transect);
    }

    public Long insertTransect(Transectas _transect){
        return mRepository.insertTransecta(_transect);
    }

    public LiveData<List<Transectas>> getAllTransectsLive(Long idProject){
        return mRepository.getAllTransectasLive(idProject);
    }

    //Tablas

    public Tabla[] getmAllTablas() {
        return mRepository.getAllTablas();
    }

    public Long insertItemValor(Valores _valor){
        return mRepository.insertItemValor(_valor);
    }

    public Long insertTablaValor(TablaValor _tablaValor){
        return mRepository.insertTablaValor(_tablaValor);
    }

    public Long insertTransectToProject(TransectasProyectos transectasProyectos) {
        return mRepository.insertTransectaToProyecto(transectasProyectos);
    }

    //Muestreos

    public LiveData<List<Muestreos>> getAllMuestreos(Long idTransect){
        return mRepository.getAllMuestreos(idTransect);
    }

    public void updateSampling(Muestreos _sampling){
        mRepository.updateMuestreo(_sampling);
    }

    public long insertSampling(Muestreos sampling){
        return mRepository.insertMuestreo(sampling);
    }

    public long insertSamplingTransect(MuestreosTransectas muestreosTransectas) {
        return mRepository.insertMuestreoToTransecta(muestreosTransectas);
    }

    public int existTransect(int numberTransact, Long idProject) {
        return mRepository.existTransecta(numberTransact, idProject);
    }

    public int existSampling(int numberSampling, Long idTransect) {
        return mRepository.existMuestreo(numberSampling, idTransect);
    }

    //Hallazgos

    public long insertFinding(Hallazgos finding) {
        return mRepository.insertFinding(finding);
    }

    public long insertFindingSampling(HallazgosMuestreos hallazgosMuestreos) {
        return mRepository.insertFindingToSampling(hallazgosMuestreos);
    }

    public int updateFinding(Hallazgos finding) {
        return mRepository.updateFinding(finding);
    }

    public LiveData<List<Hallazgos>> getAllFindings(Long idFinding){
        return mRepository.getAllFindings(idFinding);
    }

    public List<Valores> getAllTipoOcur1HallazgoList() {
        return mRepository.getAllTipoOcur1HallazgoList();
    }

    public List<Valores> getAllTipoOcur2HallazgoList() {
        return mRepository.getAllTipoOcur2HallazgoList();
    }

    public List<Valores> getAllPosicionHallazgoList() {
        return mRepository.getAllPosicionHallazgoList();
    }

    public List<Valores> getAllOrientacionHallazgoList() {
        return mRepository.getAllOrientacionHallazgoList();
    }

    public List<Valores> getAllTaxonHallazgoList() {
        return mRepository.getAllTaxonHallazgoList();
    }

    public List<Valores> getAllTaxonTamanioHallazgoList() {
        return mRepository.getAllTaxonTamanioHallazgoList();
    }
}
