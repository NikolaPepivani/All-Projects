#include "Player.h"
#include "System.h"
#include <SDL_image.h>
#include <iostream>
#include "Bullet.h"
#include "Session.h"

namespace cwing {
	
	Player::Player(int x, int y): Component(x, y, PLAYER_WIDTH, PLAYER_HEIGHT) {
		SDL_Surface* surf = IMG_Load("C:/Users/nikol/Pictures/gubbe.png");
		texture = SDL_CreateTextureFromSurface(sys.get_ren(), surf);
		SDL_FreeSurface(surf);
	}

	Player* Player::getInstance(int x, int y) {
		return new Player(x, y);
	}

	void Player::keyDown(const SDL_Event& eve) {
			perform(eve.key);
	}

	void Player::setRectX(int x) {
		rect.x += x;
	}

	void Player::setRectY(int y) {
		rect.y += y;
	}

	void Player::perform(SDL_KeyboardEvent key) {

		switch (key.keysym.sym) {
		case SDLK_UP: this->setRectY(-PLAYER_VELOCITY); break;
		case SDLK_DOWN: this->setRectY(PLAYER_VELOCITY); break;
		case SDLK_LEFT: this->setRectX(-PLAYER_VELOCITY); break;
		case SDLK_RIGHT: this->setRectX(PLAYER_VELOCITY); break;
		case SDLK_SPACE: Bullet* bullet = Bullet::getInstance(this->getRect().x+bullet->BULLET_WIDTH/2, this->getRect().y);
			ses.add(bullet);
			break;
		}
	}

	void Player::draw() const {
		SDL_RenderCopy(sys.get_ren(), texture, NULL, &getRect());
	}

	Player::~Player() {
		SDL_DestroyTexture(texture);
	}

	void Player::tick() {

	}

}