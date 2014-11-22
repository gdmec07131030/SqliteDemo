package com.example.sqlitedemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    EditText et1,et2,et3,et4;
    Button bt1,bt2,bt3,bt4,bt5,bt6;
    TextView tv;
    People people=new People();
    DBAdapter dbAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt1=(Button) findViewById(R.id.button1);
		bt2=(Button) findViewById(R.id.button2);
		bt3=(Button) findViewById(R.id.button3);
		bt4=(Button) findViewById(R.id.button4);
		bt5=(Button) findViewById(R.id.button5);
		bt6=(Button) findViewById(R.id.button6);
		et1=(EditText) findViewById(R.id.editText1);
		et2=(EditText) findViewById(R.id.editText2);
		et3=(EditText) findViewById(R.id.editText3);
		et4=(EditText) findViewById(R.id.editText4);
		tv=(TextView) findViewById(R.id.textView5);
		dbAdapter=new DBAdapter(this,null,null,0);
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(getPeopleInfo()!=null){
					long id=dbAdapter.insert(people);
					tv.setText("people id:"+id+"has been created");
				}
				
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				long id =Long.parseLong(et1.getText().toString());
				People[] ps=dbAdapter.retrieveById(id);
				tv.setText(ps[0].toString());
				
			}
		});
        bt3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				People[] ps=dbAdapter.retrieveAll();
				if(ps==null||ps.length==0){
					return ;
				}
				String msg="";
				for(int i=0;i<ps.length;i++){
					msg+=ps[i].toString();
				}
				tv.setText(msg);
				
			}
		});
        bt4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(getPeopleInfo()!=null){
					long id=dbAdapter.updata(people, Integer.parseInt(et1.getText().toString()));
					tv.setText("id:"+id+"has been updated.");
				}
				
			}
		});
        bt5.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		long id=Long.parseLong(et1.getText().toString());
		int affectrows=dbAdapter.deleteById(id);
		tv.setText(affectrows+"rows have been deleted.");
		
	}
});
        bt6.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		int affectrows=dbAdapter.deleteAll();
		tv.setText(affectrows+"rows have been deleted all.");
		
	}
});
	}
    People getPeopleInfo(){
    	people.Name=et2.getText().toString();
    	people.Age=Integer.parseInt(et3.getText().toString());
    	people.Height=Float.parseFloat(et4.getText().toString());
    	if(people.Name.equals("")){
    		return null;
    	}else{
    		return people;
    	}
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
