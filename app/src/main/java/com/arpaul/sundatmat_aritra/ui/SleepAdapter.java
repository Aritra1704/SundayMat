package com.arpaul.sundatmat_aritra.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arpaul.sundatmat_aritra.R;
import com.arpaul.sundatmat_aritra.models.Sleep;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SleepAdapter extends RecyclerView.Adapter<SleepAdapter.UserHolder> {
    private Context context;
    private List<Sleep> users;

    public class UserHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvDay)
        TextView tvDay;
        @BindView(R.id.tvTimeperiod)
        TextView tvTimeperiod;


        public UserHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public void refresh(List<Sleep> users) {
        this.users = users;
        notifyDataSetChanged();
    }


    public SleepAdapter(Context context, List<Sleep> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sleep_item, parent, false);

        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.tvDay.setText(users.get(position).getDay() + " " + users.get(position).getAction());
        holder.tvTimeperiod.setText(users.get(position).getTimeperiod() + "");

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
