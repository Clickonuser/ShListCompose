package com.example.shoplistcompose.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shoplistcompose.R
import com.example.shoplistcompose.dialog.MainDialog
import com.example.shoplistcompose.navigation.NavigationGraph
import com.example.shoplistcompose.ui.theme.BlueLight
import com.example.shoplistcompose.utils.Routes
import com.example.shoplistcompose.utils.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainNavHostController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.NavigateMain -> {
                    mainNavHostController.navigate(uiEvent.route)
                }
                is UiEvent.Navigate -> {
                    navController.navigate(uiEvent.route)
                }
                else -> {}
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        bottomBar = {
            BottomNav(currentRoute) { route ->
                viewModel.onEvent(MainScreenEvent.Navigate(route))
            }
        },
        floatingActionButton = {
            if (viewModel.showFloatingButton.value) FloatingActionButton(
                onClick = {
                    viewModel.onEvent(MainScreenEvent.OnNewItemClick(currentRoute ?: Routes.SHOPPING_LIST))
                },
                shape = CircleShape,
                containerColor = BlueLight,
                elevation = FloatingActionButtonDefaults.elevation(3.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = "add",
                    tint = Color.White
                )
            }
        },
    ) {
        NavigationGraph(navController = navController) { route ->
            viewModel.onEvent(MainScreenEvent.NavigateMain(route))
        }
        MainDialog(dialogController = viewModel)
    }
}