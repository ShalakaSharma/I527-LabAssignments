package iu.i527.shalaka.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickSaveButton(View view) {
        CalendarView calendarView = (CalendarView) findViewById(R.id.simpleCalendarView);
        EditText editText = (EditText) findViewById(R.id.editText);
        String taskDescription = editText.getText().toString();
        Date date = new Date(calendarView.getDate());

        Toast.makeText(this, "taskDescription: " + taskDescription, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "date: " + date.getDate() + date.getMonth() + date.getYear(), Toast.LENGTH_SHORT).show();

        ToDoTask newTask = new ToDoTask(taskDescription, date, false);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("toDoTask", newTask);
        startActivity(intent);
    }
}
