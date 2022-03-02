#ifndef PLAYER_H
#define PLAYER_H
#include "Component.h"

namespace cwing {
	class Player : public Component
	{
	public:
		static const int PLAYER_WIDTH = 100;
		static const int PLAYER_HEIGHT = 100;
		static Player* getInstance(int x, int y);
		static const int PLAYER_VELOCITY = 5;
		void keyDown(const SDL_Event&);
		void perform(SDL_KeyboardEvent);
		void draw() const;
		void setRectX(int x);
		void setRectY(int y);
		void tick();
		~Player();
	private:
		SDL_Texture* texture;
		Player(int x, int y);
	};
}
#endif

