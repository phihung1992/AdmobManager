package com.attlib.adsmgr;

import static com.attlib.adsmgr.Constants.DEFAULT_LOG_ENABLED;
import static com.attlib.adsmgr.Constants.DEFAULT_LOG_TAG;

import android.util.Log;

public abstract class BaseAdsManager {
    public String logTag = DEFAULT_LOG_TAG;
    public boolean logEnabled = DEFAULT_LOG_ENABLED;

    public String getLogTag() {
        return logTag;
    }

    public void setLogTag(String logTag) {
        this.logTag = logTag;
    }

    public boolean isLogEnabled() {
        return logEnabled;
    }

    public void setLogEnabled(boolean logEnabled) {
        this.logEnabled = logEnabled;
    }

    public void logInfo(String message) {
        if (!logEnabled) return;
        Log.i(logTag, message);
    }

    public void logDebug(String message) {
        if (!logEnabled) return;
        Log.d(logTag, message);
    }

    public void logError(String message) {
        if (!logEnabled) return;
        Log.e(logTag, message);
    }

    public void logWarning(String message) {
        if (!logEnabled) return;
        Log.w(logTag, message);
    }
}
