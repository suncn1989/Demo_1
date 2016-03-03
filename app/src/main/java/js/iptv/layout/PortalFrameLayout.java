package js.iptv.layout;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import js.iptv.R;
import js.iptv.activity.IPagable;
import js.iptv.handler.PlayerHandler;
import js.iptv.http.Http;
import js.iptv.utils.Anims;
import js.iptv.utils.Constants;
import js.iptv.utils.GetAllSdPath;
import js.iptv.view.IUrlImage;
import js.iptv.view.URLImageButton;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.VideoView;

public class PortalFrameLayout extends RootFrameLayout implements View.OnFocusChangeListener, SurfaceHolder.Callback,
		IAsyncTaskGetData {

	private URLImageButton countImageButton, settingImageButton;
	private URLImageButton recommand1ImageButton, recommand2ImageButton, recommand3ImageButton;
	private View[] portalViews;
	private int random = 0;

	/**
	 * 小视频窗口
	 */
	private MediaPlayer player;
	private String playerUrl = "/mnt/sda1/test.ts";

	private SurfaceView playerSurfaceView;
	private SurfaceHolder playerSurfaceHolder;
	private Handler playerHandler;

	public static class PlayerControllerPara {
		public static final int start = 1;
		public static final int stop = 2;
	}

	public PortalFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setContentView();
	}

	public PortalFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setContentView();
	}

	public PortalFrameLayout(Context context) {
		super(context);
		setContentView();
	}

	private void setContentView() {
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View portalView = inflater.inflate(R.layout.portal_framelayout, null);
		addView(portalView);
		initViewsSize();
		initPlayerSurfaceView();
	}

	private void initViewsSize() {
		int marginLeft = (int) getResources().getDimension(R.dimen.protalFramelayoutMarginLeft);
		int marginTop = (int) getResources().getDimension(R.dimen.protalFramelayoutMarginTop);
		int widthInterval = (int) getResources().getDimension(R.dimen.protalFramelayoutWidthInterval);
		int heightInterval = (int) getResources().getDimension(R.dimen.protalFramelayoutHeightInterval);
		int smallPicWidth = (int) getResources().getDimension(R.dimen.protalFramelayoutSmallPicWidth);
		int smallPicHeight = (int) getResources().getDimension(R.dimen.protalFramelayoutSmallPicHeight);
		int middlePicWidth = (int) getResources().getDimension(R.dimen.protalFramelayoutMiddlePicWidth);
		int middlePicHeight = (int) getResources().getDimension(R.dimen.protalFramelayoutMiddlePicHeight);
		int bigPicHeight = (int) getResources().getDimension(R.dimen.protalFramelayoutBigPicHeight);
		playerSurfaceView = (SurfaceView) findViewById(R.id.player);
		countImageButton = (URLImageButton) findViewById(R.id.count);
		settingImageButton = (URLImageButton) findViewById(R.id.setting);
		recommand1ImageButton = (URLImageButton) findViewById(R.id.recommand1);
		recommand2ImageButton = (URLImageButton) findViewById(R.id.recommand2);
		recommand3ImageButton = (URLImageButton) findViewById(R.id.recommand3);
		portalViews = new View[] { playerSurfaceView, countImageButton, settingImageButton, recommand1ImageButton,
				recommand2ImageButton, recommand3ImageButton };
		LayoutParams _params;
		int[] widths = new int[] { middlePicWidth * 2 + widthInterval, smallPicWidth, smallPicWidth, middlePicWidth,
				middlePicWidth, smallPicWidth * 2 + widthInterval };
		int[] heights = new int[] { bigPicHeight, smallPicHeight, smallPicHeight, middlePicHeight, middlePicHeight,
				bigPicHeight };
		int[] lefts = new int[] { marginLeft, marginLeft + middlePicWidth * 2 + widthInterval * 2,
				marginLeft + middlePicWidth * 2 + widthInterval * 3 + smallPicWidth, marginLeft,
				marginLeft + middlePicWidth + widthInterval, marginLeft + middlePicWidth * 2 + widthInterval * 2 };
		int[] tops = new int[] { marginTop, marginTop, marginTop, marginTop + heightInterval + bigPicHeight,
				marginTop + heightInterval + bigPicHeight, marginTop + smallPicHeight + heightInterval };
		int[] resources = new int[] { R.drawable.play, R.drawable.count, R.drawable.setting, R.drawable.recommand1,
				R.drawable.recommand2, R.drawable.recommand3 };

		for (int i = 0; i < portalViews.length; i++) {
			View view = portalViews[i];
			_params = (LayoutParams) view.getLayoutParams();
			_params.width = widths[i];
			_params.height = heights[i];
			_params.leftMargin = lefts[i];
			_params.topMargin = tops[i];
			view.setLayoutParams(_params);
			if (i == 0) {
				view.setBackgroundResource(resources[i]);
			} else {
				((URLImageButton) view).setImageResource(resources[i]);
			}
			view.setOnFocusChangeListener(this);
		}
	}

	private void initPlayerSurfaceView() {
		playerSurfaceHolder = playerSurfaceView.getHolder();
		playerSurfaceHolder.addCallback(this);
		playerSurfaceHolder.setSizeFromLayout();
		playerHandler = new PlayerHandler(this);
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (v instanceof IUrlImage) {
			IUrlImage view = (IUrlImage) v;
			if (hasFocus) {
				random++;
				if (0 == (random % 3)) {
					Anims.scaleTo(v, 1.1f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f,
							1000, 0);
				} else if (1 == (random % 3)) {
					Anims.alphaTo(v, 1.0f, 0.2f, 1000, 0);
				} else if (2 == (random % 3)) {
					Anims.rotateTo(v, 0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 2000,
							0);
				}
			} else {
				view.blurFocus();
			}
		}
		if (v instanceof VideoView) {
			if (hasFocus) {
				random++;
				if (0 == (random % 3)) {
					Anims.scaleTo(v, 1.1f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f,
							1000, 0);
				} else if (1 == (random % 3)) {
					Anims.alphaTo(v, 1.0f, 0.2f, 1000, 0);
				} else if (2 == (random % 3)) {
					Anims.rotateTo(v, 0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, 2000,
							0);
				}
			} else {
				((VideoView) v).clearAnimation();
			}
		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {

		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {
			case KeyEvent.KEYCODE_DPAD_UP:

				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				break;

			case KeyEvent.KEYCODE_DPAD_LEFT:
				break;

			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if (settingImageButton.isFocused() || recommand3ImageButton.isFocused()) {
					((IPagable) this.getContext()).pageDown(false);
					return true;
				}
				break;

			default:
				break;
			}
		} else if (event.getAction() == KeyEvent.ACTION_UP) {
			return true;
		}

		return super.dispatchKeyEvent(event);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		startMediaPlayer();
	}

	/**
	 * 启动mideaPlayer播放器，播放首页小视频窗口
	 */
	public void startMediaPlayer() {
		new Thread() {
			@Override
			public void run() {
				player = new MediaPlayer();
				try {
					player.setAudioStreamType(AudioManager.STREAM_MUSIC);
					player.setLooping(true);
					player.setDisplay(playerSurfaceHolder);
					player.setDataSource(playerUrl);
					player.prepareAsync();
					player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mp) {
							playerSurfaceView.setBackgroundColor(Color.TRANSPARENT);
							player.start();
						}

					});
					player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
						@Override
						public boolean onError(MediaPlayer mp, int what, int extra) {
							mediaPlayerErrorHandler(mp);
							return true;
						}
					});
					player.setOnInfoListener(new MediaPlayer.OnInfoListener() {
						@Override
						public boolean onInfo(MediaPlayer mp, int what, int extra) {
							if (MediaPlayer.MEDIA_INFO_BUFFERING_START == what) {
								System.out.println("开始缓冲");
							} else if (MediaPlayer.MEDIA_INFO_BUFFERING_END == what) {
								System.out.println("缓冲结束");
							}
							return false;
						}
					});
					player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
						@Override
						public void onCompletion(MediaPlayer mp) {
							// System.out.println("overoveroveroveroveroveroveroverover");
							// mp.reset();
							// mp.start();
						}
					});
				} catch (IllegalArgumentException e) {
					mediaPlayerErrorHandler(player);
					e.printStackTrace();
				} catch (SecurityException e) {
					mediaPlayerErrorHandler(player);
					e.printStackTrace();
				} catch (IllegalStateException e) {
					mediaPlayerErrorHandler(player);
					e.printStackTrace();
				} catch (IOException e) {
					mediaPlayerErrorHandler(player);
					e.printStackTrace();
				}
			}

			/**
			 * mediaPlayer错误处理方法，当mediaplayer播放错误时重新播放
			 * 
			 * @param mp
			 */
			private void mediaPlayerErrorHandler(MediaPlayer mp) {
				Log.e(this.getClass().getName(), getResources().getString(R.string.mediaplayer_error) + ", "
						+ getResources().getString(R.string.retry_in_3s));
				mp.release();
				mp = null;
				playerHandler.sendEmptyMessageDelayed(PlayerControllerPara.start,
						Constants.MideaPlayer.error_retry_interval);
			}
		}.start();

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		player.release();
		player = null;

	}

	@Override
	public String getData() {

		PortalFrameLayoutDoTask portalFrameLayoutDoTask = new PortalFrameLayoutDoTask();
		// try {
		// portalFrameLayoutDoTask.execute(new URL(" "));
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// Vector<String> vector = new Vector<String>();
		//
		// vector.toString();
		// }
		return null;
	}

	class PortalFrameLayoutDoTask extends AsyncTask<URL, Integer, String> {
		@Override
		protected String doInBackground(URL... params) {

			Http http = Http.getInstance();
			try {
				String dataString = http.get(new URI((params[0]).toString()));
				System.out.println("--=--=" + dataString);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
		}

	}

}
