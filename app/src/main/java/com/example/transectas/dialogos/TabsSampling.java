package com.example.transectas.dialogos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.transectas.AppViewModel;
import com.example.transectas.R;
import com.example.transectas.data.Muestreos;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import static com.example.transectas.samplings.ListSamplings.AA_CONSTRUCTION_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.AA_DESCRIPTION_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.AA_OTHERS_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.AA_WASTE_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.AA_WAY_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.AS_DESCRIPTION_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.BIO_CARNIVORES_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.BIO_DESCRIPTION_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.BIO_OTHERS_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.BIO_RODENTS_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.BIO_VEGETABLES_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.PA_ABSENCE_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.PA_HUMIDITY_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.PA_OBSERVATIONS_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.PENDING_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.PM_GLASSES_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.PM_LITHIC_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.PM_OBSERVATIONS_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.PM_OTHERS_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.POTENTIAL_OBS_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.POTENTIAL_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.SAMPLING_DATA_ID;
import static com.example.transectas.samplings.ListSamplings.NUMBER_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.DATE_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.PD_UPDATE_EROSION;
import static com.example.transectas.samplings.ListSamplings.PD_UPDATE_DEPOSITATION;
import static com.example.transectas.samplings.ListSamplings.PD_UPDATE_PEDOGENESIS;
import static com.example.transectas.samplings.ListSamplings.PA_ENCHARCAMIENTO_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.PA_ESCORRENTIA_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.PA_CANAL_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.SEDIMENT_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.VEGET_DISTRI_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.VEGET_TYPE_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.VEGET_VISIB_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.BIO_PISOTEO_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.FAUNA_ACTUAL_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.AA_GANADERIA_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.AA_AGRICULTURA_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.AA_ALAMBRADO_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.AA_TERRAPLENES_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.AA_CANAL_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.PM_FAUNISTICO_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.PM_CERAMICA_UPDATE_SAMPLING;
import static com.example.transectas.samplings.ListSamplings.PM_GRESS_UPDATE_SAMPLING;

