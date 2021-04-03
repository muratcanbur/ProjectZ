package co.icanteach.apps.android.projectz.auth

import co.icanteach.apps.android.projectz.auth.source.AuthDataSource

class FakeAuthDataSource :
    AuthDataSource {
    override suspend fun auth(request: AuthRequest): Boolean {
        return true
    }
}