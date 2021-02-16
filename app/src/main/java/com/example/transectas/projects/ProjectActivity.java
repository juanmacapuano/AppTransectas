package com.example.transectas.projects;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.transectas.AppViewModel;
import com.example.transectas.BaseActivity;
import com.example.transectas.R;
import com.example.transectas.data.Proyectos;
import com.example.transectas.data.Transectas;
import com.example.transectas.dialogos.DialogoAddItemFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.List;

public class ProjectActivity extends BaseActivity {

    private AppViewModel mViewModel;
    private ProjectsAdapter adapter;
    List<Transectas> allItemsDb;

    public static final int NEW_PROJECT_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_PROJECT_ACTIVITY_REQUEST_CODE = 2;

    public static final String NAME_UPDATE_PROJECT = "name_project_to_be_updated";
    public static final String LOCATION_UPDATE_PROJECT = "location_project_to_be_updated";
    public static final String DATE_UPDATE_PROJECT = "date_project_to_be_updated";
    public static final String PROJECT_DATA_ID = "extra_data_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyecto);
        mViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        setUpToolbar();

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(LinearLayoutManager.VERTICAL);

       adapter = new ProjectsAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(lim);

        mViewModel.getAllProjector().observe(this, projects -> adapter.setProyectos(projects));

        adapter.setOnItemClickListener((v, position) -> {
            Proyectos project = adapter.getProjectAtPosition(position);
            launchUpdateProjectActivity(project);
        });

        FloatingActionButton fab = findViewById(R.id.addMuestreo);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ProjectActivity.this, Activity_add_project.class);
            intent.putExtra("newProject",1);
            startActivityForResult(intent, NEW_PROJECT_ACTIVITY_REQUEST_CODE);
        });

        showHomeUpIcon();
    }

    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void showHomeUpIcon(){
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle(getString(R.string.projectTitle));
        }
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
            case R.id.new_search:

                break;
            case R.id.acerca_de:
                about(this);
                break;
            case R.id.addItemTabla:
                DialogoAddItemFragment dialogoAddItemFragment = new DialogoAddItemFragment();
                dialogoAddItemFragment.show(getSupportFragmentManager(), "DialogAddItem");
                break;
            case R.id.exportarDb:
                //exportDbTo();
                break;

            default:
                //Error
        }

        return super.onOptionsItemSelected(item);
    }



    private void exportDb(){

        Completable.fromAction(() -> mViewModel.getAllVisibilityList()).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        //listOperatorsBk = mViewModel.getAllVisibilityList();
                        allItemsDb = mViewModel.getAllTransects();
                        Gson gson = new Gson();
                        //Type type = new TypeToken<List<Valores>>(){}.getType();
                        //String json = gson.toJson(listOperatorsBk, type);
                        Type type = new TypeToken<List<Transectas>>(){}.getType();
                        String json = gson.toJson(allItemsDb, type);

                        try {
                            FileOutputStream out = openFileOutput("databaseTransectas.csv", Context.MODE_PRIVATE);
                            out.write(json.getBytes());
                            out.close();

                            //exporting
                            Context context = getApplicationContext();
                            File fileLocation = new File (getFilesDir(), "databaseTransectas.csv");
                            Uri path = FileProvider.getUriForFile(context, "com.example.transectas.fileprovider", fileLocation);
                            Intent fileIntent = new Intent(Intent.ACTION_SEND);
                            fileIntent.setType("text/cvs");
                            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Dababase");
                            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                            startActivity(Intent.createChooser(fileIntent, "down"));


                        } catch (Exception e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "Empty data",Toast.LENGTH_LONG).show();
                    }
                });

    }

    public void launchUpdateProjectActivity( Proyectos proyectos) {
        Intent intent = new Intent(this, Activity_item_project.class);
        intent.putExtra(NAME_UPDATE_PROJECT, proyectos.getNombre());
        intent.putExtra(LOCATION_UPDATE_PROJECT, proyectos.getUbicacion());
        intent.putExtra(DATE_UPDATE_PROJECT, proyectos.getFechaCreacion());
        intent.putExtra(PROJECT_DATA_ID, proyectos.getId());
        intent.putExtra("newProject",0);
        startActivity(intent);
        //startActivityForResult(intent, UPDATE_PROJECT_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_PROJECT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            long idNewProject;
            // Save the data.
            Proyectos newProject = new Proyectos(data.getStringExtra(Activity_add_project.NAME_REPLY),
                    data.getStringExtra(Activity_add_project.LOCATION_REPLY), data.getStringExtra(Activity_add_project.DATE_REPLY));

            idNewProject = mViewModel.insertProject(newProject);

            if (idNewProject > 0)
                Toast.makeText(this, R.string.project_saved,
                        Toast.LENGTH_LONG).show();
            else  Toast.makeText(this, R.string.unable_to_update,
                    Toast.LENGTH_LONG).show();
        } else if (requestCode == UPDATE_PROJECT_ACTIVITY_REQUEST_CODE
                && resultCode == RESULT_OK) {

            String name_data = data.getStringExtra(Activity_add_project.NAME_REPLY);
            String location_data = data.getStringExtra(Activity_add_project.LOCATION_REPLY);
            String date_data = data.getStringExtra(Activity_add_project.DATE_REPLY);
            long idProjectUpdate = data.getLongExtra(Activity_add_project.PROJECT_REPLY_ID, -1);

            if (idProjectUpdate != -1) {
                mViewModel.updateProject(new Proyectos(idProjectUpdate, name_data, location_data, date_data));
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