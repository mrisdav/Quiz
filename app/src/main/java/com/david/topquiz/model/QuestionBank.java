package com.david.topquiz.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private List<Question> mListQuestion;
    private Question mQuestion;
    private int mIndex;

    public QuestionBank(List<Question> listQuestion) {
        mListQuestion = listQuestion;
        Collections.shuffle(mListQuestion);
        mIndex = 0;
    }

    public Question getNextQuestion() {
        if(mIndex == 4) {
            mIndex = 0;
        }
        return mListQuestion.get(mIndex++);
    }
}
