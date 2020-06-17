package com.example.hello.dbManage;

//创建实体对象
  //该对象即为一个行数据记录，实体属性与数据表对应，用于映射表中数据

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanItem {
    private String id;
    private String planItem;
    private String planTime;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    Date date=new Date(System.currentTimeMillis());
    String curtime = simpleDateFormat.format(date);

    public PlanItem() {
        this.planItem = "";
        this.planTime = "";
    }

    public PlanItem(String planItem) {
        this.planItem = planItem;
        this.planTime = curtime;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getplanItem(){ return planItem ;}
    public void setplanItem(String planItem){this.planItem = planItem;}

    public String getPlanTime(){ return planTime ;}
    public void setPlanTime(String planTime){this.planTime = planTime;}





}
