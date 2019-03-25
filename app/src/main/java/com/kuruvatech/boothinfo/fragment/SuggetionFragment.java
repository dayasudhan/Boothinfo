package com.kuruvatech.boothinfo.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kuruvatech.boothinfo.R.*;
import com.kuruvatech.boothinfo.R;
import com.kuruvatech.boothinfo.model.Letter;
import com.kuruvatech.boothinfo.utils.Constants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.ArrayList;

import static com.kuruvatech.boothinfo.utils.Constants.POST_LETTER_URL;

/**
 * Created by dayas on 05-12-2017.
 */

public class SuggetionFragment  extends Fragment {

    private EditText mEBoothNo,mEBoothName,mEVoterListPageno,mEVoterListSerialNo,mEFamilyMembers,mEHeadOfFamily,mEFinancialStatus,mEVoterId,mEname,mEPhone;
    private MultiAutoCompleteTextView mMEPersonalRequirements,mMEBoothRequirements;
    private Spinner mPartty_spinner,mCast_spinner,mLanguage_spinner,mState_spinner,mLs_spinner,mVs_spinner,mZP_spinner,mTP_spinner,mGP_Spinner;

    Button btnSend;
    View view;
    String mState,mLS,mVS,mZP,mTP,mGP,mFinancialStatus,mCast,mLanguage,mParty,mBoothNo,mBoothName,
            mVoterListPageNo,mVoterListSerialNo,mFamilyMemebers,MHeadoffamily,mpersonalrequirements,mboothrequirements,mVoterID,mName,mPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.suggetion_layout, container, false);
        mState_spinner = (Spinner) view.findViewById(id.statespinner);
        mPartty_spinner = (Spinner) view.findViewById(id.partyspinner);
        mCast_spinner = (Spinner) view.findViewById(id.castspinner);
        mLs_spinner = (Spinner) view.findViewById(id.loksabhaspinner);
        mVs_spinner = (Spinner) view.findViewById(id.vidhanasabhaspinner);
        mZP_spinner = (Spinner) view.findViewById(id.zpspinner);
        mTP_spinner = (Spinner) view.findViewById(id.tpspinner);
        mGP_Spinner = (Spinner) view.findViewById(id.gpspinner);
        mLanguage_spinner = (Spinner) view.findViewById(id.languagespinner);

//        String mState = mySpinnerState.getSelectedItem().toString();
        mEname = (EditText) view.findViewById(id.voter_name);
        mEPhone = (EditText) view.findViewById(id.contact_no);
        mEBoothNo = (EditText) view.findViewById(id.boot_no);
        mEBoothName = (EditText) view.findViewById(id.boot_name);
        mEVoterId = (EditText) view.findViewById(id.voter_id);
        mEVoterListPageno = (EditText) view.findViewById(id.voterlist_pageno);
        mEVoterListSerialNo = (EditText) view.findViewById(id.voterlist_serialno);
        mEHeadOfFamily = (EditText) view.findViewById(id.head_of_family);
        mEFinancialStatus = (EditText) view.findViewById(id.financial_status);
        mMEPersonalRequirements = (MultiAutoCompleteTextView) view.findViewById(id.personal_requirements);
        mMEBoothRequirements = (MultiAutoCompleteTextView) view.findViewById(id.booth_requirements);
       // mEFamilyMembers = (EditText) view.findViewById(id.mem);
//        mEBoothName = (MultiAutoCompleteTextView) view.boo(id.boot_name);
//        String myState = myName.getText().toString();

        btnSend= (Button) view.findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mEBoothNo,mEBoothName,mEVoterListPageno,mEVoterListSerialNo,mEFamilyMembers,mEHeadOfFamily,mEFinancialStatus,mEVoterId,mEname,mEPhone

                mState = mState_spinner.getSelectedItem().toString();
                mLS = mLs_spinner.getSelectedItem().toString();
                mVS = mVs_spinner.getSelectedItem().toString();
                mZP= mZP_spinner.getSelectedItem().toString();
                mTP = mTP_spinner.getSelectedItem().toString();
                mGP = mGP_Spinner.getSelectedItem().toString();
                mParty = mPartty_spinner.getSelectedItem().toString();
                mLanguage = mLanguage_spinner.getSelectedItem().toString();
                mCast = mCast_spinner.getSelectedItem().toString();

                mBoothNo =mEBoothNo.getText().toString();
                mBoothName =mEBoothName.getText().toString();
                mVoterListPageNo =mEVoterListPageno.getText().toString();
                mVoterListSerialNo=mEVoterListSerialNo.getText().toString();
                mVoterID =mEVoterId.getText().toString();
                mName =mEname.getText().toString();
                mPhone =mEPhone.getText().toString();
