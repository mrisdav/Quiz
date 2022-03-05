package com.david.topquiz.model;

public class User {
    private String mName;
    private int mScore;

    public User(String mName, int mScore) {
        this.mName = mName;
        this.mScore = mScore;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }
}
