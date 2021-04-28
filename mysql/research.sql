/*

    This is the database that will let faculty, students, and other public
    users search for people with common interests/speakers.

*/

DROP DATABASE IF EXISTS facultyResearch;

CREATE DATABASE facultyResearch;

USE facultyResearch;

DROP TABLE IF EXISTS faculty;
CREATE TABLE faculty (

    facultyID INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    school VARCHAR(100),
    username VARCHAR(50) UNIQUE,
    pwhash VARCHAR(500),
    salt VARCHAR(500),
    facultyAbstract VARCHAR (500),
    CONSTRAINT faculty_pk PRIMARY KEY (facultyID)

);

DROP TABLE IF EXISTS student;
CREATE TABLE student (

    studentID INT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    school VARCHAR(100),
    username VARCHAR(50) UNIQUE,
    pwhash VARCHAR(500),
    salt VARCHAR(500),
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

-- DROP TABLE IF EXISTS studentAbstracts;
-- CREATE TABLE studentAbstracts (

--     studentAbstractID INT,
--     studentID INT,
--     CONSTRAINT studentAbstracts_student_fk FOREIGN KEY (studentID) REFERENCES student(studentID),
--     CONSTRAINT studentInterests_pk PRIMARY KEY (studentAbstractID, studentID)

-- );

DROP TABLE IF EXISTS skills;
CREATE TABLE skills (

    skillID INT,
    skill VARCHAR(15),
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
    keywords VARCHAR (50),
    CONSTRAINT facultyKeywords_faculty_fk FOREIGN KEY (facultyID) REFERENCES faculty(facultyID) ON DELETE CASCADE ON UPDATE CASCADE

);

-- DROP TABLE IF EXISTS studentKeywords;
-- CREATE TABLE studentKeywords (

--     studentAbstractID INT,
--     keyword VARCHAR (50),
--     CONSTRAINT studentKeywords_studentAbstracts_fk FOREIGN KEY (studentAbstractID) REFERENCES studentAbstracts(studentAbstractID)

-- );

INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (1, "Steve", "Roberts", "RIT", "steveroberts@rit.edu", "k7ykP+csWopHxokdiY5rDcxvhYrclFYh4MpNW/HEXuWJhSvxCgHZ0B2x7RSdyCAO/06Inyy7v9Cgjz0lfwk=", "iuac20OTClEJeibNBhMNrOfhTTVtqJTHVc812UcQ3SI=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (2, "Mason", "Large", "RIT", "masonlarge@rit.edu", "13jDTlQrH5QMo9QpmYO5H4Dq6fxn7hav+mxRpKkOkeNVe6LlB3OgG2VV2oqMz37hUPNGXYwLbgm89FPS05k=", "9V0JIAQ8LX/hjTi7pEBrrNKgGRK6DNrhtVW9j+Osrvw=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (3, "Henry", "Green", "RIT", "henrygreen@rit.edu", "7ByzbBdvcKA+JIKSEOsIG4rZ4D5u/en1qwMpD9DyNguHDuLcKnMUximrOFCsQZ1YlpLzzDONjSAXQ0kmswE=", "tjDKieOsYeMn15H+6N/LU5KlQ4UAmkIcfcxNO4WlprI=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (4, "Chris", "Blue", "RIT", "chrisblue@rit.edu", "bxVGCRdkMHnb0o4878sx2lS6Z9KxINsvCCUrx6YJ94oTImbyDRpCkh4xh5qLXZIO012KAk/8Rwp5hPsF4MU=", "bZTo68XMCdeAUaS4ZR9Xyj8nCEzwWnRbntPCBU6gWJU=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (5, "Sarah", "Jones", "RIT", "sarahjones@rit.edu", "XyhyWM9jUJZ8hAbBY+p65FPctRCuDEaQCmntt0w9ep/9ad4/5/ClxY6ZYj/KeFWtCFmcs33Dqoav3dzlurw=", "WAQrk0tsUjI58rNdVYAIEz2RO4YrwZHsm5QVhZzHLFQ=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (6, "Conor", "Keegan", "RIT", "conorkeegan@rit.edu", "A21B4QSnWmaz/Z94NdofaNUxUOTevF/Wa5a4rcDUpjhQrkrjoc0lAFwak7+A36AUorGRSonFucMNy0eVgvE=", "jjfuVdOC2bZzsm+8FCuvag/HxVcZLW3Ni0UXFAOBkR4=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (7, "Sam", "Romero", "RIT", "samromero@rit.edu", "gHGY63KIhMS2xokiETgRPgzwB/BPakzFnxCq9RJ2AEIoX9jXiSRpwjm4epPybyQywtRdICS8iXXHp456kpI=", "5iuUAXOdh9IEBAX1cRkhpUVh/ws9JpG0sVeEenbMxNw=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (8, "Camran", "Bridge", "RIT", "camranbridge@rit.edu", "au95sFqdrmY+4FFnh7EytUDXbQR/6QOfebbG0ut7zbjxoUqTPEHUb9a2iW0HYQge8K3I6Ztzw0Ge9xNYxCw=", "NdhL+8/yDzo+H5AllXfaXYlb+EMrzfevDXf9dLkRjbU=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (9, "Clarise", "Turner", "RIT", "clariseturner@rit.edu", "ToTKA9CyQUDlX8BxoLeNjItypCQASPGt5KQF2vYap3A9BqEN86slZIumylyDzEDX5aRXrrOoC/XOCuRCc5M=", "gWXXQmG7+lmo42mASyOubhIjCJ2YqJrwK6eWcnRdRBI=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (10, "Ronald", "Williams", "RIT", "ronaldwilliams@rit.edu", "jot9W4nnGmlFxvE1fRW1m25o8/Fc77uPpDoG17CaUv5ClflD1t9D4/7GqAquXalnHbq9c29CbSE+/srSFr4=", "euHYzsr3JzksssDBHqoO9qp+flB7kvajbm6eaSgD11Q=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (11, "Bailey", "Anthony", "RIT", "baileyanthony@rit.edu", "fQBdvNbtBDYmS93sV3EI9vO+0wg1M3MetjNuKrjEW/roE6IKUDu8C9jI+JM0qJVJEsvwPF0iKjkpEwZ+qZM=", "nUDv2BJNj8gos2zX3DPnT8V5GTjCHIps1YQ2KEh2hzE=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (12, "Courtney", "Tanner", "RIT", "courtneytanner@rit.edu", "PhwNRuIp09pFuTDWiWenvIoJtmTwu+9vSGMsVgxtMoH1fDTRrlaP9OKzZPB5JhfWfgXoPi3MjeKC2ffYdjo=", "g9tz3uAw6MoO3rkeTDevqY8jJ6vjKwm1Ws6h9sHJlug=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (13, "Claude", "Shelton", "RIT", "claudeshelton@rit.edu", "MOnQ67TvH9jQZi1991UBsLnQyqzDRXxJ4+n/DJzsCFrxXQ06urecrQIyEapTjR5bOLEZdIfzTabCtEOKyqs=", "H1eC0CpRnNftHrRYtOVVFsSKL0oQShQrX7Li/gGmpqI=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (14, "Hudson", "Dudley", "RIT", "hudsondudley@rit.edu", "20xiCs+Y4Z5hCngtkeoksvooyu5MeLmGBFEcUG4+o/S6VBpZYB2LAzgQB4Nx+Kp/YCdkapRm8Ce8CisJdsQ=", "J55bhtFqDqY+rDeV+iXukSRUZ3psNy95lMOsBTd9LcI=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (15, "Tyler", "Gibson", "RIT", "tylergibson@rit.edu", "jOAdMcbp9sq6bDLjQo4sQWt2F+pFge++PvPCC1E3tjqJil9oxdtHT/jshU7FWRPvYJJYMLfqAtEWsNyqKHE=", "6EXgDVUc2wtPPAjUVxTZV6SJzDM0JdFoIr44G+o4eME=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (16, "Savannah", "Montes", "RIT", "savannahmontes@rit.edu", "jzlKoPTmP+PYc5zJmitRasJeW+PYknAYsclcRyINvtfmT0hwAOHgUJJTojQdOApU/ehw8cNqnNJA0wUpv0g=", "yOryFYSwPs+GUk7mQayEEeGkFu1huerwdLo5YZ4YSeY=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (17, "Abbey", "Henderson", "RIT", "abbeyhenderson@rit.edu", "7b3l1EqN0I4V1y1pVdpwvNU4BwQYfLe+AGp3vie1OHDBcH2kV44xrgOKXi7Z/fGXOmJnpR1HfpxwUT1oY1k=", "YBoFzSa2DWP5qTJuzECuBAaRpmT4tnhaIJ2JXNkTCAs=");
INSERT INTO student (studentid, firstname, lastname, school, username, pwhash, salt) VALUES (18, "Hetty", "Alexander", "RIT", "hettyalexander@rit.edu", "rhFMr6890w8QH1aJrgQXvynElmmDm42nac05KeGO2TawlLicw6hr1lTqTQ4J4RIo/BCxfApmzB3Np7zr6u4=", "YaOiqLV8S0h8cEzA6BAKWP9g7PVxgw2xNXppHDD5ihQ=");

INSERT INTO faculty (facultyID, firstname, lastname, school, username, pwhash, salt, facultyAbstract) VALUES (1, "Steve", "Smith", "RIT", "stevesmith@rit.edu", "fvXX38CFm65cZSw5pLT/Sa+eQTsBLglxfJMYmipnOT2obSuqtCXdXiOsLswAVYJzpFYbXrxbkhwnimYZtp8=", "t7npLksG7sJWhen1pVMc45fyZLZ/YGTUA6vgwwUdPmw=", "Steve Smith is really into Java. He has written multiple user interfaces in Java. This includes sign up systems, database information viewers, and web applications. He now teaches multiple Java classes at RIT.");
INSERT INTO faculty (facultyID, firstname, lastname, school, username, pwhash, salt, facultyAbstract) VALUES (2, "George", "Preston", "RIT", "georgepreston@rit.edu", "fEmpqoBUTnPeKmOhnHP5bDzX4lIbxr997fAqjIoI+5rT9lnYI3wmIzUlx40rcIBaus+9irVuUrqhK5boxls=", "Ug0doNQxEpCCeKW6MIlEoMyWHZwjFM9hKNPdqsBfa/A=", "George Preston is a big supporter of object-oriented programming. Through his computer science classes at RIT, he teaches students the theory behing object-oriented programming and how it maps to real life problems.");
INSERT INTO faculty (facultyID, firstname, lastname, school, username, pwhash, salt, facultyAbstract) VALUES (3, "Karen", "Brown", "RIT", "karenbrown@rit.edu", "bVbUKw6A7+I9EHorDlkfFsEUBF7VlaLnLeXQKR88Vvta0VOCZOpWd9oerpbmsq7Ev/GWkvZfzTJhbaf4etQ=", "BjSoXk6JHDqiCrkBhhGka754GIeOHu2VY0AMlT8o6Zo=", "Karen Brown is a computing security professor at RIT. She has written multiple books on the subject and teaches her classes using the language know as Python. She also likes to teach her students about the ethics that come with gaining knowledge that allows the breaching of private data in computers.");
INSERT INTO faculty (facultyID, firstname, lastname, school, username, pwhash, salt, facultyAbstract) VALUES (4, "Terry", "Murray", "RIT", "terrymurray@rit.edu", "vihbxVu6/VciglttbsdPndRlUgAWuxk5oeYEruj5q2ZFNSMTtPImeoYUKotR8MOBGl28vta9PoUJPZyVl3g=", "n+Bzxw4gwieBTNpwXSoYL2zFAp/yuFbSdQ0+2Zt3UVw=", "Terry Murray has given multiple speeches about project management when it comes to creating applications. He stresses in all of his classes at RIT that through strict organizational skills and teamwork, the completion of a project is well under way.");
INSERT INTO faculty (facultyID, firstname, lastname, school, username, pwhash, salt, facultyAbstract) VALUES (5, "Greg", "West", "RIT", "gregwest@rit.edu", "EsM/yw56DK0sRkGZRukJzUGB413R8/6GAyor785dRHKEZl6cWhtnYoWDelZ4dCwCVIipzeoHH2+hhuNYOnQ=", "x6LCySiTUnGebqKSSWuhSL1O8Uql0gQIVAcepibafxo=", "Greg West is a mobile application development guru and professor at RIT. By teaching his students the basic rules of design, color, proximity, etc. when it comes to mobile design, he is then able to teach his students Swift code, allowing them to create good-looking user interfaces in mobile environments.");
INSERT INTO faculty (facultyID, firstname, lastname, school, username, pwhash, salt, facultyAbstract) VALUES (6, "Madison", "Mann", "RIT", "madisonmann@rit.edu", "Rc+W2vpf88WjHMtSkNK/RNhtcz9ZEalK1iQqm7BNquwkSoIhOaatofo9peW9Zo9jS6d0vHeQK3kWrIPxq8U=", "F4RnjbtVgzsRkNOwiwvHtqARlcwClpFgij4MOfVi8S4=", "Madison Mann is a former web designer at multiple big companies. Through her knowledge gained from her past experiences and general knowledge about web development she has gained over the years, she is able to teach her students how to create websites that will make users want to stick around.");
INSERT INTO faculty (facultyID, firstname, lastname, school, username, pwhash, salt, facultyAbstract) VALUES (7, "Brady", "Fleming", "RIT", "bradyfleming@rit.edu", "Dwu6/UNFEMWJVTXZd7unlpTxXVdzsMrQvjDjk42wJJfiDFQo0FOXTxhwNcPzz49uIaio86mXzoMpGqU8G1U=", "B6WxDKPfO5jq0VJUVoGkr9hFglmal3/sgW99fsBE9so=", "Brady Fleming loves to teach the hard subject of assembly language. With a large amount of experience with early game design for early handheld systems, Brady teaches his students how to write in languages very close to their machine's hardware.");
INSERT INTO faculty (facultyID, firstname, lastname, school, username, pwhash, salt, facultyAbstract) VALUES (8, "Osman", "Welch", "RIT", "osmanwelch@rit.edu", "t7GgQ4XyYq684eGuKCsJT4R3EYH0q0VutH94vma7Ky+65DdwaZGkvXLLSx7HLVCAOCKxhQCGNA+buRoRXz4=", "q4ZnirA5SaGkbHpVwouWSBbxxR6v7Bq4FT44lldjbhk=", "Osman Welch has written multiple books on JavaScript. He has always been good at object-oriented programming, so being able to write code for the web and combine his object-oriented skills is a plus. He stresses the importance of JavaScript when it comes to web development to his students at RIT and makes sure they are well-versed in the sometimes difficult language.");
INSERT INTO faculty (facultyID, firstname, lastname, school, username, pwhash, salt, facultyAbstract) VALUES (9, "Cassidy", "Ferry", "RIT", "cassidyferry@rit.edu", "uJHLBLxfjyTr7D4rKjJBs/Q8+IB8LOFJepXgroWLk2u+bArqNz6x6pHaYVO1hUzGM1M0Peo9PHjNyfiCFqQ=", "cNmfS7aZSc4cOtNp3BTDbCtS/+ooCgjVU4f8y7wttZg=", "Cassidy Ferry has researched and developed with functional programming since a young age. She went to a very prestigeous school with a major in computer science and has since become a professor at RIT. Through her career, she is able to spread her knowledge about the importance of functional programming skills to her students.");
INSERT INTO faculty (facultyID, firstname, lastname, school, username, pwhash, salt, facultyAbstract) VALUES (10, "Heather", "Brewer", "RIT", "heatherbrewer@rit.edu", "t4tJn1d4LJMSD1e5/tfodLA14bTl5gDaLuI9I0L2HMiFD+J4MQPm7Jv2KS4ifMe8yX8GPcThkcyJ2CdhIig=", "Ertm9JvQU4NVRBVNJuFyvJitfUASmypLGW6nalGWJzw=", "Heather Brewer is a very skilled level designer when it comes to video games. She has a YouTube channel in which she uploads tutorials on the subject and has even gotten a job as a professor at RIT to teach students the basics of level design in video games. Although it can be a very tedious and tiresome job, she stresses the importance of level designers in the video game design market.");
INSERT INTO faculty (facultyID, firstname, lastname, school, username, pwhash, salt, facultyAbstract) VALUES (11, "Danny", "Forest", "RIT", "dannyforest@rit.edu", "igeoxRbgBVlUsATik9rpfbXdzEDY35W+BNxuafOfAbKBA0mPfQcd6jEluKhJCRdVNsOTS9i9YDUFskmd538=", "tsay+IZiDvlW6dOj46yR/0Wkejb7asA9gDEpN+yCko8=", "Danny forest has written one top selling book on C++ and has been a professor at RIT for ten years. Through his knowledge on C++, he was able to pick up scripting skills for video games. Now teaching a game design scripting course at RIT, he teaches the importance of the technical side of video games and the work that goes into it.");
INSERT INTO faculty (facultyID, firstname, lastname, school, username, pwhash, salt, facultyAbstract) VALUES (12, "Juniper", "Sims", "RIT", "junipersims@rit.edu", "Q2iZqbIrzpy4rFy9nETunbVmc2qkzrkNHSHbhDmumLLE4LMHsz0au/pC/Qwm7dOHw5ty2JM9nDbkQnHjU+Y=", "BkWV2VpXT865JcRPq/KW9SNnok0V8bPW7HdG1Pn+5KY=", "Juniper Sims has been researching software engineering topics for decades. She has brought forth her experience and decided to become a professor at RIT to teach exactly that. Through her expertise, she is able to influence her students to design and develop applications for end users.");

INSERT INTO facultyKeywords (facultyID, keywords) VALUES (1, "Java");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (1, "Systems");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (1, "Database");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (1, "Information");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (1, "Web");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (1, "Application");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (1, "RIT");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (1, "Interface");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (1, "Web application");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (2, "Object");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (2, "Object-oriented");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (2, "Programming");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (2, "Computer science");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (2, "Computer");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (2, "RIT");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (2, "Theory");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (3, "Computing");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (3, "Security");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (3, "Professor");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (3, "Computing security");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (3, "RIT");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (3, "Book");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (3, "Books");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (3, "Language");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (3, "Python");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (3, "Data");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (3, "Computer");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (4, "Speech");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (4, "Project");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (4, "Application");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (4, "Applications");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (4, "RIT");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (4, "Project");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (5, "Mobile");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (5, "Application");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (5, "Develop");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (5, "Professor");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (5, "RIT");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (5, "Design");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (5, "Swift");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (5, "Code");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (5, "Create");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (5, "Interface");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (6, "Web");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (6, "Design");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (6, "Web design");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (6, "Knowledge");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (6, "Develop");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (6, "Create");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (6, "Website");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (6, "User");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (7, "Hard");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (7, "Assembly");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (7, "Language");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (7, "Experience");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (7, "Game");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (7, "Design");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (7, "System");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (7, "Write");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (7, "Machine");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (7, "Hardware");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (8, "Book");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (8, "JavaScript");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (8, "Object");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (8, "Object-oriented");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (8, "Write");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (8, "Code");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (8, "Web");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (8, "Skill");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (8, "Develop");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (8, "RIT");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (8, "Language");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "Research");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "Develop");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "Function");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "Functional");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "Functional programming");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "Programming");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "School");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "Major");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "Computer");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "Computer science");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "Professor");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "RIT");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "Career");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "Knowledge");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (9, "Skill");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (10, "Skill");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (10, "Level");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (10, "Design");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (10, "Video game");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (10, "Game");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (10, "Tutorial");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (10, "Professor");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (10, "RIT");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (11, "Book");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (11, "C++");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (11, "Professor");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (11, "RIT");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (11, "Knowledge");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (11, "Script");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (11, "Skill");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (11, "Game");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (11, "Video game");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (11, "Design");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (12, "Research");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (12, "Software");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (12, "Engineer");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (12, "Software engineering");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (12, "Experience");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (12, "Professor");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (12, "RIT");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (12, "Design");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (12, "Develop");
INSERT INTO facultyKeywords (facultyID, keywords) VALUES (12, "Application");

