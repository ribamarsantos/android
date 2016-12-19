package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ribamarmjs.weather.R;

import java.util.List;

import listener.OnCityClickListener;
import model.City;

/**
 * Created by ribamarmjs on 17/12/16.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.VH> {

    private List<City> mCities;
    private Context mContext;
    private OnCityClickListener mCityClickListener;


    public CityAdapter(List<City> mCities, Context mContext) {
        this.mCities = mCities;
        this.mContext = mContext;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_city, parent, false);
        VH viewHolder = new VH(view);

        view.setTag(viewHolder);
        view.setOnClickListener(mClickListener);
        return viewHolder   ;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        City city = mCities.get(position);

        holder.txtCityName.setText(city.getName());
    }

    @Override
    public int getItemCount() {
        return mCities != null ? mCities.size() : 0;
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            VH vh = (VH) view.getTag();

            int position = vh.getAdapterPosition();
            City city = mCities.get(position);

            mCityClickListener.onCityClick(city, position);
        }
    };


    public void setCityClickListener(OnCityClickListener clickListener){
        this.mCityClickListener = clickListener;
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView txtCityName;
        public VH(View itemView) {
            super(itemView);
            txtCityName = (TextView) itemView.findViewById(R.id.city_name);
        }
    }
}
