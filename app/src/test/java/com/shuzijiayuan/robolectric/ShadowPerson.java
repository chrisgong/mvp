package com.shuzijiayuan.robolectric;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * Created by gc on 2016/11/8.
 */
@Implements(Person.class)
public class ShadowPerson {

    @Implementation
    public String getName() {
        return "gongchao";
    }
}
