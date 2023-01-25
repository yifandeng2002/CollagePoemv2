package com.collagepoem.app.modules.loginpage.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.base.BaseActivity
import com.collagepoem.app.appcomponents.di.MyApp
import com.collagepoem.app.databinding.ActivityLoginpageBinding
import com.collagepoem.app.extensions.alert
import com.collagepoem.app.extensions.isJSONObject
import com.collagepoem.app.extensions.neutralButton
import com.collagepoem.app.modules.loginpage.`data`.viewmodel.LoginpageVM
import com.collagepoem.app.modules.mainpage.ui.MainpageActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception
import kotlin.Int
import kotlin.String
import kotlin.Unit
import org.json.JSONObject
import retrofit2.HttpException

class LoginpageActivity : BaseActivity<ActivityLoginpageBinding>(R.layout.activity_loginpage) {
  private val viewModel: LoginpageVM by viewModels<LoginpageVM>()

  private val firestoreRef: FirebaseFirestore = Firebase.firestore


  private val REQUEST_CODE_MAINPAGE_ACTIVITY: Int = 634


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.loginpageVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnLogInSignUpOne.setOnClickListener {
      val queryBtnLogInSignUpOne = firestoreRef
      .collection("UserInfo")
      if(viewModel.loginpageModel.value?.etAcoountValue != null) {
        queryBtnLogInSignUpOne.whereEqualTo("phonenumber",
        viewModel.loginpageModel.value?.etAcoountValue!!)
      }
      if(viewModel.loginpageModel.value?.etPasswordValue != null) {
        queryBtnLogInSignUpOne.whereEqualTo("password",
        viewModel.loginpageModel.value?.etPasswordValue!!)
      }
      queryBtnLogInSignUpOne.orderBy("phonenumber", Query.Direction.ASCENDING)
      queryBtnLogInSignUpOne.limit(2)
      queryBtnLogInSignUpOne.get()
      .addOnSuccessListener { document ->
        onSuccessBtnLogInSignUpOneQuery(document)
      }
      .addOnFailureListener { exception ->
        onErrorBtnLogInSignUpOneQuery(exception)
      }
    }
  }

  private fun onSuccessBtnLogInSignUpOneQuery(result: QuerySnapshot?): Unit {
    if (result == null) return
    val loginpageModelValue = viewModel.loginpageModel.value
    viewModel.loginpageModel.value = loginpageModelValue
    val destIntent = MainpageActivity.getIntent(this, null)
    startActivityForResult(destIntent, REQUEST_CODE_MAINPAGE_ACTIVITY)
    this.overridePendingTransition(R.anim.slide_up ,R.anim.slide_down )
  }

  private fun onErrorBtnLogInSignUpOneQuery(exception: Exception): Unit {
    when(exception) {
      is HttpException -> {
        val errorBody = exception.response()?.errorBody()?.string()
        val errorObject = if (errorBody != null  && errorBody.isJSONObject()) JSONObject(errorBody)
        else JSONObject()
        val errMessage = MyApp.getInstance().getString(R.string.msg_valid_account_name_or_pa)
        this@LoginpageActivity.alert(MyApp.getInstance().getString(R.string.lbl_error),errMessage) {
          neutralButton {
          }
        }
      }
    }
  }

  companion object {
    const val TAG: String = "LOGINPAGE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, LoginpageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
