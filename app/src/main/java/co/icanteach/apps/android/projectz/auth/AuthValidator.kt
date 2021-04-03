package co.icanteach.apps.android.projectz.auth

import androidx.core.util.PatternsCompat
import java.util.regex.Pattern
import javax.inject.Inject

class AuthValidator @Inject constructor() {

    fun isValidEmail(email: CharSequence): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        // Password should contain at least one number
        val exp = ".*[0-9].*"
        val pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }
}