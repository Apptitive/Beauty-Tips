package com.apptitive.beautytips.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.apptitive.beautytips.utilities.Constants;

/**
 * Created by Iftekhar on 6/4/2014.
 */
public class Content implements Parcelable {
    private String contentId;
    private String header;
    private String shortDescription;
    private DetailType detailType;
    private String details;

    private Content(Parcel in) {
        contentId = in.readString();
        header = in.readString();
        try {
            detailType = DetailType.valueOf(in.readString());
        }
        catch (IllegalArgumentException iae) {
            detailType = null;
        }
        details = in.readString();
    }

    public Content() {
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public DetailType getDetailType() {
        return detailType;
    }

    public void setDetailType(DetailType detailType) {
        this.detailType = detailType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(contentId);
        out.writeString(header);
        out.writeString(detailType == null ? "" : detailType.name());
        out.writeString(details);
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel parcel) {
            return new Content(parcel);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    public void populateFrom(DbContent dbContent) {
        setContentId(dbContent.getContentId());
        setHeader(dbContent.getHeader());
        setShortDescription(dbContent.getShortDescription());
        setDetailType(dbContent.getViewType().equalsIgnoreCase(Constants.content.view.TYPE_NATIVE) ? DetailType.NATIVE : DetailType.HTML);
        setDetails(dbContent.getDetails());
    }
}
