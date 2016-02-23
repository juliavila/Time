package juliavila.time;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by julia.vila on 23/02/2016.
 */
public class FragPonto extends Fragment{

    private TextView txtHoraClick;
    private String TAG = "MainActivity";
    private DateFormat dateFormat;
    private boolean clicado = false;
    private Ponto ponto;
    private PontoRepo repo;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ponto, container, false);

        context = view.getContext();
        repo = new PontoRepo(context);
        ponto = new Ponto();
        Button btnPonto = (Button) view.findViewById(R.id.btPonto);
        txtHoraClick = (TextView) view.findViewById(R.id.txtClick);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


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
                            showToast("batata");
                        }
                    }
                }
        );

        return view;

    }

    private void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

}


