package com.sugengwin.multimatics.myshoppingmall;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private AppPreference appPreference;
    private DelayAsync mDelayAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        appPreference = new AppPreference(SplashScreenActivity.this);
        mDelayAsync = new DelayAsync();
        mDelayAsync.execute();
    }

    @Override
    protected void onDestroy() {
        mDelayAsync.cancel(true);
        super.onDestroy();
    }

    class DelayAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = null;
            if (appPreference.getUserId().equals("")) {
                intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            } else {
                intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
            }

            if (intent != null) {
                startActivity(intent);
            }
            finish();
        }
    }
}
