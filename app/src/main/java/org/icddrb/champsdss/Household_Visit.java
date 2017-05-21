
 package org.icddrb.champsdss;


 //Android Manifest Code
 //<activity android:name=".Household" android:label="Household" />

 import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import Common.Connection;
import Common.Global;
 import Utility.MySharedPreferences;

 public class Household_Visit extends Activity {
    boolean networkAvailable=false;
    Location currentLocation; 
    double currentLatitude,currentLongitude; 
    //Disabled Back/Home key
    //--------------------------------------------------------------------------------------------------
    @Override 
    public boolean onKeyDown(int iKeyCode, KeyEvent event)
    {
        if(iKeyCode == KeyEvent.KEYCODE_BACK || iKeyCode == KeyEvent.KEYCODE_HOME) 
             { return false; }
        else { return true;  }
    }
    String VariableID;
    private int hour;
    private int minute;
    private int mDay;
    private int mMonth;
    private int mYear;
    static final int DATE_DIALOG = 1;
    static final int TIME_DIALOG = 2;

    Connection C;
    Global g;
    SimpleAdapter dataAdapter;
    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
         TextView lblHeading;
         LinearLayout seclbl02;
         View linelbl02;
         LinearLayout seclbl01;
         View linelbl01;
         LinearLayout secVill;
         View lineVill;
         TextView VlblVill;
         EditText txtVill;
         LinearLayout secBari;
         View lineBari;
         TextView VlblBari;
         EditText txtBari;
         LinearLayout secHH;
         View lineHH;
         TextView VlblHH;
         EditText txtHH;
         LinearLayout secReligion;
         View lineReligion;
         TextView VlblReligion;
         Spinner spnReligion;
         LinearLayout secMobileNo1;
         View lineMobileNo1;
         TextView VlblMobileNo1;
         EditText txtMobileNo1;
         LinearLayout secMobileNo2;
         View lineMobileNo2;
         TextView VlblMobileNo2;
         EditText txtMobileNo2;
         LinearLayout secHHHead;
         View lineHHHead;
         TextView VlblHHHead;
         EditText txtHHHead;
         LinearLayout secTotMem;
         View lineTotMem;
         TextView VlblTotMem;
         EditText txtTotMem;
         LinearLayout secTotRWo;
         View lineTotRWo;
         TextView VlblTotRWo;
         EditText txtTotRWo;

//         LinearLayout secEnType;
//         View lineEnType;
//         TextView VlblEnType;
//         EditText txtEnType;
//         LinearLayout secEnDate;
//         View lineEnDate;
//         TextView VlblEnDate;
//         EditText dtpEnDate;
//         LinearLayout secExType;
//         View lineExType;
//         TextView VlblExType;
//         EditText txtExType;
//         LinearLayout secExDate;
//         View lineExDate;
//         TextView VlblExDate;
//         EditText dtpExDate;
//         LinearLayout secRnd;
//         View lineRnd;
//         TextView VlblRnd;
//         EditText txtRnd;

     //***********************sakib****************************************
         Button btnPlusMobile1;
         Button btnMinusMobile1;

         LinearLayout secVDate;
         View lineVDate;
         TextView VlblVDate;
         EditText dtpVDate;
         LinearLayout secVStatus;
         View lineVStatus;
         TextView VlblVStatus;
         Spinner spnVStatus;

         LinearLayout secVStatusOth;
         View lineVStatusOth;
         TextView VlblVStatusOth;
         EditText txtVStatusOth;
         LinearLayout secResp;
         View lineResp;
         TextView VlblResp;
         Spinner spnResp;
     //***********************sakib********************************************

    static String TableName;

    static String STARTTIME = "";
    static String DEVICEID  = "";
    static String ENTRYUSER = "";

    Bundle IDbundle;
    static String VILL = "";
    static String BARI = "";
    static String HH = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.household_visit);

         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = g.getDeviceNo();
         ENTRYUSER = g.getUserId();

         IDbundle = getIntent().getExtras();
         VILL = IDbundle.getString("Vill");
         BARI = IDbundle.getString("Bari");
         HH = IDbundle.getString("HH");

         TableName = "Household";

         //turnGPSOn();

         //GPS Location
         //FindLocation();
         // Double.toString(currentLatitude);
         // Double.toString(currentLongitude);
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Household_Visit.this);
                 adb.setTitle("Close");
                 adb.setMessage("আপনি কি এই ফরম থেকে বের হতে চান [হ্যাঁ/না]?");
                 adb.setNegativeButton("না", null);
                 adb.setPositiveButton("হ্যাঁ", new AlertDialog.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                         finish();
                     }});
                 adb.show();
             }});

         seclbl02=(LinearLayout)findViewById(R.id.seclbl02);
         linelbl02=(View)findViewById(R.id.linelbl02);
         seclbl01=(LinearLayout)findViewById(R.id.seclbl01);
         linelbl01=(View)findViewById(R.id.linelbl01);
         secVill=(LinearLayout)findViewById(R.id.secVill);
         lineVill=(View)findViewById(R.id.lineVill);
         VlblVill=(TextView) findViewById(R.id.VlblVill);
         txtVill=(EditText) findViewById(R.id.txtVill);
         secBari=(LinearLayout)findViewById(R.id.secBari);
         lineBari=(View)findViewById(R.id.lineBari);
         VlblBari=(TextView) findViewById(R.id.VlblBari);
         txtBari=(EditText) findViewById(R.id.txtBari);
         secHH=(LinearLayout)findViewById(R.id.secHH);
         lineHH=(View)findViewById(R.id.lineHH);
         VlblHH=(TextView) findViewById(R.id.VlblHH);
         txtHH=(EditText) findViewById(R.id.txtHH);
         secReligion=(LinearLayout)findViewById(R.id.secReligion);
         lineReligion=(View)findViewById(R.id.lineReligion);
         VlblReligion=(TextView) findViewById(R.id.VlblReligion);
         spnReligion=(Spinner) findViewById(R.id.spnReligion);
         List<String> listReligion = new ArrayList<String>();
         
         listReligion.add("");
         listReligion.add("1-মুসলিম");
         listReligion.add("2-হিন্দু");
         listReligion.add("3-খ্রীষ্ট");
         listReligion.add("4-বুদ্ধ");
         listReligion.add("5-অন্যান্য");
         ArrayAdapter<String> adptrReligion= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listReligion);
         spnReligion.setAdapter(adptrReligion);

         secMobileNo1=(LinearLayout)findViewById(R.id.secMobileNo1);
         lineMobileNo1=(View)findViewById(R.id.lineMobileNo1);
         VlblMobileNo1=(TextView) findViewById(R.id.VlblMobileNo1);
         txtMobileNo1=(EditText) findViewById(R.id.txtMobileNo1);
         secMobileNo2=(LinearLayout)findViewById(R.id.secMobileNo2);
         lineMobileNo2=(View)findViewById(R.id.lineMobileNo2);
         VlblMobileNo2=(TextView) findViewById(R.id.VlblMobileNo2);
         txtMobileNo2=(EditText) findViewById(R.id.txtMobileNo2);



         secHHHead=(LinearLayout)findViewById(R.id.secHHHead);
         lineHHHead=(View)findViewById(R.id.lineHHHead);
         VlblHHHead=(TextView) findViewById(R.id.VlblHHHead);
         txtHHHead=(EditText) findViewById(R.id.txtHHHead);
         secTotMem=(LinearLayout)findViewById(R.id.secTotMem);
         lineTotMem=(View)findViewById(R.id.lineTotMem);
         VlblTotMem=(TextView) findViewById(R.id.VlblTotMem);
         txtTotMem=(EditText) findViewById(R.id.txtTotMem);
         secTotRWo=(LinearLayout)findViewById(R.id.secTotRWo);
