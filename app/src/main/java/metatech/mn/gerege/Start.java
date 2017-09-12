package metatech.mn.gerege;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.QwertyKeyListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import metatech.mn.gerege.fragment.FragmentBus;
import metatech.mn.gerege.fragment.FragmentHome;
import metatech.mn.gerege.fragment.FragmentMore;
import metatech.mn.gerege.fragment.FragmentNews;
import metatech.mn.gerege.fragment.FragmentTicket;
import metatech.mn.gerege.transdep.InitTransdep;

public class Start extends AppCompatActivity {

    Spinner spDirection;
    List<String> directionData;

    //static value
    private final static int REQUEST_FROM_CODE = 100;
    private final static int REQUEST_TO_CODE = 200;
    private final static int REQUEST_PASSENGER = 300;

    public final static int TAB_NEWS = 0;
    public final static int TAB_TICKET = 1;
    public final static int TAB_HOME = 2;
    public final static int TAB_BUS = 3;
    public final static int TAB_MORE = 4;
    public final static int TAB_TRANSDEP = 5;

    // info data
    private int start_stop_id;
    private int end_stop_id;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private Bundle bundleFromFragment;
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

        mSectionsPagerAdapter.addFragment(new FragmentNews(), "news");
        mSectionsPagerAdapter.addFragment(new FragmentTicket(), "ticket");
        mSectionsPagerAdapter.addFragment(new FragmentHome(), "home");
        mSectionsPagerAdapter.addFragment(new FragmentBus(), "bus");
        mSectionsPagerAdapter.addFragment(new FragmentMore(), "more");
        mSectionsPagerAdapter.addFragment(new InitTransdep(), "transdep");

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
        setupTabIcons(tabLayout);

        tabLayout.getTabAt(TAB_HOME).select();
        ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(TAB_TRANSDEP).setVisibility(View.GONE);

        bundleFromFragment = getIntent().getExtras();
        try {
            bundleFromFragment.getString("startStopName");
            tabLayout.getTabAt(TAB_TRANSDEP).select();
        } catch (Exception e) {

        }

    }

    public String getEditTextString(String string) {
        try{
            String s = bundleFromFragment.getString(string);
            if (s == null)
                return "";

            return s;
        } catch (Exception e) {
            return "";
        }

    }

    private void setupTabIcons(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);

        ImageView myCustomIcon = (ImageView) LayoutInflater.from(tabLayout.getContext()).inflate(R.layout.tabs_home_image, null);
        tabLayout.getTabAt(2).setCustomView(myCustomIcon);//set ur new params
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;
        private List<String> fragmentsTitle;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
            this.fragmentsTitle = new ArrayList<>();
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentsTitle.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentsTitle.get(position);
        }
    }

    public void setTab(int position) {
        tabLayout.getTabAt(position).select();
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
