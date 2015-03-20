package com.example.thegame;

import java.util.Random;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
	
	Sensor accelerometer;
	SensorManager sm;
	TextView topOption;
	TextView botOption;
	TextView leftOption;
	TextView rightOption;
	TextView questionText;
	TextView score;
	int scoreValue;
	private int answer;
	private HashTable hT = new HashTable ();
	private Question current;
	Random generator = new Random(); 
	boolean answerable;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		scoreValue=0;
		sm = (SensorManager)getSystemService(SENSOR_SERVICE);
		accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);	
		
		topOption=(TextView)findViewById(R.id.topOption);
		botOption=(TextView)findViewById(R.id.botOption);
		leftOption=(TextView)findViewById(R.id.leftOption);
		rightOption=(TextView)findViewById(R.id.rightOption);
		score=(TextView)findViewById(R.id.score);
		questionText=(TextView)findViewById(R.id.questionText);
		
		newTable (hT);
		current = hT.get(generator.nextInt(20) + 1);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onSensorChanged (SensorEvent event) {
		topOption.setText(current.getO1());
		botOption.setText(current.getO2());
		leftOption.setText(current.getO3());
		rightOption.setText(current.getO4());
		questionText.setText(current.getQuestion());
		score.setText(String.valueOf(scoreValue));
		
		if (event.values[1] < 5 && event.values[1] > -5 && event.values[0] < 5 && event.values[0] > -5) {
			answerable = true;
			topOption.setBackgroundColor(getResources().getColor(R.color.text_view_background));
			botOption.setBackgroundColor(getResources().getColor(R.color.text_view_background));
			leftOption.setBackgroundColor(getResources().getColor(R.color.text_view_background));
			rightOption.setBackgroundColor(getResources().getColor(R.color.text_view_background));
		}
		
		if (event.values[1]>8 && answerable) {
			answer = 4;
			topOption.setBackgroundColor(getResources().getColor(R.color.BRACK));
			botOption.setBackgroundColor(getResources().getColor(R.color.BRACK));
			leftOption.setBackgroundColor(getResources().getColor(R.color.BRACK));
			if (answer == current.getAnswer()) {	
				scoreValue += 100;
			}
			else {
				scoreValue -= 50;
			}
			current = hT.get(generator.nextInt(20) + 1);
			answerable = false;
		} 
		else if (event.values[1]<-8 && answerable) {
			answer = 3;
			topOption.setBackgroundColor(getResources().getColor(R.color.BRACK));
			botOption.setBackgroundColor(getResources().getColor(R.color.BRACK));
			rightOption.setBackgroundColor(getResources().getColor(R.color.BRACK));
			if (answer == current.getAnswer()) {
				scoreValue += 100;
			}
			else {
				scoreValue -= 50;
			}
			current = hT.get(generator.nextInt(20) + 1);
			answerable = false;
		} 
		else if (event.values[0]>8 && answerable) {
			answer = 2;
			topOption.setBackgroundColor(getResources().getColor(R.color.BRACK));
			leftOption.setBackgroundColor(getResources().getColor(R.color.BRACK));
			rightOption.setBackgroundColor(getResources().getColor(R.color.BRACK));
			if (answer == current.getAnswer()) {
				scoreValue += 100;
			}
			else {
				scoreValue -= 50;
			}
			current = hT.get(generator.nextInt(20) + 1);
			answerable = false;
		} 
		else if (event.values[0]<-8 && answerable) {
			answer =1;
			botOption.setBackgroundColor(getResources().getColor(R.color.BRACK));
			leftOption.setBackgroundColor(getResources().getColor(R.color.BRACK));
			rightOption.setBackgroundColor(getResources().getColor(R.color.BRACK));
			if (answer == current.getAnswer()) {
				scoreValue += 100;
			}
			else {
				scoreValue -= 50;
			}
			current = hT.get(generator.nextInt(20) + 1);
			answerable = false;
		}
		
		
	}
	
	public void newTable (HashTable h){
		Question q;
		q = new Question (1, "What is the tallest mountain?", "Everest", "Alps", "Fuji", "Olympus", 1);
		h.add(q);
		q = new Question (2, "What is the longest river?", "Yangtze", "Indus", "Nile", "Ganges", 3);
		h.add(q);
		q = new Question (3, "What is the biggest island?", "Greenland", "Blueland", "Redland", "Blackland", 1);
		h.add(q);
		q = new Question (4, "What is the biggest ocean?", "Pacific", "Atlantic", "Indian", "Arctic", 1);
		h.add(q);
		q = new Question (5, "What is the largest plateau?", "Antarctic", "Tibetan", "Loess", "Brazilian", 1);
		h.add(q);
		q = new Question (6, "What is the largest country?", "China", "Russia", "USA", "Canada", 2);
		h.add(q);
		q = new Question (7, "What is the tallest building?", "Taipei 101", "Khalifa", "CN tower", "Willis", 2);
		h.add(q);
		q = new Question (8, "What is the oldest arch bridge", "Vasco", "Armigo", "Caravan", "Arkadiko", 4);
		h.add(q);
		q = new Question (9, "Which country has the longest railway", "USA", "Russia", "Canada", "China", 4);
		h.add(q);
		q = new Question (10, "Which country has the longest bridge", "USA", "Russia", "Canada", "China", 4);
		h.add(q);
		q = new Question (11, "What is the longest tunnel?", "Channel", "Seikan", "laerdal", "Hsuehshan", 2);
		h.add(q);
		q = new Question (12, "What is the busiest airport?", "Hong Kong", "Pearson", "Atlanta", "Memphis", 3);
		h.add(q);
		q = new Question (13, "What is the largest transport aircraft", "C-5", "Y-20", "An-225", "An-124", 3);
		h.add(q);
		q = new Question (14, "What is largest helicopter", "AH-64", "AH-1", "Mi-26", "WZ-10", 3);
		h.add(q);
		q = new Question (15, "What is the fastest aircraft", "F-20", "X-43", "F-35", "SR-71", 2);
		h.add(q);
		q = new Question (16, "What is the most expensive aircraft", "F-35", "F-22", "B-2", "F-16", 3);
		h.add(q);
		q = new Question (17, "What is the largest aircraft carrier?", "Enterprise", "Nimitz", "Carl Vinson", "Ronald Reagan", 2);
		h.add(q);
		q = new Question (18, "What is the most quiest nuclear submarine?", "Borei", "Seawolf", "Virginia", "Typhoon", 2);
		h.add(q);
		q = new Question (19, "What is the fastest supercomputer?", "TH-2", "Titian", "K", "TH-1", 1);
		h.add(q);
		q = new Question (20, "What is the largest library?", "Congress", "British", "Toronto", "New York", 1);
		h.add(q);
		/*q = new Question (21, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (22, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (23, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (24, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (25, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (26, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (27, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (28, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (29, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (30, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (31, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (32, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (33, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (34, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (35, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (36, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (37, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (38, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (39, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);
		q = new Question (40, "What is Shengnou's last name", "yo", "li", "blazed", "lin", 4);
		h.add(q);*/
	}
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
