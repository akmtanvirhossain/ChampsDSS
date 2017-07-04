
 package org.icddrb.champsdss;


 //Android Manifest Code
 //<activity android:name=".PregHis" android:label="PregHis" />
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

 public class PregHis extends Activity {
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
    private String PNo;
    Connection C;
    Global g;
    SimpleAdapter dataAdapter;
    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
         TextView lblHeading;
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

         LinearLayout secMSlNo;
         View lineMSlNo;
         TextView VlblMSlNo;
         Spinner spnMSlNo;

         LinearLayout secPNo;
         View linePNo;
         TextView VlblPNo;
         EditText txtPNo;
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
         LinearLayout secMarriageStatus;
         View lineMarriageStatus;
         TextView VlblMarriageStatus;
         RadioGroup rdogrpMarriageStatus;
         
         RadioButton rdoMarriageStatus1;
         RadioButton rdoMarriageStatus2;
         LinearLayout seclbl02;
         View linelbl02;
         LinearLayout seclbl03;
         View linelbl03;
         LinearLayout seclbl04;
         View linelbl04;
         LinearLayout seclbl103;
         View linelbl103;
         LinearLayout secMarMon;
         View lineMarMon;
         TextView VlblMarMon;
         EditText txtMarMon;
//         LinearLayout secMarYear;
//         View lineMarYear;
         TextView VlblMarYear;
         EditText txtMarYear;
//         LinearLayout secMarDK;
//         View lineMarDK;
         TextView VlblMarDK;
         CheckBox chkMarDK;
         LinearLayout secGaveBirth;
         View lineGaveBirth;
         TextView VlblGaveBirth;
         RadioGroup rdogrpGaveBirth;
         
         RadioButton rdoGaveBirth1;
         RadioButton rdoGaveBirth2;
         LinearLayout secChildLivWWo;
         View lineChildLivWWo;
         TextView VlblChildLivWWo;
         RadioGroup rdogrpChildLivWWo;
         
         RadioButton rdoChildLivWWo1;
         RadioButton rdoChildLivWWo2;
         LinearLayout seclbl106;
         View linelbl106;
         LinearLayout seclbl106a;
         View linelbl106a;
         LinearLayout secSonLivWWo;
         View lineSonLivWWo;
         TextView VlblSonLivWWo;
         EditText txtSonLivWWo;
         LinearLayout secDaugLivWWo;
         View lineDaugLivWWo;
         TextView VlblDaugLivWWo;
         EditText txtDaugLivWWo;
         LinearLayout secChldLivOut;
         View lineChldLivOut;
         TextView VlblChldLivOut;
         RadioGroup rdogrpChldLivOut;
         
         RadioButton rdoChldLivOut1;
         RadioButton rdoChldLivOut2;
         LinearLayout seclbl108;
         View linelbl108;
         LinearLayout seclbl108a;
         View linelbl108a;
         LinearLayout secSonLivOut;
         View lineSonLivOut;
         TextView VlblSonLivOut;
         EditText txtSonLivOut;
         LinearLayout secDaugLivOut;
         View lineDaugLivOut;
         TextView VlblDaugLivOut;
         EditText txtDaugLivOut;
         LinearLayout seclbl109;
         View linelbl109;
         LinearLayout secChldDie;
         View lineChldDie;
         TextView VlblChldDie;
         RadioGroup rdogrpChldDie;
         
         RadioButton rdoChldDie1;
         RadioButton rdoChldDie2;
         LinearLayout seclbl110;
         View linelbl110;
         LinearLayout seclbl110a;
         View linelbl110a;
         LinearLayout secBoyDied;
         View lineBoyDied;
         TextView VlblBoyDied;
         EditText txtBoyDied;
         LinearLayout secGirlDied;
         View lineGirlDied;
         TextView VlblGirlDied;
         EditText txtGirlDied;
         LinearLayout seclbl111;
         View linelbl111;
         LinearLayout secNotLivBrth;
         View lineNotLivBrth;
         TextView VlblNotLivBrth;
         RadioGroup rdogrpNotLivBrth;
         
         RadioButton rdoNotLivBrth1;
         RadioButton rdoNotLivBrth2;
         LinearLayout secTotLB;
         View lineTotLB;
         TextView VlblTotLB;
         EditText txtTotLB;
         LinearLayout seclbl113;
         View linelbl113;
         LinearLayout secTotPregOut;
         View lineTotPregOut;
         TextView VlblTotPregOut;
         EditText txtTotPregOut;
         LinearLayout secCurPreg;
         View lineCurPreg;
         TextView VlblCurPreg;
         RadioGroup rdogrpCurPreg;
         
         RadioButton rdoCurPreg1;
         RadioButton rdoCurPreg2;
         RadioButton rdoCurPreg3;
         LinearLayout secLMPDate;
         View lineLMPDate;
         TextView VlblLMPDate;
         EditText dtpLMPDate;

        static String TableName;

        static String STARTTIME = "";
        static String DEVICEID  = "";
        static String ENTRYUSER = "";

        Bundle IDbundle;
        static String VILL = "";
        static String BARI = "";
        static String HH = "";
        static String MSLNO = "";

     boolean dataSeatch = false;
 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.preghis);
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


         TableName = "PregHis";



         //turnGPSOn();
         //GPS Location
         //FindLocation();
         // Double.toString(currentLatitude);
         // Double.toString(currentLongitude);

         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(PregHis.this);
                 adb.setTitle("Close");
                 adb.setMessage("আপনি কি এ ফরম থেকে বের হতে চান [হ্যাঁ/না]?");
                 adb.setNegativeButton("No", null);
                 adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                         finish();
                         Intent returnIntent = new Intent();
                         returnIntent.putExtra("res", "");
                         setResult(Activity.RESULT_OK, returnIntent);
                     }});
                 adb.show();
             }});

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

         secMSlNo=(LinearLayout)findViewById(R.id.secMSlNo);
         lineMSlNo=(View)findViewById(R.id.lineMSlNo);
         VlblMSlNo=(TextView) findViewById(R.id.VlblMSlNo);
         spnMSlNo=(Spinner) findViewById(R.id.spnMSlNo);
         spnMSlNo.setAdapter(C.getArrayAdapter("Select '' union Select MSlNo||'-'||Name from Member Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH + "' and (julianday(EnDate)-julianday(BDate))<=18262 and Sex='2' and MS<>'30'"));

         secPNo=(LinearLayout)findViewById(R.id.secPNo);
         linePNo=(View)findViewById(R.id.linePNo);
         VlblPNo=(TextView) findViewById(R.id.VlblPNo);
         txtPNo=(EditText) findViewById(R.id.txtPNo);

//         txtPNo.setText(VILL.toString()+BARI.toString()+HH.toString()+(spnMSlNo.getSelectedItem().toString()));

         spnMSlNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                 if(spnMSlNo.getSelectedItemPosition()==0){
                     txtPNo.setText("");
                     spnVStatus.setSelection(0);
                 }else{
                     String MSL = spnMSlNo.getSelectedItemPosition()==0?"":Global.Left(spnMSlNo.getSelectedItem().toString(),2);
                     String PNo= C.ReturnSingleValue("Select PNo from Member where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH +"' and MSlNo='"+ MSL +"'");
                     txtPNo.setText(PNo);
                     if(dataSeatch == true)
                         DataSearch(VILL,BARI,HH,MSL);
                 }
             }
             public void onNothingSelected(AdapterView<?> parentView) {

             }

         });


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
         listVStatus.add("2-তথ্য দানে অসন্মতি");
         listVStatus.add("3-সদস্য অনুপস্থিত");
         listVStatus.add("9-অন্যান্য");
         ArrayAdapter<String> adptrVStatus= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listVStatus);
         spnVStatus.setAdapter(adptrVStatus);

         spnVStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
             if (spnVStatus.getSelectedItem().toString().length() == 0) {
                 secMarriageStatus.setVisibility(View.GONE);
                 lineMarriageStatus.setVisibility(View.GONE);
                 rdogrpMarriageStatus.clearCheck();
                 seclbl02.setVisibility(View.GONE);
                 linelbl02.setVisibility(View.GONE);
                 seclbl04.setVisibility(View.GONE);
                 linelbl04.setVisibility(View.GONE);
                 seclbl103.setVisibility(View.GONE);
                 linelbl103.setVisibility(View.GONE);
                 secMarMon.setVisibility(View.GONE);
                 lineMarMon.setVisibility(View.GONE);
                 txtMarMon.setText("");
                 txtMarYear.setText("");
                 chkMarDK.setChecked(false);
                 secGaveBirth.setVisibility(View.GONE);
                 lineGaveBirth.setVisibility(View.GONE);
                 rdogrpGaveBirth.clearCheck();
                 secChildLivWWo.setVisibility(View.GONE);
                 lineChildLivWWo.setVisibility(View.GONE);
                 rdogrpChildLivWWo.clearCheck();
                 seclbl106.setVisibility(View.GONE);
                 linelbl106.setVisibility(View.GONE);
                 seclbl106a.setVisibility(View.GONE);
                 linelbl106a.setVisibility(View.GONE);
                 secSonLivWWo.setVisibility(View.GONE);
                 lineSonLivWWo.setVisibility(View.GONE);
                 txtSonLivWWo.setText("");
                 secDaugLivWWo.setVisibility(View.GONE);
                 lineDaugLivWWo.setVisibility(View.GONE);
                 txtDaugLivWWo.setText("");
                 secChldLivOut.setVisibility(View.GONE);
                 lineChldLivOut.setVisibility(View.GONE);
                 rdogrpChldLivOut.clearCheck();
                 seclbl108.setVisibility(View.GONE);
                 linelbl108.setVisibility(View.GONE);
                 seclbl108a.setVisibility(View.GONE);
                 linelbl108a.setVisibility(View.GONE);
                 secSonLivOut.setVisibility(View.GONE);
                 lineSonLivOut.setVisibility(View.GONE);
                 txtSonLivOut.setText("");
                 secDaugLivOut.setVisibility(View.GONE);
                 lineDaugLivOut.setVisibility(View.GONE);
                 txtDaugLivOut.setText("");
                 seclbl109.setVisibility(View.GONE);
                 linelbl109.setVisibility(View.GONE);
                 secChldDie.setVisibility(View.GONE);
                 lineChldDie.setVisibility(View.GONE);
                 rdogrpChldDie.clearCheck();
                 seclbl110.setVisibility(View.GONE);
                 linelbl110.setVisibility(View.GONE);
                 seclbl110a.setVisibility(View.GONE);
                 linelbl110a.setVisibility(View.GONE);
                 secBoyDied.setVisibility(View.GONE);
                 lineBoyDied.setVisibility(View.GONE);
                 txtBoyDied.setText("");
                 secGirlDied.setVisibility(View.GONE);
                 lineGirlDied.setVisibility(View.GONE);
                 txtGirlDied.setText("");
                 seclbl111.setVisibility(View.GONE);
                 linelbl111.setVisibility(View.GONE);
                 secNotLivBrth.setVisibility(View.GONE);
                 lineNotLivBrth.setVisibility(View.GONE);
                 rdogrpNotLivBrth.clearCheck();
                 secTotLB.setVisibility(View.GONE);
                 lineTotLB.setVisibility(View.GONE);
                 txtTotLB.setText("");
                 seclbl113.setVisibility(View.GONE);
                 linelbl113.setVisibility(View.GONE);
                 secTotPregOut.setVisibility(View.GONE);
                 lineTotPregOut.setVisibility(View.GONE);
                 txtTotPregOut.setText("");
                 secCurPreg.setVisibility(View.GONE);
                 lineCurPreg.setVisibility(View.GONE);
                 rdogrpCurPreg.clearCheck();
                 secLMPDate.setVisibility(View.GONE);
                 lineLMPDate.setVisibility(View.GONE);

                 return;
             }
             String spnData = Connection.SelectedSpinnerValue(spnVStatus.getSelectedItem().toString(),"-");

                 if(spnData.equalsIgnoreCase("9"))
                 {   secVStatusOth.setVisibility(View.VISIBLE);
                     lineVStatusOth.setVisibility(View.VISIBLE);
                 }
                 else
                 {
                     secVStatusOth.setVisibility(View.GONE);
                     lineVStatusOth.setVisibility(View.GONE);
                     txtVStatusOth.setText("");

                     rdoMarriageStatus1.setEnabled(false);
                     rdoMarriageStatus2.setEnabled(false);

                     if(spnData.equals("1")){
                         rdoMarriageStatus1.setChecked(true);
                         secMarriageStatus.setVisibility(View.VISIBLE);
                         seclbl02.setVisibility(View.VISIBLE);
                         linelbl02.setVisibility(View.VISIBLE);
                         seclbl04.setVisibility(View.VISIBLE);
                         linelbl04.setVisibility(View.VISIBLE);
                         seclbl103.setVisibility(View.VISIBLE);
                         linelbl103.setVisibility(View.VISIBLE);
                         secMarMon.setVisibility(View.VISIBLE);
                         lineMarMon.setVisibility(View.VISIBLE);
                         secGaveBirth.setVisibility(View.VISIBLE);
                         lineGaveBirth.setVisibility(View.VISIBLE);

                     }else{
                         secMarriageStatus.setVisibility(View.GONE);
                         lineMarriageStatus.setVisibility(View.GONE);
                         rdogrpMarriageStatus.clearCheck();
                         seclbl02.setVisibility(View.GONE);
                         linelbl02.setVisibility(View.GONE);
                         seclbl04.setVisibility(View.GONE);
                         linelbl04.setVisibility(View.GONE);
                         seclbl103.setVisibility(View.GONE);
                         linelbl103.setVisibility(View.GONE);
                         secMarMon.setVisibility(View.GONE);
                         lineMarMon.setVisibility(View.GONE);
                         txtMarMon.setText("");
                         txtMarYear.setText("");
                         chkMarDK.setChecked(false);
                         secGaveBirth.setVisibility(View.GONE);
                         lineGaveBirth.setVisibility(View.GONE);
                         rdogrpGaveBirth.clearCheck();
                         secChildLivWWo.setVisibility(View.GONE);
                         lineChildLivWWo.setVisibility(View.GONE);
                         rdogrpChildLivWWo.clearCheck();
                         seclbl106.setVisibility(View.GONE);
                         linelbl106.setVisibility(View.GONE);
                         seclbl106a.setVisibility(View.GONE);
                         linelbl106a.setVisibility(View.GONE);
                         secSonLivWWo.setVisibility(View.GONE);
                         lineSonLivWWo.setVisibility(View.GONE);
                         txtSonLivWWo.setText("");
                         secDaugLivWWo.setVisibility(View.GONE);
                         lineDaugLivWWo.setVisibility(View.GONE);
                         txtDaugLivWWo.setText("");
                         secChldLivOut.setVisibility(View.GONE);
                         lineChldLivOut.setVisibility(View.GONE);
                         rdogrpChldLivOut.clearCheck();
                         seclbl108.setVisibility(View.GONE);
                         linelbl108.setVisibility(View.GONE);
                         seclbl108a.setVisibility(View.GONE);
                         linelbl108a.setVisibility(View.GONE);
                         secSonLivOut.setVisibility(View.GONE);
                         lineSonLivOut.setVisibility(View.GONE);
                         txtSonLivOut.setText("");
                         secDaugLivOut.setVisibility(View.GONE);
                         lineDaugLivOut.setVisibility(View.GONE);
                         txtDaugLivOut.setText("");
                         seclbl109.setVisibility(View.GONE);
                         linelbl109.setVisibility(View.GONE);
                         secChldDie.setVisibility(View.GONE);
                         lineChldDie.setVisibility(View.GONE);
                         rdogrpChldDie.clearCheck();
                         seclbl110.setVisibility(View.GONE);
                         linelbl110.setVisibility(View.GONE);
                         seclbl110a.setVisibility(View.GONE);
                         linelbl110a.setVisibility(View.GONE);
                         secBoyDied.setVisibility(View.GONE);
                         lineBoyDied.setVisibility(View.GONE);
                         txtBoyDied.setText("");
                         secGirlDied.setVisibility(View.GONE);
                         lineGirlDied.setVisibility(View.GONE);
                         txtGirlDied.setText("");
                         seclbl111.setVisibility(View.GONE);
                         linelbl111.setVisibility(View.GONE);
                         secNotLivBrth.setVisibility(View.GONE);
                         lineNotLivBrth.setVisibility(View.GONE);
                         rdogrpNotLivBrth.clearCheck();
                         secTotLB.setVisibility(View.GONE);
                         lineTotLB.setVisibility(View.GONE);
                         txtTotLB.setText("");
                         seclbl113.setVisibility(View.GONE);
                         linelbl113.setVisibility(View.GONE);
                         secTotPregOut.setVisibility(View.GONE);
                         lineTotPregOut.setVisibility(View.GONE);
                         txtTotPregOut.setText("");
                         secCurPreg.setVisibility(View.GONE);
                         lineCurPreg.setVisibility(View.GONE);
                         rdogrpCurPreg.clearCheck();
                         secLMPDate.setVisibility(View.GONE);
                         lineLMPDate.setVisibility(View.GONE);
                     }
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

         secMarriageStatus=(LinearLayout)findViewById(R.id.secMarriageStatus);
         lineMarriageStatus=(View)findViewById(R.id.lineMarriageStatus);
         VlblMarriageStatus = (TextView) findViewById(R.id.VlblMarriageStatus);
         rdogrpMarriageStatus = (RadioGroup) findViewById(R.id.rdogrpMarriageStatus);
         
         rdoMarriageStatus1 = (RadioButton) findViewById(R.id.rdoMarriageStatus1);
         rdoMarriageStatus2 = (RadioButton) findViewById(R.id.rdoMarriageStatus2);



         seclbl02=(LinearLayout)findViewById(R.id.seclbl02);
         linelbl02=(View)findViewById(R.id.linelbl02);
//         seclbl03=(LinearLayout)findViewById(R.id.seclbl03);
//         linelbl03=(View)findViewById(R.id.linelbl03);
         seclbl04=(LinearLayout)findViewById(R.id.seclbl04);
         linelbl04=(View)findViewById(R.id.linelbl04);
         seclbl103=(LinearLayout)findViewById(R.id.seclbl103);
         linelbl103=(View)findViewById(R.id.linelbl103);

         secMarMon=(LinearLayout)findViewById(R.id.secMarMon);
         lineMarMon=(View)findViewById(R.id.lineMarMon);
         VlblMarMon=(TextView) findViewById(R.id.VlblMarMon);
         txtMarMon=(EditText) findViewById(R.id.txtMarMon);
//         secMarYear=(LinearLayout)findViewById(R.id.secMarYear);
//         lineMarYear=(View)findViewById(R.id.lineMarYear);
         VlblMarYear=(TextView) findViewById(R.id.VlblMarYear);
         txtMarYear=(EditText) findViewById(R.id.txtMarYear);
//         secMarDK=(LinearLayout)findViewById(R.id.secMarDK);
//         lineMarDK=(View)findViewById(R.id.lineMarDK);
         VlblMarDK=(TextView) findViewById(R.id.VlblMarDK);
         chkMarDK=(CheckBox) findViewById(R.id.chkMarDK);

         chkMarDK.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 if (((CheckBox) v).isChecked()) {
                     txtMarMon.setEnabled(false);
                     txtMarYear.setEnabled(false);
                     txtMarMon.setText("99");
                     txtMarYear.setText("9999");
                 }
                 else {
                     txtMarMon.setEnabled(true);
                     txtMarYear.setEnabled(true);
                     txtMarMon.setText("");
                     txtMarYear.setText("");
                 }
             }
         });

         secGaveBirth=(LinearLayout)findViewById(R.id.secGaveBirth);
         lineGaveBirth=(View)findViewById(R.id.lineGaveBirth);
         VlblGaveBirth = (TextView) findViewById(R.id.VlblGaveBirth);
         rdogrpGaveBirth = (RadioGroup) findViewById(R.id.rdogrpGaveBirth);
         
         rdoGaveBirth1 = (RadioButton) findViewById(R.id.rdoGaveBirth1);
         rdoGaveBirth2 = (RadioButton) findViewById(R.id.rdoGaveBirth2);
         rdogrpGaveBirth.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpGaveBirth = new String[] {"1","2"};
             for (int i = 0; i < rdogrpGaveBirth.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpGaveBirth.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpGaveBirth[i];
             }

             if(rbData.equalsIgnoreCase("2"))
             {
                    secChildLivWWo.setVisibility(View.GONE);
                    lineChildLivWWo.setVisibility(View.GONE);
                    rdogrpChildLivWWo.clearCheck();
                    seclbl106.setVisibility(View.GONE);
                    linelbl106.setVisibility(View.GONE);
                    seclbl106a.setVisibility(View.GONE);
                    linelbl106a.setVisibility(View.GONE);
                    secSonLivWWo.setVisibility(View.GONE);
                    lineSonLivWWo.setVisibility(View.GONE);
                    txtSonLivWWo.setText("");
                    secDaugLivWWo.setVisibility(View.GONE);
                    lineDaugLivWWo.setVisibility(View.GONE);
                    txtDaugLivWWo.setText("");
                    secChldLivOut.setVisibility(View.GONE);
                    lineChldLivOut.setVisibility(View.GONE);
                    rdogrpChldLivOut.clearCheck();
                    seclbl108.setVisibility(View.GONE);
                    linelbl108.setVisibility(View.GONE);
                    seclbl108a.setVisibility(View.GONE);
                    linelbl108a.setVisibility(View.GONE);
                    secSonLivOut.setVisibility(View.GONE);
                    lineSonLivOut.setVisibility(View.GONE);
                    txtSonLivOut.setText("");
                    secDaugLivOut.setVisibility(View.GONE);
                    lineDaugLivOut.setVisibility(View.GONE);
                    txtDaugLivOut.setText("");
             }
             else
             {
                    secChildLivWWo.setVisibility(View.VISIBLE);
                    lineChildLivWWo.setVisibility(View.VISIBLE);
                    seclbl109.setVisibility(View.GONE);
                    linelbl109.setVisibility(View.GONE);
                    secChldDie.setVisibility(View.GONE);
                    lineChldDie.setVisibility(View.GONE);

             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         });

         secChildLivWWo=(LinearLayout)findViewById(R.id.secChildLivWWo);
         lineChildLivWWo=(View)findViewById(R.id.lineChildLivWWo);
         VlblChildLivWWo = (TextView) findViewById(R.id.VlblChildLivWWo);
         rdogrpChildLivWWo = (RadioGroup) findViewById(R.id.rdogrpChildLivWWo);
         
         rdoChildLivWWo1 = (RadioButton) findViewById(R.id.rdoChildLivWWo1);
         rdoChildLivWWo2 = (RadioButton) findViewById(R.id.rdoChildLivWWo2);
         rdogrpChildLivWWo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpChildLivWWo = new String[] {"1","2"};
             for (int i = 0; i < rdogrpChildLivWWo.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpChildLivWWo.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpChildLivWWo[i];
             }

             if(rbData.equalsIgnoreCase("2"))
             {
                    seclbl106.setVisibility(View.GONE);
                    linelbl106.setVisibility(View.GONE);
                    seclbl106a.setVisibility(View.GONE);
                    linelbl106a.setVisibility(View.GONE);
                    secSonLivWWo.setVisibility(View.GONE);
                    lineSonLivWWo.setVisibility(View.GONE);
                    txtSonLivWWo.setText("");
                    secDaugLivWWo.setVisibility(View.GONE);
                    lineDaugLivWWo.setVisibility(View.GONE);
                    txtDaugLivWWo.setText("");
                    secChldLivOut.setVisibility(View.VISIBLE);
                    lineChldLivOut.setVisibility(View.VISIBLE);
             }
             else
             {
                    seclbl106.setVisibility(View.VISIBLE);
                    linelbl106.setVisibility(View.VISIBLE);
                    seclbl106a.setVisibility(View.VISIBLE);
                    linelbl106a.setVisibility(View.VISIBLE);
                    secSonLivWWo.setVisibility(View.VISIBLE);
                    lineSonLivWWo.setVisibility(View.VISIBLE);
                    secDaugLivWWo.setVisibility(View.VISIBLE);
                    lineDaugLivWWo.setVisibility(View.VISIBLE);
                    secChldLivOut.setVisibility(View.VISIBLE);
                    lineChldLivOut.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclbl106=(LinearLayout)findViewById(R.id.seclbl106);
         linelbl106=(View)findViewById(R.id.linelbl106);
         seclbl106a=(LinearLayout)findViewById(R.id.seclbl106a);
         linelbl106a=(View)findViewById(R.id.linelbl106a);
         secSonLivWWo=(LinearLayout)findViewById(R.id.secSonLivWWo);
         lineSonLivWWo=(View)findViewById(R.id.lineSonLivWWo);
         VlblSonLivWWo=(TextView) findViewById(R.id.VlblSonLivWWo);
         txtSonLivWWo=(EditText) findViewById(R.id.txtSonLivWWo);
         secDaugLivWWo=(LinearLayout)findViewById(R.id.secDaugLivWWo);
         lineDaugLivWWo=(View)findViewById(R.id.lineDaugLivWWo);
         VlblDaugLivWWo=(TextView) findViewById(R.id.VlblDaugLivWWo);
         txtDaugLivWWo=(EditText) findViewById(R.id.txtDaugLivWWo);
         secChldLivOut=(LinearLayout)findViewById(R.id.secChldLivOut);
         lineChldLivOut=(View)findViewById(R.id.lineChldLivOut);
         VlblChldLivOut = (TextView) findViewById(R.id.VlblChldLivOut);
         rdogrpChldLivOut = (RadioGroup) findViewById(R.id.rdogrpChldLivOut);
         
         rdoChldLivOut1 = (RadioButton) findViewById(R.id.rdoChldLivOut1);
         rdoChldLivOut2 = (RadioButton) findViewById(R.id.rdoChldLivOut2);
         rdogrpChldLivOut.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpChldLivOut = new String[] {"1","2"};
             for (int i = 0; i < rdogrpChldLivOut.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpChldLivOut.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpChldLivOut[i];
             }

             if(rbData.equalsIgnoreCase("2"))
             {
                    seclbl108.setVisibility(View.GONE);
                    linelbl108.setVisibility(View.GONE);
                    seclbl108a.setVisibility(View.GONE);
                    linelbl108a.setVisibility(View.GONE);
                    secSonLivOut.setVisibility(View.GONE);
                    lineSonLivOut.setVisibility(View.GONE);
                    txtSonLivOut.setText("");
                    secDaugLivOut.setVisibility(View.GONE);
                    lineDaugLivOut.setVisibility(View.GONE);
                    txtDaugLivOut.setText("");
                    seclbl109.setVisibility(View.VISIBLE);
                    linelbl109.setVisibility(View.VISIBLE);
                    secChldDie.setVisibility(View.VISIBLE);
                    lineChldDie.setVisibility(View.VISIBLE);
             }
             else
             {
                    seclbl108.setVisibility(View.VISIBLE);
                    linelbl108.setVisibility(View.VISIBLE);
                    seclbl108a.setVisibility(View.VISIBLE);
                    linelbl108a.setVisibility(View.VISIBLE);
                    secSonLivOut.setVisibility(View.VISIBLE);
                    lineSonLivOut.setVisibility(View.VISIBLE);
                    secDaugLivOut.setVisibility(View.VISIBLE);
                    lineDaugLivOut.setVisibility(View.VISIBLE);
                    secChldDie.setVisibility(View.VISIBLE);
                    lineChldDie.setVisibility(View.VISIBLE);
                    seclbl109.setVisibility(View.VISIBLE);
                    linelbl109.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclbl108=(LinearLayout)findViewById(R.id.seclbl108);
         linelbl108=(View)findViewById(R.id.linelbl108);
         seclbl108a=(LinearLayout)findViewById(R.id.seclbl108a);
         linelbl108a=(View)findViewById(R.id.linelbl108a);

         secSonLivOut=(LinearLayout)findViewById(R.id.secSonLivOut);
         lineSonLivOut=(View)findViewById(R.id.lineSonLivOut);
         VlblSonLivOut=(TextView) findViewById(R.id.VlblSonLivOut);
         txtSonLivOut=(EditText) findViewById(R.id.txtSonLivOut);
         secDaugLivOut=(LinearLayout)findViewById(R.id.secDaugLivOut);
         lineDaugLivOut=(View)findViewById(R.id.lineDaugLivOut);
         VlblDaugLivOut=(TextView) findViewById(R.id.VlblDaugLivOut);
         txtDaugLivOut=(EditText) findViewById(R.id.txtDaugLivOut);
         seclbl109=(LinearLayout)findViewById(R.id.seclbl109);
         linelbl109=(View)findViewById(R.id.linelbl109);

         secChldDie=(LinearLayout)findViewById(R.id.secChldDie);
         lineChldDie=(View)findViewById(R.id.lineChldDie);
         VlblChldDie = (TextView) findViewById(R.id.VlblChldDie);
         rdogrpChldDie = (RadioGroup) findViewById(R.id.rdogrpChldDie);
         
         rdoChldDie1 = (RadioButton) findViewById(R.id.rdoChldDie1);
         rdoChldDie2 = (RadioButton) findViewById(R.id.rdoChldDie2);
         rdogrpChldDie.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpChldDie = new String[] {"1","2"};
             for (int i = 0; i < rdogrpChldDie.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpChldDie.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpChldDie[i];
             }

             if(rbData.equalsIgnoreCase("2"))
             {
                    seclbl110.setVisibility(View.GONE);
                    linelbl110.setVisibility(View.GONE);
                    seclbl110a.setVisibility(View.GONE);
                    linelbl110a.setVisibility(View.GONE);
                    secBoyDied.setVisibility(View.GONE);
                    lineBoyDied.setVisibility(View.GONE);
                    txtBoyDied.setText("");
                    secGirlDied.setVisibility(View.GONE);
                    lineGirlDied.setVisibility(View.GONE);
                    txtGirlDied.setText("");
                    seclbl111.setVisibility(View.VISIBLE);
                    linelbl111.setVisibility(View.VISIBLE);
                    secNotLivBrth.setVisibility(View.VISIBLE);
                    lineNotLivBrth.setVisibility(View.VISIBLE);
             }
             else
             {
                    seclbl110.setVisibility(View.VISIBLE);
                    linelbl110.setVisibility(View.VISIBLE);
                    seclbl110a.setVisibility(View.VISIBLE);
                    linelbl110a.setVisibility(View.VISIBLE);
                    secBoyDied.setVisibility(View.VISIBLE);
                    lineBoyDied.setVisibility(View.VISIBLE);
                    secGirlDied.setVisibility(View.VISIBLE);
                    lineGirlDied.setVisibility(View.VISIBLE);
                    seclbl111.setVisibility(View.VISIBLE);
                    linelbl111.setVisibility(View.VISIBLE);
                    secNotLivBrth.setVisibility(View.VISIBLE);
                    lineNotLivBrth.setVisibility(View.VISIBLE);
//                    seclbl109.setVisibility(View.VISIBLE);
//                    linelbl109.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         }); 
         seclbl110=(LinearLayout)findViewById(R.id.seclbl110);
         linelbl110=(View)findViewById(R.id.linelbl110);
         seclbl110a=(LinearLayout)findViewById(R.id.seclbl110a);
         linelbl110a=(View)findViewById(R.id.linelbl110a);
         secBoyDied=(LinearLayout)findViewById(R.id.secBoyDied);
         lineBoyDied=(View)findViewById(R.id.lineBoyDied);
         VlblBoyDied=(TextView) findViewById(R.id.VlblBoyDied);
         txtBoyDied=(EditText) findViewById(R.id.txtBoyDied);
         secGirlDied=(LinearLayout)findViewById(R.id.secGirlDied);
         lineGirlDied=(View)findViewById(R.id.lineGirlDied);
         VlblGirlDied=(TextView) findViewById(R.id.VlblGirlDied);
         txtGirlDied=(EditText) findViewById(R.id.txtGirlDied);
         seclbl111=(LinearLayout)findViewById(R.id.seclbl111);
         linelbl111=(View)findViewById(R.id.linelbl111);
         secNotLivBrth=(LinearLayout)findViewById(R.id.secNotLivBrth);
         lineNotLivBrth=(View)findViewById(R.id.lineNotLivBrth);
         VlblNotLivBrth = (TextView) findViewById(R.id.VlblNotLivBrth);
         rdogrpNotLivBrth = (RadioGroup) findViewById(R.id.rdogrpNotLivBrth);
         
         rdoNotLivBrth1 = (RadioButton) findViewById(R.id.rdoNotLivBrth1);
         rdoNotLivBrth2 = (RadioButton) findViewById(R.id.rdoNotLivBrth2);
         rdogrpNotLivBrth.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
             String rbData = "";
             RadioButton rb;
             String[] d_rdogrpNotLivBrth = new String[] {"1","2"};
             for (int i = 0; i < rdogrpNotLivBrth.getChildCount(); i++)
             {
               rb = (RadioButton)rdogrpNotLivBrth.getChildAt(i);
               if (rb.isChecked()) rbData = d_rdogrpNotLivBrth[i];
             }

             if(rbData.equalsIgnoreCase("2"))
             {
                    secTotLB.setVisibility(View.GONE);
                    lineTotLB.setVisibility(View.GONE);
                    txtTotLB.setText("");
                    seclbl113.setVisibility(View.VISIBLE);
                    linelbl113.setVisibility(View.VISIBLE);
                    secTotPregOut.setVisibility(View.VISIBLE);
                    lineTotPregOut.setVisibility(View.VISIBLE);
                    secCurPreg.setVisibility(View.VISIBLE);
                    lineCurPreg.setVisibility(View.VISIBLE);
             }
             else
             {
                    secTotLB.setVisibility(View.VISIBLE);
                    lineTotLB.setVisibility(View.VISIBLE);
                    seclbl113.setVisibility(View.VISIBLE);
                    linelbl113.setVisibility(View.VISIBLE);
                    secTotPregOut.setVisibility(View.VISIBLE);
                    lineTotPregOut.setVisibility(View.VISIBLE);
                    secCurPreg.setVisibility(View.VISIBLE);
                    lineCurPreg.setVisibility(View.VISIBLE);
//                    seclbl113.setVisibility(View.VISIBLE);
//                    linelbl113.setVisibility(View.VISIBLE);
             }
            }
         public void onNothingSelected(AdapterView<?> adapterView) {
             return;
            } 
         });

         secTotLB=(LinearLayout)findViewById(R.id.secTotLB);
         lineTotLB=(View)findViewById(R.id.lineTotLB);
         VlblTotLB=(TextView) findViewById(R.id.VlblTotLB);
         txtTotLB=(EditText) findViewById(R.id.txtTotLB);
         seclbl113=(LinearLayout)findViewById(R.id.seclbl113);
         linelbl113=(View)findViewById(R.id.linelbl113);
         secTotPregOut=(LinearLayout)findViewById(R.id.secTotPregOut);
         lineTotPregOut=(View)findViewById(R.id.lineTotPregOut);
         VlblTotPregOut=(TextView) findViewById(R.id.VlblTotPregOut);
         txtTotPregOut=(EditText) findViewById(R.id.txtTotPregOut);
         secCurPreg=(LinearLayout)findViewById(R.id.secCurPreg);
         lineCurPreg=(View)findViewById(R.id.lineCurPreg);
         VlblCurPreg = (TextView) findViewById(R.id.VlblCurPreg);
         rdogrpCurPreg = (RadioGroup) findViewById(R.id.rdogrpCurPreg);
         
         rdoCurPreg1 = (RadioButton) findViewById(R.id.rdoCurPreg1);
         rdoCurPreg2 = (RadioButton) findViewById(R.id.rdoCurPreg2);
         rdoCurPreg3 = (RadioButton) findViewById(R.id.rdoCurPreg3);

         rdogrpCurPreg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
             @Override
             public void onCheckedChanged(RadioGroup radioGroup,int radioButtonID) {
                 String rbData = "";
                 RadioButton rb;
                 String[] d_rdogrpCurPreg = new String[] {"1","2","3"};
                 for (int i = 0; i < rdogrpCurPreg.getChildCount(); i++)
                 {
                     rb = (RadioButton)rdogrpCurPreg.getChildAt(i);
                     if (rb.isChecked()) rbData = d_rdogrpCurPreg[i];
                 }

                 if(! rbData.equalsIgnoreCase("1"))
                 {
                     secLMPDate.setVisibility(View.GONE);
                     lineLMPDate.setVisibility(View.GONE);
                     dtpLMPDate.setText("");
                 }
                 else
                 {
                     secLMPDate.setVisibility(View.VISIBLE);
                     lineLMPDate.setVisibility(View.VISIBLE);
                 }
             }
             public void onNothingSelected(AdapterView<?> adapterView) {
                 return;
             }
         });


         secLMPDate=(LinearLayout)findViewById(R.id.secLMPDate);
         lineLMPDate=(View)findViewById(R.id.lineLMPDate);
         VlblLMPDate=(TextView) findViewById(R.id.VlblLMPDate);
         dtpLMPDate=(EditText) findViewById(R.id.dtpLMPDate);


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
         dtpLMPDate.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 final int DRAWABLE_RIGHT  = 2;
                 if(event.getAction() == MotionEvent.ACTION_UP) {
                     if(event.getRawX() >= (dtpLMPDate.getRight() - dtpLMPDate.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                         VariableID = "btnLMPDate"; showDialog(DATE_DIALOG);
                      return true;
                     }
                 }
                 return false;
             }
         });

         //Hide all skip variables
         secVStatusOth.setVisibility(View.GONE);
         lineVStatusOth.setVisibility(View.GONE);
         secMarriageStatus.setVisibility(View.GONE);
         lineMarriageStatus.setVisibility(View.GONE);
         rdogrpMarriageStatus.clearCheck();
         seclbl02.setVisibility(View.GONE);
         linelbl02.setVisibility(View.GONE);
