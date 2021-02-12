package com.example.transectas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.transectas.data.Transectas;
import com.example.transectas.data.TransectasProyectos;
import com.example.transectas.dialogos.DialogoAddItemFragment;
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


public class Add_Transect extends BaseActivity {

    private AppViewModel mViewModel;
    private TransectasAdapter adapter;

    public static final int NEW_TRANSECT_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_TRANSECT_ACTIVITY_REQUEST_CODE = 2;

    public static final String NUMBER_UPDATE_TRANSECT = "name_transect_to_be_updated";
    public static final String DATE_UPDATE_TRANSECT = "date_transect_to_be_updated";
    public static final String DESCRIPTION_UPDATE_TRANSECT = "description_transect_to_be_updated";
    public static final String GPS_ORIGIN_UPDATE_TRANSECT = "gps_origin_transect_to_be_updated";
    public static final String GPS_ORIGINS_UPDATE_TRANSECT = "gps_origins_transect_to_be_updated";
    public static final String GPS_DESTINATION_UPDATE_TRANSECT = "gps_destination_transect_to_be_updated";
    public static final String GPS_DESTINATIONS_UPDATE_TRANSECT = "gps_destinations_transect_to_be_updated";
    public static final String COURSE_UPDATE_TRANSECT = "course_transect_to_be_updated";
    public static final String LONGITUDE_UPDATE_TRANSECT = "longitude_transect_to_be_updated";
    public static final String WIDTH_UPDATE_TRANSECT = "width_transect_to_be_updated";
    public static final String OBSERVATIONS_UPDATE_TRANSECT = "observations_transect_to_be_updated";
    public static final String OPERATOR_UPDATE_TRANSECT = "operator_transect_to_be_updated";
    public static final String CONTEXTAMB_UPDATE_TRANSECT = "contextAmb_transect_to_be_updated";
    public static final String PHOTOS_UPDATE_TRANSECT = "photos_transect_to_be_updated";

    public static final String TRANSECTA_DATA_ID = "extra_data_id";

