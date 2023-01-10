package com.collagepoem.app.modules.landinpage.ui

import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityLandinpageBinding
import com.collagepoem.app.modules.landinpage.`data`.viewmodel.LandinpageVM
import com.collagepoem.app.modules.loginpage.ui.LoginpageActivity
import com.jaeger.library.StatusBarUtil
import kotlin.String
import kotlin.Unit

class LandinpageActivity : BaseActivity<ActivityLandinpageBinding>(R.layout.activity_landinpage) {
  private val viewModel: LandinpageVM by viewModels<LandinpageVM>()

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
    binding.landinpageVM = viewModel
    Handler(Looper.getMainLooper()).postDelayed( {
      val destIntent = LoginpageActivity.getIntent(this, null)
      startActivity(destIntent)
      }, 3000)
    setStatusBarTranslucent()
    }

    override fun setUpClicks(): Unit {
    }

    companion object {
      const val TAG: String = "LANDINPAGE_ACTIVITY"

    }
  }
