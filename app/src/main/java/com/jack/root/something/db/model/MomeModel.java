package com.jack.root.something.db.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jack.root.something.adapter.MomeRecyclerAdapter;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by jack
 * On 18-2-5:下午4:45
 * Desc:
 */

public class MomeModel extends RealmObject implements Parcelable, MultiItemEntity {
    @PrimaryKey
    private long id;

    @Required
    private String title;

    @Required
    public String content;

    public Date date = new Date();

    private int type = 0;

    public MomeModel() {

    }

    public MomeModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public MomeModel(String title, String content, Date date, int type) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.type = type;
    }

    public MomeModel(Parcel in) {
        id = in.readLong();
        title = in.readString();
        content = in.readString();
    }

    public static final Parcelable.Creator<NoteModel> CREATOR = new Parcelable.Creator<NoteModel>() {
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

    @Override
    public int getItemType() {
        return MomeRecyclerAdapter.TYPE_MOME;
    }
}
