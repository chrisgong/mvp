package com.shuzijiayuan.myapplication.robolectric;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.shuzijiayuan.myapplication.BuildConfig;
import com.shuzijiayuan.myapplication.R;
import com.shuzijiayuan.myapplication.login.LoginActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowBitmap;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.util.ActivityController;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ExampleUnitTest {

    @Test
    public void testActivity() {
        LoginActivity activity = Robolectric.buildActivity(LoginActivity.class).create().get();
        assertNotNull(activity);
        assertEquals(activity.getTitle(), "My Application");
    }

    /**
     * 生命周期方法测试
     */
    @Test
    public void testLifecycle() {
        ActivityController<LoginActivity> activityController = Robolectric.buildActivity(LoginActivity.class).create().start();
        Activity activity = activityController.get();
        TextView textview = (TextView) activity.findViewById(R.id.tv_lifecycle_value);
        assertEquals("onCreate", textview.getText().toString());
        activityController.resume();
        assertEquals("onResume", textview.getText().toString());
        activityController.destroy();
        assertEquals("onDestroy", textview.getText().toString());
    }

    /**
     * 条状
     */
    @Test
    public void testStartActivity() {
        LoginActivity activity = Robolectric.buildActivity(LoginActivity.class).create().get();
        activity.findViewById(R.id.tv_lifecycle_value).performClick();
        Intent expectedIntent = new Intent(activity, LoginActivity.class);
        Intent actualIntent = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent, actualIntent);
    }

    /**
     * UI组件状态
     */
    @Test
    public void testViewState() {
        LoginActivity activity = Robolectric.buildActivity(LoginActivity.class).create().get();
        CheckBox checkBox = (CheckBox) activity.findViewById(R.id.checkBox);
        Button button = (Button) activity.findViewById(R.id.btn_inverse);

        checkBox.setChecked(true);
        button.performClick();
        assertTrue(!checkBox.isChecked());
        button.performClick();
        assertTrue(checkBox.isChecked());
    }

    /**
     * Dialog
     */
    @Test
    public void testDialog() {
        LoginActivity activity = Robolectric.buildActivity(LoginActivity.class).create().get();
        Button button = (Button) activity.findViewById(R.id.btn_dialog);
        button.performClick();

        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        assertNotNull(dialog);
    }


    /**
     * Toast
     */
    @Test
    public void testToast() {
        LoginActivity activity = Robolectric.buildActivity(LoginActivity.class).create().get();
        Button button = (Button) activity.findViewById(R.id.btn_toast);
        button.performClick();

        assertEquals(ShadowToast.getTextOfLatestToast(), "toast test");
    }

    /**
     * 资源文件访问
     */
    @Test
    public void testResource() {
        Application application = RuntimeEnvironment.application;
        String appName = application.getString(R.string.app_name);
        String activityTitle = application.getString(R.string.title_activity_login);

        assertEquals(appName, "My Application");
        assertEquals(activityTitle, "Sign in");
    }

    /**
     * Service测试
     */
    @Test
    public void addDataToSharedPreference() {
//        Application application = RuntimeEnvironment.application;
//        RoboSharedPreferences preferences = (RoboSharedPreferences) application.getSharedPreferences("example", Context.MODE_PRIVATE);
//
//        SampleIntentService service = new SampleIntentService();
//        service.onHandleIntent(new Intent());
//
//        assertEquals(preferences.getString("SAMPLE_DATA", ""), "sample data");
    }

    /**
     * BroadcastReceiver的测试
     */
    @Test
    public void testBoradcast() {
        ShadowApplication shadowApplication = ShadowApplication.getInstance();

        String action = "com.geniusmart.loveut.login";
        Intent intent = new Intent(action);
        intent.putExtra("EXTRA_USERNAME", "geniusmart");

        //测试是否注册广播接收者
        assertTrue(shadowApplication.hasReceiverForIntent(intent));

//        //以下测试广播接受者的处理逻辑是否正确
//        MyReceiver myReceiver = new MyReceiver();
//        myReceiver.onReceive(RuntimeEnvironment.application, intent);
//        SharedPreferences preferences = shadowApplication.getSharedPreferences("account", Context.MODE_PRIVATE);
//        assertEquals("geniusmart", preferences.getString("USERNAME", ""));
    }

    @Test
    public void testDefaultShadow() {
        LoginActivity LoginActivity = Robolectric.setupActivity(LoginActivity.class);

        //通过Shadows.shadowOf()可以获取很多Android对象的Shadow对象
        ShadowActivity shadowActivity = Shadows.shadowOf(LoginActivity);
        ShadowApplication shadowApplication = Shadows.shadowOf(RuntimeEnvironment.application);

        Bitmap bitmap = BitmapFactory.decodeFile("Path");
        ShadowBitmap shadowBitmap = Shadows.shadowOf(bitmap);

        assertNull(shadowActivity.getNextStartedActivity());
        assertNull(shadowApplication.getNextStartedActivity());
        assertNotNull(shadowBitmap);

    }
}