DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS Roles CASCADE;

CREATE TABLE `Roles` (
  `id` int(11) NOT NULL,
  `role` varchar(100) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET = UTF8;

INSERT INTO `Roles` (`id`, `role`) VALUES
(1, 'ADMIN'),
(2, 'USER');

CREATE TABLE `Users` (
  `id` int(11) AUTO_INCREMENT NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(256) NOT NULL,
  `roleId` int(11) NOT NULL,
  UNIQUE (email),
  PRIMARY KEY (id),
  FOREIGN KEY (roleId) REFERENCES Roles(id)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
) ENGINE=InnoDB CHARACTER SET = UTF8;

INSERT INTO `Users` (`email`, `password`, `roleId`) VALUES
  ('admin1@admin.admin', '202cb962ac59075b964b07152d234b70', 1),
  ('admin2@admin.admin', '202cb962ac59075b964b07152d234b70', 1); 
