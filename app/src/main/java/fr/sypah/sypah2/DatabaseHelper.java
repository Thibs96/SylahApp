package fr.sypah.sypah2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    DatabaseHelper db;
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "utilisateurs.db";
    public static final String TABLE_NAME = "utilisateurs";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_PRENOM = "PRENOM";
    private static final String COLUMN_PSEUDO = "PSEUDO";
    private static final String COLUMN_PASS = "PASS";
    private static final String COLUMN_PASS2 = "PASS2";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRENOM TEXT,PSEUDO TEXT,PASS TEXT" +
                ", PASS2 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUtilisateurs(String prenom, String pseudo, String pass, String pass2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRENOM, prenom);
        contentValues.put(COLUMN_PSEUDO, pseudo);
        contentValues.put(COLUMN_PASS, pass);
        contentValues.put(COLUMN_PASS2, pass2);


        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public String searchPass(String pseudo) {

        SQLiteDatabase db = this.getReadableDatabase(); //récupère la base, la base sera en lecture seule
        String requete = "select * from "+TABLE_NAME; // creation de la requete sql
        Cursor c = db.rawQuery(requete,null);// curseur est un objet qui contient le résultat d'une recherche dans une base de données
        // exécute le code SQL fourni et renvoie un Cursor
        String a,b;
        b = "null";
        while (c.moveToNext()) { // le cursor parcours chaque ligne du résultat de la requête
            a = c.getString(2); // renvoie la chaine de caractère corespondant au prenom
            if(a.equals(pseudo)){// verifie si le prenom demandé correspond au résultat de la requête
                b = c.getString(4);// renvoie la chaine de caractère correspondant au mot de passe
                break;
            }

        }return b;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


}