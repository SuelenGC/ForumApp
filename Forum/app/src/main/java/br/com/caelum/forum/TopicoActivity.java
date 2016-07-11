package br.com.caelum.forum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.caelum.forum.model.Topico;

public class TopicoActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference topicosReference;

    private EditText txtTitulo;
    private EditText txtDescricao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topico);

        txtDescricao = (EditText) findViewById(R.id.topico_descricao);
        txtTitulo = (EditText) findViewById(R.id.topico_titulo);

        database = FirebaseDatabase.getInstance();
        topicosReference = database.getReference("topicos");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_topico, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_salvar:
                String key = topicosReference.push().getKey();

                Topico topico = new Topico();
                topico.setTitulo(txtTitulo.getText().toString());
                topico.setDescricao(txtDescricao.getText().toString());

                DatabaseReference novoTopicoRef = database.getReference("topicos/" + key);
                novoTopicoRef.setValue(topico);
        }
        return true;
    }
}
