-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 17, 2018 at 11:28 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- com.foodorderingapp.Database: `foodorderingappdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_food`
--

CREATE TABLE `tbl_food` (
  `food_id` int(11) NOT NULL,
  `food_name` varchar(25) NOT NULL,
  `food_price` int(11) NOT NULL,
  `restaurant_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_food`
--

INSERT INTO `tbl_food` (`food_id`, `food_name`, `food_price`, `restaurant_id`) VALUES
(10, 'food3a', 100, 3),
(11, 'food3b', 100, 3),
(12, 'food3c', 100, 3),
(13, 'food4a', 100, 4),
(14, 'food4b', 100, 4),
(15, 'food4c', 100, 4),
(16, 'food5a', 100, 5),
(17, 'food5b', 100, 5),
(18, 'food5c', 100, 5),
(19, 'food6a', 100, 6),
(20, 'food6b', 100, 6),
(21, 'food6c', 100, 6);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_orders`
--

CREATE TABLE `tbl_orders` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `confirm` tinyint(4) NOT NULL DEFAULT '0',
  `ordered_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_orders`
--

INSERT INTO `tbl_orders` (`order_id`, `user_id`, `confirm`, `ordered_date`) VALUES
(21, 4, 1, '2018-01-14 10:35:30'),
(22, 3, 1, '2018-01-14 10:35:50'),
(23, 3, 1, '2018-01-15 10:34:27'),
(24, 3, 1, '2018-01-15 10:34:35'),
(25, 3, 1, '2018-01-16 10:14:58'),
(26, 3, 0, '2018-01-14 10:41:12'),
(27, 2, 0, '2018-01-14 10:41:38'),
(28, 1, 0, '2018-01-15 06:29:06'),
(29, 1, 0, '2018-01-15 06:30:18'),
(30, 1, 0, '2018-01-15 10:18:59'),
(31, 1, 0, '2018-01-15 10:19:20'),
(32, 1, 0, '2018-01-15 10:19:27');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_order_detail`
--

CREATE TABLE `tbl_order_detail` (
  `order_detail_id` int(11) NOT NULL,
  `food_name` varchar(25) DEFAULT NULL,
  `food_price` varchar(25) DEFAULT NULL,
  `restaurant_name` varchar(25) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_order_detail`
--

INSERT INTO `tbl_order_detail` (`order_detail_id`, `food_name`, `food_price`, `restaurant_name`, `quantity`, `order_id`) VALUES
(22, 'food6a', '100', 'res6', 1, 21),
(23, 'food6a', '100', 'res6', 1, 21),
(24, 'food3a', '100', 'res3', 1, 22),
(25, 'food3a', '100', 'res3', 1, 23),
(26, 'food3a', '100', 'res3', 1, 24),
(27, 'food3a', '100', 'res3', 1, 25),
(28, 'food3a', '100', 'res3', 1, 26),
(29, 'food3a', '100', 'res3', 1, 27),
(30, 'food3a', '100', 'res2', 1, 27),
(31, 'food3a', '100', 'res3', 1, 29),
(32, 'food4a', '100', 'res4', 1, 29),
(33, 'food3a', '100', 'res3', 1, 32),
(34, 'food4a', '100', 'res2', 1, 32);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_restaurant`
--

CREATE TABLE `tbl_restaurant` (
  `restaurant_id` int(11) NOT NULL,
  `restaurant_address` varchar(255) NOT NULL,
  `restaurant_contact` varchar(255) NOT NULL,
  `status` bit(1) NOT NULL DEFAULT b'0',
  `restaurant_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_restaurant`
--

INSERT INTO `tbl_restaurant` (`restaurant_id`, `restaurant_address`, `restaurant_contact`, `status`, `restaurant_name`) VALUES
(3, 'add1', 'cont1', b'1', 'res1'),
(4, 'add2', 'cont2', b'1', 'res2'),
(5, 'add3', 'cont3', b'1', 'res3'),
(6, 'add4', 'cont4', b'1', 'res4'),
(7, 'add4', '676767', b'1', 'rest4');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

CREATE TABLE `tbl_users` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `contact_no` varchar(255) NOT NULL,
  `user_role` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `balance` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_users`
--

INSERT INTO `tbl_users` (`user_id`, `first_name`, `middle_name`, `last_name`, `user_password`, `email`, `contact_no`, `user_role`, `address`, `balance`) VALUES
(1, 'bikram', NULL, 'samhohang', 'bikram', 'biram@yahoo.com', '981747174', 'user', 'jhapa', 8700),
(2, 'nitesh', NULL, 'shrestha', 'nitesh', 'nitesh@yahoo.com', '981747174', 'user', 'ktm', 900),
(3, 'roshan', NULL, 'shrestha', 'nitesh', 'roshan@yahoo.com', '981747174', 'user', 'ktm', 200),
(4, 'hari', NULL, 'gc', 'hari', 'hari@yahoo.com', '9815353535', 'user', 'ktm', 400);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_food`
--
ALTER TABLE `tbl_food`
  ADD PRIMARY KEY (`food_id`),
  ADD KEY `restaurant_id` (`restaurant_id`);

--
-- Indexes for table `tbl_orders`
--
ALTER TABLE `tbl_orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `tbl_order_detail`
--
ALTER TABLE `tbl_order_detail`
  ADD PRIMARY KEY (`order_detail_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `tbl_restaurant`
--
ALTER TABLE `tbl_restaurant`
  ADD PRIMARY KEY (`restaurant_id`);

--
-- Indexes for table `tbl_users`
--
ALTER TABLE `tbl_users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_food`
--
ALTER TABLE `tbl_food`
  MODIFY `food_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `tbl_orders`
--
ALTER TABLE `tbl_orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `tbl_order_detail`
--
ALTER TABLE `tbl_order_detail`
  MODIFY `order_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `tbl_restaurant`
--
ALTER TABLE `tbl_restaurant`
  MODIFY `restaurant_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tbl_users`
--
ALTER TABLE `tbl_users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_food`
--
ALTER TABLE `tbl_food`
  ADD CONSTRAINT `tbl_food_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `tbl_restaurant` (`restaurant_id`);

--
-- Constraints for table `tbl_orders`
--
ALTER TABLE `tbl_orders`
  ADD CONSTRAINT `tbl_orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`user_id`);

--
-- Constraints for table `tbl_order_detail`
--
ALTER TABLE `tbl_order_detail`
  ADD CONSTRAINT `tbl_order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `tbl_orders` (`order_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
