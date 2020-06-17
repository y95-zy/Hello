package com.example.hello.dbManage;

//创建数据库访问对象
//创建类，继承于SQLiteOpenHelper，用于创建，更新数据库，获取数据库连接

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "myPlan.db";
    public static final String TB_NAME = "plan_list";




//    db.execSQL("CREATE TABLE "+TB_NAME+"(ID" + " INTEGER PRIMARY KEY AUTOINCREMENT,"+NOTEPAD_CONTENT+" text, "+DBUtils.NOTEPAD_TIME+" text)");



    //创建构造方法
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }
    public DBHelper(Context context) {
        super(context,DB_NAME,null,VERSION);
    }


    //实现父类方法
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建一个表
        sqLiteDatabase.execSQL("CREATE TABLE "+TB_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, CURPLAN TEXT, CURTIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
