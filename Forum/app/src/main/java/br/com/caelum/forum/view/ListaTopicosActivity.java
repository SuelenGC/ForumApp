package br.com.caelum.forum.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.forum.R;
import br.com.caelum.forum.model.Topico;

public class ListaTopicosActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private ListView listaTopicos;

    private List<Topico> topicos = new ArrayList<>();
    private ArrayAdapter<Topico> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_topicos);

        database = FirebaseDatabase.getInstance();
        DatabaseReference topicosRef = database.getReference("topicos");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, topicos);
        listaTopicos = (ListView) findViewById(R.id.topicos_lista);
        listaTopicos.setAdapter(adapter);
        listaTopicos.setOnItemClickListener(new TopicosItemClickListener());


        topicosRef.addChildEventListener(new TopicosEventListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_topicos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_novo:
                Intent novoTopico = new Intent(this, TopicoActivity.class);
                startActivity(novoTopico);
        }

        return true;
    }

    class TopicosEventListener implements ChildEventListener {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Topico topico = dataSnapshot.getValue(Topico.class);
            topicos.add(topico);

            adapter.notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w("ERRO", "topicos:onCancelled", databaseError.toException());
            Toast.makeText(ListaTopicosActivity.this, "Failed to load topicos.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private class TopicosItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Intent novoTopico = new Intent(ListaTopicosActivity.this, TopicoActivity.class);

            Topico topico = topicos.get(position);
            novoTopico.putExtra("topico", topico);

            startActivity(novoTopico);
        }
    }
}
