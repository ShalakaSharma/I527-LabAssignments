package iu.i527.shalaka.quizapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Category mathematics;
    Category generalKnowledge;
    Category selectedCategory;
    int totalQuestions;
    int questionsAnswered;

    public void initializeQuiz() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.category_1);
        String category1 = "Mathematics";
        String category2 = "General Knowledge";
        ArrayList<Question> questionList1 = getMathQuestions();
        ArrayList<Question> questionList2 = getGKQuestions();
        mathematics = new Category(1,category1,questionList1,0,0);
        generalKnowledge = new Category(2,category2,questionList2,0,0);
        selectedCategory = mathematics;
        totalQuestions = mathematics.getQuestions().size() +  mathematics.getQuestions().size();
        questionsAnswered = 0;
    }

    public ArrayList<Question> getMathQuestions() {
        ArrayList<Question> questionList = new ArrayList<Question>();
        Question q1 = new Question(1, "1+5 = ?", "5", "6", "10", "20", "6","", false);
        Question q2 = new Question(2, "10 x 100 = ?", "1000", "2000", "100", "10", "1000","", false);
        Question q3 = new Question(3, "10 + 100 = ?", "1000", "100", "10", "110", "110","", false);
        Question q4 = new Question(4, "5000 / 5 = ?", "1000", "200", "300", "500", "1000","", false);
        Question q5 = new Question(5, "2 ^ 5 = ?", "4", "6", "32", "16", "32","", false);
        Question q6 = new Question(6, "5 ^ 2 = ?", "10", "25", "30", "20", "25","", false);
        questionList.add(q1);
        questionList.add(q2);
        questionList.add(q3);
        questionList.add(q4);
        questionList.add(q5);
        questionList.add(q6);
        return questionList;
    }

    public ArrayList<Question> getGKQuestions() {
        ArrayList<Question> questionList = new ArrayList<Question>();
        Question q1 = new Question(1, "First Prime Minister of India?", "Narendra Modi", "Jawaharlal Nehru", "Lal Bahadur Shastri", "Indira Gandhi", "Jawaharlal Nehru", "", false);
        Question q2 = new Question(2, "44th POTUS?", "Donald Trump", "Barack Obama", "George W. Bush", "Bill Clinton", "Barack Obama", "", false);
        Question q3 = new Question(3, "First Indian to win Miss Universe?", "Sushmita Sen", "Aiswarya Rai Bachchan", "Priyanka Chopra", "Lara Dutta", "Sushmita Sen", "", false);
        Question q4 = new Question(4, "2017 Oscar for Best Movie?", "Hidden Figures", "Hacksaw Ridge", "La La Land", "Moonlight", "Moonlight", "",  false);
        Question q5 = new Question(5, "Biggest Continent?", "Europe", "Australia", "Asia", "North America", "Asia","", false );
        Question q6 = new Question(6, "Which animal is referred as the Ship of the Desert ", "Horse", "Camel", "Penguin", "Giraffe", "Camel","", false);
        questionList.add(q1);
        questionList.add(q2);
        questionList.add(q3);
        questionList.add(q4);
        questionList.add(q5);
        questionList.add(q6);
        return questionList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initializeQuiz();
        startQuiz();
    }

    public void startQuiz() {
        renderQuestion(selectedCategory);
    }

    public void renderQuestion(Category category) {
        Question questionToRender = category.questions.get(category.index);
        TextView category_name = (TextView) findViewById(R.id.category_name);
        category_name.setText(category.getCategory_name());
        TextView question_id = (TextView) findViewById(R.id.question_id);
        question_id.setText("Question " + questionToRender.getQuestion_id() + ":");
        TextView question = (TextView) findViewById(R.id.question);
        question.setText(questionToRender.getQuestion());
        RadioButton radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton1.setText(questionToRender.getOption1());
        RadioButton radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton2.setText(questionToRender.getOption2());
        RadioButton radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton3.setText(questionToRender.getOption3());
        RadioButton radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        radioButton4.setText(questionToRender.getOption4());
        Button submit = (Button) findViewById(R.id.submit);
        boolean is_answered = questionToRender.is_answered();
        radioButton1.setEnabled(!is_answered);
        radioButton2.setEnabled(!is_answered);
        radioButton3.setEnabled(!is_answered);
        radioButton4.setEnabled(!is_answered);
        radioButton1.setBackgroundColor(Color.parseColor("#00ff0000"));
        radioButton2.setBackgroundColor(Color.parseColor("#00ff0000"));
        radioButton3.setBackgroundColor(Color.parseColor("#00ff0000"));
        radioButton4.setBackgroundColor(Color.parseColor("#00ff0000"));
        submit.setEnabled(!is_answered);
        if(is_answered) {
            if(radioButton1.getText() == questionToRender.getCorrect_answer()) {
                radioButton1.setBackgroundColor(Color.parseColor("#008000"));
            } else if(radioButton2.getText() == questionToRender.getCorrect_answer()) {
                radioButton2.setBackgroundColor(Color.parseColor("#008000"));
            } else if(radioButton3.getText() == questionToRender.getCorrect_answer()) {
                radioButton3.setBackgroundColor(Color.parseColor("#008000"));
            } else if(radioButton4.getText() == questionToRender.getCorrect_answer()) {
                radioButton4.setBackgroundColor(Color.parseColor("#008000"));
            }
            if(questionToRender.getAnswer_entered() == radioButton1.getText()) {
                radioButton1.setBackgroundColor(Color.parseColor("#ff0000"));
                radioButton1.setChecked(true);
                radioButton1.setEnabled(true);
                if(questionToRender.getAnswer_entered() == questionToRender.getCorrect_answer()) {
                    radioButton1.setBackgroundColor(Color.parseColor("#008000"));
                }
            } else if(questionToRender.getAnswer_entered() == radioButton2.getText()) {
                radioButton2.setBackgroundColor(Color.parseColor("#ff0000"));
                radioButton2.setChecked(true);
                radioButton2.setEnabled(true);
                if(questionToRender.getAnswer_entered() == questionToRender.getCorrect_answer()) {
                    radioButton2.setBackgroundColor(Color.parseColor("#008000"));
                }
                if(radioButton2.getText() == questionToRender.getCorrect_answer()) {
                    radioButton2.setBackgroundColor(Color.parseColor("#008000"));
                }
            } else if(questionToRender.getAnswer_entered() == radioButton3.getText()) {
                radioButton3.setBackgroundColor(Color.parseColor("#ff0000"));
                radioButton3.setChecked(true);
                radioButton3.setEnabled(true);
                if(questionToRender.getAnswer_entered() == questionToRender.getCorrect_answer()) {
                    radioButton3.setBackgroundColor(Color.parseColor("#008000"));
                }
                if(radioButton3.getText() == questionToRender.getCorrect_answer()) {
                    radioButton3.setBackgroundColor(Color.parseColor("#008000"));
                }
            } else if(questionToRender.getAnswer_entered() == radioButton4.getText()) {
                radioButton4.setBackgroundColor(Color.parseColor("#ff0000"));
                radioButton4.setChecked(true);
                radioButton4.setEnabled(true);
                if(questionToRender.getAnswer_entered() == questionToRender.getCorrect_answer()) {
                    radioButton4.setBackgroundColor(Color.parseColor("#008000"));
                }
                if(radioButton4.getText() == questionToRender.getCorrect_answer()) {
                    radioButton4.setBackgroundColor(Color.parseColor("#008000"));
                }
            }
        } else {
            radioButton1.setChecked(true);
        }
        TextView score = (TextView) findViewById(R.id.score_label);
        score.setText("Category: " + category.getCategory_name() +  " | Score: ");
        TextView scoreValue = (TextView) findViewById(R.id.score_value);
        scoreValue.setText("" + category.score);
        Button next = (Button) findViewById(R.id.next);
        if(selectedCategory.getIndex() == selectedCategory.getQuestions().size() - 1) {
            next.setEnabled(false);
        } else {
            next.setEnabled(true);
        }
        Button prev = (Button) findViewById(R.id.prev);
        if(selectedCategory.getIndex() == 0) {
            prev.setEnabled(false);
        } else {
            prev.setEnabled(true);
        }
        TextView questionsAnsweredText = (TextView) findViewById(R.id.questions_answered);
        questionsAnsweredText.setText("Questions answered: " + questionsAnswered + " / " + totalQuestions);
    }

    public void onClickSubmit(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
        if (group.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Select one option", Toast.LENGTH_SHORT).show();
            return;
        }
        String message = "";
        Question question = selectedCategory.getQuestions().get(selectedCategory.index);
        question.setIs_answered(true);
        if (checkAndSetAnswer(question)) {
            selectedCategory.score += 2;
            message = "Well done!";
        } else {
            selectedCategory.score--;
            if(selectedCategory.score == -1) {
                selectedCategory.score = 0;
            }

            message = "Oops! Wrong Answer!";
        }
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
        questionsAnswered++;
        onClickNext(view);
        if(questionsAnswered == totalQuestions) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("Quiz Completed!")
                    .setMessage("Score in " + mathematics.getCategory_name() +": " + mathematics.score + "\nScore in " +  generalKnowledge.getCategory_name() + ": " + generalKnowledge.score + "\nClick OK to replay!")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            initializeQuiz();
                            startQuiz();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
            Toast.makeText(this, "Final Score: " + (mathematics.score + generalKnowledge.score)  , Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickNext(View view) {
        if(selectedCategory.getIndex() == selectedCategory.getQuestions().size() - 1) {
            selectedCategory.index = 0;
        } else {
            selectedCategory.index++;
        }
        renderQuestion(selectedCategory);
    }

    public void onClickPrevious(View view) {
        if(selectedCategory.getIndex() == 0) {
            selectedCategory.index = selectedCategory.getQuestions().size() - 1;
        } else {
            selectedCategory.index--;
        }
        renderQuestion(selectedCategory);
    }

    private boolean checkAndSetAnswer(Question question) {
        int selectedOption = ((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedOption);
        question.setAnswer_entered(radioButton.getText().toString());
        if(radioButton.getText().toString() == question.getCorrect_answer()) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.reset) {
            resetQuiz();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void resetQuiz() {
        // Go to init state

        Toast.makeText(this, "Quiz Reset", Toast.LENGTH_SHORT).show();
        initializeQuiz();
        startQuiz();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.category_1) {
            selectedCategory = mathematics;
        } else if (id == R.id.category_2) {
            selectedCategory = generalKnowledge;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        renderQuestion(selectedCategory);
        return true;
    }
}
