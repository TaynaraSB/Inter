package com.br.opet.inter;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class SaloesDisponiveisUsuario extends AppCompatActivity {


    private ListView listarSalao;
    private FirebaseFirestore db;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    private List<ObjSalao> listSalao = new ArrayList<ObjSalao>();
    private ArrayAdapter<ObjSalao> arrayAdapterSalao;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saloes_all_usuario);
        iniciarFirebase();
        eventoDatabase();

        listarSalao = findViewById(R.id.listSalao);


       listarSalao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ObjSalao itemSelecionado = arrayAdapterSalao.getItem(position);

                Intent i = new Intent(SaloesDisponiveisUsuario.this, SalaoSelecionadoUsuario.class);
                i.putExtra("Uid", itemSelecionado.getUid());
                i.putExtra("senhaChamada", itemSelecionado.getNumero());
                i.putExtra("Nome", itemSelecionado.toString());
                startActivity(i);
            }
        });

    }

    private void eventoDatabase() {
        referencia.child("SalaoAll").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listSalao.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    ObjSalao s = objSnapshot.getValue(ObjSalao.class);
                    listSalao.add(s);

                }
                arrayAdapterSalao = new ArrayAdapter<ObjSalao>(SaloesDisponiveisUsuario.this,
                        android.R.layout.simple_list_item_1, listSalao);
                listarSalao.setAdapter(arrayAdapterSalao);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void iniciarFirebase() {

        db = FirebaseFirestore.getInstance();
        FirebaseApp.initializeApp(SaloesDisponiveisUsuario.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        referencia = firebaseDatabase.getReference();
    }

}
