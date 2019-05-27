package com.br.opet.inter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuPrincipalUsuario extends AppCompatActivity {

    private Button btnSalaoDisponivel, btnSair;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saloesdisponivelusuario);
        inicializarComponentes();
        
    }

    private void inicializarComponentes() {
        btnSalaoDisponivel = findViewById(R.id.btnSalaoDisponivel);
        btnSair = findViewById(R.id.btnSair);
    }

    private void eventoClick() {
        btnSalaoDisponivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPrincipalUsuario.this, SaloesDisponiveisUsuario.class);
                startActivity(i);
            }
        });
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPrincipalUsuario.this, ModoLogin.class);
                startActivity(i);
            }
        });
    }
}
