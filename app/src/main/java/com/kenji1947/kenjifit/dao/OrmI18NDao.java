package com.kenji1947.kenjifit.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.kenji1947.kenjifit.dao.OrmI18N;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ORM_I18_N".
*/
public class OrmI18NDao extends AbstractDao<OrmI18N, Long> {

    public static final String TABLENAME = "ORM_I18_N";

    /**
     * Properties of entity OrmI18N.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property RU = new Property(1, String.class, "RU", false, "RU");
        public final static Property EN = new Property(2, String.class, "EN", false, "EN");
    };


    public OrmI18NDao(DaoConfig config) {
        super(config);
    }
    
    public OrmI18NDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ORM_I18_N\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"RU\" TEXT," + // 1: RU
                "\"EN\" TEXT);"); // 2: EN
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ORM_I18_N\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, OrmI18N entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String RU = entity.getRU();
        if (RU != null) {
            stmt.bindString(2, RU);
        }
 
        String EN = entity.getEN();
        if (EN != null) {
            stmt.bindString(3, EN);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public OrmI18N readEntity(Cursor cursor, int offset) {
        OrmI18N entity = new OrmI18N( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // RU
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // EN
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, OrmI18N entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRU(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setEN(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(OrmI18N entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(OrmI18N entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
