package com.trident.neomusic;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class SongsListAdapter extends RecyclerView.Adapter<SongsListAdapter.ViewHolder> implements BubbleTextGetter {
    private LayoutInflater inflater;
    List<Song> songs = Collections.emptyList();

    public SongsListAdapter(Context context, List<Song> songs) {
        inflater = LayoutInflater.from(context);
        this.songs = songs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.song_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(songs.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    @Override
    public String getTextToShowInBubble(int position) {
        return String.valueOf(songs.get(position).getTitle().charAt(0));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.song_title);
        }
    }
}