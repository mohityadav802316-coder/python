package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.data.engine.TypingEngine
import com.example.data.engine.TypingEvent
import com.example.data.engine.WordBank
import com.example.data.local.AppDatabase
import com.example.data.model.MiniProject
import com.example.data.model.ModeProgress
import com.example.data.model.PlayerProfile
import com.example.data.model.PythonLesson
import com.example.data.python.PythonCurriculumRepository
import com.example.data.python.PythonExecutionResult
import com.example.data.python.PythonRuntime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Stack
import kotlin.random.Random

/**
 * Screen destinations matching all 7 core sections + Dashboard and Settings.
 */
enum class AppScreen(val title: String, val badge: String) {
    DASHBOARD("Hub Dashboard", "ARCADE MATRIX"),
    NINJA_FIGHT("Ninja Fight", "BATTLE ARENA"),
    NINJA_RUN("Ninja Run", "SPEED RUNNER"),
    MONSTER_BATTLE("Monster Battle", "TITAN RAID"),
    TYPING_ACADEMY("Typing Academy", "CADENCE DRILLS"),
    PYTHON_ACADEMY("Python Academy", "30-LEVEL ROADMAP"),
    REAL_PYTHON_LAB("Real Python Lab", "3-PANEL IDE"),
    PYTHON_MINI_PROJECTS("Python Mini Projects", "BLUEPRINTS"),
    SETTINGS("Terminal Settings", "CONFIG")
}

