#include <iostream>
#include <math.h>

using namespace std;

int main() {

  char choice;
  float volume;

  cout << "Choose one volume figure: " << endl
       << "[C] - Cube" << endl
       << "[R] - Rectangular prism" << endl
       << "[S] - Sphere" << endl;

  cin >> choice;

  switch (tolower(choice)) {
    case 'c':
      float side;
      cout << "Input the side of the cube: ";
      cin >> side;
      volume = pow(side, 3);
      cout << "The volume of the cube is: " << volume << endl;

      break;
    case 'r':
      float length, width, height;
      cout << "Input the length of the rectangular prism: ";
      cin >> length;
      cout << "Input the width of the rectangular prism: ";
      cin >> width;
      cout << "Input the height of the rectangular prism: ";
      cin >> height;
      volume = length * width * height;
      cout << "The volume of the rectangular prism is: " << volume << endl;
      
      break;
    case 's':
      float radius;
      cout << "Input the radius of the sphere: ";
      cin >> radius;
      volume = (4 * M_PI * pow(radius, 3)) / 3;
      cout << "The volume of the sphere is: " << volume << endl;
      
      break;
    default:
      cout << "Wrong choice!" << endl;
  }

  return 0;
}