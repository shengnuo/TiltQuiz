package com.example.thegame;

public class Question {
	private int key;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private int answer;
	
	public int getKey () {return key;}
	public String getQuestion () {return question;}
	public String getO1 () {return option1;}
	public String getO2 () {return option2;}
	public String getO3 () {return option3;}
	public String getO4 () {return option4;}
	public int getAnswer () {return answer;}
	
	public void setKey (int k) { key = k;}
	public void setQuestion (String q) { question = q;}
	public void setO1 (String s) { option1 = s;}
	public void setO2 (String s) { option2 = s;}
	public void setO3 (String s) { option3 = s;}
	public void setO4 (String s) { option4 = s;}
	public void setAnswer (int a) { answer = a;} 
	
	public Question (int k,String q, String s1, String s2, String s3, String s4, int a) {
		key = k;
		question = q;
		option1 = s1;
		option2 = s2;
		option3 = s3;
		option4 = s4;
		answer = a;
	}
}
