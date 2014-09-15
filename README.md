# **Android ViewPagerAddons** #

This library consists of various addons/extensions that can be used along with a `ViewPager` (from the support library) to enhance the user experience. The library project is included in a module named `viewpageraddons-library`.   
[Download the sample app here.](https://bitbucket.org/enthusiast94/viewpageraddons-android-library/downloads/viewpageraddons-sample-app-debug.apk) 

___

# **1. SlidingTabLayoutColor** #

 This layout imitates the sliding tab behavior noticed in the Google Play app, and also adds ability to set different colors for different tabs (and ActionBar) which blend together as the user moves from one tab to another. This can be seen in the screenshots below: 

![Screenshot_2014-09-03-12-10-31.png](https://bitbucket.org/repo/7rARLB/images/2997221742-Screenshot_2014-09-03-12-10-31.png) ![Screenshot_2014-09-03-12-10-38.png](https://bitbucket.org/repo/7rARLB/images/806519476-Screenshot_2014-09-03-12-10-38.png) ![Screenshot_2014-09-03-12-10-44.png](https://bitbucket.org/repo/7rARLB/images/957389261-Screenshot_2014-09-03-12-10-44.png)

## ** How do I get set up? ** ##
1. Import the library module into your project and add the following dependency to your build.gradle: 

		dependencies {
	    	compile project(':viewpageraddons-library')
		} 

2. Include `SlidingTabLayoutColor` in your layout as follows: 

		<LinearLayout
		    xmlns:android="http://schemas.android.com/apk/res/android"
		    xmlns:tools="http://schemas.android.com/tools"
		    xmlns:cstl="http://schemas.android.com/apk/res-auto"
		    android:layout_width="match_parent"
		    android:layout_height="match_parent"
		    android:orientation="vertical">

		    <com.manas.viewpageraddons.view.SlidingTabLayoutColor
		        android:id="@+id/slidingTabLayoutColor"
		        android:layout_width="match_parent"
		        android:layout_height="wrap_content"/>

		    <android.support.v4.view.ViewPager
		        android:id="@+id/pager"
		        android:layout_width="match_parent"
		        android:layout_height="match_parent"/>
		</LinearLayout> 

3. Inside your activity's `onCreate` (or fragment's `onCreateView`), bind the `ViewPager` with `SlidingTabLayoutColor`: 
		
		//Initialize ViewPager and set adapter
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new CustomPagerAdapter(getFragmentManager()));

        //Initialize SlidingTabLayoutColor and bind it to ViewPager
        SlidingTabLayoutColor slidingTabsLayout = (SlidingTabLayoutColor) findViewById(R.id.slidingTabLayoutColor);
        slidingTabsLayout.setViewPager(pager);

        //optional - bind the ActionBar only if you want it to be colored along with the tab indicators 
        slidingTabsLayout.setActionBar(getActionBar());

4. **(Optional)** If you want to use your own implementation of `ViewPager.OnPageChangeListener`, then you can bind it to SlidingTabLayoutColor: 

		slidingTabLayoutColor.setDelegatePageChangeListener(myPageChangeListener); 

5. **(Optional)** If you want to set colors for your tabs, then you need to let your `ViewPager` adapter implement `SlidingTabLayoutColor.ColorProvider` interface:

		private static class CustomPagerAdapter extends FragmentPagerAdapter implements  SlidingTabLayoutColor.ColorProvider {
        	//other methods overriden above

        	final int[] colors = new int[]{Color.parseColor("#0F9D58"), Color.parseColor("#3F5CA9"), Color.parseColor("#EF6C00"), Color.parseColor("#7E3794")};
        	@Override
        	public int getPageColor(int pos) {
            	return colors[pos];
        	}
		}

6. **(Optional)** If you want image tabs instead of text tabs, then you need to let your `ViewPager` adapter implement `SlidingTabLayoutColor.ImageProvider` interface:

		private static class CustomPagerAdapter extends FragmentPagerAdapter implements  SlidingTabLayoutColor.ImageProvider {
        	//other methods overriden above

        	final int[] imageResourceIDs = new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4)};
        	@Override
        	public int getPageImage(int pos) {
            	return imageResourceIDs[pos];
        	}
		}

## ** How do I customize? ** ##
You can use the following xml attributes to customize `SlidingTabLayoutColor`:

* `vpa:indicatorHeight`   Set tab indicator height (default 6dp)
* `vpa:indicatorColor`   Set tab indicator color (default #33b5e5)
* `vpa:dividerWidth`   Set tab divider width (default 1dp)
* `vpa:dividerColor`   Set tab divider color (default #cccccc)
* `vpa:dividerPadding`   Set tab divider padding (default 10dp)
* `vpa:tabPaddingLeft`   Set left tab padding (default 20dp)
* `vpa:tabPaddingTop`   Set top tab padding (default 15dp)
* `vpa:tabPaddingRight`   Set right tab padding (default 20dp)
* `vpa:tabPaddingBottom`   Set bottom tab padding (default 15dp)
* `vpa:textSize`   Set tab text size (default 12sp)
* `vpa:textColor`   Set tab text color when unselected (default #80000000)
* `vpa:textColorSelected`   Set tab text color when selected (default #000000)
* `vpa:underLineHeight`   Set underLine (the full width line below tabs) height (default 2dp)
* `vpa:underLineColor`   Set underLine color (default #cccccc)
* `vpa:shouldExpand`   Set to true if you want the tabs to expand and fit screen width (default false)
* `vpa:showDividers`   Set to true to show dividers (default true)
* `vpa:tabTextAllBold`   Set to true to make tab text bold (default true)
* `vpa:tabTextAllCaps`   Set to true to make tab text uppercase (default true)
* `vpa:scrollOffset`   Set the width of the gap b/w the left edge of the indicator and screen while scrolling (default 50dp)

___

# ** 2. SlidingPagerIndicator ** #

* Coming soon...

___

# ** Developed by ** ##

*  Manas Bajaj - <manas.bajaj94@gmail.com>

# ** Credits ** ##

* Blog post explanation - <http://www.pushing-pixels.org/2013/05/09/android-bits-and-pieces-sliding-tabs-madness.html>
* ActionBar bug fix for pre-API 17 - <http://cyrilmottier.com/2012/11/27/actionbar-on-the-move/>