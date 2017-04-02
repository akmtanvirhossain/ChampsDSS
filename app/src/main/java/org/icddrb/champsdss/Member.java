
 package org.icddrb.champsdss;


 //Android Manifest Code
 //<activity android:name=".Member" android:label="Member" />
 import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
 import android.widget.CheckBox;
 import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

 public class Member extends Activity {
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
         LinearLayout seclbl1;
         View linelbl1;
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
         LinearLayout secMSlNo;
         View lineMSlNo;
         TextView VlblMSlNo;
         EditText txtMSlNo;
         LinearLayout secPNo;
         View linePNo;
         TextView VlblPNo;
         EditText txtPNo;
         LinearLayout secName;
         View lineName;
         TextView VlblName;
         EditText txtName;
         LinearLayout secRth;
         View lineRth;
         TextView VlblRth;
         Spinner spnRth;
         LinearLayout secSex;
         View lineSex;
         TextView VlblSex;
         RadioGroup rdogrpSex;
         
         RadioButton rdoSex1;
         RadioButton rdoSex2;
         LinearLayout secBDate;
         View lineBDate;
         TextView VlblBDate;
         EditText dtpBDate;
         LinearLayout secAgeY;
         View lineAgeY;
         TextView VlblAgeY;
         EditText txtAgeY;
         LinearLayout secMoNo;
         View lineMoNo;
         TextView VlblMoNo;
         Spinner spnMoNo;

         LinearLayout secFaNo;
         View lineFaNo;
         TextView VlblFaNo;
         Spinner spnFaNo;

         LinearLayout secEdu;
         View lineEdu;
         TextView VlblEdu;
         Spinner spnEdu;
         LinearLayout secMS;
         View lineMS;
         TextView VlblMS;
         Spinner spnMS;

         LinearLayout secOcp;
         View lineOcp;
         TextView VlblOcp;
         Spinner spnOcp;

         LinearLayout seclblSpsl;
         View linelblSpsl;

         LinearLayout secSp1;
         View lineSp1;
         TextView VlblSp1;
         Spinner spnSp1;

         LinearLayout secSp2;
         View lineSp2;
         TextView VlblSp2;
         Spinner spnSp2;

         LinearLayout secSp3;
         View lineSp3;
         TextView VlblSp3;
         Spinner spnSp3;
         LinearLayout secSp4;
         View lineSp4;
         TextView VlblSp4;
         Spinner spnSp4;

         LinearLayout secEnType;
         View lineEnType;
         TextView VlblEnType;
         EditText txtEnType;
         LinearLayout secEnDate;
         View lineEnDate;
         TextView VlblEnDate;
         EditText dtpEnDate;
         LinearLayout secExType;
         View lineExType;
         TextView VlblExType;
         EditText txtExType;
         LinearLayout secExDate;
         View lineExDate;
         TextView VlblExDate;
         EditText dtpExDate;
         TextView dtpVDate;
        CheckBox chkNeedReview;

    static String TableName;

    static String STARTTIME = "";
    static String DEVICEID  = "";
    static String ENTRYUSER = "";

    Bundle IDbundle;
    static String VILL = "";
    static String BARI = "";
    static String HH = "";
    static String MSLNO = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.member);
         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = g.getDeviceNo();
         ENTRYUSER = g.getUserId();

         IDbundle = getIntent().getExtras();
         VILL = IDbundle.getString("Vill");
         BARI = IDbundle.getString("Bari");
         HH = IDbundle.getString("HH");
         MSLNO = IDbundle.getString("MSlNo");

         TableName = "Member";

         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Member.this);
                 adb.setTitle("Close");
                 adb.setMessage("আপনি কি এই ফরম থেকে বের হতে চান [হ্যাঁ/না]?");
                 adb.setNegativeButton("না", null);
                 adb.setPositiveButton("হ্যাঁ", new AlertDialog.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                         finish();
//                         startActivity(new Intent(Member.this, Member_list.class));
                     }});
                 adb.show();
             }});

         chkNeedReview = (CheckBox)findViewById(R.id.chkNeedReview);
         seclbl1=(LinearLayout)findViewById(R.id.seclbl1);
         linelbl1=(View)findViewById(R.id.linelbl1);
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

         secMSlNo=(LinearLayout)findViewById(R.id.secMSlNo);
         lineMSlNo=(View)findViewById(R.id.lineMSlNo);
         VlblMSlNo=(TextView) findViewById(R.id.VlblMSlNo);
         txtMSlNo=(EditText) findViewById(R.id.txtMSlNo);

         if(MSLNO.length()==0)
             txtMSlNo.setText(MemNo(VILL,BARI,HH));
         else
             txtMSlNo.setText(MSLNO);

         txtMSlNo.setEnabled(false);

         secPNo=(LinearLayout)findViewById(R.id.secPNo);
         linePNo=(View)findViewById(R.id.linePNo);
         VlblPNo=(TextView) findViewById(R.id.VlblPNo);
         txtPNo=(EditText) findViewById(R.id.txtPNo);

         txtPNo.setText(VILL.toString()+BARI.toString()+HH.toString()+txtMSlNo.getText().toString());

         secName=(LinearLayout)findViewById(R.id.secName);
         lineName=(View)findViewById(R.id.lineName);
         VlblName=(TextView) findViewById(R.id.VlblName);
         txtName=(EditText) findViewById(R.id.txtName);
         secRth=(LinearLayout)findViewById(R.id.secRth);
         lineRth=(View)findViewById(R.id.lineRth);
         VlblRth=(TextView) findViewById(R.id.VlblRth);
         spnRth=(Spinner) findViewById(R.id.spnRth);
         List<String> listRth = new ArrayList<String>();
         
         listRth.add("");
         listRth.add("00-খানা প্রধানের সাথে স¤পর্ক নেই");
         listRth.add("01-নিজেই খানা প্রধান");
         listRth.add("02-খানা প্রধানের স্বামী/স্ত্রী");
         listRth.add("03-খানা প্রধানের ছেলে/মেয়ে");
         listRth.add("04-খানা প্রধানের মা/বাবা");
         listRth.add("05-খানা প্রধানের ভাই/বোন");
         listRth.add("06-খানা প্রধানের চাচা/ফুফু/মামা/খালা");
         listRth.add("07-খানা প্রধানের দাদা/দাদি/নানা/নানি");
         listRth.add("08-খানা প্রধানের নাতি/নাতটি");
         listRth.add("09-খানা প্রধানের শ্যালক/শ্যালিকা/দেবর/ননদ");
         listRth.add("10-খানা প্রধানের শ্বশুর/শাশুড়ী");
         listRth.add("11-খানা প্রধানের সৎ মা/বাবা");
         listRth.add("12-খানা প্রধানের সৎ ছেলে/মেয়ে");
         listRth.add("13-খানা প্রধানের পালিত ছেলে/মেয়ে");
         listRth.add("14-খানা প্রধানের সৎ ভাই/সৎবোন");
         listRth.add("15-খানা প্রধানের ছেলের বউ/মেয়ের জামাই");
         listRth.add("16-খানা প্রধানের ভাতিজা/ভাতিজী/ভাগ্নে/ভাগ্নী");
         listRth.add("17-খানা প্রধানের বোন জামাই/ভাবী");
         listRth.add("18-গৃহকর্মী");
         listRth.add("77-অন্যান্য সম্পর্ক যা উপরের তালিকায় অন্তর্ভূক্ত নয়");
         ArrayAdapter<String> adptrRth= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listRth);
         spnRth.setAdapter(adptrRth);

         secSex=(LinearLayout)findViewById(R.id.secSex);
         lineSex=(View)findViewById(R.id.lineSex);
         VlblSex = (TextView) findViewById(R.id.VlblSex);
         rdogrpSex = (RadioGroup) findViewById(R.id.rdogrpSex);
         
         rdoSex1 = (RadioButton) findViewById(R.id.rdoSex1);
         rdoSex2 = (RadioButton) findViewById(R.id.rdoSex2);

         secBDate=(LinearLayout)findViewById(R.id.secBDate);
         lineBDate=(View)findViewById(R.id.lineBDate);
         VlblBDate=(TextView) findViewById(R.id.VlblBDate);
         dtpBDate=(EditText) findViewById(R.id.dtpBDate);

         secAgeY=(LinearLayout)findViewById(R.id.secAgeY);
         lineAgeY=(View)findViewById(R.id.lineAgeY);
         VlblAgeY=(TextView) findViewById(R.id.VlblAgeY);
         txtAgeY=(EditText) findViewById(R.id.txtAgeY);

         secMoNo = (LinearLayout) findViewById(R.id.secMoNo);
         lineMoNo = (View) findViewById(R.id.lineMoNo);
         VlblMoNo = (TextView) findViewById(R.id.VlblMoNo);
         spnMoNo = (Spinner) findViewById(R.id.spnMoNo);
         spnMoNo.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from Member Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH +"' union Select '00-এই খানার সদস্য নয়'"));

         secFaNo = (LinearLayout) findViewById(R.id.secFaNo);
         lineFaNo = (View) findViewById(R.id.lineFaNo);
         VlblFaNo = (TextView) findViewById(R.id.VlblFaNo);
         spnFaNo = (Spinner) findViewById(R.id.spnFaNo);
         spnFaNo.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from Member Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH +"' union Select '00-এই খানার সদস্য নয়'"));

