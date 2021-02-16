package com.example.transectas.transects;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.transectas.AppViewModel;
import com.example.transectas.BaseActivity;
import com.example.transectas.samplings.ListSamplings;
import com.example.transectas.R;
import com.example.transectas.data.Transectas;
import com.example.transectas.data.Valores;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import static com.example.transectas.transects.Add_Transect.WIDTH_UPDATE_TRANSECT;
import static com.example.transectas.transects.Add_Transect.DESCRIPTION_UPDATE_TRANSECT;
import static com.example.transectas.transects.Add_Transect.DATE_UPDATE_TRANSECT;
import static com.example.transectas.transects.Add_Transect.GPS_DESTINATION_UPDATE_TRANSECT;
import static com.example.transectas.transects.Add_Transect.GPS_DESTINATIONS_UPDATE_TRANSECT;
import static com.example.transectas.transects.Add_Transect.GPS_ORIGIN_UPDATE_TRANSECT;
import static com.example.transectas.transects.Add_Transect.GPS_ORIGINS_UPDATE_TRANSECT;
import static com.example.transectas.transects.Add_Transect.LONGITUDE_UPDATE_TRANSECT;
import static com.example.transectas.transects.Add_Transect.NUMBER_UPDATE_TRANSECT;
import static com.example.transectas.transects.Add_Transect.OPERATOR_UPDATE_TRANSECT;
import static com.example.transectas.transects.Add_Transect.CONTEXTAMB_UPDATE_TRANSECT;
import static com.example.transectas.transects.Add_Transect.COURSE_UPDATE_TRANSECT;
import static com.example.transectas.transects.Add_Transect.OBSERVATIONS_UPDATE_TRANSECT;
import static com.example.transectas.transects.Add_Transect.TRANSECTA_DATA_ID;
import static com.example.transectas.transects.Add_Transect.PHOTOS_UPDATE_TRANSECT;

public class Item_Transect extends BaseActivity {

    private EditText et_numberTransectItem, et_descriptionTransectItem,
            et_originGpsItemTransect, et_originGpsItemTransectS, et_destinationGpsItemTransect, et_destinationGpsItemTransectS,
            et_courseTransectItem, et_longitudeTransectItem, et_widthTransectItem, d_dateTransectItem, et_observationTransectItem;
    private Spinner sp_operatorItem, sp_contextAbmItem;
    private AppViewModel mViewModel;
    private long itemSpinnerSelectedItem, itemContextSelectedItem;
    private List<Valores> operatorsSpinnerItem, contextAbmSpinnerItem;
    private long idTransectInsert;
    private long idProject = -1;
    private List<String> photosTransect = null;

    public static final int UPDATE_TRANSECT_ACTIVITY_REQUEST_CODE = 2;

