package com.example.ishoppinglist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.models.Product;
import java.util.List;

/**
 * Adaptador personalizado para mostrar los productos en una lista.
 * Extiende de ArrayAdapter para gestionar la visualización de objetos de tipo Product.
 * @author Cristina
 */
public class ProductAdapter extends ArrayAdapter<Product> {


    private Context context;

    /**
     * Constructor del adaptador.
     *
     * @param context Context El contexto en el cual se utilizará el adaptador.
     * @param resource int El ID del recurso de la vista para cada elemento de la lista.
     * @param products List <Product> La lista de productos que se va a mostrar.
     */
    public ProductAdapter(Context context,int resource, List<Product> products) {
        super(context, resource, products);
    }

    // Método para indicar el XML de la vista y realizar las modificacioness
    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Obtenemos el producto en la posición actual
        Product product = getItem(position);

        // Si todavía no se ha creado la vista
        if (convertView == null) {
             /* Aquí le indicamos el XML que queremos que cree llamando al método .inflate
                .inflate(hace referencia al xml que queremos mostrar, Viewgroup parent,
                false para que primero cree el xml padre y luego el hijo)*/
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.name_product, parent, false);
        }

        TextView productNameTextView = convertView.findViewById(R.id.tvProductNameVisual);

        productNameTextView.setText(product.getName());

        if (product.isLactosa() && product.isGluten()) {
           convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.azul));
        } else if (product.isLactosa()) {
            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.rosa));
        } else if (product.isGluten()) {
            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.gris));
        }

        TextView tvProductNameVisual = convertView.findViewById(R.id.tvProductNameVisual);

        // Asignamos los valores a las vistas
        if (product != null) {
            tvProductNameVisual.setText(product.getName()); // Nombre del producto
        }
        // Devolvemos la vista modificada
        return convertView;

    }

}
