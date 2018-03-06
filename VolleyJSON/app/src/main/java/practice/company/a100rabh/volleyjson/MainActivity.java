package practice.company.a100rabh.volleyjson;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String myUrl="http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";


        Button btn=(Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, myUrl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {


                                try {
                                    // this code when json object are in an Array
                                    String str=response.getString("coord");

                                    Log.i("object","Sample "+str);

                                    JSONArray arr=new JSONArray(str);

                                    for(int i=0;i<arr.length();i++){
                                        JSONObject JObj=arr.getJSONObject(i);
                                        Log.i("longitude","lon is here"+JObj.getString("lon"));
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                //first option to get the json object when a json object normally declare without in array
//                                    JSONObject obj1=new JSONObject(obj);
//
//                                    String o2=obj1.getString("lon");
//                                    String o1=obj1.getString("lat");
//
//                                    Log.i("lat","lat is"+o1);
//                                    Log.i("lon","lon is"+o2);

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
