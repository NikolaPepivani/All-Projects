#ifndef SESSION_H
#define SESSION_H
#include <vector>
#include "Component.h"
namespace cwing {
	class Session
	{
	public:
		void add(Component* comp);
		void remove(Component* comp);
		void run();
		~Session();
	private:
		std::vector<Component*> comps;
		std::vector<Component*> added, removed;
		const int tickInterval = 1000 / 60;
		Uint32 nextTick = SDL_GetTicks() + tickInterval;
		int delay = nextTick - SDL_GetTicks();
	};
	extern Session ses;
}
#endif
