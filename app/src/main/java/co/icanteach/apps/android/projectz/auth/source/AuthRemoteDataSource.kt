package co.icanteach.apps.android.projectz.auth.source

import co.icanteach.apps.android.projectz.auth.AuthRequest
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor() :
    AuthDataSource {
    override suspend fun auth(request: AuthRequest): Boolean {
        return true
    }

}