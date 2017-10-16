package iu.i527.shalaka.todolistapp;

import java.util.Date;

/**
 * Created by Ramya on 10/14/2017.
 */
import java.io.Serializable;

@SuppressWarnings("serial")
public class ToDoTask implements Serializable {

    private long id;
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

    public ToDoTask(long id, String task_description, Date date, boolean status) {
        this.id = id;
        this.task_description = task_description;
        this.date = date;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
