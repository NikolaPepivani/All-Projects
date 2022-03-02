#ifndef ENEMY_H
#define ENEMY_H
#include "Component.h"

namespace cwing {
	class Enemy : public Component
	{
	public:
		static Enemy* getInstance(int x, int y, int w, int h);
		void draw() const;
		void setRectX(int x);
		void setRectY(int y);
		void tick();
		~Enemy();
	private:
		SDL_Texture* texture;
		Enemy(int x, int y, int w, int h);
		int counter = 0;
	};
}
#endif
