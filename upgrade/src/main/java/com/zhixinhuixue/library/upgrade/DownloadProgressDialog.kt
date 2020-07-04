package com.zhixinhuixue.library.upgrade

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @Date 2020/7/2
 **/
class DownloadProgressDialog : DialogFragment(), DialogInterface.OnKeyListener {

    private lateinit var activity: Activity
    private lateinit var progressBar: ProgressBar
    private lateinit var tvProgress: AppCompatTextView
    private var bundle: Bundle? = null
    var dialogListener: DownloadProgressListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments
    }

    override fun onStart() {
        super.onStart()
        dialog ?: return
        dialog?.window ?: return
        val layoutParams = dialog?.window?.attributes
        layoutParams?.run {
            width = (screenWidth / 1.05).toInt()
            height = WindowManager.LayoutParams.WRAP_CONTENT
            gravity = Gravity.CENTER
        }
        dialog?.window?.attributes = layoutParams
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        dialog?.let {
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            it.setOnKeyListener(this)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val view = View.inflate(activity, R.layout.dialog_download_progress, null)
        progressBar = view.findViewById(R.id.progressBar)
        tvProgress = view.findViewById(R.id.tv_download_progress)
        (view.findViewById(R.id.tv_upgrade_context) as AppCompatTextView).text =
            arguments?.getString(UpgradeConst.UPGRADE_CONTENT_KEY)
        (view.findViewById(R.id.btn_download_cancel) as AppCompatTextView).setOnClickListener {
            dialogListener?.onCancelDownload()
        }
        builder.setView(view)
        return builder.create()
    }

    /**
     * 显示下载框
     */
    override fun show(manager: FragmentManager, tag: String?) {
        val fragmentTransaction = manager.beginTransaction()
        val fragment: Fragment? = manager.findFragmentByTag(tag)
        fragment?.let {
            fragmentTransaction.remove(it)
        }
        fragmentTransaction.add(this, tag)
        if (isStateSaved) fragmentTransaction.commitAllowingStateLoss() else fragmentTransaction.commit()
    }

    /**
     * 更新进度
     */
    fun updateProgress(progress: Int, totalProgress: Int) {
        if (totalProgress == -1)
            progressBar.isIndeterminate = true
        else
            if (totalProgress > 0) {
                progressBar.max = totalProgress
                progressBar.progress = progress
                tvProgress.text = TextUtils.concat(
                    "${getProgressSize(progress)}/ ",
                    getProgressSize(totalProgress)
                )
            } else {
                progressBar.max = 1
                progressBar.progress = 1
                tvProgress.text = "1B / 1B"
            }
    }

    /**
     * 获取屏幕宽度
     */
    private val screenWidth: Int by lazy {
        val metric = DisplayMetrics()
        val window = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        window.defaultDisplay.getMetrics(metric)
        metric.widthPixels
    }

    override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
        return keyCode == KeyEvent.KEYCODE_BACK
    }
}