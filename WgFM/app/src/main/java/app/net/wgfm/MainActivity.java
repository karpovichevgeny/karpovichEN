package app.net.wgfm;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;


public   class MainActivity extends ActionBarActivity {

    Button play_button, pause_button;
    MediaPlayer player;
    TextView text_shown;
    String url1 = "http://81.25.32.234:8050/wgfm";
    public static RssItem selectedRssItem = null;
    String feedUrl = "";
    ListView rssListView = null;
    ArrayList<RssItem> rssItems = new ArrayList<RssItem>();
    ArrayAdapter<RssItem> aa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_shown = (TextView) findViewById(R.id.text_shown);
        play_button = (Button) findViewById(R.id.play_button);
        pause_button = (Button) findViewById(R.id.pause_button);



        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            player.setDataSource(url1);
        } catch (IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), "Check the URL!", Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            Toast.makeText(getApplicationContext(), "Check the URL!", Toast.LENGTH_LONG).show();
        } catch (IllegalStateException e) {
            Toast.makeText(getApplicationContext(), "Check the URL!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            player.prepare();
        } catch (IllegalStateException e) {
            Toast.makeText(getApplicationContext(), "Check the URL!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Check the URL!", Toast.LENGTH_LONG).show();
        }

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
                text_shown.setText("Playing...");
            }
        });
        pause_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
                text_shown.setText("Paused...");
            }
        });


        final TextView rssURLTV = (TextView) findViewById(R.id.rssURL);
        Button fetchRss = (Button) findViewById(R.id.fetchRss);
        fetchRss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                feedUrl = rssURLTV.getText().toString();
                TextView TVtitle=(TextView)findViewById(R.id.label);
                CharSequence cs="fetching";
                TVtitle.setText(cs);
                aa.notifyDataSetChanged();
                refressRssList();
                cs="Feed:";
                TVtitle.setText(cs);
            }
        });
        rssListView = (ListView) findViewById(R.id.rssListView);
        rssListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View view, int index,
                                    long arg3) {
                selectedRssItem = rssItems.get(index);

                // we call the other activity that shows a single rss item in
                // one page
                //Intent intent = new Intent("app.net.wgfm.displayRssItem");
                Intent intent = new Intent("app.net.wgtm.RssItemDisplayer");
                startActivity(intent);
            }
        });

        aa = new ArrayAdapter<RssItem>(this, R.layout.list_item, rssItems);
        rssListView.setAdapter(aa);
        feedUrl = rssURLTV.getText().toString();
        refressRssList();
    }


    private void refressRssList() {

        ArrayList<RssItem> newItems = RssItem.getRssItems(feedUrl);

        rssItems.clear();
        rssItems.addAll(newItems);
        aa.notifyDataSetChanged();

    }
}



