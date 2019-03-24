package com.weiweizhang.latte_ec.main.sort;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.weiweizhang.latte_core.delegates.bottom.BottomItemDelegate;
import com.weiweizhang.latte_ec.R;
import com.weiweizhang.latte_ec.main.sort.content.ContentDelegate;
import com.weiweizhang.latte_ec.main.sort.list.VerticalListDelegate;

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container, listDelegate);
        loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
