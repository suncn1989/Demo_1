package js.iptv.utils;

import java.io.InputStream;

public class GetAllSdPath {
	public static String getAllSDPath(){
		String strMountInfo = "";

		// 1.���Ȼ��ϵͳ�Ѽ��ص��ļ�ϵͳ��Ϣ
		try {
			// ����ϵͳ��������������
			ProcessBuilder objProcessBuilder = new ProcessBuilder();
			// ִ�� mount -h ���Կ��� mount : list mounted filesystems
			// ������������г��Ѽ��ص��ļ�ϵͳ
			objProcessBuilder.command("mount"); // �µĲ���ϵͳ��������Ĳ���
			// ���ô�������������׼����ϲ�
			objProcessBuilder.redirectErrorStream(true);
			// ���ڵ�ǰϵͳ������������״̬��ʼһ���½��̣������ؽ���ʵ��
			Process objProcess = objProcessBuilder.start();
			// �����߳��������ز���ϵͳ����ִ�н��������ر��ز���ϵͳ����ķ���ֵ
			objProcess.waitFor();

			// �õ����̶�����������������ڽ��̶�����˵�����뱾�ز���ϵͳ����ı�׼�����(stdout)�����ӵ�
			InputStream objInputStream = objProcess.getInputStream();

			byte[] buffer = new byte[1024];

			// ��ȡ mount ������򷵻ص���Ϣ�ı�
			while (-1 != objInputStream.read(buffer)) {
				strMountInfo = strMountInfo + new String(buffer);
			}

			// �رս��̶����������
			objInputStream.close();

			// ��ֹ���̲��ͷ�������ص��κ���
			objProcess.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 2.Ȼ������ϵͳ�Ѽ��ص��ļ�ϵͳ��Ϣ����� SD ��·��
		// mount ���ص��Ѽ��ص��ļ�ϵͳ��Ϣ����һ��һ����Ϣ����ʽ���ֵģ�
		// �������û��з�����ַ���
		String[] lines = strMountInfo.split("\n");

		// ��ո��ַ����������潫������װ���������õ� SD ��·���б�
		strMountInfo = "";

		for (int i = 0; i < lines.length; i++) {
			// ����������� /mnt/�� vfat �ַ�����˵����������/���� SD ���Ĺ���·��
			if (-1 != lines[i].indexOf(" /mnt/") && // ǰ��Ҫ�пո��Է�����ȡ��
					-1 != lines[i].indexOf(" vfat ")) // ǰ����пո�
			{
				// ���Կո�ָ�������ַ���
				String[] blocks = lines[i].split("\\s"); // \\s Ϊ�ո��ַ�
				for (int j = 0; j < blocks.length; j++) {
					// ����ַ����к���/mnt/�ַ�����˵������������Ҫ�ҵ� SD ������·��
					if (-1 != blocks[j].indexOf("/mnt/")) {
						// �ų��ظ���·��
						if (-1 == strMountInfo.indexOf(blocks[j])) {
							// �÷ֺŷ�(;)�ָ� SD ��·���б�
							strMountInfo += blocks[j] + ";";
						}
					}
				}
			}
		}

		return strMountInfo;
	}
}
