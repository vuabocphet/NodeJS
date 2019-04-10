package com.nguyentinhdevelop.devos.nodejs.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyentinhdevelop.devos.nodejs.MainActivity;
import com.nguyentinhdevelop.devos.nodejs.R;
import com.nguyentinhdevelop.devos.nodejs.model.hotelModel;
import com.nguyentinhdevelop.devos.nodejs.model.roomModel;
import com.nguyentinhdevelop.devos.nodejs.viewholder.ViewHolderRooms;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RoomsAdapter extends RecyclerView.Adapter<ViewHolderRooms> {
    private MainActivity context;
    private ArrayList<roomModel> list=new ArrayList<>();
    private ArrayList<hotelModel> listHotel=new ArrayList<>();

    public RoomsAdapter(MainActivity context, ArrayList<roomModel> list, ArrayList<hotelModel> listHotel) {
        this.context = context;
        this.list = list;
        this.listHotel = listHotel;
    }

    @NonNull
    @Override
    public ViewHolderRooms onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_view,parent,false);
        return new ViewHolderRooms(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRooms holder, final int position) {
        final roomModel model=list.get(position);

        holder.roomNumber.setText("Phòng:"+model.getRoom_number());
        holder.detail.setText("Giới thiệu:"+model.getDetail());
        holder.floor.setText("Tầng:"+model.getFloor());
        Picasso.with(context).load(model.getImage()).into(holder.img);
        if (model.getSingle_room().equals("true")){
            holder.singleRoom.setText("Phòng đơn:Đúng");
        }
        if (model.getSingle_room().equals("false")){
            holder.singleRoom.setText("Phòng đơn:Sai");
        }


        if (model.getStatus().equals("1")){
            holder.status.setText("Trạng thái:Đang trống");
        }

        if (model.getStatus().equals("2")){
            holder.status.setText("Trạng thái:Đã đặt");
        }

        if (model.getStatus().equals("3")){
            holder.status.setText("Trạng thái:Không sử dụng");
        }


        holder.price.setText("Giá"+model.getPrice());

        for(int i=0;i<list.size();i++){

            for (int j=0;j<listHotel.size();j++){

                if (list.get(i).getHotelid().equals(listHotel.get(j).getId())){
                    list.get(i).setHotelid(listHotel.get(j).getName());

                }


            }
        }


        holder.hotelid.setText(list.get(position).getHotelid());



        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.deleteRoom(model,position);
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
