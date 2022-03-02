#ifndef BACKGROUND_H
#define BACKGROUND_H
#include "Component.h"

namespace cwing {
	class Background : public Component
	{
	public:
		static Background* getInstance(int x, int y);
		void draw() const;
		void tick();
		~Background();
	private:
		SDL_Texture* texture;
		Background(int x, int y);
		const int X_COORDINATE = 0;
		const int Y_COORDINATE = 0;
	};
}
#endif