//         lineTotRWo=(View)findViewById(R.id.lineTotRWo);
         VlblTotRWo=(TextView) findViewById(R.id.VlblTotRWo);
         txtTotRWo=(EditText) findViewById(R.id.txtTotRWo);
//         secEnType=(LinearLayout)findViewById(R.id.secEnType);
//         lineEnType=(View)findViewById(R.id.lineEnType);
//         VlblEnType=(TextView) findViewById(R.id.VlblEnType);
//         txtEnType=(EditText) findViewById(R.id.txtEnType);
//         secEnDate=(LinearLayout)findViewById(R.id.secEnDate);
//         lineEnDate=(View)findViewById(R.id.lineEnDate);
//         VlblEnDate=(TextView) findViewById(R.id.VlblEnDate);
//         dtpEnDate=(EditText) findViewById(R.id.dtpEnDate);
//         secExType=(LinearLayout)findViewById(R.id.secExType);
//         lineExType=(View)findViewById(R.id.lineExType);
//         VlblExType=(TextView) findViewById(R.id.VlblExType);
//         txtExType=(EditText) findViewById(R.id.txtExType);
//         secExDate=(LinearLayout)findViewById(R.id.secExDate);
//         lineExDate=(View)findViewById(R.id.lineExDate);
//         VlblExDate=(TextView) findViewById(R.id.VlblExDate);
//         dtpExDate=(EditText) findViewById(R.id.dtpExDate);
//         secRnd=(LinearLayout)findViewById(R.id.secRnd);
//         lineRnd=(View)findViewById(R.id.lineRnd);
//         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
//         txtRnd=(EditText) findViewById(R.id.txtRnd);


         //***********************sakib********************************************************
         secVDate=(LinearLayout)findViewById(R.id.secVDate);
         lineVDate=(View)findViewById(R.id.lineVDate);
         VlblVDate=(TextView) findViewById(R.id.VlblVDate);
         dtpVDate=(EditText) findViewById(R.id.dtpVDate);
         dtpVDate.setText(Global.DateNowDMY());
         secVStatus=(LinearLayout)findViewById(R.id.secVStatus);
         lineVStatus=(View)findViewById(R.id.lineVStatus);
         VlblVStatus=(TextView) findViewById(R.id.VlblVStatus);
         spnVStatus=(Spinner) findViewById(R.id.spnVStatus);
         List<String> listVStatus = new ArrayList<String>();

         listVStatus.add("");
         listVStatus.add("1-ইন্টারভিউ সফল হয়েছে");
         listVStatus.add("2-বাড়ি পরিদর্শনের সময় খানার কোন সদস্যকে বা উপযুক্ত কাউকে পাওয়া যায় নাই");
         listVStatus.add("3-অনেক দিনের জন্য খানার সকল সদস্য অনুপস্থিত");
         listVStatus.add("4-ইন্টারভিউ বাতিল");
         listVStatus.add("5-ইন্টারভিউ দিতে রাজী নয়");
         listVStatus.add("6-বাসা খালি অথবা ঠিকানাটি কোন বাসস্থানের নয়");
         listVStatus.add("7-বাসস্থানটি ধংসপ্রাপ্ত");
         listVStatus.add("8-বাসস্থানটি খুঁজে পাওয়া যায় নাই");
         listVStatus.add("9-অন্যান");
         ArrayAdapter<String> adptrVStatus= new ArrayAdapter<String>(this, R.layout.multiline_spinner_dropdown_item, listVStatus);
         spnVStatus.setAdapter(adptrVStatus);

         spnVStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
         {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
             {
                 if (spnVStatus.getSelectedItem().toString().length() == 0) return;
                 String spnData = Connection.SelectedSpinnerValue(spnVStatus.getSelectedItem().toString(),"-");

                 if(spnData.equalsIgnoreCase("1"))
                 {
                     secReligion.setVisibility(View.VISIBLE);
                     lineReligion.setVisibility(View.VISIBLE);

                     secMobileNo1.setVisibility(View.VISIBLE);
                     lineMobileNo1.setVisibility(View.VISIBLE);

                     secMobileNo2.setVisibility(View.GONE);
                     lineMobileNo2.setVisibility(View.GONE);
                     txtMobileNo2.setVisibility(View.GONE);

                     secHHHead.setVisibility(View.VISIBLE);
                     lineHHHead.setVisibility(View.VISIBLE);

                     secTotMem.setVisibility(View.VISIBLE);
                     lineTotMem.setVisibility(View.VISIBLE);

                     secTotRWo.setVisibility(View.VISIBLE);

                 }
                 else
                 {
                     secReligion.setVisibility(View.GONE);
                     lineReligion.setVisibility(View.GONE);
                     spnReligion.setSelection(0);

                     secMobileNo1.setVisibility(View.GONE);
                     lineMobileNo1.setVisibility(View.GONE);
                     txtMobileNo1.setText("");

                     secMobileNo2.setVisibility(View.GONE);
                     lineMobileNo2.setVisibility(View.GONE);
                     txtMobileNo2.setText("");

//                     secHHHead.setVisibility(View.GONE);
//                     lineHHHead.setVisibility(View.GONE);
//                     txtHHHead.setText("");

                     secTotMem.setVisibility(View.GONE);
                     lineTotMem.setVisibility(View.GONE);
                     txtTotMem.setText("");

                     secTotRWo.setVisibility(View.GONE);
                     txtTotRWo.setText("");
                 }

                 if(!spnData.equalsIgnoreCase("9"))
                 {
                     secVStatusOth.setVisibility(View.GONE);
                     lineVStatusOth.setVisibility(View.GONE);
                     txtVStatusOth.setText("");
                 }
                 else
                 {
                     secVStatusOth.setVisibility(View.VISIBLE);
                     lineVStatusOth.setVisibility(View.VISIBLE);
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });

         secVStatusOth=(LinearLayout)findViewById(R.id.secVStatusOth);
         lineVStatusOth=(View)findViewById(R.id.lineVStatusOth);
         VlblVStatusOth=(TextView) findViewById(R.id.VlblVStatusOth);
         txtVStatusOth=(EditText) findViewById(R.id.txtVStatusOth);

         secResp=(LinearLayout)findViewById(R.id.secResp);
         lineResp=(View)findViewById(R.id.lineResp);
         VlblResp=(TextView) findViewById(R.id.VlblResp);
         spnResp=(Spinner) findViewById(R.id.spnResp);
         //***********************sakib********************************************************

         List<String> listResp = new ArrayList<String>();

         listResp.add("");
         listResp.add("1-Member1");
         listResp.add("2-Member2");
         listResp.add("3-Member3");
         listResp.add("4-Member4");
         listResp.add("5-Member5");
         listResp.add("6-Member6");

         ArrayAdapter<String> adptrResp= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listResp);
         spnResp.setAdapter(adptrResp);

         txtVill.setText(VILL);
         txtVill.setFocusable(false);
         txtBari.setText(BARI);
         txtBari.setFocusable(false);
         if(HH.equals(""))
         {
             HH=HHSerial();
         }
         txtHH.setText(HH);
         txtHH.setFocusable(false);


