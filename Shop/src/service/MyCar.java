package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import bean.Book;
import dao.BookDao;

/**
 * 购物车类，对加入购物的数据进行处理。进行书籍添加，删除，修改，计算价格
 * @author 老腰
 */
public class MyCar {
	private static HashMap<String, Book> hm = new HashMap<String, Book>();

	// 添加书籍
	public void add(String id, Book book,int butNums) {
		//判断集合中是否已经存在相同的书籍，如果已经存在直接将书籍数量加1
		if (hm.containsKey(id)) {
			book = hm.get(id);
			book.setShopNums(book.getShopNums() + butNums);
			hm.put(id, book);
		} else {
			book.setShopNums(butNums);
			hm.put(id, book);
		}
	}

	//删除书籍，根据id号从集合中移除
	public void delete(String id) {
		//找到对应的书籍。将书籍库存量=原库存量+被删除的数量
		hm.remove(id);
	}

	//修改书籍的数量
	public void update(String id,int nums) {
		//根据对应的书籍id找到书
		Book book = hm.get(id);
		//获取集合中修改前的对应的书籍的数量，因为暂时还没有将修改后的数量放入集合中
		//因此根据修改书的id找到对应的书，在获取修改前的数量
	    //int beforeNums = book.getShopNums();
		//修改购买的书籍量
		book.setShopNums(nums);
		//book.setNums(book.getNums()-nums+beforeNums);
	    //将书籍重新添加到集合中
		hm.put(id, book);
		
	}
	
	//清空购物车
	public void clear() {
		hm.clear();
	}
	
	// 显示集合中存放的所有书籍,且将所有书籍存放在ArrayList集合中
	public ArrayList<Book> findAllBook() {
		ArrayList<Book> list = new ArrayList<Book>();
		// 对map集合进行遍历的到所有的元素值
		Iterator<String> iterator = hm.keySet().iterator();
		while (iterator.hasNext()) {
			String id = iterator.next();
			// 找到当前id对应的书籍
			Book book = hm.get(id);
			list.add(book);
		}
		return list;
	}
	
	

}
