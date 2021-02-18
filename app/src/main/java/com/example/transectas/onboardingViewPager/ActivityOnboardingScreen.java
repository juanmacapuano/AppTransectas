package com.example.transectas.onboardingViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.transectas.BaseActivity;
import com.example.transectas.R;
import com.example.transectas.projects.ProjectActivity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

public class ActivityOnboardingScreen extends BaseActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicator;
    private MaterialButton btn_onboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen);

        layoutOnboardingIndicator = findViewById(R.id.layout_onboarding_indicators);
        btn_onboardingAction = findViewById(R.id.btn_onboarding_action);

        setupOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.view_pager_onboarding);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicators(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });

        btn_onboardingAction.setOnClickListener(v -> {
            if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
            }else {
                startActivity(new Intent(getApplicationContext(), ProjectActivity.class));
                finish();
            }
        });

    }

    private void setupOnboardingItems() {
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemProject = new OnboardingItem();
        itemProject.setTitle(getString(R.string.new_project_onboard));
        itemProject.setDescription(getString(R.string.new_project_onboard));
        itemProject.setImage(R.drawable.onboard_project);

        OnboardingItem itemTransect = new OnboardingItem();
        itemTransect.setTitle(getString(R.string.new_transect_onboard));
        itemTransect.setDescription(getString(R.string.new_transect_onboard));
        itemTransect.setImage(R.drawable.onboard_transect_1);

        OnboardingItem itemSampling = new OnboardingItem();
        itemSampling.setTitle(getString(R.string.new_sampling_onboard));
        itemSampling.setDescription(getString(R.string.new_sampling_onboard));
        itemSampling.setImage(R.drawable.onboard_sampling_1);

        OnboardingItem itemFinding = new OnboardingItem();
        itemFinding.setTitle(getString(R.string.new_finding_onboard));
        itemFinding.setDescription(getString(R.string.new_finding_onboard));
        itemFinding.setImage(R.drawable.onboard_finding_init);

        onboardingItems.add(itemProject);
        onboardingItems.add(itemTransect);
        onboardingItems.add(itemSampling);
        onboardingItems.add(itemFinding);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

    private void setupOnboardingIndicators() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,0,0);
        for (int i=0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicator.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicators(int index) {
        int childCount = layoutOnboardingIndicator.getChildCount();
        for (int i=0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnboardingIndicator.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive));
            }
        }
        if (index == onboardingAdapter.getItemCount()-1) {
            btn_onboardingAction.setText(R.string.starter);
        }else {
            btn_onboardingAction.setText(R.string.next);
        }
    }
}