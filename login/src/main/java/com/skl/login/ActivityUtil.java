package com.skl.login;

import com.skl.arouter.ARouter;
import com.skl.arouter.IRouter;

public class ActivityUtil implements IRouter {

    @Override
    public void putActivity() {
        ARouter.getInstance().putActivity("login/login", LoginActivity.class);
    }
}
