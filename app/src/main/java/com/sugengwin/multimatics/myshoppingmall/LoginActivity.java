package com.sugengwin.multimatics.myshoppingmall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.sugengwin.multimatics.myshoppingmall.api.request.PostLoginRequest;
import com.sugengwin.multimatics.myshoppingmall.api.response.User;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, PostLoginRequest.OnPostLoginRequestListener{

    private TextView tvRegister;
    private Button btnLogin;
    private EditText edtUsername, edtPassword;
    private AppPreference appPreference;

    private ProgressDialog progressDialog;
    private PostLoginRequest postLoginRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");

        appPreference = new AppPreference(LoginActivity.this);

        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);

        tvRegister = (TextView)findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(this);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Please wait...");

        postLoginRequest = new PostLoginRequest();
        postLoginRequest.setOnPostLoginRequestListener(this);
    }

    @Override
    protected void onDestroy() {
        postLoginRequest.cancelRequest();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        boolean isLogin = false;
        Intent intent = null;
        switch (v.getId()) {
        case R.id.tv_register:
            intent = new Intent(LoginActivity.this, RegisterActivity.class);
            break;
        case R.id.btn_login:
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                RequestParams mRequestParams = new RequestParams();
                mRequestParams.put("username", username);
                mRequestParams.put("password", password);
                progressDialog.show();
                postLoginRequest.setPostRequestParams(mRequestParams);
                postLoginRequest.callApi();
            }
            break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onPostLoginSuccess(User user) {
        progressDialog.cancel();
        Toast.makeText(LoginActivity.this, user.getMessage(), Toast.LENGTH_SHORT).show();
        //appPreference.setUsername(user.getUsername());
        appPreference.setUserId(user.getUserId());
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPostLoginFailure(String errorMessage) {
        progressDialog.cancel();
        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
