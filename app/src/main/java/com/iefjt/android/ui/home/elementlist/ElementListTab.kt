package com.iefjt.android.ui.home.elementlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iefjt.android.R
import com.iefjt.android.ui.common.CircularLoading
import com.iefjt.android.ui.common.ScaffoldContent
import com.iefjt.android.ui.common.buttons.ScaffoldAddButton
import com.iefjt.android.ui.navigation.Screen

@Composable
fun ElementListTab(
    viewModel: ElementListViewModel = hiltViewModel(),
    allowEdit: Boolean,
    navController: NavController
) {
    val uiState = viewModel.uiState

    Scaffold(
        floatingActionButton = {
            if (allowEdit) {
                ScaffoldAddButton(onClick = { navController.navigate(Screen.AddElement.route) })
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        ScaffoldContent(padding = padding) {
            if (uiState.loading) {
                CircularLoading()
            } else {
                if (uiState.elements.isEmpty()) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = stringResource(id = R.string.empty_inventory),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                } else {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            items(
                                uiState.elements,
                                key = { it.id }) { element ->
                                ElementCard(
                                    element = element,
                                    onEdit = { navController.navigate(route = "${Screen.ElementDetails.route}/$it") },
                                    onDelete = viewModel::onDeleteElement
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}