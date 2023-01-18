#include <stdio.h>
#include "Prompts.c"

int main()
{
  int nPlayerHP = 9, nEnemyHP = 9;
  int nEnemyAction = 1;
  int doAttack = 0;

  do
  {
    displayHPs(nPlayerHP, nEnemyHP);
    int nAction = askPlayerInput();
    showPlayerAction(nAction);
    showEnemyAction(nEnemyAction);

    switch (nAction)
    {
    case 1:
      nEnemyHP -= 3;
      break;
    case 2:
      if (nEnemyAction == 1)
      {
        nPlayerHP += 3;
      }
      if (nPlayerHP > 9)
      {
        nPlayerHP = 9;
      }
      

      break;
    case 3:
      nPlayerHP += 3;
      if (nPlayerHP > 9)
      {
        nPlayerHP = 9;
      }
      break;
    }

    switch (nEnemyAction)
    {
    case 1:
      nPlayerHP -= 3;
      if (doAttack == 1)
      {
        nEnemyAction = 3;
      }
      else
      {
        nEnemyAction = 2;
      }

      doAttack += 1;
      break;

    case 2:
      if (nAction == 1) {
        nEnemyHP += 3;
      }
      if (nEnemyHP > 9)
      {
        nEnemyHP = 9;
      }

      if (doAttack == 1 || doAttack == 2 || doAttack == 3 || doAttack == 4)
      {
        nEnemyAction = 1;
      }
      else
      {
        nEnemyAction = 3;
      }
      break;

    case 3:
      nEnemyHP += 3;
      if (nEnemyHP > 9)
      {
        nEnemyHP = 9;
      }

      if (doAttack == 3)
      {
        nEnemyAction = 1;
        doAttack = 0;
      }
      else
      {
        nEnemyAction = 2;
        doAttack += 1;
      }
      break;
    }

  } while (nPlayerHP > 0 && nEnemyHP > 0);

  displayHPs(nPlayerHP, nEnemyHP);
  if (nPlayerHP <= 0)
  {
    showPlayerLose();
  }
  else if (nEnemyHP <= 0)
  {
    showPlayerWin();
  }
  else if (nPlayerHP <= 0 && nEnemyHP <= 0)
  {
    showPlayerLose();
  }
}