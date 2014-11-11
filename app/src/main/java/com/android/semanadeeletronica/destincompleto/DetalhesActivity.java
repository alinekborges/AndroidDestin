package com.android.semanadeeletronica.destincompleto;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.semanadeeletronica.destincompleto.model.Destination;
import com.android.semanadeeletronica.destincompleto.util.Navigation;
import com.android.semanadeeletronica.destincompleto.util.Screen;
import com.squareup.picasso.Picasso;


public class DetalhesActivity extends Activity {

    //region LAYOUT VARIABLES
    private TextView txtNome;
    private TextView txtRegiao;
    private TextView txtPais;
    private TextView txtCuriosidade;
    private ImageView imageView;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Destination d = (Destination) Navigation.getParameter(Screen.Detalhes);
        if (d != null) {
            txtNome = (TextView) findViewById(R.id.txtNome);
            txtRegiao = (TextView) findViewById(R.id.txtRegiao);
            txtPais = (TextView) findViewById(R.id.txtPais);
            txtCuriosidade = (TextView) findViewById(R.id.txtCuriosidade);
            imageView = (ImageView) findViewById(R.id.imageView);

            txtNome.setText(d.getNome());
            txtRegiao.setText(d.getRegiao());
            txtPais.setText(d.getPais());
            txtCuriosidade.setText(d.getCuriosidade());

            Picasso.with(this).load(d.getImage_url())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.holder)
                    .into(imageView);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.detalhes, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Navigation.animate(this, Navigation.Animation.BACK);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
