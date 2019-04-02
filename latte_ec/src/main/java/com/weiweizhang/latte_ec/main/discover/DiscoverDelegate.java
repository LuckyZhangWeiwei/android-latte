package com.weiweizhang.latte_ec.main.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.weiweizhang.latte_core.delegates.bottom.BottomItemDelegate;
import com.weiweizhang.latte_core.delegates.web.IPageLoadListener;
import com.weiweizhang.latte_core.delegates.web.WebDelegateImpl;
import com.weiweizhang.latte_ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class DiscoverDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
        delegate.setTopDelegate(this.getParentDelegate());
        delegate.setPageLoadListener(new IPageLoadListener() {
            @Override
            public void onLoadStart() {

            }

            @Override
            public void onLoadEnd() {

            }
        });
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container, delegate);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
