package au.edu.unsw.infs3634_lab.recycler_view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import au.edu.unsw.infs3634_lab.R;
import au.edu.unsw.infs3634_lab.api.Crypto;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.MyViewHolder> implements Filterable {
    private ArrayList<Crypto> localDataSet, localDataSetFiltered;
    private RecyclerViewClickListener localListener;
    public final static int SORT_METHOD_NAME = 1;
    public final static int SORT_METHOD_VALUE = 2;

    public CryptoAdapter(ArrayList<Crypto> dataSet, RecyclerViewClickListener listener) {
        localDataSet = dataSet;
        localDataSetFiltered = dataSet;
        localListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cryptolistrow, parent, false);
        return new MyViewHolder(view, localListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Crypto crypto = localDataSetFiltered.get(position);
        holder.name.setText(crypto.getName());
        holder.value.setText(crypto.getPriceUsd());
        holder.change.setText(crypto.getPercentChange1h());
        holder.itemView.setTag(crypto.getId());
    }

    @Override
    public int getItemCount() {
        return localDataSetFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    localDataSetFiltered.clear();
                    localDataSetFiltered.addAll(localDataSet);
                } else {
                    ArrayList<Crypto> filteredList = new ArrayList<>();
                    for (Crypto crypto : localDataSet) {
                        Log.d("DetailActivity", charString.toLowerCase() + " " + crypto.getName().toLowerCase());
                        if (crypto.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(crypto);
                        }
                    }
                    localDataSetFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = localDataSetFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                localDataSetFiltered = (ArrayList<Crypto>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name, value, change;

        public MyViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            image = itemView.findViewById(R.id.ivCrypto);
            name = itemView.findViewById(R.id.tvRowName);
            value = itemView.findViewById(R.id.tvRowValue);
            change = itemView.findViewById(R.id.tvRowChange);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickRow((String) itemView.getTag());
                }
            });
        }
    }

    public void sortList(final int sortMethod) {
        if (localDataSetFiltered.size() > 0) {
            Collections.sort(localDataSetFiltered, new Comparator<Crypto>() {
                @Override
                public int compare(Crypto c1, Crypto c2) {
                    if (sortMethod == SORT_METHOD_NAME) {
                        return c1.getName().compareTo(c2.getName());
                    } else if (sortMethod == SORT_METHOD_VALUE) {
                        return Double.valueOf(c1.getPriceUsd()).compareTo(Double.valueOf(c2.getPriceUsd()));
                    }
                    return c1.getName().compareTo(c2.getName());
                }
            });
        }
        notifyDataSetChanged();
    }

    public void setData(List<Crypto> data) {
        localDataSet.clear();
        localDataSet.addAll(data);
        notifyDataSetChanged();
    }
}
