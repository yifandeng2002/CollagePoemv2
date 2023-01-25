package com.collagepoem.app.firestore.models

import com.google.firebase.firestore.PropertyName
import kotlin.Int
import kotlin.String
import kotlin.jvm.JvmField

public class UserInfo {
  @JvmField
  @PropertyName("phonenumber")
  public var phonenumber: Int? = null

  @JvmField
  @PropertyName("password")
  public var password: String? = null
}
