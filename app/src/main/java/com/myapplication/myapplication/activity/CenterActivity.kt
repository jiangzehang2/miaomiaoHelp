package com.myapplication.myapplication.com.myapplication.myapplication.activity
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.myapplication.myapplication.MainActivity
import com.myapplication.myapplication.R
import com.myapplication.myapplication.ui.theme.MyApplicationTheme
import com.myapplication.myapplication.test.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.myapplication.myapplication.com.myapplication.myapplication.getPhoneNumber
import com.myapplication.myapplication.com.myapplication.myapplication.savePhoneNumber
import com.myapplication.myapplication.performLogin

import com.myapplication.myapplication.test.ApiService
import com.myapplication.myapplication.test.LoginResponse

import com.myapplication.myapplication.test.UpdateResponse
import com.myapplication.myapplication.test.UserDetailsResponse
import kotlinx.coroutines.launch


class CenterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

                        setContent {
                            MyApplicationTheme {
                                CenterScreen()

                            }
                        }
                    }
                }



@SuppressLint("WrongViewCast")
@Composable
fun CenterScreen() {
    val context = LocalContext.current
    // 从 Flow 获取手机号并保持状态
    val phoneNumberFlow = getPhoneNumber(context).collectAsState(initial = "手机号未找到")
    // State 来保存从后端返回的用户数据

    // 发起网络请求获取用户信息
    LaunchedEffect(phoneNumberFlow.value) {
        // 获取手机号并发起请求
        val phoneNumber = phoneNumberFlow.value

            // 调用接口
            performDetails(context, phoneNumber)

    }


    // 使用 AndroidView 来显示原生的 EditText
    AndroidView(
        factory = { ctx ->
            // 加载 center.xml 布局
            android.view.LayoutInflater.from(ctx).inflate(R.layout.center, null).apply {

                //登出按钮的实现
                findViewById<ImageButton>(R.id.btnOut).setOnClickListener {
                    // 跳转到登录界面
                    context.startActivity(Intent(context, MainActivity::class.java))
                }

                // 获取 EditText
                val phoneEditText = findViewById<EditText>(R.id.editTextNumber)
                val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
                val nameEditText = findViewById<EditText>(R.id.editTextName)
                val phoneNumber = phoneNumberFlow.value
                performDetails(this, phoneNumber)


                // 设置初始的手机号
                phoneEditText.setText(phoneNumberFlow.value)

                // 禁止编辑（只读）
                phoneEditText.isFocusable = false
                phoneEditText.isFocusableInTouchMode = false
                phoneEditText.isClickable = false





                // 设置 EditText 的内容为接口返回的用户数据
                findViewById<ImageButton>(R.id.btnSave).setOnClickListener {
                    val phoneNumber = findViewById<EditText>(R.id.editTextNumber).text.toString()
                    val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
                    val name = findViewById<EditText>(R.id.editTextName).text.toString()
                    if (phoneNumber.isNotEmpty() && password.isNotEmpty()&& name.isNotEmpty()) {
                        // 调用上传信息接口
                        performEdit(context, name,phoneNumber, password)
                    } else {
                        Toast.makeText(ctx, "请输入信息", Toast.LENGTH_SHORT).show()
                    }
                    context.startActivity(Intent(context, MenuActivity::class.java))

                }
            }


        },
        modifier = Modifier.fillMaxSize()
    )




    // 监听 phoneNumberFlow 的变化并更新 EditText
    LaunchedEffect(phoneNumberFlow.value) {
        // 每当 phoneNumberFlow 的值变化时，更新 EditText
        // 需要使用 'activity' 获取当前 Context
        val phoneEditText = (context as Activity).findViewById<EditText>(R.id.editTextNumber)
        phoneEditText.setText(phoneNumberFlow.value)
    }
}




fun performDetails(context: Context, phoneNumber: String) {
    // 获取 ApiService 实例
    val apiService = RetrofitClient.getRetrofitInstance().create(ApiService::class.java)

    // 调用接口
    val call = apiService.getUserDetails(phoneNumber)

    call.enqueue(object : Callback<UserDetailsResponse> {
        override fun onResponse(
            call: Call<UserDetailsResponse>,
            response: Response<UserDetailsResponse>
        ) {
            if (response.isSuccessful) {
                val userDetails = response.body()
                if (userDetails != null && userDetails.success) {
                    // 确保在主线程更新 UI

                    passwordEditText.setText(userDetails.password)
                    nameEditText.setText(userDetails.name)
                } else {
                    Toast.makeText(
                        context,
                        "获取用户信息失败",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<UserDetailsResponse>, t: Throwable) {
            Toast.makeText(context, "网络请求失败：${t.message}", Toast.LENGTH_SHORT).show()
            Log.d("NetworkError", "${t.message}")
        }
    })
}

fun performEdit(context: Context, name:String,phoneNumber: String, password: String) {
    // 获取 ApiService 实例
    val apiService = RetrofitClient.getRetrofitInstance().create(ApiService::class.java)

    // 调用上传信息接口

    val call = apiService.update(name,phoneNumber, password)

    call.enqueue(object : Callback<UpdateResponse> {
        override fun onResponse(call: Call<UpdateResponse>, response: Response<UpdateResponse>) {
            if (response.isSuccessful) {
                val updateResponse = response.body()
                if (updateResponse != null && updateResponse.success) {
                    Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show()

                }
                else {
                    Toast.makeText(context, "保存成功：${updateResponse?.message ?: ""}", Toast.LENGTH_SHORT).show()

                }
            }
            else {
                Toast.makeText(context, "保存失败：${response.message()}", Toast.LENGTH_SHORT).show()
            }
        }




        override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
            Toast.makeText(context, "网络请求失败：${t.message}", Toast.LENGTH_SHORT).show()
            Log.d("123qwe","${t.message}")
        }
    })
}




