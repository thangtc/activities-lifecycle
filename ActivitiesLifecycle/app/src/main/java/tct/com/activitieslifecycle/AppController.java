package tct.com.activitieslifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by Thang Truong on 9/17/15.
 */
public class AppController extends Application {

    private AppStatus appStatus;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());
    }

    public boolean isForeground() {
        return appStatus != null && appStatus.ordinal() > AppStatus.BACKGROUND.ordinal();
    }

    /**
     * This class is used to manage all activities lifecycle in application.
     */
    private class MyActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {

        private int numRunningActivity;

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(Activity activity) {
            if (++numRunningActivity >= 1) {
                appStatus = AppStatus.FOREGROUND;
                // Implement your code here if you want do something only in foreground status.
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
            if (--numRunningActivity == 0) {
                appStatus = AppStatus.BACKGROUND;
                // Stop or cancel something in your app, when app in background.
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
        }
    }

}
