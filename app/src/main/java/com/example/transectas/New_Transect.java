package com.example.transectas;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.transectas.data.Valores;
import com.example.transectas.dialogos.DatePickerFragment;
import com.example.transectas.dialogos.DialogoAddItemFragment;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import static com.example.transectas.Add_Transect.TRANSECTA_DATA_ID;
import static com.example.transectas.Item_Transect.WIDTH_UPDATE_TRANSECT_ITEM;
import static com.example.transectas.Item_Transect.CONTEXT_AMB_UPDATE_TRANSECT_ITEM;
import static com.example.transectas.Item_Transect.DESCRIPTION_UPDATE_TRANSECT_ITEM;
import static com.example.transectas.Item_Transect.DATE_UPDATE_TRANSECT_ITEM;
import static com.example.transectas.Item_Transect.GPS_DESTINATIONS_UPDATE_TRANSECT_ITEM;
import static com.example.transectas.Item_Transect.GPS_DESTINATION_UPDATE_TRANSECT_ITEM;
import static com.example.transectas.Item_Transect.GPS_ORIGINS_UPDATE_TRANSECT_ITEM;
import static com.example.transectas.Item_Transect.GPS_ORIGIN_UPDATE_TRANSECT_ITEM;
import static com.example.transectas.Item_Transect.LONGITUDE_UPDATE_TRANSECT_ITEM;
import static com.example.transectas.Item_Transect.NUMBER_UPDATE_TRANSECT_ITEM;
import static com.example.transectas.Item_Transect.OBSERVATIONS_UPDATE_TRANSECT_ITEM;
import static com.example.transectas.Item_Transect.OPERATOR_UPDATE_TRANSECT_ITEM;
import static com.example.transectas.Item_Transect.PROJECT_UPDATE_TRANSECT_ITEM;
import static com.example.transectas.Item_Transect.COURSE_UPDATE_TRANSECT_ITEM;
import static com.example.transectas.Item_Transect.PHOTOS_UPDATE_TRANSECT_ITEM;

public class New_Transect extends BaseActivity {

    private EditText et_numberTransect, et_descriptionTransect, et_originGps, et_destinationGps, et_originGpsS, et_destinationGpsS, et_courseTransect, et_longitudeTransect, et_anchorTransect, d_dateTransect, et_observationsTransects;
    private Spinner sp_operator, sp_contextAmb;
    private AppViewModel mViewModel;
    private Valores itemSpinnerSelected, itemContextSelected;
    private List<Valores> operatorsSpinner, contextAmbSpinner;
    private long idTransectInsert;
    private Integer newTransect = -1;
    private Long idProject;
    private ImageView ivPhotoTransect;

    //GPS
    private FusedLocationProviderClient fusedLocationClient;
    LocationRequest locationRequest;
    private Location currentLocation;
    private LocationCallback locationCallback;
    private static final int REQUEST_CHECK_SETTINGS = 1;
    private static final int REQUEST_GRANT_PERMISSION = 2;
    private final int REQUEST_CHECK_CODE = 8989;
    private Integer gpsO;

    public static final String NUMBER_REPLY = "com.example.transectas.NUMBER_REPLY";
    public static final String DATE_REPLY = "com.example.transectas.DATE_REPLY";
    public static final String DESCRIPTION_REPLY = "com.example.transectas.DESCRIPTION_REPLY";
    public static final String GPS_ORIGIN_REPLY = "com.example.transectas.GPS_ORIGIN_REPLY";
    public static final String GPS_ORIGINS_REPLY = "com.example.transectas.GPS_ORIGINS_REPLY";
    public static final String GPS_DESTINATION_REPLY = "com.example.transectas.GPS_DESTINATION_REPLY";
    public static final String GPS_DESTINATIONS_REPLY = "com.example.transectas.GPS_DESTINATIONS_REPLY";
    public static final String COURSE_REPLY = "com.example.transectas.COURSE_REPLY";
    public static final String LONGITUDE_REPLY = "com.example.transectas.LONGITUDE_REPLY";
    public static final String ANCHOR_REPLY = "com.example.transectas.ANCHOR_REPLY";
    public static final String OPERATOR_REPLY = "com.example.transectas.OPERATOR_REPLY";
    public static final String CONTEXT_AMB_REPLY = "com.example.transectas.CONTEXT_AMB_REPLY";
    public static final String PHOTOS_REPLY = "com.example.transectas.PHOTOS_REPLY";
    public static final String OBSERVATIONS_REPLY = "com.example.transectas.OBSERVATIONS_REPLY";

