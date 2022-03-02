#include "Enemy.h"
#include "System.h"
#include <SDL_image.h>
#include "Bullet.h"
#include "Session.h"

namespace cwing {

	Enemy::Enemy(int x, int y, int w, int h) : Component(x, y, w, h) {
		SDL_Surface* surf = IMG_Load("C:/Users/nikol/Pictures/enemy.png");
		texture = SDL_CreateTextureFromSurface(sys.get_ren(), surf);
		SDL_FreeSurface(surf);
	}

	Enemy* Enemy::getInstance(int x, int y, int w, int h) {
		return new Enemy(x, y, w, h);
	}

	void Enemy::setRectX(int x) {
		rect.x += x;
	}

	void Enemy::setRectY(int y) {
		rect.y += y;
	}

	void Enemy::draw() const {
		SDL_RenderCopy(sys.get_ren(), texture, NULL, &getRect());
	}

	Enemy::~Enemy() {
		SDL_DestroyTexture(texture);
	}

	void Enemy::tick() {
		if (getRect().y > 600) {
			ses.remove(this);
		}
		else if (counter % 10 == 0) {
			setRectY(1);
		}

	}

}