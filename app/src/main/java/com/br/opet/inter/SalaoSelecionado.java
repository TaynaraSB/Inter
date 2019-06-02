package com.br.opet.inter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class SalaoSelecionado extends AppCompatActivity {

    private List<ObjSalao> listSalao = new ArrayList<ObjSalao>();
    private ArrayAdapter<ObjSalao> arrayAdapterSalao;
    private FirebaseAuth auth;
    private TextView listarSalao,listarNumero;
    private FirebaseFirestore db;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private String salaoSelecionado;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String userLogado = user.getUid();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salaoselecionado);
        auth = FirebaseAuth.getInstance();
        Intent iin = getIntent();
        String nomeSalaoSelecionado = (String) iin.getSerializableExtra("Nome");
        String chamadaSenha = (String) iin.getSerializableExtra("senhaChamada");

        listarNumero = findViewById(R.id.listarNumero);
        listarSalao = findViewById(R.id.nomeSalao);

        TextView Uid = (TextView) findViewById(R.id.nomeSalao);
        TextView senhaChamada = (TextView) findViewById(R.id.listarNumero);

        Uid.setText(nomeSalaoSelecionado);
        senhaChamada.setText(chamadaSenha);
        iniciarFirebase();

     /*    eventoDatabase(); */
    }
    /*
    private void eventoDatabase() {
        referencia.child("Salao").child(userLogado).child(salaoSelecionado).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listSalao.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    ObjSalao s = objSnapshot.getValue(ObjSalao.class);

                    listSalao.add(s);
                }
                arrayAdapterSalao = new ArrayAdapter<ObjSalao>(SalaoSelecionado.this,
                        android.R.layout.simple_list_item_1, listSalao);
                listarSalao.setAdapter(arrayAdapterSalao);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/


    private void iniciarFirebase() {

        db = FirebaseFirestore.getInstance();
        FirebaseApp.initializeApp(SalaoSelecionado.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        referencia = firebaseDatabase.getReference();
    }

}
