package com.xiaoneiit.view;
 
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
 
/**
 * Round ImageView
 * 
 */
public class RoundImageView extends ImageView {
	
	 
    private final RectF roundRect = new RectF();
    private float rectAdius = 4;
    private final Paint maskPaint = new Paint();
    private final Paint zonePaint = new Paint();
 
    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
 
    public RoundImageView(Context context) {
        super(context);
        init();
    }
 
    private void init() {
        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        
        zonePaint.setAntiAlias(true);
        zonePaint.setColor(Color.WHITE);
        
        float density = getResources().getDisplayMetrics().density;
        rectAdius = rectAdius * density;
    }
 
    public void setRectAdius(float adius) {
        rectAdius = adius;
        invalidate();
    }
 
    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int width = getWidth();
        int height = getHeight();
        roundRect.set(0, 0, width, height);
    }
 
    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(roundRect, rectAdius, rectAdius, zonePaint);
        canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }
 
}