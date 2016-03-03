package js.iptv.activity;

/**
 * 翻页控制接口
 * @author canoe
 *
 */
public interface IPagable {

	/**
	 * 向下或者向右翻页
	 * isBar为true的时候，表示上部导航栏的翻页，否则，表示scrollView部分的翻页
	 */
	public void pageDown(boolean isBar);
	
	/**
	 * 向上或者向左翻页
	 * isBar为true的时候，表示上部导航栏的翻页，否则，表示scrollView部分的翻页
	 */
	public void pageUp(boolean isBar);
}
