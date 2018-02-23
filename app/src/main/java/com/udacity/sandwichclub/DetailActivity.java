package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(imageIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();

        String alsoKnownAsString = "";
        if( !alsoKnownAsList.isEmpty() ) {
            alsoKnownAsString = alsoKnownAsList.get(0);

            for (int i = 1; i < alsoKnownAsList.size(); i++) {
                alsoKnownAsString += ", " + alsoKnownAsList.get(i);
            }
        }

        TextView alsoKnownTv = findViewById(R.id.also_known_tv);
        alsoKnownTv.setText(alsoKnownAsString);

        TextView originTv = findViewById(R.id.origin_tv);
        originTv.setText(sandwich.getPlaceOfOrigin());

        TextView descriptionTv = findViewById(R.id.description_tv);
        descriptionTv.setText(sandwich.getDescription());

        List<String> ingredientsList = sandwich.getIngredients();

        String ingredientsString = "";
        if( !ingredientsList.isEmpty() ) {
            ingredientsString = ingredientsList.get(0);

            for (int i = 1; i< ingredientsList.size(); i++) {
                ingredientsString += ", " + ingredientsList.get(i);
            }
        }

        TextView ingredientsTv = findViewById(R.id.ingredients_tv);
        ingredientsTv.setText(ingredientsString);

    }
}
