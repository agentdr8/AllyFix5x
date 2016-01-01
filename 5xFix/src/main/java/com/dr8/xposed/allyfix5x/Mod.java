package com.dr8.xposed.allyfix5x;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Surface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.getObjectField;

public class Mod implements IXposedHookLoadPackage {
    private static final String TAG = "5xFix";
    private static boolean DEBUG = true;

    private static void log(String msg) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        String formattedDate = df.format(c.getTime());
        XposedBridge.log("[" + formattedDate + "] " + TAG + ": " + msg);
    }

    @SuppressWarnings("deprecation")
    public static void setCameraDisplayOrientation(Activity activity, int cameraId,
                                                   android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam)
            throws Throwable {
        String targetpkg = "com.ally.MobileBanking";
        if (loadPackageParam.packageName.equals(targetpkg)) {

            findAndHookMethod(targetpkg + ".rdc.RdcActivityCamera", loadPackageParam.classLoader,
                    "onCreate", Bundle.class, new XC_MethodHook() {
                        @Override
                        @SuppressWarnings("deprecation")
                        protected void afterHookedMethod(MethodHookParam mparam) throws Throwable {
                            if (DEBUG) log("hooked Ally deposit activity");

                            Activity a = (Activity) mparam.thisObject;

                            Camera c = (Camera) getObjectField(mparam.thisObject, "c");

                            if (DEBUG) log("fixing camera preview rotation");
                            setCameraDisplayOrientation(a, 0, c);

                            if (c != null) {
                                Camera.Parameters cp = c.getParameters();
                                cp.setRotation(180);
                                c.setParameters(cp);
                            } else {
                                if (DEBUG) log("camera already closed or not open");
                            }
                        }
                    });
        }
    }
}

