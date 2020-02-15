package j4s.com.jokeforsmile.model;

import java.util.List;

import io.reactivex.Observable;
import j4s.com.jokeforsmile.JokeForSmileApplication;
import j4s.com.jokeforsmile.repository.RandomJokes;
import j4s.com.jokeforsmile.repository.dao.JokesDao;

public class DatabaseFragmentModel {

    private JokesDao jokesDao = JokeForSmileApplication.getRoom().jokesDao();
    public Observable<List<RandomJokes>> getAllJokesFromDatabase() {
        return Observable.create(emitter -> {
            emitter.onNext(jokesDao.getAll());
        });
    }

    public void removeJoke(int jokeId) {
        jokesDao.delete(jokesDao.getJokeById(jokeId));
    }
}
