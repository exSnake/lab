package it.unisa.mp.giaquinto.giaquintodaniele31012022;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {
    private LayoutInflater inflater;

    public CustomAdapter(Context context, int resourceId, List<Item> objects) {
            super(context, resourceId, objects);
            inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            Log.d("DEBUG","Inflating view");
            v = inflater.inflate(R.layout.item, null);
        }

        Item i = getItem(position);

        Log.d("DEBUG","item i="+i);

        TextView txtDescrizione = v.findViewById(R.id.item_descrizione);
        TextView txtQuantita = v.findViewById(R.id.item_quantita);
        ImageView imgButton = v.findViewById(R.id.item_immagine);

        txtDescrizione.setText(i.getDescrizione());
        txtQuantita.setText(i.getQuantita().toString());
        imgButton.setImageDrawable(i.getImmagine());

        txtDescrizione.setTag(position);
        txtQuantita.setTag(position);
        imgButton.setTag(position);

        return v;
    }
}