//         secMoNo=(LinearLayout)findViewById(R.id.secMoNo);
//         lineMoNo=(View)findViewById(R.id.lineMoNo);
//         VlblMoNo=(TextView) findViewById(R.id.VlblMoNo);
//         txtMoNo=(EditText) findViewById(R.id.txtMoNo);

//         secFaNo=(LinearLayout)findViewById(R.id.secFaNo);
//         lineFaNo=(View)findViewById(R.id.lineFaNo);
//         VlblFaNo=(TextView) findViewById(R.id.VlblFaNo);
//         txtFaNo=(EditText) findViewById(R.id.txtFaNo);

         secEdu=(LinearLayout)findViewById(R.id.secEdu);
         lineEdu=(View)findViewById(R.id.lineEdu);
         VlblEdu=(TextView) findViewById(R.id.VlblEdu);
         spnEdu=(Spinner) findViewById(R.id.spnEdu);
         List<String> listEdu = new ArrayList<String>();
         
         listEdu.add("");
         listEdu.add("00-কোন শিক্ষাগত যোগ্যতা নেই");
         listEdu.add("01-প্রথম শ্রেণি পাশ");
         listEdu.add("02-দ্বিতীয় শ্রেণি পাশ");
         listEdu.add("03-তৃতীয় শ্রেণি পাশ");
         listEdu.add("04-চতুর্থ শ্রেণি পাশ");
         listEdu.add("05-পঞ্চম শ্রেণি পাশ");
         listEdu.add("06-ষষ্ঠ শ্রেণি পাশ");
         listEdu.add("07-সপ্তম শ্রেণি পাশ");
         listEdu.add("08-অষ্টম শ্রেণি পাশ");
         listEdu.add("09-নবম শ্রেণি পাশ");
         listEdu.add("10-এসএসসি /দাখিল পাশ");
         listEdu.add("12-এইচএসসি /আলিম পাশ");
         listEdu.add("14-বিএ/বিকম/বিএসসি /ফাজিল/ ডিপ্লোমা পাশ");
         listEdu.add("16-বিএ/বিকম/বিএসসি (সম্মান) অথবা বিএসসি ইঞ্জিনিয়ারিং পাশ");
         listEdu.add("17-এমএ/এমএসসি/এমকম/এমএসএস/এমবিবিএস /কামিল পাশ");
         listEdu.add("30–ব্রাক শিশু শ্রেণী পাশ");
         listEdu.add("31-ব্র্যাক প্রথম শ্রেণি পাশ");
         listEdu.add("32-ব্র্যাক ২য় শ্রেণি পাশ");
         listEdu.add("33-ব্র্যাক ৩য় শ্রেণি পাশ");
         listEdu.add("34-ব্র্যাক ৪র্থ  শ্রেণি পাশ");
         listEdu.add("35-ব্র্যাক ৫ম শ্রেণি পাশ");
         listEdu.add("99-জানিনা");
         ArrayAdapter<String> adptrEdu= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listEdu);
         spnEdu.setAdapter(adptrEdu);

         secMS=(LinearLayout)findViewById(R.id.secMS);
         lineMS=(View)findViewById(R.id.lineMS);
         VlblMS=(TextView) findViewById(R.id.VlblMS);
         spnMS=(Spinner) findViewById(R.id.spnMS);
         List<String> listMS = new ArrayList<String>();
         
         listMS.add("");
         listMS.add("30-কখনও বিয়ে হয়নি");
         listMS.add("31-বর্তমানে বিবাহিতা/বিবাহিত");
         listMS.add("32-তালাকপ্রাপ্তা/তালাকপ্রাপ্ত (আইনগত ভাবে তালাকপ্রাপ্তা)");
         listMS.add("33-বিধবা/বিপত্নীক (স্বামী/স্ত্রী মৃত-বর্তমানে কোন বৈবাহিক সম্পর্ক নাই)");
         listMS.add("34-বিচ্ছিন্না/বিচ্ছন্ন");
         ArrayAdapter<String> adptrMS= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listMS);
         spnMS.setAdapter(adptrMS);

         spnMS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if (position != 2)
                 {
                     seclblSpsl.setVisibility(View.GONE);
                     spnSp1.setSelection(0);
                     spnSp1.setVisibility(View.GONE);
                     lineSp1.setVisibility(View.GONE);
                     secSp1.setVisibility(View.GONE);
                     lineSp1.setVisibility(View.GONE);

                     spnSp2.setSelection(0);
                     spnSp2.setVisibility(View.GONE);
                     lineSp2.setVisibility(View.GONE);
                     secSp2.setVisibility(View.GONE);
                     lineSp2.setVisibility(View.GONE);

                     spnSp3.setSelection(0);
                     spnSp3.setVisibility(View.GONE);
                     lineSp3.setVisibility(View.GONE);
                     secSp3.setVisibility(View.GONE);
                     lineSp3.setVisibility(View.GONE);

                     spnSp4.setSelection(0);
                     spnSp4.setVisibility(View.GONE);
                     lineSp4.setVisibility(View.GONE);
                     secSp4.setVisibility(View.GONE);
                     lineSp4.setVisibility(View.GONE);

                 } else {

                     seclblSpsl.setVisibility(View.VISIBLE);
                     spnSp1.setVisibility(View.VISIBLE);
                     lineSp1.setVisibility(View.VISIBLE);
                     secSp1.setVisibility(View.VISIBLE);
                     lineSp1.setVisibility(View.VISIBLE);

                     spnSp2.setVisibility(View.GONE);
                     lineSp2.setVisibility(View.GONE);
                     secSp2.setVisibility(View.GONE);
                     lineSp2.setVisibility(View.GONE);

                     spnSp3.setVisibility(View.GONE);
                     lineSp3.setVisibility(View.GONE);
                     secSp3.setVisibility(View.GONE);
                     lineSp3.setVisibility(View.GONE);

                     spnSp4.setVisibility(View.GONE);
                     lineSp4.setVisibility(View.GONE);
                     secSp4.setVisibility(View.GONE);
                     lineSp4.setVisibility(View.GONE);
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });


         secOcp=(LinearLayout)findViewById(R.id.secOcp);
         lineOcp=(View)findViewById(R.id.lineOcp);
         VlblOcp=(TextView) findViewById(R.id.VlblOcp);
         spnOcp=(Spinner) findViewById(R.id.spnOcp);
         List<String> listOcp = new ArrayList<String>();
         
         listOcp.add("");
         listOcp.add("01-বেকার");
         listOcp.add("02-ছাত্র /ছাত্রী (চাকুরী করে না)");
         listOcp.add("03-গৃহিনী/গৃহস্থালীর কাজকর্ম করে (চাকুরী করে না)");
         listOcp.add("04-ভিক্ষুক (চাকুরী করে না)");
         listOcp.add("05-প্রতিবন্ধী (চাকুরী করে না)");
         listOcp.add("06-অবসরপ্রাপ্ত (চাকুরী করে না)");
         listOcp.add("11-অদক্ষ শ্রমিক: গৃহকর্মী, আয়া, গার্মেন্টস শ্রমিক (পরিচ্ছন্নতা/খাবার বিতরন/নিরাপত্তা/সাপ্লাই কাজ করে),  উৎপাদন/নির্মাণ শ্রমিক (যোগালী), বাস/টেম্পুর সহকারী, পাহারাদার, পরিচ্ছন্নতাকর্মী, পিওন, রিক্সা/ভ্যান/ঠেলাগাড়ি চালক, দিনমজুর, কৃষক, জেলে");
         listOcp.add("17-অন্যান্য অদক্ষ শ্রমিক");
         listOcp.add("21-দক্ষ শ্রমিক: গার্মেন্টস কর্মী (ড্রেস তৈরী ও প্রোডাকশনের কাজে নিয়োজিত), বাবুর্চি, উৎপাদন/নির্মাণ শ্রমিক, গাড়ি চালক");
         listOcp.add("22-অন্যান্য দক্ষ শ্রমিক: দর্জি, নাপিত, তাতীঁ, কাঠের মিস্ত্রি, গাড়ির মিস্ত্রি,পাম্পের মিস্ত্রি, রং মিস্ত্রি,কারিগর, মুচি");
         listOcp.add("27-অন্যান্য দক্ষ শ্রমিক: ২২  কোডে বর্ণিত দক্ষ শ্রমিক ছাড়া অন্য কেউ");
         listOcp.add("31-মেধা সম্পন্ন: প্রশাসনিক/করণিক পদে সরকারী, বেসরকারী, গার্মেন্টস অথবা ব্যক্তিগতপ্রতিষ্ঠানে চাকুরী, সরকারী/বেসরকারী খাতের অতিরিক্ত জনবল, বিক্রয় প্রতিনিধি");
         listOcp.add("32-মাঠপর্যায়ের স্বাস্থ্যকর্মী: গ্রাম্য ডাক্তার, হোমিওপ্যাথিক ডাক্তার, কবিরাজ, অদক্ষ দাই, ঔষধ বিক্রেতা, সরকারী/বেসরকারী গ্রাম্য স্বাস্থ্যকর্মী (মেধাসম্পন্ন)");
         listOcp.add("33-ব্যবসায়ী, নিজস্ব ব্যবসা (মেধাসম্পন্ন)");
         listOcp.add("34-পেশাজীবি:ডাক্তার, কৃষিবিদ, শিক্ষক, প্রকৌশলী (মেধাসম্পন্ন)");
         listOcp.add("37-অন্যান্য মেধা সম্পন্ন");
         listOcp.add("77-অন্যান্য");
         listOcp.add("99-জানিনা");
         ArrayAdapter<String> adptrOcp= new ArrayAdapter<String>(this, R.layout.multiline_spinner_dropdown_item, listOcp);
         spnOcp.setAdapter(adptrOcp);

         seclblSpsl=(LinearLayout)findViewById(R.id.seclblSpsl);
         linelblSpsl=(View)findViewById(R.id.linelblSpsl);

         secSp1=(LinearLayout)findViewById(R.id.secSp1);
         lineSp1=(View)findViewById(R.id.lineSp1);
         VlblSp1=(TextView) findViewById(R.id.VlblSp1);
         spnSp1 = (Spinner) findViewById(R.id.spnSp1);
         spnSp1.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from Member Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH +"' union Select '00-এই খানার সদস্য নয়'"));

         secSp2=(LinearLayout)findViewById(R.id.secSp2);
         lineSp2=(View)findViewById(R.id.lineSp2);
         VlblSp2=(TextView) findViewById(R.id.VlblSp2);
         spnSp2=(Spinner) findViewById(R.id.spnSp2);
         spnSp2.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from Member Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH +"' union Select '00-এই খানার সদস্য নয়'"));

         secSp3=(LinearLayout)findViewById(R.id.secSp3);
         lineSp3=(View)findViewById(R.id.lineSp3);
         VlblSp3=(TextView) findViewById(R.id.VlblSp3);
         spnSp3=(Spinner) findViewById(R.id.spnSp3);
         spnSp3.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from Member Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH +"' union Select '00-এই খানার সদস্য নয়'"));

         secSp4=(LinearLayout)findViewById(R.id.secSp4);
         lineSp4=(View)findViewById(R.id.lineSp4);
         VlblSp4=(TextView) findViewById(R.id.VlblSp4);
         spnSp4=(Spinner) findViewById(R.id.spnSp4);
         spnSp4.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from Member Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH +"' union Select '00-এই খানার সদস্য নয়'"));

         secEnType=(LinearLayout)findViewById(R.id.secEnType);
         lineEnType=(View)findViewById(R.id.lineEnType);
         VlblEnType=(TextView) findViewById(R.id.VlblEnType);
         txtEnType=(EditText) findViewById(R.id.txtEnType);

         secEnDate=(LinearLayout)findViewById(R.id.secEnDate);
         lineEnDate=(View)findViewById(R.id.lineEnDate);
         VlblEnDate=(TextView) findViewById(R.id.VlblEnDate);
         dtpEnDate=(EditText) findViewById(R.id.dtpEnDate);

         secExType=(LinearLayout)findViewById(R.id.secExType);
         lineExType=(View)findViewById(R.id.lineExType);
         VlblExType=(TextView) findViewById(R.id.VlblExType);
         txtExType=(EditText) findViewById(R.id.txtExType);

         secExDate=(LinearLayout)findViewById(R.id.secExDate);
         lineExDate=(View)findViewById(R.id.lineExDate);
         VlblExDate=(TextView) findViewById(R.id.VlblExDate);
         dtpExDate=(EditText) findViewById(R.id.dtpExDate);

