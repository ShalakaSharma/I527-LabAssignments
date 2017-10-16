package iu.i527.shalaka.todolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Shalaka on 10/15/2017.
 */

public class TodoListDatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDoList.db";

    SQLiteDatabase database;

    public TodoListDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        database = db;
        db.execSQL(SQL_CREATE_ENTRIES);
        this.addInitialTodoTasksToDB(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ToDoListContract.ToDoList.TABLE_NAME + " (" +
                    ToDoListContract.ToDoList._ID + " INTEGER PRIMARY KEY," +
                    ToDoListContract.ToDoList.COLUMN_NAME_TASK_DESCRIPTION + " TEXT," +
                    ToDoListContract.ToDoList.COLUMN_NAME_DATE + " TEXT," +
                    ToDoListContract.ToDoList.COLUMN_NAME_STATUS+ " TEXT)";

    public void addTodoTaskToDB(ToDoTask task) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ToDoListContract.ToDoList.COLUMN_NAME_TASK_DESCRIPTION, task.getTask_description());
        values.put(ToDoListContract.ToDoList.COLUMN_NAME_DATE, task.getDate().getTime()+"");
        values.put(ToDoListContract.ToDoList.COLUMN_NAME_STATUS, task.isStatus()+"");
        db.insert(ToDoListContract.ToDoList.TABLE_NAME, null, values);
    }

    public void addInitialTodoTasksToDB(SQLiteDatabase db) {
        Date dt = new Date();
        ToDoTask t1 = new ToDoTask("Call James", dt, false);
        ToDoTask t2 = new ToDoTask("Call Mom", dt, false);
        ContentValues values = new ContentValues();
        values.put(ToDoListContract.ToDoList.COLUMN_NAME_TASK_DESCRIPTION, t1.getTask_description());
        values.put(ToDoListContract.ToDoList.COLUMN_NAME_DATE, t1.getDate().getTime()+"");
        values.put(ToDoListContract.ToDoList.COLUMN_NAME_STATUS, t1.isStatus()+"");
        db.insert(ToDoListContract.ToDoList.TABLE_NAME, null, values);
        values = new ContentValues();
        values.put(ToDoListContract.ToDoList.COLUMN_NAME_TASK_DESCRIPTION, t2.getTask_description());
        values.put(ToDoListContract.ToDoList.COLUMN_NAME_DATE, t2.getDate().getTime()+"");
        values.put(ToDoListContract.ToDoList.COLUMN_NAME_STATUS, t2.isStatus()+"");
        db.insert(ToDoListContract.ToDoList.TABLE_NAME, null, values);
    }


    public ArrayList<ToDoTask> fetchToDoListFromDB(String lastNavSelected) {
        SQLiteDatabase db = getWritableDatabase();
        String[] projection = {
                ToDoListContract.ToDoList._ID,
                ToDoListContract.ToDoList.COLUMN_NAME_TASK_DESCRIPTION,
                ToDoListContract.ToDoList.COLUMN_NAME_DATE,
                ToDoListContract.ToDoList.COLUMN_NAME_STATUS
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = ToDoListContract.ToDoList.COLUMN_NAME_STATUS + " = ?";

        String[] selectionArgs = {};

        if(lastNavSelected.equals("completed")) {
            selectionArgs = new String[1];
            selectionArgs[0] = "true";
        } else if(lastNavSelected.equals("pending")) {
            selectionArgs = new String[1];
            selectionArgs[0] = "false";
        } else {
            selection = "";
        }
        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                ToDoListContract.ToDoList.COLUMN_NAME_TASK_DESCRIPTION + " ASC";

        Cursor cursor = db.query(
                ToDoListContract.ToDoList.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        ArrayList<ToDoTask> toDoList = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(ToDoListContract.ToDoList._ID));
            String taskDesc = cursor.getString(
                    cursor.getColumnIndexOrThrow(ToDoListContract.ToDoList.COLUMN_NAME_TASK_DESCRIPTION));
            boolean status = Boolean.parseBoolean(cursor.getString(
                    cursor.getColumnIndexOrThrow(ToDoListContract.ToDoList.COLUMN_NAME_STATUS)));
            Date date = new Date(cursor.getLong(
                    cursor.getColumnIndexOrThrow(ToDoListContract.ToDoList.COLUMN_NAME_DATE)));
            ToDoTask toDoTask = new ToDoTask(itemId,taskDesc,date,status);
            toDoList.add(toDoTask);
        }
        cursor.close();
        return toDoList;
    }

    public void deleteFromDatabase(String taskDesc, String date) {
        SQLiteDatabase db = getWritableDatabase();
        // Define 'where' part of query.
        String selection = ToDoListContract.ToDoList.COLUMN_NAME_TASK_DESCRIPTION + " LIKE ? and " +
        ToDoListContract.ToDoList.COLUMN_NAME_DATE + " LIKE ?";
        String[] selectionArgs = { taskDesc, date };
        // Issue SQL statement.
        db.delete(ToDoListContract.ToDoList.TABLE_NAME, selection, selectionArgs);
    }

    public void changeTaskStatusInDB(String taskDesc, Date date, boolean isCompleted) {
        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(ToDoListContract.ToDoList.COLUMN_NAME_STATUS, isCompleted+"");

        // Which row to update, based on the title
        String selection = ToDoListContract.ToDoList.COLUMN_NAME_TASK_DESCRIPTION + " LIKE ? and " +
                ToDoListContract.ToDoList.COLUMN_NAME_DATE + " LIKE ?";
        String[] selectionArgs = { taskDesc, date.getTime()+""  };

        db.update(
                ToDoListContract.ToDoList.TABLE_NAME,
                values,
                selection,
                selectionArgs);

    }

    public void clearToDoListInDB () {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from "+ ToDoListContract.ToDoList.TABLE_NAME);
    }

}