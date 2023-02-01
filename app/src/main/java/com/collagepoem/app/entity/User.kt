package com.collagepoem.app.entity

class User {
    var id = 0
    var userAccount: String? = null
    var userPassword: String? = null
    var userName: String? = null
    var userType = 0
    var userState = 0
    var userDel = 0

    constructor() {}
    constructor(
        id: Int,
        userAccount: String?,
        userPassword: String?,
        userName: String?,
        userType: Int,
        userState: Int,
        userDel: Int
    ) {
        this.id = id
        this.userAccount = userAccount
        this.userPassword = userPassword
        this.userName = userName
        this.userType = userType
        this.userState = userState
        this.userDel = userDel
    }
}