#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <string.h>
#include <Windows.h>

#define MAX_STUDENTS 50;


typedef struct Student
{
	char name[50];
	int age;
	int id;
	float gpa;
} Student;

Student students[50];
int student_count = 0;

void print_student(Student* student);
int load_students(Student students[]);
void save_students(Student students[]);
void add_student(Student student);
void print_menu();
Student create_student(char* name, int age, int id, float gpa);
Student search(int id);

void print_student(Student* student)
{
	printf("Name: %s\n", student->name);
	printf("Age: %d\n", student->age);
	printf("ID: %d\n", student->id);
	printf("GPA: %.2f\n", student->gpa);
}


Student create_student(char* name, int age, int id, float gpa)
{
	Student student;
	strcpy(student.name, name);
	student.age = age;
	student.id = id;
	student.gpa = gpa;
	return student;
}

int load_students(Student students[])
{
	FILE* file = fopen("students.txt", "r");

	if (file == NULL)
	{
		printf("Error opening file\n");
		return -1;
	}

	char line[1024];
	int student_count = 0;

	while (fgets(line, 1024, file))
	{
		char* name = strtok(line, ",");
		int age = atoi(strtok(NULL, ","));
		int id = atoi(strtok(NULL, ","));
		float gpa = atof(strtok(NULL, ","));
		Student student = create_student(name, age, id, gpa);

		students[student_count] = student;
		student_count++;
	}

	fclose(file);

	return student_count;
}

void save_students(Student students[]) {
	FILE* fp = fopen("students.txt", "w");

	if (fp == NULL)
	{
		printf("Error opening file\n");
		return;
	}

	int i;
	for (i = 0; i < student_count; i++)
	{
		Student student = students[i];
		fprintf(fp, "%s,%d,%d,%f\n", student.name, student.age, student.id, student.gpa);
	}

	fclose(fp);
}

void add_student(Student student)
{
	students[student_count] = student;
	student_count++;
}

Student search(int id)
{
	int i;
	for (i = 0; i < student_count; i++)
	{
		Student student = students[i];
		if (student.id == id)
		{
			return student;
		}
	}

	Student student;
	strcpy(student.name, "Not Found");
}

void print_menu()
{
	printf("1. Add Student\n");
	printf("2. Display Students\n");
	printf("3. Search Students\n");
	printf("4. Delete Students\n");
	printf("5. Exit\n");
}

int main()
{
	student_count = load_students(students);

	if (student_count == -1)
	{
		return -1;
	}

	int choice = 0, age, id, i, j;
	char name[50];
	float gpa;
	Student student;

	do {
		system("cls");
		print_menu();
		printf("Enter choice: ");
		scanf("%d", &choice);

		switch (choice)
		{
		case 1:
		{
			system("cls");
			printf("Enter name: ");
			fgets(name, 50, stdin);
			fgets(name, 50, stdin);
			name[strlen(name) - 1] = '\0';
			printf("Enter age: ");
			scanf("%d", &age);
			printf("Enter id: ");
			scanf("%d", &id);		
			printf("Enter gpa: ");
			scanf("%f", &gpa);
			student = create_student(name, age, id, gpa);
			add_student(student);
			save_students(students);
			break;
		}
		case 2:
		{
			system("cls");
			printf("Students:\n\n");
			for (i = 0; i < student_count; i++) {
				printf("%d.\n", i + 1);
				print_student(&students[i]);
				printf("\n");
			}
			printf("\nPress any key to continue...\n");
			_getch();
			break;
		}
		case 3:
		{
			system("cls");
			printf("Enter id: ");
			scanf("%d", &id);
			student = search(id);

			if (strcmp(student.name, "Not Found") == 0)
			{
				printf("Student not found!\n");
				printf("Press any key to continue...\n");
				_getch();
				break;
			}

			printf("Student information:\n");
			print_student(&student);
			printf("\nPress any key to continue...\n");
			_getch();
			break;
		}
		case 4:
		{
			system("cls");
			printf("Enter id: ");
			scanf("%d", &id);
			student = search(id);

			if (strcmp(student.name, "Not Found") == 0)
			{
				printf("Student not found!\n\n");
				printf("Press any key to continue...\n");
				_getch();
				break;
			}

			for (i = 0; i < student_count - 1; i++)
			{
				if (student.id != students[i].id) continue;

				for (j = i; j < student_count - 1; j++)
				{
					students[j] = students[j + 1];
				}
				break;
			}

			student_count--;

			save_students(students);
			printf("Student deleted.\n\n");
			printf("Press any key to continue...\n");
			_getch();
			break;

		}
		case 5:
			system("cls");
			printf("Exiting...\n");
			break;
		default:
			printf("Invalid choice!\n");
			printf("Press any key to continue...\n");
			_getch();
			break;
		}
	} while (choice != 5);
}
