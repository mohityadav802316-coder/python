package com.example.data.engine

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.max

/**
 * Snapshot of the active typing engine state.
 */
data class TypingEngineState(
    val fullTargetText: String = "",
    val currentIndex: Int = 0,
    val matchedText: String = "",
    val currentTargetChar: Char? = null,
    val remainingText: String = "",
    val hasError: Boolean = false,
    val lastErrorChar: Char? = null,
    val totalKeypresses: Int = 0,
    val correctKeypresses: Int = 0,
    val mistakeCount: Int = 0,
    val comboCount: Int = 0,
    val highestCombo: Int = 0,
    val comboMultiplier: Float = 1.0f,
    val currentWpm: Int = 0,
    val accuracy: Float = 100f,
    val score: Int = 0,
    val progress: Float = 0f,
    val isCompleted: Boolean = false,
    val startTimeMs: Long = 0L,
    val wordsCompletedCount: Int = 0
)

/**
 * Event triggered by the typing engine for mode-specific mechanics (attacks, jumps, boss hits).
 */
sealed class TypingEvent {
    data class CorrectChar(val char: Char, val combo: Int, val multiplier: Float) : TypingEvent()
    data class WordCompleted(val word: String, val wpm: Int, val combo: Int) : TypingEvent()
    data class SentenceCompleted(val sentence: String, val wpm: Int, val accuracy: Float) : TypingEvent()
    data class Mistake(val expected: Char, val actual: Char) : TypingEvent()
    object SessionCompleted : TypingEvent()
}

/**
 * Core reusable typing engine shared across all battle modes and practice drills.
 */
class TypingEngine(
    private val onEvent: (TypingEvent) -> Unit = {}
) {
    private val _state = MutableStateFlow(TypingEngineState())
    val state: StateFlow<TypingEngineState> = _state.asStateFlow()

    fun loadText(text: String) {
        val clean = text.trim()
        _state.value = TypingEngineState(
            fullTargetText = clean,
            currentIndex = 0,
            matchedText = "",
            currentTargetChar = clean.firstOrNull(),
            remainingText = clean,
            startTimeMs = 0L
        )
    }

    /**
     * Handles incoming hardware keyboard input character.
     */
    fun processCharInput(char: Char) {
        val current = _state.value
        if (current.isCompleted || current.fullTargetText.isEmpty()) return

        val now = System.currentTimeMillis()
        val startTime = if (current.startTimeMs == 0L) now else current.startTimeMs
        val expected = current.currentTargetChar ?: return

        val newTotalPresses = current.totalKeypresses + 1

        if (char == expected) {
            // Correct input
            val newIndex = current.currentIndex + 1
            val newMatched = current.fullTargetText.substring(0, newIndex)
            val newRemaining = current.fullTargetText.substring(newIndex)
            val newNextChar = current.fullTargetText.getOrNull(newIndex)
            val newCorrectPresses = current.correctKeypresses + 1
            val newCombo = current.comboCount + 1
            val newHighestCombo = max(current.highestCombo, newCombo)
            val multiplier = calculateMultiplier(newCombo)

            // Real-time WPM calculation: (characters / 5.0) / (minutes)
            val elapsedMinutes = max(0.001, (now - startTime) / 60000.0)
            val calculatedWpm = ((newCorrectPresses / 5.0) / elapsedMinutes).toInt().coerceIn(0, 300)
            val calculatedAccuracy = ((newCorrectPresses.toFloat() / newTotalPresses.toFloat()) * 100f).coerceIn(0f, 100f)
            val addedScore = (10 * multiplier).toInt()
            val newScore = current.score + addedScore
            val progressRatio = (newIndex.toFloat() / current.fullTargetText.length.toFloat()).coerceIn(0f, 1f)
            val isFinished = newIndex >= current.fullTargetText.length

            // Detect word boundary
            val isWordBoundary = (expected == ' ' || isFinished)
            val newWordsCount = if (isWordBoundary) current.wordsCompletedCount + 1 else current.wordsCompletedCount

            _state.value = current.copy(
                currentIndex = newIndex,
                matchedText = newMatched,
                currentTargetChar = newNextChar,
                remainingText = newRemaining,
                hasError = false,
                lastErrorChar = null,
                totalKeypresses = newTotalPresses,
                correctKeypresses = newCorrectPresses,
                comboCount = newCombo,
                highestCombo = newHighestCombo,
                comboMultiplier = multiplier,
                currentWpm = calculatedWpm,
                accuracy = calculatedAccuracy,
                score = newScore,
                progress = progressRatio,
                isCompleted = isFinished,
                startTimeMs = startTime,
                wordsCompletedCount = newWordsCount
            )

            onEvent(TypingEvent.CorrectChar(char, newCombo, multiplier))

            if (isWordBoundary) {
                val words = current.fullTargetText.split(" ")
                val finishedWord = words.getOrNull(newWordsCount - 1) ?: ""
                onEvent(TypingEvent.WordCompleted(finishedWord, calculatedWpm, newCombo))
            }

            if (isFinished) {
                onEvent(TypingEvent.SentenceCompleted(current.fullTargetText, calculatedWpm, calculatedAccuracy))
                onEvent(TypingEvent.SessionCompleted)
            }
        } else {
            // Mistake made: reset combo, increment mistake count, provide feedback
            val newMistakes = current.mistakeCount + 1
            val calculatedAccuracy = ((current.correctKeypresses.toFloat() / newTotalPresses.toFloat()) * 100f).coerceIn(0f, 100f)

            _state.value = current.copy(
                hasError = true,
                lastErrorChar = char,
                totalKeypresses = newTotalPresses,
                mistakeCount = newMistakes,
                comboCount = 0,
                comboMultiplier = 1.0f,
                accuracy = calculatedAccuracy,
                startTimeMs = startTime
            )

            onEvent(TypingEvent.Mistake(expected, char))
        }
    }

    /**
     * Allows backspacing the error state if in error.
     */
    fun processBackspace() {
        val current = _state.value
        if (current.hasError) {
            _state.value = current.copy(
                hasError = false,
                lastErrorChar = null
            )
        }
    }

    fun reset() {
        _state.value = TypingEngineState()
    }

    private fun calculateMultiplier(combo: Int): Float {
        return when {
            combo >= 100 -> 5.0f
            combo >= 50 -> 3.0f
            combo >= 25 -> 2.0f
            combo >= 10 -> 1.5f
            else -> 1.0f
        }
    }
}
