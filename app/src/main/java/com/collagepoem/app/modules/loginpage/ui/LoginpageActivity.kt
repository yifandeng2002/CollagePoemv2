package com.collagepoem.app.modules.loginpage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityLoginpageBinding
import com.collagepoem.app.modules.loginpage.data.viewmodel.LoginpageVM
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.jaeger.library.StatusBarUtil
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


class LoginpageActivity : BaseActivity<ActivityLoginpageBinding>(R.layout.activity_loginpage) {
  private val viewModel: LoginpageVM by viewModels<LoginpageVM>()

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
    binding.loginpageVM = viewModel
    setStatusBarTranslucent()
    Thread(Runnable {
      val sql = "SELECT * FROM poeminfo"
      mysqlConnection(sql)
    }).start()

  }

  override fun setUpClicks(): Unit {
    binding.btnLogInSignUpOne.setOnClickListener {
      val destIntent = MainpageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
    }
  }

  fun mysqlConnection(sql:String) {

    var cn: Connection
    var url = "gz-cynosdbmysql-grp-i3ws27i1.sql.tencentcdb.com:25111/UserInfo" + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Guangzhou"
    try {
//      val msg = Message ()
//加载驱动
      Class.forName("com.mysql.jdbc.Driver")
//建立连接
      cn = DriverManager.getConnection("jdbc:mysql://gz-cynosdbmysql-grp-i3ws27i1.sql.tencentcdb.com:25111/Poem" + "?useUnicode=true&characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&autoReconnect=true&failOverReadOnly=false","joedeng2", "Dyf731206")
      val id=findViewById<View>(R.id.etAcoount)
      val password=findViewById<View>(R.id.etPassword)
      val ps = cn.createStatement()
      val resultSet = ps!!.executeQuery(sql)
      while (resultSet.next()) {
        Log.d("mysqlConnection: " ,
          resultSet.getString("author") +
                  resultSet.getString("country") + resultSet.getString("title"))

      }
      if (ps != null) {
        ps!!.close()
      }
      if (cn != null) {
        cn.close()
      }
    } catch (e: ClassNotFoundException) {
      e.printStackTrace()
    } catch (e: SQLException) {
      e.printStackTrace()
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
