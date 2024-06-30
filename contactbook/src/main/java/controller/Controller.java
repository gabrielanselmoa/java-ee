package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dao. */
	DAO dao = new DAO();
	
	/** The contact JB. */
	JavaBeans contactJB = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		if (action.equals("/main")) {
			getAllContact(request, response);
		} else if (action.equals("/insert")) {
			createContact(request, response);
		} else if (action.equals("/select")) {
			selectContact(request, response);
		} else if (action.equals("/update")) {
			editContact(request, response);
		} else if (action.equals("/delete")) {
			deleteContact(request, response);
		} else if (action.equals("/report")) {
			generateReport(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Gets the all contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the all contact
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// CRUD - READ CONTACTS
	protected void getAllContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Creating an object that will receive JavaBeans Data
		ArrayList<JavaBeans> list = dao.listContacts();

		// Sending the document list to Contact.jsp
		request.setAttribute("getAllContacts", list);
		RequestDispatcher rd = request.getRequestDispatcher("contact.jsp");
		rd.forward(request, response);
	}

	/**
	 * Creates the contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// CRUD - CREATE CONTACT
	protected void createContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Setting values in JavaBeans Variables
		contactJB.setName(request.getParameter("name"));
		contactJB.setPhone(request.getParameter("phone"));
		contactJB.setEmail(request.getParameter("email"));

		// Invoking insertContact method from DAO
		dao.insertContact(contactJB);

		// Redirecting to Contact.jsp document
		response.sendRedirect("main");
	}

	/**
	 * Select contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// CRUD - UPDATE CONTACT - SELECT BY ID
	protected void selectContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Setting a value for JavaBeans Variable (ID)
		contactJB.setId(request.getParameter("id"));

		// Executing - selectContact Method from (DAO)
		dao.selectContact(contactJB);

		// Setting JavaBeans Content into Forms Attributes
		request.setAttribute("id", contactJB.getId());
		request.setAttribute("name", contactJB.getName());
		request.setAttribute("phone", contactJB.getPhone());
		request.setAttribute("email", contactJB.getEmail());

		// Send them to Edit.jsp
		RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
		rd.forward(request, response);
	}

	/**
	 * Edits the contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// CRUD - UPDATE CONTACT - EDIT DATA
	protected void editContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Setting values in JavaBeans variables
		contactJB.setId(request.getParameter("id"));
		contactJB.setName(request.getParameter("name"));
		contactJB.setPhone(request.getParameter("phone"));
		contactJB.setEmail(request.getParameter("email"));

		// Executing - UpdateContact Method (DAO)
		dao.updateContact(contactJB);

		// Redirect to contact.jsp document (all data updated)
		response.sendRedirect("main");
	}

	/**
	 * Delete contact.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// CRUD - DELETE CONTACT
	protected void deleteContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Setting a value for JavaBeans Variable (ID)
		contactJB.setId(request.getParameter("id"));

		// Executing - deleteContact Method (DAO)
		dao.deleteContact(contactJB);

		// Redirecting to Contact.jsp document (all data updated)
		response.sendRedirect("main");
	}

	/**
	 * Generate report.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// GENERATE REPORT.PDF
	protected void generateReport(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Document document = new Document();

		try {
			// Setting content type
			response.setContentType("application/pdf");

			// Setting Document Name
			response.addHeader("Content-Disposition", "inline; filename=" + "contacts.pdf");

			// Create document
			PdfWriter.getInstance(document, response.getOutputStream());

			// Open document -> add content
			document.open();
			document.add(new Paragraph("Contact List:"));
			document.add(new Paragraph(" "));

			// Create Table
			PdfPTable table = new PdfPTable(3);

			// Create Header
			PdfPCell col1 = new PdfPCell(new Paragraph("Name"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Phone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));

			table.addCell(col1);
			table.addCell(col2);
			table.addCell(col3);

			// Seeding Contact Table
			ArrayList<JavaBeans> list = dao.listContacts();

			for (int i = 0; i < list.size(); i++) {
				table.addCell(list.get(i).getName());
				table.addCell(list.get(i).getPhone());
				table.addCell(list.get(i).getEmail());
			}

			// Adding all table data into PDF
			document.add(table);

			// Close the document
			document.close();
		} catch (Exception e) {
			System.out.println(e);
			document.close();
		}

	}
}
