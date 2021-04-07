package co.icanteach.apps.android.projectz.auth

import androidx.core.util.PatternsCompat
import java.util.regex.Pattern
import javax.inject.Inject

class AuthValidator @Inject constructor() {

    fun isValidEmail(email: CharSequence): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        // Minimum eight characters, at least one letter and one number:
        val exp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"
        val pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }
}