package com.example.tugaskuliahasinkron;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<DataTodo> todos;

    public TodoAdapter(Context context, List<DataTodo> todos) {
        this.context = context;
        this.todos = todos;
    }

    public class VH extends RecyclerView.ViewHolder{

        private final TextView tvId;
        private final TextView tvWhat;
        private final TextView tvTime;


        public VH(@NonNull View itemView) {
            super(itemView);
            this.tvId = itemView.findViewById(R.id.tvId);
            this.tvWhat = itemView.findViewById(R.id.tvWhat);
            this.tvTime = itemView.findViewById(R.id.tvTime);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(this.context)
                .inflate(R.layout.row_todo,parent,false);
        return new VH(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataTodo t = this.todos.get(position);
        VH vh = (VH) holder;
        vh.tvId.setText(t.id.toString());
        vh.tvWhat.setText(t.what.toString());
        vh.tvTime.setText(t.time.toString());
    }

    @Override
    public int getItemCount() {
        return this.todos.size();
    }
}
