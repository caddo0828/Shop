package bean;

/**
 * ������Ϣ��
 * @author ����
 */
public class Order {
	private String orderID;//�������
	private Book book; //�����鼮
	private double totalPrice;//��Ʒ�ܼ�
	private String date;//�����ɽ�ʱ��
	private String orderName; //����������
	private String tel ;//��������ϵ�绰
	private String address;//�ջ���ַ
	
	public Order() {
	}
	
	public Order(String orderName, String tel, String address) {
		this.orderName = orderName;
		this.tel = tel;
		this.address = address;
	}
	
	public Order(String orderID,Book book,double totalPrice,String date) {
		this.orderID = orderID;
		this.book = book;
		this.totalPrice = totalPrice;
		this.date = date;
	}

	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
