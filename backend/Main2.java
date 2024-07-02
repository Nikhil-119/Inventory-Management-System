package backend;

import java.sql.Connection;
//Importing the DriverManager class for database connection setup.
import java.sql.DriverManager;
//Importing the PreparedStatement class for executing parameterized SQL queries.
import java.sql.PreparedStatement;
//Importing the ResultSet class for handling query results.
import java.sql.ResultSet;
//Importing SQLException for handling database-related errors.
import java.sql.SQLException;
//Importing the Statement class for executing SQL queries.
import java.sql.Statement;
//Importing the Scanner class for user input.
import java.util.Scanner;

public class Main2 {
	
	 private static final String JDBC_URL = "jdbc:mysql://localhost:3306/iMS";
	    private static final String JDBC_USER = "root";
	    private static final String JDBC_PASSWORD = "root";
	    
	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	    }

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		        int choice;
		        do {
		            System.out.println("\nInventory Management System");
		            System.out.println("1. Supplier Operations");
		            System.out.println("2. Item Operations");
		            System.out.println("3. Shipment Operations");
		            System.out.println("4. ShipmentItem Operations");
		            System.out.println("5. Exit");
		            System.out.print("Enter your choice: ");
		            choice = scanner.nextInt();

		            switch (choice) {
		                case 1:
		                    supplierOperations(scanner);
		                    break;
		                case 2:
		                    itemOperations(scanner);
		                    break;
		                case 3:
		                    shipmentOperations(scanner);
		                    break;
		                case 4:
		                    shipmentItemOperations(scanner);
		                    break;
		                case 5:
		                    System.out.println("\nExiting...");
		                    break;
		                default:
		                    System.out.println("\nInvalid choice. Please try again.");
		            }
		        } while (choice != 5);

		        scanner.close();
		    }

		    private static void supplierOperations(Scanner scanner) {
		        int choice;
		        do {
		            System.out.println("\nSupplier Operations");
		            System.out.println("1. Add Supplier");
		            System.out.println("2. View Supplier");
		            System.out.println("3. Update Supplier");
		            System.out.println("4. Delete Supplier");
		            System.out.println("5. Back to Main Menu");
		            System.out.print("Enter your choice: ");
		            choice = scanner.nextInt();

		            switch (choice) {
		                case 1:
		                    addSupplier(scanner);
		                    break;
		                case 2:
		                    viewSupplier(scanner);
		                    break;
		                case 3:
		                    updateSupplier(scanner);
		                    break;
		                case 4:
		                    deleteSupplier(scanner);
		                    break;
		                case 5:
		                    System.out.println("\nReturning to Main Menu...");
		                    break;
		                default:
		                    System.out.println("\nInvalid choice. Please try again.");
		            }
		        } while (choice != 5);
		    }

		    private static void addSupplier(Scanner scanner) {
		    	System.out.print("\nEnter supplier ID: ");
		        int supplierId = scanner.nextInt();

		    	
		    	System.out.print("Enter supplier name: ");
		        String name = scanner.next();
		        System.out.print("Enter contact info: ");
		        String contactInfo = scanner.next();

		        String sql = "INSERT INTO Supplier (supplier_id, name, contact_info) VALUES (?, ?, ?)";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        	pstmt.setInt(1, supplierId);
		        	pstmt.setString(2, name);
		            pstmt.setString(3, contactInfo);
		            pstmt.executeUpdate();
		            System.out.println("\nSupplier added successfully.");

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void viewSupplier(Scanner scanner) {
		        System.out.print("\nEnter supplier ID: ");
		        int supplierId = scanner.nextInt();

		        String sql = "SELECT * FROM Supplier WHERE supplier_id = ?";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            pstmt.setInt(1, supplierId);

		            try (ResultSet rs = pstmt.executeQuery()) {
		                if (rs.next()) {
		                    System.out.println("\nSupplier ID: " + rs.getInt("supplier_id"));
		                    System.out.println("Name: " + rs.getString("name"));
		                    System.out.println("Contact Info: " + rs.getString("contact_info"));
		                } else {
		                    System.out.println("\nSupplier not found.");
		                }
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void updateSupplier(Scanner scanner) {
		        System.out.print("\nEnter supplier ID: ");
		        int supplierId = scanner.nextInt();
		        System.out.print("Enter new name: ");
		        String name = scanner.next();
		        System.out.print("Enter new contact info: ");
		        String contactInfo = scanner.next();

		        String sql = "UPDATE Supplier SET name = ?, contact_info = ? WHERE supplier_id = ?";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            pstmt.setString(1, name);
		            pstmt.setString(2, contactInfo);
		            pstmt.setInt(3, supplierId);
		            int rowsAffected = pstmt.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("\nSupplier updated successfully.");
		            } else {
		                System.out.println("\nSupplier not found.");
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void deleteSupplier(Scanner scanner) {
		        System.out.print("\nEnter supplier ID: ");
		        int supplierId = scanner.nextInt();

		        String sql = "DELETE FROM Supplier WHERE supplier_id = ?";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            pstmt.setInt(1, supplierId);
		            int rowsAffected = pstmt.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("Supplier deleted successfully.");
		            } else {
		                System.out.println("Supplier not found.");
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void itemOperations(Scanner scanner) {
		        int choice;
		        do {
		            System.out.println("\nItem Operations");
		            System.out.println("1. Add Item");
		            System.out.println("2. View Item");
		            System.out.println("3. Update Item");
		            System.out.println("4. Delete Item");
		            System.out.println("5. Back to Main Menu");
		            System.out.print("Enter your choice: ");
		            choice = scanner.nextInt();

		            switch (choice) {
		                case 1:
		                    addItem(scanner);
		                    break;
		                case 2:
		                    viewItem(scanner);
		                    break;
		                case 3:
		                    updateItem(scanner);
		                    break;
		                case 4:
		                    deleteItem(scanner);
		                    break;
		                case 5:
		                    System.out.println("\nReturning to Main Menu...");
		                    break;
		                default:
		                    System.out.println("\nInvalid choice. Please try again.");
		            }
		        } while (choice != 5);
		    }

		    private static void addItem(Scanner scanner) {
		    	System.out.print("\nEnter item id: ");
		        int item_id = scanner.nextInt();
		    	
		    	System.out.print("Enter item name: ");
		        String name = scanner.next();
		        System.out.print("Enter description: ");
		        String description = scanner.next();
		        System.out.print("Enter quantity: ");
		        int quantity = scanner.nextInt();
		        System.out.print("Enter price: ");
		        double price = scanner.nextDouble();

		        String sql = "INSERT INTO Item (item_id, name, description, quantity, price) VALUES (?, ?, ?, ?, ?)";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        	pstmt.setInt(1, item_id);
		        	pstmt.setString(2, name);
		            pstmt.setString(3, description);
		            pstmt.setInt(4, quantity);
		            pstmt.setDouble(5, price);
		            pstmt.executeUpdate();
		            System.out.println("\nItem added successfully.");

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void viewItem(Scanner scanner) {
		        System.out.print("\nEnter item ID: ");
		        int itemId = scanner.nextInt();

		        String sql = "SELECT * FROM Item WHERE item_id = ?";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            pstmt.setInt(1, itemId);

		            try (ResultSet rs = pstmt.executeQuery()) {
		                if (rs.next()) {
		                    System.out.println("\nItem ID: " + rs.getInt("item_id"));
		                    System.out.println("Name: " + rs.getString("name"));
		                    System.out.println("Description: " + rs.getString("description"));
		                    System.out.println("Quantity: " + rs.getInt("quantity"));
		                    System.out.println("Price: " + rs.getDouble("price"));
		                } else {
		                    System.out.println("\nItem not found.");
		                }
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void updateItem(Scanner scanner) {
		        System.out.print("\nEnter item ID: ");
		        int itemId = scanner.nextInt();
		        System.out.print("Enter new name: ");
		        String name = scanner.next();
		        System.out.print("Enter new description: ");
		        String description = scanner.next();
		        System.out.print("Enter new quantity: ");
		        int quantity = scanner.nextInt();
		        System.out.print("Enter new price: ");
		        double price = scanner.nextDouble();

		        String sql = "UPDATE Item SET name = ?, description = ?, quantity = ?, price = ? WHERE item_id = ?";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            pstmt.setString(1, name);
		            pstmt.setString(2, description);
		            pstmt.setInt(3, quantity);
		            pstmt.setDouble(4, price);
		            pstmt.setInt(5, itemId);
		            int rowsAffected = pstmt.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("\nItem updated successfully.");
		            } else {
		                System.out.println("\nItem not found.");
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void deleteItem(Scanner scanner) {
		        System.out.print("\nEnter item ID: ");
		        int itemId = scanner.nextInt();

		        String sql = "DELETE FROM Item WHERE item_id = ?";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            pstmt.setInt(1, itemId);
		            int rowsAffected = pstmt.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("\nItem deleted successfully.");
		            } else {
		                System.out.println("\nItem not found.");
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void shipmentOperations(Scanner scanner) {
		        int choice;
		        do {
		            System.out.println("\nShipment Operations");
		            System.out.println("1. Add Shipment");
		            System.out.println("2. View Shipment");
		            System.out.println("3. Update Shipment");
		            System.out.println("4. Delete Shipment");
		            System.out.println("5. Back to Main Menu");
		            System.out.print("Enter your choice: ");
		            choice = scanner.nextInt();

		            switch (choice) {
		                case 1:
		                    addShipment(scanner);
		                    break;
		                case 2:
		                    viewShipment(scanner);
		                    break;
		                case 3:
		                    updateShipment(scanner);
		                    break;
		                case 4:
		                    deleteShipment(scanner);
		                    break;
		                case 5:
		                    System.out.println("\nReturning to Main Menu...");
		                    break;
		                default:
		                    System.out.println("\nInvalid choice. Please try again.");
		            }
		        } while (choice != 5);
		    }

		    private static void addShipment(Scanner scanner) {
		    	System.out.print("\nEnter shipment ID: ");
		        int shipmentId = scanner.nextInt();
		    	
		    	System.out.print("Enter supplier ID: ");
		        int supplierId = scanner.nextInt();
		        System.out.print("Enter date (YYYY-MM-DD): ");
		        String date = scanner.next();
		        System.out.print("Enter total cost: ");
		        double totalCost = scanner.nextDouble();

		        String sql = "INSERT INTO Shipment (shipment_id, supplier_id, date, total_cost) VALUES (?, ?, ?, ?)";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        	pstmt.setInt(1, shipmentId);
		        	pstmt.setInt(2, supplierId);
		            pstmt.setString(3, date);
		            pstmt.setDouble(4, totalCost);
		            pstmt.executeUpdate();
		            System.out.println("Shipment added successfully.");

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void viewShipment(Scanner scanner) {
		        System.out.print("\nEnter shipment ID: ");
		        int shipmentId = scanner.nextInt();

		        String sql = "SELECT * FROM Shipment WHERE shipment_id = ?";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            pstmt.setInt(1, shipmentId);

		            try (ResultSet rs = pstmt.executeQuery()) {
		                if (rs.next()) {
		                    System.out.println("\nShipment ID: " + rs.getInt("shipment_id"));
		                    System.out.println("Supplier ID: " + rs.getInt("supplier_id"));
		                    System.out.println("Date: " + rs.getString("date"));
		                    System.out.println("Total Cost: " + rs.getDouble("total_cost"));
		                } else {
		                    System.out.println("Shipment not found.");
		                }
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void updateShipment(Scanner scanner) {
		        System.out.print("\nEnter shipment ID: ");
		        int shipmentId = scanner.nextInt();
		        System.out.print("Enter new supplier ID: ");
		        int supplierId = scanner.nextInt();
		        System.out.print("Enter new date (YYYY-MM-DD): ");
		        String date = scanner.next();
		        System.out.print("Enter new total cost: ");
		        double totalCost = scanner.nextDouble();

		        String sql = "UPDATE Shipment SET supplier_id = ?, date = ?, total_cost = ? WHERE shipment_id = ?";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            pstmt.setInt(1, supplierId);
		            pstmt.setString(2, date);
		            pstmt.setDouble(3, totalCost);
		            pstmt.setInt(4, shipmentId);
		            int rowsAffected = pstmt.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("\nShipment updated successfully.");
		            } else {
		                System.out.println("\nShipment not found.");
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void deleteShipment(Scanner scanner) {
		        System.out.print("\nEnter shipment ID: ");
		        int shipmentId = scanner.nextInt();

		        String sql = "DELETE FROM Shipment WHERE shipment_id = ?";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            pstmt.setInt(1, shipmentId);
		            int rowsAffected = pstmt.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("\nShipment deleted successfully.");
		            } else {
		                System.out.println("\nShipment not found.");
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void shipmentItemOperations(Scanner scanner) {
		        int choice;
		        do {
		            System.out.println("\nShipmentItem Operations");
		            System.out.println("1. Add ShipmentItem");
		            System.out.println("2. View ShipmentItem");
		            System.out.println("3. Update ShipmentItem");
		            System.out.println("4. Delete ShipmentItem");
		            System.out.println("5. Back to Main Menu");
		            System.out.print("Enter your choice: ");
		            choice = scanner.nextInt();

		            switch (choice) {
		                case 1:
		                    addShipmentItem(scanner);
		                    break;
		                case 2:
		                    viewShipmentItem(scanner);
		                    break;
		                case 3:
		                    updateShipmentItem(scanner);
		                    break;
		                case 4:
		                    deleteShipmentItem(scanner);
		                    break;
		                case 5:
		                    System.out.println("\nReturning to Main Menu...");
		                    break;
		                default:
		                    System.out.println("\nInvalid choice. Please try again.");
		            }
		        } while (choice != 5);
		    }

		    private static void addShipmentItem(Scanner scanner) {
		    	System.out.print("\nEnter shipment item ID: ");
		        int shipmentItemId = scanner.nextInt();
		    	
		    	System.out.print("Enter shipment ID: ");
		        int shipmentId = scanner.nextInt();
		        System.out.print("Enter item ID: ");
		        int itemId = scanner.nextInt();
		        System.out.print("Enter quantity: ");
		        int quantity = scanner.nextInt();
		        System.out.print("Enter price: ");
		        double price = scanner.nextDouble();

		        String sql = "INSERT INTO ShipmentItem (shipment_item_id, shipment_id, item_id, quantity, price) VALUES (?, ?, ?, ?, ?)";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        	pstmt.setInt(1, shipmentItemId);
		        	pstmt.setInt(2, shipmentId);
		            pstmt.setInt(3, itemId);
		            pstmt.setInt(4, quantity);
		            pstmt.setDouble(5, price);
		            pstmt.executeUpdate();
		            System.out.println("\nShipmentItem added successfully.");

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void viewShipmentItem(Scanner scanner) {
		        System.out.print("\nEnter shipment item ID: ");
		        int shipmentItemId = scanner.nextInt();

		        String sql = "SELECT * FROM ShipmentItem WHERE shipment_item_id = ?";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            pstmt.setInt(1, shipmentItemId);

		            try (ResultSet rs = pstmt.executeQuery()) {
		                if (rs.next()) {
		                    System.out.println("\nShipment Item ID: " + rs.getInt("shipment_item_id"));
		                    System.out.println("Shipment ID: " + rs.getInt("shipment_id"));
		                    System.out.println("Item ID: " + rs.getInt("item_id"));
		                    System.out.println("Quantity: " + rs.getInt("quantity"));
		                    System.out.println("Price: " + rs.getDouble("price"));
		                } else {
		                    System.out.println("Shipment Item not found.");
		                }
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void updateShipmentItem(Scanner scanner) {
		        System.out.print("\nEnter shipment item ID: ");
		        int shipmentItemId = scanner.nextInt();
		        System.out.print("Enter new shipment ID: ");
		        int shipmentId = scanner.nextInt();
		        System.out.print("Enter new item ID: ");
		        int itemId = scanner.nextInt();
		        System.out.print("Enter new quantity: ");
		        int quantity = scanner.nextInt();
		        System.out.print("Enter new price: ");
		        double price = scanner.nextDouble();

		        String sql = "UPDATE ShipmentItem SET shipment_id = ?, item_id = ?, quantity = ?, price = ? WHERE shipment_item_id = ?";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            pstmt.setInt(1, shipmentId);
		            pstmt.setInt(2, itemId);
		            pstmt.setInt(3, quantity);
		            pstmt.setDouble(4, price);
		            pstmt.setInt(5, shipmentItemId);
		            int rowsAffected = pstmt.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("\nShipmentItem updated successfully.");
		            } else {
		                System.out.println("\nShipmentItem not found.");
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    private static void deleteShipmentItem(Scanner scanner) {
		        System.out.print("\nEnter shipment item ID: ");
		        int shipmentItemId = scanner.nextInt();

		        String sql = "DELETE FROM ShipmentItem WHERE shipment_item_id = ?";

		        try (Connection conn = getConnection();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            pstmt.setInt(1, shipmentItemId);
		            int rowsAffected = pstmt.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("\nShipmentItem deleted successfully.");
		            } else {
		                System.out.println("\nShipmentItem not found.");
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		


	}


