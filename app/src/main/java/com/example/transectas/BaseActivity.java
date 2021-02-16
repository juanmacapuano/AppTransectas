package com.example.transectas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.ajts.androidmads.library.SQLiteToExcel;

import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public String selectedDate = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        selectedDate = twoDigits(day) + " / " + twoDigits(month+1) + " / " + twoDigits(year);

    }

    public void showHomeUpIcon(String title){
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }

    public void exportDbTo() {

        SQLiteToExcel sqLiteToExcel = new SQLiteToExcel(getApplicationContext(), "app_database");

        sqLiteToExcel.exportAllTables("app_database.xls", new SQLiteToExcel.ExportListener() {

            @Override
            public void onStart() {

            }
            @Override
            public void onCompleted(String filePath) {
                Toast.makeText(BaseActivity.this, R.string.bdExport, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(Exception e) {
                Toast.makeText(BaseActivity.this, R.string.bdExportError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }

    public void about(Context context) {
        Intent intent = new Intent(context, Activity_about.class);
        startActivity(intent);
    }
}
