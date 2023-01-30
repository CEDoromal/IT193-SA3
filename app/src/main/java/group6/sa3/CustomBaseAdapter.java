package group6.sa3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    String listDogName[];
    String listDogBreed[];
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, String[] dogNameList, String[] dogBreedList){
        this.context = ctx;
        this.listDogName = dogNameList;
        this.listDogBreed = dogBreedList;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return listDogName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView dogNameTxt = (TextView) convertView.findViewById(R.id.dog_name);
        TextView dogBreedTxt = (TextView) convertView.findViewById(R.id.dog_breed);
        dogNameTxt.setText(listDogName[position]);
        dogBreedTxt.setText(listDogBreed[position]);
        return convertView;
    }
}
