package ra;

import ra.business.ProductBusiness;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ProductBusiness business = new ProductBusiness();

        int choice;

        do {

            System.out.println("\n");
            System.out.println("********************* QUẢN LÝ SẢN PHẨM ********************");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Danh sách sản phẩm");
            System.out.println("3. Cập nhật sản phẩm theo mã sản phẩm");
            System.out.println("4. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo tên");
            System.out.println("6. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("7. Sắp xếp sản phẩm theo số lượng giảm dần");
            System.out.println("8. Thoát");
            System.out.println("***********************************************************");

            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số từ 1 đến 8!");
                choice = 0;
            }

            switch (choice) {

                case 1:
                    business.addProduct(scanner);
                    break;

                case 2:
                    business.displayProducts();
                    break;

                case 3:
                    business.updateProduct(scanner);
                    break;

                case 4:
                    business.deleteProduct(scanner);
                    break;

                case 5:
                    business.searchProduct(scanner);
                    break;

                case 6:
                    business.sortByPrice();
                    break;

                case 7:
                    business.sortByQuantity();
                    break;

                case 8:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
            }

        } while (choice != 8);

        scanner.close();
    }
}