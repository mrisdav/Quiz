package com.david.topquiz.model;
import static java.io.FileDescriptor.in;
import static java.io.FileDescriptor.out;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Question implements Parcelable {
    private String question;
    private List<String> answers;
    private int correctAnswerIndex;
    private int mData;

    public Question(String question, List<String> answers, int correctAnswerIndex) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public Question(Parcel parcel) {
        mData = parcel.readInt();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public static final Parcelable.Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel parcel) {
            return new Question(parcel);
        }

        @Override
        public Question[] newArray(int i) {
            return new Question[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mData);
    }
}
