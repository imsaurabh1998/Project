package saurabh.android.firebasedatabase;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edittext;
    Spinner spinner;
    Button button;
    DatabaseReference databaseFirebase;

    ListView listviewArtist;
    List<Artist> artistList;

    public static final String ARTIST_NAME="artistname";
    public static final String ARTIST_ID="artistid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        databaseFirebase= FirebaseDatabase.getInstance().getReference("Artist");

        edittext=(EditText)findViewById(R.id.edittextName);
        spinner=(Spinner)findViewById(R.id.spinner);
        button=(Button)findViewById(R.id.buttonData);
        listviewArtist=(ListView)findViewById(R.id.listviewFetch);
        artistList=new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            addArtist();

            }
        });

        listviewArtist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),ArtistTrack.class);
                Artist artist=artistList.get(i);

                intent.putExtra(ARTIST_NAME,artist.getArtist_name());
                intent.putExtra(ARTIST_ID,artist.getArtist_id());
                startActivity(intent);
            }
        });

       listviewArtist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

               Artist artist=artistList.get(i);

               updateDialog(artist.getArtist_id(),artist.getArtist_name());
               return false;
           }
       });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artistList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Artist artist=snapshot.getValue(Artist.class);

                    artistList.add(artist);
                }
                ArtistList adapter=new ArtistList(MainActivity.this,artistList);
                listviewArtist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void updateDialog(final String artist_id, String name){

        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

        LayoutInflater inflater=getLayoutInflater();

        View dialogView=inflater.inflate(R.layout.list_layout_update,null);
        alertDialog.setView(dialogView);

        final EditText editupdate=(EditText)dialogView.findViewById(R.id.edittextUpdate);
        final Spinner spinnerupdate=(Spinner)dialogView.findViewById(R.id.spinnerupdate);
        final Button buttonupdate=(Button)dialogView.findViewById(R.id.buttonUpdate);
        final Button buttondelete=(Button)dialogView.findViewById(R.id.buttonDelete);




        alertDialog.setTitle("Update "+name);

        final AlertDialog alert=alertDialog.create();
        alert.show();

        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editupdate.getText().toString();
                String spinner=spinnerupdate.getSelectedItem().toString();

                if(TextUtils.isEmpty(name)){
                    edittext.setError("Error occured");
                    return;
                }
                updateAssist(artist_id,name,spinner);
                alert.dismiss();

            }
        });


        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAssist(artist_id);
                alert.dismiss();
            }
        });

    }

    public boolean updateAssist(String id, String name, String genre){

        DatabaseReference data=FirebaseDatabase.getInstance().getReference("Artist").child(id);

        Artist artist=new Artist(id,name,genre);
        data.setValue(artist);
        Toast.makeText(this, "Value Are updated", Toast.LENGTH_SHORT).show();

        return true;
    }

    public void deleteAssist(String id){

        DatabaseReference DBArtist=FirebaseDatabase.getInstance().getReference("Artist").child(id);
        DatabaseReference DBDelete=FirebaseDatabase.getInstance().getReference("tracks").child(id);

        DBArtist.removeValue();
        DBDelete.removeValue();

        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }



    public void addArtist(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo datac = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null & datac != null)
                && (wifi.isConnected() | datac.isConnected())) {
            setContentView(R.layout.activity_main);
        }else{
            //no connection
            Toast toast = Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_LONG);
            toast.show();
        }

        String name=edittext.getText().toString();
        String genre=spinner.getSelectedItem().toString();


        if(!TextUtils.isEmpty(name)){
            String id = databaseFirebase.push().getKey();
            Artist artist = new Artist(id, genre, name);

            databaseFirebase.child(id).setValue(artist);
            Toast.makeText(this, "artist Added", Toast.LENGTH_SHORT).show();


        }else
        Toast.makeText(this, "Enter Something In Name field", Toast.LENGTH_SHORT).show();

    }


}
