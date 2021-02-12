package com.example.transectas.dialogos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.transectas.AppViewModel;
import com.example.transectas.R;
import com.example.transectas.data.Muestreos;
import com.example.transectas.data.Valores;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class VegetationFragment extends Fragment {

    private Spinner sp_veg_visibility;
    private Valores itemSpinnerSelectedVegVisibility;
    private List<Valores> veg_visibilitySpinnerList;
    private AppViewModel mViewModel;
    private static final String ARG_SECTION_NUMBER = "section_number";

    private EditText et_veg_distribution, et_typoVeget, et_presenceFauna, et_pm_others, et_pm_observations, et_descriptionAcc;
    private Switch sw_pm_litic, sw_pm_faunistico, sw_pm_ceramic, sw_pm_gress, sw_pm_glass;
    private CheckBox ck_ganaderia, ck_agricultura, ck_alambrado, ck_construction, ck_way, ck_terraplenes, ck_canal_acc, ck_waste, ck_others_aa;

    private Muestreos samplingUpdate;

    public static VegetationFragment newInstance() {
        VegetationFragment fragment = new VegetationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemSpinnerSelectedVegVisibility = new Valores();
        mViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        samplingUpdate = new Muestreos();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_vegetacion, container, false);

        et_typoVeget = root.findViewById(R.id.et_tipoVeget);
        et_veg_distribution = root.findViewById(R.id.et_veg_distribucion);
        et_presenceFauna = root.findViewById(R.id.et_presenciaFauna);
        et_pm_others = root.findViewById(R.id.et_pm_otros);
        et_pm_observations = root.findViewById(R.id.et_pm_observaciones);

        sw_pm_litic = root.findViewById(R.id.sw_pm_litico);
        sw_pm_faunistico = root.findViewById(R.id.sw_pm_faunistico);
        sw_pm_ceramic = root.findViewById(R.id.sw_pm_ceramica);
        sw_pm_gress = root.findViewById(R.id.sw_pm_gress);
        sw_pm_glass = root.findViewById(R.id.sw_pm_vidrio);

        sp_veg_visibility = root.findViewById(R.id.sp_veg_visibilidad);
        veg_visibilitySpinnerList = mViewModel.getAllVisibilityList();
        ArrayAdapter<Valores> arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),R.layout.item_spinner, veg_visibilitySpinnerList);
        sp_veg_visibility.setAdapter(arrayAdapter);

        sp_veg_visibility.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemSpinnerSelectedVegVisibility = (Valores) adapterView.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ck_ganaderia = root.findViewById(R.id.ck_ganaderia);
        ck_agricultura = root.findViewById(R.id.ck_agricultura);
        ck_alambrado = root.findViewById(R.id.ck_alambrado);
        ck_construction = root.findViewById(R.id.ck_construccion);
        ck_way = root.findViewById(R.id.ck_camino);
        ck_terraplenes = root.findViewById(R.id.ck_terraplenes);
        ck_canal_acc = root.findViewById(R.id.ck_canal_acc);
        ck_waste = root.findViewById(R.id.ck_desechos);
        ck_others_aa = root.findViewById(R.id.ck_otros_aa);
        et_descriptionAcc = root.findViewById(R.id.et_descripcionAcc);

        setSamplingUpdate();

        return root;

    }

    private void setSamplingUpdate() {
        samplingUpdate = ((TabsSampling) getActivity()).getData();
        if (samplingUpdate != null) {
            et_typoVeget.setText(samplingUpdate.getVe_tipo());

            et_veg_distribution.setText(samplingUpdate.getVe_distribucion());

            Valores item = new Valores();
            Long itemUpdate;
            List<Long> spinnerVisibility;
            spinnerVisibility = samplingUpdate.getVe_visibilidad();
            if (spinnerVisibility.size() > 0) {
                itemUpdate = spinnerVisibility.get(0);
                for (int i = 0; i < veg_visibilitySpinnerList.size(); i++) {
                    item = veg_visibilitySpinnerList.get(i);
                    if (item.getId() == itemUpdate) {
                        sp_veg_visibility.setSelection(i);
                    }
                }
            }

            et_presenceFauna.setText(samplingUpdate.getPresencia_fauna());

            Integer ganaderiaCk = -1;
            ganaderiaCk = samplingUpdate.getAa_ganaderia();
            if (ganaderiaCk == 1)
                ck_ganaderia.setChecked(true);

            Integer agriculturaCk = -1;
            agriculturaCk = samplingUpdate.getAa_agricultura();
            if (agriculturaCk == 1)
                ck_agricultura.setChecked(true);

            Integer alambradoCk = -1;
            alambradoCk = samplingUpdate.getAa_alambrado();
            if (alambradoCk == 1)
                ck_alambrado.setChecked(true);

            Integer constructionCk = -1;
            constructionCk = samplingUpdate.getAa_construccion();
            if (constructionCk == 1)
                ck_construction.setChecked(true);

            Integer wayCk = -1;
            wayCk = samplingUpdate.getAa_camino();
            if (wayCk == 1)
                ck_way.setChecked(true);

            Integer terraplenesCk = -1;
            terraplenesCk = samplingUpdate.getAa_terraplenes();
            if (terraplenesCk == 1)
                ck_terraplenes.setChecked(true);

            Integer canalAaCk = -1;
            canalAaCk = samplingUpdate.getAa_canal();
            if (canalAaCk == 1)
                ck_canal_acc.setChecked(true);

            Integer wasteCk = -1;
            wasteCk = samplingUpdate.getAa_desechos();
            if (wasteCk == 1)
                ck_waste.setChecked(true);

            Integer othersAaCk = -1;
            othersAaCk = samplingUpdate.getAa_otros();
            if (othersAaCk == 1)
                ck_others_aa.setChecked(true);

            et_descriptionAcc.setText(samplingUpdate.getAa_descripcion());

            Integer liticCk = -1;
            liticCk = samplingUpdate.getPm_litico();
            if (liticCk == 1)
                sw_pm_litic.setChecked(true);

            Integer pmFauniCk = -1;
            pmFauniCk = samplingUpdate.getPm_faunistico();
            if (pmFauniCk == 1)
                sw_pm_faunistico.setChecked(true);

            Integer ceramicCk = -1;
            ceramicCk = samplingUpdate.getPm_ceramica();
            if (ceramicCk == 1)
                sw_pm_ceramic.setChecked(true);

            Integer gressCk = -1;
            gressCk = samplingUpdate.getPm_gress();
            if (gressCk == 1)
                sw_pm_gress.setChecked(true);

            Integer glassCk = -1;
            glassCk = samplingUpdate.getPm_vidrio();
            if (glassCk == 1)
                sw_pm_glass.setChecked(true);

            et_pm_others.setText(samplingUpdate.getPm_otros());

            et_pm_observations.setText(samplingUpdate.getPm_observacion());
        }
    }

    public Muestreos getSampling(){

        Muestreos sampling = new Muestreos();

        sampling.setVe_tipo(et_typoVeget.getText().toString());
        sampling.setVe_distribucion(et_veg_distribution.getText().toString());

        long idSpinnerVisibility = -1;
        List<Long> spinnerVisibility = new ArrayList<>();
        if (itemSpinnerSelectedVegVisibility != null) {
            idSpinnerVisibility = itemSpinnerSelectedVegVisibility.getId();
            if (idSpinnerVisibility != -1) {
                spinnerVisibility.add(idSpinnerVisibility);
                sampling.setVe_visibilidad(spinnerVisibility);
            }
        }

        sampling.setPresencia_fauna(et_presenceFauna.getText().toString());

        if (sw_pm_litic.isChecked()){
            sampling.setPm_litico(1);
        } else sampling.setPm_litico(0);

        if (sw_pm_faunistico.isChecked()){
            sampling.setPm_faunistico(1);
        } else sampling.setPm_faunistico(0);

        if (sw_pm_ceramic.isChecked()){
            sampling.setPm_ceramica(1);
        } else sampling.setPm_ceramica(0);

        if (sw_pm_gress.isChecked()){
            sampling.setPm_gress(1);
        } else sampling.setPm_gress(0);

        if (sw_pm_glass.isChecked()){
            sampling.setPm_vidrio(1);
        } else sampling.setPm_vidrio(0);

        sampling.setPm_otros(et_pm_others.getText().toString());
        sampling.setPm_observacion(et_pm_observations.getText().toString());

        if (ck_ganaderia.isChecked()){
            sampling.setAa_ganaderia(1);
        } else sampling.setAa_ganaderia(0);

        if (ck_agricultura.isChecked()){
            sampling.setAa_agricultura(1);
        } else sampling.setAa_agricultura(0);

        if (ck_alambrado.isChecked()){
            sampling.setAa_alambrado(1);
        } else sampling.setAa_alambrado(0);

        if (ck_construction.isChecked()){
            sampling.setAa_construccion(1);
        } else sampling.setAa_construccion(0);

        if (ck_way.isChecked()){
            sampling.setAa_camino(1);
        } else sampling.setAa_camino(0);

        if (ck_terraplenes.isChecked()){
            sampling.setAa_terraplenes(1);
        } else sampling.setAa_terraplenes(0);

        if (ck_canal_acc.isChecked()){
            sampling.setAa_canal(1);
        } else sampling.setAa_canal(0);

        if (ck_waste.isChecked()){
            sampling.setAa_desechos(1);
        } else sampling.setAa_desechos(0);

        if (ck_others_aa.isChecked()){
            sampling.setAa_otros(1);
        } else sampling.setAa_otros(0);

        sampling.setAa_descripcion(et_descriptionAcc.getText().toString());

        return sampling;
    }
}
