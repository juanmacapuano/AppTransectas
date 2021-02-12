package com.example.transectas.dialogos;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.transectas.AppViewModel;
import com.example.transectas.R;
import com.example.transectas.data.Tabla;
import com.example.transectas.data.TablaValor;
import com.example.transectas.data.Valores;
import com.example.transectas.data.ValoresDao;


public class DialogoAddItemFragment extends DialogFragment {

    Activity activity;
    ImageButton btnSalir;
    LinearLayout barraSuperior;
    Button btnAceptar, btnCancelar;
    Spinner tablas;
    EditText et_valorItem;
    private AppViewModel mViewModel;

    public DialogoAddItemFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogoAddItem();
    }

    private AlertDialog crearDialogoAddItem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialogo_add_item, null);
        builder.setView(v);

        mViewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        barraSuperior = v.findViewById(R.id.barraSuperior);
        btnSalir = v.findViewById(R.id.btnSalir);
        btnAceptar = v.findViewById(R.id.btn_aceptar);
        btnCancelar = v.findViewById(R.id.btn_cancelar);
        tablas = v.findViewById(R.id.comboTabla);
        et_valorItem = v.findViewById(R.id.et_NombreItem);
        Tabla[] tablaLista;
        tablaLista = mViewModel.getmAllTablas();
        tablas.setAdapter(new ArrayAdapter<Tabla>(activity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,tablaLista));
        eventosBotones();

        return builder.create();
    }

    private void eventosBotones() {

        final Tabla[] item = {new Tabla("")};

        tablas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item[0] = (Tabla) tablas.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                item[0] = (Tabla) tablas.getSelectedItem();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_valorItem.getText().toString().trim() == "" || et_valorItem.getText().equals("") || et_valorItem.getText().length() == 0){
                    Toast.makeText(getContext(),"Debe ingresar un valor",Toast.LENGTH_SHORT).show();
                }else {
                    Long idTabla = item[0].getId();
                    Valores valor = new Valores();
                    valor.setValor(et_valorItem.getText().toString());
                    Long idValor = mViewModel.insertItemValor(valor);
                    TablaValor tablaValor = new TablaValor(idTabla, idValor);
                    Long idTablaValor = mViewModel.insertTablaValor(tablaValor);
                    Toast.makeText(getContext(),"Se ha agregado el valor",Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.activity = (Activity) context;
           // iComunicaFragments = (IComunicaFragments) this.activity;
        }else {
            throw new RuntimeException(context.toString()
                    + "must implements OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialogo_add_item, container, false);
    }
}