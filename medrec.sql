-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Inang: 127.0.0.1
-- Waktu pembuatan: 18 Jan 2014 pada 13.35
-- Versi Server: 5.5.27
-- Versi PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Basis data: `medrec`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `dokter`
--

CREATE TABLE IF NOT EXISTS `dokter` (
  `no_dokter` varchar(7) NOT NULL DEFAULT '',
  `nm_dokter` varchar(45) NOT NULL,
  `id_spesialis` varchar(10) NOT NULL DEFAULT '',
  `tgl_kerja_dok` date DEFAULT '0000-00-00',
  `alamat_dok` text,
  PRIMARY KEY (`no_dokter`),
  KEY `fk_spesialis` (`id_spesialis`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `jaminan`
--

CREATE TABLE IF NOT EXISTS `jaminan` (
  `id_jaminan` varchar(20) NOT NULL DEFAULT '',
  `nm_jaminan` varchar(45) NOT NULL DEFAULT '',
  `ket_jaminan` text,
  PRIMARY KEY (`id_jaminan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `obat`
--

CREATE TABLE IF NOT EXISTS `obat` (
  `id_obat` varchar(45) NOT NULL,
  `ket_obat` text,
  PRIMARY KEY (`id_obat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pasien`
--

CREATE TABLE IF NOT EXISTS `pasien` (
  `no_rm` varchar(6) NOT NULL DEFAULT '',
  `nm_pas` varchar(45) NOT NULL,
  `jk_pas` enum('L','P') NOT NULL,
  `tgl_lahir` date NOT NULL,
  `agama` varchar(10) NOT NULL DEFAULT '',
  `alamat_pas` text,
  PRIMARY KEY (`no_rm`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelayanan_tindakan`
--

CREATE TABLE IF NOT EXISTS `pelayanan_tindakan` (
  `no_daftar` varchar(12) NOT NULL DEFAULT '',
  `no_tindakan` varchar(9) NOT NULL DEFAULT '' COMMENT 'TIND.0000',
  KEY `fk_pelayanan_rm` (`no_daftar`),
  KEY `fk_pelayanan_tindakan` (`no_tindakan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `perawat`
--

CREATE TABLE IF NOT EXISTS `perawat` (
  `no_perawat` varchar(7) NOT NULL DEFAULT '',
  `nm_perawat` varchar(45) NOT NULL DEFAULT '',
  `tgl_kerja_per` date DEFAULT '0000-00-00',
  `per_spesialis` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`no_perawat`),
  KEY `fk_perawat_spesialis` (`per_spesialis`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `rekam_medis`
--

CREATE TABLE IF NOT EXISTS `rekam_medis` (
  `no_daftar` varchar(12) NOT NULL DEFAULT '',
  `no_rm` varchar(6) NOT NULL DEFAULT '',
  `no_staf` varchar(7) NOT NULL,
  `bagian_spesialis` varchar(25) NOT NULL DEFAULT '',
  `id_jaminan` varchar(20) NOT NULL DEFAULT '',
  `no_dokter` varchar(7) NOT NULL DEFAULT '',
  `no_perawat` varchar(7) DEFAULT '',
  `nadi` int(10) DEFAULT '0',
  `temperatur` int(10) DEFAULT '0',
  `pernapasan` int(10) DEFAULT '0',
  `kesadaran` varchar(20) DEFAULT '',
  `anamnesa` text,
  `tinggi_bdn` float DEFAULT '0',
  `berat_bdn` float DEFAULT '0',
  `tensi_darah` varchar(7) DEFAULT '',
  `diagnosis` text,
  `terapi` text,
  `status` varchar(20) NOT NULL DEFAULT 'Antri',
  `tgl_daftar` date NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`no_daftar`),
  KEY `fk_pasien_rm` (`no_rm`),
  KEY `fk_staf_rm` (`no_staf`),
  KEY `fk_jaminan_rm` (`id_jaminan`),
  KEY `fk_dokter_rm` (`no_dokter`),
  KEY `fk_perawat_rm` (`no_perawat`),
  KEY `fk_unit_rs_rm` (`bagian_spesialis`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `resep`
--

CREATE TABLE IF NOT EXISTS `resep` (
  `no_resep` varchar(9) NOT NULL,
  `no_daftar` varchar(12) NOT NULL,
  `tgl_resep` date NOT NULL,
  PRIMARY KEY (`no_resep`),
  KEY `fk_rekam_medis` (`no_daftar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `resep_dt`
--

CREATE TABLE IF NOT EXISTS `resep_dt` (
  `no_resep` varchar(9) NOT NULL,
  `id_obat` varchar(45) NOT NULL,
  `satuan_kons` varchar(25) NOT NULL,
  `dosis_kons` varchar(20) NOT NULL,
  `jumlah` int(2) NOT NULL,
  KEY `fk_resep` (`no_resep`),
  KEY `fk_obat` (`id_obat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `spesialis`
--

CREATE TABLE IF NOT EXISTS `spesialis` (
  `id_spesialis` varchar(10) NOT NULL,
  `nm_spesialis` varchar(45) NOT NULL,
  `tarif_konsul` int(6) unsigned NOT NULL,
  PRIMARY KEY (`id_spesialis`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `staf`
--

CREATE TABLE IF NOT EXISTS `staf` (
  `no_staf` varchar(7) NOT NULL DEFAULT '',
  `nm_staf` varchar(45) NOT NULL DEFAULT '',
  `alamat_staf` text,
  PRIMARY KEY (`no_staf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tindakan`
--

CREATE TABLE IF NOT EXISTS `tindakan` (
  `no_tindakan` varchar(9) NOT NULL DEFAULT '' COMMENT 'TIND.0000',
  `nm_tindakan` varchar(45) NOT NULL DEFAULT '',
  `tindakan_spesialis` varchar(10) NOT NULL DEFAULT '',
  `ket_tindakan` text,
  PRIMARY KEY (`no_tindakan`),
  KEY `fk_tindakan_spesialis` (`tindakan_spesialis`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `dokter`
--
ALTER TABLE `dokter`
  ADD CONSTRAINT `fk_spesialis` FOREIGN KEY (`id_spesialis`) REFERENCES `spesialis` (`id_spesialis`);

--
-- Ketidakleluasaan untuk tabel `pelayanan_tindakan`
--
ALTER TABLE `pelayanan_tindakan`
  ADD CONSTRAINT `fk_pelayanan_rm` FOREIGN KEY (`no_daftar`) REFERENCES `rekam_medis` (`no_daftar`),
  ADD CONSTRAINT `fk_pelayanan_tindakan` FOREIGN KEY (`no_tindakan`) REFERENCES `tindakan` (`no_tindakan`);

--
-- Ketidakleluasaan untuk tabel `perawat`
--
ALTER TABLE `perawat`
  ADD CONSTRAINT `fk_perawat_spesialis` FOREIGN KEY (`per_spesialis`) REFERENCES `spesialis` (`id_spesialis`);

--
-- Ketidakleluasaan untuk tabel `rekam_medis`
--
ALTER TABLE `rekam_medis`
  ADD CONSTRAINT `fk_dokter_rm` FOREIGN KEY (`no_dokter`) REFERENCES `dokter` (`no_dokter`),
  ADD CONSTRAINT `fk_jaminan_rm` FOREIGN KEY (`id_jaminan`) REFERENCES `jaminan` (`id_jaminan`),
  ADD CONSTRAINT `fk_pasien_rm` FOREIGN KEY (`no_rm`) REFERENCES `pasien` (`no_rm`),
  ADD CONSTRAINT `fk_perawat_rm` FOREIGN KEY (`no_perawat`) REFERENCES `perawat` (`no_perawat`),
  ADD CONSTRAINT `fk_spesialis_rm` FOREIGN KEY (`bagian_spesialis`) REFERENCES `spesialis` (`id_spesialis`),
  ADD CONSTRAINT `fk_staf_rm` FOREIGN KEY (`no_staf`) REFERENCES `staf` (`no_staf`);

--
-- Ketidakleluasaan untuk tabel `resep`
--
ALTER TABLE `resep`
  ADD CONSTRAINT `fk_rekam_medis` FOREIGN KEY (`no_daftar`) REFERENCES `rekam_medis` (`no_daftar`);

--
-- Ketidakleluasaan untuk tabel `resep_dt`
--
ALTER TABLE `resep_dt`
  ADD CONSTRAINT `fk_obat` FOREIGN KEY (`id_obat`) REFERENCES `obat` (`id_obat`),
  ADD CONSTRAINT `fk_resep` FOREIGN KEY (`no_resep`) REFERENCES `resep` (`no_resep`);

--
-- Ketidakleluasaan untuk tabel `tindakan`
--
ALTER TABLE `tindakan`
  ADD CONSTRAINT `fk_tindakan_spesialis` FOREIGN KEY (`tindakan_spesialis`) REFERENCES `spesialis` (`id_spesialis`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
