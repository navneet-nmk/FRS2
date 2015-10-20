package com.teenvan.frs;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.SocialNetworkManager;
import com.github.gorbin.asne.core.listener.OnLoginCompleteListener;
import com.github.gorbin.asne.linkedin.LinkedInSocialNetwork;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class LinkedinLoginActivtiyFragment extends Fragment  implements
        SocialNetworkManager.OnInitializationCompleteListener, OnLoginCompleteListener{

    public static SocialNetworkManager mSocialNetworkManager;
    String LINKEDIN_CONSUMER_KEY = "75o0gl3z4u2c0j";
    String LINKEDIN_CONSUMER_SECRET = "yi7WlPPUi01nAIwb";
    String LINKEDIN_CALLBACK_URL = "https://www.frs.redirect";
    private Button mLinkedinButton;

    public LinkedinLoginActivtiyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String linkedInScope = "r_basicprofile+r_fullprofile+rw_nus+r_network+w_messages+r_emailaddress+r_contactinfo";

        View rootView = inflater.inflate(R.layout.fragment_linkedin_login_activtiy, container, false);

        mLinkedinButton = (Button)rootView.findViewById(R.id.linkedinButtonFragment);

        //Use manager to manage SocialNetworks
        mSocialNetworkManager = (SocialNetworkManager) getFragmentManager().
                findFragmentByTag(LinkedinLoginActivtiy.SOCIAL_NETWORK_TAG);

        if(mSocialNetworkManager == null){
            mSocialNetworkManager = new SocialNetworkManager();

            //Init and add to manager LinkedInSocialNetwork
            LinkedInSocialNetwork liNetwork = new
                    LinkedInSocialNetwork(this,
                    LINKEDIN_CONSUMER_KEY,
                    LINKEDIN_CONSUMER_SECRET,
                    LINKEDIN_CALLBACK_URL, linkedInScope);
            mSocialNetworkManager.addSocialNetwork(liNetwork);


            //Initiate every network from mSocialNetworkManager
            getFragmentManager().beginTransaction().add(mSocialNetworkManager,
                    LinkedinLoginActivtiy.SOCIAL_NETWORK_TAG).commit();
            mSocialNetworkManager.setOnInitializationCompleteListener(this);

        }else{
            //if manager exist - get and setup login only for initialized SocialNetworks
            if(!mSocialNetworkManager.getInitializedSocialNetworks().isEmpty()) {
                List<SocialNetwork> socialNetworks = mSocialNetworkManager.getInitializedSocialNetworks();
                for (SocialNetwork socialNetwork : socialNetworks) {
                    socialNetwork.setOnLoginCompleteListener(this);
                    initSocialNetwork(socialNetwork);
                }
            }
        }

        mLinkedinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int networkId= 0;
                networkId = LinkedInSocialNetwork.ID;
               SocialNetwork socialNetwork = mSocialNetworkManager.getSocialNetwork(
                     networkId);
                if(!socialNetwork.isConnected()) {
                    if(networkId != 0) {
                        socialNetwork.requestLogin();
                    } else {
                        Toast.makeText(getActivity(), "Wrong networkId", Toast.LENGTH_LONG).show();
                    }
                } else {

                }
            }
        });
        return rootView;

    }


    private void initSocialNetwork(SocialNetwork socialNetwork){
        if(socialNetwork.isConnected()){

        }
    }
    @Override
    public void onSocialNetworkManagerInitialized() {
        //when init SocialNetworks - get and setup login only for initialized SocialNetworks
        for (SocialNetwork socialNetwork : mSocialNetworkManager.getInitializedSocialNetworks()) {
            socialNetwork.setOnLoginCompleteListener(this);
            initSocialNetwork(socialNetwork);
        }
    }



    @Override
    public void onLoginSuccess(int socialNetworkID) {
            Toast.makeText(getActivity(),"Successfully logged in"+socialNetworkID,
                    Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(),EducationalActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError(int socialNetworkID, String requestID, String errorMessage, Object data) {
        Toast.makeText(getActivity(), "ERROR: " + errorMessage, Toast.LENGTH_LONG).show();
        Log.d("Error"+ socialNetworkID + requestID,errorMessage);
    }
}
