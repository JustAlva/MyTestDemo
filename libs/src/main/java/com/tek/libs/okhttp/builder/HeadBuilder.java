package com.tek.libs.okhttp.builder;


import com.tek.libs.okhttp.okhttp.OkHttpUtils;
import com.tek.libs.okhttp.request.OtherRequest;
import com.tek.libs.okhttp.request.RequestCall;

public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
