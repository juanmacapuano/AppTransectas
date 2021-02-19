package com.example.transectas.findings;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transectas.AppViewModel;
import com.example.transectas.BaseActivity;
import com.example.transectas.R;
import com.example.transectas.data.Hallazgos;
import com.example.transectas.data.Valores;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import static com.example.transectas.findings.ListFindings.AMBIENTEINMED_UPDATE_HALLAZGO;
import static com.example.transectas.findings.ListFindings.ANALISTA_UPDATE_HALLAZGO;
import static com.example.transectas.findings.ListFindings.CONCCARC_UPDATE_HALLAZGO;
import static com.example.transectas.findings.ListFindings.HALLAZGO_DATA_ID;
import static com.example.transectas.findings.ListFindings.OBSERVACIONES_UPDATE_HALLAZGO;
import static com.example.transectas.findings.ListFindings.OCURRENCIA_UPDATE_HALLAZGO;
import static com.example.transectas.findings.ListFindings.ORIENTACIONAGUA_UPDATE_HALLAZGO;
import static com.example.transectas.findings.ListFindings.POSICION_UPDATE_HALLAZGO;
import static com.example.transectas.findings.ListFindings.TAMANIOTAXON_UPDATE_HALLAZGO;
import static com.example.transectas.findings.ListFindings.TAXON_UPDATE_HALLAZGO;
import static com.example.transectas.findings.ListFindings.TIPOOCURRENCIA1_UPDATE_HALLAZGO;
import static com.example.transectas.findings.ListFindings.TIPOOCURRENCIA2_UPDATE_HALLAZGO;


public class Item_Finding extends BaseActivity {

    private EditText et_ocurrenciaItemHallazgo, et_concCarcItemHallazgo, et_ambienteInmItemHallazgo, et_observaItemHallazgo;
    private TextView tv_transectaItemHallazgo, tv_muestreoItemHallazgo;
    private FloatingActionButton fa_action_element_housing, fa_add_element_housing, fa_add_housing;
    private AppViewModel mViewModel;
    private long itemTipoOc1SelectedItem, itemTipoOc2SelectedItem, itemPosicionSelectedItem, itemOrientacionSelectedItem,
    itemTaxonSelectedItem, itemTaxonTamanioSelectedItem;
    private Spinner sp_tipoOcurr1ItemHallazgo, sp_tipoOcurr2ItemHallazgo, sp_posicionItemHallazgo, sp_orientacionItemHallazgo,
            sp_taxonItemHallazgo, sp_taxonTamItemHallazgo;
    private List<Valores> tipoOc1SpinnerItem, tipoOc2SpinnerItem, posicionSpinnerItem, orientacionSpinnerItem,
            taxonSpinnerItem, taxonTamanioSpinnerItem;
    private long idHallazgoInsert;
    private long MUESTREO_ID = -1;
    private long TRANSECTA_ID = -1;
    private Boolean isFABOpen = false;

    public static final int UPDATE_HALLAZGO_ACTIVITY_REQUEST_CODE = 2;

