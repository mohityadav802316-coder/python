package com.example

import com.example.data.engine.TypingEngine
import com.example.data.engine.TypingEvent
import com.example.data.python.PythonRuntime
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TypingEngineAndPythonRuntimeTest {

    @Test
    fun typingEngine_processesCharactersAndCalculatesWpmAndAccuracy() {
        val events = mutableListOf<TypingEvent>()
        val engine = TypingEngine { event -> events.add(event) }

        engine.loadText("cyber ninja")
        val initialState = engine.state.value

        assertEquals("cyber ninja", initialState.fullTargetText)
        assertEquals('c', initialState.currentTargetChar)
        assertEquals(0, initialState.currentIndex)

        // Type 'c', 'y', 'b', 'e', 'r'
        engine.processCharInput('c')
        assertEquals(1, engine.state.value.currentIndex)
        assertEquals("c", engine.state.value.matchedText)
        assertEquals('y', engine.state.value.currentTargetChar)
        assertEquals(1, engine.state.value.comboCount)

        engine.processCharInput('y')
        engine.processCharInput('b')
        engine.processCharInput('e')
        engine.processCharInput('r')

        assertEquals(5, engine.state.value.currentIndex)
        assertEquals("cyber", engine.state.value.matchedText)
        assertEquals(' ', engine.state.value.currentTargetChar)
        assertEquals(5, engine.state.value.comboCount)
        assertEquals(100f, engine.state.value.accuracy)

        // Make an intentional mistake (type 'x' instead of ' ')
        engine.processCharInput('x')
        assertTrue(engine.state.value.hasError)
        assertEquals('x', engine.state.value.lastErrorChar)
        assertEquals(1, engine.state.value.mistakeCount)
        assertEquals(0, engine.state.value.comboCount) // Combo reset on mistake

        // Backspace to clear error
        engine.processBackspace()
        assertFalse(engine.state.value.hasError)

        // Complete the space and "ninja"
        engine.processCharInput(' ')
        engine.processCharInput('n')
        engine.processCharInput('i')
        engine.processCharInput('n')
        engine.processCharInput('j')
        engine.processCharInput('a')

        assertTrue(engine.state.value.isCompleted)
        assertEquals(11, engine.state.value.currentIndex)
        assertEquals("cyber ninja", engine.state.value.matchedText)

        // Verify session completion event was fired
        val sessionComplete = events.any { it is TypingEvent.SessionCompleted }
        assertTrue(sessionComplete)
    }

    @Test
    fun pythonRuntime_executesBasicArithmeticAndPrint() {
        val runtime = PythonRuntime()
        val code = """
            x = 15
            y = 25
            total = x + y
            print("TOTAL:", total)
        """.trimIndent()

        val result = runtime.execute(code)
        assertFalse(result.hasError)
        assertTrue(result.output.contains("TOTAL: 40"))
    }

    @Test
    fun pythonRuntime_executesConditionalsAndLoops() {
        val runtime = PythonRuntime()
        val code = """
            hp = 100
            if hp > 50:
                print("STATUS: SHIELD_ACTIVE")
            else:
                print("STATUS: CRITICAL")
                
            total_sum = 0
            for i in range(1, 4):
                total_sum += i
            print("SUM:", total_sum)
        """.trimIndent()

        val result = runtime.execute(code)
        assertFalse(result.hasError)
        assertTrue(result.output.contains("STATUS: SHIELD_ACTIVE"))
        assertTrue(result.output.contains("SUM: 6"))
    }

    @Test
    fun pythonRuntime_executesFunctionsAndReturnValues() {
        val runtime = PythonRuntime()
        val code = """
            def critical_strike(base, mult):
                return base * mult
                
            hit = critical_strike(30, 4)
            print("DAMAGE:", hit)
        """.trimIndent()

        val result = runtime.execute(code)
        assertFalse(result.hasError)
        assertTrue(result.output.contains("DAMAGE: 120"))
    }

    @Test
    fun pythonRuntime_catchesErrorsWithDetailedTraceback() {
        val runtime = PythonRuntime()
        val code = """
            x = 100
            y = unknown_variable + 5
            print(y)
        """.trimIndent()

        val result = runtime.execute(code)
        assertTrue(result.hasError)
        assertTrue(result.output.contains("Traceback"))
        assertTrue(result.output.contains("NameError") || result.output.contains("unknown_variable"))
    }
}
