package iu.i527.shalaka.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ScrollingActivity extends AppCompatActivity {

    ArrayList<ToDoTask> toDoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CalendarView calendarView = (CalendarView) findViewById(R.id.simpleCalendarView);
        calendarView.setMinDate(new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        TextView textview = (TextView) findViewById(R.id.dateTextView);
        textview.setText(date.getTime()+"");
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year,month,dayOfMonth);
                Date date = calendar.getTime();
                TextView textview = (TextView) findViewById(R.id.dateTextView);
                textview.setText(date.getTime()+"");
            }
        });

        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        if (intent.hasExtra("list")) {
            toDoList = (ArrayList)intent.getSerializableExtra("list");
        } else {
            toDoList = new ArrayList<ToDoTask>();
        }
    }

    public void onClickSaveButton(View view) {
        TextView textview = (TextView) findViewById(R.id.dateTextView);
        EditText editText = (EditText) findViewById(R.id.editText);
        String taskDescription = editText.getText().toString();
        Date date = new Date(Long.parseLong(textview.getText().toString()));

        ToDoTask newTask = new ToDoTask(taskDescription, date, false);

        toDoList.add(newTask);
        Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("list", toDoList);
        startActivity(intent);
    }
}
