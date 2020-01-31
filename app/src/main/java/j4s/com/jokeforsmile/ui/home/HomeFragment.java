package j4s.com.jokeforsmile.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;
import butterknife.OnTouch;
import j4s.com.jokeforsmile.R;
import j4s.com.jokeforsmile.Utilities;
import timber.log.Timber;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;


    @OnTouch
    public boolean swapTouch(MotionEvent event){
        switch(homeViewModel.checkMotionEvenSwap(event)){
            case 1:{
                Timber.v("Right to left swap");
                break;
            }
            case 2:{
                Timber.v("Left to right swap");
                break;
            }
            case 0:{
                Timber.v("Different touch");
                break;
            }
        }
        return true;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,root);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
       // final Button button = root.findViewById(R.id.buttonChange);
        final TextView askText = root.findViewById(R.id.askJokes);
        final TextView answerText = root.findViewById(R.id.anserwJokes);

        homeViewModel.getJokeAsk().observe(this, s -> askText.setText(s));
        homeViewModel.getJokeAnswer().observe(this, s -> answerText.setText(s));




        return root;



    }







}