package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {

	// Connection Module
	// Connection Parameters

	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";

	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbcontacts?useTimezone=true&serverTimezone=UTC";

	/** The user. */
	private String user = "root";

	/** The password. */
	private String password = "Dba@1234";

	/**
	 * Connect.
	 *
	 * @return the connection
	 */
	// Connection Method
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * CRUD - CREATE *.
	 *
	 * @param newContact the new contact
	 */
	public void insertContact(JavaBeans newContact) {
		String create = "INSERT INTO contacts (name,phone,email) VALUES (?,?,?)";
		try {
			// Open DB connection
			Connection con = connect();

			// Prepare a query to execute in DB
			PreparedStatement pst = con.prepareStatement(create);

			// Replace (?) parameters to JavaBeans' variables content
			pst.setString(1, newContact.getName());
			pst.setString(2, newContact.getPhone());
			pst.setString(3, newContact.getEmail());

			// Execute the query
			pst.executeUpdate();

			// Close DB Connection
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * CRUD - SELECT *.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listContacts() {

		ArrayList<JavaBeans> contacts = new ArrayList<>();
		String read = "SELECT * FROM contacts ORDER BY name";

		try {
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(read);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String email = rs.getString(4);

				// Seeding the Array List
				contacts.add(new JavaBeans(id, name, phone, email));
			}
			con.close();
			return contacts;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * CRUD - SELECT BY ID *.
	 *
	 * @param contact the contact
	 */
	public void selectContact(JavaBeans contact) {

		String read2 = "SELECT * FROM contacts WHERE id = ?";

		try {
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contact.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				contact.setId(rs.getString(1));
				contact.setName(rs.getString(2));
				contact.setPhone(rs.getString(3));
				contact.setEmail(rs.getString(4));
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * CRUD - UPDATE *.
	 *
	 * @param contact the contact
	 */
	public void updateContact(JavaBeans contact) {

		String update = "UPDATE contacts SET name=?, phone=?, email=? WHERE id=?;";

		try {
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getPhone());
			pst.setString(3, contact.getEmail());
			pst.setString(4, contact.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * CRUD - DELETE *.
	 *
	 * @param contact the contact
	 */
	public void deleteContact(JavaBeans contact) {

		String delete = "DELETE FROM contacts WHERE id=?";

		try {
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contact.getId());
			pst.executeUpdate();
			System.out.println("Deleted ID: " + contact.getId());
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
