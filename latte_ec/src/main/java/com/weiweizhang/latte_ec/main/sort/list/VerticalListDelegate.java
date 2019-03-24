package com.weiweizhang.latte_ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.weiweizhang.latte_core.delegates.LatteDelegate;
import com.weiweizhang.latte_core.net.RestClient;
import com.weiweizhang.latte_core.net.callback.ISuccess;
import com.weiweizhang.latte_core.ui.recycler.MultipleItemEntity;
import com.weiweizhang.latte_ec.R;
import com.weiweizhang.latte_ec.R2;
import com.weiweizhang.latte_ec.main.sort.SortDelegate;

import java.util.List;

import butterknife.BindView;

public class VerticalListDelegate extends LatteDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;
    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        //屏蔽动画
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder().url("sort_list_data.json")
        .loader(getContext())
        .success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                final List<MultipleItemEntity> data =
                        new VerticalListDataConverter().setJsonData(response).convert();
                final SortDelegate delegate = getParentDelegate();
                final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                mRecyclerView.setAdapter(adapter);
            }
        })
        .build()
        .get();
    }
}
