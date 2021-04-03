package co.icanteach.apps.android.projectz.auth.source

import co.icanteach.apps.android.projectz.auth.AuthRequest
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun auth(request: AuthRequest): Boolean {
        return authDataSource.auth(request)
    }
}