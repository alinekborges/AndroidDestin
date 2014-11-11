package com.android.semanadeeletronica.destincompleto.util;//package com.miligrama.alineborges.miligrama.util;

import android.app.Activity;
import android.content.Intent;


import com.android.semanadeeletronica.destincompleto.R;

import java.util.HashMap;

/**
 * Created by Aline Borges on 8/26/2014.
 */
public class Navigation {

    private static HashMap<Screen, Object> hashMap = new HashMap<Screen, Object>();

    public static enum Animation {
        GO, BACK
    }

    public static void navigate(Activity context, Screen screen, Animation animation) {
        context.startActivity(new Intent(context, screen.getActivityClass()));
        animate(context, animation);
    }

    public static void navigate(Activity context, Screen screen, Animation animation, Object parameter) {

        hashMap.put(screen, parameter);
        navigate(context, screen, animation);

    }

    public static Object getParameter(Screen screen) {
        Object parameter =  hashMap.get(screen);
        hashMap.put(screen, null);
        return parameter;
    }

    public static void animate(Activity activity, Animation animation) {
        if (animation == Animation.GO) {
            activity.overridePendingTransition (R.anim.open_next, R.anim.close_main);
        } else {
            activity.overridePendingTransition (R.anim.open_main, R.anim.close_next);
            activity.finish();
        }
    }

}
