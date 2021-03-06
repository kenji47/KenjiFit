package com.kenji1947.kenjifit.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.kenji1947.kenjifit.dao.OrmExercise;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ORM_EXERCISE".
*/
public class OrmExerciseDao extends AbstractDao<OrmExercise, Long> {

    public static final String TABLENAME = "ORM_EXERCISE";

    /**
     * Properties of entity OrmExercise.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property SERVER_ID = new Property(1, String.class, "SERVER_ID", false, "SERVER__ID");
        public final static Property ICON_URL = new Property(2, String.class, "ICON_URL", false, "ICON__URL");
        public final static Property PERFORMANCE = new Property(3, Integer.class, "PERFORMANCE", false, "PERFORMANCE");
        public final static Property TYPE_NAME = new Property(4, String.class, "TYPE_NAME", false, "TYPE__NAME");
        public final static Property WEIGHT = new Property(5, Integer.class, "WEIGHT", false, "WEIGHT");
        public final static Property WEIGHT_FOR_FEMALE = new Property(6, Integer.class, "WEIGHT_FOR_FEMALE", false, "WEIGHT__FOR__FEMALE");
        public final static Property PM_COEFFICIENTS_STR = new Property(7, Integer.class, "PM_COEFFICIENTS_STR", false, "PM__COEFFICIENTS__STR");
        public final static Property MUSCLE_GROUP_SERVER_ID = new Property(8, String.class, "MUSCLE_GROUP_SERVER_ID", false, "MUSCLE__GROUP__SERVER__ID");
        public final static Property NAME_ID = new Property(9, Integer.class, "NAME_ID", false, "NAME__ID");
        public final static Property DESCRIPTION_ID = new Property(10, Integer.class, "DESCRIPTION_ID", false, "DESCRIPTION__ID");
    };


    public OrmExerciseDao(DaoConfig config) {
        super(config);
    }
    
    public OrmExerciseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ORM_EXERCISE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"SERVER__ID\" TEXT," + // 1: SERVER_ID
                "\"ICON__URL\" TEXT," + // 2: ICON_URL
                "\"PERFORMANCE\" INTEGER," + // 3: PERFORMANCE
                "\"TYPE__NAME\" TEXT," + // 4: TYPE_NAME
                "\"WEIGHT\" INTEGER," + // 5: WEIGHT
                "\"WEIGHT__FOR__FEMALE\" INTEGER," + // 6: WEIGHT_FOR_FEMALE
                "\"PM__COEFFICIENTS__STR\" INTEGER," + // 7: PM_COEFFICIENTS_STR
                "\"MUSCLE__GROUP__SERVER__ID\" TEXT," + // 8: MUSCLE_GROUP_SERVER_ID
                "\"NAME__ID\" INTEGER," + // 9: NAME_ID
                "\"DESCRIPTION__ID\" INTEGER);"); // 10: DESCRIPTION_ID
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ORM_EXERCISE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, OrmExercise entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String SERVER_ID = entity.getSERVER_ID();
        if (SERVER_ID != null) {
            stmt.bindString(2, SERVER_ID);
        }
 
        String ICON_URL = entity.getICON_URL();
        if (ICON_URL != null) {
            stmt.bindString(3, ICON_URL);
        }
 
        Integer PERFORMANCE = entity.getPERFORMANCE();
        if (PERFORMANCE != null) {
            stmt.bindLong(4, PERFORMANCE);
        }
 
        String TYPE_NAME = entity.getTYPE_NAME();
        if (TYPE_NAME != null) {
            stmt.bindString(5, TYPE_NAME);
        }
 
        Integer WEIGHT = entity.getWEIGHT();
        if (WEIGHT != null) {
            stmt.bindLong(6, WEIGHT);
        }
 
        Integer WEIGHT_FOR_FEMALE = entity.getWEIGHT_FOR_FEMALE();
        if (WEIGHT_FOR_FEMALE != null) {
            stmt.bindLong(7, WEIGHT_FOR_FEMALE);
        }
 
        Integer PM_COEFFICIENTS_STR = entity.getPM_COEFFICIENTS_STR();
        if (PM_COEFFICIENTS_STR != null) {
            stmt.bindLong(8, PM_COEFFICIENTS_STR);
        }
 
        String MUSCLE_GROUP_SERVER_ID = entity.getMUSCLE_GROUP_SERVER_ID();
        if (MUSCLE_GROUP_SERVER_ID != null) {
            stmt.bindString(9, MUSCLE_GROUP_SERVER_ID);
        }
 
        Integer NAME_ID = entity.getNAME_ID();
        if (NAME_ID != null) {
            stmt.bindLong(10, NAME_ID);
        }
 
        Integer DESCRIPTION_ID = entity.getDESCRIPTION_ID();
        if (DESCRIPTION_ID != null) {
            stmt.bindLong(11, DESCRIPTION_ID);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public OrmExercise readEntity(Cursor cursor, int offset) {
        OrmExercise entity = new OrmExercise( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // SERVER_ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // ICON_URL
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // PERFORMANCE
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // TYPE_NAME
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // WEIGHT
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // WEIGHT_FOR_FEMALE
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // PM_COEFFICIENTS_STR
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // MUSCLE_GROUP_SERVER_ID
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9), // NAME_ID
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10) // DESCRIPTION_ID
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, OrmExercise entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSERVER_ID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setICON_URL(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPERFORMANCE(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setTYPE_NAME(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setWEIGHT(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setWEIGHT_FOR_FEMALE(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setPM_COEFFICIENTS_STR(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setMUSCLE_GROUP_SERVER_ID(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setNAME_ID(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
        entity.setDESCRIPTION_ID(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(OrmExercise entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(OrmExercise entity) {
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
