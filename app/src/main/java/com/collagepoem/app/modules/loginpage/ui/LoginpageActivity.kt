package com.collagepoem.app.modules.loginpage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityLoginpageBinding
import com.collagepoem.app.modules.loginpage.`data`.viewmodel.LoginpageVM
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class LoginpageActivity : BaseActivity<ActivityLoginpageBinding>(R.layout.activity_loginpage) {
  private val viewModel: LoginpageVM by viewModels<LoginpageVM>()

  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 833


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.loginpageVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnLogInSignUpOne.setOnClickListener {
      val destIntent = MainpageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.fade_in ,R.anim.fade_out )
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