//         dtpVDate=(TextView) findViewById(R.id.dtpVDate);
//         dtpVDate=(TextView) findViewById(R.id.dtpVDate);
//         final TextView VlblVDate=(TextView) findViewById(R.id.VlblVDate);
//         dtpVDate.setText(g.CurrentTime24());

         dtpBDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT  = 2;
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (dtpBDate.getRight() - dtpBDate.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         VariableID = "btnBDate"; showDialog(DATE_DIALOG);
                      return true;
                     }
                 }
                 return false;
             }
         });
         dtpEnDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT  = 2;
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (dtpEnDate.getRight() - dtpEnDate.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         VariableID = "btnEnDate"; showDialog(DATE_DIALOG);
                      return true;
                     }
                 }
                 return false;
             }
         });
         dtpExDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT  = 2;
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (dtpExDate.getRight() - dtpExDate.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         VariableID = "btnExDate"; showDialog(DATE_DIALOG);
                      return true;
                     }
                 }
                 return false;
             }
         });


         spnSp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 String d = "";
                 if (position > 0)
                 {
                     String[] a = spnSp1.getSelectedItem().toString().split("-");
                     d = a[0];
                 }

                 if(d.equals("00") | d.length()==0) {
                     spnSp2.setVisibility(View.GONE);
                     lineSp2.setVisibility(View.GONE);
                     secSp2.setVisibility(View.GONE);
                     lineSp2.setVisibility(View.GONE);
                     spnSp2.setSelection(0);

                 }else{
                     spnSp2.setVisibility(View.VISIBLE);
                     lineSp2.setVisibility(View.VISIBLE);
                     secSp2.setVisibility(View.VISIBLE);
                     lineSp2.setVisibility(View.VISIBLE);
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });
         spnSp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 String d = "";
                 if (position > 0)
                 {
                     String[] a = spnSp2.getSelectedItem().toString().split("-");
                     d = a[0];
                 }

                 if(d.equals("00") | d.length()==0) {
                     spnSp3.setVisibility(View.GONE);
                     lineSp3.setVisibility(View.GONE);
                     secSp3.setVisibility(View.GONE);
                     lineSp3.setVisibility(View.GONE);
                     spnSp3.setSelection(0);
                 }else{
                     spnSp3.setVisibility(View.VISIBLE);
                     lineSp3.setVisibility(View.VISIBLE);
                     secSp3.setVisibility(View.VISIBLE);
                     lineSp3.setVisibility(View.VISIBLE);
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });
         spnSp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 String d = "";
                 if (position > 0)
                 {
                     String[] a = spnSp3.getSelectedItem().toString().split("-");
                     d = a[0];
                 }

                 if(d.equals("00") | d.length()==0) {
                     spnSp4.setVisibility(View.GONE);
                     lineSp4.setVisibility(View.GONE);
                     secSp4.setVisibility(View.GONE);
                     lineSp4.setVisibility(View.GONE);
                     spnSp4.setSelection(0);
                 }else{
                     spnSp4.setVisibility(View.VISIBLE);
                     lineSp4.setVisibility(View.VISIBLE);
                     secSp4.setVisibility(View.VISIBLE);
                     lineSp4.setVisibility(View.VISIBLE);
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });

         //Hide all skip variables
         txtVill.setEnabled(false);
         txtBari.setEnabled(false);
         txtHH.setEnabled(false);
         txtPNo.setEnabled(false);
         //secSp1.setVisibility(View.GONE);
         secSp2.setVisibility(View.GONE);
         secSp3.setVisibility(View.GONE);
         secSp4.setVisibility(View.GONE);

        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            DataSave();
        }});

         DataSearch(VILL,BARI,HH,MSLNO);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Member.this, e.getMessage());
         return;
     }
 }

 private void DataSave()
 {
   try
     {
         String DV="";

         if(txtVill.getText().toString().length()==0 & secVill.isShown())
           {
             Connection.MessageBox(Member.this, "Required field: গ্রাম.");
             txtVill.requestFocus(); 
             return;	
           }
         else if(txtBari.getText().toString().length()==0 & secBari.isShown())
           {
             Connection.MessageBox(Member.this, "Required field: বাড়ি.");
             txtBari.requestFocus(); 
             return;	
           }
         else if(txtHH.getText().toString().length()==0 & secHH.isShown())
           {
             Connection.MessageBox(Member.this, "Required field: খানা.");
             txtHH.requestFocus(); 
             return;	
           }
         else if(txtMSlNo.getText().toString().length()==0 & secMSlNo.isShown())
           {
             Connection.MessageBox(Member.this, "Required field: সিরিয়াল নং.");
             txtMSlNo.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtMSlNo.getText().toString().length()==0 ? "1" : txtMSlNo.getText().toString()) < 1 || Integer.valueOf(txtMSlNo.getText().toString().length()==0 ? "30" : txtMSlNo.getText().toString()) > 30)
           {
             Connection.MessageBox(Member.this, "সিরিয়াল নং  অবশ্যই ১ থেকে ৩০ এর ভিতর হতে হবে(সিরিয়াল নং).");
             txtMSlNo.requestFocus(); 
             return;	
           }
         else if(txtPNo.getText().toString().length()==0 & secPNo.isShown())
           {
             Connection.MessageBox(Member.this, "Required field: PNo.");
             txtPNo.requestFocus(); 
             return;	
           }
         else if(txtName.getText().toString().length()==0 & secName.isShown())
           {
             Connection.MessageBox(Member.this, "প্রশ্ন ৩: খানার সদস্যদের নাম খালি রাখা যাবেনা.");
             txtName.requestFocus(); 
             return;	
           }
         else if(spnRth.getSelectedItemPosition()==0  & secRth.isShown())
           {
             Connection.MessageBox(Member.this, "প্রশ্ন ৪: খানা প্রধানের সাথে সম্পর্ক. খালি রাখা যাবেনা");
             spnRth.requestFocus(); 
             return;	
           }
         
         else if(!rdoSex1.isChecked() & !rdoSex2.isChecked() & secSex.isShown())
           {
              Connection.MessageBox(Member.this, "প্রশ্ন ৫: পুরুষ/মহিলা থেকে একটি অপশন নির্বাচন করুন");
              rdoSex1.requestFocus();
              return;
           }
         DV = Global.DateValidate(dtpBDate.getText().toString());
         if(DV.length()!=0 & secBDate.isShown())
           {
             Connection.MessageBox(Member.this, DV);
             dtpBDate.requestFocus(); 
             return;	
           }
         else if(txtAgeY.getText().toString().length()==0 & secAgeY.isShown())
           {
             Connection.MessageBox(Member.this, "প্রশ্ন ৭: বয়স (পূর্ণ বছরে) খালি রাখা যাবেনা.");
             txtAgeY.requestFocus(); 
             return;	
           }


         int ageday = Global.DateDifferenceDays(Global.DateNowDMY(),dtpBDate.getText().toString());
         int ageyear = Integer.parseInt(txtAgeY.getText().toString().length()==0?"0":txtAgeY.getText().toString());
         if(ageday/365!=ageyear){
             Connection.MessageBox(Member.this, "বয়স এর সাথে জন্মতারিখ মিল নেই, বয়স "+ ageday/365 +" বছর হতে হবে।");
             return;
         }

         else if(Integer.valueOf(txtAgeY.getText().toString().length()==0 ? "0" : txtAgeY.getText().toString()) < 0 || Integer.valueOf(txtAgeY.getText().toString().length()==0 ? "110" : txtAgeY.getText().toString()) > 110)
           {
             Connection.MessageBox(Member.this, "সদস্যের বয়স ০ থেকে ১১০ এর ভিতরে হতে হবে।");
             txtAgeY.requestFocus(); 
             return;	
           }


         else if(spnMoNo.getSelectedItemPosition()==0  & spnMoNo.isShown())
         {
             Connection.MessageBox(Member.this, "প্রশ্ন ৮: মায়ের সিরিয়াল নম্বর খালি রাখা যাবেনা");
             spnMoNo.requestFocus();
             return;
         }
         else if(spnFaNo.getSelectedItemPosition()==0  & spnFaNo.isShown())
         {
             Connection.MessageBox(Member.this, "প্রশ্ন ৯: বাবার সিরিয়াল নম্বর খালি রাখা যাবেনা");
             spnFaNo.requestFocus();
             return;
         }
         else if(spnEdu.getSelectedItemPosition()==0  & secEdu.isShown())
           {
             Connection.MessageBox(Member.this, "প্রশ্ন ১০: সর্বোচ্চ শ্রেণি পাশ খালি রাখা যাবেনা.");
             spnEdu.requestFocus(); 
             return;	
           }
         else if(spnMS.getSelectedItemPosition()==0  & secMS.isShown())
           {
             Connection.MessageBox(Member.this, "প্রশ্ন ১১: বৈবাহিক অবস্থা খালি রাখা যাবেনা.");
             spnMS.requestFocus(); 
             return;	
           }
         else if(spnOcp.getSelectedItemPosition()==0  & secOcp.isShown())
           {
             Connection.MessageBox(Member.this, "প্রশ্ন ১২: পেশা খালি রাখা যাবেনা.");
             spnOcp.requestFocus(); 
             return;	
           }
         else if(spnSp1.getSelectedItemPosition()==0  & spnSp1.isShown())
         {
             Connection.MessageBox(Member.this, "প্রশ্ন ১৩: সিরিয়াল নম্বর ১ খালি রাখা যাবেনা.");
             spnSp1.requestFocus();
             return;
         }
         else if(txtEnType.getText().toString().length()==0 & secEnType.isShown())
           {
             Connection.MessageBox(Member.this, "Required field: EnType.");
             txtEnType.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtEnType.getText().toString().length()==0 ? "20" : txtEnType.getText().toString()) < 20 || Integer.valueOf(txtEnType.getText().toString().length()==0 ? "25" : txtEnType.getText().toString()) > 25)
           {
             Connection.MessageBox(Member.this, "Value should be between 20 and 25(EnType).");
             txtEnType.requestFocus(); 
             return;	
           }
         DV = Global.DateValidate(dtpEnDate.getText().toString());
         if(DV.length()!=0 & secEnDate.isShown())
           {
             Connection.MessageBox(Member.this, DV);
             dtpEnDate.requestFocus(); 
             return;	
           }
         else if(txtExType.getText().toString().length()==0 & secExType.isShown())
           {
             Connection.MessageBox(Member.this, "Required field: ExType.");
             txtExType.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtExType.getText().toString().length()==0 ? "51" : txtExType.getText().toString()) < 51 || Integer.valueOf(txtExType.getText().toString().length()==0 ? "56" : txtExType.getText().toString()) > 56)
           {
             Connection.MessageBox(Member.this, "Value should be between 51 and 56(ExType).");
             txtExType.requestFocus(); 
             return;	
           }
         DV = Global.DateValidate(dtpExDate.getText().toString());
         if(DV.length()!=0 & secExDate.isShown())
           {
             Connection.MessageBox(Member.this, DV);
             dtpExDate.requestFocus(); 
             return;	
           }


         String[] RTH = spnRth.getSelectedItem().toString().split("-");
         String[] MS = spnMS.getSelectedItem().toString().split("-");
         String[] Ocp = spnOcp.getSelectedItem().toString().split("-");
         String[] Sp1 = spnSp1.getSelectedItem().toString().split("-");

         if ((RTH[0].equals("02") | RTH[0].equals("04") | RTH[0].equals("07") | RTH[0].equals("10") | RTH[0].equals("11") | RTH[0].equals("15") | RTH[0].equals("17")) & MS[0].equals("30")) {
             Connection.MessageBox(Member.this, "খানা প্রধানের সাথে সম্পর্ক  ২,৪,৭,১০,১১,১৫,১৭ হলে বৈবাহিক অবস্থা অবিবাহিত হতে পারে না.");
             spnRth.requestFocus();
             return;
         }
         else if(txtMSlNo.getText().toString().equalsIgnoreCase(Connection.SelectedSpinnerValue(spnFaNo.getSelectedItem().toString(), "-")))
         {
             Connection.MessageBox(Member.this, "প্রশ্ন ৯. বাবার সিরিয়াল ও এই সদস্যের সিরিয়াল একই হবে না");
             spnFaNo.requestFocus();
             return;
         }
         else if(txtMSlNo.getText().toString().equalsIgnoreCase(Connection.SelectedSpinnerValue(spnMoNo.getSelectedItem().toString(), "-")))
         {
             Connection.MessageBox(Member.this, "প্রশ্ন ৮. মায়ের সিরিয়াল ও এই সদস্যের সিরিয়াল একই হবে না");
             spnMoNo.requestFocus();
             return;
         }
         else if ((RTH[0].equals("04") & Integer.valueOf(txtAgeY.getText().toString().length() == 0 ? "0" : txtAgeY.getText().toString()) < 15))
         {
             Connection.MessageBox(Member.this, "খানা প্রধানের মা/বাবা এর বয়স অবশ্যই ১৫ বছরের বেশী হবে");
             txtAgeY.requestFocus();
             return;
         }
         else if ((RTH[0].equals("02") & Integer.valueOf(txtAgeY.getText().toString().length() == 0 ? "0" : txtAgeY.getText().toString()) < 15))
         {
             Connection.MessageBox(Member.this, "খানা প্রধানের স্বামী অথবা স্ত্রী  বয়স অবশ্যই ১৫ বছরের বেশী হবে");
             txtAgeY.requestFocus();
             return;
         }
         else if ((RTH[0].equals("07") & Integer.valueOf(txtAgeY.getText().toString().length() == 0 ? "0" : txtAgeY.getText().toString()) < 15))
         {
             Connection.MessageBox(Member.this, "খানা প্রধানের দাদা/দাদি/নানা/নানি এর বয়স অবশ্যই ১৫ বছরের বেশী হবে");
             txtAgeY.requestFocus();
             return;
         }
         else if ((RTH[0].equals("10") & Integer.valueOf(txtAgeY.getText().toString().length() == 0 ? "0" : txtAgeY.getText().toString()) < 15))
         {
             Connection.MessageBox(Member.this, "খানা প্রধানের শ্বশুর/শাশুড়ী এর বয়স অবশ্যই ১৫ বছরের বেশী হবে");
             txtAgeY.requestFocus();
             return;
         }
         else if ((RTH[0].equals("01") & Integer.valueOf(txtAgeY.getText().toString().length() == 0 ? "0" : txtAgeY.getText().toString()) < 12))
         {
             Connection.MessageBox(Member.this, "খানা প্রধানের এর বয়স অবশ্যই ১২ বছরের বেশী হবে");
             txtAgeY.requestFocus();
             return;
         }
         else if (!isAgeDifferenceWithParentsValid(txtVill.getText().toString(),txtBari.getText().toString(),txtHH.getText().toString(),
                 Connection.SelectedSpinnerValue(spnFaNo.getSelectedItem().toString(), "-"),
                 Connection.SelectedSpinnerValue(spnMoNo.getSelectedItem().toString(), "-"),
                 txtAgeY.getText().toString())) {
                 Connection.MessageBox(Member.this, "পিতা মাতার সাথে এই সদস্যের বয়স এর পার্থক্য  ঠিক নেই, পিতা আথবা মাতার বয়স দেখুন");
                 txtAgeY.requestFocus();
                 return;
         }
         else if (Connection.SelectedSpinnerValue(spnRth.getSelectedItem().toString(), "-").equals("01") & !isHhHeadValid(txtVill.getText().toString(), txtBari.getText().toString(),txtHH.getText().toString(), txtMSlNo.getText().toString())) {
             Connection.MessageBox(Member.this, "এক খানায় ২ জন খানা প্রধান হতে পারেনা");
             txtName.requestFocus();
             return;
         }

         else if(txtPNo.getText().length()!=11)
         {
             Connection.MessageBox(Member.this, "PNo অবশ্যই ১১ ডিজিট হতে হবে।");
             txtPNo.requestFocus();
             return;
         }
         if(Connection.SelectedSpinnerValue(spnFaNo.getSelectedItem().toString(), "-").equalsIgnoreCase("00") ||(Connection.SelectedSpinnerValue(spnMoNo.getSelectedItem().toString(), "-").equalsIgnoreCase("00")))
         {

         }
         else
         {
             /*if (Connection.SelectedSpinnerValue(spnFaNo.getSelectedItem().toString(), "-").equalsIgnoreCase(Connection.SelectedSpinnerValue(spnMoNo.getSelectedItem().toString(), "-")) & spnMoNo.isShown()) {
                 Connection.MessageBox(Member.this, "প্রশ্ন ৮ এবং ৯. পিতার সিরিয়াল ও মাতার সিরিয়াল একই হবে না");
                 spnFaNo.requestFocus();
                 return;
             }*/
         }
