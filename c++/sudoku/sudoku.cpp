#include <iostream>
#include <algorithm>
#include <ctime>
#include <fstream>
#include <sstream>
#include <string>
#include <vector>

#define UNASSIGNED 0

using namespace std;

class Sudoku
{
private:
  int guessNum[9];
  int gridPos[81];
  int difficultyLevel;
  bool grid_status;

public:
  Sudoku();
  Sudoku(string, bool row_major = true);
  int solution[9][9];
  int grid[9][9];
  int solnGrid[9][9];
  void fillEmptyDiagonalBox(int);
  void createSeed();
  void printGrid(int grid[9][9]);
  bool solveGrid();
  string setGrid();
  void countSoln(int &number);
  void genPuzzle();
  bool verifyGridStatus();
  void printSVG(string);
  void calculateDifficulty();
  int branchDifficultyScore();
  bool checkSolution();
  void insertNumber(int x, int y, int num);
  void removeNumber(int x, int y);
};

string Sudoku::setGrid()
{
  string s = "";
  for (int row_num = 0; row_num < 9; ++row_num)
  {
    for (int col_num = 0; col_num < 9; ++col_num)
    {
      s = s + to_string(grid[row_num][col_num]);
    }
  }

  return s;
}


int genRandNum(int maxLimit)
{
  return rand() % maxLimit;
}

bool FindUnassignedLocation(int grid[9][9], int &row, int &col)
{
  for (row = 0; row < 9; row++)
  {
    for (col = 0; col < 9; col++)
    {
      if (grid[row][col] == UNASSIGNED)
        return true;
    }
  }

  return false;
}

bool UsedInRow(int grid[9][9], int row, int num)
{
  for (int col = 0; col < 9; col++)
  {
    if (grid[row][col] == num)
      return true;
  }

  return false;
}

bool UsedInCol(int grid[9][9], int col, int num)
{
  for (int row = 0; row < 9; row++)
  {
    if (grid[row][col] == num)
      return true;
  }

  return false;
}

bool UsedInBox(int grid[9][9], int boxStartRow, int boxStartCol, int num)
{
  for (int row = 0; row < 3; row++)
  {
    for (int col = 0; col < 3; col++)
    {
      if (grid[row + boxStartRow][col + boxStartCol] == num)
        return true;
    }
  }

  return false;
}

bool isSafe(int grid[9][9], int row, int col, int num)
{
  return !UsedInRow(grid, row, num) && !UsedInCol(grid, col, num) && !UsedInBox(grid, row - row % 3, col - col % 3, num);
}

void Sudoku::fillEmptyDiagonalBox(int idx)
{
  int start = idx * 3;
  random_shuffle(this->guessNum, (this->guessNum) + 9, genRandNum);
  for (int i = 0; i < 3; ++i)
  {
    for (int j = 0; j < 3; ++j)
    {
      this->grid[start + i][start + j] = guessNum[i * 3 + j];
    }
  }
}

void Sudoku::createSeed()
{
  cout << "Creating seed..." << endl;
  /* Fill diagonal boxes to form:
      x | . | .
      . | x | .
      . | . | x
  */

  this->fillEmptyDiagonalBox(0);
  this->fillEmptyDiagonalBox(1);
  this->fillEmptyDiagonalBox(2);

  /* Fill the remaining blocks:
      x | x | x
      x | x | x
      x | x | x
  */
  cout << "Creating solution..." << endl;
  this->solveGrid(); 
  cout << "Seed created." << endl;
  // Saving the solution grid
  for (int i = 0; i < 9; i++)
  {
    for (int j = 0; j < 9; j++)
    {
      this->solnGrid[i][j] = this->grid[i][j];
    }
  }
}

Sudoku::Sudoku()
{

  // initialize difficulty level
  this->difficultyLevel = 0;

  // Randomly shuffling the array of removing grid positions
  for (int i = 0; i < 81; i++)
  {
    this->gridPos[i] = i;
  }

  random_shuffle(this->gridPos, (this->gridPos) + 81, genRandNum);

  // Randomly shuffling the guessing number array
  for (int i = 0; i < 9; i++)
  {
    this->guessNum[i] = i + 1;
  }

  random_shuffle(this->guessNum, (this->guessNum) + 9, genRandNum);

  // Initialising the grid
  for (int i = 0; i < 9; i++)
  {
    for (int j = 0; j < 9; j++)
    {
      this->grid[i][j] = 0;
    }
  }

  grid_status = true;
}

