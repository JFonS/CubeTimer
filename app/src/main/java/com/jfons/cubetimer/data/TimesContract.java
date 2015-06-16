package com.jfons.cubetimer.data;


        import android.content.ContentResolver;
        import android.content.ContentUris;
        import android.net.Uri;
        import android.provider.BaseColumns;

public class TimesContract
{
    public static final String CONTENT_AUTHORITY = "com.jfons.cubetimer";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_TIME = "times";

    public static final class TimeEntry implements BaseColumns
    {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TIME).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TIME;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TIME;

        public static final String TABLE_NAME = "times";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_CUBE = "cube";
        public static final String COLUMN_DATE = "date";

        public static Uri buildLocationUri(long id)
        {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
