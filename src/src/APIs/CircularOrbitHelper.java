package APIs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import centralObject.L1;
import circularOrbit.circularOrbit;
import circularOrbit.tie;
import physicalObject.E1;
import physicalObject.socialE1;

public class CircularOrbitHelper<L extends L1, E extends E1>
{
	int trackNumber;
	Map<Integer, Set<E>> temptrackObject;
	// Set<E> things = new HashSet<E>();
	Map<Integer, Integer> thingsNumberPerTrack = new HashMap<Integer, Integer>();// ����Ҫ����һ�οռ�
	Map<E, position> thingsPosition = new HashMap<E, position>();
	Map<E, Set<E>> EErelationship;
	Set<E> LErelationship;
	List<tie> tempsocialTie;
	L central;
//	Map<String, E> friend;

	public CircularOrbitHelper(circularOrbit c)// ���캯��
	{
		central = (L) c.getCentral();
		temptrackObject = c.getTrackObject();
		trackNumber = temptrackObject.size();
		EErelationship = c.getRelationship();
		LErelationship = c.getLErelationship();
		System.out.println("y");// ����û������
		Iterator<Integer> iterator = temptrackObject.keySet().iterator();
		tempsocialTie = c.getSocialTie();
//		friend = c.getFriend();
		while (iterator.hasNext())
//		for (int i = 0; i < trackNumber; i++)
		{
			int i = iterator.next();
			// System.out.println(i);
			thingsNumberPerTrack.put(i, temptrackObject.get(i).size());// ��ʱ�����Ӧ��û������
		}

	}

	public void visualize()// ����Ҫ���õĺ���
	{
//		JFrame  jf = new JFrame();
//		MyPanel jp = new MyPanel();
//		jf.setBounds(200, 200, 500, 500);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jf.add(jp);
//		jf.setVisible(true);
//		jp.display();
//		GraphicsDemod my = new GraphicsDemod();
		myFrame frame = new myFrame();
		frame.setVisible(true);
	}

	class myPanel extends JPanel
	{
		private myFrame frame;

		public myPanel(myFrame frame)
		{
			super();
			this.frame = frame;
		}