//         String MSstatus= C.ReturnSingleValue("Select MS from Member where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH +"' and MSlNo='"+ Sp1 + "'");
//
//         if ((MSstatus.equals("30")))
//         {
//             Connection.MessageBox(Member.this, "স্বামী অথবা স্ত্রী "+ spnSp1.getSelectedItem().toString().split("-") +" বৈবাহিক অবস্থা অবিবাহিত হতে পারে না.");
//             spnRth.requestFocus();
//             return;
//         }

         if (txtMSlNo.getText().toString().equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp1.getSelectedItem().toString(), "-"))& spnSp1.isShown()) {
             Connection.MessageBox(Member.this, "প্রশ্ন ১৩) ১ম স্বামী/স্ত্রী  সিরিয়াল এবং এই সদস্যের সিরিয়াল একই হবে না");
             spnSp1.requestFocus();
             return;
         }
         else if (txtMSlNo.getText().toString().equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp2.getSelectedItem().toString(), "-"))& spnSp2.isShown()) {
             Connection.MessageBox(Member.this, "প্রশ্ন ১৪) ২য় স্বামী/স্ত্রী  এবং এই সদস্যের সিরিয়াল একই হবে না");
             spnSp2.requestFocus();
             return;
         }
         else if (txtMSlNo.getText().toString().equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp3.getSelectedItem().toString(), "-"))& spnSp3.isShown()) {
             Connection.MessageBox(Member.this, "প্রশ্ন ১৫) ৩য় স্বামী/স্ত্রী  এবং এই সদস্যের সিরিয়াল একই হবে না");
             spnSp3.requestFocus();
             return;
         }
         else if (txtMSlNo.getText().toString().equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp4.getSelectedItem().toString(), "-"))& spnSp4.isShown()) {
             Connection.MessageBox(Member.this, "প্রশ্ন ১৬) ৪র্থ স্বামী/স্ত্রী  এবং এই সদস্যের সিরিয়াল একই হবে না");
             spnSp4.requestFocus();
             return;
         }


         //17 Mar 2017
         else if (!Connection.SelectedSpinnerValue(spnSp1.getSelectedItem().toString(), "-").equals("00") & Connection.SelectedSpinnerValue(spnFaNo.getSelectedItem().toString(), "-").equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp1.getSelectedItem().toString(), "-"))& spnSp1.isShown()) {
                 Connection.MessageBox(Member.this, "প্রশ্ন ৯) বাবার সিরিয়াল এবং ১ম স্বামী/স্ত্রী  সিরিয়াল একই হবে না");
                 spnFaNo.requestFocus();
                 return;
         }
         else if (!Connection.SelectedSpinnerValue(spnSp1.getSelectedItem().toString(), "-").equals("00") & Connection.SelectedSpinnerValue(spnMoNo.getSelectedItem().toString(), "-").equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp1.getSelectedItem().toString(), "-"))& spnSp1.isShown()) {
                 Connection.MessageBox(Member.this, "প্রশ্ন ৮) মায়ের সিরিয়াল এবং ১ম স্বামী/স্ত্রী  সিরিয়াল একই হবে না");
                 spnSp1.requestFocus();
                 return;
         }


