package iu.i527.shalaka.todolistapp;

import android.provider.BaseColumns;

import java.sql.Date;

/**
 * Created by Shalaka on 10/15/2017.
 */

public final class ToDoListContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ToDoListContract() {}

    /* Inner class that defines the table contents */
    public static class ToDoList implements BaseColumns {
        public static final String TABLE_NAME = "TodoList";
        public static final String COLUMN_NAME_TASK_DESCRIPTION = "task_description";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_STATUS = "status";
    }
}
