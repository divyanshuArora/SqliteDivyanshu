package app.gobusiness.com.sqlitedivyanshu;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DB_HELPER extends SQLiteOpenHelper {

    private static final String dbName = "users";
    private static final String USER_TABLE = "companyUsers";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String NUMBER = "number";
    private static final String PASSWORD = "password";
    private static final String USER_IMAGE = "user_image";

    public  DB_HELPER Helper;

    public SQLiteDatabase db;

    public DB_HELPER(Context context) {
        super(context, dbName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " + USER_TABLE
                + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + NAME + " TEXT,"
                + EMAIL + " TEXT,"
                + NUMBER + " TEXT,"
                + PASSWORD + " TEXT,"
                + USER_IMAGE + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase getDatabase() {
        return db;
    }


    void addUsers(UserModel userModel) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,userModel.getId());
        contentValues.put(NAME, userModel.getName());
        contentValues.put(EMAIL, userModel.getEmail());
        contentValues.put(NUMBER, userModel.getNumber());
        contentValues.put(PASSWORD, userModel.getPassword());
        contentValues.put(USER_IMAGE,userModel.getUser_image());

        sqLiteDatabase.insert(USER_TABLE, null, contentValues);


        sqLiteDatabase.close();

    }

    public List<UserModel> getAllUser()
    {
        String[] column ={ID,NAME,EMAIL,NUMBER,PASSWORD,USER_IMAGE};


        String sortOrder = NAME;

        List<UserModel> userModels  =new ArrayList<>();

        SQLiteDatabase db =  this.getReadableDatabase();

        Cursor cursor = db.query(USER_TABLE,column,null,null,null,null,sortOrder);

        if (cursor.moveToFirst())
        {
            do {
                //UserModel userModel = new UserModel(ID,NAME,EMAIL,NUMBER,PASSWORD);
                UserModel userModel = new UserModel();
                userModel.setId(cursor.getString(cursor.getColumnIndex(ID)));
                userModel.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                userModel.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                userModel.setNumber(cursor.getString(cursor.getColumnIndex(NUMBER)));
                userModel.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
                userModel.setUser_image(cursor.getString(cursor.getColumnIndex(USER_IMAGE)));


                userModels.add(userModel);

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return  userModels;
    }






}