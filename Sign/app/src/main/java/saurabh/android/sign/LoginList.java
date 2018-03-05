package saurabh.android.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by SAURABH on 8/26/2017.
 */

public class LoginList extends AppCompatActivity {
    EditText editemail,editpass;
    CheckBox check;
    Button btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        editemail=(EditText)findViewById(R.id.edittextEmailLogin);
        editpass=(EditText)findViewById(R.id.edittextLoginPassword);
        check=(CheckBox)findViewById(R.id.checkboxLogin);
        btn=(Button)findViewById(R.id.buttonLogin);

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b)
                    editpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                else
                    editpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });
    }
}
