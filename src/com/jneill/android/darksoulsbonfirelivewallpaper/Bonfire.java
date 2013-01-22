package com.jneill.android.darksoulsbonfirelivewallpaper;

import android.content.*;
import android.graphics.*;
import android.view.*;

public class Bonfire {
	private BonfireThread bonfireThread;
	private Bitmap currentBonfireImage;
	private Rect drawRect;
	private SurfaceHolder surfaceHolder;
	private Context context;
	private int numFrames, currentFrame, fps, imageHeight, imageWidth;
	private long timer;
	
	public Bonfire(){
		//TODO fix naming the bitmaps and choosing the correct frames
	}
	
	private void initialise(Context context, SurfaceHolder surfaceHolder, int fps, int frameCount){		
		this.timer = 0;
		this.currentFrame = 0;
		this.drawRect = new Rect(0,0,0,0);
		this.fps = 1000 / fps;
		this.numFrames = frameCount;
		
		this.bonfireThread = new BonfireThread(this);
		this.surfaceHolder = surfaceHolder;
		this.context = context;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPurgeable = true;
		this.currentBonfireImage = BitmapFactory.decodeResource(context.getResources(), com.jneill.android.darksoulsbonfirelivewallpaper.R.drawable.bonfire, options);
		this.imageHeight = currentBonfireImage.getHeight();
		this.imageWidth = currentBonfireImage.getWidth() / frameCount;
		this.drawRect = new Rect(0, 0, this.imageWidth, this.imageHeight);
	}
	
	private void update(long currentTime){
		if(currentTime > this.timer + this.fps){
			this.timer = currentTime;
			this.currentFrame++;
			
			if(this.currentFrame > this.numFrames){
				this.currentFrame = 0;
			}
		}
		
		this.drawRect.left = this.currentFrame * this.imageWidth;
		this.drawRect.right = this.drawRect.left + this.imageWidth;
	}
	
	public void Render(){
		Canvas canvas = null;		
		try{
			canvas = this.surfaceHolder.lockCanvas(null);
			synchronized(this.surfaceHolder){
				long currentTime = System.currentTimeMillis();
				this.update(currentTime);
			
				Rect dest = new Rect(0, 0, this.imageWidth, this.imageHeight);
				canvas.drawBitmap(this.currentBonfireImage, this.drawRect, dest, null);
			}
		}finally{
			if(canvas != null){
				this.surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
	
	public void Start(){
		this.bonfireThread.switchOn();
	}
	
	public void Stop(){
		this.bonfireThread.switchOff();
		boolean retry = true;
		while(retry){
			try{
				this.bonfireThread.join();
				retry = false;
			}catch(InterruptedException e){
				//do nothing as retry remains true and the loop continues
			}
		}
	}

}