package juliavila.time;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by julia.vila on 22/02/2016.
 */
public class FragRegistros extends Fragment {

    private Context context;
    private PontoRepo repo;
    private ListView lvRegistros;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registros, container, false);

        context = view.getContext();
        repo = new PontoRepo(context);
        lvRegistros = (ListView) view.findViewById(R.id.lvRegistros);

        ArrayList<HashMap<String, String>> pontoList = repo.getPontoList();

        ArrayList<String> listStr = new ArrayList<String>();

        int i = 0;
        for (HashMap<String, String> hash : pontoList) {
            for (String current : hash.values()) {
                listStr.add(current);
                i++;
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                listStr );

        lvRegistros.setAdapter(arrayAdapter);

        return view;
    }

}
