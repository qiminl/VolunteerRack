package wala.volunteerrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by liuqi on 3/9/2016.
 */
public class StableArrayAdapter extends ArrayAdapter<String> {
    /**
     * StableArrayAdapter class is used to implement a sub view of the ListView.
     * customize with pic & text & location.
     * todo make this class as a public abstract that implement by each fragment
     */
    HashMap< Integer, String> mIdMap = new HashMap< Integer, String>();
    private final Context context;

    public StableArrayAdapter(Context context, int textViewResourceId,
                              ArrayList<String> company_list) {

        super(context, textViewResourceId, company_list);
        this.context = context;
        for (int i = 0; i < company_list.size(); ++i) {
            mIdMap.put(i, company_list.get(i));
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.item_in_list, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.text1);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.image1);
        imageView.setAdjustViewBounds(true);//adjust ratio

        String company = mIdMap.get(position);
        //todo handle image properly, image online or local sd or sqlite
        if(company != null){
            textView.setText(company);
            switch (company.toLowerCase()){
                case "do what you love":
                    imageView.setImageResource(R.drawable.dowhatyoulove);
                    break;
                case "social diversity":
                    imageView.setImageResource(R.drawable.sdc);
                    break;
                case "tian -jin temple":
                    imageView.setImageResource(R.drawable.tjt);
                    break;
                case "cscf":
                    imageView.setImageResource(R.drawable.cscf);
                    break;
                case "xinviteer":
                    imageView.setImageResource(R.drawable.xinviteer);
                    break;
                default:
                    imageView.setImageResource(R.drawable.testpic3);
                    break;
            }
        }
        //MediaStore.Images.Media.insertImage(getContentResolver(), yourBitmap, yourTitle , yourDescription);
        return rowView;
    }


}
