package com.example.hello.dbManage;

//创建与业务相关的操作类
  //与业务相关的操作如新增，修改，删除汇率数据等操作


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlandbManager {
    private DBHelper dbHelper;
    private String TBNAME;

    public PlandbManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }

    //添加功能
    public void add(String planItem,String PlanTime){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CURTIME", PlanTime);
        values.put("CURPLAN", planItem);

        db.insert(TBNAME, null, values);
        db.close();
    }

    public void addAll(List<PlanItem> list){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (PlanItem item : list) {
            ContentValues values = new ContentValues();
            values.put("CURTIME", item.getPlanTime());
            values.put("CURPLAN", item.getplanItem());

            db.insert(TBNAME, null, values);
        }
        db.close();
    }


    //查询功能
    public List<PlanItem> listAll(){
        List<PlanItem> planList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if(cursor!=null){
            planList = new ArrayList<PlanItem>();
            while(cursor.moveToNext()){
                PlanItem item = new PlanItem();
                item.setId(cursor.getString(cursor.getColumnIndex("ID")));
                item.setPlanTime(cursor.getString(cursor.getColumnIndex("CURTIME")));
                item.setplanItem(cursor.getString(cursor.getColumnIndex("CURPLAN")));

                planList.add(item);
            }
            cursor.close();
        }
        db.close();
        return planList;
    }

    //删除功能
    public void delete(String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();
    }



    //修改功能
    public void update(String id,String planItem,String PlanTime){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CURTIME", PlanTime);
        values.put("CURPLAN", planItem);


        db.update(TBNAME, values, "ID=?", new String[]{id});
        db.close();
    }

}
