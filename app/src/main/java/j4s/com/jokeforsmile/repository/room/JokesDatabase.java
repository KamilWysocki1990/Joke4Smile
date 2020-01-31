package j4s.com.jokeforsmile.repository.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import j4s.com.jokeforsmile.repository.RandomJokes;
import j4s.com.jokeforsmile.repository.dao.JokesDao;

@Database(entities = {RandomJokes.class},version = 1)
public abstract class JokesDatabase extends RoomDatabase {
   public static final String NAME = "JokeDataBase";
   public abstract JokesDao jokesDao();
}
