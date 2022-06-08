package com.fmu.financesapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fmu.financesapp.R;
import com.fmu.financesapp.adapters.AccountsListAdapter;
import com.fmu.financesapp.adapters.PlanningListAdapter;
import com.fmu.financesapp.dao.AccountDao;
import com.fmu.financesapp.dao.CategoryDao;
import com.fmu.financesapp.dao.UserDao;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlanningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanningFragment extends Fragment {
    private final CategoryDao categoryList = new CategoryDao();
    private final UserDao userDao = new UserDao();
    private final AccountDao accountDao = new AccountDao();


    private PlanningListAdapter adapter;

    // TODO: Rename parameter arguments, ch
    //  oose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlanningFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlanningFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlanningFragment newInstance(String param1, String param2) {
        PlanningFragment fragment = new PlanningFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_planning, container, false);
        TextView userBudget = view.findViewById(R.id.tvTotalBudgetDisplay);
        TextView userSpend = view.findViewById(R.id.tvBudgetDisplay);
        userSpend.setText(accountDao.formartCurrency(accountDao.negativeBalance()));
        userBudget.setText("/ "+accountDao.formartCurrency(userDao.getUserBudget()));
        initRycleView(view);
        return view;
    }

    private void initRycleView(View view) {
        adapter = new PlanningListAdapter(categoryList.all());
        RecyclerView rvPlanningCard = view.findViewById(R.id.rvPlanningCard) ;
        rvPlanningCard.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvPlanningCard.setAdapter(adapter);
    }

}