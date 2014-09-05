package com.manas.viewpageraddons.view;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

/**
* Created by Manas on 8/31/2014.
*/
public class SlidingTabLayoutColor extends HorizontalScrollView {

    private final String TAG = "SlidingTabLayoutColor";
    private Resources res;
    private LinearLayout outerLinearLayout;
    private LinearLayout tabsContainer;
    private LinearLayout.LayoutParams tabLParams;
    private boolean shouldExpand = false;
    private Paint indicatorPaint;
    private Paint dividerPaint;
    private ViewPager pager;
    private ActionBar actionBar;
    private ColorDrawable actionBarBacground;
    private PageChangeListener pageChangeListener;
    private ViewPager.OnPageChangeListener delegateListener;
    private int currentPos;
    private int previousPos = 0;
    private float positionOffset;
    private float scrollOffset;
    private int tabCount;
    private int textColor;
    private int textColorSelected;
    private int indicatorColor;
    private int dividerColor;
    private float indicatorHeight;
    private float dividerWidth;
    private float dividerPadding;
    private float textPaddingLeft;
    private float textPaddingTop;
    private float textPaddingRight;
    private float textPaddingBottom;
    private float textSize;
    private float tabContainerHeight;
    private boolean tabTextAllCaps = true;
    private OnClickListener tabClickListener;
    private boolean showDividers = true;
    private boolean areColorsProvided = false;
    private boolean areImagesProvided = false;
    private boolean tabTextAllBold = true;
    private float underLineHeight;
    private int underLineColor;


    public SlidingTabLayoutColor(Context context) {
        super(context);
        init(context, null);
    }

