package j4s.com.jokeforsmile.model;

import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import j4s.com.jokeforsmile.JokeForSmileApplication;
import j4s.com.jokeforsmile.repository.RandomJokes;
import j4s.com.jokeforsmile.repository.dao.JokesDao;
import j4s.com.jokeforsmile.repository.dao.JokesDaoInterface;
import j4s.com.jokeforsmile.repository.network.Api;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class JokeFragmentModel {


    JokesDao jokesDao = JokeForSmileApplication.getRoom().jokesDao();
    RandomJokes randomJokes;
    Retrofit retrofit = new Retrofit.Builder()
            // set base url for every request method defined in Api.class
            .baseUrl(Api.BASE_URL)
            // add Gson converter to deserialize JSON into POJO
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            // add RxJava2 call adapter
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            // return Retrofit instance
            .build();


    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Api api = retrofit.create(Api.class);

    public Single<RandomJokes> getDataFromApi() {
        return Single.create(emitter -> {
            Timber.v("Inside Emitter");
            compositeDisposable.add(api.getJokes()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            randomJokes -> {
                                // onNext
                                this.randomJokes = randomJokes;
                                Timber.v(randomJokes.getSetup());
                                Timber.v(randomJokes.getPunchline());
                                emitter.onSuccess(randomJokes);
                            },
                            throwable -> {
                                Timber.v("Error");
                                // onError
                            },
                            () -> {
                                // onCompleted

                            })
            );
        });
    }


    public void saveJokeToDatabase(){
        jokesDao.insert(randomJokes);
        Timber.e("Jokes Id" +jokesDao.getJokeById(randomJokes.getId()));
    }

}
