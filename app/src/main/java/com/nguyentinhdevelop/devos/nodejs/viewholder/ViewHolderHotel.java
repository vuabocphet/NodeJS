package com.nguyentinhdevelop.devos.nodejs.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyentinhdevelop.devos.nodejs.R;

public class ViewHolderHotel extends RecyclerView.ViewHolder {
    public TextView name,city,address,owner,license_number,total_floor;
    public ImageView img;
    public ImageButton btndelete,btnEdit;
    public ViewHolderHotel(View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        city=itemView.findViewById(R.id.city);
        address=itemView.findViewById(R.id.address);
        owner=itemView.findViewById(R.id.owner);
        license_number=itemView.findViewById(R.id.license_number);
        total_floor=itemView.findViewById(R.id.total_floor);
        img=itemView.findViewById(R.id.img);
        btndelete=itemView.findViewById(R.id.btndelete);
        btnEdit=itemView.findViewById(R.id.btnedit);

    }
}
