package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.adapters.ProductAdapter;
import com.example.ishoppinglist.database.Database;
import com.example.ishoppinglist.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cristina
 */
public class MainActivity extends AppCompatActivity {

    ListView lvProducts;  // El ListView donde se mostrarán los productos
    ProductAdapter adapter; // Adaptador personalizado para los productos
    Spinner spinnerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Configura la pantalla para que ocupe todo el espacio disponible
        EdgeToEdge.enable(this);
        // Establece el layout de la actividad principal
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Asignación de botones y configuración de eventos onClick
        Button btnAddProduct = findViewById(R.id.btn_add_productMain);
        Button btnAddPending = findViewById(R.id.btn_add_pendingMain);

        // Evento para añadir un nuevo producto
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentDetailActivity = new Intent(MainActivity.this, NewProductActivity.class);
                // Método para abrir otra activity
                startActivity(intentDetailActivity);
            }
        });

        // Evento para añadir un producto a la lista de pendientes
        btnAddPending.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentDetailActivity02 = new Intent(MainActivity.this, AddProductActivity.class);
                // Método para abrir otra activity
                startActivity(intentDetailActivity02);
            }
        });

        spinnerMain = findViewById(R.id.spinnerMain);

        setupSpinnerBasic(spinnerMain);

        // Inicializamos el ListView
        lvProducts = findViewById(R.id.lvProduct);

        // Verificamos si la lista de productos está vacía, si lo está, precargamos datos
        if (Database.productList.size() == 0) {
            // Pre-cargamos datos en la "base de datos"
            Database.preloadData();
        }

        // Creamos el adaptador personalizado
        adapter = new ProductAdapter(this, 0, Database.productListPending);

        // Asignamos el adaptador al ListView
        lvProducts.setAdapter(adapter);

        // Agregamos un listener para manejar clicks en los items de la lista
        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = adapter.getItem(position); // Obtenemos el producto seleccionado
                if (selectedProduct != null) {
                    Intent intent = new Intent(MainActivity.this, DetailsProductActivity.class);
                    intent.putExtra("product", selectedProduct); // Pasamos el producto a la nueva actividad
                    startActivity(intent); // Iniciamos la actividad de detalles
                } else {
                    Log.e("MainActivity", "El producto seleccionado es null.");
                    Toast.makeText(MainActivity.this, "Producto no disponible", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    /**
     * Se llama cuando la actividad vuelve a estar activa (por ejemplo, después de volver de otra actividad).
     * Aquí se actualiza la lista de productos.
     */
    @Override
    protected void onResume() {
        super.onResume();
        updateProductList(); // Llama al método para actualizar la lista de productos
        adapter.notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
    }

    /**
     * Método para actualizar la lista de productos en el ListView.
     */
    private void updateProductList() {
        // Actualiza el adaptador con la lista de productos pendientes
        adapter.notifyDataSetChanged();
    }


    private void setupSpinnerBasic(Spinner spinnerMain) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.SpinnerMain, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMain.setAdapter(adapter);
    }



}