package com.br.opet.inter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;


public class ModoLogin extends AppCompatActivity {


    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modo_login);
        inicializarComponentes();
        auth = FirebaseAuth.getInstance();

    }


    private void inicializarComponentes() {

        auth = Conexao.getFirebaseAuth();

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
