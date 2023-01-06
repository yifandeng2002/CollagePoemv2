package com.collagepoem.app.modules.communitypage.ui

import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityCommunitypageBinding
import com.collagepoem.app.modules.communitypage.`data`.viewmodel.CommunitypageVM
import kotlin.String
import kotlin.Unit

class CommunitypageActivity :
    BaseActivity<ActivityCommunitypageBinding>(R.layout.activity_communitypage) {
  private val viewModel: CommunitypageVM by viewModels<CommunitypageVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.communitypageVM = viewModel
  }

  override fun setUpClicks(): Unit {
  }

  companion object {
    const val TAG: String = "COMMUNITYPAGE_ACTIVITY"

  }
}
