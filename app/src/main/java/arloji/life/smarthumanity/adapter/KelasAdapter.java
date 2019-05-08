package arloji.life.smarthumanity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import arloji.life.smarthumanity.R;
import arloji.life.smarthumanity.utils.string.StringHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

public class KelasAdapter extends AbstractItem<KelasAdapter, KelasAdapter.ViewHolder> {

    private int icon;
    private String title;
    private int classDuration;
    private int pertemuan;
    private Long price;

    public KelasAdapter(int icon, String title, int classDuration, int pertemuan, Long price) {
        this.icon = icon;
        this.title = title;
        this.classDuration = classDuration;
        this.pertemuan = pertemuan;
        this.price = price;
    }

    @Override
    public int getType() {
        return R.id.detail_kelas_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.adapter_detail_kelas;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon)
        AppCompatImageView icon;
        @BindView(R.id.titleTextView)
        AppCompatTextView titleTextView;
        @BindView(R.id.classDurationTextView)
        AppCompatTextView classDurationTextView;
        @BindView(R.id.pertemuanTextView)
        AppCompatTextView pertemuanTextView;
        @BindView(R.id.priceTextView)
        AppCompatTextView priceTextView;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }
    }

    @Override
    public void bindView(@NonNull ViewHolder holder,
                         @NonNull List<Object> payloads) {
        super.bindView(holder, payloads);
        holder.icon.setBackgroundResource(icon);
        holder.titleTextView.setText(title);
        holder.classDurationTextView.setText(StringHelper.Companion.getStringBuilderToString(String.valueOf(classDuration), " Bulan"));
        holder.pertemuanTextView.setText(StringHelper.Companion.getStringBuilderToString(String.valueOf(pertemuan), " Pertemuan"));
        holder.priceTextView.setText(StringHelper.Companion.getPriceInRp(price));
    }

    @Override
    public void unbindView(@NonNull ViewHolder holder) {
        super.unbindView(holder);
        holder.icon.setBackgroundResource(0);
        holder.titleTextView.setText(null);
        holder.classDurationTextView.setText(null);
        holder.pertemuanTextView.setText(null);
        holder.priceTextView.setText(null);
    }
}
