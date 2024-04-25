package com.iefjt.android.ui.inventory.elements.common

import android.content.Context
import android.content.Intent
import com.iefjt.android.ui.inventory.brands.BrandSelectorActivity
import com.iefjt.android.ui.inventory.headquarters.HeadquartersSelectorActivity
import com.iefjt.android.ui.inventory.statuses.StatusSelectorActivity
import com.iefjt.android.ui.inventory.type.TypeSelectorActivity

fun brandSelectorIntent(context: Context) = Intent(context, BrandSelectorActivity::class.java)

fun typeSelectorIntent(context: Context) = Intent(context, TypeSelectorActivity::class.java)

fun headquartersSelectorIntent(context: Context) = Intent(context, HeadquartersSelectorActivity::class.java)

fun statusSelectorIntent(context: Context) = Intent(context, StatusSelectorActivity::class.java)