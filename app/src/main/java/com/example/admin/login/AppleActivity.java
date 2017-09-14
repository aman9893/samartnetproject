package com.example.admin.login;

/**
 * Created by admin on 8/28/2017.
 */
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AppleActivity extends AppCompatActivity  {
    EditText etName;
    EditText etRoll ;
    EditText  econtact;
    Spinner etAddress;
    Spinner etBranch;
    EditText etEmail;
    EditText edate;
    Button btnSubmit,btngetdata;
    DatabaseHelpher helpher;
    List<DatabaseModel> dbList;
    private ArrayList<String> type;
    private ArrayList<String> subject;
    private String typeStr,subjectStr;
    private String imgDecodableString;
    private Calendar myCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.first);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            dbList = new ArrayList<DatabaseModel>();
            etName = (EditText) findViewById(R.id.etName);
            etRoll = (EditText) findViewById(R.id.etRoll);
            econtact=(EditText) findViewById(R.id.contact);
            etAddress = (Spinner) findViewById(R.id.etAddress);
            etBranch = (Spinner) findViewById(R.id.etBranch);
            etEmail = (EditText) findViewById(R.id.etEmail);
            edate = (EditText) findViewById(R.id.date);
            btnSubmit = (Button) findViewById(R.id.btnSubmit);
            btngetdata = (Button) findViewById(R.id.btngetdata);

            type = new ArrayList<String>();
            // type.add("Automobile");
            // type.add("Business Services");
            //  type.add("Computers");
            type.add("Select");
            type.add("Question");
            type.add("Problem");
             type.add("Request");

            subject = new ArrayList<String>();
            subject.add("Select");
            subject.add("SapB1");
            subject.add("Product");
            subject.add("Query");
             subject.add("Hardware");
             subject.add("software");
             subject.add("microsoft");


            ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);
            ArrayAdapter<String> subjectAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subject);
            typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            etAddress.setAdapter(typeAdapter);
            etBranch.setAdapter(subjectAdapter);

            etAddress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    typeStr = type.get(position);

                   // Toast.makeText(AppleActivity.this, "Type= " + typeStr, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });




             myCalendar = Calendar.getInstance();

            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();
                }

            };

            edate.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    new DatePickerDialog(AppleActivity.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });

          
            etBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    subjectStr = subject.get(position);

                  //  Toast.makeText(AppleActivity.this, "Subject= " + subjectStr, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            btngetdata.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(AppleActivity.this, Secondactivity.class));


                    // startActivity(new Intent(MainActivity.this, DetailsActivity.class));

                }
            });

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name = etName.getText().toString();
                    String email = etEmail.getText().toString();
                    String roll = etRoll.getText().toString();
                    String contact = econtact.getText().toString();
                    String date = edate.getText().toString();
                    if (name.equals("") || email.equals("") || roll.equals("") || typeStr.equals("")|| contact.equals("")|| date.equals("") || subjectStr.equals("")) {
                        Toast.makeText(AppleActivity.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                    } else {
                        helpher = new DatabaseHelpher(AppleActivity.this);
                        helpher.insertIntoDB(name, email, roll ,contact,date,typeStr, subjectStr,imgDecodableString);
                    }
                    etName.setText("");
                    etRoll.setText("");
                    econtact.setText("");
                    etAddress.setSelection(0);
                    etBranch.setSelection(0);
                    etEmail.setText("");
                    edate.setText("");

                    Toast.makeText(AppleActivity.this, "insert value", Toast.LENGTH_LONG);

                }
            });
        }catch(Exception e){
            Log.e("Error",e.getMessage());
        }

    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edate.setText(sdf.format(myCalendar.getTime()));
    }

    private static int RESULT_LOAD_IMG = 1;

    //private int PICK_Camera_IMAGE=1;
    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

       // Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

       //startActivityForResult(intent, PICK_Camera_IMAGE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                Toast.makeText(AppleActivity.this,imgDecodableString,Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(this, "You haven't picked Image",
                     //  Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}