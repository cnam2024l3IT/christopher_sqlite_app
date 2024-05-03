package com.example.sqliteexampleapp.databases.contracts;

import android.provider.BaseColumns;

public final class DataContract {

    private DataContract() {}

    public static abstract class InfoTable implements BaseColumns {
        public static String TABLE_NAME;

        public static final String DATE_CREATION = "date_creation";

        public static final String DATE_MODIFICATION = "date_modification";

        public static String SQL_CREATE;

        public static String SQL_DELETE;
    }

    public static class AnnonceTable extends InfoTable {
        public static final String TABLE_NAME = "annonces";

        public static final String PERSONNE_ID = "personne_id";

        public static final String TITLE = "title";

        public static final String PRICE = "price";

        public static final String DESCRIPTION = "description";

        public static final String DATE_PUBLICATION = "date_publication";

        public static final String DATE_FIN_PUBLICATION = "date_fin_publication";

        public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PERSONNE_ID + " INTEGER NOT NULL," + TITLE + " TEXT NOT NULL, " + PRICE + " INTEGER NOT NULL, "
            + DESCRIPTION + " TEXT NOT NULL, " + DATE_PUBLICATION + " TEXT NOT NULL, " + DATE_FIN_PUBLICATION + " TEXT, "
            + DATE_CREATION + " TEXT NOT NULL, " + DATE_MODIFICATION + " TEXT NOT NULL);";

        public static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
    public static class PersonneTable extends InfoTable {
        public static final String TABLE_NAME = "personnes";

        public static final String NOM = "nom";

        public static final String PRENOM = "prenom";

        public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NOM + " TEXT NOT NULL, " + PRENOM + " TEXT NOT NULL, " + DATE_CREATION + " TEXT NOT NULL, "
            + DATE_MODIFICATION + " TEXT NOT NULL);";

        public static final String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
