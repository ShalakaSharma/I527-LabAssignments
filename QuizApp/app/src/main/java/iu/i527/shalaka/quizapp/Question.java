package iu.i527.shalaka.quizapp;

/**
 * Created by Shalaka on 9/15/2017.
 */

public class Question {

    private int question_id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correct_answer;
    private String answer_entered;
    private boolean is_answered;

    public String getAnswer_entered() {
        return answer_entered;
    }

    public void setAnswer_entered(String answer_entered) {
        this.answer_entered = answer_entered;
    }

    public Question(int question_id, String question, String option1, String option2, String option3, String option4, String correct_answer, String answer_entered, boolean is_answered) {
        this.question_id = question_id;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correct_answer = correct_answer;
        this.answer_entered = answer_entered;
        this.is_answered = is_answered;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public boolean is_answered() {
        return is_answered;
    }

    public void setIs_answered(boolean is_answered) {
        this.is_answered = is_answered;
    }
}
