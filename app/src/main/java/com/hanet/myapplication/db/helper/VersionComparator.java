package com.hanet.myapplication.db.helper;

import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class VersionComparator implements Comparator<String> {

    private static final String TAG = SQLiteAssetHelper.class.getSimpleName();

    private Pattern pattern = Pattern
            .compile(".*_upgrade_([0-9]+)-([0-9]+).*");

    @Override
    public int compare(String file0, String file1) {
        Matcher m0 = pattern.matcher(file0);
        Matcher m1 = pattern.matcher(file1);

        if (!m0.matches()) {
            Log.w(TAG, "could not parse upgrade script file: " + file0);
            throw new SQLiteAssetException("Invalid upgrade script file");
        }

        if (!m1.matches()) {
            Log.w(TAG, "could not parse upgrade script file: " + file1);
            throw new SQLiteAssetException("Invalid upgrade script file");
        }

        int v0_from = Integer.valueOf(m0.group(1));
        int v1_from = Integer.valueOf(m1.group(1));
        int v0_to = Integer.valueOf(m0.group(2));
        int v1_to = Integer.valueOf(m1.group(2));

        if (v0_from == v1_from) {
            // 'from' versions match for both; check 'to' version next

            if (v0_to == v1_to) {
                return 0;
            }

            return v0_to < v1_to ? -1 : 1;
        }

        return v0_from < v1_from ? -1 : 1;
    }

    @SuppressWarnings("serial")
    public static class SQLiteAssetException extends SQLiteException {

        public SQLiteAssetException() {}

        public SQLiteAssetException(String error) {
            super(error);
        }
    }
}