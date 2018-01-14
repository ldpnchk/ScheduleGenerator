INSERT INTO Lecturer(name) VALUES ("Варвара Павлюк");
INSERT INTO Lecturer(name) VALUES ("Людмила Лещенко");
INSERT INTO Lecturer(name) VALUES ("Оксана Ситник");
INSERT INTO Lecturer(name) VALUES ("Любов Макаренко");
INSERT INTO Lecturer(name) VALUES ("Наталія Король");

INSERT INTO Discipline(name) VALUES ("Основи дискретної математики");
INSERT INTO Discipline(name) VALUES ("Основи математичного аналізу");
INSERT INTO Discipline(name) VALUES ("Лінійна алгебра на геометрія");
INSERT INTO Discipline(name) VALUES ("Алгебра і теорія чисел");
INSERT INTO Discipline(name) VALUES ("Дискретна математика");

INSERT INTO Specialty(name) VALUES ("Програмна інженерія");

INSERT INTO Student(name, course, id_specialty) VALUES ("Вадим Іванчук", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Омелян Заєць", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Ілля Саєнко", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Віктор Кухар", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Альберт Фесенко", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Остап Левчук", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Петро Бурлака", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Всеволод Ткач", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Ігор Гончар", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Степан Рудь", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Айдер Миронюк", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Кузьма Чайка", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Яків Романчук", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Юхим Буряк", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Дем'ян Марчук", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Микола Черняк", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Мирослав Скиба", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Тарас Близнюк", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Панас Медвідь", 1, 1);
INSERT INTO Student(name, course, id_specialty) VALUES ("Федір Стасюк", 1, 1);

INSERT INTO Room_type(name) VALUES ("Стандартна");
INSERT INTO Room_type(name) VALUES ("Комп'ютерна");

INSERT INTO Classroom(building, number, capacity, id_room_type) VALUES ("1", "101", 20, 1);
INSERT INTO Classroom(building, number, capacity, id_room_type) VALUES ("1", "102", 20, 1);
INSERT INTO Classroom(building, number, capacity, id_room_type) VALUES ("1", "103", 10, 1);
INSERT INTO Classroom(building, number, capacity, id_room_type) VALUES ("1", "104", 10, 1);
INSERT INTO Classroom(building, number, capacity, id_room_type) VALUES ("1", "105", 10, 1);

INSERT INTO Tool(name) VALUES ("Проектор");

INSERT INTO Classroom_Tool(id_classroom, id_tool) VALUES (1, 1);
INSERT INTO Classroom_Tool(id_classroom, id_tool) VALUES (3, 1);

INSERT INTO Daytime(name) VALUES ("Понеділок");
INSERT INTO Daytime(name) VALUES ("Вівторок");
INSERT INTO Daytime(name) VALUES ("Середа");
INSERT INTO Daytime(name) VALUES ("Четвер");
INSERT INTO Daytime(name) VALUES ("П'ятниця");

INSERT INTO Periodtime(number) VALUES (1);
INSERT INTO Periodtime(number) VALUES (2);
INSERT INTO Periodtime(number) VALUES (3);
INSERT INTO Periodtime(number) VALUES (4);
INSERT INTO Periodtime(number) VALUES (5);
INSERT INTO Periodtime(number) VALUES (6);