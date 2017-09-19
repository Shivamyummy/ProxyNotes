package com.pageupp.videoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Shivam Singh on 16-09-2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;
    String[] urllist;
    String []videotitle;
    String []videodurations;
    String []des;



    public RecyclerViewAdapter(Context context,String[] videotitle,String []des,String []videodurations,String []urllist){

        this.videotitle = videotitle;
        this.des=des;
        this.videodurations=videodurations;
        this.urllist=urllist;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title,description,duration,url;

        public ViewHolder(View v){

            super(v);

             title = (TextView) v.findViewById(R.id.title);
             description = (TextView) v.findViewById(R.id.descrption);
            duration = (TextView) v.findViewById(R.id.durations);
             url = (TextView) v.findViewById(R.id.url);
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        view1 = LayoutInflater.from(context).inflate(R.layout.listview,parent,false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

       holder.title.setText(videotitle[position]);
        holder.description.setText(des[position]);
       holder.duration.setText(videodurations[position]);
        holder.url.setText(urllist[position]);

    }

    @Override
    public int getItemCount(){

        return urllist.length;
    }

}
