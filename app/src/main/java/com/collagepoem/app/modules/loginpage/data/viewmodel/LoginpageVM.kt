package com.collagepoem.app.modules.loginpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.loginpage.`data`.model.LoginpageModel
import org.koin.core.KoinComponent

class LoginpageVM : ViewModel(), KoinComponent {
  val loginpageModel: MutableLiveData<LoginpageModel> = MutableLiveData(LoginpageModel())

  var navArguments: Bundle? = null
}
