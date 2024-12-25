package com.example.shoplistcompose.main_screen

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shoplistcompose.ui.theme.BlueLight
import com.example.shoplistcompose.ui.theme.GrayLight


@Composable
fun BottomNav(navController: NavHostController) {

    val listItems = listOf(
        BottomNavItem.ListItem,
        BottomNavItem.NoteItem,
        BottomNavItem.AboutItem,
        BottomNavItem.SettingsItem
    )

    NavigationBar(containerColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        listItems.forEach { bottomNavItem ->
            NavigationBarItem(
                selected = currentRoute == bottomNavItem.route,
                onClick = {
                    navController.navigate(bottomNavItem.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = bottomNavItem.iconId),
                        contentDescription = "icon"
                    )
                },
                label = {
                    Text(text = bottomNavItem.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BlueLight,
                    unselectedIconColor = GrayLight,
                    selectedTextColor = BlueLight,
                    unselectedTextColor = GrayLight,
                    indicatorColor = Color.Transparent
                ),
                alwaysShowLabel = false
            )
        }
    }
}