package trackTest;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import physicalObject.trackE1;
import track.Track;

public class trackTest
{
	int rep;
	Track test = new Track(rep);
	//Testing strategy for getName()
	//�����ض��Ĳ����½�һ��Trackʵ������ʾ�������Ȼ�����getRep()������
	//��ȡ����İ뾶���жϷ��ص�������Ԥ�������Ƿ����
	@Test
	public void testGetRep()
	{
		assertTrue(test.getRep() == rep);
	}
}
