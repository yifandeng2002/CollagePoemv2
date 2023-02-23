package com.lxj.xpopup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Build;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Lifecycle;
import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.ImageViewerPopupView;
import com.lxj.xpopup.core.PopupInfo;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.enums.PopupPosition;
import com.lxj.xpopup.impl.AttachListPopupView;
import com.lxj.xpopup.impl.BottomListPopupView;
import com.lxj.xpopup.impl.CenterListPopupView;
import com.lxj.xpopup.impl.ConfirmPopupView;
import com.lxj.xpopup.impl.InputConfirmPopupView;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnImageViewerLongPressListener;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.lxj.xpopup.interfaces.XPopupImageLoader;
import com.lxj.xpopup.util.XPermission;
import com.lxj.xpopup.util.XPopupUtils;
import java.util.ArrayList;
import java.util.List;


public class XPopup {
    private XPopup() { }

    /**
     * 全局弹窗的设置
     **/
    private static int primaryColor = Color.parseColor("#121212");
    private static int animationDuration = 300;
    private static int statusBarBgColor = Color.parseColor("#55000000");
    private static int navigationBarColor = 0;
    private static int shadowBgColor = Color.parseColor("#7F000000");
    public static int isLightStatusBar = 0; //大于0为true，小于0为false
    public static int isLightNavigationBar = 0; //大于0为true，小于0为false

    /**
     * 设置全局的背景阴影颜色
     * @param color
     */
    public static void setShadowBgColor(int color) {
        shadowBgColor = color;
    }
    public static int getShadowBgColor() {
        return shadowBgColor;
    }

    /**
     * 设置全局的状态栏背景颜色
     *
     * @param color
     */
    public static void setStatusBarBgColor(int color) {
        statusBarBgColor = color;
    }

    public static int getStatusBarBgColor() {
        return statusBarBgColor;
    }

    /**
     * 设置全局的导航栏栏背景颜色
     *
     * @param color
     */
    public static void setNavigationBarColor(int color) {
        navigationBarColor = color;
    }

    public static int getNavigationBarColor() {
        return navigationBarColor;
    }

    /**
     * 设置主色调
     *
     * @param color
     */
    public static void setPrimaryColor(int color) {
        primaryColor = color;
    }

    public static int getPrimaryColor() {
        return primaryColor;
    }

    /**
     * 统一设置是否是亮色状态栏
     * @param isLight
     */
    public static void setIsLightStatusBar(boolean isLight) {
        isLightStatusBar = isLight ? 1 : -1;
    }

    /**
     * 统一设置是否是亮色导航栏
     * @param isLight
     */
    public static void setIsLightNavigationBar(boolean isLight) {
        isLightNavigationBar = isLight ? 1 : -1;
    }

    /**
     * 设置全局动画时长
     * @param duration
     */
    public static void setAnimationDuration(int duration) {
        if (duration >= 0) {
            animationDuration = duration;
        }
    }

    public static int getAnimationDuration() {
        return animationDuration;
    }

