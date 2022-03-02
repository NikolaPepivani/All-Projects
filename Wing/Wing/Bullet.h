#ifndef BULLET_H
#define BULLET_H
#include "Component.h"
#include "SDL.h"

namespace cwing {
	class Bullet : public Component {
	public:
		static const int BULLET_WIDTH = 40;
		static const int BULLET_HEIGHT = 40;
		static Bullet* getInstance(int x, int y);
		void draw() const;
		~Bullet();
		void tick();
	private:
		Bullet(int x, int y);
		SDL_Texture* texture;
		int counter = 0;
	};
}
#endif