INSERT INTO skills (skillID, skill) VALUES (1, "C++");
INSERT INTO skills (skillID, skill) VALUES (2, "C");
INSERT INTO skills (skillID, skill) VALUES (3, "C#");
INSERT INTO skills (skillID, skill) VALUES (4, "Objective-C");
INSERT INTO skills (skillID, skill) VALUES (5, "Java");
INSERT INTO skills (skillID, skill) VALUES (6, "Python");
INSERT INTO skills (skillID, skill) VALUES (7, "Perl");
INSERT INTO skills (skillID, skill) VALUES (8, "Go");
INSERT INTO skills (skillID, skill) VALUES (9, "HTML");
INSERT INTO skills (skillID, skill) VALUES (10, "CSS");
INSERT INTO skills (skillID, skill) VALUES (11, "JavaScript");
INSERT INTO skills (skillID, skill) VALUES (12, "MySQL");
INSERT INTO skills (skillID, skill) VALUES (13, "PHP");
INSERT INTO skills (skillID, skill) VALUES (14, "Swift");

INSERT INTO studentSkill (studentID, skillID) VALUES (1, 1);
INSERT INTO studentSkill (studentID, skillID) VALUES (1, 4);
INSERT INTO studentSkill (studentID, skillID) VALUES (1, 3);
INSERT INTO studentSkill (studentID, skillID) VALUES (2, 9);
INSERT INTO studentSkill (studentID, skillID) VALUES (2, 10);
INSERT INTO studentSkill (studentID, skillID) VALUES (2, 11);
INSERT INTO studentSkill (studentID, skillID) VALUES (3, 14);
INSERT INTO studentSkill (studentID, skillID) VALUES (3, 12);
INSERT INTO studentSkill (studentID, skillID) VALUES (3, 11);
INSERT INTO studentSkill (studentID, skillID) VALUES (3, 13);
INSERT INTO studentSkill (studentID, skillID) VALUES (4, 6);
INSERT INTO studentSkill (studentID, skillID) VALUES (4, 7);
INSERT INTO studentSkill (studentID, skillID) VALUES (4, 8);
INSERT INTO studentSkill (studentID, skillID) VALUES (4, 12);
INSERT INTO studentSkill (studentID, skillID) VALUES (5, 5);
INSERT INTO studentSkill (studentID, skillID) VALUES (5, 1);
INSERT INTO studentSkill (studentID, skillID) VALUES (6, 1);
INSERT INTO studentSkill (studentID, skillID) VALUES (6, 2);
INSERT INTO studentSkill (studentID, skillID) VALUES (6, 3);
INSERT INTO studentSkill (studentID, skillID) VALUES (7, 1);
INSERT INTO studentSkill (studentID, skillID) VALUES (7, 3);
INSERT INTO studentSkill (studentID, skillID) VALUES (7, 5);
INSERT INTO studentSkill (studentID, skillID) VALUES (7, 14);
INSERT INTO studentSkill (studentID, skillID) VALUES (7, 9);
INSERT INTO studentSkill (studentID, skillID) VALUES (8, 5);
INSERT INTO studentSkill (studentID, skillID) VALUES (8, 6);
INSERT INTO studentSkill (studentID, skillID) VALUES (8, 12);
INSERT INTO studentSkill (studentID, skillID) VALUES (9, 11);
INSERT INTO studentSkill (studentID, skillID) VALUES (9, 2);
INSERT INTO studentSkill (studentID, skillID) VALUES (9, 6);
INSERT INTO studentSkill (studentID, skillID) VALUES (9, 12);
INSERT INTO studentSkill (studentID, skillID) VALUES (10, 7);
INSERT INTO studentSkill (studentID, skillID) VALUES (10, 8);
INSERT INTO studentSkill (studentID, skillID) VALUES (11, 9);
INSERT INTO studentSkill (studentID, skillID) VALUES (11, 10);
INSERT INTO studentSkill (studentID, skillID) VALUES (11, 7);
INSERT INTO studentSkill (studentID, skillID) VALUES (12, 2);
INSERT INTO studentSkill (studentID, skillID) VALUES (12, 5);
INSERT INTO studentSkill (studentID, skillID) VALUES (12, 14);
INSERT INTO studentSkill (studentID, skillID) VALUES (12, 4);
INSERT INTO studentSkill (studentID, skillID) VALUES (13, 12);
INSERT INTO studentSkill (studentID, skillID) VALUES (13, 13);
INSERT INTO studentSkill (studentID, skillID) VALUES (13, 2);
INSERT INTO studentSkill (studentID, skillID) VALUES (13, 6);
INSERT INTO studentSkill (studentID, skillID) VALUES (14, 1);
INSERT INTO studentSkill (studentID, skillID) VALUES (14, 2);
INSERT INTO studentSkill (studentID, skillID) VALUES (14, 3);
INSERT INTO studentSkill (studentID, skillID) VALUES (14, 5);
INSERT INTO studentSkill (studentID, skillID) VALUES (14, 8);
INSERT INTO studentSkill (studentID, skillID) VALUES (15, 7);
INSERT INTO studentSkill (studentID, skillID) VALUES (15, 8);
INSERT INTO studentSkill (studentID, skillID) VALUES (15, 12);
INSERT INTO studentSkill (studentID, skillID) VALUES (15, 13);
INSERT INTO studentSkill (studentID, skillID) VALUES (16, 7);
INSERT INTO studentSkill (studentID, skillID) VALUES (16, 8);
INSERT INTO studentSkill (studentID, skillID) VALUES (17, 1);
INSERT INTO studentSkill (studentID, skillID) VALUES (18, 12);
INSERT INTO studentSkill (studentID, skillID) VALUES (18, 13);
INSERT INTO studentSkill (studentID, skillID) VALUES (18, 14);