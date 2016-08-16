package com.sugengwin.multimatics.myshoppingmall.api;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by Multimatics on 21/07/2016.
 */
public class BaseApi implements BaseApiMethod{
    public String BASE_URL = "http://103.236.201.121/myshoppingmall/index.php/api";
    public String GET_ALL_PRODUCT = BASE_URL + "/products/all";
    public String GET_DETAIL_PRODUCT = BASE_URL + "/products/detail";
    public String POST_REGISTER = BASE_URL + "/user/register";
    public String POST_LOGIN = BASE_URL + "/user/login";

    public AsyncHttpClient getHttpClient() {
        AsyncHttpClient httpClient = new AsyncHttpClient();
        return httpClient;
    }

    @Override
    public void callApi() {

    }

    @Override
    public void cancelRequest() {

    }

}
