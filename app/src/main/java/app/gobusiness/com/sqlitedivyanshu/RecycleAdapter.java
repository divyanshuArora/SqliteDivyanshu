package app.gobusiness.com.sqlitedivyanshu;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ItemViewHolder> {

    Context context;
    List<UserModel> userModels = new ArrayList<>();
    DB_HELPER db_helper;



    public RecycleAdapter(Context context, List<UserModel> userModels) {
        this.context = context;
        this.userModels = userModels;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycle_item, viewGroup, false);


        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, final int i) {
        itemViewHolder.name.setText(userModels.get(i).getName());
        itemViewHolder.email.setText(userModels.get(i).getEmail());
        itemViewHolder.number.setText(userModels.get(i).getNumber());

        Bitmap bitmap = BitmapFactory.decodeFile(userModels.get(i).getUser_image());

        Log.e("image",""+bitmap);

//        byte[] decodedImageString = Base64.decode(image,Base64.DEFAULT);
//        Bitmap byteCodeImage= BitmapFactory.decodeByteArray(decodedImageString,0,decodedImageString.length);

        Glide.with(context).load(bitmap).error(R.drawable.user_icon).placeholder(R.drawable.user_icon).into(itemViewHolder.image);

        itemViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db_helper = new DB_HELPER(context);
                SQLiteDatabase sqLiteDatabase = db_helper.getReadableDatabase();

                String deleteRow = "DELETE FROM companyUsers where ID =" + userModels.get(i).getId();

                Log.e("DELETE", deleteRow);
                sqLiteDatabase.execSQL(deleteRow);

                ((Dashboard)context).getUsersName();


            }
        });


        itemViewHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id =userModels.get(i).getId();
                String name = userModels.get(i).getName();
                String email = userModels.get(i).getEmail();
                String number = userModels.get(i).getNumber();
                String password = userModels.get(i).getPassword();
                Log.e("id",""+id );
                update(id,name,email,number,password);

            }
        });


    }

    private void update(final String id2,String name2,String email2,String number2,String password2) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.dialog_item, null);

        builder.setView(view);


        final EditText name, email, number, password;
        final Button UpdateBtn, cancelBtn;

        name = view.findViewById(R.id.namePop);
        email = view.findViewById(R.id.emailPop);
        number = view.findViewById(R.id.numberPop);
        password = view.findViewById(R.id.passPop);


        name.setText(name2);
        email.setText(email2);
        number.setText(number2);
        password.setText(password2);


        UpdateBtn = view.findViewById(R.id.updatePop);
        cancelBtn = view.findViewById(R.id.cancelPop);


        final Dialog dialog = builder.create();


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(context, "update cancel", Toast.LENGTH_SHORT).show();
            }
        });


        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name1, email1, number1, password1;

                name1 = name.getText().toString();
                email1 = email.getText().toString();
                number1 = number.getText().toString();
                password1 = password.getText().toString();

                db_helper = new DB_HELPER(context);
                SQLiteDatabase sqLiteDatabase = db_helper.getWritableDatabase();


                //UPDATE companyUsers set name="divyanshu Arora", email="div@gamil.com" ,number="7737729400", password="1234" where id="1" ;
                //String updateRow = "UPDATE companyUsers set name="+name1+", email="+email1+" ,number="+number1+", password="+password1+ "where id=" +id;

                String updateRow = "UPDATE companyUsers set name='"+name1+"',email='"+email1+"',number='"+number1+"',password='"+password1+"'where id="+id2;

                Log.e("update", "" + updateRow);

                sqLiteDatabase.execSQL(updateRow);
                dialog.dismiss();

                ((Dashboard)context).getUsersName();


            }
        });


        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();
        window.setLayout(RecyclerView.LayoutParams.FILL_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);

        dialog.show();
    }


    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView name, email, number;
        Button delete, update;
        ImageView image;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            number = itemView.findViewById(R.id.number);

            delete = itemView.findViewById(R.id.delete);
            update = itemView.findViewById(R.id.update);

            image = itemView.findViewById(R.id.imageShow);
        }
    }
}
