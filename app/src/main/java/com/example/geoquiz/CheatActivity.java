package com.example.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheatActivity extends AppCompatActivity {

    //Log TAG
    public static final String TAG = "CheatActivity";

    private static final String EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown";

    public static final String KEY_INDEX = "index";

    private TextView mAnswerTextView;

    private boolean mAnswerIsTrue;
    private Button mShowAnswerButton;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue){
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null){
            mAnswerIsTrue = savedInstanceState.getBoolean(KEY_INDEX, false);
            setAnswerShownResult(true);
        }

            mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

            mAnswerTextView = findViewById(R.id.answer_text_view);

            mShowAnswerButton = findViewById(R.id.show_answer_button);
            mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mAnswerIsTrue){
                        mAnswerTextView.setText(R.string.true_button);
                    } else {
                        mAnswerTextView.setText(R.string.false_button);
                    }
                    setAnswerShownResult(true);
                }
            });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstance called ");
        savedInstanceState.putBoolean(KEY_INDEX, mAnswerIsTrue);
        setAnswerShownResult(true);
    }

    private void setAnswerShownResult(boolean answerIsShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsShown);
        setResult(RESULT_OK, data);
    }
}
