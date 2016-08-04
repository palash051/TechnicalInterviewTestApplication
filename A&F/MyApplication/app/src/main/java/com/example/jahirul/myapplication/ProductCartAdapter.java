package com.example.jahirul.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.CharacterPickerDialog;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by Jahirul Bhuiyan on 7/22/2016.
 */
public class ProductCartAdapter extends RecyclerView.Adapter<ProductCartAdapter.CartViewHolder> {
    Context mContext;
    ArrayList<Product> mData;
    Product product;
    private ImageLoader imageLoader = new ImageLoader();
    public ProductCartAdapter(Context context, ArrayList<Product> data) {
        mContext = context;
        mData = data;
    }
    @Override
    public ProductCartAdapter.CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.normal_view_list_item_layout, parent, false);
        CartViewHolder viewHolder = new CartViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ProductCartAdapter.CartViewHolder holder, int position) {
        product = mData.get(position);
        holder.tvTopDesccription.setText(product.getTopDescription());
        holder.tvTitle.setText(product.getTitle());
        holder.tvPromoMessage.setText(product.getPromoMessage());
        if(product.getBottomDescription()!=null) {
            holder.tvBottomDescription.setText(correctLinkPaths(Html.fromHtml(product.getBottomDescription())));
           // holder.tvBottomDescription.setClickable(true);
            holder.tvBottomDescription.setMovementMethod(LinkMovementMethod.getInstance());
        }

        ArrayList<ProductContent> contents=product.getContent();

        if(contents!=null){
            TextView tvContent;
            LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            param.setMargins(5,5,5,3);
            holder.llContent.removeAllViews();
            for (ProductContent productContent:contents) {
                tvContent=new TextView(mContext);
                tvContent.setLayoutParams(param);
                tvContent.setBackgroundResource(R.drawable.content_border);
                tvContent.setPadding(0,5,0,5);
                tvContent.setGravity(Gravity.CENTER);
                tvContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                tvContent.setText(productContent.getTitle());
                tvContent.setTag(productContent.getTarget());
                tvContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, ContentViewActivity.class);
                        Bundle b = new Bundle();
                        b.putString("product_Content",String.valueOf(v.getTag()));
                        intent.putExtras(b);
                        mContext.startActivity(intent);
                    }
                });
                holder.llContent.addView(tvContent);
            }

        }
        Bitmap cachedImage = null;
        try {
            cachedImage = imageLoader.loadImage(product.getBackgroundImage(), new ImageLoader.ImageLoadedListener() {
                public void imageLoaded(Bitmap imageBitmap) {
                    holder.ivProductThumbnail.setImageBitmap(imageBitmap);
                    notifyDataSetChanged();                }
            });
        } catch (MalformedURLException e) {
            holder.ivProductThumbnail.setImageResource(R.drawable.no_image);
        }

        if( cachedImage != null ) {
            holder.ivProductThumbnail.setImageBitmap(cachedImage);
        }else {
            holder.ivProductThumbnail.setImageResource(R.drawable.no_image);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        public View container;
        TextView tvTopDesccription;
        TextView tvTitle;
        TextView tvPromoMessage;
        TextView tvBottomDescription;
        ImageView ivProductThumbnail;
        LinearLayout llContent;

        public CartViewHolder(View view) {
            super(view);
            container = view;
            tvTopDesccription= (TextView) container.findViewById(R.id.tvtopDesccription);
            tvTitle= (TextView) container.findViewById(R.id.tvTitle);
            tvPromoMessage= (TextView) container.findViewById(R.id.tvPromoMessage);
            tvBottomDescription= (TextView) container.findViewById(R.id.tvBottomDescription);
            ivProductThumbnail= (ImageView) container.findViewById(R.id.ivProductThumbnail);
            llContent= (LinearLayout) container.findViewById(R.id.llContent);
        }
    }

    /**
     * Removes relative a hrefs
     * @param spantext (from Html.fromhtml())
     * @return spanned with fixed links
     */
    public Spanned correctLinkPaths (Spanned spantext) {
        Object[] spans = spantext.getSpans(0, spantext.length(), Object.class);
        for (Object span : spans) {
            int start = spantext.getSpanStart(span);
            int end = spantext.getSpanEnd(span);
            int flags = spantext.getSpanFlags(span);
            if (span instanceof URLSpan) {
                URLSpan urlSpan = (URLSpan) span;
                ((Spannable) spantext).removeSpan(span);
                ((Spannable) spantext).setSpan(urlSpan, start, end, flags);
            }
        }
        return spantext;
    }
}
