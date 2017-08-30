package com.example.myapp.mydigipassdemo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapp.mydigipassdemo.R;
import com.example.myapp.mydigipassdemo.databinding.UserFragmentBinding;
import com.example.myapp.mydigipassdemo.viewmodel.UserViewModel;

public class UserFragment extends Fragment {

    private UserActivity userActivity;
    private UserViewModel userViewModel;
    private UserFragmentBinding userFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.user_fragment, container, false);
        userFragmentBinding.setViewmodel(userViewModel);

        View root = userFragmentBinding.getRoot();
        return root;
    }

    public void setViewModel(UserViewModel viewModel) {
        this.userViewModel = viewModel;
    }

    public void setViewActivity(UserActivity userActivity) {
        this.userActivity = userActivity;
    }
}
