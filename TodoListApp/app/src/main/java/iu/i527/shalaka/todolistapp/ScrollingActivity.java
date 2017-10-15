package iu.i527.shalaka.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class ScrollingActivity extends AppCompatActivity {

    ArrayList<ToDoTask> toDoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        if (intent.hasExtra("list")) {
            toDoList = (ArrayList)intent.getSerializableExtra("list");
            Toast.makeText(this, "List present ", Toast.LENGTH_SHORT).show();
           /* ToDoTask task = (ToDoTask)intent.getSerializableExtra("toDoTask");
            Toast.makeText(this, "taskDescription: " + task.getTask_description(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "date: " + task.getDate().getDate() + task.getDate().getMonth() + task.getDate().getYear(), Toast.LENGTH_SHORT).show();*/
        } else {
            Toast.makeText(this, "List absent ", Toast.LENGTH_SHORT).show();
            toDoList = new ArrayList<ToDoTask>();
        }
    }

    public void onClickSaveButton(View view) {
        CalendarView calendarView = (CalendarView) findViewById(R.id.simpleCalendarView);
        EditText editText = (EditText) findViewById(R.id.editText);
        String taskDescription = editText.getText().toString();
        Date date = new Date(calendarView.getDate());

        Toast.makeText(this, "taskDescription: " + taskDescription, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "date: " + date.getDate() + date.getMonth() + date.getYear(), Toast.LENGTH_SHORT).show();

        ToDoTask newTask = new ToDoTask(taskDescription, date, false);

        toDoList.add(newTask);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("list", toDoList);
        startActivity(intent);
    }
}
