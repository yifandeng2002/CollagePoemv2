package com.collagepoem.app.modules.logindirectpage.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.dao.UserDao
import com.collagepoem.app.databinding.ActivityLogincheckpageBinding
import com.collagepoem.app.databinding.ActivityLogindirectpageBinding
import com.collagepoem.app.modules.logincheckpage.data.viewmodel.LoginCheckpageVM
import com.collagepoem.app.modules.logindirectpage.data.viewmodel.LoginDirectpageVM
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.jaeger.library.StatusBarUtil


class LoginDirectpageActivity : BaseActivity<ActivityLogindirectpageBinding>(R.layout.activity_logindirectpage) {
  private val viewModel: LoginDirectpageVM by viewModels<LoginDirectpageVM>()
  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 194


  //    将StatusBar设置为透明
  fun setStatusBarTranslucent() {
    StatusBarUtil.setTranslucentForImageViewInFragment(
      this,
      0, null
    )
    StatusBarUtil.setLightMode(this)
  }

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.logindirectpageVM = viewModel
    setStatusBarTranslucent()
    val extraData = intent.getStringExtra("key")
    val EditTextAccount: EditText = findViewById(R.id.etAcoount)
    EditTextAccount.setText(extraData)
  }

  override fun setUpClicks(): Unit {
    binding.btnLogInSignUpOne.setOnClickListener {
//     val destIntent = MainpageActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
//      this.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )

      val EditTextAccount: EditText = findViewById(R.id.etAcoount)
      val EditTextPassword: EditText = findViewById(R.id.etPassword)
      object : Thread() {
        override fun run() {
          val userDao = UserDao()
          val msg: Int = userDao.login(
            EditTextAccount.getText().toString(),
            EditTextPassword.getText().toString()
          )
          hand1.sendEmptyMessage(msg)
        }
      }.start()
    }
  }


  @SuppressLint("HandlerLeak")
  val hand1: Handler = object : Handler() {
    override fun handleMessage(msg: Message) {
      if (msg.what === 0) {
        Toast.makeText(applicationContext, "登录失败", Toast.LENGTH_LONG).show()
      } else if (msg.what === 1) {
        Toast.makeText(applicationContext, "登录成功", Toast.LENGTH_LONG).show()
        val destIntent = MainpageActivity.getIntent(this@LoginDirectpageActivity, null)
        startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
        this@LoginDirectpageActivity.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
      } else if (msg.what === 2) {
        Toast.makeText(applicationContext, "密码错误", Toast.LENGTH_LONG).show()
      } else if (msg.what === 3) {
        Toast.makeText(applicationContext, "账号不存在", Toast.LENGTH_LONG).show()
        val destIntent = LoginDirectpageActivity.getIntent(this@LoginDirectpageActivity, null)
        startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
        this@LoginDirectpageActivity.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
      }
    }
  }



  companion object {
    const val TAG: String = "LOGINDIRECTPAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, LoginDirectpageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
