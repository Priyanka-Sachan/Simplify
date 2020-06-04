package com.example.simplify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    List<NoteClass> notes= new ArrayList<NoteClass>();

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_note, parent, false);
        return new NoteViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        NoteClass currentNote = notes.get(position);
        holder.title.setText(currentNote.getTitle());
        holder.content.setText(currentNote.getContent());
        holder.priority.setText(String.valueOf(currentNote.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<NoteClass> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public NoteClass getNoteAt(int position) {
        return notes.get(position);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView priority;
        TextView title;
        TextView content;

        public NoteViewHolder(View itemView){
            super(itemView);
            priority=itemView.findViewById(R.id.priority);
            title=itemView.findViewById(R.id.title);
            content=itemView.findViewById(R.id.content);
        }
    }
}
