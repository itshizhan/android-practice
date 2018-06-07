package com.itshizhan.recyclerviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class RecyclerViewItemDivider extends RecyclerView.ItemDecoration {
    private final String TAG = "RecyclerViewItemDivider";
    private Drawable mDrawable;
    public RecyclerViewItemDivider(Context context, int resId) {
        this.mDrawable = context.getResources().getDrawable(resId);
    }

    /**
     * @param c      Canvas to draw into
     * @param parent RecyclerView this ItemDecoration is drawing into
     * @param state  The current state of RecyclerView.
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
       // super.onDrawOver(c, parent, state);
        int left = parent.getPaddingLeft();

        Log.i(TAG,parent.getPaddingLeft()+":left");
        Log.i(TAG,parent.getPaddingRight()+":right");
        Log.i(TAG,parent.getWidth()+":getWidth");

        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    /**
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, mDrawable.getIntrinsicWidth());
    }
}
