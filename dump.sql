-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               10.0.14-MariaDB - mariadb.org binary distribution
-- ОС Сервера:                   Win32
-- HeidiSQL Версия:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных librarydb
CREATE DATABASE IF NOT EXISTS `librarydb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `librarydb`;


-- Дамп структуры для таблица librarydb.books
CREATE TABLE IF NOT EXISTS `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(250) NOT NULL DEFAULT '0',
  `author` varchar(250) NOT NULL DEFAULT '0',
  `publication` varchar(50) NOT NULL DEFAULT '0',
  `publication_year` int(11) NOT NULL DEFAULT '2000',
  `in_stock` int(11) unsigned NOT NULL DEFAULT '0',
  `amount` int(11) unsigned NOT NULL DEFAULT '0',
  `delete_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `book_name_author_publication_publication_year_delete_at` (`book_name`,`author`,`publication`,`publication_year`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы librarydb.books: ~26 rows (приблизительно)
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
REPLACE INTO `books` (`id`, `book_name`, `author`, `publication`, `publication_year`, `in_stock`, `amount`, `delete_at`) VALUES
	(7, 'A Tour of C++', 'Bjarne Stroustrup', 'publivation', 2013, 6, 6, NULL),
	(8, 'Managing the Testing Process', 'Black, Rex', 'CRC Press', 2009, 6, 6, NULL),
	(9, 'Android для программистов. Создаем приложения', 'Datel ', 'Питер', 2012, 5, 6, NULL),
	(10, 'The Busy Coder\'s Guide to Android Development', 'Mark L. Murphy  ', '', 2009, 6, 6, NULL),
	(11, 'HTML5, JavaScript and jQuery', 'Cameron D.  ', '', 2015, 6, 6, NULL),
	(12, 'iOS and OS X Network Programming Cookbook', 'Jon Hoffman  ', 'ASCV', 2014, 6, 6, NULL),
	(13, 'Yii 2 For Beginners / Yii 2 для водопроводчиков.', 'Bill Keck  ', '', 2008, 6, 6, NULL),
	(14, 'Самоучитель Microsoft Visual Studio C++ и MFC', 'Сидорина Т.Л.', '', 2001, 6, 6, NULL),
	(16, 'Eclipse 4 Plug-in Development by Example', 'Dr Alex Blewitt  ', '', 2000, 6, 6, NULL),
	(22, '01. Кухня народов мира - Десерт ', 'Валерий Жданов', '	Интернет-издание', 2013, 6, 6, NULL),
	(23, '	100 лучших блюд из птицы ', '	Выдревич Г.С.', 'Эксмо, Терция', 2008, 6, 6, NULL),
	(24, 'Блюда с грибами', '	Санина И.', 'Аргумент Принт', 2013, 6, 6, NULL),
	(25, 'Борщи и щи (Любимые блюда) ', '', 'Аркаим', 2010, 5, 6, NULL),
	(26, 'Букет домашних вин ', '	Л.Ю. Сергеев В.В. Валик В.В. Ботан (составители)', 'Днепропетровск', 2000, 10, 10, NULL),
	(27, 'Анатомический атлас. Функциональные системы человека ', 'Лютьен-Дреколль Э. Рохен Й.', 'Москва: АСТ', 2000, 6, 6, NULL),
	(28, 'Коньяк, виски, текила, абсент... ', '	Гусев И.Е.', '	Харвест', 2004, 6, 6, NULL),
	(29, 'Лечебная физическая культура ', 'Попов C.Н.', '	Академия', 2004, 6, 6, NULL),
	(30, '200 избранных схем электроники ', 'Мэндл М.', 'Мир, 2-е издание', 1985, 6, 6, NULL),
	(31, 'Схемотехника электронных систем. Цифровые устройства ', 'Бойко В.И.', 'ГИЭИ', 2004, 10, 10, NULL),
	(32, '	Юный радиолюбитель ', 'Борисов В.Г.', '	Радио и связь, 7-е издание', 1985, 10, 10, NULL),
	(38, 'Карма', 'Толстой Лев Николаевич', 'Петербург', 1984, 5, 5, NULL),
	(40, 'Правила и тонкости кухни', 'Вильям Похлебкин', 'СупишкО', 2008, 10, 10, NULL),
	(42, 'Гастрономическая энциклопедия Larousse Gastronomique', '', '', 1938, 10, 10, NULL),
	(43, 'Особенности составления заключения эксперта при выполнении дактилоскопических экспертиз', 'В.А. Ивашков ', ' Изд-во ЭКЦ МВД ', 1999, 10, 10, NULL),
	(44, 'Хатха-йога для начинающих', ' Зубков А.Н, Очаповский А.П.', 'Медицина', 1991, 10, 10, NULL),
	(46, 'Горе от ума', 'А.С.Грибоедов', '', 1825, 5, 5, NULL),
	(47, 'Черновик', 'С. Лукьяненко', 'М', 2000, 5, 5, NULL),
	(48, 'Benjamin Franklin: An American Life', 'Walter Isaacson', 'NY', 2004, 2, 2, NULL),
	(49, 'Незнайка на Луне', 'Н.Н.Носов', 'Свирта', 1965, 10, 10, NULL),
	(50, ' Le avventure di Cipollino', 'Gianni Rodari', 'Napoli', 1957, 10, 10, NULL);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;


-- Дамп структуры для таблица librarydb.categories
CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы librarydb.categories: ~5 rows (приблизительно)
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
REPLACE INTO `categories` (`id`, `category_name`) VALUES
	(3, 'cooking'),
	(5, 'electronics'),
	(2, 'fiction'),
	(4, 'medecine'),
	(1, 'programming');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;


-- Дамп структуры для таблица librarydb.categories_books
CREATE TABLE IF NOT EXISTS `categories_books` (
  `category_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  KEY `FK_catalogue_books_books` (`book_id`),
  KEY `FK_catalogue_books_catalogue` (`category_id`),
  CONSTRAINT `FK_catalogue_books_books` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `FK_catalogue_books_catalogue` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы librarydb.categories_books: ~18 rows (приблизительно)
