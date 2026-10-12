package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.example.ui.theme.CyberPurple
import com.example.ui.theme.CyberSurface
import com.example.ui.theme.CyberSurfaceVariant
import com.example.ui.theme.ElectricAmber
import com.example.ui.theme.NeonCrimson
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonEmerald
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun MonsterBattleScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val typingState by viewModel.typingEngine.state.collectAsState()
    val titanHp by viewModel.titanHealth.collectAsState()
    val titanMaxHp by viewModel.titanMaxHealth.collectAsState()
    val titanShield by viewModel.titanShield.collectAsState()
    val phase by viewModel.titanPhase.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CyberBackground)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("monster_battle_screen")
    ) {
        // Top Raid Header
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
                    text = "TITAN RAID: CYBER KAIJU BATTLE",
                    color = NeonCrimson,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Monospace
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(CyberPurple.copy(alpha = 0.25f))
                        .border(1.dp, CyberPurple, RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "PHASE $phase: ${when(phase) { 1 -> "ARMORED"; 2 -> "ENRAGED"; else -> "CORE MELTDOWN" }}",
                        color = CyberPurple,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            KeyboardStatusBanner()

            IconButton(
                onClick = { viewModel.initMonsterBattle() },
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(CyberSurfaceVariant)
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

        // Boss Raid Arena Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF1E0818), Color(0xFF07040B))
                    )
                )
                .border(1.dp, NeonCrimson.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            // Boss HP & Shield Gauges
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "TITAN GOLIATH PRIME",
                        color = NeonCrimson,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            text = "SHIELD: $titanShield%",
                            color = NeonCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = "HEALTH: $titanHp / $titanMaxHp",
                            color = NeonCrimson,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Dual Shield and Health Progress Bars
                if (titanShield > 0) {
                    LinearProgressIndicator(
                        progress = { titanShield / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                            .clip(RoundedCornerShape(2.5.dp)),
                        color = NeonCyan,
                        trackColor = CyberSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }

                LinearProgressIndicator(
                    progress = { (titanHp.toFloat() / titanMaxHp.toFloat()).coerceIn(0f, 1f) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = NeonCrimson,
                    trackColor = Color(0xFF2A0808)
                )
            }

            // Massive Titan Avatar representation in center
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp)
            ) {
                val cx = size.width * 0.72f
                val cy = size.height * 0.55f

                // Titan Colossal Silhouette
                drawCircle(
                    color = Color(0xFF14081E),
                    radius = 65.dp.toPx(),
                    center = Offset(cx, cy)
                )

                // Glowing Horns / Spikes
                drawLine(
                    color = CyberPurple,
                    start = Offset(cx - 30.dp.toPx(), cy - 50.dp.toPx()),
                    end = Offset(cx - 45.dp.toPx(), cy - 75.dp.toPx()),
                    strokeWidth = 5.dp.toPx()
                )
                drawLine(
                    color = CyberPurple,
                    start = Offset(cx + 30.dp.toPx(), cy - 50.dp.toPx()),
                    end = Offset(cx + 45.dp.toPx(), cy - 75.dp.toPx()),
                    strokeWidth = 5.dp.toPx()
                )

                // Titan Burning Red Eye
                drawCircle(
                    color = if (phase >= 2) NeonCrimson else ElectricAmber,
                    radius = 9.dp.toPx(),
                    center = Offset(cx - 10.dp.toPx(), cy - 10.dp.toPx())
                )
                drawCircle(
                    color = Color.White,
                    radius = 3.dp.toPx(),
                    center = Offset(cx - 10.dp.toPx(), cy - 10.dp.toPx())
                )

                // Plasma Cannon charge line to player
                drawLine(
                    color = if (phase >= 2) NeonCrimson.copy(alpha = 0.5f) else Color(0x33FF0055),
                    start = Offset(cx, cy),
                    end = Offset(size.width * 0.15f, size.height * 0.75f),
                    strokeWidth = 2.dp.toPx()
                )
            }

            // Player Hero Position
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 24.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(CyberSurfaceVariant)
                        .border(2.dp, NeonCyan, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Dangerous,
                        contentDescription = null,
                        tint = NeonCyan,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Text(
                    text = "TARGET WEAKPOINT WITH SHIELD-BREAKER KEYSTROKES",
                    color = TextPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
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
