package service;

import java.util.ArrayList;
import java.util.HashMap;

import bean.Book;
import bean.Order;

/**
 * 订单历史记录辅助类
 * @author 老腰
 */
public class HistoryHelper {
	public static ArrayList<Order> orderList = new ArrayList<>();
	
	public void add(Order order) {
		orderList.add(order);
	}

}
