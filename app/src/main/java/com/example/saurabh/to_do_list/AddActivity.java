package com.example.saurabh.to_do_list;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
{
    private EditText InputName, InputNotes;
    private MaterialBetterSpinner StatusSpinner,PrioritySpinner;
    private Button InputDate,AddToList;
    String selectedStatus,selectedPriority;
    Context context;
    Integer id=null;
    ToDoDatabase toDoDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        context=this;
        InputName=(EditText) findViewById(R.id.Name);
        InputNotes=(EditText) findViewById(R.id.Notes);
        StatusSpinner=(MaterialBetterSpinner) findViewById(R.id.StatusSpinner);
        PrioritySpinner=(MaterialBetterSpinner) findViewById(R.id.PrioritySpinner);
        InputDate=(Button) findViewById(R.id.SelectDate);
        AddToList=(Button) findViewById(R.id.AddToList);

        String[] StatusList = getResources().getStringArray(R.array.prioritylist);
        String[] PriorityList = getResources().getStringArray(R.array.statuslist);

        toDoDatabase=ToDoDatabase.getInstance(context);


        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            id= Integer.valueOf(extras.getString("ID"));
            ToDoClass storetododata= toDoDatabase.getSingleToDo(id);
            InputName.setText(storetododata.getToDoItem());
            InputNotes.setText(storetododata.getToDoNotes());
            InputDate.setText(storetododata.getDate());

            for(int i=0;i<StatusList.length;i++)
            {
                if(StatusList[i].equalsIgnoreCase(storetododata.getStatus()));
                {
                    StatusSpinner.setText(storetododata.getStatus());
                }
            }

            for(int i=0;i<PriorityList.length;i++)
            {
                if(PriorityList[i].equalsIgnoreCase(storetododata.getPriority()));
                {
                    PrioritySpinner.setText(storetododata.getPriority());
                }
            }

        }



        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, StatusList);

        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, PriorityList);

        StatusSpinner.setAdapter(statusAdapter);
        PrioritySpinner.setAdapter(priorityAdapter);

        StatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                selectedStatus= parent.getItemAtPosition(pos).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        AddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedPriority = PrioritySpinner.getText().toString();
                selectedStatus = StatusSpinner.getText().toString();
                String selectedDate = InputDate.getText().toString();
                String selectedName = InputName.getText().toString();
                String selectedNotes = InputNotes.getText().toString();


                if (selectedName.isEmpty() || selectedNotes.isEmpty() || selectedPriority.isEmpty() || selectedDate.isEmpty() || selectedStatus.isEmpty())
                {
                    Toast.makeText(AddActivity.this,"Please set Value",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (id != null) {
                        Integer count = ((App) getApplicationContext()).todolist.size();
                        ToDoClass newtodo = new ToDoClass(id, InputName.getText().toString(), InputDate.getText().toString(), InputNotes.getText().toString(), selectedPriority, selectedStatus);
                        toDoDatabase.updateToDoItem(newtodo);
                    } else {

                        Integer count = ((App) getApplicationContext()).todolist.size();
                        ToDoClass newtodo = new ToDoClass(count, InputName.getText().toString(), InputDate.getText().toString(), InputNotes.getText().toString(), selectedPriority, selectedStatus);
                        toDoDatabase.addToDo(newtodo);
                    }
                    Intent intent = new Intent(AddActivity.this, ToDoActivity.class);
                    startActivity(intent);


                }
            }
        });


        InputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                DatePickerFragment fragment = new DatePickerFragment();
                fragment.show(getSupportFragmentManager(),"datePicker");
            }
        });
    }

    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        InputDate.setText(dateFormat.format(calendar.getTime()));

    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        setDate(cal);
    }
}
