package com.weiweizhang.latte_core.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.weiweizhang.latte_core.R;
import com.weiweizhang.latte_core.delegates.LatteDelegate;

public abstract class BottomItemDelegate extends LatteDelegate //implements View.OnKeyListener
{
//    private long mExitTime = 0;
//    private static final int EXIT_TIME = 2000;
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        final View rootView = getView();
//        if(rootView != null) {
//            rootView.setFocusableInTouchMode(true);
//            rootView.requestFocus();
//            rootView.setOnKeyListener(this);
//        }
//    }

//    @Override
//    public boolean onKey(View v, int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.ACTION_DOWN && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if (System.currentTimeMillis() -mExitTime > EXIT_TIME) {
//                Toast.makeText(getContext(), "双击退出"+ getString(R.string.app_name), Toast.LENGTH_SHORT).show();
//                mExitTime = System.currentTimeMillis();
//            } else {
//                _mActivity.finish();
//                if(mExitTime != 0) {
//                    mExitTime = 0;
//                }
//            }
//            return true; //消化掉，不需要系统处理
//        }
//        return false;  //需要系统处理
//    }

    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(getContext(), "双击退出"+ getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
