package com.example.samplequiz.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
    private TextView mSubmit;
    private ArrayList<String> mSelectedAnswers;
    private ArrayList<String> mAnswerList;
    private Data mData;
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
        mSubmit = view.findViewById(R.id.submit_quiz_tv);
        mSubmit.setOnClickListener(v -> openSuccessPage());
    }

    private void LoadDataFromAssert() {
        Data data = new Gson().fromJson(getDataFromAssert(), Data.class);
        if(data != null && data.getQuestions()!= null && data.getQuestions().get(0) != null){
            mData = data;
            mSelectedAnswers = new ArrayList<>();
            mAnswerList = new ArrayList<>();
            for(int i = 0; i < data.getQuestions().size(); i++){
                mSelectedAnswers.add("");
                mAnswerList.add(data.getQuestions().get(i).getAnswer());
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

    private void openSuccessPage() {
        int crtAnsCount = 0;
        for(int i = 0; i < mAnswerList.size(); i++){
            if(!TextUtils.isEmpty(mSelectedAnswers.get(i)) && !TextUtils.isEmpty(mAnswerList.get(i))
                    && mAnswerList.get(i).equalsIgnoreCase(mSelectedAnswers.get(i))){
                crtAnsCount++;
            }
        }
        Toast.makeText(getContext(), "Correct Answer : "+ crtAnsCount, Toast.LENGTH_SHORT).show();
        checkScore(crtAnsCount);
    }

    @Override
    public void onClickOfOptions(int questionPos, String selectedOption) {
        mSelectedAnswers.add(questionPos, selectedOption);
    }

    private void checkScore(int crtAnsCount) {
        androidx.fragment.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        QuizSuccessFragment quizSuccessFragment = new QuizSuccessFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", mData);
        bundle.putInt("score", crtAnsCount);
        quizSuccessFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, quizSuccessFragment, "Quiz Fragment");
        fragmentTransaction.addToBackStack(quizSuccessFragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
        fragmentTransaction.setCustomAnimations(0, 0, 0, 0);
    }
}
