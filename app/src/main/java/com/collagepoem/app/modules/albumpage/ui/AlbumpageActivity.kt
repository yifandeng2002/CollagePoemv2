package com.collagepoem.app.modules.albumpage.ui

import android.view.View
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.databinding.ActivityAlbumpageBinding
import com.collagepoem.app.modules.albumpage.`data`.model.ImagegridRowModel
import com.collagepoem.app.modules.albumpage.`data`.viewmodel.AlbumpageVM
import com.jaeger.library.StatusBarUtil
import kotlin.Int
import kotlin.String
import kotlin.Unit

class AlbumpageActivity : BaseActivity<ActivityAlbumpageBinding>(R.layout.activity_albumpage) {
  private val viewModel: AlbumpageVM by viewModels<AlbumpageVM>()

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
    val imagegridAdapter = ImagegridAdapter(viewModel.imagegridList.value?:mutableListOf())
    binding.recyclerImagegrid.adapter = imagegridAdapter
    imagegridAdapter.setOnItemClickListener(
    object : ImagegridAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ImagegridRowModel) {
        onClickRecyclerImagegrid(view, position, item)
      }
    }
    )
    viewModel.imagegridList.observe(this) {
      imagegridAdapter.updateData(it)
    }
    binding.albumpageVM = viewModel
    setStatusBarTranslucent()
  }

  override fun setUpClicks(): Unit {
  }

  fun onClickRecyclerImagegrid(
    view: View,
    position: Int,
    item: ImagegridRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "ALBUMPAGE_ACTIVITY"

  }
}
