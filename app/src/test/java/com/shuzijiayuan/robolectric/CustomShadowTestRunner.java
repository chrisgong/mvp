package com.shuzijiayuan.robolectric;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.internal.bytecode.InstrumentationConfiguration;

/**
 * Created by gc on 2016/11/8.
 */

public class CustomShadowTestRunner extends RobolectricGradleTestRunner {

    public CustomShadowTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    public InstrumentationConfiguration createClassLoaderConfig() {
        InstrumentationConfiguration.Builder builder = InstrumentationConfiguration.newBuilder();

        builder.addInstrumentedClass(Person.class.getName());
        return builder.build();
    }
}
