package com.android.semanadeeletronica.destincompleto;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.semanadeeletronica.destincompleto.adapter.ListAdapter;
import com.android.semanadeeletronica.destincompleto.model.Destination;
import com.android.semanadeeletronica.destincompleto.sample.SampleValues;
import com.android.semanadeeletronica.destincompleto.util.JSONDownloader;
import com.android.semanadeeletronica.destincompleto.util.Navigation;
import com.android.semanadeeletronica.destincompleto.util.Screen;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends Activity {

    //region LAYOUT VARIABLES
    //coloque aqui os layouts que precisam ser acessados via codigo

	/* private TextView txt; */
    private ListView listView;
    private ArrayList<Destination> destinationList;
    private Activity context;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    /**
     Inicializa os componentes de layout através de findViewById(R.id.xxx)
     */
    private void initComponents() {
	    /* txt = (TextView) findViewById(R.id.txt);*/
        listView = (ListView) findViewById(R.id.listView);
        this.context = this;

        // Infla o header e adiciona ao list view para deixar bonitinho :D
        View header = getLayoutInflater().inflate(R.layout.list_header, null);
        listView.addHeaderView(header);

        new DownloadTask().execute("");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Navigation.animate(this, Navigation.Animation.BACK);
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
        if (id == R.id.action_server) {
            new DownloadTask().execute("");
        } else if (id == R.id.action_static) {
            destinationList = SampleValues.getDestinationsList();
            ListAdapter adapter = new ListAdapter(context, destinationList);
            listView.setAdapter(adapter);
            listView.setDividerHeight(0);

            //listener que escuta quando um item da lista é clicado
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Navigation.navigate(context, Screen.Detalhes, Navigation.Animation.GO, destinationList.get(position - 1));
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    public class DownloadTask  extends AsyncTask<String, Long, ArrayList<Destination>> {

        protected ArrayList<Destination> doInBackground(String... args) {

            ArrayList<Destination> destinos = downloadDestinations();

            return destinos;
        }

        @Override
        protected void onPostExecute(ArrayList<Destination> result) {
            destinationList = result;
            ListAdapter adapter = new ListAdapter(context, destinationList);
            listView.setAdapter(adapter);
            listView.setDividerHeight(0);

            //listener que escuta quando um item da lista é clicado
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Navigation.navigate(context, Screen.Detalhes, Navigation.Animation.GO, destinationList.get(position - 1));
                }
            });
        }

        protected ArrayList<Destination> downloadDestinations() {
            Type destinosType = new TypeToken<ArrayList<Destination>>(){}.getType();
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            JSONDownloader downloader = new JSONDownloader();
            String result = downloader.downloaderJSONArray("http://destino.herokuapp.com/destinos.json");

            Log.i("Downloader", result);

            ArrayList<Destination> destinations = null;
            try {
                destinations = gson.fromJson(result, destinosType);
            } catch (Exception e) {
                Log.i("Post parse", e.getMessage());
            }
            return destinations;
        }
    }

}
