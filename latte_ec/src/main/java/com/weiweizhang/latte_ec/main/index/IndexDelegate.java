package com.weiweizhang.latte_ec.main.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.weiweizhang.latte_core.app.Latte;
import com.weiweizhang.latte_core.delegates.bottom.BottomItemDelegate;
import com.weiweizhang.latte_core.net.RestClient;
import com.weiweizhang.latte_core.net.callback.ISuccess;
import com.weiweizhang.latte_core.ui.recycler.MultipleFields;
import com.weiweizhang.latte_core.ui.recycler.MultipleItemEntity;
import com.weiweizhang.latte_core.ui.refresh.RefreshHandler;
import com.weiweizhang.latte_ec.R;
import com.weiweizhang.latte_ec.R2;
import com.weiweizhang.latte_ec.main.EcBottomDelegate;

import java.util.ArrayList;

import butterknife.BindView;

public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index_data.json");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
//        mRecyclerView.addItemDecoration
//                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
//        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
//        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }
}
