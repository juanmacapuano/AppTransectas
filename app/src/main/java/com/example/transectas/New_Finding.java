package com.example.transectas;

import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transectas.data.Valores;
import com.example.transectas.dialogos.DialogoAddItemFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import static com.example.transectas.Add_Transect.TRANSECTA_DATA_ID;
import static com.example.transectas.Item_Finding.AMBIENTE_UPDATE_HALLAZGO_ITEM;
import static com.example.transectas.Item_Finding.CONCARC_UPDATE_HALLAZGO_ITEM;
import static com.example.transectas.Item_Finding.FOTOS_UPDATE_HALLAZGO_ITEM;
import static com.example.transectas.Item_Finding.UPDATE_HALLAZGO_ACTIVITY_REQUEST_CODE;
import static com.example.transectas.ListFindings.HALLAZGO_DATA_ID;
import static com.example.transectas.Item_Finding.OBSERVACIONES_UPDATE_HALLAZGO_ITEM;
import static com.example.transectas.Item_Finding.OCURRENCIA_UPDATE_HALLAZGO_ITEM;
import static com.example.transectas.Item_Finding.ORIENTACION_UPDATE_HALLAZGO_ITEM;
import static com.example.transectas.Item_Finding.POSICION_UPDATE_HALLAZGO_ITEM;
import static com.example.transectas.Item_Finding.TAXONTAMANIO_UPDATE_HALLAZGO_ITEM;
import static com.example.transectas.Item_Finding.TAXON_UPDATE_HALLAZGO_ITEM;
import static com.example.transectas.Item_Finding.TIPOOCUR1_UPDATE_HALLAZGO_ITEM;
import static com.example.transectas.Item_Finding.TIPOOCUR2_UPDATE_HALLAZGO_ITEM;
import static com.example.transectas.Item_Finding.TRANSECTA_UPDATE_HALLAZGO_ITEM;
import static com.example.transectas.Item_Finding.MUESTREO_UPDATE_HALLAZGO_ITEM;


public class New_Finding extends BaseActivity {

    private EditText et_ocurrenciaItemHallazgo, et_concCarcItemHallazgo, et_ambienteInmItemHallazgo, et_observaItemHallazgo;
    private TextView tv_transectaItemHallazgo, tv_muestreoItemHallazgo;
    private AppViewModel mViewModel;
    private long itemTipoOc1SelectedItem, itemTipoOc2SelectedItem, itemPosicionSelectedItem, itemOrientacionSelectedItem,
    itemTaxonSelectedItem, itemTaxonTamanioSelectedItem;
    private Spinner sp_tipoOcurr1ItemHallazgo, sp_tipoOcurr2ItemHallazgo, sp_posicionItemHallazgo, sp_orientacionItemHallazgo,
            sp_taxonItemHallazgo, sp_taxonTamItemHallazgo;
    private List<Valores> tipoOc1SpinnerItem, tipoOc2SpinnerItem, posicionSpinnerItem, orientacionSpinnerItem,
            taxonSpinnerItem, taxonTamanioSpinnerItem;
    private Valores itemTipoOcur1Selected, itemTipoOcur2Selected, itemPosicionSelected, itemOrientacionSelected,
            itemTaxonSelected, itemTaxonTamanioSelected;
    private long idHallazgoInsert;
    private long MUESTREO_ID = -1;
    private int newHallazgo = -1;

