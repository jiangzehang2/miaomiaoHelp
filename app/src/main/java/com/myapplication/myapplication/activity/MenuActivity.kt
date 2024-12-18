package com.myapplication.myapplication.com.myapplication.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.myapplication.myapplication.R
import com.myapplication.myapplication.ui.theme.MyApplicationTheme

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MenuScreen()
            }
        }
    }
}
@Composable
fun  MenuScreen() {
    // 获取 Context
    val context = LocalContext.current

    // 嵌入 XML 布局到 Compose 中
    AndroidView(
        factory = { ctx ->
            // 加载 menu.xml 布局
            android.view.LayoutInflater.from(ctx).inflate(R.layout.menu, null).apply {

                // 获取按钮并设置点击事件
                findViewById<ImageButton>(R.id.btnJump1).setOnClickListener {
                    // 跳转到界面
                    context.startActivity(Intent(context, CenterActivity::class.java))
                }
                // 获取按钮并设置点击事件
                findViewById<ImageButton>(R.id.btnJump2).setOnClickListener {
                    // 跳转到界面
                    context.startActivity(Intent(context, MessageActivity::class.java))
                }

            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
