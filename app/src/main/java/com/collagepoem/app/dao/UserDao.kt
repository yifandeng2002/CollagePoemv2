package com.collagepoem.app.dao

import android.util.Log
import com.collagepoem.app.utils.JDBCUtils
import com.collagepoem.app.dao.UserDao
import com.collagepoem.app.entity.User
import java.lang.Exception
import java.lang.StringBuilder
import java.util.HashMap

class UserDao {
    /**
     * function: 登录
     */
    fun login(userAccount: String, userPassword: String): Int {
        val map = HashMap<String, Any>()
        // 根据数据库名称，建立连接
        val connection = JDBCUtils.conn
        var msg = 0
        try {
            // mysql简单的查询语句。这里是根据user表的userAccount字段来查询某条记录
            val sql = "select * from userinfo where email=?"
            if (connection != null) { // connection不为null表示与数据库建立了连接
                val ps = connection.prepareStatement(sql)
                if (ps != null) {
                    Log.e(TAG, "账号：$userAccount")
                    //根据账号进行查询
                    ps.setString(1, userAccount)
                    // 执行sql查询语句并返回结果集
                    val rs = ps.executeQuery()
                    val count = rs.metaData.columnCount
                    Log.e("error", "1")
                    //将查到的内容储存在map里
                    while (rs.next()) {
                        // 注意：下标是从1开始的
                        for (i in 1..count) {
                            val field = rs.metaData.getColumnName(i)
                            Log.e("error", "2_$field")
//                            map[field] = rs.getString(field)
                            map.put(field,rs.getString(field))
                        }
                    }

                    connection.close()
                    ps.close()
                    if (map.size != 0) {
                        val s = StringBuilder()
                        //寻找密码是否匹配
                        for (key in map.keys) {
                            Log.e("error", "key$key")
                            if (key == "password") {
                                if (userPassword == map[key]) {
                                    msg = 1 //密码正确
                                } else {

                                    msg = 2
                                } //密码错误
                                break
                            }
                        }
                    } else {
                        Log.e(TAG, "查询结果为空")
                        msg = 3
                    }
                } else {
                    msg = 0
                }
            } else {
                msg = 0
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(TAG, "异常login：" + e.message)
            msg = 0
        }
        return msg
    }

    /**
     * function: 注册
     */
    fun register(user: User): Boolean {
        val map = HashMap<String, Any>()
        // 根据数据库名称，建立连接
        val connection = JDBCUtils.conn
        return try {
            val sql =
                "insert into userinfo (email,password,user_id,phone_number,name) values (?,?,1,1,1)"
            if (connection != null) { // connection不为null表示与数据库建立了连接
                val ps = connection.prepareStatement(sql)
                if (ps != null) {

                    //将数据插入数据库
                    ps.setString(1, user.userAccount)
                    ps.setString(2, user.userPassword)


                    // 执行sql查询语句并返回结果集
                    val rs = ps.executeUpdate()
                    if (rs > 0) true else false
                } else {
                    false
                }
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "异常register：" + e.message)
            false
        }
    }

    /**
     * function: 根据账号进行查找该用户是否存在
     */
    fun findUser(userAccount: String?): User? {

        // 根据数据库名称，建立连接
        val connection = JDBCUtils.conn
        var user: User? = null
        try {
            val sql = "select * from user where userAccount = ?"
            if (connection != null) { // connection不为null表示与数据库建立了连接
                val ps = connection.prepareStatement(sql)
                if (ps != null) {
                    ps.setString(1, userAccount)
                    val rs = ps.executeQuery()
                    while (rs.next()) {
                        //注意：下标是从1开始
                        val id = rs.getInt(1)
                        val userAccount1 = rs.getString(2)
                        val userPassword = rs.getString(3)
                        val userName = rs.getString(4)
                        val userType = rs.getInt(5)
                        val userState = rs.getInt(6)
                        val userDel = rs.getInt(7)
                        user = User(
                            id,
                            userAccount1,
                            userPassword,
                            userName,
                            userType,
                            userState,
                            userDel
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(TAG, "异常findUser：" + e.message)
            return null
        }
        return user
    }

    companion object {
        private const val TAG = "mysql-party-UserDao"
    }
}