-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 15, 2026 at 07:53 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wct`
--

-- --------------------------------------------------------

--
-- Table structure for table `animals`
--

CREATE TABLE `animals` (
  `kode` varchar(6) NOT NULL,
  `animal_id` int(11) NOT NULL,
  `species_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `gender` enum('male','female','unknown') DEFAULT NULL,
  `estimated_age` int(11) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `status_animal` enum('healthy','unwell','emergency','dead') DEFAULT 'healthy',
  `photo_url` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `animals`
--

INSERT INTO `animals` (`kode`, `animal_id`, `species_id`, `name`, `gender`, `estimated_age`, `date_of_birth`, `weight`, `status_animal`, `photo_url`) VALUES
('A0001', 1, 1, 'Leci', 'female', 7, '0000-00-00', 160, 'healthy', 'Images/Leci.jpeg'),
('A0002', 2, 1, 'Nino', 'male', 8, '0000-00-00', 110.2, 'unwell', 'Images/Nino.jpeg'),
('A0003', 3, 1, 'Lala', 'female', 7, '0000-00-00', 88.7, 'unwell', 'Images/Lala.jpg'),
('A0004', 4, 1, 'Soto', 'male', 9, '0000-00-00', 120, 'healthy', 'Images/Soto.jpg'),
('A0005', 5, 1, 'Koya', 'female', 6, '0000-00-00', 85.3, 'unwell', 'Images/Koya.jpeg'),
('B0001', 6, 2, 'Bumi', 'male', 18, '0000-00-00', 48, 'healthy', 'Images/Orang-utan.jpeg'),
('B0002', 7, 2, 'Hutan', 'male', 25, '0000-00-00', 57, 'healthy', 'Images/Orangutan sitting in the grass.jpeg'),
('B0003', 8, 2, 'Laut', 'female', 20, '0000-00-00', 45, 'unwell', 'Images/orang utann.jpeg'),
('C0001', 9, 3, 'Duri', 'female', 5, '0000-00-00', 0.5, 'healthy', 'Images/LANDAK_JAWA.jpeg'),
('C0002', 10, 3, 'Dura', 'male', 5, '0000-00-00', 0.734, 'healthy', 'Images/1081789_720.jpg'),
('C0003', 11, 3, 'Duro', 'male', 8, '0000-00-00', 1.02, 'unwell', 'Images/landak.jpg'),
('D0001', 12, 4, 'Hadi', 'male', 7, '0000-00-00', 20, 'emergency', 'Images/bekantan.jpeg'),
('D0002', 13, 4, 'Rudi', 'male', 7, '0000-00-00', 18, 'healthy', 'Images/bekantan2.jpg'),
('D0003', 14, 4, 'Bedi', 'male', 6, '0000-00-00', 17, 'healthy', 'Images/450.jpeg'),
('E0001', 15, 5, 'Vida', 'female', 20, '0000-00-00', 268, 'healthy', 'Images/anoa.jpeg'),
('E0002', 16, 5, 'Ola', 'female', 25, '0000-00-00', 300, 'healthy', 'Images/anoa2.jpeg'),
('E0003', 17, 5, 'Yani', 'female', 0, '0000-00-00', 280, 'healthy', 'Images/anoa3.jpg'),
('F0001', 18, 6, 'Niri', 'male', 45, '0000-00-00', 2000, 'healthy', 'Images/badak.jpg'),
('F0002', 19, 6, 'Rina', 'female', 30, '0000-00-00', 1500, 'healthy', 'Images/badak2.jpg'),
('F0003', 20, 6, 'Sari', 'female', 35, '0000-00-00', 1850, 'emergency', 'Images/badak3.jpg'),
('G0001', 21, 7, 'Lontong', 'male', 55, '0000-00-00', 3000, 'healthy', 'Images/gajah.jpg'),
('G0002', 22, 7, 'Nasi', 'male', 60, '0000-00-00', 4200, 'healthy', 'Images/gajah2.jpg'),
('G0003', 23, 7, 'Bubur', 'female', 69, '0000-00-00', 27, 'unwell', 'Images/gajah3.jpeg'),
('G0004', 64, 7, 'Bibi', 'female', 1, '2024-11-24', 230, 'healthy', 'Images/1750749855243_Bibi.jpg'),
('E0004', 65, 5, 'Rati', 'female', 1, '2024-01-24', 135, 'healthy', 'Images/1750750335468_download (4).jpg'),
('F0004', 66, 6, 'Lili', 'female', 1, '2025-06-23', 100, NULL, 'uploads/default.jpeg'),
('B0004', 71, 2, 'Milo', 'male', 1, '2024-01-25', 150, 'emergency', 'Images/1750836337310_orangutanbayi.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `animal_behavior`
--

CREATE TABLE `animal_behavior` (
  `behavior_id` int(11) NOT NULL,
  `behavior_code` varchar(6) NOT NULL,
  `animal_id` int(11) NOT NULL,
  `daily_activity` text DEFAULT NULL,
  `diet` text DEFAULT NULL,
  `social_behavior` text DEFAULT NULL,
  `mating_season` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `animal_behavior`
--

INSERT INTO `animal_behavior` (`behavior_id`, `behavior_code`, `animal_id`, `daily_activity`, `diet`, `social_behavior`, `mating_season`) VALUES
(1, 'BH001', 1, 'Berjalan-jalan hehe', 'Daging', 'Berburu berkelompok', 'Oktober'),
(2, 'BH002', 2, 'Aktif di malam hari, menjelajah wilayah luas.', 'Karnivora, suka rusa dan babi hutan.', 'Soliter, agresif terhadap pejantan lain.', 'Mei'),
(3, 'BH003', 3, 'Nokturnal, sering beristirahat di gua.', 'Karnivora, terutama hewan kecil.', 'Soliter, hanya berinteraksi saat musim kawin.', 'Agustus'),
(4, 'BH004', 4, 'Sering terlihat di siang hari, kurang aktif karena cedera.', 'Karnivora, nafsu makan menurun.', 'Soliter.', 'Juni'),
(5, 'BH005', 5, 'Nokturnal, aktif berburu di malam hari.', 'Karnivora, makanan utama rusa.', 'Soliter.', 'September'),
(6, 'BH006', 6, 'Lebih aktif pada pagi dan sore hari. Menghabiskan sebagian besar waktunya di atas pohon (arboreal), berpindah dari pohon ke pohon menggunakan lengan panjangnya.', 'Herbivora; Memakan buah-buahan, daun muda, bunga, kulit kayu, dan kadang serangga kecil.', 'Soliter, tetapi memiliki interaksi sosial terbatas, terutama antara induk dan anak, atau saat musim kawin. Jantan dewasa cenderung menghindari satu sama lain.', 'Maret - Juni'),
(7, 'BH007', 7, 'Aktif di pagi dan siang hari. Lebih banyak diam dan mengamati lingkungan dari atas kanopi. Membuat sarang dari ranting setiap malam dan kerap menggunakan sarang yang sama selama beberapa hari.', 'Herbivora; Memakan buah-buahan hutan primer, seperti durian hutan, langsat.', 'Soliter. Hanya berinteraksi dengan individu lain saat masa kawin atau ketika induk merawat anaknya. Cenderung teritorial terhadap jantan lain.', 'Juli - September'),
(8, 'BH008', 8, 'Aktif sepanjang pagi hingga menjelang sore. Sering terlihat mencari makan di pohon rendah dan sesekali di tanah. Pandai membuat sarang rumit dari dedaunan dan cabang.', 'Herbivora; Memakan buah-buahan lokal seperti manggis hutan, rambutan liar, dan pisang hutan.', 'Soliter. Lebih sensitif terhadap kehadiran manusia dan mudah terganggu. Induk betina sangat protektif terhadap anaknya.', 'April'),
(9, 'BH009', 9, 'Nocturnal — aktif di malam hari. Menghabiskan waktu siang hari di liang atau tempat gelap, lalu keluar pada malam hari untuk mencari makan.', 'Omnivora; lebih dominan herbivora. Makan akar-akaran, umbi, buah jatuh, dan kulit pohon.', 'Soliter. Hidup sendiri atau berpasangan dalam satu liang. Akan bersikap sangat defensif jika terancam, namun jarang menyerang secara aktif.', 'Februari - April'),
(10, 'BH010', 10, 'Hewan nokturnal — aktif saat malam hari. Biasanya keluar dari sarang bebatuan atau liang alami saat senja, lalu kembali menjelang fajar.', 'Herbivora; Memakan akar-akaran, daun muda, biji-bijian, dan buah jatuh.', 'Soliter namun toleran terhadap individu lain di sekitar sarang yang kaya sumber daya. Bersifat defensif, tidak agresif kecuali terancam.', 'Oktober'),
(11, 'BH011', 11, 'Aktif pada malam hari (nokturnal), sering terlihat menyusuri tepi sungai kecil di hutan dataran rendah.', 'Herbivora; Mengonsumsi umbi-umbian, akar pohon muda, buah hutan, serta beberapa jenis jamur.', 'Soliter, sangat jarang berinteraksi dengan sesama kecuali saat musim kawin.', 'April - Juni'),
(12, 'BH012', 12, 'Diurnal — aktif pada pagi hingga sore. Sering berpindah dari pohon ke pohon di hutan bakau dan rawa. Beristirahat di dahan tinggi saat siang terik.', 'Folivora dan frugivora. Memakan daun muda, buah-buahan hutan, biji-bijian, dan kadang meminum air payau.', 'Hewan sosial, hidup dalam kelompok besar beranggotakan jantan dominan dan beberapa betina serta anak-anak. Sering menampilkan interaksi sosial seperti perawatan bulu.', ''),
(13, 'BH013', 13, 'Diurnal (aktif siang hari), biasanya memulai aktivitas sekitar pukul 06.00. Banyak berpindah dari pohon ke pohon, mencari makan di tepi sungai atau hutan bakau.', 'Omnivora; lebih dominan herbivora. Makan daun muda, buah liar, biji, dan beberapa jenis bunga.', 'Hidup berkelompok dengan struktur sosial hierarkis, jantan dominan memimpin kelompok. Terdapat perilaku grooming sebagai bentuk komunikasi sosial.', 'Maret - Mei'),
(14, 'BH014', 14, 'Aktif pada pagi hingga sore hari. Bedi cenderung lebih sering menjelajah sendirian dalam radius pendek dari kelompok, lalu kembali saat siang. Menghindari tempat terbuka terlalu lama.', 'Lebih menyukai daun muda dan buah kecil. Juga memakan bunga dan kadang kulit kayu muda.', 'Cenderung pemalu, tidak agresif. Sering terlihat mengikuti dari kejauhan sebelum bergabung dengan kelompok.', 'April - Juni'),
(15, 'BH015', 15, 'Anoa aktif di pagi hingga sore hari. Vida cenderung menghindari area terbuka, menyukai jalur hutan yang lembap dan teduh.', 'Herbivora; memakan tunas muda, daun, buah hutan, pakis, dan rerumputan.', 'Soliter, menunjukkan toleransi terhadap individu lain saat musim kawin atau saat mencari air.', 'Oktober - Desember'),
(16, 'BH016', 16, 'Lebih aktif di pagi dan sore hari. Sering menyusuri aliran air dangkal dan tempat berlumpur untuk mendinginkan tubuh.', 'Memakan rumput, daun lebar, dan batang tanaman lunak. Juga menjilati batu berlumut untuk mineral alami.', 'Soliter dan teritorial. Mengintimidasi jika terusik.', 'September - November'),
(17, 'BH017', 17, 'Diurnal, aktif siang hari. Sering berkubang di lumpur dangkal dan berjalan di jalur tetap di dalam hutan.', 'Herbivora; pemakan tumbuhan bawah, dedaunan muda, tunas pohon kecil, dan kulit kayu.', 'Soliter kecuali musim kawin. Defensif terhadap gangguan wilayah.', 'Agustus - Oktober'),
(18, 'BH018', 18, 'Bersifat soliter dan aktif saat pagi serta sore hari. Menghabiskan waktu dengan menjelajahi wilayah.', 'Herbivora; pemakan daun-daunan muda, ranting, tunas, buah-buahan hutan, dan rerumputan liar.', 'Soliter dan sangat teritorial, kecuali saat musim kawin atau induk dengan anaknya. Interaksi dengan manusia sangat jarang.', 'April - Juli'),
(19, 'BH019', 19, 'Lebih aktif di pagi dan sore hari. Menghabiskan waktu untuk menjelajah, makan, dan berkubang di lumpur untuk mendinginkan tubuh.', 'Herbivora; memakan daun muda, ranting, buah hutan, dan tunas tanaman.', 'Individu soliter, cenderung menghindari interaksi kecuali saat kawin. Mempunyai wilayah jelajah yang dijaga dengan tanda-tanda seperti tumpukan kotoran dan bekas goresan.', 'April - Juli'),
(20, 'BH020', 20, '', 'Herbivora; aneka tumbuhan hutan seperti daun muda, tunas, ranting lunak, dan buah yang jatuh.', 'Soliter dan sangat teritorial. Saling menghindari satu sama lain, kecuali saat musim kawin atau induk dengan anak.', 'Mei - Agustus'),
(21, 'BH021', 21, 'Aktif pagi mencari makan dan air, siang berkubang di lumpur, sore bergerak sebelum istirahat malam.', 'Herbivora; daun, rumput, rotan, buah-buahan hutan tropis, konsumsi sampai 200 kg per hari.', 'Hidup berkelompok dengan struktur sosial matriarkal, ramah namun waspada terhadap manusia.', 'Juni - Oktober'),
(22, 'BH022', 22, 'Beraktivitas dari pagi sampai senja, mencari makan dan air, berkubang di lumpur sebagai pendingin tubuh.', 'Herbivora; rumput, daun, kulit kayu, buah, dan tumbuhan hutan, konsumsi harian sekitar 150 kg.', 'Sosial hidup kelompok matriarkal, jantan dewasa soliter kecuali musim kawin.', 'Juni - September'),
(23, 'BH023', 23, 'Aktif pagi dan sore, siang berkubang dan istirahat, berjalan jauh mencari air dan makanan.', 'Herbivora; daun muda, kulit pohon, batang rumput, buah-buahan, konsumsi 200–250 kg per hari.', 'Cenderung tenang, dominan, hidup dalam kelompok besar tapi sering menjauh sendiri.', ''),
(35, 'BH024', 64, '', '', '', ''),
(36, 'BH025', 65, NULL, NULL, NULL, NULL),
(37, 'BH026', 66, NULL, NULL, NULL, NULL),
(42, 'BH027', 71, 'suka tidur', 'buah', 'ekstrovert', 'Juni');

-- --------------------------------------------------------

--
-- Table structure for table `animal_details`
--

CREATE TABLE `animal_details` (
  `detail_id` int(11) NOT NULL,
  `detail_code` char(6) NOT NULL,
  `animal_id` int(11) NOT NULL,
  `height` decimal(5,2) DEFAULT NULL,
  `length` decimal(5,2) DEFAULT NULL,
  `color` varchar(100) DEFAULT NULL,
  `distinguishing_features` text DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `deleted_at` varchar(25) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `animal_details`
--

INSERT INTO `animal_details` (`detail_id`, `detail_code`, `animal_id`, `height`, `length`, `color`, `distinguishing_features`, `province`, `deleted_at`) VALUES
(1, 'DT001', 1, 80.00, 150.00, 'Coklat', 'Bintik putih', 'Jawa Barat', '2025-05-21'),
(2, 'DT002', 2, 92.00, 260.00, 'oranye dengan garis hitam pekat', 'Bekas luka di telinga kiri', 'Sumatra Selatan', ''),
(3, 'DT003', 3, 88.50, 245.00, 'oranye muda dengan garis hitam', 'Cacat pada gigi depan', 'Sumatra Barat', ''),
(4, 'DT004', 4, 100.00, 270.00, 'oranye gelap dengan garis hitam', 'Luka lama di pundak kanan', 'Bengkulu', ''),
(5, 'DT005', 5, 85.00, 240.00, 'oranye cerah dengan garis tipis hitam', 'Tanda putih di dagu', 'Riau', ''),
(6, 'DT006', 6, 130.00, 200.00, 'Coklat kemerahan', 'Memiliki janggut panjang dan rambut lebat di pipi kiri-kanan. Gerakannya tenang namun waspada.', 'Aceh', ''),
(7, 'DT007', 7, 135.00, 210.00, 'Coklat tua kemerahan', 'Memiliki lipatan pipi besar asimetris dan suara panggilan jantan dewasa yang lebih dalam.', 'Kalimantan Barat', ''),
(8, 'DT008', 8, 132.00, 205.00, 'Coklat keemasan', 'Memiliki lekukan unik pada dahi dan bentuk wajah bulat. Suara panggilannya cenderung lebih pendek dan tajam.', 'Sumatera Utara', ''),
(9, 'DT009', 9, 15.00, 40.00, 'Hitam kecoklatan', 'Memiliki barisan duri keras berwarna putih kehitaman yang bisa ditegakkan saat terancam.', 'Jambi', ''),
(10, 'DT010', 10, 18.00, 42.00, 'Hitam pekat dengan garis duri putih dan krem', 'Memiliki duri yang lebih panjang dari normal, membentuk semacam mahkota di punggung.', 'Bengkulu', ''),
(11, 'DT011', 11, 20.00, 45.00, 'Campuran hitam legam dengan duri belang putih', 'Ujung durinya berbentuk spiral kecil, dan gerakannya lambat namun sangat waspada.', 'Sumatera Barat', ''),
(12, 'DT012', 12, 65.00, 75.00, 'Coklat kemerahan', 'Hidung besar menggantung khas pejantan dewasa, perut membuncit karena sistem pencernaan.', 'Kalimantan Selatan', ''),
(13, 'DT013', 13, 66.00, 78.00, 'Hitam kecoklatan', 'Hidung menggantung besar (ciri khas jantan dewasa), perut membuncit alami, bulu halus di dahi membentuk pola.', 'Kalimantan Tengah', ''),
(14, 'DT014', 14, 60.00, 72.00, 'Kombinasi coklat dan krem', 'Hidung belum terlalu besar (sub-dewasa), ekor panjang dan ramping, terdapat bekas luka lama di kaki kiri.', 'Kalimantan Timur', ''),
(15, 'DT015', 15, 90.00, 180.00, 'Coklat kehitaman', 'Sepasang tanduk pendek melengkung ke belakang, terdapat corak putih di sekitar mata dan mulut, kulit tebal di leher.', 'Sulawesi Tenggara', ''),
(16, 'DT016', 16, 95.00, 190.00, 'Hitam legam', 'Tanduk simetris berbentuk sabit, luka bekas gigitan di bagian telinga kiri, gerakan hati-hati dan waspada.', 'Sulawesi Tengah', ''),
(17, 'DT017', 17, 85.00, 170.00, 'Coklat gelap', 'Memiliki pola rambut putih di dagu (seperti jenggot), tanduk sedikit bengkok ke belakang.', 'Sulawesi Tenggara', ''),
(18, 'DT018', 18, 170.00, 999.99, 'Abu-abu gelap', 'Bertanduk satu pendek (± 25 cm), struktur kulit tebal berlapis-lapis, tidak berbulu.', 'Banten', ''),
(19, 'DT019', 19, 160.00, 300.00, 'Abu-abu gelap', 'Bertanduk satu pendek, kulit tebal dengan pola lipatan alami seperti zirah, tidak memiliki rambut.', 'Banten', ''),
(20, 'DT020', 20, 170.00, 999.99, 'Abu-abu dan hitam', 'Bertanduk satu kecil (±25 cm), kulit berlapis-lapis menyerupai zirah alami, tanpa rambut.', 'Banten', ''),
(21, 'DT021', 21, 170.00, 999.99, 'Abu-abu gelap', 'Gading panjang dan lurus ke bawah, pola bercak cerah di sekitar telinga kiri.', 'Lampung', ''),
(22, 'DT022', 22, 999.99, 999.99, 'Abu-abu kusam', 'Telinga lebih kecil dibanding gajah Asia lainnya, gading pendek dan melengkung ke luar.', 'Aceh', ''),
(23, 'DT023', 23, 999.99, 999.99, 'Abu-abu gelap', 'Ujung belalai sedikit bercabang, bekas luka lama di sisi kanan tubuh.', 'Aceh', ''),
(53, 'DT024', 64, 0.00, 0.00, '', '', '', ''),
(54, 'DT025', 65, NULL, NULL, NULL, NULL, NULL, ''),
(55, 'DT026', 66, NULL, NULL, NULL, NULL, NULL, ''),
(60, 'DT027', 71, 90.00, 50.00, 'coklat', 'ekor pendek', 'Sumatra Selatan', '');

-- --------------------------------------------------------

--
-- Table structure for table `animal_observations`
--

CREATE TABLE `animal_observations` (
  `observation_id` int(11) NOT NULL,
  `observation_code` char(6) DEFAULT NULL,
  `animal_id` int(11) NOT NULL,
  `observation_time` datetime DEFAULT NULL,
  `location` text DEFAULT NULL,
  `animal_condition` text DEFAULT NULL,
  `notes` text DEFAULT NULL,
  `zona` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `animal_observations`
--

INSERT INTO `animal_observations` (`observation_id`, `observation_code`, `animal_id`, `observation_time`, `location`, `animal_condition`, `notes`, `zona`) VALUES
(1, 'OBS01', 1, '2025-06-20 00:00:00', 'Taman Nasional', 'Healthy', 'Tidak ada luka', 'Zona 2'),
(2, 'OBS02', 2, '2025-05-10 00:00:00', 'Zona 1 - Taman Nasional Bukit Barisan', '', 'Tidak ada masalah kesehatan yang terlihat.', 'Zona 1'),
(3, 'OBS03', 3, '2025-04-25 00:00:00', 'Zona 2 - Hutan Lindung Bukit Barisan', 'Unwell', 'Terlihat sering beristirahat dan menjaga wilayahnya.', 'Zona 1'),
(4, 'OBS04', 4, '2025-05-01 14:00:00', 'Zona 3 - Hutan Rimba Bengkulu', 'healthy', 'Luka lama di pundak kanan, observasi lanjutan diperlukan.', 'Zona 1'),
(5, 'OBS05', 5, '2025-04-28 00:00:00', 'Zona 4 - Hutan Riau', '', 'Berburu dengan baik, tanda putih di dagu mudah dikenali.', 'Zona 2'),
(6, 'OBS06', 6, '2025-05-02 10:10:00', 'Zona 2 – Kawasan Ekosistem Leuser, Aceh Tenggara', 'Relatif sehat, namun terlihat lebih pasif dari biasanya. Nafsu makan masih baik.', 'Kemungkinan mengalami cedera ringan pada lengan kanan akibat jatuh saat berpindah pohon. Disarankan untuk observasi lanjutan dan pemeriksaan medis oleh dokter hewan lapangan untuk memastikan tidak ada cedera struktural serius.', 'Zona 1'),
(7, 'OBS07', 7, '2025-05-08 14:45:00', 'Zona 4 – Taman Nasional Danau Sentarum, Kalimantan Barat', 'Terlihat lesu dan lebih jarang berpindah pohon. Aktivitas menurun dibanding observasi sebelumnya.', 'Kemungkinan mengalami gangguan pencernaan atau cedera ringan pada kaki belakang. Disarankan untuk observasi lanjutan dan pemeriksaan medis oleh dokter hewan lapangan.', 'Zona 1'),
(8, 'OBS08', 8, '2025-05-11 00:00:00', 'Zona 3 - Hutan Batang Toru, Tapanuli Selatan, Sumatera Utara', 'Aktif dan sehat, namun terdapat goresan kecil pada bagian perut, kemungkinan akibat dahan tajam.', 'Disarankan untuk observasi ulang dalam 3 hari ke depan untuk memantau perkembangan luka. Tidak menunjukkan tanda-tanda infeksi saat diamati.', 'Zona 1'),
(9, 'OBS09', 9, '2025-03-02 19:10:00', 'Zona 1 - Kawasan Taman Nasional Bukit Duabelas, Jambi', 'Dalam keadaan waspada dan aktif. Tidak menunjukkan tanda cedera atau stres.', 'Tidak ada tanda gangguan kesehatan. Namun, pengamatan lanjutan tetap disarankan untuk memastikan kelangsungan populasi di habitat tersebut karena tingkat fragmentasi hutan yang tinggi di wilayah ini.', 'Zona 1'),
(10, 'OBS10', 10, '2025-07-14 00:00:00', 'Zona 2 – Hutan Lindung Bukit Daun, Bengkulu Tengah', 'Terdeteksi pincang ringan saat berjalan dan lebih banyak diam di balik semak dibanding biasanya.', 'Kemungkinan mengalami gangguan pada tungkai belakang, bisa akibat luka minor atau otot tertarik. Disarankan untuk observasi lanjutan selama 5–7 hari ke depan dan dilakukan pemeriksaan fisik oleh tenaga medis satwa liar.', 'Zona 1'),
(11, 'OBS11', 11, '2025-06-15 14:35:00', 'Zona 2 – Hutan Sekunder Lubuk Basung, Kabupaten Agam, Sumatera Barat', 'Terlihat luka pada bagian tubuh, disarankan untuk pemeriksaan.', 'Disarankan untuk pemeriksaan lebih lanjut karena risiko infeksi pada luka terbuka. Penanganan ringan dengan antiseptik mungkin diperlukan, serta pengamatan perilaku selama 3 hari berikutnya untuk mendeteksi perubahan pola aktivitas.', 'Zona 1'),
(12, 'OBS12', 12, '2025-05-05 00:00:00', 'Zona 1 - Hutan Mangrove Pulau Curiak, Barito Kuala, Kalimantan Selatan', 'Gerakan sedikit lambat dan lebih sering berdiam diri dibanding kelompoknya. Nafsu makan menurun.', 'Kemungkinan mengalami gangguan pencernaan ringan akibat perubahan sumber makanan atau stres lingkungan. Disarankan untuk observasi lanjutan selama 2–3 hari serta evaluasi feses.', 'Zona 1'),
(13, 'OBS13', 13, '2025-04-16 14:25:00', 'Zona 3 - Taman Nasional Sebangau, Kalimantan Tengah', 'Tampak cukup sehat secara umum, namun gerak sedikit kaku di kaki belakang kanan.', 'Diduga mengalami cedera ringan akibat terjatuh dari dahan rendah. Tidak menunjukkan tanda nyeri berat, tetapi disarankan untuk observasi lanjutan dan pemeriksaan fisik oleh dokter hewan lapangan.', 'Zona 1'),
(14, 'OBS14', 14, '2025-03-17 10:10:00', 'Zona 1 - Kawasan Taman Nasional Bukit Duabelas, Jambi', 'Gerak aktif, tapi ada tanda lelah lebih cepat dari biasanya. Nafas sedikit lebih cepat setelah memanjat.', 'Perlu pemantauan lanjutan terhadap kapasitas pernapasan dan ketahanan fisik. Disarankan dilakukan pengamatan intensif selama 5 hari serta pengambilan sampel feses dan darah ringan.', 'Zona 1'),
(15, 'OBS15', 15, '2025-04-15 02:45:00', 'Zona 1 - Tanjung Peropa, Sulawesi Tenggara', 'Sehat, berjalan lambat di bagian belakang kemungkinan karena luka lama.', 'Kemungkinan ketegangan otot atau memar ringan pada kaki belakang kanan. Disarankan observasi lanjutan dan pemeriksaan fisik ringan.', 'Zona 1'),
(16, 'OBS16', 16, '2025-04-05 10:15:00', 'Zona 2 - Kawasan Hutan Lindung Gunung Sojol, Sulawesi Tengah', 'Gerakan normal, waspada, tidak agresif. Tidak ada luka baru atau gejala penyakit.', 'Disarankan observasi mingguan terutama menjelang akhir musim kawin.', 'Zona 1'),
(17, 'OBS17', 17, '2025-05-14 11:13:00', 'Zona 1 - Hutan Pegunungan Mekongga, Kolaka Utara, Sulawesi Tenggara', 'Secara umum sehat, tapi sedikit pincang pada kaki depan kanan saat berjalan menurun.', 'Kemungkinan cedera ringan kaki depan kanan. Observasi lanjutan dan pemeriksaan medis disarankan.', 'Zona 1'),
(18, 'OBS18', 18, '2025-04-10 16:45:00', 'Zona 3 - Blok Cidaon, Taman Nasional Ujung Kulon, Banten', 'Gerakan tubuh normal, namun terdapat perubahan pola makan (mengurangi konsumsi pakan segar).', 'Kemungkinan mengalami gangguan pencernaan ringan. Disarankan untuk observasi intensif pada asupan makan dan tinja selama 5 hari ke depan serta pengecekan langsung oleh tim medis konservasi.', 'Zona 1'),
(19, 'OBS19', 19, '2025-01-01 08:11:00', 'Zona 1 - Taman Nasional Ujung Kulon, Banten', 'Terpantau berjalan lambat dan tidak aktif seperti biasanya, namun tetap merespons lingkungan sekitar.', 'Kemungkinan mengalami cedera ringan pada bahu kiri. Disarankan untuk dilakukan pemantauan perilaku lebih lanjut dan penjadwalan pemeriksaan medis lapangan oleh tim dokter hewan.', 'Zona 1'),
(20, 'OBS20', 20, '2025-04-09 11:45:00', 'Zona 3 - Taman Nasional Ujung Kulon, Banten', 'Menunjukkan pola gerakan tidak stabil dan terlihat sering menggaruk bagian perut dengan pohon.', 'Kemungkinan mengalami gangguan pencernaan atau cedera ringan pada kaki belakang. Disarankan untuk observasi lanjutan dan pemeriksaan medis oleh dokter hewan lapangan.', 'Zona 1'),
(21, 'OBS21', 21, '2025-01-08 09:45:00', 'Zona 1 - Taman Nasional Way Kambas, Lampung Timur', 'Tidak ada gejala gangguan besar, namun sering menggaruk perut.', 'Kemungkinan iritasi kulit akibat serangga atau alergi lingkungan. Disarankan pemeriksaan kulit dan observasi lanjutan.', 'Zona 1'),
(22, 'OBS22', 22, '2025-01-05 02:45:00', 'Zona 2 - Kawasan Hutan Lindung Seulawah, Aceh Besar', 'Terlihat pincang ringan pada kaki depan kiri, sering duduk lama.', 'Kemungkinan ketegangan otot atau cedera ringan. Disarankan observasi lanjutan dan tindakan medis ringan.', 'Zona 1'),
(23, 'OBS23', 23, '2025-04-17 00:00:00', 'Zona 2 - Kawasan Hutan Lindung Seulawah Agam, Aceh Besar', 'Secara umum sehat, tapi gerakan kaki belakang kanan agak kaku.', 'Kemungkinan cedera ringan otot kaki belakang akibat medan tidak rata. Disarankan pemeriksaan fisik lanjutan dan pengawasan.', 'Zona 1'),
(45, NULL, 64, NULL, '', '', '', ''),
(46, NULL, 65, NULL, NULL, NULL, NULL, NULL),
(47, NULL, 66, NULL, NULL, NULL, NULL, NULL),
(52, NULL, 71, '2025-05-10 00:00:00', 'Taman Nasional Bukit Barisan', NULL, '-', 'Zona 1');

-- --------------------------------------------------------

--
-- Table structure for table `species`
--

CREATE TABLE `species` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `photo_url` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `species`
--

INSERT INTO `species` (`id`, `name`, `photo_url`) VALUES
(1, 'Panthera tigris sumatrae', ''),
(2, 'Pongo pygmaeus', ''),
(3, 'Erinaceinae', ''),
(4, 'Nasalis larvatus', ''),
(5, 'Bubalus depressicornis', ''),
(6, 'Eurhinoceros sondaicus', ''),
(7, 'Elephas maximus sumatrensis', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(5) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `role` enum('admin','ranger','researcher') NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `photo_url` text DEFAULT NULL,
  `status_user` enum('active','inactive','suspended') DEFAULT 'active',
  `pass` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `full_name`, `email`, `role`, `phone_number`, `photo_url`, `status_user`, `pass`) VALUES
(1, 'Amadita Arvida', 'amadita.arvida@wtc.ac.id', 'admin', '081237580565', 'Images/user1.jpeg', 'active', 'Admin1234'),
(2, 'Ardia Pramesti Regita Cahyani', 'ardia.cahyani@wtc.ac.id', 'ranger', '081459153235', 'Images/user3.jpeg', 'active', 'Admin1234'),
(3, 'Sintia Dwi Oktaviola Rerek Tukan', 'sintia.tukan@wtc.ac.id', 'researcher', '082245238672', 'Images/user2.jpeg', 'active', 'Admin1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `animals`
--
ALTER TABLE `animals`
  ADD PRIMARY KEY (`animal_id`),
  ADD KEY `kode` (`kode`) USING BTREE;

--
-- Indexes for table `animal_behavior`
--
ALTER TABLE `animal_behavior`
  ADD PRIMARY KEY (`behavior_id`),
  ADD KEY `animal_id` (`animal_id`),
  ADD KEY `behavior_code` (`behavior_code`);

--
-- Indexes for table `animal_details`
--
ALTER TABLE `animal_details`
  ADD PRIMARY KEY (`detail_id`),
  ADD KEY `animal_id` (`animal_id`);

--
-- Indexes for table `animal_observations`
--
ALTER TABLE `animal_observations`
  ADD PRIMARY KEY (`observation_id`),
  ADD UNIQUE KEY `animal_id` (`animal_id`) USING BTREE;

--
-- Indexes for table `species`
--
ALTER TABLE `species`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `animals`
--
ALTER TABLE `animals`
  MODIFY `animal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `animal_behavior`
--
ALTER TABLE `animal_behavior`
  MODIFY `behavior_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `animal_details`
--
ALTER TABLE `animal_details`
  MODIFY `detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `animal_observations`
--
ALTER TABLE `animal_observations`
  MODIFY `observation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `species`
--
ALTER TABLE `species`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `animal_behavior`
--
ALTER TABLE `animal_behavior`
  ADD CONSTRAINT `fk_animal_behavior` FOREIGN KEY (`animal_id`) REFERENCES `animals` (`animal_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `animal_details`
--
ALTER TABLE `animal_details`
  ADD CONSTRAINT `fk_animal_details_to_animals` FOREIGN KEY (`animal_id`) REFERENCES `animals` (`animal_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `animal_observations`
--
ALTER TABLE `animal_observations`
  ADD CONSTRAINT `fk_observations_to_animals` FOREIGN KEY (`animal_id`) REFERENCES `animals` (`animal_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
