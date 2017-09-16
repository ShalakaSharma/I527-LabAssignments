package iu.i527.shalaka.quizapp;

import java.util.ArrayList;

/**
 * Created by Shalaka on 9/15/2017.
 */

public class Category {

    int category_id;
    String category_name;
    int score;
    ArrayList<Question> questions;
    int index;

    public Category(int category_id, String category_name, ArrayList<Question> questions, int score, int index) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.questions = questions;
        this.score = score;
        this.index = index;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
