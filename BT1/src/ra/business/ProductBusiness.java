package ra.business;

import ra.entity.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ProductBusiness {

    // Danh sách sản phẩm
    private ArrayList<Product> products = new ArrayList<>();

    // Kiểm tra tên sản phẩm đã tồn tại chưa
    private boolean isProductNameExist(String name, int excludeId) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(name)
                    && product.getProductId() != excludeId) {
                return true;
            }
        }
        return false;
    }

    // Tìm sản phẩm theo ID
    private Product findProductById(int id) {
        for (Product product : products) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }

    // 1. Thêm sản phẩm
    public void addProduct(Scanner scanner) {

        Product product = new Product();

        product.inputData(scanner);

        // Kiểm tra tên sản phẩm trùng lặp
        while (isProductNameExist(product.getProductName(), -1)) {
            System.out.println("Tên sản phẩm đã tồn tại!");
            System.out.print("Nhập lại tên sản phẩm: ");

            String name = scanner.nextLine().trim();

            if (name.length() < 10 || name.length() > 50) {
                System.out.println("Tên phải từ 10 đến 50 ký tự!");
                continue;
            }

            product.setProductName(name);
        }

        products.add(product);

        System.out.println("Thêm sản phẩm thành công!");
        System.out.println("Mã sản phẩm: " + product.getProductId());
    }

    // 2. Hiển thị danh sách sản phẩm
    public void displayProducts() {

        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống!");
            return;
        }

        System.out.println("========================================== DANH SÁCH SẢN PHẨM ==========================================");

        System.out.printf(
                "| %-5s | %-25s | %-12s | %-20s | %-10s |%n",
                "ID", "Tên sản phẩm", "Giá", "Danh mục", "Số lượng"
        );

        System.out.println("---------------------------------------------------------------------------------------------------------");

        for (Product product : products) {
            System.out.println(product);
        }

        System.out.println("=========================================================================================================");
    }

    // 3. Cập nhật sản phẩm theo ID
    public void updateProduct(Scanner scanner) {

        System.out.print("Nhập mã sản phẩm cần cập nhật: ");

        int id;

        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Mã sản phẩm không hợp lệ!");
            return;
        }

        Product product = findProductById(id);

        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm có mã " + id);
            return;
        }

        System.out.println("Thông tin sản phẩm hiện tại:");
        System.out.println(product);

        System.out.println("Nhập thông tin mới:");

        // Cập nhật tên
        while (true) {
            System.out.print("Tên sản phẩm mới: ");
            String name = scanner.nextLine().trim();

            if (name.length() < 10 || name.length() > 50) {
                System.out.println("Tên phải từ 10 đến 50 ký tự!");
            } else if (isProductNameExist(name, id)) {
                System.out.println("Tên sản phẩm đã tồn tại!");
            } else {
                product.setProductName(name);
                break;
            }
        }

        // Cập nhật giá
        while (true) {
            try {
                System.out.print("Giá sản phẩm mới: ");
                float price = Float.parseFloat(scanner.nextLine());

                if (price <= 0 || Float.isInfinite(price) || Float.isNaN(price)) {
                    System.out.println("Giá phải lớn hơn 0!");
                } else {
                    product.setPrice(price);
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Giá phải là số thực!");
            }
        }

        // Cập nhật danh mục
        while (true) {
            System.out.print("Danh mục mới: ");
            String category = scanner.nextLine().trim();

            if (category.isEmpty()) {
                System.out.println("Danh mục không được để trống!");
            } else if (category.length() > 200) {
                System.out.println("Danh mục tối đa 200 ký tự!");
            } else {
                product.setCategory(category);
                break;
            }
        }

        // Cập nhật số lượng
        while (true) {
            try {
                System.out.print("Số lượng mới: ");
                int quantity = Integer.parseInt(scanner.nextLine());

                if (quantity < 0) {
                    System.out.println("Số lượng phải >= 0!");
                } else {
                    product.setQuantity(quantity);
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Số lượng phải là số nguyên!");
            }
        }

        System.out.println("Cập nhật sản phẩm thành công!");
    }

    // 4. Xóa sản phẩm theo ID
    public void deleteProduct(Scanner scanner) {

        System.out.print("Nhập mã sản phẩm cần xóa: ");

        int id;

        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Mã sản phẩm không hợp lệ!");
            return;
        }

        Product product = findProductById(id);

        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm!");
            return;
        }

        System.out.println("Sản phẩm cần xóa:");
        System.out.println(product);

        System.out.print("Bạn có chắc chắn muốn xóa? (Y/N): ");

        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            products.remove(product);
            System.out.println("Xóa sản phẩm thành công!");
        } else {
            System.out.println("Đã hủy thao tác xóa!");
        }
    }

    // 5. Tìm kiếm sản phẩm theo tên
    public void searchProduct(Scanner scanner) {

        System.out.print("Nhập từ khóa tìm kiếm: ");
        String keyword = scanner.nextLine().trim();

        boolean found = false;

        for (Product product : products) {

            if (product.getProductName()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                if (!found) {
                    System.out.println("Kết quả tìm kiếm:");
                }

                System.out.println(product);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sản phẩm phù hợp!");
        }
    }

    // 6. Sắp xếp sản phẩm theo giá tăng dần
    public void sortByPrice() {

        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống!");
            return;
        }

        products.sort(Comparator.comparing(Product::getPrice));

        System.out.println("Đã sắp xếp theo giá tăng dần!");
        displayProducts();
    }

    // 7. Sắp xếp sản phẩm theo số lượng giảm dần
    public void sortByQuantity() {

        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống!");
            return;
        }

        products.sort(
                Comparator.comparingInt(Product::getQuantity).reversed()
        );

        System.out.println("Đã sắp xếp theo số lượng giảm dần!");
        displayProducts();
    }
}