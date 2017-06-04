package com.surine.recycleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by surine on 2017/5/27.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mStrings;

    public MainAdapter(Context context, List<String> strings) {
        mContext = context;
        mStrings = strings;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取点击位置
                int position = holder.getAdapterPosition();
                Toast.makeText(mContext,"点击了"+mStrings.get(position),Toast.LENGTH_SHORT).show();
            }
        });
         holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

             }
         });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       holder.mTextView.setText(mStrings.get(position));
        holder.mButton.setText("按钮"+mStrings.get(position));
    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        Button mButton;
        LinearLayout mLinearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textView);
            mButton = (Button) itemView.findViewById(R.id.button);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.lin);
        }
    }


    public void addData(int position){
        mStrings.add(position,"ADD"+position);
        notifyItemInserted(position);
    }

    public void removeData(int position){
        mStrings.remove(position);
        notifyItemRemoved(position);
    }
}
