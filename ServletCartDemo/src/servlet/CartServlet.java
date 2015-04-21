package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsDAO;
import entity.Cart;
import entity.Items;

public class CartServlet extends HttpServlet {

	private String action;//��ʾ���ﳵ�Ķ���,add,show,delete
	//��Ʒҵ���߼���Ķ���
	private ItemsDAO iDao = new ItemsDAO();
	/**
	 * Constructor of the object.
	 */
	public CartServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("action")!=null) {
			this.action = request.getParameter("action");
			if (action.equals("add")) {//����������Ʒ�����ﳵ
				if (addTocart(request, response)) {
					request.getRequestDispatcher("/success.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("/failure.jsp").forward(request, response);
				}
			}
			if (action.equals("show")) {//�������ʾ���ﳵ
//				showCart(request, response);
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}
			if (action.equals("delete")) {
				if (deleteFormCart(request, response)) {
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
				}
			}
		}
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * �ӹ��ﳵ����ɾ����Ʒ
	 */
	private boolean deleteFormCart(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		Items items = iDao.getItemsById(Integer.parseInt(id));
		if (cart.removeGoodsFromCart(items)) {
			return true;
		}
		return false;
	}

	/**
	 * @param request
	 * @param response
	 * �����Ʒ�����ﳵ�ķ���
	 */
	private boolean addTocart(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String number = request.getParameter("num");
		Items item = iDao.getItemsById(Integer.parseInt(id));
		
		//�Ƿ��ǵ�һ�θ����ﳵ�����Ʒ����Ҫ��session�д���һ���µĹ��ﳵ����
		if (request.getSession().getAttribute("cart")==null) {
			Cart cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if (cart.addGoodsInCart(item, Integer.parseInt(number))) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
