#ifndef SYSTEM_H
#define SYSTEM_H
#include <SDL.h>
#include <SDL_ttf.h>
namespace cwing {

	class System
	{
	public:
		static const int WINDOW_WIDTH = 800;
		static const int WINDOW_HEIGHT = 600;
		System();
		~System();
		SDL_Renderer* get_ren() const;
		TTF_Font* get_font() const;
	private:
		SDL_Window* win;
		SDL_Renderer* ren;
		TTF_Font* font;
	};

	extern System sys;	
}
#endif
