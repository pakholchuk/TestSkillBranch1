package com.pakholchuk.testskillbranch1;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.pakholchuk.testskillbranch1.databinding.ItemBinding;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {
    private static final String TAG = "LogTag";
    private ItemBinding binding;

    private ArrayList<Bitmap> bitmaps = new ArrayList<>();

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        binding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ImageHolder(binding);
    }

    synchronized void addBitmapToList(Bitmap bitmap) {
        bitmaps.add(bitmap);
        Log.d(TAG, "addBitmapToList: " + bitmaps.size());
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        holder.binding.image.setImageBitmap(bitmaps.get(position));
    }

    @Override
    public int getItemCount() {
        return bitmaps.size();
    }

    static class ImageHolder extends RecyclerView.ViewHolder {
        ItemBinding binding;
        ImageHolder(@NonNull ItemBinding b) {
            super(b.getRoot());
            binding = b;
        }

    }
}
