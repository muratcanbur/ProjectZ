package co.icanteach.apps.android.projectz.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import co.icanteach.apps.android.projectz.TestCoroutineRule
import com.google.common.truth.Truth
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthViewModelTest {

    @MockK
    lateinit var usecase: AuthUseCase

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val rule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    // Subject under test
    private lateinit var viewModel: AuthViewModel

    @Before
    fun setUp() {

        MockKAnnotations.init(this)

        viewModel = AuthViewModel(
            authUseCase = usecase,
            dispatcher = testCoroutineRule.testDispatcher
        )

    }

    @Test
    fun `given invalid email as user input when auth is called, then observe livedata parameter as InvalidEmailException`() {

        // Given
        val givenEmail = "hello@icanteach"
        val givenPassword = "projectz9"
        val mockObserver = spyk<Observer<AuthPageErrorViewState>>()
        viewModel.pageErrorState.observeForever(mockObserver)
        coEvery { usecase.auth(givenEmail, givenPassword) } throws InvalidEmailException()

        testCoroutineRule.testDispatcher.runBlockingTest {

            // When
            viewModel.auth(
                email = givenEmail,
                password = givenPassword
            )

            // Then
            val slot = slot<AuthPageErrorViewState>()
            coVerify { mockObserver.onChanged(capture(slot)) }

            Truth.assertThat(slot.captured.exception).isInstanceOf(InvalidEmailException::class.java)

            coVerify(exactly = 1) { usecase.auth(givenEmail, givenPassword) }

            viewModel.pageErrorState.removeObserver(mockObserver)
        }
    }

    @Test
    fun `given invalid password as user input when auth is called, then observe livedata parameter as InvalidPasswordException`() {

        // Given
        val givenEmail = "hello@icanteach.co"
        val givenPassword = "projectz"

        val mockObserver = spyk<Observer<AuthPageErrorViewState>>()
        viewModel.pageErrorState.observeForever(mockObserver)

        coEvery { usecase.auth(givenEmail, givenPassword) } throws InvalidPasswordException()

        testCoroutineRule.testDispatcher.runBlockingTest {

            // When
            viewModel.auth(email = givenEmail, password = givenPassword)

            // Then
            val slot = slot<AuthPageErrorViewState>()
            verify { mockObserver.onChanged(capture(slot)) }

            Truth.assertThat(slot.captured.exception).isInstanceOf(InvalidPasswordException::class.java)

            coVerify(exactly = 1) { usecase.auth(givenEmail, givenPassword) }

            viewModel.pageErrorState.removeObserver(mockObserver)
        }
    }

    @Test
    fun `given valid email and password as user input and  when auth is called then _authSuccessState is called`() {

        // Given
        val givenEmail = "hello@icanteach.co"
        val givenPassword = "projectz9"

        val mockObserver = spyk<Observer<Boolean>>()
        viewModel.authSuccessState.observeForever(mockObserver)

        coEvery { usecase.auth(givenEmail, givenPassword) } returns true

        // When
        testCoroutineRule.testDispatcher.runBlockingTest {

            viewModel.auth(email = givenEmail, password = givenPassword)

            // Then
            val slot = slot<Boolean>()
            verify { mockObserver.onChanged(capture(slot)) }

            Truth.assertThat(slot.captured).isTrue()

            coVerify(exactly = 1) { usecase.auth(givenEmail, givenPassword) }

            viewModel.authSuccessState.removeObserver(mockObserver)
        }
    }
}