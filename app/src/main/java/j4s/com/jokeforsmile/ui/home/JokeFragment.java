package j4s.com.jokeforsmile.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;
import butterknife.OnTouch;
import j4s.com.jokeforsmile.R;
import timber.log.Timber;

public class JokeFragment extends Fragment {

    private String TAG = "JokeFragment";
    private LottieAnimationView lottieAnimationView;
    private LottieAnimationView animationForLoading;
    private HomeViewModel homeViewModel;
    private TextView askText;
    private TextView answerText;

    @OnTouch
    public boolean swapTouch(MotionEvent event) {
        switch (homeViewModel.checkMotionEvenSwap(event)) {
            case 1: {
                Timber.v("Right to left swap");
                lottieAnimationView.playAnimation();
                Toast.makeText(getContext(), "Joke saved to Database", Toast.LENGTH_LONG).show();

                break;
            }
            case 2: {
                Timber.v("Left to right swap");
                setVisibilityAfterSwapForNextJokes();
                animationForLoading.playAnimation();
                break;
            }
            case 0: {
                Timber.v("Different touch");
                break;
            }
        }
        return true;
    }

    private void setVisibilityAfterSwapForNextJokes() {
        animationForLoading.setVisibility(View.VISIBLE);
        answerText.setVisibility(View.INVISIBLE);
        askText.setVisibility(View.INVISIBLE);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke, container, false);
        ButterKnife.bind(this, root);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        // final Button button = root.findViewById(R.id.buttonChange);
         askText = root.findViewById(R.id.askJokes);
         answerText = root.findViewById(R.id.answerJokes);
        lottieAnimationView = root.findViewById(R.id.imageViewForAnimation);
        animationForLoading = root.findViewById(R.id.animationForLoading);
        homeViewModel.getJokeAsk().observe(this, s -> {askText.setText(s);
            setVisibilityAfterJokeUpdate();
            animationForLoading.pauseAnimation();
        });
        homeViewModel.getJokeAnswer().observe(this, s -> answerText.setText(s));


        return root;


    }

    private void setVisibilityAfterJokeUpdate() {
        animationForLoading.setVisibility(View.INVISIBLE);
        answerText.setVisibility(View.VISIBLE);
        askText.setVisibility(View.VISIBLE);
    }


}