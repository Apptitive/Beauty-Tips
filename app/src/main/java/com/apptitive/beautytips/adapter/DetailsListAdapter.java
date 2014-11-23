package com.apptitive.beautytips.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apptitive.beautytips.R;
import com.apptitive.beautytips.model.Detail;
import com.apptitive.beautytips.utilities.Constants;
import com.apptitive.beautytips.utilities.Utilities;

import java.util.List;


/**
 * Created by Iftekhar on 6/5/2014.
 */
public class DetailsListAdapter extends BaseAdapter {

    private static final int VIEW_TYPE_COUNT = Constants.detail.VIEW_TYPE_COUNT;

    private List<Detail> details;
    private Context context;

    public DetailsListAdapter(Context context, List<Detail> details) {
        this.context = context;
        this.details = details;
    }

    @Override
    public int getItemViewType(int position) {
        return details.get(position).getTag();
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getCount() {
        return details.size();
    }

    @Override
    public Detail getItem(int position) {
        return details.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Detail detail = getItem(position);
        int viewType = getItemViewType(position);
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            switch (viewType) {
                case Constants.detail.VIEW_TYPE_P:
                    convertView = LayoutInflater.from(context).inflate(R.layout.list_item_detail_p, parent, false);
                    holder.tvDetail = (TextView) convertView.findViewById(R.id.btv_plainText);
                    break;
                case Constants.detail.VIEW_TYPE_UL:
                    convertView = LayoutInflater.from(context).inflate(R.layout.list_item_detail_ul, parent, false);
                    holder.tvDetail = (TextView) convertView.findViewById(R.id.btv_bulletText);
                    break;
                case Constants.detail.VIEW_TYPE_H1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.list_item_detail_h1, parent, false);
                    holder.tvDetail = (TextView) convertView.findViewById(R.id.btv_headerText);
                    break;
                case Constants.detail.VIEW_TYPE_B:
                    convertView = LayoutInflater.from(context).inflate(R.layout.list_item_detail_b, parent, false);
                    holder.tvDetail = (TextView) convertView.findViewById(R.id.textView_bold);
                    break;
                case Constants.detail.VIEW_TYPE_I:
                    convertView = LayoutInflater.from(context).inflate(R.layout.list_item_detail_i, parent, false);
                    holder.tvDetail = (TextView) convertView.findViewById(R.id.textView_italic);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvDetail.setText(Utilities.getBanglaSpannableString(detail.getText(), context));

        return convertView;
    }

    public void changeDataSet(List<Detail> details) {
        this.details = details;
    }

    private class ViewHolder {
        TextView tvDetail;
    }
}