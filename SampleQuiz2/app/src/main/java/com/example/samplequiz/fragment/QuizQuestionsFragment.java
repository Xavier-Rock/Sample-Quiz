package com.example.samplequiz.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplequiz.Model.Data;
import com.example.samplequiz.R;
import com.example.samplequiz.adapter.QuestionsAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class QuizQuestionsFragment extends Fragment implements QuestionsAdapter.Delegate{

    private RecyclerView mQuizRv;
    private ArrayList<String> mSelectedAnswers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_questions_lyt, container, false);
        initView(view);
        LoadDataFromAssert();
        return view;
    }

    private void initView(View view) {
        mQuizRv = view.findViewById(R.id.quiz_main_rv);
    }

    private void LoadDataFromAssert() {
        Data data = new Gson().fromJson(getDataFromAssert(), Data.class);
        if(data != null && data.getQuestions()!= null && data.getQuestions().get(0) != null){
            mSelectedAnswers = new ArrayList<>();
            for(int i = 0; i < data.getQuestions().size(); i++){
                mSelectedAnswers.add("");
            }
            QuestionsAdapter adapter = new QuestionsAdapter(getActivity(), data.getQuestions(), this);
            mQuizRv.setAdapter(adapter);
        }
    }

    private String getDataFromAssert() {
        String jsonString = null;
        try {
            InputStream inputStream = getActivity().getAssets().open("question.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
            return jsonString;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void onClickOfOptions(int questionPos, String selectedOption) {
        mSelectedAnswers.add(questionPos, selectedOption);
    }
}
