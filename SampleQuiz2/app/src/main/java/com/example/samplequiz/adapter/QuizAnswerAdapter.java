package com.example.samplequiz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplequiz.Model.Questions;
import com.example.samplequiz.R;

import java.util.ArrayList;

public class QuizAnswerAdapter extends RecyclerView.Adapter<QuizAnswerAdapter.QuizAnswerVH> {

    private ArrayList<Questions> mQuestions;

    public QuizAnswerAdapter(ArrayList<Questions> questions) {
        this.mQuestions = questions;
    }

    @NonNull
    @Override
    public QuizAnswerAdapter.QuizAnswerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_quiz_answer_lyt, parent, false);
        return new QuizAnswerVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAnswerAdapter.QuizAnswerVH holder, int position) {
        if(mQuestions != null && mQuestions.size() > position && mQuestions.get(position) != null){
            Questions questions = mQuestions.get(position);
            int pos = position + 1;
            holder.mQuesNoTv.setText(pos + ". ");
            holder.mQuesTv.setText(questions.getQuestion());
            holder.mAnsTv.setText(questions.getAnswer());
        }
    }

    @Override
    public int getItemCount() {
        if(mQuestions != null && mQuestions.size() > 0){
            return mQuestions.size();
        }
        return 0;
    }

    public class QuizAnswerVH extends RecyclerView.ViewHolder {
        TextView mQuesNoTv, mQuesTv, mAnsHeaderTv, mAnsTv;

        public QuizAnswerVH(@NonNull View itemView) {
            super(itemView);
            mQuesNoTv = itemView.findViewById(R.id.question_no);
            mQuesTv = itemView.findViewById(R.id.question_tv);
            mAnsHeaderTv = itemView.findViewById(R.id.ans_header);
            mAnsTv = itemView.findViewById(R.id.quiz_answer_tv);
        }
    }
}
