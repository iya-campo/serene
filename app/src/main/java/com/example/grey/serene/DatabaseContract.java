package com.example.grey.serene;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static final class Users_Info implements BaseColumns{
        public static final String TABLE_NAME = "user_info";
        public static final String COLUMN_ID = "user_id";
        public static final String COLUMN_NAME = "username";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD="password";
        public static final String COLUMN_NICKNAME= "nickname";
        public static final String COLUMN_AGE="age";
        public static final String COLUMN_ALARM="alarm";
        public static final String COLUMN_NOTIFICATIONS="notifications";
    }

    public static final class Articles implements BaseColumns{
        public static final String TABLE_NAME = "articles";
        public static final String COLUMN_ID = "article_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_CONTENTS= "contents";
    }

    public static final class Journal implements BaseColumns{
        public static final String TABLE_NAME = "journal";
        public static final String COLUMN_ID = "journal_id";
        public static final String COLUMN_USERID="user_id";
        public static final String COLUMN_SLEEPHOURS = "hours_slept";
        public static final String COLUMN_FOODINTAKE = "food_intake";
        public static final String COLUMN_MEDICINALTAKE= "medicinal_intake";
        public static final String COLUMN_DATE="date";
    }

    public static final class Users_Friends implements BaseColumns{
        public static final String TABLE_NAME = "users_friends";
        public static final String COLUMN_ID = "friend_id";
        public static final String COLUMN_USERID = "user_id";
        public static final String COLUMN_NAME = "friend_name";
        public static final String COLUMN_EMAIL= "friend_email";
    }

    public static final class Words implements BaseColumns{
        public static final String TABLE_NAME = "Expenses";
        public static final String COLUMN_ID = "Expenses_Id";
        public static final String COLUMN_WORD = "Expenses_Type";
        public static final String COLUMN_CATEGORY = "Expenses_Subtype";
    }

}
