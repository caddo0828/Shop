package service;

import java.util.ArrayList;
import java.util.HashMap;

import bean.Book;
import bean.Order;

/**
 * ������ʷ��¼������
 * @author ����
 */
public class HistoryHelper {
	public static ArrayList<Order> orderList = new ArrayList<>();
	
	public void add(Order order) {
		orderList.add(order);
	}

}
