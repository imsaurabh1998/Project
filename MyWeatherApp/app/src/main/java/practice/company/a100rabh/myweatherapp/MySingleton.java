package practice.company.a100rabh.myweatherapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by 100RABH on 2/11/2018.
 */

public  class MySingleton {

    private static MySingleton mInstance;
    private RequestQueue queue;
    private static Context mContext;

    public MySingleton(Context context) {
        mContext = context;
        queue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(mContext);
        }
        return queue;
    }

    public static synchronized MySingleton singleton(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public void myRequestQueue(Request request) {
        queue.add(request);
    }


}