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

    EditText editName,editPhone,editEmail;
    MultiAutoCompleteTextView lettertextview;
    Button btnSend;
    View view;
    private Spinner partty_spinner,cm_spinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.suggetion_layout, container, false);
//        ((MainActivity) getActivity())
//                .setActionBarTitle("Invite Friends");
//        editName = (EditText) view.findViewById(R.id.input_name);
//        editPhone = (EditText) view.findViewById(R.id.input_phone);
//        editEmail = (EditText) view.findViewById(R.id.input_email);
//        lettertextview = (MultiAutoCompleteTextView) view.findViewById(R.id.letertextview);
//        btnSend= (Button) view.findViewById(R.id.btn_send);
//
//        btnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name =editName.getText().toString();
//                String lettercontent =lettertextview.getText().toString();
//                if (!validatePhoneNumber(editPhone.getText().toString())) {
//                    alertMessage("Enter Valid Phone Number");
//                }
//                else if(name.trim().length() == 0){
//                    editName.setFocusableInTouchMode(true);
//                    editName.requestFocus();
//                    alertMessage("Enter Name");
//                }
//                else if(lettercontent.trim().length() == 0){
//                    lettertextview.setFocusableInTouchMode(true);
//                    lettertextview.requestFocus();
//                    alertMessage("Write Something into letter");
//                }
//                else
//                {
//                    Letter letter = new Letter();
//                    letter.setEmailid(editEmail.getText().toString());
//                    letter.setName(editName.getText().toString());
//                    letter.setLetter(lettertextview.getText().toString());
//                    letter.setPhone(editPhone.getText().toString());
//
//                    Gson gson = new Gson();
//                    String strOrder = gson.toJson(letter);
//
//                    postLetter(strOrder);
//                }
//            }
//        });
        addItemsOnParty();
        addItemsOnCommunity();
        return view;
    }
    public void addItemsOnCommunity() {
        ArrayList<String> languagelist = new ArrayList<String>();
        languagelist.add("Kannada");
        languagelist.add("Hindi");
        languagelist.add("English");
        languagelist.add("Konkani");
        languagelist.add("Tulu");
        languagelist.add("Marati");
        languagelist.add("Tamil");
        languagelist.add("Telugu");
        languagelist.add("Malayalam");
        languagelist.add("Urdhu");
        languagelist.add("Bengali");
        languagelist.add("Coorga");
        languagelist.add("Gujarathi");



        ArrayList<String> communitylist = new ArrayList<String>();
        communitylist.add("Lingayath");
        communitylist.add("Vokkaliga");
        communitylist.add("Kuruba");
        communitylist.add("Beda Nayaka");
        communitylist.add("Lambani");
        communitylist.add("Chalavadhi");
        communitylist.add("Adi Karnataka");
        communitylist.add("Brahmana");
        communitylist.add("Madivala");
        communitylist.add("Kumbara");
        communitylist.add("Muslim");
        communitylist.add("Bovi");
        communitylist.add("Kunchitiga");
        communitylist.add("Reddy");
        communitylist.add("Christian");
        communitylist.add("jains");
        communitylist.add("Others");

        cm_spinner = (Spinner) view.findViewById(id.castspinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),

                android.R.layout.simple_spinner_item, communitylist);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cm_spinner.setAdapter(dataAdapter);
        cm_spinner.setPrompt(communitylist.get(0).toString());
//        cm_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view,
//                int position, long id) {
//                    Object item = adapterView.getItemAtPosition(position);
//                    if (item != null) {
//                        selectedCast =  new String(item.toString());
//                    }
//                }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });
    }

    public void addItemsOnParty() {
        ArrayList<String> communitylist = new ArrayList<String>();
        communitylist.add("BJP");
        communitylist.add("Congress");
        communitylist.add("JDS");
        communitylist.add("JDU");
        communitylist.add("BSP");
        communitylist.add("Prjakiya");
        communitylist.add("Karnataka Rajya Raitha Sangha");
        communitylist.add("Kannada Naadu");
        communitylist.add("Others");


        partty_spinner = (Spinner) view.findViewById(id.partyspinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),

                android.R.layout.simple_spinner_item, communitylist);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        partty_spinner.setAdapter(dataAdapter);
        partty_spinner.setPrompt(communitylist.get(0).toString());
//        cm_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view,
//                int position, long id) {
//                    Object item = adapterView.getItemAtPosition(position);
//                    if (item != null) {
//                        selectedCast =  new String(item.toString());
//                    }
//                }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });
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
