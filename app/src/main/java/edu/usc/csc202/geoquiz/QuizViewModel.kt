package edu.usc.csc202.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia_continent, true),
        Question(R.string.question_capital_sydney, false),
        Question(R.string.question_equator_australia, false),
        Question(R.string.question_everest_border, true),
        Question(R.string.question_sahara_south_america, false),
    )

    var currentIndex = 0
        private set

    // Tracks if the user has revealed the answer for *the currently visible question*
    var isCheater: Boolean = false

    val currentQuestionTextResId: Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
        isCheater = false // reset when moving to another question
    }

    fun moveToPrev() {
        currentIndex = if (currentIndex - 1 < 0) questionBank.lastIndex else currentIndex - 1
        isCheater = false // reset when moving to another question
    }

    fun markCheater() { isCheater = true }
}
