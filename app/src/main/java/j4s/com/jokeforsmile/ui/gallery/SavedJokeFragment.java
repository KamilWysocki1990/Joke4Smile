package j4s.com.jokeforsmile.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import j4s.com.jokeforsmile.R;
import j4s.com.jokeforsmile.repository.RandomJokes;

public class SavedJokeFragment extends Fragment  {

    private SavedJokeAdapter savedJokeAdapter;

    @BindView(R.id.jokes_recycler)
    RecyclerView jokesRecycler;

    JokeRemoveListener jokeRemoveListener;

    interface JokeRemoveListener {
        void onRemoveJoke(int jokeId);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SavedJokeViewModel savedJokeViewModel = ViewModelProviders.of(this).get(SavedJokeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_saved_jokes, container, false);
        ButterKnife.bind(this, root);

        jokeRemoveListener = savedJokeViewModel::onJokeRemoved;
        setupRecycler();
        savedJokeViewModel.getAllJokes().observe(this, jokesList -> savedJokeAdapter.updateJokesList(jokesList));
        savedJokeViewModel.getJokeListFromDatabase();


        return root;
    }


    private void setupRecycler() {
        jokesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        savedJokeAdapter = new SavedJokeAdapter(jokeRemoveListener);
        jokesRecycler.setAdapter(savedJokeAdapter);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        jokesRecycler.addItemDecoration(decoration);
    }

}