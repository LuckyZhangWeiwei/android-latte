package com.weiweizhang.latte_core.ui.camera;

import android.net.Uri;

import com.weiweizhang.latte_core.delegates.PermissionCheckerDelegate;
import com.weiweizhang.latte_core.util.file.FileUtil;

public class LatteCamera {
    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
