package com.semanadeeletronica.destincompleto.util;

import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by RickEraser on 02/06/14.
 * <p/>
 * Para utilizar basta instanciar a classe.
 * <p/>
 * <p/>
 * <p/>
 * JSONDownLoader jsonDownLoader = new JSONDownLoader();
 * <p/>
 * Metodo para retornar um objeto.
 * JSONObject jsonObject = jsonDownLoader.downloaderJSONObject("SUA URL");
 * <p/>
 * <p/>
 * Metodo para retornar um array.
 * JSONArray jsonArray = jsonDownLoader.downloaderJSONArray("SUA URL");
 */
public class JSONDownloader {
    static String LOG_TAG = "JSONDownLoaderMAKER";
    JSONObject retornoJSON;


    public JSONObject downloaderJSONObject(String JSOnObject) {

        if (JSOnObject != null) {
            Log.i(LOG_TAG, "downloaderJSONObject: " + JSOnObject);


            // Cria a URL
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 3000);
            HttpConnectionParams.setSoTimeout(httpParameters, 3000);

            HttpClient httpClient = new DefaultHttpClient(httpParameters);
            try {

                HttpGet httpGet = new HttpGet(JSOnObject);
                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                String result = EntityUtils.toString(httpEntity, HTTP.UTF_8);
                JSONObject retornoJSON = new JSONObject();
                if (!result.equals("")) {
                    retornoJSON = new JSONObject(result);
                    Log.i(LOG_TAG, "Retorno JSONObject: " + retornoJSON.toString());
                }
                return retornoJSON;
            } catch (IOException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);

            } catch (Exception e) {
                Log.e(LOG_TAG, e.getMessage(), e);
            }

        } else {
            return retornoJSON;
        }
        return retornoJSON;
    }


    public String downloaderJSONArray(String urlJSONArray) {
        JSONArray retornoArrayJSON = new JSONArray();
        if (urlJSONArray != null) {
            Log.i(LOG_TAG, "downloaderJSONArray: " + urlJSONArray);


            // Cria a URL
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 3000);
            HttpConnectionParams.setSoTimeout(httpParameters, 3000);

            HttpClient httpClient = new DefaultHttpClient(httpParameters);
            try {

                HttpGet httpGet = new HttpGet(urlJSONArray);
                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                String result = EntityUtils.toString(httpEntity, HTTP.UTF_8);

                //retornoArrayJSON = new JSONArray(result);

                Log.i(LOG_TAG, "Retorno SONArray: " + retornoArrayJSON.toString());

                return result;
            } catch (IOException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
            } catch (Exception e) {
                Log.e(LOG_TAG, e.getMessage(), e);
            }

        } else {
            return "";
        }
        return "";
    }


    public JSONObject downloaderJSONObjectPOST(String urlLink, List<NameValuePair> listaPerguntas) {

        if (urlLink != null) {
            Log.i(LOG_TAG, "downloaderJSONObject: " + urlLink);


            // Cria a URL
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 3000);
            HttpConnectionParams.setSoTimeout(httpParameters, 3000);

            HttpClient httpClient = new DefaultHttpClient(httpParameters);
            try {

                HttpPost httpPost = new HttpPost(urlLink);
                httpPost.setEntity(new UrlEncodedFormEntity(listaPerguntas));
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity httpEntity = response.getEntity();
                String result = EntityUtils.toString(httpEntity, HTTP.UTF_8);


                JSONObject retornoJSON = new JSONObject(result);

                Log.i(LOG_TAG, "Retorno JSONObject: " + retornoJSON.toString());


                return retornoJSON;
            } catch (IOException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
            } catch (Exception e) {
                Log.e(LOG_TAG, e.getMessage(), e);
            }

        } else {
            return null;
        }
        return null;
    }


}
