
 package org.icddrb.champsdss;


 //Android Manifest Code
 //<activity android:name=".SES" android:label="SES" />
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import android.app.*;
 import android.app.AlertDialog;
 import android.app.DatePickerDialog;
 import android.app.Dialog;
 import android.app.TimePickerDialog;
 import android.content.Context;
 import android.content.DialogInterface;
 import android.content.Intent;
 import android.database.Cursor;
 import android.graphics.Color;
 import android.location.Location;
 import android.location.LocationListener;
 import android.location.LocationManager;
 import android.net.Uri;
 import android.provider.Settings;
 import android.view.KeyEvent;
 import android.os.Bundle;
 import android.view.Menu;
 import android.view.MenuInflater;
 import android.view.MenuItem;
 import android.view.View;
 import android.view.MotionEvent;
 import android.view.View.OnFocusChangeListener;
 import android.view.ViewGroup;
 import android.view.LayoutInflater;
 import android.widget.AdapterView;
 import android.widget.Button;
 import android.widget.CheckBox;
 import android.widget.DatePicker;
 import android.widget.EditText;
 import android.widget.ImageButton;
 import android.widget.LinearLayout;
 import android.widget.RadioButton;
 import android.widget.RadioGroup;
 import android.widget.ListView;
 import android.widget.SimpleAdapter;
 import android.widget.BaseAdapter;
 import android.widget.Spinner;
 import android.widget.TextView;
 import android.widget.TimePicker;
 import android.widget.ArrayAdapter;
 import android.widget.CompoundButton;
 import Common.*;

 public class SES extends Activity {
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
         LinearLayout seclbl01;
         View linelbl01;
         LinearLayout seclbl02;
         View linelbl02;
         LinearLayout seclbl03;
         View linelbl03;
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
         LinearLayout secSESNo;
         View lineSESNo;
         TextView VlblSESNo;
         Spinner spnSESNo;
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
         LinearLayout secRnd;
         View lineRnd;
         TextView VlblRnd;
         EditText txtRnd;
         LinearLayout seclbl015;
         View linelbl015;
         LinearLayout secWSDrink;
         View lineWSDrink;
         TextView VlblWSDrink;
         Spinner spnWSDrink;
         LinearLayout secWSDrinkOth;
         View lineWSDrinkOth;
         TextView VlblWSDrinkOth;
         EditText txtWSDrinkOth;
         LinearLayout secWSCook;
         View lineWSCook;
         TextView VlblWSCook;
         Spinner spnWSCook;
         LinearLayout secWSCookOth;
         View lineWSCookOth;
         TextView VlblWSCookOth;
         EditText txtWSCookOth;
         LinearLayout secWSWash;
         View lineWSWash;
         TextView VlblWSWash;
         Spinner spnWSWash;
         LinearLayout secWSWashOth;
         View lineWSWashOth;
         TextView VlblWSWashOth;
         EditText txtWSWashOth;
         LinearLayout secLatrine;
         View lineLatrine;
         TextView VlblLatrine;
         Spinner spnLatrine;
         LinearLayout secLatrineOth;
         View lineLatrineOth;
         TextView VlblLatrineOth;
         EditText txtLatrineOth;
         LinearLayout seclbl017;
         View linelbl017;
         LinearLayout secElectricity;
         View lineElectricity;
         TextView VlblElectricity;
         RadioGroup rdogrpElectricity;
         
         RadioButton rdoElectricity1;
         RadioButton rdoElectricity2;
         LinearLayout secRadio;
         View lineRadio;
         TextView VlblRadio;
         RadioGroup rdogrpRadio;
         
         RadioButton rdoRadio1;
         RadioButton rdoRadio2;
         LinearLayout secTV;
         View lineTV;
         TextView VlblTV;
         RadioGroup rdogrpTV;
         
         RadioButton rdoTV1;
         RadioButton rdoTV2;
         LinearLayout secMobile;
         View lineMobile;
         TextView VlblMobile;
         RadioGroup rdogrpMobile;
         
         RadioButton rdoMobile1;
         RadioButton rdoMobile2;
         LinearLayout secTelephone;
         View lineTelephone;
         TextView VlblTelephone;
         RadioGroup rdogrpTelephone;
         
         RadioButton rdoTelephone1;
         RadioButton rdoTelephone2;
         LinearLayout secRefrige;
         View lineRefrige;
         TextView VlblRefrige;
         RadioGroup rdogrpRefrige;
         
         RadioButton rdoRefrige1;
         RadioButton rdoRefrige2;
         LinearLayout secWatch;
         View lineWatch;
         TextView VlblWatch;
         RadioGroup rdogrpWatch;
         
         RadioButton rdoWatch1;
         RadioButton rdoWatch2;
         LinearLayout secElecFan;
         View lineElecFan;
         TextView VlblElecFan;
         RadioGroup rdogrpElecFan;
         
         RadioButton rdoElecFan1;
         RadioButton rdoElecFan2;
         LinearLayout secRickVan;
         View lineRickVan;
         TextView VlblRickVan;
         RadioGroup rdogrpRickVan;
         
         RadioButton rdoRickVan1;
         RadioButton rdoRickVan2;
         LinearLayout secBicycle;
         View lineBicycle;
         TextView VlblBicycle;
         RadioGroup rdogrpBicycle;
         
         RadioButton rdoBicycle1;
         RadioButton rdoBicycle2;
         LinearLayout secMotCycle;
         View lineMotCycle;
         TextView VlblMotCycle;
         RadioGroup rdogrpMotCycle;
         
         RadioButton rdoMotCycle1;
         RadioButton rdoMotCycle2;
         LinearLayout secComputer;
         View lineComputer;
         TextView VlblComputer;
         RadioGroup rdogrpComputer;
         
         RadioButton rdoComputer1;
         RadioButton rdoComputer2;
         LinearLayout seclbl018;
         View linelbl018;
         LinearLayout seclbl018a;
         View linelbl018a;

         LinearLayout seclbl019;
         View linelbl019;

         LinearLayout secBuffalo;
         View lineBuffalo;
         TextView VlblBuffalo;
         EditText txtBuffalo;
         LinearLayout secBull;
         View lineBull;
         TextView VlblBull;
         EditText txtBull;
         LinearLayout secGoat;
         View lineGoat;
         TextView VlblGoat;
         EditText txtGoat;
         LinearLayout secChicken;
         View lineChicken;
         TextView VlblChicken;
         EditText txtChicken;
         LinearLayout secPigeon;
         View linePigeon;
         TextView VlblPigeon;
         EditText txtPigeon;
         LinearLayout secRoof;
         View lineRoof;
         TextView VlblRoof;
         Spinner spnRoof;
         LinearLayout secRoofOth;
         View lineRoofOth;
         TextView VlblRoofOth;
         EditText txtRoofOth;
         LinearLayout secWall;
         View lineWall;
         TextView VlblWall;
         Spinner spnWall;
         LinearLayout secWallOth;
         View lineWallOth;
         TextView VlblWallOth;
         EditText txtWallOth;
         LinearLayout secFloor;
         View lineFloor;
         TextView VlblFloor;
         Spinner spnFloor;
         LinearLayout secFloorOth;
         View lineFloorOth;
         TextView VlblFloorOth;
         EditText txtFloorOth;
         LinearLayout secHomestead;
         View lineHomestead;
         TextView VlblHomestead;
         RadioGroup rdogrpHomestead;
         
         RadioButton rdoHomestead1;
         RadioButton rdoHomestead2;
         LinearLayout secHomesteadOth;
         View lineHomesteadOth;
         TextView VlblHomesteadOth;
         RadioGroup rdogrpHomesteadOth;
         
         RadioButton rdoHomesteadOth1;
         RadioButton rdoHomesteadOth2;
         LinearLayout secOthLand;
         View lineOthLand;
         TextView VlblOthLand;
         RadioGroup rdogrpOthLand;
         
         RadioButton rdoOthLand1;
         RadioButton rdoOthLand2;

        static String TableName;

        static String STARTTIME = "";
        static String DEVICEID  = "";
        static String ENTRYUSER = "";

        Bundle IDbundle;
        static String VILL = "";
        static String BARI = "";
        static String HH = "";
        static String SESNO = "";

 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.ses);
         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = g.getDeviceNo();
         ENTRYUSER = g.getUserId();

         IDbundle = getIntent().getExtras();
         VILL = IDbundle.getString("Vill");
         BARI = IDbundle.getString("Bari");
         HH = IDbundle.getString("HH");
         SESNO = IDbundle.getString("SESNo");

         TableName = "SES";

         //turnGPSOn();

         //GPS Location
         //FindLocation();
         // Double.toString(currentLatitude);
         // Double.toString(currentLongitude);
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(SES.this);
                 adb.setTitle("Close");
                 adb.setMessage("আপনি কি এই ফরম থেকে বের হতে চান [হ্যাঁ/না]?");
                 adb.setNegativeButton("না", null);
                 adb.setPositiveButton("হ্যাঁ", new AlertDialog.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                         finish();
                     }});
                 adb.show();
             }});
         seclbl01=(LinearLayout)findViewById(R.id.seclbl01);
         linelbl01=(View)findViewById(R.id.linelbl01);
         seclbl02=(LinearLayout)findViewById(R.id.seclbl02);
         linelbl02=(View)findViewById(R.id.linelbl02);
         seclbl03=(LinearLayout)findViewById(R.id.seclbl03);
         linelbl03=(View)findViewById(R.id.linelbl03);
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

         secSESNo=(LinearLayout)findViewById(R.id.secSESNo);
         lineSESNo=(View)findViewById(R.id.lineSESNo);
         VlblSESNo=(TextView) findViewById(R.id.VlblSESNo);
         spnSESNo=(Spinner) findViewById(R.id.spnSESNo);

         List<String> listSESNo = new ArrayList<String>();
         listSESNo.add("1");

         ArrayAdapter<String> adptrSESno= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSESNo);
         spnSESNo.setAdapter(adptrSESno);
         SESNO=spnSESNo.getSelectedItem().toString();

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
         listVStatus.add("1-ইন্টারভিউ সম্পন্ন");
         listVStatus.add("2-তথ্য  দানে অসন্মতি");
         //listVStatus.add("3-সদস্য অনুপস্থিত");
         listVStatus.add("9-অন্যান্য");
         ArrayAdapter<String> adptrVStatus= new ArrayAdapter<String>(this, R.layout.multiline_spinner_dropdown_item, listVStatus);
         spnVStatus.setAdapter(adptrVStatus);

         spnVStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             if (spnVStatus.getSelectedItem().toString().length() == 0) return;
             String spnData = Connection.SelectedSpinnerValue(spnVStatus.getSelectedItem().toString(),"-");
                 if(spnData.equalsIgnoreCase("9"))
                 {   secVStatusOth.setVisibility(View.VISIBLE);
                     lineVStatusOth.setVisibility(View.VISIBLE);
                     seclbl015.setVisibility(View.VISIBLE);
                     linelbl015.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secVStatusOth.setVisibility(View.GONE);
                     lineVStatusOth.setVisibility(View.GONE);
                     txtVStatusOth.setText("");
                     seclbl015.setVisibility(View.GONE);
                     linelbl015.setVisibility(View.GONE);
                 }
                 if(!spnData.equalsIgnoreCase("1"))
                 {
                     lineRnd.setVisibility(View.GONE);
                     txtRnd.setText("");
                     seclbl015.setVisibility(View.GONE);
                     linelbl015.setVisibility(View.GONE);
                     secWSDrink.setVisibility(View.GONE);
                     lineWSDrink.setVisibility(View.GONE);
                     spnWSDrink.setSelection(0);
                     secWSDrinkOth.setVisibility(View.GONE);
                     lineWSDrinkOth.setVisibility(View.GONE);
                     txtWSDrinkOth.setText("");
                     secWSCook.setVisibility(View.GONE);
                     lineWSCook.setVisibility(View.GONE);
                     spnWSCook.setSelection(0);
                     secWSCookOth.setVisibility(View.GONE);
                     lineWSCookOth.setVisibility(View.GONE);
                     txtWSCookOth.setText("");
                     secWSWash.setVisibility(View.GONE);
                     lineWSWash.setVisibility(View.GONE);
                     spnWSWash.setSelection(0);
                     secWSWashOth.setVisibility(View.GONE);
                     lineWSWashOth.setVisibility(View.GONE);
                     txtWSWashOth.setText("");
                     secLatrine.setVisibility(View.GONE);
                     lineLatrine.setVisibility(View.GONE);
                     spnLatrine.setSelection(0);
                     secLatrineOth.setVisibility(View.GONE);
                     lineLatrineOth.setVisibility(View.GONE);
                     txtLatrineOth.setText("");
                     seclbl017.setVisibility(View.GONE);
                     linelbl017.setVisibility(View.GONE);
                     secElectricity.setVisibility(View.GONE);
                     lineElectricity.setVisibility(View.GONE);
                     rdogrpElectricity.clearCheck();
                     secRadio.setVisibility(View.GONE);
                     lineRadio.setVisibility(View.GONE);
                     rdogrpRadio.clearCheck();
                     secTV.setVisibility(View.GONE);
                     lineTV.setVisibility(View.GONE);
                     rdogrpTV.clearCheck();
                     secMobile.setVisibility(View.GONE);
                     lineMobile.setVisibility(View.GONE);
                     rdogrpMobile.clearCheck();
                     secTelephone.setVisibility(View.GONE);
                     lineTelephone.setVisibility(View.GONE);
                     rdogrpTelephone.clearCheck();
                     secRefrige.setVisibility(View.GONE);
                     lineRefrige.setVisibility(View.GONE);
                     rdogrpRefrige.clearCheck();
                     secWatch.setVisibility(View.GONE);
                     lineWatch.setVisibility(View.GONE);
                     rdogrpWatch.clearCheck();
                     secElecFan.setVisibility(View.GONE);
                     lineElecFan.setVisibility(View.GONE);
                     rdogrpElecFan.clearCheck();
                     secRickVan.setVisibility(View.GONE);
                     lineRickVan.setVisibility(View.GONE);
                     rdogrpRickVan.clearCheck();
                     secBicycle.setVisibility(View.GONE);
                     lineBicycle.setVisibility(View.GONE);
                     rdogrpBicycle.clearCheck();
                     secMotCycle.setVisibility(View.GONE);
                     lineMotCycle.setVisibility(View.GONE);
                     rdogrpMotCycle.clearCheck();
                     secComputer.setVisibility(View.GONE);
                     lineComputer.setVisibility(View.GONE);
                     rdogrpComputer.clearCheck();
                     seclbl018.setVisibility(View.GONE);
                     linelbl018.setVisibility(View.GONE);
                     seclbl018a.setVisibility(View.GONE);
                     linelbl018a.setVisibility(View.GONE);
                     secBuffalo.setVisibility(View.GONE);
                     lineBuffalo.setVisibility(View.GONE);
                     txtBuffalo.setText("");
                     secBull.setVisibility(View.GONE);
                     lineBull.setVisibility(View.GONE);
                     txtBull.setText("");
                     secGoat.setVisibility(View.GONE);
                     lineGoat.setVisibility(View.GONE);
                     txtGoat.setText("");
                     secChicken.setVisibility(View.GONE);
                     lineChicken.setVisibility(View.GONE);
                     txtChicken.setText("");
                     secPigeon.setVisibility(View.GONE);
                     linePigeon.setVisibility(View.GONE);
                     txtPigeon.setText("");

                     seclbl019.setVisibility(View.GONE);
                     linelbl019.setVisibility(View.GONE);
                     seclbl019.setVisibility(View.GONE);
                     linelbl019.setVisibility(View.GONE);

                     secRoof.setVisibility(View.GONE);
                     lineRoof.setVisibility(View.GONE);
                     spnRoof.setSelection(0);

                     secRoofOth.setVisibility(View.GONE);
                     lineRoofOth.setVisibility(View.GONE);
                     txtRoofOth.setText("");

                     secWall.setVisibility(View.GONE);
                     lineWall.setVisibility(View.GONE);
                     spnWall.setSelection(0);

                     secWallOth.setVisibility(View.GONE);
                     lineWallOth.setVisibility(View.GONE);
                     txtWallOth.setText("");
                     secFloor.setVisibility(View.GONE);
                     lineFloor.setVisibility(View.GONE);
                     spnFloor.setSelection(0);
                     secFloorOth.setVisibility(View.GONE);
                     lineFloorOth.setVisibility(View.GONE);
                     txtFloorOth.setText("");
                     secHomestead.setVisibility(View.GONE);
                     lineHomestead.setVisibility(View.GONE);
                     rdogrpHomestead.clearCheck();

                     secHomesteadOth.setVisibility(View.GONE);
                     lineHomesteadOth.setVisibility(View.GONE);
                     rdogrpHomesteadOth.clearCheck();

                     secOthLand.setVisibility(View.GONE);
                     lineOthLand.setVisibility(View.GONE);
                     rdogrpOthLand.clearCheck();
                 }

                 else
                 {
                     seclbl015.setVisibility(View.VISIBLE);
                     linelbl015.setVisibility(View.VISIBLE);
                     secWSDrink.setVisibility(View.VISIBLE);
                     lineWSDrink.setVisibility(View.VISIBLE);
//                     secWSDrinkOth.setVisibility(View.VISIBLE);
//                     lineWSDrinkOth.setVisibility(View.VISIBLE);
                     secWSCook.setVisibility(View.VISIBLE);
                     lineWSCook.setVisibility(View.VISIBLE);
//                     secWSCookOth.setVisibility(View.VISIBLE);
//                     lineWSCookOth.setVisibility(View.VISIBLE);
                     secWSWash.setVisibility(View.VISIBLE);
                     lineWSWash.setVisibility(View.VISIBLE);
//                     secWSWashOth.setVisibility(View.VISIBLE);
//                     lineWSWashOth.setVisibility(View.VISIBLE);
                     secLatrine.setVisibility(View.VISIBLE);
                     lineLatrine.setVisibility(View.VISIBLE);
//                     secLatrineOth.setVisibility(View.VISIBLE);
//                     lineLatrineOth.setVisibility(View.VISIBLE);
                     seclbl017.setVisibility(View.VISIBLE);
                     linelbl017.setVisibility(View.VISIBLE);
                     secElectricity.setVisibility(View.VISIBLE);
                     lineElectricity.setVisibility(View.VISIBLE);
                     secRadio.setVisibility(View.VISIBLE);
                     lineRadio.setVisibility(View.VISIBLE);
                     secTV.setVisibility(View.VISIBLE);
                     lineTV.setVisibility(View.VISIBLE);
                     secMobile.setVisibility(View.VISIBLE);
                     lineMobile.setVisibility(View.VISIBLE);
                     secTelephone.setVisibility(View.VISIBLE);
                     lineTelephone.setVisibility(View.VISIBLE);
                     secRefrige.setVisibility(View.VISIBLE);
                     lineRefrige.setVisibility(View.VISIBLE);
                     secWatch.setVisibility(View.VISIBLE);
                     lineWatch.setVisibility(View.VISIBLE);
                     secElecFan.setVisibility(View.VISIBLE);
                     lineElecFan.setVisibility(View.VISIBLE);
                     secRickVan.setVisibility(View.VISIBLE);
                     lineRickVan.setVisibility(View.VISIBLE);
                     secBicycle.setVisibility(View.VISIBLE);
                     lineBicycle.setVisibility(View.VISIBLE);
                     secMotCycle.setVisibility(View.VISIBLE);
                     lineMotCycle.setVisibility(View.VISIBLE);
                     secComputer.setVisibility(View.VISIBLE);
                     lineComputer.setVisibility(View.VISIBLE);
                     seclbl018.setVisibility(View.VISIBLE);
                     linelbl018.setVisibility(View.VISIBLE);
                     seclbl018a.setVisibility(View.VISIBLE);
                     linelbl018a.setVisibility(View.VISIBLE);
                     seclbl019.setVisibility(View.VISIBLE);
                     linelbl019.setVisibility(View.VISIBLE);
                     seclbl019.setVisibility(View.VISIBLE);
                     linelbl019.setVisibility(View.VISIBLE);
                     secBuffalo.setVisibility(View.VISIBLE);
                     lineBuffalo.setVisibility(View.VISIBLE);
                     secBull.setVisibility(View.VISIBLE);
                     lineBull.setVisibility(View.VISIBLE);
                     secGoat.setVisibility(View.VISIBLE);
                     lineGoat.setVisibility(View.VISIBLE);
                     secChicken.setVisibility(View.VISIBLE);
                     lineChicken.setVisibility(View.VISIBLE);
                     secPigeon.setVisibility(View.VISIBLE);
                     linePigeon.setVisibility(View.VISIBLE);
                     secRoof.setVisibility(View.VISIBLE);
                     lineRoof.setVisibility(View.VISIBLE);
//                     secRoofOth.setVisibility(View.VISIBLE);
//                     lineRoofOth.setVisibility(View.VISIBLE);

                     secWall.setVisibility(View.VISIBLE);
                     lineWall.setVisibility(View.VISIBLE);
//                     secWallOth.setVisibility(View.VISIBLE);
//                     lineWallOth.setVisibility(View.VISIBLE);
                     secFloor.setVisibility(View.VISIBLE);
                     lineFloor.setVisibility(View.VISIBLE);
                     secHomestead.setVisibility(View.VISIBLE);
                     lineHomestead.setVisibility(View.VISIBLE);
//                     secHomesteadOth.setVisibility(View.VISIBLE);
//                     lineHomesteadOth.setVisibility(View.VISIBLE);
                     secOthLand.setVisibility(View.VISIBLE);
                     lineOthLand.setVisibility(View.VISIBLE);

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
         secRnd=(LinearLayout)findViewById(R.id.secRnd);
         lineRnd=(View)findViewById(R.id.lineRnd);
         VlblRnd=(TextView) findViewById(R.id.VlblRnd);
         txtRnd=(EditText) findViewById(R.id.txtRnd);
         seclbl015=(LinearLayout)findViewById(R.id.seclbl015);
         linelbl015=(View)findViewById(R.id.linelbl015);
         secWSDrink=(LinearLayout)findViewById(R.id.secWSDrink);
         lineWSDrink=(View)findViewById(R.id.lineWSDrink);
         VlblWSDrink=(TextView) findViewById(R.id.VlblWSDrink);
         spnWSDrink=(Spinner) findViewById(R.id.spnWSDrink);
         List<String> listWSDrink = new ArrayList<String>();
         
         listWSDrink.add("");
         listWSDrink.add("11-বাড়ির ভিতরে ট্যাপের পানি");
         listWSDrink.add("12-বাড়ির বাহিরে ট্যাপের পানি");
         listWSDrink.add("21-নলকুপ");
         listWSDrink.add("22-অগভীর নলকূপ");
         listWSDrink.add("23-গভীর নলকূপ");
         listWSDrink.add("24-কূয়া");
         listWSDrink.add("31-পুকুর/বদ্ধ জলাশয়/হ্রদ");
         listWSDrink.add("32-নদী/খাল/ঝর্ণার পানি");
         listWSDrink.add("41-বৃষ্টির পানি");
         listWSDrink.add("96-অন্যান্য");
         ArrayAdapter<String> adptrWSDrink= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listWSDrink);
         spnWSDrink.setAdapter(adptrWSDrink);

         spnWSDrink.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             if (spnWSDrink.getSelectedItem().toString().length() == 0) return;
             String spnData = Connection.SelectedSpinnerValue(spnWSDrink.getSelectedItem().toString(),"-");
                 if(!spnData.equalsIgnoreCase("96"))
                 {
                    secWSDrinkOth.setVisibility(View.GONE);
                    lineWSDrinkOth.setVisibility(View.GONE);
                    txtWSDrinkOth.setText("");
                 }
                 else
                 {
                    secWSDrinkOth.setVisibility(View.VISIBLE);
                    lineWSDrinkOth.setVisibility(View.VISIBLE);
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secWSDrinkOth=(LinearLayout)findViewById(R.id.secWSDrinkOth);
         lineWSDrinkOth=(View)findViewById(R.id.lineWSDrinkOth);
         VlblWSDrinkOth=(TextView) findViewById(R.id.VlblWSDrinkOth);
         txtWSDrinkOth=(EditText) findViewById(R.id.txtWSDrinkOth);
         secWSCook=(LinearLayout)findViewById(R.id.secWSCook);
         lineWSCook=(View)findViewById(R.id.lineWSCook);
         VlblWSCook=(TextView) findViewById(R.id.VlblWSCook);
         spnWSCook=(Spinner) findViewById(R.id.spnWSCook);
         List<String> listWSCook = new ArrayList<String>();
         
         listWSCook.add("");
         listWSCook.add("11-বাড়ির ভিতরে ট্যাপের পানি");
         listWSCook.add("12-বাড়ির বাহিরে ট্যাপের পানি");
         listWSCook.add("21-নলকুপ");
         listWSCook.add("22-অগভীর নলকূপ");
         listWSCook.add("23-গভীর নলকূপ");
         listWSCook.add("24-কূয়া");
         listWSCook.add("31-পুকুর/বদ্ধ জলাশয়/হ্রদ");
         listWSCook.add("32-নদী/খাল/ঝর্ণার পানি");
         listWSCook.add("41-বৃষ্টির পানি");
         listWSCook.add("96-অন্যান্য");
         ArrayAdapter<String> adptrWSCook= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listWSCook);
         spnWSCook.setAdapter(adptrWSCook);

         spnWSCook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             if (spnWSCook.getSelectedItem().toString().length() == 0) return;
             String spnData = Connection.SelectedSpinnerValue(spnWSCook.getSelectedItem().toString(),"-");
                 if(!spnData.equalsIgnoreCase("96"))
                 {
                    secWSCookOth.setVisibility(View.GONE);
                    lineWSCookOth.setVisibility(View.GONE);
                    txtWSCookOth.setText("");
                 }
                 else
                 {
                    secWSCookOth.setVisibility(View.VISIBLE);
                    lineWSCookOth.setVisibility(View.VISIBLE);
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secWSCookOth=(LinearLayout)findViewById(R.id.secWSCookOth);
         lineWSCookOth=(View)findViewById(R.id.lineWSCookOth);
         VlblWSCookOth=(TextView) findViewById(R.id.VlblWSCookOth);
         txtWSCookOth=(EditText) findViewById(R.id.txtWSCookOth);
         secWSWash=(LinearLayout)findViewById(R.id.secWSWash);
         lineWSWash=(View)findViewById(R.id.lineWSWash);
         VlblWSWash=(TextView) findViewById(R.id.VlblWSWash);
         spnWSWash=(Spinner) findViewById(R.id.spnWSWash);
         List<String> listWSWash = new ArrayList<String>();
         
         listWSWash.add("");
         listWSWash.add("11-বাড়ির ভিতরে ট্যাপের পানি");
         listWSWash.add("12-বাড়ির বাহিরে ট্যাপের পানি");
         listWSWash.add("21-নলকুপ");
         listWSWash.add("22-অগভীর নলকূপ");
         listWSWash.add("23-গভীর নলকূপ");
         listWSWash.add("24-কূয়া");
         listWSWash.add("31-পুকুর/বদ্ধ জলাশয়/হ্রদ");
         listWSWash.add("32-নদী/খাল/ঝর্ণার পানি");
         listWSWash.add("41-বৃষ্টির পানি");
         listWSWash.add("96-অন্যান্য");
         ArrayAdapter<String> adptrWSWash= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listWSWash);
         spnWSWash.setAdapter(adptrWSWash);

         spnWSWash.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             if (spnWSWash.getSelectedItem().toString().length() == 0) return;
             String spnData = Connection.SelectedSpinnerValue(spnWSWash.getSelectedItem().toString(),"-");
                 if(!spnData.equalsIgnoreCase("96"))
                 {
                    secWSWashOth.setVisibility(View.GONE);
                    lineWSWashOth.setVisibility(View.GONE);
                    txtWSWashOth.setText("");
                 }
                 else
                 {
                    secWSWashOth.setVisibility(View.VISIBLE);
                    lineWSWashOth.setVisibility(View.VISIBLE);
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secWSWashOth=(LinearLayout)findViewById(R.id.secWSWashOth);
         lineWSWashOth=(View)findViewById(R.id.lineWSWashOth);
         VlblWSWashOth=(TextView) findViewById(R.id.VlblWSWashOth);
         txtWSWashOth=(EditText) findViewById(R.id.txtWSWashOth);
         secLatrine=(LinearLayout)findViewById(R.id.secLatrine);
         lineLatrine=(View)findViewById(R.id.lineLatrine);
         VlblLatrine=(TextView) findViewById(R.id.VlblLatrine);
         spnLatrine=(Spinner) findViewById(R.id.spnLatrine);
         List<String> listLatrine = new ArrayList<String>();
         
         listLatrine.add("");
         listLatrine.add("11-ফ্লাশ করে সেপটিক ট্যাংকে পাঠানো / আধুনিক ল্যাট্রিন");
         listLatrine.add("21-জলাবদ্ধ/স্লাাব (স্যানিটারী) ল্যাট্রিন");
         listLatrine.add("22-জলাবদ্ধ নয়, গর্তের (পিট) ল্যাট্রিন");
         listLatrine.add("31-খোলা/ঝুলন্ত  ল্যাট্রিন");
         listLatrine.add("41-ল্যাট্রিন নাই/ঝোপ-ঝাড়/মাঠ");
         listLatrine.add("96-অন্যান্য");
         ArrayAdapter<String> adptrLatrine= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listLatrine);
         spnLatrine.setAdapter(adptrLatrine);

         spnLatrine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             if (spnLatrine.getSelectedItem().toString().length() == 0) return;
             String spnData = Connection.SelectedSpinnerValue(spnLatrine.getSelectedItem().toString(),"-");
                 if(!spnData.equalsIgnoreCase("96"))
                 {
                    secLatrineOth.setVisibility(View.GONE);
                    lineLatrineOth.setVisibility(View.GONE);
                    txtLatrineOth.setText("");
//                    seclbl017.setVisibility(View.GONE);
//                    linelbl017.setVisibility(View.GONE);
                 }
                 else
                 {
                    secLatrineOth.setVisibility(View.VISIBLE);
                    lineLatrineOth.setVisibility(View.VISIBLE);
//                    seclbl017.setVisibility(View.VISIBLE);
//                    linelbl017.setVisibility(View.VISIBLE);
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secLatrineOth=(LinearLayout)findViewById(R.id.secLatrineOth);
         lineLatrineOth=(View)findViewById(R.id.lineLatrineOth);
         VlblLatrineOth=(TextView) findViewById(R.id.VlblLatrineOth);
         txtLatrineOth=(EditText) findViewById(R.id.txtLatrineOth);
         seclbl017=(LinearLayout)findViewById(R.id.seclbl017);
         linelbl017=(View)findViewById(R.id.linelbl017);
         secElectricity=(LinearLayout)findViewById(R.id.secElectricity);
         lineElectricity=(View)findViewById(R.id.lineElectricity);
         VlblElectricity = (TextView) findViewById(R.id.VlblElectricity);
         rdogrpElectricity = (RadioGroup) findViewById(R.id.rdogrpElectricity);
         
         rdoElectricity1 = (RadioButton) findViewById(R.id.rdoElectricity1);
         rdoElectricity2 = (RadioButton) findViewById(R.id.rdoElectricity2);
         secRadio=(LinearLayout)findViewById(R.id.secRadio);
         lineRadio=(View)findViewById(R.id.lineRadio);
         VlblRadio = (TextView) findViewById(R.id.VlblRadio);
         rdogrpRadio = (RadioGroup) findViewById(R.id.rdogrpRadio);
         
         rdoRadio1 = (RadioButton) findViewById(R.id.rdoRadio1);
         rdoRadio2 = (RadioButton) findViewById(R.id.rdoRadio2);
         secTV=(LinearLayout)findViewById(R.id.secTV);
         lineTV=(View)findViewById(R.id.lineTV);
         VlblTV = (TextView) findViewById(R.id.VlblTV);
         rdogrpTV = (RadioGroup) findViewById(R.id.rdogrpTV);
         
         rdoTV1 = (RadioButton) findViewById(R.id.rdoTV1);
         rdoTV2 = (RadioButton) findViewById(R.id.rdoTV2);
         secMobile=(LinearLayout)findViewById(R.id.secMobile);
         lineMobile=(View)findViewById(R.id.lineMobile);
         VlblMobile = (TextView) findViewById(R.id.VlblMobile);
         rdogrpMobile = (RadioGroup) findViewById(R.id.rdogrpMobile);
         
         rdoMobile1 = (RadioButton) findViewById(R.id.rdoMobile1);
         rdoMobile2 = (RadioButton) findViewById(R.id.rdoMobile2);
         secTelephone=(LinearLayout)findViewById(R.id.secTelephone);
         lineTelephone=(View)findViewById(R.id.lineTelephone);
         VlblTelephone = (TextView) findViewById(R.id.VlblTelephone);
         rdogrpTelephone = (RadioGroup) findViewById(R.id.rdogrpTelephone);
         
         rdoTelephone1 = (RadioButton) findViewById(R.id.rdoTelephone1);
         rdoTelephone2 = (RadioButton) findViewById(R.id.rdoTelephone2);
         secRefrige=(LinearLayout)findViewById(R.id.secRefrige);
         lineRefrige=(View)findViewById(R.id.lineRefrige);
         VlblRefrige = (TextView) findViewById(R.id.VlblRefrige);
         rdogrpRefrige = (RadioGroup) findViewById(R.id.rdogrpRefrige);
         
         rdoRefrige1 = (RadioButton) findViewById(R.id.rdoRefrige1);
         rdoRefrige2 = (RadioButton) findViewById(R.id.rdoRefrige2);
         secWatch=(LinearLayout)findViewById(R.id.secWatch);
         lineWatch=(View)findViewById(R.id.lineWatch);
         VlblWatch = (TextView) findViewById(R.id.VlblWatch);
         rdogrpWatch = (RadioGroup) findViewById(R.id.rdogrpWatch);
         
         rdoWatch1 = (RadioButton) findViewById(R.id.rdoWatch1);
         rdoWatch2 = (RadioButton) findViewById(R.id.rdoWatch2);
         secElecFan=(LinearLayout)findViewById(R.id.secElecFan);
         lineElecFan=(View)findViewById(R.id.lineElecFan);
         VlblElecFan = (TextView) findViewById(R.id.VlblElecFan);
         rdogrpElecFan = (RadioGroup) findViewById(R.id.rdogrpElecFan);
         
         rdoElecFan1 = (RadioButton) findViewById(R.id.rdoElecFan1);
         rdoElecFan2 = (RadioButton) findViewById(R.id.rdoElecFan2);
         secRickVan=(LinearLayout)findViewById(R.id.secRickVan);
         lineRickVan=(View)findViewById(R.id.lineRickVan);
         VlblRickVan = (TextView) findViewById(R.id.VlblRickVan);
         rdogrpRickVan = (RadioGroup) findViewById(R.id.rdogrpRickVan);
         
         rdoRickVan1 = (RadioButton) findViewById(R.id.rdoRickVan1);
         rdoRickVan2 = (RadioButton) findViewById(R.id.rdoRickVan2);
         secBicycle=(LinearLayout)findViewById(R.id.secBicycle);
         lineBicycle=(View)findViewById(R.id.lineBicycle);
         VlblBicycle = (TextView) findViewById(R.id.VlblBicycle);
         rdogrpBicycle = (RadioGroup) findViewById(R.id.rdogrpBicycle);
         
         rdoBicycle1 = (RadioButton) findViewById(R.id.rdoBicycle1);
         rdoBicycle2 = (RadioButton) findViewById(R.id.rdoBicycle2);
         secMotCycle=(LinearLayout)findViewById(R.id.secMotCycle);
         lineMotCycle=(View)findViewById(R.id.lineMotCycle);
         VlblMotCycle = (TextView) findViewById(R.id.VlblMotCycle);
         rdogrpMotCycle = (RadioGroup) findViewById(R.id.rdogrpMotCycle);
         
         rdoMotCycle1 = (RadioButton) findViewById(R.id.rdoMotCycle1);
         rdoMotCycle2 = (RadioButton) findViewById(R.id.rdoMotCycle2);
         secComputer=(LinearLayout)findViewById(R.id.secComputer);
         lineComputer=(View)findViewById(R.id.lineComputer);
         VlblComputer = (TextView) findViewById(R.id.VlblComputer);
         rdogrpComputer = (RadioGroup) findViewById(R.id.rdogrpComputer);
         
         rdoComputer1 = (RadioButton) findViewById(R.id.rdoComputer1);
         rdoComputer2 = (RadioButton) findViewById(R.id.rdoComputer2);
         seclbl018=(LinearLayout)findViewById(R.id.seclbl018);
         linelbl018=(View)findViewById(R.id.linelbl018);
         seclbl018a=(LinearLayout)findViewById(R.id.seclbl018a);
         linelbl018a=(View)findViewById(R.id.linelbl018a);
         seclbl019=(LinearLayout)findViewById(R.id.seclbl019);
         linelbl019=(View)findViewById(R.id.linelbl019);

         secBuffalo=(LinearLayout)findViewById(R.id.secBuffalo);
         lineBuffalo=(View)findViewById(R.id.lineBuffalo);
         VlblBuffalo=(TextView) findViewById(R.id.VlblBuffalo);
         txtBuffalo=(EditText) findViewById(R.id.txtBuffalo);
         secBull=(LinearLayout)findViewById(R.id.secBull);
         lineBull=(View)findViewById(R.id.lineBull);
         VlblBull=(TextView) findViewById(R.id.VlblBull);
         txtBull=(EditText) findViewById(R.id.txtBull);
         secGoat=(LinearLayout)findViewById(R.id.secGoat);
         lineGoat=(View)findViewById(R.id.lineGoat);
         VlblGoat=(TextView) findViewById(R.id.VlblGoat);
         txtGoat=(EditText) findViewById(R.id.txtGoat);
         secChicken=(LinearLayout)findViewById(R.id.secChicken);
         lineChicken=(View)findViewById(R.id.lineChicken);
         VlblChicken=(TextView) findViewById(R.id.VlblChicken);
         txtChicken=(EditText) findViewById(R.id.txtChicken);
         secPigeon=(LinearLayout)findViewById(R.id.secPigeon);
         linePigeon=(View)findViewById(R.id.linePigeon);
         VlblPigeon=(TextView) findViewById(R.id.VlblPigeon);
         txtPigeon=(EditText) findViewById(R.id.txtPigeon);
         secRoof=(LinearLayout)findViewById(R.id.secRoof);
         lineRoof=(View)findViewById(R.id.lineRoof);
         VlblRoof=(TextView) findViewById(R.id.VlblRoof);
         spnRoof=(Spinner) findViewById(R.id.spnRoof);
         List<String> listRoof = new ArrayList<String>();
         
         listRoof.add("");
         listRoof.add("11-খড়/পাতা");
         listRoof.add("12-ছড়ি/পাতা/গাছের কান্ড");
         listRoof.add("21-মাটি");
         listRoof.add("22-বাঁশ/পাটকাঠি");
         listRoof.add("23-কাঠের তক্তা");
         listRoof.add("24-পাথর");
         listRoof.add("31-টিন");
         listRoof.add("32-পলিশ করা কাঠ");
         listRoof.add("33-সিরামিক টাইলস");
         listRoof.add("34-সিমেন্ট,কংক্রিট");
         listRoof.add("96-অন্যান্য");
         ArrayAdapter<String> adptrRoof= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listRoof);
         spnRoof.setAdapter(adptrRoof);

         spnRoof.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             if (spnRoof.getSelectedItem().toString().length() == 0) return;
             String spnData = Connection.SelectedSpinnerValue(spnRoof.getSelectedItem().toString(),"-");
                 if(!spnData.equalsIgnoreCase("96"))
                 {
                    secRoofOth.setVisibility(View.GONE);
                    lineRoofOth.setVisibility(View.GONE);
                    txtRoofOth.setText("");
                 }
                 else
                 {
                    secRoofOth.setVisibility(View.VISIBLE);
                    lineRoofOth.setVisibility(View.VISIBLE);
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secRoofOth=(LinearLayout)findViewById(R.id.secRoofOth);
         lineRoofOth=(View)findViewById(R.id.lineRoofOth);
         VlblRoofOth=(TextView) findViewById(R.id.VlblRoofOth);
         txtRoofOth=(EditText) findViewById(R.id.txtRoofOth);
         secWall=(LinearLayout)findViewById(R.id.secWall);
         lineWall=(View)findViewById(R.id.lineWall);
         VlblWall=(TextView) findViewById(R.id.VlblWall);
         spnWall=(Spinner) findViewById(R.id.spnWall);
         List<String> listWall = new ArrayList<String>();
         
         listWall.add("");
         listWall.add("11-খড়/পাতা");
         listWall.add("12-ছড়ি/পাতা/গাছের কান্ড");
         listWall.add("21-মাটি/পাটকাঠি");
         listWall.add("22-বাঁশ/পাটকাঠি");
         listWall.add("23-কাঠের তক্তা");
         listWall.add("24-পাথর");
         listWall.add("31-টিন");
         listWall.add("32-পলিশ করা কাঠ");
         listWall.add("33-সিরামিক টাইলস");
         listWall.add("34-সিমেন্ট,কংক্রিট");
         listWall.add("96-অন্যান্য");
         ArrayAdapter<String> adptrWall= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listWall);
         spnWall.setAdapter(adptrWall);

         spnWall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             if (spnWall.getSelectedItem().toString().length() == 0) return;
             String spnData = Connection.SelectedSpinnerValue(spnWall.getSelectedItem().toString(),"-");
                 if(!spnData.equalsIgnoreCase("96"))
                 {
                    secWallOth.setVisibility(View.GONE);
                    lineWallOth.setVisibility(View.GONE);
                    txtWallOth.setText("");
                 }
                 else
                 {
                    secWallOth.setVisibility(View.VISIBLE);
                    lineWallOth.setVisibility(View.VISIBLE);
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });

         secWallOth=(LinearLayout)findViewById(R.id.secWallOth);
         lineWallOth=(View)findViewById(R.id.lineWallOth);
         VlblWallOth=(TextView) findViewById(R.id.VlblWallOth);
         txtWallOth=(EditText) findViewById(R.id.txtWallOth);
         secFloor=(LinearLayout)findViewById(R.id.secFloor);
         lineFloor=(View)findViewById(R.id.lineFloor);
         VlblFloor=(TextView) findViewById(R.id.VlblFloor);
         spnFloor=(Spinner) findViewById(R.id.spnFloor);
         List<String> listFloor = new ArrayList<String>();
         
         listFloor.add("");
         listFloor.add("11-খড়/পাতা");
         listFloor.add("12-ছড়ি/পাতা/গাছের কান্ড");
         listFloor.add("21-মাটি");
         listFloor.add("22-বাঁশ/পাটকাঠি");
         listFloor.add("23-কাঠের তক্তা");
         listFloor.add("24-পাথর");
         listFloor.add("31-টিন");
         listFloor.add("32-পলিশ করা কাঠ");
         listFloor.add("33-সিরামিক টাইলস");
         listFloor.add("34-সিমেন্ট,কংক্রিট");
         listFloor.add("96-অন্যান্য");
         ArrayAdapter<String> adptrFloor= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listFloor);
         spnFloor.setAdapter(adptrFloor);

         spnFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             if (spnFloor.getSelectedItem().toString().length() == 0) return;
             String spnData = Connection.SelectedSpinnerValue(spnFloor.getSelectedItem().toString(),"-");
                 if(!spnData.equalsIgnoreCase("96"))
                 {
                    secFloorOth.setVisibility(View.GONE);
                    lineFloorOth.setVisibility(View.GONE);
                    txtFloorOth.setText("");
                 }
                 else
                 {
                    secFloorOth.setVisibility(View.VISIBLE);
                    lineFloorOth.setVisibility(View.VISIBLE);
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parentView) {
             }
         });
         secFloorOth=(LinearLayout)findViewById(R.id.secFloorOth);
         lineFloorOth=(View)findViewById(R.id.lineFloorOth);
         VlblFloorOth=(TextView) findViewById(R.id.VlblFloorOth);
         txtFloorOth=(EditText) findViewById(R.id.txtFloorOth);
         secHomestead=(LinearLayout)findViewById(R.id.secHomestead);
         lineHomestead=(View)findViewById(R.id.lineHomestead);
         VlblHomestead = (TextView) findViewById(R.id.VlblHomestead);
         rdogrpHomestead = (RadioGroup) findViewById(R.id.rdogrpHomestead);
         
         rdoHomestead1 = (RadioButton) findViewById(R.id.rdoHomestead1);
         rdoHomestead2 = (RadioButton) findViewById(R.id.rdoHomestead2);
         secHomesteadOth=(LinearLayout)findViewById(R.id.secHomesteadOth);
         lineHomesteadOth=(View)findViewById(R.id.lineHomesteadOth);
         VlblHomesteadOth = (TextView) findViewById(R.id.VlblHomesteadOth);
         rdogrpHomesteadOth = (RadioGroup) findViewById(R.id.rdogrpHomesteadOth);
         
         rdoHomesteadOth1 = (RadioButton) findViewById(R.id.rdoHomesteadOth1);
         rdoHomesteadOth2 = (RadioButton) findViewById(R.id.rdoHomesteadOth2);
         secOthLand=(LinearLayout)findViewById(R.id.secOthLand);
         lineOthLand=(View)findViewById(R.id.lineOthLand);
         VlblOthLand = (TextView) findViewById(R.id.VlblOthLand);
         rdogrpOthLand = (RadioGroup) findViewById(R.id.rdogrpOthLand);
         
         rdoOthLand1 = (RadioButton) findViewById(R.id.rdoOthLand1);
         rdoOthLand2 = (RadioButton) findViewById(R.id.rdoOthLand2);


         dtpVDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT  = 2;
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (dtpVDate.getRight() - dtpVDate.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         VariableID = "btnVDate"; showDialog(DATE_DIALOG);
                      return true;
                     }
                 }
                 return false;
             }
         });



         //Hide all skip variables
         txtVill.setEnabled(false);
         txtBari.setEnabled(false);
         txtHH.setEnabled(false);

         secVStatusOth.setVisibility(View.GONE);
         lineVStatusOth.setVisibility(View.GONE);
         secRnd.setVisibility(View.GONE);
         lineRnd.setVisibility(View.GONE);
         secWSDrinkOth.setVisibility(View.GONE);
         lineWSDrinkOth.setVisibility(View.GONE);
         secWSCookOth.setVisibility(View.GONE);
         lineWSCookOth.setVisibility(View.GONE);
         secWSWashOth.setVisibility(View.GONE);
         lineWSWashOth.setVisibility(View.GONE);
         secLatrineOth.setVisibility(View.GONE);
         lineLatrineOth.setVisibility(View.GONE);
         secRoofOth.setVisibility(View.GONE);
         lineRoofOth.setVisibility(View.GONE);
         secWallOth.setVisibility(View.GONE);
         lineWallOth.setVisibility(View.GONE);
         secFloorOth.setVisibility(View.GONE);
         lineFloorOth.setVisibility(View.GONE);
         seclbl015.setVisibility(View.GONE);
         linelbl015.setVisibility(View.GONE);
         secWSDrink.setVisibility(View.GONE);
         lineWSDrink.setVisibility(View.GONE);
         secWSDrinkOth.setVisibility(View.GONE);
         lineWSDrinkOth.setVisibility(View.GONE);
         secWSCook.setVisibility(View.GONE);
         lineWSCook.setVisibility(View.GONE);
         secWSCookOth.setVisibility(View.GONE);
         lineWSCookOth.setVisibility(View.GONE);
         secWSWash.setVisibility(View.GONE);
         lineWSWash.setVisibility(View.GONE);
         secWSWashOth.setVisibility(View.GONE);
         lineWSWashOth.setVisibility(View.GONE);
         secLatrine.setVisibility(View.GONE);
         lineLatrine.setVisibility(View.GONE);
         secLatrineOth.setVisibility(View.GONE);
         lineLatrineOth.setVisibility(View.GONE);
         seclbl017.setVisibility(View.GONE);
         linelbl017.setVisibility(View.GONE);
         secElectricity.setVisibility(View.GONE);
         lineElectricity.setVisibility(View.GONE);
         secRadio.setVisibility(View.GONE);
         lineRadio.setVisibility(View.GONE);
         secTV.setVisibility(View.GONE);
         lineTV.setVisibility(View.GONE);
         secMobile.setVisibility(View.GONE);
         lineMobile.setVisibility(View.GONE);
         secTelephone.setVisibility(View.GONE);
         lineTelephone.setVisibility(View.GONE);
         secRefrige.setVisibility(View.GONE);
         lineRefrige.setVisibility(View.GONE);
         secWatch.setVisibility(View.GONE);
         lineWatch.setVisibility(View.GONE);
         secElecFan.setVisibility(View.GONE);
         lineElecFan.setVisibility(View.GONE);
         secRickVan.setVisibility(View.GONE);
         lineRickVan.setVisibility(View.GONE);
         secBicycle.setVisibility(View.GONE);
         lineBicycle.setVisibility(View.GONE);
         secMotCycle.setVisibility(View.GONE);
         lineMotCycle.setVisibility(View.GONE);
         secComputer.setVisibility(View.GONE);
         lineComputer.setVisibility(View.GONE);
         seclbl018.setVisibility(View.GONE);
         linelbl018.setVisibility(View.GONE);
         seclbl018a.setVisibility(View.GONE);
         linelbl018a.setVisibility(View.GONE);
         seclbl019.setVisibility(View.GONE);
         linelbl019.setVisibility(View.GONE);
         seclbl019.setVisibility(View.GONE);
         linelbl019.setVisibility(View.GONE);
         secBuffalo.setVisibility(View.GONE);
         lineBuffalo.setVisibility(View.GONE);
         secBull.setVisibility(View.GONE);
         lineBull.setVisibility(View.GONE);
         secGoat.setVisibility(View.GONE);
         lineGoat.setVisibility(View.GONE);
         secChicken.setVisibility(View.GONE);
         lineChicken.setVisibility(View.GONE);
         secPigeon.setVisibility(View.GONE);
         linePigeon.setVisibility(View.GONE);
           secRoof.setVisibility(View.GONE);
         lineRoof.setVisibility(View.GONE);
          secRoofOth.setVisibility(View.GONE);
         lineRoofOth.setVisibility(View.GONE);
          secWall.setVisibility(View.GONE);
         lineWall.setVisibility(View.GONE);
         secWallOth.setVisibility(View.GONE);
         lineWallOth.setVisibility(View.GONE);
         secFloor.setVisibility(View.GONE);
         lineFloor.setVisibility(View.GONE);
         secFloorOth.setVisibility(View.GONE);
         lineFloorOth.setVisibility(View.GONE);
         secHomestead.setVisibility(View.GONE);
         lineHomestead.setVisibility(View.GONE);
         secHomesteadOth.setVisibility(View.GONE);
         lineHomesteadOth.setVisibility(View.GONE);
         secOthLand.setVisibility(View.GONE);
         lineOthLand.setVisibility(View.GONE);

          //**********************************sakib start********************************************
         txtVill.setText(VILL);
         txtVill.setFocusable(false);
         txtBari.setText(BARI);
         txtBari.setFocusable(false);
         txtHH.setText(HH);
