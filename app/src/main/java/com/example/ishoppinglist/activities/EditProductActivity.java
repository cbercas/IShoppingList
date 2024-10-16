package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ishoppinglist.R;
import com.example.ishoppinglist.database.Database;
import com.example.ishoppinglist.models.Product;

/**
 * @author Cristina
 */
public class EditProductActivity extends AppCompatActivity {

    // Campos de la interfaz de usuario
    private EditText etProductName, etProductNote;
    private Button btnSaveProduct, btnCancel;
    private Product product;
    private Switch etProductPending, switchLactosaEdit, switchGlutenEdit;
    private TextView tvProductId;

    /**
     * Este método es llamado cuando la actividad se crea por primera vez.
     * Inicializa las vistas de la interfaz de usuario y carga los datos del producto que se va a editar.
     *
     * @param savedInstanceState Si la actividad se recrea después de haber sido destruida, este bundle contiene el estado más reciente.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_product);

        // Inicializamos las vistas
        tvProductId = findViewById(R.id.tvProductEditId);
        etProductName = findViewById(R.id.etProductName);
        etProductNote = findViewById(R.id.etProductNote);
        etProductPending = findViewById(R.id.switch2);
        switchLactosaEdit = findViewById(R.id.switchLactosaEdit);
        switchGlutenEdit = findViewById(R.id.switchGlutenEdit);
        btnSaveProduct = findViewById(R.id.btnSaveProduct);
        btnCancel = findViewById(R.id.btnCancel);

        // Obtener el producto a editar desde el Intent
        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("product");

        // Cargar los datos del producto en los campos de edición
        loadProductData();

        // Configurar el botón de guardar
        btnSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct(); // Guardar los cambios realizados al producto
            }
        });

        // Configurar el botón de cancelar
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cerrar la actividad y volver a la anterior
            }
        });
    }

    /**
     * Carga los datos actuales del producto en los campos de texto de la actividad.
     * Muestra el nombre, la nota y el estado pendiente del producto.
     */
    private void loadProductData() {
        if (product != null) {
            tvProductId.setText("ID: "+product.getId());
            etProductName.setText(product.getName());
            etProductNote.setText(product.getNote());
            etProductPending.setChecked(product.isPending());
            switchGlutenEdit.setChecked(product.isGluten());
            switchLactosaEdit.setChecked(product.isLactosa());
        }
    }

    /**
     * Guarda los cambios realizados al producto en los campos de edición.
     * Realiza validaciones básicas y actualiza los datos del producto en la base de datos.
     * Envía el producto actualizado de vuelta a la actividad anterior usando un Intent.
     */
    private void saveProduct() {
        String name = etProductName.getText().toString().trim();
        String note = etProductNote.getText().toString().trim();
        Boolean pending = etProductPending.isChecked();
        Boolean lactosa = switchLactosaEdit.isChecked();
        Boolean gluten = switchGlutenEdit.isChecked();

        // Validación
        if (name.isEmpty() || note.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Actualizar el producto
        product.setName(name);
        product.setNote(note);
        product.setPending(pending);
        product.setLactosa(lactosa);
        product.setGluten(gluten);

        // Actualizar el producto en la base de datos
        Database.updateProduct(product);

        // Devolver el producto actualizado a DetailsProductActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("productEdited", product);
        setResult(RESULT_OK, resultIntent);

        // Mostrar un mensaje de confirmación
        Toast.makeText(this, "Producto actualizado correctamente.", Toast.LENGTH_SHORT).show();
        finish(); // Cerrar la actividad
    }


}
