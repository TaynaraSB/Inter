package com.br.opet.inter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class SalaoSelecionado extends AppCompatActivity {

    private FirebaseAuth auth;
    private ListView listarSalao;
    private FirebaseFirestore db;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private int salaoselecionado;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* setContentView(R.layout.salaoselecionado); */
        auth = FirebaseAuth.getInstance();
        Intent iin=getIntent();
        Bundle b = iin.getExtras();
        if(b!=null) {
            String salaoselecionado = b.get("Uid").toString();
        }
        iniciarFirebase();

        listarSalao = findViewById(R.id.listSalao);

    }



    private void iniciarFirebase() {

        db = FirebaseFirestore.getInstance();
        FirebaseApp.initializeApp(SalaoSelecionado.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        referencia = firebaseDatabase.getReference();
    }

}
