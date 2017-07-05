package org.icddrb.champsdss;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import Common.Connection;
import Common.Global;
import Common.ProjectSetting;
import Utility.MySharedPreferences;

public class LoginActivity extends Activity {
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    Connection C;
    Global g;
    boolean networkAvailable = false;
    int count = 0;
    TextView lblStaffType;
    String   SystemUpdateDT="";
    private ProgressDialog dialog;
    private  String Password="";
    MySharedPreferences sp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.login_activity);
            C = new Connection(this);
            g = Global.getInstance();
            sp = new MySharedPreferences();
            sp.save(this,"deviceid","");
            sp.save(this,"userid","");

            final TextView UniqueUserId      = (TextView)findViewById(R.id.UniqueUserId);
            final Spinner uid      = (Spinner)findViewById(R.id.userId);
            final EditText pass    = (EditText)findViewById(R.id.pass);
            TextView lblSystemDate = (TextView)findViewById(R.id.lblSystemDate);

            //Need to update date every time whenever shared updated system
            //*********************************************************************
            SystemUpdateDT = "05072017";  //Format: DDMMYYYY
            lblSystemDate.setText("Version: 1.0, Built on:"+ SystemUpdateDT);

            //Check for Internet connectivity
            networkAvailable = Connection.haveNetworkConnection(LoginActivity.this);


            //Rebuild Database
            String TotalTab = C.ReturnSingleValue("SELECT count(*) FROM sqlite_master WHERE type = 'table' AND name != 'android_metadata' AND name != 'sqlite_sequence'");

            if(Integer.valueOf(TotalTab) == 0)
            {
                if (networkAvailable)
                {
                    //Call Setting Form
                    finish();
                    Intent f1 = new Intent(getApplicationContext(), SettingForm.class);
                    startActivity(f1);
                    return;
                }
                else
                {
                    Connection.MessageBox(LoginActivity.this,"Internet connection is not available for building initial database.");
                    return;
                }
            }else
            {
                C.CreateTable("LastVillBari","Create table LastVillBari(Vill varchar(3), Bari varchar(4))");
            }


            //Device Unique ID
            final String UniqueID = C.ReturnSingleValue("Select DeviceId from DeviceList");
            UniqueUserId.setText("Unique ID :"+ UniqueID);
            g.setDeviceNo(UniqueID);
            sp.save(this,"deviceid",UniqueID);

