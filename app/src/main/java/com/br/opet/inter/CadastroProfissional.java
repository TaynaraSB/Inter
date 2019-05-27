package com.br.opet.inter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroProfissional extends AppCompatActivity {

    private EditText editEmail, editSenha;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);
        inicializarComponentes();

    }

    private void inicializarComponentes() {
        editEmail = findViewById(R.id.editCadastroEmail);
        editSenha = findViewById(R.id.editCadastroSenha);

    }


    private void alerta(String msg) {
        Toast.makeText(CadastroProfissional.this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }

    public void criarUser(View view) {
        final String email = editEmail.getText().toString();
        final String senha = editSenha.getText().toString();
        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            alerta("CadastroProfissional realizado com sucesso!");
                            Intent i = new Intent(CadastroProfissional.this, LoginProfissional.class);
                            startActivity(i);
                            finish();
                        } else {
                            alerta("CadastroProfissional nao realizado!");
                        }
                    }
                });
    }
}