    public static final String TRANSECTA_UPDATE_HALLAZGO_ITEM = "transecta_hallazgo_to_be_updated";
    public static final String MUESTREO_UPDATE_HALLAZGO_ITEM = "muestreo_hallazgo_to_be_updated";
    public static final String OCURRENCIA_UPDATE_HALLAZGO_ITEM = "ocurrencia_hallazgo_to_be_updated";
    public static final String TIPOOCUR1_UPDATE_HALLAZGO_ITEM = "tipoOcur1_Hallazgo_to_be_updated";
    public static final String TIPOOCUR2_UPDATE_HALLAZGO_ITEM = "tipoOcur2_Hallazgo_to_be_updated";
    public static final String CONCARC_UPDATE_HALLAZGO_ITEM = "conccarc_Hallazgo_to_be_updated";
    public static final String AMBIENTE_UPDATE_HALLAZGO_ITEM = "ambiente_Hallazgo_to_be_updated";
    public static final String POSICION_UPDATE_HALLAZGO_ITEM = "posicion_Hallazgo_to_be_updated";
    public static final String ORIENTACION_UPDATE_HALLAZGO_ITEM = "orientacion_Hallazgo_to_be_updated";
    public static final String TAXON_UPDATE_HALLAZGO_ITEM = "taxon_Hallazgo_to_be_updated";
    public static final String TAXONTAMANIO_UPDATE_HALLAZGO_ITEM = "taxontamanio_Hallazgo_to_be_updated";
    public static final String FOTOS_UPDATE_HALLAZGO_ITEM = "fotos_Hallazgo_to_be_updated";
    public static final String OBSERVACIONES_UPDATE_HALLAZGO_ITEM = "observaciones_Hallazgo_to_be_updated";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__hallazgo);
        mViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        itemTipoOc1SelectedItem = -1;
        itemTipoOc2SelectedItem = -1;
        itemPosicionSelectedItem = -1;
        itemOrientacionSelectedItem = -1;
        itemTaxonSelectedItem = -1;
        itemTaxonTamanioSelectedItem = -1;
        tipoOc1SpinnerItem = null;
        tipoOc2SpinnerItem = null;
        posicionSpinnerItem = null;
        orientacionSpinnerItem = null;
        taxonSpinnerItem = null;
        taxonTamanioSpinnerItem = null;
        idHallazgoInsert = -1;
        setUpToolbar();
        setUpComponentes();

        final Bundle extras = getIntent().getExtras();

        // If we are passed content, fill it in for the user to edit.
        if (extras != null) {
            MUESTREO_ID = extras.getLong(ListFindings.idMuestreo_string);
            TRANSECTA_ID = extras.getLong(ListFindings.transecta_id_string);
            idHallazgoInsert = extras.getLong(HALLAZGO_DATA_ID);
            //El #Ocurrencia será un número
            int ocurrenciaHallazgo = extras.getInt(OCURRENCIA_UPDATE_HALLAZGO, -1);
            long tipoOcur1Hallazgo = extras.getLong(TIPOOCURRENCIA1_UPDATE_HALLAZGO, -1);
            itemTipoOc1SelectedItem = tipoOcur1Hallazgo;
            long tipoOcur2Hallazgo = extras.getLong(TIPOOCURRENCIA2_UPDATE_HALLAZGO, -1);
            itemTipoOc2SelectedItem = tipoOcur2Hallazgo;
            String concCarcHallazgo = extras.getString(CONCCARC_UPDATE_HALLAZGO, "");
            String ambienteHallazgo = extras.getString(AMBIENTEINMED_UPDATE_HALLAZGO, "");
            long posicionHallazgo = extras.getLong(POSICION_UPDATE_HALLAZGO, -1);
            itemPosicionSelectedItem = posicionHallazgo;
            long orientacionHallazgo = extras.getLong(ORIENTACIONAGUA_UPDATE_HALLAZGO, -1);
            itemOrientacionSelectedItem = orientacionHallazgo;
            long taxonHallazgo = extras.getLong(TAXON_UPDATE_HALLAZGO, -1);
            itemTaxonSelectedItem = taxonHallazgo;
            long taxonTamanioHallazgo = extras.getLong(TAMANIOTAXON_UPDATE_HALLAZGO, -1);
            itemTaxonTamanioSelectedItem = taxonTamanioHallazgo;
            String observacionesHallazgo = extras.getString(OBSERVACIONES_UPDATE_HALLAZGO, "");

            String muestreoID_string = String.valueOf(MUESTREO_ID);

            tv_muestreoItemHallazgo.setText(muestreoID_string);

            String ocHallazgoNumbre = Integer.toString(ocurrenciaHallazgo);
            et_ocurrenciaItemHallazgo.setText(ocHallazgoNumbre);
            et_ocurrenciaItemHallazgo.setFocusable(false);
            et_ocurrenciaItemHallazgo.setEnabled(false);
            et_ocurrenciaItemHallazgo.setCursorVisible(false);
            et_ocurrenciaItemHallazgo.setKeyListener(null);
            et_ocurrenciaItemHallazgo.setBackgroundColor(Color.TRANSPARENT);
            Valores item; // = new Valores();
            if (tipoOcur1Hallazgo != -1) {
                for(int i=0; i < tipoOc1SpinnerItem.size(); i++){
                    item = tipoOc1SpinnerItem.get(i);
                    if (item.getId() == tipoOcur1Hallazgo){
                        sp_tipoOcurr1ItemHallazgo.setSelection(i);
                    }
                }
                sp_tipoOcurr1ItemHallazgo.setFocusable(false);
                sp_tipoOcurr1ItemHallazgo.setEnabled(false);
                sp_tipoOcurr1ItemHallazgo.setBackgroundColor(Color.TRANSPARENT);
            }

            if (tipoOcur2Hallazgo != -1) {
                for(int i=0; i < tipoOc2SpinnerItem.size(); i++){
                    item = tipoOc2SpinnerItem.get(i);
                    if (item.getId() == tipoOcur2Hallazgo){
                        sp_tipoOcurr2ItemHallazgo.setSelection(i);
                    }
                }
                sp_tipoOcurr2ItemHallazgo.setFocusable(false);
                sp_tipoOcurr2ItemHallazgo.setEnabled(false);
                sp_tipoOcurr2ItemHallazgo.setBackgroundColor(Color.TRANSPARENT);
            }

            et_concCarcItemHallazgo.setText(concCarcHallazgo);
            et_concCarcItemHallazgo.setFocusable(false);
            et_concCarcItemHallazgo.setEnabled(false);
            et_concCarcItemHallazgo.setCursorVisible(false);
            et_concCarcItemHallazgo.setKeyListener(null);
            et_concCarcItemHallazgo.setBackgroundColor(Color.TRANSPARENT);

            et_ambienteInmItemHallazgo.setText(ambienteHallazgo);
            et_ambienteInmItemHallazgo.setFocusable(false);
            et_ambienteInmItemHallazgo.setEnabled(false);
            et_ambienteInmItemHallazgo.setCursorVisible(false);
            et_ambienteInmItemHallazgo.setKeyListener(null);
            et_ambienteInmItemHallazgo.setBackgroundColor(Color.TRANSPARENT);

            if (posicionHallazgo != -1) {
                for(int i=0; i < posicionSpinnerItem.size(); i++){
                    item = posicionSpinnerItem.get(i);
                    if (item.getId() == posicionHallazgo){
                        sp_posicionItemHallazgo.setSelection(i);
                    }
                }
                sp_posicionItemHallazgo.setFocusable(false);
                sp_posicionItemHallazgo.setEnabled(false);
                sp_posicionItemHallazgo.setBackgroundColor(Color.TRANSPARENT);
            }

            if (orientacionHallazgo != -1) {
                for(int i=0; i < orientacionSpinnerItem.size(); i++){
                    item = orientacionSpinnerItem.get(i);
                    if (item.getId() == orientacionHallazgo){
                        sp_orientacionItemHallazgo.setSelection(i);
                    }
                }
                sp_orientacionItemHallazgo.setFocusable(false);
                sp_orientacionItemHallazgo.setEnabled(false);
                sp_orientacionItemHallazgo.setBackgroundColor(Color.TRANSPARENT);
            }

            if (taxonHallazgo != -1) {
                for(int i=0; i < taxonSpinnerItem.size(); i++){
                    item = taxonSpinnerItem.get(i);
                    if (item.getId() == taxonHallazgo){
                        sp_taxonItemHallazgo.setSelection(i);
                    }
                }
                sp_taxonItemHallazgo.setFocusable(false);
                sp_taxonItemHallazgo.setEnabled(false);
                sp_taxonItemHallazgo.setBackgroundColor(Color.TRANSPARENT);
            }

            if (taxonTamanioHallazgo != -1) {
                for(int i=0; i < taxonTamanioSpinnerItem.size(); i++){
                    item = taxonTamanioSpinnerItem.get(i);
                    if (item.getId() == taxonTamanioHallazgo){
                        sp_taxonTamItemHallazgo.setSelection(i);
                    }
                }
                sp_taxonTamItemHallazgo.setFocusable(false);
                sp_taxonTamItemHallazgo.setEnabled(false);
                sp_taxonTamItemHallazgo.setBackgroundColor(Color.TRANSPARENT);
            }

            et_observaItemHallazgo.setText(observacionesHallazgo);
            et_observaItemHallazgo.setFocusable(false);
            et_observaItemHallazgo.setEnabled(false);
            et_observaItemHallazgo.setCursorVisible(false);
            et_observaItemHallazgo.setKeyListener(null);
            et_observaItemHallazgo.setBackgroundColor(Color.TRANSPARENT);

        } // Otherwise, start with empty fields.

    }

    private void setUpComponentes() {
        tv_muestreoItemHallazgo = findViewById(R.id.tv_muestreoItemHallazgo);
        tv_transectaItemHallazgo = findViewById(R.id.tv_transectaItemHallazgo);
        et_ocurrenciaItemHallazgo = findViewById(R.id.et_ocurrenciaItemHallazgo);
        sp_tipoOcurr1ItemHallazgo = findViewById(R.id.sp_tipoOcurr1ItemHallazgo);
        sp_tipoOcurr2ItemHallazgo = findViewById(R.id.sp_tipoOcurr2ItemHallazgo);
        et_concCarcItemHallazgo = findViewById(R.id.et_concCarcItemHallazgo);
        et_ambienteInmItemHallazgo = findViewById(R.id.et_ambienteInmItemHallazgo);
        sp_posicionItemHallazgo = findViewById(R.id.sp_posicionItemHallazgo);
        sp_orientacionItemHallazgo = findViewById(R.id.sp_orientacionItemHallazgo);
        sp_taxonItemHallazgo = findViewById(R.id.sp_taxonItemHallazgo);
        sp_taxonTamItemHallazgo = findViewById(R.id.sp_taxonTamItemHallazgo);
        et_observaItemHallazgo = findViewById(R.id.et_observaItemHallazgo);

        fa_action_element_housing = findViewById(R.id.fa_action_element_housing);
        fa_add_element_housing = findViewById(R.id.fa_add_element_housing);
        fa_add_housing = findViewById(R.id.fa_add_housing);

        fa_action_element_housing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });

        cargarSpinners();

    }

    private void showFABMenu(){
        isFABOpen=true;
        fa_add_element_housing.animate().translationY(getResources().getDimension(R.dimen.standard_105));
        fa_add_housing.animate().translationY(getResources().getDimension(R.dimen.standard_55));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fa_add_element_housing.animate().translationY(0);
        fa_add_housing.animate().translationY(0);
    }

    private void cargarSpinners() {
        tipoOc1SpinnerItem = mViewModel.getAllTipoOcur1HallazgoList();
        sp_tipoOcurr1ItemHallazgo.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner,tipoOc1SpinnerItem));

        tipoOc2SpinnerItem = mViewModel.getAllTipoOcur2HallazgoList();
        sp_tipoOcurr2ItemHallazgo.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner,tipoOc2SpinnerItem));

        posicionSpinnerItem = mViewModel.getAllPosicionHallazgoList();
        sp_posicionItemHallazgo.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner,posicionSpinnerItem));

        orientacionSpinnerItem = mViewModel.getAllOrientacionHallazgoList();
        sp_orientacionItemHallazgo.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner,orientacionSpinnerItem));

        taxonSpinnerItem = mViewModel.getAllTaxonHallazgoList();
        sp_taxonItemHallazgo.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner,taxonSpinnerItem));

        taxonTamanioSpinnerItem = mViewModel.getAllTaxonTamanioHallazgoList();
        sp_taxonTamItemHallazgo.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner,taxonTamanioSpinnerItem));

    }

    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showHomeUpIcon(getString(R.string.findingItem));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem confirmItem = menu.findItem(R.id.idAceptarEdicion);
        confirmItem.setVisible(false);
        MenuItem searchItem = menu.findItem(R.id.new_search);
        searchItem.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.editItem:
                Intent intent = new Intent(Item_Finding.this, New_Finding.class);

                String ocurrencia = et_ocurrenciaItemHallazgo.getText().toString();
                String concCarcItem = et_concCarcItemHallazgo.getText().toString();
                String ambiente = et_ambienteInmItemHallazgo.getText().toString();
                String observacion = et_observaItemHallazgo.getText().toString();

                intent.putExtra(MUESTREO_UPDATE_HALLAZGO_ITEM, MUESTREO_ID);
                intent.putExtra(OCURRENCIA_UPDATE_HALLAZGO_ITEM, Integer.parseInt(ocurrencia));
                intent.putExtra(TIPOOCUR1_UPDATE_HALLAZGO_ITEM, itemTipoOc1SelectedItem);
                intent.putExtra(TIPOOCUR2_UPDATE_HALLAZGO_ITEM, itemTipoOc2SelectedItem);
                intent.putExtra(CONCARC_UPDATE_HALLAZGO_ITEM, concCarcItem);
                intent.putExtra(AMBIENTE_UPDATE_HALLAZGO_ITEM, ambiente);
                intent.putExtra(POSICION_UPDATE_HALLAZGO_ITEM, itemPosicionSelectedItem);
                intent.putExtra(ORIENTACION_UPDATE_HALLAZGO_ITEM, itemOrientacionSelectedItem);
                intent.putExtra(TAXON_UPDATE_HALLAZGO_ITEM, itemTaxonSelectedItem);
                intent.putExtra(TAXONTAMANIO_UPDATE_HALLAZGO_ITEM, itemTaxonTamanioSelectedItem);
                intent.putExtra(OBSERVACIONES_UPDATE_HALLAZGO_ITEM, observacion);
                intent.putExtra(HALLAZGO_DATA_ID, idHallazgoInsert);
               // intent.putExtra(ANALISTA_UPDATE_HALLAZGO_ITEM, et_observacionesTransectaItem.getText().toString());
                startActivityForResult(intent, UPDATE_HALLAZGO_ACTIVITY_REQUEST_CODE);
                break;
            case android.R.id.home:
                finish();
                break;
            case R.id.acerca_de:
                about(this);
                break;
            default:
                //Error
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPDATE_HALLAZGO_ACTIVITY_REQUEST_CODE
                && resultCode == RESULT_OK) {

            String ocurrenciaHallazgo= data.getStringExtra(New_Finding.OCURRENCIA_REPLY_HALLAZGO);
            assert ocurrenciaHallazgo != null;
            Integer ocurrencia_data = Integer.parseInt(ocurrenciaHallazgo);
            long tipoOcur1_data = data.getLongExtra(New_Finding.TIPOOCUR1_REPLY_HALLAZGO, -1);
            List<Long> tipoOcur1 = new ArrayList<>();
            if (tipoOcur1_data != -1){
                tipoOcur1.add(tipoOcur1_data);
            }
            long tipoOcur2_data = data.getLongExtra(New_Finding.TIPOOCUR2_REPLY_HALLAZGO, -1);
            List<Long> tipoOcur2 = new ArrayList<>();
            if (tipoOcur2_data != -1){
                tipoOcur2.add(tipoOcur2_data);
            }

            String conCarc_data = data.getStringExtra(New_Finding.CONCARC_REPLY_HALLAZGO);
            String ambiente_data = data.getStringExtra(New_Finding.AMBIENTE_REPLY_HALLAZGO);
            long posicion_data = data.getLongExtra(New_Finding.POSICION_REPLY_HALLAZGO, -1);
            List<Long> posicion = new ArrayList<>();
            if (posicion_data != -1){
                posicion.add(posicion_data);
            }
            long orientacion_data = data.getLongExtra(New_Finding.ORIENTACION_REPLY_HALLAZGO, -1);
            List<Long> orientacion = new ArrayList<>();
            if (orientacion_data != -1){
                orientacion.add(orientacion_data);
            }
            long taxon_data = data.getLongExtra(New_Finding.TAXON_REPLY_HALLAZGO, -1);
            List<Long> taxon = new ArrayList<>();
            if (taxon_data != -1){
                taxon.add(taxon_data);
            }
            long taxonTamanio_data = data.getLongExtra(New_Finding.TAXONTAMANIO_REPLY_HALLAZGO, -1);
            List<Long> taxonTamanio = new ArrayList<>();
            if (taxonTamanio_data != -1){
                taxonTamanio.add(taxonTamanio_data);
            }
            String observaciones_data = data.getStringExtra(New_Finding.OBSERVACIONES_REPLY_HALLAZGO);

            long idHallazgoUpdate = data.getLongExtra(HALLAZGO_DATA_ID, -1);
            idHallazgoInsert = idHallazgoUpdate;
            if (idHallazgoUpdate != -1) {
                mViewModel.updateFinding(new Hallazgos(idHallazgoUpdate, ocurrencia_data, tipoOcur1, tipoOcur2,
                        conCarc_data, ambiente_data, posicion, orientacion, taxon, taxonTamanio, null, "",
                        observaciones_data, MUESTREO_ID));

                Intent intent = new Intent(Item_Finding.this, Item_Finding.class);
                intent.putExtra(OCURRENCIA_UPDATE_HALLAZGO, ocurrencia_data);
                intent.putExtra(TIPOOCURRENCIA1_UPDATE_HALLAZGO, tipoOcur1_data);
                intent.putExtra(TIPOOCURRENCIA2_UPDATE_HALLAZGO, tipoOcur2_data);
                intent.putExtra(CONCCARC_UPDATE_HALLAZGO, conCarc_data);
                intent.putExtra(AMBIENTEINMED_UPDATE_HALLAZGO, ambiente_data);
                intent.putExtra(POSICION_UPDATE_HALLAZGO, posicion_data);
                intent.putExtra(ORIENTACIONAGUA_UPDATE_HALLAZGO, orientacion_data);
                intent.putExtra(TAXON_UPDATE_HALLAZGO, taxon_data);
                intent.putExtra(TAMANIOTAXON_UPDATE_HALLAZGO, taxonTamanio_data);
                intent.putExtra(ANALISTA_UPDATE_HALLAZGO, "");
                intent.putExtra(OBSERVACIONES_UPDATE_HALLAZGO, observaciones_data);
                intent.putExtra(HALLAZGO_DATA_ID, idHallazgoUpdate);
                finish();
                startActivity(intent);

                Toast.makeText(this, R.string.hallazgo_saved,
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.unable_to_update_hallazgo,
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(
                    this, R.string.sin_cambios, Toast.LENGTH_LONG).show();
        }
    }

}