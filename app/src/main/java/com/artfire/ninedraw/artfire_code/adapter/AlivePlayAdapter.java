package com.artfire.ninedraw.artfire_code.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.artfire.ninedraw.R;
import com.artfire.ninedraw.artfire_code.Bean.ALiHostoryBean;
import com.artfire.ninedraw.artfire_code.utils.RoundBackgroundColorSpan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 54hk on 2018/8/12.
 */

public class AlivePlayAdapter extends BaseAdapter {
    private List<ALiHostoryBean.AppendDataBean.ChatInfoBean.DataBeanX> list;
    private Context mContext;
    final int TEACHER = 1;// 老师端
    final int STUDENT = 2;// 学生端
    private int teacherOrStudent = 0; //传来哦的
    private int record = 0; //类型记载

    public AlivePlayAdapter(Context context, int teacherOrStudent
            , List<ALiHostoryBean.AppendDataBean.ChatInfoBean.DataBeanX> list) {
        this.list = list;
        this.mContext = context;
        this.teacherOrStudent = teacherOrStudent;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 9;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case TEACHER: //老师端  直播
                record = TEACHER;
                break;
            case STUDENT://学生端
                record = STUDENT;
                break;
        }
        return record;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        switch (getItemViewType(teacherOrStudent)) {
            case TEACHER: //老师端  直播
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.alive_play_teacher_adapter, null);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                break;
            case STUDENT://学生端
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.alive_play_adapter, null);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                break;
        }


          /*if (list.get(position).getContent() != null) {
            holder.tvMessage.setText(list.get(position).getContent() + "");
        }*/
        // 无用
         /*if (list.get(position).getName() != null) {
            holder.tvName.setText(list.get(position).getName() + ":");
        }*/
        //"role": 1,//1学员；2主持人；3讲师；4管理员；5嘉宾
        SpannableString roleStr = null;
        String role = "";
        if (list.get(position).getRole() == 2
                || list.get(position).getRole() == 3 ||
                list.get(position).getRole() == 4
                || list.get(position).getRole() == 5) {
            switch (list.get(position).getRole()) {

                case 2:
                    //holder.tvStatus.setText(mContext.getString(R.string.host));
                    roleStr = new SpannableString(mContext.getString(R.string.host));
                    role = mContext.getString(R.string.host);
                    break;
                case 3:
                    //holder.tvStatus.setText(mContext.getString(R.string.speaker));
                    roleStr = new SpannableString(mContext.getString(R.string.speaker));
                    role = mContext.getString(R.string.speaker);
                    break;
                case 4:
                    //holder.tvStatus.setText(mContext.getString(R.string.manager));
                    roleStr = new SpannableString(mContext.getString(R.string.manager));
                    role = mContext.getString(R.string.manager);
                    break;
                case 5:
                    //holder.tvStatus.setText(mContext.getString(R.string.guester));
                    roleStr = new SpannableString(mContext.getString(R.string.guester));
                    role = mContext.getString(R.string.guester);
                    break;
            }
//            holder.tvStatus.setVisibility(View.VISIBLE);
        } else {
//            holder.tvStatus.setVisibility(View.GONE);
        }

//         设置
        if (null != roleStr) {

            roleStr.setSpan(new TextAppearanceSpan(mContext, R.style.role_style), 0
                    , role.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            roleStr.setSpan(new RoundBackgroundColorSpan(Color.parseColor("#ccab86")
                            , Color.parseColor("#FFFFFF")), 0, role.length()
                    , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }


        SpannableStringBuilder builder = new SpannableStringBuilder();

        String str1 = list.get(position).getName() + ": ";

        SpannableString SS1 = new SpannableString(str1);

        int len = str1.length();


        if (list.get(position).getRole() == 1) {
            SS1.setSpan(new TextAppearanceSpan(mContext, R.style.live_bg_99), 0, len
                    , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            SS1.setSpan(new TextAppearanceSpan(mContext, R.style.live_bg), 0, len
                    , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        //设置World的格式
        String str2 = list.get(position).getContent();
        int len2 = str2.length();

        SpannableString SS2 = new SpannableString(str2);

        ForegroundColorSpan colSpan = new ForegroundColorSpan((mContext.getResources()
                .getColor(R.color.white)));

        SS2.setSpan(colSpan, 0, len2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (null != roleStr)
            builder.append(roleStr);
        if (null != SS1)
            builder.append(SS1);
        if (null != SS2)
            builder.append(SS2);

        holder.tvMessage.setText(builder);


        return convertView;
    }

    static class ViewHolder {

//        @BindView(R.id.tv_name)
//        TextView tvName;
        @BindView(R.id.tv_message)
        TextView tvMessage;
//        @BindView(R.id.tv_status)
//        TextView tvStatus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            tvMessage.setSelected(true);
//            tvName.setSelected(true);
//            tvStatus.setSelected(true);

        }
    }
}
