package com.iefjt.android.ui.inventory.headquarters

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.iefjt.android.ui.common.ActivityContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeadquartersSelectorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivityContent {
                HeadquartersScreen(
                    onSelect = { headquartersId ->
                        val intent = Intent().apply {
                            putExtra("headquartersId", headquartersId)
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