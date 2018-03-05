package saurabh.android.sign;

/**
 * Created by SAURABH on 8/26/2017.
 */

public class SignUp {

    String Sign_ID;
    String Sign_Name;
    String Sign_Last;
    String Sign_Email;
    String Password;


    public SignUp(){

    }

    public SignUp(String sign_ID, String sign_Name, String sign_Last, String sign_Email, String password) {
        this.Sign_ID = sign_ID;
        this.Sign_Name = sign_Name;
        this.Sign_Last = sign_Last;
        this.Sign_Email = sign_Email;
        this.Password = password;
    }

    public String getSign_ID() {
        return Sign_ID;
    }

    public String getSign_Name() {
        return Sign_Name;
    }

    public String getSign_Last() {
        return Sign_Last;
    }

    public String getSign_Email() {
        return Sign_Email;
    }

    public String getPassword() {
        return Password;
    }
}
