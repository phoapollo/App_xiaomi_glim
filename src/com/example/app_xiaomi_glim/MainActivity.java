package com.example.app_xiaomi_glim;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity {

	private View view;
	private Camera camera;
	private boolean isopen;
	private static Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏  
	    requestWindowFeature(Window.FEATURE_NO_TITLE);  //去掉标题栏
		this.setContentView(R.layout.activity_main);
		context = this.getApplicationContext();
		view = (View)findViewById(R.id.view);
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!isopen){
					camera = Camera.open();
					view.setBackgroundResource(R.drawable.glim);
					Camera.Parameters params = camera.getParameters();
					
					params.setFlashMode(Parameters.FLASH_MODE_TORCH);
					camera.setParameters(params);
					camera.startPreview();
					isopen = true;
				}else{
					view.setBackgroundResource(R.drawable.glim_off);
					camera.stopPreview();
					camera.release();
					camera = null;
					isopen = false;
				}
			}
		});
		
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		 if(keyCode == KeyEvent.KEYCODE_BACK){       //按back键的时候 释放照相机  
	            if(camera != null){  
	                camera.release();  
	                finish();  
	            }  
	     }  
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
