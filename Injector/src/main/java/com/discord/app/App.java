package com.discord.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.aliucord.injector.UtilsKt;

import kotlin.io.FilesKt;

public class App extends Application {
    public static final App$a Companion = new App$a();

    public static final boolean access$getIS_LOCAL$cp() { return false; }

    @Override
    protected void attachBaseContext(Context base) {
        var externalBaseDir = FilesKt.resolve(Environment.getExternalStorageDirectory(), "Aliucord");
        var smaliDexFile = FilesKt.resolve(externalBaseDir, "smali.dex");

        Log.d("Injector", String.format("Preloading smali dex %s to the classpath...", smaliDexFile.getAbsolutePath()));
        UtilsKt.addDexToClasspath(smaliDexFile, base.getClassLoader());

        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Pass back to stock onCreate() impl
        AppKt.init(this);
    }
}
