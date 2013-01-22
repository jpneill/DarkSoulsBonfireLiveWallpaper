package com.jneill.android.darksoulsbonfirelivewallpaper;

import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

public class BonfireWallpaperService extends WallpaperService{
	@Override
	public Engine onCreateEngine() {
		return new BonfireWallpaperEngine();
	}

	class BonfireWallpaperEngine extends Engine{
		private Bonfire bonfire;
		
		public BonfireWallpaperEngine(){
			this.bonfire = new Bonfire();
		}
		
		@Override
		public void onVisibilityChanged(boolean visible){
			if(visible){
				
			}
		}
		
		@Override
		public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height){
			super.onSurfaceChanged(holder, format, width, height);
		}
		
		@Override
		public void onSurfaceCreated(SurfaceHolder holder){
			super.onSurfaceCreated(holder);
		}
		
		@Override
		public void onSurfaceDestroyed(SurfaceHolder holder){
			super.onSurfaceDestroyed(holder);
		}
	}
}