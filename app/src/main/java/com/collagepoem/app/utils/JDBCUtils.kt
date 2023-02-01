package com.collagepoem.app.utils

import com.collagepoem.app.utils.JDBCUtils
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager

object JDBCUtils {
    private const val TAG = "mysql-party-JDBCUtils"
    private const val driver = "com.mysql.jdbc.Driver" // MySql驱动
    private const val dbName = "party" // 数据库名称
    private const val user = "joedeng2" // 用户名
    private const val password = "Dyf731206" // 密码// 写成本机地址，不能写成localhost，同时手机和电脑连接的网络必须是同一个

    // 尝试建立到给定数据库URL的连接
    // 动态加载类
    val conn: Connection?
        get() {
            var connection: Connection? = null
            try {
                Class.forName(driver) // 动态加载类
                val ip =
                    "gz-cynosdbmysql-grp-i3ws27i1.sql.tencentcdb.com:25111/Poem" + "?useUnicode=true&characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&autoReconnect=true&failOverReadOnly=false" // 写成本机地址，不能写成localhost，同时手机和电脑连接的网络必须是同一个

                // 尝试建立到给定数据库URL的连接
                connection = DriverManager.getConnection("jdbc:mysql://$ip", user, password)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return connection
        }
}