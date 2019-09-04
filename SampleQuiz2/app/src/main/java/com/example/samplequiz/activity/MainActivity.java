package com.example.samplequiz.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.samplequiz.fragment.QuizFragment;
import com.example.samplequiz.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openQuizMainFragment(this);
    }

    private void openQuizMainFragment(MainActivity activity) {
        androidx.fragment.app.FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        QuizFragment quizFragment = new QuizFragment();
        fragmentTransaction.replace(R.id.fragment_container, quizFragment, "Quiz Fragment");
        fragmentTransaction.addToBackStack(quizFragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
        fragmentTransaction.setCustomAnimations(0, 0, 0, 0);
    }
}
