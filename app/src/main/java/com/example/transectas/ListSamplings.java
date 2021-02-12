package com.example.transectas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import com.example.transectas.data.Muestreos;
import com.example.transectas.data.MuestreosTransectas;
import com.example.transectas.dialogos.DialogoAddItemFragment;
import com.example.transectas.dialogos.TabsSampling;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.transectas.dialogos.TabsSampling.SAMPLING_REPLY_ID;

public class ListSamplings extends BaseActivity{

    private AppViewModel mViewModel;
    private SamplingsAdapter adapter;

    public static final int NEW_SAMPLING_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_SAMPLING_ACTIVITY_REQUEST_CODE = 2;

    public static final String NUMBER_UPDATE_SAMPLING = "number_sampling_to_be_updated";
    public static final String DATE_UPDATE_SAMPLING = "date_sampling_to_be_updated";
    public static final String AS_DESCRIPTION_UPDATE_SAMPLING = "as_description_sampling_to_be_updated";
    public static final String PENDING_UPDATE_SAMPLING = "pending_sampling_to_be_updated";
    public static final String SEDIMENT_UPDATE_SAMPLING = "sediment_sampling_to_be_updated";
    public static final String PD_UPDATE_EROSION = "pd_erosion_sampling_to_be_updated";
    public static final String PD_UPDATE_DEPOSITATION = "pd_depositacion_sampling_to_be_updated";
    public static final String PD_UPDATE_PEDOGENESIS = "pd_pedogenesis_sampling_to_be_updated";
    public static final String POTENTIAL_UPDATE_SAMPLING = "potential_sampling_to_be_updated";
    public static final String POTENTIAL_OBS_UPDATE_SAMPLING = "potential_obs_sampling_to_be_updated";
    public static final String PA_ABSENCE_UPDATE_SAMPLING = "pa_absence_sampling_to_be_updated";
    public static final String PA_HUMIDITY_UPDATE_SAMPLING = "pa_humidity_sampling_to_be_updated";
    public static final String PA_ENCHARCAMIENTO_UPDATE_SAMPLING = "pa_encharcamiento_sampling_to_be_updated";
    public static final String PA_ESCORRENTIA_UPDATE_SAMPLING = "pa_escorrentia_sampling_to_be_updated";
    public static final String PA_CANAL_UPDATE_SAMPLING = "pa_canal_sampling_to_be_updated";
    public static final String PA_OBSERVATIONS_UPDATE_SAMPLING = "pa_observations_sampling_to_be_updated";
    public static final String VEGET_TYPE_UPDATE_SAMPLING = "veget_type_sampling_to_be_updated";
    public static final String VEGET_DISTRI_UPDATE_SAMPLING = "veget_distri_sampling_to_be_updated";
    public static final String VEGET_VISIB_UPDATE_SAMPLING = "veget_visib_sampling_to_be_updated";
    public static final String BIO_VEGETABLES_UPDATE_SAMPLING = "bio_vegetables_sampling_to_be_updated";
    public static final String BIO_RODENTS_UPDATE_SAMPLING = "bio_rodents_sampling_to_be_updated";
    public static final String BIO_PISOTEO_UPDATE_SAMPLING = "bio_pisoteo_sampling_to_be_updated";
    public static final String BIO_CARNIVORES_UPDATE_SAMPLING = "bio_carnivores_sampling_to_be_updated";
    public static final String BIO_OTHERS_UPDATE_SAMPLING = "bio_others_sampling_to_be_updated";
    public static final String BIO_DESCRIPTION_UPDATE_SAMPLING = "bio_description_sampling_to_be_updated";
    public static final String FAUNA_ACTUAL_UPDATE_SAMPLING = "fauna_actual_sampling_to_be_updated";
    public static final String AA_GANADERIA_UPDATE_SAMPLING = "aa_ganaderia_sampling_to_be_updated";
    public static final String AA_AGRICULTURA_UPDATE_SAMPLING = "aa_agricultura_sampling_to_be_updated";
    public static final String AA_ALAMBRADO_UPDATE_SAMPLING = "aa_alambrado_sampling_to_be_updated";
    public static final String AA_CONSTRUCTION_UPDATE_SAMPLING = "aa_construction_sampling_to_be_updated";
    public static final String AA_WAY_UPDATE_SAMPLING = "aa_way_sampling_to_be_updated";
    public static final String AA_TERRAPLENES_UPDATE_SAMPLING = "aa_terraplenes_sampling_to_be_updated";
    public static final String AA_CANAL_UPDATE_SAMPLING = "aa_canal_sampling_to_be_updated";
    public static final String AA_WASTE_UPDATE_SAMPLING = "aa_waste_sampling_to_be_updated";
    public static final String AA_OTHERS_UPDATE_SAMPLING = "aa_others_sampling_to_be_updated";
    public static final String AA_DESCRIPTION_UPDATE_SAMPLING = "aa_description_sampling_to_be_updated";
    public static final String PM_LITHIC_UPDATE_SAMPLING = "pm_lithic_sampling_to_be_updated";
    public static final String PM_FAUNISTICO_UPDATE_SAMPLING = "pm_faunistico_sampling_to_be_updated";
    public static final String PM_CERAMICA_UPDATE_SAMPLING = "pm_ceramica_sampling_to_be_updated";
    public static final String PM_GRESS_UPDATE_SAMPLING = "pm_gress_sampling_to_be_updated";
    public static final String PM_GLASSES_UPDATE_SAMPLING = "pm_glasses_sampling_to_be_updated";
    public static final String PM_OTHERS_UPDATE_SAMPLING = "pm_others_sampling_to_be_updated";
    public static final String PM_OBSERVATIONS_UPDATE_SAMPLING = "pm_observations_sampling_to_be_updated";

