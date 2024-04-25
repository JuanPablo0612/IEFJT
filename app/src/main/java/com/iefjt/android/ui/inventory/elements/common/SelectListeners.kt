package com.iefjt.android.ui.inventory.elements.common

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun brandSelectorLauncher(onSelect: (brandId: String) -> Unit, onCancel: () -> Unit) =
    rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let {
                val brandId = it.getStringExtra("brandId")!!
                onSelect(brandId)
            }
        } else {
            onCancel()
        }
    }

@Composable
fun typeSelectorLauncher(onSelect: (typeId: String) -> Unit, onCancel: () -> Unit) =
    rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let {
                val typeId = it.getStringExtra("typeId")!!
                onSelect(typeId)
            }
        } else {
            onCancel()
        }
    }

@Composable
fun headquartersSelectorLauncher(onSelect: (headquartersId: String) -> Unit, onCancel: () -> Unit) =
    rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let {
                val headquartersId = it.getStringExtra("headquartersId")!!
                onSelect(headquartersId)
            }
        } else {
            onCancel()
        }
    }

@Composable
fun statusSelectorLauncher(onSelect: (statusId: String) -> Unit, onCancel: () ->Unit) =
    rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let {
                val statusId = it.getStringExtra("statusId")!!
                onSelect(statusId)
            }
        }else {
            onCancel()
        }
    }
