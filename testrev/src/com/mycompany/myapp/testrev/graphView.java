package com.mycompany.myapp.testrev;

import android.content.res.*;
import android.content.*; 
import android.graphics.*; 
import android.view.*;
import android.util.Log;
import android.widget.Toast;

class graphView extends View
{
   private boardState board;
   public graphView(Context context)
   {
       super(context);
	   this.setBackgroundColor(Color.rgb(0,160,0));
	   board=new boardState();
	   board.setInitboard();
	   board.setStartGrid();
	   mc=0;
   }
   private final int C_G_SIZE=54;
   private final int C_G_LINES=8;
   //private final int C_G_SIZE=(getWidth()-20)/C_G_LINES;
   private final int C_X_MIN=10;
   private final int C_Y_MIN=10;
   private final int C_X_MAX=10+(C_G_SIZE*C_G_LINES);
   private final int C_Y_MAX=10+(C_G_SIZE*C_G_LINES);
   private int mc;
   
   public void setMc(int ai){
	   mc=ai;
   }
   @Override
   protected void onDraw(Canvas c)
   {
	   Paint p = new Paint();
	   p.setAntiAlias(true);
	   for(int x=0;x<C_G_LINES;x++)
	   {
		   c.drawLine(C_X_MIN+(x*C_G_SIZE),C_Y_MIN,C_X_MIN+(x*C_G_SIZE),C_Y_MAX,p);
		   for(int y=0;y<C_G_LINES;y++)
		   {
			   c.drawLine(C_X_MIN,C_Y_MIN+(y*C_G_SIZE),C_X_MAX,C_Y_MIN+(y*C_G_SIZE),p);
		   }
	   }
	   c.drawLine(C_X_MIN,C_Y_MAX,C_X_MAX,C_Y_MAX,p);
	   c.drawLine(C_X_MAX,C_Y_MIN,C_X_MAX,C_Y_MAX,p);
	   drawGrid(c,p);
	   
	   String st="Turn:";
	   if(mc==board.C_COL_BLACK){
		   st+="CPU";
	   }
	   else{
		   st+="Human";
	   }
	   c.drawText(st,0,C_Y_MAX,p);
	  }
	  
	  private void drawGrid(Canvas c,Paint p){
		 for(int x=0;x<C_G_LINES;x++)
		 {
			 for(int y=0;y<C_G_LINES;y++)
			 {
				 int g=board.getGrid(x,y);
				 if(g==board.C_COL_BLACK)
				 {
				    p.setColor(Color.BLACK);
				 }
				 else if(g==board.C_COL_WHITE)
				 {
					 p.setColor(Color.WHITE);
				 }
				 if(g!=board.C_COL_NONE){
				     c.drawCircle(C_X_MIN+(C_G_SIZE*x)+(C_G_SIZE/2),
							  C_Y_MIN+(C_G_SIZE*y)+(C_G_SIZE/2),
							  (C_G_SIZE/2)-2,p);
				 }
			 }
		 }
	  }
	  public boardState getBoardState(){
		  return board;
	  }
	  public boolean putOnGrid(int x,int y,int c)
	  {
		  if(!board.puttableInYourTurn(c)){return true;}
		  int gridx=(int)((x-C_X_MIN)/C_G_SIZE);
		  int gridy=(int)((y-C_Y_MIN)/C_G_SIZE);
		  if((x<C_X_MIN)||(y<C_Y_MIN)){
			  return false;
		  }
		  if((x>C_X_MAX)||(y>C_Y_MAX)){
			  return false;
		  }
		  Log.e("dbg","y="+y+"gy"+(int)((y-C_Y_MIN)/C_G_SIZE));
		  if( board.getGrid(gridx,gridy) == board.C_COL_NONE )
		  {
			  if(board.beAbleToPutHere(gridx,gridy,c)){
				  
				  board.putGrid(gridx,gridy,c);
			      return true;
				  }
		  }
		  return false;
	  }
	  
	  public boolean putWhite(int x,int y)
	  {
		  if(putOnGrid(x,y, board.C_COL_WHITE)){
			  setMc(board.C_COL_WHITE);
			  return true;
		  }
		  return false;
	  }
	  
	public boolean putBlack(int x,int y)
	{
		if(putOnGrid(x,y,board.C_COL_BLACK)){
			setMc(board.C_COL_BLACK);
			return true;
		}
		return false;
	}
}