    /**
     * 在长按弹出弹窗后，能保证下层View不能滑动
     * @param v
     */
    public static PointF longClickPoint = null;
    public static void fixLongClick(View v){
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    longClickPoint = new PointF(event.getRawX(), event.getRawY());
                }
                if("xpopup".equals(v.getTag()) && event.getAction()==MotionEvent.ACTION_MOVE){
                    //长按发送，阻断父View拦截
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                if(event.getAction()==MotionEvent.ACTION_UP){
                    //长按结束，恢复阻断
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    v.setTag(null);
                }
                return false;
            }
        });
        v.setTag("xpopup");
    }

    public static class Builder {
        private final PopupInfo popupInfo = new PopupInfo();
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置按下返回键是否关闭弹窗，默认为true
         *
         * @param isDismissOnBackPressed
         * @return
         */
        public Builder dismissOnBackPressed(Boolean isDismissOnBackPressed) {
            this.popupInfo.isDismissOnBackPressed = isDismissOnBackPressed;
            return this;
        }

        /**
         * 设置点击弹窗外面是否关闭弹窗，默认为true
         *
         * @param isDismissOnTouchOutside
         * @return
         */
        public Builder dismissOnTouchOutside(Boolean isDismissOnTouchOutside) {
            this.popupInfo.isDismissOnTouchOutside = isDismissOnTouchOutside;
            return this;
        }

        /**
         * 设置当操作完毕后是否自动关闭弹窗，默认为true。比如：点击Confirm弹窗的确认按钮默认是关闭弹窗，如果为false，则不关闭
         *
         * @param autoDismiss
         * @return
         */
        public Builder autoDismiss(Boolean autoDismiss) {
            this.popupInfo.autoDismiss = autoDismiss;
            return this;
        }

        /**
         * 弹窗是否有半透明背景遮罩，默认是true
         *
         * @param hasShadowBg
         * @return
         */
        public Builder hasShadowBg(Boolean hasShadowBg) {
            this.popupInfo.hasShadowBg = hasShadowBg;
            return this;
        }

        /**
         * 是否设置背景为高斯模糊背景。默认为false
         *
         * @param hasBlurBg
         * @return
         */
        public Builder hasBlurBg(boolean hasBlurBg) {
            this.popupInfo.hasBlurBg = hasBlurBg;
            return this;
        }

        /**
         * 设置弹窗依附的View，Attach弹窗必须设置这个
         *
         * @param atView
         * @return
         */
        public Builder atView(View atView) {
            popupInfo.atView = atView;
            return this;
        }

        /**
         * 设置弹窗依附的点，Attach弹窗必须设置这个
         * @param point
         * @return
         */
        public Builder atPoint(PointF point) {
            popupInfo.touchPoint = point;
            return this;
        }

        /**
         * 设置弹窗监视的View
         *
         * @param watchView
         * @return
         */
        public Builder watchView(View watchView) {
            watchView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN){
                        popupInfo.touchPoint = new PointF(event.getRawX(), event.getRawY());
                    }
                    return false;
                }
            });
            return this;
        }

        /**
         * 为弹窗设置内置的动画器，默认情况下，已经为每种弹窗设置了效果最佳的动画器；如果你不喜欢，仍然可以修改。
         *
         * @param popupAnimation
         * @return
         */
        public Builder popupAnimation(PopupAnimation popupAnimation) {
            this.popupInfo.popupAnimation = popupAnimation;
            return this;
        }

        /**
         * 自定义弹窗动画器
         *
         * @param customAnimator
         * @return
         */
        public Builder customAnimator(PopupAnimator customAnimator) {
            this.popupInfo.customAnimator = customAnimator;
            return this;
        }

        /**
         * 设置高度，如果重写了弹窗的getPopupHeight，则以重写的为准
         * 并且受最大高度限制
         * @param height
         * @return
         */
        public Builder popupHeight(int height) {
            this.popupInfo.popupHeight = height;
            return this;
        }

        /**
         * 设置宽度，如果重写了弹窗的getPopupWidth，则以重写的为准
         * 并且受最大宽度限制
         * @param width
         * @return
         */
        public Builder popupWidth(int width) {
            this.popupInfo.popupWidth = width;
            return this;
        }

        /**
         * 设置最大宽度，如果重写了弹窗的getMaxWidth，则以重写的为准
         *
         * @param maxWidth
         * @return
         */
        public Builder maxWidth(int maxWidth) {
            this.popupInfo.maxWidth = maxWidth;
            return this;
        }

        /**
         * 设置最大高度，如果重写了弹窗的getMaxHeight，则以重写的为准
         *
         * @param maxHeight
         * @return
         */
        public Builder maxHeight(int maxHeight) {
            this.popupInfo.maxHeight = maxHeight;
            return this;
        }


        /**
         * 是否自动打开输入法，当弹窗包含输入框时很有用，默认为false
         *
         * @param autoOpenSoftInput
         * @return
         */
        public Builder autoOpenSoftInput(Boolean autoOpenSoftInput) {
            this.popupInfo.autoOpenSoftInput = autoOpenSoftInput;
            return this;
        }

        /**
         * 当弹出输入法时，弹窗是否要移动到输入法之上，默认为true。如果不移动，弹窗很有可能被输入法盖住
         *
         * @param isMoveUpToKeyboard
         * @return
         */
        public Builder moveUpToKeyboard(Boolean isMoveUpToKeyboard) {
            this.popupInfo.isMoveUpToKeyboard = isMoveUpToKeyboard;
            return this;
        }
        /**
         * 当弹出输入法时，弹窗是否要覆盖/遮挡到输入法之上，默认为false，只在dialog模式下有效。
         * 注意：覆盖输入法上后，输入法将失去焦点，将无法进行输入。推荐当你想让弹窗阻止输入的
         * 场景下使用
         *
         * @param isCoverSoftInput
         * @return
         */
        public Builder isCoverSoftInput(Boolean isCoverSoftInput) {
            this.popupInfo.isCoverSoftInput = isCoverSoftInput;
            return this;
        }

        /**
         * 设置弹窗出现在目标的什么位置，有四种取值：Left，Right，Top，Bottom。这种手动设置位置的行为
         * 只对Attach弹窗和Drawer弹窗生效。
         *
         * @param popupPosition
         * @return
         */
        public Builder popupPosition(PopupPosition popupPosition) {
            this.popupInfo.popupPosition = popupPosition;
            return this;
        }

        /**
         * 设置是否给StatusBar添加阴影，目前对Drawer弹窗和全屏弹窗生效生效。
         *
         * @param hasStatusBarShadow
         * @return
         */
        public Builder hasStatusBarShadow(boolean hasStatusBarShadow) {
            this.popupInfo.hasStatusBarShadow = hasStatusBarShadow;
            return this;
        }

        /**
         * 设置是否显示状态栏，默认是显示的。如果你希望弹窗隐藏状态栏，就设置为true;
         * 只在dialog模式下有效
         * @param hasStatusBar
         * @return
         */
        public Builder hasStatusBar(boolean hasStatusBar) {
            this.popupInfo.hasStatusBar = hasStatusBar;
            return this;
        }

        /**
         * 设置是否显示导航栏，默认是显示的。如果你希望弹窗隐藏导航栏，就设置为true
         * 只在dialog模式下有效
         * @param hasNavigationBar
         * @return
         */
        public Builder hasNavigationBar(boolean hasNavigationBar) {
            this.popupInfo.hasNavigationBar = hasNavigationBar;
            return this;
        }

        /**
         * 设置导航栏的颜色，如果你希望弹窗修改导航栏的颜色的时候用；
         * 只在dialog模式下有效
         * @param navigationBarColor
         * @return
         */
        public Builder navigationBarColor(int navigationBarColor) {
            this.popupInfo.navigationBarColor = navigationBarColor;
            return this;
        }

        /**
         * 设置导航栏是否是亮色，默认false
         * 只在dialog模式下有效
         * @param isLightNavigationBar
         * @return
         */
        public Builder isLightNavigationBar(boolean isLightNavigationBar) {
            this.popupInfo.isLightNavigationBar = isLightNavigationBar ? 1 : -1;
            return this;
        }

        /**
         * 设置状态栏是否是亮色，默认false
         * 只在dialog模式下有效
         * @param isLightStatusBar
         * @return
         */
        public Builder isLightStatusBar(boolean isLightStatusBar) {
            this.popupInfo.isLightStatusBar = isLightStatusBar ? 1 : -1;
            return this;
        }

        /**
         * 设置状态栏的背景颜色，目前只对全屏弹窗和Drawer弹窗有效，其他弹窗
         * XPopup强制将状态栏设置为透明
         * @param statusBarBgColor
         * @return
         */
        public Builder statusBarBgColor(int statusBarBgColor) {
            this.popupInfo.statusBarBgColor = statusBarBgColor;
            return this;
        }

        /**
         * 弹窗在x方向的偏移量，对所有弹窗生效，单位是px
         *
         * @param offsetX
         * @return
         */
        public Builder offsetX(int offsetX) {
            this.popupInfo.offsetX = offsetX;
            return this;
        }

        /**
         * 弹窗在y方向的偏移量，对所有弹窗生效，单位是px
         *
         * @param offsetY
         * @return
         */
        public Builder offsetY(int offsetY) {
            this.popupInfo.offsetY = offsetY;
            return this;
        }

        /**
         * 是否启用拖拽，比如：Bottom弹窗默认是带手势拖拽效果的，如果禁用则不能拖拽
         *
         * @param enableDrag
         * @return
         */
        public Builder enableDrag(boolean enableDrag) {
            this.popupInfo.enableDrag = enableDrag;
            return this;
        }

        /**
         * 是否与目标水平居中，比如：默认情况下Attach弹窗依靠着目标的左边或者右边，如果isCenterHorizontal为true，则与目标水平居中对齐
         *
         * @param isCenterHorizontal
         * @return
         */
        public Builder isCenterHorizontal(boolean isCenterHorizontal) {
            this.popupInfo.isCenterHorizontal = isCenterHorizontal;
            return this;
        }

        /**
         * 是否抢占焦点，默认情况下弹窗会抢占焦点，目的是为了能处理返回按键事件。如果为false，则不在抢焦点，但也无法响应返回按键了
         *
         * @param isRequestFocus 默认为true
         * @return
         */
        public Builder isRequestFocus(boolean isRequestFocus) {
            this.popupInfo.isRequestFocus = isRequestFocus;
            return this;
        }

        /**
         * 是否让弹窗内的输入框自动获取焦点，默认是true。弹窗内有输入法的情况下该设置才有效
         *
         * @param autoFocusEditText
         * @return
         */
        public Builder autoFocusEditText(boolean autoFocusEditText) {
            this.popupInfo.autoFocusEditText = autoFocusEditText;
            return this;
        }

        /**
         * 是否使用暗色主题，默认是false。对所有内置弹窗生效。
         *
         * @param isDarkTheme
         * @return
         */
        public Builder isDarkTheme(boolean isDarkTheme) {
            this.popupInfo.isDarkTheme = isDarkTheme;
            return this;
        }

        /**
         * 是否点击弹窗背景时将点击事件透传到Activity下，默认是false。目前对Center弹窗，Attach弹窗，
         * Position弹窗，PartShadow弹窗生效；对Drawer弹窗，FullScreen弹窗，Bottom弹窗不生效（未开放功能）
         *
         * @param isClickThrough
         * @return
         */
        public Builder isClickThrough(boolean isClickThrough) {
            this.popupInfo.isClickThrough = isClickThrough;
            return this;
        }

        /**
         * 是否触摸弹窗背景时将触摸事件透传到Activity下，默认是false。目前对Center弹窗，Attach弹窗，
         * Position弹窗，PartShadow弹窗生效；对Drawer弹窗，FullScreen弹窗，Bottom弹窗不生效（未开放功能）
         *
         * @param isTouchThrough
         * @return
         */
        public Builder isTouchThrough(boolean isTouchThrough) {
            this.popupInfo.isTouchThrough = isTouchThrough;
            return this;
        }

        /**
         * 是否允许应用在后台的时候也能弹出弹窗，默认是false。注意如果开启这个开关，需要申请悬浮窗权限才能生效。
         * 直接使用 XPopup.requestOverlayPermission()即可申请
         * @param enableShowWhenAppBackground
         * @return
         */
        public Builder enableShowWhenAppBackground(boolean enableShowWhenAppBackground) {
            this.popupInfo.enableShowWhenAppBackground = enableShowWhenAppBackground;
            return this;
        }

        /**
         * 是否开启三阶拖拽效果，想高德地图上面的弹窗那样可以拖拽的效果
         *
         * @param isThreeDrag
         * @return
         */
        public Builder isThreeDrag(boolean isThreeDrag) {
            this.popupInfo.isThreeDrag = isThreeDrag;
            return this;
        }

        /**
         * 是否在弹窗消失后就立即释放资源，杜绝内存泄漏，仅仅适用于弹窗对象只用一次的场景，默认为false。
         * 如果你的弹窗对象需是复用的，千万不要开启这个设置；如果开启将会抛出异常
         *
         * @param isDestroyOnDismiss
         * @return
         */
        public Builder isDestroyOnDismiss(boolean isDestroyOnDismiss) {
            this.popupInfo.isDestroyOnDismiss = isDestroyOnDismiss;
            return this;
        }

        /**
         * 设置圆角，对所有内置弹窗有效
         *
         * @param borderRadius
         * @return
         */
        public Builder borderRadius(float borderRadius) {
            this.popupInfo.borderRadius = borderRadius;
            return this;
        }

        /**
         * 是否以屏幕中心进行定位，默认是false，为false时根据Material范式进行定位，主要影响Attach系列弹窗
         * Material范式下是：
         *      弹窗优先显示在目标下方，下方距离不够才显示在上方
         * 以屏幕中心进行定位：
         *      目标在屏幕上半方弹窗显示在目标下面，目标在屏幕下半方则弹窗显示在目标上面
         *
         * @param positionByWindowCenter
         * @return
         */
        public Builder positionByWindowCenter(boolean positionByWindowCenter) {
            this.popupInfo.positionByWindowCenter = positionByWindowCenter;
            return this;
        }

        /**
         * XPopup的弹窗默认是Dialog实现，该方法设置为true则切换为View实现，两者区别如下：
         * 1. Dialog实现，独立Window渲染，性能是View实现的2倍以上，但部分与输入法交互效果无法做到，
         *    比如根据输入进行联想搜索的场景，因为输入法也是一个Dialog，Android中无法实现2个Dialog同时获取焦点，
         *    而设置为View模式即可轻松实现；
         *    但是Dialog实现有个缺陷是弹窗内部无法使用Fragment，这是Android的限制；
         *    Dialog的层级高，会覆盖View层
         * 2. View实现本质是把弹窗挂载到Activity的decorView上面，由于还是View，所以很多与输入法的交互都能实现；
         *    View实现内部完全可以使用Fragment；
         *    缺点是和Activity相同渲染线程，性能比Dialog低
         *
         * @param viewMode 是否是View实现，默认是false
         * @return
         */
        public Builder isViewMode(boolean viewMode) {
            this.popupInfo.isViewMode = viewMode;
            return this;
        }

        /**
         * 半透明阴影的颜色
         * @param shadowBgColor
         * @return
         */
        public Builder shadowBgColor(int shadowBgColor) {
            this.popupInfo.shadowBgColor = shadowBgColor;
            return this;
        }

        /**
         * 动画时长
         * @param animationDuration
         * @return
         */
        public Builder animationDuration(int animationDuration) {
            this.popupInfo.animationDuration = animationDuration;
            return this;
        }

        /**
         * 是否保持屏幕常亮，默认false
         * @param keepScreenOn
         * @return
         */
        public Builder keepScreenOn(boolean keepScreenOn) {
            this.popupInfo.keepScreenOn = keepScreenOn;
            return this;
        }

        /**
         * 开启dismissOnTouchOutside(true)时，即使触摸在指定View时也不消失；
         * 该方法可调用多次，每次可添加一个Rect区域
         * @param view 触摸View
         * @return
         */
        public Builder notDismissWhenTouchInView(View view) {
            if(this.popupInfo.notDismissWhenTouchInArea==null){
                this.popupInfo.notDismissWhenTouchInArea = new ArrayList<>();
            }
            this.popupInfo.notDismissWhenTouchInArea.add(XPopupUtils.getViewRect(view));
            return this;
        }

        /**
         * 默认情况下XPopup监视Activity的生命周期，对于Fragment(或其他任意拥有Lifecycle的组件)实现的UI，可以传入Fragment
         * 的Lifecycle，从而实现在Fragment销毁时弹窗也自动销毁，无需手动调用dismiss()和destroy()
         * @param lifecycle 自定义UI的生命周期
         * @return
         */
        public Builder customHostLifecycle(Lifecycle lifecycle) {
            this.popupInfo.hostLifecycle = lifecycle;
            return this;
        }

        /**
         * 设置弹窗显示和隐藏的回调监听
         *
         * @param xPopupCallback
         * @return
         */
        public Builder setPopupCallback(XPopupCallback xPopupCallback) {
            this.popupInfo.xPopupCallback = xPopupCallback;
            return this;
        }

        /****************************************** 便捷方法 ****************************************/
        /**
         * 显示确认和取消对话框
         *
         * @param title           对话框标题，传空串会隐藏标题
         * @param content         对话框内容
         * @param cancelBtnText   取消按钮的文字内容
         * @param confirmBtnText  确认按钮的文字内容
         * @param confirmListener 点击确认的监听器
         * @param cancelListener  点击取消的监听器
         * @param isHideCancel    是否隐藏取消按钮
         * @param bindLayoutId    自定义的布局Id，没有则传0；要求自定义布局中必须包含的TextView以及id有：tv_title，tv_content，tv_cancel，tv_confirm
         * @return
         */
        public ConfirmPopupView asConfirm(CharSequence title, CharSequence content, CharSequence cancelBtnText, CharSequence confirmBtnText, OnConfirmListener confirmListener, OnCancelListener cancelListener, boolean isHideCancel,
                                          int bindLayoutId) {
            ConfirmPopupView popupView = new ConfirmPopupView(this.context, bindLayoutId);
            popupView.setTitleContent(title, content, null);
            popupView.setCancelText(cancelBtnText);
            popupView.setConfirmText(confirmBtnText);
            popupView.setListener(confirmListener, cancelListener);
            popupView.isHideCancel = isHideCancel;
            popupView.popupInfo = this.popupInfo;
            return popupView;
        }

        public ConfirmPopupView asConfirm(CharSequence title, CharSequence content, CharSequence cancelBtnText, CharSequence confirmBtnText, OnConfirmListener confirmListener, OnCancelListener cancelListener, boolean isHideCancel) {
            return asConfirm(title, content, cancelBtnText, confirmBtnText, confirmListener, cancelListener, isHideCancel, 0);
        }

        public ConfirmPopupView asConfirm(CharSequence title, CharSequence content, OnConfirmListener confirmListener, OnCancelListener cancelListener) {
            return asConfirm(title, content, null, null, confirmListener, cancelListener, false, 0);
        }

        public ConfirmPopupView asConfirm(CharSequence title, CharSequence content, OnConfirmListener confirmListener) {
            return asConfirm(title, content, null, null, confirmListener, null, false, 0);
        }

        /**
         * 显示带有输入框，确认和取消对话框
         *
         * @param title           对话框标题，传空串会隐藏标题
         * @param content         对话框内容,，传空串会隐藏
         * @param inputContent    输入框文字内容，会覆盖hint
         * @param hint            输入框默认文字
         * @param confirmListener 点击确认的监听器
         * @param cancelListener  点击取消的监听器
         * @param bindLayoutId   自定义布局的id，没有传0。 要求布局中必须包含的TextView以及id有：tv_title，tv_content，tv_cancel，tv_confirm
         * @return
         */
        public InputConfirmPopupView asInputConfirm(CharSequence title, CharSequence content, CharSequence inputContent, CharSequence hint, OnInputConfirmListener confirmListener, OnCancelListener cancelListener, int bindLayoutId) {
            InputConfirmPopupView popupView = new InputConfirmPopupView(this.context, bindLayoutId);
            popupView.setTitleContent(title, content, hint);
            popupView.inputContent = inputContent;
            popupView.setListener(confirmListener, cancelListener);
            popupView.popupInfo = this.popupInfo;
            return popupView;
        }

        public InputConfirmPopupView asInputConfirm(CharSequence title, CharSequence content, CharSequence inputContent, CharSequence hint, OnInputConfirmListener confirmListener) {
            return asInputConfirm(title, content, inputContent, hint, confirmListener, null, 0);
        }

        public InputConfirmPopupView asInputConfirm(CharSequence title, CharSequence content, CharSequence hint, OnInputConfirmListener confirmListener) {
            return asInputConfirm(title, content, null, hint, confirmListener, null, 0);
        }

        public InputConfirmPopupView asInputConfirm(CharSequence title, CharSequence content, OnInputConfirmListener confirmListener) {
            return asInputConfirm(title, content, null, null, confirmListener, null, 0);
        }

        /**
         * 显示在中间的列表Popup
         *
         * @param title            标题，可以不传，不传则不显示
         * @param data             显示的文本数据
         * @param iconIds          图标的id数组，可以没有
         * @param selectListener   选中条目的监听器
         * @param bindLayoutId     自定义布局的id 要求layoutId中必须有一个id为recyclerView的RecyclerView，如果你需要显示标题，则必须有一个id为tv_title的TextView
         * @param bindItemLayoutId 自定义列表的item布局 条目的布局id，要求布局中必须有id为iv_image的ImageView，和id为tv_text的TextView
         * @return
         */
        public CenterListPopupView asCenterList(CharSequence title, String[] data, int[] iconIds, int checkedPosition, OnSelectListener selectListener, int bindLayoutId,
                                                int bindItemLayoutId) {
            CenterListPopupView popupView = new CenterListPopupView(this.context, bindLayoutId, bindItemLayoutId)
                    .setStringData(title, data, iconIds)
                    .setCheckedPosition(checkedPosition)
                    .setOnSelectListener(selectListener);
            popupView.popupInfo = this.popupInfo;
            return popupView;
        }

        public CenterListPopupView asCenterList(CharSequence title, String[] data, int[] iconIds, int checkedPosition, OnSelectListener selectListener) {
            return asCenterList(title, data, iconIds, checkedPosition, selectListener, 0, 0);
        }

        public CenterListPopupView asCenterList(CharSequence title, String[] data, OnSelectListener selectListener) {
            return asCenterList(title, data, null, -1, selectListener);
        }

        public CenterListPopupView asCenterList(CharSequence title, String[] data, int[] iconIds, OnSelectListener selectListener) {
            return asCenterList(title, data, iconIds, -1, selectListener);
        }

        /**
         * 显示在中间加载的弹窗
         *
         * @param title        加载中的文字
         * @param bindLayoutId 自定义布局id， 如果要显示标题，则要求必须有id为tv_title的TextView，否则无任何要求；不需要则传0
         * @param style        进度条样式，Spinner：菊花式   ProgressBar：圆圈
         * @return
         */
        public LoadingPopupView asLoading(CharSequence title, int bindLayoutId, LoadingPopupView.Style style) {
            LoadingPopupView popupView = new LoadingPopupView(this.context, bindLayoutId)
                    .setTitle(title)
                    .setStyle(style);
            popupView.popupInfo = this.popupInfo;
            return popupView;
        }

        public LoadingPopupView asLoading(CharSequence title) {
            return asLoading(title, 0, LoadingPopupView.Style.Spinner);
        }

        public LoadingPopupView asLoading(CharSequence title, LoadingPopupView.Style style) {
            return asLoading(title, 0, style);
        }

        public LoadingPopupView asLoading() {
            return asLoading(null);
        }

        /**
         * 显示在底部的列表Popup
         *
         * @param title           标题，可以不传，不传则不显示
         * @param data             显示的文本数据
         * @param iconIds          图标的id数组，可以没有
         * @param checkedPosition  选中的位置，传-1为不选中
         * @param selectListener   选中条目的监听器
         * @param bindLayoutId     自定义布局的id  要求layoutId中必须有一个id为recyclerView的RecyclerView，如果你需要显示标题，则必须有一个id为tv_title的TextView
         * @param bindItemLayoutId 自定义列表的item布局  条目的布局id，要求布局中必须有id为iv_image的ImageView，和id为tv_text的TextView
         * @return
         */
        public BottomListPopupView asBottomList(CharSequence title, String[] data, int[] iconIds, int checkedPosition, OnSelectListener selectListener, int bindLayoutId,
                                                int bindItemLayoutId) {
            BottomListPopupView popupView = new BottomListPopupView(this.context, bindLayoutId, bindItemLayoutId)
                    .setStringData(title, data, iconIds)
                    .setCheckedPosition(checkedPosition)
                    .setOnSelectListener(selectListener);
            popupView.popupInfo = this.popupInfo;
            return popupView;
        }

        public BottomListPopupView asBottomList(CharSequence title, String[] data, int[] iconIds, int checkedPosition, OnSelectListener selectListener) {
            return asBottomList(title, data, iconIds, checkedPosition, selectListener, 0, 0);
        }

        public BottomListPopupView asBottomList(CharSequence title, String[] data, OnSelectListener selectListener) {
            return asBottomList(title, data, null, -1, selectListener);
        }

        public BottomListPopupView asBottomList(CharSequence title, String[] data, int[] iconIds, OnSelectListener selectListener) {
            return asBottomList(title, data, iconIds, -1, selectListener);
        }


        /**
         * 显示依附于某View的列表，必须调用atView()方法，指定依附的View
         *
         * @param data             显示的文本数据
         * @param iconIds          图标的id数组，可以为null
         * @param selectListener   选中条目的监听器
         * @param bindLayoutId     自定义布局的id  要求layoutId中必须有一个id为recyclerView的RecyclerView
         * @param bindItemLayoutId 自定义列表的item布局  条目的布局id，要求布局中必须有id为iv_image的ImageView，和id为tv_text的TextView
         * @param contentGravity   列表的居中位置。默认是居中
         * @return
         */
        public AttachListPopupView asAttachList(String[] data, int[] iconIds, OnSelectListener selectListener, int bindLayoutId,
                                                int bindItemLayoutId, int contentGravity) {
            AttachListPopupView popupView = new AttachListPopupView(this.context, bindLayoutId, bindItemLayoutId)
                    .setStringData(data, iconIds)
                    .setContentGravity(contentGravity)
                    .setOnSelectListener(selectListener);
            popupView.popupInfo = this.popupInfo;
            return popupView;
        }

        public AttachListPopupView asAttachList(String[] data, int[] iconIds, OnSelectListener selectListener, int bindLayoutId,
                                                int bindItemLayoutId) {
            return asAttachList(data, iconIds, selectListener, bindLayoutId, bindItemLayoutId, Gravity.CENTER);
        }

        public AttachListPopupView asAttachList(String[] data, int[] iconIds, OnSelectListener selectListener) {
            return asAttachList(data, iconIds, selectListener, 0, 0, Gravity.CENTER);
        }

        /**
         * 大图浏览类型弹窗，单张图片使用场景
         *
         * @param srcView 源View，就是你点击的那个ImageView，弹窗消失的时候需回到该位置。如果实在没有这个View，可以传空，但是动画变的非常僵硬，适用于在Webview点击场景
         * @return
         */
        public ImageViewerPopupView asImageViewer(ImageView srcView, Object url, XPopupImageLoader imageLoader) {
            ImageViewerPopupView popupView = new ImageViewerPopupView(this.context)
                    .setSingleSrcView(srcView, url)
                    .setXPopupImageLoader(imageLoader);
            popupView.popupInfo = this.popupInfo;
            return popupView;
        }

        /**
         * 大图浏览类型弹窗，单张图片使用场景
         *
         * @param srcView           源View，就是你点击的那个ImageView，弹窗消失的时候需回到该位置。如果实在没有这个View，可以传空，但是动画变的非常僵硬，适用于在Webview点击场景
         * @param url               资源id，url或者文件路径
         * @param isInfinite        是否需要无限滚动，默认为false
         * @param placeholderColor  占位View的填充色，默认为-1
         * @param placeholderStroke 占位View的边框色，默认为-1
         * @param placeholderRadius 占位View的圆角大小，默认为-1
         * @param isShowSaveBtn     是否显示保存按钮，默认显示
         * @param bgColor           背景颜色
         * @param longPressListener 当图片长按的时候执行
         * @return
         */
        public ImageViewerPopupView asImageViewer(ImageView srcView, Object url, boolean isInfinite, int placeholderColor, int placeholderStroke, int placeholderRadius,
                                                  boolean isShowSaveBtn, int bgColor, XPopupImageLoader imageLoader, OnImageViewerLongPressListener longPressListener) {
            ImageViewerPopupView popupView = new ImageViewerPopupView(this.context)
                    .setSingleSrcView(srcView, url)
                    .isInfinite(isInfinite)
                    .setPlaceholderColor(placeholderColor)
                    .setPlaceholderStrokeColor(placeholderStroke)
                    .setPlaceholderRadius(placeholderRadius)
                    .isShowSaveButton(isShowSaveBtn)
                    .setBgColor(bgColor)
                    .setXPopupImageLoader(imageLoader)
                    .setLongPressListener(longPressListener);
            popupView.popupInfo = this.popupInfo;
            return popupView;
        }

        /**
         * 大图浏览类型弹窗，多张图片使用场景
         *
         * @param srcView               源View，就是你点击的那个ImageView，弹窗消失的时候需回到该位置。如果实在没有这个View，可以传空，但是动画变的非常僵硬，适用于在Webview点击场景
         * @param currentPosition       指定显示图片的位置
         * @param urls                  图片url集合
         * @param srcViewUpdateListener 当滑动ViewPager切换图片后，需要更新srcView，此时会执行该回调，你需要调用updateSrcView方法。
         * @return
         */
        public ImageViewerPopupView asImageViewer(ImageView srcView, int currentPosition, List<Object> urls,
                                                  OnSrcViewUpdateListener srcViewUpdateListener, XPopupImageLoader imageLoader) {
            return asImageViewer(srcView, currentPosition, urls, false, true, -1, -1, -1, true,
                    Color.rgb(32, 36, 46),srcViewUpdateListener, imageLoader, null);
        }

        /**
         * 大图浏览类型弹窗，多张图片使用场景
         *
         * @param srcView               源View，就是你点击的那个ImageView，弹窗消失的时候需回到该位置。如果实在没有这个View，可以传空，但是动画变的非常僵硬，适用于在Webview点击场景
         * @param currentPosition       指定显示图片的位置
         * @param urls                  图片url集合
         * @param isInfinite            是否需要无限滚动，默认为false
         * @param isShowPlaceHolder     是否显示默认的占位View，默认为false
         * @param placeholderColor      占位View的填充色，默认为-1
         * @param placeholderStroke     占位View的边框色，默认为-1
         * @param placeholderRadius     占位View的圆角大小，默认为-1
         * @param isShowSaveBtn         是否显示保存按钮，默认显示
         * @param srcViewUpdateListener 当滑动ViewPager切换图片后，需要更新srcView，此时会执行该回调，你需要调用updateSrcView方法。
         * @param longPressListener     当图片长按的时候执行
         * @return
         */
        public ImageViewerPopupView asImageViewer(ImageView srcView, int currentPosition, List<Object> urls,
                                                  boolean isInfinite, boolean isShowPlaceHolder,
                                                  int placeholderColor, int placeholderStroke, int placeholderRadius, boolean isShowSaveBtn,
                                                  int bgColor,OnSrcViewUpdateListener srcViewUpdateListener, XPopupImageLoader imageLoader,
                                                  OnImageViewerLongPressListener longPressListener) {
            ImageViewerPopupView popupView = new ImageViewerPopupView(this.context)
                    .setSrcView(srcView, currentPosition)
                    .setImageUrls(urls)
                    .isInfinite(isInfinite)
                    .isShowPlaceholder(isShowPlaceHolder)
                    .setPlaceholderColor(placeholderColor)
                    .setPlaceholderStrokeColor(placeholderStroke)
                    .setPlaceholderRadius(placeholderRadius)
                    .isShowSaveButton(isShowSaveBtn)
                    .setBgColor(bgColor)
                    .setSrcViewUpdateListener(srcViewUpdateListener)
                    .setXPopupImageLoader(imageLoader)
                    .setLongPressListener(longPressListener);
            popupView.popupInfo = this.popupInfo;
            return popupView;
        }

        public BasePopupView asCustom(BasePopupView popupView) {
            popupView.popupInfo = this.popupInfo;
            return popupView;
        }

    }

    /**
     * 跳转申请悬浮窗权限界面
     *
     * @param context
     * @param callback
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void requestOverlayPermission(Context context, XPermission.SimpleCallback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            XPermission.create(context).requestDrawOverlays(callback);
        } else {
            callback.onGranted();
        }
    }
}
