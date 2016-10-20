package xyz.husten.finstergram.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class SquareRelativeLayout extends RelativeLayout {
  public SquareRelativeLayout(Context context) {
    super(context);
  }

  public SquareRelativeLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public SquareRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }


  @Override
  public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //noinspection SuspiciousNameCombination
    super.onMeasure(widthMeasureSpec, widthMeasureSpec);
  }
}
