package juliavila.time;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtHoraClick;
    private ListView lvRegistros;
    private String TAG = "MainActivity";
    private DateFormat dateFormat;
    private boolean clicado = false;
    private Ponto ponto;
    private PontoRepo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        repo = new PontoRepo(this);
        ponto = new Ponto();
        Button btnPonto = (Button) findViewById(R.id.btPonto);
        txtHoraClick = (TextView) findViewById(R.id.txtClick);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        lvRegistros = (ListView) findViewById(R.id.lvRegistros);

        txtHoraClick.setText("");
        btnPonto.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Date timeClick = new Date();
                        txtHoraClick.setText(dateFormat.format(timeClick.getTime()));
                        clicado = !clicado;

                        if (clicado) {
                            ponto.dataInicial = timeClick;

                        } else {
                            ponto.dataFinal = timeClick;
                            ponto.ponto_ID = 0;
                            repo.insert(ponto);
                            showToast();
                        }
                    }
                }
        );

        ArrayList<HashMap<String, String>> pontoList = repo.getPontoList();
//        if(pontoList.size() > 0) {
//            lvRegistros = getListView
//
//        }
        ArrayList<String> testelist = new ArrayList<String>();
//        testelist.add("teste1");
//        testelist.add("teste2");
//        testelist.add("teste3");

        int i = 0;
        for (HashMap<String, String> hash : pontoList) {
            for (String current : hash.values()) {
                testelist.add(current);
                i++;
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                testelist );

        lvRegistros.setAdapter(arrayAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showToast() {
        Toast.makeText(this, "Student Record updated", Toast.LENGTH_SHORT).show();
    }
}
