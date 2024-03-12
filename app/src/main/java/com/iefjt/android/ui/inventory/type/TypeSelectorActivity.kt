package com.iefjt.android.ui.inventory.type

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.iefjt.android.ui.common.ActivityContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeSelectorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivityContent {
                TypesScreen(
                    onSelect = { typeId ->
                        val intent = Intent().apply {
                            putExtra("typeId", typeId)
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