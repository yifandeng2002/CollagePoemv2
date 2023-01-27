package com.collagepoem.app

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Rect


class SaveandShare {




//    /**
//     * 根据指定的Activity截图（去除状态栏）
//     *
//     * @param activity 要截图的Activity
//     * @return Bitmap
//     */
//    fun shotActivityNoStatusBar(activity: Activity): Bitmap? {
//        // 获取windows中最顶层的view
//        val view = activity.window.decorView
//        view.buildDrawingCache()
//
//        // 获取状态栏高度
//        val rect = Rect()
//        view.getWindowVisibleDisplayFrame(rect)
//        val statusBarHeights = rect.top
//        val display = activity.windowManager.defaultDisplay
//
//        // 获取屏幕宽和高
//        val widths = display.width
//        val heights = display.height
//
//        // 允许当前窗口保存缓存信息
//        view.isDrawingCacheEnabled = true
//
//        // 去掉状态栏
//        val bmp = Bitmap.createBitmap(
//            view.drawingCache, 0,
//            statusBarHeights, widths, heights - statusBarHeights
//        )
//
//        // 销毁缓存信息
//        view.destroyDrawingCache()
//        return bmp
//    }


}