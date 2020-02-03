package j4s.com.jokeforsmile;

import android.app.Application;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.room.Room;
import j4s.com.jokeforsmile.repository.room.JokesDatabase;
import timber.log.Timber;

public class JokeForSmileApplication extends Application {


    private static JokesDatabase database;


    public static JokesDatabase getRoom() {
        return database;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new MyDebugTree());
        Timber.e("onCreate from Application");

        database = Room.databaseBuilder(this, JokesDatabase.class,JokesDatabase.NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
//// Dagger 2
//        appComponent = DaggerAppComponent
//                .builder()
//                .appModule(new AppModule(this))
//                .dataModule(new DataModule())
//                .build();


    }
}

class MyDebugTree extends Timber.DebugTree {
    private static final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");
    private final String customTag;

    MyDebugTree() {
        this.customTag = "JokeForSmile";
    }

    @Override
    protected @Nullable
    String createStackElementTag(@NotNull StackTraceElement element) {
        String tag = element.getClassName();
        Matcher m = ANONYMOUS_CLASS.matcher(tag);
        if (m.find()) {
            tag = m.replaceAll("");
        }
        tag = tag.substring(tag.lastIndexOf('.') + 1);
        return customTag + tag;
    }
}
