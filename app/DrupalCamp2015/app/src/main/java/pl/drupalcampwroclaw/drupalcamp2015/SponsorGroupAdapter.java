package pl.drupalcampwroclaw.drupalcamp2015;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class SponsorGroupAdapter extends ArrayAdapter<SponsorGroup> {

    private List<SponsorGroup> itemList;
    private Context context;

    public SponsorGroupAdapter(List<SponsorGroup> itemList, Context ctx) {
        super(ctx, android.R.layout.simple_list_item_1, itemList);
        this.itemList = itemList;
        this.context = ctx;
    }

    public int getCount() {
        if (itemList != null)
            return itemList.size();
        return 0;
    }

    public SponsorGroup getItem(int position) {
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
            v = inflater.inflate(R.layout.list_group_sponsor, null);
        }

        SponsorGroup sponsor_group = itemList.get(position);

        TextView tb_1 = (TextView) v.findViewById(R.id.textGroupSponsorView);
        tb_1.setText(sponsor_group.getName());

        for (int i = 0; i < sponsor_group.getSize(); i++) {
            // @TODO: Wyświetlanie list sponsorów i loga.
            ListView vw_1 = (ListView) v.findViewById(R.id.listGroupSponsorView);
            SponsorAdapter sa = new SponsorAdapter(sponsor_group.getSponsors(), this.context);
            vw_1.setAdapter(sa);
        }

        return v;

    }

    public List<SponsorGroup> getItemList() {
        return itemList;
    }

    public void setItemList(List<SponsorGroup> itemList) {
        this.itemList = itemList;
    }

}