    public static final String NUMBER_UPDATE_TRANSECT_ITEM = "number_transect_to_be_updated";
    public static final String DATE_UPDATE_TRANSECT_ITEM = "date_transect_to_be_updated";
    public static final String DESCRIPTION_UPDATE_TRANSECT_ITEM = "description_transect_to_be_updated";
    public static final String GPS_ORIGIN_UPDATE_TRANSECT_ITEM = "gps_origin_transect_to_be_updated";
    public static final String GPS_ORIGINS_UPDATE_TRANSECT_ITEM = "gps_origins_transect_to_be_updated";
    public static final String GPS_DESTINATION_UPDATE_TRANSECT_ITEM = "gps_destination_transect_to_be_updated";
    public static final String GPS_DESTINATIONS_UPDATE_TRANSECT_ITEM = "gps_destinations_transect_to_be_updated";
    public static final String COURSE_UPDATE_TRANSECT_ITEM = "course_transect_to_be_updated";
    public static final String LONGITUDE_UPDATE_TRANSECT_ITEM = "longitude_transect_to_be_updated";
    public static final String WIDTH_UPDATE_TRANSECT_ITEM = "width_transect_to_be_updated";
    public static final String OBSERVATIONS_UPDATE_TRANSECT_ITEM = "observations_transect_to_be_updated";
    public static final String OPERATOR_UPDATE_TRANSECT_ITEM = "operator_transect_to_be_updated";
    public static final String CONTEXT_AMB_UPDATE_TRANSECT_ITEM = "contextAmb_transect_to_be_updated";
    public static final String PROJECT_UPDATE_TRANSECT_ITEM = "project_transect_to_be_updated";
    public static final String PHOTOS_UPDATE_TRANSECT_ITEM = "photos_transect_to_be_updated";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__transecta);
        mViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        itemSpinnerSelectedItem = -1;
        itemContextSelectedItem = -1;
        operatorsSpinnerItem = null;
        contextAbmSpinnerItem = null;
        idTransectInsert = -1;
        setUpToolbar();
        setUpComponents();

        final Bundle extras = getIntent().getExtras();

        // If we are passed content, fill it in for the user to edit.
        if (extras != null) {
            idProject = extras.getLong("projectId");
            idTransectInsert = extras.getLong(TRANSECTA_DATA_ID);
            int numberTransact = extras.getInt(NUMBER_UPDATE_TRANSECT, -1);
            String dateTransect = extras.getString(DATE_UPDATE_TRANSECT, "");
            String descriptionTransect = extras.getString(DESCRIPTION_UPDATE_TRANSECT, "");
            String gpsOriginTransect = extras.getString(GPS_ORIGIN_UPDATE_TRANSECT, "");
            String gpsOriginTransectS = extras.getString(GPS_ORIGINS_UPDATE_TRANSECT, "");
            String gpsDestinationTransect = extras.getString(GPS_DESTINATION_UPDATE_TRANSECT, "");
            String gpsDestinationTransectS = extras.getString(GPS_DESTINATIONS_UPDATE_TRANSECT, "");
            String courseTransect = extras.getString(COURSE_UPDATE_TRANSECT, "");
            String longitudeTransect = extras.getString(LONGITUDE_UPDATE_TRANSECT, "");
            String widthTransect = extras.getString(WIDTH_UPDATE_TRANSECT, "");
            String observationsTransect = extras.getString(OBSERVATIONS_UPDATE_TRANSECT, "");
            long operatorTransect = extras.getLong(OPERATOR_UPDATE_TRANSECT, -1);
            itemSpinnerSelectedItem = operatorTransect;
            long contextAmbTransect = extras.getLong(CONTEXTAMB_UPDATE_TRANSECT, -1);
            photosTransect = extras.getStringArrayList(PHOTOS_UPDATE_TRANSECT);
            itemContextSelectedItem = contextAmbTransect;
            et_numberTransectItem.setText(Integer.toString(numberTransact));
            et_numberTransectItem.setFocusable(false);
            et_numberTransectItem.setEnabled(false);
            et_numberTransectItem.setCursorVisible(false);
            et_numberTransectItem.setKeyListener(null);
            et_numberTransectItem.setBackgroundColor(Color.TRANSPARENT);

            d_dateTransectItem.setText(dateTransect);
            d_dateTransectItem.setFocusable(false);
            d_dateTransectItem.setEnabled(false);
            d_dateTransectItem.setCursorVisible(false);
            d_dateTransectItem.setKeyListener(null);
            d_dateTransectItem.setBackgroundColor(Color.TRANSPARENT);

            et_descriptionTransectItem.setText(descriptionTransect);
            et_descriptionTransectItem.setFocusable(false);
            et_descriptionTransectItem.setEnabled(false);
            et_descriptionTransectItem.setCursorVisible(false);
            et_descriptionTransectItem.setKeyListener(null);
            et_descriptionTransectItem.setBackgroundColor(Color.TRANSPARENT);

            et_originGpsItemTransect.setText(gpsOriginTransect);
            et_originGpsItemTransect.setFocusable(false);
            et_originGpsItemTransect.setEnabled(false);
            et_originGpsItemTransect.setCursorVisible(false);
            et_originGpsItemTransect.setKeyListener(null);
            et_originGpsItemTransect.setBackgroundColor(Color.TRANSPARENT);

            et_originGpsItemTransectS.setText(gpsOriginTransectS);
            et_originGpsItemTransectS.setFocusable(false);
            et_originGpsItemTransectS.setEnabled(false);
            et_originGpsItemTransectS.setCursorVisible(false);
            et_originGpsItemTransectS.setKeyListener(null);
            et_originGpsItemTransectS.setBackgroundColor(Color.TRANSPARENT);

            et_destinationGpsItemTransect.setText(gpsDestinationTransect);
            et_destinationGpsItemTransect.setFocusable(false);
            et_destinationGpsItemTransect.setEnabled(false);
            et_destinationGpsItemTransect.setCursorVisible(false);
            et_destinationGpsItemTransect.setKeyListener(null);
            et_destinationGpsItemTransect.setBackgroundColor(Color.TRANSPARENT);

            et_destinationGpsItemTransectS.setText(gpsDestinationTransectS);
            et_destinationGpsItemTransectS.setFocusable(false);
            et_destinationGpsItemTransectS.setEnabled(false);
            et_destinationGpsItemTransectS.setCursorVisible(false);
            et_destinationGpsItemTransectS.setKeyListener(null);
            et_destinationGpsItemTransectS.setBackgroundColor(Color.TRANSPARENT);

            et_courseTransectItem.setText(courseTransect);
            et_courseTransectItem.setFocusable(false);
            et_courseTransectItem.setEnabled(false);
            et_courseTransectItem.setCursorVisible(false);
            et_courseTransectItem.setKeyListener(null);
            et_courseTransectItem.setBackgroundColor(Color.TRANSPARENT);

            et_longitudeTransectItem.setText(longitudeTransect);
            et_longitudeTransectItem.setFocusable(false);
            et_longitudeTransectItem.setEnabled(false);
            et_longitudeTransectItem.setCursorVisible(false);
            et_longitudeTransectItem.setKeyListener(null);
            et_longitudeTransectItem.setBackgroundColor(Color.TRANSPARENT);

            et_widthTransectItem.setText(widthTransect);
            et_widthTransectItem.setFocusable(false);
            et_widthTransectItem.setEnabled(false);
            et_widthTransectItem.setCursorVisible(false);
            et_widthTransectItem.setKeyListener(null);
            et_widthTransectItem.setBackgroundColor(Color.TRANSPARENT);

            et_observationTransectItem.setText(observationsTransect);
            et_observationTransectItem.setFocusable(false);
            et_observationTransectItem.setEnabled(false);
            et_observationTransectItem.setCursorVisible(false);
            et_observationTransectItem.setKeyListener(null);
            et_observationTransectItem.setBackgroundColor(Color.TRANSPARENT);

            if (operatorTransect != -1) {
                Valores item;
                for(int i = 0; i < operatorsSpinnerItem.size(); i++){
                    item = operatorsSpinnerItem.get(i);
                    if (item.getId() == operatorTransect){
                        sp_operatorItem.setSelection(i);
                    }
                }
                sp_operatorItem.setFocusable(false);
                sp_operatorItem.setEnabled(false);
                sp_operatorItem.setBackgroundColor(Color.TRANSPARENT);
            }

            if (contextAmbTransect != -1) {
                Valores item;
                for(int i = 0; i < contextAbmSpinnerItem.size(); i++){
                    item = contextAbmSpinnerItem.get(i);
                    if (item.getId() == contextAmbTransect){
                        sp_contextAbmItem.setSelection(i);
                    }
                }
                sp_contextAbmItem.setFocusable(false);
                sp_contextAbmItem.setEnabled(false);
                sp_contextAbmItem.setBackgroundColor(Color.TRANSPARENT);
            }
        } // Otherwise, start with empty fields.

        FloatingActionButton fab = findViewById(R.id.addNewMuestreo);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(Item_Transect.this, ListSamplings.class);
            intent.putExtra("idTransect", idTransectInsert);
            startActivity(intent);
        });
    }

    private void setUpComponents() {
        et_numberTransectItem = findViewById(R.id.et_numeroTransectaItemTransecta);
        et_descriptionTransectItem = findViewById(R.id.et_descripcionTransectaItemTransecta);
        et_originGpsItemTransect = findViewById(R.id.et_origenGpsItemTransecta);
        et_originGpsItemTransectS = findViewById(R.id.et_origenGpsItemTransectaS);
        et_destinationGpsItemTransect = findViewById(R.id.et_destinoGpsItemTransecta);
        et_destinationGpsItemTransectS = findViewById(R.id.et_destinoGpsItemTransectaS);
        et_courseTransectItem = findViewById(R.id.et_rumboTransectaItemTransecta);
        et_longitudeTransectItem = findViewById(R.id.et_longitudTransectaItemTransecta);
        et_widthTransectItem = findViewById(R.id.et_anchoTransectaItemTransecta);
        d_dateTransectItem = findViewById(R.id.d_fechaTransectaItemTransecta);
        sp_operatorItem = findViewById(R.id.sp_operadorItemTransecta);
        sp_contextAbmItem = findViewById(R.id.sp_contextoAmbItemTransecta);
        et_observationTransectItem = findViewById(R.id.et_observacionesTransectaItemTransecta);


        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        final String selectedDate = twoDigits(day) + " / " + twoDigits(month+1) + " / " + twoDigits(year);
        d_dateTransectItem.setText(selectedDate);

        chargeOperador();
    }

    private void chargeOperador() {
        operatorsSpinnerItem = mViewModel.getAllOperatorsList();
        sp_operatorItem.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner, operatorsSpinnerItem));

        contextAbmSpinnerItem = mViewModel.getAllContextAmbList();
        sp_contextAbmItem.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner, contextAbmSpinnerItem));

    }

    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showHomeUpIcon(getString(R.string.transectaItem));
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.editItem:
                Intent intent = new Intent(Item_Transect.this, New_Transect.class);

                intent.putExtra(NUMBER_UPDATE_TRANSECT_ITEM, et_numberTransectItem.getText().toString());
                intent.putExtra(DATE_UPDATE_TRANSECT_ITEM, d_dateTransectItem.getText().toString());
                intent.putExtra(DESCRIPTION_UPDATE_TRANSECT_ITEM, et_descriptionTransectItem.getText().toString());
                intent.putExtra(GPS_ORIGIN_UPDATE_TRANSECT_ITEM, et_originGpsItemTransect.getText().toString());
                intent.putExtra(GPS_ORIGINS_UPDATE_TRANSECT_ITEM, et_originGpsItemTransectS.getText().toString());
                intent.putExtra(GPS_DESTINATION_UPDATE_TRANSECT_ITEM, et_destinationGpsItemTransect.getText().toString());
                intent.putExtra(GPS_DESTINATIONS_UPDATE_TRANSECT_ITEM, et_destinationGpsItemTransectS.getText().toString());
                intent.putExtra(COURSE_UPDATE_TRANSECT_ITEM, et_courseTransectItem.getText().toString());
                intent.putExtra(LONGITUDE_UPDATE_TRANSECT_ITEM, et_longitudeTransectItem.getText().toString());
                intent.putExtra(WIDTH_UPDATE_TRANSECT_ITEM, et_widthTransectItem.getText().toString());
                intent.putExtra(OPERATOR_UPDATE_TRANSECT_ITEM, itemSpinnerSelectedItem);
                intent.putExtra(CONTEXT_AMB_UPDATE_TRANSECT_ITEM, itemContextSelectedItem);
                intent.putExtra(TRANSECTA_DATA_ID, idTransectInsert);
                intent.putExtra(PROJECT_UPDATE_TRANSECT_ITEM, idProject);
                intent.putExtra(OBSERVATIONS_UPDATE_TRANSECT_ITEM, et_observationTransectItem.getText().toString());
                intent.putStringArrayListExtra(PHOTOS_UPDATE_TRANSECT_ITEM, (ArrayList<String>) photosTransect);
                startActivityForResult(intent, UPDATE_TRANSECT_ACTIVITY_REQUEST_CODE);
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

        if (requestCode == UPDATE_TRANSECT_ACTIVITY_REQUEST_CODE
                && resultCode == RESULT_OK) {

            String numberTransect= data.getStringExtra(New_Transect.NUMBER_REPLY);
            Integer number_data = Integer.parseInt(numberTransect);

            // Integer numero_data = data.getIntExtra(New_Transect.NUMERO_REPLY, -1);
            String date_data = data.getStringExtra(New_Transect.DATE_REPLY);
            String description_data = data.getStringExtra(New_Transect.DESCRIPTION_REPLY);
            String gpsOrigin_data = data.getStringExtra(New_Transect.GPS_ORIGIN_REPLY);
            String gpsOrigin_dataS = data.getStringExtra(New_Transect.GPS_ORIGINS_REPLY);
            String gpsDestination_data = data.getStringExtra(New_Transect.GPS_DESTINATION_REPLY);
            String gpsDestination_dataS = data.getStringExtra(New_Transect.GPS_DESTINATIONS_REPLY);
            String course_data = data.getStringExtra(New_Transect.COURSE_REPLY);
            String longitude_data = data.getStringExtra(New_Transect.LONGITUDE_REPLY);
            String width_data = data.getStringExtra(New_Transect.ANCHOR_REPLY);
            Long operator_data = data.getLongExtra(New_Transect.OPERATOR_REPLY, -1);
            Long contextAmb_data = data.getLongExtra(New_Transect.CONTEXT_AMB_REPLY, -1);
            Long projectIdReply = data.getLongExtra("projectId", -1);
            String observations_data = data.getStringExtra(New_Transect.OBSERVATIONS_REPLY);
            ArrayList<String> photos_data = data.getStringArrayListExtra(New_Transect.PHOTOS_REPLY);
            List<Long> operator = new ArrayList<>();
            if (operator_data != -1){
                operator.add(operator_data);
            }

            List<Long> contextAmb = new ArrayList<>();
            if (contextAmb_data != -1){
                contextAmb.add(contextAmb_data);
            }

            Long idTransectUpdate = data.getLongExtra(TRANSECTA_DATA_ID, -1);
            idTransectInsert = idTransectUpdate;
            if (idTransectUpdate != -1) {
                mViewModel.updateTransect(new Transectas(idTransectUpdate, number_data, date_data, description_data, gpsOrigin_data, gpsOrigin_dataS, gpsDestination_data, gpsDestination_dataS,
                        course_data, longitude_data, width_data, operator,contextAmb, observations_data, photos_data));

                Intent intent = new Intent(Item_Transect.this, Item_Transect.class);
                intent.putExtra(NUMBER_UPDATE_TRANSECT, number_data);
                intent.putExtra(DATE_UPDATE_TRANSECT, date_data);
                intent.putExtra(DESCRIPTION_UPDATE_TRANSECT, description_data);
                intent.putExtra(GPS_ORIGIN_UPDATE_TRANSECT, gpsOrigin_data);
                intent.putExtra(GPS_ORIGINS_UPDATE_TRANSECT, gpsOrigin_dataS);
                intent.putExtra(GPS_DESTINATION_UPDATE_TRANSECT, gpsDestination_data);
                intent.putExtra(GPS_DESTINATIONS_UPDATE_TRANSECT, gpsDestination_dataS);
                intent.putExtra(COURSE_UPDATE_TRANSECT, course_data);
                intent.putExtra(LONGITUDE_UPDATE_TRANSECT, longitude_data);
                intent.putExtra(WIDTH_UPDATE_TRANSECT, width_data);
                intent.putExtra(OPERATOR_UPDATE_TRANSECT, operator_data);
                intent.putExtra(CONTEXTAMB_UPDATE_TRANSECT, contextAmb_data);
                intent.putExtra(TRANSECTA_DATA_ID, idTransectUpdate);
                intent.putExtra("idProject", projectIdReply);
                intent.putExtra(OBSERVATIONS_UPDATE_TRANSECT, observations_data);
                intent.putStringArrayListExtra(PHOTOS_UPDATE_TRANSECT_ITEM, photos_data);
                finish();
                startActivity(intent);

                Toast.makeText(this, R.string.transecta_updated,
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.unable_to_update_transecta,
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(
                    this, R.string.sin_cambios, Toast.LENGTH_LONG).show();
        }
    }

}