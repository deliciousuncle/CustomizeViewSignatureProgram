package com.example.customize_view_signatureprogram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.LinkedList;

public class Myview extends View {
//private LinkedList<HashMap<String,Float>> line;// HashMap<key,value> 記錄線的座標點
    private LinkedList<LinkedList<HashMap<String,Float>>> lines;// 做多條不同線段
    private Paint paint;
    public Myview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        lines=new LinkedList<>();
        paint=new Paint();
        setBackgroundColor(Color.BLUE);
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(9);//設定寬度
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (LinkedList<HashMap<String,Float>> line:lines
             ) {
            for(int i=1;i<line.size();i++) {//2個點組成一段線,三個點組成2段線
                HashMap<String,Float> p0=line.get(i-1);
                HashMap<String,Float> p1=line.get(i);
                canvas.drawLine(p0.get("x"),p0.get("y"),p1.get("x"),p1.get("y"),paint);
            }
        }
    }
//onTouchEvent 比onclick更原始
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    if(event.getAction()==MotionEvent.ACTION_DOWN){
      setFirstPoint(event);
    }
  else if(event.getAction()==MotionEvent.ACTION_MOVE){
        setMovePoint(event);
    }
else if(event.getAction()==MotionEvent.ACTION_UP){

    }
        return true;
       // return super.onTouchEvent(event);
    }

    public void setFirstPoint(MotionEvent event){
        LinkedList<HashMap<String,Float>> line=new LinkedList<>();  //做一條線
      float ex=event.getX(),ey=event.getY();
      HashMap<String,Float> point=new HashMap<>();
      point.put("x",ex);point.put("y",ey);
      line.add(point);
      lines.add(line);
    }
    public void setMovePoint(MotionEvent event){
        float ex=event.getX(),ey=event.getY();
        HashMap<String,Float> point=new HashMap<>();
        point.put("x",ex);point.put("y",ey);
        lines.getLast()/*獲取最後一個元素*/.add(point);
        invalidate();//重新繪製畫面會,進入ondraw
    }

    public void clear(){
        lines.clear();  //清除線段資料
        invalidate();//進入ondraw會得到一個乾淨的canvas,因為lines是空的所以不會有線段出來
    }

    public void undo(){
        if(lines.size()>0){//如果有線才進行
        lines.removeLast();//刪除最後一個資料段
        invalidate();
        }
    }
}
