package com.mycompany.myapp.testrev;
import org.json.*;
import android.util.Log;

public class boardState
{
   public static final int C_COL_NONE=0;
   public static final int C_COL_WHITE=1;
   public static final int C_COL_BLACK=2;
   private byte[][] b_data;
   
   public boardState(){
	   b_data=new byte[8][8];
   }

   public boolean equals(Object o){
	   boardState ao =(boardState)o;
	   for (int x=0;x<8;x++){
		   for(int y=0;y<8;y++){
			   if( ao.getGrid(x,y)!=this.getGrid(x,y) ){
				   return false;
			   }
		   }
	   }
	   return true;
   }
   
   public boolean puttableInYourTurn(int aic){
	   
	   for(int x=0;x<8;x++){
		   for(int y=0;y<8;y++){
			   if(beAbleToPutHere(x,y,aic)){return true;}
		   }
	   }
	   return false;
   }
   
   public boolean beAbleToPutHere(int gridx, int gridy,int aic)
   {
	   int tmpn=0;
	   int i=0;
	   /*out of board*/
	   if(gridx<0||gridy<0){return false;}
	   if(gridx>7||gridy>7){return false;}
	  
	   // allready puted
	   if(getGrid(gridx,gridy)!=C_COL_NONE){return false;}
	   
	   // nothing around here
	   for(int x=-1;x<2;x++){
		   for(int y=-1;y<2;y++){
			   if(y==0&&x==0){
				   continue;}
			   if((x+gridx<0)||(x+gridx>7)||(y+gridy<0)||(y+gridy>7))
			   {
				   continue;
			   }
			   tmpn+=getGrid(gridx+x,gridy+y);
		   }
	   }
	   if(tmpn==0){
		  return false;}
	   tmpn=0;
	   
	   // Check reversible to right side
	   for(i=1;(i+gridx)<=7;i++){
		   if((getGrid(gridx+i,gridy)==aic)){
			   for(int j=1;j<i;j++){
				   //setGrid(gridx+j,gridy,aic);
				   tmpn++;
			   }
			   break;
		   }
		   if(getGrid(gridx+i,gridy)==C_COL_NONE){
			   break;
		   }
	   }
	   
	   // Check reversible to left side
	   for(i=-1;(i+gridx)>=0;i--){
		   if((getGrid(gridx+i,gridy)==aic)){
			   for(int j=-1;j>i;j--){
				   //setGrid(gridx+j,gridy,aic);
				   tmpn++;
			   }
			   break;
		   }
		   if(getGrid(gridx+i,gridy)==C_COL_NONE){
			   break;
		   }
	   }
	   
	   // Check reversible to upper side
	   for(i=1;(i+gridy)<=7;i++){
		   if((getGrid(gridx,gridy+i)==aic)){
			   for(int j=1;j<i;j++){
				   //setGrid(gridx,gridy+j,aic);
				   tmpn++;
			   }
			   break;
		   }
		   if(getGrid(gridx,gridy+i)==C_COL_NONE){
			   break;
		   }
	   }
	   
	   // Check reversible to lower side
	   for(i=-1;(i+gridy)>=0;i--){
		   if((getGrid(gridx,gridy+i)==aic)){
			   for(int j=-1;j>i;j--){
				   //setGrid(gridx,gridy+j,aic);
				   tmpn++;
			   }
			   break;
		   }
		   if(getGrid(gridx,gridy+i)==C_COL_NONE){
			   break;
		   }
	   }
	   
	   for(i=1;((i+gridy)<=7)&&((i+gridx)<=7);i++){
		   if((getGrid(gridx+i,gridy+i)==aic)){
			   for(int j=1;j<i;j++){
				   //setGrid(gridx+j,gridy+j,aic);
				   tmpn++;
			   }
			   break;
		   }
		   if(getGrid(gridx+i,gridy+i)==C_COL_NONE){
			   break;
		   }
	   }

	   // Check reversible to left upper side
	   for(i=-1;((i+gridy)>=0)&&((i+gridx)>=0);i--){
		   if((getGrid(gridx+i,gridy+i)==aic)){
			   for(int j=-1;j>i;j--){
				   //setGrid(gridx+j,gridy+j,aic);
				   tmpn++;
			   }
			   break;
		   }
		   if(getGrid(gridx+i,gridy+i)==C_COL_NONE){
			   break;
		   }
	   }

	   // Check reversible to left lower side
	   for(i=1;((i+gridy)<=7)&&((gridx-i)>=0);i++){
		   if((getGrid(gridx-i,gridy+i)==aic)){
			   for(int j=1;j<i;j++){
				   //setGrid(gridx-j,gridy+j,aic);
				   tmpn++;
			   }
			   break;
		   }
		   if(getGrid(gridx-i,gridy+i)==C_COL_NONE){
			   break;
		   }
	   }

	   // Check reversible to right upper side
	   for(i=-1;((i+gridy)>=0)&&((gridx-i)<=7);i--){
		   if((getGrid(gridx-i,gridy+i)==aic)){
			   for(int j=-1;j>i;j--){
				   //setGrid(gridx-j,gridy+j,aic);
				   tmpn++;
			   }
			   break;
		   }
		   if(getGrid(gridx-i,gridy+i)==C_COL_NONE){
			   break;
		   }
	   }
	   
	   if(tmpn==0){return false;}
	   return true;
   }
   
