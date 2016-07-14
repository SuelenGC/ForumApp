package br.com.caelum.forum.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.caelum.forum.model.Topico;

public class TopicoDao {
    private FirebaseDatabase database;
    private DatabaseReference topicosReference;

    public TopicoDao(FirebaseDatabase database) {
        this.database = database;
        topicosReference = database.getReference("topicos");
    }

    public void insere(Topico topico) {
        topicosReference.push().setValue(topico);
    }
}