//         dtpEnDate.setOnTouchListener(new View.OnTouchListener() {
//             @Override
//             public boolean onTouch(View v, MotionEvent event) {
//                 final int DRAWABLE_RIGHT  = 2;
//                 if(event.getAction() == MotionEvent.ACTION_UP) {
//                     if(event.getRawX() >= (dtpEnDate.getRight() - dtpEnDate.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                         VariableID = "btnEnDate"; showDialog(DATE_DIALOG);
//                      return true;
//                     }
//                 }
//                 return false;
//             }
//         });
//         dtpExDate.setOnTouchListener(new View.OnTouchListener() {
//             @Override
//             public boolean onTouch(View v, MotionEvent event) {
//                 final int DRAWABLE_RIGHT  = 2;
//                 if(event.getAction() == MotionEvent.ACTION_UP) {
//                     if(event.getRawX() >= (dtpExDate.getRight() - dtpExDate.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                         VariableID = "btnExDate"; showDialog(DATE_DIALOG);
//                      return true;
//                     }
//                 }
//                 return false;
//             }
//         });

         dtpVDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {

                 final int DRAWABLE_RIGHT  = 2;
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (dtpVDate.getRight() - dtpVDate.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         VariableID = "dtpVDate"; showDialog(DATE_DIALOG);

                      return true;
                     }
                 }
                 return false;
             }
         });



         //Hide all skip variables
         secVStatusOth.setVisibility(View.GONE);
         secReligion.setVisibility(View.GONE);
         secMobileNo1.setVisibility(View.GONE);
         secMobileNo2.setVisibility(View.GONE);
