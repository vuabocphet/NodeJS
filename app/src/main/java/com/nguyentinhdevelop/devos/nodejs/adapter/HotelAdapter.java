package com.nguyentinhdevelop.devos.nodejs.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nguyentinhdevelop.devos.nodejs.MainActivity;
import com.nguyentinhdevelop.devos.nodejs.R;
import com.nguyentinhdevelop.devos.nodejs.model.hotelModel;
import com.nguyentinhdevelop.devos.nodejs.model.roomModel;
import com.nguyentinhdevelop.devos.nodejs.viewholder.ViewHolderHotel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotelAdapter extends RecyclerView.Adapter<ViewHolderHotel> {

    public MainActivity context;
    public ArrayList<hotelModel> listHotel=new ArrayList();
    public ArrayList<roomModel> roomModels=new ArrayList();

    public HotelAdapter(MainActivity context, ArrayList<hotelModel> listHotel, ArrayList<roomModel> roomModels) {
        this.context = context;
        this.listHotel = listHotel;
        this.roomModels = roomModels;
    }

    @NonNull
    @Override
    public ViewHolderHotel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.items_view_hotel,parent,false);
        return  new ViewHolderHotel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHotel holder, final int position) {
          final hotelModel model=listHotel.get(position);
        Picasso.with(context).load(model.getImage()).into(holder.img);
        holder.name.setText(model.getName());
        holder.address.setText("Địa chỉ:"+model.getAddress());
        holder.city.setText("Thành phố:"+model.getCity());
        holder.license_number.setText("Giay phép đăng kí:"+model.getLicense_number());
        holder.total_floor.setText("Tổng số tầng:"+model.getTotal_floor());
        holder.owner.setText("Chủ sở hữu:"+model.getOwner());
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.deleteHotel(model,position);
            }
        });


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.editHotel(model,position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean a=false;
                ArrayList<roomModel> listroom=new ArrayList<>();
                listroom.clear();
                if (!roomModels.isEmpty()){
                    for (int i=0;i<roomModels.size();i++){
                        if (roomModels.get(i).getHotelid().equals(model.getName())) {
                            listroom.add(new roomModel(roomModels.get(i).getHotelid(),roomModels.get(i).get_id(),roomModels.get(i).getSingle_room(),roomModels.get(i).getPrice(),roomModels.get(i).getImage(),roomModels.get(i).getDetail(),roomModels.get(i).getFloor(),roomModels.get(i).getRoom_number(),roomModels.get(i).getStatus()));
                            Log.e("TRUE",listroom.size()+"");
                        }else {

                            Log.e("FALSE","HỨC");
                        }
                        Log.e("SIZEA",roomModels.get(i).getHotelid());
                    }
                }else {

                }

                if (!listroom.isEmpty()){
                    context.click(listroom);
                }else {
                    Toast.makeText(context, "Không có phòng nào thuộc khách sạn này", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listHotel.size();
    }
}