    public static final String TRANSECTA_REPLY_HALLAZGO = "com.example.hallazgo.TRANSECTA_REPLY_HALLAZGO";
    public static final String MUESTREO_REPLY_HALLAZGO = "com.example.hallazgo.MUESTREO_REPLY_HALLAZGO";
    public static final String NUMERO_REPLY_HALLAZGO = "com.example.hallazgo.NUMERO_REPLY_HALLAZGO";
    public static final String OCURRENCIA_REPLY_HALLAZGO = "com.example.hallazgo.OCURRENCIA_REPLY_HALLAZGO";
    public static final String TIPOOCUR1_REPLY_HALLAZGO = "com.example.hallazgo.TIPOOCUR1_REPLY_HALLAZGO";
    public static final String TIPOOCUR2_REPLY_HALLAZGO = "com.example.hallazgo.TIPOOCUR2_REPLY_HALLAZGO";
    public static final String CONCARC_REPLY_HALLAZGO = "com.example.hallazgo.CONCARC_REPLY_HALLAZGO";
    public static final String AMBIENTE_REPLY_HALLAZGO = "com.example.hallazgo.AMBIENTE_REPLY_HALLAZGO";
    public static final String POSICION_REPLY_HALLAZGO = "com.example.hallazgo.POSICION_REPLY_HALLAZGO";
    public static final String ORIENTACION_REPLY_HALLAZGO = "com.example.hallazgo.ORIENTACION_REPLY_HALLAZGO";
    public static final String TAXON_REPLY_HALLAZGO = "com.example.hallazgo.TAXON_REPLY_HALLAZGO";
    public static final String TAXONTAMANIO_REPLY_HALLAZGO = "com.example.hallazgo.TAXONTAMANIO_REPLY_HALLAZGO";
    public static final String OBSERVACIONES_REPLY_HALLAZGO = "com.example.hallazgo.OBSERVACIONES_REPLY_HALLAZGO";


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
            long idProyecto = extras.getLong("proyectoId");
            MUESTREO_ID = extras.getLong(MUESTREO_UPDATE_HALLAZGO_ITEM);
            long TRANSECTA_ID = extras.getLong(TRANSECTA_UPDATE_HALLAZGO_ITEM);
            newHallazgo = extras.getInt("newHallazgo");
            idHallazgoInsert = extras.getLong(HALLAZGO_DATA_ID);
            //El #Ocurrencia será un número
            int ocurrenciaHallazgo = extras.getInt(OCURRENCIA_UPDATE_HALLAZGO_ITEM, -1);
            long tipoOcur1Hallazgo = extras.getLong(TIPOOCUR1_UPDATE_HALLAZGO_ITEM, -1);
            long tipoOcur2Hallazgo = extras.getLong(TIPOOCUR2_UPDATE_HALLAZGO_ITEM, -1);
            String concCarcHallazgo = extras.getString(CONCARC_UPDATE_HALLAZGO_ITEM, "");
            String ambienteHallazgo = extras.getString(AMBIENTE_UPDATE_HALLAZGO_ITEM, "");
            long posicionHallazgo = extras.getLong(POSICION_UPDATE_HALLAZGO_ITEM, -1);
            long orientacionHallazgo = extras.getLong(ORIENTACION_UPDATE_HALLAZGO_ITEM, -1);
            long taxonHallazgo = extras.getLong(TAXON_UPDATE_HALLAZGO_ITEM, -1);
            long taxonTamanioHallazgo = extras.getLong(TAXONTAMANIO_UPDATE_HALLAZGO_ITEM, -1);
            String observacionesHallazgo = extras.getString(OBSERVACIONES_UPDATE_HALLAZGO_ITEM, "");

            tv_muestreoItemHallazgo.setText(String.valueOf(MUESTREO_ID));

            String ocHallazgoNumbre = Integer.toString(ocurrenciaHallazgo);
            et_ocurrenciaItemHallazgo.setText(ocHallazgoNumbre);

            Valores item;// = new Valores();
            if (tipoOcur1Hallazgo != -1) {
                for(int i=0; i < tipoOc1SpinnerItem.size(); i++){
                    item = tipoOc1SpinnerItem.get(i);
                    if (item.getId() == tipoOcur1Hallazgo){
                        sp_tipoOcurr1ItemHallazgo.setSelection(i);
                    }
                }
            }

            if (tipoOcur2Hallazgo != -1) {
                for(int i=0; i < tipoOc2SpinnerItem.size(); i++){
                    item = tipoOc2SpinnerItem.get(i);
                    if (item.getId() == tipoOcur2Hallazgo){
                        sp_tipoOcurr2ItemHallazgo.setSelection(i);
                    }
                }
            }
            if (!concCarcHallazgo.isEmpty())    et_concCarcItemHallazgo.setText(concCarcHallazgo);

            if (!ambienteHallazgo.isEmpty())    et_ambienteInmItemHallazgo.setText(ambienteHallazgo);

            if (posicionHallazgo != -1) {
                for(int i=0; i < posicionSpinnerItem.size(); i++){
                    item = posicionSpinnerItem.get(i);
                    if (item.getId() == posicionHallazgo){
                        sp_posicionItemHallazgo.setSelection(i);
                    }
                }
            }

