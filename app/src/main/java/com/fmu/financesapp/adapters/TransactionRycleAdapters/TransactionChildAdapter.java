package com.fmu.financesapp.adapters.TransactionRycleAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fmu.financesapp.R;
import com.fmu.financesapp.dao.AccountDao;
import com.fmu.financesapp.model.Account;

import java.util.List;

public class TransactionChildAdapter extends RecyclerView.Adapter<TransactionChildAdapter.ViewHolder> {
    List<Account> accounts;
    private final AccountDao dao = new AccountDao();

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

        Boolean typeImg  = accounts.get(position).isType();
        holder.tvDateCardCategory.setText(accounts.get(position).getCategory());
        holder.tvDateCardDesc.setText(accounts.get(position).getName());
        setType(holder, position, typeImg);
    }

    private void setType(@NonNull ViewHolder holder, int position, Boolean typeImg) {
        if (typeImg) {
            holder.tvDateCardValue.setText("+ "+dao.formartCurrency(accounts.get(position).getValue()));
            holder.tvDateCardValue.setTextColor(ContextCompat.getColor( holder.itemView.getContext(),R.color.green_500));
        } else {
            holder.tvDateCardValue.setText("- "+dao.formartCurrency(accounts.get(position).getValue()));
            holder.tvDateCardValue.setTextColor(ContextCompat.getColor( holder.itemView.getContext(),R.color.red_500));
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDateCardDesc = itemView.findViewById(R.id.tvDateCardDesc);
            tvDateCardCategory = itemView.findViewById(R.id.tvDateCardCategory);
            tvDateCardValue = itemView.findViewById(R.id.tvDateCardValue);
        }
    }
}
