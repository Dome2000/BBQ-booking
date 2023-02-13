-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 16, 2021 at 06:04 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `haruto`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_username` varchar(10) NOT NULL,
  `customer_password` varchar(10) NOT NULL,
  `customer_name` varchar(99) NOT NULL,
  `customer_gender` varchar(10) NOT NULL,
  `customer_tel` varchar(10) NOT NULL,
  `customer_email` varchar(99) NOT NULL,
  `customer_address` varchar(999) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_username`, `customer_password`, `customer_name`, `customer_gender`, `customer_tel`, `customer_email`, `customer_address`) VALUES
('dome', '123', 'Dome peeraphat', 'Male', '0956510666', 'peeraphat.ch@kkumail.com', 'thailand'),
('jimmy', '123', 'siriprapa', 'Female', '0943758462', 'siriprapa@kku.ac.th', '35 ม.2 ต.เมืองพล อ.พล จ.ขอนแก่น 40120'),
('mark', 'mark', 'Mr.mark', 'Male', '0123456789', 'mark@gmail.com', 'USA');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_username` varchar(10) NOT NULL,
  `employee_password` varchar(10) NOT NULL,
  `employee_name` varchar(99) NOT NULL,
  `employee_gender` varchar(10) NOT NULL,
  `employee_tel` varchar(10) NOT NULL,
  `employee_email` varchar(99) NOT NULL,
  `employee_address` varchar(999) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_username`, `employee_password`, `employee_name`, `employee_gender`, `employee_tel`, `employee_email`, `employee_address`) VALUES
('admin', 'admin', 'peeraphat charoenthai', 'Male', '0956510666', 'adim@gmail.com', 'USA');

-- --------------------------------------------------------

--
-- Table structure for table `reserve`
--

CREATE TABLE `reserve` (
  `reserve_id` int(10) NOT NULL,
  `reserve_date` varchar(99) NOT NULL,
  `reserve_time` varchar(99) NOT NULL,
  `total_price` int(10) NOT NULL,
  `count` int(10) NOT NULL,
  `customer_username` varchar(99) NOT NULL,
  `employee_username` varchar(99) NOT NULL,
  `status_id` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reserve`
--

INSERT INTO `reserve` (`reserve_id`, `reserve_date`, `reserve_time`, `total_price`, `count`, `customer_username`, `employee_username`, `status_id`) VALUES
(10, '10/16/21', '17:00', 596, 4, 'dome', 'null', 3),
(11, '10/16/21', '17:20', 447, 3, 'dome', 'null', 2),
(12, '10/17/21', '17:00', 149, 1, 'dome', 'null', 1),
(13, '10/15/21', '17:30', 447, 3, 'jimmy', 'null', 1),
(14, '10/27/21', '17:02', 298, 2, 'jimmy', 'null', 1),
(15, '10/24/21', '11:03', 298, 2, 'mark', 'null', 1);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `status_id` int(10) NOT NULL,
  `status_reserve` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`status_id`, `status_reserve`) VALUES
(1, 'รอยืนยัน'),
(2, 'รอคิว'),
(3, 'สำเร็จ'),
(4, 'ยกเลิก');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_username`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_username`);

--
-- Indexes for table `reserve`
--
ALTER TABLE `reserve`
  ADD PRIMARY KEY (`reserve_id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`status_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reserve`
--
ALTER TABLE `reserve`
  MODIFY `reserve_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `status_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
