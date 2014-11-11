package com.android.semanadeeletronica.destincompleto.util;

import android.os.AsyncTask;
import android.util.Log;

import com.android.semanadeeletronica.destincompleto.adapter.ListAdapter;
import com.android.semanadeeletronica.destincompleto.model.Destination;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Aline Borges on 11/10/2014.
 * Esta é a classe para download async de informacões da internet.
 * Este é apenas um modelo base, é mais fácil copiar e colar dentro
 * da MainActivity para ter acesso aos componentes da Activity
 */
public class DownloadTask  extends AsyncTask<String, Long, ArrayList<Destination>> {

        protected ArrayList<Destination> doInBackground(String... args) {

            ArrayList<Destination> destinos = downloadDestinations();

            return destinos;
        }

        @Override
        protected void onPostExecute(ArrayList<Destination> result) {
            //ListAdapter adapter = new ListAdapter(context, result);
            //listView.setAdapter(adapter);
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
