use master
go

if DB_ID('QLNS_11_4_23') IS NOT NULL
   drop database QLNS_11_4_23
go

/*=============================================================
======================= CREATE DATABASE =======================
=============================================================*/
Declare @Primary_Path nvarchar(max), @Log_Path nvarchar(max), @Excel_Path nvarchar(max)
Select	@Primary_Path = N'C:\HocTap\CongNgheJava',
		@Log_Path = N'C:\HocTap\CongNgheJava'

Declare @SQL nvarchar(max);
SET @SQL = N'
CREATE DATABASE QLNS_11_4_23
ON PRIMARY
(
	NAME = QLNS_11_4_23_PRIMARY,
	FILENAME = ''' + @Primary_Path + '\QLNS_11_4_23_PRIMARY.mdf'',
	SIZE = 20 MB,
	MAXSIZE = 50 MB,
	FILEGROWTH = 10%
)
LOG ON
(
	NAME = QLNS_11_4_23_LOG,
	FILENAME = ''' + @Log_Path + '\QLNS_11_4_23_LOG.trn'',
	SIZE = 10 MB,
	MAXSIZE = 20 MB,
	FILEGROWTH = 10%
)' 
EXEC (@SQL)
GO

USE QLNS_11_4_23
GO

/*=============================================================
======================== CREATE TABLE =========================
=============================================================*/
create table KHACHHANG
(
	maKH varchar(10) primary key,
	tenKH nvarchar(50)				not null,
	SDT varchar(11)					not null,
	email varchar(50),
	soDiem int,
)
go

create table NHANVIEN
(
	maNV varchar(10) primary key,
	tenNV nvarchar(50)				not null,
	CCCD varchar(12)				not null,
	SDT varchar(11)					not null,
	thamNien int,
	diaChi nvarchar(100),
	email varchar(50),
	chucVu nvarchar(30)				not null,
	matKhau varchar(16)				not null,
)
go

create table HOADON
(
	maHD varchar(10) primary key,
	ngayLap date,
	tongTien float,
	maKH varchar(10),
	maNV varchar(10)				not null,
	tinhTrang nvarchar(20),
	constraint FK_HoaDon_maKH foreign key(maKH) references KHACHHANG(maKH),
	constraint FK_HoaDon_maNV foreign key(maNV) references NHANVIEN(maNV)
)
go

create table TACGIA
(
	maTG varchar(10) primary key,
	tenTG nvarchar(50)				not null
)
go

create table THELOAI
(
	maTL varchar(10) primary key,
	tenTL nvarchar(10)				not null	unique
)
go

create table SACH
(
	maSach varchar(10) primary key,
	tenSach nvarchar(50)			not null,
	donGia float					not null,
	soLuongCon int					not null,
	maTG varchar(10),
	maTL varchar(10),
	constraint FK_SACH_maTG foreign key(maTG) references TACGIA(maTG),
	constraint FK_SACH_maTL foreign key(maTL) references THELOAI(maTL),
)
go

create table CHITIETHOADON
(
	maHD varchar(10)	not null,
	maSach varchar(10)	not null,
	soLuongMua int,
	constraint PK_ChiTietHoaDon primary key(maHD, maSach),
	constraint FK_ChiTietHoaDon_maHD foreign key(maHD) references HOADON(maHD),
	constraint FK_ChiTietHoaDon_maSach foreign key(maSach) references SACH(maSach)
)
go

create table PHIEUNHAP
(
	maPhieuNhap varchar(10) primary key,
	ngayNhap date,
	tongTienNhap float,
	maNV varchar(10)						not null,
	constraint FK_PhieuNhap_maNV foreign key(maNV) references NHANVIEN(maNV),
)
go

create table CHITIETPHIEUNHAP
(
	maPhieuNhap varchar(10) not null,
	maSach varchar(10)		not null,
	soLuongNhap int,
	constraint PK_ChiTietPhieuNhap primary key(maPhieuNhap, maSach),
	constraint FK_ChiTietPhieuNhap_maPhieuNhap foreign key(maPhieuNhap) references PHIEUNHAP(maPhieuNhap),
	constraint FK_ChiTietPhieuNhap_maSach foreign key(maSach) references SACH(maSach)
)
go