Sudoku::Sudoku(string grid_str, bool row_major)
{
  if (grid_str.length() != 81)
  {
    grid_status = false;
    return;
  }

  // First pass: Check if all cells are valid
  for (int i = 0; i < 81; ++i)
  {
    int curr_num = grid_str[i] - '0';
    if (!((curr_num == UNASSIGNED) || (curr_num > 0 && curr_num < 10)))
    {
      grid_status = false;
      return;
    }

    if (row_major)
      grid[i / 9][i % 9] = curr_num;
    else
      grid[i % 9][i / 9] = curr_num;
  }

  // Second pass: Check if all columns are valid
  for (int col_num = 0; col_num < 9; ++col_num)
  {
    bool nums[10] = {false};
    for (int row_num = 0; row_num < 9; ++row_num)
    {
      int curr_num = grid[row_num][col_num];
      if (curr_num != UNASSIGNED && nums[curr_num] == true)
      {
        grid_status = false;
        return;
      }
      nums[curr_num] = true;
    }
  }

  // Third pass: Check if all rows are valid
  for (int row_num = 0; row_num < 9; ++row_num)
  {
    bool nums[10] = {false};
    for (int col_num = 0; col_num < 9; ++col_num)
    {
      int curr_num = grid[row_num][col_num];
      if (curr_num != UNASSIGNED && nums[curr_num] == true)
      {
        grid_status = false;
        return;
      }
      nums[curr_num] = true;
    }
  }

  // Fourth pass: Check if all blocks are valid
  for (int block_num = 0; block_num < 9; ++block_num)
  {
    bool nums[10] = {false};
    for (int cell_num = 0; cell_num < 9; ++cell_num)
    {
      int curr_num = grid[((int)(block_num / 3)) * 3 + (cell_num / 3)][((int)(block_num % 3)) * 3 + (cell_num % 3)];
      if (curr_num != UNASSIGNED && nums[curr_num] == true)
      {
        grid_status = false;
        return;
      }
      nums[curr_num] = true;
    }
  }

  // Randomly shuffling the guessing number array
  for (int i = 0; i < 9; i++)
  {
    this->guessNum[i] = i + 1;
  }

  random_shuffle(this->guessNum, (this->guessNum) + 9, genRandNum);

  grid_status = true;
}

bool Sudoku::verifyGridStatus()
{
  return grid_status;
}

void Sudoku::printGrid(int grid[9][9])

//-------------- Sudoku Puzzle --------------
//
//            0 1 2   3 4 5   6 7 8
//          #########################
//        0 # . . . | . . . | . . . #
//        1 # . . . | . . . | . . . #
//        2 # . . . | . . . | . . . #
//          #########################
//        3 # . . . | . . . | . . . #
//        4 # . . . | . . . | . . . #
//        5 # . . . | . . . | . . . #
//          #########################
//        6 # . . . | . . . | . . . #
//        7 # . . . | . . . | . . . #
//        8 # . . . | . . . | . . . #
//          #########################

{
  cout << "            0 1 2   3 4 5   6 7 8" << endl;
  for (int i = 0; i < 9; i++)
  {
    if (i % 3 == 0)
    {
      cout << "          #########################" << endl;
    }
    for (int j = 0; j < 9; j++)
    {
      if (j % 3 == 0)
      {
        if (j == 0)
        {
          cout << "        " << i << " # ";
        }
        else
        {
          cout << "# ";
        }
      }
      if (grid[i][j] == 0)
      {
        cout << ". ";
      }
      else
      {
        cout << grid[i][j] << " ";
      }
    }
    cout << "#" << endl;
  }
  cout << "          #########################" << endl;
}

bool Sudoku::solveGrid()
{

  int row, col;

  // If there is no unassigned location, we are done
  if (!FindUnassignedLocation(this->grid, row, col)) 
    return true; // success!
  

  // Consider digits 1 to 9
  for (int num = 0; num < 9; num++)
  {
    // if looks promising
    if (isSafe(this->grid, row, col, this->guessNum[num]))
    {
      // make tentative assignment
      this->grid[row][col] = this->guessNum[num];

      // return, if success, yay!
      if (solveGrid())
        return true;

      // failure, unmake & try again
      this->grid[row][col] = UNASSIGNED;
    }
  }
  return false; // this triggers backtracking
}


void Sudoku::countSoln(int &number)
{
  int row, col;

  if (!FindUnassignedLocation(this->grid, row, col))
  {
    number++;
    return;
  }

  for (int i = 0; i < 9 && number < 2; i++)
  {
    if (isSafe(this->grid, row, col, this->guessNum[i]))
    {
      this->grid[row][col] = this->guessNum[i];
      countSoln(number);
    }

    this->grid[row][col] = UNASSIGNED;
  }
}

void Sudoku::genPuzzle()
{
  for (int i = 0; i < 81; i++)
  {
    int x = (this->gridPos[i]) / 9;
    int y = (this->gridPos[i]) % 9;
    int temp = this->grid[x][y];
    this->grid[x][y] = UNASSIGNED;

    // If now more than 1 solution , replace the removed cell back.
    int check = 0;
    countSoln(check);
    if (check != 1)
    {
      this->grid[x][y] = temp;
    }
  }

  // Copy the generated puzzle to solution grid
  for (int i = 0; i < 9; i++)
  {
    for (int j = 0; j < 9; j++)
    {
      this->solution[i][j] = this->grid[i][j];
    }
  }
}

