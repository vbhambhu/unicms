-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Host: localhost:3307
-- Generation Time: Apr 25, 2016 at 09:47 AM
-- Server version: 5.6.25
-- PHP Version: 7.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `viwaa`
--

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `category_name`) VALUES
(1, 'Spring'),
(2, 'Html'),
(3, 'Categoryone'),
(4, 'Categorytwo');

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `title`, `meta_description`, `slug`, `content`, `category_id`, `user_id`, `status`, `updated_at`, `created_at`) VALUES
(1, 'hello dajana', NULL, 'hello-dajana', 'fsdsa dadad adad dasdas dasd a dadad asd', 1, 0, 1, NULL, '2016-02-21 09:36:15'),
(2, 'Hell vinod', NULL, 'hell-vinod', 'dasdada', 1, 0, 1, NULL, '2016-02-21 09:43:24'),
(3, 'sdadadadadad', NULL, 'sdadddadadadadad', 'dsdadadadsada', 2, 0, 0, NULL, '2016-04-23 10:09:19'),
(4, 'adssdsdadada', NULL, 'adssdsdadada', 'sdaada', 1, 0, 0, NULL, '2016-04-23 10:09:44');

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `first_name`, `last_name`, `role_id`, `status`, `updated_at`, `created_at`) VALUES
(1, 'vinodk', 'v.bhambhu083@gmail.com', '$2a$10$tTVFkOoe/z03A82lXpmQEuMM9AyhF8XCpJX2ckYoAhta93XWLCZ1S', 'vinod', 'kumar', 5, 1, '2016-04-23 19:41:23', '2016-04-23 19:16:03'),
(2, 'bharat', 'bhamrat@gmail.com', '$2a$10$vZJVxxDUnF3UEFhR1LaGu./tV8vaQ6eVpHSo48/0RK9dr.NnaEe9O', 'Bharat', 'Gandass', 5, 1, '2016-04-23 19:45:03', '2016-04-23 19:18:46');

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`role_id`, `role_name`) VALUES
(1, 'Subscriber'),
(2, 'Contributor'),
(3, 'Author'),
(4, 'Editor'),
(5, 'Administrator');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
