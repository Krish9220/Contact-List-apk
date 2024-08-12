package com.example.recyclelerview;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {

    Context context;
    ArrayList<ContectModel>contectData;
    RecyclerContactAdapter(Context context,ArrayList<ContectModel>a){
        this.context=context;
        this.contectData=a;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView photo;
        TextView naam,phone;
        LinearLayout llRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            photo=itemView.findViewById(R.id.iv);
            naam=itemView.findViewById(R.id.tvName);
            phone=itemView.findViewById(R.id.tvNum);
            llRow=itemView.findViewById(R.id.llRow);//llRow handling the item click on arrayList.

        }
    }

    private void setAnimation(View viewToAnimate,int position)
    {
        Animation slideIn= AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
        viewToAnimate.startAnimation(slideIn);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.contect_row,parent,false);

//        ViewHolder viewHolder=new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContectModel model=contectData.get(position);//optional
        holder.naam.setText(contectData.get(position).name);
        holder.phone.setText(contectData.get(position).number);
        holder.photo.setImageResource(contectData.get(position).image);

        setAnimation(holder.itemView,position);

        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.add_update_layout);

                EditText editName=dialog.findViewById(R.id.nameid);
                EditText editNum=dialog.findViewById(R.id.numid);
                Button btnAction=dialog.findViewById(R.id.addid);
                TextView heading=dialog.findViewById(R.id.headingid);


                editName.setText(contectData.get(position).name);//adding the current data before editing.
                editNum.setText(contectData.get(position).number);
                btnAction.setText("Edit");
                heading.setText("Edit Contact");

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name="";
                        String number="";

                        if(editName.getText().toString().equals(""))
                        {
                            Toast.makeText(context,
                                    "Please Enter Contact Name",
                                    Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            name=editName.getText().toString();
                        }

                        if(editNum.getText().toString().equals(""))
                        {
                            Toast.makeText(context,
                                    "Please Enter Contact Number",
                                    Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            number=editNum.getText().toString();
                        }

                        contectData.set(position,new ContectModel(contectData.get(position).image,name,number));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        holder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context)
                        .setTitle("Delete Contect")
                        .setMessage("Are you sure want to delete?")
                        .setIcon(R.drawable.delete)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                contectData.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });


                builder.show();
                return true;
            }
        });


        // Set OnClickListener for the item.
        String NameIs=contectData.get(position).name;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a toast indicating calling the contact
                Toast.makeText(context, "Calling to " +NameIs , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contectData.size();
    }

}
