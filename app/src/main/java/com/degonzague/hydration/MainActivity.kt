package com.degonzague.hydration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.degonzague.hydration.ui.theme.HydrationTrackerTheme

class MainActivity : ComponentActivity() {
    
    // Instantiate ViewModel standard style
    private val viewModel: HydrationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Edge to edge support for status/navigation bar styling
        enableEdgeToEdge()
        
        setContent {
            HydrationTrackerTheme {
                HydrationApp(viewModel = viewModel)
            }
        }
    }
}
