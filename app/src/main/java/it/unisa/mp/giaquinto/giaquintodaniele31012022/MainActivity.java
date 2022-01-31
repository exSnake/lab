package it.unisa.mp.giaquinto.giaquintodaniele31012022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public ListView listView;
    CustomAdapter customAdapter;
    List<Item> items;
    private Drawable image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = getResources().getDrawable(R.drawable.ic_baseline_monetization_on_24);
        items = new ArrayList<Item>();
        if (savedInstanceState != null && savedInstanceState.get("items") != null) {
            items = (List<Item>) savedInstanceState.get("items");
        }
        initButtons();
        initList();
    }

    private void initList() {
        listView = findViewById(R.id.mylistview);
        customAdapter = new CustomAdapter(this, R.layout.item, new ArrayList<Item>());
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Elimina");
            builder.setPositiveButton("Elimina",(dialog, which) -> {
                Item i = customAdapter.getItem(position);
                customAdapter.remove(i);
                items.remove(i);
            });
            builder.setNegativeButton("Annulla", null);
            builder.show();
        });
        for(Item i: items) {
            customAdapter.add(i);
        }
    }

    private void initButtons() {
        Button compraBtn = findViewById(R.id.compraBtn);
        Button vendiBtn = findViewById(R.id.vendiBtn);
        TextView txtQuantity = findViewById(R.id.inputQuantity);
        compraBtn.setOnClickListener(v -> {
            addToList(txtQuantity, "Acquisto");
        });
        vendiBtn.setOnClickListener(v -> {
            addToList(txtQuantity, "Vendita");
        });
    }

    private void addToList(TextView txtQuantity, String acquisto) {
        Item i;
        if (!txtQuantity.getText().toString().equals("")) {
            int qnt = 0;
            try {
                qnt = Integer.parseInt(txtQuantity.getText().toString());
            } catch (Exception e) {
                Toast.makeText(this, "Inserisci una quantità valida", Toast.LENGTH_LONG).show();
                return;
            }
            if(qnt <= 0) {
                txtQuantity.setText("");
                Toast.makeText(this, "Inserisci una quantità valida", Toast.LENGTH_LONG).show();
                return;
            }
            i = new Item(image);
            i.setQuantita(Integer.parseInt(txtQuantity.getText().toString()));
            i.setDescrizione(acquisto);
            txtQuantity.setText("");
            items.add(i);
            customAdapter.add(i);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("items", (ArrayList<? extends Parcelable>) items);
    }
}