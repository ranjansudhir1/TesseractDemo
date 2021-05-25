package com.tesseract.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tesseract.mylibrary.MyListData;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {
    private List<MyListData> listData;
    private MainListAdapterListener mainListAdapterListener;

    public MainListAdapter(List<MyListData> listData,MainListAdapterListener mainListAdapterListener) {
        this.listData = listData;
        this.mainListAdapterListener = mainListAdapterListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyListData myListData = listData.get(position);
        holder.textView.setText(myListData.getName()
                + "\n" + myListData.getPackages()
                + "\n" + myListData.getVersionCode()
                + "\n" + myListData.getVersionName());
        holder.imageView.setImageDrawable(myListData.getIcon());
        holder.constraintLayout.setOnClickListener(view ->
                Toast.makeText(view.getContext(), "click on item: " + myListData.getName(), Toast.LENGTH_LONG).show());
        holder.constraintLayout.setOnClickListener(view ->mainListAdapterListener.onClickItem(myListData));
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.tvAppListDetails);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.list_item_layout);
        }
    }

    interface MainListAdapterListener {
        void onClickItem(MyListData myListData);
    }
}