package circularOrbit;

import java.util.List;
import java.util.Map;
import java.util.Set;

import centralObject.L1;
import physicalObject.E1;
import physicalObject.socialE1;

public interface circularOrbit<L,E>
{
	public static <L,E> circularOrbit<L,E> empty()//����lab2
	{
		return new ConcreteCircularObject<L, E>();
	}
	/**
	 * ���ӹ��
	 * �½�һ���������(track)�����½�һ������������ӳ��
	 */
	public void addTrack();//����һ�����
	
	/*
	 * ɾ��һ�����
	 * @param ����ı��
	 * �ڴ洢��������ݽṹ��ɾ�����������ͬʱ�ڹ��ϵͳ��ɾ����������ϵ��������壬
	 * ��Щ�����������Ĺ�ϵҲ�ᱻɾ��
	 */
	public void deleteTrack(int number);//ȥ��һ�����
	
	/**
	 * �������ĵ�����
	 * @param cre ��������
	 * �Ը����Ĳ����½�һ�����ĵ��������ʵ��
	 */
	public void addCentralObject(L cre);//�������ĵ�����
	
	/**
	 * �ڸ������������һ���������壬��Ҫ�����쳣���������Ĺ����Ų����ڡ�����ɹ�������壬
	 * ���½����嵽�����ӳ�䣬���嵽λ�ã��Ƕȣ���ӳ�䣬�Լ�������ϵ��ӳ��
	 * @param ob ������
	 * @param t �������
	 * @return �ɹ���������򷵻�true,���򷵻�false������������ʾ
	 */
	public boolean addTrackObject(E ob, int t);//���ض����������һ�����壨����������λ�ã�
	
	/**
	 * ɾ����������ϵĸ������壬��Ҫ�����쳣���������Ĺ����Ų����ڣ��������岻�ڸ�������ϡ�
	 * ����ɹ�ɾ�����壬����ͬʱɾ��������������ӳ�䣬�����嵽�����ӳ�䣬�����嵽λ�ã��Ƕȣ���ӳ�䣬
	 * ������������������Ĺ�ϵ�������������ĵ���������Ĺ�ϵ
	 * @param ob ������
	 * @param t �������
	 * @return �ɹ�ɾ�������򷵻�true,���򷵻�false������������ʾ
	 */
	public boolean deleteTrackObject(E ob, int t);// ���ض������ɾ��һ�����壨����������λ�ã�
	
	/**
	 * ���Ӹ��������������������֮��Ĺ�ϵ����Ҫ�����쳣�����������岻��ϵͳ�С�
	 * �������������Ҫ��������Ӧ�����ݽṹMap������һ��ӳ�伴��
	 * @param e2 �������
	 * @return ������ӳɹ��򷵻�true,���򷵻�false������������ʾ
	 */
	public boolean addLErelationship(E e2);//������������͹������֮��Ĺ�ϵ
	
	/**
	 * �������������������֮��Ĺ�ϵ����Ҫ�����쳣�����������岻��ϵͳ�С�
	 * �������������Ҫ��������Ӧ�����ݽṹMap������һ��ӳ�伴��
	 * @param e1 �������
	 * @param e2 �������
	 * @return ������ӳɹ��򷵻�true,���򷵻�false������������ʾ
	 */
	public boolean addEErelationship(E e1,E e2);//���������������֮��Ĺ�ϵ
	
	/**
	 * ɾ�����������������֮��Ĺ�ϵ����Ҫ�����쳣�����������岻��ϵͳ�С�
	 * �������������Ҫ��������Ӧ�����ݽṹMap��ɾ��һ��ӳ�伴��
	 * @param e1 �������
	 * @param e2 �������
	 * @return ���ɾ���ɹ��򷵻�true,���򷵻�false������������ʾ
	 */
	public boolean deleteEErelationship(E e1,E e2);
	
	/**
	 * ɾ�����������������������֮��Ĺ�ϵ����Ҫ�����쳣�����������岻��ϵͳ�С�
	 * �������������Ҫ��������Ӧ�����ݽṹMap��ɾ��һ��ӳ�伴��
	 * @param e2 �������
	 * @return
	 */
	public boolean deleteLErelationship(E e2);
	
	/**
	 * �Ӹ������ļ��ж��뽨�����ϵͳ��Ҫ�Ĳ��������洢����Ӧ�����ݽṹ�У�
	 * ��ͬ���͵Ĺ��ϵͳ�����ļ����ò�ͬ�ķ�ʽ
	 * @param name �ļ�����
	 */
	public void creatingTrackFromFiles(String name);//���ⲿ�ļ���ȡ���ݹ�����ϵͳ����
	
	/**
	 * �������ϵͳ�͹����ţ����������ԭ�й���ƶ��������Ŷ�Ӧ�Ĺ���ϣ���Ҫ
	 * �����쳣����������岻�ڹ��ϵͳ�У������ų�����Χ�ȡ��ƶ��ɹ�����Ҫ�ı���Ӧ��
	 * �Ĺ���������ӳ�䡢���嵽�����ӳ��
	 * @param object �������
	 * @param t ������
	 * @return ���ԾǨ�ɹ��򷵻�true,���򷵻�false������������ʾ
	 */
	public boolean transit (E object, int t);//ԭ�ӽṹ�еĵ��ӿ���ԾǨ���罻�����е��˵ĵ�λ���Ա仯
	
	/**
	 * ͨ�������Կ�¡��ȫ���ع��ϵͳ�й�����嵽������λ�õ�ӳ��
	 * @return ������嵽������λ�õ�ӳ��
	 */
	public Map<E, Double> getAngle();
	
	/**
	 * ͨ�������Կ�¡��ȫ���ع��ϵͳ�й�����嵽�����ڹ����ӳ��
	 * @return ������嵽�����ڹ����ӳ��
	 */
	public Map<E, Integer> getObjectTrack();
	
	/**
	 * ͨ�������Կ�¡��ȫ���ع��ϵͳ�д洢�������֮���ϵ��ӳ��
	 * @return �洢�������֮���ϵ��ӳ��
	 */
	public Map<E, Set<E>> getRelationship();
	
	/**
	 * ͨ�������Կ�¡��ȫ���ع��ϵͳ�й�����������е������ӳ��
	 * @return ������������е������ӳ��
	 */
	public Map<Integer, Set<E>> getTrackObject();
	
	/**
	 * ͨ�������Կ�¡��ȫ���ع��ϵͳ�д洢������������ĵ������ϵ��ӳ��
	 * @return �洢������������ĵ������ϵ��ӳ��
	 */
	public Set<E> getLErelationship();
	
	/**
	 * ͨ�������Կ�¡��ȫ���ع��ϵͳ�д洢�������֮����ϵ��Ϣӳ��
	 * @return �洢�������֮����ϵ��Ϣӳ��
	 */
	public List<tie> getSocialTie();
	
	/**
	 * ���ع��ϵͳ�����ĵ�����(���ɱ�����)
	 * @return ���ϵͳ�����ĵ�����
	 */
	public L getCentral();

}
