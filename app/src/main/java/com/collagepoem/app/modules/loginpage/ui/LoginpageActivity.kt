package com.collagepoem.app.modules.loginpage.ui

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
import com.collagepoem.app.databinding.ActivityLoginpageBinding
import com.collagepoem.app.modules.logincheckpage.ui.LoginCheckpageActivity
import com.collagepoem.app.modules.logindirectpage.ui.LoginDirectpageActivity
import com.collagepoem.app.modules.loginpage.data.viewmodel.LoginpageVM
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.jaeger.library.StatusBarUtil


class LoginpageActivity : BaseActivity<ActivityLoginpageBinding>(R.layout.activity_loginpage) {
  private val viewModel: LoginpageVM by viewModels<LoginpageVM>()
  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 194
  private val REQUEST_CODE_LOGINCHECKPAGE_ACTIVITY: Int = 223
  private val REQUEST_CODE_LOGINDIRECTPAGE_ACTIVITY: Int = 228

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
    binding.loginpageVM = viewModel
    setStatusBarTranslucent()
  }

  override fun setUpClicks(): Unit {
    binding.btnLogInSignUpOne.setOnClickListener {
//     val destIntent = MainpageActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
//      this.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )

      val EditTextAccount: EditText = findViewById(R.id.etAcoount)
      //val EditTextPassword: EditText = findViewById(R.id.etPassword)
      object : Thread() {
        override fun run() {
          val userDao = UserDao()
          val msg: Int = userDao.login(
            EditTextAccount.getText().toString(),
            "0"//EditTextPassword.getText().toString()
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
        val destIntent = MainpageActivity.getIntent(this@LoginpageActivity, null)
        startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
        this@LoginpageActivity.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
      } else if (msg.what === 2) {
        Toast.makeText(applicationContext, "请输入密码", Toast.LENGTH_LONG).show()
        val destIntent = LoginDirectpageActivity.getIntent(this@LoginpageActivity, null)
        val EditTextAccount: EditText = findViewById(R.id.etAcoount)
        destIntent.putExtra("key",EditTextAccount.getText().toString())
        startActivityForResult(destIntent, REQUEST_CODE_LOGINDIRECTPAGE_ACTIVITY)
        this@LoginpageActivity.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )

      } else if (msg.what === 3) {
        Toast.makeText(applicationContext, "账号不存在", Toast.LENGTH_LONG).show()
        val destIntent = LoginCheckpageActivity.getIntent(this@LoginpageActivity, null)
        val EditTextAccount: EditText = findViewById(R.id.etAcoount)
        destIntent.putExtra("key",EditTextAccount.getText().toString())
        startActivityForResult(destIntent, REQUEST_CODE_LOGINCHECKPAGE_ACTIVITY)
        this@LoginpageActivity.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
      }
    }
  }



  companion object {
    const val TAG: String = "LOGINPAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, LoginpageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
