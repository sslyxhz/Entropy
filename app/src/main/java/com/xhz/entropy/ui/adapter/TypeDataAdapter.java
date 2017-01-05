package com.xhz.entropy.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xhz.entropy.R;
import com.xhz.entropy.data.bean.GankTypeData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 分类数据适配器
 * Created by xh.zeng on 2017/1/5.
 */

public class TypeDataAdapter extends RecyclerView.Adapter<TypeDataAdapter.ViewHolder> {
    public static final String TAG = TypeDataAdapter.class.getSimpleName();
    private List<GankTypeData> mListData;
    private Context mContext;
    private TypeDataAdapter.IClickItem mIClickItem;

    public TypeDataAdapter(Context context) {
        mContext = context;
        mListData = new ArrayList<>();
    }

    public void setIClickItem(TypeDataAdapter.IClickItem IClickItem) {
        mIClickItem = IClickItem;
    }

    public void setDataSource(List<GankTypeData> data) {
        mListData.clear();
        mListData.addAll(data);
        notifyDataSetChanged();
    }

    public void appendToDataSource(List<GankTypeData> data) {
        mListData.addAll(data);
        notifyDataSetChanged();
    }

    public GankTypeData getGankData(int position) {
        return mListData.get(position);
    }

    @Override
    public TypeDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_data, null);
        return new TypeDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TypeDataAdapter.ViewHolder holder, final int position) {
        GankTypeData entity = mListData.get(position);

        holder.mTvTitle.setText(">>>" + entity.getCreatedAt());
        if (mIClickItem != null) {
            holder.mTvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("TypeDataAdapter", "onClick..");

                }
            });
        }
    }

    public interface IClickItem {
        void onClickItem(int position, View view, View textView);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_type_data) TextView mTvTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
