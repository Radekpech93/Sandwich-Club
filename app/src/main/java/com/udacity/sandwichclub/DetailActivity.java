package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    Sandwich sandwich;
    TextView alsoKnownAsTextView;
    TextView alsoKnownAsTextViewLabel;
    TextView placeOfOriginTextView;
    TextView placeOfOriginTextViewLabel;
    TextView ingredientsTextView;
    TextView ingredientsTextViewLabel;
    TextView descriptionTextView;
    TextView descriptionTextViewLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        alsoKnownAsTextView = findViewById(R.id.also_known_tv);
        alsoKnownAsTextViewLabel = findViewById(R.id.also_known_tv_label);
        placeOfOriginTextView = findViewById(R.id.origin_tv);
        placeOfOriginTextViewLabel = findViewById(R.id.origin_tv_label);
        ingredientsTextView = findViewById(R.id.ingredients_tv);
        ingredientsTextViewLabel = findViewById(R.id.ingredients_tv_label);
        descriptionTextView = findViewById(R.id.description_tv);
        descriptionTextViewLabel = findViewById(R.id.description_tv_label);


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
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {



        if(sandwich.getPlaceOfOrigin().isEmpty()){
            placeOfOriginTextView.setVisibility(View.GONE);
            placeOfOriginTextViewLabel.setVisibility(View.GONE);
        }else{
            placeOfOriginTextView.setText(sandwich.getPlaceOfOrigin());
        }

        if(sandwich.getAlsoKnownAs().isEmpty()){
            alsoKnownAsTextView.setVisibility(View.GONE);
            alsoKnownAsTextViewLabel.setVisibility(View.GONE);
        }else{
            alsoKnownAsTextView.setText(sandwich.getAlsoKnownAs().toString());
        }

        if(sandwich.getIngredients().isEmpty()){
            ingredientsTextView.setVisibility(View.GONE);
            ingredientsTextViewLabel.setVisibility(View.GONE);
        }else{
            ingredientsTextView.setText(sandwich.getIngredients().toString());
        }

        if(sandwich.getDescription().isEmpty()){
            descriptionTextView.setVisibility(View.GONE);
            descriptionTextViewLabel.setVisibility(View.GONE);
        }else{
            descriptionTextView.setText(sandwich.getDescription());
        }



    }
}
