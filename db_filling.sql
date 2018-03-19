INSERT INTO `Roles` (`id`, `role`) VALUES
	(1, 'ADMIN'),
	(2, 'USER');

INSERT INTO `Users` (`id`, `email`, `password`, `roleId`) VALUES
	(1, 'serhii', '202cb962ac59075b964b07152d234b70', 1),
	(2, 'lida', '202cb962ac59075b964b07152d234b70', 1),
	(3, 'kirill', '202cb962ac59075b964b07152d234b70', 1);

INSERT INTO Worksheet(id, name, userId) VALUES (1, "ФІ", 1);
INSERT INTO Worksheet(id, name, userId) VALUES (2, "ФЕН", 1);

INSERT INTO Specialty(id, name, id_worksheet) VALUES (1, "Комп'ютерні науки Магістерська Програма", 1);
INSERT INTO Specialty(id, name, id_worksheet) VALUES (2, "Програмна інженерія Бакалаврська Програма", 1);

INSERT INTO Room_type(id, name) VALUES (1, "Стандартна");
INSERT INTO Room_type(id, name) VALUES (2, "Комп'ютерна");

INSERT INTO Classroom(id, building, number, capacity, id_room_type, id_worksheet) VALUES (1, "1", "101", 50, 1, 1);
INSERT INTO Classroom(id, building, number, capacity, id_room_type, id_worksheet) VALUES (2, "1", "102", 50, 1, 1);
INSERT INTO Classroom(id, building, number, capacity, id_room_type, id_worksheet) VALUES (3, "1", "103", 50, 1, 1);
INSERT INTO Classroom(id, building, number, capacity, id_room_type, id_worksheet) VALUES (4, "1", "104", 50, 2, 1);
INSERT INTO Classroom(id, building, number, capacity, id_room_type, id_worksheet) VALUES (5, "1", "105", 50, 2, 1);

INSERT INTO Tool(id, name) VALUES (1, "Проектор");

INSERT INTO Classroom_Tool(id_classroom, id_tool) VALUES (1, 1);
INSERT INTO Classroom_Tool(id_classroom, id_tool) VALUES (4, 1);

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