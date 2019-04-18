package com.example.softspaceposjm.ViewHolder;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.softspaceposjm.R;
//import com.squareup.picasso.Picasso;


public class MenuViewHolder extends RecyclerView.ViewHolder {
    public TextView txtTheJobName1,txtTheNoOfGuard1,txtTheDate1,txtTheLocation1,txtTheStatus1,txtserviceID, txtFurtherStatus;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        // txtRegisterEventId = itemView.findViewById(R.id.RegisterEventId);
        txtTheJobName1 = itemView.findViewById(R.id.TheJobName1);
        txtTheNoOfGuard1 = itemView.findViewById(R.id.TheNoOfGuard1);
        txtTheDate1 = itemView.findViewById(R.id.TheDate1);
        txtTheLocation1 = itemView.findViewById(R.id.TheLocation1);
        txtTheStatus1 = itemView.findViewById(R.id.TheStatus1);

        txtserviceID = itemView.findViewById(R.id.serviceID);
        txtFurtherStatus = itemView.findViewById(R.id.TheFurtherStatus);


    }
}