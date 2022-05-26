package com.fmu.financesapp.adapters.nestedRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fmu.financesapp.R;
import com.fmu.financesapp.model.TransactionParent;

import java.util.List;

public class TransactionParentAdapter extends RecyclerView.Adapter<TransactionParentAdapter.ViewHolder> {
    List<TransactionParent> transactionParentModelList;
    ViewGroup context;
    public TransactionParentAdapter(List<TransactionParent> transactionParentModelList) {
        this.transactionParentModelList = transactionParentModelList;
    }

    @NonNull
    @Override
    public TransactionParentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transactions_card_parent, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionParentAdapter.ViewHolder holder, int position) {
        holder.tvCardDate.setText(transactionParentModelList.get(position).getDataTitle());
        TransactionChildAdapter transactionChildAdapter;
        transactionChildAdapter = new TransactionChildAdapter(transactionParentModelList.get(position).getAccountsList(), context.getContext());
        holder.ivCardParent.setLayoutManager(new LinearLayoutManager(context.getContext(),LinearLayoutManager.VERTICAL,false));
        holder.ivCardParent.setAdapter(transactionChildAdapter);
        transactionChildAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return transactionParentModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCardDate;
        RecyclerView ivCardParent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCardDate = itemView.findViewById(R.id.tvCardDate);
            ivCardParent = itemView.findViewById(R.id.ivCardParent);
        }
    }
}
