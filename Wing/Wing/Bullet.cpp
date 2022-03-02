#include "Bullet.h"
#include "System.h"
#include "SDL_image.h"
#include "Session.h"

namespace cwing {
	Bullet::Bullet(int x, int y) : Component(x, y, BULLET_HEIGHT, BULLET_WIDTH) {
		SDL_Surface* surf = IMG_Load("C:/Users/nikol/Pictures/shot.jpg");
		texture = SDL_CreateTextureFromSurface(sys.get_ren(), surf);
		SDL_FreeSurface(surf);
	}

	void Bullet::tick() {
		if (rect.y <= 0) {
			ses.remove(this);
		}
		else if (counter % 10 == 0) {
			rect.y-=3;
		}
	}

	Bullet::~Bullet() {
		SDL_DestroyTexture(texture);
	}

	void Bullet::draw() const {
		SDL_RenderCopy(sys.get_ren(), texture, NULL, &getRect());
	}

	Bullet* Bullet::getInstance(int x, int y) {
		return new Bullet(x, y);
	}
}
