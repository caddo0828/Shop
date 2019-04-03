package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import bean.Book;
import dao.BookDao;

/**
 * ���ﳵ�࣬�Լ��빺������ݽ��д��������鼮��ӣ�ɾ�����޸ģ�����۸�
 * @author ����
 */
public class MyCar {
	private static HashMap<String, Book> hm = new HashMap<String, Book>();

	// ����鼮
	public void add(String id, Book book,int butNums) {
		//�жϼ������Ƿ��Ѿ�������ͬ���鼮������Ѿ�����ֱ�ӽ��鼮������1
		if (hm.containsKey(id)) {
			book = hm.get(id);
			book.setShopNums(book.getShopNums() + butNums);
			hm.put(id, book);
		} else {
			book.setShopNums(butNums);
			hm.put(id, book);
		}
	}

	//ɾ���鼮������id�ŴӼ������Ƴ�
	public void delete(String id) {
		//�ҵ���Ӧ���鼮�����鼮�����=ԭ�����+��ɾ��������
		hm.remove(id);
	}

	//�޸��鼮������
	public void update(String id,int nums) {
		//���ݶ�Ӧ���鼮id�ҵ���
		Book book = hm.get(id);
		//��ȡ�������޸�ǰ�Ķ�Ӧ���鼮����������Ϊ��ʱ��û�н��޸ĺ���������뼯����
		//��˸����޸����id�ҵ���Ӧ���飬�ڻ�ȡ�޸�ǰ������
	    //int beforeNums = book.getShopNums();
		//�޸Ĺ�����鼮��
		book.setShopNums(nums);
		//book.setNums(book.getNums()-nums+beforeNums);
	    //���鼮������ӵ�������
		hm.put(id, book);
		
	}
	
	//��չ��ﳵ
	public void clear() {
		hm.clear();
	}
	
	// ��ʾ�����д�ŵ������鼮,�ҽ������鼮�����ArrayList������
	public ArrayList<Book> findAllBook() {
		ArrayList<Book> list = new ArrayList<Book>();
		// ��map���Ͻ��б����ĵ����е�Ԫ��ֵ
		Iterator<String> iterator = hm.keySet().iterator();
		while (iterator.hasNext()) {
			String id = iterator.next();
			// �ҵ���ǰid��Ӧ���鼮
			Book book = hm.get(id);
			list.add(book);
		}
		return list;
	}
	
	

}
