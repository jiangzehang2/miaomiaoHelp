package com.myapplication.myapplication
import android.content.Intent
import android.os.Bundle
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
import com.myapplication.myapplication.com.myapplication.myapplication.activity.MenuActivity
import com.myapplication.myapplication.com.myapplication.myapplication.activity.SecondActivity
import com.myapplication.myapplication.ui.theme.MyApplicationTheme
import com.myapplication.myapplication.test.RetrofitClient
import com.myapplication.myapplication.test.LoginResponse
import com.myapplication.myapplication.test.ApiService
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Context
import androidx.compose.runtime.rememberCoroutineScope
import com.myapplication.myapplication.com.myapplication.myapplication.savePhoneNumber
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {
    // 获取 Context
    val context = LocalContext.current
    val coroutineScope= rememberCoroutineScope()

    // 嵌入 XML 布局到 Compose 中
    AndroidView(
        factory = { ctx ->
            // 加载 login.xml 布局
            android.view.LayoutInflater.from(ctx).inflate(R.layout.login, null).apply {

                // 获取注册按钮并设置点击事件
                findViewById<ImageButton>(R.id.btnGoToRegister).setOnClickListener {
                    // 跳转到注册界面
                    context.startActivity(Intent(context, SecondActivity::class.java))
                }




                // 获取登录按钮并设置点击事件
                findViewById<ImageButton>(R.id.loimg).setOnClickListener {
                    //context.startActivity(Intent(context, MenuActivity::class.java))
                    val phoneNumber = findViewById<EditText>(R.id.phoneInput).text.toString()
                    val password = findViewById<EditText>(R.id.passwordInput).text.toString()

                    if (phoneNumber.isNotEmpty() && password.isNotEmpty()) {
                        coroutineScope.launch {
                            savePhoneNumber(context, phoneNumber)
                        }
                        // 调用登录接口
                        performLogin(context, phoneNumber, password)
                    } else {
                        Toast.makeText(ctx, "请输入手机号和密码", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

fun performLogin(context: Context, phoneNumber: String, password: String) {
    // 获取 ApiService 实例
    val apiService = RetrofitClient.getRetrofitInstance().create(ApiService::class.java)

    // 调用登录接口
    val call = apiService.login(phoneNumber, password)

    call.enqueue(object : Callback<LoginResponse> {
        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
            if (response.isSuccessful) {
                val loginResponse = response.body()
                if (loginResponse != null && loginResponse.success) {
                    Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show()
                    //跳转到菜单界面
                    context.startActivity(Intent(context, MenuActivity::class.java))
                }
                else {
                    Toast.makeText(context, "登录失败：${loginResponse?.message ?: "未知错误"}", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(context, "登录失败：${response.message()}", Toast.LENGTH_SHORT).show()
            }
        }




        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            Toast.makeText(context, "网络请求失败：${t.message}", Toast.LENGTH_SHORT).show()
            Log.d("123qwe","${t.message}")
        }
    })
}
