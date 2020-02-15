package j4s.com.jokeforsmile.repository.dao;


import android.util.Log;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import j4s.com.jokeforsmile.repository.RandomJokes;

@Dao
public interface JokesDao   {

    @Query("SELECT * FROM RandomJokes")
    List<RandomJokes> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RandomJokes randomJokes);

    @Delete
    void delete(RandomJokes randomJokes);

    @Query("SELECT * FROM RandomJokes WHERE id LIKE :jokeId LIMIT 1")
    RandomJokes getJokeById(int jokeId);

}
