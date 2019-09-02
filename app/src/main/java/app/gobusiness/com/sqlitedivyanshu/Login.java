package app.gobusiness.com.sqlitedivyanshu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText number,password;
    Button login;
    DB_HELPER db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        number = findViewById(R.id.number);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = new  DB_HELPER(Login.this);


                 String number1 = number.getText().toString();
                 String pass1 = password.getText().toString();
//                 db.login(number1,pass1);

               DB_HELPER db=new DB_HELPER(Login.this);
               SQLiteDatabase obj= db.getReadableDatabase();


                String query="SELECT * FROM companyUsers where NUMBER ="+number1+" AND PASSWORD="+pass1;
                Log.e("login",query);
                Cursor cursor =obj.rawQuery(query,null);

                int CountCursor = cursor.getCount();
                cursor.close();
                db.close();
                if (CountCursor>0)
                {
                    Intent dash = new Intent(Login.this,Dashboard.class);
                    startActivity(dash);
                }
                else
                {
                    Toast.makeText(Login.this, "wrong credential", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