//         else if (Connection.SelectedSpinnerValue(spnFaNo.getSelectedItem().toString(), "-").equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp2.getSelectedItem().toString(), "-"))& spnSp2.isShown()) {
//             Connection.MessageBox(Member.this, "পিতার সিরিয়াল ও সদস্যের স্বামী অথবা স্ত্রী  সিরিয়াল একই হবে না");
//             spnFaNo.requestFocus();
//             return;
//         }
//         else if (Connection.SelectedSpinnerValue(spnFaNo.getSelectedItem().toString(), "-").equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp3.getSelectedItem().toString(), "-"))& spnSp3.isShown()) {
//             Connection.MessageBox(Member.this, "পিতার সিরিয়াল ও সদস্যের স্বামী অথবা স্ত্রী  সিরিয়াল একই হবে না");
//             spnFaNo.requestFocus();
//             return;
//         }
//         else if (Connection.SelectedSpinnerValue(spnFaNo.getSelectedItem().toString(), "-").equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp4.getSelectedItem().toString(), "-"))& spnSp4.isShown()) {
//             Connection.MessageBox(Member.this, "পিতার সিরিয়াল ও সদস্যের স্বামী অথবা স্ত্রী  সিরিয়াল একই হবে না");
//             spnFaNo.requestFocus();
//             return;
//         }

