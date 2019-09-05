package com.example.samplequiz.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samplequiz.R;

import java.util.ArrayList;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.OptionsVH> {

    private ArrayList<String> mOptions;
    private int mPreSelectedPosition = -5;
    private OptionsVH mPreViewHolder = null;
    private int mQuestionPosition;
    private Delegate mDelegate;


    public interface Delegate{
        void onClickOfOptions(int questionPos, String selectedOption);
    }

    public OptionsAdapter(ArrayList<String> options, int position, QuestionsAdapter.Delegate delegate) {
        this.mOptions = options;
        this.mQuestionPosition = position;
        this.mDelegate = delegate;
    }

    @NonNull
    @Override
    public OptionsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_options_lyt, parent, false);
        return new OptionsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionsVH holder, int position) {
        if(mOptions != null && mOptions.size() > position && !TextUtils.isEmpty(mOptions.get(0))){
            holder.mOptionsTv.setText(mOptions.get(position));
            holder.mOptionRb.setOnClickListener(v -> onClickRadioButton(position, mOptions.get(position), holder));
        } else {
            holder.mOptionLyt.setVisibility(View.GONE);
        }

    }

    private void onClickRadioButton(int position, String answer, OptionsVH holder) {
        if(mPreViewHolder == null){
            mPreSelectedPosition = position;
            mPreViewHolder = holder;
        } else {
            if(mPreSelectedPosition == position) return;
            mPreSelectedPosition = position;
            mPreViewHolder.mOptionRb.setChecked(false);
            mPreViewHolder = holder;
        }
        mDelegate.onClickOfOptions(mQuestionPosition, answer);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if(mOptions != null && mOptions.size() > 0){
            return mOptions.size();
        }
        return 0;
    }

    public class OptionsVH extends RecyclerView.ViewHolder {
        private TextView mOptionsTv;
        private RadioButton mOptionRb;
        private LinearLayout mOptionLyt;

        public OptionsVH(@NonNull View itemView) {
            super(itemView);
            mOptionLyt = itemView.findViewById(R.id.option_lyt);
            mOptionRb = itemView.findViewById(R.id.option_rb);
            mOptionsTv = itemView.findViewById(R.id.option_text);
        }
    }
}
