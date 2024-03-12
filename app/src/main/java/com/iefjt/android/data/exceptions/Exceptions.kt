package com.iefjt.android.data.exceptions

import com.google.firebase.FirebaseNetworkException
import com.iefjt.android.R

fun Exception.getMessageId() = when(this) {
    is FirebaseNetworkException -> R.string.network_error
    else -> R.string.unknown_error
}