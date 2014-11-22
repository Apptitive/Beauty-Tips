package com.apptitive.beautytips.model;

import com.apptitive.beautytips.utilities.Constants;

/**
 * Created by Iftekhar on 6/5/2014.
 */
public class Detail {
    private String text;
    private int tag;

    public Detail() {
    }

    public Detail(String text, int detailViewType) {
        this.text = text;
        tag = detailViewType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public void populateFrom(JsonDetail jsonDetail) {
        setTag(Constants.detail.findTagFor(jsonDetail.key));
        setText(jsonDetail.value);
    }
}
