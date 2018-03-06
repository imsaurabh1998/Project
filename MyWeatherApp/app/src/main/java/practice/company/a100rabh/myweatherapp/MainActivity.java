package practice.company.a100rabh.myweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btn;
    EditText edt;

    String baseUrl="&appid=1050c34d226bf38d566453250e4bbf11";
    String url="http://api.openweathermap.org/data/2.5/weather?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.textView2);
        btn=(Button)findViewById(R.id.button);
        edt=(EditText)findViewById(R.id.editText2);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myUrl=url+edt.getText().toString()+baseUrl;
                Log.i("URL","URL IS "+myUrl);

                JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, myUrl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                 try {

                                    String str=response.getString("weather");

                                    Log.i("Weather","Weather "+str);

                                    JSONArray arr=new JSONArray(str);

                                    for(int i=0;i<arr.length();i++){
                                        JSONObject JObj=arr.getJSONObject(i);
                                        String result=JObj.getString("main");
                                        Log.i("result","result"+result);
                                        tv.setText(result);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("Error","Error"+error);
                            }
                        }

                );

                MySingleton.singleton(MainActivity.this).myRequestQueue(request);
            }
        });
    }
}
