package com.example.mobi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.TextureView;
import android.view.View;

public class DrawGuideline extends View {
    TextureView textureView;
    public DrawGuideline(Context context, TextureView textureView) {
        super(context);
        this.textureView = textureView;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        DrawBox(canvas);
        super.onDraw(canvas);
    }

    protected void DrawBox(Canvas canvas) {
        Paint paint = new Paint();

        paint.setColor(Color.RED);
        paint.setStrokeWidth(300f);
        paint.setStyle(Paint.Style.STROKE);

    }
}
