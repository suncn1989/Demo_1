package js.iptv.activity;

/**
 * ��ҳ���ƽӿ�
 * @author canoe
 *
 */
public interface IPagable {

	/**
	 * ���»������ҷ�ҳ
	 * isBarΪtrue��ʱ�򣬱�ʾ�ϲ��������ķ�ҳ�����򣬱�ʾscrollView���ֵķ�ҳ
	 */
	public void pageDown(boolean isBar);
	
	/**
	 * ���ϻ�������ҳ
	 * isBarΪtrue��ʱ�򣬱�ʾ�ϲ��������ķ�ҳ�����򣬱�ʾscrollView���ֵķ�ҳ
	 */
	public void pageUp(boolean isBar);
}
