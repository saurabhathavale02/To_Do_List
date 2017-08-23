package com.example.saurabh.to_do_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

public class ToDoActivity extends AppCompatActivity
{
    private RecyclerView ToDorecycleView;
    private LinearLayoutManager linearLayoutManager;
    ToDoListAdapter toDoListAdapter;

    ImageButton Add;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        ToDorecycleView = (RecyclerView) findViewById(R.id.Todorecyclerview);
        Add=(ImageButton) findViewById(R.id.Add);
        linearLayoutManager = new LinearLayoutManager(this);

        ToDoDatabase toDoDatabase=ToDoDatabase.getInstance(this);
        ((App)getApplicationContext()).todolist.clear();
        ((App)getApplicationContext()).todolist=toDoDatabase.getAllToDo();

        toDoListAdapter = new ToDoListAdapter(this,((App)getApplicationContext()).todolist);
        toDoListAdapter.notifyDataSetChanged();
        ToDorecycleView.setLayoutManager(linearLayoutManager);
        ToDorecycleView.setItemAnimator(new DefaultItemAnimator());
        ToDorecycleView.setAdapter(toDoListAdapter);





        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(ToDoActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });


    }



}
