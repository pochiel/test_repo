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
		}
	
	public boolean onTouchEvent(MotionEvent e)
	{
		boardState lb;
		Integer x=0,y=0;
		if(e.getAction()== e.ACTION_DOWN)
		{
			
		}
		else if(e.getAction()==e.ACTION_UP)
		{
			switch(mcTurn){
				case C_HUMAN_TURN:
				    Log.i("a","human");
			        gv.putBlack((int)(e.getX()),(int)(e.getY()));
			        lb=gv.getBoardState();
			        gv.invalidate();
					mcTurn=revconst.C_CPU_TURN;
			        break;
			    case C_CPU_TURN:
				    Log.i("b","vpu");
				    gv.putWhite((int)(e.getX()),(int)(e.getY()));
				    lb=gv.getBoardState();
					//mai.eval(lb,x,y);
					gv.invalidate();
					mcTurn=revconst.C_HUMAN_TURN;
					break;
			default:
			}
		}
		return true;
	}
}