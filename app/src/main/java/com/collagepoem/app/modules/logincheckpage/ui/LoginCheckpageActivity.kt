package com.collagepoem.app.modules.logincheckpage.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.dao.UserDao
import com.collagepoem.app.databinding.ActivityLogincheckpageBinding
import com.collagepoem.app.entity.User
import com.collagepoem.app.modules.logincheckpage.data.viewmodel.LoginCheckpageVM
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.jaeger.library.StatusBarUtil
import com.mysql.jdbc.log.Log
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.IOException
import java.sql.Blob
import kotlin.math.log


class LoginCheckpageActivity : BaseActivity<ActivityLogincheckpageBinding>(R.layout.activity_logincheckpage) {
  private val viewModel: LoginCheckpageVM by viewModels<LoginCheckpageVM>()
  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 194
  val user = User()
  var userAccount: EditText? = null
  var userPassword: EditText? = null


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
    binding.logincheckpageVM = viewModel
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
      var msg = 0
      val EditTextAccount: EditText = findViewById(R.id.etAcoount)
      val EditTextPassword: EditText = findViewById(R.id.etPassword)
      val userAccount = EditTextAccount.text.toString()
      val userPassword = EditTextPassword.text.toString()
      val account=UserDao.Companion
      android.util.Log.e("HEY", userAccount)
      object : Thread() {
        override fun run() {
          val userDao = UserDao()
          user.userAccount=userAccount
          user.userPassword=userPassword
          val flag: Boolean = userDao.register(user)
          if (flag) {
            msg = 2}
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
        val destIntent = MainpageActivity.getIntent(this@LoginCheckpageActivity, null)
        startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
        this@LoginCheckpageActivity.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
      } else if (msg.what === 2) {
        Toast.makeText(applicationContext, "注册成功", Toast.LENGTH_LONG).show()
        val destIntent = MainpageActivity.getIntent(this@LoginCheckpageActivity, null)
        startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
        this@LoginCheckpageActivity.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
      } else if (msg.what === 3) {
        Toast.makeText(applicationContext, "账号不存在", Toast.LENGTH_LONG).show()
      }
    }
  }



  companion object {
    const val TAG: String = "LOGINCHECKPAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, LoginCheckpageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