//         secHHHead.setVisibility(View.GONE);
         secTotMem.setVisibility(View.GONE);
         secTotRWo.setVisibility(View.GONE);

         //***********************added by sakib********************************************
         secMobileNo2.setVisibility(View.GONE);
         txtMobileNo2.setVisibility(View.GONE);

         btnPlusMobile1= (Button) findViewById(R.id.btnPlusMobile1);
         btnPlusMobile1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 secMobileNo2.setVisibility(View.VISIBLE);
                 txtMobileNo2.setVisibility(View.VISIBLE);
             }
         });

         btnMinusMobile1= (Button) findViewById(R.id.btnMinusMobile1);
         btnMinusMobile1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 secMobileNo2.setVisibility(View.GONE);
                 txtMobileNo2.setText("");
                 txtMobileNo2.setVisibility(View.GONE);
             }
         });
         //***********************added by sakib********************************************

        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});

         DataSearch(VILL,BARI,HH);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Household_Visit.this, e.getMessage());
         return;
     }
 }

     private String HHSerial()
     {
         String SL = C.ReturnSingleValue("Select (ifnull(max(cast(HH as int)),0)+1)SL from Household where Bari='"+BARI+"'"); //where ParticipantID='"+ ParticipantID +"'");
         int length=SL.length();
         String s = "";
         for(int i=0;i<2-length;i++)
         {
             s+="0";
         }
         SL=s+SL;
         return SL;
     }

 private void DataSave()
 {
   try
     {
 
         String DV="";

         if(txtVill.getText().toString().length()==0 & secVill.isShown())
           {
             Connection.MessageBox(Household_Visit.this, "Required field: গ্রাম.");
             txtVill.requestFocus(); 
             return;	
           }
         else if(txtBari.getText().toString().length()==0 & secBari.isShown())
           {
             Connection.MessageBox(Household_Visit.this, "Required field: বাড়ি.");
             txtBari.requestFocus(); 
             return;	
           }
         else if(txtHH.getText().toString().length()==0 & secHH.isShown())
           {
             Connection.MessageBox(Household_Visit.this, "Required field: খানা.");
             txtHH.requestFocus(); 
             return;	
           }
         else if(txtHHHead.getText().toString().length()==0 & secHHHead.isShown())
         {
             Connection.MessageBox(Household_Visit.this, "Required field: খানা প্রধানের নাম.");
             txtHHHead.requestFocus();
             return;
         }
         else if(spnReligion.getSelectedItemPosition()==0  & secReligion.isShown())
           {
             Connection.MessageBox(Household_Visit.this, "Required field: ধর্ম.");
             spnReligion.requestFocus(); 
             return;	
           }
         else if(txtMobileNo1.getText().toString().length()!=0 & txtMobileNo1.getText().toString().length()!=11 & secMobileNo1.isShown())
           {
             Connection.MessageBox(Household_Visit.this, "Required field: ১ম মোবাইল নম্বর ১১ সংখ্যা হতে হবে.");
             txtMobileNo1.requestFocus(); 
             return;	
           }
         else if(txtMobileNo2.getText().toString().length()!=0 &txtMobileNo2.getText().toString().length()!=11  & secMobileNo2.isShown())
           {
             Connection.MessageBox(Household_Visit.this, "Required field: ২য় মোবাইল নম্বর ১১ সংখ্যা হতে হবে.");
             txtMobileNo2.requestFocus();
             return;
           }

         else if(txtTotMem.getText().toString().length()==0 & secTotMem.isShown())
           {
             Connection.MessageBox(Household_Visit.this, "Required field: মোট সদস্য সংখ্যা.");
             txtTotMem.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtTotMem.getText().toString().length()==0 ? "01" : txtTotMem.getText().toString()) < 01 || Integer.valueOf(txtTotMem.getText().toString().length()==0 ? "30" : txtTotMem.getText().toString()) > 30)
           {
             Connection.MessageBox(Household_Visit.this, "সদস্য  সংখ্যা  অবশ্যই ১ থেকে ৩০ এর ভিতর হতে হবে(মোট সদস্য সংখ্যা).");
             txtTotMem.requestFocus(); 
             return;	
           }
//         else if(txtTotRWo.getText().toString().length()==0 & secTotRWo.isShown())
//           {
//             Connection.MessageBox(Household_Visit.this, "Required field: মোট মহিলা.");
//             txtTotRWo.requestFocus();
//             return;
//           }
//         else if(Integer.valueOf(txtTotRWo.getText().toString().length()==0 ? "0" : txtTotRWo.getText().toString()) < 0 || Integer.valueOf(txtTotRWo.getText().toString().length()==0 ? "10" : txtTotRWo.getText().toString()) > 10)
//           {
//             Connection.MessageBox(Household_Visit.this, "মোট মহিলার সংখ্যা  অবশ্যই ০ থেকে ১০ এর ভিতর হতে হবে (মোট মহিলা).");
//             txtTotRWo.requestFocus();
//             return;
//           }
         DV = Global.DateValidate(dtpVDate.getText().toString());
         if(DV.length()!=0 & secVDate.isShown())
           {
             Connection.MessageBox(Household_Visit.this, DV);
               dtpVDate.requestFocus();
             return;
           }
         else if(spnVStatus.getSelectedItemPosition()==0  & spnVStatus.isShown())
         {
             Connection.MessageBox(Household_Visit.this, "Required field: সাক্ষাতকারের ফলাফল.");
             spnVStatus.requestFocus();
             return;
         }
         else if(txtVStatusOth.getText().toString().length()==0 & txtVStatusOth.isShown())
         {
             Connection.MessageBox(Household_Visit.this, "Required field: অন্যান্য উল্লেখ করুন");
             txtVStatusOth.requestFocus();
             return;
         }
         else if(spnResp.getSelectedItemPosition()==0  & spnResp.isShown())
         {
             Connection.MessageBox(Household_Visit.this, "Required field: উত্তরদাতা.");
             spnResp.requestFocus();
             return;
         }

         if (txtMobileNo1.getText().toString().equals(txtMobileNo2.getText().toString()) & txtMobileNo2.isShown() & txtMobileNo1.getText().toString().length() != 0)
         {
             Connection.MessageBox(Household_Visit.this, "১ম মোবাইল নম্বর এবং ২য় মোবাইল নম্বর একই হবেনা");
             txtMobileNo2.requestFocus();
             return;
         }

//         Integer TotMem = Integer.valueOf(txtTotMem.getText().toString().length() == 0 ? "0" : txtTotMem.getText().toString());
//         Integer TotRWo = Integer.valueOf(txtTotRWo.getText().toString().length() == 0 ? "0" : txtTotRWo.getText().toString());
//
//         if (TotMem < TotRWo) {
//             Connection.MessageBox(Household_Visit.this, "কতজন মহিলা আছে অবশ্যই মোট সদস্য  সংখ্যার সমান অথবা কম হবে");
//             txtTotMem.requestFocus();
//             return;
//         }


//         else if(txtEnType.getText().toString().length()==0 & secEnType.isShown())
//           {
//             Connection.MessageBox(Household_Visit.this, "Required field: তালিকাভুক্তির ধরন.");
//             txtEnType.requestFocus();
//             return;
//           }
//         else if(Integer.valueOf(txtEnType.getText().toString().length()==0 ? "20" : txtEnType.getText().toString()) < 20 || Integer.valueOf(txtEnType.getText().toString().length()==0 ? "25" : txtEnType.getText().toString()) > 25)
//           {
//             Connection.MessageBox(Household_Visit.this, "Value should be between 20 and 25(তালিকাভুক্তির ধরন).");
//             txtEnType.requestFocus();
//             return;
//           }
//         DV = Global.DateValidate(dtpEnDate.getText().toString());
//         if(DV.length()!=0 & secEnDate.isShown())
//           {
//             Connection.MessageBox(Household_Visit.this, DV);
//             dtpEnDate.requestFocus();
//             return;
//           }
//         else if(txtExType.getText().toString().length()==0 & secExType.isShown())
//           {
//             Connection.MessageBox(Household_Visit.this, "Required field: ExType.");
//             txtExType.requestFocus();
//             return;
//           }
//         else if(Integer.valueOf(txtExType.getText().toString().length()==0 ? "51" : txtExType.getText().toString()) < 51 || Integer.valueOf(txtExType.getText().toString().length()==0 ? "56" : txtExType.getText().toString()) > 56)
//           {
//             Connection.MessageBox(Household_Visit.this, "Value should be between 51 and 56(ExType).");
//             txtExType.requestFocus();
//             return;
//           }
//         DV = Global.DateValidate(dtpExDate.getText().toString());
//         if(DV.length()!=0 & secExDate.isShown())
//           {
//             Connection.MessageBox(Household_Visit.this, DV);
//             dtpExDate.requestFocus();
//             return;
//           }
//         else if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
//           {
//             Connection.MessageBox(Household_Visit.this, "Required field: রাউন্ড.");
//             txtRnd.requestFocus();
//             return;
//           }
//         else if(Integer.valueOf(txtRnd.getText().toString().length()==0 ? "1" : txtRnd.getText().toString()) < 1 || Integer.valueOf(txtRnd.getText().toString().length()==0 ? "99" : txtRnd.getText().toString()) > 99)
//           {
//             Connection.MessageBox(Household_Visit.this, "Value should be between 1 and 99(রাউন্ড).");
//             txtRnd.requestFocus();
//             return;
//           }
 
         String SQL = "";
         RadioButton rb;

         Household_DataModel objSave = new Household_DataModel();
         objSave.setVill(txtVill.getText().toString());
         objSave.setBari(txtBari.getText().toString());
         objSave.setHH(txtHH.getText().toString());
         objSave.setReligion((spnReligion.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnReligion.getSelectedItem().toString(), "-")));
         objSave.setMobileNo1(txtMobileNo1.getText().toString());
         objSave.setMobileNo2(txtMobileNo2.getText().toString());
         objSave.setHHHead(txtHHHead.getText().toString());
         objSave.setTotMem(txtTotMem.getText().toString());
