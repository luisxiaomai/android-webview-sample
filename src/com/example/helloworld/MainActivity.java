package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;  
import android.widget.Button;
import android.view.*;
import android.app.ProgressDialog;

public class MainActivity extends Activity {
	 /** Called when the activity is first created. */
    private WebView webview;
    private EditText edittext;
    private Button btn;
    private ProgressDialog dialog;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    webview=(WebView)findViewById(R.id.webView1);
	    webview.getSettings().setJavaScriptEnabled(true);
    	edittext=(EditText)findViewById(R.id.editText1);
    	loadUrl("http://www.baidu.com");	  
	    btn = (Button)findViewById(R.id.button1);
	    if(webview!=null)
		{
			webview.setWebViewClient(new MyWebViewClient()
			{
				@Override
				public void onPageFinished(WebView view, String url)
				{
					dialog.dismiss();
				}
			});
		};
	    btn.setOnClickListener(new View.OnClickListener() {
	    	@Override
	    	public void onClick(View arg0) {	
	    		loadUrl(edittext.getText().toString());	  
		   }
	    });	    
	    
	}
	public void loadUrl(String url)
	{
		if (webview!=null)
		{
			webview.loadUrl(url);
			dialog=ProgressDialog.show(this, null, " 正在加载中。。。");
			//webview.reload();
		}
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
	}

}
