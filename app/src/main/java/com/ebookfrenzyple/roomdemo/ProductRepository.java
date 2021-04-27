package com.ebookfrenzyple.roomdemo;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;


public class ProductRepository {
    private MutableLiveData<List<Product>> searchResult = new MutableLiveData<>();

    private ProductDao productDao;

    private LiveData<List<Product>> allProducts;


    public ProductRepository(Application application){
        ProductRoomDatabase db;
        db = ProductRoomDatabase.getDatabase(application);
        productDao = db.productDao();
        allProducts = productDao.getAllProducts();
    }



    private void asyncFinished(List<Product> results){
        searchResult.setValue(results);
    }

    private static class QueryAsyncTask extends AsyncTask<String, Void, List<Product>>{
        private ProductDao asyncTaskDao;
        private ProductRepository delegate = null;

        QueryAsyncTask(ProductDao dao){
            asyncTaskDao = dao;

        }

        @Override
        protected List<Product> doInBackground(final String ... params){
            return asyncTaskDao.findProduct(params[0]);
        }
        @Override
        protected void onPostExecute(List<Product> result){
            delegate.asyncFinished(result);
        }
    }

    private  static class InsertAsyncTask extends AsyncTask<Product, Void, Void>{
        private ProductDao asyncTaskDao;

        InsertAsyncTask(ProductDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected  Void doInBackground(final Product... params){
            asyncTaskDao.insertProduct(params[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<String, Void, Void> {
        private ProductDao asynTaskDao;

        DeleteAsyncTask(ProductDao dao){
            asynTaskDao = dao;
        }

        @Override
        protected  Void doInBackground(final String... params){
            asynTaskDao.deleteProduct(params[0]);

            return null;
        }

    }

    public void insertProduct(Product newproduct){
        InsertAsyncTask task = new InsertAsyncTask(productDao);
        task.execute(newproduct);

    }

    public void deleteProduct(String name){
        DeleteAsyncTask task = new DeleteAsyncTask(productDao);
        task.execute(name);
    }
    public void findProduct(String name){
        QueryAsyncTask task = new QueryAsyncTask(productDao);
        task.delegate = this;
        task.execute(name);

    }
    public LiveData<List<Product>> getAllProducts(){
        return allProducts;
    }

    public MutableLiveData<List<Product>> getSearchResult(){
        return searchResult;
    }

}
















