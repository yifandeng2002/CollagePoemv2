package com.lxj.xpopup.core;

import android.content.Context;
import android.graphics.Rect;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.animator.ScrollScaleAnimator;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.enums.PopupPosition;
import com.lxj.xpopup.util.XPopupUtils;

/**
 * Description: 水平方向的依附于某个View或者某个点的弹窗，可以轻松实现微信朋友圈点赞的弹窗效果。
 * 支持通过popupPosition()方法手动指定想要出现在目标的左边还是右边，但是对Top和Bottom则不生效。
 * Create by lxj, at 2019/3/13
 */
public class HorizontalAttachPopupView extends AttachPopupView {
    public HorizontalAttachPopupView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initPopupContent() {
        super.initPopupContent();
        defaultOffsetY = popupInfo.offsetY;
        defaultOffsetX = popupInfo.offsetX == 0 ? XPopupUtils.dp2px(getContext(), 2) : popupInfo.offsetX;
    }

    float translationX = 0, translationY = 0;
    /**
     * 执行附着逻辑
     */
    @Override
    public void doAttach() {
        if(popupInfo==null)return;
        final boolean isRTL = XPopupUtils.isLayoutRtl(getContext());
        int w = getPopupContentView().getMeasuredWidth();
        int h = getPopupContentView().getMeasuredHeight();
        //0. 判断是依附于某个点还是某个View
        if (popupInfo.touchPoint != null) {
            if(XPopup.longClickPoint!=null) popupInfo.touchPoint = XPopup.longClickPoint;
            // 依附于指定点
            popupInfo.touchPoint.x -= getActivityContentLeft();
            isShowLeft = popupInfo.touchPoint.x > XPopupUtils.getAppWidth(getContext()) / 2f;
            //限制最大宽高
            ViewGroup.LayoutParams params = getPopupContentView().getLayoutParams();
            int maxWidth = 0;
            if(isRTL){
                maxWidth = (int) (isShowLeft ? (popupInfo.touchPoint.x - overflow) : (XPopupUtils.getAppWidth(getContext()) - popupInfo.touchPoint.x - overflow));
            }else {
                maxWidth = (int) (isShowLeft ? (popupInfo.touchPoint.x - overflow) : (XPopupUtils.getAppWidth(getContext()) - popupInfo.touchPoint.x - overflow));
            }
            if (getPopupContentView().getMeasuredWidth() > maxWidth) {
                params.width = Math.max(maxWidth, getPopupWidth());
            }
            getPopupContentView().setLayoutParams(params);
            getPopupContentView().post(new Runnable() {
                @Override
                public void run() {
                    if(isRTL){
                        translationX = isShowLeft ?  -(XPopupUtils.getAppWidth(getContext())-popupInfo.touchPoint.x+defaultOffsetX)
                                : -(XPopupUtils.getAppWidth(getContext())-popupInfo.touchPoint.x-getPopupContentView().getMeasuredWidth()-defaultOffsetX);
                    }else {
                        translationX = isShowLeftToTarget() ? (popupInfo.touchPoint.x - w - defaultOffsetX) : (popupInfo.touchPoint.x + defaultOffsetX);
                    }
                    translationY = popupInfo.touchPoint.y - h * .5f + defaultOffsetY;
                    getPopupContentView().setTranslationX(translationX);
                    getPopupContentView().setTranslationY(translationY);
                    initAndStartAnimation();
                }
            });
        } else {
            // 依附于指定View
            //1. 获取atView在屏幕上的位置
            Rect rect = popupInfo.getAtViewRect();
            rect.left -= getActivityContentLeft();
            rect.right -= getActivityContentLeft();
            int centerX = (rect.left + rect.right) / 2;
            isShowLeft = centerX > XPopupUtils.getAppWidth(getContext()) / 2;
            //限制最大宽高
            ViewGroup.LayoutParams params = getPopupContentView().getLayoutParams();
            int maxWidth = 0;
            if(isRTL){
                maxWidth = isShowLeft ? (rect.left - overflow) : (XPopupUtils.getAppWidth(getContext()) - rect.right - overflow);
            }else {
                maxWidth = isShowLeft ? (rect.left - overflow) : (XPopupUtils.getAppWidth(getContext()) - rect.right - overflow);
            }
            if (getPopupContentView().getMeasuredWidth() > maxWidth) {
                params.width = Math.max(maxWidth, getPopupWidth());
            }
            getPopupContentView().setLayoutParams(params);
            getPopupContentView().post(new Runnable() {
                @Override
                public void run() {
                    if(isRTL){
                        translationX = isShowLeft ?  -(XPopupUtils.getAppWidth(getContext())-rect.left + defaultOffsetX)
                                : -(XPopupUtils.getAppWidth(getContext())-rect.right-getPopupContentView().getMeasuredWidth()-defaultOffsetX);
                    }else {
                        translationX = isShowLeftToTarget() ? (rect.left - w - defaultOffsetX) : (rect.right + defaultOffsetX);
                    }
                    translationY = rect.top + (rect.height()-h)/2f + defaultOffsetY;
                    getPopupContentView().setTranslationX(translationX);
                    getPopupContentView().setTranslationY(translationY);
                    initAndStartAnimation();
                }
            });
        }

    }

    private boolean isShowLeftToTarget() {
        return (isShowLeft || popupInfo.popupPosition == PopupPosition.Left)
                && popupInfo.popupPosition != PopupPosition.Right;
    }

    @Override
    protected PopupAnimator getPopupAnimator() {
        ScrollScaleAnimator animator;
        if (isShowLeftToTarget()) {
            animator = new ScrollScaleAnimator(getPopupContentView(), getAnimationDuration(), PopupAnimation.ScrollAlphaFromRight);
        } else {
            animator = new ScrollScaleAnimator(getPopupContentView(), getAnimationDuration(), PopupAnimation.ScrollAlphaFromLeft);
        }
        return animator;
    }
}
