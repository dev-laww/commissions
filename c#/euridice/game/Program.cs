using System;

namespace game

{
  internal class Program
  {
    public static int[,] getBoard()
    {
      int[,] board = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
            };

      return board;
    }

    public static void printBoard(int[,] board)
    {
      // Print the board
      // - - - - - - -
      // | . | . | . |
      // | - + - + - |
      // | . | . | . |
      // | - + - + - |
      // | . | . | . |
      // - - - - - - -

      Console.WriteLine("- - - - - - -");
      for (int i = 0; i < 3; i++)
      {
        for (int j = 0; j < 3; j++)
        {
          if (board[i, j] == 0)
          {
            Console.Write("| . ");
          }
          else if (board[i, j] == 1)
          {
            Console.Write("| X ");
          }
          else if (board[i, j] == 2)
          {
            Console.Write("| O ");
          }
        }
        Console.WriteLine("|");
        if (i < 2)
        {
          Console.WriteLine("| - + - + - |");
        }
      }
      Console.WriteLine("- - - - - - -");
    }

    public static void editBoard(int[,] board, int row, int col, int player)
    {
      if (board[row - 1, col - 1] == 0)
      {
        board[row - 1, col - 1] = player;
      }
      else
      {
        Console.WriteLine("This position is already taken!");
      }
    }

    public static bool checkWin(int[,] board, int player)
    {
      // Check if the player has won
      // Check rows
      for (int i = 0; i < 3; i++)
      {
        if (board[i, 0] == player && board[i, 1] == player && board[i, 2] == player)
        {
          return true;
        }
      }

      // Check columns
      for (int i = 0; i < 3; i++)
      {
        if (board[0, i] == player && board[1, i] == player && board[2, i] == player)
        {
          return true;
        }
      }

      // Check diagonals
      if (board[0, 0] == player && board[1, 1] == player && board[2, 2] == player)
      {
        return true;
      }
      if (board[0, 2] == player && board[1, 1] == player && board[2, 0] == player)
      {
        return true;
      }

      return false;
    }

    public static bool checkDraw(int[,] board)
    {
      // Check if the game is a draw
      for (int i = 0; i < 3; i++)
      {
        for (int j = 0; j < 3; j++)
        {
          if (board[i, j] == 0)
          {
            return false;
          }
        }
      }

      return true;
    }

    static void Main(string[] args)
    {
      int[,] board = getBoard();
      printBoard(board);
      int row, col;
      int player = 1;

      while (true)
      { 
        Console.WriteLine("Player " + player + "'s turn");
        try
        {
          Console.Write("Enter row [1-3]: ");
          row = Convert.ToInt32(Console.ReadLine());
          Console.Write("Enter column [1-3]: ");
          col = Convert.ToInt32(Console.ReadLine());
          while (row < 1 || row > 3 || col < 1 || col > 3)
          {
            Console.WriteLine("Invalid input!");
            Console.Write("Enter row [1-3]: ");
            row = Convert.ToInt32(Console.ReadLine());
            Console.Write("Enter column [1-3]: ");
            col = Convert.ToInt32(Console.ReadLine());
          }
        }
        catch (Exception e)
        {
          Console.WriteLine("Invalid input");
          Console.WriteLine(e);
          continue;
        }

        Console.Clear();
        editBoard(board, row, col, player);
        printBoard(board);


        if (checkDraw(board))
        {
          Console.WriteLine("Draw!");
          break;
        }

        if (checkWin(board, player))
        {
          Console.WriteLine("Player " + player + " has won!");
          break;
        }

        player = player == 1 ? 2 : 1;
      }
      Console.ReadKey();
    }
  }
}