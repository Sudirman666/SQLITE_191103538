package com.example.sqlite_191103538;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    EditText firstname, lastname;
    TextView textView;
    DB_controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstname = (EditText) findViewById(R.id.editFirstName);
        lastname = (EditText) findViewById(R.id.editLastName);
        textView = (TextView) findViewById(R.id.textView);

        controller = new DB_controller(this, "", null, 1);

    }
    public void btn_click(View view){
        switch (view.getId()){
            case R.id.btnAdd:
                try{
                    controller.insert_student(firstname.getText().toString(), lastname.getText().toString());
                }catch (SQLiteException e){
                    Toast.makeText(MainActivity.this, "ALREADY EXIST", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnDelete:
                controller.delete_student(firstname.getText().toString());
                break;
            case R.id.btnList:
                controller.list_all_student(textView);
                break;
            case R.id.btnUpdate:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Masukan Nama");

                final EditText newFirstName = new EditText(this);
                dialog.setView(newFirstName);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        controller.update_student(firstname.getText().toString(), newFirstName.getText().toString());
                    }
                });
                dialog.show();
                break;
        }
    }
}