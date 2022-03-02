#include "Button.h"
#include <SDL_ttf.h> 
#include "System.h"
#include <SDL_image.h>
namespace cwing {

	Button::Button(int x, int y, int w, int h, std::string text): Component(x,y,w,h) {
		SDL_Surface* surf = TTF_RenderText_Solid(sys.get_font(), text.c_str(), { 0, 0, 0 });
		texture = SDL_CreateTextureFromSurface(sys.get_ren(), surf);
		SDL_FreeSurface(surf);
		upIcon = IMG_LoadTexture(sys.get_ren(), "c:/Users/nikol/Pictures/UppKnapp.png");
		downIcon = IMG_LoadTexture(sys.get_ren(), "c:/Users/nikol/Pictures/NedKnapp.png");
	}

	Button::~Button() {
		SDL_DestroyTexture(texture);
		SDL_DestroyTexture(upIcon);
		SDL_DestroyTexture(downIcon);
	}

	Button* Button::getInstance(int x, int y, int w, int h, std::string text) {
		return new Button(x, y, w, h, text);
	}
	void Button::mouseDown(const SDL_Event& eve) {
		SDL_Point p = { eve.button.x, eve.button.y };
		if (SDL_PointInRect(&p, &getRect())) {
			isDown = true;
		}
	}
	void Button::mouseUp(const SDL_Event& eve) {
		SDL_Point p = { eve.button.x, eve.button.y };
		if (SDL_PointInRect(&p, &getRect())) {
			perform(this);
		}
		isDown = false;
	}
	void Button::draw() const {
		if (isDown) {
			SDL_RenderCopy(sys.get_ren(), downIcon, NULL, &getRect());
		}
		else {
			SDL_RenderCopy(sys.get_ren(), upIcon, NULL, &getRect());
		}
		SDL_RenderCopy(sys.get_ren(), texture, NULL, &getRect());
	}

	void Button::tick() {

	}

}