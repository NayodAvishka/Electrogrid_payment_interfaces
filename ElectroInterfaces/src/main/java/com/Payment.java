package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electropayment", "root", "nayod123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	//INSERT PAYMENT DETAILS INTO TABLES
	
	public String insertPayment(String paymentName, String paymenttype, String paymentDesc, String paymentdate) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payments (paymentID,paymentName,paymenttype,paymentDesc,paymentdate)"+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, paymentName);
			preparedStmt.setString(3, paymenttype);
			preparedStmt.setString(4, paymentDesc);
			preparedStmt.setString(5, paymentdate);
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//READ PAYMENT DETAILS 
	
	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Payment ID </th><th>Payment Name</th>" + "<th>Payment type</th>"
					+ "<th>Payment Description</th>" + "<th>Payment date</th>" + " <th>Update</th><th>Remove</th></tr>";

			String query = "select * from payments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String paymentName = rs.getString("paymentName");
				String paymenttype = rs.getString("paymenttype");
				String paymentDesc = rs.getString("paymentDesc");
				String paymentdate = rs.getString("paymentdate");
				// Add into the html table
				
				output += "<td>" + paymentName + "</td>";
				output += "<td>" + paymenttype + "</td>";
				output += "<td>" + paymentDesc + "</td>";
				output += "<td>" + paymentdate + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-itemid='" + paymentID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-itemid='" + paymentID + "'></td></tr>";
			}
			con.close();
			String newPayment = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//UPDATE PAYMENT DETAILS 
	
	public String updatePayment(String paymentID,  String paymentName, String paymenttype, String paymentDesc, String paymentdate) {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE payments SET paymentName=?,paymenttype=?,paymentDesc=?,paymentdate=? WHERE paymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, paymentName);
			preparedStmt.setString(2, paymenttype);
			preparedStmt.setString(3, paymentDesc);
			preparedStmt.setString(4, paymentdate);
			preparedStmt.setInt(5, Integer.parseInt(paymentID));
			// execute the statement
			preparedStmt.execute();
			String newPayment = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//DELETE PAYMENT DETAILS 
	
	
	public String deletePayment(String paymentID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from payments where paymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(paymentID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPayment = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Bill Info.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}

