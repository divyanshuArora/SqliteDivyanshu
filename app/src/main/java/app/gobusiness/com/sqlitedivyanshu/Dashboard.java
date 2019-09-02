package app.gobusiness.com.sqlitedivyanshu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    RecyclerView recyclerView;
    DB_HELPER db_helper;
    List<UserModel> userModels = new ArrayList<>();

    RecycleAdapter recycleAdapter;



    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        recyclerView = findViewById(R.id.recycle);

            db_helper = new DB_HELPER(Dashboard.this);

            getUsersName();

    }

    public void     getUsersName()
    {
       userModels =  db_helper.getAllUser();

       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
       linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
       recyclerView.setLayoutManager(linearLayoutManager);

       DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
       recyclerView.addItemDecoration(dividerItemDecoration);


       recycleAdapter = new RecycleAdapter(Dashboard.this,userModels);
       recyclerView.setAdapter(recycleAdapter);


    }
}
