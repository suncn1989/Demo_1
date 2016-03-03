package js.iptv.view;

import java.net.URL;

public interface IUrlImage {
	
	/**
	 * 获取网络图片放到对应的view中，并缓存网络图片,缓存在view对应的activity中
	 * @param url
	 */
	public void setImageFromUrl(URL url);
	
	/**
	 * 该控件得到焦点的效果
	 * @param resourceId
	 */
	public void onFocusWithAnim(int resourceId);
	
	
	/**
	 * 该控件失去焦点的效果
	 */
	public void blurFocus();

}
