package com.android.tonight8.dao;

import java.util.List;
import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import com.android.tonight8.dao.entity.User;
import com.android.tonight8.dao.entity.VoteItemOption;

import com.android.tonight8.dao.entity.VoteResult;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table VOTE_RESULT.
 */
public class VoteResultDao extends AbstractDao<VoteResult, Long> {

    public static final String TABLENAME = "VOTE_RESULT";
    private DaoSession daoSession;
    private String selectDeep;


    public VoteResultDao(DaoConfig config) {
        super(config);
    }

    public VoteResultDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "'VOTE_RESULT' (" + //
                "'ID' INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "'USER_ID' INTEGER," + // 1: userId
                "'VOTE_ITEM_OPTION_ID' INTEGER);"); // 2: voteItemOptionId
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'VOTE_RESULT'";
        db.execSQL(sql);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected void bindValues(SQLiteStatement stmt, VoteResult entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());

        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(2, userId);
        }

        Long voteItemOptionId = entity.getVoteItemOptionId();
        if (voteItemOptionId != null) {
            stmt.bindLong(3, voteItemOptionId);
        }
    }

    @Override
    protected void attachEntity(VoteResult entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }

    /**
     * @inheritdoc
     */
    @Override
    public VoteResult readEntity(Cursor cursor, int offset) {
        VoteResult entity = new VoteResult( //
                cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // userId
                cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // voteItemOptionId
        );
        return entity;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void readEntity(Cursor cursor, VoteResult entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setUserId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setVoteItemOptionId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
    }

    /**
     * @inheritdoc
     */
    @Override
    protected Long updateKeyAfterInsert(VoteResult entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long getKey(VoteResult entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getUserDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getVoteItemOptionDao().getAllColumns());
            builder.append(" FROM VOTE_RESULT T");
            builder.append(" LEFT JOIN USER T0 ON T.'USER_ID'=T0.'ID'");
            builder.append(" LEFT JOIN VOTE_ITEM_OPTION T1 ON T.'VOTE_ITEM_OPTION_ID'=T1.'ID'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }

    protected VoteResult loadCurrentDeep(Cursor cursor, boolean lock) {
        VoteResult entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        User user = loadCurrentOther(daoSession.getUserDao(), cursor, offset);
        entity.setUser(user);
        offset += daoSession.getUserDao().getAllColumns().length;

        VoteItemOption voteItemOption = loadCurrentOther(daoSession.getVoteItemOptionDao(), cursor, offset);
        entity.setVoteItemOption(voteItemOption);

        return entity;
    }

    public VoteResult loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();

        String[] keyArray = new String[]{key.toString()};
        Cursor cursor = db.rawQuery(sql, keyArray);

        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }

    /**
     * Reads all available rows from the given cursor and returns a list of new ImageTO objects.
     */
    public List<VoteResult> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<VoteResult> list = new ArrayList<VoteResult>(count);

        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }

    protected List<VoteResult> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }

    /**
     * A raw-style query where you can pass any WHERE clause and arguments.
     */
    public List<VoteResult> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }

    /**
     * Properties of entity VoteResult.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "ID");
        public final static Property UserId = new Property(1, Long.class, "userId", false, "USER_ID");
        public final static Property VoteItemOptionId = new Property(2, Long.class, "voteItemOptionId", false, "VOTE_ITEM_OPTION_ID");
    }

}
