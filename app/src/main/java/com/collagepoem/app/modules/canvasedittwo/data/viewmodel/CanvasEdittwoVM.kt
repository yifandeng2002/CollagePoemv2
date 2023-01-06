package com.collagepoem.app.modules.canvasedittwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.canvasedittwo.`data`.model.CanvasEdittwoModel
import org.koin.core.KoinComponent

class CanvasEdittwoVM : ViewModel(), KoinComponent {
  val canvasEdittwoModel: MutableLiveData<CanvasEdittwoModel> =
      MutableLiveData(CanvasEdittwoModel())

  var navArguments: Bundle? = null
}
