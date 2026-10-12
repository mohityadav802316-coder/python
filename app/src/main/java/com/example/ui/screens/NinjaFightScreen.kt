package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SportsMma
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.MainViewModel
import com.example.ui.components.KeyboardStatusBanner
import com.example.ui.components.TypingDisplayCard
import com.example.ui.theme.CyberBackground
import com.example.ui.theme.CyberCardBorder
import com.example.ui.theme.CyberSurface
import com.example.ui.theme.CyberSurfaceVariant
import com.example.ui.theme.ElectricAmber
import com.example.ui.theme.NeonCrimson
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonEmerald
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun NinjaFightScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val typingState by viewModel.typingEngine.state.collectAsState()
    val wave by viewModel.ninjaWave.collectAsState()
    val playerHp by viewModel.playerHealth.collectAsState()
    val enemyHp by viewModel.enemyHealth.collectAsState()
    val enemyName by viewModel.enemyName.collectAsState()
    val combatMove by viewModel.combatMoveAnimation.collectAsState()

    // Slash animation effect state
    val slashAnim = remember { Animatable(0f) }
    LaunchedEffect(combatMove) {
        if (combatMove == "SLASH_CRITICAL") {
            slashAnim.snapTo(0f)
            slashAnim.animateTo(1f, tween(250, easing = FastOutSlowInEasing))
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CyberBackground)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("ninja_fight_screen")
    ) {
        // Top Arena Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = { viewModel.navigateBack() },
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(CyberSurfaceVariant)
                        .testTag("back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Text(
                    text = "NINJA FIGHT: NEO-TOKYO ROOFTOPS",
                    color = NeonCyan,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Monospace
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(NeonCrimson.copy(alpha = 0.2f))
                        .border(1.dp, NeonCrimson, RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "WAVE $wave",
                        color = NeonCrimson,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            KeyboardStatusBanner()

            IconButton(
                onClick = { viewModel.initNinjaFight() },
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(CyberSurfaceVariant)
                    .testTag("restart_fight_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Restart",
                    tint = TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Center Arena Battle Stage (Landscape 2-side: Player Ninja vs Enemy Ronin)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF0F172A), Color(0xFF050811))
                    )
                )
                .border(1.dp, CyberCardBorder, RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            // Neon city skyline canvas background
            CitySkylineBackdrop(modifier = Modifier.fillMaxSize())

            // Health Gauges Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Player Health
                Column(modifier = Modifier.width(220.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "CYBER NINJA (YOU)",
                            color = NeonCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = "$playerHp / 100",
                            color = NeonCyan,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    LinearProgressIndicator(
                        progress = { playerHp / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = NeonCyan,
                        trackColor = CyberSurfaceVariant
                    )
                }

                // VS Badge
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(CyberSurfaceVariant)
                        .border(1.dp, ElectricAmber, CircleShape)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "VS",
                        color = ElectricAmber,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black
                    )
                }

                // Enemy Health
                Column(modifier = Modifier.width(220.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = enemyName.uppercase(),
                            color = NeonCrimson,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = "$enemyHp HP",
                            color = NeonCrimson,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    LinearProgressIndicator(
                        progress = { (enemyHp / (100f + (wave * 20f))).coerceIn(0f, 1f) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = NeonCrimson,
                        trackColor = CyberSurfaceVariant
                    )
                }
            }

            // Interactive Fighters Stage
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp, start = 40.dp, end = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                // Cyber Ninja Avatar
                CyberNinjaAvatar(
                    isAttacking = slashAnim.value in 0.05f..0.85f,
                    modifier = Modifier.size(110.dp)
                )

                // Slash Strike visual effect when attacking
                if (slashAnim.value in 0.01f..0.99f) {
                    SlashEffect(
                        progress = slashAnim.value,
                        modifier = Modifier
                            .size(120.dp)
                            .align(Alignment.CenterVertically)
                    )
                }

                // Enemy Mech / Ronin
                CyberEnemyAvatar(
                    isHit = slashAnim.value in 0.3f..0.8f,
                    enemyName = enemyName,
                    modifier = Modifier.size(110.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Bottom Typing Engine Display
        TypingDisplayCard(
            engineState = typingState,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CitySkylineBackdrop(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Background neon moon
        drawCircle(
            color = Color(0x2200F0FF),
            radius = 50.dp.toPx(),
            center = Offset(w * 0.5f, h * 0.45f)
        )

        // Distant silhouette rooftops
        val roofPath = Path().apply {
            moveTo(0f, h * 0.85f)
            lineTo(w * 0.15f, h * 0.85f)
            lineTo(w * 0.15f, h * 0.70f)
            lineTo(w * 0.30f, h * 0.70f)
            lineTo(w * 0.30f, h * 0.80f)
            lineTo(w * 0.45f, h * 0.80f)
            lineTo(w * 0.45f, h * 0.65f)
            lineTo(w * 0.60f, h * 0.65f)
            lineTo(w * 0.60f, h * 0.78f)
            lineTo(w * 0.80f, h * 0.78f)
            lineTo(w * 0.80f, h * 0.68f)
            lineTo(w, h * 0.68f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        drawPath(roofPath, color = Color(0xFF070B14))

        // Rooftop fighting platform
        drawLine(
            color = NeonCyan.copy(alpha = 0.6f),
            start = Offset(0f, h * 0.92f),
            end = Offset(w, h * 0.92f),
            strokeWidth = 3.dp.toPx()
        )
    }
}

@Composable
fun CyberNinjaAvatar(
    isAttacking: Boolean,
    modifier: Modifier = Modifier
) {
    val attackShift = if (isAttacking) 35.dp else 0.dp
    Column(
        modifier = modifier.padding(start = attackShift),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape)
                .background(Color(0xFF1E293B))
                .border(2.dp, if (isAttacking) NeonEmerald else NeonCyan, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val cx = size.width / 2
                val cy = size.height / 2
                // Ninja mask silhouette
                drawCircle(color = Color(0xFF0B132B), radius = size.width * 0.38f, center = Offset(cx, cy))
                // Glowing cyan eye visor
                drawLine(
                    color = NeonCyan,
                    start = Offset(cx - 16.dp.toPx(), cy - 4.dp.toPx()),
                    end = Offset(cx + 16.dp.toPx(), cy - 4.dp.toPx()),
                    strokeWidth = 4.dp.toPx()
                )
                // Katana blade angle
                drawLine(
                    color = if (isAttacking) NeonEmerald else Color(0xFF94A3B8),
                    start = Offset(cx + 12.dp.toPx(), cy - 20.dp.toPx()),
                    end = Offset(cx + 34.dp.toPx(), cy + 15.dp.toPx()),
                    strokeWidth = 3.dp.toPx()
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if (isAttacking) "⚡ SLASH!" else "SHINOBI",
            color = if (isAttacking) NeonEmerald else NeonCyan,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun CyberEnemyAvatar(
    isHit: Boolean,
    enemyName: String,
    modifier: Modifier = Modifier
) {
    val tintColor = if (isHit) NeonCrimson else Color(0xFFEF4444)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(76.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF1E293B))
                .border(2.dp, tintColor, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val cx = size.width / 2
                val cy = size.height / 2
                // Mech head
                drawRect(
                    color = if (isHit) Color(0xFF7F1D1D) else Color(0xFF18181B),
                    topLeft = Offset(cx - 20.dp.toPx(), cy - 20.dp.toPx()),
                    size = androidx.compose.ui.geometry.Size(40.dp.toPx(), 40.dp.toPx())
                )
                // Red glowing visor
                drawLine(
                    color = if (isHit) Color.White else NeonCrimson,
                    start = Offset(cx - 14.dp.toPx(), cy - 6.dp.toPx()),
                    end = Offset(cx + 14.dp.toPx(), cy - 6.dp.toPx()),
                    strokeWidth = 5.dp.toPx()
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if (isHit) "💥 -25 HP" else enemyName.take(12),
            color = tintColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun SlashEffect(
    progress: Float,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val alpha = (1f - progress).coerceIn(0f, 1f)
        val slashX = w * progress

        drawLine(
            color = NeonCyan.copy(alpha = alpha),
            start = Offset(slashX - 25.dp.toPx(), h * 0.2f),
            end = Offset(slashX + 25.dp.toPx(), h * 0.8f),
            strokeWidth = 6.dp.toPx()
        )
        drawLine(
            color = Color.White.copy(alpha = alpha),
            start = Offset(slashX - 10.dp.toPx(), h * 0.25f),
            end = Offset(slashX + 10.dp.toPx(), h * 0.75f),
            strokeWidth = 2.dp.toPx()
        )
    }
}
