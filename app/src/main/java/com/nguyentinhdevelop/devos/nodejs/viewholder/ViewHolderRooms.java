package com.nguyentinhdevelop.devos.nodejs.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyentinhdevelop.devos.nodejs.R;

public class ViewHolderRooms extends RecyclerView.ViewHolder {
    public ImageView img;
    public TextView roomNumber;
    public TextView hotelid;
    public TextView price;
    public TextView floor;
    public TextView status;
    public TextView singleRoom;
    public TextView detail;
    public ImageButton btndelete;
    public ImageButton btnedit;



    public ViewHolderRooms(View itemView) {
        super(itemView);
        img = (ImageView) itemView.findViewById(R.id.img);
        roomNumber = (TextView) itemView.findViewById(R.id.room_number);
        hotelid = (TextView) itemView.findViewById(R.id.hotelid);
        price = (TextView) itemView.findViewById(R.id.price);
        floor = (TextView) itemView.findViewById(R.id.floor);
        status = (TextView) itemView.findViewById(R.id.status);
        singleRoom = (TextView) itemView.findViewById(R.id.single_room);
        detail = (TextView) itemView.findViewById(R.id.detail);
        btndelete = (ImageButton) itemView.findViewById(R.id.btndelete);
        btnedit = (ImageButton) itemView.findViewById(R.id.btnedit);
    }
}
