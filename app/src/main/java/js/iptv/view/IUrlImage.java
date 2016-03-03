package js.iptv.view;

import java.net.URL;

public interface IUrlImage {
	
	/**
	 * ��ȡ����ͼƬ�ŵ���Ӧ��view�У�����������ͼƬ,������view��Ӧ��activity��
	 * @param url
	 */
	public void setImageFromUrl(URL url);
	
	/**
	 * �ÿؼ��õ������Ч��
	 * @param resourceId
	 */
	public void onFocusWithAnim(int resourceId);
	
	
	/**
	 * �ÿؼ�ʧȥ�����Ч��
	 */
	public void blurFocus();

}
