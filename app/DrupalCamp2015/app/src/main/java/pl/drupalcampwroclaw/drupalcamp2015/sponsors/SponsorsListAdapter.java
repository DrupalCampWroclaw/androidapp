package pl.drupalcampwroclaw.drupalcamp2015.sponsors;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import pl.drupalcampwroclaw.drupalcamp2015.R;

public class SponsorsListAdapter extends ArrayAdapter<InterfaceSponsor> {

	private Context context;
	private ArrayList<InterfaceSponsor> items;
	private LayoutInflater vi;

	public SponsorsListAdapter(Context context, ArrayList<InterfaceSponsor> items) {
		super(context,0, items);

		this.context = context;
		this.items = items;
		vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		final InterfaceSponsor item = items.get(position);
		if (item != null) {
			if(item.isGroup() == true) {
                // For group.
				GroupSponsorsItem group_item = (GroupSponsorsItem) item;

                // Set properties.
				v = vi.inflate(R.layout.list_item_group_sponsor, null);
				v.setOnClickListener(null);
				v.setOnLongClickListener(null);
				v.setLongClickable(false);

				// Set group name.
				final TextView sectionView = (TextView) v.findViewById(R.id.list_item_group_sponsor_title);
				sectionView.setText(group_item.getTitle());
			}
            else {
                // For item sponsor.
				SponsorItem sponsor_item = (SponsorItem) item;

				v = vi.inflate(R.layout.list_item_sponsor, null);

                // Set properties.
                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                // Set title and logo sponsor.
				final TextView title = (TextView) v.findViewById(R.id.list_item_sponsor_name);
				final ImageView logo = (ImageView) v.findViewById(R.id.list_item_sponsor_logo);

				if (logo != null && sponsor_item.getImageIdResource() > 0) {
                    logo.setImageResource(sponsor_item.getImageIdResource());

                    // Set properties title.
                    title.setHeight(0);
                    title.setPadding(0, 0, 0, 0);
                }
                else if (title != null && sponsor_item.getTitle() != null) {
                    // Show if is not image.
                    title.setText(sponsor_item.getTitle());
                }
			}
		}

		return v;
	}

}
