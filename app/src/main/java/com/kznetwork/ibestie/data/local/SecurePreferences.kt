package com.kznetwork.ibestie.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

/**
 * Wrapper for EncryptedSharedPreferences to securely store sensitive data
 * TODO: Initialize with Application context
 */
class SecurePreferences {

    private var sharedPreferences: SharedPreferences? = null

    fun initialize(context: Context) {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        sharedPreferences = EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveString(key: String, value: String) {
        sharedPreferences?.edit()?.putString(key, value)?.apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences?.getString(key, null)
    }

    fun saveInt(key: String, value: Int) {
        sharedPreferences?.edit()?.putInt(key, value)?.apply()
    }

    fun getInt(key: String): Int? {
        return if (sharedPreferences?.contains(key) == true) {
            sharedPreferences?.getInt(key, 0)
        } else null
    }

    fun saveLong(key: String, value: Long) {
        sharedPreferences?.edit()?.putLong(key, value)?.apply()
    }

    fun getLong(key: String): Long? {
        return if (sharedPreferences?.contains(key) == true) {
            sharedPreferences?.getLong(key, 0L)
        } else null
    }

    fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences?.edit()?.putBoolean(key, value)?.apply()
    }

    fun getBoolean(key: String): Boolean? {
        return if (sharedPreferences?.contains(key) == true) {
            sharedPreferences?.getBoolean(key, false)
        } else null
    }

    fun saveStringSet(key: String, value: Set<String>) {
        sharedPreferences?.edit()?.putStringSet(key, value)?.apply()
    }

    fun getStringSet(key: String): Set<String>? {
        return sharedPreferences?.getStringSet(key, null)
    }

    fun remove(key: String) {
        sharedPreferences?.edit()?.remove(key)?.apply()
    }

    fun clear() {
        sharedPreferences?.edit()?.clear()?.apply()
    }

    companion object {
        private const val PREFS_NAME = "secure_prefs"
    }
}