//         seclbl03.setVisibility(View.GONE);
//         linelbl03.setVisibility(View.GONE);
         seclbl04.setVisibility(View.GONE);
         linelbl04.setVisibility(View.GONE);
         seclbl103.setVisibility(View.GONE);
         linelbl103.setVisibility(View.GONE);
         secMarMon.setVisibility(View.GONE);
         lineMarMon.setVisibility(View.GONE);
         txtMarMon.setText("");
//         secMarYear.setVisibility(View.GONE);
//         lineMarYear.setVisibility(View.GONE);
         txtMarYear.setText("");
//         secMarDK.setVisibility(View.GONE);
//         lineMarDK.setVisibility(View.GONE);
         chkMarDK.setChecked(false);
         secGaveBirth.setVisibility(View.GONE);
         lineGaveBirth.setVisibility(View.GONE);
         rdogrpGaveBirth.clearCheck();
         secChildLivWWo.setVisibility(View.GONE);
         lineChildLivWWo.setVisibility(View.GONE);
         rdogrpChildLivWWo.clearCheck();
         seclbl106.setVisibility(View.GONE);
         linelbl106.setVisibility(View.GONE);
         seclbl106a.setVisibility(View.GONE);
         linelbl106a.setVisibility(View.GONE);
         secSonLivWWo.setVisibility(View.GONE);
         lineSonLivWWo.setVisibility(View.GONE);
         txtSonLivWWo.setText("");
         secDaugLivWWo.setVisibility(View.GONE);
         lineDaugLivWWo.setVisibility(View.GONE);
         txtDaugLivWWo.setText("");
         secChldLivOut.setVisibility(View.GONE);
         lineChldLivOut.setVisibility(View.GONE);
         rdogrpChldLivOut.clearCheck();
         seclbl108.setVisibility(View.GONE);
         linelbl108.setVisibility(View.GONE);
         seclbl108a.setVisibility(View.GONE);
         linelbl108a.setVisibility(View.GONE);
         secSonLivOut.setVisibility(View.GONE);
         lineSonLivOut.setVisibility(View.GONE);
         txtSonLivOut.setText("");
         secDaugLivOut.setVisibility(View.GONE);
         lineDaugLivOut.setVisibility(View.GONE);
         txtDaugLivOut.setText("");
         seclbl109.setVisibility(View.GONE);
         linelbl109.setVisibility(View.GONE);
         secChldDie.setVisibility(View.GONE);
         lineChldDie.setVisibility(View.GONE);
         rdogrpChldDie.clearCheck();
         seclbl110.setVisibility(View.GONE);
         linelbl110.setVisibility(View.GONE);
         seclbl110a.setVisibility(View.GONE);
         linelbl110a.setVisibility(View.GONE);
         secBoyDied.setVisibility(View.GONE);
         lineBoyDied.setVisibility(View.GONE);
         txtBoyDied.setText("");
         secGirlDied.setVisibility(View.GONE);
         lineGirlDied.setVisibility(View.GONE);
         txtGirlDied.setText("");
         seclbl111.setVisibility(View.GONE);
         linelbl111.setVisibility(View.GONE);
         secNotLivBrth.setVisibility(View.GONE);
         lineNotLivBrth.setVisibility(View.GONE);
         rdogrpNotLivBrth.clearCheck();
         secTotLB.setVisibility(View.GONE);
         lineTotLB.setVisibility(View.GONE);
         txtTotLB.setText("");
         seclbl113.setVisibility(View.GONE);
         linelbl113.setVisibility(View.GONE);
         secTotPregOut.setVisibility(View.GONE);
         lineTotPregOut.setVisibility(View.GONE);
         txtTotPregOut.setText("");
         secCurPreg.setVisibility(View.GONE);
         lineCurPreg.setVisibility(View.GONE);
         rdogrpCurPreg.clearCheck();
         secLMPDate.setVisibility(View.GONE);
         lineLMPDate.setVisibility(View.GONE);


         txtVill.setEnabled(false);
         txtBari.setEnabled(false);
         txtHH.setEnabled(false);

         txtVill.setText(VILL);
         txtVill.setFocusable(false);
         txtBari.setText(BARI);
         txtBari.setFocusable(false);
         txtHH.setText(HH);

        Button cmdSave = (Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) { 
            DataSave();
        }});

        //DataSearch(VILL,BARI,HH,MSLNO);
     }
     catch(Exception  e)
     {
         Connection.MessageBox(PregHis.this, e.getMessage());
         return;
     }
 }

     @Override
     protected void onResume() {
         super.onResume();
         dataSeatch = true;
     }

     private void ClearForm()
     {
         spnVStatus.setSelection(0);
         secMarriageStatus.setVisibility(View.GONE);
         lineMarriageStatus.setVisibility(View.GONE);
         rdogrpMarriageStatus.clearCheck();
         seclbl02.setVisibility(View.GONE);
         linelbl02.setVisibility(View.GONE);
         seclbl04.setVisibility(View.GONE);
         linelbl04.setVisibility(View.GONE);
         seclbl103.setVisibility(View.GONE);
         linelbl103.setVisibility(View.GONE);
         secMarMon.setVisibility(View.GONE);
         lineMarMon.setVisibility(View.GONE);
         txtMarMon.setText("");
         txtMarYear.setText("");
         chkMarDK.setChecked(false);
         secGaveBirth.setVisibility(View.GONE);
         lineGaveBirth.setVisibility(View.GONE);
         rdogrpGaveBirth.clearCheck();
         secChildLivWWo.setVisibility(View.GONE);
         lineChildLivWWo.setVisibility(View.GONE);
         rdogrpChildLivWWo.clearCheck();
         seclbl106.setVisibility(View.GONE);
         linelbl106.setVisibility(View.GONE);
         seclbl106a.setVisibility(View.GONE);
         linelbl106a.setVisibility(View.GONE);
         secSonLivWWo.setVisibility(View.GONE);
         lineSonLivWWo.setVisibility(View.GONE);
         txtSonLivWWo.setText("");
         secDaugLivWWo.setVisibility(View.GONE);
         lineDaugLivWWo.setVisibility(View.GONE);
         txtDaugLivWWo.setText("");
         secChldLivOut.setVisibility(View.GONE);
         lineChldLivOut.setVisibility(View.GONE);
         rdogrpChldLivOut.clearCheck();
         seclbl108.setVisibility(View.GONE);
         linelbl108.setVisibility(View.GONE);
         seclbl108a.setVisibility(View.GONE);
         linelbl108a.setVisibility(View.GONE);
         secSonLivOut.setVisibility(View.GONE);
         lineSonLivOut.setVisibility(View.GONE);
         txtSonLivOut.setText("");
         secDaugLivOut.setVisibility(View.GONE);
         lineDaugLivOut.setVisibility(View.GONE);
         txtDaugLivOut.setText("");
         seclbl109.setVisibility(View.GONE);
         linelbl109.setVisibility(View.GONE);
         secChldDie.setVisibility(View.GONE);
         lineChldDie.setVisibility(View.GONE);
         rdogrpChldDie.clearCheck();
         seclbl110.setVisibility(View.GONE);
         linelbl110.setVisibility(View.GONE);
         seclbl110a.setVisibility(View.GONE);
         linelbl110a.setVisibility(View.GONE);
         secBoyDied.setVisibility(View.GONE);
         lineBoyDied.setVisibility(View.GONE);
         txtBoyDied.setText("");
         secGirlDied.setVisibility(View.GONE);
         lineGirlDied.setVisibility(View.GONE);
         txtGirlDied.setText("");
         seclbl111.setVisibility(View.GONE);
         linelbl111.setVisibility(View.GONE);
         secNotLivBrth.setVisibility(View.GONE);
         lineNotLivBrth.setVisibility(View.GONE);
         rdogrpNotLivBrth.clearCheck();
         secTotLB.setVisibility(View.GONE);
         lineTotLB.setVisibility(View.GONE);
         txtTotLB.setText("");
         seclbl113.setVisibility(View.GONE);
         linelbl113.setVisibility(View.GONE);
         secTotPregOut.setVisibility(View.GONE);
         lineTotPregOut.setVisibility(View.GONE);
         txtTotPregOut.setText("");
         secCurPreg.setVisibility(View.GONE);
         lineCurPreg.setVisibility(View.GONE);
         rdogrpCurPreg.clearCheck();
         secLMPDate.setVisibility(View.GONE);
         lineLMPDate.setVisibility(View.GONE);
     }


     private void DataSave()
 {
   try
     {
 
         String DV="";

         if(txtVill.getText().toString().length()==0 & secVill.isShown())
           {
             Connection.MessageBox(PregHis.this, "Required field: গ্রাম.");
             txtVill.requestFocus(); 
             return;	
           }
         else if(txtBari.getText().toString().length()==0 & secBari.isShown())
           {
             Connection.MessageBox(PregHis.this, "Required field: বাড়ি.");
             txtBari.requestFocus(); 
             return;	
           }
         else if(txtHH.getText().toString().length()==0 & secHH.isShown())
           {
             Connection.MessageBox(PregHis.this, "Required field: খানা.");
             txtHH.requestFocus(); 
             return;	
           }
//         else if(txtMSlNo.getText().toString().length()==0 & secMSlNo.isShown())
//           {
//             Connection.MessageBox(PregHis.this, "Required field: MSlNo.");
//             txtMSlNo.requestFocus();
//             return;
//           }
//         else if(Integer.valueOf(txtMSlNo.getText().toString().length()==0 ? "1" : txtMSlNo.getText().toString()) < 1 || Integer.valueOf(txtMSlNo.getText().toString().length()==0 ? "99" : txtMSlNo.getText().toString()) > 99)
//           {
//             Connection.MessageBox(PregHis.this, "Value should be between 1 and 99(MSlNo).");
//             txtMSlNo.requestFocus();
//             return;
//           }
         else if(txtPNo.getText().toString().length()==0 & secPNo.isShown())
           {
             Connection.MessageBox(PregHis.this, "Required field: PNo.");
             txtPNo.requestFocus(); 
             return;	
           }
         DV = Global.DateValidate(dtpVDate.getText().toString());
         if(DV.length()!=0 & secVDate.isShown())
           {
             Connection.MessageBox(PregHis.this, DV);
             dtpVDate.requestFocus(); 
             return;	
           }
         else if(spnVStatus.getSelectedItemPosition()==0  & secVStatus.isShown())
           {
             Connection.MessageBox(PregHis.this, "সাক্ষাতকারের ফলাফল খালি রাখা যাবেনা.");
             spnVStatus.requestFocus(); 
             return;	
           }
         else if(txtVStatusOth.getText().toString().length()==0 & secVStatusOth.isShown())
           {
             Connection.MessageBox(PregHis.this, "অন্যান্য উল্লেখ করুন খালি রাখা যাবেনা.");
             txtVStatusOth.requestFocus(); 
             return;	
           }
         
         else if(!rdoMarriageStatus1.isChecked() & !rdoMarriageStatus2.isChecked() & secMarriageStatus.isShown())
           {
              Connection.MessageBox(PregHis.this, "এ খানা রেজিস্ট্রেশন ফর্ম চেক করুন , এই খানায় কি কোন ৫০ বছরের নীচে মহিলা আছে যিনি বিবাহিত অথবা কখনও বিবাহ হয়েছিল ?.");
              rdoMarriageStatus1.requestFocus();
              return;
           }
         else if(txtMarMon.getText().toString().length()==0 & secMarMon.isShown() )
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১০৩. বিবাহের মাস খালি রাখা যাবেনা.");
             txtMarMon.requestFocus(); 
             return;	
           }
         /*if (Integer.valueOf(txtMarMon.getText().toString().length()==0 ? "99" : txtMarMon.getText().toString()) == 99)
         {

         }*/
         else if(!chkMarDK.isChecked() & (Integer.valueOf(txtMarMon.getText().toString().length()==0 ? "1" : txtMarMon.getText().toString()) < 1 || Integer.valueOf(txtMarMon.getText().toString().length()==0 ? "12" : txtMarMon.getText().toString()) > 12))
           {
             Connection.MessageBox(PregHis.this, "১০৩. মাস অবশ্যই ১ থেকে ১২ এর ভিতর হতে হবে.");
             txtMarMon.requestFocus(); 
             return;	
           }
//         if (Integer.valueOf(txtMarYear.getText().toString().length()==0 ? "9999" : txtMarYear.getText().toString()) == 9999)
//         {
//
//         }
         else if(txtMarYear.getText().toString().length()==0 & secMarMon.isShown())
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১০৩. বিবাহের বছর খালি রাখা যাবেনা.");
             txtMarYear.requestFocus(); 
             return;	
           }
         /*else if(!chkMarDK.isChecked() & (Integer.valueOf(txtMarYear.getText().toString().length()==0 ? "1960" : txtMarYear.getText().toString()) < 1960 || Integer.valueOf(txtMarYear.getText().toString().length()==0 ? "2004" : txtMarYear.getText().toString()) > 2004))
           {
             Connection.MessageBox(PregHis.this, "১০৩. বছর অবশ্যই ১৯৬০ থেকে ২০০৪ এর ভিতর হতে হবে.");
             txtMarYear.requestFocus(); 
             return;	
           }*/
         String MSL = spnMSlNo.getSelectedItemPosition()==0?"":Global.Left(spnMSlNo.getSelectedItem().toString(),2);
         String BDate= C.ReturnSingleValue("Select BDate from Member where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH +"' and MSlNo='"+ MSL +"'");
         if(!chkMarDK.isChecked() & Global.DateDifferenceDays("01/"+txtMarMon.getText().toString()+"/"+txtMarYear.getText().toString(),Global.DateConvertDMY(BDate))<0){
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১০৩. বিবাহের তারিখ জন্ম তারিখ["+ Global.DateConvertDMY(BDate) +"] থেকে বড় হবে।");
             txtMarMon.requestFocus();
             return;
         }
         else if(!chkMarDK.isChecked() & Global.DateDifferenceDays(Global.DateNowDMY(),"01/"+txtMarMon.getText().toString()+"/"+txtMarYear.getText().toString())<0){
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১০৩. বিবাহের তারিখ আজকের তারিখ["+ Global.DateConvertDMY(Global.DateNowYMD()) +"] থেকে ছোট হবে।");
             txtMarMon.requestFocus();
             return;
         }


         if(!rdoGaveBirth1.isChecked() & !rdoGaveBirth2.isChecked() & secGaveBirth.isShown())
           {
              Connection.MessageBox(PregHis.this, "প্রশ্ন ১০৪. আপনার কি কখনও কোন ছেলেমেয়ে হয়েছে একটি অপশন নির্বাচন করুন.");
              rdoGaveBirth1.requestFocus();
              return;
           }
         
         else if(!rdoChildLivWWo1.isChecked() & !rdoChildLivWWo2.isChecked() & secChildLivWWo.isShown())
           {
              Connection.MessageBox(PregHis.this, "প্রশ্ন ১০৫. একটি অপশন নির্বাচন করুন (আপনি জন্ম দিয়েছেন , এমন ছেলে অথবা মেয়ে কি এখন আপনার সাথে বসবাস করছে ?).");
              rdoChildLivWWo1.requestFocus();
              return;
           }
         else if(txtSonLivWWo.getText().toString().length()==0 & secSonLivWWo.isShown())
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১০৬ a) কয়জন ছেলে আপনার সাথে থাকে খালি রাখা যাবেনা.");
             txtSonLivWWo.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtSonLivWWo.getText().toString().length()==0 ? "0" : txtSonLivWWo.getText().toString()) < 0 || Integer.valueOf(txtSonLivWWo.getText().toString().length()==0 ? "30" : txtSonLivWWo.getText().toString()) > 30)
           {
             Connection.MessageBox(PregHis.this, "১০৬ a) কয়জন ছেলে আপনার সাথে থাকে অবশ্যই ০ থেকে ৩০ এর ভিতর হতে হবে.");
             txtSonLivWWo.requestFocus(); 
             return;	
           }
         else if(txtDaugLivWWo.getText().toString().length()==0 & secDaugLivWWo.isShown())
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১০৬ b) কয়জন মেয়ে আপনার সাথে থাকে খালি রাখা যাবেনা.");
             txtDaugLivWWo.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtDaugLivWWo.getText().toString().length()==0 ? "00" : txtDaugLivWWo.getText().toString()) < 00 || Integer.valueOf(txtDaugLivWWo.getText().toString().length()==0 ? "30" : txtDaugLivWWo.getText().toString()) > 30)
           {
             Connection.MessageBox(PregHis.this, "১০৬ b) কয়জন মেয়ে আপনার সাথে থাকে অবশ্যই ০ থেকে ৩০ এর ভিতর হতে হবে.");
             txtDaugLivWWo.requestFocus(); 
             return;	
           }
         
         else if(!rdoChldLivOut1.isChecked() & !rdoChldLivOut2.isChecked() & secChldLivOut.isShown())
           {
              Connection.MessageBox(PregHis.this, "প্রশ্ন ১০৭. আপনি এমন কোন ছেলে বা মেয়ে জন্ম দিয়েছেন কি যারা এখন জীবিত আছে কিন্তু এখন আপনার সাথে থাকে না ? একটি অপশন নির্বাচন করুন .");
              rdoChldLivOut1.requestFocus();
              return;
           }
         else if(txtSonLivOut.getText().toString().length()==0 & secSonLivOut.isShown())
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১০৮ a) অন্য কোথাও থাকে এমন ছেলের সংখ্যা. খালি রাখা যাবেনা");
             txtSonLivOut.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtSonLivOut.getText().toString().length()==0 ? "00" : txtSonLivOut.getText().toString()) < 00 || Integer.valueOf(txtSonLivOut.getText().toString().length()==0 ? "20" : txtSonLivOut.getText().toString()) > 20)
           {
             Connection.MessageBox(PregHis.this, "১০৮ a) অন্য কোথাও থাকে এমন ছেলের সংখ্যা) অবশ্যই ০ থেকে ২০ এর ভিতর হতে হবে");
             txtSonLivOut.requestFocus(); 
             return;	
           }
         else if(txtDaugLivOut.getText().toString().length()==0 & secDaugLivOut.isShown())
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১০৮ b) অন্য কোথাও থাকে এমন মেয়ের সংখ্যা. খালি রাখা যাবেনা");
             txtDaugLivOut.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtDaugLivOut.getText().toString().length()==0 ? "00" : txtDaugLivOut.getText().toString()) < 00 || Integer.valueOf(txtDaugLivOut.getText().toString().length()==0 ? "20" : txtDaugLivOut.getText().toString()) > 20)
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১০৮ b) অন্য কোথাও থাকে এমন মেয়ের সংখ্যা) অবশ্যই ০ থেকে ২০ এর ভিতর হতে হবে ");
             txtDaugLivOut.requestFocus(); 
             return;	
           }
         
         else if(!rdoChldDie1.isChecked() & !rdoChldDie2.isChecked() & secChldDie.isShown())
           {
              Connection.MessageBox(PregHis.this, "প্রশ্ন ১০৯. একটি অপশন নির্বাচন করুন (আপনি কি কখনও এমন কোন ছেলে বা মেয়ে জন্ম দিয়েছেন যে জীবিত জন্ম নিয়েছিল কিন্তু পরে মারা গিয়েছে ?).");
              rdoChldDie1.requestFocus();
              return;
           }
         else if(txtBoyDied.getText().toString().length()==0 & secBoyDied.isShown())
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১১০ a) ছেলে মারা গেছে কতজন খালি রাখা যাবেনা.");
             txtBoyDied.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtBoyDied.getText().toString().length()==0 ? "0" : txtBoyDied.getText().toString()) < 0 || Integer.valueOf(txtBoyDied.getText().toString().length()==0 ? "20" : txtBoyDied.getText().toString()) > 20)
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১১০ a) ছেলে মারা গেছে কতজন অবশ্যই ০ থেকে ২০ এর ভিতর হতে হবে ");
             txtBoyDied.requestFocus(); 
             return;	
           }
         else if(txtGirlDied.getText().toString().length()==0 & secGirlDied.isShown())
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১১০ b) মেয়ে মারা গেছে কতজন খালি রাখা যাবেনা.");
             txtGirlDied.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtGirlDied.getText().toString().length()==0 ? "0" : txtGirlDied.getText().toString()) < 0 || Integer.valueOf(txtGirlDied.getText().toString().length()==0 ? "20" : txtGirlDied.getText().toString()) > 20)
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১১০ b) মেয়ে মারা গেছে কতজন অবশ্যই ০ থেকে ২০ এর ভিতর হতে হবে ");
             txtGirlDied.requestFocus(); 
             return;	
           }
         
         else if(!rdoNotLivBrth1.isChecked() & !rdoNotLivBrth2.isChecked() & secNotLivBrth.isShown())
           {
              Connection.MessageBox(PregHis.this, "প্রশ্ন ১১১. একটি অপশন নির্বাচন করুন (আপনার জীবনে কি কখনও পেটে বাচ্চা আসার পর এধরনের কোন ঘটনা অর্থাৎ জীবিত বাচ্চা জন্ম না দেয়ার মত ঘটনা ঘটেছে?).");
              rdoNotLivBrth1.requestFocus();
              return;
           }
         else if(txtTotLB.getText().toString().length()==0 & secTotLB.isShown())
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১১২. মোট কতগুলো গর্ভাবস্থার ক্ষেত্রে জীবিত বাচ্চা জন্ম না দেয়ার মত ঘটনা ঘটেছে খালি রাখা যাবেনা.");
             txtTotLB.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtTotLB.getText().toString().length()==0 ? "1" : txtTotLB.getText().toString()) < 1 || Integer.valueOf(txtTotLB.getText().toString().length()==0 ? "30" : txtTotLB.getText().toString()) > 30)
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১১২. মোট কতগুলো গর্ভাবস্থার ক্ষেত্রে জীবিত বাচ্চা জন্ম না দেয়ার মত ঘটনা ঘটেছে অবশ্যই ১ থেকে ৩০ এর ভিতর হতে হবে .");
             txtTotLB.requestFocus(); 
             return;	
           }
         else if(txtTotPregOut.getText().toString().length()==0 & secTotPregOut.isShown())
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১১৩. মোট গর্ভ ফলাফলের সংখ্যা. খালি রাখা যাবেনা");
             txtTotPregOut.requestFocus(); 
             return;	
           }
         else if(Integer.valueOf(txtTotPregOut.getText().toString().length()==0 ? "0" : txtTotPregOut.getText().toString()) < 0 || Integer.valueOf(txtTotPregOut.getText().toString().length()==0 ? "40" : txtTotPregOut.getText().toString()) > 40)
           {
             Connection.MessageBox(PregHis.this, "প্রশ্ন ১১৩. মোট গর্ভ ফলাফলের সংখ্যা) মোট গর্ভ ফলাফলের সংখ্যা) অবশ্যই ০০ থেকে ৪০ এর ভিতর হতে হবে ");
             txtTotPregOut.requestFocus(); 
             return;	
           }
         
         else if(!rdoCurPreg1.isChecked() & !rdoCurPreg2.isChecked() & !rdoCurPreg3.isChecked() & secCurPreg.isShown())
           {
              Connection.MessageBox(PregHis.this, "১১৪. আপনি কি বর্তমানে গর্ভবতী, এখান থেকে একটি অপশন নির্বাচন করুন .");
              rdoCurPreg1.requestFocus();
              return;
           }
         DV = Global.DateValidate(dtpLMPDate.getText().toString());
         if(DV.length()!=0 & secLMPDate.isShown())
           {
             Connection.MessageBox(PregHis.this, "শেষ মাসিকের তারিখ সঠিক নয়।");
             dtpLMPDate.requestFocus(); 
             return;	
           }
         else if(txtPNo.getText().length()!=11)
         {
             Connection.MessageBox(PregHis.this, "PNo অবশ্যই ১১ ডিজিট হতে হবে।");
             txtPNo.requestFocus();
             return;
         }

         Integer Q106A=0,Q106B=0,Q108A=0,Q108B=0,Q110A=0,Q110B=0,Q112=0;

         Q106A = Integer.valueOf(txtSonLivWWo.getText().toString().length() == 0 ? "0" : txtSonLivWWo.getText().toString());
         Q106B = Integer.valueOf(txtDaugLivWWo.getText().toString().length() == 0 ? "0" : txtDaugLivWWo.getText().toString());

         Q108A = Integer.valueOf(txtSonLivOut.getText().toString().length() == 0 ? "0" : txtSonLivOut.getText().toString());
         Q108B = Integer.valueOf(txtDaugLivOut.getText().toString().length() == 0 ? "0" : txtDaugLivOut.getText().toString());

         Q110A = Integer.valueOf(txtBoyDied.getText().toString().length() == 0 ? "0" : txtBoyDied.getText().toString());
         Q110B = Integer.valueOf(txtGirlDied.getText().toString().length() == 0 ? "0" : txtGirlDied.getText().toString());

         Q112 = Integer.valueOf(txtTotLB.getText().toString().length() == 0 ? "0" : txtTotLB.getText().toString());

         if(rdoChildLivWWo1.isChecked() & Q106A+Q106B==0){
             Connection.MessageBox(PregHis.this, "১0৫ হ্যাঁ হলে, ১০৬ এর যোগফল ০ হবে না।");
             txtSonLivWWo.requestFocus();
             return;
         }else if(rdoChldLivOut1.isChecked() & Q108A+Q108B==0){
             Connection.MessageBox(PregHis.this, "১0৭ হ্যাঁ হলে, ১০৮ এর যোগফল ০ হবে না।");
             txtSonLivOut.requestFocus();
             return;
         }else if(rdoChldDie1.isChecked() & Q110A+Q110B==0){
             Connection.MessageBox(PregHis.this, "১0৯ হ্যাঁ হলে, ১১০ এর যোগফল ০ হবে না।");
             txtBoyDied.requestFocus();
             return;
         }else if(rdoNotLivBrth1.isChecked() & Q112==0){
             Connection.MessageBox(PregHis.this, "১১১ হ্যাঁ হলে, ১১২ ০ হবে না।");
             txtTotLB.requestFocus();
             return;
         }

         if(Q106A+Q106B+Q108A+Q108B+Q110A+Q110B+Q112 !=Integer.valueOf(txtTotPregOut.getText().toString().length()==0?"0":txtTotPregOut.getText().toString()))
         {
             Connection.MessageBox(PregHis.this, "১১৩ - মোট গর্ভ ফলাফলের সংখ্যা সঠিক নয় (প্রশ্ন ১০৬, ১০৮, ১১০, এবং ১১২ উত্তর গুলো যাচাই করুন।) ।");
             return;
         }

         if(rdoCurPreg1.isChecked() & Global.DateDifferenceDays(dtpLMPDate.getText().toString(),Global.DateConvertDMY(BDate))<0){
             Connection.MessageBox(PregHis.this, "১১৫. শেষ মাসিকের তারিখ জন্ম তারিখ["+ Global.DateConvertDMY(BDate) +"] থেকে বড় হবে।");
             dtpLMPDate.requestFocus();
             return;
         }


         String SQL = "";
         RadioButton rb;

         PregHis_DataModel objSave = new PregHis_DataModel();
         objSave.setVill(txtVill.getText().toString());
         objSave.setBari(txtBari.getText().toString());
         objSave.setHH(txtHH.getText().toString());
