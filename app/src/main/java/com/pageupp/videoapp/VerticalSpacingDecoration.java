package com.pageupp.videoapp;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Shivam Singh on 16-09-2017.
 */

public class VerticalSpacingDecoration extends RecyclerView.ItemDecoration {

    private int spacing;

    public VerticalSpacingDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = spacing;
    }
}
