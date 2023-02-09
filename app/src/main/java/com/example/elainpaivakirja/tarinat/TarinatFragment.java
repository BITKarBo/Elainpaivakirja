package com.example.elainpaivakirja.tarinat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.elainpaivakirja.databinding.FragmentTarinatBinding;
public class TarinatFragment extends Fragment {

    private FragmentTarinatBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TarinatViewModel tarinatViewModel =
                new ViewModelProvider(this).get(TarinatViewModel.class);

        binding = FragmentTarinatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTarinat;
        tarinatViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}