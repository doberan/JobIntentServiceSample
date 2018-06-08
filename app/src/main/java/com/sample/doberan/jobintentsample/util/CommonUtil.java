package com.sample.doberan.jobintentsample.util;

import android.util.Log;

import java.util.regex.Pattern;

public class CommonUtil {
    private static final boolean DEBUG = (android.os.Build.TYPE.equals("user")) ? false : true;

    public static void log(String val) {

        if (DEBUG) {
            Log.v(getTag(), val);
        }
    }

    public static void errorLog(String val) {
        Log.e(getTag(), val);
    }

    private static String getTag() {
        final StackTraceElement trace = Thread.currentThread().getStackTrace()[4];
        final String cla = trace.getClassName();
        Pattern pattern = Pattern.compile("[\\.]+");
        final String[] splitedStr = pattern.split(cla);
        final String simpleClass = splitedStr[splitedStr.length - 1];

        final String mthd = trace.getMethodName();
        final int line = trace.getLineNumber();
        final String tag = simpleClass + "#" + mthd + ":" + line;

        return tag;
    }

    public static void stackTrace(String msg){
        Log.d(getTag(), msg, new Throwable());
    }
}
