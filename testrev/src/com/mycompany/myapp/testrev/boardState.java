package com.mycompany.myapp.testrev;

public class boardState
{
   public static final int C_COL_NONE=0;
   public static final int C_COL_WHITE=1;
   public static final int C_COL_BLACK=2;
   private byte[][] b_data;
   
   public boardState(){
	   b_data=new byte[8][8];
   }

   public boolean beAbleToPutHere(int gridx, int gridy)
   {
	   if(getGrid(gridx,gridy)!=C_COL_NONE){return false;}
	   if( (getGrid(gridx-1,gridy-1)==C_COL_NONE)&&
	       (getGrid(gridx,gridy-1)==C_COL_NONE)&&
		   (getGrid(gridx+1,gridy-1)==C_COL_NONE)&&
		   (getGrid(gridx-1,gridy)==C_COL_NONE)&&
		   (getGrid(gridx+1,gridy)==C_COL_NONE)&&
		   (getGrid(gridx-1,gridy+1)==C_COL_NONE)&&
		   (getGrid(gridx,gridy+1)==C_COL_NONE)&&
		   (getGrid(gridx+1,gridy+1)==C_COL_NONE)
		   ){return false;}
	   return true;
   }
   
   public int getGrid(int x,int y){
	   return b_data[y][x];
   }
   
   public void setGrid(int x,int y,int c){
	   b_data[y][x]=(byte)c;
   }
   
   public void setInitboard()
   {
	   for(int x=0;x<8;x++)
	   {
		   for(int y=0;y<8;y++){
			   b_data[y][x]=C_COL_NONE;
		   }
	   }
   }
   public void setStartGrid()
   {
	   b_data[3][3]=C_COL_BLACK;
       b_data[4][4]=C_COL_BLACK;
       b_data[3][4]=C_COL_WHITE;
       b_data[4][3]=C_COL_WHITE;
   }
   
}
