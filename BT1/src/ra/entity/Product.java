package ra.entity;

import java.util.Scanner;

public class Product {
    private int productId;
    private String productName;
    private float price;
    private String category;
    private int quantity;

    private static int nextId = 1;

    // Constructor không tham số
    public Product() {
        this.productId = nextId++;
    }

    // Constructor đầy đủ tham số
    public Product(int productId, String productName, float price,
                   String category, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;

        if (productId >= nextId) {
            nextId = productId + 1;
        }
    }

    // Getter và Setter
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Nhập dữ liệu sản phẩm
    public void inputData(Scanner scanner) {

        // Nhập tên sản phẩm
        while (true) {
            System.out.print("Nhập tên sản phẩm: ");
            String name = scanner.nextLine().trim();

            if (name.length() < 10 || name.length() > 50) {
                System.out.println("Tên sản phẩm phải từ 10 đến 50 ký tự!");
            } else {
                this.productName = name;
                break;
            }
        }

        // Nhập giá sản phẩm
        while (true) {
            try {
                System.out.print("Nhập giá sản phẩm: ");
                float price = Float.parseFloat(scanner.nextLine());

                if (price <= 0 || Float.isInfinite(price) || Float.isNaN(price)) {
                    System.out.println("Giá sản phẩm phải lớn hơn 0!");
                } else {
                    this.price = price;
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Giá sản phẩm phải là số thực!");
            }
        }

        // Nhập danh mục
        while (true) {
            System.out.print("Nhập danh mục sản phẩm: ");
            String category = scanner.nextLine().trim();

            if (category.length() > 200) {
                System.out.println("Danh mục không được vượt quá 200 ký tự!");
            } else if (category.isEmpty()) {
                System.out.println("Danh mục không được để trống!");
            } else {
                this.category = category;
                break;
            }
        }

        // Nhập số lượng
        while (true) {
            try {
                System.out.print("Nhập số lượng tồn kho: ");
                int quantity = Integer.parseInt(scanner.nextLine());

                if (quantity < 0) {
                    System.out.println("Số lượng phải lớn hơn hoặc bằng 0!");
                } else {
                    this.quantity = quantity;
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Số lượng phải là số nguyên!");
            }
        }
    }

    // Hiển thị thông tin sản phẩm
    @Override
    public String toString() {
        return String.format(
                "| %-5d | %-25s | %-12.2f | %-20s | %-10d |",
                productId,
                productName,
                price,
                category,
                quantity
        );
    }
}