package com.weiweizhang.latte_ec.main;

import android.graphics.Color;

import com.weiweizhang.latte_core.delegates.bottom.BaseBottomDelegate;
import com.weiweizhang.latte_core.delegates.bottom.BottomItemDelegate;
import com.weiweizhang.latte_core.delegates.bottom.BottomTabBean;
import com.weiweizhang.latte_core.delegates.bottom.ItemBuilder;
import com.weiweizhang.latte_ec.main.index.DiscoverDelegate;
import com.weiweizhang.latte_ec.main.index.IndexDelegate;
import com.weiweizhang.latte_ec.main.index.PersonalDelegate;
import com.weiweizhang.latte_ec.main.index.ShopCartDelegate;
import com.weiweizhang.latte_ec.main.index.SortDelegate;

import java.util.LinkedHashMap;

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
