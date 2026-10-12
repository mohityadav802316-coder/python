package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.SportsMma
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.AppScreen
import com.example.ui.MainViewModel
import com.example.ui.components.KeyboardStatusBanner
import com.example.ui.theme.CyberBackground
import com.example.ui.theme.CyberCardBorder
import com.example.ui.theme.CyberPurple
import com.example.ui.theme.CyberSurface
import com.example.ui.theme.CyberSurfaceVariant
import com.example.ui.theme.ElectricAmber
import com.example.ui.theme.NeonCrimson
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonEmerald
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

private data class SectionCardData(
    val screen: AppScreen,
    val title: String,
    val subtitle: String,
    val badge: String,
    val icon: ImageVector,
    val accentColor: Color,
    val statLabel: String
)

@Composable
fun DashboardScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val profile by viewModel.profile.collectAsState()

    val sectionCards = listOf(
        SectionCardData(
            screen = AppScreen.NINJA_FIGHT,
            title = "Ninja Fight",
            subtitle = "Cyberpunk Neo-Tokyo rooftop arena with enemy slash combos",
            badge = "COMBAT ARENA",
            icon = Icons.Default.SportsMma,
            accentColor = NeonCyan,
            statLabel = "Best Wave: ${profile.ninjaFightHighWave}"
        ),
        SectionCardData(
            screen = AppScreen.NINJA_RUN,
            title = "Ninja Run",
            subtitle = "High-speed cadence rooftop runner leaping neon lasers",
            badge = "SPEED RUNNER",
            icon = Icons.AutoMirrored.Filled.DirectionsRun,
            accentColor = NeonEmerald,
            statLabel = "Best Run: ${profile.ninjaRunBestDistance}m"
        ),
        SectionCardData(
            screen = AppScreen.MONSTER_BATTLE,
            title = "Monster Battle",
            subtitle = "Raid the colossal Cyber Titan with shield-breaker attacks",
            badge = "TITAN RAID",
            icon = Icons.Default.Shield,
            accentColor = NeonCrimson,
            statLabel = "Titans Slain: ${profile.titanBossDefeated}"
        ),
        SectionCardData(
            screen = AppScreen.TYPING_ACADEMY,
            title = "Typing Academy",
            subtitle = "Home Row, Common Words & Python Symbol drills",
            badge = "TRAINING DRILLS",
            icon = Icons.Default.Keyboard,
            accentColor = ElectricAmber,
            statLabel = "Best Speed: ${profile.bestWpm} WPM"
        ),
        SectionCardData(
            screen = AppScreen.PYTHON_ACADEMY,
            title = "Python Academy",
            subtitle = "30-Level interactive roadmap from Variables to Metaclasses",
            badge = "30 LEVELS",
            icon = Icons.Default.School,
            accentColor = CyberPurple,
            statLabel = "Completed: ${profile.pythonLessonsCompleted}/30"
        ),
        SectionCardData(
            screen = AppScreen.REAL_PYTHON_LAB,
            title = "Real Python Lab",
            subtitle = "3-Panel landscape IDE with genuine live Python execution runtime",
            badge = "LIVE RUNTIME",
            icon = Icons.Default.Terminal,
            accentColor = NeonCyan,
            statLabel = "Native AST Engine"
        ),
        SectionCardData(
            screen = AppScreen.PYTHON_MINI_PROJECTS,
            title = "Python Mini Projects",
            subtitle = "Interactive blueprints: Calculator, RPG, Password Gen & CLI apps",
            badge = "8 BLUEPRINTS",
            icon = Icons.Default.Code,
            accentColor = NeonEmerald,
            statLabel = "Projects: 8 Ready"
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CyberBackground)
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .testTag("dashboard_screen")
    ) {
        // Top Bar: Brand, Hardware Keyboard Status, Profile Summary, Settings
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(CyberSurfaceVariant)
                        .border(1.dp, NeonCyan, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Bolt,
                        contentDescription = null,
                        tint = NeonCyan,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Column {
                    Text(
                        text = "TYPING & PYTHON MASTER",
                        color = TextPrimary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "NATIVE CYBERNETIC ARCADE ENGINE",
                        color = NeonCyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            // Keyboard status badge
            KeyboardStatusBanner()

            // Profile stats chip & Settings
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProfileBadge(
                    level = profile.level,
                    title = profile.title,
                    xp = profile.xp
                )

                IconButton(
                    onClick = { viewModel.navigateTo(AppScreen.SETTINGS) },
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(CyberSurfaceVariant)
                        .testTag("settings_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 7 Main Section Cards Grid in Landscape layout
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(sectionCards) { card ->
                DashboardCard(
                    data = card,
                    onLaunch = { viewModel.navigateTo(card.screen) }
                )
            }
        }
    }
}

@Composable
private fun ProfileBadge(
    level: Int,
    title: String,
    xp: Int
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(CyberSurface)
            .border(1.dp, CyberCardBorder, RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(ElectricAmber),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$level",
                color = CyberBackground,
                fontSize = 12.sp,
                fontWeight = FontWeight.Black
            )
        }

        Column {
            Text(
                text = title.uppercase(),
                color = TextPrimary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Text(
                text = "$xp XP",
                color = ElectricAmber,
                fontSize = 10.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Composable
private fun DashboardCard(
    data: SectionCardData,
    onLaunch: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(115.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(CyberSurface)
            .border(1.dp, data.accentColor.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .clickable { onLaunch() }
            .padding(10.dp)
            .testTag("card_${data.screen.name.lowercase()}")
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(data.accentColor.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = data.icon,
                            contentDescription = null,
                            tint = data.accentColor,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Text(
                        text = data.title,
                        color = TextPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(data.accentColor.copy(alpha = 0.2f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = data.badge,
                        color = data.accentColor,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            Text(
                text = data.subtitle,
                color = TextSecondary,
                fontSize = 10.sp,
                lineHeight = 13.sp,
                maxLines = 2
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data.statLabel,
                    color = data.accentColor,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "ENTER",
                        color = data.accentColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = data.accentColor,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}