    public long PROJECT_ID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__transecta);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        setUpToolbar();

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        final Bundle extras = getIntent().getExtras();
        long idProyecto = -1;
        if (extras != null) {
            idProyecto = extras.getLong("idProject");
            PROJECT_ID = idProyecto;
        }
        adapter = new TransectasAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(lim);

        mViewModel.getAllTransectsLive(idProyecto).observe(this, transects -> adapter.setTransectas(transects));

        adapter.setOnItemClickListener((v, position) -> {
            Transectas transect = adapter.getTransectaAtPosition(position);
            launchUpdateTransectActivity(transect);
        });

        FloatingActionButton fab = findViewById(R.id.addTransecta);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(Add_Transect.this, New_Transect.class);
            intent.putExtra("newTransect",1);
            intent.putExtra("idProject", PROJECT_ID);
            startActivityForResult(intent, NEW_TRANSECT_ACTIVITY_REQUEST_CODE);
        });
    }

    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showHomeUpIcon(getString(R.string.transectsTitle));
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

    public void launchUpdateTransectActivity(Transectas transectas) {
        Intent intent = new Intent(this, Item_Transect.class);
        intent.putExtra(NUMBER_UPDATE_TRANSECT, transectas.getNumero());
        intent.putExtra(DATE_UPDATE_TRANSECT, transectas.getFechaCreacion());
        intent.putExtra(DESCRIPTION_UPDATE_TRANSECT, transectas.getDescripcion());
        intent.putExtra(GPS_ORIGIN_UPDATE_TRANSECT, transectas.getGpsOrigen());
        intent.putExtra(GPS_ORIGINS_UPDATE_TRANSECT, transectas.getGpsOrigenS());
        intent.putExtra(GPS_DESTINATION_UPDATE_TRANSECT, transectas.getGpsDestino());
        intent.putExtra(GPS_DESTINATIONS_UPDATE_TRANSECT, transectas.getGpsDestinoS());
        intent.putExtra(COURSE_UPDATE_TRANSECT, transectas.getRumbo());
        intent.putExtra(LONGITUDE_UPDATE_TRANSECT, transectas.getLongitud());
        intent.putExtra(WIDTH_UPDATE_TRANSECT, transectas.getAncho());
        intent.putExtra(OBSERVATIONS_UPDATE_TRANSECT, transectas.getObservaciones());
        intent.putStringArrayListExtra(PHOTOS_UPDATE_TRANSECT, (ArrayList<String>) transectas.getFotos());
        List<Long> idOperator = transectas.getFk_operador();
        Long idSpinner = idOperator.get(0);
        intent.putExtra(OPERATOR_UPDATE_TRANSECT, idSpinner);
        List<Long> idContextAmb = transectas.getFk_contextoAmb();
        Long idSpinnerContextAmb = idContextAmb.get(0);
        intent.putExtra(CONTEXTAMB_UPDATE_TRANSECT, idSpinnerContextAmb);
        intent.putExtra(TRANSECTA_DATA_ID, transectas.getId());
        intent.putExtra("projectId", PROJECT_ID);
        intent.putExtra("newTransect",0);
        startActivity(intent);
        //startActivityForResult(intent, UPDATE_TRANSECTA_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TRANSECT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            long idTransectNew;
            String numberTransect= data.getStringExtra(New_Transect.NUMBER_REPLY);
            Integer numberTransectInt = Integer.parseInt(numberTransect);
            List<Long> operators = new ArrayList<>();
            long idOperatorSpinner = data.getLongExtra(New_Transect.OPERATOR_REPLY, -1);
            if (idOperatorSpinner != -1)
                operators.add(idOperatorSpinner);
            List<Long> contextAmb = new ArrayList<>();
            long idContextAmbSpinner = data.getLongExtra(New_Transect.CONTEXT_AMB_REPLY, -1);
            if (idContextAmbSpinner != -1)
                contextAmb.add(idContextAmbSpinner);

            // Save the data.
            Transectas newTransect = new Transectas(numberTransectInt,
                    data.getStringExtra(New_Transect.DATE_REPLY),
                    data.getStringExtra(New_Transect.DESCRIPTION_REPLY),
                    data.getStringExtra(New_Transect.GPS_ORIGIN_REPLY),
                    data.getStringExtra(New_Transect.GPS_ORIGINS_REPLY),
                    data.getStringExtra(New_Transect.GPS_DESTINATION_REPLY),
                    data.getStringExtra(New_Transect.GPS_DESTINATIONS_REPLY),
                    data.getStringExtra(New_Transect.COURSE_REPLY),
                    data.getStringExtra(New_Transect.LONGITUDE_REPLY),
                    data.getStringExtra(New_Transect.ANCHOR_REPLY),
                    operators,
                    contextAmb,
                    data.getStringExtra(New_Transect.OBSERVATIONS_REPLY),
                    data.getStringArrayListExtra(New_Transect.PHOTOS_REPLY)
                    );

            idTransectNew = mViewModel.insertTransect(newTransect);

            if (idTransectNew > 0) {
                if (PROJECT_ID > 0) {
                    TransectasProyectos transectasProyectos = new TransectasProyectos(PROJECT_ID, idTransectNew);
                    long idProjectTransect;
                    idProjectTransect = mViewModel.insertTransectToProject(transectasProyectos);
                    if (idProjectTransect > 0)
                        Toast.makeText(this, R.string.transecta_saved,
                                Toast.LENGTH_LONG).show();
                    else Toast.makeText(this, R.string.transecta_a_proyecto_not_saved,
                            Toast.LENGTH_LONG).show();
                } else Toast.makeText(this, R.string.transecta_a_proyecto_not_saved,
                            Toast.LENGTH_LONG).show();
            } else Toast.makeText(this, R.string.unable_to_update_transecta,
                        Toast.LENGTH_LONG).show();

        } else if (requestCode == UPDATE_TRANSECT_ACTIVITY_REQUEST_CODE
                && resultCode == RESULT_OK) {

            String numeroTransecta= data.getStringExtra(New_Transect.NUMBER_REPLY);
            Integer numero_data = Integer.parseInt(numeroTransecta);

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
            String observations_data = data.getStringExtra(New_Transect.OBSERVATIONS_REPLY);
            List<String> photos_data = data.getStringArrayListExtra(New_Transect.PHOTOS_REPLY);
            long operator_data = data.getLongExtra(New_Transect.OPERATOR_REPLY, -1);
            List<Long> operator = new ArrayList<>();
            if (operator_data != -1){
                operator.add(operator_data);
            }
            long contextAmb_data = data.getLongExtra(New_Transect.CONTEXT_AMB_REPLY, -1);
            List<Long> contextAmb = new ArrayList<>();
            if (contextAmb_data != -1){
                contextAmb.add(contextAmb_data);
            }

            long idTransectUpdate = data.getLongExtra(New_Transect.TRANSECT_REPLY_ID, -1);

            if (idTransectUpdate != -1) {
                mViewModel.updateTransect(new Transectas(idTransectUpdate, numero_data, date_data, description_data, gpsOrigin_data, gpsOrigin_dataS, gpsDestination_data, gpsDestination_dataS,
                        course_data, longitude_data, width_data, operator, contextAmb, observations_data, photos_data));
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