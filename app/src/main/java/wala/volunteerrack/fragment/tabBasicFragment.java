package wala.volunteerrack.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import wala.volunteerrack.R;
import wala.volunteerrack.SlidingTabLayout;

/**
 * Created by liuqi on 3/7/2016.
 */
public class tabBasicFragment extends Fragment {

    private final String LOG_TAG = "debug";
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private SamplePagerAdapter samplePagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // BEGIN_INCLUDE (setup_viewpager)
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        samplePagerAdapter = new SamplePagerAdapter();

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        //set how many page should be load each time (including background view)
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(samplePagerAdapter);
        // END_INCLUDE (setup_viewpager)

        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        //mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        //mSlidingTabLayout.setViewPager(mViewPager);
        // END_INCLUDE (setup_slidingtablayout)


        //getListView().setOnItemClickListener(this);

        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();
    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        try {
            long id = getListView().getItemIdAtPosition(0);
            Log.d("debug","list item id @ position 0" +id);
        }
        catch (Exception e){Log.d("debug","list failed" +e);}
        */
    }

    /**
     * Adding custom view to tab
     */
    private void setupTabIcons() {
        try{
            TextView tabOne = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            tabOne.setText("Opportunity");
            tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_favourite, 0, 0);
            tabLayout.getTabAt(0).setCustomView(tabOne);

            TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            tabTwo.setText("Event");
            tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_call, 0, 0);
            tabLayout.getTabAt(1).setCustomView(tabTwo);

            TextView tabThree = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            tabThree.setText("Collaborator");
            tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_contacts, 0, 0);
            tabLayout.getTabAt(2).setCustomView(tabThree);

            TextView tabFour = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
            tabFour.setText("Volunteer");
            tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_contacts, 0, 0);
            tabLayout.getTabAt(3).setCustomView(tabFour);
        }catch (NullPointerException e){
            //todo handle null pointer exc
            Log.d("debug","null pointer @ setupTabIcons");
        }
    }

    /**
     * The {@link android.support.v4.view.PagerAdapter} used to display pages in this sample.
     * The individual pages are simple and just display two lines of text. The important section of
     * this class is the {@link #getPageTitle(int)} method which controls what is displayed in the
     * {@link SlidingTabLayout}.
     *
     * Position in pagerAdapter :
     * #0~3 is tab in sequence
     * #4 is opportunity details
     * #5 is collaborator details
     */
    class SamplePagerAdapter extends PagerAdapter {

        //todo need to be modify in order to delete view correctly
        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount() {
            return 4;
        }

        /**
         * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
         * same object as the {@link View} added to the {@link ViewPager}.
         */
        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        // BEGIN_INCLUDE (pageradapter_getpagetitle)
        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link SlidingTabLayout}.
         * <p>
         * Here we construct one using the position value, but for real application the title should
         * refer to the item's contents.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return "Item " + (position + 1);
        }
        // END_INCLUDE (pageradapter_getpagetitle)

        /**
         * Instantiate the {@link View} which should be displayed at {@code position}. Here we
         * inflate a layout from the apps resources and then change the text view to signify the position.
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view;
            switch (position){
                case 0:
                    view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
                            container, false);
                    getActivity().setTitle("Opportunity");
                    break;
                case 1:
                    view = getActivity().getLayoutInflater().inflate(R.layout.pager_item_test,
                            container, false);
                    getActivity().setTitle("Event");
                    break;
                case 2:
                    view = getActivity().getLayoutInflater().inflate(R.layout.pager_item_test,
                            container, false);

                    getActivity().setTitle("Collaborator");
                    break;
                case 3:
                    view = getActivity().getLayoutInflater().inflate(R.layout.pager_item_test,
                            container, false);

                    getActivity().setTitle("Volunteer");
                    break;
                case 4:
                    view = getActivity().getLayoutInflater().inflate(R.layout.pager_item_test4,
                            container, false);

                    getActivity().setTitle("Details");
                    break;
                default:
                    view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
                            container, false);

                    getActivity().setTitle("Volunteer Rack");
                    break;
            }
            // Inflate a new layout from our resources
            //view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
            //        container, false);
            // Add the newly created View to the ViewPager
            container.addView(view);

            // Retrieve a TextView from the inflated View, and update it's text
            TextView description = (TextView) view.findViewById(R.id.item_title);
            //title.setText(String.valueOf(position + 1));
            String tempDescription = "1!";
            description.setText(tempDescription);

            ArrayList<String> list = new ArrayList<>();
            list.add("comment 1");list.add("comment 2");list.add("comment 3");list.add("comment 4");
            //todo may change as using dynamic input
            StableArrayAdapter listAdapter = new StableArrayAdapter(getActivity(),R.layout.item_in_list,list);
            //ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(),R.layout.item_in_list, list);
            ListView listView = (ListView)view.findViewById(R.id.list);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                /**
                 * position and id is the position inside list view, referring to list item
                 * View is
                 * parent is the AdapterView, as the container of the actual view page area.
                 */
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i(LOG_TAG, "On item click view tag is : " + view.getTag() );
                    Log.i(LOG_TAG, "On item click view id is : " + view.getId() );
                    samplePagerAdapter.instantiateItem((ViewGroup)parent.getParent(), 4);
                }
            });

            Log.i(LOG_TAG, "instantiateItem() [position: " + position + "]");
            // Return the View
            return view;
        }

        /**
         * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
         * {@link View}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            Log.i(LOG_TAG, "destroyItem() [position: " + position + "]");
        }
    }



    /**
     * StableArrayAdapter class is used to implement a sub view of the ListView.
     * customize with pic & text & location.
     * todo make this class as a public abstract that implement by each fragment
     */
    private class StableArrayAdapter extends ArrayAdapter<String> {
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

        /*@Override
        public long getItemId(int position) {
            Diary item = getItem(position);
            return mIdMap.get(item);
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }*/

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
                    case "company 1":
                        imageView.setImageResource(R.drawable.testpic);
                        break;
                    case "company 2":
                        imageView.setImageResource(R.drawable.testpic2);
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

}
