package com.example.myapp.mydigipassdemo.task;

import android.os.AsyncTask;

import com.example.myapp.mydigipassdemo.data.MydigipassService;

import java.io.IOException;
import java.util.Map;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.myapp.mydigipassdemo.BuildConfig.MDP_BASE_URL;
import static com.example.myapp.mydigipassdemo.BuildConfig.MDP_CLIENT_ID;
import static com.example.myapp.mydigipassdemo.BuildConfig.MDP_CLIENT_SECRET;

public class TokenTask extends AsyncTask<Void, Void, String> {
    private String redirectUri;
    private String authotizationCode;
    private TokenListener listener;

    public TokenTask(String redirectUri, String authorizationCode, TokenListener listener) {
        this.redirectUri = redirectUri;
        this.authotizationCode = authorizationCode;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MDP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MydigipassService service = retrofit.create(MydigipassService.class);

        try {
            Response<Map<String, Object>> tokenResponse = service.token(authotizationCode,
                    MDP_CLIENT_ID,
                    MDP_CLIENT_SECRET,
                    redirectUri,
                    "authorization_code")
                    .execute();

            if (tokenResponse.isSuccessful()) {
                String accessToken = tokenResponse.body().get("access_token").toString();
                return accessToken;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String accessToken) {
        super.onPostExecute(accessToken);

        if (accessToken != null) {
            listener.onTokenSuccess(accessToken);
        } else {
            listener.onTokenError();
        }
    }

    public interface TokenListener {
        public void onTokenSuccess(String accessToken);

        public void onTokenError();
    }
}