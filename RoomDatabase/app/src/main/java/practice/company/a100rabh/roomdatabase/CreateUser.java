package practice.company.a100rabh.roomdatabase;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 100RABH on 3/3/2018.
 */

public class CreateUser extends AppCompatActivity {

    private static final String TAG = "CreateUser";

    EditText first_name,last_name,email;
    Button addUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        first_name=findViewById(R.id.first_name);
        last_name=findViewById(R.id.last_name);
        email=findViewById(R.id.email);
        addUser=findViewById(R.id.add_user);

        final AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"production")
                .allowMainThreadQueries()
                .build();

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"item clicked");
                 db.userDAO().insertUser(new User(first_name.getText().toString(),last_name.getText().toString(),email.getText().toString()));
                startActivity(new Intent(CreateUser.this,MainActivity.class));

            }
        });
    }
}
