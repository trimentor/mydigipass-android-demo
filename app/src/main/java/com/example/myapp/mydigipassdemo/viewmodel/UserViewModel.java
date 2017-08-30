package com.example.myapp.mydigipassdemo.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.Nullable;

import com.example.myapp.mydigipassdemo.BR;
import com.example.myapp.mydigipassdemo.model.User;
import com.example.myapp.mydigipassdemo.task.UserNavigator;

public class UserViewModel extends BaseObservable {
    private Context vmContext;
    private User vmUser;
    private UserNavigator vmUserNavigator;

    public UserViewModel(Context context) {
        this.vmContext = context.getApplicationContext();
        this.vmUser = new User();
    }

    public void onActivityDestroyed() {

    }

    public void setUserNavigator(UserNavigator userNavigator) {
        vmUserNavigator = userNavigator;
    }

    public void authenticate() {
        vmUserNavigator.authenticate();
    }

    public void setFirstName(@Nullable String firstName) {
        if (firstName != null) {
            vmUser.setFirstName(firstName);
            notifyPropertyChanged(BR.firstName);
        }
    }

    public void setLastName(@Nullable String lastName) {
        if (lastName != null) {
            vmUser.setLastName(lastName);
            notifyPropertyChanged(BR.lastName);
        }
    }

    @Bindable
    public String getFirstName() {
        return vmUser.getFirstName();
    }

    @Bindable
    public String getLastName() {
        return vmUser.getLastName();
    }
}