    public SlidingTabLayoutColor(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SlidingTabLayoutColor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setWillNotDraw(false);
        setFillViewport(true);
        setHorizontalScrollBarEnabled(false);

        res = getResources();

        //set default colors and dimens
        indicatorColor = res.getColor(R.color.indicatorColor);
        dividerColor = res.getColor(R.color.dividerColor);
        textColor = res.getColor(R.color.textColor);
        textColorSelected = res.getColor(R.color.textColorSelected);
        indicatorHeight = res.getDimension(R.dimen.indicatorHeight);
        dividerWidth = res.getDimension(R.dimen.dividerWidth);
        dividerPadding = res.getDimension(R.dimen.dividerPadding);
        textPaddingLeft = res.getDimension(R.dimen.textPaddingLeft);
        textPaddingTop = res.getDimension(R.dimen.textPaddingTop);
        textPaddingRight = res.getDimension(R.dimen.textPaddingRight);
        textPaddingBottom = res.getDimension(R.dimen.textPaddingBottom);
        textSize = res.getDimension(R.dimen.textSize);
        underLineColor = res.getColor(R.color.underLineColor);
        underLineHeight = res.getDimension(R.dimen.underLineHeight);
        scrollOffset = res.getDimension(R.dimen.scrollOffset);

        //set user colors and dimens
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlidingTabLayoutColor);
        if (typedArray != null) {
            int indicatorColor = typedArray.getColor(R.styleable.SlidingTabLayoutColor_indicatorColor, 0);
            if (indicatorColor != 0) {
                this.indicatorColor = indicatorColor;
            }
            int dividerColor = typedArray.getColor(R.styleable.SlidingTabLayoutColor_dividerColor, 0);
            if (dividerColor != 0) {
                this.dividerColor = dividerColor;
            }
            int textColor = typedArray.getColor(R.styleable.SlidingTabLayoutColor_textColor, 0);
            if (textColor != 0) {
                this.textColor = textColor;
            }
            int textColorSelected = typedArray.getColor(R.styleable.SlidingTabLayoutColor_textColorSelected, 0);
            if (textColorSelected != 0) {
                this.textColorSelected = textColorSelected;
            }
            float indicatorHeight = typedArray.getDimension(R.styleable.SlidingTabLayoutColor_indicatorHeight, 0f);
            if (indicatorHeight != 0f) {
                this.indicatorHeight = indicatorHeight;
            }
            float dividerWidth = typedArray.getDimension(R.styleable.SlidingTabLayoutColor_dividerWidth, 0f);
            if (dividerWidth != 0f) {
                this.dividerWidth = dividerWidth;
            }
            float dividerPadding = typedArray.getDimension(R.styleable.SlidingTabLayoutColor_dividerPadding, 0f);
            if (dividerPadding != 0f) {
                this.dividerPadding = dividerPadding;
            }
            float textPaddingLeft = typedArray.getDimension(R.styleable.SlidingTabLayoutColor_tabPaddingLeft, 0f);
            if (textPaddingLeft != 0f) {
                this.textPaddingLeft = textPaddingLeft;
            }
            float textPaddingTop = typedArray.getDimension(R.styleable.SlidingTabLayoutColor_tabPaddingTop, 0f);
            if (textPaddingTop != 0f) {
                this.textPaddingTop = textPaddingTop;
            }
            float textPaddingRight = typedArray.getDimension(R.styleable.SlidingTabLayoutColor_tabPaddingRight, 0f);
            if (textPaddingRight != 0f) {
                this.textPaddingRight = textPaddingRight;
            }
            float textPaddingBottom = typedArray.getDimension(R.styleable.SlidingTabLayoutColor_tabPaddingBottom, 0f);
            if (textPaddingBottom != 0f) {
                this.textPaddingBottom = textPaddingBottom;
            }
            float textSize = typedArray.getDimension(R.styleable.SlidingTabLayoutColor_textSize, 0f);
            if (textSize != 0f) {
                this.textSize = textSize;
            }
            int underLineColor = typedArray.getColor(R.styleable.SlidingTabLayoutColor_underLineColor, 0);
            if (underLineColor != 0) {
                this.underLineColor = underLineColor;
            }
            float underLineHeight = typedArray.getDimension(R.styleable.SlidingTabLayoutColor_underLineHeight, 0f);
            if (underLineHeight != 0f) {
                this.underLineHeight = underLineHeight;
            }
            float scrollOffset = typedArray.getDimension(R.styleable.SlidingTabLayoutColor_scrollOffset, 0f);
            if (scrollOffset != 0f) {
                this.scrollOffset = scrollOffset;
            }
            tabTextAllCaps = typedArray.getBoolean(R.styleable.SlidingTabLayoutColor_tabTextAllCaps, true);
            tabTextAllBold = typedArray.getBoolean(R.styleable.SlidingTabLayoutColor_tabTextAllBold, true);
            shouldExpand = typedArray.getBoolean(R.styleable.SlidingTabLayoutColor_shouldExpand, false);
            showDividers = typedArray.getBoolean(R.styleable.SlidingTabLayoutColor_showDividers, true);
        }

        //init tab click listener
        tabClickListener = new OnClickListener() {

            @Override
            public void onClick(View view) {
                pager.setCurrentItem((Integer) view.getTag());
            }
        };

        //init paints
        indicatorPaint = new Paint();
        indicatorPaint.setColor(indicatorColor);
        indicatorPaint.setAntiAlias(true);
        indicatorPaint.setStyle(Paint.Style.FILL);
        dividerPaint = new Paint();
        dividerPaint.setColor(dividerColor);
        dividerPaint.setAntiAlias(true);
        dividerPaint.setStyle(Paint.Style.FILL);

        //init outerLinearLayout frame layout and add
        outerLinearLayout = new LinearLayout(context);
        outerLinearLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        outerLinearLayout.setOrientation(LinearLayout.VERTICAL);
        addView(outerLinearLayout);

        //init and add tabsContainer linear layout to outerLinearLayout
        tabsContainer = new LinearLayout(context);
        tabsContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        outerLinearLayout.addView(tabsContainer);

