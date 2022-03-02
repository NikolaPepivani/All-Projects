#include "Session.h" 
#include <SDL.h>
#include "System.h"
#define FPS 60

namespace cwing {

	void Session::add(Component* comp) {
		added.push_back(comp);
	}

	void Session::remove(Component* comp) {
		removed.push_back(comp);
	}

	void Session::run() {
		bool quit = false;
		while (!quit) {
			SDL_Event eve;
			while (SDL_PollEvent(&eve)) {
				switch (eve.type) {
				case SDL_QUIT: quit = true;
					break;
				case SDL_MOUSEBUTTONDOWN:
					for (Component* comp : comps) {
						comp->mouseDown(eve);
					}
					break;
				case SDL_MOUSEBUTTONUP:
					for (Component* comp : comps) {
						comp->mouseUp(eve);
					}
					break;
				case SDL_KEYDOWN:
					for (Component* comp : comps) {
						comp->keyDown(eve);
					}
					break;
				case SDL_KEYUP:
					for (Component* comp : comps) {
						comp->keyUp(eve);
					}
					break;
				} // switch
			} // inner while

			for (Component* comp : comps) {
				comp->tick();
			}

			for (Component* comp : added) {
				comps.push_back(comp);
			}
			added.clear();

			for (Component* comp : removed) {
				for (std::vector<Component*>::iterator i = comps.begin(); i != comps.end();) {
					if (*i == comp) {
						i = comps.erase(i);
					}
					else {
						i++;
					}
				}
			}

			SDL_SetRenderDrawColor(sys.get_ren(), 255, 255, 255, 255);
			SDL_RenderClear(sys.get_ren());
			for (Component* comp : comps) {
				comp->draw();
			}
			SDL_RenderPresent(sys.get_ren());

			if (delay > 0) {
				SDL_Delay(delay);
			}

		} // outer while
	} 
	Session::~Session() {

	}
	Session ses;
}