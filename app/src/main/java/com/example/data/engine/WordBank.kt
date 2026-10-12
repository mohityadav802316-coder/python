package com.example.data.engine

/**
 * Curated vocabulary pools for combat arenas, rhythm runners, titan raids, and typing drills.
 */
object WordBank {

    val ninjaFightWords = listOf(
        "slash", "strike", "katana", "blade", "stealth", "shadow", "flicker", "venom",
        "shuriken", "kunai", "dash", "parry", "reflex", "smoke", "phantom", "assassin",
        "thunder", "lightning", "dragon", "shinobi", "cyclone", "whirlwind", "nemesis",
        "phantom slash", "shadow step", "lightning cut", "crimson edge", "tempest blow",
        "def counter_strike(target):", "class ShadowNinja(Player):", "import time, random",
        "ninja.teleport(x, y)", "return blade.is_critical()"
    )

    val ninjaRunWords = listOf(
        "run", "jump", "dash", "glide", "vault", "slide", "boost", "speed",
        "laser", "drone", "gap", "spike", "rooftop", "neon", "wire", "pulse",
        "hazard", "cadence", "rhythm", "velocity", "momentum", "sprint", "hyperdrive",
        "overclock", "wallrun", "catapult", "flux", "warp", "kinetic", "afterburner"
    )

    val monsterRaidWords = listOf(
        "titan armor", "plasma cannon", "nuclear core", "heavy strike", "shield breaker",
        "laser salvo", "orbital strike", "emp blast", "quantum surge", "armor piercing",
        "boss.take_damage(9999)", "while titan.health > 0:", "titan.shield_integrity = 0",
        "super_nova.charge()", "execute_finisher()", "def shatter_mech():", "yield from attack_combo()"
    )

    val typingDrillsHomeRow = listOf(
        "asdf", "jkl;", "fjad", "klaf", "fads", "lads", "fall", "flask", "glad", "salad",
        "half", "flash", "slash", "all", "ask", "dash", "fad", "lad", "sad", "dada"
    )

    val typingDrillsCommonEnglish = listOf(
        "the quick brown fox jumps over the lazy dog",
        "precision and speed are forged through continuous deliberate practice",
        "muscle memory develops when fingers return naturally to home row position",
        "mastering touch typing multiplies your software engineering velocity tenfold",
        "clarity of code reflects clarity of thought in every line written"
    )

    val typingDrillsPythonCode = listOf(
        "def binary_search(arr, target):",
        "items = [x * 2 for x in range(10) if x % 2 == 0]",
        "user_profile = {'username': 'Neo', 'level': 99, 'active': True}",
        "class CyberTitan(GameObject):",
        "with open('system_matrix.log', 'w') as file:",
        "async def fetch_telemetry(stream_id: str) -> dict:",
        "lambda x, y: x if x > y else y",
        "try:\n    connect_neural_link()\nexcept ConnectionError as err:\n    reboot_core()"
    )

    val keyboardSymbols = listOf(
        "[]", "{}", "()", "->", ":=", "==", "!=", "<=", ">=", "+=", "-=", "*=", "/=", "//=", "%=",
        "__init__", "__str__", "__repr__", "@staticmethod", "@classmethod", "@property"
    )
}
