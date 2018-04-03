package com.lib.utils.floatingactionbutton.utils;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * describe: FloatingActionButton 滚动Behavior
 * creator: keding.zhou
 * date: 2018/2/11 9:26
 */
public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {
    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super();
    }
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private boolean mIsAnimatingOut = false;
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
                        nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0 && !this.mIsAnimatingOut && child.getVisibility() == View.VISIBLE) {

            if (Build.VERSION.SDK_INT >= 14) {
                ViewCompat.animate(child).scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setInterpolator(INTERPOLATOR).withLayer()
                        .setListener(new ViewPropertyAnimatorListener() {
                            public void onAnimationStart(View view) {
                                ScrollAwareFABBehavior.this.mIsAnimatingOut = true;
                            }

                            public void onAnimationCancel(View view) {
                                ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
                            }

                            public void onAnimationEnd(View view) {
                                ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
                                view.setVisibility(View.INVISIBLE);
                            }
                        }).start();

            }
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            child.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= 14) {
                ViewCompat.animate(child).scaleX(1.0F).scaleY(1.0F).alpha(1.0F)
                        .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
                        .start();

            }
        }
    }

}
