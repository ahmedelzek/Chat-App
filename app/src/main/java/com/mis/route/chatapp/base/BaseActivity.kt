package com.mis.route.chatapp.base

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mis.route.chatapp.R

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {
    lateinit var viewModel: VM
    lateinit var binding: DB
    private var dialog: AlertDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        observeToLiveData()
    }

    open fun observeToLiveData() {
        viewModel.isLoadingLiveData.observe(this) {
            if (it)
                showLoading()
            else
                hideLoading()
        }
        viewModel.viewMessageLiveData.observe(this) {
            showDialog(
                it.title,
                it.message,
                it.posButtonTitle,
                it.negButtonTitle,
                it.onPosButtonClick,
                it.onNegButtonClick
            )
        }
    }

    abstract fun initViewModel(): VM
    abstract fun getLayoutId(): Int

    private fun showLoading() {
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.dialog_loading)
        dialog = builder.create()
        dialog?.show()
    }

    private fun hideLoading() {
        dialog?.dismiss()
    }

    private fun showDialog(
        title: String? = null,
        message: String? = null,
        posButtonTitle: String? = null,
        negButtonTitle: String? = null,
        onPosButtonClick: (() -> Unit)? = null,
        onNegButtonClick: (() -> Unit)? = null
    ) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        posButtonTitle.let {
            builder.setPositiveButton(
                posButtonTitle
            ) { dialog, _ ->
                dialog.dismiss()
                onPosButtonClick?.invoke()
            }
        }
        negButtonTitle.let {
            builder.setPositiveButton(
                posButtonTitle
            ) { dialog, _ ->
                dialog.dismiss()
                onNegButtonClick?.invoke()
            }
        }
        builder.create().show()
    }
}