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

    TodoListDatabaseHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new TodoListDatabaseHelper(getApplicationContext());
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
    }

    public void onClickSaveButton(View view) {
        TextView textview = (TextView) findViewById(R.id.dateTextView);
        EditText editText = (EditText) findViewById(R.id.editText);
        String taskDescription = editText.getText().toString();
        Date date = new Date(Long.parseLong(textview.getText().toString()));
        ToDoTask newTask = new ToDoTask(taskDescription, date, false);
        mDbHelper.addTodoTaskToDB(newTask);
        Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }
}
