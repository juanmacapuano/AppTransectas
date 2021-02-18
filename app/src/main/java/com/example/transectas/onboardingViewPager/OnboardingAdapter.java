package com.example.transectas.onboardingViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.transectas.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private List<OnboardingItem> onboardingItemList;

    public OnboardingAdapter(List<OnboardingItem> onboardingItemList) {
        this.onboardingItemList = onboardingItemList;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboarding, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(onboardingItemList.get(position));

    }

    @Override
    public int getItemCount() {
        return onboardingItemList.size();
    }

    static class OnboardingViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private TextView tv_description;
        private ImageView iv_onboarding;

        public OnboardingViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title_onboarding);
            tv_description = itemView.findViewById(R.id.tv_description_onboarding);
            iv_onboarding = itemView.findViewById(R.id.iv_Onboarding);
        }

        void setOnboardingData (OnboardingItem onboardingItem) {
            //tv_title.setText(onboardingItem.getTitle());
            tv_description.setText(onboardingItem.getDescription());
            iv_onboarding.setImageResource(onboardingItem.getImage());
        }
    }
}