//         objSave.setTotRWo(txtTotRWo.getText().toString());
         objSave.setTotRWo("");
         objSave.setEnType("20");
         objSave.setEnDate(dtpVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpVDate.getText().toString()) : dtpVDate.getText().toString());
         objSave.setExType("");
         objSave.setExDate("");
         objSave.setRnd("00");
         objSave.setEnDt(Global.DateTimeNowYMDHMS());
         objSave.setStartTime(STARTTIME);
         objSave.setEndTime(g.CurrentTime24());
         objSave.setDeviceID(DEVICEID);
         objSave.setEntryUser(ENTRYUSER); //from data entry user list
         //objSave.setLat(Double.toString(currentLatitude));
         //objSave.setLon(Double.toString(currentLongitude));
         String status = objSave.SaveUpdateData(this);

         //*************************************visit save by sakib************************************************
         Visits_DataModel objSave1 = new Visits_DataModel();
         objSave1.setVill(txtVill.getText().toString());
         objSave1.setBari(txtBari.getText().toString());
         objSave1.setHH(txtHH.getText().toString());
         objSave1.setVDate(dtpVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpVDate.getText().toString()) : dtpVDate.getText().toString());
         objSave1.setVStatus((spnVStatus.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnVStatus.getSelectedItem().toString(), "-")));
         objSave1.setVStatusOth(txtVStatusOth.getText().toString());

         //objSave1.setResp((spnResp.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnResp.getSelectedItem().toString(), "-")));
         objSave1.setResp((spnResp.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnResp.getSelectedItem().toString(), "-")));

         objSave1.setRnd("00");
         objSave1.setEnDt(Global.DateTimeNowYMDHMS());
         objSave1.setStartTime(STARTTIME);
         objSave1.setEndTime(g.CurrentTime24());
         objSave1.setDeviceID(DEVICEID);
         objSave1.setEntryUser(ENTRYUSER); //from data entry user list
         objSave1.setLat(MySharedPreferences.getValue(Household_Visit.this,"lat"));
         objSave1.setLon(MySharedPreferences.getValue(Household_Visit.this,"lon"));

         String status1 = objSave1.SaveUpdateData(this);

         //*************************************visit save by sakib************************************************

         if(status1.length()==0) {
             if(status.length()==0)
             {
                 String VS = spnVStatus.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnVStatus.getSelectedItem().toString(), "-");
                 if(VS.equals("1"))
                 {
                     Intent returnIntent = new Intent();
                     returnIntent.putExtra("res", "hh");
                     setResult(Activity.RESULT_OK, returnIntent);

                     finish();
                     /*Intent f1;
                     f1 = new Intent(getApplicationContext(), Member_list.class);
                     f1.putExtras(IDbundle);
                     startActivity(f1);*/

                     Intent f1;
                     f1 = new Intent(getApplicationContext(), Member_list.class);
                     f1.putExtras(IDbundle);
                     startActivityForResult(f1, 1);
                 }else
                 {
                     Intent returnIntent = new Intent();
                     returnIntent.putExtra("res", "hh");
                     setResult(Activity.RESULT_OK, returnIntent);
                     Connection.MessageBox(Household_Visit.this, "Saved Successfully");
                 }
             }
             else{
                 Connection.MessageBox(Household_Visit.this, status1);
                 return;
             }
         }
         else{
             Connection.MessageBox(Household_Visit.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Household_Visit.this, e.getMessage());
         return;
     }
 }


 private void DataSearch(String Vill, String Bari, String HH)
     {
       try
        {
           RadioButton rb;
           Household_DataModel d = new Household_DataModel();
           String SQL = "Select * from "+ TableName +"  Where Vill='"+ Vill +"' and Bari='"+ Bari +"' and HH='"+ HH +"'";
           List<Household_DataModel> data = d.SelectAll(this, SQL);
           for(Household_DataModel item : data){
             txtVill.setText(item.getVill());
             txtBari.setText(item.getBari());
             txtHH.setText(item.getHH());
             spnReligion.setSelection(Global.SpinnerItemPositionAnyLength(spnReligion, item.getReligion()));
             txtMobileNo1.setText(item.getMobileNo1());
             txtMobileNo2.setText(item.getMobileNo2());
             txtHHHead.setText(item.getHHHead());
             txtTotMem.setText(item.getTotMem());
//             txtTotRWo.setText(item.getTotRWo());

             //*********************************************sakib*********************************************

               Visits_DataModel d1 = new Visits_DataModel();
               String SQL1 = "Select * from Visits Where Vill='"+ Vill +"' and Bari='"+ Bari +"' and HH='"+ HH +"'";
               List<Visits_DataModel> data1 = d1.SelectAll(this, SQL1);
               for(Visits_DataModel item1 : data1) {
                   txtVill.setText(item1.getVill());
                   txtBari.setText(item1.getBari());
                   txtHH.setText(item1.getHH());
                   dtpVDate.setText(item1.getVDate().toString().length() == 0 ? "" : Global.DateConvertDMY(item1.getVDate()));
                   spnVStatus.setSelection(Global.SpinnerItemPositionAnyLength(spnVStatus, item1.getVStatus()));
                   txtVStatusOth.setText(item1.getVStatusOth());
                   spnResp.setSelection(Global.SpinnerItemPositionAnyLength(spnResp, item1.getResp()));
               }


            //*********************************************sakib**********************************************
//             txtEnType.setText(item.getEnType());
//             dtpEnDate.setText(item.getEnDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getEnDate()));
//             txtExType.setText(item.getExType());
//             dtpExDate.setText(item.getExDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getExDate()));
//             txtRnd.setText(item.getRnd());
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Household_Visit.this, e.getMessage());
            return;
        }
     }



 protected Dialog onCreateDialog(int id) {
   final Calendar c = Calendar.getInstance();
   hour = c.get(Calendar.HOUR_OF_DAY);
   minute = c.get(Calendar.MINUTE);
   switch (id) {
       case DATE_DIALOG:
           return new DatePickerDialog(this, mDateSetListener,g.mYear,g.mMonth-1,g.mDay);
       case TIME_DIALOG:
           return new TimePickerDialog(this, timePickerListener, hour, minute,false);
       }
     return null;
 }
