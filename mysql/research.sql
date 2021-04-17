/*

    This is the database that will let faculty, students, and other public
    users search for people with common interests/speakers.

*/

DROP DATABASE IF EXISTS facultyResearch;

CREATE DATABASE facultyResearch;

USE facultyResearch;

DROP TABLE IF EXISTS faculty;
CREATE TABLE faculty (

    facultyID INT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    school VARCHAR(100),
    facultyAbstract VARCHAR (500),
    CONSTRAINT faculty_pk PRIMARY KEY (facultyID)

);

DROP TABLE IF EXISTS student;
CREATE TABLE student (

    studentID INT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    school VARCHAR(100),
    CONSTRAINT student_pk PRIMARY KEY (studentID)

);

-- DROP TABLE IF EXISTS facultyAbstracts;
-- CREATE TABLE facultyAbstracts (

--     facultyAbstractID INT,
--     facultyID INT,
--     abstract VARCHAR (500),
--     CONSTRAINT facultyAbstracts_faculty_fk FOREIGN KEY (facultyID) REFERENCES faculty(facultyID),
--     CONSTRAINT facultyInterests_pk PRIMARY KEY (facultyAbstractID, facultyID)

-- );

DROP TABLE IF EXISTS studentAbstracts;
CREATE TABLE studentAbstracts (

    studentAbstractID INT,
    studentID INT,
    CONSTRAINT studentAbstracts_student_fk FOREIGN KEY (studentID) REFERENCES student(studentID),
    CONSTRAINT studentInterests_pk PRIMARY KEY (studentAbstractID, studentID)

);

DROP TABLE IF EXISTS skills;
CREATE TABLE skills (

    skillID INT,
    skill VARCHAR(10),
    CONSTRAINT PRIMARY KEY (skillID)

);

DROP TABLE IF EXISTS studentskill;
CREATE TABLE studentskill (

    studentID INT,
    skillID INT,
    CONSTRAINT PRIMARY KEY (studentID, skillID),
    CONSTRAINT studentSkill_student_fk FOREIGN KEY (studentID) REFERENCES student(studentID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT studentSkill_skill_fk FOREIGN KEY (skillID) REFERENCES skills(skillID) ON DELETE CASCADE ON UPDATE CASCADE

);

DROP TABLE IF EXISTS facultyKeywords;
CREATE TABLE facultyKeywords (

    facultyID INT,
    keyword VARCHAR (50),
    CONSTRAINT facultyKeywords_faculty_fk FOREIGN KEY (facultyID) REFERENCES faculty(facultyID)

);

DROP TABLE IF EXISTS studentKeywords;
CREATE TABLE studentKeywords (

    studentAbstractID INT,
    keyword VARCHAR (50),
    CONSTRAINT studentKeywords_studentAbstracts_fk FOREIGN KEY (studentAbstractID) REFERENCES studentAbstracts(studentAbstractID)

);