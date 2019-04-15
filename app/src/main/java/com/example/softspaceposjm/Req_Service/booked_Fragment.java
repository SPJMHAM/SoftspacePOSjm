package com.example.softspaceposjm.Req_Service;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.softspaceposjm.Common.CUser;
import com.example.softspaceposjm.R;
import com.example.softspaceposjm.Model.service_Info;
import com.example.softspaceposjm.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


import java.util.ArrayList;

public class booked_Fragment extends Fragment {
    RecyclerView recycle_menu;
    RecyclerView.LayoutManager layoutManager;
    //  DatabaseReference jobinfo;
    DatabaseReference service_Info;
    ArrayList<service_Info> items = new ArrayList<>();
    int Test = 0;

    FirebaseRecyclerAdapter <service_Info, MenuViewHolder> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("Test 11111111:"+":End");
        View view = inflater.inflate(R.layout.request_fragment, container, false);

        Test = 1;
        recycle_menu = view. findViewById(R.id.recycle_menu1);
        recycle_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());

        recycle_menu.setLayoutManager(layoutManager);
        final String TestCurrentUser;
        // TestCurrentUser = CUser.currentUser.getUserName();
        TestCurrentUser = "ggggjhjh";
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        service_Info = FirebaseDatabase.getInstance().getReference().child("service_Info").child(TestCurrentUser);
        System.out.println("Test 555555555:"+":End");

        // Query query = jobinfo.orderByKey();
        //    Query query = allUserDatabaseRef.orderByChild("registerEventName").startAt(searchBoxInput).endAt(searchBoxInput+"\uf8ff");
        String searchBoxInput = "booked";
        Query query = service_Info.orderByChild("status").startAt(searchBoxInput).endAt(searchBoxInput+"\uf8ff");
        System.out.println("Test 11111166666611:"+":End");
        FirebaseRecyclerOptions firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<service_Info>().setQuery(query, service_Info.class).build();
        adapter = new FirebaseRecyclerAdapter<service_Info, MenuViewHolder>(firebaseRecyclerOptions) {

            @NonNull
            @Override
            public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_list,viewGroup,false);
                return new MenuViewHolder(view);

            }

            @Override
            public void onBindViewHolder(@NonNull MenuViewHolder holder, int position, @NonNull service_Info model) {
                System.out.println("Test 222:"+":End");

                holder.txtTheJobName1.setText(model.getJobTitle());
                System.out.println("Test 333:"+":End");
                System.out.println("Test Name:"+model.getJobTitle()+":End");

                holder.txtTheNoOfGuard1.setText(model.getNoOfPax());
                holder.txtTheDate1.setText(model.getReqDate());
                holder.txtTheLocation1.setText(model.getAddressInfo());
                holder.txtTheStatus1.setText(model.getStatus());

                System.out.println("Test Test:"+Test+":End");

            }
        };
        recycle_menu.setAdapter(adapter);

        return view;
    }


    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
