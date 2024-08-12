package com.example.recyclelerview;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ContectModel model;
    ArrayList<ContectModel>arrayList;
    FloatingActionButton addBtn;
    RecyclerContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.myrecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList=new ArrayList<>();
        addBtn=findViewById(R.id.fab);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_layout);

                EditText editName=dialog.findViewById(R.id.nameid);
                EditText editNum=dialog.findViewById(R.id.numid);
                Button btnAction=dialog.findViewById(R.id.addid);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name="";
                        String number="";

                        if(editName.getText().toString().equals(""))
                        {
                            Toast.makeText(getApplicationContext(),
                                    "Please Enter Contact Name",
                                    Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            name=editName.getText().toString();
                        }

                        if(editNum.getText().toString().equals(""))
                        {
                            Toast.makeText(getApplicationContext(),
                                    "Please Enter Contact Number",
                                    Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            number=editNum.getText().toString();
                        }

                        arrayList.add(new ContectModel(R.drawable.contect,name,number));

                        adapter.notifyItemInserted(arrayList.size()-1);
                        recyclerView.scrollToPosition(arrayList.size()-1);//push as to the recently added contect in the end.
                        dialog.dismiss();//to remove dilog box after its work is done.
                    }
                });
                dialog.show();//it is must to show diolog.
            }
        });




        arrayList.add(new ContectModel(R.drawable.doremon,"Doremon","9292430202"));
        arrayList.add(new ContectModel(R.drawable.nobita,"Nobita","9238210202"));
        arrayList.add(new ContectModel(R.drawable.sizuka,"Sizuka","5678230202"));
        arrayList.add(new ContectModel(R.drawable.giyan,"Giyan","2953230202"));
        arrayList.add(new ContectModel(R.drawable.sunio,"Sunio","8538230202"));
        arrayList.add(new ContectModel(R.drawable.bheem,"Bheem","4328230202"));
        arrayList.add(new ContectModel(R.drawable.raju,"Raju","7654230202"));
        arrayList.add(new ContectModel(R.drawable.jaggu,"Jaggu","3218230202"));
        arrayList.add(new ContectModel(R.drawable.jon,"Jon","9565230202"));
        arrayList.add(new ContectModel(R.drawable.oggy,"Oggy","8765230202"));
        arrayList.add(new ContectModel(R.drawable.jack,"Jack","9878230202"));
        arrayList.add(new ContectModel(R.drawable.jerry,"Jerry","8432730202"));
        arrayList.add(new ContectModel(R.drawable.tom,"Tom","889877087"));
        arrayList.add(new ContectModel(R.drawable.sinchin,"Sinchin","8798230202"));
        arrayList.add(new ContectModel(R.drawable.masao,"Masao","6576230202"));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new RecyclerContactAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);


    }

}