//            C.Save("update Visits set Upload='2'");
//            C.Save("update Household set Upload='2'");
//            C.Save("update Member set Upload='2'");
//            C.Save("update SES set Upload='2'");
//            C.Save("update PregHis set Upload='2'");

            //Only for removing the data of training: 17 Nov 2015
            /*if(Global.DateNowDMY().equals("11/03/2017") | Global.DateNowDMY().equals("12/03/2017")  | Global.DateNowDMY().equals("13/03/2017") | Global.DateNowDMY().equals("14/03/2017") | Global.DateNowDMY().equals("15/03/2017"))
            {
                String Count = C.ReturnSingleValue("select count(*) total from DataCollector");
                if(Integer.valueOf(Count)>87) {
                    C.Save("Delete from DataCollector");
                    C.Save("Delete from Baris");
                }
                C.Save("Delete from Household where date(endt)     <= '2017-03-15'");
                C.Save("Delete from Visits where date(endt)        <= '2017-03-15'");
                C.Save("Delete from SES where date(endt)           <= '2017-03-15'");
                C.Save("Delete from PregHis where date(endt)       <= '2017-03-15'");
                C.Save("Delete from Member where date(endt)        <= '2017-03-15'");
            }*/


            //**************************************************************************************
            if (networkAvailable)
            {
                //Reqular data sync
                C.Sync_DatabaseStructure(UniqueID);
                C.Sync_Download("DataCollector",UniqueID,"Status='d'");
                //C.Sync_Download("Unions",UniqueID,"");

                //Intent syncService = new Intent(this, Sync_Service.class);
                //startService(syncService);
            }
            //**************************************************************************************
            uid.setAdapter(C.getArrayAdapter("select UserId||'-'||UserName User from DataCollector order by UserName"));
            String[] CL = uid.getSelectedItem().toString().split("-");
            uid.setSelection(Global.SpinnerItemPosition(uid,CL[0].length(),C.ReturnSingleValue("Select UserId from LastLogin")));

            Button btnClose=(Button)findViewById(R.id.btnClose);
            btnClose.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    //Connection.MessageBox(LoginActivity.this,MySharedPreferences.getValue(LoginActivity.this,"lat"));
                    finish();
                    System.exit(0);
                }
            });

            //Login -----------------------------------------------------------------------
            Button loginButton = (Button) findViewById(R.id.btnLogin);
            loginButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    try
                    {
                        String[] U = Connection.split(uid.getSelectedItem().toString(),'-');
                        g.setUserId(U[0]);

                        if (!C.Existence("Select * from DataCollector where UserId='" + U[0] + "' and Pass='" + pass.getText().toString() + "'"))
                        {
                            Connection.MessageBox(LoginActivity.this,"This is not a valid user id or password");
                            return;
                        }

                        //Store Last Login information
                        C.Save("Delete from LastLogin");
                        C.Save("Insert into LastLogin(UserId)Values('"+ U[0] +"')");


                        //Download Updated System
                        //...................................................................................
                        if (networkAvailable == true)
                        {
                            //Retrieve data from server for checking local device
                            String[] ServerVal  = Connection.split(C.ReturnResult("ReturnSingleValue","sp_ServerCheckDSS '"+ UniqueID +"'"),',');
                            String ServerDate   = ServerVal[0].toString();
                            String UpdateDT     = ServerVal[1].toString();

                            //Check for New Version
                            if (!UpdateDT.equals(SystemUpdateDT)) {
                                SystemDownload d = new SystemDownload();
                                d.setContext(getApplicationContext());
                                d.execute(Global.UpdatedSystem);
                            }
                            else
                            {
                                //check for system date
                                if(ServerDate.equals(Global.TodaysDateforCheck())==false)
                                {
                                    Connection.MessageBox(LoginActivity.this, "আপনার ট্যাব এর তারিখ সঠিক নয় ["+ Global.DateNowDMY() +"]।");
                                    startActivity(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS));
                                    return;
                                }

                                final ProgressDialog progDailog = ProgressDialog.show(LoginActivity.this, "", "Please Wait . . .", true);

                                new Thread() {
                                    public void run() {
                                        try {
                                            finish();
                                            Intent f1 = new Intent(getApplicationContext(),MainMenu.class);
                                            startActivity(f1);
                                        } catch (Exception e) {

                                        }
                                        progDailog.dismiss();
                                    }
                                }.start();
                            }
                        }
                        else
                        {
                            final ProgressDialog progDailog = ProgressDialog.show(LoginActivity.this, "", "Please Wait . . .", true);

                            new Thread() {
                                public void run() {
                                    try {
                                        finish();
                                        Intent f1 = new Intent(getApplicationContext(),MainMenu.class);
                                        startActivity(f1);
                                    } catch (Exception e) {

                                    }
                                    progDailog.dismiss();
                                }
                            }.start();
                        }
                    }
                    catch(Exception ex)
                    {
                        //Connection.MessageBox(LoginActivity.this, ex.getMessage());
                        //return;
                        final ProgressDialog progDailog = ProgressDialog.show(LoginActivity.this, "", "Please Wait . . .", true);

                        new Thread() {
                            public void run() {
                                try {
                                    finish();
                                    Intent f1 = new Intent(getApplicationContext(),MainMenu.class);
                                    startActivity(f1);
                                } catch (Exception e) {

                                }
                                progDailog.dismiss();
                            }
                        }.start();
                    }
                }
            });
        }
        catch(Exception ex)
        {
            Connection.MessageBox(LoginActivity.this, ex.getMessage());
        }
    }

    //Install application
    private void InstallApplication()
    {
        File apkfile = new File(Environment.getExternalStorageDirectory() + File.separator + ProjectSetting.NewVersionName +".apk");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
        intent.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");

        startActivity(intent);
    }

    //Downloading updated system from the central server
    class SystemDownload extends AsyncTask<String, String, Void> {
        private Context context;

        public void setContext(Context contextf){
            context = contextf;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(LoginActivity.this);
            dialog.setMessage("Downloading Updated System...");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setCancelable(false);
            dialog.show();
        }


        protected void onProgressUpdate(String... progress) {
            dialog.setProgress(Integer.parseInt(progress[0]));
            //publishProgress(progress);

        }

        //@Override
        protected void onPostExecute(String unused) {
            dialog.dismiss();
        }


        @Override
        protected Void doInBackground(String... arg0) {
            try {
                URL url = new URL(arg0[0]);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.connect();
                int lenghtOfFile = c.getContentLength();

                File file=Environment.getExternalStorageDirectory();

                file.mkdirs();
                File outputFile = new File(file.getAbsolutePath()+ File.separator + ProjectSetting.NewVersionName +".apk");

                if(outputFile.exists()){
                    outputFile.delete();
                }
                else
                {
                    outputFile.createNewFile();
                }

                FileOutputStream fos = new FileOutputStream(outputFile);

                InputStream is = c.getInputStream();


                byte[] buffer = new byte[1024];
                int len1 = 0;
                long total = 0;
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                    count++;
                }
                fos.close();
                is.close();


                InstallApplication();

                dialog.dismiss();

            } catch (IOException e) {
                //Log.e("UpdateAPP", "Update error! " + e.getMessage());
            }
            return null;
        }
    }

}

