package com.example.helloworld;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;  
import android.widget.Button;
import android.view.*;
import android.app.ProgressDialog;
import android.graphics.Bitmap;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {
	 /** Called when the activity is first created. */
    private WebView webview;
    private EditText edittext;
    private Button btn;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    webview=(WebView)findViewById(R.id.webView1);
	    webview.getSettings().setJavaScriptEnabled(true);
    	edittext=(EditText)findViewById(R.id.editText1);
	    btn = (Button)findViewById(R.id.button1);
	    if(webview!=null)
		{
			webview.setWebViewClient(new MyWebViewClient());
			webview.setWebChromeClient(new MyWebChromeClient());
		};
	    btn.setOnClickListener(new View.OnClickListener() {
	    	@Override
	    	public void onClick(View arg0) {	
				webview.loadUrl(edittext.getText().toString());
		   }
	    });	    
    	webview.loadUrl("http://www.baidu.com");	  
	}
	
	public boolean onKeyDown(int keyCode,KeyEvent event)
	{
	        if((keyCode==KeyEvent.KEYCODE_BACK)&&webview.canGoBack())
	        {
	                webview.goBack();
	                return true;
	        }
	        return super.onKeyDown(keyCode,event);
	}
	
	public class MyWebViewClient extends WebViewClient
	{
		public boolean shouldOverviewUrlLoading(WebView view,String url)
        {
                view.loadUrl(url);
                return true;
        }

		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			super.onPageStarted(view, url, favicon);
		}    
		
	}
	
	public class MyWebChromeClient extends WebChromeClient
	{
	    private ProgressDialog dialog;
	    @Override
		public void onProgressChanged(WebView view, int newProgress) {
			if(dialog==null){
				dialog=ProgressDialog.show(MainActivity.this,null,"正在加载中。。。");
			}
			if(newProgress>=100 && dialog!=null){
				dialog.dismiss();
				dialog=null;
			}
			super.onProgressChanged(view, newProgress);
		}
	    

		   
	}

}
