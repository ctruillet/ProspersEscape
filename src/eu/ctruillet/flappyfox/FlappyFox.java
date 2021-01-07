/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package eu.ctruillet.flappyfox;

import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;

public class FlappyFox {
	//Attributs
	PApplet sketch;
	FlappyPlayer flappyPlayer;
	FlappyFloor flappyFloor;
	ArrayList<FlappyObstacle> flappyObstacles = new ArrayList<>();
	FlappyScore flappyScore;
	boolean isPlay = false;
	boolean isGameOver = false;
	boolean isWin = false;
	double score = 0;

	//Constructeur

	public FlappyFox(PApplet sketch) {
		this.sketch = sketch;
		flappyPlayer = new FlappyPlayer(sketch);
		flappyFloor = new FlappyFloor(sketch, sketch.height - 2*flappyPlayer.getRadius());
		flappyScore = new FlappyScore(sketch);
	}


	//Méthodes
	public void draw(){

		sketch.background(0);


		flappyPlayer.draw();


		flappyFloor.draw();

		for (FlappyObstacle flappyObstacle : flappyObstacles) {
			flappyObstacle.draw();
		}

		flappyScore.draw();

		sketch.fill(240);
		sketch.textAlign(PConstants.LEFT);
		sketch.textSize(32);
		if(isPlay){
			if (isWin) {
				sketch.text("YOU WIN", 50, flappyPlayer.getRadius());
			}else{

			}
		}else{
			if(isGameOver){
				if (isWin) {
					sketch.text("YOU WIN", 50, flappyPlayer.getRadius());
				}else{
					sketch.text("GAME OVER",50,flappyPlayer.getRadius());
				}
				sketch.fill(0);
				sketch.textSize(18);
				sketch.text("Press ESCAPE to escape ce jeu de mort",50,sketch.height - flappyPlayer.getRadius());
			}else{
				sketch.text("PRESS SPACE TO PLAY",50,flappyPlayer.getRadius());
				sketch.fill(0);
				sketch.textSize(18);
				sketch.text("Aide Prosper à fuir l'UPSSITECH !w",50,sketch.height - flappyPlayer.getRadius());
			}
		}

		sketch.textSize(14);



	}

	public void update(){
		if(this.isPlay) {
			flappyPlayer.update();
			flappyScore.update();

//			if(flappyScore.getScore() >= 1000){
//				isWin = true;
//			}

			//Suppression des obstacle pour la survie du jeu
			for (int i = 0; i < flappyObstacles.size(); i++) {
				if (flappyObstacles.get(i).isOfScreen()) {
					this.flappyObstacles.remove(flappyObstacles.get(i));
				} else {
					flappyObstacles.get(i).update();
				}
			}

			//Génération des obstacles
			if (flappyScore.getScore() > 15 && (flappyObstacles.size() == 0 || flappyObstacles.get(flappyObstacles.size() - 1).x < 3 * sketch.width / 8)) {
				switch (flappyObstacles.size()) {
					case 0:
						if (Math.random() > 0.80)
							this.flappyObstacles.add(new FlappyObstacle(this.sketch, this.flappyFloor,this.flappyScore));
						break;

					case 1:
						if (Math.random() > 0.95)
							this.flappyObstacles.add(new FlappyObstacle(this.sketch, this.flappyFloor,this.flappyScore));
						break;

					default:
						if (Math.random() > 0.999)
							this.flappyObstacles.add(new FlappyObstacle(this.sketch, this.flappyFloor,this.flappyScore));
						break;
				}
			}

			//System.out.println(sketch.frameRate);

			//Controle de la collision
			for (FlappyObstacle flappyObstacle : flappyObstacles) {
				if (this.flappyPlayer.isHit(flappyObstacle)) {
					this.isPlay = false;
					this.isGameOver = true;
				}
			}
		}
	}

	public void jump(){
		flappyPlayer.jump();
	}

	public boolean isPlay() {
		return isPlay;
	}

	public void play(){
		this.isPlay = true;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void reset(){
		flappyPlayer.reset();
		flappyScore.reset();
		flappyObstacles.clear();
		this.isGameOver = false;
	}
}
