package co.icanteach.apps.android.projectz.auth

import android.content.Context
import co.icanteach.apps.android.projectz.R
import java.lang.Exception

class AuthPageErrorViewState(private val exception: Exception) {

    fun getEmailErrorMessage(context: Context): String {
        return if (exception is InvalidEmailException) {
            context.getString(R.string.invalid_email_error_message)
        } else {
            ""
        }
    }

    fun getPasswordErrorMessage(context: Context): String {
        return if (exception is InvalidPasswordException) {
            context.getString(R.string.invalid_password_error_message)
        } else {
            ""
        }
    }
}