//         objSave.setMSlNo((spnMSlNo.getSelectedItem().toString()));
         objSave.setMSlNo((spnMSlNo.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnMSlNo.getSelectedItem().toString(), "-")));
         objSave.setPNo(txtPNo.getText().toString());
         objSave.setVDate(dtpVDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpVDate.getText().toString()) : dtpVDate.getText().toString());
         objSave.setVStatus((spnVStatus.getSelectedItemPosition() == 0 ? "" : Connection.SelectedSpinnerValue(spnVStatus.getSelectedItem().toString(), "-")));
         objSave.setVStatusOth(txtVStatusOth.getText().toString());
         String[] d_rdogrpMarriageStatus = new String[] {"1","2"};
         objSave.setMarriageStatus("");
         for (int i = 0; i < rdogrpMarriageStatus.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpMarriageStatus.getChildAt(i);
             if (rb.isChecked()) objSave.setMarriageStatus(d_rdogrpMarriageStatus[i]);
         }

         objSave.setMarMon(txtMarMon.getText().toString());
         objSave.setMarYear(txtMarYear.getText().toString());
         objSave.setMarDK((chkMarDK.isChecked()?"1":(secMarMon.isShown()?"2":"")));
         String[] d_rdogrpGaveBirth = new String[] {"1","2"};
         objSave.setGaveBirth("");
         for (int i = 0; i < rdogrpGaveBirth.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpGaveBirth.getChildAt(i);
             if (rb.isChecked()) objSave.setGaveBirth(d_rdogrpGaveBirth[i]);
         }

         String[] d_rdogrpChildLivWWo = new String[] {"1","2"};
         objSave.setChildLivWWo("");
         for (int i = 0; i < rdogrpChildLivWWo.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpChildLivWWo.getChildAt(i);
             if (rb.isChecked()) objSave.setChildLivWWo(d_rdogrpChildLivWWo[i]);
         }

         objSave.setSonLivWWo(txtSonLivWWo.getText().toString());
         objSave.setDaugLivWWo(txtDaugLivWWo.getText().toString());
         String[] d_rdogrpChldLivOut = new String[] {"1","2"};
         objSave.setChldLivOut("");
         for (int i = 0; i < rdogrpChldLivOut.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpChldLivOut.getChildAt(i);
             if (rb.isChecked()) objSave.setChldLivOut(d_rdogrpChldLivOut[i]);
         }

         objSave.setSonLivOut(txtSonLivOut.getText().toString());
         objSave.setDaugLivOut(txtDaugLivOut.getText().toString());
         String[] d_rdogrpChldDie = new String[] {"1","2"};
         objSave.setChldDie("");
         for (int i = 0; i < rdogrpChldDie.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpChldDie.getChildAt(i);
             if (rb.isChecked()) objSave.setChldDie(d_rdogrpChldDie[i]);
         }

         objSave.setBoyDied(txtBoyDied.getText().toString());
         objSave.setGirlDied(txtGirlDied.getText().toString());
         String[] d_rdogrpNotLivBrth = new String[] {"1","2"};
         objSave.setNotLivBrth("");
         for (int i = 0; i < rdogrpNotLivBrth.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpNotLivBrth.getChildAt(i);
             if (rb.isChecked()) objSave.setNotLivBrth(d_rdogrpNotLivBrth[i]);
         }

         objSave.setTotLB(txtTotLB.getText().toString());
         objSave.setTotPregOut(txtTotPregOut.getText().toString());
         String[] d_rdogrpCurPreg = new String[] {"1","2","9"};
         objSave.setCurPreg("");
         for (int i = 0; i < rdogrpCurPreg.getChildCount(); i++)
         {
             rb = (RadioButton)rdogrpCurPreg.getChildAt(i);
             if (rb.isChecked()) objSave.setCurPreg(d_rdogrpCurPreg[i]);
         }

         objSave.setLMPDate(dtpLMPDate.getText().toString().length() > 0 ? Global.DateConvertYMD(dtpLMPDate.getText().toString()) : dtpLMPDate.getText().toString());
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

             Connection.MessageBox(PregHis.this, "Saved Successfully");
         }
         else{
             Connection.MessageBox(PregHis.this, status);
             return;
         }
     }
     catch(Exception  e)
     {
         Connection.MessageBox(PregHis.this, e.getMessage());
         return;
     }
 }

 private void DataSearch(String Vill, String Bari, String HH, String MSlNo)
     {
       try
        {
           RadioButton rb;
           PregHis_DataModel d = new PregHis_DataModel();
           String SQL = "Select * from "+ TableName +"  Where Vill='"+ Vill +"' and Bari='"+ Bari +"' and HH='"+ HH +"' and MSlNo='"+ MSlNo +"'";
           List<PregHis_DataModel> data = d.SelectAll(this, SQL);
           for(PregHis_DataModel item : data){
             //txtVill.setText(item.getVill());
             //txtBari.setText(item.getBari());
             //txtHH.setText(item.getHH());
//             spnMSlNo.setSelection(Global.SpinnerItemPositionAnyLength(spnMSlNo, item.getMSlNo()));
             txtPNo.setText(item.getPNo());
             dtpVDate.setText(item.getVDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getVDate()));
             spnVStatus.setSelection(Global.SpinnerItemPositionAnyLength(spnVStatus, item.getVStatus()));
             txtVStatusOth.setText(item.getVStatusOth());
             String[] d_rdogrpMarriageStatus = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpMarriageStatus.length; i++)
             {
                 if (item.getMarriageStatus().equals(String.valueOf(d_rdogrpMarriageStatus[i])))
                 {
                     rb = (RadioButton)rdogrpMarriageStatus.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtMarMon.setText(item.getMarMon());
             txtMarYear.setText(item.getMarYear());
             if(item.getMarDK().equals("1"))
             {
                chkMarDK.setChecked(true);
             }
             else if(item.getMarDK().equals("2"))
             {
                chkMarDK.setChecked(false);
             }
             String[] d_rdogrpGaveBirth = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpGaveBirth.length; i++)
             {
                 if (item.getGaveBirth().equals(String.valueOf(d_rdogrpGaveBirth[i])))
                 {
                     rb = (RadioButton)rdogrpGaveBirth.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             String[] d_rdogrpChildLivWWo = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpChildLivWWo.length; i++)
             {
                 if (item.getChildLivWWo().equals(String.valueOf(d_rdogrpChildLivWWo[i])))
                 {
                     rb = (RadioButton)rdogrpChildLivWWo.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtSonLivWWo.setText(item.getSonLivWWo());
             txtDaugLivWWo.setText(item.getDaugLivWWo());
             String[] d_rdogrpChldLivOut = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpChldLivOut.length; i++)
             {
                 if (item.getChldLivOut().equals(String.valueOf(d_rdogrpChldLivOut[i])))
                 {
                     rb = (RadioButton)rdogrpChldLivOut.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtSonLivOut.setText(item.getSonLivOut());
             txtDaugLivOut.setText(item.getDaugLivOut());
             String[] d_rdogrpChldDie = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpChldDie.length; i++)
             {
                 if (item.getChldDie().equals(String.valueOf(d_rdogrpChldDie[i])))
                 {
                     rb = (RadioButton)rdogrpChldDie.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtBoyDied.setText(item.getBoyDied());
             txtGirlDied.setText(item.getGirlDied());
             String[] d_rdogrpNotLivBrth = new String[] {"1","2"};
             for (int i = 0; i < d_rdogrpNotLivBrth.length; i++)
             {
                 if (item.getNotLivBrth().equals(String.valueOf(d_rdogrpNotLivBrth[i])))
                 {
                     rb = (RadioButton)rdogrpNotLivBrth.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             txtTotLB.setText(item.getTotLB());
             txtTotPregOut.setText(item.getTotPregOut());
             String[] d_rdogrpCurPreg = new String[] {"1","2","9"};
             for (int i = 0; i < d_rdogrpCurPreg.length; i++)
             {
                 if (item.getCurPreg().equals(String.valueOf(d_rdogrpCurPreg[i])))
                 {
                     rb = (RadioButton)rdogrpCurPreg.getChildAt(i);
                     rb.setChecked(true);
                 }
             }
             dtpLMPDate.setText(item.getLMPDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getLMPDate()));
           }
        }
        catch(Exception  e)
        {
            Connection.MessageBox(PregHis.this, e.getMessage());
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
             else if (VariableID.equals("btnLMPDate"))
              {
                  dtpDate = (EditText)findViewById(R.id.dtpLMPDate);
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