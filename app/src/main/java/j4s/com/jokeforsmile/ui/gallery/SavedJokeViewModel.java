package j4s.com.jokeforsmile.ui.gallery;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import j4s.com.jokeforsmile.model.DatabaseFragmentModel;
import j4s.com.jokeforsmile.repository.RandomJokes;

public class SavedJokeViewModel extends ViewModel {

    private DatabaseFragmentModel databaseModel = new DatabaseFragmentModel();
    private MutableLiveData<List<RandomJokes>> savedJokes;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    LiveData<List<RandomJokes>> getAllJokes() {
        return savedJokes;
    }

    public SavedJokeViewModel() {
        savedJokes = new MutableLiveData<>();
    }

    void getJokeListFromDatabase() {
        compositeDisposable.add(databaseModel.getAllJokesFromDatabase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listRandomJokes -> {
                            savedJokes.setValue(listRandomJokes);
                        }, Throwable::printStackTrace));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

     void onJokeRemoved(int jokeId) {
        databaseModel.removeJoke(jokeId);
    }
}