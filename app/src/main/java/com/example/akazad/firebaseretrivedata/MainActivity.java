package com.example.akazad.firebaseretrivedata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
   // private Button insertData, displayData;
    DatabaseReference myRef;
    FirebaseDatabase database;
    TextView displayText;
    EditText nameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        displayText=findViewById(R.id.dataDispalyId);
        nameEditText=findViewById(R.id.nameEditTextId);
// Write a message to the database
        database = FirebaseDatabase.getInstance();
         myRef = database.getReference("message");




    }

    public void insartData(View view) {

        String name=nameEditText.getText().toString();
        myRef.setValue(name);
        Toast.makeText(getApplicationContext(), "Insert Successfully", Toast.LENGTH_SHORT).show();

    }

    public void displayData(View view) {

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               // String name=displayText.getText().toString();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                displayText.setText(value);
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this, "Faild", Toast.LENGTH_LONG).show();
            }
        });

    }
}