        //add underline to outerLinearLayout
        View underLine = new View(context);
        LayoutParams underLineLParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) underLineHeight);
        underLineLParams.gravity = Gravity.BOTTOM;
        underLine.setLayoutParams(underLineLParams);
        underLine.setBackgroundColor(underLineColor);
        outerLinearLayout.addView(underLine);

        //init text tab layout params
        if (shouldExpand) {
            tabLParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
        }
        else {
            tabLParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }

    }

    public void setViewPager(ViewPager pager) {
        if (pager != null) {
            if (pager.getAdapter() != null) {
                this.pager = pager;

                pageChangeListener = new PageChangeListener();
                pager.setOnPageChangeListener(pageChangeListener);

                if (pager.getAdapter() instanceof ColorProvider) {
                    areColorsProvided = true;
                }

                if (pager.getAdapter() instanceof ImageProvider) {
                    areImagesProvided = true;
                }

                notifiyDataSetChanged();
            }
            else {
                throw new IllegalArgumentException("The supplied ViewPager instance must have an adapter attached to it");
            }
        }
        else {
            throw new IllegalArgumentException("The supplied ViewPager instance cannot be null");
        }
    }

    public void setActionBar(ActionBar actionBar) {
        if (actionBar != null) {
            this.actionBar = actionBar;
            actionBarBacground = new ColorDrawable(Color.TRANSPARENT);
            actionBar.setBackgroundDrawable(actionBarBacground);
        }
        else {
            throw new IllegalArgumentException("The supplied ActionBar instance cannot be null");
        }
    }

    public void setDelegatePageChangeListener(ViewPager.OnPageChangeListener delegateListener) {
        if (this.delegateListener == null) {
            this.delegateListener = delegateListener;
        }
        else {
            throw new IllegalStateException("A delegate listener has already been set");
        }
    }

    public void notifiyDataSetChanged() {
        tabsContainer.removeAllViews();

        currentPos = pager.getCurrentItem();
        tabCount = pager.getAdapter().getCount();

        for (int i = 0; i < tabCount; i++) {
            if (areImagesProvided) {
                addImageTab(i);
            }
            else {
                addTextTab(i);
            }
        }

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                tabContainerHeight = tabsContainer.getHeight();

                pager.setCurrentItem(currentPos);

                selectTab(currentPos);

                if (areColorsProvided) {
                    ColorProvider colorProvider = (ColorProvider) pager.getAdapter();
                    indicatorPaint.setColor(colorProvider.getPageColor(currentPos));
                    if (actionBar != null) {
                        actionBarBacground.setColor(colorProvider.getPageColor(currentPos));
                    }
                }

                invalidate();

                if (Build.VERSION.SDK_INT >= 16) {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                else {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    private void addTextTab(int pos) {
        TextView textTab = new TextView(getContext());
        textTab.setLayoutParams(tabLParams);
        if (tabTextAllCaps) {
            textTab.setText(pager.getAdapter().getPageTitle(pos).toString().toUpperCase(Locale.ENGLISH));
        }
        else {
            textTab.setText(pager.getAdapter().getPageTitle(pos));
        }
        textTab.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        textTab.setTextColor(textColor);
        if (tabTextAllBold) {
            textTab.setTypeface(Typeface.create("sans-serif-regular", Typeface.BOLD));
        }
        else {
            textTab.setTypeface(Typeface.create("sans-serif-regular", Typeface.NORMAL));
        }
        textTab.setGravity(Gravity.CENTER);
        textTab.setSingleLine();
        addTab(textTab, pos);
    }

    private void addImageTab(int pos) {
        ImageView imageTab = new ImageView(getContext());
        imageTab.setLayoutParams(tabLParams);
        int imageResId;
        if ((imageResId = ((ImageProvider) pager.getAdapter()).getPageImage(pos)) != 0) {
            imageTab.setImageResource(imageResId);
        }
        addTab(imageTab, pos);
    }

    private void addTab(View tab, int pos) {
        if (Build.VERSION.SDK_INT >= 16) {
            tab.setBackground(res.getDrawable(R.drawable.selector_tab));
        }
        else {
            tab.setBackgroundDrawable(res.getDrawable(R.drawable.selector_tab));
        }
        tab.setClickable(true);
        tab.setFocusable(true);
        tab.setTag(pos);
        tab.setOnClickListener(tabClickListener);
        tab.setPadding((int) textPaddingLeft, (int) textPaddingTop, (int) textPaddingRight, (int) textPaddingBottom);
        tabsContainer.addView(tab, pos);
    }

    private void scrollToChild(int pos, float offset) {
        if (pos < tabCount-1) {
            float scrollX = lerp(tabsContainer.getChildAt(pos).getLeft(), tabsContainer.getChildAt(pos+1).getLeft(), positionOffset) - offset;
            scrollTo((int) scrollX, 0);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float lineLeft = tabsContainer.getChildAt(currentPos).getLeft();
        float lineRight = tabsContainer.getChildAt(currentPos).getRight();

        if (positionOffset > 0 && currentPos < tabCount-1) {
            float nextLineLeft = tabsContainer.getChildAt(currentPos+1).getLeft();
            float nextLineRight = tabsContainer.getChildAt(currentPos+1).getRight();
            lineLeft = lerp(lineLeft, nextLineLeft, positionOffset);
            lineRight = lerp(lineRight, nextLineRight, positionOffset);

            //change indicator color
            if (areColorsProvided) {
                ColorProvider colorProvider = (ColorProvider) pager.getAdapter();
                int newColor = blendColors(colorProvider.getPageColor(currentPos), (colorProvider.getPageColor(currentPos+1)), positionOffset);
                indicatorPaint.setColor(newColor);
                if (actionBar != null) {
                    actionBarBacground.setColor(newColor);
                    //In pre-JELLY_BEACON_MR1 devices, actionBarBackground is not invalidating itself when required as it is not registering itself to the Drawable's callback
                    //so we need to manually set the actionBar background drawable
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        actionBar.setBackgroundDrawable(actionBarBacground);
                    }
                }
            }
        }

        //draw indicator
        canvas.drawRect(lineLeft, tabContainerHeight-indicatorHeight, lineRight, tabContainerHeight, indicatorPaint);

        //draw dividers
        if (showDividers) {
            for (int i = 0; i < tabCount-1; i++) {
                canvas.drawRect(tabsContainer.getChildAt(i).getRight(), dividerPadding, tabsContainer.getChildAt(i).getRight() + dividerWidth, tabContainerHeight - dividerPadding, dividerPaint);
            }
        }
    }

    private void selectTab(int pos) {
        View child;
        if ((child = tabsContainer.getChildAt(pos)) instanceof TextView) {
            ((TextView) child).setTextColor(textColorSelected);
        }
        previousPos = pos;
    }

    private void deselectTab(int pos) {
        View child;
        if ((child = tabsContainer.getChildAt(pos)) instanceof TextView) {
            ((TextView) child).setTextColor(textColor);
        }
    }

    private static float lerp(float v0, float v1, float t) {
        return (1-t)*v0 + t*v1;
    }

    private static float lerpInts(int v0, int v1, float t) {
        return (1-t)*v0 + t*v1;
    }

    private static int blendColors(int color1, int color2, float t) {
        float r = lerpInts(Color.red(color1), Color.red(color2), t);
        float g = lerpInts(Color.green(color1), Color.green(color2), t);
        float b = lerpInts(Color.blue(color1), Color.blue(color2), t);
        return Color.rgb((int) r, (int) g, (int) b);
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int pos, float posOffset, int posOffsetPx) {
            currentPos = pos;
            positionOffset = posOffset;
            invalidate();

            scrollToChild(pos, scrollOffset);

            if (delegateListener != null) {
                delegateListener.onPageScrolled(pos, posOffset, posOffsetPx);
            }
        }

        @Override
        public void onPageSelected(int pos) {
            deselectTab(previousPos);
            selectTab(pos);

            if (delegateListener != null) {
                delegateListener.onPageSelected(pos);
            }
        }

        @Override
        public void onPageScrollStateChanged(int scrollState) {
            if (delegateListener != null) {
                delegateListener.onPageScrollStateChanged(scrollState);
            }
        }
    }

    /**
     * To be implemented by ViewPager adapter in order to provide image resource id for each tab
     */
    public static interface ImageProvider {
        public int getPageImage(int pos);
    }

    /**
     * To be implemented by ViewPager adapter in order to provide color for each tab's indicator (and actionBar)
     */
    public static interface ColorProvider {
        public int getPageColor(int pos);
    }

}
