package com.baway.xianger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * 类的描述：
 * 时间：  2017/8/24.19:59
 * 姓名：chenlong
 */
public class MyAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> implements View.OnClickListener {
    Context context;
    List<Data.DataBean> list;
    OnItemClickListener listener;



    public MyAdapter(Context context, List<Data.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        view.setOnClickListener(this);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(XRecyclerView.ViewHolder holder, int position) {
        ViewHolder ho = (ViewHolder) holder;
        Glide.with(context).load(list.get(position).getUserImg()).into(ho.images);
        ho.name.setText(list.get(position).getTitle());
        ho.ages.setText(""+list.get(position).getUserAge());
        ho.work.setText(list.get(position).getOccupation());
        ho.conts.setText(list.get(position).getIntroduction());
        ho.itemView.setTag(position);
        Animation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(5000);
        ((ViewHolder) holder).images.setAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends XRecyclerView.ViewHolder {
        ImageView images;
        TextView name,ages, work, conts;

        public ViewHolder(View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            ages = itemView.findViewById(R.id.age);
            work = itemView.findViewById(R.id.job);
            conts = itemView.findViewById(R.id.cont);
        }
    }
    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.OnItemClickListener(view, (Integer) view.getTag());
        }
    }
    public interface OnItemClickListener{
        void OnItemClickListener(View view,int position);
    }



}
