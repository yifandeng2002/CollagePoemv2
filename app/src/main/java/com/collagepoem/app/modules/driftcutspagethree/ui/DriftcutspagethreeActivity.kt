package com.collagepoem.app.modules.driftcutspagethree.ui

import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityDriftcutspagethreeBinding
import com.collagepoem.app.modules.driftcutspagethree.`data`.viewmodel.DriftcutspagethreeVM
import kotlin.String
import kotlin.Unit

class DriftcutspagethreeActivity :
    BaseActivity<ActivityDriftcutspagethreeBinding>(R.layout.activity_driftcutspagethree) {
  private val viewModel: DriftcutspagethreeVM by viewModels<DriftcutspagethreeVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.driftcutspagethreeVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "DRIFTCUTSPAGETHREE_ACTIVITY"

  }
}