	public void putGrid(int gridx,int gridy,int aic)
	{
		int tmpn=0;
		int i=0;
		
		setGrid(gridx,gridy,aic);
		
		// Check reversible to right side
		for(i=1;(i+gridx)<=7;i++){
			if((getGrid(gridx+i,gridy)==aic)){
				for(int j=1;j<i;j++){
					setGrid(gridx+j,gridy,aic);
					tmpn++;
				}
				break;
			}
			if(getGrid(gridx+i,gridy)==C_COL_NONE){
				break;
			}
		}

		// Check reversible to left side
		for(i=-1;(i+gridx)>=0;i--){
			if((getGrid(gridx+i,gridy)==aic)){
				for(int j=-1;j>i;j--){
					setGrid(gridx+j,gridy,aic);
					tmpn++;
				}
				break;
			}
			if(getGrid(gridx+i,gridy)==C_COL_NONE){
				break;
			}
		}

		// Check reversible to upper side
		for(i=1;(i+gridy)<=7;i++){
			if((getGrid(gridx,gridy+i)==aic)){
				for(int j=1;j<i;j++){
					setGrid(gridx,gridy+j,aic);
					tmpn++;
				}
				break;
			}
			if(getGrid(gridx,gridy+i)==C_COL_NONE){
				break;
			}
		}

		// Check reversible to lower side
		for(i=-1;(i+gridy)>=0;i--){
			if((getGrid(gridx,gridy+i)==aic)){
				for(int j=-1;j>i;j--){
					setGrid(gridx,gridy+j,aic);
					tmpn++;
				}
				break;
			}
			if(getGrid(gridx,gridy+i)==C_COL_NONE){
				break;
			}
		}

		// Check reversible to right lower side
		for(i=1;((i+gridy)<=7)&&((i+gridx)<=7);i++){
			if((getGrid(gridx+i,gridy+i)==aic)){
				for(int j=1;j<i;j++){
					setGrid(gridx+j,gridy+j,aic);
					tmpn++;
				}
				break;
			}
			if(getGrid(gridx+i,gridy+i)==C_COL_NONE){
				break;
			}
		}

		// Check reversible to left upper side
		for(i=-1;((i+gridy)>=0)&&((i+gridx)>=0);i--){
			if((getGrid(gridx+i,gridy+i)==aic)){
				for(int j=-1;j>i;j--){
					setGrid(gridx+j,gridy+j,aic);
					tmpn++;
				}
				break;
			}
			if(getGrid(gridx+i,gridy+i)==C_COL_NONE){
				break;
			}
		}

		// Check reversible to left lower side
		for(i=1;((i+gridy)<=7)&&((gridx-i)>=0);i++){
			if((getGrid(gridx-i,gridy+i)==aic)){
				for(int j=1;j<i;j++){
					setGrid(gridx-j,gridy+j,aic);
					tmpn++;
				}
				break;
			}
			if(getGrid(gridx-i,gridy+i)==C_COL_NONE){
				break;
			}
		}

		// Check reversible to right upper side
		for(i=-1;((i+gridy)>=0)&&((gridx-i)<=7);i--){
			if((getGrid(gridx-i,gridy+i)==aic)){
				for(int j=-1;j>i;j--){
					setGrid(gridx-j,gridy+j,aic);
					tmpn++;
				}
				break;
			}
			if(getGrid(gridx-i,gridy+i)==C_COL_NONE){
				break;
			}
		}
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
