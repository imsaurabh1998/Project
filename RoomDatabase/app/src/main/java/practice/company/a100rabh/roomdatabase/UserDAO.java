package practice.company.a100rabh.roomdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by 100RABH on 3/3/2018.
 */
@Dao
public interface UserDAO {

    @Query("SELECT * FROM user")
    List<User> getAllUser();

    @Insert
     void insertUser(User... users);
}
