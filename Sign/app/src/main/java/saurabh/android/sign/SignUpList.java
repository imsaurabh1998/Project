package saurabh.android.sign;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.security.KeyStore;

/**
 * Created by SAURABH on 8/26/2017.
 */

public class SignUpList extends AppCompatActivity {
        EditText editname,editlast,editemail,editpass;
        CheckBox checkBox;
    Button btn;

    DatabaseReference databaseSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_layout);
        editname=(EditText)findViewById(R.id.edittextNameSignup);
        editlast=(EditText)findViewById(R.id.edittextLastSignup);
        editemail=(EditText)findViewById(R.id.edittextEmailSignup);
        editpass=(EditText)findViewById(R.id.edittextPasswordSignup);
        checkBox=(CheckBox)findViewById(R.id.checkboxSignUp);
        btn=(Button)findViewById(R.id.buttonSignup);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b)
                    editpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                else
                    editpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        databaseSignUp= FirebaseDatabase.getInstance().getReference("SignUp");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue();
            }
        });

    }

    public void addValue(){

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo datac = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null & datac != null)
                && (wifi.isConnected() | datac.isConnected())) {
            setContentView(R.layout.sign_layout);
        }else{
            //no connection
            Toast toast = Toast.makeText(SignUpList.this, "No Internet Connection", Toast.LENGTH_LONG);
            toast.show();
        }

        String name=editname.getText().toString();
        String last=editlast.getText().toString();
        String email=editemail.getText().toString();
        String pass=editpass.getText().toString();


        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(last)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(pass)){

            String id=databaseSignUp.push().getKey();
            SignUp signUp=new SignUp(id,name,last,email,pass);

            databaseSignUp.child(id).setValue(signUp);
            Toast.makeText(this, "Values Added", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Please enter the value", Toast.LENGTH_SHORT).show();
    }
}
