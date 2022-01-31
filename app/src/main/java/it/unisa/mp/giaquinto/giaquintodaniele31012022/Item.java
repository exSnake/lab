package it.unisa.mp.giaquinto.giaquintodaniele31012022;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Item implements Parcelable {
    private String descrizione;
    private Integer quantita;
    private Drawable immagine;

    public Item(String descrizione, int quantita, Drawable immagine) {
        this.descrizione = descrizione;
        this.quantita = quantita;
        this.immagine = immagine;
    }

    public Item(Drawable drawable) {
        this.immagine = drawable;
    }

    protected Item(Parcel in) {
        descrizione = in.readString();
        if (in.readByte() == 0) {
            quantita = null;
        } else {
            quantita = in.readInt();
        }
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getDescrizione() {
        return descrizione;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public Drawable getImmagine() {
        return immagine;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return descrizione;
    }

    @NonNull
    @Override
    protected Item clone() {
        Item i = new Item(this.getDescrizione(), this.getQuantita(), this.getImmagine());
        return i;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(descrizione);
        if (quantita == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(quantita);
        }
    }
}
