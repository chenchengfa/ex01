package com.example.ex_xmbcit_day;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(new MyAdapter());
	}
	class MyAsync extends AsyncTask<String, Void, Bitmap>{

		private ImageView mImage;
		public MyAsync(ImageView image) {
			mImage = image;
		}
		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = null;
			try {
				URL url = new URL(params[0]);
				InputStream is = url.openStream();
				bitmap = BitmapFactory.decodeStream(is);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bitmap;
		}
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			mImage.setImageBitmap(result);
		}
	}
	
	class MyAdapter extends BaseAdapter{

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View inflate = getLayoutInflater().inflate(R.layout.list_item, null);
			ImageView image = (ImageView) inflate.findViewById(R.id.imageView1);
			MyAsync myAsync = new MyAsync(image);
			myAsync.execute("http://192.169.1.38/images/mymusic"+position+".png");
			return inflate;
		}
		
		@Override
		public int getCount() {
			return 15;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

}
