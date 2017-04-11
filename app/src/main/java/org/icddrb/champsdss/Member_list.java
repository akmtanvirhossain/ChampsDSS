package org.icddrb.champsdss;
//Android Manifest Code
 //<activity android:name=".Member_list" android:label="Member: List" />
 import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Common.Connection;
import Common.Global;
import Common.Utility;

public class Member_list extends Activity {
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
    static String TableName;

    TextView lblHeading;
    Button btnAdd;
    Button btnRefresh;

    EditText txtVill;
    EditText txtBari;
    EditText txtHH;

    static String DEVICEID  = "";
    static String ENTRYUSER = "";

    Bundle IDbundle;
    private static String CurrentVillage;
    private static String CurrentVCode;

    static String STARTTIME = "";
    static String VILL = "";
    static String BARI = "";
    static String HH = "";
    static String MSLNO = "";

    Button btnMemberName;
    Button btnSES;
    Button btnPregHis;
 public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
   try
     {
         setContentView(R.layout.member_list1);
         C = new Connection(this);
         g = Global.getInstance();

         STARTTIME = g.CurrentTime24();
         DEVICEID  = g.getDeviceNo();
         ENTRYUSER = g.getUserId();

         IDbundle=getIntent().getExtras();
         VILL = IDbundle.getString("Vill");
         BARI = IDbundle.getString("Bari");
         HH = IDbundle.getString("HH");
         MSLNO = IDbundle.getString("MSlNo");

         final TextView txtVill = (TextView) findViewById(R.id.txtVill);
         final TextView txtBari = (TextView) findViewById(R.id.txtBari);
         final TextView txtHH = (TextView) findViewById(R.id.txtHH);

         TextView lblHHHead=(TextView)findViewById(R.id.lblHHHead);
         lblHHHead.setText(": "+IDbundle.getString("HHHead"));

         txtVill.setText(VILL);
         txtBari.setText(BARI);
         txtHH.setText(HH);

         LinearLayout secMenu;
         secMenu= (LinearLayout) findViewById(R.id.secMenu);
         secMenu.setVisibility(View.VISIBLE);

         TableName = "Member";
         lblHeading = (TextView)findViewById(R.id.lblHeading);

         ImageButton cmdBack = (ImageButton) findViewById(R.id.cmdBack);
         cmdBack.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 String infoMiss = C.ReturnSingleValue("Select count(*)TotalMiss from Member where Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH + "' and length(Sex)=0");

                 if (Integer.valueOf(infoMiss) > 0) {
                     Connection.MessageBox(Member_list.this, infoMiss + " জন সদস্যের তথ্য আপডেট করা হয় নাই");
                     return;
                 }
                 if (!C.Existence("Select PNo from Member where Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH + "'")) {
                     Connection.MessageBox(Member_list.this, "কমপক্ষে একজন সদস্য এন্ট্রি করতে হবে.");
                     return;
                 }
                 if(!C.Existence("Select Rth from Member  where Rth='01' and Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH +"'"))
                 {
                     Connection.MessageBox(Member_list.this, "খানায় কমপক্ষে একজন খানা প্রধান থাকতে হবে।");
                     return;
                 }
                 if (!C.Existence("Select VStatus from SES where Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH + "'")) {
                     Connection.MessageBox(Member_list.this, "Required: খানার তথ্য  খালি রাখা যাবেনা ");
                     return;
                 }
                 AlertDialog.Builder adb = new AlertDialog.Builder(Member_list.this);
                 adb.setTitle("Close");
                 adb.setMessage("আপনি কি এই ফরম থেকে বের হতে চান [হ্যাঁ/না]?");
                 adb.setNegativeButton("না", null);
                 adb.setPositiveButton("হ্যাঁ", new AlertDialog.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                         /*Bundle IDbundle = new Bundle();
                         IDbundle.putString("Vill", VILL);
                         IDbundle.putString("Bari", BARI);
                         IDbundle.putString("HH", HH);
                         *//*
                         Intent intent = new Intent(getApplicationContext(), Household_list.class);
                         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         //intent.putExtras(IDbundle);
                         getApplicationContext().startActivity(intent);
                         finish();
//                        startActivity(new Intent(Member_list.this, Household_list.class));
                         */
                         Intent returnIntent = new Intent();
                         returnIntent.putExtra("res", "hh");
                         setResult(Activity.RESULT_OK, returnIntent);
                         finish();
                     }});
                 adb.show();
             }});

         btnRefresh = (Button) findViewById(R.id.btnRefresh);
         btnRefresh.setOnClickListener(new View.OnClickListener() {

             public void onClick(View view) {
                   //write your code here
                 DataSearch(VILL, BARI, HH);
             }});

         btnSES = (Button) findViewById(R.id.btnSES);
         btnSES.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (!C.Existence("Select PNo from Member where Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH + "'")) {
                     Connection.MessageBox(Member_list.this, "Required: কমপক্ষে একজন সদস্য এন্ট্রি করতে হবে.");
                     return;
                 }
                 if(!C.Existence("Select Rth from Member  where Rth='01' and Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH +"'"))
                 {
                     Connection.MessageBox(Member_list.this, "খানায় কমপক্ষে একজন খানা প্রধান থাকতে হবে।");
                     return;
                 }
                 String infoMiss = C.ReturnSingleValue("Select count(*)TotalMiss from Member where Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH + "' and length(Sex)=0");

                 if (Integer.valueOf(infoMiss) > 0) {
                     Connection.MessageBox(Member_list.this, infoMiss + " জন সদস্যের তথ্য আপডেট করা হয় নাই");
                     return;
                 }

//                 Toast.makeText(Member_list.this, "Vill:"+VILL+"/n Bari:"+BARI+"/n HH:"+HH, Toast.LENGTH_SHORT).show();
                 Intent f1;
                 f1 = new Intent(getApplicationContext(), SES.class);
                 IDbundle.putString("Vill", VILL);
                 IDbundle.putString("Bari", BARI);
                 IDbundle.putString("HH", HH);
                 f1.putExtras(IDbundle);
                 startActivityForResult(f1, 1);

             }
         });
         btnPregHis = (Button) findViewById(R.id.btnPregHis);
         btnPregHis.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (!C.Existence("Select PNo from Member where Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH + "'")) {
                     Connection.MessageBox(Member_list.this, "Required: কমপক্ষে একজন সদস্য এন্ট্রি করতে হবে.");
                     return;
                 }
                 if(!C.Existence("Select Rth from Member  where Rth='01' and Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH +"'"))
                 {
                     Connection.MessageBox(Member_list.this, "খানায় কমপক্ষে একজন খানা প্রধান থাকতে হবে।");
                     return;
                 }

                 if(!C.Existence("Select Sex from Member  where Sex='2' and Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH +"'"))
                 {
                     Connection.MessageBox(Member_list.this, "খানায় কমপক্ষে একজন মহিলা সদস্য এন্ট্রি করতে হবে.");
                     return;
                 }

                 if(!C.Existence("Select Sex, AgeY,MS from Member Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH + "' and (julianday('now')-julianday(BDate))<=18262 and Sex='2' and MS<>'30'"))
                 {
                     Connection.MessageBox(Member_list.this, "খানায় ৫০ বছরের কম বয়সের কখনও বিবাহ হয়েছে এমন মহিলা নেই .");
                     return;
                 }
                 String infoMiss = C.ReturnSingleValue("Select count(*)TotalMiss from Member where Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH + "' and length(Sex)=0");

                 if (Integer.valueOf(infoMiss) > 0) {

                     Connection.MessageBox(Member_list.this, infoMiss + " জন সদস্যের তথ্য আপডেট করা হয় নাই");

                     return;
                 }
                 Toast.makeText(Member_list.this, "Vill:"+VILL+"/n Bari:"+BARI+"/n HH:"+HH, Toast.LENGTH_SHORT).show();
                 Intent f1;
                 f1 = new Intent(getApplicationContext(), PregHis.class);
                 IDbundle.putString("Vill", VILL);
                 IDbundle.putString("Bari", BARI);
                 IDbundle.putString("HH", HH);
                 f1.putExtras(IDbundle);
                 startActivityForResult(f1, 1);

             }
         });

         Button btnMemberName = (Button) findViewById(R.id.btnMemberName);
         btnMemberName.setOnClickListener(new View.OnClickListener() {

             public void onClick(View view) {
//                 Toast.makeText(Member_list.this, "Vill:"+VILL+"/n Bari:"+BARI+"/n HH:"+HH, Toast.LENGTH_SHORT).show();

                 IDbundle.putString("Vill", VILL);
                 IDbundle.putString("Bari", BARI);
                 IDbundle.putString("HH", HH);
                 MemberNameForm(VILL, BARI,HH);
             }

         });

         txtVill.setEnabled(false);
         txtBari.setEnabled(false);
         txtHH.setEnabled(false);
         DataSearch(VILL,BARI,HH);
         DataStatus();
         /*if (C.Existence("Select VStatus from SES where Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH + "'")) {
             btnSES.setBackgroundColor(Color.GREEN);
         }

         //String TotRh = C.ReturnSingleValue("Select TotRWO from Household Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH + "'");
         String TotRh = C.ReturnSingleValue("Select Count(*)TotRWO from Member Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH + "' and (julianday(EnDate)-julianday(BDate))<=18262 and Sex='2' and MS<>'30'");
         String PregHis = C.ReturnSingleValue("Select count(*)Total from PregHis Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH + "'");

//         Toast.makeText(this, ""+Integer.valueOf(TotRh), Toast.LENGTH_LONG).show();
         if (Integer.valueOf(TotRh) == Integer.valueOf(PregHis))
         {
             btnPregHis.setBackgroundColor(Color.GREEN);
         }*/

     }
     catch(Exception  e)
     {
         Connection.MessageBox(Member_list.this, e.getMessage());
         return;
     }
 }
 
@Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if (resultCode == Activity.RESULT_CANCELED) {
         //Write your code if there's no result
     } else {
         DataSearch(VILL,BARI,HH);
         DataStatus();
     }
 }

    private void DataStatus()
    {
        if (C.Existence("Select VStatus from SES where Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH + "'")) {
            btnSES.setBackgroundColor(Color.GREEN);
        }
        else
        {
            btnSES.setBackgroundResource(R.drawable.button_style);
        }

        String TotRh = C.ReturnSingleValue("Select Count(*)TotRWO from Member Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH + "' and (julianday(EnDate)-julianday(BDate))<=18262 and Sex='2' and MS<>'30'");
        String PregHis = C.ReturnSingleValue("Select count(*)Total from PregHis Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH + "'");

        if (Integer.valueOf(TotRh)>0 & Integer.valueOf(TotRh) == Integer.valueOf(PregHis))
        {
            btnPregHis.setBackgroundColor(Color.GREEN);
        }
        else{
            btnPregHis.setBackgroundResource(R.drawable.button_style);
        }

//        String TotalMember = C.ReturnSingleValue("Select TotMem from Household Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH + "'");
//        String TotMem = C.ReturnSingleValue("Select count(*)Total from Member Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH + "'");
//
//        if (Integer.valueOf(TotalMember) == Integer.valueOf(TotMem))
//        {
//            btnMemberName.setBackgroundColor(Color.GREEN);
//        }
//        else{
//            btnMemberName.setBackgroundResource(R.drawable.button_style);
//        }

    }
 private void DataSearch(String Vill, String Bari, String HH )
     {
       try
        {
           Member_DataModel d = new Member_DataModel();
             String SQL = "Select * from "+ TableName +"  Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH +"'";
             List<Member_DataModel> data = d.SelectAll(this, SQL);
             dataList.clear();

             dataAdapter = null;

             ListView list = (ListView)findViewById(R.id.lstData);
             HashMap<String, String> map;
            Integer i = 0;
             for(Member_DataModel item : data){
                 map = new HashMap<String, String>();
                 map.put("Vill", item.getVill());
                 map.put("Bari", item.getBari());
                 map.put("HH", item.getHH());
                 map.put("MSlNo", item.getMSlNo());
                 map.put("PNo", item.getPNo());
                 map.put("Name", item.getName());
                 map.put("Rth", item.getRth());
                 map.put("Sex", item.getSex());
                 map.put("BDate", item.getBDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getBDate()));
                 map.put("AgeY", item.getAgeY());
                 map.put("MoNo", item.getMoNo());
                 map.put("FaNo", item.getFaNo());
                 map.put("Edu", item.getEdu());
                 map.put("MS", item.getMS());
                 map.put("Ocp", item.getOcp());
                 map.put("Sp1", item.getSp1());
                 map.put("Sp2", item.getSp2());
                 map.put("Sp3", item.getSp3());
                 map.put("Sp4", item.getSp4());
                 map.put("EnType", item.getEnType());
                 map.put("EnDate", item.getEnDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getEnDate()));
                 map.put("ExType", item.getExType());
                 map.put("ExDate", item.getExDate().toString().length()==0 ? "" : Global.DateConvertDMY(item.getExDate()));
                 map.put("sl", i.toString());
                 map.put("needreview", item.getNeedReview());
                 i+=1;
                 dataList.add(map);
             }
             dataAdapter = new SimpleAdapter(Member_list.this, dataList, R.layout.member_list,new String[] {"rowsec"},
                           new int[] {R.id.secListRow});
             list.setAdapter(new DataListAdapter(this, dataAdapter));
            //Utility.setListViewHeightBasedOnChildren(list);
        }
        catch(Exception  e)
        {
            Connection.MessageBox(Member_list.this, e.getMessage());
            return;
        }
     }

     private void MemberNameForm(final String VILL, final String BARI , final String HH) {
         try {
             final Dialog dialog = new Dialog(Member_list.this);
             dialog.setTitle("Member Name Form");
             dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
             dialog.setContentView(R.layout.member_name);
             dialog.setCanceledOnTouchOutside(true);
             dialog.setCancelable(true);

             Window window = dialog.getWindow();
             WindowManager.LayoutParams wlp = window.getAttributes();

             wlp.gravity = Gravity.TOP;
             wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
             window.setAttributes(wlp);

             final TextView txtVill = (TextView) dialog.findViewById(R.id.txtVill);
             final TextView txtBari = (TextView) dialog.findViewById(R.id.txtBari);
             final TextView txtHH = (TextView) dialog.findViewById(R.id.txtHH);
             final TextView txtMSlNo = (TextView) dialog.findViewById(R.id.txtMSlNo);
             final TextView txtName = (TextView) dialog.findViewById(R.id.txtName);

             txtName.requestFocus();
             final  TextView txtNameHint=(TextView)dialog.findViewById(R.id.txtNameHint);

             txtVill.setText(VILL);
             txtBari.setText(BARI);
             txtHH.setText(HH);

             txtVill.setEnabled(false);
             txtBari.setEnabled(false);
             txtHH.setEnabled(false);
             txtMSlNo.setEnabled(false);

             txtMSlNo.setText(MemberSerial(VILL, BARI,HH));

             if(txtMSlNo.getText().toString().equals("01"))
             {
                 txtName.setText(IDbundle.getString("HHHead"));
                 txtNameHint.setVisibility(View.VISIBLE);
             }else
             {
                 txtNameHint.setVisibility(View.GONE);
             }

             Button cmdContactNoSave = (Button) dialog.findViewById(R.id.cmdSave);
             cmdContactNoSave.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View arg0) {

                     if (txtName.getText().toString().length() == 0)
                     {
                         Connection.MessageBox(Member_list.this, "Required field: সদস্যের নাম খালি রাখা যাবেনা ।");
                         txtName.requestFocus();
                         return;
                     }

                     String TotalMember = C.ReturnSingleValue("Select TotMem from Household Where Vill='"+ VILL +"' and Bari='"+ BARI +"' and HH='"+ HH + "'");

                     if(txtMSlNo.getText().toString().equals(TotalMember))
                     {
                         AlertDialog.Builder adb = new AlertDialog.Builder(Member_list.this);
                         adb.setTitle("Close");
                         adb.setMessage("খানা নিবন্ধন ফর্মে মোট সদস্য  " + TotalMember +  " জন, আপনি কি আরো সদস্য  অন্তর্ভুক্ত করতে চান [হ্যাঁ/না]?");
                         adb.setPositiveButton("হ্যাঁ", null);
                         adb.setNegativeButton("না", new AlertDialog.OnClickListener() {
                             public void onClick(DialogInterface dialog, int which) {
                                 finish();
                                 Intent intent = new Intent(getApplicationContext(),Member_list.class);
                                 intent.putExtras(IDbundle);
                                 startActivityForResult(intent, 1);
                             }});
                         adb.show();
                     }

                     //***
                     Member_DataModel objSave = new Member_DataModel();
                     objSave.setVill(txtVill.getText().toString());
                     objSave.setBari(txtBari.getText().toString());
                     objSave.setHH(txtHH.getText().toString());
                     objSave.setMSlNo(txtMSlNo.getText().toString());
                     objSave.setName(txtName.getText().toString());
                     objSave.setEnDt(Global.DateTimeNowYMDHMS());
                     objSave.setStartTime(STARTTIME);
                     objSave.setEndTime(g.CurrentTime24());
                     objSave.setDeviceID(DEVICEID);
                     objSave.setEntryUser(ENTRYUSER); //from data entry user list
                     String status = objSave.SaveUpdateData(Member_list.this);

                     DataSearch(VILL,BARI,HH);
                     txtMSlNo.setText(MemberSerial(VILL,BARI,HH));
                     txtName.setText("");
                     txtName.requestFocus();
                     if(txtMSlNo.getText().toString().equals("01"))
                     {
                         txtNameHint.setVisibility(View.VISIBLE);
//
                     }else
                     {
                         txtNameHint.setVisibility(View.GONE);
                     }

                 }
             });

            ImageButton cmdBack = (ImageButton)dialog.findViewById(R.id.cmdBack);
            cmdBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    dialog.dismiss();
                }
            });


             dialog.show();
         } catch (Exception e) {
             Connection.MessageBox(Member_list.this, e.getMessage());
             return;
         }
     }

     private String MemberSerial(String VILL, String BARI , String HH)
     {
         String M = C.ReturnSingleValue("Select (ifnull(max(cast(MSlNo as int)),0)+1)serial from Member where Vill='" + VILL + "' and Bari='" + BARI + "' and HH='" + HH + "'");
         M = Global.Right("0"+M,2);
         return M;
     }

 public class DataListAdapter extends BaseAdapter 
 {
     private Context context;
     private SimpleAdapter dataAdap;

     public DataListAdapter(Context c, SimpleAdapter da){ context = c;  dataAdap = da; }
     public int getCount() {  return dataAdap.getCount();  }
     public Object getItem(int position) {  return position;  }
     public long getItemId(int position) {  return position;  }
     public View getView(final int position, View convertView, ViewGroup parent) {
         LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         if (convertView == null) {
 	        convertView = inflater.inflate(R.layout.member_row, null); 
 	        }
         LinearLayout   secListRow = (LinearLayout)convertView.findViewById(R.id.secListRow);

         final TextView Vill = (TextView)convertView.findViewById(R.id.Vill);
         final TextView Bari = (TextView)convertView.findViewById(R.id.Bari);
         final TextView HH = (TextView)convertView.findViewById(R.id.HH);
         final TextView MSlNo = (TextView)convertView.findViewById(R.id.MSlNo);
         final TextView PNo = (TextView)convertView.findViewById(R.id.PNo);
         final TextView Name = (TextView)convertView.findViewById(R.id.Name);
         final TextView Rth = (TextView)convertView.findViewById(R.id.Rth);
         final TextView Sex = (TextView)convertView.findViewById(R.id.Sex);
         final TextView BDate = (TextView)convertView.findViewById(R.id.BDate);
         final TextView AgeY = (TextView)convertView.findViewById(R.id.AgeY);
         final TextView MoNo = (TextView)convertView.findViewById(R.id.MoNo);
         final TextView FaNo = (TextView)convertView.findViewById(R.id.FaNo);
         final TextView Edu = (TextView)convertView.findViewById(R.id.Edu);
         final TextView MS = (TextView)convertView.findViewById(R.id.MS);
         final TextView Ocp = (TextView)convertView.findViewById(R.id.Ocp);
         final TextView Sp1 = (TextView)convertView.findViewById(R.id.Sp1);
         final TextView Sp2 = (TextView)convertView.findViewById(R.id.Sp2);
         final TextView Sp3 = (TextView)convertView.findViewById(R.id.Sp3);
         final TextView Sp4 = (TextView)convertView.findViewById(R.id.Sp4);
         final TextView EnType = (TextView)convertView.findViewById(R.id.EnType);
         final TextView EnDate = (TextView)convertView.findViewById(R.id.EnDate);
         final TextView ExType = (TextView)convertView.findViewById(R.id.ExType);
         final TextView ExDate = (TextView)convertView.findViewById(R.id.ExDate);
         final ImageButton delMember = (ImageButton) convertView.findViewById(R.id.delMember);

         final HashMap<String, String> o = (HashMap<String, String>) dataAdap.getItem(position);
         Vill.setText(o.get("Vill"));
         Bari.setText(o.get("Bari"));
         HH.setText(o.get("HH"));
         MSlNo.setText(o.get("MSlNo"));
         PNo.setText(o.get("PNo"));
         Name.setText(o.get("Name"));
         Rth.setText(o.get("Rth"));
         Sex.setText(o.get("Sex"));
         BDate.setText(o.get("BDate"));
         AgeY.setText(o.get("AgeY"));
         MoNo.setText(o.get("MoNo"));
         FaNo.setText(o.get("FaNo"));
         Edu.setText(o.get("Edu"));
         MS.setText(o.get("MS"));
         Ocp.setText(o.get("Ocp"));
         Sp1.setText(o.get("Sp1"));
         Sp2.setText(o.get("Sp2"));
         Sp3.setText(o.get("Sp3"));
         Sp4.setText(o.get("Sp4"));
         EnType.setText(o.get("EnType"));
         EnDate.setText(o.get("EnDate"));
         ExType.setText(o.get("ExType"));
         ExDate.setText(o.get("ExDate"));

         if (o.get("PNo").length() == 0) {
             MSlNo.setTextColor(Color.RED);
             Name.setTextColor(Color.RED);
             delMember.setVisibility(View.VISIBLE);
         } else {
             if(o.get("needreview").equals("1")){
                 MSlNo.setTextColor(Color.parseColor("#006699"));
                 Name.setTextColor(Color.parseColor("#006699"));
                 delMember.setVisibility(View.INVISIBLE);
             }else {
                 MSlNo.setTextColor(Color.BLACK);
                 Name.setTextColor(Color.BLACK);
                 delMember.setVisibility(View.INVISIBLE);
             }
         }

         if(Integer.valueOf(o.get("sl"))%2==0) {
             secListRow.setBackgroundColor(Color.parseColor("#F3F3F3"));
             delMember.setBackgroundColor(Color.parseColor("#F3F3F3"));
         }
         else {
             secListRow.setBackgroundColor(Color.parseColor("#FFFFFF"));
             delMember.setBackgroundColor(Color.parseColor("#FFFFFF"));
         }



         secListRow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               //Write your code here
               Bundle IDbundle = new Bundle();
               IDbundle.putString("Vill", o.get("Vill"));
               IDbundle.putString("Bari", o.get("Bari"));
               IDbundle.putString("HH", o.get("HH"));
               IDbundle.putString("MSlNo", o.get("MSlNo"));
               Intent f1;
               f1 = new Intent(getApplicationContext(), Member.class);
               f1.putExtras(IDbundle);
               //startActivity(f1);
                startActivityForResult(f1, 1);
            }
          });


         delMember.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 AlertDialog.Builder adb = new AlertDialog.Builder(Member_list.this);
                 adb.setTitle("মুছে ফেলা");
                 adb.setMessage("আপনি কি সদস্যঃ "+ o.get("Name") +" এর তথ্য  মুছে ফেলতে চান[হ্যাঁ/না]?");
                 adb.setNegativeButton("না", null);
                 adb.setPositiveButton("হ্যাঁ", new AlertDialog.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                      C.Save("Delete from Member where Vill='" + o.get("Vill") + "' and Bari='" + o.get("Bari") + "' and HH='" + o.get("HH") + "' and MSlNo='" + o.get("MSlNo") + "'");
                         DataSearch(o.get("Vill"),o.get("Bari"),o.get("HH"));
                     }
                 });
                 adb.show();

             }
         });

         return convertView;
       }
 }


}