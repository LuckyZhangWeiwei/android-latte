package com.weiweizhang.latte_ec.main.personal.settings;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.weiweizhang.latte_core.delegates.LatteDelegate;
import com.weiweizhang.latte_core.util.callback.CallbackManager;
import com.weiweizhang.latte_core.util.callback.CallbackType;
import com.weiweizhang.latte_core.util.callback.IGlobalCallback;
import com.weiweizhang.latte_ec.R;
import com.weiweizhang.latte_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class NameDelegate extends LatteDelegate{

    @BindView(R2.id.et_Name)
    public EditText et_name = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @OnClick(R2.id.btn_name_submit)
    public void updateName(){
        final IGlobalCallback<String> callback = CallbackManager
                .getInstance()
                .getCallback(CallbackType.UPDATE_NAME);
        if (callback != null) {
            callback.executeCallback(et_name.getText().toString());
        }
        getSupportDelegate().pop();
    }
}
