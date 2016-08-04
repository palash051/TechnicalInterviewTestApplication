package com.example.jahirul.myapplication;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Jahirul on 4/12/2016.
 */
public class JsonParserService {

    private final Fragment fragment;
    Context context;
    RequestQueue mRequestQueue;
    private ArrayList<Product> products;

    private final static String dataUrl="https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json";

    public JsonParserService(Context context) {
        this.context = context;fragment=null;
        mRequestQueue= Volley.newRequestQueue(this.context);
    }

    public JsonParserService(Fragment fragment) {
        this.context = fragment.getContext();
        this.fragment=fragment;
        mRequestQueue= Volley.newRequestQueue(this.context);
    }

    public interface IApplicationDataUpdater {
        void showApplicationData(ArrayList< Product> applicationEntities);
    }

    public void loadApplicationList() {
        try {

            StringRequest jsObjRequest = new StringRequest
                    (Request.Method.GET,dataUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        if(response!=null && !response.equalsIgnoreCase("")){
                                            Type collectionType = new TypeToken<Collection<Product>>(){}.getType();
                                            Gson gson=new Gson();
                                            products=gson.fromJson(response,collectionType);
                                            IApplicationDataUpdater dataUpdater = (IApplicationDataUpdater) context;
                                            dataUpdater.showApplicationData(products);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    System.out.println("Error: " + error.toString());
                                }
                            });

            jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            mRequestQueue.add(jsObjRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
