package com.c05.customer.controller;

import com.c05.customer.model.Customer;
import com.c05.customer.service.CustomerGetDb;
import com.c05.customer.service.CustomerService;
import com.c05.customer.service.CustomerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", urlPatterns = "/customers")
public class CustomerController extends HttpServlet {
    private CustomerService service = new CustomerGetDb();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("page");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showFormCreate(req, resp);
                break;
            case "edit":
                break;
            case "delete":
                break;
            case "detail":
                showCustomerById(req, resp);
                break;
            default:
                showAllCustomer(req, resp);
                break;
        }
    }

    private void showFormCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/create.jsp");
        dispatcher.forward(req, resp);
    }

    private void showCustomerById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int id1 = Integer.parseInt(id);
        Customer customer = service.findById(id1);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/detail.jsp");
        req.setAttribute("c", customer);
        dispatcher.forward(req, resp);
    }

    private void showAllCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher reqd = req.getRequestDispatcher("customer/list.jsp");
        List<Customer> customers = service.findAll();
        req.setAttribute("customers", customers);
        reqd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("page");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createNewCustomer(req, resp);
                break;
//            case "edit":
//                break;
//            case "delete":
//                break;
//            case "detail":
//                showCustomerById(req, resp);
//                break;
//            default:
//                showAllCustomer(req, resp);
//                break;
        }
    }

    private void createNewCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("mail");
        String address = req.getParameter("add");
//        int id = (int)(Math.random() * 10000);

        Customer customer = new Customer(name, email, address);
        service.save(customer);
        resp.sendRedirect("/customers");
//        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/create.jsp");
//        req.setAttribute("message", "New customer was created");
//        try {
//            dispatcher.forward(req, resp);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
