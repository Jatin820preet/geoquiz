package edu.usc.csc202.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CheatActivity : AppCompatActivity() {

    private var answerIsTrue: Boolean = false
    private var answerShown: Boolean = false

    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerShown = savedInstanceState?.getBoolean(KEY_ANSWER_SHOWN, false) ?: false

        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)

        if (answerShown) {
            showAnswerNow()
        }

        showAnswerButton.setOnClickListener {
            showAnswerNow()
        }
    }

    private fun showAnswerNow() {
        answerTextView.setText(if (answerIsTrue) R.string.true_button else R.string.false_button)
        answerShown = true
        setAnswerShownResult(true)
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_ANSWER_SHOWN, answerShown)
    }

    companion object {
        private const val EXTRA_ANSWER_IS_TRUE = "edu.usc.csc202.geoquiz.answer_is_true"
        const val EXTRA_ANSWER_SHOWN = "edu.usc.csc202.geoquiz.answer_shown"
        private const val KEY_ANSWER_SHOWN = "key_answer_shown"

        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}
