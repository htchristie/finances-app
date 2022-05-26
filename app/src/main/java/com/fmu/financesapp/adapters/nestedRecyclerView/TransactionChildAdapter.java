package com.fmu.financesapp.adapters.nestedRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fmu.financesapp.R;
import com.fmu.financesapp.model.Account;

import java.util.List;

public class TransactionChildAdapter extends RecyclerView.Adapter<TransactionChildAdapter.ViewHolder> {
    List<Account> accounts;
    Context context;

    public TransactionChildAdapter(List<Account> accounts, Context context) {
        this.accounts = accounts;
        this.context = context;
    }

    @NonNull
    @Override
    public TransactionChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transactions_card_child, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionChildAdapter.ViewHolder holder, int position) {
        holder.tvDateCardCategory.setText(accounts.get(position).getCategory());
        holder.tvDateCardDesc.setText(accounts.get(position).getName());
        holder.tvDateCardValue.setText(accounts.get(position).getValue().toString());
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDateCardDesc;
        TextView tvDateCardCategory;
        TextView tvDateCardValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDateCardDesc = itemView.findViewById(R.id.tvDateCardDesc);
            tvDateCardCategory = itemView.findViewById(R.id.tvDateCardCategory);
            tvDateCardValue = itemView.findViewById(R.id.tvDateCardValue);
        }
    }
}
