package app.net.wgfm;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public  class MainActivity extends ActionBarActivity implements  View.OnClickListener {

    Button play_button, pause_button;
    MediaPlayer player;
    TextView text_shown;
    String url="http://108.168.175.174:7120";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url="http://108.168.175.174:7120";
        player = new MediaPlayer();
        try{
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(url);
            player.prepareAsync();
        }
        catch (Exception e){
            Log.i("ExceptionOnCreate:", e.getMessage());
        }

        text_shown =(TextView) findViewById(R.id.text_shown);
        play_button = (Button) findViewById(R.id.play_button);
        pause_button = (Button) findViewById(R.id.pause_button);
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_shown.setText("Playing...");
                player.start();
            }
        });
        pause_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
                text_shown.setText("Paused...");
            }
        });
//        getInit();
    }

    public void mediaPlayerStream(){
        try{
            String url="http://108.168.175.174:7120";
            MediaPlayer player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(url);
            player.prepare();
        }
        catch (Exception e){
            Log.i("ExceptionOnCreate:", e.getMessage());
        }
        }

//
//    public void getInit() {
//        text_shown =(TextView) findViewById(R.id.text_shown);
//        play_button = (Button) findViewById(R.id.play_button);
//        pause_button = (Button) findViewById(R.id.pause_button);
//        play_button.setOnClickListener(this);
//        pause_button.setOnClickListener(this);
//    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.play_button:
                text_shown.setText("Playing...");
                player.start();
                break;
            case R.id.pause_button:
                player.pause();
                text_shown.setText("Paused...");
                break;
        }
    }



}
