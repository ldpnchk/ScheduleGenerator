-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Дек 15 2017 г., 08:40
-- Версия сервера: 5.7.14
-- Версия PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `schdlgnrtr`
--

-- --------------------------------------------------------

--
-- Структура таблицы `classroom`
--

CREATE TABLE `classroom` (
  `id` int(11) NOT NULL,
  `building` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `capacity` int(11) NOT NULL,
  `id_room_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `classroom`
--

INSERT INTO `classroom` (`id`, `building`, `number`, `capacity`, `id_room_type`) VALUES
(1, '1', '101', 50, 1),
(2, '1', '102', 50, 1),
(3, '1', '103', 50, 2),
(4, '1', '104', 50, 2),
(5, '1', '105', 50, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `classroom_tool`
--

CREATE TABLE `classroom_tool` (
  `id_classroom` int(11) NOT NULL,
  `id_tool` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `classroom_tool`
--

INSERT INTO `classroom_tool` (`id_classroom`, `id_tool`) VALUES
(1, 1),
(3, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `daytime`
--

CREATE TABLE `daytime` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `daytime`
--

INSERT INTO `daytime` (`id`, `name`) VALUES
(1, 'Понеділок'),
(2, 'Вівторок'),
(3, 'Середа'),
(4, 'Четвер'),
(5, 'П\'ятниця');

-- --------------------------------------------------------

--
-- Структура таблицы `discipline`
--

CREATE TABLE `discipline` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `discipline`
--

INSERT INTO `discipline` (`id`, `name`) VALUES
(1, 'Основи дискретної математики'),
(2, 'Основи математичного аналізу'),
(3, 'Лінійна алгебра на геометрія'),
(4, 'Алгебра і теорія чисел'),
(5, 'Дискретна математика'),
(6, 'ОБДЗ');

-- --------------------------------------------------------

--
-- Структура таблицы `discipline_lecturer`
--

CREATE TABLE `discipline_lecturer` (
  `id_discipline` int(11) NOT NULL,
  `id_lecturer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `discipline_lecturer`
--

INSERT INTO `discipline_lecturer` (`id_discipline`, `id_lecturer`) VALUES
(6, 1),
(5, 3),
(6, 3),
(4, 4),
(4, 6),
(6, 6);

-- --------------------------------------------------------

--
-- Структура таблицы `discipline_student`
--

CREATE TABLE `discipline_student` (
  `id_discipline` int(11) NOT NULL,
  `id_student` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `discipline_student`
--

INSERT INTO `discipline_student` (`id_discipline`, `id_student`) VALUES
(4, 1),
(6, 3),
(4, 4),
(6, 6),
(4, 7),
(6, 7),
(5, 8),
(6, 8),
(6, 9),
(4, 10),
(6, 10),
(5, 11),
(6, 11),
(6, 12),
(4, 13),
(6, 13),
(5, 14),
(6, 14),
(4, 16),
(6, 16),
(5, 17),
(6, 17),
(4, 19),
(6, 19),
(5, 20),
(4, 21);

-- --------------------------------------------------------

--
-- Структура таблицы `lecturer`
--

CREATE TABLE `lecturer` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `lecturer`
--

INSERT INTO `lecturer` (`id`, `name`) VALUES
(1, 'Варвара Павлюк'),
(2, 'Людмила Лещенко'),
(3, 'Оксана Ситник'),
(4, 'Любов Макаренко'),
(5, 'Наталія Король'),
(6, 'Наталія');

-- --------------------------------------------------------

--
-- Структура таблицы `lesson`
--

CREATE TABLE `lesson` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `id_worksheet` int(11) NOT NULL,
  `id_room_type` int(11) NOT NULL,
  `id_discipline` int(11) NOT NULL,
  `id_lecturer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `lesson`
--

INSERT INTO `lesson` (`id`, `name`, `id_worksheet`, `id_room_type`, `id_discipline`, `id_lecturer`) VALUES
(7, 'АТЧ лекція', 1, 2, 4, 4),
(8, 'АТЧ практика', 1, 2, 4, 6),
(9, 'Дискретна Математика л.', 1, 2, 5, 3),
(10, 'Дискретна Математика пр.', 1, 2, 5, 3),
(11, 'ОБДЗ лекція', 1, 2, 6, 6);

-- --------------------------------------------------------

--
-- Структура таблицы `lesson_student`
--

CREATE TABLE `lesson_student` (
  `id_lesson` int(11) NOT NULL,
  `id_student` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `lesson_student`
--

INSERT INTO `lesson_student` (`id_lesson`, `id_student`) VALUES
(7, 1),
(11, 3),
(7, 4),
(11, 6),
(7, 7),
(11, 7),
(9, 8),
(10, 8),
(11, 8),
(11, 9),
(7, 10),
(8, 10),
(11, 10),
(9, 11),
(11, 11),
(11, 12),
(7, 13),
(11, 13),
(9, 14),
(10, 14),
(11, 14),
(7, 16),
(8, 16),
(11, 16),
(9, 17),
(10, 17),
(11, 17),
(7, 19),
(11, 19),
(9, 20),
(7, 21);

-- --------------------------------------------------------

--
-- Структура таблицы `lesson_tool`
--

CREATE TABLE `lesson_tool` (
  `id_lesson` int(11) NOT NULL,
  `id_tool` int(11) NOT NULL,
  `selection` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `lesson_tool`
--

INSERT INTO `lesson_tool` (`id_lesson`, `id_tool`, `selection`) VALUES
(7, 1, 1),
(9, 1, 1),
(10, 1, 0),
(11, 1, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `periodtime`
--

CREATE TABLE `periodtime` (
  `id` int(11) NOT NULL,
  `number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `periodtime`
--

INSERT INTO `periodtime` (`id`, `number`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6);

-- --------------------------------------------------------

--
-- Структура таблицы `restriction`
--

CREATE TABLE `restriction` (
  `id` int(11) NOT NULL,
  `id_discipline` int(11) DEFAULT NULL,
  `id_lecturer` int(11) DEFAULT NULL,
  `id_classroom` int(11) DEFAULT NULL,
  `id_daytime` int(11) DEFAULT NULL,
  `id_periodtime` int(11) DEFAULT NULL,
  `selection` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `restriction`
--

INSERT INTO `restriction` (`id`, `id_discipline`, `id_lecturer`, `id_classroom`, `id_daytime`, `id_periodtime`, `selection`) VALUES
(4, NULL, 6, NULL, 1, NULL, 1),
(5, NULL, 4, NULL, 1, NULL, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `room_type`
--

CREATE TABLE `room_type` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `room_type`
--

INSERT INTO `room_type` (`id`, `name`) VALUES
(1, 'Комп\'ютерний'),
(2, 'Стандартна');

-- --------------------------------------------------------

--
-- Структура таблицы `schedule`
--

CREATE TABLE `schedule` (
  `id_lesson` int(11) NOT NULL,
  `id_classroom` int(11) NOT NULL,
  `id_daytime` int(11) NOT NULL,
  `id_periodtime` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `schedule`
--

INSERT INTO `schedule` (`id_lesson`, `id_classroom`, `id_daytime`, `id_periodtime`) VALUES
(7, 3, 1, 1),
(8, 3, 1, 3),
(9, 3, 3, 2),
(11, 3, 1, 2),
(10, 4, 3, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `specialty`
--

CREATE TABLE `specialty` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `specialty`
--

INSERT INTO `specialty` (`id`, `name`) VALUES
(1, 'Програмна інженерія');

-- --------------------------------------------------------

--
-- Структура таблицы `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `course` int(11) NOT NULL,
  `id_specialty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `student`
--

INSERT INTO `student` (`id`, `name`, `course`, `id_specialty`) VALUES
(1, 'Вадим Іванчук', 1, 1),
(2, 'Омелян Заєць', 1, 1),
(3, 'Ілля Саєнко', 1, 1),
(4, 'Віктор Кухар', 1, 1),
(5, 'Альберт Фесенко', 1, 1),
(6, 'Остап Левчук', 1, 1),
(7, 'Петро Бурлака', 1, 1),
(8, 'Всеволод Ткач', 1, 1),
(9, 'Ігор Гончар', 1, 1),
(10, 'Степан Рудь', 1, 1),
(11, 'Айдер Миронюк', 1, 1),
(12, 'Кузьма Чайка', 1, 1),
(13, 'Яків Романчук', 1, 1),
(14, 'Юхим Буряк', 1, 1),
(15, 'Дем\'ян Марчук', 1, 1),
(16, 'Микола Черняк', 1, 1),
(17, 'Мирослав Скиба', 1, 1),
(18, 'Тарас Близнюк', 1, 1),
(19, 'Панас Медвідь', 1, 1),
(20, 'Федір Стасюк', 1, 1),
(21, 'Іван Стакан', 5, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `tool`
--

CREATE TABLE `tool` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tool`
--

INSERT INTO `tool` (`id`, `name`) VALUES
(1, 'Проектор');

-- --------------------------------------------------------

--
-- Структура таблицы `worksheet`
--

CREATE TABLE `worksheet` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `worksheet`
--

INSERT INTO `worksheet` (`id`, `name`) VALUES
(1, 'Розклад ФІ');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_room_type` (`id_room_type`);

--
-- Индексы таблицы `classroom_tool`
--
ALTER TABLE `classroom_tool`
  ADD PRIMARY KEY (`id_classroom`,`id_tool`),
  ADD KEY `id_tool` (`id_tool`);

--
-- Индексы таблицы `daytime`
--
ALTER TABLE `daytime`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `discipline`
--
ALTER TABLE `discipline`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `discipline_lecturer`
--
ALTER TABLE `discipline_lecturer`
  ADD PRIMARY KEY (`id_discipline`,`id_lecturer`),
  ADD KEY `id_lecturer` (`id_lecturer`);

--
-- Индексы таблицы `discipline_student`
--
ALTER TABLE `discipline_student`
  ADD PRIMARY KEY (`id_discipline`,`id_student`),
  ADD KEY `id_student` (`id_student`);

--
-- Индексы таблицы `lecturer`
--
ALTER TABLE `lecturer`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `lesson`
--
ALTER TABLE `lesson`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_worksheet` (`id_worksheet`),
  ADD KEY `id_room_type` (`id_room_type`),
  ADD KEY `id_discipline` (`id_discipline`),
  ADD KEY `id_lecturer` (`id_lecturer`);

--
-- Индексы таблицы `lesson_student`
--
ALTER TABLE `lesson_student`
  ADD PRIMARY KEY (`id_lesson`,`id_student`),
  ADD KEY `id_student` (`id_student`);

--
-- Индексы таблицы `lesson_tool`
--
ALTER TABLE `lesson_tool`
  ADD PRIMARY KEY (`id_lesson`,`id_tool`),
  ADD KEY `id_tool` (`id_tool`);

--
-- Индексы таблицы `periodtime`
--
ALTER TABLE `periodtime`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `restriction`
--
ALTER TABLE `restriction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_discipline` (`id_discipline`),
  ADD KEY `id_lecturer` (`id_lecturer`),
  ADD KEY `id_classroom` (`id_classroom`),
  ADD KEY `id_daytime` (`id_daytime`),
  ADD KEY `id_periodtime` (`id_periodtime`);

--
-- Индексы таблицы `room_type`
--
ALTER TABLE `room_type`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id_lesson`,`id_classroom`,`id_daytime`,`id_periodtime`),
  ADD KEY `id_classroom` (`id_classroom`),
  ADD KEY `id_daytime` (`id_daytime`),
  ADD KEY `id_periodtime` (`id_periodtime`);

--
-- Индексы таблицы `specialty`
--
ALTER TABLE `specialty`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_specialty` (`id_specialty`);

--
-- Индексы таблицы `tool`
--
ALTER TABLE `tool`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `worksheet`
--
ALTER TABLE `worksheet`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `classroom`
--
ALTER TABLE `classroom`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT для таблицы `daytime`
--
ALTER TABLE `daytime`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT для таблицы `discipline`
--
ALTER TABLE `discipline`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT для таблицы `lecturer`
--
ALTER TABLE `lecturer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT для таблицы `lesson`
--
ALTER TABLE `lesson`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT для таблицы `periodtime`
--
ALTER TABLE `periodtime`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT для таблицы `restriction`
--
ALTER TABLE `restriction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT для таблицы `room_type`
--
ALTER TABLE `room_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `specialty`
--
ALTER TABLE `specialty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT для таблицы `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT для таблицы `tool`
--
ALTER TABLE `tool`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT для таблицы `worksheet`
--
ALTER TABLE `worksheet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `classroom`
--
ALTER TABLE `classroom`
  ADD CONSTRAINT `classroom_ibfk_1` FOREIGN KEY (`id_room_type`) REFERENCES `room_type` (`id`) ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `classroom_tool`
--
ALTER TABLE `classroom_tool`
  ADD CONSTRAINT `classroom_tool_ibfk_1` FOREIGN KEY (`id_classroom`) REFERENCES `classroom` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `classroom_tool_ibfk_2` FOREIGN KEY (`id_tool`) REFERENCES `tool` (`id`) ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `discipline_lecturer`
--
ALTER TABLE `discipline_lecturer`
  ADD CONSTRAINT `discipline_lecturer_ibfk_1` FOREIGN KEY (`id_discipline`) REFERENCES `discipline` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `discipline_lecturer_ibfk_2` FOREIGN KEY (`id_lecturer`) REFERENCES `lecturer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `discipline_student`
--
ALTER TABLE `discipline_student`
  ADD CONSTRAINT `discipline_student_ibfk_1` FOREIGN KEY (`id_discipline`) REFERENCES `discipline` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `discipline_student_ibfk_2` FOREIGN KEY (`id_student`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `lesson`
--
ALTER TABLE `lesson`
  ADD CONSTRAINT `lesson_ibfk_1` FOREIGN KEY (`id_worksheet`) REFERENCES `worksheet` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lesson_ibfk_2` FOREIGN KEY (`id_room_type`) REFERENCES `room_type` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `lesson_ibfk_3` FOREIGN KEY (`id_discipline`) REFERENCES `discipline` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lesson_ibfk_4` FOREIGN KEY (`id_lecturer`) REFERENCES `lecturer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `lesson_student`
--
ALTER TABLE `lesson_student`
  ADD CONSTRAINT `lesson_student_ibfk_1` FOREIGN KEY (`id_lesson`) REFERENCES `lesson` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lesson_student_ibfk_2` FOREIGN KEY (`id_student`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `lesson_tool`
--
ALTER TABLE `lesson_tool`
  ADD CONSTRAINT `lesson_tool_ibfk_1` FOREIGN KEY (`id_lesson`) REFERENCES `lesson` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lesson_tool_ibfk_2` FOREIGN KEY (`id_tool`) REFERENCES `tool` (`id`) ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `restriction`
--
ALTER TABLE `restriction`
  ADD CONSTRAINT `restriction_ibfk_1` FOREIGN KEY (`id_discipline`) REFERENCES `discipline` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `restriction_ibfk_2` FOREIGN KEY (`id_lecturer`) REFERENCES `lecturer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `restriction_ibfk_3` FOREIGN KEY (`id_classroom`) REFERENCES `classroom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `restriction_ibfk_4` FOREIGN KEY (`id_daytime`) REFERENCES `daytime` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `restriction_ibfk_5` FOREIGN KEY (`id_periodtime`) REFERENCES `periodtime` (`id`) ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`id_lesson`) REFERENCES `lesson` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `schedule_ibfk_2` FOREIGN KEY (`id_classroom`) REFERENCES `classroom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `schedule_ibfk_3` FOREIGN KEY (`id_daytime`) REFERENCES `daytime` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `schedule_ibfk_4` FOREIGN KEY (`id_periodtime`) REFERENCES `periodtime` (`id`) ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`id_specialty`) REFERENCES `specialty` (`id`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
