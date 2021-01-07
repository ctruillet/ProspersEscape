package eu.ctruillet.flappyfox;/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */


import processing.core.PApplet;
import processing.event.KeyEvent;

public class Main extends PApplet {
	//Attributs
	public static PApplet processing;
	public FlappyFox flappyFox;


	//Constructeur

	//Méthodes
	public static void main(String[] args) {
		PApplet.main("eu.ctruillet.flappyfox.Main", args);
	}

	public void settings() {
		size(640, 483);
	}

	public void setup() {
		processing = this;
		surface.setTitle("Prosper's Escape");
		surface.setIcon(loadImage("./ressources/logo.png"));

		flappyFox = new FlappyFox(processing);


		textFont(createFont("./ressources/DejaVuSansMono.ttf", 14));

	}

	public void draw() {
		flappyFox.update();
		flappyFox.draw();
	}

	public void keyPressed(KeyEvent event) {
		if (event.getKey() == ' ') {
			if (flappyFox.isGameOver()) {
				flappyFox.reset();
				flappyFox.play();
			} else {
				if (!flappyFox.isPlay()) {
					flappyFox.play();
				}
			}
			flappyFox.jump();
		}
	}
}
