package com.example.transectas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.transectas.data.Proyectos;
import com.example.transectas.dialogos.DatePickerFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import static com.example.transectas.ProjectActivity.PROJECT_DATA_ID;
import static com.example.transectas.ProjectActivity.NAME_UPDATE_PROJECT;
import static com.example.transectas.ProjectActivity.LOCATION_UPDATE_PROJECT;
import static com.example.transectas.ProjectActivity.DATE_UPDATE_PROJECT;

public class Activity_item_project extends BaseActivity {

    private EditText et_nameProjectItem, et_locationProjectItem, d_dateCreateItem;
    private AppViewModel mViewModel;
    private long idProjectInsert;

    public static final String NAME_UPDATE_PROJECT_ITEM = "name_project_to_be_updatedItem";
    public static final String LOCATION_UPDATE_PROJECT_ITEM = "location_project_to_be_updatedItem";
    public static final String DATE_UPDATE_PROJECT_ITEM = "date_project_to_be_updatedItem";
    public static final String PROJECT_DATA_ID_ITEM = "extra_data_idItem";
    public static final int UPDATE_PROJECT_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_proyecto);
        idProjectInsert = -1;
        mViewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        setUpToolbar();
        setUpComponents();
        final Bundle extras = getIntent().getExtras();

        if (extras != null) {
            idProjectInsert = extras.getLong(PROJECT_DATA_ID);
            String nameProject = extras.getString(NAME_UPDATE_PROJECT, "");
            String locationProject = extras.getString(LOCATION_UPDATE_PROJECT, "");
            String dateProject = extras.getString(DATE_UPDATE_PROJECT, "");
            if (!nameProject.isEmpty()) {
                et_nameProjectItem.setText(nameProject);
                et_nameProjectItem.setFocusable(false);
                et_nameProjectItem.setEnabled(false);
                et_nameProjectItem.setCursorVisible(false);
                et_nameProjectItem.setKeyListener(null);
                et_nameProjectItem.setBackgroundColor(Color.TRANSPARENT);
            }
            if (!locationProject.isEmpty()) {
                et_locationProjectItem.setText(locationProject);
                et_locationProjectItem.setFocusable(false);
                et_locationProjectItem.setEnabled(false);
                et_locationProjectItem.setCursorVisible(false);
                et_locationProjectItem.setKeyListener(null);
                et_locationProjectItem.setBackgroundColor(Color.TRANSPARENT);
            }
            if (!dateProject.isEmpty()) {
                d_dateCreateItem.setText(dateProject);
                d_dateCreateItem.setFocusable(false);
                d_dateCreateItem.setEnabled(false);
                d_dateCreateItem.setCursorVisible(false);
                d_dateCreateItem.setKeyListener(null);
                d_dateCreateItem.setBackgroundColor(Color.TRANSPARENT);
            }
        } // Otherwise, start with empty fields.

        FloatingActionButton fab = findViewById(R.id.addNewTransectaItem);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(Activity_item_project.this, Add_Transect.class);
            intent.putExtra("idProject", idProjectInsert);
            startActivity(intent);
        });
    }

    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showHomeUpIcon(getString(R.string.projectItem));
    }

    private void setUpComponents() {

        et_nameProjectItem = findViewById(R.id.et_nombreProyectoItem);
        et_locationProjectItem = findViewById(R.id.et_ubicacionProyectoItem);
        d_dateCreateItem = findViewById(R.id.d_fechaTransectaItem);


        d_dateCreateItem.setText(selectedDate);

        d_dateCreateItem.setOnClickListener(view -> {
            if (view.getId() == R.id.d_fechaTransectaItem) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance((datePicker, i, i1, i2) -> {
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
       // inflater.inflate(R.menu.menu_cancelar, menu);
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
                Intent intent = new Intent(Activity_item_project.this, Activity_add_project.class);
                intent.putExtra(NAME_UPDATE_PROJECT_ITEM, et_nameProjectItem.getText().toString());
                intent.putExtra(LOCATION_UPDATE_PROJECT_ITEM, et_locationProjectItem.getText().toString());
                intent.putExtra(DATE_UPDATE_PROJECT_ITEM, d_dateCreateItem.getText().toString());
                intent.putExtra(PROJECT_DATA_ID_ITEM, idProjectInsert);
                intent.putExtra("newProject",0);
                startActivityForResult(intent, UPDATE_PROJECT_ACTIVITY_REQUEST_CODE);
                break;
            case android.R.id.home:
                Intent intentBack = new Intent(Activity_item_project.this, ProjectActivity.class);
                startActivity(intentBack);
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

        if (requestCode == UPDATE_PROJECT_ACTIVITY_REQUEST_CODE
                && resultCode == RESULT_OK) {

            String nombre_data = data.getStringExtra(Activity_add_project.NAME_REPLY);
            String ubicacion_data = data.getStringExtra(Activity_add_project.LOCATION_REPLY);
            String fecha_data = data.getStringExtra(Activity_add_project.DATE_REPLY);
            long idProjectUpdate = data.getLongExtra(Activity_add_project.PROJECT_REPLY_ID, -1);

            if (idProjectUpdate != -1) {
                mViewModel.updateProject(new Proyectos(idProjectUpdate, nombre_data, ubicacion_data, fecha_data));
                Intent intent = new Intent(Activity_item_project.this, Activity_item_project.class);
                intent.putExtra(NAME_UPDATE_PROJECT, nombre_data);
                intent.putExtra(LOCATION_UPDATE_PROJECT, ubicacion_data);
                intent.putExtra(DATE_UPDATE_PROJECT, fecha_data);
                intent.putExtra(PROJECT_DATA_ID, idProjectInsert);
                finish();
                startActivity(intent);
                Toast.makeText(this, R.string.project_updated,
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.unable_to_update,
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(
                    this, R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }


}