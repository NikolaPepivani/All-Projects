#ifndef LABEL_H
#define LABEL_H
#include "Component.h"
#include <string>
#include "SDL.h"
namespace cwing {

	class Label : public Component
	{
	public:
		static Label* getInstance(int x, int y, int w, int h, std::string txt);
		void draw() const;
		~Label();
		std::string getText() const;
		void setText(std::string newText);
		void tick();
	protected:
		Label(int x, int y, int w, int h, std::string txt);
	private:
		std::string text;
		SDL_Texture* texture;
	};

}
#endif
