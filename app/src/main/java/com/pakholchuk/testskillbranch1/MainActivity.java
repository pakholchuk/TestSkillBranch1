package com.pakholchuk.testskillbranch1;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.pakholchuk.testskillbranch1.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LogTag";
    public static final String URL = "https://via.placeholder.com/10";
    private ActivityMainBinding binding;
    private ImageAdapter imageAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Target> targets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initRecycler();
        fillTable();
    }

    void fillTable(){
        targets = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            targets.add(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    imageAdapter.addBitmapToList(bitmap);
                    imageAdapter.notifyItemInserted(imageAdapter.getItemCount());
                    if (imageAdapter.getItemCount() == 20) {
                        new DoneDialogFragment().show(getSupportFragmentManager(), "Tag");
                    }
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    Log.d(TAG, "onBitmapFailed: " + Thread.currentThread());
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    Log.d(TAG, "onPrepareLoad: ");
                }
            });
            Picasso picasso = Picasso.get();
            Log.d(TAG, "loadPics: " + i + picasso.toString());
            picasso
                    .load(URL+(i*5))
                    .into(targets.get(i));
        }
    }

    private void initRecycler() {
        layoutManager = new LinearLayoutManager(this);
        imageAdapter = new ImageAdapter();
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(imageAdapter);
    }

}