		public void paintComponent(Graphics g)
		{
			// g.drawRoundRect(10, 10, 100, 100, 100, 100);
			int length = 50;
			int doubleLength = 2 * length;
			super.paintComponent(g);
			int width = getWidth();
			int height = getHeight();
			if (central!= null)
			{
				if (central.getA1() == 0 && central.getA2() == 0)
				{
					g.fillOval(width / 2 - 16, height / 2 - 16, 32, 32);// �����ĵ�����
				}
				else
				{
					int ri = 4;// �뾶
					if (central.getA1() > 0)
					{

						g.setColor(Color.CYAN);
						int p = central.getA1();
						for (int i = 0; i < p; i++)
						{
							g.fillOval(width / 2 + i * (2 * ri), height / 2 - ri, 2 * ri, 2 * ri);
						}
					}
					if (central.getA2() > 0)
					{
						g.setColor(Color.darkGray);
						int q = central.getA2();
						for (int i = 0; i < q; i++)
						{
							g.fillOval(width / 2 - (i + 1) * (2 * ri), height / 2 - ri, 2 * ri, 2 * ri);
						}
					}
				}
			}
			
			g.setColor(Color.BLACK);
			Iterator<Integer> iterator = temptrackObject.keySet().iterator();
			int m = 0;
			while (iterator.hasNext())
			{
				// ���������
				int realNumber = iterator.next();
				System.out.println("realNumber" + realNumber);
				g.drawOval(width / 2 - (m + 1) * length, height / 2 - (m + 1) * length, doubleLength + doubleLength * m,
						doubleLength + doubleLength * m);
				int thingsNumber = thingsNumberPerTrack.get(realNumber);
				// System.out.println("thingsNumber" + thingsNumber);
				double partition;
				if (thingsNumber != 0)// ����һ�����֮���¹������û�������
				{
					partition = (Math.PI) / ((double) thingsNumber);
					// ���㲿��
					double a = 0.00;
					// ��øù���ϵ���������
					List<E> things = new ArrayList<E>(temptrackObject.get(realNumber));
					for (int j = 0; j < thingsNumber; j++)
					{
						// cos�ĵ�λ�ǻ���
						a = a + partition;
						int x = width / 2 - (m + 1) * length + (length * (m + 1))
								+ (int) (((m + 1) * length) * Math.cos(a + j * partition));
						int y = height / 2 - (m + 1) * length + (length * (m + 1))
								+ (int) (((m + 1) * length) * Math.sin(a + j * partition));
						// thingsPosition.put(temptrackObject.get(i), new position(x, y));
						thingsPosition.put(things.get(j), new position(x, y));// ����ϵ�ÿ�������Ӧ��һ������
						// g.setColor(Color.YELLOW);
						// System.out.println("YEACHYEACH");// ָ������һ������
						g.setFont(new Font("����", Font.PLAIN, 12));
						g.fillOval(x - 8, y - 8, 16, 16);
						g.setColor(Color.GREEN);
						g.drawString(things.get(j).getName(), x - 12, y - 12);
						g.setColor(Color.BLACK);
					}
				}
				m++;
			}
			g.setColor(Color.RED);
			System.out.println("i is " + m);
			System.out.println(temptrackObject.size());
			// ���������������
			// System.out.println("׼������");// ���ϴ���û������
			Iterator<E> iterator1 = EErelationship.keySet().iterator();
			// System.out.println("hhh" + EErelationship.size());
			// System.out.println("����׼������");
			g.setColor(Color.RED);
			while (iterator1.hasNext())
			{
				// System.out.println("��ʼ��������");
				E r1 = iterator1.next();

				Set<E> rr1 = EErelationship.get(r1);
				Iterator<E> iterator2 = rr1.iterator();
				// System.out.println("����");
				while (iterator2.hasNext())// ɾ����һ����֮�����ѭ������û����
				{
					g.setColor(Color.RED);
					// System.out.println("xxxxxxxxxx");
					E m1 = iterator2.next();
					g.drawLine(thingsPosition.get(r1).getX(), thingsPosition.get(r1).getY(),
							thingsPosition.get(m1).getX(), thingsPosition.get(m1).getY());
					// ��������ע�����ܶ�,���ܶȵ���ɫ����ɫ
					g.setColor(Color.blue);
					for (int mn = 0; mn < tempsocialTie.size(); mn++)
					{
//						if(friend.containsKey(tempsocialTie.get(i).getName1())&&friend.containsKey(tempsocialTie.get(i).getName2()))
//						{
//							int numberx = (thingsPosition.get(r1).getX()+thingsPosition.get(m1).getX())/2;
//							int numbery = (thingsPosition.get(r1).getY()+thingsPosition.get(m1).getY())/2;
//							g.drawString(Float.toString(tempsocialTie.get(m).getIni()), numberx, numbery);
//						}
						if ((r1.getName().equals(tempsocialTie.get(mn).getName1())
								&& m1.getName().equals(tempsocialTie.get(mn).getName2()))
								|| (r1.getName().equals(tempsocialTie.get(mn).getName2())
										&& m1.getName().equals(tempsocialTie.get(mn).getName1())))
						{
							int numberx = (thingsPosition.get(r1).getX() + thingsPosition.get(m1).getX()) / 2;
							int numbery = (thingsPosition.get(r1).getY() + thingsPosition.get(m1).getY()) / 2;
							g.drawString(Float.toString(tempsocialTie.get(mn).getIni()), numberx, numbery);
						}
//						if(r1.getName().equals(tempsocialTie.get(m).getName2())&&m1.getName().equals(tempsocialTie.get(m).getName1()))
//						{
//							int numberx = ;
//							int numbery = ;
//							g.drawString(Float.toString(tempsocialTie.get(m).getIni()), numberx, numbery);
//						}
					}
				}
			}
//			for (int v = 0; v < tempsocialTie.size(); v++)
//			{
//				if (friend.containsKey(tempsocialTie.get(v).getName1())
//						&& friend.containsKey(tempsocialTie.get(v).getName2()))
//				{
//					if (thingsPosition.containsKey(friend.get(tempsocialTie.get(v).getName1()))
//							&& thingsPosition.containsKey(friend.get(tempsocialTie.get(v).getName2())))
//					{
//						int numberx = (thingsPosition.get(friend.get(tempsocialTie.get(v).getName1())).getX()
//								+ thingsPosition.get(friend.get(tempsocialTie.get(v).getName2())).getX()) / 2;
//						int numbery = (thingsPosition.get(friend.get(tempsocialTie.get(v).getName1())).getY()
//								+ thingsPosition.get(friend.get(tempsocialTie.get(v).getName2())).getY()) / 2;
//						;
//						g.drawString(Float.toString(tempsocialTie.get(v).getIni()), numberx, numbery);
//					}
//
//				}
//			}
			// �����ĵ��������������
			Iterator<E> iterator2 = LErelationship.iterator();
			// System.out.println("eee" + LErelationship.size());
			while (iterator2.hasNext())
			{
				g.setColor(Color.RED);
				E r2 = iterator2.next();
				g.drawLine(width / 2, height / 2, thingsPosition.get(r2).getX(), thingsPosition.get(r2).getY());
				g.setColor(Color.blue);
				for (int v = 0; v < tempsocialTie.size(); v++)
				{
					// ����ж�ʵ���ϱȽ����࣬�����friend�ӵ�concretecircularOrbit����ͻ�úܶ�
					if ((r2.getName().equals(tempsocialTie.get(v).getName1())
							&& central.getName().equals(tempsocialTie.get(v).getName2()))
							|| (r2.getName().equals(tempsocialTie.get(v).getName2())
									&& central.getName().equals(tempsocialTie.get(v).getName1())))
					{
						int numberx = (thingsPosition.get(r2).getX() + width / 2) / 2;
						int numbery = (thingsPosition.get(r2).getY() + height / 2) / 2;
						g.drawString(Float.toString(tempsocialTie.get(v).getIni()), numberx, numbery);
					}
//					if(r1.getName().equals(tempsocialTie.get(m).getName2())&&m1.getName().equals(tempsocialTie.get(m).getName1()))
//					{
//						int numberx = ;
//						int numbery = ;
//						g.drawString(Float.toString(tempsocialTie.get(m).getIni()), numberx, numbery);
//					}
				}
			}
//			for (int u = 0; u < tempsocialTie.size(); u++)
//			{
//				if (tempsocialTie.get(u).getName1().equals(cretral.getName()))
//				{
//					if (friend.containsKey(tempsocialTie.get(u).getName2()))
//					{
//						if (thingsPosition.containsKey(friend.get(tempsocialTie.get(u).getName2())))
//						{
//							int numberx = (thingsPosition.get(friend.get(tempsocialTie.get(u).getName2())).getX()
//									+ (width / 2)) / 2;
//							int numbery = (thingsPosition.get(friend.get(tempsocialTie.get(u).getName2())).getY()
//									+ (height / 2)) / 2;
//							;
//							g.drawString(Float.toString(tempsocialTie.get(u).getIni()), numberx, numbery);
//						}
//					}
//
//				}
//				if (tempsocialTie.get(u).getName2().equals(cretral.getName()))
//				{
//					if (friend.containsKey(tempsocialTie.get(u).getName1()))
//					{
//						if (thingsPosition.containsKey(friend.get(tempsocialTie.get(u).getName1())))
//						{
//							int numberx = (thingsPosition.get(friend.get(tempsocialTie.get(u).getName1())).getX()
//									+ (width / 2)) / 2;
//							int numbery = (thingsPosition.get(friend.get(tempsocialTie.get(u).getName1())).getY()
//									+ (height / 2)) / 2;
//							;
//							g.drawString(Float.toString(tempsocialTie.get(u).getIni()), numberx, numbery);
//						}
//					}
//				}
//			}

		}
	}

