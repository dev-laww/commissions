#include <iostream>
#include <string>

using namespace std;

int main()
{
    string course;
    float quizWeight, midtermWeight, finalsWeight;

    while (true)
    {
        cout << "Enter course number: ";
        getline(cin, course);

        if (course == "IT 112")
            cout << "Course Description: Computer Programming 1" << endl;
        else if (course == "IT 211")
            cout << "Course Description: Computer Programming 2" << endl;
        else if (course == "IT 212")
            cout << "Course Description: Data Structures and Algorithms" << endl;
        else if (course == "IT 321")
            cout << "Course Description: Capstone Project and Research 1" << endl;
        else if (course == "IT 412")
            cout << "Course Description: Capstone Project and Research 2" << endl;
        else {
            cout << "ALERT! COURSE NUMBER NOT FOUND! PLEASE ENTER AGAIN." << endl;
            continue;
        }
        
        break;
    }

    while (true)
    {
        cout << "\nEnter weight (%) for the following:" << endl;
        cout << "\tQuiz: ";
        cin >> quizWeight;
        cout << "\tMidterm: ";
        cin >> midtermWeight;
        cout << "\tFinals: ";
        cin >> finalsWeight;

        if (quizWeight + midtermWeight + finalsWeight != 100)
        {
            cout << "ALERT! WEIGHTS ENTERED IS NOT EQUAL TO 100! PLEASE ENTER AGAIN." << endl;
            continue;
        }

        break;
    }

    int numStudents;
    cout << "\nEnter Number of Students: ";
    cin >> numStudents;

    for (int i = 0; i < numStudents; i++)
    {
        string name;
        float quiz, midterm, finals, finalGrade;
        cout << "\nStudent " << i + 1 << endl;
        cout << "\tName: ";
        cin >> name;
        cout << "\tQuiz: ";
        cin >> quiz;
        cout << "\tMidterm exam: ";
        cin >> midterm;
        cout << "\tFinal exam: ";
        cin >> finals;
        finalGrade = ((quiz * (quizWeight / 100)) + (midterm * (midtermWeight / 100)) + (finals * (finalsWeight / 100))) * 0.5 + 50;
        cout << "Final grade of " << name << " ";
        if (finalGrade >= 95)
            cout << "A+" << endl;
        else if (finalGrade >= 90)
            cout << "A" << endl;
        else if (finalGrade >= 80)
            cout << "B+" << endl;
        else if (finalGrade >= 75)
            cout << "B" << endl;
        else
            cout << "C" << endl;
    }

    return 0;
}


