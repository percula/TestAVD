package com.perculacreative.peter.testavd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean toEmptySun;
    public enum SunState {FULL_SUN, HALF_SUN, NO_SUN};
    private SunState currentSun;

    private static final int[] STATE_SET_FULL_SUN =
            {R.attr.state_full_sun, -R.attr.state_half_sun, -R.attr.state_no_sun};
    private static final int[] STATE_SET_HALF_SUN =
            {-R.attr.state_full_sun, R.attr.state_half_sun, -R.attr.state_no_sun};
    private static final int[] STATE_SET_NO_SUN =
            {-R.attr.state_full_sun, -R.attr.state_half_sun, R.attr.state_no_sun};



    private boolean toStop;
    public enum PlayState {PLAY, PAUSE, STOP};
    private PlayState currentPlayState;

    private static final int[] STATE_SET_PLAY =
            {R.attr.state_play, -R.attr.state_pause, -R.attr.state_stop};
    private static final int[] STATE_SET_PAUSE =
            {-R.attr.state_play, R.attr.state_pause, -R.attr.state_stop};
    private static final int[] STATE_SET_STOP =
            {-R.attr.state_play, -R.attr.state_pause, R.attr.state_stop};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView optimalSunIcon = (ImageView) findViewById(R.id.optimal_sun_icon);
        final ImageView playPauseStop = (ImageView) findViewById(R.id.play_pause_stop);

        toEmptySun = true;
        currentSun = SunState.FULL_SUN;
        optimalSunIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sun Clicked " + currentSun.toString(), Toast.LENGTH_SHORT).show();
                if (currentSun == SunState.FULL_SUN) {
                    optimalSunIcon.setImageState(STATE_SET_HALF_SUN, true);
                    currentSun = SunState.HALF_SUN;
                    toEmptySun = true;
                } else if (currentSun == SunState.HALF_SUN && toEmptySun) {
                    optimalSunIcon.setImageState(STATE_SET_NO_SUN, true);
                    currentSun = SunState.NO_SUN;
                } else if (currentSun == SunState.HALF_SUN && !toEmptySun) {
                    optimalSunIcon.setImageState(STATE_SET_FULL_SUN, true);
                    currentSun = SunState.FULL_SUN;
                } else if (currentSun == SunState.NO_SUN){
                    optimalSunIcon.setImageState(STATE_SET_HALF_SUN, true);
                    toEmptySun = false;
                    currentSun = SunState.HALF_SUN;
                }
            }
        });


        toStop = true;
        currentPlayState = PlayState.PLAY;
        playPauseStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "PlayPauseStop Clicked " + currentSun.toString(), Toast.LENGTH_SHORT).show();
                if (currentPlayState == PlayState.PLAY) {
                    playPauseStop.setImageState(STATE_SET_PAUSE, true);
                    currentPlayState = PlayState.PAUSE;
                    toStop = true;
                } else if (currentPlayState == PlayState.PAUSE && toStop) {
                    playPauseStop.setImageState(STATE_SET_STOP, true);
                    currentPlayState = PlayState.STOP;
                } else if (currentPlayState == PlayState.PAUSE && !toStop) {
                    playPauseStop.setImageState(STATE_SET_PLAY, true);
                    currentPlayState = PlayState.PLAY;
                } else if (currentPlayState == PlayState.STOP){
                    playPauseStop.setImageState(STATE_SET_PAUSE, true);
                    toStop = false;
                    currentPlayState = PlayState.PAUSE;
                }
            }
        });
    }
}
