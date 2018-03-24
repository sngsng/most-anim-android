package com.skn.mostanimsample.wash;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;

import com.skn.mostanimsample.R;
import com.skn.mostanimsample.databinding.ActivityWashBinding;

public class WashActivity extends AppCompatActivity {

    private static final int SLEEP = 1000;

    private enum WashAnimResultType {

        Issuable,
        NoCoupon,
        MembershipRequired,
        CanBuy,
        Issued,
    }

    private ActivityWashBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_wash);

        mBinding.membershipRequiredAnimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startWashLayoutProgress();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        stopWashLayoutProgress(WashAnimResultType.MembershipRequired);
                    }
                }, SLEEP);
            }
        });

        mBinding.noCouponAnimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startWashLayoutProgress();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        stopWashLayoutProgress(WashAnimResultType.NoCoupon);
                    }
                }, SLEEP);
            }
        });

        mBinding.canBuyAnimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startWashLayoutProgress();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        stopWashLayoutProgress(WashAnimResultType.CanBuy);
                    }
                }, SLEEP);
            }
        });

        mBinding.issuableAnimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startWashLayoutProgress();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        stopWashLayoutProgress(WashAnimResultType.Issuable);
                    }
                }, SLEEP);
            }
        });

        mBinding.issueAnimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startWashLayoutProgress();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        stopWashLayoutProgress(WashAnimResultType.Issuable);
                    }
                }, SLEEP);
            }
        });

        mBinding.washCouponIssueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mBinding.loadingView.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        applyDelayedTransition();
                        mBinding.loadingView.setVisibility(View.GONE);
                        mBinding.washCouponIssueButton.setVisibility(View.GONE);
                        mBinding.washIssued.parent.setVisibility(View.VISIBLE);

                    }
                }, SLEEP);


            }
        });

        mBinding.washIssued.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                applyDelayedTransition();
                mBinding.washCouponIssueButton.setVisibility(View.VISIBLE);
                mBinding.washIssued.parent.setVisibility(View.GONE);
            }
        });
    }

    private void startWashLayoutProgress() {

        mBinding.loadingView.setVisibility(View.VISIBLE);
        mBinding.loading.parent.setVisibility(View.VISIBLE);

        mBinding.membershipRequired.parent.setVisibility(View.GONE);
        mBinding.noCoupon.parent.setVisibility(View.GONE);
        mBinding.canCharge.parent.setVisibility(View.GONE);
        mBinding.issuable.parent.setVisibility(View.GONE);
    }

    private void stopWashLayoutProgress(WashAnimResultType resultType) {

        applyDelayedTransition();
        mBinding.loadingView.setVisibility(View.GONE);
        mBinding.loading.parent.setVisibility(View.GONE);
        switch (resultType) {

            case MembershipRequired:
                mBinding.membershipRequired.parent.setVisibility(View.VISIBLE);
                break;

            case NoCoupon:
                mBinding.noCoupon.parent.setVisibility(View.VISIBLE);
                break;

            case CanBuy:
                mBinding.canCharge.parent.setVisibility(View.VISIBLE);
                break;

            case Issuable:
                mBinding.issuable.parent.setVisibility(View.VISIBLE);
                break;

            case Issued:
                break;
        }
    }

    private void applyDelayedTransition() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.wash_transition);
            TransitionManager.beginDelayedTransition(mBinding.parentContainer, transition);
        }
    }

}
