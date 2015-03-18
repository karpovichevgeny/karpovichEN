package app.net.wgfm;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class RssItemDisplayer extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_item_displayer);

        RssItem selectedRssItem = MainActivity.selectedRssItem;
        Bundle extras = getIntent().getExtras();
//        TextView titleTv = (TextView)findViewById(R.id.titleTextView);
//        TextView contentTv = (TextView)findViewById(R.id.contentTextView);
//
//        String title = "";
//        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd - hh:mm:ss");
//        title = "\n" + selectedRssItem.getTitle() + "  ( "
//                + sdf.format(selectedRssItem.getPubDate()) + " )\n\n";
//
//        String content = "";
//        content += selectedRssItem.getLink() + "\n"
//                + selectedRssItem.getDescription()+"\n";
//        тупо убираю лишний верх
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl(selectedRssItem.getLink());

//        titleTv.setText(title);
//       contentTv.setText(content);
    }
}
