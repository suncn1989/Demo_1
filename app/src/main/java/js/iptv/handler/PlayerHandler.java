package js.iptv.handler;

import js.iptv.layout.PortalFrameLayout;
import android.os.Handler;
import android.os.Message;

public class PlayerHandler extends Handler {

	private PortalFrameLayout frameLayout;

	public PlayerHandler(PortalFrameLayout frameLayout) {
		super();
		this.frameLayout = frameLayout;
	}

	@Override
	public void handleMessage(Message msg) {
		if (PortalFrameLayout.PlayerControllerPara.start == msg.what) {
			frameLayout.startMediaPlayer();
		}
	}
}
