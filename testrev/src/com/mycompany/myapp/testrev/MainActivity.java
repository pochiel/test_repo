package com.mycompany.myapp.testrev;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.graphics.Color;
import android.util.Log;

public class MainActivity extends Activity
{
    private graphView gv;
	private cAi mai;
	private revconst mcTurn;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		mcTurn = revconst.C_HUMAN_TURN;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		gv=new graphView(this);
        setContentView(gv);
		mai=new cAi();
		}
	
	public boolean onTouchEvent(MotionEvent e)
	{
		boardState lb;
		CPoint p = new CPoint();
		
		if(e.getAction()== e.ACTION_DOWN)
		{
			
		}
		else if(e.getAction()==e.ACTION_UP)
		{
			if(gv.putBlack((int)(e.getX()),(int)(e.getY()))){
				lb=gv.getBoardState();
				if(mai.eval(lb,p)){
					dlog("pass");
				}
				else{
					lb.putGrid(p.getx(),p.gety(),lb.C_COL_WHITE);
					lb=gv.getBoardState();
					gv.invalidate();
				}
			}
			gv.invalidate();
			
		}
		return true;
	}
	private void dlog(String s){
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}
}