import com.example.transectas.dialogos.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabsSampling extends AppCompatActivity {

    private AppViewModel mViewModel;
    public Integer number_sampling;
    private long idTransect;

    Muestreos samplingUpdate;

    public static final String NUMBER_REPLY_SAMPLING = "com.example.transectas.NUMBER_REPLY_SAMPLING";
    public static final String DATE_REPLY_SAMPLING = "com.example.transectas.DATE_REPLY_SAMPLING";
    public static final String AS_DESCRIPTION_REPLY_SAMPLING = "com.example.transectas.AS_DESCRIPTION_REPLY_SAMPLING";
    public static final String PENDING_REPLY_SAMPLING = "com.example.transectas.PENDING_REPLY_SAMPLING";
    public static final String SEDIMENT_REPLY_SAMPLING = "com.example.transectas.SEDIMENT_REPLY_SAMPLING";
    public static final String PD_EROSION_REPLY_SAMPLING = "com.example.transectas.PD_EROSION_REPLY_SAMPLING";
    public static final String PD_DEPOSITACION_REPLY_SAMPLING = "com.example.transectas.PD_DEPOSITACION_REPLY_SAMPLING";
    public static final String PD_PEDOGENESIS_REPLY_SAMPLING = "com.example.transectas.PD_PEDOGENESIS_REPLY_SAMPLING";
    public static final String POTENTIAL_REPLY_SAMPLING = "com.example.transectas.POTENTIAL_REPLY_SAMPLING";
    public static final String POTENTIAL_OBS_REPLY_SAMPLING = "com.example.transectas.POTENTIAL_OBS_REPLY_SAMPLING";
    public static final String PA_ABSENCE_REPLY_SAMPLING = "com.example.transectas.PA_ABSENCE_REPLY_SAMPLING";
    public static final String PA_HUMIDITY_REPLY_SAMPLING = "com.example.transectas.PA_HUMIDITY_REPLY_SAMPLING";
    public static final String PA_ENCHARCAMIENTO_REPLY_SAMPLING = "com.example.transectas.PA_ENCHARCAMIENTO_REPLY_SAMPLING";
    public static final String PA_ESCORRENTIA_REPLY_SAMPLING = "com.example.transectas.PA_ESCORRENTIA_REPLY_SAMPLING";
    public static final String PA_CANAL_REPLY_SAMPLING = "com.example.transectas.PA_CANAL_REPLY_SAMPLING";
    public static final String PA_OBSERVATIONS_REPLY_SAMPLING = "com.example.transectas.PA_OBSERVATIONS_REPLY_SAMPLING";
    public static final String VEGET_TIPO_REPLY_SAMPLING = "com.example.transectas.VEGET_TIPO_REPLY_SAMPLING";
    public static final String VEGET_DISTRI_REPLY_SAMPLING = "com.example.transectas.VEGET_DISTRI_REPLY_SAMPLING";
    public static final String VEGET_VISIB_REPLY_SAMPLING = "com.example.transectas.VEGET_VISIB_REPLY_SAMPLING";
    public static final String BIO_VEGETABLES_REPLY_SAMPLING = "com.example.transectas.BIO_VEGETABLES_REPLY_SAMPLING";
    public static final String BIO_RODENTS_REPLY_SAMPLING = "com.example.transectas.BIO_RODENTS_REPLY_SAMPLING";
    public static final String BIO_PISOTEO_REPLY_SAMPLING = "com.example.transectas.BIO_PISOTEO_REPLY_SAMPLING";
    public static final String BIO_CARNIVORES_REPLY_SAMPLING = "com.example.transectas.BIO_CARNIVORES_REPLY_SAMPLING";
    public static final String BIO_OTHERS_REPLY_SAMPLING = "com.example.transectas.BIO_OTHERS_REPLY_SAMPLING";
    public static final String BIO_DESCRIPCION_REPLY_SAMPLING = "com.example.transectas.BIO_DESCRIPCION_REPLY_SAMPLING";
    public static final String FAUNA_ACTUAL_REPLY_SAMPLING = "com.example.transectas.FAUNA_ACTUAL_REPLY_SAMPLING";
    public static final String AA_GANADERIA_REPLY_SAMPLING = "com.example.transectas.AA_GANADERIA_REPLY_SAMPLING";
    public static final String AA_AGRICULTURA_REPLY_SAMPLING = "com.example.transectas.AA_AGRICULTURA_REPLY_SAMPLING";
    public static final String AA_ALAMBRADO_REPLY_SAMPLING = "com.example.transectas.AA_ALAMBRADO_REPLY_SAMPLING";
    public static final String AA_CONSTRUCTION_REPLY_SAMPLING = "com.example.transectas.AA_CONSTRUCTION_REPLY_SAMPLING";
    public static final String AA_WAY_REPLY_SAMPLING = "com.example.transectas.AA_WAY_REPLY_SAMPLING";
    public static final String AA_TERRAPLENES_REPLY_SAMPLING = "com.example.transectas.AA_TERRAPLENES_REPLY_SAMPLING";
    public static final String AA_CANAL_REPLY_SAMPLING = "com.example.transectas.AA_CANAL_REPLY_SAMPLING";
    public static final String AA_WASTE_REPLY_SAMPLING = "com.example.transectas.AA_WASTE_REPLY_SAMPLING";
    public static final String AA_OTHERS_REPLY_SAMPLING = "com.example.transectas.AA_OTHERS_REPLY_SAMPLING";
    public static final String AA_DESCRIPTION_REPLY_SAMPLING = "com.example.transectas.AA_DESCRIPTION_REPLY_SAMPLING";
    public static final String PM_LITHIC_REPLY_SAMPLING = "com.example.transectas.PM_LITHIC_REPLY_SAMPLING";
    public static final String PM_FAUNISTICO_REPLY_SAMPLING = "com.example.transectas.PM_FAUNISTICO_REPLY_SAMPLING";
    public static final String PM_CERAMIC_REPLY_SAMPLING = "com.example.transectas.PM_CERAMIC_REPLY_SAMPLING";
    public static final String PM_GRESS_REPLY_SAMPLING = "com.example.transectas.PM_GRESS_REPLY_SAMPLING";
    public static final String PM_GLASSES_REPLY_SAMPLING = "com.example.transectas.PM_GLASSES_REPLY_SAMPLING";
    public static final String PM_OTHERS_REPLY_SAMPLING = "com.example.transectas.PM_OTHERS_REPLY_SAMPLING";
    public static final String PM_OBSERVATIONS_REPLY_SAMPLING = "com.example.transectas.PM_OBSERVATIONS_REPLY_SAMPLING";


    public static final String SAMPLING_REPLY_ID = "extra_data_id";

    public static long TRANSECT_ID;
    public static long SAMPLING_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_muestreo);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        mViewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        setUpToolbar();
        //setUpComponentes();


        final Bundle extras = getIntent().getExtras();

        if (extras != null) {
            idTransect = extras.getLong("idTransect");
            SAMPLING_ID = extras.getLong(SAMPLING_DATA_ID);
            number_sampling = extras.getInt(NUMBER_UPDATE_SAMPLING, -1);
            if(number_sampling > 0) {


                //Integer newMuestreo = extras.getInt("newSampling");
                //idSamplingInsert = extras.getLong(SAMPLING_DATA_ID);

                TRANSECT_ID = idTransect;

                //textBox
                String date_sampling = extras.getString(DATE_UPDATE_SAMPLING, "");
                String as_description_sampling = extras.getString(AS_DESCRIPTION_UPDATE_SAMPLING, "");
                //spinner
                long as_pending_sampling = extras.getLong(PENDING_UPDATE_SAMPLING, -1);
                //textBox
                String as_sediment_sampling = extras.getString(SEDIMENT_UPDATE_SAMPLING, "");
                //checkbox
                Integer as_pd_erosion_sampling = extras.getInt(PD_UPDATE_EROSION, -1);
                Integer as_pd_depositacion_sampling = extras.getInt(PD_UPDATE_DEPOSITATION, -1);
                Integer as_pd_pedogenesis_sampling = extras.getInt(PD_UPDATE_PEDOGENESIS, -1);
                //spinner
                long as_potEnterramiento_sampling = extras.getLong(POTENTIAL_UPDATE_SAMPLING, -1);
                //textBox
                String as_potEnterramientoObs_sampling = extras.getString(POTENTIAL_OBS_UPDATE_SAMPLING, "");
                //checkbox
                Integer as_pa_absence_sampling = extras.getInt(PA_ABSENCE_UPDATE_SAMPLING, -1);
                Integer as_pa_humidity_sampling = extras.getInt(PA_HUMIDITY_UPDATE_SAMPLING, -1);
                Integer as_pa_encharcamiento_sampling = extras.getInt(PA_ENCHARCAMIENTO_UPDATE_SAMPLING, -1);
                Integer as_pa_escorrentia_sampling = extras.getInt(PA_ESCORRENTIA_UPDATE_SAMPLING, -1);
                Integer as_pa_canal_sampling = extras.getInt(PA_CANAL_UPDATE_SAMPLING, -1);
                //textBox
                String as_pa_observations_sampling = extras.getString(PA_OBSERVATIONS_UPDATE_SAMPLING, "");
                String veg_type_sampling = extras.getString(VEGET_TYPE_UPDATE_SAMPLING, "");
                String veg_distribution_sampling = extras.getString(VEGET_DISTRI_UPDATE_SAMPLING, "");
                //spinner
                long veg_visibility_sampling = extras.getLong(VEGET_VISIB_UPDATE_SAMPLING, -1);
                //checkbox
                Integer bio_vegetables_sampling = extras.getInt(BIO_VEGETABLES_UPDATE_SAMPLING, -1);
                Integer bio_rodents_sampling = extras.getInt(BIO_RODENTS_UPDATE_SAMPLING, -1);
                Integer bio_pisoteo_sampling = extras.getInt(BIO_PISOTEO_UPDATE_SAMPLING, -1);
                Integer bio_carnivores_sampling = extras.getInt(BIO_CARNIVORES_UPDATE_SAMPLING, -1);
                Integer bio_others_sampling = extras.getInt(BIO_OTHERS_UPDATE_SAMPLING, -1);
                //textBox
                String bio_description_sampling = extras.getString(BIO_DESCRIPTION_UPDATE_SAMPLING, "");
                String fauna_actual_sampling = extras.getString(FAUNA_ACTUAL_UPDATE_SAMPLING, "");
                //checkbox
                Integer aa_ganaderia_sampling = extras.getInt(AA_GANADERIA_UPDATE_SAMPLING, -1);
                Integer aa_agricultura_sampling = extras.getInt(AA_AGRICULTURA_UPDATE_SAMPLING, -1);
                Integer aa_alambrado_sampling = extras.getInt(AA_ALAMBRADO_UPDATE_SAMPLING, -1);
                Integer aa_construction_sampling = extras.getInt(AA_CONSTRUCTION_UPDATE_SAMPLING, -1);
                Integer aa_way_sampling = extras.getInt(AA_WAY_UPDATE_SAMPLING, -1);
                Integer aa_terraplenes_sampling = extras.getInt(AA_TERRAPLENES_UPDATE_SAMPLING, -1);
                Integer aa_canal_sampling = extras.getInt(AA_CANAL_UPDATE_SAMPLING, -1);
                Integer aa_waste_sampling = extras.getInt(AA_WASTE_UPDATE_SAMPLING, -1);
                Integer aa_others_sampling = extras.getInt(AA_OTHERS_UPDATE_SAMPLING, -1);
                //textBox
                String aa_description_sampling = extras.getString(AA_DESCRIPTION_UPDATE_SAMPLING, "");
                //checkbox
                Integer pm_lithic_sampling = extras.getInt(PM_LITHIC_UPDATE_SAMPLING, -1);
                Integer pm_faunistico_sampling = extras.getInt(PM_FAUNISTICO_UPDATE_SAMPLING, -1);
                Integer pm_ceramica_sampling = extras.getInt(PM_CERAMICA_UPDATE_SAMPLING, -1);
                Integer pm_gress_sampling = extras.getInt(PM_GRESS_UPDATE_SAMPLING, -1);
                Integer pm_glasses_sampling = extras.getInt(PM_GLASSES_UPDATE_SAMPLING, -1);

                //textBox
                String pm_others_sampling = extras.getString(PM_OTHERS_UPDATE_SAMPLING, "");
                String pm_observations_sampling = extras.getString(PM_OBSERVATIONS_UPDATE_SAMPLING, "");

                samplingUpdate = new Muestreos();
                samplingUpdate.setId(SAMPLING_ID);
                samplingUpdate.setNumero(number_sampling);
                samplingUpdate.setNumeroTransecta(TRANSECT_ID);
                samplingUpdate.setFecha_creacion(date_sampling);
                if (!as_description_sampling.isEmpty()) {
                    samplingUpdate.setAs_descripcion(as_description_sampling);
                }
                if (as_pending_sampling != -1) {
                    List<Long> spinnerPending = new ArrayList<>();
                    spinnerPending.add(as_pending_sampling);
                    samplingUpdate.setAs_pendiente(spinnerPending);
                }
                if (!as_sediment_sampling.isEmpty()) {
                    samplingUpdate.setAs_sedimento_tipo(as_sediment_sampling);
                }
                samplingUpdate.setAs_pd_erosion(as_pd_erosion_sampling);
                samplingUpdate.setAs_pd_depositacion(as_pd_depositacion_sampling);
                samplingUpdate.setAs_pd_pedogenesis(as_pd_pedogenesis_sampling);

                if (as_potEnterramiento_sampling != -1) {
                    List<Long> spinnerPotential = new ArrayList<>();
                    spinnerPotential.add(as_potEnterramiento_sampling);
                    samplingUpdate.setAs_potencial_enterramiento(spinnerPotential);
                }

                samplingUpdate.setAs_pe_observaciones(as_potEnterramientoObs_sampling);

                samplingUpdate.setAs_pa_ausencia(as_pa_absence_sampling);

                samplingUpdate.setAs_pa_humedad(as_pa_humidity_sampling);

                samplingUpdate.setAs_pa_encharcamiento(as_pa_encharcamiento_sampling);

                samplingUpdate.setAs_pa_escorrentia(as_pa_escorrentia_sampling);

                samplingUpdate.setAs_pa_canal(as_pa_canal_sampling);

                if (!as_pa_observations_sampling.isEmpty()) {
                    samplingUpdate.setAs_pa_observaciones(as_pa_observations_sampling);
                }

                if (!veg_type_sampling.isEmpty()) {
                    samplingUpdate.setVe_tipo(veg_type_sampling);
                }

                if (!veg_distribution_sampling.isEmpty()) {
                    samplingUpdate.setVe_distribucion(veg_distribution_sampling);
                }

                if (veg_visibility_sampling != -1) {
                    List<Long> spinnerVisibility = new ArrayList<>();
                    spinnerVisibility.add(veg_visibility_sampling);
                    samplingUpdate.setVe_visibilidad(spinnerVisibility);
                }

                samplingUpdate.setBio_vegetales(bio_vegetables_sampling);

                samplingUpdate.setBio_roedores(bio_rodents_sampling);

                samplingUpdate.setBio_pisoteo(bio_pisoteo_sampling);

                samplingUpdate.setBio_carnivoros(bio_carnivores_sampling);

                samplingUpdate.setBio_otros(bio_others_sampling);

                if (!bio_description_sampling.isEmpty()) {
                    samplingUpdate.setBio_descripcion(bio_description_sampling);
                }

                if (!fauna_actual_sampling.isEmpty()) {
                    samplingUpdate.setPresencia_fauna(fauna_actual_sampling);
                }

                samplingUpdate.setAa_ganaderia(aa_ganaderia_sampling);

                samplingUpdate.setAa_agricultura(aa_agricultura_sampling);

                samplingUpdate.setAa_alambrado(aa_alambrado_sampling);

                samplingUpdate.setAa_construccion(aa_construction_sampling);

                samplingUpdate.setAa_camino(aa_way_sampling);

                samplingUpdate.setAa_terraplenes(aa_terraplenes_sampling);

                samplingUpdate.setAa_canal(aa_canal_sampling);

                samplingUpdate.setAa_desechos(aa_waste_sampling);

                samplingUpdate.setAa_otros(aa_others_sampling);

                samplingUpdate.setAa_descripcion(aa_description_sampling);

                samplingUpdate.setPm_litico(pm_lithic_sampling);

                samplingUpdate.setPm_faunistico(pm_faunistico_sampling);

                samplingUpdate.setPm_ceramica(pm_ceramica_sampling);

                samplingUpdate.setPm_gress(pm_gress_sampling);

                samplingUpdate.setPm_vidrio(pm_glasses_sampling);

                if (!pm_others_sampling.isEmpty()) {
                    samplingUpdate.setPm_otros(pm_others_sampling);
                }

                if (!pm_observations_sampling.isEmpty()) {
                    samplingUpdate.setPm_observacion(pm_observations_sampling);
                }

                getData();

            } else samplingUpdate = null;
        }
    }

    public Muestreos getData() {
        if (samplingUpdate != null)
            return  samplingUpdate;
        return null;
    }

    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showHomeUpIcon();
    }

    public void showHomeUpIcon(){
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Muestreo");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cancelar, menu);
        MenuItem camera = menu.findItem(R.id.tomarFoto);
        camera.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                alertDialogBuilder();
                break;
            case R.id.acerca_de:
                break;
            case R.id.addItemTabla:
                DialogoAddItemFragment dialogoAddItemFragment = new DialogoAddItemFragment();
                dialogoAddItemFragment.show(getSupportFragmentManager(), "DialogAddItem");
                break;
            case R.id.altaProyecto:
                insertSamplingConfirm();
                break;
            default:
                //Error
        }

        return super.onOptionsItemSelected(item);
    }

    public void insertSamplingConfirm() {

        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> listFragments = fm.getFragments();
        int limite;
        EnvironmentSedFragment ambienteSedFragment = new EnvironmentSedFragment();
        VegetationFragment vegetationFragment = new VegetationFragment();


        limite = listFragments.size();
        if (limite > 0){
            vegetationFragment = (VegetationFragment) listFragments.get(1);
        }
        if (limite > 1){
            ambienteSedFragment = (EnvironmentSedFragment) listFragments.get(0);
        }


        //SETEAR EL ID DEL SAMPLING A LO QUE VIENE DE LOS FRAGMENTS
        Muestreos samplingAmbSed = ambienteSedFragment.getSampling();

        Muestreos samplingVegetation = vegetationFragment.getSampling();

        insertSamplingConfirm(samplingAmbSed, samplingVegetation);

    }

    private void insertSamplingConfirm(Muestreos samplingAmb, Muestreos samplingVeg){
        final Bundle extras = getIntent().getExtras();
        Intent replyIntent = new Intent();
        //if (TextUtils.isEmpty(tv_nroMuestreo.getText())) {
        if (samplingAmb.getNumero() < 0){
            // No number was entered, set the result accordingly.
            //setResult(RESULT_CANCELED, replyIntent);
            Toast.makeText(this,R.string.muestreo_obligatorio,Toast.LENGTH_SHORT).show();
        } else {
            replyIntent.putExtra(NUMBER_REPLY_SAMPLING, samplingAmb.getNumero());
            replyIntent.putExtra(DATE_REPLY_SAMPLING, samplingAmb.getFecha_creacion());
            replyIntent.putExtra(AS_DESCRIPTION_REPLY_SAMPLING, samplingAmb.getAs_descripcion());
            List<Long> spinnerSelectedPending = new ArrayList<>();
            if (samplingAmb.getAs_pendiente().size() > 0)
                spinnerSelectedPending = samplingAmb.getAs_pendiente();
            Long pendingSelected = spinnerSelectedPending.get(0);

            replyIntent.putExtra(PENDING_REPLY_SAMPLING, pendingSelected);

            replyIntent.putExtra(SEDIMENT_REPLY_SAMPLING, samplingAmb.getAs_sedimento_tipo());
            replyIntent.putExtra(PD_EROSION_REPLY_SAMPLING, samplingAmb.getAs_pd_erosion());
            replyIntent.putExtra(PD_DEPOSITACION_REPLY_SAMPLING, samplingAmb.getAs_pd_depositacion());
            replyIntent.putExtra(PD_PEDOGENESIS_REPLY_SAMPLING, samplingAmb.getAs_pd_pedogenesis());

            List<Long> spinnerSelectedPotential = new ArrayList<>();
            if (samplingAmb.getAs_potencial_enterramiento().size() > 0)
                spinnerSelectedPotential = samplingAmb.getAs_potencial_enterramiento();
            Long potentialSelected = spinnerSelectedPotential.get(0);

            replyIntent.putExtra(POTENTIAL_REPLY_SAMPLING, potentialSelected);

            replyIntent.putExtra(POTENTIAL_OBS_REPLY_SAMPLING, samplingAmb.getAs_pe_observaciones());
            replyIntent.putExtra(PA_ABSENCE_REPLY_SAMPLING, samplingAmb.getAs_pa_ausencia());
            replyIntent.putExtra(PA_HUMIDITY_REPLY_SAMPLING, samplingAmb.getAs_pa_humedad());
            replyIntent.putExtra(PA_ENCHARCAMIENTO_REPLY_SAMPLING, samplingAmb.getAs_pa_encharcamiento());
            replyIntent.putExtra(PA_ESCORRENTIA_REPLY_SAMPLING, samplingAmb.getAs_pa_escorrentia());
            replyIntent.putExtra(PA_CANAL_REPLY_SAMPLING, samplingAmb.getAs_pa_canal());
            replyIntent.putExtra(PA_OBSERVATIONS_REPLY_SAMPLING, samplingAmb.getAs_pa_observaciones());
            replyIntent.putExtra(VEGET_TIPO_REPLY_SAMPLING, samplingVeg.getVe_tipo());
            replyIntent.putExtra(VEGET_DISTRI_REPLY_SAMPLING, samplingVeg.getVe_distribucion());

            List<Long> spinnerSelectedVisibility = new ArrayList<>();
            if (samplingVeg.getVe_visibilidad().size() > 0)
                spinnerSelectedVisibility = samplingVeg.getVe_visibilidad();
            Long visibilitySelected = spinnerSelectedVisibility.get(0);

            replyIntent.putExtra(VEGET_VISIB_REPLY_SAMPLING, visibilitySelected);

            replyIntent.putExtra(BIO_VEGETABLES_REPLY_SAMPLING, samplingAmb.getBio_vegetales());
            replyIntent.putExtra(BIO_RODENTS_REPLY_SAMPLING, samplingAmb.getBio_roedores());
            replyIntent.putExtra(BIO_PISOTEO_REPLY_SAMPLING, samplingAmb.getBio_pisoteo());
            replyIntent.putExtra(BIO_CARNIVORES_REPLY_SAMPLING, samplingAmb.getBio_carnivoros());
            replyIntent.putExtra(BIO_OTHERS_REPLY_SAMPLING, samplingAmb.getBio_otros());
            replyIntent.putExtra(BIO_DESCRIPCION_REPLY_SAMPLING, samplingAmb.getBio_descripcion());
            replyIntent.putExtra(FAUNA_ACTUAL_REPLY_SAMPLING, samplingVeg.getPresencia_fauna());
            replyIntent.putExtra(AA_GANADERIA_REPLY_SAMPLING, samplingVeg.getAa_ganaderia());
            replyIntent.putExtra(AA_AGRICULTURA_REPLY_SAMPLING, samplingVeg.getAa_agricultura());
            replyIntent.putExtra(AA_ALAMBRADO_REPLY_SAMPLING, samplingVeg.getAa_alambrado());
            replyIntent.putExtra(AA_CONSTRUCTION_REPLY_SAMPLING, samplingVeg.getAa_construccion());
            replyIntent.putExtra(AA_WAY_REPLY_SAMPLING, samplingVeg.getAa_camino());
            replyIntent.putExtra(AA_TERRAPLENES_REPLY_SAMPLING, samplingVeg.getAa_terraplenes());
            replyIntent.putExtra(AA_CANAL_REPLY_SAMPLING, samplingVeg.getAa_canal());
            replyIntent.putExtra(AA_WASTE_REPLY_SAMPLING, samplingVeg.getAa_desechos());
            replyIntent.putExtra(AA_OTHERS_REPLY_SAMPLING, samplingVeg.getAa_otros());
            replyIntent.putExtra(AA_DESCRIPTION_REPLY_SAMPLING, samplingVeg.getAa_descripcion());
            replyIntent.putExtra(PM_LITHIC_REPLY_SAMPLING, samplingVeg.getPm_litico());
            replyIntent.putExtra(PM_FAUNISTICO_REPLY_SAMPLING, samplingVeg.getPm_faunistico());
            replyIntent.putExtra(PM_CERAMIC_REPLY_SAMPLING, samplingVeg.getPm_ceramica());
            replyIntent.putExtra(PM_GRESS_REPLY_SAMPLING, samplingVeg.getPm_gress());
            replyIntent.putExtra(PM_GLASSES_REPLY_SAMPLING, samplingVeg.getPm_vidrio());
            replyIntent.putExtra(PM_OTHERS_REPLY_SAMPLING, samplingVeg.getPm_otros());
            replyIntent.putExtra(PM_OBSERVATIONS_REPLY_SAMPLING, samplingVeg.getPm_observacion());

            if (extras != null && extras.containsKey(SAMPLING_DATA_ID)) {
                long id = extras.getLong(SAMPLING_DATA_ID, -1);
                if (id != -1) {
                    replyIntent.putExtra(SAMPLING_DATA_ID, id);
                }
            }

            int existSampling = mViewModel.existSampling(samplingAmb.getNumero(), idTransect);

            if (existSampling > 0){
                Toast.makeText(this, R.string.muestreo_repetido,
                        Toast.LENGTH_LONG).show();
            }
            else {
                // Set the result status to indicate success.
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            alertDialogBuilder();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void alertDialogBuilder(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Desea guardar los cambios?")
                .setPositiveButton("Si", (dialogInterface, i) -> insertSamplingConfirm())
                .setNegativeButton("No", (dialogInterface, i) -> {
                    Intent replyIntent = new Intent();
                    setResult(RESULT_CANCELED, replyIntent);
                    //dialogInterface.dismiss();
                    finish();
                });
        builder.show();
    }
}