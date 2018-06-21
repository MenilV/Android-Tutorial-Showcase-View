package com.menilv.testoverlay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

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
        dialog.setViewReference(findViewById(R.id.text));
        dialog.setContentView(R.layout.dialog_view);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.setSkipButton();
        dialog.show();
      }
    });




  }
}
