package com.zhixinhuixue.library.widget.custom

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.wanandroid.developer.library.widget.R

/**
 *  @description:自定义CustomToolbar
 *  @author xcl qq:244672784
 *  @Date 2020/7/8
 **/
class CustomToolbar : FrameLayout {
    lateinit var btnFinish: AppCompatImageButton
    lateinit var btnMenu: AppCompatImageButton
    lateinit var tvTitle: AppCompatTextView
    lateinit var tvMenu: AppCompatTextView
    lateinit var editSearch: AppCompatEditText

    private var toolbarClickListener: ToolbarClickListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    /**
     * 初始化
     */
    private fun init() {
        val toolbar = ConstraintLayout.inflate(context, R.layout.layout_custom_toolbar, null)
        btnFinish = toolbar.findViewById(R.id.btnFinish)
        btnMenu = toolbar.findViewById(R.id.btnMenu)
        tvTitle = toolbar.findViewById(R.id.tvTitle)
        tvMenu = toolbar.findViewById(R.id.tvMenu)
        editSearch = toolbar.findViewById(R.id.editSearch)
        btnFinish.setOnClickListener { toolbarClickListener?.onFinishClick() }
        btnMenu.setOnClickListener { toolbarClickListener?.onMenuClick() }
        tvTitle.setOnClickListener { toolbarClickListener?.onTitleClick() }
        tvMenu.setOnClickListener { toolbarClickListener?.onMenuClick() }
        addView(toolbar)
    }

    /**
     * 设置监听事件
     * @param clickListener ToolbarClickListener?
     */
    fun setToolbarClickListener(clickListener: ToolbarClickListener?) {
        this.toolbarClickListener = clickListener
    }

    /**
     * 设置 Toolbar左边Icon
     * @param icon Any?
     */
    fun setLeftIcon(icon: Any?) {
        if (icon == null) {
            btnFinish.visibility = View.GONE
            return
        }
        btnFinish.visibility = View.VISIBLE
        when (icon) {
            is Drawable -> btnFinish.setImageDrawable(icon)
            is Int -> btnFinish.setImageResource(icon)
            is Bitmap -> btnFinish.setImageBitmap(icon)
            is Uri -> btnFinish.setImageURI(icon)
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && icon is Icon) {
                    btnFinish.setImageIcon(icon)
                }
            }
        }
    }

    /**
     * 设置 Toolbar右边Icon
     * @param icon Any?
     */
    fun setMenuIcon(icon: Any?) {
        tvMenu.visibility = View.GONE
        if (icon == null) {
            btnMenu.visibility = View.GONE
            return
        }
        btnMenu.visibility = View.VISIBLE
        when (icon) {
            is Drawable -> btnMenu.setImageDrawable(icon)
            is Int -> btnMenu.setImageResource(icon)
            is Bitmap -> btnMenu.setImageBitmap(icon)
            is Uri -> btnMenu.setImageURI(icon)
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && icon is Icon) {
                    btnMenu.setImageIcon(icon)
                }
            }
        }
    }

    /**
     * 设置 Toolbar右边Text
     * @param text Any?
     */
    fun setTitleText(text: Any?) {
        if (text == null) {
            tvTitle.visibility = View.GONE
            return
        }
        editSearch.visibility = View.GONE
        tvTitle.visibility = View.VISIBLE
        when (text) {
            is String -> tvTitle.text = text
            is CharSequence -> tvTitle.text = text
            is Int -> tvTitle.text = resources.getText(text)
            else -> tvTitle.text = ""
        }
    }

    /**
     * 设置 Toolbar右边Text
     * @param text Any?
     */
    fun setMenuText(text: Any?) {
        btnMenu.visibility = View.GONE
        if (text == null) {
            tvMenu.visibility = View.GONE
            return
        }
        tvMenu.visibility = View.VISIBLE
        when (text) {
            is String -> tvMenu.text = text
            is CharSequence -> tvMenu.text = text
            is Int -> tvMenu.text = resources.getText(text)
            else -> tvMenu.text = ""
        }
    }

    /**
     * 设置 Toolbar中间输入框
     * @param block 接收EditText输入情况
     */
    fun setEditText(block: (value: CharSequence) -> Unit = {}) {
        editSearch.visibility = View.VISIBLE
        tvTitle.visibility = View.GONE
        editSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable) {
            }

            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                block.invoke(p0)
            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            }
        })
    }
}