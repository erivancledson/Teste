-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 23/11/2020 às 03:00
-- Versão do servidor: 10.4.13-MariaDB
-- Versão do PHP: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `teste`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `cargo`
--

CREATE TABLE `cargo` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Despejando dados para a tabela `cargo`
--

INSERT INTO `cargo` (`id`, `name`) VALUES
(1, 'Assistente de T.I'),
(2, 'Gerente T.I'),
(4, 'Analista de T.I'),
(5, 'Assistente de RH'),
(6, 'Analista de RH'),
(7, 'Analista Fiscal');

-- --------------------------------------------------------

--
-- Estrutura para tabela `departamento`
--

CREATE TABLE `departamento` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Despejando dados para a tabela `departamento`
--

INSERT INTO `departamento` (`id`, `name`) VALUES
(2, 'RH'),
(3, 'T.I'),
(4, 'CONTABILIDADE'),
(5, 'ADMINISTRATIVO'),
(6, 'FATURAMENTO');

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `id` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `birthdate` datetime(6) DEFAULT NULL,
  `document` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `cargo_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Despejando dados para a tabela `funcionario`
--

INSERT INTO `funcionario` (`id`, `age`, `birthdate`, `document`, `name`, `cargo_id`) VALUES
(1, 32, '1993-11-22 22:07:28.000000', '111111', 'ERIVAN', 4);

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario_departamento`
--

CREATE TABLE `funcionario_departamento` (
  `funcionario_id` int(11) NOT NULL,
  `departamento_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Despejando dados para a tabela `funcionario_departamento`
--

INSERT INTO `funcionario_departamento` (`funcionario_id`, `departamento_id`) VALUES
(1, 4),
(1, 3);

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `departamento`
--
ALTER TABLE `departamento`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1mmiur94efkujcsaab0cdgskv` (`cargo_id`);

--
-- Índices de tabela `funcionario_departamento`
--
ALTER TABLE `funcionario_departamento`
  ADD KEY `FKtpyo8d0x9ym7dqnlqmlkorohp` (`departamento_id`),
  ADD KEY `FK43lec5ukndlwsw7spw4nqhgi0` (`funcionario_id`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `cargo`
--
ALTER TABLE `cargo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `departamento`
--
ALTER TABLE `departamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `FK1mmiur94efkujcsaab0cdgskv` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`id`);

--
-- Restrições para tabelas `funcionario_departamento`
--
ALTER TABLE `funcionario_departamento`
  ADD CONSTRAINT `FK43lec5ukndlwsw7spw4nqhgi0` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`),
  ADD CONSTRAINT `FKtpyo8d0x9ym7dqnlqmlkorohp` FOREIGN KEY (`departamento_id`) REFERENCES `departamento` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
