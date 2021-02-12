package com.example.transectas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.transectas.data.Hallazgos;
import com.example.transectas.data.HallazgosMuestreos;
import com.example.transectas.dialogos.DialogoAddItemFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.widget.SearchView;

public class ListFindings extends BaseActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private List<Hallazgos> findingsList;
    private AppViewModel mViewModel;
    private FindingsAdapter adapter;

    public static final int NEW_FINDING_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_FINDING_ACTIVITY_REQUEST_CODE = 2;

    public static final String OCURRENCIA_UPDATE_HALLAZGO = "ocurrencia_hallazgo_to_be_updated";
    public static final String TIPOOCURRENCIA1_UPDATE_HALLAZGO = "tipo_ocurrencia1_hallazgo_to_be_updated";
    public static final String TIPOOCURRENCIA2_UPDATE_HALLAZGO = "tipo_ocurrencia2_hallazgo_to_be_updated";
    public static final String CONCCARC_UPDATE_HALLAZGO = "conc_carc_hallazgo_to_be_updated";
    public static final String AMBIENTEINMED_UPDATE_HALLAZGO = "ambiente_inmed_hallazgo_to_be_updated";
    public static final String POSICION_UPDATE_HALLAZGO = "posicion_hallazgo_to_be_updated";
    public static final String ORIENTACIONAGUA_UPDATE_HALLAZGO = "orientacion_agua_hallazgo_to_be_updated";
    public static final String TAXON_UPDATE_HALLAZGO = "taxon_hallazgo_to_be_updated";
    public static final String TAMANIOTAXON_UPDATE_HALLAZGO = "tamanio_taxon_hallazgo_to_be_updated";
    public static final String ANALISTA_UPDATE_HALLAZGO = "analista_hallazgo_to_be_updated";
    public static final String OBSERVACIONES_UPDATE_HALLAZGO = "observaciones_hallazgo_to_be_updated";

    public static final String HALLAZGO_DATA_ID = "extra_data_id";

    public static final String transecta_id_string = "transecta_id";
    public static final String idMuestreo_string = "idMuestreo";

    public long MUESTREO_ID = -1;
    public long TRANSECTA_ID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hallazgos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        setUpToolbar();

        recyclerView = findViewById(R.id.rvHallazgos);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        findingsList = new ArrayList<>();

        final Bundle extras = getIntent().getExtras();

        if (extras != null) {
            MUESTREO_ID = extras.getLong(idMuestreo_string);
            TRANSECTA_ID = extras.getLong(transecta_id_string);
        }

        adapter = new FindingsAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(lim);
        //TO DO

        mViewModel.getAllFindings(MUESTREO_ID).observe(this, new Observer<List<Hallazgos>>() {
            @Override
            public void onChanged(@Nullable final List<Hallazgos> hallazgos) {
                adapter.setHallazgos(hallazgos);
            }
        });

        adapter.setOnItemClickListener(new FindingsAdapter.ClickListener()  {

            @Override
            public void onItemClick(View v, int position) {
                Hallazgos hallazgo = adapter.getHallazgoAtPosition(position);
                launchUpdateHallazgoActivity(hallazgo);
            }
        });

        FloatingActionButton fab = findViewById(R.id.addHallazgo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListFindings.this, New_Finding.class);
                //intent.putExtra(ListFindings.idMuestreo_string, MUESTREO_ID);
                //intent.putExtra("idTransecta", TRANSECTA_ID);
                startActivityForResult(intent, NEW_FINDING_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    private void setUpToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showHomeUpIcon(getString(R.string.findingTitle));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem confirmItem = menu.findItem(R.id.idAceptarEdicion);
        confirmItem.setVisible(false);
        MenuItem editItem = menu.findItem(R.id.editItem);
        editItem.setVisible(false);
        MenuItem searchItem = menu.findItem(R.id.new_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String textoFiltrado) {
                adapter.getFilter().filter(textoFiltrado);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.new_search:
                acercaDe();
                break;
            case R.id.acerca_de:
                about(this);
                break;
            case R.id.addItemTabla:
                DialogoAddItemFragment dialogoAddItemFragment = new DialogoAddItemFragment();
                dialogoAddItemFragment.show(getSupportFragmentManager(), "DialogoAddItem");
                break;
            default:
                //Error
        }

        return super.onOptionsItemSelected(item);
    }

    private void acercaDe(){    }

    public void launchUpdateHallazgoActivity( Hallazgos hallazgo) {

        Intent intent = new Intent(this, Item_Finding.class);

        intent.putExtra(OCURRENCIA_UPDATE_HALLAZGO, hallazgo.getOcurrencia());

        List<Long> idTipoOcur1 = hallazgo.getTipo_ocur1();
        Long idSpTipoOcur1 = idTipoOcur1.get(0);
        intent.putExtra(TIPOOCURRENCIA1_UPDATE_HALLAZGO, idSpTipoOcur1);
        List<Long> idTipoOCur2 = hallazgo.getTipo_ocur2();
        Long idSpTipoOcur2 = idTipoOCur2.get(0);
        intent.putExtra(TIPOOCURRENCIA2_UPDATE_HALLAZGO, idSpTipoOcur2);
        intent.putExtra(CONCCARC_UPDATE_HALLAZGO, hallazgo.getConc_carc());
        intent.putExtra(AMBIENTEINMED_UPDATE_HALLAZGO, hallazgo.getAmbiente_inmediato());
        List<Long> idPosicion = hallazgo.getPosicion();
        Long idSpPosicion = idPosicion.get(0);
        intent.putExtra(POSICION_UPDATE_HALLAZGO, idSpPosicion);
        List<Long> idOrientacionAgua = hallazgo.getOrientacion_agua();
        Long idSpOrientacionAgua = idOrientacionAgua.get(0);
        intent.putExtra(ORIENTACIONAGUA_UPDATE_HALLAZGO, idSpOrientacionAgua);
        List<Long> idTaxon = hallazgo.getTaxon();
        Long idSpTaxon = idTaxon.get(0);
        intent.putExtra(TAXON_UPDATE_HALLAZGO, idSpTaxon);
        List<Long> idTamanioTaxon = hallazgo.getTaxon_tamanio();
        Long idSpTamanioTaxon = idTamanioTaxon.get(0);
        intent.putExtra(TAMANIOTAXON_UPDATE_HALLAZGO, idSpTamanioTaxon);
        intent.putExtra(ANALISTA_UPDATE_HALLAZGO, hallazgo.getAnalista());
        intent.putExtra(OBSERVACIONES_UPDATE_HALLAZGO, hallazgo.getObservaciones());

        intent.putExtra(HALLAZGO_DATA_ID, hallazgo.getId());



        intent.putExtra(idMuestreo_string, MUESTREO_ID);
        intent.putExtra(transecta_id_string, TRANSECTA_ID);

        startActivityForResult(intent, UPDATE_FINDING_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_FINDING_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            long idHallazgoNuevo = -1;
            String ocurrenciaString = data.getStringExtra(New_Finding.OCURRENCIA_REPLY_HALLAZGO);
            Integer ocurrencia = Integer.parseInt(ocurrenciaString);
            List<Long> tipoOcur1 = new ArrayList<Long>();
            Long idTipoOcur1Spinner = data.getLongExtra(New_Finding.TIPOOCUR1_REPLY_HALLAZGO, -1);
            if (idTipoOcur1Spinner != -1)
                tipoOcur1.add(idTipoOcur1Spinner);

            List<Long> tipoOcur2 = new ArrayList<Long>();
            Long idTipoOcur2Spinner = data.getLongExtra(New_Finding.TIPOOCUR2_REPLY_HALLAZGO, -1);
            if (idTipoOcur2Spinner != -1)
                tipoOcur2.add(idTipoOcur2Spinner);

            List<Long> posicion = new ArrayList<Long>();
            Long idPosicionSpinner = data.getLongExtra(New_Finding.POSICION_REPLY_HALLAZGO, -1);
            if (idPosicionSpinner != -1)
                posicion.add(idPosicionSpinner);

            List<Long> orientacionAgua = new ArrayList<Long>();
            Long idOrientacionAguaSpinner = data.getLongExtra(New_Finding.ORIENTACION_REPLY_HALLAZGO, -1);
            if (idOrientacionAguaSpinner != -1)
                orientacionAgua.add(idOrientacionAguaSpinner);

            List<Long> taxon = new ArrayList<Long>();
            Long idTaxonSpinner = data.getLongExtra(New_Finding.TAXON_REPLY_HALLAZGO, -1);
            if (idTaxonSpinner != -1)
                taxon.add(idTaxonSpinner);

            List<Long> taxonTamanio = new ArrayList<Long>();
            Long idTaxonTamanioSpinner = data.getLongExtra(New_Finding.TAXONTAMANIO_REPLY_HALLAZGO, -1);
            if (idTaxonTamanioSpinner != -1)
                taxonTamanio.add(idTaxonTamanioSpinner);


            // Save the data.

            Hallazgos hallazgoNuevo = new Hallazgos(
                    ocurrencia,
                    tipoOcur1,
                    tipoOcur2,
                    data.getStringExtra(New_Finding.CONCARC_REPLY_HALLAZGO),
                    data.getStringExtra(New_Finding.AMBIENTE_REPLY_HALLAZGO),
                    posicion,
                    orientacionAgua,
                    taxon,
                    taxonTamanio,
                    null,
                    "",
                    data.getStringExtra(New_Finding.OBSERVACIONES_REPLY_HALLAZGO),
                    MUESTREO_ID
            );

            if (MUESTREO_ID > 0) {
                hallazgoNuevo.setMuestreo_id(MUESTREO_ID);
                idHallazgoNuevo = mViewModel.insertFinding(hallazgoNuevo);
                if (idHallazgoNuevo > 0){
                    HallazgosMuestreos hallazgosMuestreos = new HallazgosMuestreos(idHallazgoNuevo, MUESTREO_ID);
                    long idHallazgoMuestroInsert = mViewModel.insertFindingSampling(hallazgosMuestreos);
                    if (idHallazgoMuestroInsert > 0)
                        Toast.makeText(this, R.string.hallazgo_saved,
                                Toast.LENGTH_LONG).show();
                    else Toast.makeText(this, R.string.hallazgo_to_sampling_error,
                            Toast.LENGTH_LONG).show();
                }
                else  Toast.makeText(this, R.string.hallazgo_not_saved, Toast.LENGTH_LONG).show();
            }else Toast.makeText(this, "Muestreo ID null",
                        Toast.LENGTH_LONG).show();
        } else if (requestCode == UPDATE_FINDING_ACTIVITY_REQUEST_CODE
                && resultCode == RESULT_OK) {

            Integer ocurrencia_data = data.getIntExtra(New_Finding.OCURRENCIA_REPLY_HALLAZGO, -1);

            Long tipoOcur1_data_long = data.getLongExtra(New_Finding.TIPOOCUR1_REPLY_HALLAZGO, -1);
            List<Long> tipoOcur1 = new ArrayList<Long>();
            if (tipoOcur1_data_long != -1){
                tipoOcur1.add(tipoOcur1_data_long);
            }

            Long tipoOcur2_data_long = data.getLongExtra(New_Finding.TIPOOCUR2_REPLY_HALLAZGO, -1);
            List<Long> tipoOcur2 = new ArrayList<Long>();
            if (tipoOcur2_data_long != -1){
                tipoOcur2.add(tipoOcur2_data_long);
            }

            String concCarc_data = data.getStringExtra(New_Finding.CONCARC_REPLY_HALLAZGO);
            String ambienteInmed_data = data.getStringExtra(New_Finding.AMBIENTE_REPLY_HALLAZGO);

            Long posicion_data_long = data.getLongExtra(New_Finding.POSICION_REPLY_HALLAZGO, -1);
            List<Long> posicion = new ArrayList<Long>();
            if (posicion_data_long != -1){
                posicion.add(posicion_data_long);
            }

            Long orientacionAgua_data_long = data.getLongExtra(New_Finding.ORIENTACION_REPLY_HALLAZGO, -1);
            List<Long> orientacionAgua = new ArrayList<Long>();
            if (orientacionAgua_data_long != -1){
                orientacionAgua.add(orientacionAgua_data_long);
            }

            Long taxon_data_long = data.getLongExtra(New_Finding.TAXON_REPLY_HALLAZGO, -1);
            List<Long> taxon = new ArrayList<Long>();
            if (taxon_data_long != -1){
                taxon.add(taxon_data_long);
            }

            Long taxonTamanio_data_long = data.getLongExtra(New_Finding.TAXONTAMANIO_REPLY_HALLAZGO, -1);
            List<Long> taxonTamanio = new ArrayList<Long>();
            if (taxonTamanio_data_long != -1){
                taxonTamanio.add(taxonTamanio_data_long);
            }

            String observaciones_data = data.getStringExtra(New_Finding.OBSERVACIONES_REPLY_HALLAZGO);

            long idHallazgoUpdate = data.getLongExtra(HALLAZGO_DATA_ID, -1);

            if (idHallazgoUpdate != -1) {
                Hallazgos hallazgoUpdate = new Hallazgos(
                        idHallazgoUpdate,
                        ocurrencia_data,
                        tipoOcur1,
                        tipoOcur2,
                        concCarc_data,
                        ambienteInmed_data,
                        posicion,
                        orientacionAgua,
                        taxon,
                        taxonTamanio,
                        null,
                        null,
                        observaciones_data,
                        MUESTREO_ID);

                mViewModel.updateFinding(hallazgoUpdate);
                Toast.makeText(this, R.string.hallazgo_saved,
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.unable_to_update_hallazgo,
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(
                    this, R.string.unable_to_update_hallazgo, Toast.LENGTH_LONG).show();
        }
    }


}
