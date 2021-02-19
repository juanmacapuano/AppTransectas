package com.example.transectas.projects;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.transectas.BaseActivity;
import com.example.transectas.R;
import com.example.transectas.dialogos.DatePickerFragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import static com.example.transectas.projects.Activity_item_project.DATE_UPDATE_PROJECT_ITEM;
import static com.example.transectas.projects.Activity_item_project.NAME_UPDATE_PROJECT_ITEM;
import static com.example.transectas.projects.Activity_item_project.PROJECT_DATA_ID_ITEM;
import static com.example.transectas.projects.Activity_item_project.LOCATION_UPDATE_PROJECT_ITEM;

public class Activity_add_project extends BaseActivity {

    private EditText et_nameProject, et_locationProject, d_dateCreated;
    private Integer newProject = -1;

    public static final String NAME_REPLY = "com.example.transectas.NAME_REPLY";
    public static final String LOCATION_REPLY = "com.example.transectas.LOCATION_REPLY";
    public static final String DATE_REPLY = "com.example.transectas.DATE_REPLY";
    public static final String PROJECT_REPLY_ID = "com.example.transectas.PROJECT_REPLY_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_proyecto);
        //AppViewModel mViewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        setUpToolbar();
        setUpComponents();
        final Bundle extras = getIntent().getExtras();

        if (extras != null) {
            newProject = extras.getInt("newProject");
            String nameProject = extras.getString(NAME_UPDATE_PROJECT_ITEM, "");
            String locationProject = extras.getString(LOCATION_UPDATE_PROJECT_ITEM, "");
            String dateProject = extras.getString(DATE_UPDATE_PROJECT_ITEM, "");
            if (!nameProject.isEmpty()) {
                et_nameProject.setText(nameProject);
                et_nameProject.setFocusable(false);
                et_nameProject.setEnabled(false);
                et_nameProject.setCursorVisible(false);
                et_nameProject.setKeyListener(null);
                et_nameProject.setBackgroundColor(Color.TRANSPARENT);
            }
            if (!locationProject.isEmpty()) {
                et_locationProject.setText(locationProject);
                et_locationProject.setFocusable(false);
                et_locationProject.setEnabled(false);
                et_locationProject.setCursorVisible(false);
                et_locationProject.setKeyListener(null);
                et_locationProject.setBackgroundColor(Color.TRANSPARENT);
            }
            if (!dateProject.isEmpty()) {
                d_dateCreated.setText(dateProject);
            }
        } // Otherwise, start with empty fields.

        if (newProject > 0){
            if(getSupportActionBar() != null){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle("Nuevo Proyecto");
            }
        }

    }

    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showHomeUpIcon(getString(R.string.projectEditTitle));
    }

    private void setUpComponents() {

        et_nameProject = findViewById(R.id.et_nombreProyecto);
        et_locationProject = findViewById(R.id.et_ubicacionProyecto);
        d_dateCreated = findViewById(R.id.d_fechaTransectaItem);

        d_dateCreated.setText(selectedDate);

        d_dateCreated.setOnClickListener(view -> {
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
        inflater.inflate(R.menu.menu_cancelar, menu);
        MenuItem camara = menu.findItem(R.id.tomarFoto);
        camara.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.altaProyecto:
                insertProjectConfirm();
                break;
            case R.id.acerca_de:
                about(this);
                break;
            case android.R.id.home:
            case R.id.cancelarAlta:
                alertDialogBuilder();
                break;
            default:
                //Error
        }

        return super.onOptionsItemSelected(item);
    }

    private void insertProjectConfirm(){

        final Bundle extras = getIntent().getExtras();
        Intent replyIntent = new Intent();
        if (TextUtils.isEmpty(et_nameProject.getText())) {
            //setResult(RESULT_CANCELED, replyIntent);
            Toast.makeText(this,R.string.project_obligatorio,Toast.LENGTH_SHORT).show();
            et_nameProject.setFocusable(true);
        } else {
            // Get the new project name that the user entered.
            String nameProject = et_nameProject.getText().toString();
            String locationProject = et_locationProject.getText().toString();
            String dateProject = d_dateCreated.getText().toString();
            replyIntent.putExtra(NAME_REPLY, nameProject);
            replyIntent.putExtra(LOCATION_REPLY, locationProject);
            replyIntent.putExtra(DATE_REPLY, dateProject);

            if (extras != null && extras.containsKey(PROJECT_DATA_ID_ITEM)) {
                long id = extras.getLong(PROJECT_DATA_ID_ITEM, -1);
                if (id != -1) {
                    replyIntent.putExtra(PROJECT_REPLY_ID, id);
                }
            }
            // Set the result status to indicate success.
            setResult(RESULT_OK, replyIntent);
            finish();
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
                .setPositiveButton("Si", (dialogInterface, i) -> insertProjectConfirm())
                .setNegativeButton("No", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    Intent replyIntent = new Intent();
                    setResult(RESULT_CANCELED, replyIntent);
                    finish();
                    //Intent intent = new Intent(Activity_add_project.this, ProjectActivity.class);
                    //startActivity(intent);
                });
        builder.show();
    }

}