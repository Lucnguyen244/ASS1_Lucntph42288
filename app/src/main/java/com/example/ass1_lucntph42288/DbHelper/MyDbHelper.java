package com.example.ass1_lucntph42288.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

    static String name = "db_congviec";
    static int version = 2;

    public MyDbHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tb_user = "CREATE TABLE User (" +
                "    id       INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    username TEXT    NOT NULL," +
                "    email    TEXT    NOT NULL," +
                "    password TEXT    NOT NULL," +
                "    fullname TEXT    NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(tb_user);

        String tb_task = "CREATE TABLE Tasks (" +
                "    id         INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    name       TEXT    NOT NULL," +
                "    content    TEXT    NOT NULL," +
                "    status     INTEGER NOT NULL," +
                "    start_task TEXT    NOT NULL," +
                "    end_task   TEXT    NOT NULL," +
                "    user_id    INTEGER REFERENCES User (id) " +
                "                       NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(tb_task);

        String insert_user = "INSERT INTO User (username, email, password, fullname)" +
                "VALUES('luc', 'luc@gmail.com', '123', 'Nguyen The Luc')";
        sqLiteDatabase.execSQL(insert_user);

        String insert_tasks = "INSERT INTO Tasks(name, content, status, start_task, end_task, user_id)" +
                "VALUES('android 2', 'học', 0, '17/11/2023', '20/11/2023', 1), ('thiết kế đồ họa', 'học', 1, '17/11/2023', '20/11/2023', 2)," +
                " ('quản trị kinh doanh', 'học', -1, '17/11/2023', '20/11/2023', 3), ('ứng dụng phần mềm ', 'content_4', 2, '17/11/2023', '20/11/2023', 4), ('cv5', 'content_5', 2, '17/11/2023', '20/11/2023', 5);";
        sqLiteDatabase.execSQL(insert_tasks);


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
