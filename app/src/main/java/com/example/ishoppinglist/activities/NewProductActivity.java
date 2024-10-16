package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ishoppinglist.R;
import com.example.ishoppinglist.database.Database;
import com.example.ishoppinglist.models.Product;

/**
 * Actividad para crear un nuevo producto. Permite introducir un nombre, una nota y marcar
 * si el producto está pendiente o no, y luego guarda el producto en la base de datos.
 * @author Cristina
 */
public class NewProductActivity extends AppCompatActivity {

    //Declaración de los elementos de la interfaz
    private EditText etProductName, etProductNote;
    private Switch switchPending;
    private Button btnAddProduct;
    private Button btnBackNewProduct;
    private Switch switchLactosa;
    private Switch switchGluten;

    /**
     * Método que se ejecuta cuando se crea la actividad. Inicializa la interfaz de usuario
     * y establece los eventos para los botones.
     *
     * @param savedInstanceState Estado anterior de la actividad, si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        // Asociamos los elementos de la interfaz con los objetos Java
        etProductName = findViewById(R.id.etNewProductName);
        etProductNote = findViewById(R.id.etNewProductNote);
        switchPending = findViewById(R.id.switch3);
        btnAddProduct = findViewById(R.id.btnAddNewProduct);
        btnBackNewProduct = findViewById(R.id.btnBackNewProduct);
        switchLactosa = findViewById(R.id.switchLactosa);
        switchGluten = findViewById(R.id.switchGluten);

        /**
         * Configura el evento del botón para volver a la actividad principal.
         * Al pulsar el botón, se inicia la MainActivity.
         */
        btnBackNewProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Intención para cambiar de actividad a MainActivity
                Intent intentDetail = new Intent(NewProductActivity.this, MainActivity.class);
                // Método para abrir otra activity
                startActivity(intentDetail);
            }
        });

        /**
         * Configura el evento del botón para añadir un nuevo producto.
         * Al pulsar el botón, valida los datos introducidos, crea un nuevo producto
         * y lo añade a las listas correspondientes.
         */
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener datos de los campos de entrada
                String productName = etProductName.getText().toString().trim();
                String productNote = etProductNote.getText().toString().trim();
                boolean isPending = switchPending.isChecked();
                boolean isLactosa = switchLactosa.isChecked();
                boolean isGluten = switchGluten.isChecked();

                // Validar los datos de entrada
                if (productName.isEmpty()) {
                    etProductName.setError("Product name is required");
                    return;
                }

                // Crear un nuevo producto y añadirlo a la base de datos
                Product newProduct = new Product(productName, productNote, isPending, isLactosa,isGluten);
                newProduct.setId(Database.productList.size()+1);// Asignar un ID basado en el tamaño actual de la lista
                Boolean isAddedToProductList = Database.addProductToProductList(newProduct); // Añadir el producto a la lista de productos

                // Si el producto está marcado como pendiente, añadirlo también a la lista de pendientes
                if(isPending){
                    Boolean isAddedToPendingList = Database.addProductToPendingList(newProduct);
                }

                // Mostrar un mensaje si no se pudo añadir a la lista de productos
                if(!isAddedToProductList){
                    Toast.makeText(NewProductActivity.this, "Product has not been added to productList", Toast.LENGTH_SHORT).show();
                }

                // Volver a la actividad principal (MainActivity) después de añadir el producto
                Intent intent = new Intent(NewProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
