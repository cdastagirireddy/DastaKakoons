package com.kuncham.kakoons;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by admin on 1/24/2018.
 */

public class ProductAdapter extends BaseAdapter {

    Context ct;
    List<Product> details;
    ImageLoader il;
    int layout;
    public ProductAdapter(Context ct, List<Product> details, int layout) {
        this.ct=ct;
        this.details=details;
        this.layout=layout;

            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(ct).build();
            il=ImageLoader.getInstance();
            il.init(config);
    }

    @Override
    public int getCount() {
        return details.size();
    }

    @Override
    public Object getItem(int i) {
        return details.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = li.inflate(layout, null);

        ImageView iv1 = (ImageView) v.findViewById(R.id.top_men_image);
        TextView tv1 = (TextView) v.findViewById(R.id.heading_mentop);
        //TextView tv2 = (TextView) v.findViewById(R.id.tv_price);
        TextView tv3=(TextView)v.findViewById(R.id.description_mentop);

        Product p=details.get(position);
        tv1.setText(p.getName());
        // tv2.setText(p.getPrice());
        tv3.setText(p.getDescription());
        il.displayImage(p.getImage_url(),iv1);
        return v;
    }

}
