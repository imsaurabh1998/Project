package practice.company.a100rabh.roomdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by 100RABH on 3/3/2018.
 */
@Database(entities = {User.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDAO userDAO();
}