    public static final String TRANSECT_REPLY_ID = "extra_data_id";

    private Long idProjectItem;

    private Uri image_uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__transecta);
        mViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        itemSpinnerSelected = new Valores();
        operatorsSpinner = null;
        itemContextSelected = new Valores();
        contextAmbSpinner = null;
        idTransectInsert = -1;
        idProject = null;
        setUpToolbar();
        setUpComponents();

        final Bundle extras = getIntent().getExtras();

        // If we are passed content, fill it in for the user to edit.
        if (extras != null) {
            newTransect = extras.getInt("newTransect", -1);
            idProjectItem = extras.getLong("idProject");
            idTransectInsert = extras.getLong(TRANSECTA_DATA_ID);
            idProject = extras.getLong(PROJECT_UPDATE_TRANSECT_ITEM, -1);
            String numberTransect= extras.getString(NUMBER_UPDATE_TRANSECT_ITEM, "");
            String dateTransect = extras.getString(DATE_UPDATE_TRANSECT_ITEM, "");
            String descriptionTransect = extras.getString(DESCRIPTION_UPDATE_TRANSECT_ITEM, "");
            String gpsOriginTransect = extras.getString(GPS_ORIGIN_UPDATE_TRANSECT_ITEM, "");
            String gpsOriginTransectS = extras.getString(GPS_ORIGINS_UPDATE_TRANSECT_ITEM, "");
            String gpsDestinationTransect = extras.getString(GPS_DESTINATION_UPDATE_TRANSECT_ITEM, "");
            String gpsDestinationTransectS = extras.getString(GPS_DESTINATIONS_UPDATE_TRANSECT_ITEM, "");
            String courseTransect = extras.getString(COURSE_UPDATE_TRANSECT_ITEM, "");
            String longitudeTransect = extras.getString(LONGITUDE_UPDATE_TRANSECT_ITEM, "");
            String anchorTransect = extras.getString(WIDTH_UPDATE_TRANSECT_ITEM, "");
            long operatorTransect = extras.getLong(OPERATOR_UPDATE_TRANSECT_ITEM, -1);
            long contextAmbTransect = extras.getLong(CONTEXT_AMB_UPDATE_TRANSECT_ITEM, -1);
            String observationsTransects = extras.getString(OBSERVATIONS_UPDATE_TRANSECT_ITEM, "");
            if (!numberTransect.isEmpty()) {
                et_numberTransect.setText(numberTransect);
                et_numberTransect.setFocusable(false);
                et_numberTransect.setEnabled(false);
                et_numberTransect.setCursorVisible(false);
                et_numberTransect.setKeyListener(null);
                et_numberTransect.setBackgroundColor(Color.TRANSPARENT);
            }
            if (!dateTransect.isEmpty()) {
                d_dateTransect.setText(dateTransect);
            }
            if (!descriptionTransect.isEmpty()) {
                et_descriptionTransect.setText(descriptionTransect);
            }
            if (!gpsOriginTransect.isEmpty()) {
                et_originGps.setText(gpsOriginTransect);
            }
            if (!gpsOriginTransectS.isEmpty()) {
                et_originGpsS.setText(gpsOriginTransectS);
            }
            if (!gpsDestinationTransect.isEmpty()) {
                et_destinationGps.setText(gpsDestinationTransect);
            }
            if (!gpsDestinationTransectS.isEmpty()) {
                et_destinationGpsS.setText(gpsDestinationTransectS);
            }
            if (!courseTransect.isEmpty()) {
                et_courseTransect.setText(courseTransect);
            }
            if (!longitudeTransect.isEmpty()) {
                et_longitudeTransect.setText(longitudeTransect);
            }
            if (!anchorTransect.isEmpty()) {
                et_anchorTransect.setText(anchorTransect);
            }
            if (!observationsTransects.isEmpty()) {
                et_observationsTransects.setText(observationsTransects);
            }
            if (operatorTransect != -1) {
                Valores item = new Valores();
                for (int i = 0; i < operatorsSpinner.size(); i++) {
                    item = operatorsSpinner.get(i);
                    if (item.getId() == operatorTransect) {
                        sp_operator.setSelection(i);
                    }
                }
            }
            if (contextAmbTransect != -1) {
                Valores item = new Valores();
                for (int i = 0; i < contextAmbSpinner.size(); i++) {
                    item = contextAmbSpinner.get(i);
                    if (item.getId() == contextAmbTransect) {
                        sp_contextAmb.setSelection(i);
                    }
                }
            }
        } 
        
        if (newTransect > 0) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(getString(R.string.transectaNew));
            }
        }
    }

    private void setUpComponents() {
        et_numberTransect = findViewById(R.id.et_numeroTransecta);
        et_descriptionTransect = findViewById(R.id.et_descripcionTransecta);
        et_originGps = findViewById(R.id.et_origenGps);
        et_destinationGps = findViewById(R.id.et_destinoGps);
        et_destinationGpsS = findViewById(R.id.et_destinoGpsS);
        et_originGpsS = findViewById(R.id.et_origenGpsS);
        et_courseTransect = findViewById(R.id.et_rumboTransecta);
        et_longitudeTransect = findViewById(R.id.et_longitudTransecta);
        et_anchorTransect = findViewById(R.id.et_anchoTransecta);
        d_dateTransect = findViewById(R.id.d_fechaTransectaItem);
        sp_operator = findViewById(R.id.sp_operador);
        sp_contextAmb = findViewById(R.id.sp_contextoAmb);
        et_observationsTransects = findViewById(R.id.et_observacionesTransecta);

        ivPhotoTransect = findViewById(R.id.iv_photoTransect);


        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        final String selectedDate = twoDigits(day) + " / " + twoDigits(month + 1) + " / " + twoDigits(year);
        d_dateTransect.setText(selectedDate);

        d_dateTransect.setOnClickListener(view -> {
            if (view.getId() == R.id.d_fechaTransectaItem) {
                showDatePickerDialog();
            }
        });

        sp_operator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemSpinnerSelected = (Valores) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_contextAmb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemContextSelected = (Valores) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        loadOperator();

        //GPS
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        gpsO = 0;
        createLocationRequest();
        //settingsCheck();

        //Fotos

    }

    private void loadOperator() {
        operatorsSpinner = mViewModel.getAllOperatorsList();
        sp_operator.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner, operatorsSpinner));
        contextAmbSpinner = mViewModel.getAllContextAmbList();
        sp_contextAmb.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner, contextAmbSpinner));
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, i, i1, i2) -> {

        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showHomeUpIcon(getString(R.string.transectaEdit));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cancelar, menu);
        if (newTransect > 0) {
            MenuItem photoItem = menu.findItem(R.id.tomarFoto);
            photoItem.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                alertDialogBuilder();
                break;
            case R.id.new_search:
                break;
            case R.id.acerca_de:
                about(this);
                break;
            case R.id.addItemTabla:
                DialogoAddItemFragment dialogoAddItemFragment = new DialogoAddItemFragment();
                dialogoAddItemFragment.show(getSupportFragmentManager(), "DialogAddItem");
                break;
            case R.id.altaProyecto:
                insertTransectConfirm();
                break;
            case R.id.tomarFoto:
                takePhoto();
                break;
            default:
                //Error
        }

        return super.onOptionsItemSelected(item);
    }

    String currentPhotoPath;

    private void takePhoto(){

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            return;
        }
        else{
            //permission already granted
            createPhoto();
        }
    }

    private void createPhoto(){

        String timestamp = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());
        String idTransectString = String.valueOf(idTransectInsert);
        String idProjectString = String.valueOf(idProject);
        String imageFileName =  "ProyectoID_" + idProjectString + "TransectaID_" + idTransectString + "_" + timestamp;

        ContentValues values = new ContentValues();
        ContentResolver contentResolver = getBaseContext().getContentResolver();
        values.put(MediaStore.Images.Media.TITLE, imageFileName);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, imageFileName);
        values.put(MediaStore.Images.Media.DESCRIPTION, imageFileName);
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        //camera intent
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        if (cameraIntent.resolveActivity(getPackageManager()) != null){
            int PHOTO_CONST = 1001;
            startActivityForResult(cameraIntent, PHOTO_CONST);
        }
    }

    private void insertTransectConfirm() {
        final Bundle extras = getIntent().getExtras();
        Intent replyIntent = new Intent();
        /*if (TextUtils.isEmpty(et_numeroTransecta.getText()) || TextUtils.isEmpty(et_descripcionTransecta.getText()) ||
                TextUtils.isEmpty(et_origenGps.getText()) || TextUtils.isEmpty(et_origenGpsS.getText()) ||
                TextUtils.isEmpty(et_destinoGps.getText()) || TextUtils.isEmpty(et_destinoGpsS.getText()) ||
                TextUtils.isEmpty(et_rumboTransecta.getText()) || TextUtils.isEmpty(et_longitudTransecta.getText()) ||
                TextUtils.isEmpty(et_anchoTransecta.getText()) || TextUtils.isEmpty(et_observacionesTransecta.getText())
        )*/
        if (TextUtils.isEmpty(et_numberTransect.getText()))
        {
            Toast.makeText(this, R.string.transecta_obligatorio, Toast.LENGTH_SHORT).show();
            et_numberTransect.setFocusable(true);
        } else {
            // Get the new transecta name that the user entered.
            String numberTransect = et_numberTransect.getText().toString();
            String dateTransect = d_dateTransect.getText().toString();
            String descriptionTransect = et_descriptionTransect.getText().toString();
            String gpsOriginTransect = et_originGps.getText().toString();
            String gpsOriginTransectS = et_originGpsS.getText().toString();
            String gpsDestinyTransect = et_destinationGps.getText().toString();
            String gpsDestinyTransectS = et_destinationGpsS.getText().toString();
            String courseTransect = et_courseTransect.getText().toString();
            String longitudeTransect = et_longitudeTransect.getText().toString();
            String anchorTransect = et_anchorTransect.getText().toString();
            String observationsTransects = et_observationsTransects.getText().toString();

            long idSpinner = -1;
            if (itemSpinnerSelected != null) {
                idSpinner = itemSpinnerSelected.getId();
            }

            long idSpinnerContextoAmb = -1;
            if (itemContextSelected != null) {
                idSpinnerContextoAmb = itemContextSelected.getId();
            }

            replyIntent.putExtra(NUMBER_REPLY, numberTransect);
            replyIntent.putExtra(DATE_REPLY, dateTransect);
            replyIntent.putExtra(DESCRIPTION_REPLY, descriptionTransect);
            replyIntent.putExtra(GPS_ORIGIN_REPLY, gpsOriginTransect);
            replyIntent.putExtra(GPS_ORIGINS_REPLY, gpsOriginTransectS);
            replyIntent.putExtra(GPS_DESTINATION_REPLY, gpsDestinyTransect);
            replyIntent.putExtra(GPS_DESTINATIONS_REPLY, gpsDestinyTransectS);
            replyIntent.putExtra(COURSE_REPLY, courseTransect);
            replyIntent.putExtra(LONGITUDE_REPLY, longitudeTransect);
            replyIntent.putExtra(ANCHOR_REPLY, anchorTransect);
            replyIntent.putExtra(OPERATOR_REPLY, idSpinner);
            replyIntent.putExtra(CONTEXT_AMB_REPLY, idSpinnerContextoAmb);
            replyIntent.putExtra(OBSERVATIONS_REPLY, observationsTransects);
            replyIntent.putExtra("projectId", idProject);

            if (extras != null && extras.containsKey(TRANSECTA_DATA_ID)) {
                long id = extras.getLong(TRANSECTA_DATA_ID, -1);
                if (id != -1) {
                    replyIntent.putExtra(TRANSECTA_DATA_ID, id);
                }
            }
            //check if exists idTransect

            int existTransect = -1;
            existTransect = mViewModel.existTransect(Integer.parseInt(numberTransect), idProjectItem);
            //si devuelve un valor mayor de cero, es que existe un numero de transecta. No se puede insertar
            if (existTransect > 0){
                Toast.makeText(this, R.string.transecta_repetida,
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
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            alertDialogBuilder();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void alertDialogBuilder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.save_change)
                .setPositiveButton("Si", (dialogInterface, i) -> {
                    if (!TextUtils.isEmpty(et_numberTransect.getText())) {
                        insertTransectConfirm();
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

    public void alertDialogGps(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
                .setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    //GPS

    public void getGPS(View view) {
        gpsO = 1;
        //  createLocationRequest();
        settingsCheck();
        //if(locationCallback!=null)
        //  fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    public void getGPSD(View view) {
        gpsO = 0;
        //   createLocationRequest();
        settingsCheck();
        //if(locationCallback!=null)
        //    fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    protected void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void settingsCheck() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
        task.addOnSuccessListener(this, locationSettingsResponse -> {
            // All location settings are satisfied. The client can initialize
            // location requests here.
            Log.d("TAG", "onSuccess: settingsCheck");
            getCurrentLocation();
        });

        task.addOnCompleteListener(taskTry -> {
            try {
                taskTry.getResult(ApiException.class);
            } catch (ApiException e) {
                switch (e.getStatusCode()) {
                    case LocationSettingsStatusCodes
                            .RESOLUTION_REQUIRED:
                        ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                        try {
                            resolvableApiException.startResolutionForResult(New_Transect.this, REQUEST_CHECK_CODE);
                        } catch (IntentSender.SendIntentException ex) {
                            ex.printStackTrace();
                        } catch (ClassCastException ignored) {

                        }

                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE: {
                        break;
                    }
                }
                e.printStackTrace();
            }
        });

        task.addOnFailureListener(this, e -> {
            if (e instanceof ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                Log.d("TAG", "onFailure: settingsCheck");
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    resolvable.startResolutionForResult(New_Transect.this,
                            REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException sendEx) {
                    // Ignore the error.
                }
            }
        });
    }

    public void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    Log.d("TAG", "onSuccess: getLastLocation");
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        currentLocation = location;
                        Log.d("TAG", "onSuccess:latitude " + location.getLatitude());
                        Log.d("TAG", "onSuccess:longitude " + location.getLongitude());
                        String latitude = String.valueOf(location.getLatitude());
                        String longitude = String.valueOf((location.getLongitude()));
                        if (gpsO == 1) {
                            et_originGpsS.setText(latitude);
                            et_originGps.setText(longitude);
                        } else if (gpsO == 0) {
                            et_destinationGpsS.setText(latitude);
                            et_destinationGps.setText(longitude);
                        }
                        if (locationCallback != null)
                            fusedLocationClient.removeLocationUpdates(locationCallback);
                    } else {
                        Log.d("TAG", "location is null");
                        buildLocationCallback();
                    }
                });
    }

    private void buildLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Update UI with location data
                    currentLocation = location;
                    Log.d("TAG", "onLocationResult: " + currentLocation.getLatitude());
                    String latitude = String.valueOf(location.getLatitude());
                    String longitude = String.valueOf((location.getLongitude()));
                    if (gpsO == 1) {
                        et_originGpsS.setText(latitude);
                        et_originGps.setText(longitude);
                    } else if (gpsO == 0) {
                        et_destinationGpsS.setText(latitude);
                        et_destinationGps.setText(longitude);
                    }
                }
                if (locationCallback != null)
                    fusedLocationClient.removeLocationUpdates(locationCallback);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(locationRequest, locationCallback, null);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_GRANT_PERMISSION){
            getCurrentLocation();
        }
        if(requestCode==1000){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                takePhoto();
            }
            else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //called after user responds to location settings popup
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "onActivityResult: ");
        if(requestCode==REQUEST_CHECK_SETTINGS && resultCode==RESULT_OK)
            getCurrentLocation();
        if(requestCode==REQUEST_CHECK_SETTINGS && resultCode==RESULT_CANCELED)
            Toast.makeText(this, "Please enable Location settings...!!!", Toast.LENGTH_SHORT).show();

        ivPhotoTransect.setImageURI(image_uri);
    }


}