//
 private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
      mYear = year; mMonth = monthOfYear+1; mDay = dayOfMonth;
      EditText dtpDate;


              dtpDate = (EditText)findViewById(R.id.dtpEnDate);
             if (VariableID.equals("btnEnDate"))
              {
                  dtpDate = (EditText)findViewById(R.id.dtpEnDate);
              }
             else if (VariableID.equals("btnExDate"))
              {
                  dtpDate = (EditText)findViewById(R.id.dtpExDate);
              }
             else if(VariableID.equals("dtpVDate"))
             {
                 dtpDate= (EditText) findViewById(R.id.dtpVDate);
             }
      dtpDate.setText(new StringBuilder()
      .append(Global.Right("00"+mDay,2)).append("/")
      .append(Global.Right("00"+mMonth,2)).append("/")
      .append(mYear));
      }
  };

 private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
       hour = selectedHour; minute = selectedMinute;
       EditText tpTime;


          //tpTime.setText(new StringBuilder().append(Global.Right("00"+hour,2)).append(":").append(Global.Right("00"+minute,2)));

    }
  };


 //GPS Reading
 //.....................................................................................................
 public void FindLocation() {
 LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

 LocationListener locationListener = new LocationListener() {
     public void onLocationChanged(Location location) {
         updateLocation(location);
     }
     public void onStatusChanged(String provider, int status, Bundle extras) {
     }
     public void onProviderEnabled(String provider) {
     }
     public void onProviderDisabled(String provider) {
     }
   };
  //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
 }

 void updateLocation(Location location) {
     currentLocation  = location;
     currentLatitude  = currentLocation.getLatitude();
     currentLongitude = currentLocation.getLongitude();
 }


 // Method to turn on GPS
 public void turnGPSOn(){
     try
     {
         String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
         if(!provider.contains("gps")){ //if gps is disabled
             final Intent poke = new Intent();
             poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
             poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
             poke.setData(Uri.parse("3"));
             sendBroadcast(poke);
         }
     }
     catch (Exception e) {
     }
 }
 
 // Method to turn off the GPS
 public void turnGPSOff(){
     String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
 
     if(provider.contains("gps")){ //if gps is enabled
         final Intent poke = new Intent();
         poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
         poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
         poke.setData(Uri.parse("3"));
         sendBroadcast(poke);
     }
 }
 
 // turning off the GPS if its in on state. to avoid the battery drain.
 @Override
 protected void onDestroy() {
     // TODO Auto-generated method stub
     super.onDestroy();
     turnGPSOff();
 }
}