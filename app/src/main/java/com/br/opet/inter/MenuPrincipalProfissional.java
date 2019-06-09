package com.br.opet.inter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MenuPrincipalProfissional extends AppCompatActivity {

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_profissional);
        auth = FirebaseAuth.getInstance();
    }

    private void alerta(String msg) {
        Toast.makeText(MenuPrincipalProfissional.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void meusSaloes(View view) {

        Intent i = new Intent(MenuPrincipalProfissional.this , SaloesProfissional.class);
        startActivity(i);
    }

    public void novoSalao(View view) {
        Intent i = new Intent(MenuPrincipalProfissional.this, NovoSalao.class);
        startActivity(i);
    }

    public void sair(View view) {
        auth.signOut();
        Intent i = new Intent(MenuPrincipalProfissional.this, ModoLogin.class);
        startActivity(i);
    }
}
