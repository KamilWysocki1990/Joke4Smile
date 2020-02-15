package j4s.com.jokeforsmile.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import j4s.com.jokeforsmile.R;
import j4s.com.jokeforsmile.repository.RandomJokes;


public class SavedJokeAdapter extends RecyclerView.Adapter<SavedJokeAdapter.JokeHolder> {


   private SavedJokeFragment.JokeRemoveListener jokeRemoveListener;


    private List<RandomJokes> jokesList = new ArrayList<>();

    public SavedJokeAdapter(SavedJokeFragment.JokeRemoveListener jokeDeleteListener) {
        this.jokeRemoveListener = jokeDeleteListener;
    }


    void updateJokesList(List<RandomJokes> jokes) {
        jokesList.addAll(jokes);
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public JokeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View jokeHolderView = layoutInflater.inflate(R.layout.item_joke, parent, false);
        return new JokeHolder(jokeHolderView);
    }

    @Override
    public void onBindViewHolder(JokeHolder holder, int position) {
        holder.removeJokeIcon.setOnClickListener( view -> {
            jokeRemoveListener.onRemoveJoke(jokesList.get(holder.getAdapterPosition()).getId());
            jokesList.remove(jokesList.get(holder.getAdapterPosition()));
            notifyItemRemoved(holder.getAdapterPosition());
        });
        holder.setupJoke(jokesList.get(position));
    }

    @Override
    public int getItemCount() {
        return jokesList.size();
    }

    class JokeHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_joke_ask)
        TextView jokeAsk;
        @BindView(R.id.item_joke_answer)
        TextView jokeAnswer;
        @BindView(R.id.item_joke_remove_icon)
        ImageView removeJokeIcon;

         JokeHolder(View jokeHolderView) {
            super(jokeHolderView);
            ButterKnife.bind(this, jokeHolderView);

           /* removeJokeIcon.setOnClickListener(view -> {
                jokeRemoveListener.onRemoveJoke(jokesList.get(getAdapterPosition()).getId());
                jokesList.remove(jokesList.get(getAdapterPosition()));
                notifyItemRemoved(getAdapterPosition());
            });*/
        }

         void setupJoke(RandomJokes randomJokes) {
            jokeAsk.setText(randomJokes.getSetup());
            jokeAnswer.setText(randomJokes.getPunchline());
        }
    }


}
