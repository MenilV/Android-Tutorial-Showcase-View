package com.menilv.testoverlay;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class NewOverlayLayout extends RelativeLayout {
  private int left;
  private int right;
  private int bottom;
  private int top;
  private Button button;
  private ViewGroup.LayoutParams params;

  public NewOverlayLayout(Context context) {
    super(context);
    init(context);
  }

  public NewOverlayLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public NewOverlayLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.custom_view_layout, this);
    button = view.findViewById(R.id.button);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    FrameLayout.LayoutParams params2 = (FrameLayout.LayoutParams) button.getLayoutParams();
    params2.setMargins(left, top, 0, 0);
    button.setLayoutParams(params2);
    button.setText("asdasdad");
  }

  public void setHighlightView(View v) {
    this.left = v.getLeft();
    this.right = v.getRight();
    this.bottom = v.getBottom();
    this.top = v.getTop();
    this.params = v.getLayoutParams();
    redraw(button);
  }

  private void redraw(View v) {
    v.invalidate();
    v.requestLayout();
  }
}