//         else if (Connection.SelectedSpinnerValue(spnMoNo.getSelectedItem().toString(), "-").equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp2.getSelectedItem().toString(), "-"))& spnSp2.isShown()) {
//             Connection.MessageBox(Member.this, "মাতার সিরিয়াল ও সদস্যের স্বামী অথবা স্ত্রী  সিরিয়াল একই হবে না");
//             spnSp2.requestFocus();
//             return;
//         }
//         else if (Connection.SelectedSpinnerValue(spnMoNo.getSelectedItem().toString(), "-").equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp3.getSelectedItem().toString(), "-"))& spnSp3.isShown()) {
//             Connection.MessageBox(Member.this, "মাতার সিরিয়াল ও সদস্যের স্বামী অথবা স্ত্রী  সিরিয়াল একই হবে না");
//             spnSp3.requestFocus();
//             return;
//         }
//         else if (Connection.SelectedSpinnerValue(spnMoNo.getSelectedItem().toString(), "-").equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp4.getSelectedItem().toString(), "-"))& spnSp4.isShown()) {
//             Connection.MessageBox(Member.this, "মাতার সিরিয়াল ও সদস্যের স্বামী অথবা স্ত্রী  সিরিয়াল একই হবে না");
//             spnSp4.requestFocus();
//             return;
//         }
         else if (Connection.SelectedSpinnerValue(spnSp1.getSelectedItem().toString(), "-").equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp2.getSelectedItem().toString(), "-"))& spnSp2.isShown()) {
             Connection.MessageBox(Member.this, "প্রশ্ন ১৩) ১ম স্বামী/স্ত্রী সিরিয়াল নং  এবং প্রশ্ন ১৪) ২য় স্বামী/স্ত্রী সিরিয়াল নং একই হবে না");
             spnSp1.requestFocus();
             return;
         }
         else if (Connection.SelectedSpinnerValue(spnSp2.getSelectedItem().toString(), "-").equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp3.getSelectedItem().toString(), "-"))& spnSp3.isShown()) {
             Connection.MessageBox(Member.this, "প্রশ্ন ১৪) ২য় স্বামী/স্ত্রী সিরিয়াল নং  এবং প্রশ্ন ১৫) ৩য় স্বামী/স্ত্রী সিরিয়াল নং একই হবে না");
             spnSp1.requestFocus();
             return;
         }
         else if (Connection.SelectedSpinnerValue(spnSp3.getSelectedItem().toString(), "-").equalsIgnoreCase(Connection.SelectedSpinnerValue(spnSp4.getSelectedItem().toString(), "-"))& spnSp4.isShown()) {
             Connection.MessageBox(Member.this, "প্রশ্ন ১৫) ৩য় স্বামী/স্ত্রী সিরিয়াল নং  এবং প্রশ্ন ১৬) ৪র্থ স্বামী/স্ত্রী সিরিয়াল নং একই হবে না");
             spnSp1.requestFocus();
             return;
         }

