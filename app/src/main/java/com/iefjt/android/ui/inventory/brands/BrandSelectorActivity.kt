package com.iefjt.android.ui.inventory.brands

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.iefjt.android.ui.common.ActivityContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrandSelectorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivityContent {
                BrandsScreen(
                    onSelect = { brandId ->
                        val intent = Intent().apply {
                            putExtra("brandId", brandId)
                        }

                        setResult(RESULT_OK, intent)
                        finish()
                    },
                    onCancel = {
                        setResult(RESULT_CANCELED)
                        finish()
                    }
                )
            }
        }
    }
}