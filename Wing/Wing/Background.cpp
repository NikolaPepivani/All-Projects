#include "Background.h"
#include <SDL_image.h>
#include "System.h"

namespace cwing {
	Background::Background(int x, int y) : Component(x, y, 800, 600) {
		SDL_Surface* surf = IMG_Load("C:/Users/nikol/Pictures/Hell.jpg");
		texture = SDL_CreateTextureFromSurface(sys.get_ren(), surf);
		SDL_FreeSurface(surf);
	}

	void Background::draw() const {
		SDL_RenderCopy(sys.get_ren(), texture, NULL, &getRect());
	}

	Background* Background::getInstance(int x, int y) {
		return new Background(x, y);
	}

	void Background::tick() {

	}

	Background::~Background() {
		SDL_DestroyTexture(texture);
	}

}
