package com.shuzijiayuan.myapplication.utils;

import android.support.annotation.NonNull;

/**
 * Created by gc on 2016/11/10.
 */

public class TextUtils {

    /**
     * String not null
     * @param str
     * @return
     */
    public static boolean isEmpty(@NonNull CharSequence str){
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
}
