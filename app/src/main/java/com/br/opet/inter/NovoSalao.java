package com.br.opet.inter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class NovoSalao extends AppCompatActivity {

    private TextView editNome, editRua, editBairro, editCidade, editNumero;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String userLogado = user.getUid();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novosalao);
        inicializarComponentes();

    }


    private void inicializarComponentes() {
        editNome = findViewById(R.id.editNome);
        editRua = findViewById(R.id.editBairro);
        editBairro = findViewById(R.id.editBairro);
        editNumero = findViewById(R.id.editNumero);
        editCidade = findViewById(R.id.editCidade);
        auth = Conexao.getFirebaseAuth();
        db = FirebaseFirestore.getInstance();
        FirebaseApp.initializeApp(NovoSalao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        referencia = firebaseDatabase.getReference();

    }

    private void alerta(String msg) {
        Toast.makeText(NovoSalao.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void addSalao(View view) {

        ObjSalao salao = new ObjSalao();
        salao.setUid(UUID.randomUUID().toString());
        salao.setNome(editNome.getText().toString());
        salao.setRua(editRua.getText().toString());
        salao.setBairro(editBairro.getText().toString());
        salao.setNumero(editNumero.getText().toString());
        salao.setCidade(editCidade.getText().toString());
        referencia.child(userLogado).child(salao.getUid()).setValue(salao);

    }

    public void voltar(View view) {
        Intent i = new Intent(NovoSalao.this, MenuPrincipalProfissional.class);
        startActivity(i);
    }
}