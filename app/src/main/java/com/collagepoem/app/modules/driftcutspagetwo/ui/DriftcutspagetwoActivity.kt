package com.collagepoem.app.modules.driftcutspagetwo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityDriftcutspagetwoBinding
import com.collagepoem.app.modules.driftcutspagethree.ui.DriftcutspagethreeActivity
import com.collagepoem.app.modules.driftcutspagetwo.`data`.viewmodel.DriftcutspagetwoVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class DriftcutspagetwoActivity :
    BaseActivity<ActivityDriftcutspagetwoBinding>(R.layout.activity_driftcutspagetwo) {
  private val viewModel: DriftcutspagetwoVM by viewModels<DriftcutspagetwoVM>()

  private val REQUEST_CODE_DRIFTCUTSPAGETHREE_ACTIVITY: Int = 773

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.driftcutspagetwoVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageBack.setOnClickListener {
      finish()
    }
    binding.imageBottonTwo.setOnClickListener {
      val destIntent = DriftcutspagethreeActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_DRIFTCUTSPAGETHREE_ACTIVITY)
      this.overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out )
    }
  }

  companion object {
    const val TAG: String = "DRIFTCUTSPAGETWO_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, DriftcutspagetwoActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
