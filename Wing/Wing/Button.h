#ifndef BUTTON_H
#define BUTTON_H
#include "Component.h"
#include <string>
#include <SDL.h>

namespace cwing {

	class Button : public Component
	{
	public: 
		static Button* getInstance(int x, int y, int w, int h, std::string text);
		void mouseDown(const SDL_Event&);
		void mouseUp(const SDL_Event&);
		void draw() const;
		virtual void perform(Button* source) { }
		void tick();
		~Button();
	protected:
		Button(int x, int y, int w, int h, std::string text);
	private:
		std::string text;
		SDL_Texture* texture;
		SDL_Texture *upIcon, *downIcon;
		bool isDown = false;
	};
}
#endif

