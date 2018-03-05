package com.example.saurabh.camera;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void call(View v)
    {
        Intent i;
        //get id from the view object and
        //perform action according to button id
        switch(v.getId())
        {
            case R.id.internet:
                //if id is internet than open net
                //use action view because we want to view the given website page
                i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.innosen.blogspot.com"));
                startActivity(i);break;
            case R.id.fb:
                //if id is fb than open facebook
                //use action view because we want to view the given website page
                i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"));
                startActivity(i);break;
            case R.id.phone:
                //if id is phone than open phone dialer
                //use action dial because we want to open phone dialer
                i=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:8527801400"));
                startActivity(i);break;
            case R.id.bigb:
                //if id is bigb than call this no. +918527801400
                //use action call because we want to call on the given no.
                i=new Intent(Intent.ACTION_CALL, Uri.parse("tel:+918527801400"));
                startActivity(i);break;
            case R.id.cam:
                //if id is cam than use camera driver path to open camera
                i=new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(i);break;
            case R.id.sv:
                //if id is cam than use camera driver path to open camera
                i=new Intent(Intent.ACT);
                startActivity(i);break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
