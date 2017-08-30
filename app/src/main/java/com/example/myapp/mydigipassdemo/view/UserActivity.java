package com.example.myapp.mydigipassdemo.view;

import android.arch.lifecycle.LifecycleActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.myapp.mydigipassdemo.R;
import com.example.myapp.mydigipassdemo.model.User;
import com.example.myapp.mydigipassdemo.task.TokenTask;
import com.example.myapp.mydigipassdemo.task.UserDataTask;
import com.example.myapp.mydigipassdemo.task.UserNavigator;
import com.example.myapp.mydigipassdemo.util.ActivityUtil;
import com.example.myapp.mydigipassdemo.viewmodel.UserViewModel;
import com.vasco.mydigipass.sdk.MDPMobile;
import com.vasco.mydigipass.sdk.MDPResponse;
import com.vasco.mydigipass.sdk.OnMDPAuthenticationListener;

import java.util.HashMap;
import java.util.Map;

import static com.example.myapp.mydigipassdemo.BuildConfig.MDP_CLIENT_ID;

public class UserActivity extends LifecycleActivity implements UserNavigator, OnMDPAuthenticationListener, TokenTask.TokenListener, UserDataTask.UserDataListener {
    private MDPMobile mydigipass;
    private UserViewModel viewModel;
    private UserFragment viewFragment;
    private static final String USER_VIEWMODEL_TAG = "USER_VIEWMODEL_TAG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        setupMydigipass();

        viewModel = findOrCreateViewModel();
        viewFragment = findOrCreateViewFragment();

        viewModel.setUserNavigator(this);;
        viewFragment.setViewActivity(this);
        viewFragment.setViewModel(viewModel);
    }

    @Override
    protected void onDestroy() {
        viewModel.onActivityDestroyed();
        super.onDestroy();
    }

    @Override
    public void authenticate() {
        Map<String, String> passthroughParameters = new HashMap<>();
        passthroughParameters.put("new_user", "yes");
        mydigipass.authenticate("xyzabc1234567", "profile", passthroughParameters);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Handles an incoming intent triggered by the MYDIGIPASS app.
        mydigipass.handleResult(requestCode, resultCode, data);
    }

    @Override
    public void onMDPAuthenticationSuccess(MDPResponse response) {
        new TokenTask(response.getRedirectUri(), response.getAuthorizationCode(), this).execute();
    }

    @Override
    public void onMDPAuthenticationFail(MDPResponse response) {

    }

    @Override
    public void onTokenSuccess(@Nullable String accessToken) {
        new UserDataTask(accessToken, this).execute();
    }

    @Override
    public void onTokenError() {

    }

    @Override
    public void onUserDataSuccess(@Nullable User user) {
        viewModel.setFirstName(user.getFirstName());
        viewModel.setLastName(user.getLastName());
    }

    @Override
    public void onUserDataError() {

    }

    private void setupMydigipass() {
        // Initialize the MYDIGIPASS SDK.
        mydigipass = new MDPMobile(this);

        // Configure your MYDIGIPASS client ID.
        mydigipass.setClientId(MDP_CLIENT_ID);

        // Configure your MYDIGIPASS redirect URI.
        mydigipass.setRedirectUri("myapp://mydigipass-login");

        // Handles an incoming intent triggered by the MYDIGIPASS website.
        mydigipass.webFlow();

        // Sets the MYDIGIPASS authentication listener to enable authentication callbacks
        mydigipass.setMDPAuthenticationListener(this);
    }

    private UserViewModel findOrCreateViewModel() {
        // In a configuration change we might have a ViewModel present.
        // It's retained using the Fragment Manager.
        ViewModelHolder<UserViewModel> retainedViewModel = (ViewModelHolder<UserViewModel>) getSupportFragmentManager().findFragmentByTag(USER_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewModel() != null) {
            return retainedViewModel.getViewModel();
        } else {
            UserViewModel vModel = new UserViewModel(getApplicationContext());
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), ViewModelHolder.createContainer(viewModel), USER_VIEWMODEL_TAG);
            return vModel;
        }
    }

    private UserFragment findOrCreateViewFragment() {
        UserFragment vFragment = (UserFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (vFragment == null) {
            vFragment = new UserFragment();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), vFragment, R.id.contentFrame);
        }

        return vFragment;
    }
}