//         if(SESNO.equals(""))
//         {
//             SESNO=SesSerial();
//         }
//         txtHH.setText(HH);
//         txtHH.setFocusable(false);

//         txtSESNo.setText(SESNO);
//         txtSESNo.setFocusable(false);
         //**********************************sakib start********************************************


        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});
         DataSearch(VILL,BARI,HH,SESNO);

     }

     catch(Exception  e)
     {
         Connection.MessageBox(SES.this, e.getMessage());
         return;
     }
 }

     private String SesSerial()
     {
         String SL = C.ReturnSingleValue("Select (ifnull(max(cast(SESNo as int)),0)+1)SL from SES where HH='"+HH+"'"); //where ParticipantID='"+ ParticipantID +"'");
         int length=SL.length();
         String s = "";
         for(int i=0;i<1-length;i++)
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
             Connection.MessageBox(SES.this, "Required field: গ্রাম.");
             txtVill.requestFocus(); 
             return;	
           }
         else if(txtBari.getText().toString().length()==0 )
           {
            Connection.MessageBox(SES.this, "Required field: বাড়ি.");
             txtBari.requestFocus();
             return;
           }
         else if(txtHH.getText().toString().length()==0)
           {
             Connection.MessageBox(SES.this, "Required field: খানা.");
             txtHH.requestFocus();
             return;
           }
