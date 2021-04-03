package co.icanteach.apps.android.projectz.auth

import com.google.common.truth.Truth
import org.junit.Test


class AuthValidatorTest {

    @Test
    fun `given valid email as input, when isValidEmail is called, then isValidEmail should return true`() {

        // Given
        val givenEmail = "hello@icanteach.co"
        val authValidator = AuthValidator()

        // When
        val actualResult = authValidator.isValidEmail(givenEmail)

        // Then
        Truth.assertThat(actualResult).isTrue()
    }

    @Test
    fun `given invalid email as input, when isValidEmail is called, then isValidEmail should return false`() {

        // Given
        val givenEmail = "hello@icanteach"
        val authValidator = AuthValidator()

        // When
        val actualResult = authValidator.isValidEmail(givenEmail)

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `given valid password as input, when isValidPassword is called, then isValidPassword should return true`() {

        // Given
        val givenPassword = "projectz9"
        val authValidator = AuthValidator()

        // When
        val actualResult = authValidator.isValidPassword(givenPassword)

        // Then
        Truth.assertThat(actualResult).isTrue()
    }

    @Test
    fun `given invalid password as input, when isValidPassword is called, then isValidPassword should return false`() {

        // Given
        val givenPassword = "projectz"
        val authValidator = AuthValidator()

        // When
        val actualResult = authValidator.isValidPassword(givenPassword)

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

}