package com.example.gautamnarayanan.t3;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Gautam Narayanan on 7/15/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomView> {

    ArrayList<PokeObject> list;
    Context context;

    public CustomAdapter(Context context,ArrayList<PokeObject> list) {
        this.list = list;
        this.context=context;
    }

    @Override
    public CustomView onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.custom_view,parent,false);
        CustomView viewholder = new CustomView(view);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(final CustomView holder, final int position) {

        holder.Gt1.setText(list.get(position).getName());
        Picasso.with(context).load(list.get(position).getUrl()).resize(500,500).into(holder.Gi1);
        holder.Gb1.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View view)
                    {
                        delete(position);
                    }
                }

        );
    }

    public void delete(int position) { //removes the row
        list.remove(position);
        notifyItemRemoved(position);
    }


    public int getItemCount() {
        return list.size();
    }

    class CustomView extends RecyclerView.ViewHolder{

        EditText Gt1;
        ImageButton Gb1;
        ImageView Gi1;


        public CustomView(View itemView) {
            super(itemView);
            Gt1=(EditText)itemView.findViewById(R.id.Gt1);
            Gt1.setEnabled(false);
            Gb1=(ImageButton) itemView.findViewById(R.id.Gb1);
            Gi1=(ImageView)itemView.findViewById(R.id.Gi1);

        }


    }


}
