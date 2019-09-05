package com.example.samplequiz.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplequiz.Model.Data;
import com.example.samplequiz.R;
import com.example.samplequiz.adapter.QuizAnswerAdapter;

public class QuizSuccessFragment extends Fragment {

    private Data data;
    private int mScore = 0;
    private TextView mQuizScoreTv;
    private RecyclerView mQuizAnsRv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retriveBundle(getArguments());
    }

    private void retriveBundle(Bundle arguments) {
        if(arguments != null){
            if(arguments.getParcelable("data") != null){
                data = arguments.getParcelable("data");
            }
            mScore = arguments.getInt("score");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_success_lyt, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mQuizScoreTv = view.findViewById(R.id.quiz_score_tv);
        mQuizAnsRv = view.findViewById(R.id.quiz_answer_rv);
        mQuizScoreTv.setText("You Scored "+ mScore + "/" + data.getQuestions().size());
        mQuizAnsRv.setNestedScrollingEnabled(false);
        if(data != null && data.getQuestions() != null) {
            QuizAnswerAdapter adapter = new QuizAnswerAdapter(data.getQuestions());
            mQuizAnsRv.setAdapter(adapter);
        }
    }

}
