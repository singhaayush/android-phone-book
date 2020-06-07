package com.example.phonebookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
     private Context mContext;
     private ArrayList<Person>entries;

    public PersonAdapter(Context mContext, ArrayList<Person> entries) {
        this.mContext = mContext;
        this.entries = entries;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View v=inflater.inflate(R.layout.custom_person_info,parent,false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
         holder.name.setText(entries.get(position).getPersonName());
         holder.email.setText(entries.get(position).getPersonEmail());
         holder.dob.setText(entries.get(position).getPersonDOB());
         holder.phone.setText(entries.get(position).getPersonPhoneNumber());

         if(entries.get(position).isExpanded()==false)
         {
             holder.linearLayout.setBackgroundResource(R.color.colorPrimary);
             holder.relativeLayout.setVisibility(View.GONE);
         }
         else {
             holder.linearLayout.setBackgroundResource(R.color.yellow);

             holder.relativeLayout.setVisibility(View.VISIBLE);
         }
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout linearLayout;
         TextView name,email,phone,dob;
         RelativeLayout relativeLayout;
         Button edit,delete;
        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.header_layout);
            relativeLayout=itemView.findViewById(R.id.expandable_layout);
            name=itemView.findViewById(R.id.person_name);
            email=itemView.findViewById(R.id.person_email);
            phone=itemView.findViewById(R.id.person_phoneNumber);
            dob=itemView.findViewById(R.id.person_dob);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    linearLayout.setBackground(mContext.getDrawable(R.color.colorPrimary));
                    entries.get(getAdapterPosition()).setExpanded(!entries.get(getAdapterPosition()).isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });

        }
    }
}

