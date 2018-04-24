package com.example.sylvain.storyloupio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by sylvain on 29/11/2017.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Game.db";
    private static final int DATABASE_VERSION = 10;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSQL = "create table T_Chapter ("
                +" idChapter integer primary key autoincrement,"
                +" titleChapter text not null,"
                +" contentChapter text not null,"
                +" titleProp1 text,"
                +" idChapterProp1 integer,"
                +" titleProp2 text,"
                +" idChapterProp2 integer,"
                +" titleProp3 text,"
                +" idChapterProp3 integer,"
                +" titleProp4 text,"
                +" idChapterProp4 integer,"
                +" idGame integer default 0"
                +")";
        db.execSQL(strSQL);
        Log.i("DATABASE", "onCreate invoked");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String strSQL = "Create table T_GameSave ("
                + "idLastChapter integer"
                + ")";
        db.execSQL(strSQL);

        System.out.println(db.getPath());
        Log.i("DATABASE", "onUpdate invoked");
    }

    public void insertChapter(String title, String content, String titleProp1, int idChapterProp1, String titleProp2, int idChapterProp2, String titleProp3, int idChapterProp3, String titleProp4, int idChapterProp4){

        title = title.replace("'", "''");
        content = content.replace("'","''");
        if (titleProp1 != null){
            titleProp1 = titleProp1.replace("'","''");
        }
        if (titleProp2 != null) {
            titleProp2 = titleProp2.replace("'", "''");
        }
        if (titleProp3 != null) {
            titleProp3 = titleProp3.replace("'", "''");
        }
        if (titleProp4 != null) {
            titleProp4 = titleProp4.replace("'", "''");
        }

        System.out.println(title+"\n"+content+"\n"+titleProp1+"\n"+idChapterProp1+"\n"+titleProp2+"\n"+idChapterProp2+"\n"+titleProp3+"\n"+idChapterProp3+"\n"+titleProp4+"\n"+idChapterProp4);

        String strSQL = "insert into T_Chapter (titleChapter, contentChapter, titleProp1, idChapterProp1, titleProp2, idChapterProp2, titleProp3, idChapterProp3, titleProp4, idChapterProp4) values ('"
                + title + "', '"+ content + "', '"+ titleProp1+"', '"+ idChapterProp1+"', '"+ titleProp2+"', '"+ idChapterProp2+"', '"+titleProp3+"', '"+idChapterProp3+"', '"+titleProp4+"', '"+idChapterProp4+"')";
        System.out.println(strSQL);
        this.getWritableDatabase().execSQL(strSQL);
        Log.i("DATABASE", "insertChapter invoked");
    }

    public List<ChapterData> getAllChaptersData(){
        List<ChapterData> allChaptersData = new ArrayList<>();

        // 1ère technique : SQL
        String strSql = "select * from T_Chapter";
        Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            ChapterData chapter = new ChapterData( cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7),
                    cursor.getInt(8),
                    cursor.getString(9),
                    cursor.getInt(10),
                    cursor.getInt(11)
            );
            allChaptersData.add( chapter );
            cursor.moveToNext();
        }
        cursor.close();

        return allChaptersData;
    }

    public ChapterData getOneChapterData(int code){
        String codeS= String.valueOf(code);
        ChapterData chapterData;
        System.out.println("id chapitre recherché : "+codeS);
        String strSql = "select * from T_Chapter where idChapter= ?";
        Cursor cursor= this.getReadableDatabase().rawQuery(strSql, new String[] {codeS});
        cursor.moveToFirst();
        chapterData = new ChapterData( cursor.getInt(0), cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getString(5),
                cursor.getInt(6),
                cursor.getString(7),
                cursor.getInt(8),
                cursor.getString(9),
                cursor.getInt(10),
                cursor.getInt(11)
        );

        return chapterData;
    }

    public void insertGameSave(int idLastChapter){
        String strSQL = "delete from T_GameSave";
        this.getWritableDatabase().execSQL(strSQL);
        strSQL = "insert into T_GameSave (idLastChapter) values ('"
                +idLastChapter+"')";
        this.getWritableDatabase().execSQL(strSQL);
    }

    public int getGameSave(){
        String strSQL = "select * from T_GameSave";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSQL,null);
        cursor.moveToFirst();
        int ChapterSaved = cursor.getInt(0);
        return ChapterSaved;
    }
}
