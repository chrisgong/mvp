package com.shuzijiayuan.robolectric;

import com.shuzijiayuan.myapplication.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.internal.ShadowExtractor;

import static junit.framework.Assert.assertEquals;

/**
 * Created by gc on 2016/11/8.
 */

@RunWith(CustomShadowTestRunner.class)
@Config(constants = BuildConfig.class, shadows = {ShadowPerson.class}, sdk = 21)
public class ShadowTest {

    @Test
    public void testCustomShadow() {
        Person person = new Person("gongchao");
        assertEquals(person.getName(), "gongchao");

        ShadowPerson shadowPerson = (ShadowPerson) ShadowExtractor.extract(person);
        assertEquals("gongchao", shadowPerson.getName());
    }
}
