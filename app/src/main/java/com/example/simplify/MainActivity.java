package com.example.simplify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ViewModelProviders is now deprecated.
        noteViewModel= new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<NoteClass>>() {
            @Override
            public void onChanged(List<NoteClass> noteClasses) {
                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
