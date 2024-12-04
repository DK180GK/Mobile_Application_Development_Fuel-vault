package com.example.fuelvault;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MileageCalculator extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_mileage_calculator, container, false);

        // Initialize views after inflation
        EditText editTextDistance = rootView.findViewById(R.id.distance_input);
        EditText editTextFuelPrice = rootView.findViewById(R.id.fuel_input);
        Button button = rootView.findViewById(R.id.calculate_button);
        TextView textView = rootView.findViewById(R.id.mileage);

        // Button click logic for calculating mileage
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    float distance = Float.parseFloat(editTextDistance.getText().toString());
                    float fuelPrice = Float.parseFloat(editTextFuelPrice.getText().toString());

                    if (fuelPrice > 0) {
                        // Calculate mileage: distance / (fuel price / 100)
                        float mileage = distance / (fuelPrice / 100);
                        textView.setText(String.format("%.2f Km/L", mileage));
                    } else {
                        textView.setText("Invalid fuel price");
                    }
                } catch (NumberFormatException e) {
                    textView.setText("Please enter valid numbers");
                }
            }
        });

        return rootView;
    }
}
