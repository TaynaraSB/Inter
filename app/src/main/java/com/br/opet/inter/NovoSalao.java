package com.br.opet.inter;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;;


import java.util.List;
import java.util.UUID;

public class NovoSalao extends AppCompatActivity {

    private TextView editNome, editRua, editBairro, editCidade, editNumero;
    private FirebaseAuth auth;
    private List<Uri> mSelected;
    private ImageView imageSelector;
    private FirebaseFirestore db;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String userLogado = user.getUid();
    private static final int REQUEST_CODE_CHOOSE = 1234;
    private StorageReference mStorage;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_salao);
        FirebaseApp.initializeApp(NovoSalao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        referencia = firebaseDatabase.getReference();
        imageSelector = findViewById(R.id.imageSelector);
        mStorage = FirebaseStorage.getInstance().getReference();

        editNome = findViewById(R.id.editNome);
        editRua = findViewById(R.id.editBairro);
        editBairro = findViewById(R.id.editBairro);
        editNumero = findViewById(R.id.editNumero);
        editCidade = findViewById(R.id.editCidade);
        auth = Conexao.getFirebaseAuth();
        db = FirebaseFirestore.getInstance();


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

        ObjSenha senhaExibida = new ObjSenha();
        senhaExibida.setSenhaExibida("0");

        referencia.child("Senha").child(salao.getUid()).setValue(senhaExibida);
        referencia.child("Salao").child(userLogado).child(salao.getUid()).setValue(salao);
        referencia.child("SalaoAll").child(salao.getUid()).setValue(salao);
        salvarImagemFirebase(salao.getUid());


    }

    public void voltar(View view) {
        Intent i = new Intent(NovoSalao.this, MenuPrincipalProfissional.class);
        startActivity(i);
    }

    public void selectImageFromDisk(View view) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {

                if (aBoolean) {
                    Matisse.from(NovoSalao.this)
                            .choose(MimeType.ofImage())
                            .countable(false)
                            .thumbnailScale(0.9f)
                            .imageEngine(new GlideV4Engine())
                            .forResult(REQUEST_CODE_CHOOSE);
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + mSelected);

            Picasso.get().load(mSelected.get(0)).into(imageSelector);
        }


    }

    public void salvarImagemFirebase(String id) {
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        StorageReference userRef = mStorage.child("images/" + id + ".png");
        userRef.putFile(mSelected.get(0))
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                });
    }
}
