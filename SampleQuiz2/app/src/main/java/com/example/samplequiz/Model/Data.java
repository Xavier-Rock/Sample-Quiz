package com.example.samplequiz.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Data implements Parcelable{

    private ArrayList<Questions> questions;

    protected Data(Parcel in) {
        in.readList(this.questions, (Questions.class.getClassLoader()));
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public ArrayList<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Questions> questions) {
        this.questions = questions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(questions);
    }
}
