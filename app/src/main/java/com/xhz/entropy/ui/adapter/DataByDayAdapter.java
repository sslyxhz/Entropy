package com.xhz.entropy.ui.adapter;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.xhz.entropy.R;
import com.xhz.entropy.data.bean.GankTypeData;
import com.xhz.entropy.ui.widget.RatioImageView;
import com.xhz.entropy.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xh.zeng on 2017/1/3.
 */

public class DataByDayAdapter extends RecyclerView.Adapter<DataByDayAdapter.ViewHolder> {
    private List<GankTypeData> mListData;
    private Context mContext;
    private DataByDayAdapter.IClickItem mIClickItem;
    private static ColorFilter mColorFilter;

    public DataByDayAdapter(Context context) {
        mContext = context;
        mListData = new ArrayList<>();

        float[] array = new float[]{
                1, 0, 0, 0, -70,
                0, 1, 0, 0, -70,
                0, 0, 1, 0, -70,
                0, 0, 0, 1, 0,
        };
        mColorFilter = new ColorMatrixColorFilter(new ColorMatrix(array));
    }

    public void setIClickItem(DataByDayAdapter.IClickItem IClickItem) {
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
    public DataByDayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.index_item, null);
        DataByDayAdapter.ViewHolder holder = new DataByDayAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final DataByDayAdapter.ViewHolder holder, final int position) {
        GankTypeData entity = mListData.get(position);

        Glide.with(mContext)
                .load(entity.getUrl())
                .into(holder.mIvIndexPhoto)
                .getSize(new SizeReadyCallback() {
                    @Override
                    public void onSizeReady(int width, int height) {
                        holder.mIvIndexPhoto.setColorFilter(mColorFilter);
                    }
                });
        holder.mTvTime.setText(DateUtil.toDate(entity.getPublishedAt()));
        if (mIClickItem != null) {
            holder.mIvIndexPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIClickItem.onClickPhoto(position, holder.mIvIndexPhoto, holder.mTvTime);
                }
            });
        }
    }

    public interface IClickItem {
        void onClickPhoto(int position, View view, View textView);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_index_photo)
        RatioImageView mIvIndexPhoto;
        @BindView(R.id.tv_time)
        TextView mTvTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            mIvIndexPhoto.setOriginalSize(50, 50);
        }
    }

}
