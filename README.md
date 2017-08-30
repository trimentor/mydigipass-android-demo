# mydigipass-android-demo
Project to demonstrate integration of the MYDIGIPASS SDK on Android

## Usage

Log in to [https://developer.mydigipass.com/](https://developer.mydigipass.com/) and create a new application.

Open the app's `build.gradle` file and fill in the following fields:

    buildConfigField("String", "MDP_CLIENT_ID", '""')
    buildConfigField("String", "MDP_CLIENT_SECRET", '""')

Update your application on the developer site by setting the `bundle identifier` and `redirect URI` fields:

    bundle identifier: com.example.myapp.mydigipassdemo
    redirect URI: myapp://mydigipass-login

Build the APK and start testing!

**NOTE:** *When reading the documentation on the developer site carefully you'll notice that a web server ("Your server") should be involved as well.
I skipped this step! This example is just a way to quickly test or demonstrate the app2app flow and display some user data on successful authentication.
Please follow the instructions as described on the developer site when integrating with MYDIGIPASS!*