package com.example.finalproject_mobilproggamelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GameAdapter extends BaseAdapter {

    private final Context context;
    private final List<GameDB> gameList;

    public GameAdapter(Context context, List<GameDB> gameList) {
        this.context = context;
        this.gameList = gameList;
    }

    @Override
    public int getCount() {
        return gameList.size();
    }

    @Override
    public Object getItem(int position) {
        return gameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_game, parent, false);
        }

        GameDB game = gameList.get(position);

        TextView oyunisim = convertView.findViewById(R.id.gameName);
        TextView oyunfiyat = convertView.findViewById(R.id.gamePrice);
        ImageView oyunLogo = convertView.findViewById(R.id.imgOyunLogo);



        oyunisim.setText(game.getOyunisim());
        oyunfiyat.setText(game.getOyunfiyat());
        oyunLogo.setBackgroundResource(
                context.getResources().getIdentifier(String.valueOf(game.getOyunLogosu()), "drawable", context.getPackageName())
        );

        return convertView;
    }
}
