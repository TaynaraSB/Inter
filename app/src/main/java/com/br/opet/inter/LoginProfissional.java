package com.br.opet.inter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginProfissional extends AppCompatActivity {

    private EditText editEmail, editSenha;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        inicializarComponentes();
        verificarLogin();

    }




    private void inicializarComponentes() {
        editEmail = findViewById(R.id.editLoginEmail);
        editSenha = findViewById(R.id.editLoginSenha);
        auth = FirebaseAuth.getInstance();
    }

    //-----------------VERIFICAR SE ESTÁ LOGADO---------------------------------------------------//

    protected void verificarLogin() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            Toast.makeText(this, "Logado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MenuPrincipalProfissional.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Você não está logado", Toast.LENGTH_SHORT).show();

        }
    }
    //-----------------VERIFICAR SE ESTÁ LOGADO---------------------------------------------------//



    //-----------------   LOGAR     ---------------------------------------------------//

    public void logar(View view) {
        final String email = editEmail.getText().toString();
        final String senha = editSenha.getText().toString();
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    alerta("Logado com sucesso!");
                    Intent i = new Intent(LoginProfissional.this, MenuPrincipalProfissional.class);
                    startActivity(i);
                } else {

                    alerta("Usuario ou senha incorretos!");
                }
            }
        });


    }

    //-----------------   LOGAR     ---------------------------------------------------//

    private void alerta(String msg) {
        Toast.makeText(LoginProfissional.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void cadastrar(View view) {
        Intent i = new Intent(LoginProfissional.this, CadastroProfissional.class);
        startActivity(i);
    }
}
