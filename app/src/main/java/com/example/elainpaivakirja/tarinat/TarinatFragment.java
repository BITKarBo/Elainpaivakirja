package com.example.elainpaivakirja.tarinat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.elainpaivakirja.R;
import com.example.elainpaivakirja.databinding.FragmentTarinatBinding;

import java.io.IOException;

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

    private String noteText = "";

    private String imagePath;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Luo Shared Preferences -olio
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("story",Context.MODE_PRIVATE);
        // Muokkaa Shared Preferences -oliota
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //editor.clear();
        //editor.commit();
        TarinatViewModel tarinatViewModel = new ViewModelProvider(this).get(TarinatViewModel.class);

        binding = FragmentTarinatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        imageStory = root.findViewById(R.id.image_story);
        editTextStory = root.findViewById(R.id.edit_text_story);
        textView = root.findViewById(R.id.text_Tarinat);
        buttonAddStory = root.findViewById(R.id.button_add_story);
        buttonAddPhoto = root.findViewById(R.id.button_add_photo);


        noteText = sharedPreferences.getString("story", "");
        // näytä TextViewissä
        textView.setText(noteText);



        buttonAddStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String story = editTextStory.getText().toString();

                if (!story.isEmpty()) {
                    if (textView.getText().toString().isEmpty()) {
                        textView.setText(story);
                        // Tallenna teksti Shared Preferencesiin
                        editor.putString("story", story);
                        // Vahvista tallennus
                        editor.apply();
                    } else {
                        String currentText = textView.getText().toString();
                        textView.setText(currentText + "\n\n" + story);
                        editor.putString("story", currentText + "\n\n" + story);
                        // Vahvista tallennus
                        editor.apply();
                    }
                    editTextStory.setText("");

                    if (imagePath != null) {
                        Glide.with(getContext())
                                .load("https://t1.daumcdn.net/cfile/tistory/9939EB365DA9C3A209")
                                .into(imageStory);

                    }
                }
            }
        });

        buttonAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
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
    private void imageChooser()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        imagePath = selectedImageUri.getPath();
                    }
                }
            });

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
            Log.wtf("TOIMI", uri.getPath());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}