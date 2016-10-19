package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import android.support.v4.content.ContextCompat;
/**
 * Created by Administrator on 9/24/2016.
 */
public class wordAdapter extends ArrayAdapter<word> {
    private int mColorResourceId;

    public wordAdapter(Activity context, ArrayList<word> words,int colorResourceId) {

        super(context, 0, words);
        mColorResourceId = colorResourceId;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);


        // Get the {@link AndroidFlavor} object located at this position in the list
        word currentword = getItem(position);

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);

        defaultTextView.setText(currentword.getmDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView miwoTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        miwoTextView.setText(currentword.getmMiwokTranslation());
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        // Check if an image is provided for this word or not
        if (currentword.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentword.getImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }



        return listItemView;
    }
}