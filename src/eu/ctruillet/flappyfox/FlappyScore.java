/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package eu.ctruillet.flappyfox;

import processing.core.PApplet;
import processing.core.PConstants;

public class FlappyScore {
	//Attributs
	public PApplet sketch;
	public float score = 0;
	public float highScore = 0;

	//Constructeur

	public FlappyScore(PApplet sketch) {
		this.sketch = sketch;
	}


	//Méthodes
	public void draw(){
		sketch.textAlign(PConstants.RIGHT);
		sketch.text("Score:" + String.format("%03d", (int)score), sketch.width-10, 25);
		sketch.text("Hight Score:" + String.format("%03d", (int)highScore), sketch.width-10, 40);
	}

	public void update(){
		//12s -> 100pts
		//55 fps = 1s
		//1s -> 100/12pts
		score+=100./(12.*sketch.frameRate);

		if(this.score > this.highScore)
			this.highScore = this.score;
	}

	public float getScore() {
		return score;
	}

	public float getHighScore() {
		return highScore;
	}

	public void reset() {
		this.score = 0;
	}
}
