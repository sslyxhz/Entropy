package com.xhz.entropy.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xhz.entropy.R;
import com.xhz.entropy.data.bean.GankTypeData;
import com.xhz.entropy.net.GankService;
import com.xhz.entropy.util.DateUtil;
import com.xhz.entropy.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 分类数据适配器
 * Created by xh.zeng on 2017/1/5.
 */

public class TypeDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case R.layout.item_gank_welfare:
                view = LayoutInflater.from(parent.getContext()).inflate(viewType, null);
                return new WelfareViewHolder(view);
            case R.layout.item_gank_ganhuo:
                view = LayoutInflater.from(parent.getContext()).inflate(viewType, null);
                return new GankViewHolder(view);
            default:
                LogUtil.w("Unknown view type:" + viewType);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        String type = mListData.get(position).getType();
        if(GankService.GankType.福利.name().equals(type)){
            return R.layout.item_gank_welfare;
        } else{
            return R.layout.item_gank_ganhuo;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final GankTypeData data = mListData.get(position);
        if(holder instanceof WelfareViewHolder){
            WelfareViewHolder welfareHolder = (WelfareViewHolder)holder;
            welfareHolder.tvDate.setText(data.getDesc());
            Glide.with(mContext)
                    .load(data.getUrl())
                    .override(600, 200)
                    .centerCrop()
                    .into(welfareHolder.ivWelfare);
        } else{
            GankViewHolder gankHolder = (GankViewHolder) holder;
            gankHolder.tvDesc.setText(data.getDesc());
            gankHolder.tvAuthor.setText(data.getWho());
            gankHolder.tvTime.setText(DateUtil.toDate(data.getPublishedAt()));
            gankHolder.tvTag.setText(data.getType());
            if (mIClickItem != null) {
                gankHolder.ivOption.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtil.w("CreateAt:" + data.getCreatedAt());

                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class WelfareViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_item_gank_date) TextView tvDate;
        @BindView(R.id.iv_item_gank_welfare) ImageView ivWelfare;

        public WelfareViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class GankViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_gank_option) ImageView ivOption;
        @BindView(R.id.tv_item_gank_desc) TextView tvDesc;
        @BindView(R.id.tv_item_gank_author) TextView tvAuthor;
        @BindView(R.id.tv_item_gank_time) TextView tvTime;
        @BindView(R.id.tv_item_gank_tag) TextView tvTag;

        GankViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface IClickItem {
        void onClickItem(int position, View view, View textView);
    }
}