            if (orientacionHallazgo != -1) {
                for(int i=0; i < orientacionSpinnerItem.size(); i++){
                    item = orientacionSpinnerItem.get(i);
                    if (item.getId() == orientacionHallazgo){
                        sp_orientacionItemHallazgo.setSelection(i);
                    }
                }
            }

            if (taxonHallazgo != -1) {
                for(int i=0; i < taxonSpinnerItem.size(); i++){
                    item = taxonSpinnerItem.get(i);
                    if (item.getId() == taxonHallazgo){
                        sp_taxonItemHallazgo.setSelection(i);
                    }
                }
            }

            if (taxonTamanioHallazgo != -1) {
                for(int i=0; i < taxonTamanioSpinnerItem.size(); i++){
                    item = taxonTamanioSpinnerItem.get(i);
                    if (item.getId() == taxonTamanioHallazgo){
                        sp_taxonTamItemHallazgo.setSelection(i);
                    }
                }
            }
            if (!observacionesHallazgo.isEmpty())    et_observaItemHallazgo.setText(observacionesHallazgo);

        } // Otherwise, start with empty fields.

        if (newHallazgo > 0) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(getString(R.string.findingNew));
            }
        }

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

        cargarSpinners();

    }

    private void cargarSpinners() {

        sp_tipoOcurr1ItemHallazgo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemTipoOcur1Selected = (Valores) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_tipoOcurr2ItemHallazgo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemTipoOcur2Selected = (Valores) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_orientacionItemHallazgo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemOrientacionSelected = (Valores) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_posicionItemHallazgo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemPosicionSelected = (Valores) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_taxonItemHallazgo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemTaxonSelected = (Valores) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_taxonTamItemHallazgo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemTaxonTamanioSelected = (Valores) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
        showHomeUpIcon(getString(R.string.findingEdit));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cancelar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                alertDialogBuilder();
                break;
            case R.id.addItemTabla:
                DialogoAddItemFragment dialogoAddItemFragment = new DialogoAddItemFragment();
                dialogoAddItemFragment.show(getSupportFragmentManager(), "DialogoAddItem");
                break;
            case R.id.altaProyecto:
                insertarHallazgoConfirm();
                break;
            case R.id.editItem:
                Intent intent = new Intent(New_Finding.this, New_Finding.class);

                intent.putExtra(TRANSECTA_UPDATE_HALLAZGO_ITEM, tv_transectaItemHallazgo.getText());
                intent.putExtra(OCURRENCIA_UPDATE_HALLAZGO_ITEM, et_ocurrenciaItemHallazgo.getText().toString());
                intent.putExtra(TIPOOCUR1_UPDATE_HALLAZGO_ITEM, itemTipoOc1SelectedItem);
                intent.putExtra(TIPOOCUR2_UPDATE_HALLAZGO_ITEM, itemTipoOc2SelectedItem);
                intent.putExtra(CONCARC_UPDATE_HALLAZGO_ITEM, et_concCarcItemHallazgo.getText().toString());
                intent.putExtra(AMBIENTE_UPDATE_HALLAZGO_ITEM, et_ambienteInmItemHallazgo.getText().toString());
                intent.putExtra(POSICION_UPDATE_HALLAZGO_ITEM, itemPosicionSelectedItem);
                intent.putExtra(ORIENTACION_UPDATE_HALLAZGO_ITEM, itemOrientacionSelectedItem);
                intent.putExtra(TAXON_UPDATE_HALLAZGO_ITEM, itemTaxonSelectedItem);
                intent.putExtra(TAXONTAMANIO_UPDATE_HALLAZGO_ITEM, itemTaxonTamanioSelectedItem);
                intent.putExtra(OBSERVACIONES_UPDATE_HALLAZGO_ITEM, et_observaItemHallazgo.getText().toString());
                intent.putExtra(HALLAZGO_DATA_ID, idHallazgoInsert);
               // intent.putExtra(ANALISTA_UPDATE_HALLAZGO_ITEM, et_observacionesTransectaItem.getText().toString());
                startActivityForResult(intent, UPDATE_HALLAZGO_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.acerca_de:
                about(this);
                break;
            default:
                //Error
        }

        return super.onOptionsItemSelected(item);
    }

    public void insertarHallazgoConfirm(){
        final Bundle extras = getIntent().getExtras();
        Intent replyIntent = new Intent();
        if (TextUtils.isEmpty(et_ocurrenciaItemHallazgo.getText())){
            Toast.makeText(this, R.string.hallazgo_obligatorio, Toast.LENGTH_SHORT).show();
            et_ocurrenciaItemHallazgo.setFocusable(true);
        } else {
            long idSpinnerTipoOcur1 = -1;
            if (itemTipoOcur1Selected != null) {
                idSpinnerTipoOcur1 = itemTipoOcur1Selected.getId();
            }
            long idSpinnerTipoOcur2 = -1;
            if (itemTipoOcur2Selected != null) {
                idSpinnerTipoOcur2 = itemTipoOcur2Selected.getId();
            }
            long idSpinnerPosicion = -1;
            if (itemPosicionSelected != null) {
                idSpinnerPosicion = itemPosicionSelected.getId();
            }
            long idSpinnerOrientacion = -1;
            if (itemOrientacionSelected != null) {
                idSpinnerOrientacion = itemOrientacionSelected.getId();
            }
            long idSpinnerTaxon = -1;
            if (itemTaxonSelected != null) {
                idSpinnerTaxon = itemTaxonSelected.getId();
            }
            long idSpinnerTaxonTamanio = -1;
            if (itemTaxonTamanioSelected != null) {
                idSpinnerTaxonTamanio = itemTaxonTamanioSelected.getId();
            }
            String transectaItem = tv_transectaItemHallazgo.getText().toString();
            String ocurrencia = et_ocurrenciaItemHallazgo.getText().toString();
            String concCarcItem = et_concCarcItemHallazgo.getText().toString();
            String ambiente = et_ambienteInmItemHallazgo.getText().toString();
            String observacion = et_observaItemHallazgo.getText().toString();

            replyIntent.putExtra(TRANSECTA_REPLY_HALLAZGO, transectaItem);
            replyIntent.putExtra(OCURRENCIA_REPLY_HALLAZGO, ocurrencia);
            replyIntent.putExtra(TIPOOCUR1_REPLY_HALLAZGO, idSpinnerTipoOcur1);
            replyIntent.putExtra(TIPOOCUR2_REPLY_HALLAZGO, idSpinnerTipoOcur2);
            replyIntent.putExtra(POSICION_REPLY_HALLAZGO, idSpinnerPosicion);
            replyIntent.putExtra(ORIENTACION_REPLY_HALLAZGO, idSpinnerOrientacion);
            replyIntent.putExtra(TAXON_REPLY_HALLAZGO, idSpinnerTaxon);
            replyIntent.putExtra(TAXONTAMANIO_REPLY_HALLAZGO, idSpinnerTaxonTamanio);
            replyIntent.putExtra(CONCARC_REPLY_HALLAZGO, concCarcItem);
            replyIntent.putExtra(AMBIENTE_REPLY_HALLAZGO, ambiente);
            replyIntent.putExtra(OBSERVACIONES_REPLY_HALLAZGO, observacion);

            if (extras != null && extras.containsKey(HALLAZGO_DATA_ID)) {
                long idHallazgo = extras.getLong(HALLAZGO_DATA_ID, -1);
                if (idHallazgo != -1) {
                    replyIntent.putExtra(HALLAZGO_DATA_ID, idHallazgo);
                }
            }

            //comprueba que no exista el número de hallazgo

            int existeHallazgo = -1;
            //TO DO
            //existeHallazgo = mViewModel.existHallazgo(Integer.parseInt(et_ocurrenciaItemHallazgo.toString()), MUESTREO_ID);
            //si devuelve un valor mayor de cero, es que existe un numero de hallazgo. No se puede insertar
            if (existeHallazgo > 0){
                Toast.makeText(this, R.string.hallazgo_repetido,
                        Toast.LENGTH_LONG).show();
            }
            else {
                // Set the result status to indicate success.
                setResult(RESULT_OK, replyIntent);
                finish();
            }


        }
    }

    private void alertDialogBuilder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Desea guardar los cambios?")
                .setPositiveButton("Si", (dialogInterface, i) -> {
                    if (!TextUtils.isEmpty(et_ocurrenciaItemHallazgo.getText())) {
                        insertarHallazgoConfirm();
                    }
                })
                .setNegativeButton("No", (dialogInterface, i) -> {
                    Intent replyIntent = new Intent();
                    setResult(RESULT_CANCELED, replyIntent);
                    dialogInterface.dismiss();
                    finish();
                });
        builder.show();
    }

}