	class myFrame extends JFrame
	{
		public static final String TITLE = "Javaͼ�λ���";
		public static final int WIDTH = 1000;
		public static final int HEIGHT = 618;

		public myFrame()
		{
			super();
			initFrame();
		}

		private void initFrame()
		{
			// ���� ���ڱ��� �� ���ڴ�С
			setTitle(TITLE);
			setSize(WIDTH, HEIGHT);
			// ���ô��ڹرհ�ť��Ĭ�ϲ���(����ر�ʱ�˳�����)
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// �Ѵ���λ�����õ���Ļ������
			setLocationRelativeTo(null);
			// ���ô��ڵ��������
			myPanel panel = new myPanel(this);
			setContentPane(panel);

		}
	}

	class position
	{
		private final int x;
		private final int y;

		public position(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		public int getX()
		{
			return this.x;
		}

		public int getY()
		{
			return this.y;
		}
	}

}

//	public static void main(String[] args)
//	{
//		JFrame  jf = new JFrame();
//		MyPanel jp = new MyPanel();
//		
//		jf.setBounds(200, 200, 500, 500);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jf.add(jp);
//		jf.setVisible(true);
//		
//		while(true)
//		{
//			//��ͣ���ػ�JPanel,ʵ�ֶ�����Ч��
//			jp.display();
//			
//			try
//			{
//				Thread.sleep(300);
//			}
//			catch (InterruptedException e)
//			{
//				e.printStackTrace();
//			}
//		}
//	}

//}//
///
//		Graphics2D g2d = (Graphics2D)g;
//		
//		g2d.setColor(Color.RED);//���û�ͼ����ɫ
//		g2d.fill3DRect(x, y, 100, 100, true);//���һ������		
// }//
