package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Player progress and profile data.
 */
@Entity(tableName = "player_profile")
data class PlayerProfile(
    @PrimaryKey val id: Int = 1,
    val username: String = "CyberNinja_01",
    val title: String = "Code Ronin",
    val xp: Int = 1250,
    val level: Int = 5,
    val bestWpm: Int = 78,
    val averageAccuracy: Float = 96.5f,
    val totalWordsTyped: Int = 4280,
    val ninjaFightHighWave: Int = 12,
    val ninjaRunBestDistance: Int = 1840,
    val titanBossDefeated: Int = 3,
    val pythonLessonsCompleted: Int = 6,
    val miniProjectsCompleted: Int = 2
)

/**
 * Progress records for specific levels and modes.
 */
@Entity(tableName = "mode_progress")
data class ModeProgress(
    @PrimaryKey val modeKey: String, // e.g. "ninja_fight", "python_lesson_1"
    val highScore: Int = 0,
    val bestWpm: Int = 0,
    val bestAccuracy: Float = 0f,
    val isCompleted: Boolean = false,
    val stars: Int = 0,
    val lastPlayedTimestamp: Long = System.currentTimeMillis()
)

/**
 * Python Academy lesson model.
 */
data class PythonLesson(
    val id: Int,
    val phase: Int,
    val phaseTitle: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val conceptNotes: List<String>,
    val codeExample: String,
    val quizQuestion: String,
    val quizOptions: List<String>,
    val quizCorrectIndex: Int,
    val quizExplanation: String,
    val challengePrompt: String,
    val challengeStarterCode: String,
    val challengeSolutionSnippet: String,
    val expectedOutputContains: String
)

/**
 * Python Mini-Project blueprint.
 */
data class MiniProject(
    val id: String,
    val title: String,
    val category: String,
    val difficulty: String,
    val description: String,
    val keyConcepts: List<String>,
    val sourceCode: String,
    val sampleInput: String = ""
)
