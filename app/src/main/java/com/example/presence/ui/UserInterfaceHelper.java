package com.example.presence.ui;

import android.view.Window;
import android.view.WindowManager;

public class UserInterfaceHelper implements UserInterfaceService {

    @Override
    public void changeStatusBarColor(int color, Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
    }
}
