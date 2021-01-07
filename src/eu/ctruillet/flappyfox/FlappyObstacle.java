/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package eu.ctruillet.flappyfox;

import processing.core.PApplet;

public class FlappyObstacle {
	//Attributs
	protected PApplet sketch;
	protected int x;
	protected int y;
	protected int width = 30;
	protected FlappyFloor flappyFloor;
	protected float velocity = -10;
	protected FlappyScore flappyScore;

	//Constructeur

	public FlappyObstacle(PApplet sketch,FlappyFloor flappyFloor,FlappyScore flappyScore) {
		this.sketch = sketch;
		this.x = sketch.width;
		this.flappyFloor = flappyFloor;
		this.y = this.flappyFloor.getY()-this.width;
		this.flappyScore = flappyScore;

	}


	//Méthodes
	public void draw(){
		sketch.fill(255);
		sketch.square(x,this.y,this.width);
	}

	public void update(){
		this.velocity = (float) ((-17 + 10)/1000. * this.flappyScore.getScore() + -10);
		this.x += this.velocity;
		//Score   : 0 --> 1000
		//Vitesse : -10 --> -20
		//Function: VItesse = ax + b
	}

	public boolean isOfScreen(){
		return (this.x + this.width) < 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}
}
