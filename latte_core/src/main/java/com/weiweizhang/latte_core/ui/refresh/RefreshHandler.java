package com.weiweizhang.latte_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.weiweizhang.latte_core.app.Latte;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        this.REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh() {
        this.REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {

    }
}
