package com.falceso.falcesoemmanuelpracticalexam4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.falceso.falcesoemmanuelpracticalexam4.models.AndroidVersion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    EditText etId, etCode,etDate, etApi;
    int i;
    ArrayList<AndroidVersion> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("AndroidVersions");

        etId = findViewById(R.id.main_text_versionnum);
        etCode = findViewById(R.id.main_text_codename);
        etDate = findViewById(R.id.main_text_releasedate);
        etApi = findViewById(R.id.main_text_apilvl);
        list = new ArrayList<AndroidVersion>();
    }

    public void add(View v){
        String id = myRef.push().getKey();
        String codeName = etCode.getText().toString();
        String releaseDate = etDate.getText().toString();
        String apiLevel = etApi.getText().toString();

        AndroidVersion androidVersion = new AndroidVersion(id, codeName, releaseDate, apiLevel);
        myRef.child(id).setValue(androidVersion);

    }

    public void delete(View v){
        String id = etId.getText().toString();
        myRef.child(id).removeValue();
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot androidObject: dataSnapshot.getChildren()){
                    AndroidVersion av = androidObject.getValue(AndroidVersion.class);
                    list.add(av);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void update(View v){
        String id = etId.getText().toString();
        String codeName = etCode.getText().toString();
        String releaseDate = etDate.getText().toString();
        String apiLevel = etApi.getText().toString();
        AndroidVersion androidVersion = new AndroidVersion(id, codeName, releaseDate, apiLevel);
        myRef.child(id).setValue(androidVersion);

    }

    public void moveFirst(View v){
        etId.setText(list.get(0).getId());
        etCode.setText(list.get(0).getCodeName());
        etDate.setText(list.get(0).getReleaseDate());
        etApi.setText(list.get(0).getApiLevel());
        i = 0;
    }

    public void movePrev(View v){
        etId.setText(list.get(--i).getId());
        etCode.setText(list.get(--i).getCodeName());
        etDate.setText(list.get(--i).getReleaseDate());
        etApi.setText(list.get(--i).getApiLevel());
    }

    public void moveNext(View v){
        etId.setText(list.get(++i).getId());
        etCode.setText(list.get(++i).getCodeName());
        etDate.setText(list.get(++i).getReleaseDate());
        etApi.setText(list.get(++i).getApiLevel());
    }
}
