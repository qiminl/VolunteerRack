package wala.volunteerrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by liuqi on 3/14/2016.
 */

public class DBHandler extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "details";
    private final static String TABLE_NAME_COMMENT = "comments";
    private static final int DATABASE_VERSION = 1;
    private final static String COLUMN_ID = "_id";
    private final static String COLUMN_OPP_ID = "opportunity_id";
    private final static String COLUMN_USER_ID = "user_id";
    private final static String COLUMN_URL= "url";
    private final static String COLUMN_URI= "uri";
    private final static String COLUMN_COMMENT = "comment";


    private final static String TABLE_NAME_OPPORTUNITY = "opportunity";
    private final static String COLUMN_LIKE = "like";

    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_COMMENT + " ( " + COLUMN_ID
            +" integer primary key, " + COLUMN_OPP_ID +" TEXT NOT NULL, " + COLUMN_USER_ID +" TEXT NOT NULL, "
            +COLUMN_URL + " TEXT , " + COLUMN_URI+ " TEXT , " + COLUMN_COMMENT +" TEXT);";
    /*
     * Table comments:
     * Columns #0 _id, integer, #1 opportunity_id, text #2 user_id,text #3 url,text #4 uri,text
     *         #5 comment,text (or BLOB?)
     */

    private static final String OPPORTUNITY_CREATE = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_OPPORTUNITY
            + " ( " + COLUMN_ID  +" integer primary key, " + COLUMN_OPP_ID +" TEXT NOT NULL, "
            +COLUMN_URL + " TEXT NOT NULL, " + COLUMN_URI+ " TEXT NOT NULL, " +COLUMN_LIKE + " integer NOT NULL, " + COLUMN_COMMENT +" TEXT);";
    /*
     * Table opportunity:
     * Columns #0 _id, integer, #1 opportunity_id, text #2 user_id,text #3 url,text #4 uri,text
     *         #5 comment,text (or BLOB?)
     */

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        db.execSQL(OPPORTUNITY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_COMMENT );
        db.execSQL("DROP TABLE IF EXISTS " + OPPORTUNITY_CREATE );
        onCreate(db);
    }

    public long addComment(Comment comment){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " +  TABLE_NAME_COMMENT + " WHERE "
                + COLUMN_ID + " = " +comment.getCOLUMN_ID();

        Cursor cursor = db.rawQuery(selectQuery, null);
        long newRowId;

        /**
         * @todo handle the stupid duplicate: practice DRAFT !!!!!!
         * should test for difference
         */
        if(cursor.getCount()==1){
            //now only return the exist diary.
            newRowId = cursor.getColumnCount();
        }
        else{
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, comment.getCOLUMN_ID());
            values.put(COLUMN_OPP_ID, comment.getCOLUMN_OPP_ID());
            values.put(COLUMN_USER_ID, comment.getCOLUMN_USER_ID());
            values.put(COLUMN_URL, comment.getCOLUMN_URL());
            values.put(COLUMN_URI, comment.getCOLUMN_URI());
            values.put(COLUMN_COMMENT, comment.getCOLUMN_COMMENT());
            //System.out.println("db path: " + db.getPath());
            newRowId  = db.insert(TABLE_NAME_COMMENT, null, values);
            System.out.println("row id = " + newRowId);
            //db.close();
            Log.d("debug", "comment Database added");
        }
        db.close();
        return newRowId;
    }

    public Comment findCommentByID(String id){
        String query = "Select * FROM " + TABLE_NAME_COMMENT + " WHERE " + COLUMN_ID + " = \"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Comment comment = new Comment();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            comment.setCOLUMN_ID(cursor.getString(0));
            comment.setCOLUMN_OPP_ID(cursor.getString(2));
            comment.setCOLUMN_USER_ID(cursor.getString(1));
            comment.setCOLUMN_URL(cursor.getString(3));
            comment.setCOLUMN_URI(cursor.getString(4));
            comment.setCOLUMN_COMMENT(cursor.getString(5));
            cursor.close();
        } else {
            comment = null;
        }
        db.close();
        return comment;
    }

    public long addOpportunity (Opportunity opportunity){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " +  TABLE_NAME_COMMENT + " WHERE "
                + COLUMN_ID + " = " +opportunity.getCOLUMN_ID();

        Cursor cursor = db.rawQuery(selectQuery, null);
        long newRowId;

        /**
         * @todo handle the stupid duplicate: practice DRAFT !!!!!!
         * should test for difference
         */
        if(cursor.getCount()==1){
            //now only return the exist diary.
            newRowId = cursor.getColumnCount();
        }
        else{
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, opportunity.getCOLUMN_ID());
            values.put(COLUMN_OPP_ID, opportunity.getCOLUMN_OPP_ID());
            values.put(COLUMN_URL, opportunity.getCOLUMN_URL());
            values.put(COLUMN_URI, opportunity.getCOLUMN_URI());
            //System.out.println("db path: " + db.getPath());
            newRowId  = db.insert(TABLE_NAME_COMMENT, null, values);
            System.out.println("row id = " + newRowId);
            //db.close();
            Log.d("debug", "comment Database added");
        }
        db.close();
        return newRowId;
    }
}