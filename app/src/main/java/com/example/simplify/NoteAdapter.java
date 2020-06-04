package com.example.simplify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


class NoteAdapter extends ListAdapter<NoteClass,NoteAdapter.NoteViewHolder> {
    OnItemClickListener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }
    private static final DiffUtil.ItemCallback<NoteClass> DIFF_CALLBACK = new DiffUtil.ItemCallback<NoteClass>() {
        @Override
        public boolean areItemsTheSame(NoteClass oldItem, NoteClass newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(NoteClass oldItem, NoteClass newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getContent().equals(newItem.getContent()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_note, parent, false);
        return new NoteViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        NoteClass currentNote = getItem(position);
        holder.title.setText(currentNote.getTitle());
        holder.content.setText(currentNote.getContent());
        holder.priority.setText(String.valueOf(currentNote.getPriority()));
    }

    public NoteClass getNoteAt(int position) {
        return getItem(position);
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(NoteClass note);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
