package com.jack.root.something.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by jack
 * On 18-1-24:下午4:33
 * Desc: note model
 */
public class NoteModel implements Parcelable, RealmModel {
    @PrimaryKey
    private long id;

    @Required
    private String title;

    @Required
    private String content;

    public NoteModel() {

    }

    public NoteModel(Parcel in) {
        id = in.readLong();
        title = in.readString();
        content = in.readString();
    }

    public static final Creator<NoteModel> CREATOR = new Creator<NoteModel>() {
        @Override
        public NoteModel createFromParcel(Parcel in) {
            return new NoteModel(in);
        }

        @Override
        public NoteModel[] newArray(int size) {
            return new NoteModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(content);
    }
}