//         else if(txtSESNo.getText().toString().length()==0 & secSESNo.isShown())
//           {
//             Connection.MessageBox(SES.this, "Required field: SESNo.");
//             txtSESNo.requestFocus();
//             return;
//           }
//         else if(Integer.valueOf(txtSESNo.getText().toString().length()==0 ? "1" : txtSESNo.getText().toString()) < 1 || Integer.valueOf(txtSESNo.getText().toString().length()==0 ? "9" : txtSESNo.getText().toString()) > 9)
//           {
//             Connection.MessageBox(SES.this, "Value should be between 1 and 9(SESNo).");
//             txtSESNo.requestFocus();
//             return;
//           }

         DV = Global.DateValidate(dtpVDate.getText().toString());
         if(DV.length()!=0 & secVDate.isShown())
           {
             Connection.MessageBox(SES.this, DV);
             dtpVDate.requestFocus(); 
             return;	
           }
         else if(spnVStatus.getSelectedItemPosition()==0  & secVStatus.isShown())
           {
             Connection.MessageBox(SES.this, "সাক্ষাতকারের ফলাফল খালি রাখা যাবেনা.");
             spnVStatus.requestFocus(); 
             return;	
           }
         else if(txtVStatusOth.getText().toString().length()==0 & secVStatusOth.isShown())
           {
             Connection.MessageBox(SES.this, "Required field: অন্যান্য উল্লেখ করুন খালি রাখা যাবেনা.");
             txtVStatusOth.requestFocus(); 
             return;	
           }
         else if(txtRnd.getText().toString().length()==0 & secRnd.isShown())
           {
             Connection.MessageBox(SES.this, "Required field: Rnd.");
             txtRnd.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtRnd.getText().toString().length()==0 ? "1" : txtRnd.getText().toString()) < 1 || Integer.valueOf(txtRnd.getText().toString().length()==0 ? "99" : txtRnd.getText().toString()) > 99)
           {
             Connection.MessageBox(SES.this, "Value should be between 1 and 99(Rnd).");
             txtRnd.requestFocus(); 
             return;	
           }
         else if(spnWSDrink.getSelectedItemPosition()==0  & secWSDrink.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ০১৫ a: খাবার পানি খালি রাখা যাবেনা.");
             spnWSDrink.requestFocus(); 
             return;	
           }
         else if(txtWSDrinkOth.getText().toString().length()==0 & secWSDrinkOth.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ০১৫ a: অন্যান্য উল্লেখ করুন খালি রাখা যাবেনা.");
             txtWSDrinkOth.requestFocus(); 
             return;	
           }
         else if(spnWSCook.getSelectedItemPosition()==0  & secWSCook.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ০১৫ b: রান্নার পানি খালি রাখা যাবেনা.");
             spnWSCook.requestFocus(); 
             return;	
           }
         else if(txtWSCookOth.getText().toString().length()==0 & secWSCookOth.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ০১৫ b: অন্যান্য উল্লেখ করুন খালি রাখা যাবেনা.");
             txtWSCookOth.requestFocus(); 
             return;	
           }
         else if(spnWSWash.getSelectedItemPosition()==0  & secWSWash.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ০১৫ c: থালা বাসন ধোয়ার পানি খালি রাখা যাবেনা.");
             spnWSWash.requestFocus(); 
             return;	
           }
         else if(txtWSWashOth.getText().toString().length()==0 & secWSWashOth.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ০১৫ c: অন্যান্য উল্লেখ করুন খালি রাখা যাবেনা.");
             txtWSWashOth.requestFocus(); 
             return;	
           }
         else if(spnLatrine.getSelectedItemPosition()==0  & secLatrine.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ০১৬: আপনার খানার সদস্যরা সাধারণত কি ধরণের পায়খানা/ল্যাট্রিন ব্যবহার করে খালি রাখা যাবেনা.");
             spnLatrine.requestFocus(); 
             return;	
           }
         else if(txtLatrineOth.getText().toString().length()==0 & secLatrineOth.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ০১৬: অন্যান্য উল্লেখ করুন খালি রাখা যাবেনা.");
             txtLatrineOth.requestFocus(); 
             return;	
           }
         
         else if(!rdoElectricity1.isChecked() & !rdoElectricity2.isChecked() & secElectricity.isShown())
           {
              Connection.MessageBox(SES.this, "১৭ a). বিদ্যুৎ/সৌর বিদ্যুৎ থেকে একটি অপশন নির্বাচন করুন.");
              rdoElectricity1.requestFocus();
              return;
           }
         
         else if(!rdoRadio1.isChecked() & !rdoRadio2.isChecked() & secRadio.isShown())
           {
              Connection.MessageBox(SES.this, "১৭ b). রেডিও থেকে একটি অপশন নির্বাচন করুন .");
              rdoRadio1.requestFocus();
              return;
           }
         
         else if(!rdoTV1.isChecked() & !rdoTV2.isChecked() & secTV.isShown())
           {
              Connection.MessageBox(SES.this, "১৭ c). টেলিভিশন থেকে একটি অপশন নির্বাচন করুন .");
              rdoTV1.requestFocus();
              return;
           }
         
         else if(!rdoMobile1.isChecked() & !rdoMobile2.isChecked() & secMobile.isShown())
           {
              Connection.MessageBox(SES.this, "১৭ d). মোবাইল ফোন থেকে একটি অপশন নির্বাচন করুন .");
              rdoMobile1.requestFocus();
              return;
           }
         
         else if(!rdoTelephone1.isChecked() & !rdoTelephone2.isChecked() & secTelephone.isShown())
           {
              Connection.MessageBox(SES.this, "১৭ e). অন্য টেলিফোন থেকে একটি অপশন নির্বাচন করুন .");
              rdoTelephone1.requestFocus();
              return;
           }
         
         else if(!rdoRefrige1.isChecked() & !rdoRefrige2.isChecked() & secRefrige.isShown())
           {
              Connection.MessageBox(SES.this, "১৭ f). রেফ্রিজারেটর থেকে একটি অপশন নির্বাচন করুন .");
              rdoRefrige1.requestFocus();
              return;
           }
         
         else if(!rdoWatch1.isChecked() & !rdoWatch2.isChecked() & secWatch.isShown())
           {
              Connection.MessageBox(SES.this, "১৭ g). হাত ঘড়ি) থেকে একটি অপশন নির্বাচন করুন .");
              rdoWatch1.requestFocus();
              return;
           }
         
         else if(!rdoElecFan1.isChecked() & !rdoElecFan2.isChecked() & secElecFan.isShown())
           {
              Connection.MessageBox(SES.this, "১৭ h). বৈদ্যুতিক পাখা থেকে একটি অপশন নির্বাচন করুন .");
              rdoElecFan1.requestFocus();
              return;
           }
         
         else if(!rdoRickVan1.isChecked() & !rdoRickVan2.isChecked() & secRickVan.isShown())
           {
              Connection.MessageBox(SES.this, "১৭ i). রিকশা/ভ্যান/নৌকা থেকে একটি অপশন নির্বাচন করুন .");
              rdoRickVan1.requestFocus();
              return;
           }
         
         else if(!rdoBicycle1.isChecked() & !rdoBicycle2.isChecked() & secBicycle.isShown())
           {
              Connection.MessageBox(SES.this, "১৭ j). বাইসাইকেল থেকে একটি অপশন নির্বাচন করুন .");
              rdoBicycle1.requestFocus();
              return;
           }
         
         else if(!rdoMotCycle1.isChecked() & !rdoMotCycle2.isChecked() & secMotCycle.isShown())
           {
              Connection.MessageBox(SES.this, "১৭ k). মোটরসাইকেল/সিএনজি/টেম্পু/ইলেকট্রিক বাইক থেকে একটি অপশন নির্বাচন করুন.");
              rdoMotCycle1.requestFocus();
              return;
           }
         
         else if(!rdoComputer1.isChecked() & !rdoComputer2.isChecked() & secComputer.isShown())
           {
              Connection.MessageBox(SES.this, "১৭ l). কম্পিউটার/ল্যাপটপ থেকে একটি অপশন নির্বাচন করুন.");
              rdoComputer1.requestFocus();
              return;
           }
         else if(txtBuffalo.getText().toString().length()==0 & secBuffalo.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ১৮ a) মহিষ/ঘোড়া খালি রাখা যাবেনা.");
             txtBuffalo.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtBuffalo.getText().toString().length()==0 ? "00" : txtBuffalo.getText().toString()) < 00 || Integer.valueOf(txtBuffalo.getText().toString().length()==0 ? "99" : txtBuffalo.getText().toString()) > 99)
           {
             Connection.MessageBox(SES.this, "মহিষ সংখ্যা  অবশ্যই ০০ থেকে ৯৯ এর ভিতর হতে হবে.");
             txtBuffalo.requestFocus(); 
             return;	
           }
         else if(txtBull.getText().toString().length()==0 & secBull.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ১৮ b) ষাঁড়/পরম্ন খালি রাখা যাবেনা.");
             txtBull.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtBull.getText().toString().length()==0 ? "00" : txtBull.getText().toString()) < 00 || Integer.valueOf(txtBull.getText().toString().length()==0 ? "99" : txtBull.getText().toString()) > 99)
           {
             Connection.MessageBox(SES.this, "ষাঁড়/পরম্ন সংখ্যা  অবশ্যই ০০ থেকে ৯৯ এর ভিতর হতে হবে");
             txtBull.requestFocus(); 
             return;	
           }
         else if(txtGoat.getText().toString().length()==0 & secGoat.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ১৮ c) ছাগল/ভেড়া খালি রাখা যাবেনা.");
             txtGoat.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtGoat.getText().toString().length()==0 ? "00" : txtGoat.getText().toString()) < 00 || Integer.valueOf(txtGoat.getText().toString().length()==0 ? "99" : txtGoat.getText().toString()) > 99)
           {
             Connection.MessageBox(SES.this, "ছাগল/ভেড়া সংখ্যা  অবশ্যই ০০ থেকে ৯৯ এর ভিতর হতে হবে.");
             txtGoat.requestFocus(); 
             return;	
           }
         else if(txtChicken.getText().toString().length()==0 & secChicken.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ১৮ d) মুরগি/হাঁস খালি রাখা যাবেনা.");
             txtChicken.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtChicken.getText().toString().length()==0 ? "00" : txtChicken.getText().toString()) < 00 || Integer.valueOf(txtChicken.getText().toString().length()==0 ? "99" : txtChicken.getText().toString()) > 99)
           {
             Connection.MessageBox(SES.this, "মুরগি/হাঁস সংখ্যা  অবশ্যই ০০ থেকে ৯৯ এর ভিতর হতে হবে .");
             txtChicken.requestFocus(); 
             return;	
           }
         else if(txtPigeon.getText().toString().length()==0 & secPigeon.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ১৮ e) কবুতর/কোয়েল পাখি খালি রাখা যাবেনা.");
             txtPigeon.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtPigeon.getText().toString().length()==0 ? "0" : txtPigeon.getText().toString()) < 0 || Integer.valueOf(txtPigeon.getText().toString().length()==0 ? "99" : txtPigeon.getText().toString()) > 99)
           {
             Connection.MessageBox(SES.this, "কবুতর/কোয়েল পাখি সংখ্যা  অবশ্যই ০০ থেকে ৯৯ এর ভিতর হতে হবে.");
             txtPigeon.requestFocus(); 
             return;	
           }
         else if(spnRoof.getSelectedItemPosition()==0  & secRoof.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ০১৯. বসত ঘরের চালের  প্রধান নির্মাণ সামগ্রী. খালি রাখা যাবেনা");
             spnRoof.requestFocus(); 
             return;	
           }
         else if(txtRoofOth.getText().toString().length()==0 & secRoofOth.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ০১৯. অন্যান্য উল্লেখ করুন খালি রাখা যাবেনা.");
             txtRoofOth.requestFocus(); 
             return;	
           }
         else if(spnWall.getSelectedItemPosition()==0  & secWall.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ০১৯. বসত ঘরের দেয়ালের প্রধান নির্মাণ সামগ্রী: খালি রাখা যাবেনা.");
             spnWall.requestFocus(); 
             return;	
           }
         else if(txtWallOth.getText().toString().length()==0 & secWallOth.isShown())
           {
             Connection.MessageBox(SES.this, "অন্যান্য উল্লেখ করুন খালি রাখা যাবেনা.");
             txtWallOth.requestFocus(); 
             return;	
           }
         else if(spnFloor.getSelectedItemPosition()==0  & secFloor.isShown())
           {
             Connection.MessageBox(SES.this, "প্রশ্ন ০১৯. বসত ঘরের মেঝের প্রধান নির্মাণ সামগ্রী: খালি রাখা যাবেনা.");
             spnFloor.requestFocus(); 
             return;	
           }
         else if(txtFloorOth.getText().toString().length()==0 & secFloorOth.isShown())
           {
             Connection.MessageBox(SES.this, "অন্যান্য উল্লেখ করুন খালি রাখা যাবেনা.");
             txtFloorOth.requestFocus(); 
             return;	
           }
         
         else if(!rdoHomestead1.isChecked() & !rdoHomestead2.isChecked() & secHomestead.isShown())
           {
              Connection.MessageBox(SES.this, "প্রশ্ন ০২০. আপনাদের খানার মালিকানায় বসত ভিটা আছে কি ? থেকে একটি অপশন নির্বাচন করুন .");
              rdoHomestead1.requestFocus();
              return;
           }
         
         else if(!rdoHomesteadOth1.isChecked() & !rdoHomesteadOth2.isChecked() & secHomesteadOth.isShown())
           {
              Connection.MessageBox(SES.this, "আপনাদের খানার অন্য কোথাও বসত ভিটা আছে কি ? থেকে একটি অপশন নির্বাচন করুন .");
              rdoHomesteadOth1.requestFocus();
              return;
           }
         
         else if(!rdoOthLand1.isChecked() & !rdoOthLand2.isChecked() & secOthLand.isShown())
           {
              Connection.MessageBox(SES.this, "প্রশ্ন ০২১. খানার বসত ভিটা ছাড়া আপনাদের কোন জমি আছে কি ? থেকে একটি অপশন নির্বাচন করুন.");
              rdoOthLand1.requestFocus();
              return;
           }
 
         String SQL = "";
         RadioButton rb;

         SES_DataModel objSave = new SES_DataModel();
         objSave.setVill(txtVill.getText().toString());
         objSave.setBari(txtBari.getText().toString());
         objSave.setHH(txtHH.getText().toString());
         objSave.setSESNo((spnSESNo.getSelectedItem().toString()));
         objSave.setVDate(dtpVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpVDate.getText().toString()) : dtpVDate.getText().toString());
         objSave.setVStatus((spnVStatus.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnVStatus.getSelectedItem().toString(), "-")));
         objSave.setVStatusOth(txtVStatusOth.getText().toString());
         objSave.setRnd(txtRnd.getText().toString());
         objSave.setWSDrink((spnWSDrink.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnWSDrink.getSelectedItem().toString(), "-")));
         objSave.setWSDrinkOth(txtWSDrinkOth.getText().toString());
         objSave.setWSCook((spnWSCook.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnWSCook.getSelectedItem().toString(), "-")));
         objSave.setWSCookOth(txtWSCookOth.getText().toString());
         objSave.setWSWash((spnWSWash.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnWSWash.getSelectedItem().toString(), "-")));
         objSave.setWSWashOth(txtWSWashOth.getText().toString());
         objSave.setLatrine((spnLatrine.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnLatrine.getSelectedItem().toString(), "-")));
         objSave.setLatrineOth(txtLatrineOth.getText().toString());
         String[] d_rdogrpElectricity = new String[] {"1","2"};
         objSave.setElectricity("");
         for (int i = 0; i < rdogrpElectricity.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpElectricity.getChildAt(i);
             if (rb.isChecked()) objSave.setElectricity(d_rdogrpElectricity[i]);
         }

         String[] d_rdogrpRadio = new String[] {"1","2"};
         objSave.setRadio("");
         for (int i = 0; i < rdogrpRadio.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpRadio.getChildAt(i);
             if (rb.isChecked()) objSave.setRadio(d_rdogrpRadio[i]);
         }

         String[] d_rdogrpTV = new String[] {"1","2"};
         objSave.setTV("");
         for (int i = 0; i < rdogrpTV.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpTV.getChildAt(i);
             if (rb.isChecked()) objSave.setTV(d_rdogrpTV[i]);
         }

         String[] d_rdogrpMobile = new String[] {"1","2"};
         objSave.setMobile("");
         for (int i = 0; i < rdogrpMobile.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMobile.getChildAt(i);
             if (rb.isChecked()) objSave.setMobile(d_rdogrpMobile[i]);
         }

         String[] d_rdogrpTelephone = new String[] {"1","2"};
         objSave.setTelephone("");
         for (int i = 0; i < rdogrpTelephone.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpTelephone.getChildAt(i);
             if (rb.isChecked()) objSave.setTelephone(d_rdogrpTelephone[i]);
         }

         String[] d_rdogrpRefrige = new String[] {"1","2"};
         objSave.setRefrige("");
         for (int i = 0; i < rdogrpRefrige.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpRefrige.getChildAt(i);
             if (rb.isChecked()) objSave.setRefrige(d_rdogrpRefrige[i]);
         }

         String[] d_rdogrpWatch = new String[] {"1","2"};
         objSave.setWatch("");
         for (int i = 0; i < rdogrpWatch.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpWatch.getChildAt(i);
             if (rb.isChecked()) objSave.setWatch(d_rdogrpWatch[i]);
         }

         String[] d_rdogrpElecFan = new String[] {"1","2"};
         objSave.setElecFan("");
         for (int i = 0; i < rdogrpElecFan.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpElecFan.getChildAt(i);
             if (rb.isChecked()) objSave.setElecFan(d_rdogrpElecFan[i]);
         }

         String[] d_rdogrpRickVan = new String[] {"1","2"};
         objSave.setRickVan("");
         for (int i = 0; i < rdogrpRickVan.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpRickVan.getChildAt(i);
             if (rb.isChecked()) objSave.setRickVan(d_rdogrpRickVan[i]);
         }

         String[] d_rdogrpBicycle = new String[] {"1","2"};
         objSave.setBicycle("");
         for (int i = 0; i < rdogrpBicycle.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpBicycle.getChildAt(i);
             if (rb.isChecked()) objSave.setBicycle(d_rdogrpBicycle[i]);
         }

         String[] d_rdogrpMotCycle = new String[] {"1","2"};
         objSave.setMotCycle("");
         for (int i = 0; i < rdogrpMotCycle.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMotCycle.getChildAt(i);
             if (rb.isChecked()) objSave.setMotCycle(d_rdogrpMotCycle[i]);
         }

         String[] d_rdogrpComputer = new String[] {"1","2"};
         objSave.setComputer("");
         for (int i = 0; i < rdogrpComputer.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpComputer.getChildAt(i);
             if (rb.isChecked()) objSave.setComputer(d_rdogrpComputer[i]);
         }

         objSave.setBuffalo(txtBuffalo.getText().toString());
         objSave.setBull(txtBull.getText().toString());
         objSave.setGoat(txtGoat.getText().toString());
         objSave.setChicken(txtChicken.getText().toString());
         objSave.setPigeon(txtPigeon.getText().toString());
         objSave.setRoof((spnRoof.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnRoof.getSelectedItem().toString(), "-")));
         objSave.setRoofOth(txtRoofOth.getText().toString());
         objSave.setWall((spnWall.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnWall.getSelectedItem().toString(), "-")));
         objSave.setWallOth(txtWallOth.getText().toString());
         objSave.setFloor((spnFloor.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnFloor.getSelectedItem().toString(), "-")));
         objSave.setFloorOth(txtFloorOth.getText().toString());
         String[] d_rdogrpHomestead = new String[] {"1","2"};
         objSave.setHomestead("");
         for (int i = 0; i < rdogrpHomestead.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpHomestead.getChildAt(i);
             if (rb.isChecked()) objSave.setHomestead(d_rdogrpHomestead[i]);
         }

         String[] d_rdogrpHomesteadOth = new String[] {"1","2"};
         objSave.setHomesteadOth("");
         for (int i = 0; i < rdogrpHomesteadOth.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpHomesteadOth.getChildAt(i);
             if (rb.isChecked()) objSave.setHomesteadOth(d_rdogrpHomesteadOth[i]);
         }

         String[] d_rdogrpOthLand = new String[] {"1","2"};
         objSave.setOthLand("");
         for (int i = 0; i < rdogrpOthLand.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpOthLand.getChildAt(i);
             if (rb.isChecked()) objSave.setOthLand(d_rdogrpOthLand[i]);
         }
         objSave.setRnd("00");
         objSave.setEnDt(Global.DateTimeNowYMDHMS());
         objSave.setStartTime(STARTTIME);
         objSave.setEndTime(g.CurrentTime24());
         objSave.setDeviceID(DEVICEID);
         objSave.setEntryUser(ENTRYUSER); //from data entry user list
         //objSave.setLat(Double.toString(currentLatitude));
         //objSave.setLon(Double.toString(currentLongitude));

         String status = objSave.SaveUpdateData(this);
         if(status.length()==0) {
             Intent returnIntent = new Intent();
             returnIntent.putExtra("res", "");
             setResult(Activity.RESULT_OK, returnIntent);

             Connection.MessageBox(SES.this, "Saved Successfully");
         }
         else{
             Connection.MessageBox(SES.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(SES.this, e.getMessage());
         return;
     }
 }

 private void DataSearch(String Vill, String Bari, String HH, String SESNo)
     {
       try
        {
     
           RadioButton rb;
           SES_DataModel d = new SES_DataModel();
           String SQL = "Select * from "+ TableName +"  Where Vill='"+ Vill +"' and Bari='"+ Bari +"' and HH='"+ HH +"' and SESNo='"+ SESNo +"'";
           List<SES_DataModel> data = d.SelectAll(this, SQL);
           for(SES_DataModel item : data){
             txtVill.setText(item.getVill());
             txtBari.setText(item.getBari());
             txtHH.setText(item.getHH());
             spnSESNo.setSelection(Global.SpinnerItemPositionAnyLength(spnSESNo, item.getSESNo()));
             dtpVDate.setText(item.getVDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getVDate()));
             spnVStatus.setSelection(Global.SpinnerItemPositionAnyLength(spnVStatus, item.getVStatus()));
             txtVStatusOth.setText(item.getVStatusOth());
             spnWSDrink.setSelection(Global.SpinnerItemPositionAnyLength(spnWSDrink, item.getWSDrink()));
             txtWSDrinkOth.setText(item.getWSDrinkOth());
             spnWSCook.setSelection(Global.SpinnerItemPositionAnyLength(spnWSCook, item.getWSCook()));
             txtWSCookOth.setText(item.getWSCookOth());
             spnWSWash.setSelection(Global.SpinnerItemPositionAnyLength(spnWSWash, item.getWSWash()));
             txtWSWashOth.setText(item.getWSWashOth());
             spnLatrine.setSelection(Global.SpinnerItemPositionAnyLength(spnLatrine, item.getLatrine()));
             txtLatrineOth.setText(item.getLatrineOth());
             String[] d_rdogrpElectricity = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpElectricity.length; i++)
             {
                 if (item.getElectricity().equals(String.valueOf(d_rdogrpElectricity[i])))
                 {
                     rb = (RadioButton)rdogrpElectricity.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpRadio = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpRadio.length; i++)
             {
                 if (item.getRadio().equals(String.valueOf(d_rdogrpRadio[i])))
                 {
                     rb = (RadioButton)rdogrpRadio.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpTV = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpTV.length; i++)
             {
                 if (item.getTV().equals(String.valueOf(d_rdogrpTV[i])))
                 {
                     rb = (RadioButton)rdogrpTV.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpMobile = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpMobile.length; i++)
             {
                 if (item.getMobile().equals(String.valueOf(d_rdogrpMobile[i])))
                 {
                     rb = (RadioButton)rdogrpMobile.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpTelephone = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpTelephone.length; i++)
             {
                 if (item.getTelephone().equals(String.valueOf(d_rdogrpTelephone[i])))
                 {
                     rb = (RadioButton)rdogrpTelephone.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpRefrige = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpRefrige.length; i++)
             {
                 if (item.getRefrige().equals(String.valueOf(d_rdogrpRefrige[i])))
                 {
                     rb = (RadioButton)rdogrpRefrige.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpWatch = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpWatch.length; i++)
             {
                 if (item.getWatch().equals(String.valueOf(d_rdogrpWatch[i])))
                 {
                     rb = (RadioButton)rdogrpWatch.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpElecFan = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpElecFan.length; i++)
             {
                 if (item.getElecFan().equals(String.valueOf(d_rdogrpElecFan[i])))
                 {
                     rb = (RadioButton)rdogrpElecFan.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpRickVan = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpRickVan.length; i++)
             {
                 if (item.getRickVan().equals(String.valueOf(d_rdogrpRickVan[i])))
                 {
                     rb = (RadioButton)rdogrpRickVan.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpBicycle = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpBicycle.length; i++)
             {
                 if (item.getBicycle().equals(String.valueOf(d_rdogrpBicycle[i])))
                 {
                     rb = (RadioButton)rdogrpBicycle.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpMotCycle = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpMotCycle.length; i++)
             {
                 if (item.getMotCycle().equals(String.valueOf(d_rdogrpMotCycle[i])))
                 {
                     rb = (RadioButton)rdogrpMotCycle.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpComputer = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpComputer.length; i++)
             {
                 if (item.getComputer().equals(String.valueOf(d_rdogrpComputer[i])))
                 {
                     rb = (RadioButton)rdogrpComputer.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtBuffalo.setText(item.getBuffalo());
             txtBull.setText(item.getBull());
             txtGoat.setText(item.getGoat());
             txtChicken.setText(item.getChicken());
             txtPigeon.setText(item.getPigeon());
             spnRoof.setSelection(Global.SpinnerItemPositionAnyLength(spnRoof, item.getRoof()));
             txtRoofOth.setText(item.getRoofOth());
             spnWall.setSelection(Global.SpinnerItemPositionAnyLength(spnWall, item.getWall()));
             txtWallOth.setText(item.getWallOth());
             spnFloor.setSelection(Global.SpinnerItemPositionAnyLength(spnFloor, item.getFloor()));
             txtFloorOth.setText(item.getFloorOth());
             String[] d_rdogrpHomestead = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpHomestead.length; i++)
             {
                 if (item.getHomestead().equals(String.valueOf(d_rdogrpHomestead[i])))
                 {
                     rb = (RadioButton)rdogrpHomestead.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpHomesteadOth = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpHomesteadOth.length; i++)
             {
                 if (item.getHomesteadOth().equals(String.valueOf(d_rdogrpHomesteadOth[i])))
                 {
                     rb = (RadioButton)rdogrpHomesteadOth.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpOthLand = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpOthLand.length; i++)
             {
                 if (item.getOthLand().equals(String.valueOf(d_rdogrpOthLand[i])))
                 {
                     rb = (RadioButton)rdogrpOthLand.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(SES.this, e.getMessage());
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

 private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
      mYear = year; mMonth = monthOfYear+1; mDay = dayOfMonth;
      EditText dtpDate;


              dtpDate = (EditText)findViewById(R.id.dtpVDate);
             if (VariableID.equals("btnVDate"))
              {
                  dtpDate = (EditText)findViewById(R.id.dtpVDate);
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
//  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
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