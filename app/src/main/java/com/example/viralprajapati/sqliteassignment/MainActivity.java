package com.example.viralprajapati.sqliteassignment;


import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseClass db;
    EditText name, tvShow, email, id;
    Button Add, View, Update, Del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseClass(this);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        tvShow = (EditText) findViewById(R.id.favAnimal);
        id = (EditText) findViewById(R.id.id);
        Add = (Button) findViewById(R.id.Add);
        View = (Button) findViewById(R.id.View);
        Update = (Button) findViewById(R.id.Update);
        Del = (Button) findViewById(R.id.Del);
        addData();
        getData();
        updateData();
        deleteData();
    }

    public void addData() {
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().length() == 0) {
                    Toast.makeText(MainActivity.this,"Enter Name...!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(email.getText().length() == 0) {
                    Toast.makeText(MainActivity.this,"Enter Email...!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(tvShow.getText().length() == 0) {
                    Toast.makeText(MainActivity.this,"Enter Animal Name...!", Toast.LENGTH_LONG).show();
                    return;
                }
                boolean result = db.insertData(name.getText().toString(), email.getText().toString(), tvShow.getText().toString());
                if(result == true) {
                    Toast.makeText(MainActivity.this,"Data Added!", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Data NOT Inserted...!", Toast.LENGTH_LONG).show();
                name.setText(null);
                email.setText(null);
                tvShow.setText(null);
            }
        });
    }

    public void getData() {
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor answer = db.getData();
                if(answer.getCount() == 0) {
                    viewData("ERROR!", "Table Empty...!");
                    return;
                }

                StringBuffer sb = new StringBuffer();
                while(answer.moveToNext()) {
                    sb.append("ID: " + answer.getString(0) + "\n");
                    sb.append("Name: " + answer.getString(1) + "\n");
                    sb.append("Email: " + answer.getString(2) + "\n");
                    sb.append("Favorite TV Show: " + answer.getString(3) + "\n");
                    sb.append("\n");
                }

                viewData("Data of Table",sb.toString());

            }
        });
    }

    public void viewData(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateData() {
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.getText().length() == 0) {
                    Toast.makeText(MainActivity.this,"Enter Id...!", Toast.LENGTH_LONG).show();
                    return;
                }
                boolean result = db.updateData(id.getText().toString(),name.getText().toString(), email.getText().toString(), tvShow.getText().toString());
                if(result == true) {
                    Toast.makeText(MainActivity.this,"Update complete...!", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Data NOT Updated...!", Toast.LENGTH_LONG).show();
                name.setText(null);
                email.setText(null);
                tvShow.setText(null);
                id.setText(null);
            }
        });
    }

    public void deleteData() {
        Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.getText().length() == 0) {
                    Toast.makeText(MainActivity.this,"Enter Id...!", Toast.LENGTH_LONG).show();
                    return;
                }
                Integer result = db.deleteData(id.getText().toString());
                if(result > 0) {
                    Toast.makeText(MainActivity.this,"Entry Deleted...!", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Data NOT Deleted...!", Toast.LENGTH_LONG).show();
                id.setText(null);
            }
        });
    }


}

