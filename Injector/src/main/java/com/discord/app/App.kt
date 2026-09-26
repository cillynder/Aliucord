package com.discord.app

import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.discord.BuildConfig
import com.discord.models.domain.emoji.ModelEmojiCustom
import com.discord.utilities.analytics.*
import com.discord.utilities.buildutils.BuildUtils
import com.discord.utilities.cache.SharedPreferencesProvider
import com.discord.utilities.debug.DebugPrintableCollection
import com.discord.utilities.error.Error
import com.discord.utilities.fcm.NotificationClient
import com.discord.utilities.images.MGImagesConfig
import com.discord.utilities.lifecycle.ActivityProvider
import com.discord.utilities.lifecycle.ApplicationProvider
import com.discord.utilities.logging.Logger
import com.discord.utilities.logging.LoggingProvider
import com.discord.utilities.persister.PersisterConfig
import com.discord.utilities.rest.RestAPI
import com.discord.utilities.surveys.SurveyUtils
import com.discord.utilities.systemlog.SystemLogUtils
import com.discord.utilities.time.ClockFactory
import com.discord.utilities.view.text.LinkifiedTextView
import com.google.firebase.crashlytics.FirebaseCrashlytics
import d0.z.d.m
import rx.functions.Action3

typealias FirebaseApp = b.i.c.c

@Suppress("UNCHECKED_CAST")
fun App.init() {
    SharedPreferencesProvider.INSTANCE.init(this)
    ApplicationProvider.INSTANCE.init(this)
    ActivityProvider.Companion!!.init(this)
    ClockFactory.INSTANCE.init(this)
    val i = AppLog.a
    m.checkNotNullParameter(this, "application")
    AppLog.b = true
    AppLog.a = 0
    AppLog.c = PreferenceManager.getDefaultSharedPreferences(this)
    val loggingProvider = LoggingProvider.INSTANCE
    val appLog = AppLog.g
    loggingProvider.init(appLog)
    FirebaseApp.e(this)
    if (BuildUtils.INSTANCE.isValidBuildVersionName(BuildConfig.VERSION_NAME)) {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    } else {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false)
        Logger.`w$default`(appLog, "Disable crashlytics logging, likely modified client detected.", null, 2, null)
    }
    SystemLogUtils.INSTANCE.initSystemLogCapture()
    val bundle = packageManager.getApplicationInfo(packageName, 128).metaData
    var string = if (bundle != null) bundle.getString("libdiscord_version") else null
    if (string == null) {
        string = "Unknown"
    }
    appLog.recordBreadcrumb(string, "libdiscord_version")
    DebugPrintableCollection.Companion!!.initialize(string)
    AdjustConfig.INSTANCE.init(this, false)
    val dVar = b.a.e.d.d
    val bVar = `App$b`.j
    m.checkNotNullParameter(this, "application")
    m.checkNotNullParameter(bVar, "onError")
    registerActivityLifecycleCallbacks(b.a.e.b(bVar))
    PersisterConfig.INSTANCE.init(this, ClockFactory.get())
    val dVar2 = b.a.k.g.d.b
    val dVar3 = b.a.k.g.d.a.value as b.a.k.g.d
    val cVar = `App$c`.j
    val dVar4 = `App$d`.j
    val aVar = b.a.k.a.d
    b.a.k.a.a = dVar3
    b.a.k.a.b = cVar
    b.a.k.a.c = dVar4
    RestAPI.Companion!!.init(this)
    NotificationClient.INSTANCE.init(this)
    MGImagesConfig.INSTANCE.init(this)
    Error.init(b.a.d.a(`App$e`(appLog)) as Action3<String, Throwable, Map<String, String>>)
    LinkifiedTextView.Companion!!.init(`App$f`.j)
    ModelEmojiCustom.setCdnUri(BuildConfig.HOST_CDN)
    SurveyUtils.INSTANCE.init(this)
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    // initializeFlipper()
    System.loadLibrary("dsti"); // initializeRLottie()
    if (FirebaseCrashlytics.getInstance().didCrashOnPreviousExecution()) {
        AnalyticsTracker.INSTANCE.appCrashed()
    }
    AnalyticsDeviceResourceUsageMonitor.INSTANCE.start()
    // Observable<R> G = StoreStream.Companion.getExperiments().observeUserExperiment("2022-01_rna_rollout_experiment_validation", true).y(ObservableExtensionsKt$filterNull$1.INSTANCE).G(ObservableExtensionsKt$filterNull$2.INSTANCE);
    // m.checkNotNullExpressionValue(G, "filter { it != null }.map { it!! }");
    // Observable Z = G.Z(1);
    // m.checkNotNullExpressionValue(Z, "StoreStream.getExperimen…erNull()\n        .take(1)");
    // ObservableExtensionsKt.appSubscribe$default(Z, getClass(), (Context) null, (Function1) null, (Function1) null, (Function0) null, (Function0) null, g.j, 62, (Object) null);
    AppLog.i("Application initialized.")
}
