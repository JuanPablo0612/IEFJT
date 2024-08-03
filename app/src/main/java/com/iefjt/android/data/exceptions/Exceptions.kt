package com.iefjt.android.data.exceptions

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

fun Exception.toAppException() = when (this) {
    is AppException -> this
    is FirebaseAuthUserCollisionException -> UserAlreadyExistsException()
    is FirebaseAuthInvalidCredentialsException -> InvalidCredentialsException()
    else -> UnknownException()
}

inline fun <T> handleExceptions(action: () -> T): Result<T> {
    return try {
        Result.success(action())
    } catch (e: Exception) {
        Result.failure(e.toAppException())
    }
}

open class AppException(message: String) : Exception(message)

class UserAlreadyExistsException : AppException("User already exists")
class InvalidCredentialsException : AppException("Invalid credentials")
class UserIsNotSignedInException : AppException("User is not signed in")
class DniIsNotRegisteredException : AppException("Dni is not registered")
class UnknownException : AppException("An unknown error occurred")
