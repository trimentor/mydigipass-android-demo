package com.example.myapp.mydigipassdemo.task;

import android.os.AsyncTask;

import com.example.myapp.mydigipassdemo.model.User;
import com.example.myapp.mydigipassdemo.data.MydigipassService;

import java.io.IOException;
import java.util.Map;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.myapp.mydigipassdemo.BuildConfig.MDP_BASE_URL;

public class UserDataTask extends AsyncTask<Void, Void, User> {
    private String accessToken;
    private UserDataListener listener;

    public UserDataTask(String accessToken, UserDataListener listener) {
        this.accessToken = accessToken;
        this.listener = listener;
    }

    @Override
    protected User doInBackground(Void... params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MDP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MydigipassService service = retrofit.create(MydigipassService.class);

        try {
            Response<Map<String, Object>> userDataResponse = service.getUserData("Bearer " + accessToken).execute();
            if (userDataResponse.isSuccessful()) {
                User user = new User(userDataResponse.body().get("first_name").toString(),
                        userDataResponse.body().get("last_name").toString());
                return user;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);

        if (user != null) {
            listener.onUserDataSuccess(user);
        } else {
            listener.onUserDataError();
        }
    }

    public interface UserDataListener {
        public void onUserDataSuccess(User user);

        public void onUserDataError();
    }
}