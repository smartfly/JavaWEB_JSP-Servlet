package entity;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TestCartDemo {
	public static void main(String[] args) {
		//�ȴ���������Ʒ�Ķ���
		Items i1 = new Items(1, "��������Ь","����",200, 500, "001.jpg");
		Items i2 = new Items(2, "�����˶�Ь","����",300, 500, "002.jpg");
		Items i3 = new Items(1, "��������Ь","����",200, 500, "001.jpg");
		
		Cart c = new Cart();
		c.addGoodsInCart(i1, 1);
		c.addGoodsInCart(i2, 2);
		//�ٴι�����������Ь��������˫
		c.addGoodsInCart(i3, 3);
		
		//����������Ʒ�ļ���
		Set<Map.Entry<Items, Integer>> items = c.getGoods().entrySet();
		for (Entry<Items, Integer> entry : items) {
			System.out.println(entry);
		}
		
		
		System.out.println("���ﳵ���ܽ��:"+c.getTotalPrice());
	}
}
