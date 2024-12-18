package com.myapplication.myapplication.com.myapplication.myapplication.activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
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
import com.myapplication.myapplication.test.ApiService
import com.myapplication.myapplication.test.LoginResponse
import com.myapplication.myapplication.test.RegisterResponse
import com.myapplication.myapplication.test.RetrofitClient
import com.myapplication.myapplication.ui.theme.MyApplicationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                RegisterScreen()
            }
        }
    }
}
@Composable
fun  RegisterScreen() {
    // 获取 Context
    val context = LocalContext.current

    // 嵌入 XML 布局到 Compose 中
    AndroidView(
        factory = { ctx ->
            // 加载 register.xml 布局
            android.view.LayoutInflater.from(ctx).inflate(R.layout.register, null).apply {
                // 获取登录界面注册按钮的 ID 并设置点击事件
                findViewById<ImageButton>(R.id.btnSubmit).setOnClickListener {
                    val phoneNumber = findViewById<EditText>(R.id.phoneInput1).text.toString()
                    val password = findViewById<EditText>(R.id.passwordInput1).text.toString()

                    if (phoneNumber.isNotEmpty() && password.isNotEmpty()) {
                        // 调用登录接口
                        performRegister(context, phoneNumber, password)
                    } else {
                        Toast.makeText(ctx, "请输入手机号和密码", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

fun performRegister(context: Context, phoneNumber: String, password: String) {
    // 获取 ApiService 实例
    val apiService = RetrofitClient.getRetrofitInstance().create(ApiService::class.java)

    // 调用登录接口
    val call = apiService.register(phoneNumber, password)

    call.enqueue(object : Callback<RegisterResponse> {
        override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
            if (response.isSuccessful) {
                val registerResponse = response.body()
                if (registerResponse != null && registerResponse.success) {
                    Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show()

                    // 跳转到登录界面
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
                else {
                    val errorMessage = registerResponse?.message ?: "未知错误"
                    Toast.makeText(context, "注册失败：$errorMessage", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                // 这里可以根据HTTP状态码做更多的处理
                Toast.makeText(context, "注册失败：${response.message()}", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
            Toast.makeText(context, "网络请求失败：${t.message}", Toast.LENGTH_SHORT).show()
            Log.d("123qwe","${t.message}")
        }
    })
}
