package pl.drupalcampwroclaw.drupalcamp2015.sponsors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pl.drupalcampwroclaw.drupalcamp2015.R;


public class SponsorAdapter extends ArrayAdapter<Sponsor> {

    private List<Sponsor> itemList;
    private Context context;

    public SponsorAdapter(List<Sponsor> itemList, Context ctx) {
        super(ctx, android.R.layout.simple_list_item_1, itemList);
        this.itemList = itemList;
        this.context = ctx;
    }

    public int getCount() {
        if (itemList != null)
            return itemList.size();
        return 0;
    }

    public Sponsor getItem(int position) {
        if (itemList != null)
            return itemList.get(position);
        return null;
    }

    public long getItemId(int position) {
        if (itemList != null)
            return itemList.get(position).hashCode();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_sponsor, null);
        }

        Sponsor sponsor = itemList.get(position);

        TextView tb_1 = (TextView) v.findViewById(R.id.textSponsorNameView);
        tb_1.setText(sponsor.getNameSponsor());

        ImageView im_1 = (ImageView) v.findViewById(R.id.imageSponsorView);
        //im_1.setImage

        return v;

    }

    public List<Sponsor> getItemList() {
        return itemList;
    }

    public void setItemList(List<Sponsor> itemList) {
        this.itemList = itemList;
    }

}

