package com.myapplication.myapplication

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import com.myapplication.myapplication.R

class CustomEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : EditText(context, attrs, defStyleAttr) {
    init {
        typeface = ResourcesCompat.getFont(context, R.font.miao)
    }
}