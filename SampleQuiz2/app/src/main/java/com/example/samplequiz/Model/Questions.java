package com.example.samplequiz.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Questions implements Parcelable {
    private String question;
    private ArrayList<String> options;
    private String answer;

    protected Questions(Parcel in) {
        question = in.readString();
        options = in.createStringArrayList();
        answer = in.readString();
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(question);
        parcel.writeStringList(options);
        parcel.writeString(answer);
    }
}
