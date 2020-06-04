# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /usr/local/Cellar/android-sdk/24.3.3/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# Core SDK
-keepnames class zendesk.core.** { *; }

# Gson
-keep class sun.misc.Unsafe { *; }

# Okio
-dontwarn okio.**

# Retrofit
-dontwarn retrofit2.Platform**

# Dagger
-dontwarn com.google.errorprone.annotations.CanIgnoreReturnValue

# OkHttp3: https://github.com/square/okhttp/blob/master/okhttp/src/main/resources/META-INF/proguard/okhttp3.pro
## JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

## A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

## Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

## OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform

# Support SDK
-keepnames class zendesk.support.** { *; }

# Gson
-keep class sun.misc.Unsafe { *; }

# Okio
-dontwarn okio.**

# Retrofit
-dontwarn retrofit2.Platform**

# java-common
-dontwarn java.awt.Color

# Dagger
-dontwarn com.google.errorprone.annotations.*
#Picasso
-dontwarn com.squareup.okhttp.**

# MenuItems
-keep class android.support.v7.widget.SearchView { *; }
-keep class zendesk.support.request.ViewCellAttachmentMenuItem { *; }