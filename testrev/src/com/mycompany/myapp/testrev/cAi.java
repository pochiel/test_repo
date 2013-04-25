package com.mycompany.myapp.testrev;
import android.util.Log;

public class cAi
{
    public cAi(){
		
	}

	public boolean eval(boardState b,CPoint p){
		
		int i=0;
		int j=0;
		int max=0,tmpn=0;
		int x,y;
		
		x=-1;y=-1;
		for(i=0;i<8;i++){
			for(j=0;j<8;j++){
				tmpn=b.getRevCountPutHere(i,j,b.C_COL_WHITE);
				if(tmpn>max){max=tmpn;x=i;y=j;}
			}
		}
		
		p.setPoint(x,y);
		return ((x==-1)&&(y==-1));
	}
	
	private void minimax(boardState lb,int turn,int cnt){
		
	}
	
	
}
