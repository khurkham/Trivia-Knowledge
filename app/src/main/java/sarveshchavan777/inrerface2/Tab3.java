package sarveshchavan777.inrerface2;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;
import com.google.example.games.basegameutils.BaseGameUtils;

import java.util.List;

import info.hoang8f.widget.FButton;
import sarveshchavan777.inrerface2.application.AppController;

public class Tab3 extends Fragment {
    FButton achievement;
    private static final int REQUEST_ACHIEVEMENTS =9004;

    /* GoogleApiClient client = AppController.getInstance().getClient();*/
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab03,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        achievement=(FButton) getView().findViewById(R.id.achievementButton);
        final Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/shablagooital.ttf");
        achievement.setTypeface(typeface);

        achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.mGoogleApiClient.isConnected()){
                    startActivityForResult(Games.Achievements.getAchievementsIntent(MainActivity.mGoogleApiClient),
                            REQUEST_ACHIEVEMENTS);
                }else {
                    BaseGameUtils.makeSimpleDialog(getActivity(), getString(R.string.achievement_not_available)).show();
                }

                if(checkSound()){
                    MediaPlayer ring= MediaPlayer.create(getActivity(),R.raw.gameaudio2);
                    ring.start();
                }
            }
        });
    }

    public Boolean checkSound(){
        DemoHelperClass demoHelperClass=new DemoHelperClass(getActivity());
        List list=demoHelperClass.getSound();
        if(list!=null){
            if( list.size()%2==0 ){
                //  Toast.makeText(getActivity(),"true",Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }

}