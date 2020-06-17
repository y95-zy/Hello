package com.example.hello.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hello.R;
import com.example.hello.dbManage.PlanItem;
import com.example.hello.dbManage.PlandbManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifyActivity extends AppCompatActivity {


    TextView noteName;
    private PlandbManager dbManager;
    private String id;
    EditText content;
    TextView note_time;
    String curtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);
        content=(EditText) findViewById(R.id.note_content);//记录的内容
        noteName=(TextView) findViewById(R.id.page_name);//标题的名称
        note_time=(TextView)findViewById(R.id.tv_time);//保存记录的时间
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date=new Date(System.currentTimeMillis());
        curtime = simpleDateFormat.format(date);
        initData();


    }

    public void initData(){
        dbManager=new PlandbManager(this);
        noteName.setText("添加记录");
        Intent intent=getIntent();
        if(intent!=null){
            id=intent.getStringExtra("id");
            if(id!=null){
                noteName.setText("修改记录");
                content.setText(intent.getStringExtra("curplan"));
                note_time.setText(intent.getStringExtra("curtime"));
                note_time.setVisibility(View.VISIBLE);
            }
        }
    }

    public void Click(View btn){
        dbManager=new PlandbManager(this);
        Intent intent=getIntent();
        intent.putExtra("curplan",content.getText().toString().trim());
        intent.putExtra("curtime",curtime);

        PlanItem noteContent = new PlanItem(content.getText().toString().trim());
        noteContent.setPlanTime(curtime);

        if(id!=null){

            //修改记录的功能
            dbManager.update(id,noteContent.getplanItem(),noteContent.getPlanTime());
            Toast.makeText(ModifyActivity.this, "修改成功!", Toast.LENGTH_LONG).show();
            setResult(2,intent);
            Log.i("TAG", "Click: ");
            finish();
        }else{
            //添加记录的功能
            dbManager.add(noteContent.getplanItem(),noteContent.getPlanTime());
            Toast.makeText(ModifyActivity.this, "添加成功!", Toast.LENGTH_LONG).show();
            setResult(2,intent);
            Log.i("TAG", "Click:  添加记录= " + content.getText().toString().trim() + curtime + id);

            finish();

        }
    }
}

