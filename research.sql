/*

    This is the database that will let faculty, students, and other public
    users search for people with common interests/speakers.

*/

DROP DATABASE IF EXISTS research;

CREATE DATABASE research;

USE research;

CREATE TABLE faculty (

    facultyID INT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    school VARCHAR(100),
    CONSTRAINT faculty_pk PRIMARY KEY (facultyID)

);

CREATE TABLE student (

    studentID INT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    school VARCHAR(100),
    CONSTRAINT student_pk PRIMARY KEY (studentID)

);

CREATE TABLE facultyAbstracts (

    facultyAbstractID INT,
    facultyID INT,
    abstract VARCHAR (500),
    CONSTRAINT facultyAbstracts_faculty_fk FOREIGN KEY (facultyID) REFERENCES faculty(facultyID),
    CONSTRAINT facultyInterests_pk PRIMARY KEY (facultyAbstractID)

);

CREATE TABLE studentAbstracts (

    studentAbstractID INT,
    studentID INT,
    abstract VARCHAR (500),
    CONSTRAINT studentAbstracts_student_fk FOREIGN KEY (studentID) REFERENCES student(studentID),
    CONSTRAINT studentInterests_pk PRIMARY KEY (studentAbstractID)

);

CREATE TABLE facultyKeywords (

    facultyAbstractID INT,
    keyword VARCHAR (50),
    CONSTRAINT facultyKeywords_facultyAbstracts_fk FOREIGN KEY (facultyAbstractID) REFERENCES facultyAbstracts(facultyAbstractID)

);

CREATE TABLE studentKeywords (

    studentAbstractID INT,
    keyword VARCHAR (50),
    CONSTRAINT studentKeywords_studentAbstracts_fk FOREIGN KEY (studentAbstractID) REFERENCES studentAbstracts(studentAbstractID)

);