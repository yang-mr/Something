package com.jack.root.something.db;

import com.jack.root.something.db.model.MomeModel;
import com.jack.root.something.db.model.NoteModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by jack
 * On 18-1-24:下午3:08
 * Desc: db manager
 */

public class DbManager {
    public final static String DB_NAME = "realm.db";
    private static DbManager mDbManager;
    private static Realm mRealm;
    private DbManager() {
        mRealm = Realm.getDefaultInstance();
    }

    public static DbManager getInstance() {
        if (mDbManager == null) {
            mDbManager = new DbManager();
        }
        return mDbManager;
    }

    /**
     Desc add note
     18-1-24:下午4:38
     Author jack
    */
    public static boolean addNote(final NoteModel note) {
        try {
            mRealm.executeTransaction(new MyTransaction() {
                @Override
                public void execute(Realm realm) {
                    super.execute(realm);
                    realm.copyToRealm(note);
                }
            });
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     Desc add note
     18-1-24:下午4:38
     Author jack
     */
    public static boolean addNoteByAsync(final NoteModel note) {
        try {
           mRealm.executeTransactionAsync(new Realm.Transaction() {
               @Override
               public void execute(Realm realm) {
                   realm.copyToRealm(note);
               }
           }, new Realm.Transaction.OnSuccess() {

               @Override
               public void onSuccess() {

               }
           }, new Realm.Transaction.OnError() {
               @Override
               public void onError(Throwable error) {

               }
           });
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     Desc add note
     18-1-24:下午4:38
     Author jack
     */
    public static boolean addMome(final MomeModel mome) {
        try {
            mRealm.executeTransaction(new MyTransaction() {
                @Override
                public void execute(Realm realm) {
                    super.execute(realm);
                    realm.copyToRealm(mome);
                }
            });
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     Desc get all mome
     18-2-6:上午10:49
     Author jack
    */
    public static List<MomeModel> getAllMome() {
        RealmResults<MomeModel> momeList = mRealm.where(MomeModel.class).findAll();
        return momeList;
    }

    private static class MyTransaction implements Realm.Transaction {
        @Override
        public void execute(Realm realm) {

        }
    }
}
