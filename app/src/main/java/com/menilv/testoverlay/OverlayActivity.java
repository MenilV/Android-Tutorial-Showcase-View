package com.menilv.testoverlay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OverlayActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_overlay);

    findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        finish();
      }
    });
  }
}
