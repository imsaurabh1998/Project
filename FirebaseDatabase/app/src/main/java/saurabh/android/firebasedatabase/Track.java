package saurabh.android.firebasedatabase;

/**
 * Created by SAURABH on 8/25/2017.
 */

public class Track {

    String Track_id;
    String Track_name;
    int Track_seekbar;

    public Track(){

    }

    public Track(String track_id, String track_name, int track_seekbar) {
        Track_id = track_id;
        Track_name = track_name;
        Track_seekbar = track_seekbar;
    }

    public String getTrack_id() {
        return Track_id;
    }

    public String getTrack_name() {
        return Track_name;
    }

    public int getTrack_seekbar() {
        return Track_seekbar;
    }
}
