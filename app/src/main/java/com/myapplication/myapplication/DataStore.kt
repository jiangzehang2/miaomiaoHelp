package com.myapplication.myapplication.com.myapplication.myapplication
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 创建 DataStore 实例
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

// 定义键
private val PHONE_KEY = stringPreferencesKey("phone")

// 保存手机号
suspend fun savePhoneNumber(context: Context, phoneNumber: String) {
    context.dataStore.edit { preferences ->
        preferences[PHONE_KEY] = phoneNumber
    }
    Log.d("123qwe","手机号已保存：$phoneNumber")//打印
}

// 读取手机号
fun getPhoneNumber(context: Context): Flow<String?> {
    return context.dataStore.data
        .map { preferences ->
            val phone = preferences[PHONE_KEY]
            Log.d("123qwe", "读取到手机号：$phone") // 打印读取到的手机号
            return@map phone  // 返回读取的手机号
        }
}

