package com.weiweizhang.latte_ec.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.weiweizhang.latte_core.app.Latte;
import com.weiweizhang.latte_core.delegates.bottom.BottomItemDelegate;
import com.weiweizhang.latte_core.net.RestClient;
import com.weiweizhang.latte_core.net.callback.ISuccess;
import com.weiweizhang.latte_core.ui.recycler.MultipleItemEntity;
import com.weiweizhang.latte_ec.R;
import com.weiweizhang.latte_ec.R2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess {

    private ShopCartAdapter mAdapter = null;
    //购物车数量标记
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView recyclerView = null;

    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectAll.getTag();
        if(tag == 0) {
            mIconSelectAll.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            mAdapter.notifyDataSetChanged();
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shopcart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mIconSelectAll.setTag(0);
    }

    @Override
    public void onSuccess(String response) {
        final ArrayList<MultipleItemEntity> data = new ShopCartDataConverter().setJsonData(response).convert();
        mAdapter = new ShopCartAdapter(data);
        LinearLayoutManager manager = new LinearLayoutManager(Latte.getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient
                .builder()
                .url("shop_cart_data.json")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }
}
