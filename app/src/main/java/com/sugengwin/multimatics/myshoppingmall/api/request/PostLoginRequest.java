package com.sugengwin.multimatics.myshoppingmall.api.request;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sugengwin.multimatics.myshoppingmall.api.BaseApi;
import com.sugengwin.multimatics.myshoppingmall.api.response.User;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Multimatics on 21/07/2016.
 */
public class PostLoginRequest extends BaseApi {
    OnPostLoginRequestListener onPostLoginRequestListener;
    RequestParams postRequestParams;
    AsyncHttpClient client;

    public PostLoginRequest() {
        client = getHttpClient();
    }

    public OnPostLoginRequestListener getOnPostLoginRequestListener() {
        return onPostLoginRequestListener;
    }

    public void setOnPostLoginRequestListener(OnPostLoginRequestListener onPostLoginRequestListener) {
        this.onPostLoginRequestListener = onPostLoginRequestListener;
    }

    public RequestParams getPostRequestParams() {
        return postRequestParams;
    }

    public void setPostRequestParams(RequestParams postRequestParams) {
        this.postRequestParams = postRequestParams;
    }

    private User getUser(String response) {
        User mUser = null;
        try {
            JSONObject jsonObject = new JSONObject(response);
            mUser = new User();
            if (jsonObject.getString("status").equals("200")) {
                JSONObject userObj = jsonObject.optJSONObject("user");
                mUser.setUserId(userObj.optString("user_id"));
                mUser.setUsername(userObj.optString("username"));
                mUser.setEmail(userObj.optString("email"));
                mUser.setStatus(jsonObject.optString("status"));
                mUser.setMessage("Login Success");
            } else {
                mUser.setStatus(jsonObject.optString("status"));
                mUser.setMessage(jsonObject.optString("message"));
            }

        } catch (Exception e) {
            return null;
        }
        return mUser;
    }

    @Override
    public void callApi() {
        super.callApi();
        client.post(POST_LOGIN, getPostRequestParams(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                User user = getUser(response);
                if (user != null) {
                    if (user.getStatus().equals("200")) {
                        getOnPostLoginRequestListener().onPostLoginSuccess(user);
                    } else {
                        getOnPostLoginRequestListener().onPostLoginFailure(user.getMessage());
                    }
                } else {
                    getOnPostLoginRequestListener().onPostLoginFailure("Invalid Response");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                getOnPostLoginRequestListener().onPostLoginFailure("Could not connect to server");
            }
        });
    }

    @Override
    public void cancelRequest() {
        super.cancelRequest();
        if (client != null) {
            client.cancelAllRequests(true);
        }
    }

    public interface OnPostLoginRequestListener {
        void onPostLoginSuccess(User user);

        void onPostLoginFailure(String errorMessage);
    }

}
