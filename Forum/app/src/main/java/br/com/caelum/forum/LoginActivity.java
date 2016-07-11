package br.com.caelum.forum;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    private Button btnCadastrar;
    private Button btnEntrar;
    private EditText txtEmail;
    private EditText txtSenha;

    private String email;
    private String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Passo 1
        auth = FirebaseAuth.getInstance();

        //Passo 2
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out

                }
            }
        };

        //Passo 5
        btnCadastrar = (Button) findViewById(R.id.login_btn_cadastrar);
        btnEntrar = (Button) findViewById(R.id.login_btn_entrar);
        txtEmail = (EditText) findViewById(R.id.login_email);
        txtSenha = (EditText) findViewById(R.id.login_senha);

        //Passo 6
        //Cadastrar novo usuário (tip: senha pelo menos 6 caracteres)
        btnCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                email = txtEmail.getText().toString();
                senha = txtSenha.getText().toString();

                Task<AuthResult> novoUsuarioCallback = auth.createUserWithEmailAndPassword(email, senha);

                novoUsuarioCallback.addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Cadastro feito com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Falha ao cadastrar!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //Logar com usuário existente
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = txtEmail.getText().toString();
                senha = txtSenha.getText().toString();

                Task<AuthResult> entrarCallback = auth.signInWithEmailAndPassword(email, senha);

                entrarCallback.addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Usuário entrou com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Falha ao entrar!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //Passo 3
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    //Passo 4
    @Override
    protected void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
