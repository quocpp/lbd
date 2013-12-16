package com.LBD.app;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.webkit.WebView;
import android.widget.GridView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        Log.d("BEAN", "START");

        new getHtml().execute("http://www.livescore.com/");
        //new getHtml().execute("http://www.livescore.com/soccer/england/premier-league/fixtures/7-days/");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    private class getHtml extends AsyncTask<String, Void, ArrayList<ArrayList<String>>> {
        @Override
        protected ArrayList<ArrayList<String>> doInBackground(String... urls) {
            Document doc = null;
            ArrayList<ArrayList<String>> lbdTable = new ArrayList<ArrayList<String>>();
            SimpleDateFormat timeFormatgmt = new SimpleDateFormat("HH:mm");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            timeFormatgmt.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                doc = Jsoup.connect(urls[0]).userAgent("Mozilla").get();
                Elements table = doc.getElementsByClass("league-table");
                for (int j = 0; j < table.size(); j++) {
                    Elements evens = table.get(j).getElementsByTag("tr");
                    for (int i = 0; i < evens.size(); i++) {
                        ArrayList<String> rowTable = new ArrayList<String>();
                        String tag = evens.get(i).childNode(1).nodeName();
                        if (evens.get(i).childNode(1).nodeName() == "td") {
                            rowTable.add("1");
                            String stime = evens.get(i).getElementsByClass("fd").get(0).text();
                            try {
                                Date time = timeFormatgmt.parse(stime);
                                rowTable.add(timeFormat.format(time));
                            } catch (Exception e) {
                                rowTable.add(stime);
                            }
                            rowTable.add(evens.get(i).getElementsByClass("fh").get(0).text());
                            rowTable.add(evens.get(i).getElementsByClass("fs").get(0).text());
                            rowTable.add(evens.get(i).getElementsByClass("fa").get(0).text());
                        } else {
                            rowTable.add("0");
                            rowTable.add(evens.get(i).text());
                        }
                        // }
                        lbdTable.add(rowTable);
                    }
                }

            } catch (Exception e) {
                Log.d("BEAN", e.toString());
            }

            return lbdTable;
        }

        public void onPostExecute(ArrayList<ArrayList<String>> table) {
            GridView gridview = (GridView) findViewById(R.id.gridview);
            gridview.setAdapter(new DataAdapter(com.LBD.app.MainActivity.this, table));
        }
    }
}


