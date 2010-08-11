package frb.scoreboarddroid;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Chronometer.OnChronometerTickListener;


public class handball extends Activity {
	
	static long tiempo = SystemClock.elapsedRealtime();
	static boolean running = false;
	long elapsedTime=0;
	
	Chronometer crono;
	String currentTime="";
	Boolean resume=false;
	
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handballmatch);
        
        Typeface face=Typeface.createFromAsset(getAssets(), "fonts/DroidLogo.ttf");

        crono = (Chronometer) this.findViewById(R.id.crono);
        crono.setTypeface(face);
        crono.setOnChronometerTickListener(new OnChronometerTickListener(){
        	public void onChronometerTick(Chronometer arg0) {
        		if(currentTime.equalsIgnoreCase("")){
        			long minutes=((SystemClock.elapsedRealtime()-crono.getBase())/1000)/60;
        			long seconds=((SystemClock.elapsedRealtime()-crono.getBase())/1000)%60;
        			if(minutes < 10) currentTime = "0"+minutes+":";
        			else currentTime = minutes+":";
        			if(seconds<10) currentTime = currentTime+"0"+seconds;
        			else currentTime = currentTime+seconds;
        			elapsedTime=SystemClock.elapsedRealtime();
        		}else{
        			long minutes=((elapsedTime-crono.getBase())/1000)/60;
        			long seconds=((elapsedTime-crono.getBase())/1000)%60;
        			currentTime=minutes+":"+seconds;
        			if(minutes < 10) currentTime = "0"+minutes+":";
        			else currentTime = minutes+":";
        			if(seconds<10) currentTime = currentTime+"0"+seconds;
        			else currentTime = currentTime+seconds;
        			elapsedTime=elapsedTime+1000;
        		}
        		arg0.setText(currentTime);
        	}
        });
        
        //evento click de los botones
        ImageButton but_addLocal = (ImageButton) this.findViewById(R.id.but_addLocal);
        ImageButton but_removeLocal = (ImageButton) this.findViewById(R.id.but_removeLocal);
        ImageButton but_addVisitor = (ImageButton) this.findViewById(R.id.but_addVisitor);
        ImageButton but_removeVisitor = (ImageButton) this.findViewById(R.id.but_removeVisitor);
        but_addLocal.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		TextView  points = (TextView) findViewById(R.id.point_local);
        		changePoints(points,"+");
        	}
        });
        
        but_removeLocal.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		TextView  points = (TextView) findViewById(R.id.point_local);
        		changePoints(points,"-");
        	}
        });
        
        but_addVisitor.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		TextView  points = (TextView) findViewById(R.id.point_visitor);
        		changePoints(points,"+");
        	}
        });
        
        but_removeVisitor.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		TextView  points = (TextView) findViewById(R.id.point_visitor);
        		changePoints(points,"-");
        	}
        });
        
    }
    
    public void startStopMatch(View v){
    	if(running){
    		crono.stop();
            currentTime = "1";
    		running = false;
    	}else{
    		if(currentTime.equalsIgnoreCase("")){
    			crono.setBase(SystemClock.elapsedRealtime());
    			crono.start();
    		}else{
    			crono.start();
    		}
  
    		running = true;
    	}
    }
    
    public void changePoints(TextView points, String op){
    	Integer result = 0;
    	if( op.equalsIgnoreCase("+"))
    		result = Integer.parseInt(points.getText().toString())+1;
    	else if(!points.getText().toString().equalsIgnoreCase("0"))
			result = Integer.parseInt(points.getText().toString())-1;
    	
		points.setText(result.toString());
    	
    }
    
}