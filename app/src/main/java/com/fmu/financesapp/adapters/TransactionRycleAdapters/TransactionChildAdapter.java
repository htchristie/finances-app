package com.fmu.financesapp.adapters.TransactionRycleAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fmu.financesapp.R;
import com.fmu.financesapp.interfaces.TransactionInterface;
import com.fmu.financesapp.model.Account;

import java.util.List;

public class TransactionChildAdapter extends RecyclerView.Adapter<TransactionChildAdapter.ViewHolder> {
    List<Account> accounts;
    private final TransactionInterface transactionInterface;
    Context context;

    public TransactionChildAdapter(List<Account> accounts, TransactionInterface transactionInterface, Context context) {
        this.accounts = accounts;
        this.transactionInterface = transactionInterface;
        this.context = context;
    }

    @NonNull
    @Override
    public TransactionChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transactions_card_child, null, false);
        return new ViewHolder(view, transactionInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionChildAdapter.ViewHolder holder, int position) {

        Account account = accounts.get(position);

        Boolean typeImg  = account.isType();
        holder.tvDateCardCategory.setText(account.getCategory());
        holder.tvDateCardDesc.setText(account.getName());
        holder.tvDateCardDate.setText(account.getDate());
        setType(holder, position, typeImg);
    }

    private void setType(@NonNull ViewHolder holder, int position, Boolean typeImg) {
        if (typeImg) {
            holder.tvDateCardValue.setText("+ "+accounts.get(position).getValue());
            holder.tvDateCardValue.setTextColor(ContextCompat.getColor( holder.itemView.getContext(),R.color.green_500));
            holder.ivDateCardIconBg.setImageResource(R.drawable.ic_circle_green);
        } else {
            holder.tvDateCardValue.setText("- "+accounts.get(position).getValue());
            holder.tvDateCardValue.setTextColor(ContextCompat.getColor( holder.itemView.getContext(),R.color.red_500));
            holder.ivDateCardIconBg.setImageResource(R.drawable.ic_circle_red);
        }
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDateCardDesc;
        TextView tvDateCardCategory;
        TextView tvDateCardValue;
        TextView tvDateCardDate;
        ImageView ivDateCardIconBg;

        public ViewHolder(@NonNull View itemView, TransactionInterface transactionInterface) {
            super(itemView);
            tvDateCardDesc = itemView.findViewById(R.id.tvDateCardDesc);
            tvDateCardCategory = itemView.findViewById(R.id.tvDateCardCategory);
            tvDateCardValue = itemView.findViewById(R.id.tvDateCardValue);
            tvDateCardDate = itemView.findViewById(R.id.tvDateCardDate);
            ivDateCardIconBg = itemView.findViewById(R.id.ivDateCardIconBg);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int ps = getAdapterPosition();
                    transactionInterface.onItemClick(ps);
                }
            });
        }
    }
}
