package co.icanteach.apps.android.projectz.auth

import co.icanteach.apps.android.projectz.auth.source.AuthRepository
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class AuthUseCaseTest {

    @MockK
    lateinit var repository: AuthRepository


    private lateinit var authUseCase: AuthUseCase
    private val testDispatcher = TestCoroutineDispatcher()


    @Before
    fun setUp() {

        MockKAnnotations.init(this)

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

        coVerify(exactly = 0) { repository.auth(request = any()) }
        confirmVerified(repository)
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

        coVerify(exactly = 0) { repository.auth(request = any()) }
        confirmVerified(repository)
    }

    @Test
    fun `given valid password & email as input, when auth is called, then should throw no Exception`() {

        // Given
        val givenEmail = "hello@icanteach.co"
        val givenPassword = "projectz9"
        val exceptionHandler = TestCoroutineExceptionHandler()
        coEvery { repository.auth(request = any()) } returns true

        // When
        testDispatcher.runBlockingTest {
            launch(exceptionHandler) {
                authUseCase.auth(givenEmail, givenPassword)
            }
        }

        // Then
        Truth.assertThat(exceptionHandler.uncaughtExceptions.size).isEqualTo(0)
        coVerify(exactly = 1) { repository.auth(request = any()) }
        confirmVerified(repository)
    }

    @After
    fun tearDown() {
        // make sure nothing else is scheduled to be executed
        testDispatcher.cleanupTestCoroutines()
    }
}