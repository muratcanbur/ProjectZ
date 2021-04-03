package co.icanteach.apps.android.projectz.auth

import co.icanteach.apps.android.projectz.auth.source.AuthRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test


class AuthUseCaseTest2 {


    private lateinit var authUseCase: AuthUseCase
    val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {


        val repository =
            AuthRepository(
                authDataSource = FakeAuthDataSource(),
                dispatcher = testDispatcher
            )

        authUseCase = AuthUseCase(
            authRepository = repository,
            validator = AuthValidator()
        )
    }

    @Test
    fun `given invalid email as input, when auth is called, then should throw InvalidEmailException`() {

        // Given
        val givenEmail = "hello@icanteach"
        val givenPassword = "projectz9"
        val exceptionHandler = TestCoroutineExceptionHandler()

        // When
        testDispatcher.runBlockingTest {
            launch(exceptionHandler) {
                authUseCase.auth(givenEmail, givenPassword)
            }
        }

        // Then
        Truth.assertThat(exceptionHandler.uncaughtExceptions.first())
            .isInstanceOf(InvalidEmailException::class.java)
    }

    @Test
    fun `given invalid password as input, when auth is called, then should throw InvalidEmailException`() {

        // Given
        val givenEmail = "hello@icanteach.co"
        val givenPassword = "projectz"
        val exceptionHandler = TestCoroutineExceptionHandler()

        // When
        testDispatcher.runBlockingTest {
            launch(exceptionHandler) {
                authUseCase.auth(givenEmail, givenPassword)
            }
        }

        // Then
        Truth.assertThat(exceptionHandler.uncaughtExceptions.first())
            .isInstanceOf(InvalidPasswordException::class.java)
    }

    @Test
    fun `given valid password & email as input, when auth is called, then should throw no Exception`() {

        // Given
        val givenEmail = "hello@icanteach.co"
        val givenPassword = "projectz9"
        val exceptionHandler = TestCoroutineExceptionHandler()

        // When
        testDispatcher.runBlockingTest {
            launch(exceptionHandler) {
                authUseCase.auth(givenEmail, givenPassword)
            }
        }

        // Then
        Truth.assertThat(exceptionHandler.uncaughtExceptions.size).isEqualTo(0)
    }

    @After
    fun tearDown() {
        // make sure nothing else is scheduled to be executed
        testDispatcher.cleanupTestCoroutines()
    }
}