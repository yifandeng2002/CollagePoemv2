package com.collagepoem.app.noteinfo

import android.util.Log
import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import com.collagepoem.app.entity.Poem
import com.collagepoem.app.modules.mainpage.Poem1
import com.collagepoem.app.utils.JDBCUtils
import java.lang.Exception
import java.util.HashMap

class poemInfo {
    fun showpoem(poem1:Poem1): Poem1? {
        val mapnote = HashMap<String, Any>()
        // 根据数据库名称，建立连接
        val connection = JDBCUtils.conn
        var note: Poem? = null
        try {
            val sql = "select * from poeminfo"
            if(connection != null) { // connection不为null表示与数据库建立了连接
                val ps = connection.prepareStatement(sql)
                if (ps != null) {
                    //ps.setInt(0, state)
                    val rs = ps.executeQuery()
                    while (rs.next()) {
                        var TimeEN = rs.getString(1)
                        var TimeCN = rs.getString(2)
                        var Order = rs.getString(3)
                        var Descrip = rs.getString(4)
                        var imageCardView = MyApp.getInstance().resources.getDrawable(R.drawable.img_poemcard_648x356)
                        //var image= rs.getBlob(8)
                        if(TimeCN!= null) {
                            poem1.TimeEN.add(TimeEN.toString())
                            poem1.TimeCN.add(TimeCN.toString())
                            poem1.Order.add(Order.toString())
                            poem1.Descrip.add(Descrip.toString())
                            poem1.image.add(imageCardView)
                        }
                    }
            }
                connection.close()
                ps.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("poem", "异常" )
            return null
        }

        return poem1

    }
}