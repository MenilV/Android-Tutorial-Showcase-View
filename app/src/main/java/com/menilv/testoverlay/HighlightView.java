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
  private Context context;
  private int height;

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

    //putTitle(osCanvas);
    //
    //putDescription(osCanvas);
    //
    //putButton(osCanvas);
  }

  private void putButton(Canvas osCanvas) {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(getResources().getColor(R.color.white));
    RectF rectF = new RectF(left, top, right, bottom);
    osCanvas.drawRect(rectF, paint);

    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint1.setColor(getResources().getColor(R.color.black));
    paint1.setTextSize(40);
    paint1.setTextAlign(Paint.Align.LEFT);
    osCanvas.drawText("Button", left+20, bottom, paint1);
  }

  private void putTitle (Canvas osCanvas){
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(getResources().getColor(R.color.white));
    paint.setTextSize(122);
    osCanvas.drawText("title", left, bottom+120, paint);
  }

  private void putDescription (Canvas osCanvas){
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(getResources().getColor(R.color.white));
    paint.setTextSize(90);
    osCanvas.drawText("description", left, bottom+220, paint);
  }

  private void drawBackground(Canvas osCanvas){
    RectF background = new RectF(0, 0, getWidth(), getHeight());
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(getResources().getColor(R.color.gray));
    paint.setAlpha(200);
    osCanvas.drawRect(background, paint);
  }

  private void highlightView(Canvas osCanvas){
    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint2.setColor(Color.TRANSPARENT);
    paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
    int padding = 10;
    RectF rect = new RectF(left-padding, top-padding + height, right+padding, bottom+padding+height);
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
    this.left= v.getLeft();
    this.right= v.getRight();
    this.bottom= v.getBottom();
    this.top= v.getTop();

    this.context = v.getContext();
    this.height = actionbarHeight;
  }
}