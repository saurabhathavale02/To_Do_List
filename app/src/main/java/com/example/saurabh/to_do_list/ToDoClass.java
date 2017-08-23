package com.example.saurabh.to_do_list;

import java.io.Serializable;

/**
 * Created by saura on 8/19/2017.
 */

public class ToDoClass implements Serializable
{
    Integer id;
    String ToDoItem;
    String Date;
    String ToDoNotes;
    String Status;
    String Priority;

    public ToDoClass(Integer Id,String toDoItem, String date, String toDoNotes,String priority, String status)
    {
        id=Id;
        ToDoItem = toDoItem;
        Date = date;
        ToDoNotes = toDoNotes;
        Priority=priority;
        Status = status;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToDoItem() {
        return ToDoItem;
    }

    public void setToDoItem(String toDoItem) {
        ToDoItem = toDoItem;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getToDoNotes() {
        return ToDoNotes;
    }

    public void setToDoNotes(String toDoNotes) {
        ToDoNotes = toDoNotes;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
