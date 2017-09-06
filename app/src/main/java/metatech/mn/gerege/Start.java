package metatech.mn.gerege;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import metatech.mn.gerege.basic.News;
import metatech.mn.gerege.server.DBUpdate;
import metatech.mn.gerege.transdep.InitTransdep;

public class Start extends AppCompatActivity {

    Spinner spDirection;
    List<String> directionData;

    //static value
    private final static int REQUEST_FROM_CODE = 100;
    private final static int REQUEST_TO_CODE = 200;
    private final static int REQUEST_PASSENGER = 300;

    // info data
    private int start_stop_id;
    private int end_stop_id;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    static TabLayout tabLayout;

    private int[] tabIcons = {
            R.drawable.search,
            R.drawable.ticket,
            R.drawable.icon,
            R.drawable.bus,
            R.drawable.more,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();

        tabLayout.getTabAt(2).select();
        ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(5).setVisibility(View.GONE);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), false);
            }
        });

        Bundle bundle = getIntent().getExtras();
        try{
            Toast.makeText(this, bundle.getString("Aimag") + "-" + bundle.getString("startStopName"), Toast.LENGTH_LONG).show();
            tabLayout.getTabAt(5).select();
        }
        catch (Exception e) {
            Toast.makeText(this, "Empty", Toast.LENGTH_LONG).show();
        }
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);

        ImageView myCustomIcon = (ImageView) LayoutInflater.from(tabLayout.getContext()).inflate(R.layout.tabs_home_image, null);
        tabLayout.getTabAt(2).setCustomView(myCustomIcon);//set ur new params
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;

            switch (getArguments().getInt(ARG_SECTION_NUMBER)){
                case 1: {
                    rootView = inflater.inflate(R.layout.tab_news, container, false);
                    return new News().onCreateView(inflater, container, savedInstanceState);

                }
                case 2: {
                    rootView = inflater.inflate(R.layout.tab_ticket, container, false);
                };break;
                case 3: {
                   rootView = inflater.inflate(R.layout.tab_home, container, false);
                };break;
                case 4: {
                    rootView = inflater.inflate(R.layout.tab_bus, container, false);
                }break;
                case 5: {
                    rootView = inflater.inflate(R.layout.tab_more, container, false);
                }break;
                case 6: {
                    //rootView = inflater.inflate(R.layout.transdep_front, container, false);
                    //rootView = initTransdep.trasView;

                    return new InitTransdep().onCreateView(inflater, container, savedInstanceState);
                }
            }

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.news);
                case 1:
                    return getString(R.string.ticket);
                case 3:
                    return getString(R.string.bus);
                case 4:
                    return getString(R.string.more);
            }
            return null;
        }
    }

    public void Transdep(View v){
        //Intent iBusTimer = new Intent(Start.this, InitTransdep.class);
        //startActivity(iBusTimer);// Activity
        tabLayout.getTabAt(5).select();

        //((ViewGroup) tabLayout.getChildAt(5)).getChildAt(5).setVisibility(View.GONE);
        //LayoutInflater vi = (LayoutInflater) getApplicationContext()
         //       .getSystemService(v.getContext().LAYOUT_INFLATER_SERVICE);
        //View view = vi.inflate(R.layout.transdep_front, null);
        //mViewPager.setAdapter(null);
            // set item content in view
        //mViewPager.addView(view);

        //((ViewGroup) tabLayout.getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
        //Set the next  tab as selected tab


        //mViewPager.setAdapter();
        //tabLayout.refreshDrawableState();

    }

    public void Logout(View v){
        //
        Intent iDBUpdate = new Intent(Start.this, DBUpdate.class);
        startActivity(iDBUpdate);// Activity
    }



    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(resultCode==2){
            String message = data.getStringExtra("MESSAGE");
            EditText etFrom = (EditText) findViewById(R.id.etFrom);
            EditText etTo = (EditText) findViewById(R.id.etTo);
            EditText etPassenger = (EditText) findViewById(R.id.etPassenger);

            if(requestCode == REQUEST_FROM_CODE){
                start_stop_id = data.getIntExtra("STOPID",0);
                etFrom.setText(message);
                etTo.setText("");
            }
            if(requestCode == REQUEST_TO_CODE){
                end_stop_id = data.getIntExtra("STOPID",0);
                etTo.setText(message);
            }

            if(requestCode == REQUEST_PASSENGER){
                end_stop_id = data.getIntExtra("STOPID",0);
                etPassenger.setText(message);
            }

        }
    }

}
