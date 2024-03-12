package com.iefjt.android.ui.inventory.elements.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iefjt.android.R
import com.iefjt.android.ui.common.ScaffoldContent
import com.iefjt.android.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElementsScreen(viewModel: ElementsViewModel = hiltViewModel(), navController: NavController) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.elements_title)) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(route = Screen.AddElement.route) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        ScaffoldContent(padding = padding) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                items(uiState.elements, key = { it.id }) { element ->
                    ElementCard(
                        element = element,
                        onClick = {
                            navController.navigate(route = "${Screen.ElementDetails.route}/${element.id}")
                        }
                    )
                }
            }
        }
    }
}