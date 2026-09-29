package com.discord.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.aliucord.injector.*;

import kotlin.io.FilesKt;

public class App extends Application {
    public static final App$a Companion = new App$a();

    public static final boolean access$getIS_LOCAL$cp() { return false; }

    private boolean aliu_Loaded = false;

    @Override
    protected void attachBaseContext(Context base) {
        Log.d("PreInit", "Hey ;)");
        try {
            var externalBaseDir = FilesKt.resolve(Environment.getExternalStorageDirectory(), "Aliucord");
            var smaliDexFile = FilesKt.resolve(externalBaseDir, "smali.dex");
            var externalCustomCoreFile = FilesKt.resolve(externalBaseDir, "Aliucord.zip");
            var internalCustomCoreFile = FilesKt.resolve(base.getCodeCacheDir(), "Aliucord.custom.zip");
            var internalCoreFile = FilesKt.resolve(base.getCodeCacheDir(), "Aliucord.zip");

            Log.d("PreInit", String.format("Preloading smali dex %s to the classpath...", smaliDexFile.getAbsolutePath()));
            UtilsKt.addDexToClasspath(smaliDexFile, base.getClassLoader());

            if (externalCustomCoreFile.exists()) {
                Log.d("PreInit", String.format("Copying core bundle %s to %s", externalCustomCoreFile.getAbsolutePath(), internalCustomCoreFile.getAbsolutePath()));
                FilesKt.copyTo(externalCustomCoreFile, internalCoreFile, true, 8 * 1024);
            }
            if (internalCustomCoreFile.exists()) {
                Log.d("PreInit", String.format("Preloading custom core bundle %s to the classpath...", internalCustomCoreFile.getAbsolutePath()));
                UtilsKt.addDexToClasspath(internalCustomCoreFile, base.getClassLoader());
                aliu_Loaded = true;
            } else if (internalCoreFile.exists()) {
                Log.d("PreInit", String.format("Preloading stock core bundle %s to the classpath...", internalCustomCoreFile.getAbsolutePath()));
                UtilsKt.addDexToClasspath(internalCoreFile, base.getClassLoader());
                aliu_Loaded = true;
            } else {
                Log.d("PreInit", "No core bundle found, Injector will handle it.");
            }
        } catch (Exception e) {
            Log.e("PreInit", "Something went wrong! Injector will try again...", e);
        }
        Log.d("PreInit", "All done!");

        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("PreInit", "Say hello to Injector!");
        // Early init
        InjectorKt.init(this, aliu_Loaded);

        // Pass back to stock onCreate() impl
        AppKt.init(this);
    }
}
