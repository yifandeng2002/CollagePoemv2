package com.collagepoem.app.modules.driftcutspagetwo.ui

import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityDriftcutspagetwoBinding
import com.collagepoem.app.modules.driftcutspagetwo.`data`.viewmodel.DriftcutspagetwoVM
import kotlin.String
import kotlin.Unit

class DriftcutspagetwoActivity :
    BaseActivity<ActivityDriftcutspagetwoBinding>(R.layout.activity_driftcutspagetwo) {
  private val viewModel: DriftcutspagetwoVM by viewModels<DriftcutspagetwoVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.driftcutspagetwoVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "DRIFTCUTSPAGETWO_ACTIVITY"

  }
}
