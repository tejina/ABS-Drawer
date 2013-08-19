package com.natewickstrom.actionbarcompatdrawerdemo;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.natewickstrom.actionbarcompatdrawer.ActionBarDrawerToggleCompat;
import com.natewickstrom.actionbarcompatdrawerdemo.listener.MyListener;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.content.res.Configuration;
import android.view.View;

public class DemoActivity extends SherlockFragmentActivity implements MyListener {

	private DrawerLayout mDrawerLayout;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ActionBarDrawerToggleCompat mDrawerToggle;
	private HomeFragment mHomeFragment;
	private DrawerFragment mDrawerFragment;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation_drawer);
        
        mTitle = getTitle();
        mDrawerTitle = this.getString(R.string.drawer_open);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout); 
        
        FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		mDrawerFragment = (DrawerFragment) fm.findFragmentByTag("Drawer");
		mHomeFragment = (HomeFragment) fm.findFragmentByTag("Home");
		
		if (mDrawerFragment == null) {
			mDrawerFragment = new DrawerFragment();	
			ft.add(R.id.left_drawer, mDrawerFragment, "Drawer");
		} else {
			ft.show(mDrawerFragment);
		}
		
		if (mHomeFragment == null) {
			mHomeFragment = new HomeFragment();	
			ft.add(R.id.fragment_holder, mHomeFragment, "Home");
		} else {
			ft.show(mHomeFragment);
		}		
		
		ft.commit();   
		
		// set a custom shadow that overlays the main content when the drawer opens
		// this has to be set after we add the Fragment
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggleCompat(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
            }

            public void onDrawerOpened(View drawerView) {
            	mDrawerFragment.refresh();
                getSupportActionBar().setTitle(mDrawerTitle);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
	}
	
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getSupportMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	    	
    	// Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
    	
        // Handle your other action bar items...
		switch (item.getItemId()) {
			
		case R.id.action_settings:
			break;
		}	
	
		return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


	@Override
	public int getSomeRandomInfoInIntegerForm() {
		return mHomeFragment.getInt();
	}

}
