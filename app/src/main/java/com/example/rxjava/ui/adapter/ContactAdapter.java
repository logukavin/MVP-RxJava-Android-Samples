package com.example.rxjava.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rxjava.R;
import com.example.rxjava.model.ContactsItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;
    private List<ContactsItem> contactsItems;
    private LayoutInflater inflater;

    public ContactAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        contactsItems=new ArrayList<>();
    }

    public void setContactList(List<ContactsItem> contactList) {
        if (contactList == null)
            return;
        contactsItems.clear();
        contactsItems.addAll(contactList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.from(context).inflate(R.layout.item_contact, parent, false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContactsItem item = contactsItems.get(position);
        holder.tvName.setText(item.getName());
        holder.tvNumber.setText(item.getPhone().getMobile());

    }

    @Override
    public int getItemCount() {
        return contactsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_number)
        TextView tvNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
