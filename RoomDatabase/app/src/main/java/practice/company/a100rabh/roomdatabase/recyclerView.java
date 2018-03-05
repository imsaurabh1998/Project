package practice.company.a100rabh.roomdatabase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 100RABH on 3/3/2018.
 */

public class recyclerView extends RecyclerView.Adapter<recyclerView.ViewHolder> {

    List<User> users;

    public recyclerView(List<User> users) {
        this.users = users;
    }

    @Override
    public recyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.users_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(recyclerView.ViewHolder holder, int position) {
        holder.first_name.setText(users.get(position).getFirstName());
        holder.last_name.setText(users.get(position).getLastName());
        holder.email.setText(users.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView first_name;
        public TextView last_name;
        public TextView email;

        public ViewHolder(View itemView) {
            super(itemView);

            first_name=itemView.findViewById(R.id.first_name);
            last_name=itemView.findViewById(R.id.last_name);
            email=itemView.findViewById(R.id.email);

        }
    }
}
