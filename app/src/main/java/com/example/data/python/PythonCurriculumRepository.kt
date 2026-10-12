package com.example.data.python

import com.example.data.model.MiniProject
import com.example.data.model.PythonLesson

/**
 * 30-level interactive Python Academy roadmap data and mini-project blueprints.
 */
object PythonCurriculumRepository {

    val lessons: List<PythonLesson> = listOf(
        // Phase 1: Core Fundamentals (Levels 1-5)
        PythonLesson(
            id = 1,
            phase = 1,
            phaseTitle = "Phase 1: Cyberpunk Syntax Basics",
            title = "Printing & The Python Shell",
            subtitle = "Your first transmission to the terminal matrix",
            description = "The `print()` function sends text and numerical output to the standard console output stream.",
            conceptNotes = listOf(
                "`print('Hello, Cyber World!')` outputs text.",
                "Strings can be enclosed in single or double quotes.",
                "Multiple arguments separated by commas will be printed with space separators."
            ),
            codeExample = "print(\"System online\")\nprint(\"Agent:\", \"Cipher\")",
            quizQuestion = "Which function is used to output text to the console in Python?",
            quizOptions = listOf("echo()", "console.log()", "print()", "System.out.println()"),
            quizCorrectIndex = 2,
            quizExplanation = "`print()` is Python's standard built-in function to display text and values in the terminal.",
            challengePrompt = "Print the message: Welcome to Cyber Tokyo!",
            challengeStarterCode = "# Send the greeting to terminal\n",
            challengeSolutionSnippet = "print(\"Welcome to Cyber Tokyo!\")",
            expectedOutputContains = "Welcome to Cyber Tokyo!"
        ),
        PythonLesson(
            id = 2,
            phase = 1,
            phaseTitle = "Phase 1: Cyberpunk Syntax Basics",
            title = "Variables & Dynamic Types",
            subtitle = "Storing memory values in neural registers",
            description = "In Python, variables are created when you assign a value using `=`. Python dynamically infers the data type.",
            conceptNotes = listOf(
                "Integers: `katana_damage = 50`",
                "Floats: `speed_multiplier = 1.75`",
                "Strings: `ninja_codename = 'Ghost'`",
                "Booleans: `is_stealth_active = True`"
            ),
            codeExample = "power = 100\nname = \"Kage\"\nprint(name, \"power level:\", power)",
            quizQuestion = "What happens when you write `xp = 250` without declaring an int keyword?",
            quizOptions = listOf("SyntaxError", "Python automatically infers `xp` as an integer", "It defaults to string", "It throws a TypeError"),
            quizCorrectIndex = 1,
            quizExplanation = "Python is dynamically typed; it infers the type of `xp` as `int` at assignment time.",
            challengePrompt = "Declare a variable `ninja_rank = 1` and print it.",
            challengeStarterCode = "# Declare ninja_rank and output it\n",
            challengeSolutionSnippet = "ninja_rank = 1\nprint(ninja_rank)",
            expectedOutputContains = "1"
        ),
        PythonLesson(
            id = 3,
            phase = 1,
            phaseTitle = "Phase 1: Cyberpunk Syntax Basics",
            title = "Arithmetic & Energy Calculations",
            subtitle = "Math operators powering mechanical combat",
            description = "Python provides basic operators `+`, `-`, `*`, `/`, integer division `//`, modulus `%`, and exponent `**`.",
            conceptNotes = listOf(
                "`+` addition, `-` subtraction, `*` multiplication",
                "`/` float division (returns float), `//` floor integer division",
                "`%` modulus (remainder of division), `**` power"
            ),
            codeExample = "base_slash = 30\ncombo_multiplier = 3\ntotal_damage = base_slash * combo_multiplier\nprint(\"Total Damage:\", total_damage)",
            quizQuestion = "What is the result of `17 // 5` in Python?",
            quizOptions = listOf("3.4", "3", "2", "3.0"),
            quizCorrectIndex = 1,
            quizExplanation = "The `//` operator performs floor division, truncating the fractional part to produce `3`.",
            challengePrompt = "Calculate `critical_hit = 25 * 4` and print it.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "critical_hit = 25 * 4\nprint(critical_hit)",
            expectedOutputContains = "100"
        ),
        PythonLesson(
            id = 4,
            phase = 1,
            phaseTitle = "Phase 1: Cyberpunk Syntax Basics",
            title = "String Manipulation & F-Strings",
            subtitle = "Formatting terminal telemetry and combat readouts",
            description = "F-strings (formatted string literals `f\"...{var}...\"`) provide a clean, readable syntax to interpolate values.",
            conceptNotes = listOf(
                "Prefix string with `f` to interpolate: `f\"WPM: {wpm}\"`",
                "Concatenation: `\"Cyber\" + \" \" + \"Ninja\"`",
                "String multiplication: `\"=\" * 10` produces `\"==========\"`"
            ),
            codeExample = "wpm = 95\naccuracy = 98.4\nprint(f\"Stats -> WPM: {wpm} | Accuracy: {accuracy}%\")",
            quizQuestion = "Which syntax creates an f-string in Python 3?",
            quizOptions = listOf("format(\"val: %s\")", "f\"Score: {val}\"", "\"Score: \" + str(score)", "printf(\"Score: {score}\")"),
            quizCorrectIndex = 1,
            quizExplanation = "F-strings use an `f` or `F` prefix with expressions evaluated directly inside curly braces `{}`.",
            challengePrompt = "Create `hero = 'Kage'` and print `f'Agent: {hero}'`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "hero = \"Kage\"\nprint(f\"Agent: {hero}\")",
            expectedOutputContains = "Agent: Kage"
        ),
        PythonLesson(
            id = 5,
            phase = 1,
            phaseTitle = "Phase 1: Cyberpunk Syntax Basics",
            title = "Type Conversion & Casting",
            subtitle = "Transmuting raw input bytes into typed signals",
            description = "Convert between types using built-in constructors: `int()`, `float()`, `str()`, `bool()`.",
            conceptNotes = listOf(
                "`int(\"42\")` converts string to integer `42`.",
                "`str(100)` converts integer to string `\"100\"`.",
                "`float(\"3.14\")` converts to float `3.14`."
            ),
            codeExample = "raw_score = \"500\"\nbonus = 50\ntotal = int(raw_score) + bonus\nprint(f\"Total Score: {total}\")",
            quizQuestion = "What happens if you run `\"10\" + 20` directly in Python?",
            quizOptions = listOf("\"1020\"", "30", "TypeError", "1020"),
            quizCorrectIndex = 2,
            quizExplanation = "Python does not automatically coerce strings and ints; doing so raises a TypeError.",
            challengePrompt = "Convert `\"85\"` to int and print the result added to 15.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "val = int(\"85\") + 15\nprint(val)",
            expectedOutputContains = "100"
        ),

        // Phase 2: Flow Control (Levels 6-10)
        PythonLesson(
            id = 6,
            phase = 2,
            phaseTitle = "Phase 2: Decision Matrix & Loops",
            title = "Conditional Logic: If & Else",
            subtitle = "Branching execution paths for battle state",
            description = "Use `if`, `elif`, and `else` with indentation (4 spaces) to conditionally execute code blocks.",
            conceptNotes = listOf(
                "Comparisons: `==`, `!=`, `<`, `>`, `<=`, `>=`",
                "Indentation defines blocks; no braces `{}` needed.",
                "The `else` branch executes when all preceding conditions are false."
            ),
            codeExample = "combo = 15\nif combo >= 10:\n    print(\"COMBO STRIKE!\")\nelse:\n    print(\"Normal Hit\")",
            quizQuestion = "How does Python delimit code blocks inside an `if` statement?",
            quizOptions = listOf("Curly braces `{}`", "Indentation (whitespace)", "`begin` and `end` tags", "Semicolons"),
            quizCorrectIndex = 1,
            quizExplanation = "Python uses consistent indentation (typically 4 spaces) rather than brackets to structure blocks.",
            challengePrompt = "Check if `hp = 0`. If `hp <= 0`, print `\"DEFEATED\"`, else print `\"ALIVE\"`.",
            challengeStarterCode = "hp = 0\n",
            challengeSolutionSnippet = "hp = 0\nif hp <= 0:\n    print(\"DEFEATED\")\nelse:\n    print(\"ALIVE\")",
            expectedOutputContains = "DEFEATED"
        ),
        PythonLesson(
            id = 7,
            phase = 2,
            phaseTitle = "Phase 2: Decision Matrix & Loops",
            title = "Elif & Multi-Tier Branches",
            subtitle = "Rating typing cadence across bronze, silver, gold",
            description = "`elif` allows chaining multiple conditions in sequence, evaluating until the first true condition matches.",
            conceptNotes = listOf(
                "Evaluates top-to-bottom sequentially.",
                "Use `elif` to categorize scores, WPM, and difficulty modes."
            ),
            codeExample = "wpm = 85\nif wpm >= 100:\n    rank = \"Grandmaster\"\nelif wpm >= 70:\n    rank = \"Cyber Ninja\"\nelse:\n    rank = \"Initiate\"\nprint(f\"Rank: {rank}\")",
            quizQuestion = "Can you have multiple `elif` statements in a single conditional chain?",
            quizOptions = listOf("No, only one `elif` is allowed", "Yes, you can chain as many as needed", "Only up to 3", "Only if there is no `else`"),
            quizCorrectIndex = 1,
            quizExplanation = "You can chain as many `elif` clauses as needed between the initial `if` and terminal `else`.",
            challengePrompt = "Write `xp = 120`. If `xp > 100` print `\"LEVEL UP\"` else print `\"GRINDING\"`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "xp = 120\nif xp > 100:\n    print(\"LEVEL UP\")\nelse:\n    print(\"GRINDING\")",
            expectedOutputContains = "LEVEL UP"
        ),
        PythonLesson(
            id = 8,
            phase = 2,
            phaseTitle = "Phase 2: Decision Matrix & Loops",
            title = "Logical Operators: and, or, not",
            subtitle = "Combining multi-factor defense shields",
            description = "Combine boolean conditions using `and`, `or`, and invert conditions with `not`.",
            conceptNotes = listOf(
                "`and`: True only if both sides are True.",
                "`or`: True if at least one side is True.",
                "`not`: Inverts boolean value (`not True` -> `False`)."
            ),
            codeExample = "has_katana = True\nenergy = 80\nif has_katana and energy >= 50:\n    print(\"Special Attack Ready!\")",
            quizQuestion = "What does `True or False` evaluate to?",
            quizOptions = listOf("False", "True", "None", "Error"),
            quizCorrectIndex = 1,
            quizExplanation = "`or` evaluates to `True` if any operand is true.",
            challengePrompt = "Set `shield = True` and `hp = 50`. If `shield and hp > 0`, print `\"PROTECTED\"`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "shield = True\nhp = 50\nif shield and hp > 0:\n    print(\"PROTECTED\")",
            expectedOutputContains = "PROTECTED"
        ),
        PythonLesson(
            id = 9,
            phase = 2,
            phaseTitle = "Phase 2: Decision Matrix & Loops",
            title = "While Loops & Guard Conditions",
            subtitle = "Sustaining battle simulation till boss falls",
            description = "A `while` loop repeatedly executes as long as its condition remains `True`.",
            conceptNotes = listOf(
                "Ensure loop variables are updated inside the body to prevent infinite loops.",
                "`break` terminates the loop immediately.",
                "`continue` skips to the next iteration."
            ),
            codeExample = "rounds = 3\nwhile rounds > 0:\n    print(f\"Round {rounds}\")\n    rounds -= 1\nprint(\"Battle Finished!\")",
            quizQuestion = "What statement immediately exits a while loop?",
            quizOptions = listOf("exit", "stop", "break", "return"),
            quizCorrectIndex = 2,
            quizExplanation = "`break` immediately terminates the innermost enclosing loop.",
            challengePrompt = "Count down: start `count = 3`, while `count > 0`: print(count) and decrement. Then print `\"GO!\"`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "count = 3\nwhile count > 0:\n    print(count)\n    count -= 1\nprint(\"GO!\")",
            expectedOutputContains = "GO!"
        ),
        PythonLesson(
            id = 10,
            phase = 2,
            phaseTitle = "Phase 2: Decision Matrix & Loops",
            title = "For Loops & Range Generator",
            subtitle = "Iterating through combat rounds and training reps",
            description = "The `for` loop iterates over items of any sequence or a numerical `range()`.",
            conceptNotes = listOf(
                "`range(5)` yields 0, 1, 2, 3, 4.",
                "`range(1, 6)` yields 1, 2, 3, 4, 5.",
                "`range(0, 10, 2)` yields 0, 2, 4, 6, 8 (step 2)."
            ),
            codeExample = "for wave in range(1, 4):\n    print(f\"Spawning Wave #{wave}\")",
            quizQuestion = "How many times does `for i in range(3):` iterate?",
            quizOptions = listOf("2", "3", "4", "Infinite"),
            quizCorrectIndex = 1,
            quizExplanation = "`range(3)` yields values 0, 1, 2, which is exactly 3 iterations.",
            challengePrompt = "Use a for loop with `range(1, 4)` and print `f'Rep: {i}'`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "for i in range(1, 4):\n    print(f\"Rep: {i}\")",
            expectedOutputContains = "Rep: 3"
        ),

        // Phase 3: Data Structures (Levels 11-15)
        PythonLesson(
            id = 11,
            phase = 3,
            phaseTitle = "Phase 3: Cyber Data Structures",
            title = "Lists: Arrays of Weapons & Words",
            subtitle = "Ordered, mutable collections for inventory systems",
            description = "Lists store ordered sequences of items in square brackets `[]`.",
            conceptNotes = listOf(
                "Zero-indexed: `inventory[0]` is the first item.",
                "Negative indexing: `inventory[-1]` is the last item.",
                "Append: `inventory.append('Shuriken')` adds to the end."
            ),
            codeExample = "weapons = [\"Katana\", \"Kunai\", \"Smoke Bomb\"]\nweapons.append(\"Laser Blade\")\nprint(weapons[0], len(weapons))",
            quizQuestion = "What index accesses the first item of a Python list?",
            quizOptions = listOf("1", "0", "-1", "first"),
            quizCorrectIndex = 1,
            quizExplanation = "Python lists are zero-indexed, so `list[0]` references the first element.",
            challengePrompt = "Create `skills = ['Slash', 'Dash']`, append `'Parry'`, and print `len(skills)`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "skills = [\"Slash\", \"Dash\"]\nskills.append(\"Parry\")\nprint(len(skills))",
            expectedOutputContains = "3"
        ),
        PythonLesson(
            id = 12,
            phase = 3,
            phaseTitle = "Phase 3: Cyber Data Structures",
            title = "List Slicing & Iteration",
            subtitle = "Extracting sub-arrays of target words and combos",
            description = "Use slice syntax `list[start:stop:step]` to extract sub-sequences.",
            conceptNotes = listOf(
                "`list[0:2]` takes items at index 0 and 1 (exclusive of stop).",
                "`list[:3]` takes from start up to index 3.",
                "`list[2:]` takes from index 2 to end."
            ),
            codeExample = "scores = [45, 60, 78, 92, 105]\ntop_two = scores[3:]\nprint(\"Top Scores:\", top_two)",
            quizQuestion = "Given `arr = [10, 20, 30, 40]`, what is `arr[1:3]`?",
            quizOptions = listOf("[10, 20]", "[20, 30]", "[20, 30, 40]", "[10, 20, 30]"),
            quizCorrectIndex = 1,
            quizExplanation = "Slice `[1:3]` starts at index 1 (`20`) and stops before index 3 (`40`), returning `[20, 30]`.",
            challengePrompt = "Create `ranks = ['D', 'C', 'B', 'A', 'S']` and print `ranks[3:]`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "ranks = [\"D\", \"C\", \"B\", \"A\", \"S\"]\nprint(ranks[3:])",
            expectedOutputContains = "['A', 'S']"
        ),
        PythonLesson(
            id = 13,
            phase = 3,
            phaseTitle = "Phase 3: Cyber Data Structures",
            title = "Dictionaries: Key-Value Databases",
            subtitle = "Storing telemetry, player stats, and inventory maps",
            description = "Dictionaries `{key: value}` store key-value mappings for rapid lookup.",
            conceptNotes = listOf(
                "`hero = {'name': 'Ryu', 'hp': 100, 'wpm': 85}`",
                "Access: `hero['name']`",
                "Update / Add: `hero['hp'] = 90`"
            ),
            codeExample = "ninja = {\"name\": \"Cipher\", \"speed\": 95}\nninja[\"rank\"] = \"Master\"\nprint(ninja[\"name\"], ninja[\"rank\"])",
            quizQuestion = "How do you access the value associated with key `'mana'` in dictionary `d`?",
            quizOptions = listOf("d.mana", "d('mana')", "d['mana']", "d->mana"),
            quizCorrectIndex = 2,
            quizExplanation = "Dictionary values are retrieved using square bracket subscripting with the key name: `d['mana']`.",
            challengePrompt = "Create `stats = {'wpm': 110}` and print `stats['wpm']`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "stats = {\"wpm\": 110}\nprint(stats[\"wpm\"])",
            expectedOutputContains = "110"
        ),
        PythonLesson(
            id = 14,
            phase = 3,
            phaseTitle = "Phase 3: Cyber Data Structures",
            title = "Tuples & Immutability",
            subtitle = "Fixed coordinates and locked coordinate vectors",
            description = "Tuples `(x, y)` are immutable sequences that cannot be modified after creation.",
            conceptNotes = listOf(
                "Created with parentheses: `coords = (1920, 1080)`",
                "Cannot be appended or changed (`coords[0] = 5` raises TypeError).",
                "Ideal for fixed coordinates, screen sizes, and immutable configs."
            ),
            codeExample = "spawn_point = (100, 250)\nprint(f\"Spawn X: {spawn_point[0]}, Y: {spawn_point[1]}\")",
            quizQuestion = "What is the primary difference between a list and a tuple in Python?",
            quizOptions = listOf("Lists are faster", "Tuples are immutable (cannot be modified)", "Lists cannot hold strings", "Tuples cannot be indexed"),
            quizCorrectIndex = 1,
            quizExplanation = "Tuples are immutable: once created, their elements cannot be added, removed, or changed.",
            challengePrompt = "Create `pos = (50, 75)` and print `pos[0]`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "pos = (50, 75)\nprint(pos[0])",
            expectedOutputContains = "50"
        ),
        PythonLesson(
            id = 15,
            phase = 3,
            phaseTitle = "Phase 3: Cyber Data Structures",
            title = "Sets & Unique Key Sequences",
            subtitle = "Deduplicating words and fast membership checking",
            description = "Sets `{item1, item2}` are unordered collections of unique elements.",
            conceptNotes = listOf(
                "Automatically removes duplicates: `set([1, 2, 2, 3])` -> `{1, 2, 3}`",
                "Fast membership check using `in`: `'katana' in weapons`",
                "Set operations: union `|`, intersection `&`."
            ),
            codeExample = "tags = [\"ninja\", \"speed\", \"ninja\", \"arcade\"]\nunique_tags = set(tags)\nprint(f\"Unique count: {len(unique_tags)}\")",
            quizQuestion = "What does `set([1, 1, 2, 3])` contain?",
            quizOptions = listOf("{1, 1, 2, 3}", "{1, 2, 3}", "[1, 2, 3]", "Error"),
            quizCorrectIndex = 1,
            quizExplanation = "Sets only retain unique elements, discarding duplicate entries.",
            challengePrompt = "Create `skills = set(['slash', 'slash', 'dash'])` and print `len(skills)`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "skills = set([\"slash\", \"slash\", \"dash\"])\nprint(len(skills))",
            expectedOutputContains = "2"
        ),

        // Phase 4: Functions & Modularity (Levels 16-20)
        PythonLesson(
            id = 16,
            phase = 4,
            phaseTitle = "Phase 4: Functions & Modular Power",
            title = "Defining Functions with def",
            subtitle = "Packaging reusable combat and calculation routines",
            description = "Use `def function_name(params):` followed by indented body to encapsulate reusable logic.",
            conceptNotes = listOf(
                "`def calculate_damage(base, combo):`",
                "Return output using the `return` statement.",
                "Call functions: `damage = calculate_damage(20, 3)`"
            ),
            codeExample = "def calculate_xp(words, accuracy):\n    return int(words * (accuracy / 100) * 10)\n\nxp_earned = calculate_xp(50, 96)\nprint(\"XP Earned:\", xp_earned)",
            quizQuestion = "What keyword exits a function and passes back a result to the caller?",
            quizOptions = listOf("send", "output", "return", "yield"),
            quizCorrectIndex = 2,
            quizExplanation = "`return` immediately ends function execution and hands the value back to the caller.",
            challengePrompt = "Define `def square(n): return n * n` and print `square(7)`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "def square(n):\n    return n * n\nprint(square(7))",
            expectedOutputContains = "49"
        ),
        PythonLesson(
            id = 17,
            phase = 4,
            phaseTitle = "Phase 4: Functions & Modular Power",
            title = "Default Parameters & Keyword Arguments",
            subtitle = "Customizing attack parameters with smart fallbacks",
            description = "Functions can specify default parameter values: `def attack(target, damage=25):`.",
            conceptNotes = listOf(
                "Parameters with defaults must come after positional parameters.",
                "Callers can override defaults or omit them to use standard values."
            ),
            codeExample = "def spawn_enemy(name, health=100):\n    return f\"{name} spawned with {health} HP\"\n\nprint(spawn_enemy(\"Drone\"))\nprint(spawn_enemy(\"Titan\", 500))",
            quizQuestion = "In `def strike(dmg=10):`, what is `strike()` without arguments?",
            quizOptions = listOf("Error", "dmg takes default value 10", "dmg is None", "dmg is 0"),
            quizCorrectIndex = 1,
            quizExplanation = "When the argument is omitted, Python uses the default value `10`.",
            challengePrompt = "Write `def greet(agent=\"Cipher\"): return f\"Agent: {agent}\"`. Print `greet()`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "def greet(agent=\"Cipher\"):\n    return f\"Agent: {agent}\"\nprint(greet())",
            expectedOutputContains = "Agent: Cipher"
        ),
        PythonLesson(
            id = 18,
            phase = 4,
            phaseTitle = "Phase 4: Functions & Modular Power",
            title = "Variable Scope: Local vs Global",
            subtitle = "Securing local memory against register pollution",
            description = "Variables created inside functions belong to the function's local scope.",
            conceptNotes = listOf(
                "Local variables exist only during function execution.",
                "To modify a global variable inside a function, declare `global var_name`."
            ),
            codeExample = "global_score = 0\ndef add_score(points):\n    global global_score\n    global_score += points\n\nadd_score(150)\nprint(\"Score:\", global_score)",
            quizQuestion = "Can a function read a variable defined in the outer global scope?",
            quizOptions = listOf("No, never", "Yes, read access is permitted", "Only if it is an integer", "Only inside loops"),
            quizCorrectIndex = 1,
            quizExplanation = "Python functions can freely read global variables; `global` is only required when assigning or modifying them.",
            challengePrompt = "Set `total = 50`. Write `def boost(): return total + 50`. Print `boost()`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "total = 50\ndef boost():\n    return total + 50\nprint(boost())",
            expectedOutputContains = "100"
        ),
        PythonLesson(
            id = 19,
            phase = 4,
            phaseTitle = "Phase 4: Functions & Modular Power",
            title = "Lambda Functions: Anonymous Micro-Routines",
            subtitle = "One-line expressions for quick data transforms",
            description = "Lambdas are compact, anonymous functions defined with `lambda params: expression`.",
            conceptNotes = listOf(
                "Single expression only, returns expression result implicitly.",
                "Commonly used with `sorted()`, `map()`, and `filter()`."
            ),
            codeExample = "critical_strike = lambda base, mult: base * mult\nprint(\"Critical:\", critical_strike(30, 3))",
            quizQuestion = "How many expressions can a Python lambda contain?",
            quizOptions = listOf("Unlimited", "Exactly one expression", "Up to three statements", "None"),
            quizCorrectIndex = 1,
            quizExplanation = "A lambda function in Python is syntactically restricted to a single expression.",
            challengePrompt = "Define `double = lambda x: x * 2` and print `double(21)`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "double = lambda x: x * 2\nprint(double(21))",
            expectedOutputContains = "42"
        ),
        PythonLesson(
            id = 20,
            phase = 4,
            phaseTitle = "Phase 4: Functions & Modular Power",
            title = "List Comprehensions",
            subtitle = "High-velocity inline data filtering and mapping",
            description = "List comprehensions offer a concise syntax: `[expression for item in iterable if condition]`.",
            conceptNotes = listOf(
                "Transform: `[x * 2 for x in numbers]`",
                "Filter: `[x for x in numbers if x > 50]`"
            ),
            codeExample = "raw_scores = [40, 75, 90, 30, 85]\nhigh_scores = [s for s in raw_scores if s >= 70]\nprint(\"High Scores:\", high_scores)",
            quizQuestion = "What does `[n * 2 for n in range(3)]` evaluate to?",
            quizOptions = listOf("[0, 2, 4]", "[2, 4, 6]", "[0, 1, 2]", "[1, 2, 3]"),
            quizCorrectIndex = 0,
            quizExplanation = "For `n` in 0, 1, 2, multiplying by 2 yields `[0, 2, 4]`.",
            challengePrompt = "Generate `evens = [x for x in range(6) if x % 2 == 0]` and print `evens`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "evens = [x for x in range(6) if x % 2 == 0]\nprint(evens)",
            expectedOutputContains = "[0, 2, 4]"
        ),

        // Phase 5: Object-Oriented Architecture (Levels 21-25)
        PythonLesson(
            id = 21,
            phase = 5,
            phaseTitle = "Phase 5: Object-Oriented Architecture",
            title = "Classes & The __init__ Constructor",
            subtitle = "Blueprinting cyber entities, heroes, and bosses",
            description = "Classes represent blueprints for objects, initialized with the `__init__` constructor method.",
            conceptNotes = listOf(
                "`class Ninja:` defines a class.",
                "`def __init__(self, name, hp):` binds instance attributes.",
                "`self` represents the specific instance being operated on."
            ),
            codeExample = "class Player:\n    def __init__(self, name, hp):\n        self.name = name\n        self.hp = hp\n\np = Player(\"Ghost\", 100)\nprint(f\"Player {p.name} loaded with {p.hp} HP\")",
            quizQuestion = "What parameter must be the first argument of any instance method in Python?",
            quizOptions = listOf("this", "self", "instance", "cls"),
            quizCorrectIndex = 1,
            quizExplanation = "Python passes the current instance explicitly as the first parameter, conventionally named `self`.",
            challengePrompt = "Create `class Mech: def __init__(self, model): self.model = model`. Instantiate `m = Mech('Titan-X')` and print `m.model`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "class Mech:\n    def __init__(self, model):\n        self.model = model\nm = Mech(\"Titan-X\")\nprint(m.model)",
            expectedOutputContains = "Titan-X"
        ),
        PythonLesson(
            id = 22,
            phase = 5,
            phaseTitle = "Phase 5: Object-Oriented Architecture",
            title = "Methods: Actions & Behaviors",
            subtitle = "Invoking attack maneuvers and status updates",
            description = "Methods are functions defined inside a class that operate on the instance's state.",
            conceptNotes = listOf(
                "Access and update instance state via `self.attribute`.",
                "Call methods using dot syntax: `player.slash(enemy)`."
            ),
            codeExample = "class Hero:\n    def __init__(self, name):\n        self.name = name\n        self.combo = 0\n    def hit(self):\n        self.combo += 1\n        return f\"{self.name} combo: {self.combo}\"\n\nh = Hero(\"Ryu\")\nh.hit()\nprint(h.hit())",
            quizQuestion = "How do you access instance attribute `energy` from inside a class method?",
            quizOptions = listOf("energy", "this.energy", "self.energy", "instance.energy"),
            quizCorrectIndex = 2,
            quizExplanation = "Instance attributes are accessed via `self.attribute_name` inside methods.",
            challengePrompt = "Create `Hero` with `xp = 0` and method `add(n): self.xp += n`. Add 50 and print `h.xp`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "class Hero:\n    def __init__(self):\n        self.xp = 0\n    def add(self, n):\n        self.xp += n\nh = Hero()\nh.add(50)\nprint(h.xp)",
            expectedOutputContains = "50"
        ),
        PythonLesson(
            id = 23,
            phase = 5,
            phaseTitle = "Phase 5: Object-Oriented Architecture",
            title = "Inheritance: Specializing Sub-Classes",
            subtitle = "Extending base fighters into elemental ninjas",
            description = "Inheritance allows a child class to inherit attributes and methods from a parent class.",
            conceptNotes = listOf(
                "`class ShadowNinja(BaseFighter):` inherits from `BaseFighter`.",
                "Child classes can override parent methods or add new ones."
            ),
            codeExample = "class Character:\n    def __init__(self, name):\n        self.name = name\nclass Shinobi(Character):\n    def stealth(self):\n        return f\"{self.name} entered shadow mode\"\n\ns = Shinobi(\"Kage\")\nprint(s.stealth())",
            quizQuestion = "How do you declare that `TitanBoss` inherits from `Enemy`?",
            quizOptions = listOf("class TitanBoss extends Enemy:", "class TitanBoss(Enemy):", "class TitanBoss : Enemy", "class TitanBoss -> Enemy:"),
            quizCorrectIndex = 1,
            quizExplanation = "In Python, parent classes are placed inside parentheses after the class name: `class Child(Parent):`.",
            challengePrompt = "Create `class Base: def info(self): return 'Base'` and `class Sub(Base): pass`. Print `Sub().info()`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "class Base:\n    def info(self):\n        return \"Base\"\nclass Sub(Base):\n    pass\nprint(Sub().info())",
            expectedOutputContains = "Base"
        ),
        PythonLesson(
            id = 24,
            phase = 5,
            phaseTitle = "Phase 5: Object-Oriented Architecture",
            title = "String Representations: __str__ & __repr__",
            subtitle = "Pretty-printing game objects in debugging logs",
            description = "Implementing `__str__` customizes the human-readable string returned by `print(object)`.",
            conceptNotes = listOf(
                "`__str__` is called by `str()` and `print()`.",
                "`__repr__` provides an unambiguous developer representation."
            ),
            codeExample = "class Weapon:\n    def __init__(self, name, damage):\n        self.name = name\n        self.damage = damage\n    def __str__(self):\n        return f\"<{self.name}: {self.damage} DMG>\"\n\nw = Weapon(\"Katana\", 75)\nprint(w)",
            quizQuestion = "Which special method does `print(instance)` invoke to get a string representation?",
            quizOptions = listOf("__print__", "__str__", "__text__", "__display__"),
            quizCorrectIndex = 1,
            quizExplanation = "`print()` invokes `str()` which looks for the `__str__` dunder method on the object.",
            challengePrompt = "Define `class Bot: def __str__(self): return 'BotOnline'`. Print `Bot()`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "class Bot:\n    def __str__(self):\n        return \"BotOnline\"\nprint(Bot())",
            expectedOutputContains = "BotOnline"
        ),
        PythonLesson(
            id = 25,
            phase = 5,
            phaseTitle = "Phase 5: Object-Oriented Architecture",
            title = "Encapsulation & Property Decorators",
            subtitle = "Shielding internal health states with @property",
            description = "Use `@property` to create getter and setter methods that behave like regular attributes.",
            conceptNotes = listOf(
                "Prevents invalid attribute assignments.",
                "Getter: `@property def hp(self):`"
            ),
            codeExample = "class Shield:\n    def __init__(self, max_cap):\n        self._cap = max_cap\n    @property\n    def capacity(self):\n        return self._cap\n\ns = Shield(250)\nprint(\"Shield Capacity:\", s.capacity)",
            quizQuestion = "What is the purpose of the `@property` decorator?",
            quizOptions = listOf("Declares a database table", "Allows calling a method like a readable attribute", "Forces variable to be integer", "Creates an infinite loop"),
            quizCorrectIndex = 1,
            quizExplanation = "`@property` turns an instance method into a getter that can be accessed like a simple property without `()`.",
            challengePrompt = "Create a class with `@property def status(self): return 'ONLINE'`. Print `instance.status`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "class Core:\n    @property\n    def status(self):\n        return \"ONLINE\"\nprint(Core().status)",
            expectedOutputContains = "ONLINE"
        ),

        // Phase 6: Professional Python (Levels 26-30)
        PythonLesson(
            id = 26,
            phase = 6,
            phaseTitle = "Phase 6: Pro Python & Robust Systems",
            title = "Error Handling with Try & Except",
            subtitle = "Catching runtime faults before system crash",
            description = "Use `try`, `except`, and `finally` to handle errors gracefully.",
            conceptNotes = listOf(
                "`try:` contains code that might raise an exception.",
                "`except SpecificError as e:` catches and handles it.",
                "Common exceptions: `ValueError`, `KeyError`, `IndexError`, `ZeroDivisionError`."
            ),
            codeExample = "def safe_divide(a, b):\n    try:\n        return a / b\n    except Exception as e:\n        return \"Error: Cannot divide by zero\"\n\nprint(safe_divide(10, 2))\nprint(safe_divide(10, 0))",
            quizQuestion = "What block of code executes regardless of whether an exception occurred?",
            quizOptions = listOf("try", "catch", "finally", "else"),
            quizCorrectIndex = 2,
            quizExplanation = "The `finally` block always executes after `try` and `except` blocks complete.",
            challengePrompt = "Catch division by zero: `try: x = 10 / 0 except: print('HANDLED')`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "try:\n    x = 10 / 0\nexcept:\n    print(\"HANDLED\")",
            expectedOutputContains = "HANDLED"
        ),
        PythonLesson(
            id = 27,
            phase = 6,
            phaseTitle = "Phase 6: Pro Python & Robust Systems",
            title = "File Input & Output Patterns",
            subtitle = "Writing telemetry and reading system archives",
            description = "Files are safely read and written using the `with open(...) as file:` context manager.",
            conceptNotes = listOf(
                "`'r'` read mode, `'w'` write mode, `'a'` append mode.",
                "`with` statement guarantees the file is properly closed automatically."
            ),
            codeExample = "# In memory simulation\nlog_entry = \"[BATTLE] Wave 5 Cleared - 92 WPM\"\nprint(\"Saved:\", log_entry)",
            quizQuestion = "Why is the `with` statement preferred when opening files?",
            quizOptions = listOf("It runs faster", "It automatically closes the file even if errors occur", "It encrypts the file", "It creates infinite loops"),
            quizCorrectIndex = 1,
            quizExplanation = "The `with` statement provides context management, ensuring the file resource is reliably closed.",
            challengePrompt = "Print `\"LOG SAVED\"` to simulate writing a telemetry entry.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "print(\"LOG SAVED\")",
            expectedOutputContains = "LOG SAVED"
        ),
        PythonLesson(
            id = 28,
            phase = 6,
            phaseTitle = "Phase 6: Pro Python & Robust Systems",
            title = "Modules & Package Architecture",
            subtitle = "Structuring multi-file game libraries",
            description = "Split code into modules (`.py` files) and use `import` to share functions and classes.",
            conceptNotes = listOf(
                "`import math` brings in the math library.",
                "`from random import choice` imports specific functions."
            ),
            codeExample = "modules = [\"battle.py\", \"skills.py\", \"config.py\"]\nprint(\"Loaded modules:\", len(modules))",
            quizQuestion = "What statement imports specific functions from another module?",
            quizOptions = listOf("include module", "from module import function", "require(module)", "using module"),
            quizCorrectIndex = 1,
            quizExplanation = "The `from <module> import <item>` syntax imports designated symbols directly into scope.",
            challengePrompt = "Print `\"MODULES IMPORTED\"`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "print(\"MODULES IMPORTED\")",
            expectedOutputContains = "MODULES IMPORTED"
        ),
        PythonLesson(
            id = 29,
            phase = 6,
            phaseTitle = "Phase 6: Pro Python & Robust Systems",
            title = "Generators & Yield Statements",
            subtitle = "Streaming infinite waves without memory bottlenecks",
            description = "Generators produce sequence values on-demand using the `yield` keyword.",
            conceptNotes = listOf(
                "`yield` pauses execution and resumes from that point on the next call.",
                "Conserves memory compared to building gigantic in-memory lists."
            ),
            codeExample = "def wave_spawner(max_waves):\n    w = 1\n    while w <= max_waves:\n        yield f\"Wave {w}\"\n        w += 1\n\nfor wave in wave_spawner(3):\n    print(wave)",
            quizQuestion = "What keyword turns a normal function into a generator?",
            quizOptions = listOf("stream", "yield", "generate", "return"),
            quizCorrectIndex = 1,
            quizExplanation = "Any function containing a `yield` statement becomes a generator function in Python.",
            challengePrompt = "Print `\"WAVE 1 YIELDED\"`.",
            challengeStarterCode = "",
            challengeSolutionSnippet = "print(\"WAVE 1 YIELDED\")",
            expectedOutputContains = "WAVE 1 YIELDED"
        ),
        PythonLesson(
            id = 30,
            phase = 6,
            phaseTitle = "Phase 6: Pro Python & Robust Systems",
            title = "Decorators: Function Metaprogramming",
            subtitle = "Wrapping telemetry, timers, and buff multipliers",
            description = "Decorators are functions that take another function as input and extend its behavior.",
            conceptNotes = listOf(
                "Decorated using the `@decorator_name` syntax.",
                "Ideal for logging, measuring execution time, and authentication checks."
            ),
            codeExample = "def combat_logger(func):\n    def wrapper(*args):\n        print(\"[LOG] Combat Action Executed\")\n        return func(*args)\n    return wrapper\n\nprint(\"Decorator Pattern Mastered!\")",
            quizQuestion = "What symbol is placed before a decorator name above a function definition?",
            quizOptions = listOf("#", "$", "@", "&"),
            quizCorrectIndex = 2,
            quizExplanation = "The `@` symbol (pie syntax) is used to apply decorators in Python.",
            challengePrompt = "Print `\"SHOGUN CERTIFIED: 30/30\"` to conclude your Academy journey!",
            challengeStarterCode = "",
            challengeSolutionSnippet = "print(\"SHOGUN CERTIFIED: 30/30\")",
            expectedOutputContains = "SHOGUN CERTIFIED: 30/30"
        )
    )

    val miniProjects: List<MiniProject> = listOf(
        MiniProject(
            id = "proj_calculator",
            title = "CLI Combat Calculator",
            category = "Arithmetic & Logic",
            difficulty = "Beginner",
            description = "A terminal calculator computing basic math, critical damage multipliers, and percentages with formatted output.",
            keyConcepts = listOf("Functions", "Conditionals", "Type Casting", "String Formatting"),
            sourceCode = """
# CLI Combat Calculator
def calculate(a, b, op):
    if op == '+':
        return a + b
    elif op == '-':
        return a - b
    elif op == '*':
        return a * b
    elif op == '/':
        return a / b if b != 0 else "Error: Division by 0"
    return "Unknown operator"

base_dmg = 45
multiplier = 3
total = calculate(base_dmg, multiplier, '*')
print("=== COMBAT CALCULATOR ===")
print(f"Base Damage: {base_dmg}")
print(f"Multiplier : {multiplier}")
print(f"Total Slash: {total} DMG")
""".trimIndent()
        ),
        MiniProject(
            id = "proj_todo",
            title = "Cyber Ninja Task Manager",
            category = "Data Structures",
            difficulty = "Beginner",
            description = "Manage stealth missions and daily coding milestones with an in-memory task tracker.",
            keyConcepts = listOf("Lists", "Loops", "String Manipulation", "CRUD"),
            sourceCode = """
# Cyber Ninja Task Manager
missions = []

def add_mission(task):
    missions.append(task)
    print(f"+ Mission Added: {task}")

def show_missions():
    print(f"\n--- ACTIVE MISSIONS ({len(missions)}) ---")
    for i in range(len(missions)):
        print(f"[{i + 1}] {missions[i]}")

add_mission("Infiltrate Shinjuku Megatower")
add_mission("Extract encrypted Python kernel")
add_mission("Exfiltrate via neon rooftops")

show_missions()
""".trimIndent()
        ),
        MiniProject(
            id = "proj_guess_number",
            title = "Neural Frequency Number Guesser",
            category = "Algorithms & Search",
            difficulty = "Intermediate",
            description = "An interactive simulation of binary search and hint-based guessing of unknown frequencies.",
            keyConcepts = listOf("While Loops", "Comparisons", "Binary Search", "Counters"),
            sourceCode = """
# Neural Frequency Guesser Simulation
secret_target = 42
low = 1
high = 100
attempts = 0

print("=== NEURAL GUESSER SIMULATION ===")
while low <= high:
    attempts += 1
    guess = (low + high) // 2
    print(f"Attempt #{attempts}: Probing frequency {guess}...")
    if guess == secret_target:
        print(f"SUCCESS! Frequency matched: {guess} in {attempts} probes.")
        break
    elif guess < secret_target:
        print("  -> Signal too LOW, adjusting higher.")
        low = guess + 1
    else:
        print("  -> Signal too HIGH, adjusting lower.")
        high = guess - 1
""".trimIndent()
        ),
        MiniProject(
            id = "proj_password_gen",
            title = "Cyber Vault Password Generator",
            category = "Security & Strings",
            difficulty = "Intermediate",
            description = "Generates cryptographically structured passwords combining alphanumeric characters and symbol sets.",
            keyConcepts = listOf("Lists", "String Slicing", "Modulus", "Randomization"),
            sourceCode = """
# Cyber Vault Password Generator
alphabet = ["a", "b", "c", "x", "y", "z", "A", "B", "C", "X", "Y", "Z"]
digits = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]
symbols = ["!", "@", "#", "$", "%", "*"]

def generate_key(seed):
    key = ""
    for i in range(12):
        if i % 3 == 0:
            key += alphabet[(seed + i * 3) % len(alphabet)]
        elif i % 3 == 1:
            key += digits[(seed + i * 5) % len(digits)]
        else:
            key += symbols[(seed + i * 7) % len(symbols)]
    return key

pw1 = generate_key(17)
pw2 = generate_key(89)
print("=== SECURE KEY GENERATOR ===")
print("Generated Vault Key 1:", pw1)
print("Generated Vault Key 2:", pw2)
""".trimIndent()
        ),
        MiniProject(
            id = "proj_text_rpg",
            title = "Neo-Tokyo Text Dungeon Crawler",
            category = "Object-Oriented Design",
            difficulty = "Advanced",
            description = "An object-oriented text RPG turn-based battle simulation pitting the player against automated rogue mechs.",
            keyConcepts = listOf("Classes", "__init__", "Methods", "State Machines"),
            sourceCode = """
# Neo-Tokyo Text Dungeon Battle
class Entity:
    def __init__(self, name, hp, attack):
        self.name = name
        self.hp = hp
        self.attack = attack
    
    def is_alive(self):
        return self.hp > 0

class Hero(Entity):
    def strike(self, target):
        dmg = self.attack
        target.hp -= dmg
        print(f"{self.name} strikes {target.name} for {dmg} DMG! (Enemy HP: {max(0, target.hp)})")

player = Hero("Cipher", 100, 25)
boss = Entity("Cyber Ronin", 60, 15)

print("=== COMBAT ENGAGED ===")
turn = 1
while player.is_alive() and boss.is_alive():
    print(f"\n[Turn {turn}]")
    player.strike(boss)
    if not boss.is_alive():
        print("VICTORY! The rogue Cyber Ronin was terminated.")
        break
    turn += 1
""".trimIndent()
        ),
        MiniProject(
            id = "proj_contact_book",
            title = "Neural Contact Network",
            category = "Dictionary Databases",
            difficulty = "Intermediate",
            description = "A contact registry with lookup, insertion, update, and search by role/rank.",
            keyConcepts = listOf("Dictionaries", "Formatting", "Lookups", "Comprehensions"),
            sourceCode = """
# Neural Contact Network
contacts = {
    "Cipher": {"role": "Infiltrator", "tier": "Master"},
    "Kage": {"role": "Ronin", "tier": "A-Rank"},
    "Valkyrie": {"role": "Sniper", "tier": "S-Rank"}
}

def display_directory():
    print("=== NEURAL NETWORK DIRECTORY ===")
    for agent in contacts:
        data = contacts[agent]
        print(f"Agent: {agent:<10} | Role: {data['role']:<12} | Tier: {data['tier']}")

display_directory()
""".trimIndent()
        )
    )
}
