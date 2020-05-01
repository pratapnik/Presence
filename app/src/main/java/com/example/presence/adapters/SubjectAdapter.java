package com.example.presence.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.util.StringUtil;

import com.example.presence.R;
import com.example.presence.database.Subject;
import com.example.presence.database.SubjectViewModel;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectHolder> {
    private Context context;
    private List<Subject> subjectList;
    private LayoutInflater inflater;

    public SubjectAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setItems(List<Subject> list) {
        subjectList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectAdapter.SubjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectHolder(inflater.inflate(R.layout.subject_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.SubjectHolder holder, int position) {
        Subject newSubject = subjectList.get(position);
        holder.subjectName.setText(newSubject.getSubjectName());
        String daysOfWeek = newSubject.getDaysOfWeek().replaceAll(","," ");
        holder.daysOfWeek.setText(daysOfWeek);
    }

    @Override
    public int getItemCount() {
        if (subjectList != null) {
            return subjectList.size();
        }
        return 0;
    }

    public class SubjectHolder extends RecyclerView.ViewHolder {
        TextView subjectName, daysOfWeek;
        ImageButton btnDelete;
        public SubjectHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.tvItemSubjectName);
            daysOfWeek = itemView.findViewById(R.id.tvItemDaysOfWeek);
            btnDelete = itemView.findViewById(R.id.btnItemDeleteSubject);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SubjectViewModel model = ViewModelProviders.of((FragmentActivity) context).get(SubjectViewModel.class);
                    model.delete(subjectList.get(getAdapterPosition()));
                }
            });
        }
    }
}
