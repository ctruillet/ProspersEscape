/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package eu.ctruillet.flappyfox;

import processing.core.PApplet;

public class FlappyFloor {
	//Attributs
	protected PApplet sketch;
	protected int y;


	//Constructeur

	public FlappyFloor(PApplet sketch, int y) {
		this.sketch = sketch;
		this.y = y;
	}


	//Méthodes
	public void draw(){
		sketch.fill(240);
		sketch.noStroke();
		sketch.rect(0, y, sketch.width, sketch.width);


	}

	public void update(){


	}

	public int getY() {
		return y;
	}
}
