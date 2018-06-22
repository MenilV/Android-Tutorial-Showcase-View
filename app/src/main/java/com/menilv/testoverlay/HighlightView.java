package com.menilv.testoverlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class HighlightView extends LinearLayout {
  private Bitmap bitmap;
  private int left;
  private int right;
  private int bottom;
  private int top;
  private int actionbarHeight;

  public HighlightView(Context context) {
    super(context);
  }

  public HighlightView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public HighlightView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public HighlightView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);

    if (bitmap == null) {
      createWindowFrame();
    }
    canvas.drawBitmap(bitmap, 0, 0, null);
  }

  protected void createWindowFrame() {
    bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
    Canvas osCanvas = new Canvas(bitmap);

    drawBackground(osCanvas);

    highlightView(osCanvas);
  }

  private void drawBackground(Canvas osCanvas) {
    RectF background = new RectF(0, 0, getWidth(), getHeight());
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(getResources().getColor(R.color.black));
    paint.setAlpha(190);
    osCanvas.drawRect(background, paint);
  }

  private void highlightView(Canvas osCanvas) {
    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint2.setColor(Color.TRANSPARENT);
    paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
    //int padding = 10;
    RectF rect = new RectF(left, top + actionbarHeight, right,
        bottom + actionbarHeight);
    osCanvas.drawRect(rect, paint2);
  }

  @Override public boolean isInEditMode() {
    return true;
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    super.onLayout(changed, l, t, r, b);
    bitmap = null;
  }

  public void setHighlightView(View v, int actionbarHeight) {
    this.left = v.getLeft();
    this.right = v.getRight();
    this.bottom = v.getBottom();
    this.top = v.getTop();

    this.actionbarHeight = actionbarHeight;
  }

  public void setHighlightView(int left, int top, int right, int bottom, Context context,
      int actionbarHeight) {
    this.left = left;
    this.top = top;
    this.right = right;
    this.bottom = bottom;

    this.actionbarHeight = actionbarHeight;
  }
}