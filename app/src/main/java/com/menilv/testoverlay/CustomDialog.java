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
  private View referencedView;
  private boolean showSkip = false;
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
    HighlightView view = findViewById(R.id.highlighted_view);
    view.setHighlightView(referencedView, 100);

    //region title setup
    TextView title = this.findViewById(R.id.title);
    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    params.leftMargin = referencedView.getLeft();
    params.topMargin = referencedView.getTop() + 180;
    params.width = referencedView.getWidth();
    title.setGravity(Gravity.CENTER);
    title.setLayoutParams(params);
    //endregion

    //region skip button setup
    if(showSkip)
      findViewById(R.id.skip).setVisibility(View.VISIBLE);
    //endregion

    super.show();
  }

  public void setViewReference(View v) {
    if (v == null) throw new NullPointerException("view cannot be null");
    referencedView = v;
  }

  public void setSkipButton(){
    this.showSkip = true;
  }
}
