package br.com.caelum.forum.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

import br.com.caelum.forum.R;
import br.com.caelum.forum.dao.TopicoDao;
import br.com.caelum.forum.model.Topico;

public class TopicoActivity extends AppCompatActivity {

    private EditText txtTitulo;
    private EditText txtDescricao;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topico);

        database = FirebaseDatabase.getInstance();

        txtDescricao = (EditText) findViewById(R.id.topico_descricao);
        txtTitulo = (EditText) findViewById(R.id.topico_titulo);
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
                Topico topico = new Topico();
                topico.setTitulo(txtTitulo.getText().toString());
                topico.setDescricao(txtDescricao.getText().toString());

                TopicoDao dao = new TopicoDao(database);
                dao.insere(topico);
                finish();
        }
        return true;
    }
}
