package com.example.hello.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hello.R;
import com.example.hello.adapter.PlanAdapter;
import com.example.hello.dbManage.PlanItem;
import com.example.hello.dbManage.PlandbManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PlanListActivity extends AppCompatActivity {

    private ListView listView;
    private List<PlanItem> listItems;
    private PlanAdapter adapter;
    private PlandbManager dbManager;
    String curtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.bg);
        setContentView(R.layout.activity_modify);
        listView=findViewById(R.id.listview);
        initData();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date=new Date(System.currentTimeMillis());
        curtime = simpleDateFormat.format(date);


    }

    public void add(View btn){
        Intent intent=new Intent(PlanListActivity.this, ModifyActivity.class);
        startActivityForResult(intent,1);
    }

    public void initData(){
        dbManager=new PlandbManager(this);
        showQueryData();

        //短按修改内容
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent, View view, int position, long id ){
                PlanItem planItem = listItems.get(position);
                Intent intent=new Intent(PlanListActivity.this, ModifyActivity.class);
                intent.putExtra("id",planItem.getId());
                intent.putExtra("curplan",planItem.getplanItem());
                intent.putExtra("curtime",planItem.getPlanTime());
                PlanListActivity.this.startActivityForResult(intent,1);

                Log.i("FinalExamActivity", "onItemClick: id= " + planItem.getId());
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog dialog;
                AlertDialog.Builder builder=new AlertDialog.Builder(PlanListActivity.this)
                        .setMessage("是否删除此记录?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PlanItem notepadBean=listItems.get(position);
                                dbManager.delete(notepadBean.getId());
                                listItems.remove(position);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(PlanListActivity.this,"删除成功",Toast.LENGTH_LONG).show();
                            }


                            }
                        )
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog=builder.create();
                dialog.show();
                return true;
            }
        });
    }

    private void showQueryData(){
        if(listItems!=null){
            listItems.clear();
        }
        listItems=dbManager.listAll();
        adapter=new PlanAdapter(this,listItems);
        listView.setAdapter(adapter);
    }

    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==1&&resultCode==2){
            showQueryData();
        }
    }











//    //添加菜单，添加内容
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.plan_menu,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//
//        Intent intent=new Intent(FinalExamActivity.this,PlanActivity.class);
//        startActivityForResult(intent,1);
//
//        return super.onOptionsItemSelected(item);
//    }


}
