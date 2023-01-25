package com.collagepoem.app.modules.driftcutspagethree.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityDriftcutspagethreeBinding
import com.collagepoem.app.modules.driftcutspagethree.`data`.viewmodel.DriftcutspagethreeVM
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class DriftcutspagethreeActivity :
    BaseActivity<ActivityDriftcutspagethreeBinding>(R.layout.activity_driftcutspagethree) {
  private val viewModel: DriftcutspagethreeVM by viewModels<DriftcutspagethreeVM>()

  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 754


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.driftcutspagethreeVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageBack.setOnClickListener {
      finish()
    }
    binding.imageBacktoholder.setOnClickListener {
      val destIntent = MainpageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
      this.overridePendingTransition(R.anim.right_to_left ,R.anim.right_to_left )
    }
  }

  companion object {
    const val TAG: String = "DRIFTCUTSPAGETHREE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, DriftcutspagethreeActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