//         if(Global.DateDifferenceDays(dtpVDate.getText().toString(), dtpBDate.getText().toString())<0)
//         {
//             Connection.MessageBox(Member.this, "জন্ম তারিখ ভিজিটের তারিখের আগে হতে হবে।");
//             dtpBDate.requestFocus();
//             return;
//         }



         dtpEnDate.setText(Global.DateConvertDMY(C.ReturnSingleValue("select VDate from Visits where Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH + "'")));

         String SQL = "";
         RadioButton rb;

         Member_DataModel objSave = new Member_DataModel();
         objSave.setVill(txtVill.getText().toString());
         objSave.setBari(txtBari.getText().toString());
         objSave.setHH(txtHH.getText().toString());
         objSave.setMSlNo(txtMSlNo.getText().toString());
         objSave.setPNo(txtPNo.getText().toString());
         objSave.setName(txtName.getText().toString());
         objSave.setRth((spnRth.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnRth.getSelectedItem().toString(), "-")));
         String[] d_rdogrpSex = new String[] {"1","2"};
         objSave.setSex("");

         for (int i = 0; i < rdogrpSex.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpSex.getChildAt(i);
             if (rb.isChecked()) objSave.setSex(d_rdogrpSex[i]);
         }

         objSave.setBDate(dtpBDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpBDate.getText().toString()) : dtpBDate.getText().toString());
         objSave.setAgeY(txtAgeY.getText().toString());
         objSave.setMoNo((spnMoNo.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnMoNo.getSelectedItem().toString(), "-")));
         objSave.setFaNo((spnFaNo.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnFaNo.getSelectedItem().toString(), "-")));
         objSave.setEdu((spnEdu.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnEdu.getSelectedItem().toString(), "-")));
         objSave.setMS((spnMS.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnMS.getSelectedItem().toString(), "-")));
         objSave.setOcp((spnOcp.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnOcp.getSelectedItem().toString(), "-")));
         objSave.setSp1((spnSp1.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnSp1.getSelectedItem().toString(), "-")));
         objSave.setSp2((spnSp2.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnSp2.getSelectedItem().toString(), "-")));
         objSave.setSp3((spnSp3.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnSp3.getSelectedItem().toString(), "-")));
         objSave.setSp4((spnSp4.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnSp4.getSelectedItem().toString(), "-")));
         objSave.setEnType("20");
         objSave.setEnDate(dtpEnDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpEnDate.getText().toString()) : dtpEnDate.getText().toString());
         objSave.setExType("");
         objSave.setExDate("");
         objSave.setEnDt(Global.DateTimeNowYMDHMS());
         objSave.setStartTime(STARTTIME);
         objSave.setEndTime(g.CurrentTime24());
         objSave.setDeviceID(DEVICEID);
         objSave.setEntryUser(ENTRYUSER); //from data entry user list

         objSave.setNeedReview(chkNeedReview.isChecked()?"1":"2");
         //objSave.setLat(Double.toString(currentLatitude));
         //objSave.setLon(Double.toString(currentLongitude));

         String status = objSave.SaveUpdateData(this);
         if(status.length()==0)
         {
             /*Intent returnIntent = new Intent();
             returnIntent.putExtra("res", "");
             setResult(Activity.RESULT_OK, returnIntent);
             Bundle IDBundle = new Bundle();
             IDBundle.putString("Vill", txtVill.getText().toString());
             IDBundle.putString("Bari", txtBari.getText().toString());
             IDBundle.putString("hh", txtHH.getText().toString());
             IDBundle.putString("MSlNo", txtMSlNo.getText().toString());

             g.setVillageCode(txtVill.getText().toString());
             g.setBariCode(txtBari.getText().toString());
             g.setHouseholdNo(txtHH.getText().toString());
             g.setmemSlNo(txtMSlNo.getText().toString());
             finish();*/

             Intent returnIntent = new Intent();
             returnIntent.putExtra("res", "");
             setResult(Activity.RESULT_OK, returnIntent);

             Connection.MessageBox(Member.this, "Saved Successfully");
         }
         else{
             Connection.MessageBox(Member.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(Member.this, e.getMessage());
         return;
     }
 }

 private void DataSearch(String Vill, String Bari, String HH, String MSlNo)
     {
       try
        {
     
           RadioButton rb;
           Member_DataModel d = new Member_DataModel();
           String SQL = "Select * from "+ TableName +"  Where Vill='"+ Vill +"' and Bari='"+ Bari +"' and HH='"+ HH +"' and MSlNo='"+ MSlNo +"'";
           List<Member_DataModel> data = d.SelectAll(this, SQL);
           for(Member_DataModel item : data){
             txtVill.setText(item.getVill());
             txtBari.setText(item.getBari());
             txtHH.setText(item.getHH());
             txtMSlNo.setText(item.getMSlNo());
            // txtPNo.setText(item.getPNo());
             txtName.setText(item.getName());
             spnRth.setSelection(Global.SpinnerItemPositionAnyLength(spnRth, item.getRth()));
             String[] d_rdogrpSex = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpSex.length; i++)
             {
                 if (item.getSex().equals(String.valueOf(d_rdogrpSex[i])))
                 {
                     rb = (RadioButton)rdogrpSex.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             dtpBDate.setText(item.getBDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getBDate()));
             txtAgeY.setText(item.getAgeY());
             spnMoNo.setSelection(Global.SpinnerItemPositionAnyLength(spnMoNo, item.getMoNo()));
             spnFaNo.setSelection(Global.SpinnerItemPositionAnyLength(spnFaNo, item.getFaNo()));
             spnEdu.setSelection(Global.SpinnerItemPositionAnyLength(spnEdu, item.getEdu()));
             spnMS.setSelection(Global.SpinnerItemPositionAnyLength(spnMS, item.getMS()));
             spnOcp.setSelection(Global.SpinnerItemPositionAnyLength(spnOcp, item.getOcp()));
             spnSp1.setSelection(Global.SpinnerItemPositionAnyLength(spnSp1, item.getSp1()));
             spnSp2.setSelection(Global.SpinnerItemPositionAnyLength(spnSp2, item.getSp2()));
             spnSp3.setSelection(Global.SpinnerItemPositionAnyLength(spnSp3, item.getSp3()));
             spnSp4.setSelection(Global.SpinnerItemPositionAnyLength(spnSp4, item.getSp4()));
             txtEnType.setText(item.getEnType());
             dtpEnDate.setText(item.getEnDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getEnDate()));
             txtExType.setText(item.getExType());
             dtpExDate.setText(item.getExDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getExDate()));
               if(item.getNeedReview().equals("1")) chkNeedReview.setChecked(true); else chkNeedReview.setChecked(false);
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Member.this, e.getMessage());
            return;
        }
     }

     private boolean isAgeDifferenceWithParentsValid(String VILL, String BARI,String HH, String fatherSL, String motherSL, String Age) {
         // Cursor fCursor,mCursor;
         String fAge = C.ReturnSingleValue("select AgeY from MEMBER where Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH + "' and MSlNo='" + fatherSL + "'");
         String mAge = C.ReturnSingleValue("select AgeY from MEMBER where Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH + "' and MSlNo='" + motherSL + "'");

         if (fAge.isEmpty()) {
             fAge = "0";

         }
         if (mAge.isEmpty()) {
             mAge = "0";
         }
         if (fAge.equals("0") | mAge.equals("0")) {
             return true;
         }

         int cAge = Integer.valueOf(Age);
         return !(((Integer.valueOf(fAge) - cAge) < 12) | ((Integer.valueOf(mAge) - cAge) < 12));
     }
     private boolean isHhHeadValid(String VILL, String BARI,String HH, String MSLNO) {
         Cursor cursor;
         cursor = C.ReadData("select * from Member where Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH + "' and Rth ='01' and MSlNo !='" + MSLNO + "'");
         if (cursor.getCount() > 0) {

             cursor.close();
             return false;
         } else {
             cursor.close();
             return true;
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

 private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
      mYear = year; mMonth = monthOfYear+1; mDay = dayOfMonth;
      EditText dtpDate;


              dtpDate = (EditText)findViewById(R.id.dtpBDate);
             if (VariableID.equals("btnBDate"))
              {
                  dtpDate = (EditText)findViewById(R.id.dtpBDate);
              }
             else if (VariableID.equals("btnEnDate"))
              {
                  dtpDate = (EditText)findViewById(R.id.dtpEnDate);
              }
             else if (VariableID.equals("btnExDate"))
              {
                  dtpDate = (EditText)findViewById(R.id.dtpExDate);
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
     private String MemNo(String Vill,String Bari,String HH)
     {
         String M = C.ReturnSingleValue("Select cast(ifnull(max(MSlNo),0)+1 as varchar(2))MemNo from Member where Vill='"+ Vill +"' and Bari='"+ Bari +"' and HH='"+ HH +"'");
         M = Global.Right("0"+M,2);
         return M;
     }
}
