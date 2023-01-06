package com.collagepoem.app.modules.albumpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.albumpage.`data`.model.AlbumpageModel
import com.collagepoem.app.modules.albumpage.`data`.model.ImagegridRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class AlbumpageVM : ViewModel(), KoinComponent {
  val albumpageModel: MutableLiveData<AlbumpageModel> = MutableLiveData(AlbumpageModel())

  var navArguments: Bundle? = null

  val imagegridList: MutableLiveData<MutableList<ImagegridRowModel>> =
      MutableLiveData(mutableListOf())
}
