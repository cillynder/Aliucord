package com.aliucord.injector

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import dalvik.system.BaseDexClassLoader
import java.io.File
import java.io.FileOutputStream

@Suppress("UNCHECKED_CAST")
class DexPathListWrapper(val instance: Any) {
    private val dexElementsField = instance.javaClass.getDeclaredField("dexElements").apply { this.isAccessible = true }
    val dexElements get() = dexElementsField.get(instance) as Array<*>

    fun addDexPath(dexPath: String, optimizedDirectory: File?) {
        // DexPathList#addDexPath(String dexPath, File optimizedDirectory)
        instance.javaClass.getDeclaredMethod("addDexPath", String::class.java, File::class.java)
            .apply { this.isAccessible = true }
            .invoke(instance, dexPath, optimizedDirectory)
    }
}

/**
 * Adds a dex file/container to the front of the global classloader classpath.
 * This causes any conflicting classes defined in the dex to override everything further down the list.
 *
 * This private api seems to be stable, thanks to Facebook who use it in the Facebook app
 */
@SuppressLint("DiscouragedPrivateApi")
internal fun addDexToClasspath(dexFile: File, classLoader: ClassLoader) {
    Logger.d("Adding ${dexFile.absolutePath} to classpath..")

    // public void addDexPath(String dexPath, File optimizedDirectory) {
    // BaseDexClassLoader#pathList -> DexPathList
    // https://android.googlesource.com/platform/libcore/+/58b4e5dbb06579bec9a8fc892012093b6f4fbe20/dalvik/src/main/java/dalvik/system/BaseDexClassLoader.java#59
    val pathList = BaseDexClassLoader::class.java.getDeclaredField("pathList")
        .apply { isAccessible = true }
        .get(classLoader)
        .let { DexPathListWrapper(it!!) }

    val oldElements = pathList.dexElements.clone()
    pathList.dexElements.reverse()
    pathList.addDexPath(dexFile.absolutePath, null)
    pathList.dexElements.reverse()

    Logger.d("classpath dexes: ${oldElements.size} -> ${pathList.dexElements.size}")
    Logger.d("  - before: ${oldElements.contentToString()}")
    Logger.d("  - after: ${pathList.dexElements.contentToString()}")
}

/**
 * Try to prevent method inlining by deleting the usage profile used by AOT compilation
 * https://source.android.com/devices/tech/dalvik/configure#how_art_works
 */
internal fun pruneArtProfile(ctx: Context) {
    val profile = File("/data/misc/profiles/cur/0/${ctx.packageName}/primary.prof")
    if (profile.exists() && profile.length() > 0) {
        try {
            // Clear file contents
            FileOutputStream(profile).close()
        } catch (e: Throwable) {
            Logger.w("Failed to clear ART usage profile", e)
        }
    }
}

internal fun mainThread(runnable: Runnable) {
    Handler(Looper.getMainLooper()).post(runnable)
}

internal fun Context.showToast(text: String, length: Int = Toast.LENGTH_LONG) {
    if (Looper.getMainLooper() == Looper.myLooper()) {
        Toast.makeText(this, text, length).show()
    } else mainThread {
        Toast.makeText(this, text, length).show()
    }
}
