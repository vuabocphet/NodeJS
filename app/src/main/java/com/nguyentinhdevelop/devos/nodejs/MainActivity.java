package com.nguyentinhdevelop.devos.nodejs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.nguyentinhdevelop.devos.nodejs.adapter.HotelAdapter;
import com.nguyentinhdevelop.devos.nodejs.adapter.RoomsAdapter;
import com.nguyentinhdevelop.devos.nodejs.adapter.RoomsAdapter_1;
import com.nguyentinhdevelop.devos.nodejs.model.hotelModel;
import com.nguyentinhdevelop.devos.nodejs.model.roomModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class MainActivity extends AppCompatActivity {

    private ArrayList<hotelModel> listHotel = new ArrayList<>();
    private ArrayList<roomModel> listRoom = new ArrayList<>();
    private ArrayList<roomModel> listRoom1 = new ArrayList<>();
    private Socket socket;

    private RecyclerView recyclerView, recyclerView1,recyclerView2;
    private HotelAdapter hoteladapter;
    private RoomsAdapter roomAdapter;
    private RoomsAdapter_1 roomsAdapter_1;
    private LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    private LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    private LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    private Button addRoom, addHotel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView1=findViewById(R.id.recyclerviewHotel);
        recyclerView2=findViewById(R.id.recyclerview2);
        progressBar=findViewById(R.id.loading);

        addRoom=findViewById(R.id.addRoom);
        addHotel=findViewById(R.id.addCate);
        addHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHotel();
            }
        });
        progressBar.setVisibility(View.VISIBLE);

        addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        listHotel.clear();
        listRoom.clear();
        listRoom1.clear();
        roomAdapter=new RoomsAdapter(MainActivity.this,listRoom,listHotel);

        hoteladapter=new HotelAdapter(MainActivity.this,listHotel,listRoom);

        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(hoteladapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(roomAdapter);
        socketio();


    }








    public void socketio(){
        try {
            socket = IO.socket("http://192.168.31.121:3000/");
           // socket = IO.socket("http://10.253.210.199:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socket.connect();
        socket.on("data-hotel",onDataHotel);
        socket.on("data-room",onData);
    }

    public void onclick(){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.product:
                getSupportActionBar().setTitle("Phòng");
                addRoom.setVisibility(View.GONE);
                addHotel.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView1.setVisibility(View.INVISIBLE);
                recyclerView2.setVisibility(View.INVISIBLE);
                socketio();

                break;

            case R.id.cate:
                getSupportActionBar().setTitle("Khách sạn");
                addRoom.setVisibility(View.INVISIBLE);
                addHotel.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                recyclerView1.setVisibility(View.VISIBLE);
                recyclerView2.setVisibility(View.INVISIBLE);
                socketio();
                break;


        }
        return true;
    }

    private Emitter.Listener onDataHotel=new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object= (JSONObject) args[0];
                      listHotel.clear();
                    try {
                        JSONArray jsonArray=object.getJSONArray("hotel");
                         Log.e("DATA",jsonArray.toString());
                        for(int i=0;i < jsonArray.length();i++) {
                            JSONObject jsonRoot = new JSONObject(String.valueOf(jsonArray.getJSONObject(i)));
                            String name = jsonRoot.getString("name");
                            String image=jsonRoot.getString("image");
                            String id=jsonRoot.getString("_id");
                            String city=jsonRoot.getString("city");
                            String address=jsonRoot.getString("address");
                            String owner=jsonRoot.getString("owner");
                            String license_number=jsonRoot.getString("license_number");
                            String total_floor=jsonRoot.getString("total_floor");
                            listHotel.add(new hotelModel(id,city,address,owner,license_number,total_floor,image,name));

                        }
                        Log.e("SIZE LIST CATE",listHotel.size()+"");
                        progressBar.setVisibility(View.INVISIBLE);
                        hoteladapter.notifyDataSetChanged();
                        //roomAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    public void deleteHotel(final  hotelModel hotelModel,final int i){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có muốn xóa khách sạn:"+hotelModel.getName()+"\tkhông?");
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                socket.connected();
                socket.connect();
                socket.emit("delete-hotel",hotelModel.getId());
                listHotel.remove(i);
                Toast.makeText(MainActivity.this, "Xóa thành công khách sạn", Toast.LENGTH_SHORT).show();
                hoteladapter.notifyDataSetChanged();
                socketio();
            }
        });
        builder.show();
    }

    public void addHotel(){

        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.custom_view);
        final TextView name,city,address,owner,image,total_floor,license_number;
        name=dialog.findViewById(R.id.name);
        city=dialog.findViewById(R.id.city);
        address=dialog.findViewById(R.id.address);
        owner=dialog.findViewById(R.id.owner);
        image=dialog.findViewById(R.id.image);
        total_floor=dialog.findViewById(R.id.total_floor);
        license_number=dialog.findViewById(R.id.license_number);
        dialog.findViewById(R.id.btnhuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.findViewById(R.id.btnEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().equals("")){
                    name.setError(getString(R.string.err));
                    return;
                }
                if (city.getText().toString().trim().equals("")){
                    city.setError(getString(R.string.err));
                    return;
                }
                if (address.getText().toString().trim().equals("")){
                    address.setError(getString(R.string.err));
                    return;
                }
                if (owner.getText().toString().trim().equals("")){
                    owner.setError(getString(R.string.err));
                    return;
                }
                if (image.getText().toString().trim().equals("")){
                    image.setError(getString(R.string.err));
                    return;
                }
                if (total_floor.getText().toString().trim().equals("")){
                    total_floor.setError(getString(R.string.err));
                    return;
                }
                if (license_number.getText().toString().trim().equals("")){
                    license_number.setError(getString(R.string.err));
                    return;
                }

                JSONObject obj = new JSONObject();
                try {
                    obj.putOpt("name", name.getText().toString().trim());
                    obj.putOpt("city", city.getText().toString().trim());
                    obj.putOpt("address", address.getText().toString().trim());
                    obj.putOpt("owner", owner.getText().toString().trim());
                    obj.putOpt("license_number", license_number.getText().toString().trim());
                    obj.putOpt("total_floor", total_floor.getText().toString().trim());
                    obj.putOpt("image", image.getText().toString().trim());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                    socket.connected();
                socket.connect();
                socket.emit("them-hotel",obj);

                socket.on("data-hotel",onDataHotel);
                socketio();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void editHotel(final hotelModel hotelModel, final int posion){

        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.custom_view_edit_hotel);
        final TextView name,city,address,owner,image,total_floor,license_number;
        name=dialog.findViewById(R.id.name);
        city=dialog.findViewById(R.id.city);
        address=dialog.findViewById(R.id.address);
        owner=dialog.findViewById(R.id.owner);
        image=dialog.findViewById(R.id.image);
        total_floor=dialog.findViewById(R.id.total_floor);
        license_number=dialog.findViewById(R.id.license_number);

        name.setText(hotelModel.getName());
        city.setText(hotelModel.getCity());
        address.setText(hotelModel.getAddress());
        owner.setText(hotelModel.getOwner());
        image.setText(hotelModel.getImage());
        total_floor.setText(hotelModel.getTotal_floor());
        license_number.setText(hotelModel.getLicense_number());

        dialog.findViewById(R.id.btnhuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.findViewById(R.id.btnEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().equals("")){
                    name.setError(getString(R.string.err));
                    return;
                }
                if (city.getText().toString().trim().equals("")){
                    city.setError(getString(R.string.err));
                    return;
                }
                if (address.getText().toString().trim().equals("")){
                    address.setError(getString(R.string.err));
                    return;
                }
                if (owner.getText().toString().trim().equals("")){
                    owner.setError(getString(R.string.err));
                    return;
                }
                if (image.getText().toString().trim().equals("")){
                    image.setError(getString(R.string.err));
                    return;
                }
                if (total_floor.getText().toString().trim().equals("")){
                    total_floor.setError(getString(R.string.err));
                    return;
                }
                if (license_number.getText().toString().trim().equals("")){
                    license_number.setError(getString(R.string.err));
                    return;
                }
                com.nguyentinhdevelop.devos.nodejs.model.hotelModel model=new hotelModel(hotelModel.getId(),city.getText().toString().trim(),address.getText().toString().trim(),owner.getText().toString().trim(),license_number.getText().toString().trim(),total_floor.getText().toString().trim(),image.getText().toString().trim(),name.getText().toString().trim());
                listHotel.set(posion,model);
                hoteladapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                JSONObject obj = new JSONObject();
                try {
                    obj.putOpt("id", hotelModel.getId());
                    obj.putOpt("name", name.getText().toString().trim());
                    obj.putOpt("city", city.getText().toString().trim());
                    obj.putOpt("address", address.getText().toString().trim());
                    obj.putOpt("owner", owner.getText().toString().trim());
                    obj.putOpt("license_number", license_number.getText().toString().trim());
                    obj.putOpt("total_floor", total_floor.getText().toString().trim());
                    obj.putOpt("image", image.getText().toString().trim());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                socket.connected();
                socket.connect();
                socket.emit("sua-hotel",obj);
                  
                socket.on("data-hotel",onDataHotel);
                socketio();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private Emitter.Listener onData=new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject object= (JSONObject) args[0];
                    listRoom.clear();

                    try {
                        JSONArray jsonArray=object.getJSONArray("room");

                        for(int i=0;i < jsonArray.length();i++) {
                            JSONObject jsonRoot = new JSONObject(String.valueOf(jsonArray.getJSONObject(i)));
                            String room_number = jsonRoot.getString("room_number");
                            String hotelid=jsonRoot.getString("hotelid");
                            String single_room=jsonRoot.getString("single_room");
                            String status=jsonRoot.getString("status");
                            String price=jsonRoot.getString("price");
                            String detail=jsonRoot.getString("detail");
                            String image=jsonRoot.getString("image");
                            String id=jsonRoot.getString("_id");
                            String floor=jsonRoot.getString("floor");

                            listRoom.add(new roomModel(hotelid,id,single_room,price,image,detail,floor,room_number,status));


                        }
                        progressBar.setVisibility(View.GONE);
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.e("SIZE",listRoom.size()+"");
                        roomAdapter.notifyDataSetChanged();

                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }

                    }
            });
        }
    };

    public void deleteRoom(final  roomModel roomModel,final int i){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có muốn xóa phòng:"+roomModel.getRoom_number()+"\tkhông?");
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                socket.connected();
                socket.connect();
                socket.emit("delete-room",roomModel.get_id());
                listRoom.remove(i);
                Toast.makeText(MainActivity.this, "Xóa thành công khách sạn", Toast.LENGTH_SHORT).show();
                roomAdapter.notifyDataSetChanged();
                socketio();
            }
        });
        builder.show();
    }


    public void click(ArrayList<roomModel> listRoom_1){


        recyclerView2.setLayoutManager(layoutManager2);
        roomsAdapter_1=new RoomsAdapter_1(MainActivity.this,listRoom_1,listHotel);
        recyclerView2.setAdapter(roomsAdapter_1);
        getSupportActionBar().setTitle("Phòng");
        addRoom.setVisibility(View.VISIBLE);
        addHotel.setVisibility(View.INVISIBLE);
        recyclerView2.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        recyclerView1.setVisibility(View.INVISIBLE);
        Log.e("PHONG",listRoom_1.size()+"");

    }

}






