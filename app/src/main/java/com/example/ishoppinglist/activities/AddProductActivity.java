package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.database.Database;
import com.example.ishoppinglist.models.Product;

import java.util.ArrayList;

/**
 * @author Cristina
 */
public class AddProductActivity extends AppCompatActivity {

    // Campos de la interfaz de usuario
    Spinner spinner;
    Button btnAddListPending; // Añadido para el botón
    Button btnBackAddProduct; // Añadido para el botón

    /**
     * Método llamado cuando la actividad es creada por primera vez.
     * Inicializa la interfaz de usuario, llena el Spinner con productos y configura los listeners para los botones.
     *
     * @param savedInstanceState Estado anterior de la actividad, si está disponible.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addProduct), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializamos los Spinners
        spinner = findViewById(R.id.Spinner);

        // Inicializamos el botón
        btnAddListPending = findViewById(R.id.btnAddListPending);
        btnBackAddProduct = findViewById(R.id.btnBackAddProduct);

        // Llenamos el Spinner con los nombres de los productos
        fillSpinner(spinner, Database.productList, "Products");

        // Establecer el listener del botón
        btnAddListPending.setOnClickListener(v -> addProductToPending());
        // Establecer el listener del botón
        btnBackAddProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentDetail = new Intent(AddProductActivity.this, MainActivity.class);
                // Método para abrir otra activity
                startActivity(intentDetail);
            }
        });

    }

    /**
     * Llena el Spinner con los nombres de los productos de la lista proporcionada.
     *
     * @param spinner      El Spinner que será llenado.
     * @param productList  La lista de productos disponibles.
     * @param categoryName El nombre de la categoría que aparecerá como primer elemento del Spinner.
     */
    private void fillSpinner(Spinner spinner, ArrayList<Product> productList, String categoryName) {
        ArrayList<String> productNames = new ArrayList<>();
        productNames.add(categoryName); // Añade el nombre de la categoría a la lista
        for (Product product : productList) {
            productNames.add(product.getName()); // Añade el nombre del producto a la lista
        }
        // Crear y asignar el adaptador al Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /**
     * Obtiene el producto seleccionado del Spinner y lo agrega a la lista de productos pendientes.
     */
    private void addProductToPending() {
        // Obtenemos el producto seleccionado del Spinner
        String product = (String) spinner.getSelectedItem();
        addToPending(product, Database.productList);
    }

    /**
     * Añade el producto seleccionado a la lista de productos pendientes.
     * Actualiza el estado del producto a pendiente y lo guarda en la base de datos.
     *
     * @param productName El nombre del producto seleccionado.
     * @param productList La lista de productos de la base de datos.
     */
    private void addToPending(String productName, ArrayList<Product> productList) {
        for (Product product : productList) {
            if (product.getName().equals(productName)) {
                product.setPending(true);
                if (Database.addProductToPendingList(product)){
                    // Agrega el producto a la lista de pendientes
                    Toast.makeText(this, productName + " add to pending list", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, productName + " already in pending list", Toast.LENGTH_SHORT).show();
                }
                return; // Salimos del método después de agregar el producto
            }
        }
    }

}