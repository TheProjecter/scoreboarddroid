package frb.scoreboarddroid;

import android.os.Bundle;

public class soccer extends baseMatch {
	
	public Integer TIME_MATCH = 45;
	public Integer MAX_PERIODE = 2;
	
	static int periode = 1;
	
		 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soccer);
        
        sport = "soccer";
        ini();
        
    }
    
}