package com.fmu.financesapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fmu.financesapp.EditGoal;
import com.fmu.financesapp.R;
import com.fmu.financesapp.dao.AccountDao;
import com.fmu.financesapp.dao.CategoryDao;
import com.fmu.financesapp.model.Account;
import com.fmu.financesapp.model.Category;

import java.util.ArrayList;

public class PlanningListAdapter extends RecyclerView.Adapter<PlanningListAdapter.MyViewHolder> {
    private final AccountDao daoAccount = new AccountDao();
    private OnCategoryListener onCategoryListener;

    private ArrayList<Category> categoryList;

    public PlanningListAdapter(ArrayList<Category> categoryList, OnCategoryListener onCategoryListener){
        this.categoryList = categoryList;
        this.onCategoryListener = onCategoryListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvPlCategoryTitle;
        private TextView tvPlCategorySpent;
        private TextView tvPlCategoryBudget;
        private ProgressBar pbCategory;
        OnCategoryListener onCategoryListener;

        public MyViewHolder(final View view,OnCategoryListener onCategoryListener ){
            super(view);
            tvPlCategoryTitle = view.findViewById(R.id.tvPlCategoryTitle);
            tvPlCategorySpent = view.findViewById(R.id.tvPlCategorySpent);
            tvPlCategoryBudget = view.findViewById(R.id.tvPlCategoryLimit);
            pbCategory = view.findViewById(R.id.pbCategory);
            this.onCategoryListener = onCategoryListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCategoryListener.onCategoryClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public PlanningListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.goals_category_item,parent, false);
        return new MyViewHolder(itemView, onCategoryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanningListAdapter.MyViewHolder holder, int position) {
        String categoryName  = categoryList.get(position).getName();
        double categoryBudget  = categoryList.get(position).getBudget();
        double categorySpent  = daoAccount.filterByCategory(categoryName);
        holder.tvPlCategorySpent.setText(String.valueOf(daoAccount.formartCurrency(categorySpent)));
        holder.tvPlCategoryTitle.setText(categoryName);
        holder.tvPlCategoryBudget.setText(String.valueOf(daoAccount.formartCurrency(categoryBudget)));
        holder.pbCategory.setMax((int ) categoryBudget);
        holder.pbCategory.setProgress((int) categorySpent);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public interface OnCategoryListener{
        void onCategoryClick(int position);
    }
}
