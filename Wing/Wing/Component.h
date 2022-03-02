#ifndef COMPONENT_H
#define COMPONENT_H
#include <SDL.h>
namespace cwing {
	class Component
	{
	public:
		virtual ~Component();
		virtual void draw() const = 0;
		virtual void mouseDown(const SDL_Event&) {};
		virtual void mouseUp(const SDL_Event&) {};
		virtual void keyDown(const SDL_Event&) {};
		virtual void keyUp(const SDL_Event&) {};
		const SDL_Rect& getRect() const {
			return rect; 
		}
		virtual void tick() = 0;
	protected:
		Component(int x, int y, int w, int h);
		SDL_Rect rect;
	private:
		Component(const Component&) = delete;
		const Component& operator=(const Component&) = delete;
	};
}
#endif

