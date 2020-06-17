package com.example.hello.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hello.R;
import com.example.hello.dbManage.PlanItem;

import java.util.List;



public class PlanAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;//这个对象用于加载item的布局文件
    private List<PlanItem> list;//list集合是列表中需要显示的集合
    public PlanAdapter(Context context, List<PlanItem>list){
        layoutInflater=LayoutInflater.from(context);
        this.list=list;
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }//获取集合长度

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }//返回集合的内容

    @Override
    public long getItemId(int position) {
        return position;
    }//返回位置信息

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.planitem_layout,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        PlanItem notepadBean=(PlanItem)getItem(position);
        viewHolder.tvNotepadContent.setText(notepadBean.getplanItem());
        viewHolder.tvNotepadTime.setText(notepadBean.getPlanTime());
        return convertView;
    }

    class ViewHolder{
        TextView tvNotepadContent;
        TextView tvNotepadTime;
        public ViewHolder(View view){
            tvNotepadContent=view.findViewById(R.id.item_content);//记录的内容
            tvNotepadTime=view.findViewById(R.id.item_time);//保存记录的时间

        }
    }

}


