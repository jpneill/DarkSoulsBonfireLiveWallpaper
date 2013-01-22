package com.jneill.android.darksoulsbonfirelivewallpaper;

public class BonfireThread extends Thread{
	private Bonfire bonfire;
	private boolean isRunning;
	
	public BonfireThread(Bonfire bonfire){
		this.bonfire = bonfire;
	}
	
	public void switchOn(){
		this.isRunning = true;
		this.start();
	}
	
	public void switchOff(){
		this.isRunning = false;
		synchronized(this){
			this.notify();
		}
	}
	
	public void pause(){
		this.isRunning = false;
		synchronized(this){
			this.notify();
		}
	}
	
	@Override
	public void run(){
		while(this.isRunning){
			this.bonfire.Render();
		}
	}
}
