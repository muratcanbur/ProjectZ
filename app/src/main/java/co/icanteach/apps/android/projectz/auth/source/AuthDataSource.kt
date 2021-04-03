package co.icanteach.apps.android.projectz.auth.source

import co.icanteach.apps.android.projectz.auth.AuthRequest

interface AuthDataSource {

    suspend fun auth(request: AuthRequest): Boolean
}