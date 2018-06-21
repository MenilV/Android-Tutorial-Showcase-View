package com.menilv.testoverlay;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomDialog extends Dialog {
  private View referedView;

  public CustomDialog(@NonNull Context context) {
    super(context);
  }

  public CustomDialog(@NonNull Context context, int themeResId) {
    super(context, themeResId);
  }

  protected CustomDialog(@NonNull Context context, boolean cancelable,
      @Nullable OnCancelListener cancelListener) {
    super(context, cancelable, cancelListener);
  }

  @Override public void show() {
    RelativeLayout rl = (RelativeLayout) findViewById(R.id.parent_view);
    CircleOverlayView view = findViewById(R.id.view);
    view.setHighlightView(referedView,100);

    TextView tv = this.findViewById(R.id.textx);

    RelativeLayout.LayoutParams params =
        new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);

    params.leftMargin = referedView.getLeft();
    params.topMargin = referedView.getTop() + 180;
    params.width = referedView.getWidth();
    tv.setGravity(Gravity.CENTER);
    tv.setLayoutParams(params);



    super.show();
  }

  public void setViewReference(View v) {
    referedView = v;
  }
}
