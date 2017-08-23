package com.example.saurabh.to_do_list;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saura on 8/20/2017.
 */

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder>
{
    private final String TAG =ToDoListAdapter.class.getName();
    List<ToDoClass> ToDoList=new ArrayList<ToDoClass>();
    View.OnClickListener mClickListener;
    ToDoActivity toDoActivity;

    public ToDoListAdapter(ToDoActivity toDoActivity, List<ToDoClass> toDoList)
    {
        this.ToDoList = toDoList;
        this.toDoActivity=toDoActivity;
    }

    @Override
    public ToDoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todolist, parent, false);

        final ViewHolder holder=new ViewHolder(view);

        final TextView ID= (TextView) view.findViewById(R.id.ID);



        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), AddActivity.class);
                intent.putExtra("ID",ID.getText());
                v.getContext().startActivity(intent);
            }
        });


        holder.Delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String Id = ID.getText().toString();
                ToDoDatabase toDoDatabase=ToDoDatabase.getInstance(toDoActivity);
                toDoDatabase.deleteToDoItem(Integer.valueOf(Id));
                ToDoList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), ToDoList.size());

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ToDoListAdapter.ViewHolder holder, int position)
    {
        holder.Name.setText(String.valueOf(ToDoList.get(position).getToDoItem()));
        holder.Notes.setText("Notes - : "+ToDoList.get(position).getToDoNotes());
        holder.Status.setText("Status - : "+ToDoList.get(position).getStatus());
        holder.Priority.setText("Priority - : "+ToDoList.get(position).getPriority());
        holder.ID.setText(String.valueOf(ToDoList.get(position).getId()));

    }

    @Override
    public int getItemCount() {
        return ToDoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public final View mView;

        public final TextView Notes,Status,Priority,Name,ID;
        public final ImageButton Delete;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ID=(TextView) view.findViewById(R.id.ID);
            Notes = (TextView) view.findViewById(R.id.Notes);
            Status = (TextView) view.findViewById(R.id.Status);
            Priority=(TextView) view.findViewById(R.id.Priority);
            Name=(TextView) view.findViewById(R.id.ToDoItem);
            Delete=(ImageButton) view.findViewById(R.id.Delete);
        }
    }
}
