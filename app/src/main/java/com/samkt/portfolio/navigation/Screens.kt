package com.samkt.portfolio.navigation

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.samkt.about.AboutScreen
import com.samkt.contact.ContactScreen
import com.samkt.home.HomeScreen
import com.samkt.portfolio.R
import com.samkt.projects.ProjectsScreen


private sealed interface TopLevelRoute {
    val icon: Int
    val label: Int
}

private data object Home : TopLevelRoute {
    override val icon = R.drawable.ic_outline_home
    override val label: Int
        get() = R.string.home
}

private data object Projects : TopLevelRoute {
    override val icon = R.drawable.ic_outline_work
    override val label: Int
        get() = R.string.projects
}

private data object AboutMe : TopLevelRoute {
    override val icon = R.drawable.ic_outline_person
    override val label: Int
        get() = R.string.about_me
}

private data object Contact : TopLevelRoute {
    override val icon = R.drawable.ic_outline_email
    override val label: Int
        get() = R.string.contact_me
}

private fun topLevelRoutes() = listOf(Home, Projects, AboutMe, Contact)


@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun MainAppNavGraph(modifier: Modifier = Modifier) {
    val topLevelBackStack = remember { TopLevelBackStack<Any>(Home) }
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                topLevelRoutes().forEach { topLevelRoute ->
                    val isSelected = topLevelRoute == topLevelBackStack.topLevelKey
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            topLevelBackStack.addTopLevel(topLevelRoute)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(topLevelRoute.icon),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                stringResource(topLevelRoute.label)
                            )
                        }
                    )
                }
            }
        }
    ) {
        NavDisplay(
            modifier = Modifier,
            backStack = topLevelBackStack.backStack,
            onBack = { topLevelBackStack.removeLast() },
            entryProvider = entryProvider {
                entry<Home> {
                    HomeScreen(
                        onSettingsClicked = {

                        },
                        onProjectsClick = {
                            topLevelBackStack.addTopLevel(Projects)
                        },
                        onAboutMeClick = {
                            topLevelBackStack.addTopLevel(AboutMe)
                        },
                        onContactClick = {
                            topLevelBackStack.addTopLevel(Contact)
                        }
                    )
                }
                entry<Projects> {
                    ProjectsScreen(
                        onBackClick = {
                            topLevelBackStack.removeLast()
                        }
                    )
                }
                entry<AboutMe> {
                    AboutScreen(
                        onBackClick = {
                            topLevelBackStack.removeLast()
                        }
                    )
                }
                entry<Contact> {
                    ContactScreen(
                        onBackClicked = {
                            topLevelBackStack.removeLast()
                        }
                    )
                }
            },
        )
    }
}

