package com.degonzague.hydration

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.degonzague.hydration.ui.theme.ElectricTurquoise
import com.degonzague.hydration.ui.theme.MutedText
import com.degonzague.hydration.ui.theme.OnSurfaceWhite
import com.degonzague.hydration.ui.theme.ProgressTrackColor
import com.degonzague.hydration.ui.theme.SoftTurquoise
import com.degonzague.hydration.ui.theme.SurfaceCard
import com.degonzague.hydration.ui.theme.SurfaceCardPressed
import com.degonzague.hydration.ui.theme.TurquoiseDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HydrationApp(viewModel: HydrationViewModel) {
    val state by viewModel.state.collectAsState()

    // Smooth progress animation
    val animatedProgress by animateFloatAsState(
        targetValue = state.progressFraction,
        animationSpec = tween(durationMillis = 600),
        label = "WaterProgress"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "SUIVI D'HYDRATATION",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp,
                            color = ElectricTurquoise
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Main Circular Gauge container
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(280.dp)
                    .aspectRatio(1f)
            ) {
                // Perfect Outer Glow / Tracker Ring
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val strokeWidth = 18.dp.toPx()
                    val diameter = size.minDimension - strokeWidth
                    val topLeftOffset = (size.minDimension - diameter) / 2

                    // Track Background (Dark Slate-Teal)
                    drawCircle(
                        color = ProgressTrackColor,
                        radius = diameter / 2,
                        style = Stroke(width = strokeWidth)
                    )

                    // Progress Arc (Electric Turquoise)
                    drawArc(
                        color = ElectricTurquoise,
                        startAngle = -90f,
                        sweepAngle = animatedProgress * 360f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                    )
                }

                // Inner Liters text zone perfectly surrounded
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .size(220.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    ElectricTurquoise.copy(alpha = 0.08f),
                                    Color.Transparent
                                )
                            )
                        )
                ) {
                    // Liters Display
                    val liters = state.currentAmountMl / 1000f
                    Text(
                        text = String.format("%.2f L", liters),
                        fontSize = 44.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = OnSurfaceWhite,
                        fontFamily = FontFamily.SansSerif
                    )
                    
                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "sur ${state.targetAmountMl / 1000}L",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = MutedText
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    val percent = (state.progressFraction * 100).toInt()
                    Surface(
                        color = ElectricTurquoise.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "$percent%",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            color = ElectricTurquoise,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            // Quick Status messages
            Text(
                text = when {
                    state.isGoalAchieved -> "Objectif Atteint ! Excellent travail ! 🎉"
                    state.progressFraction > 0.7f -> "Presque là, continuez comme ça ! 💪"
                    state.progressFraction > 0.4f -> "Bonne progression ! 💧"
                    state.currentAmountMl > 0 -> "C'est un bon début !"
                    else -> "Commencez à boire de l'eau !"
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (state.isGoalAchieved) ElectricTurquoise else SoftTurquoise,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            // Button Control Panel
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Large Turquoise Action Button (+250ml)
                Button(
                    onClick = { viewModel.addWater(250) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ElectricTurquoise,
                        contentColor = Color(0xFF0C1017)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Ajouter de l'eau",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Ajouter 250 ml",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Row of alternative actions (+500ml, Undo, Reset)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // +500ml Option
                    FilledTonalButton(
                        onClick = { viewModel.addWater(500) },
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = SurfaceCard,
                            contentColor = OnSurfaceWhite
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                    ) {
                        Text("+ 500 ml", fontWeight = FontWeight.Bold)
                    }

                    // Undo Option
                    IconButton(
                        onClick = { viewModel.undoLast() },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = SurfaceCard,
                            contentColor = MutedText
                        ),
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Undo,
                            contentDescription = "Annuler",
                            tint = SoftTurquoise
                        )
                    }

                    // Reset Option
                    Button(
                        onClick = { viewModel.reset() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2C1E21),
                            contentColor = Color(0xFFFF8A80)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Réinitialiser",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Reset", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Copyright Area (Tiny Text)
            Text(
                text = "Copyright © 2024 DE GONZAGUE. All rights reserved.",
                fontSize = 8.sp,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Monospace,
                color = MutedText.copy(alpha = 0.5f),
                modifier = Modifier.padding(bottom = 8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}
