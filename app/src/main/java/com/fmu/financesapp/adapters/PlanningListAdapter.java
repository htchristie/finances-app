package com.fmu.financesapp.adapters;

import static com.fmu.financesapp.interfaces.GoalRecycleInterface.ICONMAP;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fmu.financesapp.R;
import com.fmu.financesapp.dao.AccountDao;
import com.fmu.financesapp.interfaces.GoalRecycleInterface;
import com.fmu.financesapp.model.Category;

import java.util.ArrayList;

public class PlanningListAdapter extends RecyclerView.Adapter<PlanningListAdapter.MyViewHolder> {
    private final AccountDao daoAccount = new AccountDao();
    private ArrayList<Category> categoryList;
    private final GoalRecycleInterface goalInterface;

    public PlanningListAdapter(ArrayList<Category> categoryList, GoalRecycleInterface goalInterface){
        this.categoryList = categoryList;
        this.goalInterface = goalInterface;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvPlCategoryTitle;
        private TextView tvPlCategorySpent;
        private TextView tvPlCategoryBudget;
        private ProgressBar pbCategory;
        private ImageView ivPlCategoryIcon;

        public MyViewHolder(final View view, GoalRecycleInterface goalInterface){
            super(view);
            tvPlCategoryTitle = view.findViewById(R.id.tvPlCategoryTitle);
            tvPlCategorySpent = view.findViewById(R.id.tvPlCategorySpent);
            tvPlCategoryBudget = view.findViewById(R.id.tvPlCategoryLimit);
            pbCategory = view.findViewById(R.id.pbCategory);
            ivPlCategoryIcon = view.findViewById(R.id.ivPlCategoryIcon);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int ps  = getAdapterPosition();
                    goalInterface.onItemClick(ps);
                }
            });
        }
    }

    @NonNull
    @Override
    public PlanningListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.goals_category_item,parent, false);
        return new MyViewHolder(itemView, goalInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanningListAdapter.MyViewHolder holder, int position) {
        String categoryName  = categoryList.get(position).getName();
        double categoryBudget  = categoryList.get(position).getBudget();
        double categorySpent  = daoAccount.filterByCategory(categoryName);
        holder.tvPlCategorySpent.setText(String.valueOf(daoAccount.formatCurrency(categorySpent)));
        holder.tvPlCategoryTitle.setText(categoryName);
        holder.tvPlCategoryBudget.setText(String.valueOf(daoAccount.formatCurrency(categoryBudget)));
        holder.pbCategory.setMax((int ) categoryBudget);
        holder.pbCategory.setProgress((int) categorySpent);
        try {
            int id = R.drawable.class.getField(ICONMAP.get(categoryName)).getInt(null);
            holder.ivPlCategoryIcon.setImageResource(id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
