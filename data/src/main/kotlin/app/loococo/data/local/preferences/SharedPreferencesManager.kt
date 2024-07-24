package app.loococo.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class SharedPreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    private val gson by lazy {
        Gson().newBuilder().create()
    }

    fun <T> saveObject(key: String, value: T) {
        sharedPreferences.edit().putString(key, gson.toJson(value)).apply()
    }

    fun <T> getObject(key: String, classOfT: Class<T>): T? {
        val json = sharedPreferences.getString(key, null)
        return if (json != null) {
            gson.fromJson(json, classOfT)
        } else {
            null
        }
    }
}