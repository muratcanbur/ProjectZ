package co.icanteach.apps.android.projectz.auth

import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class AuthEmailValidatorParameterizedTest constructor(
    private val givenEmail: String,
    private val expectedResult: Boolean
) {

    @Test
    fun `email validation test `() {
        // Given
        val validator = AuthValidator()

        // When
        val actualResult = validator.isValidEmail(givenEmail)

        // Then
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "given {0} as input, then should return {1}")
        fun provideParameters(): Collection<Array<Any>> {
            return listOf(
                arrayOf("hello@icanteach.co", true),
                arrayOf("projectz@icanteach.co", true),
                arrayOf("projectz@icanteach", false),
                arrayOf("m.projectz@icanteach.co", true),
                arrayOf("m.projectz@gmail", false)
            )
        }
    }
}

@RunWith(Parameterized::class)
class AuthPasswordValidatorParameterizedTest constructor(
    private val givenPassword: String,
    private val expectedResult: Boolean
) {

    @Test
    fun `password validation test `() {
        // Given
        val validator = AuthValidator()

        // When
        val actualResult = validator.isValidPassword(givenPassword)

        // Then
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "given {0} as input, then should return {1}")
        fun provideParameters(): Collection<Array<Any>> {
            return listOf(
                arrayOf("projectz9", true),
                arrayOf("projectz", false),
                arrayOf("Projectz9", true),
                arrayOf("Projectz", false),
                arrayOf("Projectz987", true),
                arrayOf("Project987", true)
            )
        }
    }
}