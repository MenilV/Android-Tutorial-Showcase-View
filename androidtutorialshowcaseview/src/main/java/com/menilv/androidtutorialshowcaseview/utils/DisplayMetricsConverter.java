package com.menilv.androidtutorialshowcaseview.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class DisplayMetricsConverter {

  public static int dpToPx(float dp, Context context) {
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    return (int) ((dp * displayMetrics.density) + 0.5);
  }

  public static int pxToDp(int px, Context context) {
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    return (int) ((px / displayMetrics.density) + 0.5);
  }
}
