package com.okhttplib.builder;


import com.okhttplib.okhttp.OkHttpUtils;
import com.okhttplib.request.OtherRequest;
import com.okhttplib.request.RequestCall;

public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
