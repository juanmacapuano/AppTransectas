package com.example.transectas.dialogos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.transectas.AppViewModel;
import com.example.transectas.findings.ListFindings;
import com.example.transectas.R;
import com.example.transectas.data.Muestreos;
import com.example.transectas.data.Valores;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EnvironmentSedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnvironmentSedFragment extends Fragment {

    private Spinner sp_pendingFrag, sp_potentialEntFrag;
    private Valores itemSpinnerSelectedPending, itemSpinnerSelectedPotentialEnt;
    private List<Valores> as_pendingSpinnerList, as_PotentialEntSpinnerList;
    private AppViewModel mViewModel;
    private EditText et_nroSampling, et_environmentSed, et_sedimentTypo, et_potentialEntObs, et_presenceWaterObs;
    private CheckBox ck_erosion, ck_depositacion, ck_pedogenesis, ck_absence, ck_humedity, ck_encharcamiento, ck_escorrentia, ck_canal_as;
    //bioturbacion
    private CheckBox ck_vegetables, ck_rodents, ck_pisoteo, ck_carnivores, ck_others_bio;

    private EditText et_descriptionBio;

    private Integer newSampling = -1;
    private Muestreos samplingUpdate;
    private View root;

    public static EnvironmentSedFragment newInstance() {
        EnvironmentSedFragment fragment = new EnvironmentSedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemSpinnerSelectedPending = new Valores();
        itemSpinnerSelectedPotentialEnt = new Valores();
        mViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        samplingUpdate = new Muestreos();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_ambiente_sed, container, false);

        et_nroSampling = root.findViewById(R.id.et_nroMuestreo);
        et_environmentSed = root.findViewById(R.id.et_ambienteSed);
        et_sedimentTypo = root.findViewById(R.id.et_sedimentoTipo);
        et_potentialEntObs = root.findViewById(R.id.et_potencialEntObs);
        et_presenceWaterObs = root.findViewById(R.id.et_presenciaAgua_obs);

        ck_erosion = root.findViewById(R.id.ck_erosion);
        ck_depositacion = root.findViewById(R.id.ck_depositacion);
        ck_pedogenesis = root.findViewById(R.id.ck_pedogenesis);
        ck_absence = root.findViewById(R.id.ck_ausencia);
        ck_humedity = root.findViewById(R.id.ck_humedad);
        ck_encharcamiento = root.findViewById(R.id.ck_encharcamiento);
        ck_escorrentia = root.findViewById(R.id.ck_escorrentia);
        ck_canal_as = root.findViewById(R.id.ck_canal_as);

        sp_pendingFrag = root.findViewById(R.id.sp_pendiente);

        as_pendingSpinnerList = null;
        as_pendingSpinnerList = mViewModel.getAllPendentList();
        ArrayAdapter<Valores> arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),R.layout.item_spinner, as_pendingSpinnerList);
        sp_pendingFrag.setAdapter(arrayAdapter);
        //arrayAdapter.notifyDataSetChanged();

        sp_pendingFrag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemSpinnerSelectedPending = (Valores) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_potentialEntFrag = root.findViewById(R.id.sp_potencialEnt);
        as_PotentialEntSpinnerList = mViewModel.getAllPotentialEntList();
        ArrayAdapter<Valores> arrayAdapterPotEnt = new ArrayAdapter<>(getActivity(), R.layout.item_spinner, as_PotentialEntSpinnerList);
        sp_potentialEntFrag.setAdapter(arrayAdapterPotEnt);

        sp_potentialEntFrag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemSpinnerSelectedPotentialEnt = (Valores) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ck_vegetables = root.findViewById(R.id.ck_vegetales);
        ck_rodents = root.findViewById(R.id.ck_roedores);
        ck_pisoteo = root.findViewById(R.id.ck_pisoteo);
        ck_carnivores = root.findViewById(R.id.ck_carnivoros);
        ck_others_bio = root.findViewById(R.id.ck_otros_bio);
        et_descriptionBio = root.findViewById(R.id.et_descripcionBio);

        setSamplingUpdate();

        return root;
    }

    private void setSamplingUpdate(){
        samplingUpdate = ((TabsSampling) getActivity()).getData();
        if (samplingUpdate != null) {
            et_nroSampling.setText(samplingUpdate.getNumero().toString());
            et_nroSampling.setFocusable(false);
            et_nroSampling.setEnabled(false);
            et_nroSampling.setCursorVisible(false);
            et_nroSampling.setKeyListener(null);
            et_nroSampling.setBackgroundColor(Color.TRANSPARENT);

            et_environmentSed.setText(samplingUpdate.getAs_descripcion());

            FloatingActionButton fab = root.findViewById(R.id.btn_fa_listHallazgo);
            fab.setOnClickListener(view -> {
                Intent intent = new Intent(getContext(), ListFindings.class);
                intent.putExtra("idMuestreo", samplingUpdate.getId());
                startActivity(intent);
            });

            Valores item;
            Long itemUpdate;
            List<Long> spinnerPending;
            spinnerPending = samplingUpdate.getAs_pendiente();
            if (spinnerPending.size() > 0){
                itemUpdate = spinnerPending.get(0);
                for (int i = 0; i < as_pendingSpinnerList.size(); i++) {
                    item = as_pendingSpinnerList.get(i);
                    if (item.getId() == itemUpdate) {
                        sp_pendingFrag.setSelection(i);
                    }
                }
            }

            et_sedimentTypo.setText(samplingUpdate.getAs_sedimento_tipo());

            Integer erosionCk = -1;
            erosionCk = samplingUpdate.getAs_pd_erosion();
            if (erosionCk == 1)
                ck_erosion.setChecked(true);

            Integer depositacionCk = -1;
            depositacionCk = samplingUpdate.getAs_pd_depositacion();
            if (depositacionCk == 1)
                ck_depositacion.setChecked(true);

            Integer pedogenesisCk = -1;
            pedogenesisCk = samplingUpdate.getAs_pd_pedogenesis();
            if (pedogenesisCk == 1)
                ck_pedogenesis.setChecked(true);

            item = new Valores();
            itemUpdate = null;
            List<Long> spinnerPotencial = new ArrayList<Long>();
            spinnerPotencial = samplingUpdate.getAs_potencial_enterramiento();
            if (spinnerPotencial.size() > 0){
                itemUpdate = spinnerPotencial.get(0);
                for (int i = 0; i < as_PotentialEntSpinnerList.size(); i++) {
                    item = as_PotentialEntSpinnerList.get(i);
                    if (item.getId() == itemUpdate) {
                        sp_potentialEntFrag.setSelection(i);
                    }
                }
            }

            et_potentialEntObs.setText(samplingUpdate.getAs_pe_observaciones());

            Integer ausenciaCk = -1;
            ausenciaCk = samplingUpdate.getAs_pa_ausencia();
            if (ausenciaCk == 1)
                ck_absence.setChecked(true);

            Integer humedadCk = -1;
            humedadCk = samplingUpdate.getAs_pa_humedad();
            if (humedadCk == 1)
                ck_humedity.setChecked(true);

            Integer encharcamientoCk = -1;
            encharcamientoCk = samplingUpdate.getAs_pa_encharcamiento();
            if (encharcamientoCk == 1)
                ck_encharcamiento.setChecked(true);

            Integer escorrentiaCk = -1;
            escorrentiaCk = samplingUpdate.getAs_pa_escorrentia();
            if (escorrentiaCk == 1)
                ck_escorrentia.setChecked(true);

            Integer pacanalCk = -1;
            pacanalCk = samplingUpdate.getAs_pa_canal();
            if (pacanalCk == 1)
                ck_canal_as.setChecked(true);

            et_presenceWaterObs.setText(samplingUpdate.getAs_pa_observaciones());

            Integer bioVegCk = -1;
            bioVegCk = samplingUpdate.getBio_vegetales();
            if (bioVegCk == 1)
                ck_vegetables.setChecked(true);

            Integer bioRoedCk = -1;
            bioRoedCk = samplingUpdate.getBio_roedores();
            if (bioRoedCk == 1)
                ck_rodents.setChecked(true);

            Integer bioPisoteoCk = -1;
            bioPisoteoCk = samplingUpdate.getBio_pisoteo();
            if (bioPisoteoCk == 1)
                ck_pisoteo.setChecked(true);

            Integer bioCanrnivorosCk = -1;
            bioCanrnivorosCk = samplingUpdate.getBio_carnivoros();
            if (bioCanrnivorosCk == 1)
                ck_carnivores.setChecked(true);

            Integer bioOtrosCk = -1;
            bioOtrosCk = samplingUpdate.getBio_otros();
            if (bioOtrosCk == 1)
                ck_others_bio.setChecked(true);

            et_descriptionBio.setText(samplingUpdate.getBio_descripcion());
        }

    }

    public Muestreos getSampling(){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        final String dateCreation = twoDigits(day) + " / " + twoDigits(month+1) + " / " + twoDigits(year);

        Muestreos sampling = new Muestreos();
        if (et_nroSampling.length() != 0){
            newSampling = Integer.parseInt(et_nroSampling.getText().toString());
        }
        sampling.setNumero(newSampling);
        sampling.setAs_descripcion(et_environmentSed.getText().toString());
        sampling.setFecha_creacion(dateCreation);
        sampling.setAs_sedimento_tipo(et_sedimentTypo.getText().toString());
        sampling.setAs_pe_observaciones(et_potentialEntObs.getText().toString());
        sampling.setAs_pa_observaciones(et_presenceWaterObs.getText().toString());

        if (ck_erosion.isChecked()){
            sampling.setAs_pd_erosion(1);
        } else sampling.setAs_pd_erosion(0);

        if (ck_depositacion.isChecked()){
            sampling.setAs_pd_depositacion(1);
        } else sampling.setAs_pd_depositacion(0);

        if (ck_pedogenesis.isChecked()){
            sampling.setAs_pd_pedogenesis(1);
        } else sampling.setAs_pd_pedogenesis(0);

        if (ck_absence.isChecked()){
            sampling.setAs_pa_ausencia(1);
        } else sampling.setAs_pa_ausencia(0);

        if (ck_humedity.isChecked()){
            sampling.setAs_pa_humedad(1);
        } else sampling.setAs_pa_humedad(0);

        if (ck_encharcamiento.isChecked()){
            sampling.setAs_pa_encharcamiento(1);
        } else sampling.setAs_pa_encharcamiento(0);

        if (ck_escorrentia.isChecked()){
            sampling.setAs_pa_escorrentia(1);
        } else sampling.setAs_pa_escorrentia(0);

        if (ck_canal_as.isChecked()){
            sampling.setAs_pa_canal(1);
        } else sampling.setAs_pa_canal(0);

        long idSpinnerPending = -1;
        List<Long> spinnerPending = new ArrayList<Long>();
        if (itemSpinnerSelectedPending != null) {
            idSpinnerPending = itemSpinnerSelectedPending.getId();
            if (idSpinnerPending != -1) {
                spinnerPending.add(idSpinnerPending);
                sampling.setAs_pendiente(spinnerPending);
            }
        }

        long idSpinnerPotential = -1;
        List<Long> spinnerPotential = new ArrayList<Long>();
        if (itemSpinnerSelectedPotentialEnt != null) {
            idSpinnerPotential = itemSpinnerSelectedPotentialEnt.getId();
            if (idSpinnerPotential != -1) {
                spinnerPotential.add(idSpinnerPotential);
                sampling.setAs_potencial_enterramiento(spinnerPotential);
            }
        }

        if (ck_vegetables.isChecked()){
            sampling.setBio_vegetales(1);
        } else sampling.setBio_vegetales(0);

        if (ck_rodents.isChecked()){
            sampling.setBio_roedores(1);
        } else sampling.setBio_roedores(0);

        if (ck_pisoteo.isChecked()){
            sampling.setBio_pisoteo(1);
        } else sampling.setBio_pisoteo(0);

        if (ck_carnivores.isChecked()){
            sampling.setBio_carnivoros(1);
        } else sampling.setBio_carnivoros(0);

        if (ck_others_bio.isChecked()){
            sampling.setBio_otros(1);
        } else sampling.setBio_otros(0);

        sampling.setBio_descripcion(et_descriptionBio.getText().toString());

        return sampling;
    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }
}