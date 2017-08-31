package com.example.myapp.mydigipassdemo.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.myapp.mydigipassdemo.BR;
import com.example.myapp.mydigipassdemo.model.User;

public class UserViewModel extends BaseObservable {
    private Context vmContext;
    private User vmUser;

    public UserViewModel(Context context) {
        this.vmContext = context.getApplicationContext();
        this.vmUser = new User();
    }

    public void onActivityDestroyed() {

    }

    public void updateUser(User user) {
        vmUser.setFirstName(user.getFirstName());
        notifyPropertyChanged(BR.firstName);
        vmUser.setLastName(user.getLastName());
        notifyPropertyChanged(BR.lastName);
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
