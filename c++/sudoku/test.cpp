#include <iostream>

using namespace std;

int main()
{
  int population = 170000, years;
  while (population < 1000000) {
    population += (population * 0.0263);
    cout << population << endl;
    years++;
  }
  cout << "The population will reach 1 million in " << years << " years." << endl;
  return 0;
}

// Population: 174471
// Population: 179059
// Population: 183768
// Population: 188601
// Population: 193561
// Population: 198651
// Population: 203875
// Population: 209236
// Population: 214738
// Population: 220385
// Population: 226181
// Population: 232129
// Population: 238233
// Population: 244498
// Population: 250928
// Population: 257527
// Population: 264299
// Population: 271250
// Population: 278383
// Population: 285704
// Population: 293218
// Population: 300929
// Population: 308843
// Population: 316965
// Population: 325301
// Population: 333856
// Population: 342636
// Population: 351647
// Population: 360895
// Population: 370386
// Population: 380127
// Population: 390124
// Population: 400384
// Population: 410914
// Population: 421721
// Population: 432812
// Population: 444194
// Population: 455876
// Population: 467865
// Population: 480169
// Population: 492797
// Population: 505757
// Population: 519058
// Population: 532709
// Population: 546719
// Population: 561097
// Population: 575853
// Population: 590997
// Population: 606540
// Population: 622492
// Population: 638863
// Population: 655665
// Population: 672908
// Population: 690605
// Population: 708767
// Population: 727407
// Population: 746537
// Population: 766170
// Population: 786320
// Population: 807000
// Population: 828224
// Population: 850006
// Population: 872361
// Population: 895304
// Population: 918850
// Population: 943015
// Population: 967816
// Population: 993269
// Population: 1019391