package com.collagepoem.app.modules.canvaseditone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.canvaseditone.`data`.model.CanvasEditoneModel
import org.koin.core.KoinComponent

class CanvasEditoneVM : ViewModel(), KoinComponent {
  val canvasEditoneModel: MutableLiveData<CanvasEditoneModel> =
      MutableLiveData(CanvasEditoneModel())

  var navArguments: Bundle? = null
}
