package group6.sa3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import group6.sa3.model.Dog;

public class CustomBaseAdapter extends BaseAdapter {
    Context context;
    List<Dog> dogList;
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, List<Dog> dogList){
        this.context = ctx;
        this.dogList = dogList;
        this.inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return dogList.size();
    }

    @Override
    public Object getItem(int position) {
        return dogList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dogList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView dogNameTxt = (TextView) convertView.findViewById(R.id.dog_name);
        TextView dogBreedTxt = (TextView) convertView.findViewById(R.id.dog_breed);
        dogNameTxt.setText(dogList.get(position).getName());
        dogBreedTxt.setText(dogList.get(position).getName());
        return convertView;
    }
}
