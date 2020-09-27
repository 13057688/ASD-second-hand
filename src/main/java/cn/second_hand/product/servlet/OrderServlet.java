package cn.second_hand.product.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.second_hand.product.service.OrderService;
import cn.second_hand.query.AuditQueryObject;
import cn.second_hand.query.PageResult;
import cn.second_hand.user.servlet.BaseServlet;

public class OrderServlet extends BaseServlet{
	private OrderService orderService = new OrderService();
	
	public void getOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException,Exception {
		System.out.println("-------进入方法--------------");
		String typeString =  request.getParameter("typeId");
		AuditQueryObject auditQueryObject = new AuditQueryObject();
		auditQueryObject.setPageSize(1);
		int currentPage = 1;
		if(request.getParameter("currentPage")==null || request.getParameter("currentPage").equals("")) {
			auditQueryObject.setCurrentPage(currentPage);
		}
		PageResult pageResult;
		if (Integer.valueOf(typeString)==1) {
			pageResult = orderService.getBuyersOrder(auditQueryObject);
			request.setAttribute("pageResult", pageResult);
			System.out.println("-----买家订单----------");
			request.getRequestDispatcher("/BuyersOrder.jsp").forward(request, response);
			//return "http://localhost:8080/second-hand/BuyersOrder.jsp";
		}else {
			pageResult = orderService.getSellerOrder(auditQueryObject);
			request.setAttribute("pageResult", pageResult);
			System.out.println("-----卖家订单----------");
			request.getRequestDispatcher("/SellerOrder.jsp").forward(request, response);
			//return "http://localhost:8080/second-hand/SellerOrder.jsp";
			//http://localhost:8080/second-hand/OrderServlet?method=getOrderList&typeId=1
		}
	
	}

}