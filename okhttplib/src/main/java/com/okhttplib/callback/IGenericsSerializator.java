package com.okhttplib.callback;

public interface IGenericsSerializator {
    <T> T transform(String response, Class<T> classOfT);
}
