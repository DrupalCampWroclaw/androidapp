package pl.drupalcampwroclaw.drupalcamp.sessions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pl.drupalcampwroclaw.drupalcamp.R;


public class SessionAdapter extends ArrayAdapter<Session> {

    private List<Session> itemList;
    private Context context;

    public SessionAdapter(List<Session> itemList, Context ctx) {
        super(ctx, android.R.layout.simple_list_item_1, itemList);
        this.itemList = itemList;
        this.context = ctx;
    }

    public int getCount() {
        if (itemList != null)
            return itemList.size();
        return 0;
    }

    public Session getItem(int position) {
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
            v = inflater.inflate(R.layout.list_item_session, null);
        }

        Session session = itemList.get(position);

        TextView tb_1 = (TextView) v.findViewById(R.id.name);
        tb_1.setText(session.getName());

        TextView tb_2 = (TextView) v.findViewById(R.id.speakers);
        tb_2.setText(session.getSpeakers());

        TextView tb_3 = (TextView) v.findViewById(R.id.time);
        tb_3.setText(session.getTime());

        TextView tb_4 = (TextView) v.findViewById(R.id.room);
        tb_4.setText(session.getRoom());

        TextView tb_5 = (TextView) v.findViewById(R.id.language);
        tb_5.setText(session.getLanguage());

        return v;

    }

    public List<Session> getItemList() {
        return itemList;
    }

    public void setItemList(List<Session> itemList) {
        this.itemList = itemList;
    }


}

