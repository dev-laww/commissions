#include <stdio.h>
#include <string.h>
#include <conio.h>

#define MAX_LEN 100 // maximum length for strings
#define NUM_STUDENTS 5 // number of students

// structure for student record
typedef struct {
    char first_name[MAX_LEN];
    char last_name[MAX_LEN];
    int age;
    char gender;
    float GPA;
} Student;

int main(void) {
    // array of student recordsN
	Student students[NUM_STUDENTS];
    for (int i = 0; i < NUM_STUDENTS; i++) {
        printf("Enter details for student %d:\n", i + 1);
        printf("  First name: ");
        scanf_s("%s", students[i].first_name, MAX_LEN);
        printf("  Last name: ");
        scanf_s("%s", students[i].last_name, MAX_LEN);
        printf("  Age: ");
        scanf_s("%d", &students[i].age);
        printf("  Gender: ");
		scanf_s(" %c", &students[i].gender, 1);
        printf("  GPA: ");
        scanf_s("%f", &students[i].GPA);
    }

    // open binary file for writing
    FILE* fp;
    if (fopen_s(&fp, "cruz.bin", "wb") != 0) {
        printf("Error opening file\n");
        return 1;
    }

    // write student records to binary file
    if (fwrite(students, sizeof(Student), NUM_STUDENTS, fp) != NUM_STUDENTS) {
        printf("Error writing to file\n");
        return 1;
    }

    // close file
    fclose(fp);

    // open binary file for reading
    if (fopen_s(&fp, "cruz.bin", "rb") != 0) {
        printf("Error opening file\n");
        return 1;
    }

    // read student records from binary file
    Student read_students[NUM_STUDENTS];
    if (fread(read_students, sizeof(Student), NUM_STUDENTS, fp) != NUM_STUDENTS) {
        printf("Error reading from file\n");
        return 1;
    }

    // close file
    fclose(fp);
    // print read student records
    for (int i = 0; i < NUM_STUDENTS; i++) {
        printf("Student %d:\n", i + 1);
        printf("  First name: %s\n", read_students[i].first_name);
        printf("  Last name: %s\n", read_students[i].last_name);
        printf("  Age: %d\n", read_students[i].age);
        printf("  Gender: %c\n", read_students[i].gender);
        printf("  GPA: %.2f\n\n", read_students[i].GPA);
    }

    return 0;
}