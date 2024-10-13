package com.example.ishoppinglist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.database.Database;
import com.example.ishoppinglist.models.Product;

/**
 * @author Cristina
 */
public class DetailsProductActivity extends AppCompatActivity {

    // Campos de la interfaz de usuario
    private Product product;
    private TextView tvProductName, tvProductNote, tvProductId;
    private Switch switch1;
    Button btnBackDetailProduct, btnEditProduct;
    Intent intentDetail;
    Adapter adapter = null;

    /**
     * Método llamado cuando la actividad es creada por primera vez.
     * Inicializa la interfaz de usuario y muestra los detalles del producto.
     *
     * @param savedInstanceState Estado anterior de la actividad, si está disponible.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_product);

        // Obtener el producto desde el Intent
        product = (Product) getIntent().getSerializableExtra("product");

        // Referencias a las vistas
        tvProductName = findViewById(R.id.tvProductName);
        tvProductNote = findViewById(R.id.tvProductNote);
        tvProductId = findViewById(R.id.tvProductDetailsId);
        switch1 = findViewById(R.id.switch1);

        // Mostrar los detalles del producto
        if (product != null) {
            tvProductName.setText(product.getName());
            tvProductNote.setText(product.getNote());
            tvProductId.setText("ID: " + product.getId());
            switch1.setChecked(product.isPending());
        }

        // Mostrar los detalles del producto
        displayProductDetails(product);

        // Listener para detectar cambios en el switch y actualizar el estado del producto
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            product.setPending(isChecked); // Actualizar el estado de pendiente
            if (!isChecked) {
                Database.deleteProductPendingList(product);
            } else {
                Database.addProductToPendingList(product);
            }
        });

        // Botón para volver a la actividad principal
        btnBackDetailProduct = findViewById(R.id.btnBackDetailProduct);
        btnBackDetailProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentDetail = new Intent(DetailsProductActivity.this, MainActivity.class);
                // Método para abrir otra activity
                startActivity(intentDetail);
            }
        });

        // Botón para editar el producto
        btnEditProduct = findViewById(R.id.btnEditProduct);
        btnEditProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentDetail = new Intent(DetailsProductActivity.this, EditProductActivity.class);
                intentDetail.putExtra("product", product); // Pasamos el producto a la nueva actividad
                startActivity(intentDetail); // Iniciamos la actividad de detalles

            }
        });

    }

    /**
     * Método llamado cuando la actividad es reanudada.
     * Actualiza el producto si ha sido modificado en la actividad de edición.
     */
    @Override
    protected void onResume() {
        super.onResume();
        for (Product productAux : Database.productListPending) {
            if (productAux.getId() == product.getId()) {
                product = productAux;
                break;
            }
        }
        // Recargar los datos del producto, por si fueron modificados
        displayProductDetails(product);
    }

    /**
     * Muestra los detalles del producto en los elementos
     *
     * @param product El producto cuyos detalles serán mostrados.
     */
    private void displayProductDetails(Product product) {
        if (tvProductName != null && product != null) {
            tvProductName.setText(product.getName());
            tvProductNote.setText(product.getNote());
            tvProductId.setText("ID: " + product.getId());
            switch1.setChecked(product.isPending());
        } else {
            Log.e("DetailsProductActivity", "Product is null.");
        }
    }

}