int Sudoku::branchDifficultyScore()
{
  int emptyPositions = -1;
  int tempGrid[9][9];
  int sum = 0;

  for (int i = 0; i < 9; i++)
  {
    for (int j = 0; j < 9; j++)
    {
      tempGrid[i][j] = this->grid[i][j];
    }
  }

  while (emptyPositions != 0)
  {
    vector<vector<int>> empty;

    for (int i = 0; i < 81; i++)
    {
      if (tempGrid[(int)(i / 9)][(int)(i % 9)] == 0)
      {
        vector<int> temp;
        temp.push_back(i);

        for (int num = 1; num <= 9; num++)
        {
          if (isSafe(tempGrid, i / 9, i % 9, num))
          {
            temp.push_back(num);
          }
        }

        empty.push_back(temp);
      }
    }

    if (empty.size() == 0)
    {
      cout << "Hello: " << sum << endl;
      return sum;
    }

    int minIndex = 0;

    int check = empty.size();
    for (int i = 0; i < check; i++)
    {
      if (empty[i].size() < empty[minIndex].size())
        minIndex = i;
    }

    int branchFactor = empty[minIndex].size();
    int rowIndex = empty[minIndex][0] / 9;
    int colIndex = empty[minIndex][0] % 9;

    tempGrid[rowIndex][colIndex] = this->solnGrid[rowIndex][colIndex];
    sum = sum + ((branchFactor - 2) * (branchFactor - 2));

    emptyPositions = empty.size() - 1;
  }

  return sum;
}

void Sudoku::calculateDifficulty()
{
  int B = branchDifficultyScore();
  int emptyCells = 0;

  for (int i = 0; i < 9; i++)
  {
    for (int j = 0; j < 9; j++)
    {
      if (this->grid[i][j] == 0)
        emptyCells++;
    }
  }

  this->difficultyLevel = B * 100 + emptyCells;
}

bool Sudoku::checkSolution()
{
  for (int i = 0; i < 9; i++)
  {
    for (int j = 0; j < 9; j++)
    {
      if (this->solution[i][j] != this->solnGrid[i][j])
        return false;
    }
  }
  return true;
}

void Sudoku::insertNumber(int x, int y, int num)
{
  if (!(this->grid[x][y] == 0))
  {
    cout << "This cell is not empty" << endl;
    return;
  }
  this->solution[x][y] = num;
}

void Sudoku::removeNumber(int x, int y)
{
  if (this->grid[x][y] == 0 || this->solution[x][y] == solnGrid[x][y])
  {
    cout << "This cell is empty or the cell is given" << endl;

    return;
  }
  if (this->solution[x][y] == solnGrid[x][y])

    this->solution[x][y] = 0;
}

int main(int argc, char const *argv[])
{
  
  // Initialising seed for random number generation
  srand(time(NULL));

  // Creating an instance of Sudoku
  Sudoku *puzzle = new Sudoku();

  // Creating a seed for puzzle generation
  puzzle->createSeed();

  // Generating the puzzle
  puzzle->genPuzzle();

  // Calculating difficulty of puzzle
  puzzle->calculateDifficulty();

  int x, y, num, choice;


  while (true)
  {

    cout << "\n-------------- Sudoku Puzzle --------------\n"
         << endl;
    puzzle->printGrid(puzzle->solution);
    cout << "\n-------------------------------------------\n"
         << endl;

    cout << "Enter your choice" << endl;
    cout << "1. Insert a number" << endl;
    cout << "2. Remove a number" << endl;
    cout << "3. View solution" << endl;
    cout << "4. Check solution " << endl;
    cout << "5. Exit" << endl;
    cout << "Your choice: ";
    cin >> choice;

    cout << "\n-------------------------------------------\n"
         << endl;
    switch (choice)
    {
    case 1:
      cout << "Enter the row number: ";
      cin >> x;
      cout << "Enter the column number: ";
      cin >> y;
      cout << "Enter the number: ";
      cin >> num;
      puzzle->insertNumber(x, y, num);
      break;
    case 2:
      cout << "Enter the row number: ";
      cin >> x;
      cout << "Enter the column number: ";
      cin >> y;
      puzzle->removeNumber(x, y);
      break;
    case 3:
      cout << "Here is the solution\n" << endl;
      puzzle->printGrid(puzzle->solnGrid);
      break;
    case 4:
      if (puzzle->checkSolution())
        cout << "Congratulations! You have solved the puzzle correctly" << endl;
      else
      {
        cout << "Sorry! You have not solved the puzzle correctly" << endl;
        puzzle->printGrid(puzzle->solnGrid);
        exit(0);
      }
      break;
    case 5:
      cout << "Thank you for playing!" << endl;
      return 0;

    default:
      cout << "Invalid choice" << endl;
    }
  }

  // freeing the memory
  delete puzzle;

  return 0;
}
