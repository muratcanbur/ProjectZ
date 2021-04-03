package co.icanteach.apps.android.projectz.auth

import co.icanteach.apps.android.projectz.auth.source.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val validator: AuthValidator
) {

    suspend fun auth(email: String, password: String): Boolean {

        if (!validator.isValidEmail(email)) {
            throw InvalidEmailException()
        } else if (!validator.isValidPassword(password)) {
            throw  InvalidPasswordException()
        }

        return authRepository.auth(
            AuthRequest(
                email = email,
                password = password
            )
        )
    }
}
