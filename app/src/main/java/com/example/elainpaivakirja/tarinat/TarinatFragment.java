package com.example.elainpaivakirja.tarinat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.elainpaivakirja.R;
import com.example.elainpaivakirja.databinding.FragmentTarinatBinding;
public class TarinatFragment extends Fragment {

    private FragmentTarinatBinding binding;
    private TarinatViewModel tarinatViewModel;
    private EditText editTextStory;
    private TextView textView;
    private Button buttonAddStory;

    private Button buttonAddPhoto;

    private ImageView imageStory;
    private int REQUEST_IMAGE_PICKER = 1;
    private int RESULT_OK = -1;

    private String imagePath;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TarinatViewModel tarinatViewModel =
                new ViewModelProvider(this).get(TarinatViewModel.class);

        binding = FragmentTarinatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        imageStory = root.findViewById(R.id.image_story);
        editTextStory = root.findViewById(R.id.edit_text_story);
        textView = root.findViewById(R.id.text_Tarinat);
        buttonAddStory = root.findViewById(R.id.button_add_story);
        buttonAddPhoto = root.findViewById(R.id.button_add_photo);

        buttonAddStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String story = editTextStory.getText().toString();

                if (!story.isEmpty()) {
                    if (textView.getText().toString().isEmpty()) {
                        textView.setText(story);
                    } else {
                        String currentText = textView.getText().toString();
                        textView.setText(currentText + "\n\n" + story);
                    }
                    editTextStory.setText("");
                    if (imagePath != null) {
                        imageStory.setImageURI(Uri.parse(imagePath));
                        loadImage();

                    }
                }
            }
        });

        buttonAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImagePath();
            }
        });
        tarinatViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    private void getImagePath() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_PICKER);
    }


    private void loadImage() {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            imageStory.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICKER && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            imagePath = uri.getPath();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}