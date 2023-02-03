package com.collagepoem.app.modules.logindirectpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.logincheckpage.data.model.LoginCheckpageModel
import com.collagepoem.app.modules.logindirectpage.data.model.LoginDirectpageModel
import org.koin.core.KoinComponent

class LoginDirectpageVM : ViewModel(), KoinComponent {
  val loginDirectpageModel: MutableLiveData<LoginDirectpageModel> = MutableLiveData(LoginDirectpageModel())

  var navArguments: Bundle? = null
}
