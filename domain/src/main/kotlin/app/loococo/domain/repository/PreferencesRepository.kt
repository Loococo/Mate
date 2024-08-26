package app.loococo.domain.repository

import app.loococo.domain.model.Token
import app.loococo.domain.model.User

interface PreferencesRepository {

    fun saveUser(user: User)

    fun getUser(): User?

    fun getId(): String

    fun saveToken(token: Token)

    fun getToken(): Token?

    fun removeUser()
}