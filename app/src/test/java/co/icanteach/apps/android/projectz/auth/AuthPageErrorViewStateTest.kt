package co.icanteach.apps.android.projectz.auth

import android.content.Context
import co.icanteach.apps.android.projectz.R
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class AuthPageErrorViewStateTest {

    private val FAKE_EMAIL_ERROR_MESSAGE = "Invalid Email."
    private val FAKE_PASSWORD_ERROR_MESSAGE =
        "Password must contain at least 8 and at least one number character."

    val instrumentationContext = mockk<Context>(relaxed = true)

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        // Given a mocked Context injected into the object under test...
        every { instrumentationContext.getString(R.string.invalid_email_error_message) } returns FAKE_EMAIL_ERROR_MESSAGE
        every { instrumentationContext.getString(R.string.invalid_password_error_message) } returns FAKE_PASSWORD_ERROR_MESSAGE
    }

    @Test
    fun `given exception as InvalidEmailException when getEmailErrorMessage is called, then should return correct message`() {

        // Given
        val givenViewState = AuthPageErrorViewState(InvalidEmailException())
        // When
        val actualResult = givenViewState.getEmailErrorMessage(instrumentationContext)

        // Then
        Truth.assertThat(actualResult).isEqualTo(FAKE_EMAIL_ERROR_MESSAGE)
    }

    @Test
    fun `given exception as InvalidEmailException when getPasswordErrorMessage is called, then should return empty message`() {

        // Given
        val givenViewState = AuthPageErrorViewState(InvalidEmailException())

        // When
        val actualResult = givenViewState.getPasswordErrorMessage(instrumentationContext)

        // Then
        Truth.assertThat(actualResult).isEmpty()
    }

    @Test
    fun `given exception as InvalidPasswordException when getPasswordErrorMessage is called, then should return correct message`() {

        // Given
        val givenViewState = AuthPageErrorViewState(InvalidPasswordException())

        // When
        val actualResult = givenViewState.getPasswordErrorMessage(instrumentationContext)

        // Then
        Truth.assertThat(actualResult).isEqualTo(FAKE_PASSWORD_ERROR_MESSAGE)
    }

    @Test
    fun `given exception as InvalidPasswordException when getEmailErrorMessage is called, then should return empty message`() {

        // Given
        val givenViewState = AuthPageErrorViewState(InvalidPasswordException())

        // When
        val actualResult = givenViewState.getEmailErrorMessage(instrumentationContext)

        // Then
        Truth.assertThat(actualResult).isEmpty()
    }
}