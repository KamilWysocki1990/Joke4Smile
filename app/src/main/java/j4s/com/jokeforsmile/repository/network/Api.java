package j4s.com.jokeforsmile.repository.network;


import io.reactivex.Flowable;
import io.reactivex.Single;
import j4s.com.jokeforsmile.repository.RandomJokes;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://official-joke-api.appspot.com/";
    @GET("random_ten")
    Flowable<RandomJokes> getTenJokes();
    @GET("random_joke")
    Flowable<RandomJokes> getJokes();


}
