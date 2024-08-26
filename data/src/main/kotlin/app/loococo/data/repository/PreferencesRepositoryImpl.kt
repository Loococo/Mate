package app.loococo.data.repository

import app.loococo.data.local.preferences.SharedPreferencesManager
import app.loococo.domain.model.Token
import app.loococo.domain.model.User
import app.loococo.domain.repository.PreferencesRepository
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val preferencesManager: SharedPreferencesManager
) : PreferencesRepository {

    override fun saveUser(user: User) {
        preferencesManager.saveObject("user", user)
    }

    override fun getUser(): User? {
        return preferencesManager.getObject("user", User::class.java)
    }

    override fun getId(): String {
        return getUser()?.id ?: ""
    }

    override fun saveToken(token: Token) {
        preferencesManager.saveObject("token", token)
    }

    override fun getToken(): Token? {
        return preferencesManager.getObject("token", Token::class.java)
    }

    override fun removeUser() {
        preferencesManager.removeObject("user")
        preferencesManager.removeObject("token")
    }
}