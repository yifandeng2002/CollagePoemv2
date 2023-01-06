package com.collagepoem.app.modules.floatwindowmycutsvtwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.floatwindowmycutsvtwo.`data`.model.FloatwindowMycutsVtwoModel
import com.collagepoem.app.modules.floatwindowmycutsvtwo.`data`.model.StickRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class FloatwindowMycutsVtwoVM : ViewModel(), KoinComponent {
  val floatwindowMycutsVtwoModel: MutableLiveData<FloatwindowMycutsVtwoModel> =
      MutableLiveData(FloatwindowMycutsVtwoModel())

  var navArguments: Bundle? = null

  val stickList: MutableLiveData<MutableList<StickRowModel>> = MutableLiveData(mutableListOf())
}
