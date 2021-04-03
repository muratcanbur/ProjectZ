package co.icanteach.apps.android.projectz.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _pageErrorState = MutableLiveData<AuthPageErrorViewState>()

    // The external immutable LiveData for the request status
    val pageErrorState: LiveData<AuthPageErrorViewState>
        get() = _pageErrorState

    fun auth(username: String, password: String) {
        viewModelScope.launch {
            try {
                authUseCase.auth(username, password)
            } catch (ex: Exception) {
                _pageErrorState.value = AuthPageErrorViewState(ex)
            }
        }
    }
}