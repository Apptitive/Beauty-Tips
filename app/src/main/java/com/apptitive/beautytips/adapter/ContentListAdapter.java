package com.apptitive.beautytips.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apptitive.beautytips.R;
import com.apptitive.beautytips.model.Content;

import java.util.List;

/**
 * Created by Iftekhar on 6/4/2014.
 */
public class ContentListAdapter extends ArrayAdapter<Content> {
    private int layoutResId;
    private ContentCallback contentCallback;

    public ContentListAdapter(Context context, int resource, List<Content> objects, ContentCallback contentCallback) {
        super(context, resource, objects);
        layoutResId = resource;
        this.contentCallback = contentCallback;
    }

    @Override
    public int getViewTypeCount() {
        if (getCount() != 0)
            return getCount();
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Content content = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutResId, parent, false);
            holder = new ViewHolder();
            holder.selectableView = convertView.findViewById(R.id.brief_content);
            holder.tvHeader = (TextView) convertView.findViewById(R.id.textView_content_header);
            holder.tvBrief = (TextView) convertView.findViewById(R.id.textView_content_brief);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvHeader.setText(content.getHeader());
        holder.tvBrief.setText(content.getShortDescription());

        final ViewHolder finalViewHolder = holder;
        finalViewHolder.selectableView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentCallback.onContentClick(content);
            }
        });

        return convertView;
    }

    public interface ContentCallback {
        void onContentClick(Content content);
    }

    private class ViewHolder {
        View selectableView;
        TextView tvHeader;
        TextView tvBrief;
    }
}