    public static final String SAMPLING_DATA_ID = "extra_data_id";

    public long TRANSECT_ID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_muestreo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        setUpToolbar();

        RecyclerView recyclerView = findViewById(R.id.rvMuestreos);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        final Bundle extras = getIntent().getExtras();
        long idTransect = -1;
        if (extras != null)
            idTransect = extras.getLong("idTransect");
        TRANSECT_ID = idTransect;

        adapter = new SamplingsAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(lim);

        mViewModel.getAllMuestreos(idTransect).observe(this, samplings -> adapter.setMuestreos(samplings));

        adapter.setOnItemClickListener((v, position) -> {
            Muestreos sampling = adapter.getSamplingAtPosition(position);
            launchUpdateSamplingActivity(sampling);
        });

        FloatingActionButton fab = findViewById(R.id.addMuestro);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ListSamplings.this, TabsSampling.class);
            intent.putExtra("idTransect", TRANSECT_ID);
            startActivityForResult(intent, NEW_SAMPLING_ACTIVITY_REQUEST_CODE);
        });
    }

    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showHomeUpIcon(getString(R.string.samplingTitle));
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
            public boolean onQueryTextChange(String textFiltrate) {
                adapter.getFilter().filter(textFiltrate);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.new_search:
            case R.id.acerca_de:
                about(this);
                break;
            case R.id.addItemTabla:
                DialogoAddItemFragment dialogoAddItemFragment = new DialogoAddItemFragment();
                dialogoAddItemFragment.show(getSupportFragmentManager(), "DialogAddItem");
                break;
            default:
                //Error
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchUpdateSamplingActivity(Muestreos sampling) {

        Intent intent = new Intent(this, TabsSampling.class);
        intent.putExtra(NUMBER_UPDATE_SAMPLING, sampling.getNumero());
        intent.putExtra(DATE_UPDATE_SAMPLING, sampling.getFecha_creacion());
        intent.putExtra(AS_DESCRIPTION_UPDATE_SAMPLING, sampling.getAs_descripcion());
        List<Long> idPending = sampling.getAs_pendiente();
        Long idSpPending = idPending.get(0);
        intent.putExtra(PENDING_UPDATE_SAMPLING, idSpPending);
        intent.putExtra(SEDIMENT_UPDATE_SAMPLING, sampling.getAs_sedimento_tipo());
        intent.putExtra(PD_UPDATE_EROSION, sampling.getAs_pd_erosion());
        intent.putExtra(PD_UPDATE_DEPOSITATION, sampling.getAs_pd_depositacion());
        intent.putExtra(PD_UPDATE_PEDOGENESIS, sampling.getAs_pd_pedogenesis());
        List<Long> idPotentialEat = sampling.getAs_potencial_enterramiento();
        Long idSpPotentialEat = idPotentialEat.get(0);
        intent.putExtra(POTENTIAL_UPDATE_SAMPLING, idSpPotentialEat);
        intent.putExtra(POTENTIAL_OBS_UPDATE_SAMPLING, sampling.getAs_pe_observaciones());
        intent.putExtra(PA_ABSENCE_UPDATE_SAMPLING, sampling.getAs_pa_ausencia());
        intent.putExtra(PA_HUMIDITY_UPDATE_SAMPLING, sampling.getAs_pa_humedad());
        intent.putExtra(PA_ENCHARCAMIENTO_UPDATE_SAMPLING, sampling.getAs_pa_encharcamiento());
        intent.putExtra(PA_ESCORRENTIA_UPDATE_SAMPLING, sampling.getAs_pa_escorrentia());
        intent.putExtra(PA_CANAL_UPDATE_SAMPLING, sampling.getAs_pa_canal());
        intent.putExtra(PA_OBSERVATIONS_UPDATE_SAMPLING, sampling.getAs_pa_observaciones());

        intent.putExtra(VEGET_TYPE_UPDATE_SAMPLING, sampling.getVe_tipo());
        intent.putExtra(VEGET_DISTRI_UPDATE_SAMPLING, sampling.getVe_distribucion());
        List<Long> idVegVisibility = sampling.getVe_visibilidad();
        Long idSpVegVisibility = idVegVisibility.get(0);
        intent.putExtra(VEGET_VISIB_UPDATE_SAMPLING, idSpVegVisibility);
        intent.putExtra(BIO_VEGETABLES_UPDATE_SAMPLING, sampling.getBio_vegetales());
        intent.putExtra(BIO_RODENTS_UPDATE_SAMPLING, sampling.getBio_roedores());
        intent.putExtra(BIO_PISOTEO_UPDATE_SAMPLING, sampling.getBio_pisoteo());
        intent.putExtra(BIO_CARNIVORES_UPDATE_SAMPLING, sampling.getBio_carnivoros());
        intent.putExtra(BIO_OTHERS_UPDATE_SAMPLING, sampling.getBio_otros());
        intent.putExtra(BIO_DESCRIPTION_UPDATE_SAMPLING, sampling.getBio_descripcion());
        intent.putExtra(FAUNA_ACTUAL_UPDATE_SAMPLING, sampling.getPresencia_fauna());
        intent.putExtra(AA_GANADERIA_UPDATE_SAMPLING, sampling.getAa_ganaderia());
        intent.putExtra(AA_AGRICULTURA_UPDATE_SAMPLING, sampling.getAa_agricultura());
        intent.putExtra(AA_ALAMBRADO_UPDATE_SAMPLING, sampling.getAa_alambrado());
        intent.putExtra(AA_CONSTRUCTION_UPDATE_SAMPLING, sampling.getAa_construccion());
        intent.putExtra(AA_WAY_UPDATE_SAMPLING, sampling.getAa_camino());
        intent.putExtra(AA_TERRAPLENES_UPDATE_SAMPLING, sampling.getAa_terraplenes());
        intent.putExtra(AA_CANAL_UPDATE_SAMPLING, sampling.getAa_canal());
        intent.putExtra(AA_WASTE_UPDATE_SAMPLING, sampling.getAa_desechos());
        intent.putExtra(AA_OTHERS_UPDATE_SAMPLING, sampling.getAa_otros());
        intent.putExtra(AA_DESCRIPTION_UPDATE_SAMPLING, sampling.getAa_descripcion());
        intent.putExtra(PM_LITHIC_UPDATE_SAMPLING, sampling.getPm_litico());
        intent.putExtra(PM_FAUNISTICO_UPDATE_SAMPLING, sampling.getPm_faunistico());
        intent.putExtra(PM_CERAMICA_UPDATE_SAMPLING, sampling.getPm_ceramica());
        intent.putExtra(PM_GRESS_UPDATE_SAMPLING, sampling.getPm_gress());
        intent.putExtra(PM_GLASSES_UPDATE_SAMPLING, sampling.getPm_vidrio());
        intent.putExtra(PM_OTHERS_UPDATE_SAMPLING, sampling.getPm_otros());
        intent.putExtra(PM_OBSERVATIONS_UPDATE_SAMPLING, sampling.getPm_observacion());

        intent.putExtra(SAMPLING_DATA_ID, sampling.getId());
        startActivityForResult(intent, UPDATE_SAMPLING_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_SAMPLING_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            long idSamplingNew;
            Integer numberSamplingInt = data.getIntExtra(TabsSampling.NUMBER_REPLY_SAMPLING, -1);
            List<Long> pending = new ArrayList<>();
            long idPendingSpinner = data.getLongExtra(TabsSampling.PENDING_REPLY_SAMPLING, -1);
            if (idPendingSpinner != -1)
                pending.add(idPendingSpinner);

            List<Long> potentialEnt = new ArrayList<>();
            long idPotentialSpinner = data.getLongExtra(TabsSampling.POTENTIAL_REPLY_SAMPLING, -1);
            if (idPotentialSpinner != -1)
                potentialEnt.add(idPotentialSpinner);

            List<Long> visibility = new ArrayList<>();
            long idVisibilitySpinner = data.getLongExtra(TabsSampling.VEGET_VISIB_REPLY_SAMPLING, -1);
            if (idVisibilitySpinner != -1)
                visibility.add(idVisibilitySpinner);

            // Save the data.

            Muestreos samplingsNew = new Muestreos(numberSamplingInt,
                    data.getStringExtra(TabsSampling.DATE_REPLY_SAMPLING),
                    data.getStringExtra(TabsSampling.AS_DESCRIPTION_REPLY_SAMPLING),
                    pending,
                    data.getStringExtra(TabsSampling.SEDIMENT_REPLY_SAMPLING),
                    data.getIntExtra(TabsSampling.PD_EROSION_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.PD_DEPOSITACION_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.PD_PEDOGENESIS_REPLY_SAMPLING, -1),
                    potentialEnt,
                    data.getStringExtra(TabsSampling.POTENTIAL_OBS_REPLY_SAMPLING),
                    data.getIntExtra(TabsSampling.PA_ABSENCE_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.PA_HUMIDITY_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.PA_ENCHARCAMIENTO_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.PA_ESCORRENTIA_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.PA_CANAL_REPLY_SAMPLING, -1),
                    data.getStringExtra(TabsSampling.PA_OBSERVATIONS_REPLY_SAMPLING),
                    data.getStringExtra(TabsSampling.VEGET_TIPO_REPLY_SAMPLING),
                    data.getStringExtra(TabsSampling.VEGET_DISTRI_REPLY_SAMPLING),
                    visibility,
                    data.getIntExtra(TabsSampling.BIO_VEGETABLES_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.BIO_RODENTS_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.BIO_PISOTEO_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.BIO_CARNIVORES_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.BIO_OTHERS_REPLY_SAMPLING, -1),
                    data.getStringExtra(TabsSampling.BIO_DESCRIPCION_REPLY_SAMPLING),
                    data.getStringExtra(TabsSampling.FAUNA_ACTUAL_REPLY_SAMPLING),

                    data.getIntExtra(TabsSampling.AA_GANADERIA_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.AA_AGRICULTURA_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.AA_ALAMBRADO_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.AA_CONSTRUCTION_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.AA_WAY_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.AA_TERRAPLENES_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.AA_CANAL_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.AA_WASTE_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.AA_OTHERS_REPLY_SAMPLING, -1),
                    data.getStringExtra(TabsSampling.AA_DESCRIPTION_REPLY_SAMPLING),

                    data.getIntExtra(TabsSampling.PM_LITHIC_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.PM_FAUNISTICO_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.PM_CERAMIC_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.PM_GRESS_REPLY_SAMPLING, -1),
                    data.getIntExtra(TabsSampling.PM_GLASSES_REPLY_SAMPLING, -1),
                    data.getStringExtra(TabsSampling.PM_OTHERS_REPLY_SAMPLING),
                    data.getStringExtra(TabsSampling.PM_OBSERVATIONS_REPLY_SAMPLING),
                    TRANSECT_ID
            );

            idSamplingNew = mViewModel.insertSampling(samplingsNew);

            if (idSamplingNew > 0){
                //insertar sampling a List Proyecto
                if (TRANSECT_ID > 0) {
                    MuestreosTransectas samplingsTransects = new MuestreosTransectas(TRANSECT_ID, idSamplingNew);
                    long idSamplingTransect = mViewModel.insertSamplingTransect(samplingsTransects);
                    if (idSamplingTransect > 0)
                        Toast.makeText(this, R.string.muestreo_saved,
                                Toast.LENGTH_LONG).show();
                    else Toast.makeText(this, R.string.muestreo_a_transecta_not_saved,
                            Toast.LENGTH_LONG).show();
                }else Toast.makeText(this, R.string.muestreo_a_transecta_not_saved,
                        Toast.LENGTH_LONG).show();
            }
            else  Toast.makeText(this, R.string.unable_to_update_muestreo, Toast.LENGTH_LONG).show();
        } else if (requestCode == UPDATE_SAMPLING_ACTIVITY_REQUEST_CODE
                && resultCode == RESULT_OK) {

            Integer number_data = data.getIntExtra(TabsSampling.NUMBER_REPLY_SAMPLING, -1);
            String date_data = data.getStringExtra(TabsSampling.DATE_REPLY_SAMPLING);
            String as_description_data = data.getStringExtra(TabsSampling.AS_DESCRIPTION_REPLY_SAMPLING);

            long pending_data_long = data.getLongExtra(TabsSampling.PENDING_REPLY_SAMPLING, -1);
            List<Long> pending = new ArrayList<>();
            if (pending_data_long != -1){
                pending.add(pending_data_long);
            }

            String as_sediment_data = data.getStringExtra(TabsSampling.SEDIMENT_REPLY_SAMPLING);
            Integer pd_erosion_data = data.getIntExtra(TabsSampling.PD_EROSION_REPLY_SAMPLING, -1);
            Integer pd_depositacion_data = data.getIntExtra(TabsSampling.PD_DEPOSITACION_REPLY_SAMPLING, -1);
            Integer pd_pedogenesis_data = data.getIntExtra(TabsSampling.PD_PEDOGENESIS_REPLY_SAMPLING, -1);

            long potentialEnt_data_long = data.getLongExtra(TabsSampling.POTENTIAL_REPLY_SAMPLING, -1);
            List<Long> potential = new ArrayList<>();
            if (potentialEnt_data_long != -1){
                potential.add(potentialEnt_data_long);
            }

            long visibility_data_long = data.getLongExtra(TabsSampling.VEGET_VISIB_REPLY_SAMPLING, -1);
            List<Long> visibility = new ArrayList<>();
            if (visibility_data_long != -1){
                visibility.add(visibility_data_long);
            }

            String potentialObs_data = data.getStringExtra(TabsSampling.POTENTIAL_OBS_REPLY_SAMPLING);
            Integer pa_absence_data = data.getIntExtra(TabsSampling.PA_ABSENCE_REPLY_SAMPLING, -1);
            Integer pa_humidity_data = data.getIntExtra(TabsSampling.PA_HUMIDITY_REPLY_SAMPLING, -1);
            Integer pa_encharcamiento_data = data.getIntExtra(TabsSampling.PA_ENCHARCAMIENTO_REPLY_SAMPLING, -1);
            Integer pa_escorrentia_data = data.getIntExtra(TabsSampling.PA_ESCORRENTIA_REPLY_SAMPLING, -1);
            Integer pa_canal_data = data.getIntExtra(TabsSampling.PA_CANAL_REPLY_SAMPLING, -1);
            String pa_observations = data.getStringExtra(TabsSampling.PA_OBSERVATIONS_REPLY_SAMPLING);
            String veget_type_data = data.getStringExtra(TabsSampling.VEGET_TIPO_REPLY_SAMPLING);
            String veget_distri_data = data.getStringExtra(TabsSampling.VEGET_DISTRI_REPLY_SAMPLING);

            Integer bio_vegetables_data = data.getIntExtra(TabsSampling.BIO_VEGETABLES_REPLY_SAMPLING, -1);
            Integer bio_rodents_data = data.getIntExtra(TabsSampling.BIO_RODENTS_REPLY_SAMPLING, -1);
            Integer bio_pisoteo_data = data.getIntExtra(TabsSampling.BIO_PISOTEO_REPLY_SAMPLING, -1);
            Integer bio_carnivores_data = data.getIntExtra(TabsSampling.BIO_CARNIVORES_REPLY_SAMPLING, -1);
            Integer bio_others_data =  data.getIntExtra(TabsSampling.BIO_OTHERS_REPLY_SAMPLING, -1);
            String bio_description_data =  data.getStringExtra(TabsSampling.BIO_DESCRIPCION_REPLY_SAMPLING);
            String fauna_actual_data = data.getStringExtra(TabsSampling.FAUNA_ACTUAL_REPLY_SAMPLING);

            Integer aa_ganaderia_data = data.getIntExtra(TabsSampling.AA_GANADERIA_REPLY_SAMPLING, -1);
            Integer aa_agricultura_data = data.getIntExtra(TabsSampling.AA_AGRICULTURA_REPLY_SAMPLING, -1);
            Integer aa_alambrado_data = data.getIntExtra(TabsSampling.AA_ALAMBRADO_REPLY_SAMPLING, -1);
            Integer aa_construction_data = data.getIntExtra(TabsSampling.AA_CONSTRUCTION_REPLY_SAMPLING, -1);
            Integer aa_way_data = data.getIntExtra(TabsSampling.AA_WAY_REPLY_SAMPLING, -1);
            Integer aa_terraplenes_data = data.getIntExtra(TabsSampling.AA_TERRAPLENES_REPLY_SAMPLING, -1);
            Integer aa_canal_data = data.getIntExtra(TabsSampling.AA_CANAL_REPLY_SAMPLING, -1);
            Integer aa_waste_data = data.getIntExtra(TabsSampling.AA_WASTE_REPLY_SAMPLING, -1);
            Integer aa_others_data = data.getIntExtra(TabsSampling.AA_OTHERS_REPLY_SAMPLING, -1);
            String aa_description_data =  data.getStringExtra(TabsSampling.AA_DESCRIPTION_REPLY_SAMPLING);

            Integer pm_lithic_data = data.getIntExtra(TabsSampling.PM_LITHIC_REPLY_SAMPLING, -1);
            Integer pm_faunistico_data = data.getIntExtra(TabsSampling.PM_FAUNISTICO_REPLY_SAMPLING, -1);
            Integer pm_ceraminca_data = data.getIntExtra(TabsSampling.PM_CERAMIC_REPLY_SAMPLING, -1);
            Integer pm_gress_data = data.getIntExtra(TabsSampling.PM_GRESS_REPLY_SAMPLING, -1);
            Integer pm_glasses_data = data.getIntExtra(TabsSampling.PM_GLASSES_REPLY_SAMPLING, -1);
            String pm_others_data = data.getStringExtra(TabsSampling.PM_OTHERS_REPLY_SAMPLING);
            String pm_observations_data = data.getStringExtra(TabsSampling.PM_OBSERVATIONS_REPLY_SAMPLING);

            long idSamplingUpdate = data.getLongExtra(SAMPLING_REPLY_ID, -1);

            if (idSamplingUpdate != -1) {
                Muestreos samplingsUpdate = new Muestreos(idSamplingUpdate,number_data, date_data, as_description_data, pending, as_sediment_data, pd_erosion_data, pd_depositacion_data,
                        pd_pedogenesis_data, potential, potentialObs_data, pa_absence_data, pa_humidity_data, pa_encharcamiento_data, pa_escorrentia_data,
                        pa_canal_data, pa_observations, veget_type_data, veget_distri_data, visibility, bio_vegetables_data, bio_rodents_data, bio_pisoteo_data,
                        bio_carnivores_data, bio_others_data, bio_description_data, fauna_actual_data, aa_ganaderia_data, aa_agricultura_data, aa_alambrado_data, aa_construction_data,
                        aa_way_data, aa_terraplenes_data, aa_canal_data, aa_waste_data, aa_others_data, aa_description_data, pm_lithic_data, pm_faunistico_data,
                        pm_ceraminca_data, pm_gress_data, pm_glasses_data, pm_others_data, pm_observations_data, TRANSECT_ID);

                mViewModel.updateSampling(samplingsUpdate);
                Toast.makeText(this, R.string.muestreo_saved,
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.unable_to_update_muestreo,
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(
                    this, R.string.sin_cambios, Toast.LENGTH_LONG).show();
        }
    }
}
