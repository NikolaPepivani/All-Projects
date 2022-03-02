#include <SDL.h>
#include "Label.h"
#include "Session.h"
#include "Button.h"
#include <string>
#include "Player.h"
#include "Bullet.h"
#include <iostream>
#include "Enemy.h"
#include "System.h"
#include <SDL_image.h>
#include "Background.h"
using namespace std;
using namespace cwing;

int value = 0;

//class OkaKnapp : public Button {
//public:
//	OkaKnapp(Label* lbl) :Button(100, 100, 200, 70, "Öka"), label(lbl) {}
//	void perform(Button* source) {
//		value++;
//		label->setText(to_string(value));
//	}
//private:
//	Label* label; 
//};
//
//class MinskaKnapp : public Button {
//public:
//	MinskaKnapp(Label* lbl) :Button(500, 100, 200, 70, "Minska"), label(lbl) {}
//	void perform(Button* source) {
//		value--;
//		label->setText(to_string(value));
//	}
//private:
//	Label* label;
//};

int main(int argc, char** argv) {
	Background* background = Background::getInstance(0, 0);
	ses.add(background);
	Player* player = Player::getInstance(350, 450);
	ses.add(player);
	Label* lbl = Label::getInstance(300, 100, 200, 70, "0");
	ses.add(lbl);
	Enemy* enemy = Enemy::getInstance(100, 100, 100, 100);
	ses.add(enemy);
	ses.run();

	return 0;
}