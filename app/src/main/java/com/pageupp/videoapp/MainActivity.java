package com.pageupp.videoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;





    public static String[] urllist = {
            "http://proxynotes.com/assignmnet/test.mp4?videoid=0001",
            "http://proxynotes.com/assignmnet/test.mp4?videoid=0002",
            "http://proxynotes.com/assignmnet/test.mp4?videoid=0003",
            "http://proxynotes.com/assignmnet/test.mp4?videoid=0004",
            "http://proxynotes.com/assignmnet/test.mp4?videoid=0005",
            "http://proxynotes.com/assignmnet/test.mp4?videoid=0006",
            "http://proxynotes.com/assignmnet/test.mp4?videoid=0007",
            "http://proxynotes.com/assignmnet/test.mp4?videoid=0008",

    };

   public static String []videotitle={"Chapter 1st","Chapter 2nd","Chapter 3rd","Chapter 4th",
            "Chapter 5th","Chapter 6th","Chapter 7th","Chapter 8th"};
    String []videodurations={"Durations : 18 min","Durations : 17 min 32 sec","Durations : 17 min","Durations : 13 min 20 sec",
            "Durations : 18 min","Durations : 15 min","Durations : 12 min "," Durations : 18 min"};
    String []des={"This unit introduces us to Matter.",
            "This unit contains all about Matter's nature and states.",
            "This unit introdues us about Organization in the living world.",
            "This unit contains all about Cell, Tissues and Biological diversity",
            "This unit introdues us to the Motion.",
            "This unit contains all about Force and Work.",
            "This unit contains all about Our Environment.",
            "This unit contains all about Food Production.",
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recylerViewLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recylerViewLayoutManager);

        recyclerViewAdapter=new RecyclerViewAdapter(getApplicationContext(),this.videotitle,this.des,this.videodurations,this.urllist);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.addItemDecoration(new VerticalSpacingDecoration(64));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(ContextCompat.getDrawable(getApplicationContext(), R.drawable.item_decorator)));

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView,
                new RecyclerItemClickListener.RecyclerTouchListener() {

                    public void onClickItem(View v, int position) {

                        checkConnectionPlayVideo(position);

                    }

                    public void onLongClickItem(View v, int position) {

                    }
                }));
    }

    public void checkConnectionPlayVideo(int position){
        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cManager.getActiveNetworkInfo();
        if (nInfo != null && nInfo.isConnected()) {
            Intent intent=new Intent(MainActivity.this,PlayVideoActivity.class);
            intent.putExtra("position",position);
            startActivity(intent);

        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Connection Manager!");
            builder.setCancelable(false);
            builder.setMessage("Would you like to enable internet connection?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(Settings.ACTION_SETTINGS);
                    startActivity(i);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.create().show();

        }

    }


}
