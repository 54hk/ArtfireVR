package com.artfire.ninedraw.artfire_code.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.artfire.ninedraw.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 打赏的View
 */
public class RewardView implements AdapterView.OnItemClickListener,View.OnClickListener{
    Context context;
    Dialog dialog;
    String crid;
    ImageView userIcon;
    TextView tvUsername;
    TextView tvUsetId;
    GridView gvMonth;
    TextView tvRewardBtn;
    LinearLayout ll;
    private int money; //当前选择的金额
    RoundImageView user_icon;
    LinearLayout llBg;

    private static PopupWindow rewardViewPopup;

    String teacherName;
    String teacherUmid;
    String icon;
    View parent;

    List<String> num;
    RewardAdapter rewardAdapter;
    int selected = 0;
    RelativeLayout rlRewardClose;

    public RewardView(final Context context, View parent, String teacherName, String teacherUmid, String icon) {
        this.context = context;
        this.teacherName = teacherName;
        this.teacherUmid = teacherUmid;
        this.icon = icon;
        this.parent = parent;
        num = new ArrayList<>();
        num.add("1");
        num.add("2");
        num.add("5");
        num.add("10");
        num.add("20");
        num.add("50");
        View view = LayoutInflater.from(context).inflate(R.layout.reward_view, null);
        userIcon = (ImageView) view.findViewById(R.id.user_icon);
        tvUsername = (TextView) view.findViewById(R.id.tv_username);
        tvUsetId = (TextView) view.findViewById(R.id.tv_uset_id);
        gvMonth = (GridView) view.findViewById(R.id.gv_month);
        tvRewardBtn = (TextView) view.findViewById(R.id.tv_reward_btn);
        rlRewardClose = (RelativeLayout) view.findViewById(R.id.rl_reward_close);
        ll = (LinearLayout) view.findViewById(R.id.ll);
        llBg = (LinearLayout) view.findViewById(R.id.ll_bg);
        view.findViewById(R.id.rl_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        llBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rewardViewPopup != null && rewardViewPopup.isShowing()) {
                    rewardViewPopup.dismiss();
                }
            }
        });
        if (!TextUtils.isEmpty(icon)) {
            GlideUtil.loadRoundPortraits(icon, userIcon);
        }
        if (!TextUtils.isEmpty(teacherName)) {
            tvUsername.setText(teacherName);
        }
        if (!TextUtils.isEmpty(teacherUmid)) {
            tvUsetId.setText("艺伙ID:" + teacherUmid);
        }
        rewardAdapter = new RewardAdapter();
        gvMonth.setAdapter(rewardAdapter);
        gvMonth.setOnItemClickListener(this);
        gvMonth.setSelector(new ColorDrawable(Color.TRANSPARENT));
        rewardViewPopup = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        tvRewardBtn.setOnClickListener(this);
        rlRewardClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rewardViewPopup != null && rewardViewPopup.isShowing()) {
                    rewardViewPopup.dismiss();
                }
            }
        });
    }


    public void show() {
        if (rewardViewPopup != null && !rewardViewPopup.isShowing()) {
            rewardViewPopup.showAtLocation(parent, Gravity.CENTER, 0, 0);
        }
    }


    public void close() {
        if (rewardViewPopup != null && rewardViewPopup.isShowing()) {
            rewardViewPopup.dismiss();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selected = position;
        rewardAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_reward_btn:
                if (mOnRewardItemClickListener!=null){
                    mOnRewardItemClickListener.OnRewardItemClick(num.get(selected));
                }


                break;

        }
    }
    private OnRewardItemClickListener mOnRewardItemClickListener;
    public interface OnRewardItemClickListener {
        void OnRewardItemClick(String num);
    }
    public void setOnRewardItemClickListener(OnRewardItemClickListener mOnRewardItemClickListener) {
        this.mOnRewardItemClickListener = mOnRewardItemClickListener;
    }

    private class RewardAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return num.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_reward_grid, parent, false);
                holder = new ViewHolder();
                holder.tvReward = (TextView) convertView.findViewById(R.id.tv_reward);
                holder.rlItem = (RelativeLayout) convertView.findViewById(R.id.rl_item);
                holder.tvMoney=(TextView) convertView.findViewById(R.id.tv_money);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvReward.setText(num.get(position));
            if (selected == position) {
                holder.rlItem.setBackgroundResource(R.drawable.reward_focus);
                holder.tvReward.setTextColor(context.getResources().getColor(R.color.text_f46123));
                holder.tvMoney.setTextColor(context.getResources().getColor(R.color.text_f46123));
            } else {
                holder.rlItem.setBackgroundResource(R.drawable.month_unfocus);
                holder.tvReward.setTextColor(context.getResources().getColor(R.color.text_999));
                holder.tvMoney.setTextColor(context.getResources().getColor(R.color.text_999));
            }
            return convertView;
        }
    }

    class ViewHolder {
        TextView tvReward;
        RelativeLayout rlItem;
        TextView tvMoney;
    }
}