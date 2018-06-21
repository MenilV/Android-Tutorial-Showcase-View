package com.menilv.testoverlay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override protected void onResume() {
    super.onResume();
    final CustomDialog dialog = new CustomDialog(this, R.style.NewDialog);
    dialog.setCanceledOnTouchOutside(false);
    dialog.setCancelable(false);

    findViewById(R.id.text).post(new Runnable() {
      @Override public void run() {
        dialog.setViewReference(findViewById(R.id.text))
            .setTitleText("TITLE!!!")
            .setDescriptionText("DESCRIPTION TEXT TEXT TEXT TEXT TEXT")
            .setOnSkipClickListener(new View.OnClickListener() {
              @Override public void onClick(View view) {
                Log.e("LOGLOG", "skip clicked!!!");
              }
            })
            .setOnCustomButtonClickListener(new View.OnClickListener() {
              @Override public void onClick(View view) {

              }
            })
            .setSkipButtonText("Skip")
            //.setImageDrawable(getResources().getDrawable(R.drawable.arrowdown))
            .setTutorialPosition(CustomDialog.TUTORIAL_POSITION.ABOVE)
            .setCustomButtonText("Next")
            .show();
      }
    });
  }
}
