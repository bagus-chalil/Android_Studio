package id.com.RestApiFull;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by M Bagus Chalil A on 14/06/2021.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Result> results;

    public RecyclerViewAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = results.get(position);
        holder.textViewKode.setText(result.getKode());
        holder.textViewNama.setText(result.getNama());
        holder.textViewHarga.setText(result.getHarga());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.textKode) TextView textViewKode;
        @BindView(R.id.textNama) TextView textViewNama;
        @BindView(R.id.textHarga) TextView textViewHarga;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String kode = textViewKode.getText().toString();
            String nama = textViewNama.getText().toString();
            String harga = textViewHarga.getText().toString();

            Intent i = new Intent(context, UpdateActivity.class);
            i.putExtra("kode", kode);
            i.putExtra("nama", nama);
            i.putExtra("harga", harga);
            context.startActivity(i);
        }
    }
}
