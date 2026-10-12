package com.example.ui.components

import android.content.res.Configuration
import android.view.InputDevice
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.engine.TypingEngineState
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

/**
 * Checks if a hardware QWERTY keyboard is detected.
 */
@Composable
fun isHardwareKeyboardConnected(): Boolean {
    val config = LocalConfiguration.current
    if (config.keyboard == Configuration.KEYBOARD_QWERTY) {
        return true
    }
    // Also check input devices for alphanumeric keyboard
    val deviceIds = InputDevice.getDeviceIds()
    for (id in deviceIds) {
        val device = InputDevice.getDevice(id)
        if (device != null && !device.isVirtual) {
            val sources = device.sources
            if ((sources and InputDevice.SOURCE_KEYBOARD) == InputDevice.SOURCE_KEYBOARD &&
                device.keyboardType == InputDevice.KEYBOARD_TYPE_ALPHABETIC
            ) {
                return true
            }
        }
    }
    return false
}

/**
 * Header status badge indicating hardware keyboard status and instructions.
 */
@Composable
fun KeyboardStatusBanner(
    modifier: Modifier = Modifier
) {
    val hasKeyboard = isHardwareKeyboardConnected()
    val bgColor = if (hasKeyboard) CyberSurfaceVariant else Color(0xFF2D1515)
    val borderColor = if (hasKeyboard) NeonCyan.copy(alpha = 0.5f) else ElectricAmber
    val icon = if (hasKeyboard) Icons.Default.Keyboard else Icons.Default.Warning
    val statusText = if (hasKeyboard) {
        "PHYSICAL KEYBOARD CONNECTED (HARDWARE TYPING ACTIVE)"
    } else {
        "PHYSICAL KEYBOARD RECOMMENDED (ATTACH USB/BT KEYBOARD FOR ARCADES)"
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Keyboard Status",
            tint = if (hasKeyboard) NeonCyan else ElectricAmber,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = statusText,
            color = if (hasKeyboard) NeonCyan else ElectricAmber,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}

/**
 * Real-time combat typing display box with neon character feedback.
 */
@Composable
fun TypingDisplayCard(
    engineState: TypingEngineState,
    modifier: Modifier = Modifier
) {
    val borderColor = when {
        engineState.hasError -> NeonCrimson
        engineState.isCompleted -> NeonEmerald
        engineState.comboCount >= 20 -> ElectricAmber
        else -> NeonCyan
    }

    val infiniteTransition = rememberInfiniteTransition(label = "cursor")
    val cursorAlpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(400),
            repeatMode = RepeatMode.Reverse
        ),
        label = "cursorBlink"
    )

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(CyberSurface)
            .border(1.5.dp, borderColor, RoundedCornerShape(12.dp))
            .padding(14.dp)
            .testTag("typing_display_card")
    ) {
        // Top metrics bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MetricChip(
                    icon = Icons.Default.Speed,
                    label = "WPM",
                    value = "${engineState.currentWpm}",
                    color = NeonCyan
                )
                MetricChip(
                    icon = Icons.Default.CheckCircle,
                    label = "ACCURACY",
                    value = "${engineState.accuracy.toInt()}%",
                    color = NeonEmerald
                )
                MetricChip(
                    icon = Icons.Default.Bolt,
                    label = "COMBO",
                    value = "${engineState.comboCount}x (${engineState.comboMultiplier}x)",
                    color = ElectricAmber
                )
            }

            Text(
                text = "SCORE: ${engineState.score}",
                color = ElectricAmber,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.Monospace,
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Progress line
        LinearProgressIndicator(
            progress = { engineState.progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp)),
            color = if (engineState.hasError) NeonCrimson else NeonCyan,
            trackColor = CyberSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Text display rendering matched, error, current target, and remaining
        val annotatedText = buildAnnotatedString {
            // Matched characters
            withStyle(
                style = SpanStyle(
                    color = NeonEmerald,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            ) {
                append(engineState.matchedText)
            }

            // Error character if mistake occurred
            if (engineState.hasError && engineState.lastErrorChar != null) {
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        background = NeonCrimson,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append(engineState.lastErrorChar.toString())
                }
            }

            // Current target character (highlighted with cursor)
            engineState.currentTargetChar?.let { targetChar ->
                withStyle(
                    style = SpanStyle(
                        color = CyberBackground,
                        background = NeonCyan.copy(alpha = cursorAlpha),
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace
                    )
                ) {
                    append(targetChar.toString())
                }
            }

            // Remaining pending text
            val remainingWithoutCurrent = if (engineState.remainingText.isNotEmpty()) {
                engineState.remainingText.substring(1)
            } else ""

            withStyle(
                style = SpanStyle(
                    color = TextMuted,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.Monospace
                )
            ) {
                append(remainingWithoutCurrent)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(CyberBackground)
                .padding(12.dp)
        ) {
            Text(
                text = annotatedText,
                fontSize = 18.sp,
                lineHeight = 26.sp,
                fontFamily = FontFamily.Monospace
            )
        }

        if (engineState.hasError) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "MISMATCH! Press key '${engineState.currentTargetChar}' or Backspace to resume.",
                color = NeonCrimson,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Composable
fun MetricChip(
    icon: ImageVector,
    label: String,
    value: String,
    color: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(CyberSurfaceVariant)
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(13.dp)
        )
        Text(
            text = "$label: $value",
            color = TextPrimary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
    }
}

/**
 * Modern styled Cyber Card container.
 */
@Composable
fun CyberCard(
    modifier: Modifier = Modifier,
    borderColor: Color = CyberCardBorder,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val clickModifier = if (onClick != null) {
        Modifier.clickable(onClick = onClick)
    } else Modifier

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(CyberSurface)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .then(clickModifier)
            .padding(14.dp)
    ) {
        content()
    }
}
