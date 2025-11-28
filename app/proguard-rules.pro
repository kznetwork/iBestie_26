# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in ${sdk.dir}/tools/proguard/proguard-android.txt

# Keep Vosk models
-keep class org.vosk.** { *; }

# Keep Retrofit interfaces
-keepattributes Signature
-keepattributes *Annotation*
-keep class retrofit2.** { *; }

# Keep model classes
-keep class com.kznetwork.ibestie.model.** { *; }

# Mixpanel
-keep class com.mixpanel.** { *; }

