package com.br.opet.inter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class ModoLogin extends AppCompatActivity {


    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modologin);
        inicializarComponentes();
        auth = FirebaseAuth.getInstance();

    }


    private void inicializarComponentes() {

        auth = Conexao.getFirebaseAuth();

    }

    private void alerta(String msg) {
        Toast.makeText(ModoLogin.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void profissional(View view) {
        Intent i = new Intent(ModoLogin.this, LoginProfissional.class);
        startActivity(i);


    }

    public void usuario(View view) {
        Intent i = new Intent(ModoLogin.this, MenuPrincipalUsuario.class);
        startActivity(i);
    }
}
