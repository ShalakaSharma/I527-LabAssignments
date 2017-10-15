package iu.i527.shalaka.todolistapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;

/**
 * Created by Ramya on 10/14/2017.
 */
import java.io.Serializable;

@SuppressWarnings("serial")
public class ToDoTask implements Serializable {

    private String task_description;
    private Date date;
    private boolean status;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ToDoTask(String task_description, Date date, boolean status) {
        this.task_description = task_description;
        this.date = date;
        this.status = status;
    }

    public ToDoTask() {
    }
}
