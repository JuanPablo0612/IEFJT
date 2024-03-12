package com.iefjt.android.ui.inventory.statuses

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.iefjt.android.ui.common.ActivityContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatusSelectorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivityContent {
                StatusesScreen(
                    onSelect = { statusId ->
                        val intent = Intent().apply {
                            putExtra("statusId", statusId)
                        }

                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    },
                    onCancel = {
                        setResult(Activity.RESULT_CANCELED)
                        finish()
                    }
                )
            }
        }
    }
}