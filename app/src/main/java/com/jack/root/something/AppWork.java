package com.jack.root.something;

import android.app.Application;

import com.jack.root.something.db.DbManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import org.xutils.x;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jackyang on 18-1-15.
 *
 * @DESCRIPTION --------------------
 */

public class AppWork extends Application {
    private final static String TAG = "jack";
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        x.Ext.init(this);   // init xutils
        initLog();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                .name(DbManager.DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    private void initLog() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .tag(TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }
}
