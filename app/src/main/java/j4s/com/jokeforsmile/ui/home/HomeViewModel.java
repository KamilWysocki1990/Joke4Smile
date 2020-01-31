package j4s.com.jokeforsmile.ui.home;

import android.util.Log;
import android.view.MotionEvent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import j4s.com.jokeforsmile.model.JokeFragmentModel;
import timber.log.Timber;

public class HomeViewModel extends ViewModel {

    JokeFragmentModel jokeFragmentModel = new JokeFragmentModel();
    float x1, x2;

    private MutableLiveData<String> jokeAsk;
    private MutableLiveData<String> jokeAnswer;

    public HomeViewModel() {
        jokeAsk = new MutableLiveData<>();
        jokeAnswer = new MutableLiveData<>();
        //  mText.setValue("This is home fragment");
        observeDataFromModel();
    }

    public LiveData<String> getJokeAsk() {
        return jokeAsk;
    }

    public LiveData<String> getJokeAnswer() {
        return jokeAnswer;
    }

    private void observeDataFromModel() {
        Disposable d = jokeFragmentModel.getDataFromApi()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        randomJokes -> {
                            jokeAsk.setValue(randomJokes.getSetup());
                            jokeAnswer.setValue(randomJokes.getPunchline());

                        }, Throwable::printStackTrace);

    }
    public int checkMotionEvenSwap(MotionEvent event) {
        switch (event.getAction()) {
            //coordinates from  1st touch
            case MotionEvent.ACTION_DOWN: {
                x1 = event.getX();
                break;
            }
            case MotionEvent.ACTION_UP: {
                x2 = event.getX();
                // left to right swap
                if (x1 < x2) {
                    Timber.v("x1 : "+ x1 + "x2 : "+x2);
                    observeDataFromModel();
                    jokeFragmentModel.getDataFromApi();
                    return 2;
                }

                // right to left swap
                if (x1 > x2) {
                    return 1;


                }

            }

        }
        return 0;
    }
}