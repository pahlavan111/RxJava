package com.example.rxjava.fake_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjava.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataModelAdapter extends RecyclerView.Adapter<DataModelAdapter.DataModelHolder> {

    private List<DataModel> dataModelList;
    private Context context;


    public DataModelAdapter(List<DataModel> dataModelList, Context context) {
        this.dataModelList = dataModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public DataModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_raw, parent, false);
        return new DataModelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataModelHolder holder, int position) {

        final DataModel dataModel=dataModelList.get(position);
        holder.txt_title.setText(dataModel.getName());
        Picasso.get().load(dataModel.getLink()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "id is: "+dataModel.getId(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    static class DataModelHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView txt_title;

        DataModelHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.img_raw);
            txt_title= itemView.findViewById(R.id.txt_title_raw);

        }
    }
}
