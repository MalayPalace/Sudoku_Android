package com.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SudokuActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	public static int sudo[][]=new int[9][9];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button b1;
        b1=(Button)findViewById(R.id.create_sudo);
        b1.setOnClickListener(this);
    }

	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.create_sudo:
			/*SUDOKU CODE STARTS HERE*/
			long start,end;
			int i,j,dec;
			TextView t1;
			t1=(TextView)findViewById(R.id.timeView);
	        
	        //FILLING THE SUDOKU BOX
			start=System.currentTimeMillis();
	        int game_count=0;
	        i=0;
	        dec=0;
	        while(i<9)
	        {
	            dec=insert(i);
	            game_count++;
	            if(dec==0){
	                i++;
	            }
	            if(game_count>200){     //START AGAIN FROM BEGINNING       
	                for(i=0;i<9;i++){
	                    for(j=0;j<9;j++){
	                        sudo[i][j]=0;
	                    }
	                }
	                i=0;
	                game_count=0;
	            }
	        }
	        
	        //DISPLAYING THE SUDOKU
	        display();
	        end=System.currentTimeMillis();
			t1.setText("Time Taken : "+(end-start)+" milliseconds");
	        
	        /*SUDOKU CODE ENDS HERE*/
			break;
		}
	}

	void display(){
		TextView e1=(TextView)findViewById(R.id.textSudo);
		int i;
		e1.setText("-------------------------------------\n");
		for(i=0;i<9;i++){
			e1.append("|"+sudo[i][0]+"|"+sudo[i][1]+"|"+sudo[i][2]+"|  |"
					+sudo[i][3]+"|"+sudo[i][4]+"|"+sudo[i][5]+"|  |"+
                    sudo[i][6]+"|"+sudo[i][7]+"|"+sudo[i][8]+"|\n");
			if(i==2 || i==5 || i==8)
            {
                e1.append("-------------------------------------\n");
            }
            else
            {
                e1.append("-----------   -----------  -----------\n");
            }
		}
	}
	
	int insert(int i){
        int j,p,count;
        boolean cond;
        int ha[]=new int[9];
        
        //FLUSH THE LINE
        for(j=0;j<9;j++)
        {
            sudo[i][j]=0;
        }
        
        //RANDOMIZE ARRAY
        p=0;
        ha=ranHoriArr();
        for(j=0;j<9;j++)
        {
            p=0;
            count=0;
            cond=true;
            while(cond)
            {
                count++;
                if(ha[p]!=0 && verticalcheck(ha[p],i,j) && boxcheck(ha[p],i,j)){
                    sudo[i][j]=ha[p];
                    ha=delArr(ha,p);
                    cond=false;
                }
                p++;
                if(p>8){
                    p=0;
                }
                if(count>8){
                    return 1;   //REQUIRES BACKTRACK
                }
            }
        }
        return 0;   //DOES NOT REQUIRE BACKTRACK
	}

    int[] ranHoriArr(){
        int i,r,temp;
        int a[]={1,2,3,4,5,6,7,8,9};
            //RANDOMIZE THE ARRAY
        for(i=0;i<9;i++){
            r=(int)(Math.random()*10);
            if(r!=9){
                temp=a[i];
                a[i]=a[r];
                a[r]=temp;
            }            
        }
        return(a);
    }
       
    boolean verticalcheck(int ran,int i,int j){
        int k;
        for(k=0;k<=i;k++)
        {
            if(ran==SudokuActivity.sudo[k][j])
            {
                return false;
            }
        }
        return true;
    }
    
    boolean boxcheck(int ran,int i,int j){
        int m,n;
        if(i<=2 && j<=2)    //TOP-LEFT BOX
        {
            for(m=0;m<=i;m++)
            {
                for(n=0;n<=2;n++)
                {
                    if(ran==SudokuActivity.sudo[m][n])
                        return false;
                }
            }
            return true;
        }
        else if(i<=2 && j>=3 && j<=5)   //TOP-MIDDLE BOX
        {
            for(m=0;m<=i;m++)
            {
                for(n=3;n<=5;n++)
                {
                    if(ran==SudokuActivity.sudo[m][n])
                        return false;
                }
            }
            return true;
        }
        else if(i<=2 && j>=6 && j<=8)   //TOP-RIGHT BOX
        {
            for(m=0;m<=i;m++)
            {
                for(n=6;n<=8;n++)
                {
                    if(ran==SudokuActivity.sudo[m][n])
                        return false;
                }
            }
            return true;
        }
        else if(i>=3 && i<=5 && j<=2)   //MIDDLE-LEFT BOX
        {
            for(m=3;m<=i;m++)
            {
                for(n=0;n<=2;n++)
                {
                    if(ran==SudokuActivity.sudo[m][n])
                        return false;
                }
            }
            return true;
        }
        else if(i>=3 && i<=5 && j>=3 && j<=5)   //MIDDLE BOX
        {
            for(m=3;m<=i;m++)
            {
                for(n=3;n<=5;n++)
                {
                    if(ran==SudokuActivity.sudo[m][n])
                        return false;
                }
            }
            return true;
        }
        else if(i>=3 && i<=5 && j>=6 && j<=8)   //MIDDLE-RIGHT BOX
        {
            for(m=3;m<=i;m++)
            {
                for(n=6;n<=8;n++)
                {
                    if(ran==SudokuActivity.sudo[m][n])
                        return false;
                }
            }
            return true;
        }
        else if(i>=6 && i<=8 && j<=2)   //BOTTOM-LEFT BOX
        {
            for(m=6;m<=i;m++)
            {
                for(n=0;n<=2;n++)
                {
                    if(ran==SudokuActivity.sudo[m][n])
                        return false;
                }
            }
            return true;
        }
        else if(i>=6 && i<=8 && j>=3 && j<=5)   //BOTTOM-MIDDLE BOX
        {
            for(m=6;m<=i;m++)
            {
                for(n=3;n<=5;n++)
                {
                    if(ran==SudokuActivity.sudo[m][n])
                        return false;
                }
            }
            return true;
        }
        else if(i>=6 && i<=8 && j>=6 && j<=8)   //BOTTOM-RIGHT BOX
        {
            for(m=6;m<=i;m++)
            {
                for(n=6;n<=8;n++)
                {
                    if(ran==SudokuActivity.sudo[m][n])
                        return false;
                }
            }
            return true;
        }
        return false;
    }
    

    int[] delArr(int b[],int p) {
        int i,len;
        len=b.length-1;
        for(i=p;i<len;i++){
            b[i]=b[i+1];
        }
        b[len]=0;
        return(b);
    }
}