/**
 * Main application coordinator implementing clean architecture and MVVM.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "typing_master_db"
    ).build()

    private val playerDao = db.playerDao()
    private val progressDao = db.progressDao()
    private val pythonRuntime = PythonRuntime()

    // Navigation Stack
    private val navStack = Stack<AppScreen>().apply { push(AppScreen.DASHBOARD) }
    private val _currentScreen = MutableStateFlow(AppScreen.DASHBOARD)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    // Player Profile StateFlow
    val profile: StateFlow<PlayerProfile> = playerDao.getProfileFlow()
        .map { it ?: PlayerProfile() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PlayerProfile()
        )

    init {
        // Initialize default profile if empty
        viewModelScope.launch {
            if (playerDao.getProfile() == null) {
                playerDao.insertProfile(PlayerProfile())
            }
        }
    }

    // ----------------------------------------------------
    // SHARED TYPING ENGINE (Ninja Fight / Run / Monster)
    // ----------------------------------------------------
    private var lastCombatMove = MutableStateFlow("STEALTH_READY")
    val combatMoveAnimation: StateFlow<String> = lastCombatMove.asStateFlow()

    val typingEngine = TypingEngine { event ->
        when (event) {
            is TypingEvent.CorrectChar -> {
                // Trigger quick hit
            }
            is TypingEvent.WordCompleted -> {
                handleCombatWordComplete(event.word, event.wpm, event.combo)
            }
            is TypingEvent.SentenceCompleted -> {
                handleCombatRoundComplete(event.sentence, event.wpm, event.accuracy)
            }
            is TypingEvent.Mistake -> {
                lastCombatMove.value = "GLITCH_HIT"
            }
            is TypingEvent.SessionCompleted -> {}
        }
    }

    // --- Ninja Fight State ---
    val ninjaWave = MutableStateFlow(1)
    val playerHealth = MutableStateFlow(100)
    val enemyHealth = MutableStateFlow(100)
    val enemyName = MutableStateFlow("Cyber Ronin Mk.IV")

    // --- Ninja Run State ---
    val runnerDistance = MutableStateFlow(0)
    val runnerSpeedKmh = MutableStateFlow(45)
    val runnerObstacleAhead = MutableStateFlow("Neon Laser Barrier")

    // --- Monster Battle / Titan Raid State ---
    val titanHealth = MutableStateFlow(500)
    val titanMaxHealth = MutableStateFlow(500)
    val titanShield = MutableStateFlow(100)
    val titanPhase = MutableStateFlow(1) // Phase 1, Phase 2 (Rage), Phase 3 (Core)

    // --- Python Academy State ---
    val selectedLesson = MutableStateFlow(PythonCurriculumRepository.lessons.first())
    val quizSelectedOption = MutableStateFlow<Int?>(null)
    val quizSubmitted = MutableStateFlow(false)
    val challengeUserCode = MutableStateFlow("")
    val challengeResultOutput = MutableStateFlow<String?>(null)

    // --- Real Python Lab State ---
    val ideActiveFile = MutableStateFlow("main.py")
    val ideFiles = MutableStateFlow(
        mapOf(
            "main.py" to """
# Real Python Lab - Typing & Python Master
# Powered by genuine native Kotlin-Python runtime

def cyber_strike(hero_name, combo):
    base_dmg = 45
    multiplier = 1.0 + (combo * 0.1)
    total = base_dmg * multiplier
    print(f"[{hero_name}] executed Cyber Strike!")
    print(f"Combo: {combo}x | Total Impact: {total:.1f} DMG")
    return total

print(">>> Initializing Shinjuku Terminal Core...")
hit = cyber_strike("Kage_99", 5)
print(">>> System Telemetry OK.")
""".trimIndent(),
            "skills.py" to """
# Skills definitions
skills = ["Shadow Slash", "Phantom Dash", "Lightning Cut"]
for s in skills:
    print(f"- Unlocked skill: {s}")
""".trimIndent(),
            "config.py" to """
# Game telemetry settings
GAME_VERSION = "2.4.0"
DIFFICULTY = "CYBERPUNK_NIGHTMARE"
print(f"Engine Version: {GAME_VERSION} | Mode: {DIFFICULTY}")
""".trimIndent()
        )
    )
    val ideOutput = MutableStateFlow(">>> Click [RUN CODE] or press F5 / Ctrl+Enter to execute python code.")
    val ideHasError = MutableStateFlow(false)

    // --- Python Mini Projects State ---
    val selectedMiniProject = MutableStateFlow(PythonCurriculumRepository.miniProjects.first())

    // ----------------------------------------------------
    // NAVIGATION METHODS
    // ----------------------------------------------------
    fun navigateTo(screen: AppScreen) {
        if (_currentScreen.value != screen) {
            navStack.push(screen)
            _currentScreen.value = screen
            onEnterScreen(screen)
        }
    }

    fun navigateBack(): Boolean {
        if (navStack.size > 1) {
            navStack.pop()
            _currentScreen.value = navStack.peek()
            onEnterScreen(_currentScreen.value)
            return true
        }
        return false
    }

    private fun onEnterScreen(screen: AppScreen) {
        when (screen) {
            AppScreen.NINJA_FIGHT -> initNinjaFight()
            AppScreen.NINJA_RUN -> initNinjaRun()
            AppScreen.MONSTER_BATTLE -> initMonsterBattle()
            AppScreen.TYPING_ACADEMY -> initTypingAcademyDrill()
            AppScreen.PYTHON_ACADEMY -> initPythonLesson(selectedLesson.value)
            else -> {}
        }
    }

    // ----------------------------------------------------
    // COMBAT & MODE INITIALIZERS
    // ----------------------------------------------------
    fun initNinjaFight() {
        ninjaWave.value = 1
        playerHealth.value = 100
        enemyHealth.value = 100
        enemyName.value = "Cyber Shinobi Lv.1"
        loadNextNinjaFightWord()
    }

    fun loadNextNinjaFightWord() {
        val word = WordBank.ninjaFightWords.random()
        typingEngine.loadText(word)
    }

    private fun handleCombatWordComplete(word: String, wpm: Int, combo: Int) {
        when (_currentScreen.value) {
            AppScreen.NINJA_FIGHT -> {
                lastCombatMove.value = "SLASH_CRITICAL"
                val damage = (25 * typingEngine.state.value.comboMultiplier).toInt()
                val nextHp = (enemyHealth.value - damage).coerceAtLeast(0)
                enemyHealth.value = nextHp

                if (nextHp <= 0) {
                    // Next Wave!
                    ninjaWave.value += 1
                    enemyHealth.value = 100 + (ninjaWave.value * 20)
                    enemyName.value = when {
                        ninjaWave.value % 5 == 0 -> "ELITE MECH ASSASSIN"
                        ninjaWave.value % 3 == 0 -> "SHADOW RONIN"
                        else -> "Cyber Shinobi Lv.${ninjaWave.value}"
                    }
                    gainXp(50 * ninjaWave.value, 1)
                } else {
                    gainXp(10, 1)
                }
                loadNextNinjaFightWord()
            }
            AppScreen.NINJA_RUN -> {
                lastCombatMove.value = "DASH_BOOST"
                runnerDistance.value += 50
                runnerSpeedKmh.value = (runnerSpeedKmh.value + 5).coerceAtMost(160)
                runnerObstacleAhead.value = listOf(
                    "Neon Gap [CLEARED]",
                    "Laser Drone [HURDLED]",
                    "Spike Barrier [VAULTED]"
                ).random()
                gainXp(15, 1)
                val nextWord = WordBank.ninjaRunWords.random()
                typingEngine.loadText(nextWord)
            }
            AppScreen.MONSTER_BATTLE -> {
                lastCombatMove.value = "TITAN_STRIKE"
                val dmg = (40 * typingEngine.state.value.comboMultiplier).toInt()
                if (titanShield.value > 0) {
                    titanShield.value = (titanShield.value - dmg).coerceAtLeast(0)
                } else {
                    titanHealth.value = (titanHealth.value - dmg).coerceAtLeast(0)
                }
                gainXp(25, 1)
                if (titanHealth.value <= 0) {
                    // Boss phase change or win
                    if (titanPhase.value < 3) {
                        titanPhase.value += 1
                        titanMaxHealth.value = 600 + (titanPhase.value * 200)
                        titanHealth.value = titanMaxHealth.value
                        titanShield.value = 150
                    } else {
                        titanPhase.value = 1
                        titanHealth.value = 500
                        gainXp(500, 10)
                    }
                }
                val nextWord = WordBank.monsterRaidWords.random()
                typingEngine.loadText(nextWord)
            }
            AppScreen.TYPING_ACADEMY -> {
                gainXp(5, 1)
            }
            else -> {}
        }
    }

    private fun handleCombatRoundComplete(sentence: String, wpm: Int, accuracy: Float) {
        viewModelScope.launch {
            val modeKey = _currentScreen.value.name.lowercase()
            progressDao.saveProgress(
                ModeProgress(
                    modeKey = modeKey,
                    bestWpm = wpm,
                    bestAccuracy = accuracy,
                    isCompleted = true,
                    highScore = typingEngine.state.value.score
                )
            )
        }
    }

    fun initNinjaRun() {
        runnerDistance.value = 0
        runnerSpeedKmh.value = 45
        runnerObstacleAhead.value = "Approaching Neon Laser Wall"
        typingEngine.loadText(WordBank.ninjaRunWords.random())
    }

    fun initMonsterBattle() {
        titanPhase.value = 1
        titanMaxHealth.value = 500
        titanHealth.value = 500
        titanShield.value = 100
        typingEngine.loadText(WordBank.monsterRaidWords.random())
    }

    fun initTypingAcademyDrill(drillType: String = "home_row") {
        val drillText = when (drillType) {
            "home_row" -> WordBank.typingDrillsHomeRow.shuffled().take(6).joinToString(" ")
            "sentences" -> WordBank.typingDrillsCommonEnglish.random()
            "python" -> WordBank.typingDrillsPythonCode.random()
            else -> WordBank.typingDrillsCommonEnglish.random()
        }
        typingEngine.loadText(drillText)
    }

    // ----------------------------------------------------
    // PYTHON ACADEMY METHODS
    // ----------------------------------------------------
    fun selectLesson(lesson: PythonLesson) {
        selectedLesson.value = lesson
        initPythonLesson(lesson)
    }

    private fun initPythonLesson(lesson: PythonLesson) {
        quizSelectedOption.value = null
        quizSubmitted.value = false
        challengeUserCode.value = lesson.challengeStarterCode.ifEmpty { lesson.challengeSolutionSnippet }
        challengeResultOutput.value = null
    }

    fun submitQuiz(optionIndex: Int) {
        quizSelectedOption.value = optionIndex
        quizSubmitted.value = true
        if (optionIndex == selectedLesson.value.quizCorrectIndex) {
            gainXp(40, 0)
        }
    }

    fun runLessonChallengeCode() {
        val code = challengeUserCode.value
        val result = pythonRuntime.execute(code)
        challengeResultOutput.value = result.output
        if (!result.hasError && result.output.contains(selectedLesson.value.expectedOutputContains)) {
            gainXp(100, 5)
            viewModelScope.launch {
                progressDao.saveProgress(
                    ModeProgress(
                        modeKey = "python_level_${selectedLesson.value.id}",
                        isCompleted = true,
                        stars = 3
                    )
                )
            }
        }
    }

    // ----------------------------------------------------
    // REAL PYTHON LAB METHODS
    // ----------------------------------------------------
    fun selectIdeFile(fileName: String) {
        ideActiveFile.value = fileName
    }

    fun updateIdeFileContent(content: String) {
        val current = ideFiles.value.toMutableMap()
        current[ideActiveFile.value] = content
        ideFiles.value = current
    }

    fun runIdeCode() {
        val currentCode = ideFiles.value[ideActiveFile.value] ?: ""
        val result = pythonRuntime.execute(currentCode)
        ideOutput.value = result.output
        ideHasError.value = result.hasError
        if (!result.hasError) {
            gainXp(25, 2)
        }
    }

    // ----------------------------------------------------
    // MINI PROJECTS METHODS
    // ----------------------------------------------------
    fun selectMiniProject(project: MiniProject) {
        selectedMiniProject.value = project
    }

    fun loadMiniProjectInLab(project: MiniProject) {
        val current = ideFiles.value.toMutableMap()
        val fileName = "${project.id}.py"
        current[fileName] = project.sourceCode
        ideFiles.value = current
        ideActiveFile.value = fileName
        navigateTo(AppScreen.REAL_PYTHON_LAB)
        runIdeCode()
    }

    // ----------------------------------------------------
    // XP & LEVEL PROGRESSION
    // ----------------------------------------------------
    private fun gainXp(amount: Int, words: Int) {
        viewModelScope.launch {
            playerDao.addXpAndWords(amount, words)
        }
    }

    // ----------------------------------------------------
    // KEYBOARD EVENT DISPATCHER
    // ----------------------------------------------------
    fun onHardwareKeyChar(char: Char) {
        typingEngine.processCharInput(char)
    }

    fun onHardwareBackspace() {
        typingEngine.processBackspace()
    }
}
