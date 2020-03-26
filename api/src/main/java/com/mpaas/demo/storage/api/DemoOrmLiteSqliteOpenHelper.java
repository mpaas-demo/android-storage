package com.mpaas.demo.storage.api;

import android.content.Context;

import com.alibaba.sqlcrypto.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DemoOrmLiteSqliteOpenHelper extends OrmLiteSqliteOpenHelper {

    /**
     * 数据库名称
     */
    private static final String DB_NAME = "com_mpaas_demo_storage.db";

    /**
     * 当前数据库版本
     */
    private static final int DB_VERSION = 1;

    /**
     * 数据库加密秘钥，mPaaS支持数据库加密，使数据在设备上更安全，若为null则不加密
     */
    private static final String DB_PASSWORD = "mpaas";

    public DemoOrmLiteSqliteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        setPassword(DB_PASSWORD);
    }

    /**
     * 数据库创建时的回调函数
     *
     * @param sqLiteDatabase   数据库
     * @param connectionSource 连接
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            // 创建User表
            TableUtils.createTableIfNotExists(connectionSource, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库更新时的回调函数
     *
     * @param database         数据库
     * @param connectionSource 连接
     * @param oldVersion       旧数据库版本
     * @param newVersion       新数据库版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            // 删除旧版User表，忽略错误
            TableUtils.dropTable(connectionSource, User.class, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // 从新创建User表
            TableUtils.createTableIfNotExists(connectionSource, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