//                mFamilyMemebers=mEFamilyMembers.getText().toString();
                MHeadoffamily =mEHeadOfFamily.getText().toString();
                mFinancialStatus =mEFinancialStatus.getText().toString();
                mpersonalrequirements = mMEPersonalRequirements.getText().toString();
                mboothrequirements = mMEBoothRequirements.getText().toString();

                // String lettercontent =lettertextview.getText().toString();
                if (!validatePhoneNumber(mEPhone.getText().toString())) {
                    alertMessage("Enter Valid Phone Number");
                }
                else if(mBoothNo.trim().length() == 0){
                    mEBoothNo.setFocusableInTouchMode(true);
                    mEBoothNo.requestFocus();
                    alertMessage("Enter Booth Number");
                }
                else if(mBoothName.trim().length() == 0){
                    mEBoothName.setFocusableInTouchMode(true);
                    mEBoothName.requestFocus();
                    alertMessage("Enter Booth Name");
                }
                else if(mVoterListPageNo.trim().length() == 0){
                    mEVoterListPageno.setFocusableInTouchMode(true);
                    mEVoterListPageno.requestFocus();
                    alertMessage("Enter Voter List Page Number");
                }
                else if(mVoterListSerialNo.trim().length() == 0){
                    mEVoterListSerialNo.setFocusableInTouchMode(true);
                    mEVoterListSerialNo.requestFocus();
                    alertMessage("Enter Voter List Serial Numbere");
                }
                else if(mVoterID.trim().length() == 0){
                    mEVoterId.setFocusableInTouchMode(true);
                    mEVoterId.requestFocus();
                    alertMessage("Enter Voter ID");
                }
                else if(MHeadoffamily.trim().length() == 0){
                    mEHeadOfFamily.setFocusableInTouchMode(true);
                    mEHeadOfFamily.requestFocus();
                    alertMessage("Enter Head Of Family");
                }
                else if(mFinancialStatus.trim().length() == 0){
                    mEFinancialStatus.setFocusableInTouchMode(true);
                    mEFinancialStatus.requestFocus();
                    alertMessage("Enter Financial Status");
                }
                else if(mpersonalrequirements.trim().length() == 0){
                    mMEPersonalRequirements.setFocusableInTouchMode(true);
                    mMEPersonalRequirements.requestFocus();
                    alertMessage("Enter Personal Requirements");
                }
                else if(mboothrequirements.trim().length() == 0){
                    mMEBoothRequirements.setFocusableInTouchMode(true);
                    mMEBoothRequirements.requestFocus();
                    alertMessage("Enter Booth Requirements");
                }

                else
                {
                    Letter letter = new Letter();
                    letter.setmBoothName(mBoothName);
                    letter.setmBoothNo(mBoothNo);
                    letter.setmCast(mCast);
                    letter.setmFamilyMemebers(mFamilyMemebers);
                    letter.setmFinancialStatus(mFinancialStatus);
                    letter.setmGP(mGP);
                    letter.setmLanguage(mLanguage);
                    letter.setmLS(mLS);
                    letter.setmName(mName);
                    letter.setmParty(mParty);
                    letter.setmPhone(mPhone);
                    letter.setmState(mState);
                    letter.setmTP(mTP);
                    letter.setmVoterID(mVoterID);
                    letter.setmVoterListPageNo(mVoterListPageNo);
                    letter.setmVoterListSerialNo(mVoterListSerialNo);
                    letter.setmVS(mVS);
                    letter.setmZP(mZP);
                    letter.setMpersonalrequirements(mpersonalrequirements);
                    letter.setmBoothNo(mboothrequirements);
                    letter.setMHeadoffamily(MHeadoffamily);



                    Gson gson = new Gson();
                    String strOrder = gson.toJson(letter);

                    postLetter(strOrder);
                }
            }
        });
        return view;
    }


    public void postLetter(String letter)
    {
        new PostJSONAsyncTask().execute(POST_LETTER_URL, letter);
    }
    public  class PostJSONAsyncTask extends AsyncTask<String, Void, Boolean> {
        Dialog dialog;
        public  PostJSONAsyncTask()
        {
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new Dialog(getActivity(),android.R.style.Theme_Translucent);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom_progress_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();
            dialog.setCancelable(true);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                HttpPost request = new HttpPost(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
                StringEntity se = new StringEntity(urls[1]);
                request.setEntity(se);
                request.setHeader("Accept", "application/json");
                request.setHeader("Content-type", "application/json");
                request.setHeader(Constants.SECUREKEY_KEY, Constants.SECUREKEY_VALUE);
                request.setHeader(Constants.VERSION_KEY, Constants.VERSION_VALUE);
                request.setHeader(Constants.CLIENT_KEY, Constants.CLIENT_VALUE);
                HttpResponse response = httpclient.execute(request);

                int status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    HttpEntity entity = response.getEntity();

                  //  responseOrder = EntityUtils.toString(entity);
                    return true;
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        protected void onPostExecute(Boolean result) {

            dialog.cancel();
            if(result == true){
                Toast.makeText(getActivity().getApplicationContext(), "Successfully posted the letter", Toast.LENGTH_LONG).show();
            }
            else if (result == false)
                Toast.makeText(getActivity().getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();
        }
    }
    private static boolean validatePhoneNumber(String phoneNo)
    {
        if (phoneNo.matches("\\d{10}"))
            return true;
        else if(phoneNo.matches("\\+\\d{12}")) return true;
        else return false;
    }
    public void alertMessage(String message) {
        DialogInterface.OnClickListener dialogClickListeneryesno = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {

                    case DialogInterface.BUTTON_NEUTRAL:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(message).setNeutralButton("Ok", dialogClickListeneryesno)
                .setIcon(R.drawable.ic_action_about).show();

    }
    @Override
    public void onResume() {
        super.onResume();
    }

}
