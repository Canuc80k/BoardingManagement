package model.meal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MealDatabase {

    private static final Logger LOGGER = Logger.getLogger(MealDatabase.class.getName());

    private Connection connect() {
        String url = "jdbc:mysql://localhost:3306/boardingmanagement";
        String user = "root";
        String password = "";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to connect to the database.", e);
            return null;
        }
    }

    public boolean menuIDExists(String menuID) {
        String sql = "SELECT MenuID FROM meal WHERE MenuID = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, menuID);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking menu ID existence.", e);
            return false;
        }
    }

    public String generateNewMenuID() {
        String sql = "SELECT MAX(CAST(SUBSTRING(MenuID, 5) AS UNSIGNED)) AS maxID FROM meal";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int maxID = rs.getInt("maxID");
                return "Menu" + (maxID + 1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error generating new MenuID.", e);
        }
        return null; // Return null if unable to generate ID
    }

    public void addOrUpdateMeal(String menuID, String imagePath) {
        if (menuIDExists(menuID)) {
            updateMeal(menuID, imagePath);
        } else {
            insertMeal(menuID, imagePath);
        }
    }

    public void insertMeal(String menuID, String imagePath) {
        String sql = "INSERT INTO meal (MenuID, MealPhoto) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, menuID);
            try (FileInputStream fis = new FileInputStream(imagePath)) {
                pstmt.setBlob(2, fis);
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error inserting meal.", e);
        }
    }

    public void updateMeal(String menuID, String imagePath) {
        String sql = "UPDATE meal SET MealPhoto = ? WHERE MenuID = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, menuID);
            try (FileInputStream fis = new FileInputStream(imagePath)) {
                pstmt.setBlob(1, fis);
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating meal.", e);
        }
    }

    public ImageIcon resizeCover(String imagePath, byte[] pic, int width, int height) {
        try {
            Image image;
            if (imagePath != null) {
                image = ImageIO.read(new File(imagePath));
            } else {
                image = ImageIO.read(new ByteArrayInputStream(pic));
            }
            Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(newImg);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error resizing image.", e);
            return null;
        }
    }

    public void browseImage(JLabel label, int width, int height, String[] imgPath) {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(file);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            imgPath[0] = path; // Assign the selected file path to the first element of the array
            System.out.println("imgpath: " + imgPath[0] + "\n");
            ImageIcon resizedImage = resizeCover(path, null, width, height);
            if (resizedImage != null) {
                label.setIcon(resizedImage);
            } else {
                LOGGER.log(Level.WARNING, "Failed to resize image: " + path);
            }
        } else if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "No file selected");
        }
    }

    public ImageIcon getPhoto(String menuID, int width, int height) {
        String sql = "SELECT MealPhoto FROM meal WHERE MenuID = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, menuID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    byte[] imageData = rs.getBytes("MealPhoto");
                    if (imageData != null) {
                        return resizeCover(null, imageData, width, height);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error fetching meal photo.", e);
        }
        return null;
    }

    public void displayPhoto(String menuID, JLabel label, int width, int height) {
        ImageIcon imageIcon = getPhoto(menuID, width, height);
        if (imageIcon != null) {
            label.setIcon(imageIcon);
        } else {
            label.setIcon(null); // Clear the label if no image found
        }
    }
}
