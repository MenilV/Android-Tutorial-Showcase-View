package com.menilv.testoverlay;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.menilv.testoverlay.utils.DisplayMetricsConverter;

public class CustomDialog extends Dialog {

  private Context context;

  private String titleText;
  private boolean titleVisible;

  private String descriptionText;
  private boolean descriptionVisible;

  private Drawable imageDrawable;
  private boolean imageVisible;

  private String skipButtonText;
  private boolean showSkip;

  private String customButtonText;
  private boolean showCustomButton;

  private int left;
  private int top;
  private int right;
  private int bottom;

  private View.OnClickListener onSkipClickListener;
  private View.OnClickListener onCustomButtonClickListener;

  private TUTORIAL_POSITION tutorialPosition = TUTORIAL_POSITION.BELOW;
  private boolean viewSet;

  public enum TUTORIAL_POSITION {
    ABOVE,
    BELOW
  }

  public CustomDialog(@NonNull Context context) {
    super(context);
    this.context = context;
    initialize();
  }

  public CustomDialog(@NonNull Context context, int themeResId) {
    super(context, themeResId);
    this.context = context;
    initialize();
  }

  protected CustomDialog(@NonNull Context context, boolean cancelable,
      @Nullable OnCancelListener cancelListener) {
    super(context, cancelable, cancelListener);
    this.context = context;
    initialize();
  }

  private void initialize() {
    setContentView(R.layout.dialog_view);
    getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT);
  }

  @Override public void show() {

    HighlightView view = findViewById(R.id.highlighted_view);

    ActionBar actionBar = ((MainActivity) context).getSupportActionBar();
    int actionbarHeight = actionBar == null ? 0 : actionBar.getHeight();

    view.setHighlightView(left, top, right, bottom, view.getContext(), actionbarHeight);

    LinearLayout linearLayout = findViewById(R.id.container);
    RelativeLayout.LayoutParams params =
        new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);

    if (viewSet) {
      params.leftMargin = left;
      if (tutorialPosition == TUTORIAL_POSITION.ABOVE) {
        params.topMargin = top
            - DisplayMetricsConverter.dpToPx(
            linearLayout.getContext().getResources().getDimension(R.dimen.padding_medium),
            linearLayout.getContext())
            - actionbarHeight;
      } else {
        params.topMargin = top
            + DisplayMetricsConverter.dpToPx(
            linearLayout.getContext().getResources().getDimension(R.dimen.padding_medium),
            linearLayout.getContext())
            + actionbarHeight;
      }
      params.width = right - left;
    } else {
      params.addRule(RelativeLayout.CENTER_IN_PARENT);
    }

    linearLayout.setGravity(Gravity.CENTER);
    linearLayout.setLayoutParams(params);

    //region title setup
    if (titleVisible) {
      TextView title = this.findViewById(R.id.title);
      title.setVisibility(View.VISIBLE);
      title.setText(titleText);
    }
    //endregion

    //region description setup
    if (descriptionVisible) {
      TextView description = findViewById(R.id.description);
      description.setVisibility(View.VISIBLE);
      description.setText(descriptionText);
    }
    //endregion

    //region skip button setup
    if (showSkip) {
      AppCompatButton skip = findViewById(R.id.skip);
      skip.setVisibility(View.VISIBLE);
      skip.setOnClickListener(onSkipClickListener);
      skip.setText(skipButtonText);
    }
    //endregion

    //region image setup
    if (imageVisible) {
      ImageView image;
      if (tutorialPosition == TUTORIAL_POSITION.ABOVE) {
        image = findViewById(R.id.imageBottom);
      } else {
        image = findViewById(R.id.imageTop);
      }

      image.setImageDrawable(imageDrawable);
      image.setVisibility(View.VISIBLE);
    }
    //endregion

    //region custom button setup
    if (showCustomButton) {
      AppCompatButton customButton = findViewById(R.id.custom_button);
      customButton.setVisibility(View.VISIBLE);
      customButton.setText(customButtonText);
    }
    //endregion

    super.show();
  }

  public CustomDialog setHighlightedView(View v) {
    if (v == null) throw new NullPointerException("view cannot be null");
    this.left = v.getLeft();
    this.top = v.getTop();
    this.right = v.getRight();
    this.bottom = v.getBottom();
    viewSet = true;
    return this;
  }

  public CustomDialog setHighlightedBounds(int left, int top, int right, int bottom) {
    this.left = left;
    this.top = top;
    this.right = right;
    this.bottom = bottom;
    viewSet = true;
    return this;
  }

  public String getTitleText() {
    return titleText;
  }

  public CustomDialog setTitleText(String titleText) {
    this.titleText = titleText;
    this.titleVisible = true;
    return this;
  }

  public String getDescriptionText() {
    return descriptionText;
  }

  public CustomDialog setDescriptionText(String descriptionText) {
    this.descriptionText = descriptionText;
    this.descriptionVisible = true;
    return this;
  }

  public CustomDialog setOnSkipClickListener(View.OnClickListener onSkipClickListener) {
    this.onSkipClickListener = onSkipClickListener;
    this.showSkip = true;
    return this;
  }

  public CustomDialog setSkipButtonText(String skipButtonText) {
    this.skipButtonText = skipButtonText;
    return this;
  }

  public CustomDialog setImageDrawable(Drawable imageDrawable) {
    this.imageDrawable = imageDrawable;
    imageVisible = true;
    return this;
  }

  public CustomDialog setTutorialPosition(
      TUTORIAL_POSITION tutorialPosition) {
    this.tutorialPosition = tutorialPosition;
    return this;
  }

  public CustomDialog setCustomButtonText(String customButtonText) {
    this.customButtonText = customButtonText;
    return this;
  }

  public CustomDialog setOnCustomButtonClickListener(
      View.OnClickListener onCustomButtonClickListener) {
    this.onCustomButtonClickListener = onCustomButtonClickListener;
    showCustomButton = true;
    return this;
  }
}
