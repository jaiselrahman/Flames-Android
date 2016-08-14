package com.jaisel.flames;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.util.*;

public class MainActivity extends Activity 
{
	public String s1,s2,a;
	public String res;
	EditText ta,tb;
	TextView tv;
	Button clear,ok,about;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		tv = (TextView) findViewById(R.id.text);
		ta = (EditText) findViewById(R.id.edit1);
		tb = (EditText) findViewById(R.id.edit2); 
		
        Button ok=(Button) findViewById(R.id.ok);
		ok.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view)
				{

					a = new String("flames");
                    
                    s1 = new String(ta.getText().toString().toLowerCase());
                    s2 = new String(tb.getText().toString().toLowerCase());
                    
                    res = "FLAMES";
					flames();
					switch (a)
					{
						case "f": res = "FRIENDS"; 		break;
						case "l": res = "LOVE"; 		break;	
						case "a": res = "AFFECTION"; 	break;
						case "m": res = "MARRIAGE";		break;	
						case "e": res = "ENEMY"; 		break;	
						case "s": res = "SIBLING"; 		break;	
					}
					tv.setText(res);
				}
			});

		clear = (Button) findViewById(R.id.clear);
		clear.setOnClickListener(new View.OnClickListener() {
				public void onClick(View p1)
				{
					ta.setText("");
					tb.setText("");
					tv.setText("");
				}
			});

    }
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
	{
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
	public boolean onOptionsItemSelected(MenuItem item)
	{
        switch (item.getItemId())
		{
			case R.id.about:
				Intent i=new Intent(getApplicationContext(), about.class);
   			 	startActivityForResult(i, 0);
				return true;
			case R.id.exit:
				System.exit(0);
				return true;
			default:
				return super.onOptionsItemSelected(item);
        } 

	}
	int flames()
	{
		int i=0,j=0,n=0,len1,len2,tot;
		
		char[] ar1 = s1.toCharArray();
		Arrays.sort(ar1);
		s1 = new String(ar1);
		s1=s1.trim();
//		Log.d("debug : ",s1);
		ar1 = s2.toCharArray();
		Arrays.sort(ar1);
		s2 = new String(ar1);
		s2=s2.trim();
//		Log.d("debug : ","\""+s2+"\"")

		if (s1.length() < s2.length())
		{
			String t=s2;
			s2 = s1;
			s1 = t;
		}
		len1 = s1.length();
		len2 = s2.length();	
		tot = s1.length() + s2.length();


		for (i = 0;i < len1;i++)
		{
			for (j = n;j < len2;j++)
			{
				if (s1.charAt(i) == s2.charAt(j))
				{
					tot -= 2;
					n = ++j;
					break;	
				}
			}
		}	
		if (tot <= 0) return 0;
		rem(tot);

		return tot;

	}

	void rem(int n)
	{
		String t;
		int n2;
		while (a.length() > 1)
		{
			n2 = n % a.length();
			n2 = (n2 == 0) ?a.length(): n2;
			t = a.substring(n2);
			a = a.substring(0, n2 - 1);
			a = t + a;

		}

	}
}
