package com.fmu.financesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fmu.financesapp.R;
import com.fmu.financesapp.dao.AccountDao;
import com.fmu.financesapp.model.Account;

import java.util.ArrayList;

public class AccountsListAdapter extends RecyclerView.Adapter<AccountsListAdapter.MyViewHolder> {
    private final AccountDao dao = new AccountDao();
    private ArrayList<Account> accountList;

    public AccountsListAdapter(ArrayList<Account> accountList){
        this.accountList = accountList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView accountCategory;
        private TextView tvCurrency;
        private ImageView accountImg;

        public MyViewHolder(final View view){
            super(view);
            accountCategory = view.findViewById(R.id.tvCategory);
            tvCurrency = view.findViewById(R.id.tvCurrency);
            accountImg = view.findViewById(R.id.imageView3);
        }
    }

    @NonNull
    @Override
    public AccountsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_activity_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountsListAdapter.MyViewHolder holder, int position) {
        String category  = accountList.get(position).getCategory();
        Boolean typeImg  = accountList.get(position).isType();
        double currency  = accountList.get(position).getValue();
        holder.accountCategory.setText(category);
        formatConditionInformation(holder, typeImg, Double.toString(currency));

    }

    private void formatConditionInformation(@NonNull MyViewHolder holder, Boolean typeImg, String currencyFormat) {
        if (typeImg) {
            holder.accountImg.setImageResource(R.drawable.ic_circle_green);
            holder.tvCurrency.setText("+ "+ currencyFormat);
            holder.tvCurrency.setTextColor(ContextCompat.getColor( holder.itemView.getContext(),R.color.green_500));

        } else {
            holder.accountImg.setImageResource(R.drawable.ic_circle_red);
            holder.tvCurrency.setText("- "+ currencyFormat);
            holder.tvCurrency.setTextColor(ContextCompat.getColor( holder.itemView.getContext(),R.color.red_500));
        }
    }

    public void updateData(){
        accountList.clear();
        accountList = dao.all();
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }
}
