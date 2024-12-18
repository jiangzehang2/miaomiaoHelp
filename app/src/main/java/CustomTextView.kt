package com.myapplication.myapplication

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

class CustomTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {
    init {
        // 设置自定义字体
        typeface = ResourcesCompat.getFont(context, R.font.miao)
    }
}