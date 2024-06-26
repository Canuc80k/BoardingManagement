package model.meal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    private void closeResources(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Error closing resource.", e);
                }
            }
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

    public String dayUsed(String day) {
        String sql = "SELECT MenuID FROM meal WHERE day = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, day);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("MenuID");
                    System.out.println("using day :" + day + "is Id:" + id + "\n");
                    return rs.getString("MenuID");
                } else {
                    LOGGER.log(Level.INFO, "No meal found for the specified day.");
                    return null;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking menu ID existence.", e);
            return null;
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
        return null;
    }

    public void addOrUpdateDay(String menuID, String day) throws SQLException {
        if (menuIDExists(menuID)) {
            System.out.println("update menuID:" + menuID + ", to day:" + day + "\n");
            updateDay(menuID, day);
        } else {
            insertDay(menuID, day);
        }
    }

    public void addOrUpdateMeal(String menuID, String imagePath, String day) {
        if (menuIDExists(menuID)) {
            updateMeal(menuID, imagePath, day);
        } else {
            insertMeal(menuID, imagePath, day);
        }
    }

    public void insertDay(String menuID, String day) {
        String sql = "INSERT INTO meal (MenuID,Day) VALUES (?, ?)";
        String sql1 = "UPDATE meal SET  Day = ? WHERE MenuID = ?";
        if (dayUsed(day) == null) {
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, menuID);
                // try (FileInputStream fis = new FileInputStream(imagePath)) {
                // pstmt.setBlob(2, fis);
                pstmt.setString(2, day);
                pstmt.executeUpdate();
                //}
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error inserting meal.", e);
            }
        } else {
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql1)) {
                pstmt.setString(2, dayUsed(day));
                // try (FileInputStream fis = new FileInputStream(imagePath)) {
                // pstmt.setBlob(2, fis);
                pstmt.setString(1, "NULL");
                pstmt.executeUpdate();
                //}
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error inserting meal.", e);
            }
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, menuID);
                // try (FileInputStream fis = new FileInputStream(imagePath)) {
                // pstmt.setBlob(2, fis);
                pstmt.setString(2, day);
                pstmt.executeUpdate();
                //}
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error inserting meal.", e);
            }
        }
    }

    public void updateDay(String menuID, String day) throws SQLException {
        String sql = "UPDATE meal SET  Day = ? WHERE MenuID = ?";
        String sql1 = "UPDATE meal SET  Day = ? WHERE MenuID = ?";
        if (dayUsed(day) == null) {
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(2, menuID);
                // try (FileInputStream fis = new FileInputStream(imagePath)) {
                // pstmt.setBlob(2, fis);
                pstmt.setString(1, day);
                pstmt.executeUpdate();
                //}
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error inserting meal.", e);
            }
        } else {
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql1)) {
                pstmt.setString(2, dayUsed(day));
                // try (FileInputStream fis = new FileInputStream(imagePath)) {
                // pstmt.setBlob(2, fis);
                System.out.println("update setting menuID:" + dayUsed(day) + ", to day:" + "NULL" + "\n");
                pstmt.setString(1, "NULL");
                pstmt.executeUpdate();
                //}
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error inserting meal.", e);
            }
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(2, menuID);
                // try (FileInputStream fis = new FileInputStream(imagePath)) {
                // pstmt.setBlob(2, fis);
                pstmt.setString(1, day);
                pstmt.executeUpdate();
                //}
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error inserting meal.", e);
            }
        }
    }

    public void insertMeal(String menuID, String imagePath, String day) {
        String sql = "INSERT INTO meal (MenuID, MealPhoto, Day) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, menuID);
            try (FileInputStream fis = new FileInputStream(imagePath)) {
                pstmt.setBlob(2, fis);
                pstmt.setString(3, day);
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error inserting meal.", e);
        }
    }

    public void updateMeal(String menuID, String imagePath, String day) {
        String sql = "UPDATE meal SET MealPhoto = ?, Day = ? WHERE MenuID = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(3, menuID);
            try (FileInputStream fis = new FileInputStream(imagePath)) {
                pstmt.setBlob(1, fis);
                pstmt.setString(2, day);
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating meal.", e);
        }
    }

    public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    public BufferedImage resizeCover(String imgPath, byte[] imageData, int width, int height) {
        BufferedImage resizedImage = null;
        try {
            BufferedImage image = null;
            if (imgPath != null) {
                File imgFile = new File(imgPath);
                if (!imgFile.exists() || !imgFile.isFile()) {
                    throw new IOException("File does not exist or is not a file: " + imgPath);
                }
                image = ImageIO.read(imgFile);
            } else if (imageData != null) {
                ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
                image = ImageIO.read(bais);
            }

            if (image == null) {
                throw new IOException("Failed to load image.");
            }

            resizedImage = resizeImage(image, width, height);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error resizing image.", e);
        }
        return resizedImage;
    }

    public void browseImage(JLabel label, int width, int height, String[] imgPath) {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            imgPath[0] = path; // Assign the selected file path to the first element of the array
            BufferedImage resizedImage = resizeCover(path, null, width, height);
            if (resizedImage != null) {
                label.setIcon(new ImageIcon(resizedImage));
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
                        BufferedImage resizedImage = resizeCover(null, imageData, width, height);
                        return new ImageIcon(resizedImage);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error fetching meal photo.", e);
        }
        return null;
    }

    public static List<String> getAllMenuItemsInMealTable(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Use try-with-resources to ensure resources are closed automatically
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", ""); PreparedStatement stmt = con.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            List<String> res = new ArrayList<>();
            while (rs.next()) {
                String temp = rs.getString(1);
                res.add(temp);
            }
            return res;
        }
    }

    public String getDay(String menuID) {
        String sql = "SELECT Day FROM meal WHERE MenuID = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, menuID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Day");
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error fetching day for menu ID.", e);
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
    public void deleteMenu(String menuID) {
        String sql = "DELETE FROM meal WHERE MenuID = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, menuID);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                LOGGER.log(Level.INFO, "Menu with MenuID {0} was deleted successfully.", menuID);
            } else {
                LOGGER.log(Level.WARNING, "No menu with MenuID {0} found.", menuID);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting menu.", e);
        }
    }
}