/*!40000 ALTER TABLE `categories_books` DISABLE KEYS */;
REPLACE INTO `categories_books` (`category_id`, `book_id`) VALUES
	(3, 25),
	(3, 24),
	(1, 7),
	(1, 9),
	(1, 10),
	(1, 16),
	(3, 26),
	(1, 11),
	(1, 12),
	(1, 8),
	(1, 13),
	(1, 14),
	(4, 27),
	(4, 28),
	(4, 29),
	(5, 32),
	(5, 31),
	(5, 30),
	(2, 38),
	(3, 40),
	(3, 42),
	(4, 43),
	(4, 44),
	(2, 46),
	(2, 47),
	(2, 48),
	(2, 49),
	(2, 50);
/*!40000 ALTER TABLE `categories_books` ENABLE KEYS */;


-- Дамп структуры для таблица librarydb.fines
CREATE TABLE IF NOT EXISTS `fines` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `status` enum('OPEN','CLOSED') NOT NULL DEFAULT 'OPEN',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_fines_users_books` (`order_id`),
  KEY `FK_fines_users` (`user_id`),
  CONSTRAINT `FK_fines_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_fines_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы librarydb.fines: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `fines` DISABLE KEYS */;
/*!40000 ALTER TABLE `fines` ENABLE KEYS */;


-- Дамп структуры для таблица librarydb.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `book_id` int(11) NOT NULL,
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `return_date` datetime DEFAULT NULL,
  `status` enum('DONE','BOOKED','REQUESTED','READING_ROOM','LOST') NOT NULL DEFAULT 'REQUESTED',
  PRIMARY KEY (`id`),
  KEY `FK__books` (`book_id`),
  KEY `FK_orders_users` (`user_id`),
  CONSTRAINT `FK_orders_books` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `FK_orders_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы librarydb.orders: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


-- Дамп структуры для таблица librarydb.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(250) NOT NULL,
  `last_name` varchar(250) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(128) NOT NULL,
  `role` enum('administrator','librarian','reader') NOT NULL DEFAULT 'reader',
  `banned_at` datetime DEFAULT NULL,
  `delete_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_delete_at` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы librarydb.users: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
REPLACE INTO `users` (`id`, `first_name`, `last_name`, `email`, `password`, `role`, `banned_at`, `delete_at`) VALUES
	(1, 'admin', 'adminnovich', 'admin@admin.ua', '4DFF4EA340F0A823F15D3F4F01AB62EAE0E5DA579CCB851F8DB9DFE84C58B2B37B89903A740E1EE172DA793A6E79D560E5F7F9BD058A12A280433ED6FA46510A', 'administrator', NULL, NULL),
	(2, 'user', 'user', 'user@user.ua', '3BAFBF08882A2D10133093A1B8433F50563B93C14ACD05B79028EB1D12799027241450980651994501423A66C276AE26C43B739BC65C4E16B10C3AF6C202AEBB', 'reader', NULL, NULL),
	(3, 'librarian', 'librarian', 'librarian@librarian.ua', '40B244112641DD78DD4F93B6C9190DD46E0099194D5A44257B7EFAD6EF9FF4683DA1EDA0244448CB343AA688F5D3EFD7314DAFE580AC0BCBF115AECA9E8DC114', 'librarian', NULL, NULL),
	(6, 'Alekseytsev', 'Anton', 'alekseytsev_a@ymail.com', 'A885C69E49C167EBAA1948AEDBA2152C93605D80C2043B82DA777BEC9883E1EE1E9C502225EC5F4FFACB6EDC46D80777B3F7E29A859D0F2B0F077531F12BCD2D', 'reader', NULL, NULL),
	(7, 'Вася ', 'Пупкин', 'pupa@gmail.com', '73311CC74616462B6CDE0CF060760ACE4F8A02C56EE40641E7129DF669CC67788411A6F648D3D24EE30F092F18053969B84215F2AFF7D4DDA0B3C50971EE3D15', 'reader', NULL, NULL),
	(8, 'Walt', 'Dysney', 'walt@yahoo.com', '928563141A960C525920CDE9B573CD45BB71C47ACEE2F54EA1CC853ECC6742D6694EAF9F555BE1AA4920FE29921FF8CE8382F29B793FDBA613813A9C38EBA93F', 'reader', NULL, NULL),
	(9, 'Malvina', 'Artemonova', 'malvina@in.ua', '6164556F6DF5ABA8EEB47B349942AB6A3F6994E245EE4FEAC3705CFC1385C53B6FDB7B5C5587EE6DA6C122A2D06C4B4C4D8D6E9E3973A2E601C61B8099898159', 'reader', NULL, NULL),
	(10, 'Test1', 'Test!@#%^&*()', 'testanduz10@gmail.com', 'B9B838709456C3B9A738F87E4DEFFB17EB9A69297729FE356C45AD0D4BCD31FA80E0B7778D3C5DF9BC3DA6DC07BFFD5DDE3B77C697B76D08C738EC7A6DC52FD1', 'reader', NULL, NULL),
	(11, 'بي', 'بي', 'de@fr.fr', '4DFF4EA340F0A823F15D3F4F01AB62EAE0E5DA579CCB851F8DB9DFE84C58B2B37B89903A740E1EE172DA793A6E79D560E5F7F9BD058A12A280433ED6FA46510A', 'reader', NULL, NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
