package com.example.samplequiz.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplequiz.Model.Questions;
import com.example.samplequiz.R;
import com.example.samplequiz.fragment.QuizQuestionsFragment;

import java.util.ArrayList;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    private Activity activity;
    private ArrayList<Questions> mQuestions;
    private Delegate mDelegate;

    public interface Delegate extends OptionsAdapter.Delegate{

    }

    public QuestionsAdapter(Activity activity, ArrayList<Questions> questions, QuizQuestionsFragment delegate) {
        this.activity = activity;
        this.mQuestions = questions;
        this.mDelegate = delegate;
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_questions_lyt, parent, false);
        return new QuestionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        if(mQuestions != null && mQuestions.size() > position && mQuestions.get(position) != null){
            Questions questions = mQuestions.get(position);
            if(questions.getOptions() != null && questions.getOptions().size() > 0
                    && !TextUtils.isEmpty(questions.getQuestion())) {
                int qusNo = position +1;
                holder.mQuestionNo.setText(qusNo + ". ");
                holder.mQuestionTv.setText(questions.getQuestion());
                OptionsAdapter adapter = new OptionsAdapter(questions.getOptions(), holder.getAdapterPosition(), mDelegate);
                holder.mOptionsRv.setAdapter(adapter);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if(mQuestions != null && mQuestions.size()>0) {
            return mQuestions.size();
        }
        return 0;
    }

    public class QuestionsViewHolder extends RecyclerView.ViewHolder {
        private TextView mQuestionTv, mQuestionNo;
        private RecyclerView mOptionsRv;

        public QuestionsViewHolder(@NonNull View itemView) {
            super(itemView);
            mQuestionNo = itemView.findViewById(R.id.question_no);
            mQuestionTv = itemView.findViewById(R.id.question_tv);
            mOptionsRv = itemView.findViewById(R.id.options_rv);
        }
    }
}
