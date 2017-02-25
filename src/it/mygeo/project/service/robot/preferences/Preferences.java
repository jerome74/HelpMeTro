package it.mygeo.project.service.robot.preferences;



import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.robot.util.Default;

import java.util.Locale;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;

public class Preferences {
    private final Context context;
    private final SharedPreferences preferences;

    public Preferences(Context context) {
        this.context = context.getApplicationContext();
        this.preferences =  PreferenceManager.getDefaultSharedPreferences(this.context);
    }

    public enum Keys {
        ENABLE_AUTO_BACKUP("enable_auto_sync"),
        INCOMING_TIMEOUT_SECONDS("auto_backup_incoming_schedule"),
        REGULAR_TIMEOUT_SECONDS ("auto_backup_schedule"),
        MAX_ITEMS_PER_SYNC("max_items_per_sync"),
        MAX_ITEMS_PER_RESTORE ("max_items_per_restore"),
        CALLLOG_SYNC_CALENDAR ("backup_calllog_sync_calendar"),
        CALLLOG_SYNC_CALENDAR_ENABLED ("backup_calllog_sync_calendar_enabled"),
        BACKUP_CONTACT_GROUP("backup_contact_group"),
        CONNECTED("connected"),
        WIFI_ONLY("wifi_only"),
        REFERENCE_UID("reference_uid"),
        MAIL_SUBJECT_PREFIX("mail_subject_prefix"),
        RESTORE_STARRED_ONLY("restore_starred_only"),
        MARK_AS_READ("mark_as_read"),
        MARK_AS_READ_ON_RESTORE("mark_as_read_on_restore"),
        THIRD_PARTY_INTEGRATION("third_party_integration"),
        APP_LOG("app_log"),
        APP_LOG_DEBUG("app_log_debug"),
        LAST_VERSION_CODE("last_version_code"),
        CONFIRM_ACTION("confirm_action"),
        NOTIFICATIONS("notifications"),
        FIRST_USE("first_use"),
        IMAP_SETTINGS("imap_settings"),
        DONATE("donate"),
        BACKUP_SETTINGS_SCREEN("auto_backup_settings_screen"),
        ;

        public final String key;
        private Keys(String key) {
            this.key = key;
        }
    }

    public boolean isAppLogEnabled() {
        return preferences.getBoolean(Keys.APP_LOG.key, false);
    }

    public boolean isAppLogDebug() {
        return  isAppLogEnabled() &&
                preferences.getBoolean(Keys.APP_LOG_DEBUG.key, false);
    }

    public String getReferenceUid() {
        return preferences.getString(Keys.REFERENCE_UID.key, null);
    }

    public void setReferenceUid(String referenceUid) {
        preferences.edit()
                .putString(Keys.REFERENCE_UID.key, referenceUid)
                .commit();
    }


    private int getStringAsInt(Keys key, int def) {
        return getStringAsInt(key.key, def);
    }

    private int getStringAsInt(String key, int def) {
        try {
            String s = preferences.getString(key, null);
            if (s == null) return def;

            return Integer.valueOf(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public void setIncomingTimeoutSecs(String incomingTimeoutSecs) 
    {
    	 preferences.edit()
         .putString(Keys.INCOMING_TIMEOUT_SECONDS.key, incomingTimeoutSecs)
         .commit();
    }
    
    public int getIncomingTimeoutSecs() {
        return getStringAsInt(Keys.INCOMING_TIMEOUT_SECONDS, Default.INCOMING_TIMEOUT_SECONDS);
    }

    public int getRegularTimeoutSecs() {
        return getStringAsInt(Keys.REGULAR_TIMEOUT_SECONDS, Default.REGULAR_TIMEOUT_SECONDS);
    }

    public boolean isFirstUse() {
        if (!preferences.contains(Keys.FIRST_USE.key)) {
            preferences.edit().putBoolean(Keys.FIRST_USE.key, false).commit();
            return true;
        } else {
            return false;
        }
    }

    public void setNotificationEnabled(Boolean notification) {
    	preferences.edit().putBoolean(Keys.NOTIFICATIONS.key, notification);
    }
    
    public boolean isNotificationEnabled() {
        return preferences.getBoolean(Keys.NOTIFICATIONS.key, false);
    }

    public void setConfirmAction(Boolean confirmAction) {
    	preferences.edit().putBoolean(Keys.CONFIRM_ACTION.key, confirmAction);
    }
    
    public boolean confirmAction() {
        return preferences.getBoolean(Keys.CONFIRM_ACTION.key, false);
    }

    public String getVersion(boolean code) {
        PackageInfo pInfo;
        try {
            pInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_META_DATA);
            return "" + (code ? pInfo.versionCode : pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(UTIL_GEO.HELPMETRO, "error", e);
            return null;
        }
    }

    @TargetApi(8)
    public boolean isInstalledOnSDCard() {
        PackageInfo pInfo;
        try {
            pInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_META_DATA);

            return (pInfo.applicationInfo.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(UTIL_GEO.HELPMETRO, "error", e);
            return false;
        }
    }

    public boolean shouldShowUpgradeMessage() {
        final String key = "upgrade_message_seen";
        boolean seen = preferences.getBoolean(key, false);
        if (!seen && isOldSmsBackupInstalled()) {
            preferences.edit().putBoolean(key, true).commit();
            return true;
        } else {
            return false;
        }
    }

    public boolean shouldShowAboutDialog() {
        int code;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_META_DATA);
            code = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("ERROR", "error", e);
            code = -1;
        }

        int lastSeenCode = preferences.getInt(Keys.LAST_VERSION_CODE.key, -1);
        if (lastSeenCode < code) {
            preferences.edit().putInt(Keys.LAST_VERSION_CODE.key, code).commit();
            return true;
        } else {
            return false;
        }
    }

    boolean isOldSmsBackupInstalled() {
        try {
            context.getPackageManager().getPackageInfo(
                    "tv.studer.smssync",
                    PackageManager.GET_META_DATA);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    boolean isWhatsAppInstalled() {
        try {
            context.getPackageManager().getPackageInfo(
                    "com.whatsapp",
                    PackageManager.GET_META_DATA);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    <T extends Enum<T>> T getDefaultType(String pref, Class<T> tClazz, T defaultType) {
        try {
            final String s = preferences.getString(pref, null);
            return s == null ? defaultType : Enum.valueOf(tClazz, s.toUpperCase(Locale.ENGLISH));
        } catch (IllegalArgumentException e) {
            Log.e(UTIL_GEO.HELPMETRO, "getDefaultType(" + pref + ")", e);
            return defaultType;
        }
    }
}
