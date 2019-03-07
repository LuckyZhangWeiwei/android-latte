package com.weiweizhang.fastec.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.weiweizhang.fastec.R;
import com.weiweizhang.latte_core.delegates.LatteDelegate;
import com.weiweizhang.latte_core.net.RestClient;
import com.weiweizhang.latte_core.net.callback.IError;
import com.weiweizhang.latte_core.net.callback.IFailure;
import com.weiweizhang.latte_core.net.callback.ISuccess;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder().url("index_data.json")
        .success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
            }
        })
        .failure(new IFailure() {
            @Override
            public void onFailure(String Message) {
                Log.d("XXXX", Message);
                Toast.makeText(getContext(), "onFailure:"+Message, Toast.LENGTH_LONG).show();
            }
        })
        .error(new IError() {
            @Override
            public void onError(int code, String msg) {
                Toast.makeText(getContext(), code+""+"_"+msg, Toast.LENGTH_LONG).show();
            }
        })
        .build()
        .get();
    }
}
