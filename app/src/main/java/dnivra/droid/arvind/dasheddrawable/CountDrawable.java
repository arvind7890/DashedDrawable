package dnivra.droid.arvind.dasheddrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class CountDrawable extends Drawable {

    private Paint mBadgePaint;
    private Paint mTextPaint;
    private Rect mTxtRect = new Rect();
    private String mCount = "5";
    private boolean mWillDraw;
    private float mTextSize;
    private int backgroundColor,textColor;
    private float xoffset,yoffset;

    public CountDrawable(Context context) {

        mTextSize=50f;
        backgroundColor=Color.RED;
        textColor=Color.WHITE;

        init();
    }

    private void init() {
        mBadgePaint = new Paint();
        mBadgePaint.setColor(backgroundColor);
        mBadgePaint.setAntiAlias(true);
        mBadgePaint.setStyle(Paint.Style.FILL);
        mTextPaint = new Paint();
        mTextPaint.setColor(textColor);
        mTextPaint.setTypeface(Typeface.DEFAULT);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public float getXoffset() {
        return xoffset;
    }

    public void setXoffset(float xoffset) {
        this.xoffset = xoffset;
    }

    public float getYoffset() {
        return yoffset;
    }

    public void setYoffset(float yoffset) {
        this.yoffset = yoffset;
        invalidateSelf();
    }

    public String getmCount() {
        return mCount;
    }

    public void setmCount(String mCount) {
        this.mCount = mCount;
    }

    public float getmTextSize() {
        return mTextSize;
    }

    public void setmTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
    Log.e("DRAW","XOFF:"+xoffset+"//"+"YOFF:"+yoffset);

        Rect bounds = getBounds();

        float width = bounds.right - bounds.left;
        float height = bounds.bottom - bounds.top;

        float radius = Math.max(width, height)/ 2;
        float centerX = (width - radius - 1) +xoffset;
        float centerY = radius -yoffset;
        mTextPaint.getTextBounds(mCount, 0, mCount.length(), mTxtRect);
        float textHeight = mTxtRect.bottom - mTxtRect.top;
        float textY = centerY + (textHeight / 2f);

        canvas.drawCircle(centerX, centerY, radius, mBadgePaint);
        if(mCount.length() > 2)

            canvas.drawText("99+", centerX, textY, mTextPaint);
        else
            canvas.drawText(mCount, centerX, textY, mTextPaint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    public void setCount(String count) {

        mCount = count;
        mWillDraw = !count.equalsIgnoreCase("0");
        invalidateSelf();

    }


}
