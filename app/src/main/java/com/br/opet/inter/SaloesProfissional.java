package com.br.opet.inter;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class SaloesProfissional extends AppCompatActivity {


    private FirebaseAuth auth;
    private ListView listarSalao;
    private FirebaseFirestore db;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    private List<ObjSalao> listSalao = new ArrayList<ObjSalao>();
    private ArrayAdapter<ObjSalao> arrayAdapterSalao;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saloesprofissional);
        auth = FirebaseAuth.getInstance();
        iniciarFirebase();
        eventoDatabase();
        listarSalao = findViewById(R.id.listSalao);
    }

    private void eventoDatabase() {
        referencia.child("Salao").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listSalao.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    ObjSalao s = objSnapshot.getValue(ObjSalao.class);
                    listSalao.add(s);


                }
            arrayAdapterSalao = new ArrayAdapter<ObjSalao>(SaloesProfissional.this,
                    android.R.layout.simple_list_item_1,listSalao );
                listarSalao.setAdapter(arrayAdapterSalao);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void iniciarFirebase() {

        db = FirebaseFirestore.getInstance();
        FirebaseApp.initializeApp(SaloesProfissional.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        referencia = firebaseDatabase.getReference();
    }


}