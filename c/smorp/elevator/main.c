#include <stdio.h>
#include <stdlib.h>


// Elevator simulation
// Problem Statement:
// You are to simulate a single car elevator in a 10 floor office building. The purpose od this simultor is to simulate how long it would take to evacuate people from building under some policy that whe have the elevator adhere to


// Create person struct with destination floor and pointers to next and previous person


// Create queue struct with pointers to head and tail of queue
// typedef struct queue
// {
//     struct Person *head;
//     struct Person *tail;
// } Queue;

// Create floor struct with floor number and queue
// typedef struct floor
// {
//     int floor;
//     struct queue *q;
// } Floor;




// Constraints:
// 1. Requests to go up or down occur at the rate of between 0 and 3 riders per minute per floor. (The random class and the math class have the methods that will be helpful in generating random numbers)
// Use random number generator to generate a random number between 0 and 3 riders per minute per floor

// Create a function to generate a random number between 0 and 3

// Function to create a new person


// generate random number between 0 and 3 riders per minute per floor and print it


// 2. The elevator starts floor 1 and returns when there are no riders or requests

// 3. The elevator must pick up only  requesters going in its present direction, unless there are no occupants in the elevator, in which case it must pick up the longest waiting request in the queue



// 4. Riders going in the same direction as the elevator but not at the head of the queue can be reprioritized to the front of the queue
// Create a function to reprioritize the queue based on the direction of the elevator
// void reprioritizeQueue(struct Person *head, int direction)
// {
//     struct Person *temp = head;
//     while (temp != NULL)
//     {
//         if (temp->direction == direction)
//         {
//             temp->priority = 1;
//         }
//         temp = temp->next;
//     }
// }


// There is no limit to the number of riders in the elevator

// Initial conditions:
// 1. The elevator rests at floor 1
// 2. There are no ocupants in the elevator
// 3. There are no requests in the queue

// Operations:
// 1. Use random number generator to generate the number of people requestign the elevator service
// 2. Run for 1000 requests

// Data displays:
// For each event show (print):
// 1. The current floor of the elevator
// 2. Number of ocupants in the elevator
// 3. Target floor
// 4. The number of riders alighting on a given floor
// 5. Total number of successful landings during the interval
// 6. The station arrival event (Doors Opening)
// 7. The station departure event (Doors Closing)
// 8. Total number of successful departures during the interval
// 9. The number of pending requests waiting at some floor

// At the end of the simulation, print the following:
// 1. The average wait time between the request and the elevator arrival
// 2. Total number of riders
// 3. Total time to evacuate the building
