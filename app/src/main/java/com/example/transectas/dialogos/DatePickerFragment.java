package com.example.transectas.dialogos;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.transectas.R;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog.OnDateSetListener listener;
    Activity activity;
    private EditText d_fechaCreacion;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        d_fechaCreacion = getActivity().findViewById(R.id.d_fechaTransectaItem);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        datePickerDialog.updateDate(year, month, day);
        // Create a new instance of DatePickerDialog and return it
        return datePickerDialog;
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

    public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        final String selectedDate = twoDigits(i2) + " / " + twoDigits(i1+1) + " / " + twoDigits(i);
        d_fechaCreacion.setText(selectedDate);
        datePicker.updateDate(i2,i1,i);
    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }
}
