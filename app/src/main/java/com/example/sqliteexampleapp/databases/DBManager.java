package com.example.sqliteexampleapp.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sqliteexampleapp.databases.contracts.DataContract;
import com.example.sqliteexampleapp.databases.helpers.DatabaseHelper;
import com.example.sqliteexampleapp.models.Annonce;
import com.example.sqliteexampleapp.models.Person;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    public DBManager(Context c) {
        context = c;
    }
    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }
    public Cursor fetchAnnonces() {
        String[] columns = new String[] { DataContract.AnnonceTable._ID,DataContract.AnnonceTable.PERSONNE_ID,
            DataContract.AnnonceTable.TITLE, DataContract.AnnonceTable.PRICE, DataContract.AnnonceTable.DESCRIPTION,
            DataContract.AnnonceTable.DATE_PUBLICATION, DataContract.AnnonceTable.DATE_FIN_PUBLICATION,
            DataContract.AnnonceTable.DATE_CREATION, DataContract.AnnonceTable.DATE_MODIFICATION };
        return getCursor(DataContract.AnnonceTable.TABLE_NAME, columns);
    }
    public Cursor fetchPersonnes() {
        String[] columns = new String[] { DataContract.PersonneTable._ID, DataContract.PersonneTable.NOM,
            DataContract.PersonneTable.PRENOM, DataContract.PersonneTable.DATE_CREATION,
            DataContract.PersonneTable.DATE_MODIFICATION };
        return getCursor(DataContract.PersonneTable.TABLE_NAME, columns);
    }

    @Nullable
    private Cursor getCursor(String tableName, String[] columns) {
        Cursor cursor = database.query(tableName, columns, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public long insertAnnonce(Annonce annonce) {
        ContentValues content = new ContentValues();
        content.put(DataContract.AnnonceTable.PERSONNE_ID, annonce.getPersonne().get_id());
        content.put(DataContract.AnnonceTable.TITLE, annonce.getTitle());
        content.put(DataContract.AnnonceTable.PRICE, annonce.getPrice());
        content.put(DataContract.AnnonceTable.DESCRIPTION, annonce.getDescription());
        content.put(DataContract.AnnonceTable.DATE_PUBLICATION, annonce.getDatePublication());
        content.put(DataContract.AnnonceTable.DATE_FIN_PUBLICATION, annonce.getDateFinPublication());
        return insert(DataContract.AnnonceTable.TABLE_NAME, content);
    }
    public long insertPersonne(Person personne) {
        ContentValues content = new ContentValues();
        content.put(DataContract.PersonneTable.NOM, personne.getLastName());
        content.put(DataContract.PersonneTable.PRENOM, personne.getFirstName());
        return insert(DataContract.PersonneTable.TABLE_NAME, content);
    }

    @NonNull
    private static String getMetaDataDate() {
        SimpleDateFormat dateFormatMeta = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormatMeta.format(new Date());
    }

    private long insert(String tableName, ContentValues content) {
        String dateMeta = getMetaDataDate();
        content.put(DataContract.InfoTable.DATE_CREATION, dateMeta);
        content.put(DataContract.InfoTable.DATE_MODIFICATION, dateMeta);
        return database.insert(tableName, null, content);
    }

    public int updateAnnonce(long _id, Annonce annonce) {
        ContentValues content = new ContentValues();
        content.put(DataContract.AnnonceTable.TITLE, annonce.getTitle());
        content.put(DataContract.AnnonceTable.PRICE, annonce.getPrice());
        content.put(DataContract.AnnonceTable.DESCRIPTION, annonce.getDescription());
        content.put(DataContract.AnnonceTable.DATE_PUBLICATION, annonce.getDatePublication());
        content.put(DataContract.AnnonceTable.DATE_FIN_PUBLICATION, annonce.getDateFinPublication());
        return update(content, DataContract.AnnonceTable.TABLE_NAME, _id);
    }
    public int updatePersonne(long _id, Person personne) {
        ContentValues content = new ContentValues();
        content.put(DataContract.PersonneTable.NOM, personne.getLastName());
        content.put(DataContract.PersonneTable.PRENOM, personne.getFirstName());
        return update(content, DataContract.PersonneTable.TABLE_NAME, _id);
    }

    private int update(ContentValues content, String tableName, long _id) {
        String dateMeta = getMetaDataDate();
        content.put(DataContract.InfoTable.DATE_MODIFICATION, dateMeta);
        return database.update(tableName, content, DataContract.InfoTable._ID + " = " + _id, null);
    }

    public void deleteAnnonces(long _id) {
        delete(DataContract.AnnonceTable.TABLE_NAME, _id);
    }
    public void deletePersonne(long _id) {
        delete(DataContract.PersonneTable.TABLE_NAME, _id);
    }

    private void delete(String tableName, long _id) {
        database.delete(tableName, DataContract.InfoTable._ID + "=" + _id, null);
    }
}
