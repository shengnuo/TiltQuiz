/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.thegame;

import java.util.ArrayList;

/**
 *
 * @author slin
 */
public class HashTable {
    private static final int BUCKET_SIZE = 6;
    private ArrayList<Question>[] table = new ArrayList[BUCKET_SIZE];
    
    HashTable() {
        for (int i=0; i<BUCKET_SIZE; i++)
            table[i]=new ArrayList();
    }
    
    private int getIndex(int key) {
        return (key % table.length);
    }
    
    void add(Question e) {
        table[getIndex(e.getKey())].add(e);
    }
    
    void add(int key, Question e) {
        table[getIndex(key)].add(e);
    }
    
    int search(int key) {
        for (int i=0; i<table[getIndex(key)].size();i++) {
            if (key==table[getIndex(key)].get(i).getKey())
                return(i);
        }
        return -1;
    }
    
    Question get (int key) {
        if (search(key)!=-1)
            return (table[getIndex(key)].get(search(key)));
        else
        	if (key<20){
        		get (key+1);
        	} 
        	else
        		for (int i = 1; i < 21; i ++) {
        			if (search (i) != -1) {
        				get (i);
        			}
        		}
        		
        return null;
    }
    
    void remove(int key) {
        if(search(key)>0)
            table[getIndex(key)].remove(search(key));
    }
    
    void replace(int key, Question newEmp) {
        try {
            table[getIndex(key)].set(search(key), newEmp);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(getIndex(key)+" "+search(key));
        }
    }  
    
}