/*=============================================================
======================= ADD CONSTRAINT  =======================
=============================================================*/
alter table KHACHHANG
add constraint df_soDiem default 0 for soDiem,
	constraint df_email_kh default N'Không có' for email
go

alter table NHANVIEN
add constraint df_thamNien default 0 for thamNien,
	constraint df_diaChi default N'Không có' for diaChi,
	constraint df_email_nv default N'Không có' for email,
	constraint ck_chucVu check(chucVu in(N'Kho',N'Thu ngân'))
go

alter table HOADON
add constraint df_ngayLap default getdate() for ngayLap,
	constraint df_tongTien default 0 for tongTien,
	constraint df_tinhTrang default N'Đang xử lý' for tinhTrang,
	constraint df_maKH default null for maKH,
	constraint ck_tinhTrang check(tinhTrang in(N'Đang xử lý', N'Đã hoàn tất'))
go

alter table THELOAI
add constraint uni_tenTL unique(tenTL)
go

alter table CHITIETHOADON
add constraint df_soLuongMua default 1 for soLuongMua
go

alter table PHIEUNHAP
add constraint df_ngayNhap default getdate() for ngayNhap,
	constraint df_tongTienNhap default 0 for tongTienNhap

alter table CHITIETPHIEUNHAP
add constraint df_soLuongNhap default 1 for soLuongNhap
go

/*=============================================================
=================== IMPORT DATA FROM EXCEL  ===================
=============================================================*/
--sp_configure 'show advanced options', 1;
--RECONFIGURE;
--go

--sp_configure 'Ad Hoc Distributed Queries', 1;
--RECONFIGURE;
--go

--EXEC master.dbo.sp_MSset_oledb_prop N'Microsoft.ACE.OLEDB.12.0', N'AllowInProcess', 1 
--GO 

--EXEC master.dbo.sp_MSset_oledb_prop N'Microsoft.ACE.OLEDB.12.0', N'DynamicParameters', 1 
--GO

