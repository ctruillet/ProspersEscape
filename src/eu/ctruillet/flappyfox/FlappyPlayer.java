/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package eu.ctruillet.flappyfox;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class FlappyPlayer {
	//Attributs
	PApplet sketch;
	int y;
	PImage image;
	PImage[] walk;
	PImage jump;
	double gravity = 0.98;
	double velocity = 0 ;
	double lift = -15;

	double floor;
	int i = 0;


	int radius = 48;


	//Constructeur

	public FlappyPlayer(PApplet sketch) {
		this.sketch = sketch;
		this.floor = sketch.height - 3* this.radius;
		this.y = (int) this.floor;
		this.image = sketch.loadImage("./ressources/FlappyFox_player.png");

		this.walk = new PImage[2];
		this.walk[0] = sketch.loadImage("./ressources/FlappyFox_player_walk1.png");
		this.walk[1] = sketch.loadImage("./ressources/FlappyFox_player_walk2.png");

		this.jump = this.image;
	}



	//Méthodes
	public void draw(){
		sketch.ellipseMode(PConstants.CORNER);
//		sketch.fill(255,255,255, (float) 0.1);
//		sketch.stroke(240);
//		sketch.circle(50,y,this.radius);

		sketch.image(this.image,50,y);
	}

	public void update(){
		this.velocity += this.gravity;
		this.y += this.velocity;

		if (this.y > this.floor) {
			this.y = (int) this.floor;
			this.velocity = 0;
		}

		if (this.y < 0) {
			this.y = 0 ;
			this.velocity = 0;
		}

		if(sketch.frameCount % 6 == 0){
			i = (i+1)%2;
			this.image = this.walk[i];
		}

		if(this.isJumping()){
			this.image = this.jump;
		}

	}

	public void jump(){
		if(this.y == this.floor)
			this.velocity += this.lift;
	}

	public boolean isJumping(){
		return !(this.y == this.floor);
	}

	public int getRadius() {
		return this.radius;
	}

	public boolean isHit(FlappyObstacle flappyObstacle){
		float cx = (float) (50 + this.radius/2.);
		float cy = (float) (this.y + this.radius/2.);
		float rx = flappyObstacle.getX();
		float ry = flappyObstacle.getY();
		float rw = flappyObstacle.getWidth();
		float rh = flappyObstacle.getWidth();
		// temporary variables to set edges for testing
		float testX = cx;
		float testY = cy;

		// which edge is closest?
		if (cx < rx)         testX = rx;      // test left edge
		else if (cx > rx+rw) testX = rx+rw;   // right edge
		if (cy < ry)         testY = ry;      // top edge
		else if (cy > ry+rh) testY = ry+rh;   // bottom edge

		// get distance from closest edges
		float distX = cx-testX;
		float distY = cy-testY;
		float distance = (float) Math.sqrt((distX*distX) + (distY*distY) );

		// if the distance is less than the radius, collision!
		if (distance <= this.radius/2.0) {
			return true;
		}
		return false;
	}

	public void reset() {
		this.y = (int) this.floor;
		this.velocity = 0;
	}
}
