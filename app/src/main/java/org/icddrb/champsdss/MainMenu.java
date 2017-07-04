package org.icddrb.champsdss;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import Common.Connection;
import Common.Global;
import Common.Tran_Download;

public class MainMenu extends Activity {

    static String DEVICEID = "";
    Button cmdDataSync;
    Button cmdHHInterview;
    Button cmdMember;

    Connection C;
    Global g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            //getLayoutInflater().inflate(R.layout.main_menu, frameLayout);
            setContentView(R.layout.main_menu);
            C = new Connection(this);
            g = Global.getInstance();

            turnGPSOn();
            Intent gpsService = new Intent(this, GPSService.class);
            startService(gpsService);

            DEVICEID = g.getDeviceNo();
            /*
            cmdHHInterview = (Button) findViewById(R.id.cmdHHInterview);
            cmdHHInterview.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    finish();
                    Intent f1 = new Intent(getApplicationContext(), Household_list.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("village", "");
                    bundle.putString("VCode", "");
                    //f1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    f1.putExtras(bundle);
                    startActivity(f1);
                    //startActivity(new Intent(MainMenu.this, Household_list.class));
                }
            });
            */

            cmdHHInterview = (Button) findViewById(R.id.cmdHHInterview);
            cmdHHInterview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle IDbundle = new Bundle();
                    IDbundle.putString("Village", "");
                    IDbundle.putString("VCode", "");
                    finish();
                    Intent f1;
                    f1 = new Intent(getApplicationContext(), Household_list.class);
                    f1.putExtras(IDbundle);
                    startActivity(f1);
                    //startActivity(new Intent(MainMenu.this, HouseholdIndex1.class));
                }
            });

//            cmdMember = (Button) findViewById(R.id.cmdMember);
//            cmdMember.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Bundle IDbundle = new Bundle();
//                    IDbundle.putString("Village", "");
//                    IDbundle.putString("VCode", "");
//                    finish();
//                    Intent f1;
//                    f1 = new Intent(getApplicationContext(), Member_list.class);
//                    f1.putExtras(IDbundle);
//                    startActivity(f1);
//                    //startActivity(new Intent(MainMenu.this, HouseholdIndex1.class));
//                }
//            });

            cmdDataSync = (Button) findViewById(R.id.cmdDataSync);
            cmdDataSync.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    //Check for Internet connectivity
                    if (Connection.haveNetworkConnection(MainMenu.this)) {
                    } else {
                        Connection.MessageBox(MainMenu.this, "ডেটা সিঙ্ক করার জন্য  ইন্টারনেট সংযোগ নাই.");
                        return;
                    }

                    AlertDialog.Builder adb = new AlertDialog.Builder(MainMenu.this);
                    adb.setTitle("Data Sync");
                    adb.setMessage("আপনি ডেটা সিঙ্ক করতে চান[হ্যাঁ/না]?");
                    adb.setNegativeButton("না", null);
                    adb.setPositiveButton("হ্যাঁ", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            C = new Connection(MainMenu.this);
                            final ProgressDialog progDailog = ProgressDialog.show(MainMenu.this, "", "Please Wait . . .", true);

                            new Thread() {
                                public void run() {
                                    try {
                                        String r = C.ExecuteCommandOnServer("Insert into UploadMonitor(DeviceID)Values('"+ DEVICEID +"')");

                                        Tran_Download td = new Tran_Download(MainMenu.this);

                                        //GPS Bari List
                                        td.Sync_Download("Baris",DEVICEID,"");

                                        List<String> tableList = new ArrayList<String>();
                                        tableList.add("Baris");
                                        tableList.add("Household");
                                        tableList.add("Visits");
                                        tableList.add("Member");
                                        tableList.add("SES");
                                        tableList.add("PregHis");

                                        C.Sync_Upload(tableList);
                                        C.Sync_Download("Village",DEVICEID,"");

                                    } catch (Exception e) {

                                    }
                                    progDailog.dismiss();
                                }
                            }.start();

                        }
                    });
                    adb.show();
                }
            });

            Button cmdExit = (Button) findViewById(R.id.cmdExit);
            cmdExit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainMenu.this);
                    adb.setTitle("Exit");
                    adb.setMessage("আপনি কি এই সিস্টেম থেকে বের হতে চান [হ্যাঁ/না]?");
                    adb.setNegativeButton("না", null);
                    adb.setPositiveButton("হ্যাঁ", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            /*BroadcastReceiver rec = new BroadcastReceiver()
                            {
                                String lat = "";
                                String lon = "";
                                @Override
                                public void onReceive(Context context, Intent intent)
                                {
                                    String action = intent.getAction();
                                    if(action.equals("gps_data")){
                                        lat = intent.getExtras().getString("Latitude");
                                        lon = intent.getExtras().getString("Longitude");
                                    }
                                }
                            };
                            IntentFilter filter = new IntentFilter("gps_data");
                            registerReceiver(rec, filter);*/

                            Intent gpsService = new Intent(MainMenu.this, GPSService.class);
                            stopService(gpsService);
                            finish();
                        }
                    });
                    adb.show();
                }
            });



            /* Start Usage of SharedPreferences class */
            /*
            final MySharedPreferences mySharedPreferences = new MySharedPreferences();
            final EditText txtSPrefInput = (EditText) findViewById(R.id.txtSPrefInput);
            final EditText txtSPrefOutput = (EditText) findViewById(R.id.txtSPrefOutput);
            Button cmdSPrefSave = (Button) findViewById(R.id.cmdSPrefSave);
            cmdSPrefSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mySharedPreferences.save(MainMenu.this, "KeyName", txtSPrefInput.getText().toString());
                }
            });
            Button cmdSPrefGet = (Button) findViewById(R.id.cmdSPrefGet);
            cmdSPrefGet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String value = mySharedPreferences.getValue(MainMenu.this, "KeyName");
                    txtSPrefOutput.setText(value);
                }
            });
            Button cmdSPrefRemove = (Button) findViewById(R.id.cmdSPrefRemove);
            cmdSPrefRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mySharedPreferences.removeValue(MainMenu.this, "KeyName");
                    txtSPrefOutput.setText("Removed");
                }
            });
            Button cmdSPrefClear = (Button) findViewById(R.id.cmdSPrefClear);
            cmdSPrefClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mySharedPreferences.clearSharedPreference(MainMenu.this);
                }
            });
            */
            /* End Usage of SharedPreferences class */


        } catch (Exception ex)
        {
            Connection.MessageBox(MainMenu.this,ex.getMessage());
        }
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