---- KHACHHANG
--INSERT INTO KHACHHANG(maKH, tenKH, SDT, email)
--SELECT *
--FROM OPENDATASOURCE('Microsoft.ACE.OLEDB.12.0',
--    N'Data Source=C:\HocTap\CongNgheJava\Import\DuLieuNhaSach.xlsx;
--	Extended Properties=Excel 12.0')...[KHACHHANG$]
--GO
--select * from KHACHHANG
--go

---- NHANVIEN
--INSERT INTO NHANVIEN
--SELECT *
--FROM OPENDATASOURCE('Microsoft.ACE.OLEDB.12.0',
--    N'Data Source=C:\HocTap\CongNgheJava\Import\DuLieuNhaSach.xlsx;
--	Extended Properties=Excel 12.0')...[NHANVIEN$];
--GO
--select * from NHANVIEN
--go

---- HOADON
--INSERT INTO HOADON(maHD, ngayLap, maKH, maNV)
--SELECT *
--FROM OPENDATASOURCE('Microsoft.ACE.OLEDB.12.0',
--    N'Data Source=C:\HocTap\CongNgheJava\Import\DuLieuNhaSach.xlsx;
--	Extended Properties=Excel 12.0')...[HOADON$];
--GO
--select * from HOADON
--go

---- TACGIA
--INSERT INTO TACGIA
--SELECT *
--FROM OPENDATASOURCE('Microsoft.ACE.OLEDB.12.0',
--    N'Data Source=C:\HocTap\CongNgheJava\Import\DuLieuNhaSach.xlsx;
--	Extended Properties=Excel 12.0')...[TACGIA$];
--GO
--select * from TACGIA
--go

---- THELOAI
--INSERT INTO THELOAI
--SELECT *
--FROM OPENDATASOURCE('Microsoft.ACE.OLEDB.12.0',
--    N'Data Source=C:\HocTap\CongNgheJava\Import\DuLieuNhaSach.xlsx;
--	Extended Properties=Excel 12.0')...[THELOAI$];
--GO
--select * from THELOAI
--go

---- SACH
--INSERT INTO SACH
--SELECT *
--FROM OPENDATASOURCE('Microsoft.ACE.OLEDB.12.0',
--    N'Data Source=C:\HocTap\CongNgheJava\Import\DuLieuNhaSach.xlsx;
--	Extended Properties=Excel 12.0')...[SACH$];
--GO
--select * from SACH
--go

---- CHITIETHOADON
--INSERT INTO CHITIETHOADON
--SELECT *
--FROM OPENDATASOURCE('Microsoft.ACE.OLEDB.12.0',
--    N'Data Source=C:\HocTap\CongNgheJava\Import\DuLieuNhaSach.xlsx;
--	Extended Properties=Excel 12.0')...[CHITIETHOADON$];
--GO
--select * from CHITIETHOADON
--go

---- PHIEUNHAP
--INSERT INTO PHIEUNHAP(maPhieuNhap, ngayNhap, maNV)
--SELECT *
--FROM OPENDATASOURCE('Microsoft.ACE.OLEDB.12.0',
--    N'Data Source=C:\HocTap\CongNgheJava\Import\DuLieuNhaSach.xlsx;
--	Extended Properties=Excel 12.0')...[PHIEUNHAP$];
--GO
--select * from PHIEUNHAP
--go

---- CHITIETPHIEUNHAP
--INSERT INTO CHITIETPHIEUNHAP
--SELECT *
--FROM OPENDATASOURCE('Microsoft.ACE.OLEDB.12.0',
--    N'Data Source=C:\HocTap\CongNgheJava\Import\DuLieuNhaSach.xlsx;
--	Extended Properties=Excel 12.0')...[CHITIETPHIEUNHAP$];
--GO
--select * from CHITIETPHIEUNHAP
--go

/*=============================================================
===================== IMPORT STATIC DATA  =====================
=============================================================*/
insert into KHACHHANG(maKH, tenKH, SDT, email) values
('KH1',N'Si Giai Dương','0945287634','sigiaidduong123@gmail.com'),
('KH2',N'Hồ Cẩm Toàn','0825531893','conheoxinh@gmail.com'),
('KH3',N'Hồ Văn Vở','0909006124','vo12oli@gmail.com'),
('KH4',N'Trịnh Thăng Trầm','0125507951','tramcam@gmail.com'),
('KH5',N'Lê Cẩm Thạch','0909002546','thachraucau@gmail.com'),
('KH6',N'Nguyễn Than Thở','0825531351','thonhieucungmet@gmail.com'),
('KH7',N'Thái Từng Lát','0916631893','latnuaansau@gmail.com'),
('KH8',N'Davis Mafius','0917301813','mabulues@gmail.com'),
('KH9',N'Đặng Ngọc Tịnh','0905614851','tinhtamtu@gmail.com'),
('KH10',N'Lê Đình Thái Ngân','09133154210','nanthh@gmail.com')
go

insert into NHANVIEN values
('NV1',N'Nguyễn Phương Việt','079202027772','0834512345',10,N'Hồ Thị Kỷ Q.10 TPHCM','awdssdfgviethufi@gmail.com',N'Kho','NV1KHO'),
('NV2',N'Trần Đình Tín','079556737772','0855662322',5,N'Kênh Nước Đen Q.Bình Tân TPHCM','trantindeptraiaka@gmail.com',N'Thu ngân','NV2THUNGAN'),
('NV3',N'Nguyễn Phương Nam','079556737402','01205508952',7,N'Lê Văn Phan Q.Tân Phú TPHCM','tuibedema@gmail.com',N'Kho','NV2KHO'),
('NV4',N'Lê Bích Ngân','079556736662','0205154261',5,N'Kênh Nước Xanh Q.Bình Tân TPHCM','tuidepgaihong@gmail.com',N'Thu ngân','NV2THUNGAN'),
('NV5',N'Phùng Thanh Độ','07920201102','0834114345',6,N'Lê Trọng Tấn Q.Tân Phú TPHCM','huhahi@gmail.com',N'Kho','NV3KHO'),
('NV6',N'Lê Thị Mỹ Hạnh','079556736222','0205154111',2,N'Kênh Nước Trắng Q.Bình Tân TPHCM','tuicute@gmail.com',N'Thu ngân','NV3THUNGAN')
go

SET DATEFORMAT dmy;  
GO  
insert into HOADON(maHD, ngayLap, maKH, maNV) values
('HD1','10/3/2023','KH1','NV2'),
('HD2','10/3/2023','KH2','NV4'),
('HD3','11/3/2023','KH3','NV6'),
('HD4','11/3/2023','KH4','NV2'),
('HD5','11/3/2023','KH5','NV4'),
('HD6','12/3/2023','KH6','NV6'),
('HD7','12/3/2023','KH7','NV2'),
('HD8','12/3/2023','KH8','NV4'),
('HD9','13/3/2023','KH9','NV4'),
('HD10','13/3/2023','KH10','NV6'),
('HD11','14/02/2022','KH1','NV6'),
('HD12','5/05/2022','KH5','NV2'),
('HD13','21/10/2022','KH2','NV3'),
('HD14','17/10/2022','KH5','NV6'),
('HD15','18/11/2022','KH2','NV5'),
('HD16','19/02/2022','KH10','NV2'),
('HD17','20/05/2022','KH9','NV1'),
('HD18','21/07/2022','KH8','NV1'),
('HD19','22/04/2022','KH8','NV4'),
('HD20','23/02/2022','KH4','NV2'),
('HD21','02/08/2022','KH5','NV2')
go

insert into TACGIA values
('TG1',N'Gosho Aoyama'),
('TG2',N'Nguyễn Quang Thiều'),
('TG3',N'Fujiko Fujio'),
('TG4',N'Togashi Yoshihiro'),
('TG5',N'Junji Ito')
go

insert into THELOAI values
('TL1',N'Tình Cảm'),
('TL2',N'Kinh Dị'),
('TL3',N'Trinh Thám'),
('TL4',N'Hài Hước'),
('TL5',N'Chiến Đấu')
go

insert into SACH values
('S1',N'Thám Tử Lừng Danh Conan',26000,30,'TG1','TL3'),
('S2',N'Magic Kaito',29000,45,'TG1','TL3'),
('S3',N'Yaiba',20000,20,'TG1','TL4'),
('S4',N'Chuyện Của Anh Em Nhà Mem & Kya',85000,75,'TG2','TL1'),
('S5',N'Tết Đoàn Viên',30000,100,'TG2','TL1'),
('S6',N'TRONG NGÔI NHÀ CỦA MẸ',79200,65,'TG2','TL1'),
('S7',N'Doraemon',26000,50,'TG3','TL4'),
('S8',N'Chú mèo Poko',20000,45,'TG3','TL4'),
('S9',N'Bakeru-kun',17000,30,'TG3','TL4'),
('S10',N'Ten de Shōwaru Cupid',17000,40,'TG4','TL1'),
('S11',N'YuYu Hakusho',16000,35,'TG4','TL5'),
('S12',N'Jura no Miduki',17000,30,'TG4','TL5'),
('S13',N'Uzumaki',29000,30,'TG5','TL2'),
('S14',N'Tomie',36000,20,'TG5','TL2'),
('S15',N'Lovesick Dead',45000,15,'TG5','TL2')
go

insert into CHITIETHOADON values
('HD1','S15',1),
('HD2','S2',1),
('HD2','S3',5),
('HD3','S7',1),
('HD4','S1',1),
('HD4','S2',1),
('HD5','S11',1),
('HD5','S12',1),
('HD6','S8',1),
('HD6','S9',1),
('HD7','S9',1),
('HD8','S3',1),
('HD9','S15',1),
('HD10','S7',1),
('HD10','S1','1'),
('HD10','S3','2'),
('HD11','S2','3'),
('HD11','S4','1'),
('HD12','S5','1'),
('HD13','S5','1'),
('HD14','S6','2'),
('HD14','S1','2'),
('HD14','S14','1'),
('HD14','S10','4'),
('HD15','S11','2'),
('HD16','S2','1'),
('HD17','S5','2'),
('HD18','S7','1'),
('HD19','S7','1'),
('HD20','S2','2'),
('HD20','S1','3'),
('HD21','S3','5')
go

SET DATEFORMAT ymd;  
go
insert into PHIEUNHAP(maPhieuNhap, ngayNhap, maNV) values
('PN1','12/05/2021','NV1'),
('PN2','12/25/2021','NV2'),
('PN3','12/11/2021','NV2')
go

insert into CHITIETPHIEUNHAP values
('PN1','S1','30'),
('PN1','S2','45'),
('PN1','S3','20'),
('PN1','S4','75'),
('PN1','S5','100'),
('PN2','S6','65'),
('PN2','S7','50'),
('PN2','S8','45'),
('PN2','S9','30'),
('PN2','S10','40'),
('PN2','S11','35'),
('PN3','S12','30'),
('PN3','S13','30'),
('PN3','S14','20'),
('PN3','S15','15')
go

/*=============================================================
===================== PROCEDURE, FUNCTION  ====================
=============================================================*/
/*------------- XUAT HOA DON -------------*/
create proc xuat_HD (@maHD varchar(10))
as
	begin
		select hd.maHD, ngayLap, tongTien, tenSach, soLuongMua, donGia 
		from HOADON hd, CHITIETHOADON ct, SACH s 
		where hd.maHD = @maHD and hd.maHD = ct.maHD and s.maSach = ct.maSach
	end
go
--exec dbo.xuat_HD HD10

/*------------- DOANH THU NHAN VIEN -------------*/
create function tk_DoanhThuNV()
returns table
as
	return	( 
				select HOADON.maNV,tenNV,sum(tongTien) as N'Doanh thu' from HOADON 
				inner join NHANVIEN 
				on HOADON.maNV = NHANVIEN.maNV
				group by HOADON.maNV, tenNV
			)
go

/*------------- DOANH THU NHAN VIEN THEO NGAY -------------*/
create function tk_DoanhThuNVTheoNgay(@fromDate date, @toDate date)
returns table
as
	return	( 
				select HOADON.maNV,tenNV,sum(tongTien) as N'Doanh thu' from HOADON 
				inner join NHANVIEN 
				on HOADON.maNV = NHANVIEN.maNV and 
					ngayLap between @fromDate and @toDate
				group by HOADON.maNV, tenNV
			)
go

/*------------- TONG THU KHACH HANG -------------*/
create function tk_TongThuKH ()
returns table
as
	return	(
				select HOADON.maKH, tenKH, sum(tongTien) as N'Tong thu' from HOADON 
				inner join KHACHHANG 
				on HOADON.maKH = KHACHHANG.maKH
				group by HOADON.maKH, tenKH
			)
go

/*------------- TONG THU KHACH HANG THEO NGAY -------------*/
create function tk_TongThuKHTheoNgay (@fromDate date, @toDate date)
returns table
as
	return	(
				select HOADON.maKH, tenKH, sum(tongTien) as N'Tong thu' from HOADON 
				inner join KHACHHANG 
				on HOADON.maKH = KHACHHANG.maKH and 
					ngayLap between @fromDate and @toDate
				group by HOADON.maKH, tenKH
			)
go

/*------------- TINH TONG TIEN -------------*/
create proc tinh_TongTien
as
	begin
		declare @maHD varchar(10), @maS varchar(10), @donGia float, @sl int, @tongTien float = 0
		declare cur_maHD CURSOR
		for select maHD from HOADON
			open cur_maHD
			while (1 = 1)
				begin
					fetch next from cur_maHD into @maHD
					if (@@FETCH_STATUS = 0)
						begin
							set @tongTien = 0
							--print 'Ma hoa don: ' + convert(varchar,@maHD)
							declare cur_maS CURSOR
							for select maSach, soLuongMua from CHITIETHOADON where maHD = @maHD
								open cur_maS
								while (1 = 1)
									begin
										fetch next from cur_maS into @maS, @sl
										if (@@FETCH_STATUS = 0)
											begin
												--print '  Ma sach: ' + convert(varchar,@maS) + ' So luong: ' + convert(varchar,@sl)
												declare cur_DonGia CURSOR
												for select donGia from SACH where maSach = @maS
													open cur_DonGia
													while (1 = 1)
														begin
															fetch next from cur_DonGia into @donGia
															if (@@FETCH_STATUS = 0)
																begin
																	--print '    Don gia: ' + convert(varchar,@donGia)
																	set @tongTien = @tongTien + (@donGia * @sl)
																	--print '    Tong tien: ' + convert(varchar,@tongTien)
																end
															else
																begin
																	--print N'    Kết thúc'
																	break
																end
														end
													close cur_DonGia
													deallocate cur_DonGia
											end
										else
											begin
												--print N'  Kết thúc'
												break
											end
									end
								close cur_maS
								deallocate cur_maS
							update HOADON set tongTien = @tongTien, tinhTrang = N'Đã hoàn tất' where maHD = @maHD
						end
					else
						begin
							--print N'Kết thúc'
							break
						end
				end
			close cur_maHD
			deallocate cur_maHD
	end
go
exec tinh_TongTien
go

/*------------- TINH TONG TIEN NHAP -------------*/
create proc tinh_TongTienNhap
as
	begin
		declare @maPhieuNhap varchar(10), @maS varchar(10), @donGia float, @sl int, @tongTien float = 0
		declare cur_maPhieuNhap CURSOR
		for select maPhieuNhap from PHIEUNHAP
			open cur_maPhieuNhap
			while (1 = 1)
				begin
					fetch next from cur_maPhieuNhap into @maPhieuNhap
					if (@@FETCH_STATUS = 0)
						begin
							set @tongTien = 0
							--print 'Ma phieu nhap: ' + convert(varchar,@maPhieuNhap)
							declare cur_maS CURSOR
							for select maSach, soLuongNhap from CHITIETPHIEUNHAP where maPhieuNhap = @maPhieuNhap
								open cur_maS
								while (1 = 1)
									begin
										fetch next from cur_maS into @maS, @sl
										if (@@FETCH_STATUS = 0)
											begin
												--print '  Ma sach: ' + convert(varchar,@maS) + ' So luong: ' + convert(varchar,@sl)
												declare cur_DonGia CURSOR
												for select donGia from SACH where maSach = @maS
													open cur_DonGia
													while (1 = 1)
														begin
															fetch next from cur_DonGia into @donGia
															if (@@FETCH_STATUS = 0)
																begin
																	--print '    Don gia: ' + convert(varchar,@donGia)
																	set @tongTien = @tongTien + (@donGia * @sl)
																	--print '    Tong tien: ' + convert(varchar,@tongTien)
																end
															else
																begin
																	--print N'    Kết thúc'
																	break
																end
														end
													close cur_DonGia
													deallocate cur_DonGia
											end
										else
											begin
												--print N'  Kết thúc'
												break
											end
									end
								close cur_maS
								deallocate cur_maS
							update PHIEUNHAP set tongTienNhap = @tongTien where maPhieuNhap = @maPhieuNhap
						end
					else
						begin
							--print N'Kết thúc'
							break
						end
				end
			close cur_maPhieuNhap
			deallocate cur_maPhieuNhap
	end
go
exec tinh_TongTienNhap
go

/*------------- TINH SO DIEM -------------*/
create proc tinh_SoDiem
as
	begin
		declare @maKH varchar(10), @tongTienTatCaHD float = 0, @tongTienMotHD float = 0
		declare cur_MaKH CURSOR
		for select maKH from KHACHHANG
			open cur_MaKH
			while (1 = 1)
				begin
					set @tongTienTatCaHD = 0
					fetch next from cur_MaKH into @maKH
					if (@@FETCH_STATUS = 0)
						begin
							--print 'Ma kh: ' + convert(varchar,@maKH)
							declare cur_TongTienMotHD CURSOR
							for select tongTien from HOADON where maKH = @maKH
								open cur_TongTienMotHD
								while (1 = 1)
									begin
										fetch next from cur_TongTienMotHD into @tongTienMotHD
										if (@@FETCH_STATUS = 0)
											begin
												set @tongTienTatCaHD = @tongTienTatCaHD + @tongTienMotHD
											end
										else
											break
									end
								close cur_TongTienMotHD
								deallocate cur_TongTienMotHD
							--print '	So diem: ' + convert(varchar,@tongTienTatCaHD/1000)
							update KHACHHANG set soDiem = @tongTienTatCaHD / 1000 where maKH = @maKH
						end
					else
						break
				end
			close cur_MaKH
			deallocate cur_MaKH
	end
go
exec tinh_SoDiem
go

/*------------- CAP NHAT SL SACH -------------*/
create proc capNhat_SLSACH
as
	begin
		declare @maSach varchar(10), @soLuongMua int=0, @tongSoLuongMua int=0
		declare cur_maSach CURSOR
		for select maSach from SACH
			open cur_maSach
			while (1 = 1)
				begin
					set @tongSoLuongMua = 0
					fetch next from cur_maSach into @maSach
					if (@@FETCH_STATUS = 0)
						begin
							--print 'Ma sach: ' + convert(varchar,@maSach)
							declare cur_soLuong CURSOR
							for select soLuongMua from CHITIETHOADON where maSach = @maSach
								open cur_soLuong
								while (1 = 1)
									begin
										fetch next from cur_soLuong into @soLuongMua
										if (@@FETCH_STATUS = 0)
											begin
												--print '	So luong: ' + convert(varchar,@soLuongMua)
												set @tongSoLuongMua = @tongSoLuongMua + @soLuongMua
											end
										else
											break
									end
								close cur_soLuong
								deallocate cur_soLuong
							--print '	Tong so luong: ' + convert(varchar,@tongSoLuongMua)
							update SACH set soLuongCon = soLuongCon - @tongSoLuongMua where maSach = @maSach
						end
					else
						break
				end
			close cur_maSach
			deallocate cur_maSach
	end
go
exec capNhat_SLSACH
go

/*=============================================================
=========================== TRIGGER  ==========================
=============================================================*/
/*------------- DELETE TACGIA -------------*/
create trigger Del_TacGia 
on TACGIA
instead of delete
as
	begin
		declare @maTG varchar(10) = (select maTG from deleted)
		
		update SACH set maTG = null where maTG in(@maTG)
		delete TACGIA where maTG = @maTG
	end
go

/*------------- DELETE THELOAI -------------*/
create trigger Del_TheLoai
on THELOAI
instead of delete
as
	begin
		declare @maTL varchar(10) = (select maTL from deleted)
		
		update SACH set maTL = null where maTL in(@maTL)
		delete THELOAI where maTL = @maTL
	end
go

/*------------- DELETE KHACHHANG -------------*/
create trigger Del_KhachHang
on KHACHHANG
instead of delete
as
	begin
		declare @maKH varchar(10) = (select maKH from deleted)
		
		update HOADON set maKH = null where maKH in(@maKH)
		delete KHACHHANG where maKH = @maKH
	end
go

/*------------- DELETE NHANVIEN -------------*/
/*create trigger Del_NhanVien
on NHANVIEN
instead of delete
as
	begin
		declare @maNV varchar(10) = (select maNV from deleted)
		
		update HOADON set maNV = null where maNV in(@maNV)
		delete NHANVIEN where maNV = @maNV
	end
go*/

/*------------- DELETE HOADON -------------*/
create trigger Del_HoaDon
on HOADON
instead of delete
as
	begin
		declare @maHD varchar(10) = (select maHD from deleted)
		
		delete CHITIETHOADON where maHD in(@maHD)
		delete HOADON where maHD = @maHD
	end
go

/*------------- DELETE SACH -------------*/
create trigger Del_Sach
on SACH
instead of delete
as
	begin
		declare @maSach varchar(10) = (select maSach from deleted)
		
		delete CHITIETHOADON where maSach in(@maSach)
		delete SACH where maSach = @maSach
	end
go

/*------------- UPDATE SODIEM -------------*/
create trigger Upd_SoDiem
on HOADON
for insert, delete, update
as
	begin
		exec dbo.tinh_SoDiem
	end
go

/*------------- UPDATE TONGTIENNHAP -------------*/
create trigger Upd_TongTienNhap_SoLuong
on CHITIETPHIEUNHAP
for insert, delete, update
as
	begin
		declare @soluongnhap_Del int, @soluongnhap_Ins int, @soluongnhap_Cur int
		set @soluongnhap_Del = (select soLuongNhap from deleted)
		set @soluongnhap_Ins = (select soLuongNhap from inserted)

		if exists (select * from inserted)
			begin
				if @soluongnhap_Del is NULL --insert
					begin
						set @soluongnhap_Del = 0
						set @soluongnhap_Cur = @soluongnhap_Del - @soluongnhap_Ins
						update SACH set soLuongCon = soLuongCon - (@soluongnhap_Cur) from inserted where SACH.maSach = inserted.maSach
					end
				else -- update
					begin
						set @soluongnhap_Cur = @soluongnhap_Del - @soluongnhap_Ins
						update SACH set soLuongCon = soLuongCon - (@soluongnhap_Cur) from inserted where SACH.maSach = inserted.maSach
					end
			end
		else -- delete
			begin
				update SACH set soLuongCon = soLuongCon - @soluongnhap_Del from deleted where SACH.maSach = deleted.maSach
			end
		exec dbo.tinh_TongTienNhap
	end
go

/*------------- UPDATE TONGTIEN, SL -------------*/
create trigger Upd_TongTien_SoLuong
on CHITIETHOADON
for insert, delete, update
as
	begin
		declare @soluongmua_Del int, @soluongmua_Ins int, @soluongmua_Cur int
		set @soluongmua_Del = (select soLuongMua from deleted)
		set @soluongmua_Ins = (select soLuongMua from inserted)

		if exists (select * from inserted)
			begin
				if @soluongmua_Del is NULL
					begin
						set @soluongmua_Del = 0
						set @soluongmua_Cur = @soluongmua_Del - @soluongmua_Ins
						update SACH set soLuongCon = soLuongCon + (@soluongmua_Cur) from inserted where SACH.maSach = inserted.maSach
					end
				else
					begin
						set @soluongmua_Cur = @soluongmua_Del - @soluongmua_Ins
						update SACH set soLuongCon = soLuongCon + (@soluongmua_Cur) from inserted where SACH.maSach = inserted.maSach
					end
			end
		else
			begin
				update SACH set soLuongCon = soLuongCon + @soluongmua_Del from deleted where SACH.maSach = deleted.maSach
			end
		exec dbo.tinh_TongTien
	end
go

/*=============================================================
======================== AUTHORIZATION  =======================
=============================================================*/
--Tao user NV1
create login NV1 with password = 'NV1KHO'
create user NV1 for login NV1

--Phan quyen
GRANT SELECT, INSERT, UPDATE,DELETE ON SACH to NV1
GRANT SELECT, INSERT, UPDATE,DELETE ON PHIEUNHAP to NV1
GRANT SELECT, INSERT, UPDATE,DELETE ON CHITIETPHIEUNHAP to NV1
GRANT SELECT, INSERT, UPDATE,DELETE ON TACGIA to NV1
GRANT SELECT, INSERT, UPDATE,DELETE ON THELOAI to NV1

--Tao user NV2
create login NV2 with password = 'NV2THUNGAN'
create user NV2 for login NV2

--Phan quyen
GRANT SELECT, INSERT, UPDATE, DELETE ON HOADON to NV2
GRANT SELECT, INSERT, UPDATE, DELETE ON CHITIETHOADON to NV2
GRANT SELECT, INSERT, UPDATE, DELETE ON KHACHHANG to NV2
GRANT UPDATE, SELECT ON SACH to NV2
GRANT SELECT ON TACGIA to NV2
GRANT SELECT ON THELOAI to NV2
