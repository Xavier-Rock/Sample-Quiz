package com.example.samplequiz.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.samplequiz.R;

public class QuizFragment extends Fragment {

    TextView mStartQuizTv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_lyt, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mStartQuizTv = view.findViewById(R.id.start_quiz_tv);
        mStartQuizTv.setOnClickListener(v -> startQuiz());
    }

    private void startQuiz() {
        androidx.fragment.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        QuizQuestionsFragment quizQuestionsFragment = new QuizQuestionsFragment();
        fragmentTransaction.replace(R.id.fragment_container, quizQuestionsFragment, "Quiz Fragment");
        fragmentTransaction.addToBackStack(quizQuestionsFragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
        fragmentTransaction.setCustomAnimations(0, 0, 0, 0);
    }
}
