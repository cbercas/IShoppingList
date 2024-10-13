package com.example.ishoppinglist.database;

import com.example.ishoppinglist.models.Product;
import java.util.ArrayList;

/**
 * Clase que simula una base de datos para gestionar los productos de una lista de compras
 * Esta clase contiene métodos estáticos para agregar, actualizar y eliminar productos de las listas
 * @author Cristina
 */
public class Database {

    // Lista que contiene los productos que están pendientes de ser comprados
    public static ArrayList<Product> productListPending = new ArrayList<>();
    // Lista que contiene todos los productos, independientemente de si están pendientes o no
    public static ArrayList<Product> productList = new ArrayList<>();

    /**
     * Método que carga datos predefinidos en las listas de productos.
     * Simula un conjunto de datos inicial para trabajar con ellos en la aplicación.
     */
    public static void preloadData() {
        productListPending.add(new Product(1, "Apples", "5 red apples", true));
        productListPending.add(new Product(2, "Bananas", "6 bananas", true));
        productListPending.add(new Product(3, "Potatoes", "1.5 kg of potatoes", true));
        productList.add(new Product(1, "Apples", "5 red apples", true));
        productList.add(new Product(2, "Bananas", "6 bananas", true));
        productList.add(new Product(3, "Potatoes", "1.5 kg of potatoes", true));
        productList.add(new Product(4, "Carrots", "1 kg of carrots", true));
        productList.add(new Product(5, "Onions", "500g of onions", false));
        productList.add(new Product(6, "Tomatoes", "6 ripe tomatoes", true));
        productList.add(new Product(7, "Lettuce", "1 head of lettuce", true));
        productList.add(new Product(8, "Bell Peppers", "3 bell peppers", true));
        productList.add(new Product(9, "Cucumbers", "2 cucumbers", true));
        productList.add(new Product(10, "Spinach", "1 bag of fresh spinach", true));
        productList.add(new Product(11, "Bread", "Whole wheat bread", false));
        productList.add(new Product(12, "Brown Bread", "Rye bread, 500g", false));
        productList.add(new Product(13, "Croissants", "4 butter croissants", false));
        productList.add(new Product(14, "Bagels", "Pack of 6 bagels", false));
        productList.add(new Product(15, "Muffins", "Blueberry muffins, 6-pack", false));
        productList.add(new Product(16, "Milk", "1 liter of milk", true));
        productList.add(new Product(17, "Cheese", "Cheddar cheese, 200g", true));
        productList.add(new Product(18, "Yogurt", "Greek yogurt, 4x125g", true));
        productList.add(new Product(19, "Butter", "Salted butter, 250g", false));
        productList.add(new Product(20, "Cream Cheese", "200g of cream cheese", true));
        productList.add(new Product(21, "Sour Cream", "250g of sour cream", false));
        productList.add(new Product(22, "Milk Powder", "400g of milk powder", false));
        productList.add(new Product(23, "Chicken", "1 kg of chicken breasts", false));
        productList.add(new Product(24, "Ground Beef", "500g of ground beef", false));
        productList.add(new Product(25, "Pork Chops", "500g of pork chops", false));
        productList.add(new Product(26, "Salmon", "400g of salmon fillets", true));
        productList.add(new Product(27, "Shrimp", "300g of shrimp", true));
        productList.add(new Product(28, "Tuna", "2 cans of tuna", true));
        productList.add(new Product(29, "Toilet Paper", "12 rolls of toilet paper", true));
        productList.add(new Product(30, "Shampoo", "Anti-dandruff shampoo, 500ml", false));
        productList.add(new Product(31, "Toothpaste", "Mint-flavored toothpaste, 150g", true));
        productList.add(new Product(32, "Soap", "Bar of soap, 3-pack", true));
        productList.add(new Product(33, "Dishwashing Liquid", "500ml of dishwashing liquid", false));
        productList.add(new Product(34, "Laundry Detergent", "1 liter of laundry detergent", false));
        productList.add(new Product(35, "All-Purpose Cleaner", "500ml of all-purpose cleaner", false));
        productList.add(new Product(36, "Juice", "Orange juice, 1L", false));
        productList.add(new Product(37, "Coffee", "Ground coffee, 500g", true));
        productList.add(new Product(38, "Tea", "Green tea, 20 bags", true));
        productList.add(new Product(39, "Soda", "Cola soda, 1.5L", false));
        productList.add(new Product(40, "Mineral Water", "1.5L of mineral water", true));
        productList.add(new Product(41, "Beer", "Pack of 6 beers", false));
        productList.add(new Product(42, "Wine", "Bottle of red wine", false));
        productList.add(new Product(43, "Eggs", "12 eggs", true));
        productList.add(new Product(44, "Rice", "2 kg of white rice", true));
        productList.add(new Product(45, "Tomato Sauce", "Tomato sauce, 400g can", false));
        productList.add(new Product(46, "Olive Oil", "Extra virgin olive oil, 500ml", true));
        productList.add(new Product(47, "Salt", "Table salt, 1 kg", false));
        productList.add(new Product(48, "Pepper", "Black pepper, 100g", true));
        productList.add(new Product(49, "Pasta", "Spaghetti, 500g", true));
        productList.add(new Product(50, "Cereal", "Oat cereal, 750g", false));
    }

    /**
     * Metodo que actualiza un producto en la lista de productos pendientes.
     * Si el producto existe en la lista, se reemplaza por el producto actualizado.
     *
     * @param product Producto que debe ser actualizado en la lista de pendientes
     */
    public static void updateProduct(Product product) {
        for (int i = 0; i < productListPending.size(); i++) {
            if (productListPending.get(i).getId() == product.getId()) {
                productListPending.set(i, product);
                break;
            }
        }
    }

    /**
     * Metodo que agrega un producto a la lista de pendientes si no existe ya en dicha lista.
     *
     * @param product El producto que se desea agregar a la lista de pendientes
     * @return true si el producto fue agregado, false si ya existía en la lista
     */
    public static boolean addProductToPendingList(Product product){

        boolean isInProductPending = false;

        for (Product existingProduct : productListPending) {
            if (existingProduct.getName().equals(product.getName())) {
                // Si el producto ya existe, no lo añadimos
                isInProductPending = true;
            }
        }

        if (!isInProductPending) {
            productListPending.add(product); // Añadir producto a la lista
        }

        return !isInProductPending;
    }

    /**
     * Metodo que agrega un producto a la lista general de productos si no existe ya en dicha lista.
     *
     * @param product El producto que se desea agregar a la lista
     * @return true si el producto fue agregado, false si ya existía en la lista
     */
    public static boolean addProductToProductList(Product product){
        boolean isInProductList = false;

        for (Product existingProduct : productList) {
            if (existingProduct.getName().equals(product.getName())) {
                // Si el producto ya existe, no lo añadimos
                isInProductList = true;
            }
        }
        if(!isInProductList){
            productList.add(product);
        }

        return !isInProductList;
    }

    /**
     * Metodo que elimina un producto de la lista de productos pendientes.
     *
     * @param product El producto que se desea eliminar de la lista de pendientes
     */
    public static void deleteProductPendingList(Product product) {
        Product productToDelete = null;
        int pos = -1;
        for(int i=0; i<productListPending.size(); i++){
            if(productListPending.get(i).getId() == product.getId()){
                pos=i;
                break;
            }
        }
        if( pos >-1){
            productListPending.remove(pos);
        }
    }

}


