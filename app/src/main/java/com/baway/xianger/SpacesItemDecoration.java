package com.baway.xianger;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * 类的描述：
 * 时间：  2017/8/24.20:34
 * 姓名：chenlong
 */

public class SpacesItemDecoration extends XRecyclerView.ItemDecoration{
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=space;
        outRect.right = space;
        outRect.bottom=space;
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top = space;
        }


    }
}
