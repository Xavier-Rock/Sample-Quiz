package com.example.samplequiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;

public class QuizQuestionsFragment extends Fragment {

    private RecyclerView mQuizRv;

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
        mQuizRv.setHasFixedSize(true);
    }

    private void LoadDataFromAssert() {

    }

    private void getDataFromAssert() throws IOException {
        InputStream inputStream = getActivity().getAssets().open("question.json");
        int size = inputStream.available();
        byte[] buffer = new byte[size];


    }
}
