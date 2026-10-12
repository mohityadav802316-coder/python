package com.example.ui.screens

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
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
import com.example.ui.theme.CyberSurface
import com.example.ui.theme.CyberSurfaceVariant
import com.example.ui.theme.ElectricAmber
import com.example.ui.theme.NeonCrimson
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonEmerald
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun NinjaRunScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val typingState by viewModel.typingEngine.state.collectAsState()
    val distance by viewModel.runnerDistance.collectAsState()
    val speedKmh by viewModel.runnerSpeedKmh.collectAsState()
    val obstacle by viewModel.runnerObstacleAhead.collectAsState()

    val infiniteTransition = rememberInfiniteTransition(label = "runner")
    val runOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "runnerLoop"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CyberBackground)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("ninja_run_screen")
    ) {
        // Top Runner Header
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
                    text = "NINJA RUN: CADENCE SPRINT",
                    color = NeonEmerald,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Monospace
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(NeonEmerald.copy(alpha = 0.2f))
                        .border(1.dp, NeonEmerald, RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "$distance M",
                        color = NeonEmerald,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            KeyboardStatusBanner()

            IconButton(
                onClick = { viewModel.initNinjaRun() },
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

        // High-Speed Rooftop Track Stage
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(CyberSurface)
                .border(1.dp, CyberCardBorder, RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            // Speed and obstacle HUD
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Speed,
                        contentDescription = null,
                        tint = NeonEmerald,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "VELOCITY: $speedKmh KM/H",
                        color = NeonEmerald,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 12.sp
                    )
                }

                Text(
                    text = "STATUS: $obstacle",
                    color = ElectricAmber,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp
                )
            }

            // Scrolling Rooftop Grid Canvas
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 28.dp)
            ) {
                val w = size.width
                val h = size.height

                // Moving grid lines
                val spacing = 80.dp.toPx()
                val shift = (runOffset * spacing)
                var x = -spacing + shift
                while (x < w) {
                    drawLine(
                        color = Color(0x1800F0FF),
                        start = Offset(x, h * 0.4f),
                        end = Offset(x - 60.dp.toPx(), h * 0.9f),
                        strokeWidth = 1.5.dp.toPx()
                    )
                    x += spacing
                }

                // Runner track floor
                drawLine(
                    color = NeonEmerald,
                    start = Offset(0f, h * 0.85f),
                    end = Offset(w, h * 0.85f),
                    strokeWidth = 3.dp.toPx()
                )

                // Running Ninja Sprite representation
                val ninjaX = w * 0.25f
                val ninjaY = h * 0.76f

                // Neon trail behind runner
                drawLine(
                    color = NeonEmerald.copy(alpha = 0.4f),
                    start = Offset(ninjaX - 60.dp.toPx(), ninjaY),
                    end = Offset(ninjaX, ninjaY),
                    strokeWidth = 4.dp.toPx()
                )

                drawCircle(
                    color = NeonEmerald,
                    radius = 16.dp.toPx(),
                    center = Offset(ninjaX, ninjaY)
                )

                // Laser obstacle approaching
                val obsX = w * (0.85f - (runOffset * 0.3f))
                drawLine(
                    color = NeonCrimson,
                    start = Offset(obsX, h * 0.55f),
                    end = Offset(obsX, h * 0.85f),
                    strokeWidth = 4.dp.toPx()
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Shared Typing Engine Card
        TypingDisplayCard(
            engineState = typingState,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
