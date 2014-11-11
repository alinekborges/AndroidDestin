package com.semanadeeletronica.destincompleto;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.semanadeeletronica.destincompleto.adapter.ListAdapter;
import com.semanadeeletronica.destincompleto.model.Destination;
import com.semanadeeletronica.destincompleto.sample.SampleValues;
import com.semanadeeletronica.destincompleto.util.JSONDownloader;
import com.semanadeeletronica.destincompleto.util.Navigation;
import com.semanadeeletronica.destincompleto.util.Screen;
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

        //botoes de mudança de JSON e SAMPLE
        final View github = header.findViewById(R.id.GITHUB);
        final TextView json = (TextView) header.findViewById(R.id.JSON);
        final TextView sample = (TextView) header.findViewById(R.id.SAMPLE);

        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.navigate(context, Screen.Github, Navigation.Animation.GO);
            }
        });

        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.navigate(context, Screen.Github, Navigation.Animation.GO);
            }
        });

        json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask().execute("");
                json.setTextColor(getResources().getColor(android.R.color.white));
                sample.setTextColor(getResources().getColor(R.color.gray));
            }
        });

        sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sample.setTextColor(getResources().getColor(android.R.color.white));
                json.setTextColor(getResources().getColor(R.color.gray));
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
        });
        //new DownloadTask().execute("");
        //vou inicializar por padrao pelo sample
        destinationList = SampleValues.getDestinationsList();
        ListAdapter adapter = new ListAdapter(context, destinationList);
        listView.setAdapter(adapter);
        listView.setDividerHeight(0);

        //listener que escuta quando um item da lista é clicado
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Navigation.navigate(context, Screen.Detalhes, Navigation.Animation.GO, destinationList.get(position-1));
            }
        });
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
