package com.menilv.testoverlay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

  }

  @Override protected void onResume() {
    super.onResume();



    //final CircleOverlayView view  = findViewById(R.id.view);
    //findViewById(R.id.text).post(new Runnable() {
    //  @Override public void run() {
    //    TextView textView = findViewById(R.id.text);
    //    view.setHighlightView(textView);
    //
    //    NewOverlayLayout layout = findViewById(R.id.newlayout);
    //    layout.setHighlightView(textView);
    //  }
    //});

    final CustomDialog dialog = new CustomDialog(this, R.style.NewDialog);
    dialog.setCanceledOnTouchOutside(false);
    dialog.setCancelable(false);
    findViewById(R.id.text).post(new Runnable() {
      @Override public void run() {
        dialog.setViewReference(findViewById(R.id.text));
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_view);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        //wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();
      }
    });




  }
}
